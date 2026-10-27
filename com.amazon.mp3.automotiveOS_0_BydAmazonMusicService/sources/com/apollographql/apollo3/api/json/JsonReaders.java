package com.apollographql.apollo3.api.json;

import com.apollographql.apollo3.api.json.JsonReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* JADX INFO: renamed from: com.apollographql.apollo3.api.json.-JsonReaders, reason: invalid class name */
/* JADX INFO: compiled from: JsonReaders.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0018\u0010\u0003\u001a\u00020\u0002*\u0010\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0004\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0006\u001a\u000e\u0010\u0007\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0007¨\u0006\b"}, d2 = {"guessNumber", "", "Lcom/apollographql/apollo3/api/json/JsonReader;", "jsonReader", "", "", "Lokio/BufferedSource;", "readAny", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
@JvmName(name = "-JsonReaders")
public final class JsonReaders {

    /* JADX INFO: renamed from: com.apollographql.apollo3.api.json.-JsonReaders$WhenMappings */
    /* JADX INFO: compiled from: JsonReaders.kt */
    @Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[JsonReader.Token.values().length];
            iArr[JsonReader.Token.NULL.ordinal()] = 1;
            iArr[JsonReader.Token.BOOLEAN.ordinal()] = 2;
            iArr[JsonReader.Token.LONG.ordinal()] = 3;
            iArr[JsonReader.Token.NUMBER.ordinal()] = 4;
            iArr[JsonReader.Token.STRING.ordinal()] = 5;
            iArr[JsonReader.Token.BEGIN_OBJECT.ordinal()] = 6;
            iArr[JsonReader.Token.BEGIN_ARRAY.ordinal()] = 7;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final JsonReader jsonReader(BufferedSource bufferedSource) {
        Intrinsics.checkNotNullParameter(bufferedSource, "<this>");
        return new BufferedSourceJsonReader(bufferedSource);
    }

    public static final JsonReader jsonReader(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "<this>");
        return new MapJsonReader(map);
    }

    public static final Object readAny(JsonReader jsonReader) throws IOException {
        Intrinsics.checkNotNullParameter(jsonReader, "<this>");
        JsonReader.Token tokenPeek = jsonReader.peek();
        switch (WhenMappings.$EnumSwitchMapping$0[tokenPeek.ordinal()]) {
            case 1:
                return jsonReader.nextNull();
            case 2:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 3:
            case 4:
                return guessNumber(jsonReader);
            case 5:
                return jsonReader.nextString();
            case 6:
                jsonReader.beginObject();
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                while (jsonReader.hasNext()) {
                    linkedHashMap.put(jsonReader.nextName(), readAny(jsonReader));
                }
                jsonReader.endObject();
                return linkedHashMap;
            case 7:
                jsonReader.beginArray();
                ArrayList arrayList = new ArrayList();
                while (jsonReader.hasNext()) {
                    arrayList.add(readAny(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            default:
                throw new IllegalStateException(Intrinsics.stringPlus("unknown token ", tokenPeek).toString());
        }
    }

    private static final Object guessNumber(JsonReader jsonReader) {
        try {
            try {
                try {
                    return Integer.valueOf(jsonReader.nextInt());
                } catch (Exception unused) {
                    return Double.valueOf(jsonReader.nextDouble());
                }
            } catch (Exception unused2) {
                return Long.valueOf(jsonReader.nextLong());
            }
        } catch (Exception unused3) {
            return jsonReader.nextNumber();
        }
    }
}
