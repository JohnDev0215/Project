package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1147j {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13666a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13667b;

    public C1147j() {
        HandlerThread handlerThread = new HandlerThread("TestInstrumentManagerImplCallback");
        handlerThread.start();
        this.f13667b = new H(this, handlerThread.getLooper(), 10);
    }

    public final void a(int i7, int i10) {
        H h10 = this.f13667b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            messageObtain.arg2 = 0;
            h10.sendMessage(messageObtain);
        }
    }
}
