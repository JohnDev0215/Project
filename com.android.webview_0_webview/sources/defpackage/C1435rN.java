package defpackage;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.widget.ListView;

/* JADX INFO: renamed from: rN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1435rN {
    public InterfaceC1655vN s;

    public C1435rN(Context context, View view) {
        if (Build.VERSION.SDK_INT >= 24) {
            this.s = new C1600uN(context, view);
        } else {
            this.s = new C1820yN(context, view);
        }
    }

    public ListView a() {
        return this.s.i();
    }
}
