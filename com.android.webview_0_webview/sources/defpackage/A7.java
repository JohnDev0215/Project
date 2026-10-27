package defpackage;

import android.view.View;
import android.view.WindowId;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class A7 implements B7 {
    public final WindowId a;

    public A7(View view) {
        this.a = view.getWindowId();
    }

    public boolean equals(Object obj) {
        return (obj instanceof A7) && ((A7) obj).a.equals(this.a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }
}
