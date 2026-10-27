package org.chromium.chrome.browser.ui.autofill;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.rb2;
import defpackage.s9;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.ui.autofill.b;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes2.dex */
public class AuthenticatorSelectionDialogBridge implements b.InterfaceC0118b {
    public final long a;
    public final b b;

    public AuthenticatorSelectionDialogBridge(long j, Context context, bi1 bi1Var) {
        this.a = j;
        this.b = new b(context, this, bi1Var);
    }

    @CalledByNative
    public static AuthenticatorSelectionDialogBridge create(long j, WindowAndroid windowAndroid) {
        Context context = (Context) windowAndroid.k().get();
        bi1 bi1VarN = windowAndroid.n();
        if (context == null || bi1VarN == null) {
            return null;
        }
        return new AuthenticatorSelectionDialogBridge(j, context, bi1VarN);
    }

    @CalledByNative
    public static void createAuthenticatorOptionAndAddToList(List<s9> list, String str, String str2, String str3, int i) {
        if (list == null) {
            return;
        }
        list.add(new s9(str, str2, str3, (i == 0 || i != 1) ? 0 : R$drawable.ic_outline_sms_24dp, i));
    }

    @CalledByNative
    public static List<s9> createAuthenticatorOptionList() {
        return new ArrayList();
    }

    @CalledByNative
    public void dismiss() {
        b bVar = this.b;
        bVar.d.b(4, bVar.h);
    }

    @CalledByNative
    public void show(List<s9> list) {
        b bVar = this.b;
        bVar.getClass();
        bVar.i = list.get(0);
        Context context = bVar.b;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.authenticator_selection_dialog, (ViewGroup) null);
        bVar.f = viewInflate.findViewById(R$id.authenticator_selection_dialog_contents);
        View viewFindViewById = viewInflate.findViewById(R$id.progress_bar_overlay);
        bVar.e = viewFindViewById;
        viewFindViewById.setVisibility(8);
        bVar.g = (RecyclerView) viewInflate.findViewById(R$id.authenticator_options_view);
        bVar.g.setAdapter(new a(context, list, bVar));
        PropertyModel.b bVar2 = new PropertyModel.b(di1.B);
        bVar2.e(di1.a, bVar.a);
        bVar2.e(di1.h, viewInflate);
        bVar2.e(di1.c, context.getResources().getString(list.size() > 1 ? R$string.autofill_card_auth_selection_dialog_title_multiple_options : R$string.autofill_payments_authenticator_selection_dialog_title));
        PropertyModel.r<Drawable> rVar = di1.e;
        Resources resources = context.getResources();
        int i = R$drawable.google_pay_with_divider;
        Resources.Theme theme = context.getTheme();
        ThreadLocal<TypedValue> threadLocal = rb2.a;
        bVar2.e(rVar, resources.getDrawable(i, theme));
        bVar2.e(di1.m, context.getResources().getString(R$string.autofill_payments_authenticator_selection_dialog_negative_button_label));
        bVar2.e(di1.j, bVar.a(bVar.i.e));
        PropertyModel propertyModelA = bVar2.a();
        bVar.h = propertyModelA;
        bVar.d.j(propertyModelA, 0, false);
    }
}
