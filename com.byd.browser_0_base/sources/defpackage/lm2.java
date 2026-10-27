package defpackage;

import android.view.View;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: SigninFirstRunProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class lm2 {
    public static final PropertyModel.l<View.OnClickListener> a;
    public static final PropertyModel.r<fb0> b;
    public static final PropertyModel.n c;
    public static final PropertyModel.l<View.OnClickListener> d;
    public static final PropertyModel.l<View.OnClickListener> e;
    public static final PropertyModel.n f;
    public static final PropertyModel.n g;
    public static final PropertyModel.n h;
    public static final PropertyModel.r<a> i;
    public static final PropertyModel.n j;
    public static final PropertyModel.r<CharSequence> k;
    public static final v62[] l;

    /* JADX INFO: compiled from: SigninFirstRunProperties.java */
    public static class a {
    }

    static {
        PropertyModel.l<View.OnClickListener> lVar = new PropertyModel.l<>("on_selected_account_clicked");
        a = lVar;
        PropertyModel.r<fb0> rVar = new PropertyModel.r<>("selected_account_data", false);
        b = rVar;
        PropertyModel.n nVar = new PropertyModel.n("is_selected_account_supervised");
        c = nVar;
        PropertyModel.l<View.OnClickListener> lVar2 = new PropertyModel.l<>("on_continue_as_clicked");
        d = lVar2;
        PropertyModel.l<View.OnClickListener> lVar3 = new PropertyModel.l<>("on_dismiss_clicked");
        e = lVar3;
        PropertyModel.n nVar2 = new PropertyModel.n("show_signin_progress_spinner_with_text");
        f = nVar2;
        PropertyModel.n nVar3 = new PropertyModel.n("show_signin_progress_spinner");
        g = nVar3;
        PropertyModel.n nVar4 = new PropertyModel.n("show_initial_load_progress_spinner");
        h = nVar4;
        PropertyModel.r<a> rVar2 = new PropertyModel.r<>("fre_policy", false);
        i = rVar2;
        PropertyModel.n nVar5 = new PropertyModel.n("is_signin_supported");
        j = nVar5;
        PropertyModel.r<CharSequence> rVar3 = new PropertyModel.r<>("footer_string", false);
        k = rVar3;
        l = new v62[]{lVar, rVar, nVar, lVar2, lVar3, nVar2, nVar3, nVar4, rVar2, nVar5, rVar3};
    }
}
