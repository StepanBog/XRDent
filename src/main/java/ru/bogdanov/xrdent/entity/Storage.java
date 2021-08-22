package ru.bogdanov.xrdent.entity;

import java.sql.Blob;

public class Storage {

    private Long id;
    private byte[] zip;
    private Blob blob;
    public void setZip(byte[] bytes) {
        zip = bytes;
    }

    public byte[] getZip() {
        return zip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Blob getBlob() {
        return blob;
    }

    public void setBlob(Blob blob) {
        this.blob = blob;
    }

    public void setID(long id) {
        this.id = id;
    }
}
