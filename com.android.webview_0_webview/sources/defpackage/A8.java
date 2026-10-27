package defpackage;

import android.content.Context;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class A8 implements Runnable {
    public final Context s;
    public final Callback t;

    public A8(Context context, Callback callback) {
        this.s = context;
        this.t = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context = this.s;
        final Callback callback = this.t;
        AbstractC0178Jq abstractC0178JqA = AbstractC0178Jq.a();
        context.getApplicationContext();
        Objects.requireNonNull(abstractC0178JqA);
        final Boolean bool = Boolean.FALSE;
        if (callback != null) {
            PostTask.b(AbstractC0538bE.a, new Runnable(callback, bool) { // from class: Bo
                public final Callback s;
                public final Boolean t;

                {
                    this.s = callback;
                    this.t = bool;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.a(this.t);
                }
            });
        }
    }
}
