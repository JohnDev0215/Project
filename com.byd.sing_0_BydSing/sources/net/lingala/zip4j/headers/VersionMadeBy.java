package net.lingala.zip4j.headers;

/* JADX INFO: loaded from: classes.dex */
public enum VersionMadeBy {
    SPECIFICATION_VERSION((byte) 51),
    WINDOWS((byte) 0),
    UNIX((byte) 3);

    private final byte code;

    VersionMadeBy(byte b2) {
        this.code = b2;
    }

    public byte getCode() {
        return this.code;
    }
}
