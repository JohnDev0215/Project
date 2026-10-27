package defpackage;

import java.util.List;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: PasswordEditDialogProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class fx1 {
    public static final PropertyModel.l<List<String>> a;
    public static final PropertyModel.r<String> b;
    public static final PropertyModel.p c;
    public static final PropertyModel.l<Callback<String>> d;
    public static final PropertyModel.l<Callback<Integer>> e;
    public static final PropertyModel.r<String> f;
    public static final PropertyModel.l<String> g;
    public static final PropertyModel.l<Callback<String>> h;
    public static final PropertyModel.r<String> i;
    public static final v62[] j;

    static {
        PropertyModel.l<List<String>> lVar = new PropertyModel.l<>("usernames");
        a = lVar;
        PropertyModel.r<String> rVar = new PropertyModel.r<>("username", false);
        b = rVar;
        PropertyModel.p pVar = new PropertyModel.p("username index");
        c = pVar;
        PropertyModel.l<Callback<String>> lVar2 = new PropertyModel.l<>("username changed callback");
        d = lVar2;
        PropertyModel.l<Callback<Integer>> lVar3 = new PropertyModel.l<>("username selected callback");
        e = lVar3;
        PropertyModel.r<String> rVar2 = new PropertyModel.r<>("password", false);
        f = rVar2;
        PropertyModel.l<String> lVar4 = new PropertyModel.l<>("footer");
        g = lVar4;
        PropertyModel.l<Callback<String>> lVar5 = new PropertyModel.l<>("password changed callback");
        h = lVar5;
        PropertyModel.r<String> rVar3 = new PropertyModel.r<>("empty password error", false);
        i = rVar3;
        j = new v62[]{lVar, rVar, pVar, lVar2, lVar3, rVar2, lVar5, rVar3, lVar4};
    }
}
