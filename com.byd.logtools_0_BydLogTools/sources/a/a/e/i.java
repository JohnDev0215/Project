package a.a.e;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoGearboxListener> f21a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f22b;

    /* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            i.this.a(message);
        }
    }

    public i() {
        HandlerThread handlerThread = new HandlerThread("TestGearboxManagerImplCallback");
        handlerThread.start();
        this.f22b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f21a.isEmpty()) {
            return;
        }
        int i = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f21a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i);
            absBYDAutoGearboxListener.onGearboxStateChanged(i);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i);
            absBYDAutoGearboxListener.onEPBStateChanged(i);
            absBYDAutoGearboxListener.onCurrentGearChanged(i);
            absBYDAutoGearboxListener.onError(i, i + HttpUrl.FRAGMENT_ENCODE_SET);
            absBYDAutoGearboxListener.onDataEventChanged(i, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f21a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f21a.add(absBYDAutoGearboxListener);
    }
}
