package b.d.a.a.u;

import a.b.k.l;
import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public interface c {

    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final TypeEvaluator<e> f2058b = new b();

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final e f2059a = new e(null);

        @Override // android.animation.TypeEvaluator
        public e evaluate(float f2, e eVar, e eVar2) {
            e eVar3 = eVar;
            e eVar4 = eVar2;
            e eVar5 = this.f2059a;
            float fS = l.i.S(eVar3.f2062a, eVar4.f2062a, f2);
            float fS2 = l.i.S(eVar3.f2063b, eVar4.f2063b, f2);
            float fS3 = l.i.S(eVar3.f2064c, eVar4.f2064c, f2);
            eVar5.f2062a = fS;
            eVar5.f2063b = fS2;
            eVar5.f2064c = fS3;
            return this.f2059a;
        }
    }

    /* JADX INFO: renamed from: b.d.a.a.u.c$c, reason: collision with other inner class name */
    public static class C0042c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f2060a = new C0042c("circularReveal");

        public C0042c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        public e get(c cVar) {
            return cVar.getRevealInfo();
        }

        @Override // android.util.Property
        public void set(c cVar, e eVar) {
            cVar.setRevealInfo(eVar);
        }
    }

    public static class d extends Property<c, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, Integer> f2061a = new d("circularRevealScrimColor");

        public d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        public Integer get(c cVar) {
            return Integer.valueOf(cVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        public void set(c cVar, Integer num) {
            cVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f2062a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f2063b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f2064c;

        public e() {
        }

        public e(float f2, float f3, float f4) {
            this.f2062a = f2;
            this.f2063b = f3;
            this.f2064c = f4;
        }

        public e(a aVar) {
        }
    }

    void a();

    void b();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
