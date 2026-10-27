package t9;

import java.io.Serializable;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class h extends y9.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f22539a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final w9.a f22540b;
    public final Serializable c;

    public h() {
        this.f22539a = 1;
        this.f22540b = new w9.l();
        this.c = new ArrayList();
    }

    @Override // y9.a
    public void a(CharSequence charSequence) {
        switch (this.f22539a) {
            case 1:
                ((ArrayList) this.c).add(charSequence);
                break;
        }
    }

    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
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
    @Override // y9.a
    public void c() {
        /*
            r10 = this;
            int r0 = r10.f22539a
            switch(r0) {
                case 1: goto L6;
                default: goto L5;
            }
        L5:
            return
        L6:
            java.io.Serializable r0 = r10.c
            java.util.ArrayList r0 = (java.util.ArrayList) r0
            int r1 = r0.size()
            r2 = 1
            int r1 = r1 - r2
        L10:
            r3 = 0
            if (r1 < 0) goto L3c
            java.lang.Object r4 = r0.get(r1)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r5 = r4.length()
            r6 = r3
        L1e:
            r7 = -1
            if (r6 >= r5) goto L30
            char r8 = r4.charAt(r6)
            r9 = 32
            if (r8 == r9) goto L2d
            switch(r8) {
                case 9: goto L2d;
                case 10: goto L2d;
                case 11: goto L2d;
                case 12: goto L2d;
                case 13: goto L2d;
                default: goto L2c;
            }
        L2c:
            goto L31
        L2d:
            int r6 = r6 + 1
            goto L1e
        L30:
            r6 = r7
        L31:
            if (r6 != r7) goto L35
            r4 = r2
            goto L36
        L35:
            r4 = r3
        L36:
            if (r4 != 0) goto L39
            goto L3c
        L39:
            int r1 = r1 + (-1)
            goto L10
        L3c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
        L41:
            int r5 = r1 + 1
            if (r3 >= r5) goto L56
            java.lang.Object r5 = r0.get(r3)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r4.append(r5)
            r5 = 10
            r4.append(r5)
            int r3 = r3 + 1
            goto L41
        L56:
            java.lang.String r0 = r4.toString()
            w9.a r10 = r10.f22540b
            w9.l r10 = (w9.l) r10
            r10.f23375g = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: t9.h.c():void");
    }

    @Override // y9.a
    public final w9.a d() {
        switch (this.f22539a) {
            case 0:
                return (w9.h) this.f22540b;
            default:
                return (w9.l) this.f22540b;
        }
    }

    @Override // y9.a
    public void f(j jVar) {
        switch (this.f22539a) {
            case 0:
                jVar.f((String) this.c, (w9.h) this.f22540b);
                break;
        }
    }

    @Override // y9.a
    public final P2.g g(f fVar) {
        switch (this.f22539a) {
            case 0:
                return null;
            default:
                if (fVar.f22530g >= 4) {
                    return new P2.g(-1, fVar.c + 4, false);
                }
                if (fVar.f22531h) {
                    return P2.g.a(fVar.f22528e);
                }
                return null;
        }
    }

    public h(int i7, String str) {
        this.f22539a = 0;
        w9.h hVar = new w9.h();
        this.f22540b = hVar;
        hVar.f23371g = i7;
        this.c = str;
    }
}
