package com.byd.systemsettings.system.utils;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import m4.d;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class TextLengthUtil {
    public static final int AP_PWD_MAX_LENGTH = 63;
    public static final int BT_NAME_MAX_LENGTH = 10;
    public static final int BT_NAME_MAX_LENGTH_EN = 15;
    public static final String TAG = "TextLengthUtil";
    public static final float TEXT_LEFT_MAX_RATE = 0.538f;
    public static final int WIFI_NAME_MAX_LENGTH = 10;
    public static final int WIFI_NAME_MAX_LENGTH_EN = 15;

    /* JADX INFO: renamed from: com.byd.systemsettings.system.utils.TextLengthUtil$1, reason: invalid class name */
    public class AnonymousClass1 implements ViewTreeObserver.OnGlobalLayoutListener {
        public final /* synthetic */ TextView val$tv;

        public AnonymousClass1(TextView textView) {
            this.val$tv = textView;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            a.a(TextLengthUtil.TAG, "---checkAndChangeWidthOnGlobalLayout--onGlobalLayout--");
            TextLengthUtil.checkAndChangeWidth(this.val$tv, new ViewTreeObserver.OnGlobalLayoutListener() { // from class: s4.a
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public final void onGlobalLayout() {
                    this.f7901d.onGlobalLayout();
                }
            });
        }
    }

    public static void checkAndChangeWidth(TextView textView, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (textView == null) {
            return;
        }
        a.a(TAG, "---checkAndChangeWidth---");
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        ViewGroup viewGroup = (ViewGroup) textView.getParent();
        int measuredWidth = textView.getMeasuredWidth();
        float measuredWidth2 = viewGroup.getMeasuredWidth();
        if (measuredWidth / (1.0f * measuredWidth2) < 0.538f) {
            textView.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
            return;
        }
        layoutParams.width = (int) (measuredWidth2 * 0.538f);
        textView.setSingleLine(false);
        textView.setLayoutParams(layoutParams);
    }

    public static void checkAndChangeWidthOnGlobalLayout(TextView textView) {
        if (textView == null) {
            return;
        }
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new AnonymousClass1(textView));
    }

    public static String getBtName(String str) {
        return getName(str, 15, 10);
    }

    public static String getHotspotPwd(String str) {
        return getName(str, 63, 63);
    }

    private static String getName(String str, int i8, int i9) {
        if (TextUtils.isEmpty(str) || str.length() <= i9) {
            return str;
        }
        a.a(TAG, "---getName--text.length: " + str.length());
        int iL = d.l(str);
        if (iL >= 0 && iL < i8) {
            int length = str.length();
            if (length > i9) {
                length = i9;
            }
            int i10 = i9 - 2;
            i8 = d.a(str.substring(0, length)) < i10 ? (iL <= i9 || iL >= i8) ? i9 : iL + 1 : i10;
        }
        a.a(TAG, "---getName--1--maxLength: " + i8 + ", index: " + iL);
        if (i8 >= str.length()) {
            return str;
        }
        return str.substring(0, i8) + "...";
    }

    public static String getWifiName(String str) {
        return getName(str, 15, 10);
    }
}
