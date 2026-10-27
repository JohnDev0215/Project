package defpackage;

import android.graphics.Bitmap;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AccountSelectionProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v1 {
    public static final PropertyModel.l<Runnable> a;
    public static final PropertyModel.l<String> b;
    public static final PropertyModel.l<String> c;
    public static final PropertyModel.l<String> d;
    public static final PropertyModel.l<Bitmap> e;
    public static final PropertyModel.l<a> f;
    public static final v62[] g;

    /* JADX INFO: compiled from: AccountSelectionProperties.java */
    public enum a {
        SIGN_IN,
        VERIFY,
        VERIFY_AUTO_REAUTHN
    }

    static {
        PropertyModel.l<Runnable> lVar = new PropertyModel.l<>("close_on_click_listener");
        a = lVar;
        PropertyModel.l<String> lVar2 = new PropertyModel.l<>("idp_for_display");
        b = lVar2;
        PropertyModel.l<String> lVar3 = new PropertyModel.l<>("top_frame_for_display");
        c = lVar3;
        PropertyModel.l<String> lVar4 = new PropertyModel.l<>("iframe_for_display");
        d = lVar4;
        PropertyModel.l<Bitmap> lVar5 = new PropertyModel.l<>("brand_icon");
        e = lVar5;
        PropertyModel.l<a> lVar6 = new PropertyModel.l<>("type");
        f = lVar6;
        g = new v62[]{lVar, lVar2, lVar3, lVar4, lVar5, lVar6};
    }
}
