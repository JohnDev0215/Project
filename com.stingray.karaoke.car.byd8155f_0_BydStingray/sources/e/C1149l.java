package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.l, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1149l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13670a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13671b;

    public C1149l() {
        HandlerThread handlerThread = new HandlerThread("TestPm2p5ManagerImplCallback");
        handlerThread.start();
        this.f13671b = new H(this, handlerThread.getLooper(), 12);
    }

    public final void a(int i7, int i10) {
        H h10 = this.f13671b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            messageObtain.arg2 = 0;
            h10.sendMessage(messageObtain);
        }
    }

    public final void b(int i7, int i10) {
        H h10 = this.f13671b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 5002;
            messageObtain.arg1 = i7;
            messageObtain.arg2 = i10;
            h10.sendMessage(messageObtain);
        }
    }
}
