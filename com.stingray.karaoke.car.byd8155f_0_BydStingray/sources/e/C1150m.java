package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.m, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1150m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13672a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13673b;

    public C1150m() {
        HandlerThread handlerThread = new HandlerThread("TestSeatManagerImplCallback");
        handlerThread.start();
        this.f13673b = new H(this, handlerThread.getLooper(), 13);
    }

    public final void a(int i7) {
        H h10 = this.f13673b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = 3003;
            messageObtain.arg1 = i7;
            messageObtain.arg2 = 0;
            h10.sendMessage(messageObtain);
        }
    }

    public final void b(int i7, int i10, int i11) {
        H h10 = this.f13673b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            messageObtain.arg2 = i11;
            h10.sendMessage(messageObtain);
        }
    }
}
