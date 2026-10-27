package c.a.d.q;

import android.view.MotionEvent;
import android.view.View;
import c.a.d.q.h;

/* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends h {

    /* JADX INFO: renamed from: c.a.d.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    public static class C0079a extends h.a {
        public C0079a() {
            this.f3645a = View.TRANSLATION_X;
        }

        @Override // c.a.d.q.h.a
        public void a(View view) {
            this.f3646b = view.getTranslationX();
            this.f3647c = view.getWidth();
        }
    }

    /* JADX INFO: compiled from: HorizontalOverScrollBounceEffectDecorator.java */
    public static class b extends h.e {
        @Override // c.a.d.q.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.f3655a = view.getTranslationX();
            this.f3656b = x;
            this.f3657c = x > 0.0f;
            return true;
        }
    }

    public a(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // c.a.d.q.h
    public h.a b() {
        return new C0079a();
    }

    @Override // c.a.d.q.h
    public h.e c() {
        return new b();
    }

    @Override // c.a.d.q.h
    public void f(View view, float f2) {
        view.setTranslationX(f2);
    }

    @Override // c.a.d.q.h
    public void g(View view, float f2, MotionEvent motionEvent) {
        view.setTranslationX(f2);
        motionEvent.offsetLocation(f2 - motionEvent.getX(0), 0.0f);
    }

    public a(c cVar, float f2, float f3, float f4) {
        super(cVar, f4, f2, f3);
    }
}
