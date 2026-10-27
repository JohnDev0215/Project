package a.a.e;

import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.pm2p5.AbsBYDAutoPM2p5Listener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestPm2p5ManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoPM2p5Listener> f30a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f31b;

    /* JADX INFO: compiled from: TestPm2p5ManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            l.this.a(message);
        }
    }

    public l() {
        HandlerThread handlerThread = new HandlerThread("TestPm2p5ManagerImplCallback");
        handlerThread.start();
        this.f31b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f30a.isEmpty()) {
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        switch (i) {
            case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.f30a.iterator();
                while (it.hasNext()) {
                    it.next().onRapidPurificationPM2p5StateChanged(i2);
                }
                break;
            case 5001:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.f30a.iterator();
                while (it2.hasNext()) {
                    it2.next().onNegativeIonStateChange(i2);
                }
                break;
            case 5002:
                Iterator<AbsBYDAutoPM2p5Listener> it3 = this.f30a.iterator();
                while (it3.hasNext()) {
                    it3.next().onPM2p5CheckStateChanged(i2, i3);
                }
                break;
            case 5003:
                Iterator<AbsBYDAutoPM2p5Listener> it4 = this.f30a.iterator();
                while (it4.hasNext()) {
                    it4.next().onPM2p5DualChannelDetectChanged(i2);
                }
                break;
            case 5004:
                Iterator<AbsBYDAutoPM2p5Listener> it5 = this.f30a.iterator();
                while (it5.hasNext()) {
                    it5.next().onPM2p5AnionDetectChanged(i2);
                }
                break;
        }
    }

    public void b(int i, int i2) {
        if (this.f31b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f31b.sendMessage(messageObtain);
        }
    }

    public void c(int i, int i2, int i3) {
        if (this.f31b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = i3;
            this.f31b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener != null) {
            this.f30a.remove(absBYDAutoPM2p5Listener);
        }
    }

    public void e(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener == null || this.f30a.contains(absBYDAutoPM2p5Listener)) {
            return;
        }
        this.f30a.add(absBYDAutoPM2p5Listener);
    }
}
