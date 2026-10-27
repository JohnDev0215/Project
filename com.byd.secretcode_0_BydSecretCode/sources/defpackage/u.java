package defpackage;

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
public class u {
    public final List<AbsBYDAutoGearboxListener> a = new ArrayList();
    public Handler b;

    /* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            u.this.a(message);
        }
    }

    public u() {
        HandlerThread handlerThread = new HandlerThread("TestGearboxManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.a.isEmpty()) {
            return;
        }
        int i = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i);
            absBYDAutoGearboxListener.onGearboxStateChanged(i);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i);
            absBYDAutoGearboxListener.onEPBStateChanged(i);
            absBYDAutoGearboxListener.onCurrentGearChanged(i);
            absBYDAutoGearboxListener.onError(i, i + "");
            absBYDAutoGearboxListener.onDataEventChanged(i, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.a.add(absBYDAutoGearboxListener);
    }
}
