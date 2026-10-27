package p;

import android.content.Context;
import java.util.concurrent.Callable;

/* JADX INFO: loaded from: classes.dex */
public final class tv0 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ String e;
    public final /* synthetic */ Context k;
    public final /* synthetic */ pv0 l;
    public final /* synthetic */ int m;

    public /* synthetic */ tv0(String str, Context context, pv0 pv0Var, int i, int i2) {
        this.c = i2;
        this.e = str;
        this.k = context;
        this.l = pv0Var;
        this.m = i;
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @Override // java.util.concurrent.Callable
    public final java.lang.Object call() {
        /*
            r6 = this;
            r0 = -3
            java.lang.String r1 = r6.e
            android.content.Context r2 = r6.k
            p.pv0 r3 = r6.l
            int r4 = r6.m
            int r5 = r6.c
            switch(r5) {
                case 0: goto L22;
                default: goto Le;
            }
        Le:
            switch(r5) {
                case 0: goto L1d;
                default: goto L11;
            }
        L11:
            p.vv0 r0 = p.wv0.a(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L16
            goto L21
        L16:
            p.vv0 r1 = new p.vv0
            r1.<init>(r0)
            r0 = r1
            goto L21
        L1d:
            p.vv0 r0 = p.wv0.a(r1, r2, r3, r4)
        L21:
            return r0
        L22:
            switch(r5) {
                case 0: goto L31;
                default: goto L25;
            }
        L25:
            p.vv0 r0 = p.wv0.a(r1, r2, r3, r4)     // Catch: java.lang.Throwable -> L2a
            goto L35
        L2a:
            p.vv0 r1 = new p.vv0
            r1.<init>(r0)
            r0 = r1
            goto L35
        L31:
            p.vv0 r0 = p.wv0.a(r1, r2, r3, r4)
        L35:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.tv0.call():java.lang.Object");
    }
}
