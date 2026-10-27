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
    public final List<AbsBYDAutoGearboxListener> f2767a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2768b;

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
        this.f2768b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2767a.isEmpty()) {
            return;
        }
        int i3 = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f2767a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i3);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i3);
            absBYDAutoGearboxListener.onGearboxStateChanged(i3);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i3);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i3);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i3);
            absBYDAutoGearboxListener.onEPBStateChanged(i3);
            absBYDAutoGearboxListener.onCurrentGearChanged(i3);
            absBYDAutoGearboxListener.onError(i3, i3 + "");
            absBYDAutoGearboxListener.onDataEventChanged(i3, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f2767a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f2767a.add(absBYDAutoGearboxListener);
    }
}
