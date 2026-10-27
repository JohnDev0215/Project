package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b7\u0018\u0000 \t2\u00020\u0001:\u0001\tB\t\b\u0004¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u0006\u001a\u00020\u00028&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lkotlinx/serialization/json/JsonPrimitive;", "Lkotlinx/serialization/json/JsonElement;", "", "toString", "getContent", "()Ljava/lang/String;", "content", "<init>", "()V", "Companion", "Lkotlinx/serialization/json/JsonLiteral;", "Lkotlinx/serialization/json/JsonNull;", "kotlinx-serialization-json"}, k = 1, mv = {1, 7, 1})
@Serializable(with = JsonPrimitiveSerializer.class)
public abstract class JsonPrimitive extends JsonElement {
    public /* synthetic */ JsonPrimitive(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public abstract String getContent();

    private JsonPrimitive() {
        super(null);
    }

    public String toString() {
        return getContent();
    }
}
