package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.AssetManager;

/* JADX INFO: renamed from: Yb, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0432Yb extends ContextWrapper {
    public C0432Yb(C0450Zb c0450Zb, Context context) {
        super(context);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public AssetManager getAssets() {
        return getResources().getAssets();
    }
}
