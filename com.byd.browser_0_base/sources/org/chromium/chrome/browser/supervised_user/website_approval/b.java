package org.chromium.chrome.browser.supervised_user.website_approval;

import android.graphics.Bitmap;
import android.view.View;
import defpackage.ji;
import defpackage.us3;
import defpackage.v62;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebsiteApprovalCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public final c a;

    /* JADX INFO: compiled from: WebsiteApprovalCoordinator.java */
    public interface a {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public b(WindowAndroid windowAndroid, GURL gurl, org.chromium.chrome.browser.supervised_user.a aVar, Bitmap bitmap) {
        HashMap mapE = PropertyModel.e(us3.f);
        PropertyModel.l<GURL> lVar = us3.c;
        int i = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = gurl;
        mapE.put(lVar, gVar);
        PropertyModel.r<Bitmap> rVar = us3.b;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = bitmap;
        mapE.put(rVar, gVar2);
        PropertyModel propertyModel = new PropertyModel(mapE);
        ji jiVarA = h.a(windowAndroid);
        WebsiteApprovalSheetContent websiteApprovalSheetContent = new WebsiteApprovalSheetContent(windowAndroid.i.get());
        j.b(propertyModel, websiteApprovalSheetContent, new j.a() { // from class: org.chromium.chrome.browser.supervised_user.website_approval.a
            @Override // org.chromium.ui.modelutil.j.a
            public final void b(k kVar, Object obj, Object obj2) {
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                WebsiteApprovalSheetContent websiteApprovalSheetContent2 = (WebsiteApprovalSheetContent) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.r<String> rVar2 = us3.a;
                if (v62Var == rVar2) {
                    websiteApprovalSheetContent2.y((String) propertyModel2.j(rVar2));
                    return;
                }
                PropertyModel.l<GURL> lVar2 = us3.c;
                if (v62Var == lVar2) {
                    websiteApprovalSheetContent2.v((GURL) propertyModel2.j(lVar2));
                    websiteApprovalSheetContent2.x((GURL) propertyModel2.j(lVar2));
                    return;
                }
                PropertyModel.r<View.OnClickListener> rVar3 = us3.d;
                if (v62Var == rVar3) {
                    websiteApprovalSheetContent2.b().setOnClickListener((View.OnClickListener) propertyModel2.j(rVar3));
                    return;
                }
                PropertyModel.r<View.OnClickListener> rVar4 = us3.e;
                if (v62Var == rVar4) {
                    websiteApprovalSheetContent2.c().setOnClickListener((View.OnClickListener) propertyModel2.j(rVar4));
                    return;
                }
                PropertyModel.r<Bitmap> rVar5 = us3.b;
                if (v62Var == rVar5) {
                    websiteApprovalSheetContent2.w((Bitmap) propertyModel2.j(rVar5));
                }
            }
        });
        this.a = new c(aVar, jiVarA, websiteApprovalSheetContent, propertyModel);
    }
}
