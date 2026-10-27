package com.ts.appservice.power.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;
import com.ts.appservice.power.R;
import com.ts.appservice.power.view.PowerViewContract;
import com.ts.lib.power.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class CarPowerAbnormalView extends BaseLayout<PowerViewPresenter> implements PowerViewContract.IView {
    private static final int WARNING_TEM_FLAG = 3;
    private TextView mTvFaceidCommit;
    private TextView mTvFaceidInputTitle;
    private TextView mTvFaceidTipOne;

    public CarPowerAbnormalView(Context context) {
        super(context);
    }

    public CarPowerAbnormalView(Context context, int flag) {
        super(context, flag);
    }

    public CarPowerAbnormalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarPowerAbnormalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override // com.ts.appservice.power.view.BaseLayout
    protected void initView() {
        this.mTvFaceidInputTitle = (TextView) this.mRootView.findViewById(R.id.tv_power_abnomal_view_title);
        this.mTvFaceidTipOne = (TextView) this.mRootView.findViewById(R.id.tv_power_abnomal_view_tip);
        this.mTvFaceidCommit = (TextView) this.mRootView.findViewById(R.id.tv_power_abnomal_view_time);
        LogUtil.info("WARNING_SHOW", "mWarnFlag: " + this.mWarnFlag);
        this.mTvFaceidInputTitle.setText(3 == this.mWarnFlag ? R.string.power_abnomal_temperature : R.string.power_abnomal_voltage);
    }

    @Override // com.ts.appservice.power.view.BaseLayout
    protected int attachLayoutRes() {
        return R.layout.layout_power_abnormal;
    }

    @Override // com.ts.appservice.power.view.BaseLayout
    protected void initPresenter(Context context) {
        this.mPresenter = new PowerViewPresenter(context, this);
        ((PowerViewPresenter) this.mPresenter).startTimer();
    }

    @Override // com.ts.appservice.power.view.PowerViewContract.IView
    public void updateRemainTime(boolean end, long remainTime) {
        if (!end) {
            this.mTvFaceidCommit.setText(remainTime + getResources().getString(R.string.time_second));
            this.mTvFaceidCommit.setTextColor(getResources().getColor(R.color.color_unclick));
            return;
        }
        this.mPop.dismiss();
    }

    @Override // com.ts.appservice.power.view.BaseLayout
    public void dismiss() {
        super.dismiss();
        ((PowerViewPresenter) this.mPresenter).exitTimer();
    }
}
