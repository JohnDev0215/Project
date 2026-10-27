package org.chromium.components.permissions.nfc;

import J.N;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.os.Process;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import defpackage.bi1;
import defpackage.di1;
import defpackage.kn1;
import defpackage.rh3;
import defpackage.uq0;
import defpackage.xz;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.base.task.PostTask;
import org.chromium.components.permissions.R$drawable;
import org.chromium.components.permissions.R$id;
import org.chromium.components.permissions.R$layout;
import org.chromium.components.permissions.R$string;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;

/* JADX INFO: loaded from: classes3.dex */
public class NfcSystemLevelSetting {
    @CalledByNative
    public static boolean isNfcAccessPossible() {
        Context context = xz.a;
        return context.checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0 && NfcAdapter.getDefaultAdapter(context) != null;
    }

    @CalledByNative
    public static boolean isNfcSystemLevelSettingEnabled() {
        if (isNfcAccessPossible()) {
            return NfcAdapter.getDefaultAdapter(xz.a).isEnabled();
        }
        return false;
    }

    @CalledByNative
    public static void promptToEnableNfcSystemLevelSetting(WebContents webContents, final long j) {
        WindowAndroid windowAndroidV = webContents.V();
        if (windowAndroidV == null) {
            PostTask.c(rh3.a, new Runnable() { // from class: ln1
                @Override // java.lang.Runnable
                public final void run() {
                    N.M4qSX4lZ(j);
                }
            });
            return;
        }
        kn1 kn1Var = new kn1();
        final int i = 1;
        final uq0 uq0Var = new uq0(i, j);
        bi1 bi1VarN = windowAndroidV.n();
        if (bi1VarN == null) {
            PostTask.c(rh3.a, new Runnable() { // from class: uq3
                @Override // java.lang.Runnable
                public final void run() {
                    int i2 = i;
                    Runnable runnable = uq0Var;
                    switch (i2) {
                        case 0:
                            N.MxULk9PS(0, 61);
                            if (runnable != null) {
                                runnable.run();
                            }
                            break;
                        default:
                            runnable.run();
                            break;
                    }
                }
            });
            return;
        }
        Activity activity = (Activity) windowAndroidV.k().get();
        View viewInflate = LayoutInflater.from(activity).inflate(R$layout.permission_dialog, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R$id.text);
        int i2 = R$string.nfc_disabled_on_device_message;
        textView.setText(i2);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R$drawable.gm_filled_nfc_24, 0, 0, 0);
        Resources resources = activity.getResources();
        PropertyModel.b bVar = new PropertyModel.b(di1.B);
        bVar.e(di1.a, kn1Var);
        bVar.e(di1.h, viewInflate);
        bVar.d(di1.j, resources, R$string.nfc_prompt_turn_on);
        bVar.d(di1.m, resources, R$string.cancel);
        bVar.d(di1.b, resources, i2);
        bVar.b(di1.r, true);
        PropertyModel propertyModelA = bVar.a();
        kn1Var.f = windowAndroidV;
        kn1Var.g = uq0Var;
        kn1Var.e = bi1VarN;
        bi1VarN.j(propertyModelA, 0, false);
    }
}
