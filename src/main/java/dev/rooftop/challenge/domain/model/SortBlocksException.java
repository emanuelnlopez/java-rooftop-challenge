package dev.rooftop.challenge.domain.model;

import dev.rooftop.challenge.domain.service.SortBlocksService;

public class SortBlocksException extends Exception {
    public SortBlocksException(String message) {
        super(message);
    }
}
