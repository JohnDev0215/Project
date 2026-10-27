package defpackage;

import J.N;
import android.content.Context;
import android.net.Uri;
import android.webkit.ValueCallback;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.task.PostTask;
import org.chromium.support_lib_boundary.StaticsBoundaryInterface;

/* JADX INFO: renamed from: fN, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0770fN implements StaticsBoundaryInterface {
    public D8 a;

    public C0770fN(D8 d8) {
        this.a = d8;
    }

    @Override // org.chromium.support_lib_boundary.StaticsBoundaryInterface
    public Uri getSafeBrowsingPrivacyPolicyUrl() {
        C0826gN.a(3);
        Objects.requireNonNull(this.a);
        return (Uri) PostTask.c(AbstractC0538bE.a, new C8());
    }

    @Override // org.chromium.support_lib_boundary.StaticsBoundaryInterface
    public void initSafeBrowsing(Context context, ValueCallback valueCallback) {
        C0826gN.a(11);
        D8 d8 = this.a;
        Callback callbackA = AbstractC0532b8.a(valueCallback);
        Objects.requireNonNull(d8);
        PostTask.b(AbstractC0538bE.a, new A8(context, callbackA));
    }

    @Override // org.chromium.support_lib_boundary.StaticsBoundaryInterface
    public boolean isMultiProcessEnabled() {
        C0826gN.a(13);
        Objects.requireNonNull(this.a);
        return N.M04mALrd();
    }

    @Override // org.chromium.support_lib_boundary.StaticsBoundaryInterface
    public void setSafeBrowsingWhitelist(List list, ValueCallback valueCallback) {
        C0826gN.a(26);
        D8 d8 = this.a;
        Callback callbackA = AbstractC0532b8.a(valueCallback);
        Objects.requireNonNull(d8);
        PostTask.b(AbstractC0538bE.a, new B8(list, callbackA));
    }
}
