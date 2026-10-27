package com.android.car.developeroptions.accessibility;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.storage.StorageManager;
import android.text.BidiFormatter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class AccessibilityServiceWarning {
    public static Dialog createCapabilitiesDialog(Activity activity, AccessibilityServiceInfo accessibilityServiceInfo, DialogInterface.OnClickListener onClickListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(activity.getString(R.string.enable_service_title, new Object[]{getServiceName(activity, accessibilityServiceInfo)}));
        builder.setView(createEnableDialogContentView(activity, accessibilityServiceInfo));
        builder.setPositiveButton(android.R.string.ok, onClickListener);
        builder.setNegativeButton(android.R.string.cancel, onClickListener);
        AlertDialog alertDialogCreate = builder.create();
        $$Lambda$AccessibilityServiceWarning$AwxWq_8IQsLEpKKzyGrkVhRo86c __lambda_accessibilityservicewarning_awxwq_8iqslepkkzygrkvhro86c = new View.OnTouchListener() { // from class: com.android.car.developeroptions.accessibility.-$$Lambda$AccessibilityServiceWarning$AwxWq_8IQsLEpKKzyGrkVhRo86c
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return AccessibilityServiceWarning.lambda$createCapabilitiesDialog$0(view, motionEvent);
            }
        };
        Window window = alertDialogCreate.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.privateFlags |= 524288;
        window.setAttributes(attributes);
        alertDialogCreate.create();
        alertDialogCreate.getButton(-1).setOnTouchListener(__lambda_accessibilityservicewarning_awxwq_8iqslepkkzygrkvhro86c);
        alertDialogCreate.setCanceledOnTouchOutside(true);
        return alertDialogCreate;
    }

    static /* synthetic */ boolean lambda$createCapabilitiesDialog$0(View view, MotionEvent motionEvent) {
        if ((motionEvent.getFlags() & 1) == 0 && (motionEvent.getFlags() & 2) == 0) {
            return false;
        }
        if (motionEvent.getAction() == 1) {
            Toast.makeText(view.getContext(), R.string.touch_filtered_warning, 0).show();
        }
        return true;
    }

    private static boolean isFullDiskEncrypted() {
        return StorageManager.isNonDefaultBlockEncrypted();
    }

    private static View createEnableDialogContentView(Context context, AccessibilityServiceInfo accessibilityServiceInfo) {
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.enable_accessibility_service_dialog_content, (ViewGroup) null);
        TextView textView = (TextView) viewInflate.findViewById(R.id.encryption_warning);
        if (isFullDiskEncrypted()) {
            textView.setText(context.getString(R.string.enable_service_encryption_warning, getServiceName(context, accessibilityServiceInfo)));
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        ((TextView) viewInflate.findViewById(R.id.accessibility_service_warning)).setText(context.getString(R.string.accessibility_service_warning, getServiceName(context, accessibilityServiceInfo)));
        return viewInflate;
    }

    private static CharSequence getServiceName(Context context, AccessibilityServiceInfo accessibilityServiceInfo) {
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        return BidiFormatter.getInstance(locale).unicodeWrap(accessibilityServiceInfo.getResolveInfo().loadLabel(context.getPackageManager()));
    }
}
