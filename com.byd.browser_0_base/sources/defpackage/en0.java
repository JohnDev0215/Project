package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.os.UserManager;

/* JADX INFO: compiled from: FirstRunAppRestrictionInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class en0 extends i9<Boolean> {
    public final /* synthetic */ Context h;
    public final /* synthetic */ long i;
    public final /* synthetic */ fn0 j;

    public en0(fn0 fn0Var, Context context, long j) {
        this.j = fn0Var;
        this.h = context;
        this.i = j;
    }

    @Override // defpackage.i9
    public final Boolean b() {
        Bundle bundle;
        Context context = this.h;
        try {
            bundle = ((UserManager) context.getSystemService("user")).getApplicationRestrictions(context.getPackageName());
        } catch (SecurityException unused) {
            bundle = new Bundle();
        }
        return Boolean.valueOf((bundle == null || bundle.isEmpty()) ? false : true);
    }

    @Override // defpackage.i9
    public final void l(Boolean bool) {
        this.j.a(this.i, bool.booleanValue());
    }
}
