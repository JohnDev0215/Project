package com.amazon.mp3.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.amazon.mp3.automotiveOS.R;

/* JADX INFO: loaded from: classes4.dex */
public class DialogUtil {
    private static DialogInterface.OnKeyListener sNoSearchOnKeyListener = new DialogInterface.OnKeyListener() { // from class: com.amazon.mp3.util.DialogUtil.1
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return keyEvent.getKeyCode() == 84;
        }
    };

    public static void installDefaultKeyListener(Dialog dialog) {
        dialog.setOnKeyListener(sNoSearchOnKeyListener);
    }

    public static void applyTheme(Dialog dialog) {
        if (dialog instanceof AlertDialog) {
            AlertDialog alertDialog = (AlertDialog) dialog;
            Context context = dialog.getContext();
            int[] iArr = {-1, -3, -2};
            for (int i = 0; i < 3; i++) {
                Button button = alertDialog.getButton(iArr[i]);
                if (button != null) {
                    button.setBackgroundResource(R.drawable.btn_secondary);
                    button.setTextColor(context.getResources().getColorStateList(R.color.primary_text_color));
                }
            }
        }
    }

    public static void hackAroundOtterDialogActivityBug(Activity a2) {
        Object parent;
        View viewFindViewById = a2.findViewById(android.R.id.content);
        if (viewFindViewById == null || (parent = viewFindViewById.getParent()) == null || !(parent instanceof View)) {
            return;
        }
        ((View) parent).setBackgroundDrawable(null);
    }
}
