package org.chromium.chrome.browser.download;

import J.N;
import android.app.Activity;
import defpackage.bi1;
import defpackage.bq;
import defpackage.ci1;
import defpackage.di1;
import defpackage.sk;
import defpackage.ve0;
import defpackage.we0;
import defpackage.wq0;
import defpackage.xe0;
import defpackage.ye0;
import defpackage.ze0;
import defpackage.zj;
import java.io.File;
import java.util.HashMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.OTRProfileID;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes.dex */
public class DuplicateDownloadDialogBridge {
    public long a;

    public DuplicateDownloadDialogBridge(long j) {
        this.a = j;
    }

    @CalledByNative
    public static DuplicateDownloadDialogBridge create(long j) {
        return new DuplicateDownloadDialogBridge(j);
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v4, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v6, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v1, types: [T, we0] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, java.lang.String] */
    @CalledByNative
    public void showDialog(WindowAndroid windowAndroid, String str, String str2, long j, boolean z, OTRProfileID oTRProfileID, long j2) {
        T tD;
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == 0) {
            N.MQbifKYb(this.a, j2, false);
            return;
        }
        ye0 ye0Var = new ye0();
        bi1 bi1VarP0 = ((ci1) activity).P0();
        ze0 ze0Var = new ze0(j2, 0, this);
        ye0Var.a = bi1VarP0;
        HashMap mapE = PropertyModel.e(di1.B);
        PropertyModel.l<di1.a> lVar = di1.a;
        ?? we0Var = new we0(ze0Var, bi1VarP0, str2, activity);
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = we0Var;
        mapE.put(lVar, gVar);
        PropertyModel.r<String> rVar = di1.c;
        ?? string = activity.getResources().getString(str2.isEmpty() ? R$string.duplicate_download_dialog_title : R$string.duplicate_page_download_dialog_title);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = string;
        mapE.put(rVar, gVar2);
        PropertyModel.r<CharSequence> rVar2 = di1.f;
        if (str2.isEmpty()) {
            tD = DownloadUtils.d(activity.getString(R$string.duplicate_download_dialog_text), new File(str).getName(), true, j, new ve0(str, new wq0(29, ye0Var), oTRProfileID, 14));
        } else {
            tD = DownloadUtils.d(activity.getString(z ? R$string.duplicate_download_request_infobar_text : R$string.duplicate_download_infobar_text), str, false, 0L, new xe0(ye0Var, activity, str));
        }
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = tD;
        mapE.put(rVar2, gVar3);
        PropertyModel.r<String> rVar3 = di1.j;
        ?? string2 = activity.getResources().getString(R$string.duplicate_download_dialog_confirm_text);
        PropertyModel.g gVar4 = new PropertyModel.g();
        gVar4.a = string2;
        mapE.put(rVar3, gVar4);
        PropertyModel.r<String> rVar4 = di1.m;
        ?? string3 = activity.getResources().getString(R$string.cancel);
        PropertyModel.g gVar5 = new PropertyModel.g();
        gVar5.a = string3;
        mapE.put(rVar4, gVar5);
        ye0Var.b = new PropertyModel(mapE);
        OTRProfileID oTRProfileID2 = OTRProfileID.b;
        boolean z2 = oTRProfileID != null;
        sk skVar = bq.a;
        if (N.M09VlOh_("IncognitoDownloadsWarning") && z2) {
            ye0Var.b.p(di1.g, activity.getResources().getString(R$string.download_location_incognito_warning));
        }
        bi1VarP0.j(ye0Var.b, 0, false);
        ye0.a(0, true ^ str2.isEmpty());
    }
}
