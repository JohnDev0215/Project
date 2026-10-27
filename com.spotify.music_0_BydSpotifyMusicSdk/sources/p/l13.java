package p;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.spotify.music.R;
import com.spotify.remoteconfig.commons.logging.EventLogger$FetchErrorReason;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class l13 implements zd0 {
    public static w03 a;
    public static final Object[] b = new Object[0];
    public static final char[] c = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final d71 d = new d71(2);
    public static final vk0 e = new vk0(4);

    public static final void a(qb qbVar, int i) {
        v03.j(qbVar, "<this>");
        qbVar.c = new int[i];
        qbVar.e = new Object[i];
    }

    public static byte[] b(byte[] bArr) {
        Deflater deflater = new Deflater(1);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
            try {
                deflaterOutputStream.write(bArr);
                deflaterOutputStream.close();
                deflater.end();
                return byteArrayOutputStream.toByteArray();
            } finally {
            }
        } catch (Throwable th) {
            deflater.end();
            throw th;
        }
    }

    public static final int c(Bitmap bitmap) {
        int i = 1;
        if (!bitmap.isRecycled()) {
            try {
                return bitmap.getAllocationByteCount();
            } catch (Exception unused) {
                int height = bitmap.getHeight() * bitmap.getWidth();
                Bitmap.Config config = bitmap.getConfig();
                if (config != Bitmap.Config.ALPHA_8) {
                    i = 2;
                    if (config != Bitmap.Config.RGB_565 && config != Bitmap.Config.ARGB_4444) {
                        i = config == Bitmap.Config.RGBA_F16 ? 8 : 4;
                    }
                }
                return height * i;
            }
        }
        throw new IllegalStateException(("Cannot obtain size for recycled bitmap: " + bitmap + " [" + bitmap.getWidth() + " x " + bitmap.getHeight() + "] + " + bitmap.getConfig()).toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0049, code lost:
    
        if (r5.c == r8.hashCode()) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.content.res.ColorStateList d(android.content.Context r8, int r9) {
        /*
            java.lang.Object r0 = p.sz.a
            android.content.res.Resources r0 = r8.getResources()
            android.content.res.Resources$Theme r8 = r8.getTheme()
            p.o42 r1 = new p.o42
            r1.<init>(r0, r8)
            java.lang.Object r2 = p.s42.c
            monitor-enter(r2)
            java.util.WeakHashMap r3 = p.s42.b     // Catch: java.lang.Throwable -> L3e
            java.lang.Object r3 = r3.get(r1)     // Catch: java.lang.Throwable -> L3e
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L3e
            r4 = 0
            if (r3 == 0) goto L52
            int r5 = r3.size()     // Catch: java.lang.Throwable -> L3e
            if (r5 <= 0) goto L52
            java.lang.Object r5 = r3.get(r9)     // Catch: java.lang.Throwable -> L3e
            p.n42 r5 = (p.n42) r5     // Catch: java.lang.Throwable -> L3e
            if (r5 == 0) goto L52
            android.content.res.Configuration r6 = r5.b     // Catch: java.lang.Throwable -> L3e
            android.content.res.Configuration r7 = r0.getConfiguration()     // Catch: java.lang.Throwable -> L3e
            boolean r6 = r6.equals(r7)     // Catch: java.lang.Throwable -> L3e
            if (r6 == 0) goto L4f
            if (r8 != 0) goto L41
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3e
            if (r6 == 0) goto L4b
            goto L41
        L3e:
            r8 = move-exception
            goto Lb2
        L41:
            if (r8 == 0) goto L4f
            int r6 = r5.c     // Catch: java.lang.Throwable -> L3e
            int r7 = r8.hashCode()     // Catch: java.lang.Throwable -> L3e
            if (r6 != r7) goto L4f
        L4b:
            android.content.res.ColorStateList r3 = r5.a     // Catch: java.lang.Throwable -> L3e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            goto L54
        L4f:
            r3.remove(r9)     // Catch: java.lang.Throwable -> L3e
        L52:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            r3 = r4
        L54:
            if (r3 == 0) goto L57
            goto Lb1
        L57:
            java.lang.ThreadLocal r2 = p.s42.a
            java.lang.Object r3 = r2.get()
            android.util.TypedValue r3 = (android.util.TypedValue) r3
            if (r3 != 0) goto L69
            android.util.TypedValue r3 = new android.util.TypedValue
            r3.<init>()
            r2.set(r3)
        L69:
            r2 = 1
            r0.getValue(r9, r3, r2)
            int r2 = r3.type
            r3 = 28
            if (r2 < r3) goto L78
            r3 = 31
            if (r2 > r3) goto L78
            goto L80
        L78:
            android.content.res.XmlResourceParser r2 = r0.getXml(r9)
            android.content.res.ColorStateList r4 = p.pu.a(r0, r2, r8)     // Catch: java.lang.Exception -> L80
        L80:
            if (r4 == 0) goto Lad
            java.lang.Object r2 = p.s42.c
            monitor-enter(r2)
            java.util.WeakHashMap r0 = p.s42.b     // Catch: java.lang.Throwable -> L98
            java.lang.Object r3 = r0.get(r1)     // Catch: java.lang.Throwable -> L98
            android.util.SparseArray r3 = (android.util.SparseArray) r3     // Catch: java.lang.Throwable -> L98
            if (r3 != 0) goto L9a
            android.util.SparseArray r3 = new android.util.SparseArray     // Catch: java.lang.Throwable -> L98
            r3.<init>()     // Catch: java.lang.Throwable -> L98
            r0.put(r1, r3)     // Catch: java.lang.Throwable -> L98
            goto L9a
        L98:
            r8 = move-exception
            goto Lab
        L9a:
            p.n42 r0 = new p.n42     // Catch: java.lang.Throwable -> L98
            android.content.res.Resources r1 = r1.a     // Catch: java.lang.Throwable -> L98
            android.content.res.Configuration r1 = r1.getConfiguration()     // Catch: java.lang.Throwable -> L98
            r0.<init>(r4, r1, r8)     // Catch: java.lang.Throwable -> L98
            r3.append(r9, r0)     // Catch: java.lang.Throwable -> L98
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L98
            r3 = r4
            goto Lb1
        Lab:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L98
            throw r8
        Lad:
            android.content.res.ColorStateList r3 = p.l42.b(r0, r9, r8)
        Lb1:
            return r3
        Lb2:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L3e
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: p.l13.d(android.content.Context, int):android.content.res.ColorStateList");
    }

    public static Drawable f(Context context, int i) {
        return h42.c().e(context, i);
    }

    public static final int g(qb qbVar, Object obj, int i) {
        v03.j(qbVar, "<this>");
        int i2 = qbVar.k;
        if (i2 == 0) {
            return -1;
        }
        try {
            int i3 = zu.i(qbVar.k, i, qbVar.c);
            if (i3 < 0 || v03.b(obj, qbVar.e[i3])) {
                return i3;
            }
            int i4 = i3 + 1;
            while (i4 < i2 && qbVar.c[i4] == i) {
                if (v03.b(obj, qbVar.e[i4])) {
                    return i4;
                }
                i4++;
            }
            for (int i5 = i3 - 1; i5 >= 0 && qbVar.c[i5] == i; i5--) {
                if (v03.b(obj, qbVar.e[i5])) {
                    return i5;
                }
            }
            return ~i4;
        } catch (IndexOutOfBoundsException unused) {
            throw new ConcurrentModificationException();
        }
    }

    public static final o7 h(com.apollographql.apollo3.api.c cVar, tq1 tq1Var, u71 u71Var) throws Throwable {
        o7 o7Var;
        v03.j(tq1Var, "<this>");
        v03.j(cVar, "customScalarAdapters");
        ve1 ve1Var = new ve1();
        ve1Var.f();
        tq1Var.a(ve1Var, cVar);
        ve1Var.k();
        Object objC = ve1Var.c();
        if (objC == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        }
        oh1 oh1Var = new oh1((Map) objC);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Throwable th = null;
        linkedHashMap.putAll(cVar.c);
        z4 z4Var = cVar.b;
        oh1 oh1Var2 = z4Var.a;
        com.apollographql.apollo3.api.c cVar2 = new com.apollographql.apollo3.api.c(linkedHashMap, new z4(oh1Var, z4Var.b));
        try {
            u71Var.f();
            Map map = null;
            pq1 pq1Var = null;
            List listR = null;
            while (u71Var.hasNext()) {
                String strR0 = u71Var.r0();
                int iHashCode = strR0.hashCode();
                if (iHashCode != -1809421292) {
                    if (iHashCode != -1294635157) {
                        if (iHashCode == 3076010 && strR0.equals("data")) {
                            pq1Var = (pq1) c5.a(tq1Var.b()).a(u71Var, cVar2);
                        } else {
                            u71Var.D();
                        }
                    } else if (strR0.equals("errors")) {
                        listR = q30.r(u71Var);
                    } else {
                        u71Var.D();
                    }
                } else if (strR0.equals("extensions")) {
                    Object objJ = he1.J(u71Var);
                    map = objJ instanceof Map ? (Map) objJ : null;
                } else {
                    u71Var.D();
                }
            }
            u71Var.k();
            UUID uuidRandomUUID = UUID.randomUUID();
            v03.i(uuidRandomUUID, "randomUUID()");
            o7Var = new o7(uuidRandomUUID, tq1Var, pq1Var, listR, map == null ? kotlin.collections.c.h0() : map, td0.b, false);
        } catch (Throwable th2) {
            th = th2;
            o7Var = null;
        }
        try {
            u71Var.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            } else {
                kotlin.a.a(th, th3);
            }
        }
        if (th != null) {
            throw th;
        }
        v03.h(o7Var);
        return o7Var;
    }

    public static byte[] i(int i, InputStream inputStream) throws IOException {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int i3 = inputStream.read(bArr, i2, i - i2);
            if (i3 < 0) {
                throw new IllegalStateException(u4.e("Not enough bytes to read: ", i));
            }
            i2 += i3;
        }
        return bArr;
    }

    public static byte[] j(FileInputStream fileInputStream, int i, int i2) {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i2];
            byte[] bArr2 = new byte[2048];
            int i3 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i3 < i) {
                int i4 = fileInputStream.read(bArr2);
                if (i4 < 0) {
                    throw new IllegalStateException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i + " bytes");
                }
                inflater.setInput(bArr2, 0, i4);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i2 - iInflate);
                    i3 += i4;
                } catch (DataFormatException e2) {
                    throw new IllegalStateException(e2.getMessage());
                }
            }
            if (i3 == i) {
                if (inflater.finished()) {
                    return bArr;
                }
                throw new IllegalStateException("Inflater did not finish");
            }
            throw new IllegalStateException("Didn't read enough bytes during decompression. expected=" + i + " actual=" + i3);
        } finally {
            inflater.end();
        }
    }

    public static long k(int i, InputStream inputStream) throws IOException {
        byte[] bArrI = i(i, inputStream);
        long j = 0;
        for (int i2 = 0; i2 < i; i2++) {
            j += ((long) (bArrI[i2] & 255)) << (i2 * 8);
        }
        return j;
    }

    public static final void l(View view, s91 s91Var) {
        v03.j(view, "<this>");
        view.setTag(R.id.view_tree_lifecycle_owner, s91Var);
    }

    public static final void m(View view, x62 x62Var) {
        v03.j(view, "<this>");
        view.setTag(R.id.view_tree_saved_state_registry_owner, x62Var);
    }

    public static final Object[] n(Collection collection) {
        v03.j(collection, "collection");
        int size = collection.size();
        Object[] objArr = b;
        if (size == 0) {
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            return objArr;
        }
        Object[] objArrCopyOf = new Object[size];
        int i = 0;
        while (true) {
            int i2 = i + 1;
            objArrCopyOf[i] = it.next();
            if (i2 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i3 = ((i2 * 3) + 1) >>> 1;
                if (i3 <= i2) {
                    i3 = 2147483645;
                    if (i2 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                v03.i(objArrCopyOf, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i2);
                v03.i(objArrCopyOf2, "copyOf(result, size)");
                return objArrCopyOf2;
            }
            i = i2;
        }
    }

    public static final Object[] o(Collection collection, Object[] objArr) {
        Object[] objArrCopyOf;
        v03.j(collection, "collection");
        objArr.getClass();
        int size = collection.size();
        int i = 0;
        if (size == 0) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        Iterator it = collection.iterator();
        if (!it.hasNext()) {
            if (objArr.length <= 0) {
                return objArr;
            }
            objArr[0] = null;
            return objArr;
        }
        if (size <= objArr.length) {
            objArrCopyOf = objArr;
        } else {
            Object objNewInstance = Array.newInstance(objArr.getClass().getComponentType(), size);
            v03.g(objNewInstance, "null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
            objArrCopyOf = (Object[]) objNewInstance;
        }
        while (true) {
            int i2 = i + 1;
            objArrCopyOf[i] = it.next();
            if (i2 >= objArrCopyOf.length) {
                if (!it.hasNext()) {
                    return objArrCopyOf;
                }
                int i3 = ((i2 * 3) + 1) >>> 1;
                if (i3 <= i2) {
                    i3 = 2147483645;
                    if (i2 >= 2147483645) {
                        throw new OutOfMemoryError();
                    }
                }
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                v03.i(objArrCopyOf, "copyOf(result, newSize)");
            } else if (!it.hasNext()) {
                if (objArrCopyOf == objArr) {
                    objArr[i2] = null;
                    return objArr;
                }
                Object[] objArrCopyOf2 = Arrays.copyOf(objArrCopyOf, i2);
                v03.i(objArrCopyOf2, "copyOf(result, size)");
                return objArrCopyOf2;
            }
            i = i2;
        }
    }

    public static EventLogger$FetchErrorReason p(int i) {
        return i == 404 ? EventLogger$FetchErrorReason.NOT_FOUND : (i == 408 || i == 504) ? EventLogger$FetchErrorReason.TIMEOUT : i >= 500 ? EventLogger$FetchErrorReason.SERVER_ERROR : i >= 400 ? EventLogger$FetchErrorReason.CLIENT_ERROR : EventLogger$FetchErrorReason.UNKNOWN;
    }

    public static final List q(List list) {
        int size = list.size();
        return size != 0 ? size != 1 ? Collections.unmodifiableList(new ArrayList(list)) : Collections.singletonList(ou.c0(list)) : EmptyList.c;
    }

    public static final Map r(Map map) {
        int size = map.size();
        if (size == 0) {
            return kotlin.collections.c.h0();
        }
        if (size != 1) {
            return Collections.unmodifiableMap(new LinkedHashMap(map));
        }
        Map.Entry entry = (Map.Entry) ou.b0(map.entrySet());
        return Collections.singletonMap(entry.getKey(), entry.getValue());
    }

    public static void s(ByteArrayOutputStream byteArrayOutputStream, long j, int i) throws IOException {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = (byte) ((j >> (i2 * 8)) & 255);
        }
        byteArrayOutputStream.write(bArr);
    }

    public static void t(ByteArrayOutputStream byteArrayOutputStream, int i) throws IOException {
        s(byteArrayOutputStream, i, 2);
    }

    public static String u(String str, String str2) {
        StringBuilder sb = new StringBuilder(str.length() + 1 + String.valueOf(str2).length());
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        return sb.toString();
    }

    public static String v(String str, String str2, String str3) {
        int length = String.valueOf(str2).length();
        StringBuilder sb = new StringBuilder(str.length() + 2 + length + String.valueOf(str3).length());
        sb.append(str);
        sb.append(":");
        sb.append(str2);
        sb.append(":");
        sb.append(str3);
        return sb.toString();
    }

    public abstract void e(float f, float f2, yc2 yc2Var);
}
