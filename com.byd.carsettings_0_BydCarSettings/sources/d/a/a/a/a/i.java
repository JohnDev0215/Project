package d.a.a.a.a;

import android.view.MotionEvent;
import android.view.View;
import d.a.a.a.a.g;

/* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes3.dex */
public class i extends g {

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class a extends g.a {
        public a() {
            this.f3903a = View.TRANSLATION_Y;
        }

        @Override // d.a.a.a.a.g.a
        public void a(View view) {
            this.f3904b = view.getTranslationY();
            this.f3905c = view.getHeight();
        }
    }

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    public static class b extends g.e {
        @Override // d.a.a.a.a.g.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.f3913a = view.getTranslationY();
            this.f3914b = y;
            this.f3915c = y > 0.0f;
            return true;
        }
    }

    public i(d.a.a.a.a.j.a aVar) {
        this(aVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // d.a.a.a.a.g
    public g.a b() {
        return new a();
    }

    @Override // d.a.a.a.a.g
    public g.e c() {
        return new b();
    }

    @Override // d.a.a.a.a.g
    public void f(View view, float f2) {
        view.setTranslationY(f2);
    }

    @Override // d.a.a.a.a.g
    public void g(View view, float f2, MotionEvent motionEvent) {
        view.setTranslationY(f2);
        motionEvent.offsetLocation(f2 - motionEvent.getY(0), 0.0f);
    }

    public i(d.a.a.a.a.j.a aVar, float f2, float f3, float f4) {
        super(aVar, f4, f2, f3);
    }
}
