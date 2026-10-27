package b;

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
    private final List<AbsBYDAutoPM2p5Listener> f3456a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3457b;

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
        this.f3457b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3456a.isEmpty()) {
        }
        int i7 = message.what;
        int i8 = message.arg1;
        int i9 = message.arg2;
        switch (i7) {
            case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.f3456a.iterator();
                while (it.hasNext()) {
                    it.next().onRapidPurificationPM2p5StateChanged(i8);
                }
                break;
            case 5001:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.f3456a.iterator();
                while (it2.hasNext()) {
                    it2.next().onNegativeIonStateChange(i8);
                }
                break;
            case 5002:
                Iterator<AbsBYDAutoPM2p5Listener> it3 = this.f3456a.iterator();
                while (it3.hasNext()) {
                    it3.next().onPM2p5CheckStateChanged(i8, i9);
                }
                break;
            case 5003:
                Iterator<AbsBYDAutoPM2p5Listener> it4 = this.f3456a.iterator();
                while (it4.hasNext()) {
                    it4.next().onPM2p5DualChannelDetectChanged(i8);
                }
                break;
            case 5004:
                Iterator<AbsBYDAutoPM2p5Listener> it5 = this.f3456a.iterator();
                while (it5.hasNext()) {
                    it5.next().onPM2p5AnionDetectChanged(i8);
                }
                break;
        }
    }

    public void b(int i7, int i8) {
        if (this.f3457b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3457b.sendMessage(messageObtain);
        }
    }

    public void c(int i7, int i8, int i9) {
        if (this.f3457b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = i9;
            this.f3457b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener != null) {
            this.f3456a.remove(absBYDAutoPM2p5Listener);
        }
    }

    public void e(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener == null || this.f3456a.contains(absBYDAutoPM2p5Listener)) {
            return;
        }
        this.f3456a.add(absBYDAutoPM2p5Listener);
    }
}
