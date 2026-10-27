package e;

import B5.H;
import android.os.Message;

/* JADX INFO: renamed from: e.n, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1151n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public H f13674a;

    public final void a(int i7) {
        H h10 = this.f13674a;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 22000;
            messageObtain.arg1 = i7;
            h10.sendMessage(messageObtain);
        }
    }
}
