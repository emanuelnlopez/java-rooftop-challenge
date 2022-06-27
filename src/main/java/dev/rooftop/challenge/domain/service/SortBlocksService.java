package dev.rooftop.challenge.domain.service;

import dev.rooftop.challenge.domain.model.SortBlocksException;

public interface SortBlocksService {

    public String getToken(String email) throws SortBlocksException;

    public String[] getBlocks(String token) throws SortBlocksException;

    public String[] check(String token, String[] input) throws SortBlocksException;
}
