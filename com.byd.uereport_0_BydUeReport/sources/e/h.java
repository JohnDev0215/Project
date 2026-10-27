package e;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: TestFragranceManagerImplCallback.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final List<AbsBYDAutoFragranceListener> f4492a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f4493b;

    /* JADX INFO: compiled from: TestFragranceManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            h.this.a(message);
        }
    }

    public h() {
        HandlerThread handlerThread = new HandlerThread("TestFragranceManagerImplCallback");
        handlerThread.start();
        this.f4493b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f4492a.isEmpty()) {
            return;
        }
        int i8 = message.what;
        int i9 = message.arg1;
        int i10 = message.arg2;
        if (i8 == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.f4492a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i9);
            }
        } else {
            if (i8 != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.f4492a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i9, i10);
            }
        }
    }

    public void b(int i8, int i9) {
        if (this.f4493b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i8;
            messageObtain.arg1 = i9;
            messageObtain.arg2 = 0;
            this.f4493b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.f4492a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.f4492a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.f4492a.add(absBYDAutoFragranceListener);
    }
}
