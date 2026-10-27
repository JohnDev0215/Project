package defpackage;

import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: TouchToFillCreditCardProperties.java */
/* JADX INFO: loaded from: classes2.dex */
public final class he3 {
    public static final PropertyModel.n a;
    public static final PropertyModel.l<ya1> b;
    public static final PropertyModel.l<Callback<Integer>> c;
    public static final v62[] d;

    /* JADX INFO: compiled from: TouchToFillCreditCardProperties.java */
    public static class a {
        public static final PropertyModel.j a;
        public static final PropertyModel.l<String> b;
        public static final PropertyModel.l<String> c;
        public static final PropertyModel.l<String> d;
        public static final PropertyModel.l<String> e;
        public static final PropertyModel.l<Runnable> f;
        public static final v62[] g;

        static {
            PropertyModel.j jVar = new PropertyModel.j("card_icon_id");
            a = jVar;
            PropertyModel.l<String> lVar = new PropertyModel.l<>("card_name");
            b = lVar;
            PropertyModel.l<String> lVar2 = new PropertyModel.l<>("card_number");
            c = lVar2;
            PropertyModel.l<String> lVar3 = new PropertyModel.l<>("card_expiration");
            d = lVar3;
            PropertyModel.l<String> lVar4 = new PropertyModel.l<>("virtual_card_label");
            e = lVar4;
            PropertyModel.l<Runnable> lVar5 = new PropertyModel.l<>("on_click_action");
            f = lVar5;
            g = new v62[]{jVar, lVar, lVar2, lVar3, lVar4, lVar5};
        }
    }

    /* JADX INFO: compiled from: TouchToFillCreditCardProperties.java */
    public static class b {
        public static final PropertyModel.n a;
        public static final PropertyModel.l<Runnable> b;
        public static final PropertyModel.l<Runnable> c;
        public static final v62[] d;

        static {
            PropertyModel.n nVar = new PropertyModel.n("should_show_scan_credit_card");
            a = nVar;
            PropertyModel.l<Runnable> lVar = new PropertyModel.l<>("scan_credit_card_callback");
            b = lVar;
            PropertyModel.l<Runnable> lVar2 = new PropertyModel.l<>("show_credit_card_settings_callback");
            c = lVar2;
            d = new v62[]{nVar, lVar, lVar2};
        }
    }

    /* JADX INFO: compiled from: TouchToFillCreditCardProperties.java */
    public static class c {
        public static final PropertyModel.j a;
        public static final v62[] b;

        static {
            PropertyModel.j jVar = new PropertyModel.j("image_drawable_id");
            a = jVar;
            b = new v62[]{jVar};
        }
    }

    static {
        PropertyModel.n nVar = new PropertyModel.n("visible");
        a = nVar;
        PropertyModel.l<ya1> lVar = new PropertyModel.l<>("sheet_items");
        b = lVar;
        PropertyModel.l<Callback<Integer>> lVar2 = new PropertyModel.l<>("dismiss_handler");
        c = lVar2;
        d = new v62[]{nVar, lVar, lVar2};
    }
}
