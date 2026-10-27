package defpackage;

import android.content.Context;
import android.os.Bundle;
import org.chromium.weblayer_private.BrowserImpl;
import org.chromium.weblayer_private.ProfileImpl;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class CP extends DQ {
    public final ProfileImpl b;
    public final String c;
    public BrowserImpl d;
    public Context e;

    public CP(CQ cq, VR vr, Bundle bundle) {
        super(vr);
        this.c = bundle.getString("persistence_id");
        this.b = cq.a(bundle.getString("profile_name"));
    }
}
