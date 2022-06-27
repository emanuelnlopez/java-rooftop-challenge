package dev.rooftop.challenge.data.model;

public class Blocks {
    private String[] data;
    private long chunkSize;
    private long length;

    public String[] getData() { return data; }
    public void setData(String[] value) { this.data = value; }

    public long getChunkSize() { return chunkSize; }
    public void setChunkSize(long value) { this.chunkSize = value; }

    public long getLength() { return length; }
    public void setLength(long value) { this.length = value; }
}
