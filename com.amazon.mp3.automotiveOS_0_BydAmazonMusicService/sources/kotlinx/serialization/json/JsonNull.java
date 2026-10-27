package kotlinx.serialization.json;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096D¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/serialization/json/JsonNull;", "Lkotlinx/serialization/json/JsonPrimitive;", "", "content", "Ljava/lang/String;", "getContent", "()Ljava/lang/String;", "<init>", "()V", "kotlinx-serialization-json"}, k = 1, mv = {1, 7, 1})
@Serializable(with = JsonNullSerializer.class)
public final class JsonNull extends JsonPrimitive {
    public static final JsonNull INSTANCE = new JsonNull();
    private static final String content = "null";
    private static final /* synthetic */ Lazy<KSerializer<Object>> $cachedSerializer$delegate = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.PUBLICATION, (Function0) new Function0<KSerializer<Object>>() { // from class: kotlinx.serialization.json.JsonNull$$cachedSerializer$delegate$1
        @Override // kotlin.jvm.functions.Function0
        public final KSerializer<Object> invoke() {
            return JsonNullSerializer.INSTANCE;
        }
    });

    private JsonNull() {
        super(null);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return content;
    }
}
