package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;

/* JADX INFO: renamed from: gC, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0815gC extends ContextWrapper {
    public Resources a;

    public C0815gC(Context context, AbstractC0592cC abstractC0592cC) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.a == null) {
            Resources resources = super.getResources();
            this.a = new C0703eC(this, resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration());
        }
        return this.a;
    }
}
