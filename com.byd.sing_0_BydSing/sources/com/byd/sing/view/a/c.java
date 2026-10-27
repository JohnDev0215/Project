package com.byd.sing.view.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: compiled from: AutoCloseFrame.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class c extends f {
    private final Handler p;

    /* JADX INFO: compiled from: AutoCloseFrame.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 0) {
                c.this.F();
            }
        }
    }

    public c() {
        this.p = new a(Looper.getMainLooper());
    }

    @Override // com.byd.sing.view.a.f
    protected void A(int i, int i2) {
        super.A(i, i2);
        if (H()) {
            this.p.sendEmptyMessageDelayed(0, 5000L);
        }
    }

    @Override // com.byd.sing.view.a.f
    public void B(int i, int i2, int i3) {
        super.B(i, i2, i3);
        if (H()) {
            this.p.sendEmptyMessageDelayed(0, 5000L);
        }
    }

    public void F() {
        com.byd.sing.a.a("autoDismiss");
        a();
    }

    protected void G() {
        this.p.removeMessages(0);
    }

    public abstract boolean H();

    public void I() {
        if (H()) {
            this.p.removeMessages(0);
            this.p.sendEmptyMessageDelayed(0, 5000L);
        }
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        G();
        super.a();
    }

    public c(int i) {
        super(i);
        this.p = new a(Looper.getMainLooper());
    }
}
