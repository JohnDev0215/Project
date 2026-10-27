package l5;

import E0.X;
import android.content.Context;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.HandlerThread;
import android.os.Trace;
import android.view.Surface;
import c9.InterfaceC1048i;
import e2.C1208p;
import e2.L;
import h2.AbstractC1343A;
import h3.y;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.atomic.AtomicReference;
import l3.C1764u;
import n4.z;
import o5.AbstractC2047c;
import q5.C2185a;
import s3.Z;
import u.C2465e;

/* JADX INFO: renamed from: l5.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1778f implements InterfaceC1048i, s2.j, t4.b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f18638m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f18639n;

    public /* synthetic */ C1778f(int i7, byte b10) {
        this.l = i7;
    }

    public static String a(Class cls) {
        int modifiers = cls.getModifiers();
        if (Modifier.isInterface(modifiers)) {
            return "Interfaces can't be instantiated! Register an InstanceCreator or a TypeAdapter for this type. Interface name: ".concat(cls.getName());
        }
        if (!Modifier.isAbstract(modifiers)) {
            return null;
        }
        return "Abstract classes can't be instantiated! Adjust the R8 configuration or register an InstanceCreator or a TypeAdapter for this type. Class name: " + cls.getName() + "\nSee " + "https://github.com/google/gson/blob/main/Troubleshooting.md#".concat("r8-abstract-class");
    }

    public static z e(ImageDecoder.Source source, int i7, int i10, e4.h hVar) throws IOException {
        Drawable drawableDecodeDrawable = ImageDecoder.decodeDrawable(source, new m4.b(i7, i10, hVar));
        if (drawableDecodeDrawable instanceof AnimatedImageDrawable) {
            return new z((AnimatedImageDrawable) drawableDecodeDrawable, 2);
        }
        throw new IOException("Received unexpected drawable type for animated image, failing: " + drawableDecodeDrawable);
    }

    @Override // t4.b
    public void J() {
        Context context = (Context) this.f18638m;
        if (t4.e.f22461p == null) {
            synchronized (t4.e.class) {
                try {
                    if (t4.e.f22461p == null) {
                        t4.e.f22461p = new t4.e(context.getApplicationContext(), 0);
                    }
                } finally {
                }
            }
        }
        t4.e eVar = t4.e.f22461p;
        com.bumptech.glide.k kVar = (com.bumptech.glide.k) this.f18639n;
        synchronized (eVar) {
            ((HashSet) eVar.o).add(kVar);
            eVar.g();
        }
    }

    public void c() {
        switch (this.l) {
            case 1:
                this.f18638m = null;
                this.f18639n = null;
                break;
            default:
                int[] iArr = (int[]) this.f18638m;
                if (iArr != null) {
                    Arrays.fill(iArr, -1);
                }
                this.f18639n = null;
                break;
        }
    }

    @Override // s2.j
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public X b(H0.b bVar) throws Exception {
        MediaCodec mediaCodecCreateByCodecName;
        s2.l c1764u;
        int i7;
        String str = ((s2.n) bVar.f2765m).f22095a;
        X x8 = null;
        try {
            Trace.beginSection("createCodec:" + str);
            mediaCodecCreateByCodecName = MediaCodec.createByCodecName(str);
            try {
                C1208p c1208p = (C1208p) bVar.o;
                int i10 = AbstractC1343A.f15195a;
                if (i10 >= 34 && (i10 >= 35 || L.k(c1208p.f14316n))) {
                    c1764u = new C1764u(mediaCodecCreateByCodecName, 23);
                    i7 = 4;
                } else {
                    c1764u = new s2.e(mediaCodecCreateByCodecName, (HandlerThread) ((s2.c) this.f18639n).get());
                    i7 = 0;
                }
                X x9 = new X(mediaCodecCreateByCodecName, (HandlerThread) ((s2.c) this.f18638m).get(), c1764u, (y) bVar.f2769r);
                try {
                    Trace.endSection();
                    Surface surface = (Surface) bVar.f2767p;
                    if (surface == null && ((s2.n) bVar.f2765m).f22100h && i10 >= 35) {
                        i7 |= 8;
                    }
                    X.n(x9, (MediaFormat) bVar.f2766n, surface, (MediaCrypto) bVar.f2768q, i7);
                    return x9;
                } catch (Exception e10) {
                    e = e10;
                    x8 = x9;
                    if (x8 != null) {
                        x8.a();
                    } else if (mediaCodecCreateByCodecName != null) {
                        mediaCodecCreateByCodecName.release();
                    }
                    throw e;
                }
            } catch (Exception e11) {
                e = e11;
            }
        } catch (Exception e12) {
            e = e12;
            mediaCodecCreateByCodecName = null;
        }
    }

    public void f(int i7) {
        int[] iArr = (int[]) this.f18638m;
        if (iArr == null) {
            int[] iArr2 = new int[Math.max(i7, 10) + 1];
            this.f18638m = iArr2;
            Arrays.fill(iArr2, -1);
        } else if (i7 >= iArr.length) {
            int length = iArr.length;
            while (length <= i7) {
                length *= 2;
            }
            int[] iArr3 = new int[length];
            this.f18638m = iArr3;
            System.arraycopy(iArr, 0, iArr3, 0, iArr.length);
            int[] iArr4 = (int[]) this.f18638m;
            Arrays.fill(iArr4, iArr.length, iArr4.length, -1);
        }
    }

    public o h(C2185a c2185a) {
        String str;
        o qVar;
        int i7 = 29;
        int i10 = 28;
        int i11 = 27;
        Map map = (Map) this.f18638m;
        Type type = c2185a.f21254b;
        if (map.get(type) != null) {
            throw new ClassCastException();
        }
        Class cls = c2185a.f21253a;
        if (map.get(cls) != null) {
            throw new ClassCastException();
        }
        o eVar = null;
        C1777e c1777e = EnumSet.class.isAssignableFrom(cls) ? new C1777e(type, 0) : cls == EnumMap.class ? new C1777e(type, 1) : null;
        if (c1777e != null) {
            return c1777e;
        }
        AbstractC1776d.f((List) this.f18639n);
        if (Modifier.isAbstract(cls.getModifiers())) {
            qVar = null;
        } else {
            try {
                Constructor declaredConstructor = cls.getDeclaredConstructor(null);
                h6.r rVar = AbstractC2047c.f20529a;
                try {
                    declaredConstructor.setAccessible(true);
                    str = null;
                } catch (Exception e10) {
                    str = "Failed making constructor '" + AbstractC2047c.b(declaredConstructor) + "' accessible; either increase its visibility or write a custom InstanceCreator or TypeAdapter for its declaring type: " + e10.getMessage() + AbstractC2047c.e(e10);
                }
                qVar = str != null ? new B2.q(str, 8) : new C1764u(declaredConstructor, 4);
            } catch (NoSuchMethodException unused) {
                qVar = null;
            }
        }
        if (qVar != null) {
            return qVar;
        }
        if (Collection.class.isAssignableFrom(cls)) {
            eVar = SortedSet.class.isAssignableFrom(cls) ? new C8.d(i11) : Set.class.isAssignableFrom(cls) ? new Y4.e(i11) : Queue.class.isAssignableFrom(cls) ? new io.sentry.hints.i(i11) : new C8.d(i10);
        } else if (Map.class.isAssignableFrom(cls)) {
            eVar = ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new Y4.e(i10) : ConcurrentMap.class.isAssignableFrom(cls) ? new io.sentry.hints.i(i10) : SortedMap.class.isAssignableFrom(cls) ? new C8.d(i7) : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new C2185a(((ParameterizedType) type).getActualTypeArguments()[0]).f21253a)) ? new io.sentry.hints.i(i7) : new Y4.e(i7);
        }
        if (eVar != null) {
            return eVar;
        }
        String strA = a(cls);
        return strA != null ? new B2.q(strA, 7) : new C1764u(cls, 3);
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00d0 A[PHI: r15 r16 r17
  0x00d0: PHI (r15v5 java.lang.Integer) = (r15v4 java.lang.Integer), (r15v9 java.lang.Integer) binds: [B:56:0x00fc, B:38:0x00c6] A[DONT_GENERATE, DONT_INLINE]
  0x00d0: PHI (r16v8 java.lang.Integer) = (r16v6 java.lang.Integer), (r16v4 java.lang.Integer) binds: [B:56:0x00fc, B:38:0x00c6] A[DONT_GENERATE, DONT_INLINE]
  0x00d0: PHI (r17v17 boolean) = (r17v11 boolean), (r17v20 boolean) binds: [B:56:0x00fc, B:38:0x00c6] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0151  */
    @Override // c9.InterfaceC1048i
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void j(g9.j r20, c9.H r21) {
        /*
            Method dump skipped, instruction units count: 464
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l5.C1778f.j(g9.j, c9.H):void");
    }

    public void k(int i7, int i10) {
        int[] iArr = (int[]) this.f18638m;
        if (iArr == null || i7 >= iArr.length) {
            return;
        }
        int i11 = i7 + i10;
        f(i11);
        int[] iArr2 = (int[]) this.f18638m;
        System.arraycopy(iArr2, i7, iArr2, i11, (iArr2.length - i7) - i10);
        Arrays.fill((int[]) this.f18638m, i7, i11, -1);
        ArrayList arrayList = (ArrayList) this.f18639n;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Z z6 = (Z) ((ArrayList) this.f18639n).get(size);
            int i12 = z6.l;
            if (i12 >= i7) {
                z6.l = i12 + i10;
            }
        }
    }

    public void l(int i7, int i10) {
        int[] iArr = (int[]) this.f18638m;
        if (iArr == null || i7 >= iArr.length) {
            return;
        }
        int i11 = i7 + i10;
        f(i11);
        int[] iArr2 = (int[]) this.f18638m;
        System.arraycopy(iArr2, i11, iArr2, i7, (iArr2.length - i7) - i10);
        int[] iArr3 = (int[]) this.f18638m;
        Arrays.fill(iArr3, iArr3.length - i10, iArr3.length, -1);
        ArrayList arrayList = (ArrayList) this.f18639n;
        if (arrayList == null) {
            return;
        }
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Z z6 = (Z) ((ArrayList) this.f18639n).get(size);
            int i12 = z6.l;
            if (i12 >= i7) {
                if (i12 < i11) {
                    ((ArrayList) this.f18639n).remove(size);
                } else {
                    z6.l = i12 - i10;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:148:0x022f, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0140  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m(android.content.Context r14, android.content.res.XmlResourceParser r15) {
        /*
            Method dump skipped, instruction units count: 670
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l5.C1778f.m(android.content.Context, android.content.res.XmlResourceParser):void");
    }

    @Override // c9.InterfaceC1048i
    public void r(g9.j jVar, IOException iOException) {
        A7.m.f(jVar, "call");
        ((q9.g) this.f18638m).c(iOException);
    }

    public String toString() {
        switch (this.l) {
            case 0:
                return ((Map) this.f18638m).toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ C1778f(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f18638m = obj;
        this.f18639n = obj2;
    }

    public C1778f() {
        this.l = 9;
        this.f18638m = new AtomicReference();
        this.f18639n = new C2465e(0);
    }

    public C1778f(Context context, com.bumptech.glide.k kVar) {
        this.l = 8;
        this.f18638m = context.getApplicationContext();
        this.f18639n = kVar;
    }

    public C1778f(int i7) {
        this.l = 6;
        s2.c cVar = new s2.c(i7, 0);
        s2.c cVar2 = new s2.c(i7, 1);
        this.f18638m = cVar;
        this.f18639n = cVar2;
    }
}
