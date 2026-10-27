package e;

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
    public final List<AbsBYDAutoGearboxListener> f4495a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4496b;

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
        this.f4496b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4495a.isEmpty()) {
            return;
        }
        int i8 = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f4495a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i8);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i8);
            absBYDAutoGearboxListener.onGearboxStateChanged(i8);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i8);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i8);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i8);
            absBYDAutoGearboxListener.onEPBStateChanged(i8);
            absBYDAutoGearboxListener.onCurrentGearChanged(i8);
            absBYDAutoGearboxListener.onError(i8, i8 + "");
            absBYDAutoGearboxListener.onDataEventChanged(i8, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f4495a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f4495a.add(absBYDAutoGearboxListener);
    }
}
