package org.chromium.chrome.browser.password_entry_edit;

import J.N;
import android.app.Dialog;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import defpackage.C0175b;
import defpackage.C0177c;
import defpackage.cx;
import defpackage.dx;
import defpackage.eu1;
import defpackage.ex;
import defpackage.lj1;
import defpackage.n3;
import defpackage.oj2;
import defpackage.ww1;
import defpackage.xm1;
import defpackage.z7;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.chrome.browser.password_entry_edit.a;
import org.chromium.ui.base.Clipboard;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: CredentialEditMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d implements CredentialEntryFragmentViewBase.b {
    public final ww1 a;
    public final ex b;
    public final a.InterfaceC0103a c;
    public final boolean d;
    public PropertyModel e;
    public String f;
    public String g;
    public boolean h;
    public HashSet i;

    public d(ww1 ww1Var, ex exVar, CredentialEditBridge credentialEditBridge, boolean z) {
        this.a = ww1Var;
        this.b = exVar;
        this.c = credentialEditBridge;
        this.d = z;
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void a() {
        PropertyModel propertyModel = this.e;
        PropertyModel.n nVar = e.e;
        if (propertyModel.k(nVar)) {
            oj2.U(3, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
            this.e.l(nVar, false);
            return;
        }
        lj1 lj1Var = new lj1(12, this);
        ww1 ww1Var = this.a;
        if (((KeyguardManager) ww1Var.a.getSystemService("keyguard")).isKeyguardSecure()) {
            ww1Var.a(lj1Var, 0);
        } else {
            Toast.c(ww1Var.a, R$string.password_entry_view_set_screen_lock, 1).e();
        }
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void b(Context context) {
        oj2.U(1, 8, ((String) this.e.j(e.h)).isEmpty() ? "PasswordManager.CredentialEntryActions.SavedPassword" : "PasswordManager.CredentialEntryActions.FederatedCredential");
        Clipboard.getInstance().h("username", z7.q((String) this.e.j(e.c)));
        Toast.c(context, R$string.password_entry_viewer_username_copied_into_clipboard, 0).e();
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void c(String str) {
        this.e.p(e.c, str);
        boolean z = !this.f.equals(str) && this.i.contains(str);
        this.e.l(e.d, z);
        if (z) {
            oj2.U(1, 2, "PasswordManager.CredentialEditError");
        }
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void d() {
        if (this.d) {
            h();
            N.MAcoX59m(((CredentialEditBridge) this.c).a);
            return;
        }
        ex exVar = this.b;
        WeakReference<Context> weakReference = exVar.e;
        Resources resources = weakReference.get() == null ? null : weakReference.get().getResources();
        if (resources == null) {
            return;
        }
        String string = resources.getString(R$string.password_entry_edit_delete_credential_dialog_title);
        String string2 = resources.getString(this.h ? R$string.password_check_delete_credential_dialog_body : R$string.password_entry_edit_deletion_dialog_body, this.e.j(e.b));
        int i = R$string.password_entry_edit_delete_credential_dialog_confirm;
        eu1 eu1Var = new eu1(3, this);
        Context context = exVar.e.get();
        if (context == null) {
            return;
        }
        exVar.f = eu1Var;
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.dialog_delete_passwd, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.tv_title);
        TextView textView2 = (TextView) viewInflate.findViewById(R$id.tv_message);
        Button button = (Button) viewInflate.findViewById(R$id.btn_sure);
        Button button2 = (Button) viewInflate.findViewById(R$id.btn_cancel);
        textView.setText(string);
        textView2.setText(string2);
        button.setText(i);
        button2.setText(R$string.cancel);
        LinearLayout linearLayout = (LinearLayout) viewInflate.findViewById(R$id.ll_sure_cancel);
        if (n3.Q0()) {
            linearLayout.setLayoutDirection(1);
        } else {
            linearLayout.setLayoutDirection(0);
        }
        Dialog dialog = new Dialog(context, R$style.Dialog_Style);
        dialog.setContentView(viewInflate);
        dialog.setCancelable(true);
        Window window = dialog.getWindow();
        window.setGravity(17);
        WindowManager.LayoutParams attributes = window.getAttributes();
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        attributes.width = (Math.min(displayMetrics.widthPixels, displayMetrics.heightPixels) * 4) / 5;
        attributes.height = -2;
        window.setAttributes(attributes);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        button.setOnClickListener(new cx(exVar, dialog));
        button2.setOnClickListener(new dx(dialog));
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void e() {
        PropertyModel propertyModel = this.e;
        PropertyModel.r<String> rVar = e.c;
        boolean z = !((String) propertyModel.j(rVar)).equals(this.f);
        PropertyModel propertyModel2 = this.e;
        PropertyModel.r<String> rVar2 = e.f;
        boolean z2 = !((String) propertyModel2.j(rVar2)).equals(this.g);
        if (z && z2) {
            oj2.U(7, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
        } else if (z) {
            oj2.U(5, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
        } else if (z2) {
            oj2.U(6, 8, "PasswordManager.CredentialEntryActions.SavedPassword");
        }
        String strQ = z7.q((String) this.e.j(rVar));
        String str = (String) this.e.j(rVar2);
        CredentialEditBridge credentialEditBridge = (CredentialEditBridge) this.c;
        if (credentialEditBridge.a == 0) {
            return;
        }
        N.MXvicdfl(credentialEditBridge.a, C0175b.j("b%:&", C0177c.a(strQ)), str);
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void f(Context context) {
        xm1 xm1Var = new xm1(this, 7, context);
        ww1 ww1Var = this.a;
        if (((KeyguardManager) ww1Var.a.getSystemService("keyguard")).isKeyguardSecure()) {
            ww1Var.a(xm1Var, 2);
        } else {
            Toast.c(ww1Var.a, R$string.password_entry_copy_set_screen_lock, 1).e();
        }
    }

    @Override // org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase.b
    public final void g(String str) {
        this.e.p(e.f, str);
        this.e.l(e.g, str.isEmpty());
        if (str.isEmpty()) {
            oj2.U(0, 2, "PasswordManager.CredentialEditError");
        }
    }

    public final void h() {
        oj2.U(0, 8, this.d ? "PasswordManager.CredentialEntryActions.BlockedCredential" : !((String) this.e.j(e.h)).isEmpty() ? "PasswordManager.CredentialEntryActions.FederatedCredential" : "PasswordManager.CredentialEntryActions.SavedPassword");
    }
}
