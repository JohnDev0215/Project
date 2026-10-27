package defpackage;

import android.content.Context;
import android.os.UserManager;

/* JADX INFO: renamed from: jL, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0991jL extends AbstractC0936iL {
    public final UserManager e;

    public AbstractC0991jL(Context context) {
        super(context);
        this.e = (UserManager) context.getSystemService("user");
    }
}
