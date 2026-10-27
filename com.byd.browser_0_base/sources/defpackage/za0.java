package defpackage;

import android.graphics.Point;
import android.view.Display;

/* JADX INFO: compiled from: DisplayCompat.java */
/* JADX INFO: loaded from: classes3.dex */
public final class za0 {
    public final Point a;
    public final boolean b;

    public za0(Point point) {
        this.b = true;
        this.a = point;
    }

    public za0(Display.Mode mode, boolean z) {
        if (mode != null) {
            this.b = z;
            this.a = new Point(mode.getPhysicalWidth(), mode.getPhysicalHeight());
            return;
        }
        throw new NullPointerException("Display.Mode == null, can't wrap a null reference");
    }
}
