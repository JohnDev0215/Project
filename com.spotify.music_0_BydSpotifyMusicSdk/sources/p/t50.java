package p;

import android.app.Service;
import android.content.ComponentCallbacks2;

/* JADX INFO: loaded from: classes.dex */
public abstract class t50 extends Service {
    @Override // android.app.Service
    public void onCreate() {
        ComponentCallbacks2 application = getApplication();
        if (!(application instanceof m11)) {
            throw new RuntimeException(String.format("%s does not implement %s", application.getClass().getCanonicalName(), m11.class.getCanonicalName()));
        }
        v03.u(this, (m11) application);
        super.onCreate();
    }
}
