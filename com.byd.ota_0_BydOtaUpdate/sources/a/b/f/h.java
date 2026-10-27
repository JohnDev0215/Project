package a.b.f;

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
    public final List<AbsBYDAutoFragranceListener> f31a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Handler f32b;

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
        this.f32b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f31a.isEmpty()) {
            return;
        }
        int i2 = message.what;
        int i3 = message.arg1;
        int i4 = message.arg2;
        if (i2 == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.f31a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i3);
            }
        } else {
            if (i2 != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.f31a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i3, i4);
            }
        }
    }

    public void b(int i2, int i3) {
        if (this.f32b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i2;
            messageObtain.arg1 = i3;
            messageObtain.arg2 = 0;
            this.f32b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.f31a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.f31a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.f31a.add(absBYDAutoFragranceListener);
    }
}
