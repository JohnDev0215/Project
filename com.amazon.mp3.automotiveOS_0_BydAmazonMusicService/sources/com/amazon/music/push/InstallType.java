package com.amazon.music.push;

/* JADX INFO: loaded from: classes4.dex */
public enum InstallType {
    PRELOAD("Preload"),
    DOWNLOAD("Download");

    private final String name;

    InstallType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
