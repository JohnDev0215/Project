package defpackage;

import android.os.SystemClock;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: BrandingChecker.java */
/* JADX INFO: loaded from: classes.dex */
public final class aj extends i9<Integer> {
    public final String h;
    public final long i;
    public final a j;
    public final Callback<Integer> k;
    public final int l = 2;
    public Boolean m;

    /* JADX INFO: compiled from: BrandingChecker.java */
    public interface a {
    }

    public aj(String str, pl2 pl2Var, wb wbVar, long j) {
        this.h = str;
        this.j = pl2Var;
        this.k = wbVar;
        this.i = j;
    }

    @Override // defpackage.i9
    public final Integer b() {
        Integer numValueOf;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        String str = this.h;
        Boolean boolValueOf = Boolean.valueOf(zt1.e(str));
        this.m = boolValueOf;
        if (boolValueOf.booleanValue()) {
            long j = ((pl2) this.j).a().getLong(pl2.b(str), -1L);
            numValueOf = Integer.valueOf(j == -1 ? 2 : jElapsedRealtime - j >= this.i ? 1 : 0);
        } else {
            numValueOf = null;
        }
        oj2.b0(SystemClock.elapsedRealtime() - jElapsedRealtime, "CustomTabs.Branding.BrandingCheckDuration");
        oj2.O("CustomTabs.Branding.IsPackageNameValid", this.m.booleanValue());
        return numValueOf;
    }

    @Override // defpackage.i9
    public final void j() {
        o(null);
    }

    @Override // defpackage.i9
    public final void l(Integer num) {
        o(num);
    }

    public final void o(Integer num) {
        Boolean bool;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (num == null) {
            num = Integer.valueOf(this.l);
        }
        this.k.onResult(num);
        if (num.intValue() != 0 && (bool = this.m) != null && bool.booleanValue()) {
            String str = this.h;
            pl2 pl2Var = (pl2) this.j;
            pl2Var.getClass();
            PostTask.c(d73.i, new ol2(jElapsedRealtime, pl2Var, 0, str));
        }
        oj2.U(num.intValue(), 3, "CustomTabs.Branding.BrandingDecision");
        oj2.O("CustomTabs.Branding.BrandingCheckCanceled", i());
    }
}
