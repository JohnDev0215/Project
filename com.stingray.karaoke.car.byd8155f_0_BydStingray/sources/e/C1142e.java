package e;

import B5.H;
import android.hardware.bydauto.doorlock.AbsBYDAutoDoorLockListener;
import android.os.Message;

/* JADX INFO: renamed from: e.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1142e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoDoorLockListener f13657a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public H f13658b;

    public final void a(int i7, int i10) {
        H h10 = this.f13658b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 32000;
            messageObtain.arg1 = i7;
            messageObtain.arg2 = i10;
            h10.sendMessage(messageObtain);
        }
    }
}
