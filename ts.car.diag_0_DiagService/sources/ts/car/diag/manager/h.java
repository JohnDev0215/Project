package ts.car.diag.manager;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.byd.mediacenter.api.media.BuildConfig;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class h {

    /* JADX INFO: compiled from: caradapter-bug */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what == 0) {
                q.b("DIAG-AudioSource", "handleMessage  init SDK");
                h.this.b();
            }
        }
    }

    public h(Context context) {
        new a(Looper.getMainLooper());
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        q.e("DIAG-AudioSource", "init");
    }

    public void c() {
        q.e("DIAG-AudioSource", BuildConfig.BUILD_TYPE);
    }
}
