package defpackage;

import android.view.MotionEvent;
import android.view.View;

/* JADX INFO: renamed from: vz, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1685vz {
    public final MotionEvent.PointerProperties[] a = new MotionEvent.PointerProperties[16];
    public final MotionEvent.PointerCoords[] b = new MotionEvent.PointerCoords[16];
    public final View c;
    public long d;

    public C1685vz(View view) {
        this.c = view;
    }

    public void a(int i, int i2, int i3, int i4) {
        MotionEvent.PointerCoords pointerCoords = new MotionEvent.PointerCoords();
        pointerCoords.x = i2;
        pointerCoords.y = i3;
        pointerCoords.pressure = 1.0f;
        this.b[i] = pointerCoords;
        MotionEvent.PointerProperties pointerProperties = new MotionEvent.PointerProperties();
        pointerProperties.id = i4;
        pointerProperties.toolType = 0;
        this.a[i] = pointerProperties;
    }
}
