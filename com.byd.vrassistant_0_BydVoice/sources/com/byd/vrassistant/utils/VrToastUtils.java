package com.byd.vrassistant.utils;

import android.os.Handler;
import android.text.TextUtils;
import android.widget.Toast;
import com.byd.vrassistant.BydVoiceApplication;

/* JADX INFO: loaded from: classes2.dex */
public class VrToastUtils {
    private static Toast toast;
    private static String toastStr;

    public static void show(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(String str, int i) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(str, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void show(int i) {
        try {
            if (TextUtils.isEmpty(BydVoiceApplication.getContext().getString(i))) {
                return;
            }
            initToast(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAtCenter(int i) {
        try {
            if (TextUtils.isEmpty(BydVoiceApplication.getContext().getString(i))) {
                return;
            }
            initToast(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showAtCenter(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            initToast(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void initToast(int i) {
        initToast(BydVoiceApplication.getContext().getString(i));
    }

    private static void initToast(String str) {
        initToast(str, 0);
    }

    private static void initToast(String str, int i) {
        if (str.equals(toastStr)) {
            return;
        }
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
        toastStr = str;
        new Handler().postDelayed(new Runnable() { // from class: com.byd.vrassistant.utils.VrToastUtils.1
            @Override // java.lang.Runnable
            public void run() {
                String unused = VrToastUtils.toastStr = null;
            }
        }, 2000L);
        Toast toast3 = new Toast(BydVoiceApplication.getContext());
        toast = toast3;
        if (i > 0) {
            toast3.setDuration(0);
        } else {
            toast3.setDuration(i);
        }
        toast.setText(str);
        toast.show();
    }
}
