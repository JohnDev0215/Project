package com.qtxa.security;

/* JADX INFO: loaded from: classes.dex */
public class FileEncrypt {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static FileEncrypt f161a;

    private FileEncrypt() {
    }

    public static FileEncrypt b() {
        if (f161a == null) {
            synchronized (FileEncrypt.class) {
                f161a = new FileEncrypt();
            }
        }
        return f161a;
    }

    public String a(String str) {
        return null;
    }
}
