package com.byd.vrsettings.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.byd.vrsettings.BydVoiceSettingApplication;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtils {
    private static Toast toast;

    public static void show(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(context, str);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, String str, int i) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(context, str, i);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(Context context, int i) {
        try {
            if (TextUtils.isEmpty(BydVoiceSettingApplication.getContext().getString(i))) {
                return;
            }
            initToast(BydVoiceSettingApplication.getContext(), i);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAtCenter(Context context, int i) {
        try {
            if (TextUtils.isEmpty(context.getString(i))) {
                return;
            }
            initToast(context, i);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAtCenter(Context context, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(context, str);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAtCenter(Context context, int i, String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(context, i, str);
            toast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initToast(Context context, int i) {
        initToast(context, context.getString(i));
    }

    private static void initToast(Context context, String str) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast toast3 = new Toast(context);
        toast = toast3;
        toast3.setDuration(0);
        toast.setText(str);
    }

    private static void initToast(Context context, int i, String str) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast toast3 = new Toast(context);
        toast = toast3;
        toast3.setDuration(0);
        toast.setText(str);
    }

    private static void initToast(Context context, String str, int i) {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        Toast toast3 = new Toast(context);
        toast = toast3;
        toast3.setDuration(i);
        toast.setText(str);
    }
}
