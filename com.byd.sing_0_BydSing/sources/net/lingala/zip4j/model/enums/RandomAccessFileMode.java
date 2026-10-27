package net.lingala.zip4j.model.enums;

/* JADX INFO: loaded from: classes.dex */
public enum RandomAccessFileMode {
    READ("r"),
    WRITE("rw");

    private String value;

    RandomAccessFileMode(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
