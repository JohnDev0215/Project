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
import defpackage.AbstractC0317Rm;
import defpackage.AbstractC0371Um;
import defpackage.AbstractC0425Xm;
import defpackage.AbstractC0538bE;
import defpackage.AbstractC0566bn;
import defpackage.AbstractC1711wO;
import defpackage.BO;
import defpackage.C1355py;
import defpackage.C1546tO;
import defpackage.Is;
import defpackage.JO;
import org.chromium.base.task.PostTask;
import org.chromium.content_public.browser.WebContents;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class NfcSystemLevelSetting {
    public static boolean isNfcAccessPossible() {
        Context context = Is.a;
        return context.checkPermission("android.permission.NFC", Process.myPid(), Process.myUid()) == 0 && NfcAdapter.getDefaultAdapter(context) != null;
    }

    public static boolean isNfcSystemLevelSettingEnabled() {
        if (isNfcAccessPossible()) {
            return NfcAdapter.getDefaultAdapter(Is.a).isEnabled();
        }
        return false;
    }

    public static void promptToEnableNfcSystemLevelSetting(WebContents webContents, final long j) {
        WindowAndroid windowAndroidH = webContents.H();
        if (windowAndroidH == null) {
            PostTask.a(AbstractC0538bE.a, new Runnable(j) { // from class: qy
                public final long s;

                {
                    this.s = j;
                }

                @Override // java.lang.Runnable
                public void run() {
                    N.M4qSX4lZ(this.s);
                }
            }, 0L);
            return;
        }
        C1355py c1355py = new C1355py();
        final Runnable runnable = new Runnable(j) { // from class: ry
            public final long s;

            {
                this.s = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                N.M4qSX4lZ(this.s);
            }
        };
        C1546tO c1546tOT = windowAndroidH.T();
        if (c1546tOT == null) {
            PostTask.a(AbstractC0538bE.a, new Runnable(runnable) { // from class: ny
                public final Runnable s;

                {
                    this.s = runnable;
                }

                @Override // java.lang.Runnable
                public void run() {
                    this.s.run();
                }
            }, 0L);
            return;
        }
        Activity activity = (Activity) windowAndroidH.Q().get();
        View viewInflate = LayoutInflater.from(activity).inflate(AbstractC0425Xm.N0, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(AbstractC0371Um.H4);
        textView.setText(AbstractC0566bn.W1);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(AbstractC0317Rm.b2, 0, 0, 0);
        Resources resources = activity.getResources();
        BO bo = new BO(AbstractC1711wO.q);
        bo.d(AbstractC1711wO.a, c1355py);
        bo.d(AbstractC1711wO.f, viewInflate);
        bo.c(AbstractC1711wO.g, resources, AbstractC0566bn.X1);
        bo.c(AbstractC1711wO.j, resources, AbstractC0566bn.t0);
        bo.c(AbstractC1711wO.b, resources, AbstractC0566bn.W1);
        bo.b(AbstractC1711wO.n, true);
        JO joA = bo.a();
        c1355py.b = windowAndroidH;
        c1355py.c = runnable;
        c1355py.a = c1546tOT;
        c1546tOT.e(joA, 1, false);
    }
}
