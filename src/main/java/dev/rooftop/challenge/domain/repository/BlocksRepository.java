package dev.rooftop.challenge.domain.repository;

import dev.rooftop.challenge.data.model.Blocks;
import dev.rooftop.challenge.data.model.Token;
import dev.rooftop.challenge.domain.model.SortBlocksException;

public interface BlocksRepository {

    public String getToken(String email) throws SortBlocksException;

    public String[] getBlocks(String token) throws SortBlocksException;

    public boolean sequentialCheck(String token, String[] blocks) throws SortBlocksException;

    public boolean finalCheck(String token, String blocks) throws SortBlocksException;
}
