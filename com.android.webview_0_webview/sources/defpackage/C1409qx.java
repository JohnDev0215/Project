package defpackage;

import J.N;
import org.chromium.components.embedder_support.delegate.ColorChooserAndroid;

/* JADX INFO: renamed from: qx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1409qx implements InterfaceC1903zx {
    public final /* synthetic */ ColorChooserAndroid s;

    public C1409qx(ColorChooserAndroid colorChooserAndroid) {
        this.s = colorChooserAndroid;
    }

    @Override // defpackage.InterfaceC1903zx
    public void a(int i) {
        this.s.a.dismiss();
        ColorChooserAndroid colorChooserAndroid = this.s;
        N.M2zph6xH(colorChooserAndroid.b, colorChooserAndroid, i);
    }
}
