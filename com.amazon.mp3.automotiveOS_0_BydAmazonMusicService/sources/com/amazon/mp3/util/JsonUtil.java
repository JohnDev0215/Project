package com.amazon.mp3.util;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class JsonUtil {
    private static final String TAG = "JsonUtil";

    public static JSONObject loadFromResource(Context context, int resourceId) {
        InputStream inputStreamOpenRawResource = context.getResources().openRawResource(resourceId);
        JSONObject jSONObjectStringToJson = stringToJson(IoUtil.streamToString(inputStreamOpenRawResource));
        try {
            inputStreamOpenRawResource.close();
        } catch (IOException e) {
            Log.warning(TAG, "Failed to close stream", e);
        }
        return jSONObjectStringToJson;
    }

    public static JSONObject stringToJson(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            Log.info(TAG, "Invalid json", e);
            return null;
        }
    }

    public static JSONObject loadFromFile(final File file) throws Throwable {
        String strLoadFromFile = FileUtil.loadFromFile(file);
        if (strLoadFromFile == null || "".equals(strLoadFromFile)) {
            return null;
        }
        return stringToJson(strLoadFromFile);
    }
}
