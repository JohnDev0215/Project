package com.amazon.music.media.auto.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kotlin.Metadata;

/* JADX INFO: compiled from: Mappers.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0003\u001a\u00020\u0002R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/music/media/auto/util/Mappers;", "", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "mapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "getMapper", "()Lcom/fasterxml/jackson/databind/ObjectMapper;", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class Mappers {
    public static final Mappers INSTANCE = new Mappers();
    private static final ObjectMapper mapper;

    private Mappers() {
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        mapper = objectMapper;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public final ObjectMapper mapper() {
        return mapper;
    }
}
