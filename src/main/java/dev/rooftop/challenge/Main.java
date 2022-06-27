package dev.rooftop.challenge;

import dev.rooftop.challenge.domain.model.SortBlocksException;
import dev.rooftop.challenge.domain.service.SortBlocksServiceImpl;

public class Main {
    public static void main(String[] args) {
        try {
            var service = new SortBlocksServiceImpl();

            var token = service.getToken("emanuel.n.lopez@gmail.com");
            System.out.println("Token obtenido: " + token);
            System.out.println();

            var blocks = service.getBlocks(token);
            System.out.println("Bloques obtenidos:");
            for (String block : blocks) {
                System.out.println(" - " + block);
            }
            System.out.println();

            var sortedBlocks = service.check(token, blocks);
            System.out.println();
            System.out.println("Bloques ordenados:");
            for (String block : sortedBlocks) {
                System.out.println(" - " + block);
            }

        } catch (SortBlocksException sbe) {
            System.out.println("Ha ocurrido un error: " + sbe.getMessage());
        }
    }
}