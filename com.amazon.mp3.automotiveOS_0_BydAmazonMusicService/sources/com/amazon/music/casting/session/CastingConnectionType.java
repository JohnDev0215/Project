package com.amazon.music.casting.session;

/* JADX INFO: loaded from: classes4.dex */
public enum CastingConnectionType {
    AUTOMATIC("automatic"),
    MANUAL("manual");

    private String name;

    CastingConnectionType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
