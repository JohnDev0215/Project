package defpackage;

import android.util.Log;
import com.google.protobuf.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: compiled from: VariationsCompressionUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yk3 {

    /* JADX INFO: compiled from: VariationsCompressionUtils.java */
    public static class a extends Exception {
        public a(String str) {
            super(str);
        }
    }

    /* JADX INFO: compiled from: VariationsCompressionUtils.java */
    public static class b {
        public final boolean a;
        public final boolean b;

        public b(boolean z, boolean z2) {
            this.a = z;
            this.b = z2;
        }
    }

    /* JADX INFO: compiled from: VariationsCompressionUtils.java */
    public static class c extends Exception {
        public c(String str) {
            super(str);
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) throws a {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                g gVarI = g.i(ByteBuffer.wrap(bArr2), false);
                while (!gVarI.g()) {
                    int iG = gVarI.G();
                    if (iG != 0) {
                        byteArrayOutputStream.write(gVarI.x(iG), 0, iG);
                    } else {
                        int iG2 = gVarI.G();
                        int iG3 = gVarI.G();
                        byteArrayOutputStream.write(Arrays.copyOfRange(bArr, iG2, Math.addExact(iG2, iG3)), 0, iG3);
                    }
                }
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException | ArithmeticException | ArrayIndexOutOfBoundsException e) {
            Log.w("cr_VariationsUtils", "Delta patch failed.", e);
            throw new a("Failed to delta patch variations seed");
        }
    }

    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r5v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r5v2 */
    public static b b(String str) throws c {
        List arrayList = new ArrayList();
        if (str.length() > 0) {
            arrayList = Arrays.asList(str.split("\\s*,\\s*"));
        }
        int iIndexOf = arrayList.indexOf("x-bm");
        int iIndexOf2 = arrayList.indexOf("gzip");
        ?? r5 = iIndexOf >= 0 ? 1 : 0;
        ?? r3 = iIndexOf2 < 0 ? 0 : 1;
        if (r5 + r3 != arrayList.size()) {
            throw new c(gq2.g("Unrecognized instance manipulations in ", str, "; only x-bm and gzip are supported"));
        }
        if (r5 == 0 || r3 == 0 || iIndexOf <= iIndexOf2) {
            return new b(r3, r5);
        }
        throw new c("Unsupported instance manipulations order: expected x-bm,gzip, but received gzip,x-bm");
    }

    public static byte[] c(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        try {
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                gZIPOutputStream.close();
                byteArrayOutputStream.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    gZIPOutputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (Throwable th2) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable unused2) {
            }
            throw th2;
        }
    }

    public static byte[] d(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                try {
                    byte[] bArr2 = new byte[1024];
                    while (true) {
                        int i = gZIPInputStream.read(bArr2);
                        if (i == -1) {
                            gZIPInputStream.close();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            gZIPInputStream.close();
                            byteArrayOutputStream.close();
                            byteArrayInputStream.close();
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i);
                    }
                } catch (Throwable th) {
                    try {
                        gZIPInputStream.close();
                    } catch (Throwable unused) {
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused2) {
                }
                throw th2;
            }
        } catch (Throwable th3) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable unused3) {
            }
            throw th3;
        }
    }
}
