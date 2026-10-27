package com.amazon.mp3.ui.interaction;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.util.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONObject;

/* JADX INFO: compiled from: UiInteractionMappingUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J:\u0010\u0006\u001a6\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u0002j\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0004`\u0005J:\u0010\u0007\u001a6\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00040\u0002j\u001a\u0012\u0004\u0012\u00020\u0003\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0004`\u0005J\u000e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bR\u001f\u0010\f\u001a\n \u000b*\u0004\u0018\u00010\u00030\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/mp3/ui/interaction/UiInteractionMappingUtil;", "", "Ljava/util/HashMap;", "", "", "Lkotlin/collections/HashMap;", "UiClickMap", "UiPageViewMap", "Ljava/io/InputStream;", "jsonInputStream", "mappingsAsInputStream", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "getTAG", "()Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class UiInteractionMappingUtil {
    public static final UiInteractionMappingUtil INSTANCE;
    private static final String TAG;

    private UiInteractionMappingUtil() {
    }

    static {
        UiInteractionMappingUtil uiInteractionMappingUtil = new UiInteractionMappingUtil();
        INSTANCE = uiInteractionMappingUtil;
        TAG = uiInteractionMappingUtil.getClass().getSimpleName();
    }

    public final HashMap<String, Map<String, String>> UiClickMap() {
        UiClickActionType[] uiClickActionTypeArrValues = UiClickActionType.values();
        HashMap<String, Map<String, String>> map = new HashMap<>();
        int length = uiClickActionTypeArrValues.length;
        int i = 0;
        while (i < length) {
            UiClickActionType uiClickActionType = uiClickActionTypeArrValues[i];
            i++;
            HashMap map2 = new HashMap();
            map2.put("actionType", uiClickActionType.name());
            map.put(uiClickActionType.getKey(), map2);
        }
        return map;
    }

    public final HashMap<String, Map<String, String>> UiPageViewMap() {
        UiPageViewPageType[] uiPageViewPageTypeArrValues = UiPageViewPageType.values();
        HashMap<String, Map<String, String>> map = new HashMap<>();
        int length = uiPageViewPageTypeArrValues.length;
        int i = 0;
        while (i < length) {
            UiPageViewPageType uiPageViewPageType = uiPageViewPageTypeArrValues[i];
            i++;
            HashMap map2 = new HashMap();
            map2.put("pageType", uiPageViewPageType.name());
            map.put(uiPageViewPageType.getKey(), map2);
        }
        return map;
    }

    public final InputStream mappingsAsInputStream(InputStream jsonInputStream) {
        HashMap map;
        HashMap map2;
        Object value;
        Intrinsics.checkNotNullParameter(jsonInputStream, "jsonInputStream");
        ObjectMapper objectMapper = AmazonApplication.getObjectMapper();
        HashMap map3 = new HashMap();
        try {
            try {
                value = objectMapper.readValue(jsonInputStream, (Class<Object>) Map.class);
            } catch (Exception e) {
                Log.error(TAG, "Could not convert ui interaction mapping json file to a HashMap", e.getMessage());
                jsonInputStream.close();
            }
            if (value == null) {
                throw new NullPointerException("null cannot be cast to non-null type java.util.HashMap<kotlin.String, java.util.HashMap<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.String>>>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, java.util.HashMap<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.String>>{ kotlin.collections.TypeAliasesKt.HashMap<kotlin.String, kotlin.collections.Map<kotlin.String, kotlin.String>> }> }");
            }
            HashMap map4 = (HashMap) value;
            jsonInputStream.close();
            map3 = map4;
            HashMap<String, Map<String, String>> mapUiClickMap = UiClickMap();
            for (String str : mapUiClickMap.keySet()) {
                Map<String, String> map5 = mapUiClickMap.get(str);
                if (map5 != null && (map2 = (HashMap) map3.get("uiClick")) != null) {
                }
            }
            HashMap<String, Map<String, String>> mapUiPageViewMap = UiPageViewMap();
            for (String str2 : mapUiPageViewMap.keySet()) {
                Map<String, String> map6 = mapUiPageViewMap.get(str2);
                if (map6 != null && (map = (HashMap) map3.get("uiPageView")) != null) {
                }
            }
            String string = new JSONObject((Map<?, ?>) map3).toString();
            Intrinsics.checkNotNullExpressionValue(string, "json.toString()");
            byte[] bytes = string.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            return new ByteArrayInputStream(bytes);
        } catch (Throwable th) {
            jsonInputStream.close();
            throw th;
        }
    }
}
