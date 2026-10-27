package b;

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
    private final List<AbsBYDAutoFragranceListener> f3444a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f3445b;

    /* JADX INFO: compiled from: TestFragranceManagerImplCallback.java */
    class a extends Handler {
        a(Looper looper) {
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
        this.f3445b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f3444a.isEmpty()) {
            return;
        }
        int i7 = message.what;
        int i8 = message.arg1;
        int i9 = message.arg2;
        if (i7 == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.f3444a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i8);
            }
        } else {
            if (i7 != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.f3444a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i8, i9);
            }
        }
    }

    public void b(int i7, int i8) {
        if (this.f3445b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i7;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            this.f3445b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.f3444a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.f3444a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.f3444a.add(absBYDAutoFragranceListener);
    }
}
