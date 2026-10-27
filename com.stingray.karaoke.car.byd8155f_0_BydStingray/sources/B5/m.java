package B5;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final m f725a = new m();

    public static void b(Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        for (Map.Entry entry : map.entrySet()) {
            jsonWriter.name(String.valueOf(entry.getKey()));
            h(entry.getValue(), jsonWriter);
        }
        jsonWriter.endObject();
    }

    public static HttpURLConnection c(String str) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setReadTimeout(20000);
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setRequestProperty("User-Agent", "analytics-android/4.11.3");
            httpURLConnection.setDoInput(true);
            return httpURLConnection;
        } catch (MalformedURLException e10) {
            throw new IOException(M5.b.g("Attempted to use malformed url: ", str), e10);
        }
    }

    public static Object d(JsonReader jsonReader) throws IOException {
        JsonToken jsonTokenPeek = jsonReader.peek();
        switch (AbstractC0088l.f724a[jsonTokenPeek.ordinal()]) {
            case 1:
                return e(jsonReader);
            case 2:
                ArrayList arrayList = new ArrayList();
                jsonReader.beginArray();
                while (jsonReader.hasNext()) {
                    arrayList.add(d(jsonReader));
                }
                jsonReader.endArray();
                return arrayList;
            case 3:
                return Boolean.valueOf(jsonReader.nextBoolean());
            case 4:
                jsonReader.nextNull();
                return null;
            case 5:
                return Double.valueOf(jsonReader.nextDouble());
            case 6:
                return jsonReader.nextString();
            default:
                throw new IllegalStateException("Invalid token " + jsonTokenPeek);
        }
    }

    public static LinkedHashMap e(JsonReader jsonReader) throws IOException {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            linkedHashMap.put(jsonReader.nextName(), d(jsonReader));
        }
        jsonReader.endObject();
        return linkedHashMap;
    }

    public static void h(Object obj, JsonWriter jsonWriter) throws IOException {
        if (obj == null) {
            jsonWriter.nullValue();
            return;
        }
        if (obj instanceof Number) {
            if (obj instanceof Double) {
                Double d = (Double) obj;
                if (Double.isNaN(d.doubleValue()) || Double.isInfinite(d.doubleValue())) {
                    obj = Double.valueOf(0.0d);
                }
            }
            if (obj instanceof Float) {
                Float f10 = (Float) obj;
                if (Float.isNaN(f10.floatValue()) || Float.isInfinite(f10.floatValue())) {
                    obj = Double.valueOf(0.0d);
                }
            }
            jsonWriter.value((Number) obj);
            return;
        }
        if (obj instanceof Boolean) {
            jsonWriter.value(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof List) {
            jsonWriter.beginArray();
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                h(it.next(), jsonWriter);
            }
            jsonWriter.endArray();
            return;
        }
        if (obj instanceof Map) {
            b((Map) obj, jsonWriter);
            return;
        }
        if (!obj.getClass().isArray()) {
            jsonWriter.value(String.valueOf(obj));
            return;
        }
        jsonWriter.beginArray();
        int length = Array.getLength(obj);
        for (int i7 = 0; i7 < length; i7++) {
            h(Array.get(obj, i7), jsonWriter);
        }
        jsonWriter.endArray();
    }

    public LinkedHashMap a(Reader reader) throws IOException {
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        try {
            return e(jsonReader);
        } finally {
            reader.close();
        }
    }

    public void f(L l, Writer writer) {
        if (l == null) {
            throw new IllegalArgumentException("map == null");
        }
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.setLenient(true);
        try {
            b(l, jsonWriter);
        } finally {
            jsonWriter.close();
        }
    }

    public HttpURLConnection g(String str) {
        HttpURLConnection httpURLConnectionC = c("https://" + str + "/import");
        httpURLConnectionC.setRequestProperty("Content-Encoding", "gzip");
        httpURLConnectionC.setDoOutput(true);
        httpURLConnectionC.setChunkedStreamingMode(0);
        return httpURLConnectionC;
    }
}
