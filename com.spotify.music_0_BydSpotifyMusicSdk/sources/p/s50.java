package p;

import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.ContentProvider;

/* JADX INFO: loaded from: classes.dex */
public abstract class s50 extends ContentProvider {
    @Override // android.content.ContentProvider
    public boolean onCreate() {
        ComponentCallbacks2 componentCallbacks2 = (Application) getContext().getApplicationContext();
        if (!(componentCallbacks2 instanceof m11)) {
            throw new RuntimeException(String.format("%s does not implement %s", componentCallbacks2.getClass().getCanonicalName(), m11.class.getCanonicalName()));
        }
        v03.u(this, (m11) componentCallbacks2);
        return true;
    }
}
