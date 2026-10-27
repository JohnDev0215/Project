package com.byd.filemanager.ui.d;

import android.view.MotionEvent;
import android.view.View;
import com.byd.filemanager.ui.d.h;

/* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
/* JADX INFO: loaded from: classes.dex */
public class k extends h {

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    protected static class a extends h.a {
        public a() {
            this.f3792a = View.TRANSLATION_Y;
        }

        @Override // com.byd.filemanager.ui.d.h.a
        protected void a(View view) {
            this.f3793b = view.getTranslationY();
            this.f3794c = view.getHeight();
        }
    }

    /* JADX INFO: compiled from: VerticalOverScrollBounceEffectDecorator.java */
    protected static class b extends h.e {
        protected b() {
        }

        @Override // com.byd.filemanager.ui.d.h.e
        public boolean a(View view, MotionEvent motionEvent) {
            if (motionEvent.getHistorySize() == 0) {
                return false;
            }
            float y = motionEvent.getY(0) - motionEvent.getHistoricalY(0, 0);
            if (Math.abs(motionEvent.getX(0) - motionEvent.getHistoricalX(0, 0)) > Math.abs(y)) {
                return false;
            }
            this.f3802a = view.getTranslationY();
            this.f3803b = y;
            this.f3804c = y > 0.0f;
            return true;
        }
    }

    public k(c cVar) {
        this(cVar, 3.0f, 1.0f, -2.0f);
    }

    @Override // com.byd.filemanager.ui.d.h
    protected h.a b() {
        return new a();
    }

    @Override // com.byd.filemanager.ui.d.h
    protected h.e c() {
        return new b();
    }

    @Override // com.byd.filemanager.ui.d.h
    protected void f(View view, float f) {
        view.setTranslationY(f);
    }

    @Override // com.byd.filemanager.ui.d.h
    protected void g(View view, float f, MotionEvent motionEvent) {
        view.setTranslationY(f);
        motionEvent.offsetLocation(f - motionEvent.getY(0), 0.0f);
    }

    public k(c cVar, float f, float f2, float f3) {
        super(cVar, f3, f, f2);
    }
}
