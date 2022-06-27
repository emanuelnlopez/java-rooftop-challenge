package dev.rooftop.challenge.data.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dev.rooftop.challenge.data.model.Blocks;
import dev.rooftop.challenge.data.model.Check;
import dev.rooftop.challenge.data.model.Endpoints;
import dev.rooftop.challenge.data.model.Token;
import dev.rooftop.challenge.domain.model.SortBlocksException;
import dev.rooftop.challenge.domain.repository.BlocksRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class BlocksRepositoryImpl implements BlocksRepository {
    private HttpClient client = HttpClient.newHttpClient();
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public String getToken(String email) throws SortBlocksException {
        try {
            var uri = URI.create(Endpoints.getTokenEndpoint.replace("{email}", email));

            var request = HttpRequest.newBuilder(uri).build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var token = gson.fromJson(response.body(), Token.class);

            return token.getToken();
        } catch (Exception e) {
            throw new SortBlocksException(e.getMessage());
        }
    }

    @Override
    public String[] getBlocks(String token) throws SortBlocksException {
        try {
            var uri = URI.create(Endpoints.getBlocksEndpoint.replace("{token}", token));

            var request = HttpRequest.newBuilder(uri)
                    //.header("accept", "application/json")
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var blocks = gson.fromJson(response.body(), Blocks.class);

            return blocks.getData();
        } catch (Exception e) {
            throw new SortBlocksException(e.getMessage());
        }
    }

    @Override
    public boolean sequentialCheck(String token, String[] blocks) throws SortBlocksException {
        try {
            var uri = URI.create(Endpoints.getSecuentialCheckEndpoint.replace("{token}", token));

            var body = gson.toJson(Map.of("blocks", blocks));

            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            gson = new GsonBuilder().setPrettyPrinting().create();

            var result = gson.fromJson(response.body(), Check.class);

            return result.getMessage();
        } catch (Exception e) {
            throw new SortBlocksException(e.getMessage());
        }
    }

    @Override
    public boolean finalCheck(String token, String blocks) throws SortBlocksException {
        try {
            var uri = URI.create(Endpoints.getFinalCheckEndpoint.replace("{token}", token));

            var body = gson.toJson(Map.of("encoded", blocks));

            var request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body))
                    .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString());

            var result = gson.fromJson(response.body(), Check.class);

            return result.getMessage();
        } catch (Exception e) {
            throw new SortBlocksException(e.getMessage());
        }
    }
}
