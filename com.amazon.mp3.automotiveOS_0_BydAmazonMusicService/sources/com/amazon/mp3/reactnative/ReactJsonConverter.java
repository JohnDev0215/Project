package com.amazon.mp3.reactnative;

import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ReactJsonConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\t\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/reactnative/ReactJsonConverter;", "", "Lorg/json/JSONObject;", "jsonObject", "Lcom/facebook/react/bridge/WritableMap;", "convertJsonToMap", "Lorg/json/JSONArray;", "jsonArray", "Lcom/facebook/react/bridge/WritableArray;", "convertJsonToArray", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ReactJsonConverter {
    public static final ReactJsonConverter INSTANCE = new ReactJsonConverter();

    private ReactJsonConverter() {
    }

    public final WritableMap convertJsonToMap(JSONObject jsonObject) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonObject, "jsonObject");
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        Iterator<String> itKeys = jsonObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object obj = jsonObject.get(next);
            if (obj == null) {
                writableNativeMap.putNull(next);
            } else if (obj instanceof JSONObject) {
                writableNativeMap.putMap(next, convertJsonToMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeMap.putArray(next, convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeMap.putBoolean(next, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeMap.putInt(next, ((Number) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeMap.putDouble(next, ((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeMap.putString(next, (String) obj);
            } else {
                writableNativeMap.putString(next, obj.toString());
            }
        }
        return writableNativeMap;
    }

    public final WritableArray convertJsonToArray(JSONArray jsonArray) throws JSONException {
        Intrinsics.checkNotNullParameter(jsonArray, "jsonArray");
        WritableNativeArray writableNativeArray = new WritableNativeArray();
        int length = jsonArray.length();
        int i = 0;
        while (i < length) {
            int i2 = i + 1;
            Object obj = jsonArray.get(i);
            if (obj == null) {
                writableNativeArray.pushNull();
            } else if (obj instanceof JSONObject) {
                writableNativeArray.pushMap(convertJsonToMap((JSONObject) obj));
            } else if (obj instanceof JSONArray) {
                writableNativeArray.pushArray(convertJsonToArray((JSONArray) obj));
            } else if (obj instanceof Boolean) {
                writableNativeArray.pushBoolean(((Boolean) obj).booleanValue());
            } else if (obj instanceof Integer) {
                writableNativeArray.pushInt(((Number) obj).intValue());
            } else if (obj instanceof Double) {
                writableNativeArray.pushDouble(((Number) obj).doubleValue());
            } else if (obj instanceof String) {
                writableNativeArray.pushString((String) obj);
            } else {
                writableNativeArray.pushString(obj.toString());
            }
            i = i2;
        }
        return writableNativeArray;
    }
}
