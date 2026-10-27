package org.chromium.weblayer_private;

import android.app.Service;
import android.content.Context;
import android.os.IBinder;
import defpackage.AbstractBinderC0774fR;
import defpackage.AbstractC1299ox;
import defpackage.Bu;
import defpackage.XQ;
import org.chromium.content.app.ContentChildProcessServiceDelegate;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public final class ChildProcessServiceImpl extends AbstractBinderC0774fR {
    public Bu a;

    public ChildProcessServiceImpl(Service service, Context context) {
        this.a = new Bu(new ContentChildProcessServiceDelegate(), service, context);
    }

    public static IBinder create(Service service, Context context) {
        return create(service, context, XQ.c(context));
    }

    public static IBinder create(Service service, Context context, Context context2) {
        AbstractC1299ox.c = context2;
        return new ChildProcessServiceImpl(service, AbstractC1299ox.a(context));
    }
}
