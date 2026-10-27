package h5;

import android.view.View;
import com.google.android.material.behavior.SwipeDismissBehavior;
import io.sentry.C1489n1;
import l3.P;

/* JADX INFO: loaded from: classes.dex */
public final class t implements Runnable {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f15562m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Object f15563n;

    public /* synthetic */ t(int i7, Object obj, Object obj2, boolean z6) {
        this.l = i7;
        this.f15563n = obj;
        this.f15562m = obj2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:105:0x026e, code lost:
    
        if (r3 == r6) goto L315;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x0271, code lost:
    
        if (r3 == 2) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x0274, code lost:
    
        if (r3 == 3) goto L113;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x0277, code lost:
    
        if (r3 == 4) goto L316;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x0279, code lost:
    
        r3 = r24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:113:0x027c, code lost:
    
        r3 = r24 + 1;
        r2[r24] = (byte) (r23 >> 10);
        r24 = r24 + 2;
        r2[r3] = (byte) (r23 >> 2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x028b, code lost:
    
        r3 = r24 + 1;
        r2[r24] = (byte) (r23 >> 4);
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0293, code lost:
    
        if (r3 != r0) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x0296, code lost:
    
        r0 = new byte[r3];
        java.lang.System.arraycopy(r2, 0, r0, 0, r3);
        r2 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x029d, code lost:
    
        r9 = new f7.b(r2, "message");
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x02a8, code lost:
    
        throw new java.lang.IllegalArgumentException("bad base-64");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:160:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x06c3  */
    /* JADX WARN: Type inference failed for: r12v0, types: [boolean] */
    /* JADX WARN: Type inference failed for: r12v5, types: [int] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instruction units count: 1824
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h5.t.run():void");
    }

    public String toString() {
        switch (this.l) {
            case 0:
                P p10 = new P(t.class.getSimpleName());
                C1489n1 c1489n1 = new C1489n1(15, false);
                ((C1489n1) p10.o).f16625n = c1489n1;
                p10.o = c1489n1;
                c1489n1.f16624m = (s) this.f15563n;
                return p10.toString();
            default:
                return super.toString();
        }
    }

    public /* synthetic */ t(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f15562m = obj;
        this.f15563n = obj2;
    }

    public t(SwipeDismissBehavior swipeDismissBehavior, View view, boolean z6) {
        this.l = 6;
        this.f15563n = swipeDismissBehavior;
        this.f15562m = view;
    }
}
