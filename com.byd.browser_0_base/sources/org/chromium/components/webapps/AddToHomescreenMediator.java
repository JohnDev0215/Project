package org.chromium.components.webapps;

import J.N;
import android.graphics.Bitmap;
import android.util.Pair;
import defpackage.w3;
import defpackage.y3;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.base.d;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes3.dex */
public class AddToHomescreenMediator implements y3 {
    public long e = N.MBJ_fnwd(this);
    public final PropertyModel f;
    public final WindowAndroid g;
    public AppData h;

    public AddToHomescreenMediator(PropertyModel propertyModel, WindowAndroid windowAndroid) {
        this.f = propertyModel;
        this.g = windowAndroid;
    }

    @Override // defpackage.y3
    public final boolean a() {
        if (this.f.h(w3.e) != 0) {
            return false;
        }
        this.h.getClass();
        d dVar = this.g.e;
        if (dVar != null) {
            dVar.b = (dVar.b + 1) % 100;
            throw null;
        }
        long j = this.e;
        if (j != 0) {
            N.MpeFYmhO(j);
        }
        return true;
    }

    @Override // defpackage.y3
    public final void b(String str) {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.MQa5VwR6(j, str);
        long j2 = this.e;
        if (j2 == 0) {
            return;
        }
        N.MwQAZYNv(j2);
        this.e = 0L;
    }

    @Override // defpackage.y3
    public final void c() {
        long j = this.e;
        if (j == 0) {
            return;
        }
        N.M3ujfdbM(j);
        long j2 = this.e;
        if (j2 == 0) {
            return;
        }
        N.MwQAZYNv(j2);
        this.e = 0L;
    }

    @CalledByNative
    public void setIcon(Bitmap bitmap, boolean z, boolean z2) {
        if (z2) {
            bitmap = WebappsIconUtils.createHomeScreenIconFromWebIcon(bitmap, true);
        }
        PropertyModel.r<Pair<Bitmap, Boolean>> rVar = w3.d;
        Pair pair = new Pair(bitmap, Boolean.valueOf(z));
        PropertyModel propertyModel = this.f;
        propertyModel.p(rVar, pair);
        propertyModel.l(w3.f, true);
    }

    @CalledByNative
    public void setNativeAppInfo(AppData appData) {
        this.h = appData;
        PropertyModel.r<String> rVar = w3.a;
        appData.getClass();
        PropertyModel propertyModel = this.f;
        propertyModel.p(rVar, null);
        propertyModel.n(w3.e, 0);
        propertyModel.m(w3.i, 0.0f);
        propertyModel.l(w3.f, true);
        propertyModel.p(w3.h, null);
    }

    @CalledByNative
    public void setWebAppInfo(String str, String str2, boolean z) {
        PropertyModel.r<String> rVar = w3.a;
        PropertyModel propertyModel = this.f;
        propertyModel.p(rVar, str);
        propertyModel.p(w3.b, str2);
        propertyModel.n(w3.e, z ? 1 : 2);
    }
}
