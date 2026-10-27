package a.b.f;

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
    public final List<AbsBYDAutoPM2p5Listener> f43a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f44b;

    /* JADX INFO: compiled from: TestPm2p5ManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
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
        this.f44b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f43a.isEmpty()) {
        }
        int i2 = message.what;
        int i3 = message.arg1;
        int i4 = message.arg2;
        switch (i2) {
            case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.f43a.iterator();
                while (it.hasNext()) {
                    it.next().onRapidPurificationPM2p5StateChanged(i3);
                }
                break;
            case 5001:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.f43a.iterator();
                while (it2.hasNext()) {
                    it2.next().onNegativeIonStateChange(i3);
                }
                break;
            case 5002:
                Iterator<AbsBYDAutoPM2p5Listener> it3 = this.f43a.iterator();
                while (it3.hasNext()) {
                    it3.next().onPM2p5CheckStateChanged(i3, i4);
                }
                break;
            case 5003:
                Iterator<AbsBYDAutoPM2p5Listener> it4 = this.f43a.iterator();
                while (it4.hasNext()) {
                    it4.next().onPM2p5DualChannelDetectChanged(i3);
                }
                break;
            case 5004:
                Iterator<AbsBYDAutoPM2p5Listener> it5 = this.f43a.iterator();
                while (it5.hasNext()) {
                    it5.next().onPM2p5AnionDetectChanged(i3);
                }
                break;
        }
    }

    public void b(int i2, int i3) {
        if (this.f44b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = 0;
            this.f44b.sendMessage(messageObtain);
        }
    }

    public void c(int i2, int i3, int i4) {
        if (this.f44b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = i4;
            this.f44b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener != null) {
            this.f43a.remove(absBYDAutoPM2p5Listener);
        }
    }

    public void e(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener == null || this.f43a.contains(absBYDAutoPM2p5Listener)) {
            return;
        }
        this.f43a.add(absBYDAutoPM2p5Listener);
    }
}
