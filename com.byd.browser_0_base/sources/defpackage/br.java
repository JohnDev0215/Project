package defpackage;

import android.content.Context;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import org.chromium.chrome.browser.base.SplitCompatService;

/* JADX INFO: compiled from: ChromePrerenderServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class br extends SplitCompatService.a {
    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        return new Messenger(new a(xz.a)).getBinder();
    }

    /* JADX INFO: compiled from: ChromePrerenderServiceImpl.java */
    public static class a extends hl3 {
        public a(Context context) {
            super(context, zi0.b);
        }

        @Override // android.os.Handler
        public final void handleMessage(Message message) {
        }
    }
}
