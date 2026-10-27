package c.b.a.a.v;

import android.animation.TypeEvaluator;
import android.graphics.drawable.Drawable;
import android.util.Property;
import c.b.a.a.v.b;

/* JADX INFO: compiled from: CircularRevealWidget.java */
/* JADX INFO: loaded from: classes.dex */
public interface c extends b.a {

    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class b implements TypeEvaluator<e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final TypeEvaluator<e> f4093a = new b();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final e f4094b = new e();

        @Override // android.animation.TypeEvaluator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public e evaluate(float f2, e eVar, e eVar2) {
            this.f4094b.b(c.b.a.a.e0.a.d(eVar.f4097a, eVar2.f4097a, f2), c.b.a.a.e0.a.d(eVar.f4098b, eVar2.f4098b, f2), c.b.a.a.e0.a.d(eVar.f4099c, eVar2.f4099c, f2));
            return this.f4094b;
        }
    }

    /* JADX INFO: renamed from: c.b.a.a.v.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CircularRevealWidget.java */
    public static class C0093c extends Property<c, e> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final Property<c, e> f4095a = new C0093c("circularReveal");

        public C0093c(String str) {
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
        public static final Property<c, Integer> f4096a = new d("circularRevealScrimColor");

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
        public float f4097a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public float f4098b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public float f4099c;

        public boolean a() {
            return this.f4099c == Float.MAX_VALUE;
        }

        public void b(float f2, float f3, float f4) {
            this.f4097a = f2;
            this.f4098b = f3;
            this.f4099c = f4;
        }

        public void c(e eVar) {
            b(eVar.f4097a, eVar.f4098b, eVar.f4099c);
        }

        public e() {
        }

        public e(float f2, float f3, float f4) {
            this.f4097a = f2;
            this.f4098b = f3;
            this.f4099c = f4;
        }

        public e(e eVar) {
            this(eVar.f4097a, eVar.f4098b, eVar.f4099c);
        }
    }

    void b();

    void c();

    int getCircularRevealScrimColor();

    e getRevealInfo();

    void setCircularRevealOverlayDrawable(Drawable drawable);

    void setCircularRevealScrimColor(int i2);

    void setRevealInfo(e eVar);
}
