package defpackage;

import android.content.Context;
import android.view.View;
import java.util.List;
import org.chromium.base.Callback;
import org.chromium.content.browser.GestureListenerManagerImpl;
import org.chromium.content_public.browser.WebContents;

/* JADX INFO: renamed from: mB, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1149mB implements InterfaceC0479aB {
    public final Callback a;
    public final C1435rN b;
    public boolean c;

    public C1149mB(Context context, Callback callback, View view, List list, int[] iArr, boolean z, WebContents webContents) {
        this.a = callback;
        C1435rN c1435rN = new C1435rN(context, view);
        this.b = c1435rN;
        c1435rN.s.f(new C0981jB(this));
        this.b.s.b(iArr.length > 0 ? iArr[0] : -1);
        this.b.s.l(new C1216nN(context, list, null));
        this.b.s.g(z);
        this.b.s.j(new C1037kB(this));
        GestureListenerManagerImpl.O(webContents).t.f(new C1093lB(this));
    }

    @Override // defpackage.InterfaceC0479aB
    public void a() {
        this.b.s.e();
    }

    @Override // defpackage.InterfaceC0479aB
    public void b(boolean z) {
        if (!z) {
            this.c = true;
            this.b.s.dismiss();
            return;
        }
        this.b.s.dismiss();
        if (this.c) {
            return;
        }
        this.a.a(null);
        this.c = true;
    }
}
