package b.a.a.a.r;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
public interface d {

    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f1763a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final e f1764b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f, e eVar, e eVar2) {
            this.f1764b.a(b.a.a.a.w.a.c(eVar.f1767a, eVar2.f1767a, f), b.a.a.a.w.a.c(eVar.f1768b, eVar2.f1768b, f), b.a.a.a.w.a.c(eVar.f1769c, eVar2.f1769c, f));
            return this.f1764b;
        }
    }

    public static class c extends Property<d, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<d, e> f1765a = new c("circularReveal");

        private c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e get(d dVar) {
            return dVar.getRevealInfo();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, e eVar) {
            dVar.setRevealInfo(eVar);
        }
    }

    /* JADX INFO: renamed from: b.a.a.a.r.d$d, reason: collision with other inner class name */
    public static class C0053d extends Property<d, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<d, Integer> f1766a = new C0053d("circularRevealScrimColor");

        private C0053d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(d dVar) {
            return Integer.valueOf(dVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(d dVar, Integer num) {
            dVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1767a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1768b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1769c;

        private e() {
        }

        public e(float f, float f2, float f3) {
            this.f1767a = f;
            this.f1768b = f2;
            this.f1769c = f3;
        }

        public void a(float f, float f2, float f3) {
            this.f1767a = f;
            this.f1768b = f2;
            this.f1769c = f3;
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
