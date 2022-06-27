package dev.rooftop.challenge;

import dev.rooftop.challenge.domain.model.SortBlocksException;
import dev.rooftop.challenge.domain.service.SortBlocksService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainTest {

    String[] blocks;
    SortBlocksService service;
    String[] sortedBlocks;
    String token;

    @Before
    public void setUp() {
        service = Mockito.mock(SortBlocksService.class);
        blocks = new String[]{"qwerty", "asdfgh", "zxcvbn", "tyuiop", "fghjkl", "xcvbnm"};
        sortedBlocks = new String[]{"asdfgh", "fghjkl", "qwerty", "tyuiop", "xcvbnm", "zxcvbn"};
        token = "randomToken";
    }

    @Test
    public void shouldSortBlocksSuccessfully() {
        try {
            // Arrange
            when(service.check(token, blocks)).thenReturn(sortedBlocks);

            // Act
            var sortedBlocks = service.check(token, blocks);

            // Assert
            assertEquals(blocks.length, sortedBlocks.length);
            Assert.assertArrayEquals(sortedBlocks, sortedBlocks);
        } catch (SortBlocksException sbe) {}
    }

    @Test(expected = SortBlocksException.class)
    public void shouldThrowSortBlocksException() throws SortBlocksException {
            // Arrange
            when(service.check(token, blocks)).thenThrow(SortBlocksException.class);

            // Act & Assert
            var sortedBlocks = service.check(token, blocks);
    }
}
