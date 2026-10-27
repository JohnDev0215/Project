package spi;

import android.car.utils.Logs;
import android.os.CountDownTimer;

/* JADX INFO: compiled from: BydTrafficNoticeDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class y0 extends CountDownTimer {
    public final /* synthetic */ z0 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public y0(z0 z0Var, long j, long j2) {
        super(j, j2);
        this.a = z0Var;
    }

    @Override // android.os.CountDownTimer
    public final void onFinish() {
        z0 z0Var = this.a;
        z0Var.f = -1L;
        if (z0Var.a.isChecked()) {
            t9.a().b(1L);
        } else {
            t9.a().b(0L);
        }
        this.a.dismiss();
        Logs.d("BydTrafficNoticeDialog", "onFinish");
    }

    @Override // android.os.CountDownTimer
    public final void onTick(long j) {
        z0 z0Var = this.a;
        z0Var.f = j;
        z0Var.a(j);
        Logs.d("BydTrafficNoticeDialog", "onTick millisUntilFinished " + j);
    }
}
