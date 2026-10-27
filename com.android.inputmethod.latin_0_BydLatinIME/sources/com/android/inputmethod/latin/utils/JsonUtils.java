package com.android.inputmethod.latin.utils;

import android.util.JsonReader;
import android.util.JsonWriter;
import android.util.Log;
import java.io.Closeable;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public final class JsonUtils {
    private static final String EMPTY_STRING = "";
    private static final String INTEGER_CLASS_NAME = Integer.class.getSimpleName();
    private static final String STRING_CLASS_NAME = String.class.getSimpleName();
    private static final String TAG = "JsonUtils";

    public static List<Object> jsonStrToList(final String s) {
        ArrayList arrayList = new ArrayList();
        JsonReader jsonReader = new JsonReader(new StringReader(s));
        try {
            jsonReader.beginArray();
            while (jsonReader.hasNext()) {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    String strNextName = jsonReader.nextName();
                    if (strNextName.equals(INTEGER_CLASS_NAME)) {
                        arrayList.add(Integer.valueOf(jsonReader.nextInt()));
                    } else if (strNextName.equals(STRING_CLASS_NAME)) {
                        arrayList.add(jsonReader.nextString());
                    } else {
                        Log.w(TAG, "Invalid name: " + strNextName);
                        jsonReader.skipValue();
                    }
                }
                jsonReader.endObject();
            }
            jsonReader.endArray();
            close(jsonReader);
            return arrayList;
        } catch (IOException unused) {
            close(jsonReader);
            return Collections.emptyList();
        } catch (Throwable th) {
            close(jsonReader);
            throw th;
        }
    }

    public static String listToJsonStr(final List<Object> list) {
        if (list != null && !list.isEmpty()) {
            StringWriter stringWriter = new StringWriter();
            JsonWriter jsonWriter = new JsonWriter(stringWriter);
            try {
                jsonWriter.beginArray();
                for (Object obj : list) {
                    jsonWriter.beginObject();
                    if (obj instanceof Integer) {
                        jsonWriter.name(INTEGER_CLASS_NAME).value((Integer) obj);
                    } else if (obj instanceof String) {
                        jsonWriter.name(STRING_CLASS_NAME).value((String) obj);
                    }
                    jsonWriter.endObject();
                }
                jsonWriter.endArray();
                return stringWriter.toString();
            } catch (IOException unused) {
            } finally {
                close(jsonWriter);
            }
        }
        return "";
    }

    private static void close(final Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
