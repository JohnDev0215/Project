package b.b.g.k;

import android.hardware.bydauto.ota.AbsBYDAutoOtaListener;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: BydOtaListener.java */
/* JADX INFO: loaded from: classes2.dex */
public class m extends AbsBYDAutoOtaListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f860b = Integer.MIN_VALUE;

    @Override // android.hardware.bydauto.ota.AbsBYDAutoOtaListener
    public void onSocValueChanged(int i) {
        if (this.f860b == i) {
            return;
        }
        this.f860b = i;
        super.onSocValueChanged(i);
        b.b.g.r.e.h(280000);
        EventBus.getDefault().post(new b.b.a.c.a(280000, i));
    }
}
