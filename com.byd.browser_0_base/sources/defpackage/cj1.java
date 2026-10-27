package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: compiled from: MotionEventSynthesizerImpl.java */
/* JADX INFO: loaded from: classes3.dex */
public final class cj1 {
    public final MotionEvent.PointerProperties[] a = new MotionEvent.PointerProperties[16];
    public final MotionEvent.PointerCoords[] b = new MotionEvent.PointerCoords[16];
    public final View c;
    public long d;

    public cj1(View view) {
        this.c = view;
    }

    public final void a(int i, float f, float f2, int i2) {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = f;
        pointerCoords.y = f2;
        pointerCoords.pressure = 1.0f;
        this.b[i] = pointerCoords;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = i2;
        pointerProperties.toolType = 0;
        this.a[i] = pointerProperties;
    }
}
