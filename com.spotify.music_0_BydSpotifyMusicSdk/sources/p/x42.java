package p;

import android.os.Handler;
import android.util.Log;
import com.google.common.base.Optional;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;

/* JADX INFO: loaded from: classes.dex */
public final class x42 implements uq1, q13, ah2 {
    public final /* synthetic */ int c;
    public Object e;
    public Object k;

    public /* synthetic */ x42(Object obj, int i, Object obj2) {
        this.c = i;
        this.e = obj;
        this.k = obj2;
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0046 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0041 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static p.x42 b(android.content.Context r5) {
        /*
            java.lang.String r0 = "generatefid.lock"
            r1 = 0
            java.io.File r2 = new java.io.File     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            java.io.File r5 = r5.getFilesDir()     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            r2.<init>(r5, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            java.io.RandomAccessFile r5 = new java.io.RandomAccessFile     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            java.lang.String r0 = "rw"
            r5.<init>(r2, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            java.nio.channels.FileChannel r5 = r5.getChannel()     // Catch: java.nio.channels.OverlappingFileLockException -> L30 java.lang.Error -> L34 java.io.IOException -> L36
            java.nio.channels.FileLock r0 = r5.lock()     // Catch: java.nio.channels.OverlappingFileLockException -> L29 java.lang.Error -> L2c java.io.IOException -> L2e
            p.x42 r2 = new p.x42     // Catch: java.nio.channels.OverlappingFileLockException -> L23 java.lang.Error -> L25 java.io.IOException -> L27
            r3 = 11
            r2.<init>(r5, r3, r0)     // Catch: java.nio.channels.OverlappingFileLockException -> L23 java.lang.Error -> L25 java.io.IOException -> L27
            return r2
        L23:
            r2 = move-exception
            goto L38
        L25:
            r2 = move-exception
            goto L38
        L27:
            r2 = move-exception
            goto L38
        L29:
            r2 = move-exception
        L2a:
            r0 = r1
            goto L38
        L2c:
            r2 = move-exception
            goto L2a
        L2e:
            r2 = move-exception
            goto L2a
        L30:
            r2 = move-exception
        L31:
            r5 = r1
            r0 = r5
            goto L38
        L34:
            r2 = move-exception
            goto L31
        L36:
            r2 = move-exception
            goto L31
        L38:
            java.lang.String r3 = "CrossProcessLock"
            java.lang.String r4 = "encountered error while creating and acquiring the lock, ignoring"
            android.util.Log.e(r3, r4, r2)
            if (r0 == 0) goto L44
            r0.release()     // Catch: java.io.IOException -> L44
        L44:
            if (r5 == 0) goto L49
            r5.close()     // Catch: java.io.IOException -> L49
        L49:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: p.x42.b(android.content.Context):p.x42");
    }

    @Override // p.q13
    public final Object a() {
        com.google.android.play.core.assetpacks.j jVar = (com.google.android.play.core.assetpacks.j) this.e;
        List list = (List) this.k;
        jVar.getClass();
        HashMap map = new HashMap();
        for (o13 o13Var : jVar.d.values()) {
            String str = o13Var.c.a;
            if (list.contains(str)) {
                o13 o13Var2 = (o13) map.get(str);
                if ((o13Var2 == null ? -1 : o13Var2.a) < o13Var.a) {
                    map.put(str, o13Var);
                }
            }
        }
        return map;
    }

    public final gh c() {
        String str = ((z31) this.e) == null ? " files" : "";
        if (str.isEmpty()) {
            return new gh((z31) this.e, (String) this.k);
        }
        throw new IllegalStateException("Missing required properties:".concat(str));
    }

    public final xo1 d(qo2 qo2Var) {
        ww1 ww1Var;
        Type type = qo2Var.b;
        u4.z(((Map) this.e).get(type));
        Map map = (Map) this.e;
        Class cls = qo2Var.a;
        u4.z(map.get(cls));
        int i = 0;
        xo1 pxVar = null;
        try {
            Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            if (!declaredConstructor.isAccessible()) {
                ((s22) this.k).a(declaredConstructor);
            }
            ww1Var = new ww1(this, declaredConstructor, 12);
        } catch (NoSuchMethodException unused) {
            ww1Var = null;
        }
        if (ww1Var != null) {
            return ww1Var;
        }
        int i2 = 2;
        int i3 = 1;
        if (Collection.class.isAssignableFrom(cls)) {
            pxVar = SortedSet.class.isAssignableFrom(cls) ? new yn(i3) : EnumSet.class.isAssignableFrom(cls) ? new ay2(this, type, i) : Set.class.isAssignableFrom(cls) ? new px(i3) : Queue.class.isAssignableFrom(cls) ? new w1(2) : new yn(i2);
        } else if (Map.class.isAssignableFrom(cls)) {
            pxVar = ConcurrentNavigableMap.class.isAssignableFrom(cls) ? new px(i2) : ConcurrentMap.class.isAssignableFrom(cls) ? new w1(0) : SortedMap.class.isAssignableFrom(cls) ? new yn(i) : (!(type instanceof ParameterizedType) || String.class.isAssignableFrom(new qo2(((ParameterizedType) type).getActualTypeArguments()[0]).a)) ? new w1(1) : new px(i);
        }
        return pxVar != null ? pxVar : new gh2(this, cls, type);
    }

    public final pp2 e(String str) {
        switch (this.c) {
            case 15:
                op2 op2Var = new op2();
                op2Var.c((ip2) this.e);
                op2Var.b = ((fj) this.k).a;
                ep2 ep2Var = ep2.e;
                dp2 dp2VarR = u11.r();
                dp2VarR.a = "ui_navigate";
                dp2VarR.c = "hit";
                dp2VarR.b = 1;
                dp2VarR.b(str, "destination");
                op2Var.d = dp2VarR.a();
                return (pp2) op2Var.a();
            default:
                op2 op2Var2 = new op2();
                op2Var2.c((ip2) this.e);
                op2Var2.b = ((gj) this.k).a;
                ep2 ep2Var2 = ep2.e;
                dp2 dp2VarR2 = u11.r();
                dp2VarR2.a = "ui_navigate";
                dp2VarR2.c = "hit";
                dp2VarR2.b = 1;
                dp2VarR2.b(str, "destination");
                op2Var2.d = dp2VarR2.a();
                return (pp2) op2Var2.a();
        }
    }

    public final void f(bk0 bk0Var) {
        boolean z;
        androidx.lifecycle.b bVar = (androidx.lifecycle.b) this.e;
        synchronized (bVar.a) {
            z = bVar.f == androidx.lifecycle.b.k;
            bVar.f = bk0Var;
        }
        if (z) {
            eb.w().x(bVar.j);
        }
        if (bk0Var instanceof sq1) {
            ((androidx.work.impl.utils.futures.b) this.k).j((sq1) bk0Var);
        } else if (bk0Var instanceof qq1) {
            ((androidx.work.impl.utils.futures.b) this.k).k(((qq1) bk0Var).q);
        }
    }

    public final void g(vv0 vv0Var) {
        int i = vv0Var.b;
        int i2 = 1;
        if (i == 0) {
            ((Handler) this.k).post(new xf1(this, (hk0) this.e, vv0Var.a, i2));
        } else {
            ((Handler) this.k).post(new a52(i, i2, this, (hk0) this.e));
        }
    }

    public final void h() {
        try {
            ((FileLock) this.k).release();
            ((FileChannel) this.e).close();
        } catch (IOException e) {
            Log.e("CrossProcessLock", "encountered error while releasing, ignoring", e);
        }
    }

    @Override // p.ah2
    public final b33 j(Object obj) {
        return ((com.google.firebase.crashlytics.internal.common.a) this.k).e.y(new a30(this, 1, (Boolean) obj));
    }

    public final String toString() {
        switch (this.c) {
            case 12:
                return ((Map) this.e).toString();
            default:
                return super.toString();
        }
    }

    public x42(int i) {
        this.c = i;
        if (i != 4) {
            if (i == 5) {
                this.k = new HashMap();
            } else if (i != 10) {
                this.e = new androidx.lifecycle.b();
                this.k = new androidx.work.impl.utils.futures.b();
                f(uq1.j);
            }
        }
    }

    public x42(Map map) {
        this.c = 12;
        this.k = s22.a;
        this.e = map;
    }

    public x42(zi ziVar) {
        this.c = 14;
        this.k = ziVar;
        hp2 hp2VarB = ziVar.b.b();
        hp2VarB.i.add(new jp2("back_arrow", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public x42(t00 t00Var) {
        this.c = 13;
        this.k = Optional.a();
        this.e = t00Var;
    }

    public x42(fj fjVar) {
        this.c = 15;
        this.k = fjVar;
        hp2 hp2VarB = fjVar.b.b();
        hp2VarB.i.add(new jp2("need_help", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public x42(gj gjVar) {
        this.c = 16;
        this.k = gjVar;
        hp2 hp2VarB = gjVar.b.b();
        hp2VarB.i.add(new jp2("need_help", null, null, null, null));
        hp2VarB.j = false;
        this.e = hp2VarB.a();
    }

    public x42(com.google.firebase.crashlytics.internal.common.a aVar, b33 b33Var) {
        this.c = 9;
        this.k = aVar;
        this.e = b33Var;
    }
}
