package com.byd.systemsettings.ui.dialog;

import android.R;
import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;
import x3.a;

/* JADX INFO: loaded from: classes2.dex */
public class PopupWindowUtil {
    private static final String TAG = "PopupWindowUtil";

    public interface OnClickedCallback {
        void onClicked(boolean z7);
    }

    public static void backgroundAlpha(Activity activity, View view, int i8, float f8) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        a.c(TAG, "---backgroundAlpha---lp.width = " + attributes.width + ", lp.height = " + attributes.height);
        attributes.alpha = f8;
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        int i9 = iArr[1] + i8;
        a.c(TAG, "---backgroundAlpha---vh = " + i8 + ", h = " + i9);
        attributes.verticalMargin = (float) i9;
        activity.getWindow().setAttributes(attributes);
    }

    private static PopupWindow createPopuWindow(final Activity activity, View view, int i8, int i9, final boolean z7) {
        PopupWindow popupWindow = new PopupWindow(view, i8, i9);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(activity.getWindow(), 0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.PopupWindowUtil.4
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (z7) {
                    return;
                }
                PopupWindowUtil.backgroundAlpha(activity.getWindow(), 1.0f);
            }
        });
        view.measure(0, 0);
        return popupWindow;
    }

    public static PopupWindow showAsDropDown(final Activity activity, View view, View view2, int i8, int i9, final boolean z7, boolean z8, final PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = new PopupWindow(view2, i8, i9);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view2.measure(0, 0);
        backgroundAlpha(activity.getWindow(), 0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.PopupWindowUtil.1
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (!z7) {
                    PopupWindowUtil.backgroundAlpha(activity.getWindow(), 1.0f);
                }
                PopupWindow.OnDismissListener onDismissListener2 = onDismissListener;
                if (onDismissListener2 != null) {
                    onDismissListener2.onDismiss();
                }
            }
        });
        int[] iArr = new int[2];
        popupWindow.showAsDropDown(view, iArr[0], iArr[1], !z8 ? 8388613 : 7);
        return popupWindow;
    }

    public static PopupWindow showAtBottom(Activity activity, View view, int i8, int i9, boolean z7) {
        View childAt = ((ViewGroup) activity.findViewById(R.id.content)).getChildAt(0);
        int[] iArr = new int[2];
        PopupWindow popupWindowCreatePopuWindow = createPopuWindow(activity, view, i8, i9, z7);
        popupWindowCreatePopuWindow.showAtLocation(childAt, 80, iArr[0], iArr[1]);
        return popupWindowCreatePopuWindow;
    }

    public static void backgroundAlpha(Window window, float f8) {
        if (window == null) {
            return;
        }
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = f8;
        window.setAttributes(attributes);
    }

    public static PopupWindow showAsDropDown(final Window window, View view, View view2, int i8, int i9, final boolean z7, boolean z8, final PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = new PopupWindow(view2, i8, i9);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view2.measure(0, 0);
        backgroundAlpha(window, 0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.PopupWindowUtil.2
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                if (!z7) {
                    PopupWindowUtil.backgroundAlpha(window, 1.0f);
                }
                PopupWindow.OnDismissListener onDismissListener2 = onDismissListener;
                if (onDismissListener2 != null) {
                    onDismissListener2.onDismiss();
                }
            }
        });
        int[] iArr = new int[2];
        popupWindow.showAsDropDown(view, iArr[0], iArr[1], !z8 ? 8388613 : 7);
        return popupWindow;
    }

    public static PopupWindow showAsDropDown(View view, View view2, int i8, int i9, boolean z7, final PopupWindow.OnDismissListener onDismissListener) {
        PopupWindow popupWindow = new PopupWindow(view2, i8, i9);
        popupWindow.setInputMethodMode(1);
        popupWindow.setSoftInputMode(16);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        view2.measure(0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.byd.systemsettings.ui.dialog.PopupWindowUtil.3
            @Override // android.widget.PopupWindow.OnDismissListener
            public void onDismiss() {
                PopupWindow.OnDismissListener onDismissListener2 = onDismissListener;
                if (onDismissListener2 != null) {
                    onDismissListener2.onDismiss();
                }
            }
        });
        int[] iArr = new int[2];
        popupWindow.showAsDropDown(view, iArr[0], iArr[1], !z7 ? 8388613 : 7);
        return popupWindow;
    }
}
