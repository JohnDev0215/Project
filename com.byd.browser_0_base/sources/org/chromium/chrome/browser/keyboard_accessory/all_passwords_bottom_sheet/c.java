package org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet;

import defpackage.p81;
import defpackage.s10;
import defpackage.u10;
import defpackage.v62;
import defpackage.xa1;
import defpackage.zj;
import java.util.HashMap;
import org.chromium.base.Callback;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: AllPasswordsBottomSheetProperties.java */
/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final PropertyModel.n a;
    public static final PropertyModel.l<Callback<Integer>> b;
    public static final PropertyModel.l<p81<xa1>> c;
    public static final PropertyModel.l<String> d;
    public static final PropertyModel.l<Callback<String>> e;
    public static final v62[] f;

    /* JADX INFO: compiled from: AllPasswordsBottomSheetProperties.java */
    public static class a {
        public static final PropertyModel.l<s10> a;
        public static final PropertyModel.l<Callback<u10>> b;
        public static final PropertyModel.h c;
        public static final v62[] d;

        static {
            PropertyModel.l<s10> lVar = new PropertyModel.l<>("credential");
            a = lVar;
            PropertyModel.l<Callback<u10>> lVar2 = new PropertyModel.l<>("on_click_listener");
            b = lVar2;
            PropertyModel.h hVar = new PropertyModel.h("is_password_field");
            c = hVar;
            d = new v62[]{lVar, lVar2, hVar};
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static PropertyModel a(s10 s10Var, Callback<u10> callback, boolean z) {
            HashMap mapE = PropertyModel.e(d);
            PropertyModel.l<s10> lVar = a;
            int i = zj.a;
            PropertyModel.g gVar = new PropertyModel.g();
            gVar.a = s10Var;
            mapE.put(lVar, gVar);
            PropertyModel.l<Callback<u10>> lVar2 = b;
            PropertyModel.g gVar2 = new PropertyModel.g();
            gVar2.a = callback;
            mapE.put(lVar2, gVar2);
            PropertyModel.h hVar = c;
            PropertyModel.a aVar = new PropertyModel.a();
            aVar.a = z;
            mapE.put(hVar, aVar);
            return new PropertyModel(mapE);
        }
    }

    static {
        PropertyModel.n nVar = new PropertyModel.n("visible");
        a = nVar;
        PropertyModel.l<Callback<Integer>> lVar = new PropertyModel.l<>("dismiss_handler");
        b = lVar;
        PropertyModel.l<p81<xa1>> lVar2 = new PropertyModel.l<>("sheet_items");
        c = lVar2;
        PropertyModel.l<String> lVar3 = new PropertyModel.l<>("origin");
        d = lVar3;
        PropertyModel.l<Callback<String>> lVar4 = new PropertyModel.l<>("on_query_text_change");
        e = lVar4;
        f = new v62[]{nVar, lVar, lVar2, lVar3, lVar4};
    }
}
