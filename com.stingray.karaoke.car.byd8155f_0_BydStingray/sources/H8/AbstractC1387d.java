package h8;

import F8.AbstractC0287w;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import T.C0746i0;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.ParcelFileDescriptor;
import android.view.WindowManager;
import e2.M;
import g1.AbstractC1274a;
import io.sentry.C1523x;
import io.sentry.I1;
import io.sentry.J1;
import io.sentry.android.core.H;
import io.sentry.android.replay.u;
import j8.C1579y;
import j8.G;
import j8.Q;
import j8.Z;
import java.io.ByteArrayInputStream;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.List;
import n7.AbstractC1998m;
import o7.C2050b;
import o8.C2058c;
import r9.C2317g;
import r9.C2319i;
import v8.AbstractC2564d;

/* JADX INFO: renamed from: h8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1387d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f15605a = 0;

    public static C2050b a(C2050b c2050b) {
        c2050b.t();
        c2050b.f20544n = true;
        return c2050b.f20543m > 0 ? c2050b : C2050b.o;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0020  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.String b(Q7.InterfaceC0652e r3, h8.C1390g r4) {
        /*
            java.lang.String r0 = "klass"
            A7.m.f(r3, r0)
            java.lang.String r0 = "typeMappingConfiguration"
            A7.m.f(r4, r0)
            Q7.j r0 = r3.o()
            java.lang.String r1 = "klass.containingDeclaration"
            A7.m.e(r0, r1)
            o8.f r1 = r3.getName()
            if (r1 == 0) goto L20
            o8.f r2 = o8.AbstractC2063h.f20575a
            boolean r2 = r1.f20573m
            if (r2 != 0) goto L20
            goto L22
        L20:
            o8.f r1 = o8.AbstractC2063h.c
        L22:
            java.lang.String r1 = r1.c()
            boolean r2 = r0 instanceof Q7.D
            if (r2 == 0) goto L56
            Q7.D r0 = (Q7.D) r0
            T7.B r0 = (T7.B) r0
            o8.c r3 = r0.f8767q
            boolean r4 = r3.d()
            if (r4 == 0) goto L37
            goto L55
        L37:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r3 = r3.b()
            r0 = 46
            r2 = 47
            java.lang.String r3 = Q8.o.w0(r3, r0, r2)
            r4.append(r3)
            r4.append(r2)
            r4.append(r1)
            java.lang.String r1 = r4.toString()
        L55:
            return r1
        L56:
            boolean r2 = r0 instanceof Q7.InterfaceC0652e
            if (r2 == 0) goto L5e
            r2 = r0
            Q7.e r2 = (Q7.InterfaceC0652e) r2
            goto L5f
        L5e:
            r2 = 0
        L5f:
            if (r2 == 0) goto L7a
            java.lang.String r3 = b(r2, r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r4.append(r3)
            r3 = 36
            r4.append(r3)
            r4.append(r1)
            java.lang.String r3 = r4.toString()
            return r3
        L7a:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "Unexpected container: "
            r1.<init>(r2)
            r1.append(r0)
            java.lang.String r0 = " for "
            r1.append(r0)
            r1.append(r3)
            java.lang.String r3 = r1.toString()
            r4.<init>(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: h8.AbstractC1387d.b(Q7.e, h8.g):java.lang.String");
    }

    public static C1523x c(Object obj) {
        C1523x c1523x = new C1523x();
        c1523x.c(obj, "sentry:typeCheckHint");
        return c1523x;
    }

    public static void d(String str) {
        A7.m.f(str, "text");
    }

    public static Bitmap e(int i7, int i10, byte[] bArr) throws IOException {
        BitmapFactory.Options options;
        int i11 = 0;
        if (i10 != -1) {
            options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(bArr, 0, i7, options);
            options.inJustDecodeBounds = false;
            options.inSampleSize = 1;
            for (int iMax = Math.max(options.outWidth, options.outHeight); iMax > i10; iMax /= 2) {
                options.inSampleSize *= 2;
            }
        } else {
            options = null;
        }
        Bitmap bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, i7, options);
        if (options != null) {
            options.inSampleSize = 1;
        }
        if (bitmapDecodeByteArray == null) {
            throw M.a(new IllegalStateException(), "Could not decode image data");
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        try {
            W1.g gVar = new W1.g(byteArrayInputStream);
            byteArrayInputStream.close();
            switch (gVar.c()) {
                case 3:
                case 4:
                    i11 = 180;
                    break;
                case 5:
                case 8:
                    i11 = 270;
                    break;
                case 6:
                case 7:
                    i11 = 90;
                    break;
            }
            if (i11 == 0) {
                return bitmapDecodeByteArray;
            }
            Matrix matrix = new Matrix();
            matrix.postRotate(i11);
            return Bitmap.createBitmap(bitmapDecodeByteArray, 0, 0, bitmapDecodeByteArray.getWidth(), bitmapDecodeByteArray.getHeight(), matrix, false);
        } catch (Throwable th) {
            try {
                byteArrayInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static u f(Context context, J1 j12) {
        Rect rect;
        A7.m.f(context, "context");
        A7.m.f(j12, "sessionReplay");
        Object systemService = context.getSystemService("window");
        A7.m.d(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        WindowManager windowManager = (WindowManager) systemService;
        if (Build.VERSION.SDK_INT >= 30) {
            rect = windowManager.getCurrentWindowMetrics().getBounds();
        } else {
            Point point = new Point();
            windowManager.getDefaultDisplay().getRealSize(point);
            rect = new Rect(0, 0, point.x, point.y);
        }
        A7.m.e(rect, "if (VERSION.SDK_INT >= V…enBounds.y)\n            }");
        float fHeight = rect.height() / context.getResources().getDisplayMetrics().density;
        I1 i12 = j12.f15882e;
        int iW = C7.a.W(fHeight * i12.sizeScale);
        int i7 = iW % 16;
        Integer numValueOf = Integer.valueOf(i7 <= 8 ? iW - i7 : iW + (16 - i7));
        int iW2 = C7.a.W((rect.width() / context.getResources().getDisplayMetrics().density) * i12.sizeScale);
        int i10 = iW2 % 16;
        Integer numValueOf2 = Integer.valueOf(i10 <= 8 ? iW2 - i10 : iW2 + (16 - i10));
        int iIntValue = numValueOf.intValue();
        int iIntValue2 = numValueOf2.intValue();
        return new u(iIntValue2, iIntValue, iIntValue2 / rect.width(), iIntValue / rect.height(), j12.f15883f, i12.bitRate);
    }

    public static Object g(C1523x c1523x) {
        Object obj;
        synchronized (c1523x) {
            obj = c1523x.f16982a.get("sentry:typeCheckHint");
        }
        return obj;
    }

    public static boolean h(C1523x c1523x, Class cls) {
        return cls.isInstance(g(c1523x));
    }

    public static boolean i(C1523x c1523x) {
        return Boolean.TRUE.equals(c1523x.b(Boolean.class, "sentry:isFromHybridSdk"));
    }

    public static List j(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        A7.m.e(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x0110  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object k(F8.AbstractC0287w r25, h8.r r26, z7.InterfaceC2786o r27) {
        /*
            Method dump skipped, instruction units count: 1042
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h8.AbstractC1387d.k(F8.w, h8.r, z7.o):java.lang.Object");
    }

    public static MappedByteBuffer l(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "r", null);
            if (parcelFileDescriptorOpenFileDescriptor == null) {
                if (parcelFileDescriptorOpenFileDescriptor != null) {
                    parcelFileDescriptorOpenFileDescriptor.close();
                }
                return null;
            }
            try {
                FileDescriptor fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
                io.sentry.instrumentation.file.e eVarI = h6.j.i(new FileInputStream(fileDescriptor), fileDescriptor);
                try {
                    FileChannel channel = eVarI.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    eVarI.close();
                    parcelFileDescriptorOpenFileDescriptor.close();
                    return map;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static final Q m(Q q4, C0746i0 c0746i0) {
        A7.m.f(q4, "<this>");
        A7.m.f(c0746i0, "typeTable");
        int i7 = q4.f17184n;
        if ((i7 & 256) == 256) {
            return q4.f17193x;
        }
        if ((i7 & 512) == 512) {
            return c0746i0.c(q4.f17194y);
        }
        return null;
    }

    public static void n(String str) {
        try {
            Class<?> cls = Class.forName(str);
            try {
                throw new RuntimeException("Expected instanceof GlideModule, but found: " + cls.getDeclaredConstructor(null).newInstance(null));
            } catch (IllegalAccessException e10) {
                v(cls, e10);
                throw null;
            } catch (InstantiationException e11) {
                v(cls, e11);
                throw null;
            } catch (NoSuchMethodException e12) {
                v(cls, e12);
                throw null;
            } catch (InvocationTargetException e13) {
                v(cls, e13);
                throw null;
            }
        } catch (ClassNotFoundException e14) {
            throw new IllegalArgumentException("Unable to find GlideModule implementation", e14);
        }
    }

    public static final Q o(C1579y c1579y, C0746i0 c0746i0) {
        A7.m.f(c1579y, "<this>");
        A7.m.f(c0746i0, "typeTable");
        if (c1579y.p()) {
            return c1579y.f17495u;
        }
        if ((c1579y.f17489n & 64) == 64) {
            return c0746i0.c(c1579y.f17496v);
        }
        return null;
    }

    public static final boolean p(AbstractC0287w abstractC0287w) {
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN == null || !r8.h.b(interfaceC0654gN) || AbstractC2564d.g((InterfaceC0652e) interfaceC0654gN).equals(N7.o.f4872g)) {
            InterfaceC0654g interfaceC0654gN2 = abstractC0287w.B0().n();
            Q7.Q q4 = interfaceC0654gN2 instanceof Q7.Q ? (Q7.Q) interfaceC0654gN2 : null;
            if (!(q4 == null ? false : p(AbstractC1274a.M(q4)))) {
                return false;
            }
        }
        return true;
    }

    public static final Q q(C1579y c1579y, C0746i0 c0746i0) {
        A7.m.f(c1579y, "<this>");
        A7.m.f(c0746i0, "typeTable");
        int i7 = c1579y.f17489n;
        if ((i7 & 8) == 8) {
            Q q4 = c1579y.f17492r;
            A7.m.e(q4, "returnType");
            return q4;
        }
        if ((i7 & 16) == 16) {
            return c0746i0.c(c1579y.f17493s);
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Function");
    }

    public static final Q r(G g7, C0746i0 c0746i0) {
        A7.m.f(g7, "<this>");
        A7.m.f(c0746i0, "typeTable");
        int i7 = g7.f17118n;
        if ((i7 & 8) == 8) {
            Q q4 = g7.f17121r;
            A7.m.e(q4, "returnType");
            return q4;
        }
        if ((i7 & 16) == 16) {
            return c0746i0.c(g7.f17122s);
        }
        throw new IllegalStateException("No returnType in ProtoBuf.Property");
    }

    public static boolean s(C1523x c1523x) {
        return !(io.sentry.hints.d.class.isInstance(g(c1523x)) || io.sentry.hints.b.class.isInstance(g(c1523x))) || H.class.isInstance(g(c1523x));
    }

    public static List t(List list) {
        List listB0 = AbstractC1998m.B0(list);
        Collections.shuffle(listB0);
        return listB0;
    }

    public static final C2058c u(C2058c c2058c, C2058c c2058c2) {
        A7.m.f(c2058c, "<this>");
        A7.m.f(c2058c2, "prefix");
        if (!c2058c.equals(c2058c2) && !c2058c2.d()) {
            String strB = c2058c.b();
            String strB2 = c2058c2.b();
            if (!Q8.o.z0(strB, strB2, false) || strB.charAt(strB2.length()) != '.') {
                return c2058c;
            }
        }
        if (c2058c2.d()) {
            return c2058c;
        }
        if (c2058c.equals(c2058c2)) {
            C2058c c2058c3 = C2058c.c;
            A7.m.e(c2058c3, "ROOT");
            return c2058c3;
        }
        String strSubstring = c2058c.b().substring(c2058c2.b().length() + 1);
        A7.m.e(strSubstring, "this as java.lang.String).substring(startIndex)");
        return new C2058c(strSubstring);
    }

    public static void v(Class cls, ReflectiveOperationException reflectiveOperationException) {
        throw new RuntimeException("Unable to instantiate GlideModule implementation for " + cls, reflectiveOperationException);
    }

    public static final m7.i w(Integer num, Integer num2) {
        return new m7.i(num, num2);
    }

    public static void x(C2317g c2317g, byte[] bArr) {
        long j3;
        A7.m.f(c2317g, "cursor");
        A7.m.f(bArr, "key");
        int length = bArr.length;
        int i7 = 0;
        do {
            byte[] bArr2 = c2317g.f21928p;
            int i10 = c2317g.f21929q;
            int i11 = c2317g.f21930r;
            if (bArr2 != null) {
                while (i10 < i11) {
                    int i12 = i7 % length;
                    bArr2[i10] = (byte) (bArr2[i10] ^ bArr[i12]);
                    i10++;
                    i7 = i12 + 1;
                }
            }
            long j10 = c2317g.o;
            C2319i c2319i = c2317g.l;
            A7.m.c(c2319i);
            if (j10 == c2319i.f21932m) {
                throw new IllegalStateException("no more bytes");
            }
            j3 = c2317g.o;
        } while (c2317g.g(j3 == -1 ? 0L : j3 + ((long) (c2317g.f21930r - c2317g.f21929q))) != -1);
    }

    public static final Q y(Z z6, C0746i0 c0746i0) {
        A7.m.f(c0746i0, "typeTable");
        int i7 = z6.f17255n;
        if ((i7 & 4) == 4) {
            Q q4 = z6.f17257q;
            A7.m.e(q4, "type");
            return q4;
        }
        if ((i7 & 8) == 8) {
            return c0746i0.c(z6.f17258r);
        }
        throw new IllegalStateException("No type in ProtoBuf.ValueParameter");
    }
}
