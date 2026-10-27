package org.chromium.content.browser;

import J.N;
import android.content.Context;
import android.text.TextUtils;
import defpackage.BN;
import defpackage.InterfaceC1161mN;
import defpackage.RN;
import java.util.Objects;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ContactsDialogHost implements InterfaceC1161mN {
    public long a;
    public final WindowAndroid b;

    public ContactsDialogHost(WindowAndroid windowAndroid, long j) {
        this.a = j;
        this.b = windowAndroid;
    }

    public static ContactsDialogHost create(WindowAndroid windowAndroid, long j) {
        return new ContactsDialogHost(windowAndroid, j);
    }

    private void showDialog(final boolean z, final boolean z2, final boolean z3, final boolean z4, final boolean z5, final boolean z6, final String str) {
        if (this.b.Q().get() == null) {
            N.MOM50EIZ(this.a);
            return;
        }
        if (this.b.hasPermission("android.permission.READ_CONTACTS")) {
            BN.f((Context) this.b.Q().get(), this, z, z2, z3, z4, z5, z6, str);
            N.MOM50EIZ(this.a);
        } else if (this.b.canRequestPermission("android.permission.READ_CONTACTS")) {
            this.b.E(new String[]{"android.permission.READ_CONTACTS"}, new RN(this, z, z2, z3, z4, z5, z6, str) { // from class: Zy
                public final ContactsDialogHost a;
                public final boolean b;
                public final boolean c;
                public final boolean d;
                public final boolean e;
                public final boolean f;
                public final boolean g;
                public final String h;

                {
                    this.a = this;
                    this.b = z;
                    this.c = z2;
                    this.d = z3;
                    this.e = z4;
                    this.f = z5;
                    this.g = z6;
                    this.h = str;
                }

                @Override // defpackage.RN
                public void a(String[] strArr, int[] iArr) {
                    ContactsDialogHost contactsDialogHost = this.a;
                    boolean z7 = this.b;
                    boolean z8 = this.c;
                    boolean z9 = this.d;
                    boolean z10 = this.e;
                    boolean z11 = this.f;
                    boolean z12 = this.g;
                    String str2 = this.h;
                    Objects.requireNonNull(contactsDialogHost);
                    if (strArr.length != 1 || iArr.length != 1 || !TextUtils.equals(strArr[0], "android.permission.READ_CONTACTS") || iArr[0] != 0) {
                        N.MOM50EIZ(contactsDialogHost.a);
                    } else {
                        BN.f((Context) contactsDialogHost.b.Q().get(), contactsDialogHost, z7, z8, z9, z10, z11, z12, str2);
                        N.MOM50EIZ(contactsDialogHost.a);
                    }
                }
            });
        } else {
            N.MOM50EIZ(this.a);
        }
    }

    public void destroy() {
        this.a = 0L;
    }
}
