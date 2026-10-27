package e;

import B5.H;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;

/* JADX INFO: renamed from: e.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1144g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f13661a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final H f13662b;

    public C1144g() {
        HandlerThread handlerThread = new HandlerThread("TestEngineManagerImplCallback");
        handlerThread.start();
        this.f13662b = new H(this, handlerThread.getLooper(), 7);
    }

    public final void a(int i7) {
        H h10 = this.f13662b;
        if (h10 != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
            messageObtain.arg1 = i7;
            h10.sendEmptyMessage(BYDAutoEngineDevice.ENGINE_SPEED_MAX);
        }
    }
}
