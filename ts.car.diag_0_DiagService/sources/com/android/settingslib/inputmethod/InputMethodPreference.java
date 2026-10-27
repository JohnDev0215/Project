package com.android.settingslib.inputmethod;

import android.R;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodInfo;
import android.widget.Toast;
import androidx.preference.Preference;
import com.android.internal.annotations.VisibleForTesting;
import com.android.settingslib.R$string;
import com.android.settingslib.RestrictedSwitchPreference;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class InputMethodPreference extends RestrictedSwitchPreference implements Preference.d, Preference.c {
    private static final String e0 = InputMethodPreference.class.getSimpleName();
    private final InputMethodInfo b0;
    private final a c0;
    private AlertDialog d0;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(InputMethodPreference inputMethodPreference);
    }

    @VisibleForTesting
    InputMethodPreference(Context context, InputMethodInfo inputMethodInfo, CharSequence charSequence, boolean z, a aVar) {
        super(context);
        this.d0 = null;
        r0(false);
        this.b0 = inputMethodInfo;
        this.c0 = aVar;
        M0("");
        L0("");
        l0(inputMethodInfo.getId());
        x0(charSequence);
        String settingsActivity = inputMethodInfo.getSettingsActivity();
        if (TextUtils.isEmpty(settingsActivity)) {
            k0(null);
        } else {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.setClassName(inputMethodInfo.getPackageName(), settingsActivity);
            k0(intent);
        }
        g.a(context);
        if (inputMethodInfo.isSystem()) {
            f.a(inputMethodInfo);
        }
        p0(this);
        o0(this);
    }

    private boolean R0() {
        return B() != 0;
    }

    private boolean S0() {
        return (j().getResources().getConfiguration().uiMode & 15) == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: T0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void U0(DialogInterface dialogInterface, int i) {
        d1(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: V0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void W0(DialogInterface dialogInterface, int i) {
        d1(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: X0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void Y0(DialogInterface dialogInterface, int i) {
        if (this.b0.getServiceInfo().directBootAware || S0()) {
            d1(true);
        } else {
            e1();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: Z0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void a1(DialogInterface dialogInterface, int i) {
        d1(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void c1(DialogInterface dialogInterface) {
        d1(false);
    }

    private void d1(boolean z) {
        super.F0(z);
        this.c0.a(this);
        I();
    }

    private void e1() {
        AlertDialog alertDialog = this.d0;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d0.dismiss();
        }
        Context contextJ = j();
        AlertDialog.Builder builder = new AlertDialog.Builder(contextJ);
        builder.setCancelable(true);
        builder.setMessage(contextJ.getText(R$string.direct_boot_unaware_dialog_message));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.c
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f1364c.U0(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.a
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f1362c.W0(dialogInterface, i);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.d0 = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void f1() {
        AlertDialog alertDialog = this.d0;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.d0.dismiss();
        }
        Context contextJ = j();
        AlertDialog.Builder builder = new AlertDialog.Builder(contextJ);
        builder.setCancelable(true);
        builder.setTitle(R.string.dialog_alert_title);
        builder.setMessage(contextJ.getString(R$string.ime_security_warning, this.b0.getServiceInfo().applicationInfo.loadLabel(contextJ.getPackageManager())));
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.e
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f1366c.Y0(dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.settingslib.inputmethod.b
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f1363c.a1(dialogInterface, i);
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.settingslib.inputmethod.d
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f1365c.c1(dialogInterface);
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.d0 = alertDialogCreate;
        alertDialogCreate.show();
    }

    @Override // androidx.preference.Preference.c
    public boolean a(Preference preference, Object obj) {
        if (!R0()) {
            return false;
        }
        if (E0()) {
            d1(false);
            return false;
        }
        if (!this.b0.isSystem()) {
            f1();
        } else if (this.b0.getServiceInfo().directBootAware || S0()) {
            d1(true);
        } else if (!S0()) {
            e1();
        }
        return false;
    }

    @Override // androidx.preference.Preference.d
    public boolean b(Preference preference) {
        if (R0()) {
            return true;
        }
        Context contextJ = j();
        try {
            Intent intentO = o();
            if (intentO != null) {
                contextJ.startActivity(intentO);
            }
        } catch (ActivityNotFoundException e) {
            Log.d(e0, "IME's Settings Activity Not Found", e);
            Toast.makeText(contextJ, contextJ.getString(R$string.failed_to_open_app_settings_toast, this.b0.loadLabel(contextJ.getPackageManager())), 1).show();
        }
        return true;
    }
}
