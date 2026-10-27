package defpackage;

import android.content.Context;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: ScreenshotShareSheetMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class we2 {
    public final PropertyModel a;
    public final Context b;
    public final Runnable c;
    public final Runnable d;
    public final Callback<Runnable> e;
    public final rq f;
    public final WindowAndroid g;
    public final String h;

    public we2(Context context, PropertyModel propertyModel, d92 d92Var, eu1 eu1Var, WindowAndroid windowAndroid, String str, rq rqVar, Callback callback) {
        this.d = d92Var;
        this.c = eu1Var;
        this.b = context;
        this.a = propertyModel;
        this.g = windowAndroid;
        this.h = str;
        this.f = rqVar;
        this.e = callback;
        propertyModel.p(ye2.a, new td2(29, this));
    }
}
