package p;

import android.content.ComponentCallbacks2;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public abstract class r50 extends t7 implements m11 {
    public ia0 G;

    @Override // p.m11
    public final ia0 b() {
        return this.G;
    }

    @Override // p.zw0, androidx.activity.a, p.wv, android.app.Activity
    public void onCreate(Bundle bundle) {
        ComponentCallbacks2 application = getApplication();
        if (!(application instanceof m11)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), m11.class.getCanonicalName()));
        }
        v03.u(this, (m11) application);
        super.onCreate(bundle);
    }
}
