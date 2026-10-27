package org.chromium.chrome.browser.contacts_picker;

import android.accounts.Account;
import android.content.Context;
import android.text.TextUtils;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.fb0;
import defpackage.h62;
import defpackage.xx;
import defpackage.z7;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.browser_ui.contacts_picker.TopView;
import org.chromium.components.browser_ui.contacts_picker.f;
import org.chromium.components.signin.AccountManagerFacadeProvider;
import org.chromium.components.signin.b;
import org.chromium.components.signin.base.CoreAccountInfo;

/* JADX INFO: loaded from: classes.dex */
public class ChromePickerAdapter extends f implements h62.b {
    public final h62 v;
    public boolean w;
    public boolean x;

    public ChromePickerAdapter(Context context) {
        this.v = new h62(context, context.getResources().getDimensionPixelSize(R$dimen.contact_picker_icon_size), null);
    }

    @Override // org.chromium.components.browser_ui.contacts_picker.f
    public final void D(ArrayList<xx> arrayList) {
        this.x = true;
        boolean z = this.w;
        h62 h62Var = this.v;
        if (!z) {
            this.w = true;
            h62Var.b(this);
        }
        String str = this.n;
        fb0 fb0VarD = h62Var.d(str);
        String strB = fb0VarD.c;
        if (strB == null) {
            strB = fb0VarD.a;
        }
        if (TextUtils.isEmpty(strB) || TextUtils.equals(strB, str)) {
            z7 z7VarM = z7.m();
            Profile profileD = Profile.d();
            z7VarM.getClass();
            strB = CoreAccountInfo.b(z7.v(profileD).a(1));
        }
        xx xxVar = new xx("-1", strB, Collections.singletonList(str), null, null);
        xxVar.k = true;
        xxVar.l = fb0VarD.b;
        arrayList.add(0, xxVar);
    }

    @Override // org.chromium.components.browser_ui.contacts_picker.f
    public final String F() {
        z7 z7VarM = z7.m();
        Profile profileD = Profile.d();
        z7VarM.getClass();
        CoreAccountInfo coreAccountInfoA = z7.v(profileD).a(1);
        if (coreAccountInfoA != null) {
            return coreAccountInfoA.getEmail();
        }
        List<Account> listE = b.e(AccountManagerFacadeProvider.getInstance().b());
        Account account = listE.isEmpty() ? null : listE.get(0);
        if (account != null) {
            return account.name;
        }
        return null;
    }

    @Override // h62.b
    public final void n(String str) {
        if (this.x && TextUtils.equals(str, this.n)) {
            this.x = false;
            boolean z = this.w;
            h62 h62Var = this.v;
            if (z) {
                this.w = false;
                h62Var.e(this);
            }
            this.m.get(0).l = h62Var.d(this.n).b;
            TopView topView = this.j;
            if (topView != null) {
                topView.h.setText(NumberFormat.getInstance().format(this.m.size()));
            }
            o();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void s(RecyclerView recyclerView) {
        if (this.w) {
            return;
        }
        this.w = true;
        this.v.b(this);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.e
    public final void w(RecyclerView recyclerView) {
        if (this.w) {
            this.w = false;
            this.v.e(this);
        }
    }
}
