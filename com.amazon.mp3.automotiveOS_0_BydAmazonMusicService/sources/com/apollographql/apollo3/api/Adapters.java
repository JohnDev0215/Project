package com.apollographql.apollo3.api;

import com.apollographql.apollo3.api.json.JsonReader;
import com.apollographql.apollo3.api.json.JsonReaders;
import com.apollographql.apollo3.api.json.JsonWriter;
import com.apollographql.apollo3.api.json.JsonWriters;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Adapters.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000`\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000e\u001a)\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u0004\u0010\u0005\u001a%\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\b\u0010\t\u001a/\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\r\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u00022\b\b\u0002\u0010\f\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u000e\u0010\u000f\u001a%\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00000\u0011\"\u0004\b\u0000\u0010\u0001*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0007¢\u0006\u0004\b\u0012\u0010\u0013\" \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00150\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u0012\u0004\b\u0018\u0010\u0019\" \u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u0017\u0012\u0004\b\u001c\u0010\u0019\" \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b\u001e\u0010\u0017\u0012\u0004\b\u001f\u0010\u0019\" \u0010!\u001a\b\u0012\u0004\u0012\u00020 0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b!\u0010\u0017\u0012\u0004\b\"\u0010\u0019\" \u0010$\u001a\b\u0012\u0004\u0012\u00020#0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b$\u0010\u0017\u0012\u0004\b%\u0010\u0019\" \u0010&\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b&\u0010\u0017\u0012\u0004\b'\u0010\u0019\" \u0010(\u001a\b\u0012\u0004\u0012\u00020\u00000\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b(\u0010\u0017\u0012\u0004\b)\u0010\u0019\" \u0010+\u001a\b\u0012\u0004\u0012\u00020*0\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\b+\u0010\u0017\u0012\u0004\b,\u0010\u0019\" \u0010-\u001a\b\u0012\u0004\u0012\u00020\u00150\u00038\u0006X\u0087\u0004¢\u0006\f\n\u0004\b-\u0010.\u0012\u0004\b/\u0010\u0019\" \u00100\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00038\u0006X\u0087\u0004¢\u0006\f\n\u0004\b0\u0010.\u0012\u0004\b1\u0010\u0019\" \u00102\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00038\u0006X\u0087\u0004¢\u0006\f\n\u0004\b2\u0010.\u0012\u0004\b3\u0010\u0019\" \u00104\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00038\u0006X\u0087\u0004¢\u0006\f\n\u0004\b4\u0010.\u0012\u0004\b5\u0010\u0019\" \u00106\u001a\b\u0012\u0004\u0012\u00020\u00000\u00038\u0006X\u0087\u0004¢\u0006\f\n\u0004\b6\u0010.\u0012\u0004\b7\u0010\u0019¨\u00068"}, d2 = {"", "T", "Lcom/apollographql/apollo3/api/Adapter;", "Lcom/apollographql/apollo3/api/NullableAdapter;", "-nullable", "(Lcom/apollographql/apollo3/api/Adapter;)Lcom/apollographql/apollo3/api/NullableAdapter;", "nullable", "Lcom/apollographql/apollo3/api/ListAdapter;", "-list", "(Lcom/apollographql/apollo3/api/Adapter;)Lcom/apollographql/apollo3/api/ListAdapter;", "list", "", "buffered", "Lcom/apollographql/apollo3/api/ObjectAdapter;", "-obj", "(Lcom/apollographql/apollo3/api/Adapter;Z)Lcom/apollographql/apollo3/api/ObjectAdapter;", "obj", "Lcom/apollographql/apollo3/api/OptionalAdapter;", "-optional", "(Lcom/apollographql/apollo3/api/Adapter;)Lcom/apollographql/apollo3/api/OptionalAdapter;", "optional", "", "StringAdapter", "Lcom/apollographql/apollo3/api/Adapter;", "getStringAdapter$annotations", "()V", "", "IntAdapter", "getIntAdapter$annotations", "", "DoubleAdapter", "getDoubleAdapter$annotations", "", "FloatAdapter", "getFloatAdapter$annotations", "", "LongAdapter", "getLongAdapter$annotations", "BooleanAdapter", "getBooleanAdapter$annotations", "AnyAdapter", "getAnyAdapter$annotations", "Lcom/apollographql/apollo3/api/Upload;", "UploadAdapter", "getUploadAdapter$annotations", "NullableStringAdapter", "Lcom/apollographql/apollo3/api/NullableAdapter;", "getNullableStringAdapter$annotations", "NullableDoubleAdapter", "getNullableDoubleAdapter$annotations", "NullableIntAdapter", "getNullableIntAdapter$annotations", "NullableBooleanAdapter", "getNullableBooleanAdapter$annotations", "NullableAnyAdapter", "getNullableAnyAdapter$annotations", "apollo-api"}, k = 2, mv = {1, 5, 1})
@JvmName(name = "Adapters")
public final class Adapters {

    @JvmField
    public static final Adapter<Object> AnyAdapter;

    @JvmField
    public static final Adapter<Boolean> BooleanAdapter;

    @JvmField
    public static final Adapter<Double> DoubleAdapter;

    @JvmField
    public static final Adapter<Float> FloatAdapter;

    @JvmField
    public static final Adapter<Integer> IntAdapter;

    @JvmField
    public static final Adapter<Long> LongAdapter;

    @JvmField
    public static final NullableAdapter<Object> NullableAnyAdapter;

    @JvmField
    public static final NullableAdapter<Boolean> NullableBooleanAdapter;

    @JvmField
    public static final NullableAdapter<Double> NullableDoubleAdapter;

    @JvmField
    public static final NullableAdapter<Integer> NullableIntAdapter;

    @JvmField
    public static final NullableAdapter<String> NullableStringAdapter;

    @JvmField
    public static final Adapter<String> StringAdapter;

    @JvmField
    public static final Adapter<Upload> UploadAdapter;

    static {
        Adapter<String> adapter = new Adapter<String>() { // from class: com.apollographql.apollo3.api.Adapters$StringAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public String fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) throws IOException {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                String strNextString = reader.nextString();
                Intrinsics.checkNotNull(strNextString);
                return strNextString;
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, String value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.value(value);
            }
        };
        StringAdapter = adapter;
        Adapter<Integer> adapter2 = new Adapter<Integer>() { // from class: com.apollographql.apollo3.api.Adapters$IntAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, Integer num) throws IOException {
                toJson(jsonWriter, customScalarAdapters, num.intValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Integer fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return Integer.valueOf(reader.nextInt());
            }

            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, int value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                writer.value(value);
            }
        };
        IntAdapter = adapter2;
        Adapter<Double> adapter3 = new Adapter<Double>() { // from class: com.apollographql.apollo3.api.Adapters$DoubleAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, Double d) throws IOException {
                toJson(jsonWriter, customScalarAdapters, d.doubleValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Double fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return Double.valueOf(reader.nextDouble());
            }

            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, double value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                writer.value(value);
            }
        };
        DoubleAdapter = adapter3;
        FloatAdapter = new Adapter<Float>() { // from class: com.apollographql.apollo3.api.Adapters$FloatAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, Float f) throws IOException {
                toJson(jsonWriter, customScalarAdapters, f.floatValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Float fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return Float.valueOf((float) reader.nextDouble());
            }

            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, float value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                writer.value(value);
            }
        };
        LongAdapter = new Adapter<Long>() { // from class: com.apollographql.apollo3.api.Adapters$LongAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, Long l) throws IOException {
                toJson(jsonWriter, customScalarAdapters, l.longValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Long fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return Long.valueOf(reader.nextLong());
            }

            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, long value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                writer.value(value);
            }
        };
        Adapter<Boolean> adapter4 = new Adapter<Boolean>() { // from class: com.apollographql.apollo3.api.Adapters$BooleanAdapter$1
            @Override // com.apollographql.apollo3.api.Adapter
            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, CustomScalarAdapters customScalarAdapters, Boolean bool) throws IOException {
                toJson(jsonWriter, customScalarAdapters, bool.booleanValue());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Boolean fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return Boolean.valueOf(reader.nextBoolean());
            }

            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, boolean value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                writer.value(value);
            }
        };
        BooleanAdapter = adapter4;
        Adapter<Object> adapter5 = new Adapter<Object>() { // from class: com.apollographql.apollo3.api.Adapters$AnyAdapter$1
            public final Object fromJson(JsonReader reader) throws IOException {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Object any = JsonReaders.readAny(reader);
                Intrinsics.checkNotNull(any);
                return any;
            }

            public final void toJson(JsonWriter writer, Object value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(value, "value");
                JsonWriters.writeAny(writer, value);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public Object fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                return fromJson(reader);
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Object value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                toJson(writer, value);
            }
        };
        AnyAdapter = adapter5;
        UploadAdapter = new Adapter<Upload>() { // from class: com.apollographql.apollo3.api.Adapters$UploadAdapter$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.apollographql.apollo3.api.Adapter
            public Upload fromJson(JsonReader reader, CustomScalarAdapters customScalarAdapters) {
                Intrinsics.checkNotNullParameter(reader, "reader");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                throw new IllegalStateException("File Upload used in output position".toString());
            }

            @Override // com.apollographql.apollo3.api.Adapter
            public void toJson(JsonWriter writer, CustomScalarAdapters customScalarAdapters, Upload value) throws IOException {
                Intrinsics.checkNotNullParameter(writer, "writer");
                Intrinsics.checkNotNullParameter(customScalarAdapters, "customScalarAdapters");
                Intrinsics.checkNotNullParameter(value, "value");
                writer.value(value);
            }
        };
        NullableStringAdapter = m2609nullable(adapter);
        NullableDoubleAdapter = m2609nullable(adapter3);
        NullableIntAdapter = m2609nullable(adapter2);
        NullableBooleanAdapter = m2609nullable(adapter4);
        NullableAnyAdapter = m2609nullable(adapter5);
    }

    @JvmName(name = "-nullable")
    /* JADX INFO: renamed from: -nullable, reason: not valid java name */
    public static final <T> NullableAdapter<T> m2609nullable(Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new NullableAdapter<>(adapter);
    }

    @JvmName(name = "-list")
    /* JADX INFO: renamed from: -list, reason: not valid java name */
    public static final <T> ListAdapter<T> m2608list(Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new ListAdapter<>(adapter);
    }

    @JvmName(name = "-obj")
    /* JADX INFO: renamed from: -obj, reason: not valid java name */
    public static final <T> ObjectAdapter<T> m2610obj(Adapter<T> adapter, boolean z) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new ObjectAdapter<>(adapter, z);
    }

    /* JADX INFO: renamed from: -obj$default, reason: not valid java name */
    public static /* synthetic */ ObjectAdapter m2611obj$default(Adapter adapter, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return m2610obj(adapter, z);
    }

    @JvmName(name = "-optional")
    /* JADX INFO: renamed from: -optional, reason: not valid java name */
    public static final <T> OptionalAdapter<T> m2612optional(Adapter<T> adapter) {
        Intrinsics.checkNotNullParameter(adapter, "<this>");
        return new OptionalAdapter<>(adapter);
    }
}
