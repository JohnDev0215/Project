package defpackage;

import com.google.android.gms.common.api.internal.BasePendingResult;

/* JADX INFO: renamed from: qe, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1390qe implements InterfaceC1499se {
    public final /* synthetic */ C1444re a;

    public C1390qe(C1444re c1444re) {
        this.a = c1444re;
    }

    @Override // defpackage.InterfaceC1499se
    public final void a(BasePendingResult basePendingResult) {
        this.a.a.remove(basePendingResult);
    }
}
