package org.chromium.content.browser;

import J.N;
import android.text.TextUtils;
import defpackage.hy;
import defpackage.n3;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content.browser.ContactsDialogHost;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.permissions.PermissionCallback;

/* JADX INFO: loaded from: classes3.dex */
public class ContactsDialogHost implements hy {
    public long a;
    public final WebContents b;

    public ContactsDialogHost(long j, WebContents webContents) {
        this.a = j;
        this.b = webContents;
    }

    @CalledByNative
    public static ContactsDialogHost create(WebContents webContents, long j) {
        return new ContactsDialogHost(j, webContents);
    }

    public final void a(int i, ArrayList arrayList, int i2, int i3) {
        long j = this.a;
        if (j == 0) {
            return;
        }
        if (i == 0) {
            N.MwwTaBAE(j, 0, i3);
            return;
        }
        if (i != 1) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            hy.a aVar = (hy.a) it.next();
            long j2 = this.a;
            List<String> list = aVar.a;
            String[] strArr = list != null ? (String[]) list.toArray(new String[list.size()]) : null;
            List<String> list2 = aVar.b;
            String[] strArr2 = list2 != null ? (String[]) list2.toArray(new String[list2.size()]) : null;
            List<String> list3 = aVar.c;
            String[] strArr3 = list3 != null ? (String[]) list3.toArray(new String[list3.size()]) : null;
            ArrayList arrayList2 = aVar.d;
            ByteBuffer[] byteBufferArr = arrayList2 != null ? (ByteBuffer[]) arrayList2.toArray(new ByteBuffer[arrayList2.size()]) : null;
            ArrayList arrayList3 = aVar.e;
            N.MS9BZrW0(j2, strArr, strArr2, strArr3, byteBufferArr, arrayList3 != null ? (ByteBuffer[]) arrayList3.toArray(new ByteBuffer[arrayList3.size()]) : null);
        }
        N.MwwTaBAE(this.a, i2, i3);
    }

    @CalledByNative
    public void destroy() {
        this.a = 0L;
    }

    @CalledByNative
    public final void showDialog(final boolean z, final boolean z2, final boolean z3, final boolean z4, final boolean z5, final boolean z6, final String str) {
        WindowAndroid windowAndroidV = this.b.V();
        if (windowAndroidV.k().get() == null) {
            N.MOM50EIZ(this.a);
            return;
        }
        if (windowAndroidV.hasPermission("android.permission.READ_CONTACTS")) {
            if (n3.n1(this.b, this, z, z2, z3, z4, z5, z6, str)) {
                return;
            }
            N.MOM50EIZ(this.a);
        } else if (windowAndroidV.canRequestPermission("android.permission.READ_CONTACTS")) {
            windowAndroidV.b(new String[]{"android.permission.READ_CONTACTS"}, new PermissionCallback() { // from class: gy
                @Override // org.chromium.ui.permissions.PermissionCallback
                public final void s(int[] iArr, String[] strArr) {
                    boolean z7 = z;
                    boolean z8 = z2;
                    boolean z9 = z3;
                    boolean z10 = z4;
                    boolean z11 = z5;
                    boolean z12 = z6;
                    String str2 = str;
                    ContactsDialogHost contactsDialogHost = this.e;
                    contactsDialogHost.getClass();
                    if (strArr.length != 1 || iArr.length != 1 || !TextUtils.equals(strArr[0], "android.permission.READ_CONTACTS") || iArr[0] != 0) {
                        N.MOM50EIZ(contactsDialogHost.a);
                    } else {
                        if (n3.n1(contactsDialogHost.b, contactsDialogHost, z7, z8, z9, z10, z11, z12, str2)) {
                            return;
                        }
                        N.MOM50EIZ(contactsDialogHost.a);
                    }
                }
            });
        } else {
            N.MOM50EIZ(this.a);
        }
    }
}
