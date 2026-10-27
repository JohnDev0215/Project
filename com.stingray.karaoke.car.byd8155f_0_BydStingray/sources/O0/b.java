package O0;

import android.graphics.RectF;
import android.text.Layout;
import android.text.SegmentFinder;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f5010a = new b();

    /* JADX WARN: Type inference failed for: r6v2, types: [O0.a] */
    public final int[] a(x xVar, RectF rectF, int i7, final InterfaceC2785n interfaceC2785n) {
        SegmentFinder segmentFinderH;
        if (i7 == 1) {
            segmentFinderH = P0.b.f5265a.a(new io.sentry.internal.debugmeta.c(xVar.f5050f.getText(), 9, xVar.j()));
        } else {
            M.p.n();
            segmentFinderH = M.p.h(M.p.g(xVar.f5050f.getText(), xVar.f5047a));
        }
        return xVar.f5050f.getRangeForRect(rectF, segmentFinderH, new Layout.TextInclusionStrategy() { // from class: O0.a
            @Override // android.text.Layout.TextInclusionStrategy
            public final boolean isSegmentInside(RectF rectF2, RectF rectF3) {
                return ((Boolean) interfaceC2785n.invoke(rectF2, rectF3)).booleanValue();
            }
        });
    }
}
