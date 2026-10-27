package b;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoGearboxListener> f3447a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3448b;

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
        this.f3448b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3447a.isEmpty()) {
            return;
        }
        int i7 = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f3447a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i7);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i7);
            absBYDAutoGearboxListener.onGearboxStateChanged(i7);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i7);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i7);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i7);
            absBYDAutoGearboxListener.onEPBStateChanged(i7);
            absBYDAutoGearboxListener.onCurrentGearChanged(i7);
            absBYDAutoGearboxListener.onError(i7, i7 + "");
            absBYDAutoGearboxListener.onDataEventChanged(i7, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f3447a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f3447a.add(absBYDAutoGearboxListener);
    }
}
