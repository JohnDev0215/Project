package P0;

import android.text.SegmentFinder;

/* JADX INFO: loaded from: classes.dex */
public final class a extends SegmentFinder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ c f5264a;

    public a(c cVar) {
        this.f5264a = cVar;
    }

    public final int nextEndBoundary(int i7) {
        return this.f5264a.m(i7);
    }

    public final int nextStartBoundary(int i7) {
        return this.f5264a.y(i7);
    }

    public final int previousEndBoundary(int i7) {
        return this.f5264a.A(i7);
    }

    public final int previousStartBoundary(int i7) {
        return this.f5264a.l(i7);
    }
}
