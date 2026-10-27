package com.bugsnag.android.internal;

import com.bugsnag.android.JsonStream;
import com.bugsnag.android.repackaged.dslplatform.json.DslJson;
import com.bugsnag.android.repackaged.dslplatform.json.JsonWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.CharsKt__CharJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: JsonHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001aJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010\u0005\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\u0007J\u001e\u0010\r\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\f\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b2\u0006\u0010\b\u001a\u00020\nJ\u001e\u0010\r\u001a\u0012\u0012\u0006\b\u0000\u0012\u00020\f\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0019\u0010\u0011\u001a\u0004\u0018\u00010\f2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0010¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00102\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0013\u0010\u0014Rn\u0010\u0017\u001aV\u0012$\u0012\"\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001 \u0016*\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b0\u000b \u0016**\u0012$\u0012\"\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001 \u0016*\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b0\u000b\u0018\u00010\u00150\u00158\u0002X\u0082\u0004¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u0012\u0004\b\u0019\u0010\u001aR:\u0010\u001c\u001a(\u0012$\u0012\"\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001 \u0016*\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b0\u000b0\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d¨\u0006\u001f"}, d2 = {"Lcom/bugsnag/android/internal/JsonHelper;", "", "Lcom/bugsnag/android/JsonStream$Streamable;", "streamable", "", "serialize", "value", "Ljava/io/OutputStream;", "stream", "", "Ljava/io/InputStream;", "", "", "deserialize", "Ljava/io/File;", "file", "", "ulongToHex", "(Ljava/lang/Long;)Ljava/lang/String;", "jsonToLong", "(Ljava/lang/Object;)Ljava/lang/Long;", "Lcom/bugsnag/android/repackaged/dslplatform/json/DslJson$Settings;", "kotlin.jvm.PlatformType", "settings", "Lcom/bugsnag/android/repackaged/dslplatform/json/DslJson$Settings;", "getSettings$annotations", "()V", "Lcom/bugsnag/android/repackaged/dslplatform/json/DslJson;", "dslJson", "Lcom/bugsnag/android/repackaged/dslplatform/json/DslJson;", "<init>", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class JsonHelper {
    public static final JsonHelper INSTANCE = new JsonHelper();
    private static final DslJson<Map<String, Object>> dslJson;
    private static final DslJson.Settings<Map<String, Object>> settings;

    static {
        DslJson.Settings<Map<String, Object>> settingsFallbackTo = new DslJson.Settings().fallbackTo(new FallbackWriter());
        settings = settingsFallbackTo;
        DslJson<Map<String, Object>> dslJson2 = new DslJson<>(settingsFallbackTo);
        dslJson = dslJson2;
        dslJson2.registerWriter(Date.class, new JsonWriter.WriteObject<Date>() { // from class: com.bugsnag.android.internal.JsonHelper.1
            @Override // com.bugsnag.android.repackaged.dslplatform.json.JsonWriter.WriteObject
            public final void write(JsonWriter jsonWriter, Date date) {
                if (date != null) {
                    jsonWriter.writeString(DateUtils.toIso8601(date));
                }
            }
        });
    }

    private JsonHelper() {
    }

    public final byte[] serialize(JsonStream.Streamable streamable) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            JsonStream jsonStream = new JsonStream(new PrintWriter(byteArrayOutputStream));
            try {
                streamable.toStream(jsonStream);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(jsonStream, null);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                CloseableKt.closeFinally(byteArrayOutputStream, null);
                Intrinsics.checkExpressionValueIsNotNull(byteArray, "ByteArrayOutputStream().…s.toByteArray()\n        }");
                return byteArray;
            } finally {
            }
        } finally {
        }
    }

    public final void serialize(Object value, OutputStream stream) throws IOException {
        dslJson.serialize(value, stream);
    }

    public final Map<? super String, ? extends Object> deserialize(InputStream stream) {
        Map map = (Map) dslJson.deserialize(Map.class, stream);
        if (map == null) {
            throw new IllegalArgumentException("JSON document is invalid".toString());
        }
        return TypeIntrinsics.asMutableMap(map);
    }

    public final Map<? super String, ? extends Object> deserialize(File file) throws IOException {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                Map<? super String, ? extends Object> mapDeserialize = INSTANCE.deserialize(fileInputStream);
                CloseableKt.closeFinally(fileInputStream, null);
                return mapDeserialize;
            } finally {
            }
        } catch (FileNotFoundException e) {
            throw e;
        } catch (IOException e2) {
            throw new IOException("Could not deserialize from " + file, e2);
        }
    }

    public final String ulongToHex(Long value) {
        if (value == null) {
            return null;
        }
        if (value.longValue() >= 0) {
            String str = String.format("0x%x", Arrays.copyOf(new Object[]{value}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "java.lang.String.format(this, *args)");
            return str;
        }
        String str2 = String.format("0x%x%02x", Arrays.copyOf(new Object[]{Long.valueOf(value.longValue() >>> 8), Long.valueOf(value.longValue() & 255)}, 2));
        Intrinsics.checkNotNullExpressionValue(str2, "java.lang.String.format(this, *args)");
        return str2;
    }

    public final Long jsonToLong(Object value) {
        long jLongValue;
        Long lValueOf;
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        if (value instanceof String) {
            String str = (String) value;
            if (str.length() == 0) {
                return null;
            }
            try {
                lValueOf = Long.decode((String) value);
            } catch (NumberFormatException e) {
                if (StringsKt__StringsJVMKt.startsWith$default(str, "0x", false, 2, null)) {
                    if (str.length() != 18) {
                        throw e;
                    }
                    int length = str.length() - 2;
                    String strSubstring = str.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    long jLongValue2 = Long.decode(strSubstring).longValue() << 8;
                    String strSubstring2 = str.substring(length, str.length());
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    jLongValue = Long.parseLong(strSubstring2, CharsKt__CharJVMKt.checkRadix(16)) | jLongValue2;
                } else {
                    if (str.length() < 19) {
                        throw e;
                    }
                    int length2 = str.length() - 3;
                    String strSubstring3 = str.substring(0, length2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring3, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    long jLongValue3 = Long.decode(strSubstring3).longValue() * ((long) 1000);
                    String strSubstring4 = str.substring(length2, str.length());
                    Intrinsics.checkNotNullExpressionValue(strSubstring4, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                    Long lDecode = Long.decode(strSubstring4);
                    Intrinsics.checkExpressionValueIsNotNull(lDecode, "java.lang.Long.decode(va…eadLength, value.length))");
                    jLongValue = lDecode.longValue() + jLongValue3;
                }
                lValueOf = Long.valueOf(jLongValue);
            }
            return lValueOf;
        }
        throw new IllegalArgumentException("Cannot convert " + value + " to long");
    }
}
