package defpackage;

import android.os.ParcelFileDescriptor;
import org.chromium.android_webview.services.VariationsSeedServer;

/* JADX INFO: renamed from: os, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class BinderC1294os extends AbstractBinderC0411Wq {
    public final /* synthetic */ VariationsSeedServer a;

    public BinderC1294os(VariationsSeedServer variationsSeedServer) {
        this.a = variationsSeedServer;
    }

    @Override // defpackage.InterfaceC0429Xq
    public void s(ParcelFileDescriptor parcelFileDescriptor, long j, InterfaceC0514ar interfaceC0514ar) {
        VariationsSeedServer.a(this.a, interfaceC0514ar);
        C1239ns c1239ns = C1239ns.e;
        c1239ns.b.post(new RunnableC1184ms(c1239ns, parcelFileDescriptor, j));
    }
}
