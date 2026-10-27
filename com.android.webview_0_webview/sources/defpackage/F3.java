package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class F3 extends P3 {
    public final /* synthetic */ K3 a;

    public F3(K3 k3) {
        this.a = k3;
    }

    @Override // defpackage.P3
    public K3 a(Context context, String str, Bundle bundle) {
        Objects.requireNonNull(this.a.f4J);
        return K3.v(context, str, bundle);
    }

    @Override // defpackage.P3
    public View b(int i) {
        View view = this.a.Z;
        if (view != null) {
            return view.findViewById(i);
        }
        throw new IllegalStateException("Fragment does not have a view");
    }

    @Override // defpackage.P3
    public boolean c() {
        return this.a.Z != null;
    }
}
