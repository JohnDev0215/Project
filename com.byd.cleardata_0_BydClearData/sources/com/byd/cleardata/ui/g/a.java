package com.byd.cleardata.ui.g;

import android.view.MotionEvent;
import android.view.View;
import com.byd.cleardata.ui.g.h;

/* JADX INFO: loaded from: classes.dex */
public class a extends h {

    /* JADX INFO: renamed from: com.byd.cleardata.ui.g.a$a, reason: collision with other inner class name */
    protected static class C0107a extends h.a {
        public C0107a() {
            this.f2889a = View.TRANSLATION_X;
        }

        @Override // com.byd.cleardata.ui.g.h.a
        protected void a(View view) {
            this.f2890b = view.getTranslationX();
            this.f2891c = view.getWidth();
        }
    }

    protected static class b extends h.e {
        protected b() {
        }

        @Override // com.byd.cleardata.ui.g.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            float x = motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0);
            if (Math.abs(x) < Math.abs(y)) {
                return false;
            }
            this.f2898a = view.getTranslationX();
            this.f2899b = x;
            this.f2900c = x > 0.0f;
            return true;
        }
    }

    public a(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    public a(c cVar, float f, float f2, float f3) {
        super(cVar, f3, f, f2);
    }

    @Override // com.byd.cleardata.ui.g.h
    protected h.a b() {
        return new C0107a();
    }

    @Override // com.byd.cleardata.ui.g.h
    protected h.e c() {
        return new b();
    }

    @Override // com.byd.cleardata.ui.g.h
    protected void f(View view, float f) {
        view.setTranslationX(f);
    }

    @Override // com.byd.cleardata.ui.g.h
    protected void g(View view, float f, MotionEvent motionEvent) {
        view.setTranslationX(f);
        motionEvent.offsetLocation(f - motionEvent.getX(0), 0.0f);
    }
}
