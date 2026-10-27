package spi;

import android.car.utils.Logs;
import android.widget.CompoundButton;

/* JADX INFO: compiled from: BydTrafficNoticeDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class x0 implements CompoundButton.OnCheckedChangeListener {
    public x0(z0 z0Var) {
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Logs.d("BydTrafficNoticeDialog", "onCheckedChanged " + z);
    }
}
