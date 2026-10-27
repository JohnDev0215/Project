package org.chromium.chrome.browser.device_dialog;

import J.N;
import android.app.Activity;
import android.text.SpannableString;
import android.text.TextUtils;
import defpackage.iq2;
import defpackage.j90;
import defpackage.qp;
import defpackage.un1;
import defpackage.xr;
import defpackage.z7;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.components.omnibox.OmniboxUrlEmphasizer;
import org.chromium.components.permissions.a;
import org.chromium.components.permissions.b;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes.dex */
public class UsbChooserDialog implements b.c {
    public b e;
    public long f;

    public UsbChooserDialog(long j, Profile profile) {
        this.f = j;
    }

    @CalledByNative
    public static UsbChooserDialog create(WindowAndroid windowAndroid, String str, int i, Profile profile, long j) {
        Activity activity = (Activity) windowAndroid.k().get();
        if (activity == null) {
            return null;
        }
        UsbChooserDialog usbChooserDialog = new UsbChooserDialog(j, profile);
        SpannableString spannableString = new SpannableString(str);
        boolean z = !z7.I(activity);
        qp qpVar = new qp(profile);
        OmniboxUrlEmphasizer.a(spannableString, activity, qpVar, i, z, true);
        qpVar.a();
        SpannableString spannableString2 = new SpannableString(activity.getString(R$string.usb_chooser_dialog_prompt, str));
        TextUtils.copySpansFrom(spannableString, 0, spannableString.length(), Object.class, spannableString2, spannableString2.toString().indexOf(str));
        String string = activity.getString(R$string.usb_chooser_dialog_no_devices_found_prompt);
        SpannableString spannableStringA = iq2.a(activity.getString(R$string.usb_chooser_dialog_footnote_text), new iq2.a(new un1(activity, new xr(14, usbChooserDialog)), "<link>", "</link>"));
        usbChooserDialog.e = new b(activity, activity.getWindow(), usbChooserDialog, new b.C0140b(spannableString2, "", string, spannableStringA, spannableStringA, spannableStringA, activity.getString(R$string.usb_chooser_dialog_connect_button_text)));
        return usbChooserDialog;
    }

    @Override // org.chromium.components.permissions.b.c
    public final void a(String str) {
        if (this.f != 0) {
            if (str.isEmpty()) {
                N.MyQOumx4(this.f);
            } else {
                N.M8m3iwzV(this.f, str);
            }
        }
    }

    @CalledByNative
    public void addDevice(String str, String str2) {
        b bVar = this.e;
        bVar.f.setVisibility(8);
        bVar.k.a(null, str, str2, null);
        bVar.b(2);
    }

    @CalledByNative
    public final void closeDialog() {
        this.f = 0L;
        this.e.c.dismiss();
    }

    @CalledByNative
    public final void removeDevice(String str) {
        b bVar = this.e;
        a aVar = bVar.k;
        j90 j90Var = (j90) aVar.k.remove(str);
        if (j90Var != null) {
            int position = aVar.getPosition(j90Var);
            int i = aVar.i;
            if (position == i) {
                aVar.e(-1);
            } else if (position < i) {
                aVar.i = i - 1;
            }
            aVar.d(j90Var.b);
            aVar.remove(j90Var);
        }
        bVar.b(3);
    }

    @CalledByNative
    public final void setIdleState() {
        b bVar = this.e;
        bVar.f.setVisibility(8);
        bVar.b(3);
    }
}
