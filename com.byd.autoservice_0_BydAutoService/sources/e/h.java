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
    public final List<AbsBYDAutoFragranceListener> f2764a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f2765b;

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
        this.f2765b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f2764a.isEmpty()) {
            return;
        }
        int i3 = message.what;
        int i4 = message.arg1;
        int i5 = message.arg2;
        if (i3 == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.f2764a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i4);
            }
        } else {
            if (i3 != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.f2764a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i4, i5);
            }
        }
    }

    public void b(int i3, int i4) {
        if (this.f2765b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i3;
            messageObtain.arg1 = i4;
            messageObtain.arg2 = 0;
            this.f2765b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.f2764a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.f2764a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.f2764a.add(absBYDAutoFragranceListener);
    }
}
