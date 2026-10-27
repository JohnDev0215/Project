package com.sun.activation.registries;

/* JADX INFO: loaded from: classes3.dex */
public class MimeTypeEntry {
    private String extension;
    private String type;

    public MimeTypeEntry(String str, String str2) {
        this.type = str;
        this.extension = str2;
    }

    public String getMIMEType() {
        return this.type;
    }

    public String getFileExtension() {
        return this.extension;
    }

    public String toString() {
        return new StringBuffer("MIMETypeEntry: ").append(this.type).append(", ").append(this.extension).toString();
    }
}
