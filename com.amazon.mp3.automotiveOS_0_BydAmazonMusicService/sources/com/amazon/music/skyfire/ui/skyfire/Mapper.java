package com.amazon.music.skyfire.ui.skyfire;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Mapper.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J!\u0010\b\u001a\u0004\u0018\u00010\t\"\u0006\b\u0000\u0010\n\u0018\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH\u0086\bJ\u0010\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u0001J!\u0010\u000f\u001a\n\u0012\u0004\u0012\u0002H\n\u0018\u00010\f\"\u0006\b\u0000\u0010\n\u0018\u00012\u0006\u0010\u0010\u001a\u00020\tH\u0086\bJ \u0010\u0011\u001a\u0004\u0018\u0001H\n\"\u0006\b\u0000\u0010\n\u0018\u00012\u0006\u0010\u0010\u001a\u00020\tH\u0086\b¢\u0006\u0002\u0010\u0012R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/skyfire/ui/skyfire/Mapper;", "", "()V", "<set-?>", "Lcom/fasterxml/jackson/databind/ObjectMapper;", "instance", "getInstance", "()Lcom/fasterxml/jackson/databind/ObjectMapper;", "collectionToString", "", "T", "list", "", "objectToString", "obj", "stringToList", "string", "stringToObject", "(Ljava/lang/String;)Ljava/lang/Object;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class Mapper {
    public static final Mapper INSTANCE = new Mapper();
    private static ObjectMapper instance;

    private Mapper() {
    }

    public final ObjectMapper getInstance() {
        return instance;
    }

    static {
        ObjectMapper objectMapper = new ObjectMapper();
        instance = objectMapper;
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public final String objectToString(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "obj");
        return instance.writeValueAsString(obj);
    }

    public final /* synthetic */ <T> String collectionToString(List<? extends T> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ObjectMapper mapper = getInstance();
        TypeFactory typeFactory = getInstance().getTypeFactory();
        Intrinsics.reifiedOperationMarker(4, "T");
        return mapper.writerFor(typeFactory.constructCollectionType(List.class, Object.class)).writeValueAsString(list);
    }

    public final /* synthetic */ <T> T stringToObject(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        ObjectMapper mapper = getInstance();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (T) mapper.readValue(string, Object.class);
    }

    public final /* synthetic */ <T> List<T> stringToList(String string) {
        Intrinsics.checkNotNullParameter(string, "string");
        ObjectMapper mapper = getInstance();
        TypeFactory typeFactory = getInstance().getTypeFactory();
        Intrinsics.reifiedOperationMarker(4, "T");
        return (List) mapper.readValue(string, typeFactory.constructCollectionType(List.class, Object.class));
    }
}
