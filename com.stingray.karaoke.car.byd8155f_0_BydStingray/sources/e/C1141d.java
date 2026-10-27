package e;

import B5.H;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import android.os.Message;

/* JADX INFO: renamed from: e.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1141d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AbsBYDAutoDiPilotListener f13655a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public H f13656b;

    public final void a(int i7, int i10) {
        H h10 = this.f13656b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            h10.sendMessage(messageObtain);
        }
    }
}
