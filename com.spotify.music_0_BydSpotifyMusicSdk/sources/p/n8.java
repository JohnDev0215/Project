package p;

import android.content.Context;
import android.content.IntentFilter;
import android.os.PowerManager;

/* JADX INFO: loaded from: classes.dex */
public final class n8 extends p8 {
    public final /* synthetic */ int c = 0;
    public final /* synthetic */ s8 d;
    public final Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n8(s8 s8Var, v4 v4Var) {
        super(s8Var);
        this.d = s8Var;
        this.e = v4Var;
    }

    @Override // p.p8
    public final IntentFilter c() {
        switch (this.c) {
            case 0:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.POWER_SAVE_MODE_CHANGED");
                return intentFilter;
            default:
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.TIME_SET");
                intentFilter2.addAction("android.intent.action.TIMEZONE_CHANGED");
                intentFilter2.addAction("android.intent.action.TIME_TICK");
                return intentFilter2;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x00d1, code lost:
    
        if (r1 != false) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00e3, code lost:
    
        if (r1 < 22) goto L50;
     */
    @Override // p.p8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int d() {
        /*
            Method dump skipped, instruction units count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.n8.d():int");
    }

    @Override // p.p8
    public final void f() {
        int i = this.c;
        s8 s8Var = this.d;
        switch (i) {
            case 0:
                s8Var.l(true, true);
                break;
            default:
                s8Var.l(true, true);
                break;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public n8(s8 s8Var, Context context) {
        super(s8Var);
        this.d = s8Var;
        this.e = (PowerManager) context.getApplicationContext().getSystemService("power");
    }
}
