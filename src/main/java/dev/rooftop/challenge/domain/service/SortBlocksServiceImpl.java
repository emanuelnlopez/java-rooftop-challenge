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

        var i = 0;

        sorted.add((original.get(i)));
        original.remove(i);

        while (!original.isEmpty() && i < original.size()) {
            System.out.println("Comparando: " + sorted.get(sorted.size() - 1).substring(0, 7) + "... contra: " + original.get(i).substring(0, 7) + "...");

            var data = new String[]{sorted.get(sorted.size() - 1), original.get(i)};

            var result = repository.sequentialCheck(token, data);

            if (result == true) {
                sorted.add(original.get(i));
                original.remove(i);
                i = 0;
            } else {
                i++;
            }
        }

        var result = repository.finalCheck(token, String.join("", sorted));

        if (!result) {
            throw new SortBlocksException("No se logró ordenar los bloques");
        }

        String[] resultArray = new String[sorted.size()];
        return sorted.toArray(resultArray);
    }
}
