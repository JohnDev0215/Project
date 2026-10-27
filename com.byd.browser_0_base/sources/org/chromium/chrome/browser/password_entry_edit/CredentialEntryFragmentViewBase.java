package org.chromium.chrome.browser.password_entry_edit;

import J.N;
import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import defpackage.ag;
import defpackage.cg;
import defpackage.n3;
import defpackage.ww1;
import defpackage.zf;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$menu;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;

/* JADX INFO: loaded from: classes2.dex */
public abstract class CredentialEntryFragmentViewBase extends androidx.preference.d {
    public a h0;
    public b i0;

    public interface a {
    }

    public interface b {
        void a();

        void b(Context context);

        void c(String str);

        void d();

        void e();

        void f(Context context);

        void g(String str);
    }

    public final void M0() {
        D().finish();
    }

    @Override // androidx.fragment.app.Fragment
    public final void d0(Menu menu, MenuInflater menuInflater) {
        menu.clear();
        menuInflater.inflate(R$menu.credential_edit_action_bar_menu, menu);
    }

    @Override // androidx.fragment.app.Fragment
    public final void f0() {
        a aVar;
        this.H = true;
        if (!D().isFinishing() || (aVar = this.h0) == null) {
            return;
        }
        CredentialEditBridge credentialEditBridge = (CredentialEditBridge) ((org.chromium.chrome.browser.password_entry_edit.a) aVar).d;
        long j = credentialEditBridge.a;
        if (j != 0) {
            N.MtW4Yizq(j);
        }
        credentialEditBridge.a = 0L;
        CredentialEditBridge.c = null;
    }

    @Override // androidx.fragment.app.Fragment
    public final boolean k0(MenuItem menuItem) {
        if (this.i0 == null || menuItem.getItemId() != R$id.action_delete_saved_password) {
            return false;
        }
        this.i0.d();
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public final void m0() {
        ww1 ww1Var;
        Callback<Boolean> callback;
        this.H = true;
        a aVar = this.h0;
        if (aVar == null || (callback = (ww1Var = ((org.chromium.chrome.browser.password_entry_edit.a) aVar).b).c) == null) {
            return;
        }
        callback.onResult(Boolean.valueOf(n3.o(0)));
        ww1Var.c = null;
    }

    @Override // androidx.preference.d, androidx.fragment.app.Fragment
    public void o0() {
        super.o0();
        a aVar = this.h0;
        if (aVar != null) {
            org.chromium.chrome.browser.password_entry_edit.a aVar2 = (org.chromium.chrome.browser.password_entry_edit.a) aVar;
            PropertyModel propertyModel = aVar2.f;
            CredentialEntryFragmentViewBase credentialEntryFragmentViewBase = aVar2.a;
            if (credentialEntryFragmentViewBase instanceof CredentialEditFragmentView) {
                j.b(propertyModel, (CredentialEditFragmentView) credentialEntryFragmentViewBase, new cg(16));
            } else if (credentialEntryFragmentViewBase instanceof BlockedCredentialFragmentView) {
                j.b(propertyModel, (BlockedCredentialFragmentView) credentialEntryFragmentViewBase, new zf(19));
            } else if (credentialEntryFragmentViewBase instanceof FederatedCredentialFragmentView) {
                j.b(propertyModel, (FederatedCredentialFragmentView) credentialEntryFragmentViewBase, new ag(9));
            }
            aVar2.f.p(e.a, aVar2.c);
        }
    }
}
