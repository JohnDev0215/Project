package com.bydux.api.common.views.window;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.ctrl.BydTimer;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
public class BydWindow implements ISkinUpdate {
    private Context mContext;
    private BydTimer mTimer;
    private final String TAG = getClass().getSimpleName();
    private View mView = null;
    private SkinInflaterFactory mSkinInflaterFactory = new SkinInflaterFactory();

    public BydWindow(Context context) {
        this.mContext = context;
        BydTimer bydTimer = new BydTimer(context);
        this.mTimer = bydTimer;
        bydTimer.setBydTimerListener(new BydTimer.BydTimerListener() { // from class: com.bydux.api.common.views.window.BydWindow.1
            @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
            public void onTimeUp(View view) {
                BydWindow.this.closeWindow();
            }
        });
    }

    private void release() {
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
        SkinManager.getInstance().detach(this);
        this.mSkinInflaterFactory.clean();
    }

    public void closeWindow() {
        BydLogHelper.logV(this.TAG, "closeWindow", null);
        if (this.mView != null) {
            release();
            ((WindowManager) this.mContext.getSystemService("window")).removeViewImmediate(this.mView);
            this.mView = null;
            TextUtils.isEmpty(onWindowAction());
        }
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        BydLogHelper.logV(this.TAG, "onSkinUpdate", "skin=" + SkinManager.getInstance().getSkinPath());
        this.mSkinInflaterFactory.applySkin();
    }

    public String onWindowAction() {
        return null;
    }

    public void setCloseTime(long j8) {
        BydLogHelper.logV(this.TAG, "setCloseTime", "ms=" + j8);
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(j8);
        }
    }

    public View showWindow(int i8, WindowManager.LayoutParams layoutParams) {
        return showWindow(i8, layoutParams, -1);
    }

    public void updateViewLayout() {
    }

    public View showWindow(int i8, WindowManager.LayoutParams layoutParams, int i9) {
        BydLogHelper.logV(this.TAG, "showWindow", null);
        if (this.mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            if (layoutInflater.getFactory() == null) {
                BydLogHelper.logV(this.TAG, "showWindow", "window has no factory before");
                layoutInflater.setFactory(this.mSkinInflaterFactory);
                SkinManager.getInstance().attach(this);
            }
            if (i9 == 0) {
                layoutParams.windowAnimations = R.style.byd_dialog_anim;
            } else if (i9 > 0) {
                layoutParams.windowAnimations = i9;
            }
            this.mView = layoutInflater.inflate(i8, (ViewGroup) null);
            ((WindowManager) this.mContext.getSystemService("window")).addView(this.mView, layoutParams);
            TextUtils.isEmpty(onWindowAction());
        }
        return this.mView;
    }
}
