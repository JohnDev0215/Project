package com.android.inputmethod.latin.utils;

import android.content.Context;
import android.provider.Settings;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public final class UncachedInputMethodManagerUtils {
    public static boolean isThisImeEnabled(final Context context, final InputMethodManager imm) {
        String packageName = context.getPackageName();
        Iterator<InputMethodInfo> it = imm.getEnabledInputMethodList().iterator();
        while (it.hasNext()) {
            if (packageName.equals(it.next().getPackageName())) {
                return true;
            }
        }
        return false;
    }

    public static boolean isThisImeCurrent(final Context context, final InputMethodManager imm) {
        InputMethodInfo inputMethodInfoOf = getInputMethodInfoOf(context.getPackageName(), imm);
        return inputMethodInfoOf != null && inputMethodInfoOf.getId().equals(Settings.Secure.getString(context.getContentResolver(), "default_input_method"));
    }

    public static InputMethodInfo getInputMethodInfoOf(final String packageName, final InputMethodManager imm) {
        for (InputMethodInfo inputMethodInfo : imm.getInputMethodList()) {
            if (packageName.equals(inputMethodInfo.getPackageName())) {
                return inputMethodInfo;
            }
        }
        return null;
    }
}
