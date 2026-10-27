package com.byd.vrsettings.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.byd.vrsettings.R;
import com.byd.vrsettings.databinding.ViewMultifunProgressbarBinding;
import com.byd.vrsettings.utils.DimenUtil;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class MultiFunProgressBar extends ConstraintLayout {
    ViewMultifunProgressbarBinding mBinding;
    private Context mContext;
    private int mState;

    public interface OnMultiFunProgressClick {
        void onClick(View view, int i);
    }

    public MultiFunProgressBar(Context context) {
        this(context, null);
    }

    public MultiFunProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        init();
    }

    private void init() {
        this.mBinding = ViewMultifunProgressbarBinding.bind(View.inflate(this.mContext, R.layout.view_multifun_progressbar, this));
        setState(4, true, false);
    }

    public void setOnProgressClick(final OnMultiFunProgressClick onMultiFunProgressClick) {
        int i = this.mState;
        if (i == 2 || i == 1) {
            this.mBinding.progressbar.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.MultiFunProgressBar.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onMultiFunProgressClick.onClick(view, MultiFunProgressBar.this.mState);
                }
            });
        } else if (i == 0 || i == 6 || i == 3) {
            this.mBinding.textFuntion.setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrsettings.view.MultiFunProgressBar.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    onMultiFunProgressClick.onClick(view, MultiFunProgressBar.this.mState);
                }
            });
        }
    }

    public void setProgress(int i) {
        if (i <= this.mBinding.progressbar.getMax()) {
            this.mBinding.progressbar.setProgress(i);
            this.mBinding.textProgress.setText((isPercentLeft() ? new StringBuilder("%").append(i) : new StringBuilder().append(i).append("%")).toString());
        }
    }

    public int getState() {
        return this.mState;
    }

    public void setState(int i, boolean z, boolean z2) {
        this.mState = i;
        int i2 = R.mipmap.ic_download_continue;
        int i3 = R.string.text_update;
        int i4 = R.color.btn_ota_main_color;
        switch (i) {
            case 0:
            case 5:
            case 6:
                this.mBinding.gpProgress.setVisibility(4);
                this.mBinding.textFuntion.setVisibility(0);
                TextView textView = this.mBinding.textFuntion;
                if (!z2) {
                    i3 = R.string.text_download;
                }
                textView.setText(i3);
                this.mBinding.textFuntion.setTextColor(this.mContext.getColor(R.color.white));
                this.mBinding.textFuntion.setEnabled(true);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.shape_corner);
                break;
            case 1:
                this.mBinding.gpProgress.setVisibility(0);
                this.mBinding.textFuntion.setVisibility(4);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.progressbar_style);
                TextView textView2 = this.mBinding.textProgress;
                Context context = this.mContext;
                if (!z) {
                    i4 = R.color.white;
                }
                textView2.setTextColor(context.getColor(i4));
                this.mBinding.ivStatus.setImageResource(z ? R.mipmap.ic_download_pause : R.mipmap.ic_download_pause_dark);
                break;
            case 2:
                this.mBinding.gpProgress.setVisibility(0);
                this.mBinding.textFuntion.setVisibility(4);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.progressbar_style);
                TextView textView3 = this.mBinding.textProgress;
                Context context2 = this.mContext;
                if (!z) {
                    i4 = R.color.white;
                }
                textView3.setTextColor(context2.getColor(i4));
                ImageView imageView = this.mBinding.ivStatus;
                if (!z) {
                    i2 = R.mipmap.ic_download_continue_dark;
                }
                imageView.setImageResource(i2);
                break;
            case 3:
                this.mBinding.gpProgress.setVisibility(4);
                this.mBinding.textFuntion.setVisibility(0);
                this.mBinding.textFuntion.setText(R.string.text_update);
                this.mBinding.textFuntion.setTextColor(this.mContext.getColor(R.color.white));
                this.mBinding.textFuntion.setEnabled(true);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.shape_corner);
                break;
            case 4:
                this.mBinding.gpProgress.setVisibility(4);
                this.mBinding.textFuntion.setVisibility(4);
                break;
            case 7:
                this.mBinding.gpProgress.setVisibility(4);
                this.mBinding.textFuntion.setVisibility(0);
                this.mBinding.textFuntion.setText(R.string.text_installing);
                TextView textView4 = this.mBinding.textFuntion;
                Context context3 = this.mContext;
                if (!z) {
                    i4 = R.color.white;
                }
                textView4.setTextColor(context3.getColor(i4));
                this.mBinding.textFuntion.setEnabled(false);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.install_btn_style);
                break;
            case 9:
                this.mBinding.gpProgress.setVisibility(0);
                this.mBinding.textFuntion.setVisibility(4);
                this.mBinding.textFuntion.setBackgroundResource(R.drawable.progressbar_style);
                setProgress(100);
                TextView textView5 = this.mBinding.textProgress;
                Context context4 = this.mContext;
                if (!z) {
                    i4 = R.color.white;
                }
                textView5.setTextColor(context4.getColor(i4));
                ImageView imageView2 = this.mBinding.ivStatus;
                if (!z) {
                    i2 = R.mipmap.ic_download_continue_dark;
                }
                imageView2.setImageResource(i2);
                break;
        }
    }

    public void setLandScape(boolean z) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.mBinding.rootView.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.mBinding.ivStatus.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) this.mBinding.textFuntion.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) this.mBinding.progressbar.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) this.mBinding.textProgress.getLayoutParams();
        if (z) {
            layoutParams.width = DimenUtil.dp2px(120.0f);
            layoutParams3.width = DimenUtil.dp2px(120.0f);
            layoutParams4.width = DimenUtil.dp2px(120.0f);
            layoutParams2.setMarginStart(DimenUtil.dp2px(24.0f));
            layoutParams5.setMarginStart(DimenUtil.dp2px(10.0f));
            return;
        }
        layoutParams.width = DimenUtil.dp2px(100.0f);
        layoutParams3.width = DimenUtil.dp2px(100.0f);
        layoutParams4.width = DimenUtil.dp2px(100.0f);
        layoutParams2.setMarginStart(DimenUtil.dp2px(18.0f));
        layoutParams5.setMarginStart(DimenUtil.dp2px(6.0f));
    }

    private boolean isPercentLeft() {
        Locale locale = Locale.getDefault();
        return locale != null && locale.getLanguage().equals("tr");
    }
}
