package org.chromium.chrome.browser.password_entry_edit;

import defpackage.ex;
import defpackage.fu0;
import defpackage.ww1;
import java.lang.ref.WeakReference;
import org.chromium.chrome.browser.password_entry_edit.CredentialEntryFragmentViewBase;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: compiled from: CredentialEditCoordinator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a implements CredentialEntryFragmentViewBase.a {
    public final CredentialEntryFragmentViewBase a;
    public final ww1 b;
    public final d c;
    public final b d;
    public final fu0 e;
    public PropertyModel f;

    /* JADX INFO: renamed from: org.chromium.chrome.browser.password_entry_edit.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CredentialEditCoordinator.java */
    public interface InterfaceC0103a {
    }

    /* JADX INFO: compiled from: CredentialEditCoordinator.java */
    public interface b {
    }

    public a(CredentialEntryFragmentViewBase credentialEntryFragmentViewBase, CredentialEditBridge credentialEditBridge, CredentialEditBridge credentialEditBridge2, fu0 fu0Var) {
        this.a = credentialEntryFragmentViewBase;
        ww1 ww1Var = new ww1(credentialEntryFragmentViewBase.D(), credentialEntryFragmentViewBase.M());
        this.b = ww1Var;
        this.c = new d(ww1Var, new ex(new WeakReference(credentialEntryFragmentViewBase.K())), credentialEditBridge2, credentialEntryFragmentViewBase instanceof BlockedCredentialFragmentView);
        this.d = credentialEditBridge;
        credentialEntryFragmentViewBase.h0 = this;
        this.e = fu0Var;
    }
}
