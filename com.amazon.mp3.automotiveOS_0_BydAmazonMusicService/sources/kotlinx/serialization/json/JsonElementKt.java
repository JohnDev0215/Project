package kotlinx.serialization.json;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlinx.serialization.json.internal.StringOpsKt;

/* JADX INFO: compiled from: JsonElement.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0004\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\r\u001a\u0017\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0010\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0005\u001a\u0010\u0010\u0003\u001a\u00020\u00022\b\u0010\u0001\u001a\u0004\u0018\u00010\u0006\u001a\u0014\u0010\n\u001a\u00020\t*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0006H\u0002\"\u0015\u0010\r\u001a\u00020\u0002*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\"\u0015\u0010\u0011\u001a\u00020\u000e*\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u0015\u0010\u0015\u001a\u00020\u0012*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0015\u0010\u0019\u001a\u00020\u0016*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018\"\u0017\u0010\u001c\u001a\u0004\u0018\u00010\u0016*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\"\u0015\u0010 \u001a\u00020\u001d*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0017\u0010#\u001a\u0004\u0018\u00010\u001d*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b!\u0010\"\"\u0015\u0010'\u001a\u00020$*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b%\u0010&\"\u0015\u0010*\u001a\u00020\u0000*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b(\u0010)\"\u0017\u0010-\u001a\u0004\u0018\u00010\u0000*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b+\u0010,\"\u0017\u00100\u001a\u0004\u0018\u00010\u0006*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b.\u0010/¨\u00061"}, d2 = {"", "value", "Lkotlinx/serialization/json/JsonPrimitive;", "JsonPrimitive", "(Ljava/lang/Boolean;)Lkotlinx/serialization/json/JsonPrimitive;", "", "", "Lkotlinx/serialization/json/JsonElement;", "element", "", "error", "getJsonPrimitive", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonPrimitive;", "jsonPrimitive", "Lkotlinx/serialization/json/JsonObject;", "getJsonObject", "(Lkotlinx/serialization/json/JsonElement;)Lkotlinx/serialization/json/JsonObject;", "jsonObject", "", "getInt", "(Lkotlinx/serialization/json/JsonPrimitive;)I", "int", "", "getLong", "(Lkotlinx/serialization/json/JsonPrimitive;)J", "long", "getLongOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Long;", "longOrNull", "", "getDouble", "(Lkotlinx/serialization/json/JsonPrimitive;)D", "double", "getDoubleOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Double;", "doubleOrNull", "", "getFloat", "(Lkotlinx/serialization/json/JsonPrimitive;)F", "float", "getBoolean", "(Lkotlinx/serialization/json/JsonPrimitive;)Z", "boolean", "getBooleanOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/Boolean;", "booleanOrNull", "getContentOrNull", "(Lkotlinx/serialization/json/JsonPrimitive;)Ljava/lang/String;", "contentOrNull", "kotlinx-serialization-json"}, k = 2, mv = {1, 7, 1})
public final class JsonElementKt {
    public static final JsonPrimitive JsonPrimitive(Boolean bool) {
        if (bool == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(bool, false);
    }

    public static final JsonPrimitive JsonPrimitive(Number number) {
        if (number == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(number, false);
    }

    public static final JsonPrimitive JsonPrimitive(String str) {
        if (str == null) {
            return JsonNull.INSTANCE;
        }
        return new JsonLiteral(str, true);
    }

    public static final JsonPrimitive getJsonPrimitive(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonPrimitive jsonPrimitive = jsonElement instanceof JsonPrimitive ? (JsonPrimitive) jsonElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        error(jsonElement, "JsonPrimitive");
        throw new KotlinNothingValueException();
    }

    public static final JsonObject getJsonObject(JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(jsonElement, "<this>");
        JsonObject jsonObject = jsonElement instanceof JsonObject ? (JsonObject) jsonElement : null;
        if (jsonObject != null) {
            return jsonObject;
        }
        error(jsonElement, "JsonObject");
        throw new KotlinNothingValueException();
    }

    public static final int getInt(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Integer.parseInt(jsonPrimitive.getContent());
    }

    public static final long getLong(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Long.parseLong(jsonPrimitive.getContent());
    }

    public static final Long getLongOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt__StringNumberConversionsKt.toLongOrNull(jsonPrimitive.getContent());
    }

    public static final double getDouble(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Double.parseDouble(jsonPrimitive.getContent());
    }

    public static final Double getDoubleOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(jsonPrimitive.getContent());
    }

    public static final float getFloat(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return Float.parseFloat(jsonPrimitive.getContent());
    }

    public static final boolean getBoolean(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        Boolean booleanStrictOrNull = StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
        if (booleanStrictOrNull != null) {
            return booleanStrictOrNull.booleanValue();
        }
        throw new IllegalStateException(jsonPrimitive + " does not represent a Boolean");
    }

    public static final Boolean getBooleanOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        return StringOpsKt.toBooleanStrictOrNull(jsonPrimitive.getContent());
    }

    public static final String getContentOrNull(JsonPrimitive jsonPrimitive) {
        Intrinsics.checkNotNullParameter(jsonPrimitive, "<this>");
        if (jsonPrimitive instanceof JsonNull) {
            return null;
        }
        return jsonPrimitive.getContent();
    }

    private static final Void error(JsonElement jsonElement, String str) {
        throw new IllegalArgumentException("Element " + Reflection.getOrCreateKotlinClass(jsonElement.getClass()) + " is not a " + str);
    }
}
