package defpackage;

import J.N;
import android.os.IBinder;
import android.util.Log;
import java.util.concurrent.FutureTask;
import org.chromium.base.library_loader.a;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.base.SplitCompatService;
import org.chromium.components.browser_ui.photo_picker.g;

/* JADX INFO: compiled from: DecoderServiceImpl.java */
/* JADX INFO: loaded from: classes2.dex */
public class f70 extends SplitCompatService.a {
    public final g b = new g();

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final IBinder a() {
        Log.i("cr_DecoderService", "Decoder process binding");
        return this.b;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatService.a
    public final void b() {
        Log.i("cr_DecoderService", "Decoder service process started");
        if (!(iv.a.get() != null)) {
            iv.h(null);
        }
        d73 d73Var = rh3.a;
        qe3 qe3Var = new qe3(17);
        Object obj = PostTask.a;
        FutureTask futureTask = new FutureTask(qe3Var, null);
        PostTask.d(d73Var, futureTask);
        try {
            futureTask.get();
            a.l.a();
            g gVar = this.b;
            gVar.getClass();
            N.Mw4AD5hY();
            gVar.f = true;
            Log.i("cr_DecoderService", "Decoder service process initialized");
        } catch (Exception e) {
            throw new uv(e);
        }
    }
}
