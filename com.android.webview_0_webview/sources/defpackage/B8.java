package defpackage;

import J.N;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class B8 implements Runnable {
    public final List s;
    public final Callback t;

    public B8(List list, Callback callback) {
        this.s = list;
        this.t = callback;
    }

    @Override // java.lang.Runnable
    public void run() {
        List list = this.s;
        Callback callback = this.t;
        String[] strArr = (String[]) list.toArray(new String[list.size()]);
        if (callback == null) {
            callback = new Callback() { // from class: Ao
                @Override // org.chromium.base.Callback
                public void a(Object obj) {
                }
            };
        }
        N.MlIL02rX(strArr, callback);
    }
}
