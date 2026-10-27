package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: LoadProgressSimulator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h91 {
    public final PropertyModel a;
    public final a b = new a(Looper.getMainLooper());
    public float c;

    /* JADX INFO: compiled from: LoadProgressSimulator.java */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            h91 h91Var = h91.this;
            float f = h91Var.c + 0.1f;
            h91Var.c = f;
            h91Var.c = Math.min(1.0f, f);
            h91Var.a.m(g91.b, h91Var.c);
            if (a8.h(h91Var.c, 1.0f)) {
                h91Var.a.n(g91.a, 1);
            } else {
                sendEmptyMessageDelayed(1, 10L);
            }
        }
    }

    public h91(PropertyModel propertyModel) {
        this.a = propertyModel;
    }
}
