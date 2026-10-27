package a.b.f;

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
    public final List<AbsBYDAutoGearboxListener> f34a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f35b;

    /* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f35b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f34a.isEmpty()) {
            return;
        }
        int i2 = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f34a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i2);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i2);
            absBYDAutoGearboxListener.onGearboxStateChanged(i2);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i2);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i2);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i2);
            absBYDAutoGearboxListener.onEPBStateChanged(i2);
            absBYDAutoGearboxListener.onCurrentGearChanged(i2);
            absBYDAutoGearboxListener.onError(i2, i2 + "");
            absBYDAutoGearboxListener.onDataEventChanged(i2, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f34a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f34a.add(absBYDAutoGearboxListener);
    }
}
