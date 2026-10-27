package defpackage;

import android.content.Context;
import android.os.Bundle;

/* JADX INFO: renamed from: Yr, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0448Yr extends AbstractC0991jL {
    public C0448Yr(Context context) {
        super(context);
    }

    @Override // defpackage.AbstractC1047kL
    public void a(Bundle bundle) {
        Bundle bundle2;
        if (bundle != null) {
            bundle2 = new Bundle();
            for (String str : bundle.keySet()) {
                if (str.startsWith("com.android.browser:")) {
                    bundle2.putSerializable(str.substring(20), bundle.getSerializable(str));
                }
            }
        } else {
            bundle2 = null;
        }
        super.a(bundle2);
    }
}
