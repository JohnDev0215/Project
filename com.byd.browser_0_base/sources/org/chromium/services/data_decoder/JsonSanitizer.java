package org.chromium.services.data_decoder;

import J.N;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.JsonWriter;
import android.util.MalformedJsonException;
import defpackage.a8;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class JsonSanitizer {

    public static /* synthetic */ class a {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            a = iArr;
            try {
                iArr[JsonToken.BEGIN_ARRAY.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[JsonToken.END_ARRAY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[JsonToken.BEGIN_OBJECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                a[JsonToken.END_OBJECT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                a[JsonToken.NAME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                a[JsonToken.STRING.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                a[JsonToken.NUMBER.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                a[JsonToken.BOOLEAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                a[JsonToken.NULL.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                a[JsonToken.END_DOCUMENT.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
        }
    }

    public static String a(String str) {
        JsonReader jsonReader = new JsonReader(new StringReader(str));
        StringWriter stringWriter = new StringWriter(str.length());
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        boolean z = false;
        int i = 0;
        while (!z) {
            try {
                switch (a.a[jsonReader.peek().ordinal()]) {
                    case 1:
                        i++;
                        if (i >= 200) {
                            throw new IllegalStateException("Too much nesting");
                        }
                        jsonReader.beginArray();
                        jsonWriter.beginArray();
                        continue;
                        break;
                    case 2:
                        i--;
                        jsonReader.endArray();
                        jsonWriter.endArray();
                        continue;
                    case 3:
                        i++;
                        if (i >= 200) {
                            throw new IllegalStateException("Too much nesting");
                        }
                        jsonReader.beginObject();
                        jsonWriter.beginObject();
                        continue;
                        break;
                    case 4:
                        i--;
                        jsonReader.endObject();
                        jsonWriter.endObject();
                        continue;
                    case 5:
                        String strNextName = jsonReader.nextName();
                        b(strNextName);
                        jsonWriter.name(strNextName);
                        continue;
                    case 6:
                        String strNextString = jsonReader.nextString();
                        b(strNextString);
                        jsonWriter.value(strNextString);
                        continue;
                    case 7:
                        String strNextString2 = jsonReader.nextString();
                        try {
                            jsonWriter.value(Long.parseLong(strNextString2));
                        } catch (NumberFormatException unused) {
                            jsonWriter.value(Double.parseDouble(strNextString2));
                        }
                        break;
                    case 8:
                        jsonWriter.value(jsonReader.nextBoolean());
                        continue;
                    case 9:
                        jsonReader.nextNull();
                        jsonWriter.nullValue();
                        continue;
                    case 10:
                        z = true;
                        continue;
                }
            } finally {
            }
            a8.s(jsonReader);
            a8.s(jsonWriter);
        }
        return stringWriter.toString();
    }

    public static void b(String str) throws MalformedJsonException {
        int length = str.length();
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= length) {
                z = true;
                break;
            }
            char cCharAt = str.charAt(i);
            if (Character.isLowSurrogate(cCharAt)) {
                break;
            }
            if (Character.isHighSurrogate(cCharAt)) {
                i++;
                if (i >= length) {
                    break;
                }
                char cCharAt2 = str.charAt(i);
                if (!Character.isLowSurrogate(cCharAt2)) {
                    break;
                } else {
                    Character.toCodePoint(cCharAt, cCharAt2);
                }
            }
            i++;
        }
        if (!z) {
            throw new MalformedJsonException("Invalid escape sequence");
        }
    }

    @CalledByNative
    public static void sanitize(long j, String str) {
        try {
            N.MkUGEqr$(j, a(str));
        } catch (IOException | IllegalStateException e) {
            N.MOkhqs8N(j, e.getMessage());
        }
    }
}
