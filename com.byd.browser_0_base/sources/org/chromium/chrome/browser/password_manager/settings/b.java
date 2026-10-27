package org.chromium.chrome.browser.password_manager.settings;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentManager;
import defpackage.ln2;
import defpackage.qm0;
import defpackage.r90;
import defpackage.rh3;
import defpackage.s90;
import defpackage.ua3;
import defpackage.zx1;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.password_manager.settings.ExportErrorDialogFragment;

/* JADX INFO: compiled from: ExportFlow.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b {
    public int a;
    public Uri b;
    public Integer c;
    public final s90 d = new s90();
    public ExportErrorDialogFragment.a e;
    public ExportWarningDialogFragment f;
    public c g;

    /* JADX INFO: compiled from: ExportFlow.java */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            if (i == -2) {
                b.this.a = 0;
            }
        }
    }

    /* JADX INFO: renamed from: org.chromium.chrome.browser.password_manager.settings.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: ExportFlow.java */
    public class DialogInterfaceOnClickListenerC0105b implements DialogInterface.OnClickListener {
        public final /* synthetic */ int e;

        public DialogInterfaceOnClickListenerC0105b(int i) {
            this.e = i;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            b bVar = b.this;
            if (i != -1) {
                if (i == -2) {
                    bVar.a = 0;
                    return;
                }
                return;
            }
            int i2 = R$string.password_settings_export_learn_google_drive;
            int i3 = this.e;
            if (i3 == i2) {
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://support.google.com/drive/answer/2424384"));
                intent.setPackage(PasswordSettings.this.D().getPackageName());
                PasswordSettings.this.D().startActivity(intent);
            } else if (i3 == R$string.try_again) {
                bVar.a = 1;
                ExportWarningDialogFragment exportWarningDialogFragment = new ExportWarningDialogFragment();
                bVar.f = exportWarningDialogFragment;
                exportWarningDialogFragment.p0 = new org.chromium.chrome.browser.password_manager.settings.a(bVar);
                exportWarningDialogFragment.M0(PasswordSettings.this.v, null);
            }
        }
    }

    /* JADX INFO: compiled from: ExportFlow.java */
    public interface c {
    }

    public final void a() {
        ExportErrorDialogFragment exportErrorDialogFragment = new ExportErrorDialogFragment();
        ExportErrorDialogFragment.a aVar = this.e;
        int i = aVar.a;
        exportErrorDialogFragment.q0 = aVar;
        this.e = null;
        exportErrorDialogFragment.setExportErrorHandler(new DialogInterfaceOnClickListenerC0105b(i));
        exportErrorDialogFragment.M0(PasswordSettings.this.v, null);
    }

    public final void b() {
        if (this.a != 2) {
            return;
        }
        Integer num = this.c;
        s90 s90Var = this.d;
        if (num == null) {
            ProgressBarDialogFragment progressBarDialogFragment = new ProgressBarDialogFragment();
            progressBarDialogFragment.setCancelProgressHandler(new a());
            FragmentManager fragmentManager = PasswordSettings.this.v;
            s90Var.a = progressBarDialogFragment;
            progressBarDialogFragment.M0(fragmentManager, null);
            ln2 ln2Var = new ln2(new qm0(21, s90Var));
            s90Var.c = ln2Var;
            ua3 ua3Var = s90Var.b;
            ua3Var.a.postDelayed(ln2Var, ua3Var.b);
            return;
        }
        s90Var.d = new zx1(1, this);
        ln2 ln2Var2 = s90Var.c;
        if (ln2Var2 != null) {
            ln2Var2.run();
            return;
        }
        r90 r90Var = s90Var.a;
        if (r90Var != null) {
            r90Var.I0(false, false);
        }
        Runnable runnable = s90Var.d;
        if (runnable != null) {
            PostTask.c(rh3.a, runnable);
        }
        s90Var.a = null;
        s90Var.d = null;
        s90Var.c = null;
    }
}
