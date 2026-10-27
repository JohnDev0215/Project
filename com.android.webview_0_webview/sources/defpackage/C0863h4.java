package defpackage;

import java.util.ArrayList;

/* JADX INFO: renamed from: h4, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0863h4 implements InterfaceC0807g4 {
    public final String a = null;
    public final int b;
    public final int c;
    public final /* synthetic */ LayoutInflaterFactory2C0974j4 d;

    public C0863h4(LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4, String str, int i, int i2) {
        this.d = layoutInflaterFactory2C0974j4;
        this.b = i;
        this.c = i2;
    }

    @Override // defpackage.InterfaceC0807g4
    public boolean a(ArrayList arrayList, ArrayList arrayList2) {
        LayoutInflaterFactory2C0974j4 layoutInflaterFactory2C0974j4;
        K3 k3 = this.d.G;
        if (k3 == null || this.b >= 0 || this.a != null || (layoutInflaterFactory2C0974j4 = k3.K) == null || !layoutInflaterFactory2C0974j4.e0()) {
            return this.d.f0(arrayList, arrayList2, this.a, this.b, this.c);
        }
        return false;
    }
}
