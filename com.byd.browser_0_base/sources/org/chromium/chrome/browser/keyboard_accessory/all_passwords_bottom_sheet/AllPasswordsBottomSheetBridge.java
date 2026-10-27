package org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet;

import android.content.Context;
import defpackage.C0175b;
import defpackage.ag;
import defpackage.h4;
import defpackage.i4;
import defpackage.j4;
import defpackage.ji;
import defpackage.k4;
import defpackage.p81;
import defpackage.s10;
import defpackage.xa1;
import defpackage.zj;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.a;
import org.chromium.chrome.browser.keyboard_accessory.all_passwords_bottom_sheet.c;
import org.chromium.components.browser_ui.bottomsheet.h;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes.dex */
public class AllPasswordsBottomSheetBridge implements a.InterfaceC0089a {
    public long a;
    public s10[] b;
    public final a c;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [T, h4] */
    /* JADX WARN: Type inference failed for: r2v0, types: [T, i4] */
    /* JADX WARN: Type inference failed for: r4v1, types: [T, p81] */
    public AllPasswordsBottomSheetBridge(long j, WindowAndroid windowAndroid, String str) {
        this.a = j;
        a aVar = new a();
        this.c = aVar;
        Context context = (Context) windowAndroid.k().get();
        ji jiVarA = h.a(windowAndroid);
        b bVar = aVar.a;
        Objects.requireNonNull(bVar);
        ?? h4Var = new h4(bVar, 0);
        ?? i4Var = new i4(bVar, 0);
        HashMap mapE = PropertyModel.e(c.f);
        PropertyModel.n nVar = c.a;
        int i = zj.a;
        PropertyModel.a aVar2 = new PropertyModel.a();
        aVar2.a = false;
        mapE.put(nVar, aVar2);
        PropertyModel.l<p81<xa1>> lVar = c.c;
        ?? p81Var = new p81();
        PropertyModel.g gVar = new PropertyModel.g();
        gVar.a = p81Var;
        mapE.put(lVar, gVar);
        PropertyModel.l<Callback<Integer>> lVar2 = c.b;
        PropertyModel.g gVar2 = new PropertyModel.g();
        gVar2.a = h4Var;
        mapE.put(lVar2, gVar2);
        PropertyModel.l<String> lVar3 = c.d;
        PropertyModel.g gVar3 = new PropertyModel.g();
        gVar3.a = str;
        mapE.put(lVar3, gVar3);
        PropertyModel.l<Callback<String>> lVar4 = c.e;
        PropertyModel.g gVar4 = new PropertyModel.g();
        gVar4.a = i4Var;
        PropertyModel propertyModelP = C0175b.p(mapE, lVar4, gVar4, mapE);
        bVar.a = this;
        bVar.b = propertyModelP;
        j.b(propertyModelP, new k4(context, jiVarA), new ag(4));
    }

    @CalledByNative
    public static AllPasswordsBottomSheetBridge create(long j, WindowAndroid windowAndroid, String str) {
        return new AllPasswordsBottomSheetBridge(j, windowAndroid, str);
    }

    @CalledByNative
    public final void createCredentialArray(int i) {
        this.b = new s10[i];
    }

    @CalledByNative
    public final void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public final void insertCredential(int i, String str, String str2, String str3, String str4, boolean z, String str5) {
        this.b[i] = new s10(str, str2, str3, str4, z, str5);
    }

    @CalledByNative
    public final void showCredentials(boolean z) {
        s10[] s10VarArr = this.b;
        b bVar = this.c.a;
        bVar.getClass();
        Arrays.sort(s10VarArr, new j4(0));
        bVar.c = s10VarArr;
        bVar.d = z;
        p81 p81Var = (p81) bVar.b.j(c.c);
        p81Var.clear();
        for (s10 s10Var : bVar.c) {
            if (!s10Var.b.isEmpty() || !z) {
                p81Var.t(new xa1(0, c.a.a(s10Var, new h4(bVar, 1), bVar.d)));
            }
        }
        bVar.b.l(c.a, true);
    }
}
