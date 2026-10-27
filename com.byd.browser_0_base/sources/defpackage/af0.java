package defpackage;

import android.view.View;
import defpackage.af0;
import defpackage.c90;
import defpackage.q5;
import java.util.ArrayList;

/* JADX INFO: compiled from: DynamicAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class af0<T extends af0<T>> implements q5.b {
    public static final b l = new b();
    public static final c m = new c();
    public static final d n = new d();
    public static final e o = new e();
    public static final f p = new f();
    public static final a q = new a();
    public float a;
    public float b;
    public boolean c;
    public final Object d;
    public final sn0 e;
    public boolean f;
    public final float g;
    public long h;
    public final float i;
    public final ArrayList<h> j;
    public final ArrayList<i> k;

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class a extends j {
        public a() {
            super("alpha");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getAlpha();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setAlpha(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class b extends j {
        public b() {
            super("scaleX");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getScaleX();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setScaleX(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class c extends j {
        public c() {
            super("scaleY");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getScaleY();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setScaleY(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class d extends j {
        public d() {
            super("rotation");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getRotation();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setRotation(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class e extends j {
        public e() {
            super("rotationX");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getRotationX();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setRotationX(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public class f extends j {
        public f() {
            super("rotationY");
        }

        @Override // defpackage.sn0
        public final float d(Object obj) {
            return ((View) obj).getRotationY();
        }

        @Override // defpackage.sn0
        public final void k(Object obj, float f) {
            ((View) obj).setRotationY(f);
        }
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public static class g {
        public float a;
        public float b;
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public interface h {
        void a();
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public interface i {
        void a();
    }

    /* JADX INFO: compiled from: DynamicAnimation.java */
    public static abstract class j extends sn0 {
        public j(String str) {
            super(str);
        }
    }

    public af0(Object obj) {
        c90.a aVar = c90.u;
        this.a = 0.0f;
        this.b = Float.MAX_VALUE;
        this.c = false;
        this.f = false;
        this.g = -3.4028235E38f;
        this.h = 0L;
        this.j = new ArrayList<>();
        this.k = new ArrayList<>();
        this.d = obj;
        this.e = aVar;
        if (aVar == n || aVar == o || aVar == p) {
            this.i = 0.1f;
            return;
        }
        if (aVar == q) {
            this.i = 0.00390625f;
        } else if (aVar == l || aVar == m) {
            this.i = 0.002f;
        } else {
            this.i = 1.0f;
        }
    }

    public static q5 b() {
        ThreadLocal<q5> threadLocal = q5.i;
        if (threadLocal.get() == null) {
            threadLocal.set(new q5(new q5.d()));
        }
        return threadLocal.get();
    }

    @Override // q5.b
    public final boolean a(long j2) {
        ArrayList<h> arrayList;
        long j3 = this.h;
        int i2 = 0;
        if (j3 == 0) {
            this.h = j2;
            c(this.b);
            return false;
        }
        long j4 = j2 - j3;
        this.h = j2;
        float f2 = b().g;
        boolean zD = d(f2 == 0.0f ? 2147483647L : (long) (j4 / f2));
        float fMin = Math.min(this.b, Float.MAX_VALUE);
        this.b = fMin;
        float fMax = Math.max(fMin, this.g);
        this.b = fMax;
        c(fMax);
        if (zD) {
            this.f = false;
            q5 q5VarB = b();
            q5VarB.a.remove(this);
            ArrayList<q5.b> arrayList2 = q5VarB.b;
            int iIndexOf = arrayList2.indexOf(this);
            if (iIndexOf >= 0) {
                arrayList2.set(iIndexOf, null);
                q5VarB.f = true;
            }
            this.h = 0L;
            this.c = false;
            while (true) {
                arrayList = this.j;
                if (i2 >= arrayList.size()) {
                    break;
                }
                if (arrayList.get(i2) != null) {
                    arrayList.get(i2).a();
                }
                i2++;
            }
            int size = arrayList.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                }
                if (arrayList.get(size) == null) {
                    arrayList.remove(size);
                }
            }
        }
        return zD;
    }

    public final void c(float f2) {
        ArrayList<i> arrayList;
        this.e.k(this.d, f2);
        int i2 = 0;
        while (true) {
            arrayList = this.k;
            if (i2 >= arrayList.size()) {
                break;
            }
            if (arrayList.get(i2) != null) {
                arrayList.get(i2).a();
            }
            i2++;
        }
        int size = arrayList.size();
        while (true) {
            size--;
            if (size < 0) {
                return;
            }
            if (arrayList.get(size) == null) {
                arrayList.remove(size);
            }
        }
    }

    public abstract boolean d(long j2);

    public void removeEndListener(h hVar) {
        ArrayList<h> arrayList = this.j;
        int iIndexOf = arrayList.indexOf(hVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }

    public void removeUpdateListener(i iVar) {
        ArrayList<i> arrayList = this.k;
        int iIndexOf = arrayList.indexOf(iVar);
        if (iIndexOf >= 0) {
            arrayList.set(iIndexOf, null);
        }
    }
}
