package c.b.a.a.j;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import c.b.a.a.j.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f1701a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f1702b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f, e eVar, e eVar2) {
            this.f1702b.b(c.b.a.a.r.a.d(eVar.f1705a, eVar2.f1705a, f), c.b.a.a.r.a.d(eVar.f1706b, eVar2.f1706b, f), c.b.a.a.r.a.d(eVar.f1707c, eVar2.f1707c, f));
            return this.f1702b;
        }
    }

    /* JADX INFO: renamed from: c.b.a.a.j.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0053c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f1703a = new C0053c("circularReveal");

        public C0053c(String str) {
            super(e.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e get(c cVar) {
            return cVar.getRevealInfo();
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(c cVar, e eVar) {
            cVar.setRevealInfo(eVar);
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class d extends Property<c, Integer> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, Integer> f1704a = new d("circularRevealScrimColor");

        public d(String str) {
            super(Integer.class, str);
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Integer get(c cVar) {
            return Integer.valueOf(cVar.getCircularRevealScrimColor());
        }

        @Override // android.util.Property
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void set(c cVar, Integer num) {
            cVar.setCircularRevealScrimColor(num.intValue());
        }
    }

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public float f1705a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f1706b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f1707c;

        public boolean a() {
            return this.f1707c == Float.MAX_VALUE;
        }

        public void b(float f, float f2, float f3) {
            this.f1705a = f;
            this.f1706b = f2;
            this.f1707c = f3;
        }

        public void c(e eVar) {
            b(eVar.f1705a, eVar.f1706b, eVar.f1707c);
        }

        public e() {
        }

        public e(float f, float f2, float f3) {
            this.f1705a = f;
            this.f1706b = f2;
            this.f1707c = f3;
        }

        public e(e eVar) {
            this(eVar.f1705a, eVar.f1706b, eVar.f1707c);
        }
    }

    void b();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i);

    void setRevealInfo(e eVar);
}
