package a.b.e;

import android.hardware.bydauto.fragrance.AbsBYDAutoFragranceListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<AbsBYDAutoFragranceListener> f185a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Handler f186b;

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
        this.f186b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.f185a.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        if (i == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.f185a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i2);
            }
        } else {
            if (i != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.f185a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i2, i3);
            }
        }
    }

    public void b(int i, int i2) {
        if (this.f186b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.f186b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.f185a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.f185a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.f185a.add(absBYDAutoFragranceListener);
    }
}
