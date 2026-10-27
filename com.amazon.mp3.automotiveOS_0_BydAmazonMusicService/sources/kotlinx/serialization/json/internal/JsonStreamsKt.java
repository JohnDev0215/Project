package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonEncoder;

/* JADX INFO: compiled from: JsonStreams.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a7\u0010\b\u001a\u00020\u0007\"\u0004\b\u0000\u0010\u0000*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0006\u0010\u0006\u001a\u00028\u0000H\u0001¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"T", "Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/json/internal/JsonWriter;", "writer", "Lkotlinx/serialization/SerializationStrategy;", "serializer", "value", "", "encodeByWriter", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/JsonWriter;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "kotlinx-serialization-json"}, k = 2, mv = {1, 7, 1})
public final class JsonStreamsKt {
    @PublishedApi
    public static final <T> void encodeByWriter(Json json, JsonWriter writer, SerializationStrategy<? super T> serializer, T t) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(writer, "writer");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        new StreamingJsonEncoder(writer, json, WriteMode.OBJ, new JsonEncoder[WriteMode.values().length]).encodeSerializableValue(serializer, t);
    }
}
