package e;

import B5.H;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1145h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13663a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13664b;

    public C1145h() {
        HandlerThread handlerThread = new HandlerThread("TestFragranceManagerImplCallback");
        handlerThread.start();
        this.f13664b = new H(this, handlerThread.getLooper(), 8);
    }

    public final void a(int i7, int i10) {
        H h10 = this.f13664b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i10;
            messageObtain.arg2 = 0;
            h10.sendMessage(messageObtain);
        }
    }
}
