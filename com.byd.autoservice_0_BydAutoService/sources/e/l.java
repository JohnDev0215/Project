package e;

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
    public final List<AbsBYDAutoPM2p5Listener> f2776a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2777b;

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
        this.f2777b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2776a.isEmpty()) {
        }
        int i3 = message.what;
        int i4 = message.arg1;
        int i5 = message.arg2;
        switch (i3) {
            case TsNormalNum.THOUSAND_FIVE /* 5000 */:
                Iterator<AbsBYDAutoPM2p5Listener> it = this.f2776a.iterator();
                while (it.hasNext()) {
                    it.next().onRapidPurificationPM2p5StateChanged(i4);
                }
                break;
            case 5001:
                Iterator<AbsBYDAutoPM2p5Listener> it2 = this.f2776a.iterator();
                while (it2.hasNext()) {
                    it2.next().onNegativeIonStateChange(i4);
                }
                break;
            case 5002:
                Iterator<AbsBYDAutoPM2p5Listener> it3 = this.f2776a.iterator();
                while (it3.hasNext()) {
                    it3.next().onPM2p5CheckStateChanged(i4, i5);
                }
                break;
            case 5003:
                Iterator<AbsBYDAutoPM2p5Listener> it4 = this.f2776a.iterator();
                while (it4.hasNext()) {
                    it4.next().onPM2p5DualChannelDetectChanged(i4);
                }
                break;
            case 5004:
                Iterator<AbsBYDAutoPM2p5Listener> it5 = this.f2776a.iterator();
                while (it5.hasNext()) {
                    it5.next().onPM2p5AnionDetectChanged(i4);
                }
                break;
        }
    }

    public void b(int i3, int i4) {
        if (this.f2777b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2777b.sendMessage(messageObtain);
        }
    }

    public void c(int i3, int i4, int i5) {
        if (this.f2777b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = i5;
            this.f2777b.sendMessage(messageObtain);
        }
    }

    public void d(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener != null) {
            this.f2776a.remove(absBYDAutoPM2p5Listener);
        }
    }

    public void e(AbsBYDAutoPM2p5Listener absBYDAutoPM2p5Listener) {
        if (absBYDAutoPM2p5Listener == null || this.f2776a.contains(absBYDAutoPM2p5Listener)) {
            return;
        }
        this.f2776a.add(absBYDAutoPM2p5Listener);
    }
}
