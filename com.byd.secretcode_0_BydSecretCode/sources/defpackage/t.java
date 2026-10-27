package defpackage;

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
public class t {
    public final List<AbsBYDAutoFragranceListener> a = new ArrayList();
    public Handler b;

    /* JADX INFO: compiled from: TestFragranceManagerImplCallback.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            t.this.a(message);
        }
    }

    public t() {
        HandlerThread handlerThread = new HandlerThread("TestFragranceManagerImplCallback");
        handlerThread.start();
        this.b = new a(handlerThread.getLooper());
    }

    public void a(Message message) {
        if (this.a.isEmpty()) {
            return;
        }
        int i = message.what;
        int i2 = message.arg1;
        int i3 = message.arg2;
        if (i == 2000) {
            Iterator<AbsBYDAutoFragranceListener> it = this.a.iterator();
            while (it.hasNext()) {
                it.next().onFragranceTypeChanged(i2);
            }
        } else {
            if (i != 2001) {
                return;
            }
            Iterator<AbsBYDAutoFragranceListener> it2 = this.a.iterator();
            while (it2.hasNext()) {
                it2.next().onFragranceConcentrationChanged(i2, i3);
            }
        }
    }

    public void b(int i, int i2) {
        if (this.b != null) {
            Message messageObtain = Message.obtain();
            messageObtain.what = i;
            messageObtain.arg1 = i2;
            messageObtain.arg2 = 0;
            this.b.sendMessage(messageObtain);
        }
    }

    public void c(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener != null) {
            this.a.remove(absBYDAutoFragranceListener);
        }
    }

    public void d(AbsBYDAutoFragranceListener absBYDAutoFragranceListener) {
        if (absBYDAutoFragranceListener == null || this.a.contains(absBYDAutoFragranceListener)) {
            return;
        }
        this.a.add(absBYDAutoFragranceListener);
    }
}
