package n0;

import android.graphics.PathMeasure;

/* JADX INFO: renamed from: n0.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1881j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PathMeasure f19438a;

    public C1881j(PathMeasure pathMeasure) {
        this.f19438a = pathMeasure;
    }

    public final void a(float f10, float f11, C1880i c1880i) {
        if (c1880i == null) {
            throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
        }
        this.f19438a.getSegment(f10, f11, c1880i.f19436a, true);
    }
}
