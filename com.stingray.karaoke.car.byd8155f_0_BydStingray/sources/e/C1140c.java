package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1140c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final H f13653a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f13654b = new ArrayList();

    public C1140c() {
        HandlerThread handlerThread = new HandlerThread("TestChargingManagerImplCallback");
        handlerThread.start();
        this.f13653a = new H(this, handlerThread.getLooper(), 3);
    }

    public final void a(int i7, int i10) {
        H h10 = this.f13653a;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            h10.sendMessage(messageObtain);
        }
    }

    public final void b(int i7, Number number) {
        H h10 = this.f13653a;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.obj = number;
            h10.sendMessage(messageObtain);
        }
    }
}
