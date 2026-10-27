package defpackage;

import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: IncognitoReauthProperties.java */
/* JADX INFO: loaded from: classes.dex */
public final class vy0 {
    public static final PropertyModel.l<Runnable> a;
    public static final PropertyModel.l<Runnable> b;
    public static final PropertyModel.h c;
    public static final PropertyModel.l<l81> d;
    public static final v62[] e;

    static {
        PropertyModel.l<Runnable> lVar = new PropertyModel.l<>("on_unlock_incognito_clicked");
        a = lVar;
        PropertyModel.l<Runnable> lVar2 = new PropertyModel.l<>("on_see_other_tabs_clicked");
        b = lVar2;
        PropertyModel.h hVar = new PropertyModel.h();
        c = hVar;
        PropertyModel.l<l81> lVar3 = new PropertyModel.l<>();
        d = lVar3;
        e = new v62[]{lVar, lVar2, hVar, lVar3};
    }
}
