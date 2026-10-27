package com.android.systemui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.WindowManager;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.statusbar.wm.GestureControl;
import com.android.systemui.util.StyleUtil;

/* JADX INFO: loaded from: classes.dex */
public class GestureDialog extends Dialog {
    protected GestureControl gestureControl;
    protected Context mContext;
    protected int mTouchSlop;

    protected void initSize() {
    }

    public GestureDialog(Context context) {
        this(context, R.style.dialog_style_gesture);
    }

    public GestureDialog(Context context, int themeResId) {
        super(context, R.style.dialog_style_gesture);
        this.mContext = context;
        onCreate();
    }

    protected void onCreate() {
        setContentView(StyleUtil.getResLayout(R.layout.view_gesture_dialog, "view_gesture_dialog"));
        addFlag();
        setAttributes();
        this.mTouchSlop = 30;
        this.gestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    private void addFlag() {
        getWindow().addFlags(402653440);
    }

    private void setAttributes() {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -2;
        attributes.height = -2;
        attributes.gravity = 49;
        attributes.format = -3;
        attributes.dimAmount = 0.0f;
        if (Build.VERSION.SDK_INT >= 26) {
            attributes.type = 2017;
        } else {
            attributes.type = 2003;
        }
        attributes.packageName = this.mContext.getPackageName();
        attributes.flags = 918816;
        setCanceledOnTouchOutside(true);
        getWindow().setAttributes(attributes);
    }
}
