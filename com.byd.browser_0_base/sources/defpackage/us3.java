package defpackage;

import android.graphics.Bitmap;
import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: WebsiteApprovalProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class us3 {
    public static final PropertyModel.r<String> a;
    public static final PropertyModel.r<Bitmap> b;
    public static final PropertyModel.l<GURL> c;
    public static final PropertyModel.r<View.OnClickListener> d;
    public static final PropertyModel.r<View.OnClickListener> e;
    public static final v62[] f;

    static {
        PropertyModel.r<String> rVar = new PropertyModel.r<>("child_name", false);
        a = rVar;
        PropertyModel.r<Bitmap> rVar2 = new PropertyModel.r<>("favicon", false);
        b = rVar2;
        PropertyModel.l<GURL> lVar = new PropertyModel.l<>("url");
        c = lVar;
        PropertyModel.r<View.OnClickListener> rVar3 = new PropertyModel.r<>("on_click_approve", false);
        d = rVar3;
        PropertyModel.r<View.OnClickListener> rVar4 = new PropertyModel.r<>("on_click_deny", false);
        e = rVar4;
        f = new v62[]{rVar, rVar2, lVar, rVar3, rVar4};
    }
}
