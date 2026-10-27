package defpackage;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroupOverlay;

/* JADX INFO: renamed from: l7, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1089l7 implements InterfaceC1145m7 {
    public final ViewGroupOverlay a;

    public C1089l7(ViewGroup viewGroup) {
        this.a = viewGroup.getOverlay();
    }

    @Override // defpackage.InterfaceC1145m7
    public void a(View view) {
        this.a.remove(view);
    }
}
