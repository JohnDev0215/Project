package com.spotify.connectivity.pubsub;

import java.util.List;
import java.util.Map;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class PushedMessageSource {
    private final Map<String, String> attributes;
    private final String ident;
    private final List<byte[]> payload;

    public PushedMessageSource(String str, List<byte[]> list, Map<String, String> map) {
        v03.j(str, "ident");
        v03.j(list, "payload");
        v03.j(map, "attributes");
        this.ident = str;
        this.payload = list;
        this.attributes = map;
    }

    public final Map<String, String> getAttributes() {
        return this.attributes;
    }

    public final String getIdent() {
        return this.ident;
    }

    public final List<byte[]> getPayload() {
        return this.payload;
    }
}
