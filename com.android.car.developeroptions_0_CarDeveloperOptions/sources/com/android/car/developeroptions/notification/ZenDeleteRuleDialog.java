package com.android.car.developeroptions.notification;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.BidiFormatter;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;

/* JADX INFO: loaded from: classes.dex */
public class ZenDeleteRuleDialog extends InstrumentedDialogFragment {
    protected static PositiveClickListener mPositiveClickListener;

    public interface PositiveClickListener {
        void onOk(String str);
    }

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 1266;
    }

    public static void show(Fragment fragment, String str, String str2, PositiveClickListener positiveClickListener) {
        BidiFormatter bidiFormatter = BidiFormatter.getInstance();
        Bundle bundle = new Bundle();
        bundle.putString("zen_rule_name", bidiFormatter.unicodeWrap(str));
        bundle.putString("zen_rule_id", str2);
        mPositiveClickListener = positiveClickListener;
        ZenDeleteRuleDialog zenDeleteRuleDialog = new ZenDeleteRuleDialog();
        zenDeleteRuleDialog.setArguments(bundle);
        zenDeleteRuleDialog.setTargetFragment(fragment, 0);
        zenDeleteRuleDialog.show(fragment.getFragmentManager(), "ZenDeleteRuleDialog");
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        final Bundle arguments = getArguments();
        String string = arguments.getString("zen_rule_name");
        final String string2 = arguments.getString("zen_rule_id");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(getString(R.string.zen_mode_delete_rule_confirmation, string));
        builder.setNegativeButton(R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.setPositiveButton(R.string.zen_mode_delete_rule_button, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.notification.ZenDeleteRuleDialog.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arguments != null) {
                    ZenDeleteRuleDialog.mPositiveClickListener.onOk(string2);
                }
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        View viewFindViewById = alertDialogCreate.findViewById(android.R.id.message);
        if (viewFindViewById != null) {
            viewFindViewById.setTextDirection(5);
        }
        return alertDialogCreate;
    }
}
