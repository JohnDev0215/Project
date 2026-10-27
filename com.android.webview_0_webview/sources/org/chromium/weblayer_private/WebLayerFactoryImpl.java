package org.chromium.weblayer_private;

import android.os.IBinder;
import defpackage.AbstractBinderC0887hS;
import defpackage.AbstractC1054kS;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class WebLayerFactoryImpl extends AbstractBinderC0887hS {
    public static int a;

    public WebLayerFactoryImpl(String str, int i) {
        a = i;
    }

    public static int c() {
        int i = a;
        if (i != 0) {
            return i;
        }
        throw new IllegalStateException("This should only be called once WebLayer is initialized");
    }

    public static IBinder create(String str, int i, int i2) {
        return new WebLayerFactoryImpl(str, i);
    }

    public boolean d() {
        AbstractC1054kS.a();
        int i = a;
        AbstractC1054kS.a();
        return Math.abs(i + (-83)) <= 4;
    }
}
