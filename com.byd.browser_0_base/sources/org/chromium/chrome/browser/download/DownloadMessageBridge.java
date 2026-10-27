package org.chromium.chrome.browser.download;

import android.content.Context;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ad0;
import defpackage.ng1;
import defpackage.vg1;
import defpackage.xc0;
import defpackage.xz;
import defpackage.yo;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$string;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class DownloadMessageBridge {
    public long a;

    public DownloadMessageBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static DownloadMessageBridge create(long j) {
        return new DownloadMessageBridge(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public void showIncognitoDownloadMessage(long j) {
        e eVar = DownloadManagerService.g().m;
        xc0 xc0Var = new xc0(j, 0, this);
        Context context = xz.a;
        vg1 vg1VarI = eVar.i();
        if (vg1VarI == null) {
            xc0Var.onResult(Boolean.TRUE);
            e.k(5);
            return;
        }
        HashMap mapE = PropertyModel.e(ng1.H);
        PropertyModel.j jVar = ng1.a;
        int i = zj.a;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = 36;
        mapE.put(jVar, dVar);
        PropertyModel propertyModel = new PropertyModel(mapE);
        propertyModel.p(ng1.f, context.getString(R$string.incognito_download_message_title));
        propertyModel.p(ng1.h, context.getString(R$string.incognito_download_message_detail));
        propertyModel.p(ng1.c, context.getString(R$string.incognito_download_message_button));
        propertyModel.p(ng1.l, AppCompatResources.a(context, R$drawable.ic_incognito_download_message));
        propertyModel.p(ng1.d, new yo(7, xc0Var));
        propertyModel.p(ng1.x, new ad0(xc0Var, 0));
        vg1VarI.c(propertyModel, true);
        e.k(0);
    }
}
