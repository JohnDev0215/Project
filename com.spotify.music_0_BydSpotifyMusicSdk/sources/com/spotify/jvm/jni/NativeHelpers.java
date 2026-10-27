package com.spotify.jvm.jni;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.collections.b;
import kotlin.jvm.internal.DefaultConstructorMarker;
import p.cs;
import p.i51;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class NativeHelpers {
    public static final Companion Companion = new Companion(null);

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private final int nextNull(byte[] bArr, int i) {
            while (bArr[i] != 0) {
                try {
                    i++;
                } catch (Exception unused) {
                    throw new IndexOutOfBoundsException(u4.e("Invalid character found at offset ", i));
                }
            }
            return i;
        }

        private final void writeStringToStream(ByteArrayOutputStream byteArrayOutputStream, String str) throws IOException {
            byte[] bytes = str.getBytes(cs.a);
            v03.i(bytes, "this as java.lang.String).getBytes(charset)");
            byteArrayOutputStream.write(bytes);
            byteArrayOutputStream.write(0);
        }

        public final Map<String, String> byteArrayToMap(byte[] bArr) {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            if (bArr == null) {
                return linkedHashMap;
            }
            int i = 0;
            while (i < bArr.length) {
                int iNextNull = nextNull(bArr, i);
                byte[] bArrU0 = b.u0(bArr, new i51(i, iNextNull - 1, 1));
                Charset charset = cs.a;
                String str = new String(bArrU0, charset);
                int i2 = iNextNull + 1;
                int iNextNull2 = nextNull(bArr, i2);
                String str2 = new String(b.u0(bArr, new i51(i2, iNextNull2 - 1, 1)), charset);
                i = iNextNull2 + 1;
                linkedHashMap.put(str, str2);
            }
            return linkedHashMap;
        }

        public final byte[] mapToByteArray(Map<String, String> map) throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    Companion companion = NativeHelpers.Companion;
                    companion.writeStringToStream(byteArrayOutputStream, key);
                    companion.writeStringToStream(byteArrayOutputStream, value);
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            v03.i(byteArray, "outputStream.toByteArray()");
            return byteArray;
        }
    }

    private NativeHelpers() {
    }

    public static final Map<String, String> byteArrayToMap(byte[] bArr) {
        return Companion.byteArrayToMap(bArr);
    }

    public static final byte[] mapToByteArray(Map<String, String> map) {
        return Companion.mapToByteArray(map);
    }
}
