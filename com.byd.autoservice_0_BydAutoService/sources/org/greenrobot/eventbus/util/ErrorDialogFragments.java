package org.greenrobot.eventbus.util;

import android.R;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class ErrorDialogFragments {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f4457a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Class<?> f4458b;

    @TargetApi(11)
    public static class Honeycomb extends DialogFragment implements DialogInterface.OnClickListener {
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i3) {
            ErrorDialogFragments.b(dialogInterface, i3, getActivity(), getArguments());
        }

        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            return ErrorDialogFragments.a(getActivity(), getArguments(), this);
        }
    }

    public static Dialog a(Context context, Bundle bundle, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(bundle.getString("de.greenrobot.eventbus.errordialog.title"));
        builder.setMessage(bundle.getString("de.greenrobot.eventbus.errordialog.message"));
        int i3 = f4457a;
        if (i3 != 0) {
            builder.setIcon(i3);
        }
        builder.setPositiveButton(R.string.ok, onClickListener);
        return builder.create();
    }

    public static void b(DialogInterface dialogInterface, int i3, Activity activity, Bundle bundle) {
        Class<?> cls = f4458b;
        if (cls != null) {
            try {
                cls.newInstance();
                throw null;
            } catch (Exception e3) {
                throw new RuntimeException("Event cannot be constructed", e3);
            }
        } else {
            if (!bundle.getBoolean("de.greenrobot.eventbus.errordialog.finish_after_dialog", false) || activity == null) {
                return;
            }
            activity.finish();
        }
    }
}
