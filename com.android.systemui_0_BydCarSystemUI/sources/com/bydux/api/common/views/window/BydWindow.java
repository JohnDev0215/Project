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

    protected String onWindowAction() {
        return null;
    }

    public void updateViewLayout() {
    }

    public View showWindow(int layoutId, WindowManager.LayoutParams lp) {
        return showWindow(layoutId, lp, -1);
    }

    public View showWindow(int layoutId, WindowManager.LayoutParams lp, int animId) {
        BydLogHelper.logV(this.TAG, "showWindow", null);
        if (this.mView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            if (layoutInflater.getFactory() == null) {
                BydLogHelper.logV(this.TAG, "showWindow", "window has no factory before");
                layoutInflater.setFactory(this.mSkinInflaterFactory);
                SkinManager.getInstance().attach(this);
            }
            if (animId == 0) {
                lp.windowAnimations = R.style.byd_dialog_anim;
            } else if (animId > 0) {
                lp.windowAnimations = animId;
            }
            this.mView = layoutInflater.inflate(layoutId, (ViewGroup) null);
            ((WindowManager) this.mContext.getSystemService("window")).addView(this.mView, lp);
            TextUtils.isEmpty(onWindowAction());
        }
        return this.mView;
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

    public void setCloseTime(long ms) {
        BydLogHelper.logV(this.TAG, "setCloseTime", "ms=" + ms);
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(ms);
        }
    }

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

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        BydLogHelper.logV(this.TAG, "onSkinUpdate", "skin=" + SkinManager.getInstance().getSkinPath());
        this.mSkinInflaterFactory.applySkin();
    }
}
