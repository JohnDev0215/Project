package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1139b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13651a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13652b;

    public C1139b() {
        HandlerThread handlerThread = new HandlerThread("TestBodyworkManagerImplCallback");
        handlerThread.start();
        this.f13652b = new H(this, handlerThread.getLooper(), 2);
    }

    public final void a(int i7) {
        H h10 = this.f13652b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 4001;
            messageObtain.arg1 = i7;
            messageObtain.arg2 = 0;
            h10.sendMessage(messageObtain);
        }
    }

    public final void b(int i7, int i10) {
        H h10 = this.f13652b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 4000;
            messageObtain.arg1 = i7;
            messageObtain.arg2 = i10;
            h10.sendMessage(messageObtain);
        }
    }
}
