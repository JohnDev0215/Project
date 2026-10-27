package com.amazon.mp3.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.mp3.util.AccessibilityUtil;

/* JADX INFO: loaded from: classes3.dex */
public class AccessibilityFocusDialogBuilder extends AlertDialog.Builder {
    private static String[] resArrayId = {"alerTitle", "message", "button1", "button2"};

    public AccessibilityFocusDialogBuilder(final Context context) {
        super(context);
    }

    @Override // android.app.AlertDialog.Builder
    public AlertDialog create() {
        AlertDialog alertDialogCreate = super.create();
        if (AccessibilityUtil.isAccessibilityEnabled(getContext())) {
            setAccessibilityFocusDelay(getContext(), alertDialogCreate);
        }
        return alertDialogCreate;
    }

    public static void setAccessibilityFocusDelay(@NonNull final Context context, @NonNull final Dialog dialog) {
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                AccessibilityFocusDialogBuilder.lambda$setAccessibilityFocusDelay$1(context, dialog, dialogInterface);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAccessibilityFocusDelay$1(final Context context, final Dialog dialog, DialogInterface dialogInterface) {
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.mp3.dialog.AccessibilityFocusDialogBuilder$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AccessibilityFocusDialogBuilder.lambda$setAccessibilityFocusDelay$0(context, dialog);
            }
        }, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setAccessibilityFocusDelay$0(Context context, Dialog dialog) {
        for (String str : resArrayId) {
            View viewFindViewById = dialog.findViewById(context.getResources().getIdentifier(str, "id", "android"));
            if (viewFindViewById != null) {
                viewFindViewById.sendAccessibilityEvent(32);
                return;
            }
        }
    }
}
