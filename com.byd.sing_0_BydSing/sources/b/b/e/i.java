package b.b.e;

import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.android.volley.BuildConfig;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: TestGearboxManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoGearboxListener> f1947a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f1948b;

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
        this.f1948b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f1947a.isEmpty()) {
            return;
        }
        int i = message.arg1;
        for (AbsBYDAutoGearboxListener absBYDAutoGearboxListener : this.f1947a) {
            absBYDAutoGearboxListener.onGearboxAutoModeTypeChanged(i);
            absBYDAutoGearboxListener.onGearboxManualModeLevelChanged(i);
            absBYDAutoGearboxListener.onGearboxStateChanged(i);
            absBYDAutoGearboxListener.onBrakeFluidLevelChanged(i);
            absBYDAutoGearboxListener.onParkBrakeSwitchChanged(i);
            absBYDAutoGearboxListener.onBrakePedalStateChanged(i);
            absBYDAutoGearboxListener.onEPBStateChanged(i);
            absBYDAutoGearboxListener.onCurrentGearChanged(i);
            absBYDAutoGearboxListener.onError(i, i + BuildConfig.FLAVOR);
            absBYDAutoGearboxListener.onDataEventChanged(i, new BYDAutoEventValue());
        }
    }

    public void b(AbsBYDAutoGearboxListener absBYDAutoGearboxListener) {
        if (absBYDAutoGearboxListener == null || this.f1947a.contains(absBYDAutoGearboxListener)) {
            return;
        }
        this.f1947a.add(absBYDAutoGearboxListener);
    }
}
