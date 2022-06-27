package dev.rooftop.challenge.data.model;

public class Endpoints {
    private static final String endpointPrefix = "https://rooftop-career-switch.herokuapp.com";
    public static final String getTokenEndpoint = endpointPrefix + "/token?email={email}";
    public static final String getBlocksEndpoint = endpointPrefix + "/blocks?token={token}";
    public static final String getSecuentialCheckEndpoint = endpointPrefix + "/check?token={token}";
    public static final String getFinalCheckEndpoint = endpointPrefix + "/check?token={token}";
}
