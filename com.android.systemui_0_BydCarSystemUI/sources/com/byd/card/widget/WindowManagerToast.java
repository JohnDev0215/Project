package com.byd.card.widget;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.android.systemui.R;
import com.byd.card.utils.DensityUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class WindowManagerToast {
    private static final int HIDE = 1001;
    private static final int SHOW = 1000;
    private static volatile WindowManagerToast sInstance;
    private Context context;
    private TextView mTipTv;
    private View view;
    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams mParams = null;
    private Handler handler = new Handler(Looper.getMainLooper()) { // from class: com.byd.card.widget.WindowManagerToast.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            if (i != 1000) {
                if (i == 1001 && WindowManagerToast.this.mWindowManager != null) {
                    try {
                        WindowManagerToast.this.mWindowManager.removeView(WindowManagerToast.this.view);
                        WindowManagerToast.this.handler.removeCallbacksAndMessages(null);
                        return;
                    } catch (IllegalArgumentException e) {
                        BydLogHelper.logE(e.getMessage());
                        return;
                    }
                }
                return;
            }
            if (WindowManagerToast.this.mWindowManager != null) {
                try {
                    WindowManagerToast.this.mWindowManager.addView(WindowManagerToast.this.view, WindowManagerToast.this.mParams);
                    WindowManagerToast.this.handler.sendEmptyMessageDelayed(1001, 3000L);
                } catch (IllegalStateException e2) {
                    BydLogHelper.logE(e2.getMessage());
                }
            }
        }
    };

    public static WindowManagerToast getInstance(Context context) {
        if (sInstance == null) {
            synchronized (WindowManagerToast.class) {
                if (sInstance == null) {
                    sInstance = new WindowManagerToast(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private WindowManagerToast(Context context) {
        this.context = context;
        initParam();
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.window_toast, (ViewGroup) null);
        this.view = viewInflate;
        this.mTipTv = (TextView) viewInflate.findViewById(R.id.toast_content);
    }

    public void showToast(String content) {
        this.mTipTv.setText(content);
        Message messageObtain = Message.obtain();
        messageObtain.what = 1000;
        this.handler.sendMessage(messageObtain);
    }

    private WindowManager.LayoutParams initParam() {
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) this.context.getApplicationContext().getSystemService("window");
        }
        if (this.mParams == null) {
            this.mParams = new WindowManager.LayoutParams();
        }
        if (Build.VERSION.SDK_INT >= 26) {
            this.mParams.type = 2038;
        } else {
            this.mParams.type = 2003;
        }
        this.mParams.format = -2;
        this.mParams.flags = 40;
        this.mParams.gravity = 48;
        this.mParams.y = ((int) this.context.getResources().getDimension(R.dimen.dp_128)) - DensityUtil.getStatusBarHeight(this.context);
        this.mParams.width = -2;
        this.mParams.height = -2;
        return this.mParams;
    }
}
