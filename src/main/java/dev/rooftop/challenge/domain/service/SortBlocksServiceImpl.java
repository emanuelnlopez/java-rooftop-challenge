package dev.rooftop.challenge.domain.service;

import dev.rooftop.challenge.domain.model.SortBlocksException;
import dev.rooftop.challenge.domain.repository.BlocksRepository;
import dev.rooftop.challenge.data.repository.BlocksRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;

public class SortBlocksServiceImpl implements SortBlocksService {
    private BlocksRepository repository = new BlocksRepositoryImpl();

    @Override
    public String getToken(String email) throws SortBlocksException {
        return repository.getToken(email);
    }

    @Override
    public String[] getBlocks(String token) throws SortBlocksException {
        return repository.getBlocks(token);
    }

    @Override
    public String[] check(String token, String[] input) throws SortBlocksException {
        var original = new ArrayList<String>(Arrays.asList(input));
        var sorted = new ArrayList<String>();

        var index = 0;
        var serverCallNumber = 0;

        sorted.add((original.get(index)));
        original.remove(index);

        while (!original.isEmpty() && index < original.size()) {

            /// Si en la lista original solo queda un bloque,
            /// no es necesario realizar la comparación con una llamada al endpoint.
            /// Es el último elemento.
            if (original.size() == 1) {
                sorted.add(original.get(index));
                original.remove(index);
                index = 0;
            } else {
                serverCallNumber++;
                System.out.println(String.format("%02d", serverCallNumber) + ": Comparando: " + sorted.get(sorted.size() - 1).substring(0, 7) + "... contra: " + original.get(index).substring(0, 7) + "...");

                var data = new String[]{sorted.get(sorted.size() - 1), original.get(index)};

                var result = repository.sequentialCheck(token, data);

                if (result == true) {
                    sorted.add(original.get(index));
                    original.remove(index);
                    index = 0;
                } else {
                    index++;
                }
            }
        }

        var result = repository.finalCheck(token, String.join("", sorted));
        System.out.println();
        System.out.println("Final Check: Están los bloques ordenados? " + result);

        if (!result) {
            throw new SortBlocksException("No se logró ordenar los bloques");
        }

        String[] resultArray = new String[sorted.size()];
        return sorted.toArray(resultArray);
    }
}
