package com.amazon.mp3.video.stories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kotlin.Metadata;

/* JADX INFO: compiled from: Mappers.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/video/stories/Mappers;", "", "()V", "objectMapper", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "mapper", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Mappers {
    public static final Mappers INSTANCE = new Mappers();
    private static ObjectMapper objectMapper;

    private Mappers() {
    }

    static {
        ObjectMapper objectMapper2 = new ObjectMapper();
        objectMapper = objectMapper2;
        objectMapper2.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public final ObjectMapper mapper() {
        return objectMapper;
    }
}
