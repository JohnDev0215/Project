package p;

import android.content.Context;
import android.content.IntentFilter;

/* JADX INFO: loaded from: classes.dex */
public final class pl extends nn {
    public final /* synthetic */ int g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public pl(Context context, yx2 yx2Var, int i) {
        super(context, yx2Var);
        this.g = i;
        if (i == 1) {
            v03.j(yx2Var, "taskExecutor");
            super(context, yx2Var);
        } else if (i != 2) {
            v03.j(yx2Var, "taskExecutor");
        } else {
            v03.j(yx2Var, "taskExecutor");
            super(context, yx2Var);
        }
    }

    @Override // p.qy
    public final /* bridge */ /* synthetic */ Object a() {
        switch (this.g) {
        }
        return f();
    }

    @Override // p.nn
    public final IntentFilter e() {
        switch (this.g) {
            case 0:
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.os.action.CHARGING");
                intentFilter.addAction("android.os.action.DISCHARGING");
                return intentFilter;
            case 1:
                IntentFilter intentFilter2 = new IntentFilter();
                intentFilter2.addAction("android.intent.action.BATTERY_OKAY");
                intentFilter2.addAction("android.intent.action.BATTERY_LOW");
                return intentFilter2;
            default:
                IntentFilter intentFilter3 = new IntentFilter();
                intentFilter3.addAction("android.intent.action.DEVICE_STORAGE_OK");
                intentFilter3.addAction("android.intent.action.DEVICE_STORAGE_LOW");
                return intentFilter3;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Boolean f() {
        /*
            r9 = this;
            int r0 = r9.g
            r1 = -1
            java.lang.String r2 = "status"
            java.lang.String r3 = "getInitialState - null intent received"
            java.lang.String r4 = "android.intent.action.BATTERY_CHANGED"
            r5 = 0
            r6 = 1
            r7 = 0
            android.content.Context r8 = r9.b
            switch(r0) {
                case 0: goto L86;
                case 1: goto L4d;
                default: goto L11;
            }
        L11:
            android.content.IntentFilter r0 = r9.e()
            android.content.Intent r0 = r8.registerReceiver(r7, r0)
            if (r0 == 0) goto L40
            java.lang.String r1 = r0.getAction()
            if (r1 != 0) goto L22
            goto L40
        L22:
            java.lang.String r0 = r0.getAction()
            if (r0 == 0) goto L48
            int r1 = r0.hashCode()
            r2 = -1181163412(0xffffffffb998e06c, float:-2.9158907E-4)
            if (r1 == r2) goto L42
            r2 = -730838620(0xffffffffd47049a4, float:-4.1281105E12)
            if (r1 == r2) goto L37
            goto L48
        L37:
            java.lang.String r1 = "android.intent.action.DEVICE_STORAGE_OK"
            boolean r0 = r0.equals(r1)
            if (r0 != 0) goto L40
            goto L48
        L40:
            r5 = r6
            goto L48
        L42:
            java.lang.String r1 = "android.intent.action.DEVICE_STORAGE_LOW"
            boolean r0 = r0.equals(r1)
        L48:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r5)
            return r0
        L4d:
            android.content.IntentFilter r0 = new android.content.IntentFilter
            r0.<init>(r4)
            android.content.Intent r0 = r8.registerReceiver(r7, r0)
            if (r0 != 0) goto L64
            p.vc1 r0 = p.vc1.c()
            java.lang.String r1 = p.rl.a
            r0.a(r1, r3)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            goto L85
        L64:
            int r2 = r0.getIntExtra(r2, r1)
            java.lang.String r3 = "level"
            int r3 = r0.getIntExtra(r3, r1)
            java.lang.String r4 = "scale"
            int r0 = r0.getIntExtra(r4, r1)
            float r1 = (float) r3
            float r0 = (float) r0
            float r1 = r1 / r0
            if (r2 == r6) goto L80
            r0 = 1041865114(0x3e19999a, float:0.15)
            int r0 = (r1 > r0 ? 1 : (r1 == r0 ? 0 : -1))
            if (r0 <= 0) goto L81
        L80:
            r5 = r6
        L81:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r5)
        L85:
            return r0
        L86:
            android.content.IntentFilter r0 = new android.content.IntentFilter
            r0.<init>(r4)
            android.content.Intent r0 = r8.registerReceiver(r7, r0)
            if (r0 != 0) goto L9d
            p.vc1 r0 = p.vc1.c()
            java.lang.String r1 = p.ql.a
            r0.a(r1, r3)
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            goto Lac
        L9d:
            int r0 = r0.getIntExtra(r2, r1)
            r1 = 2
            if (r0 == r1) goto La7
            r1 = 5
            if (r0 != r1) goto La8
        La7:
            r5 = r6
        La8:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r5)
        Lac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: p.pl.f():java.lang.Boolean");
    }
}
