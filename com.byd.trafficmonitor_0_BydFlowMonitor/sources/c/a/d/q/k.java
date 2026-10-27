package c.a.d.q;

import android.view.MotionEvent;
import android.view.View;
import c.a.d.q.h;

/* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends h {

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class a extends h.a {
        public a() {
            this.f3645a = View.TRANSLATION_Y;
        }

        @Override // c.a.d.q.h.a
        public void a(View view) {
            this.f3646b = view.getTranslationY();
            this.f3647c = view.getHeight();
        }
    }

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class b extends h.e {
        @Override // c.a.d.q.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.f3655a = view.getTranslationY();
            this.f3656b = y;
            this.f3657c = y > 0.0f;
            return true;
        }
    }

    public k(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // c.a.d.q.h
    public h.a b() {
        return new a();
    }

    @Override // c.a.d.q.h
    public h.e c() {
        return new b();
    }

    @Override // c.a.d.q.h
    public void f(View view, float f2) {
        view.setTranslationY(f2);
    }

    @Override // c.a.d.q.h
    public void g(View view, float f2, MotionEvent motionEvent) {
        view.setTranslationY(f2);
        motionEvent.offsetLocation(f2 - motionEvent.getY(0), 0.0f);
    }

    public k(c cVar, float f2, float f3, float f4) {
        super(cVar, f4, f2, f3);
    }
}
