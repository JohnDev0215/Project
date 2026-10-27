package com.spotify.cosmos.cosmonaut;

import java.lang.reflect.Type;

/* JADX INFO: loaded from: classes.dex */
public interface Converter<From, To> {

    public interface Factory {
        Converter<?, byte[]> createRequestConverter();

        Converter<byte[], ?> createResponseConverter();
    }

    boolean canHandle(Type type);

    To convert(Type type, From from);
}
