package com.amazon.core.api;

import com.fasterxml.jackson.databind.ObjectMapper;

/* JADX INFO: loaded from: classes2.dex */
final class Mappers {
    private static final ObjectMapper mapper = new ObjectMapper();

    Mappers() {
    }

    public static final ObjectMapper mapper() {
        return mapper;
    }
}
