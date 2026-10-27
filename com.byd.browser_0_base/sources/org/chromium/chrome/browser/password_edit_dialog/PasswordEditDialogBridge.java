package org.chromium.chrome.browser.password_edit_dialog;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import defpackage.C0175b;
import defpackage.bi1;
import defpackage.bq;
import defpackage.dm0;
import defpackage.fx1;
import defpackage.ko0;
import defpackage.lj1;
import defpackage.sk;
import defpackage.zj;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_edit_dialog.b;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public class PasswordEditDialogBridge implements b.a {
    public long a;
    public final b b;

    public PasswordEditDialogBridge(long j, WindowAndroid windowAndroid) {
        this.a = j;
        Context context = windowAndroid.i.get();
        bi1 bi1VarN = windowAndroid.n();
        sk skVar = bq.a;
        this.b = new b(context, bi1VarN, N.M09VlOh_("PasswordEditDialogWithDetails") ? (PasswordEditDialogWithDetailsView) LayoutInflater.from(context).inflate(R$layout.password_edit_dialog_with_details, (ViewGroup) null) : (UsernameSelectionConfirmationView) LayoutInflater.from(context).inflate(R$layout.password_edit_dialog, (ViewGroup) null), this);
    }

    @CalledByNative
    public static PasswordEditDialogBridge create(long j, WindowAndroid windowAndroid) {
        return new PasswordEditDialogBridge(j, windowAndroid);
    }

    @CalledByNative
    public void dismiss() {
        b bVar = this.b;
        bVar.b.b(4, bVar.d);
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [T, lj1] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, java.util.List, java.util.List<java.lang.String>] */
    @CalledByNative
    public void showLegacyPasswordEditDialog(String[] strArr, int i, String str) {
        b bVar = this.b;
        bVar.getClass();
        ?? AsList = Arrays.asList(strArr);
        bVar.d = bVar.a(R$string.confirm_username_dialog_title, R$string.password_manager_update_button);
        HashMap mapE = PropertyModel.e(fx1.j);
        PropertyModel.l<List<String>> lVar = fx1.a;
        int i2 = zj.a;
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = AsList;
        mapE.put(lVar, gVar);
        PropertyModel.p pVar = fx1.c;
        PropertyModel.d dVar = new PropertyModel.d();
        dVar.a = i;
        mapE.put(pVar, dVar);
        PropertyModel.l<Callback<Integer>> lVar2 = fx1.e;
        c cVar = bVar.f;
        Objects.requireNonNull(cVar);
        ?? lj1Var = new lj1(11, cVar);
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = lj1Var;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar2, gVar2, mapE);
        bVar.e = propertyModelP;
        PropertyModel propertyModel = bVar.d;
        cVar.e = propertyModelP;
        cVar.f = propertyModel;
        cVar.g = AsList;
        j.b(propertyModelP, bVar.c, new a());
        bVar.b.j(bVar.d, 0, false);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @CalledByNative
    public void showPasswordEditDialog(String[] strArr, String str, String str2, String str3) {
        b bVar = this.b;
        bVar.getClass();
        List listAsList = Arrays.asList(strArr);
        boolean zContains = listAsList.contains(str);
        List<String> listAsList2 = zContains ? listAsList : Arrays.asList(str);
        bVar.d = bVar.a(listAsList.contains(str) ? listAsList2.size() < 2 ? R$string.password_update_dialog_title : R$string.confirm_username_dialog_title : R$string.save_password, zContains ? R$string.password_manager_update_button : R$string.password_manager_save_button);
        PropertyModel.b bVar2 = new PropertyModel.b(fx1.j);
        PropertyModel.l lVar = fx1.a;
        ArrayList arrayList = new ArrayList();
        for (String str4 : listAsList2) {
            if (!str4.isEmpty()) {
                arrayList.add(str4);
            }
        }
        bVar2.e(lVar, arrayList);
        bVar2.e(fx1.f, str2);
        bVar2.e(fx1.g, bVar.a.getString(TextUtils.isEmpty(str3) ? R$string.password_edit_dialog_unsynced_footer : R$string.password_edit_dialog_synced_footer_google, str3));
        bVar2.e(fx1.b, str);
        PropertyModel.l lVar2 = fx1.d;
        c cVar = bVar.f;
        Objects.requireNonNull(cVar);
        bVar2.e(lVar2, new ko0(26, cVar));
        bVar2.e(fx1.h, new dm0(21, cVar));
        PropertyModel propertyModelA = bVar2.a();
        bVar.e = propertyModelA;
        PropertyModel propertyModel = bVar.d;
        List<String> listAsList3 = Arrays.asList(strArr);
        cVar.e = propertyModelA;
        cVar.f = propertyModel;
        cVar.g = listAsList3;
        j.b(bVar.e, bVar.c, new a());
        bVar.b.j(bVar.d, bVar.g ? 1 : 0, false);
    }
}
