package defpackage;

import android.app.Activity;
import android.content.Intent;

/* JADX INFO: renamed from: uf, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class C1610uf extends AbstractDialogInterfaceOnClickListenerC1555tf {
    public final /* synthetic */ Intent s;
    public final /* synthetic */ Activity t;
    public final /* synthetic */ int u;

    public C1610uf(Intent intent, Activity activity, int i) {
        this.s = intent;
        this.t = activity;
        this.u = i;
    }

    @Override // defpackage.AbstractDialogInterfaceOnClickListenerC1555tf
    public final void a() {
        Intent intent = this.s;
        if (intent != null) {
            this.t.startActivityForResult(intent, this.u);
        }
    }
}
