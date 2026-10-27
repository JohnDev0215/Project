package com.android.car.developeroptions.applications.specialaccess.zenaccess;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class ScaryWarningDialogFragment extends InstrumentedDialogFragment {
    static /* synthetic */ void lambda$onCreateDialog$1(DialogInterface dialogInterface, int i) {
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 554;
    }

    public ScaryWarningDialogFragment setPkgInfo(String str, CharSequence charSequence) {
        Bundle bundle = new Bundle();
        bundle.putString("p", str);
        if (!TextUtils.isEmpty(charSequence)) {
            str = charSequence.toString();
        }
        bundle.putString("l", str);
        setArguments(bundle);
        return this;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        final String string = arguments.getString("p");
        String string2 = getResources().getString(R.string.zen_access_warning_dialog_title, arguments.getString("l"));
        String string3 = getResources().getString(R.string.zen_access_warning_dialog_summary);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(string3);
        builder.setTitle(string2);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.specialaccess.zenaccess.-$$Lambda$ScaryWarningDialogFragment$A0F4l5lrpAKWMMnGuLc9cmtbNEA
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreateDialog$0$ScaryWarningDialogFragment(string, dialogInterface, i);
            }
        });
        builder.setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.applications.specialaccess.zenaccess.-$$Lambda$ScaryWarningDialogFragment$buqrqpae6qxw_9bYYDwH5g02UHE
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                ScaryWarningDialogFragment.lambda$onCreateDialog$1(dialogInterface, i);
            }
        });
        return builder.create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$onCreateDialog$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$onCreateDialog$0$ScaryWarningDialogFragment(String str, DialogInterface dialogInterface, int i) {
        ZenAccessController.setAccess(getContext(), str, true);
    }
}
