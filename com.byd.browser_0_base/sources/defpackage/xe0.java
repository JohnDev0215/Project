package defpackage;

import android.app.Activity;
import android.content.Context;
import android.text.style.ClickableSpan;
import android.view.View;
import org.chromium.chrome.browser.download.DownloadUtils;

/* JADX INFO: compiled from: DuplicateDownloadDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class xe0 extends ClickableSpan {
    public final /* synthetic */ Context e;
    public final /* synthetic */ String f;
    public final /* synthetic */ ye0 g;

    public xe0(ye0 ye0Var, Activity activity, String str) {
        this.g = ye0Var;
        this.e = activity;
        this.f = str;
    }

    @Override // android.text.style.ClickableSpan
    public final void onClick(View view) {
        ye0 ye0Var = this.g;
        ye0Var.a.b(3, ye0Var.b);
        ye0.a(3, true);
        DownloadUtils.g(this.e, this.f);
    }
}
