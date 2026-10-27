package com.byd.music.fragment.radio;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.SPUtils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.databinding.FragmentDabSettingBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.CheckBoxView;

/* JADX INFO: loaded from: classes5.dex */
public class DABSettingFragment extends BaseFragment<FragmentDabSettingBinding, EmptyViewModel> implements CompoundButton.OnCheckedChangeListener {
    public static final int FLAG_DAB_EMERGENCY = 4;
    public static final int FLAG_DAB_EVENT = 128;
    public static final int FLAG_DAB_MISC = 512;
    public static final int FLAG_DAB_NEWS = 64;
    public static final int FLAG_DAB_SPORT = 256;
    public static final int FLAG_DAB_TRAFFIC = 16;
    public static final int FLAG_DAB_WARNING = 8;
    public static final int FLAG_DAB_WEATHER = 32;
    public static final int FLAG_SERVICE_FOLLOW = 1;
    public static final int FLAG_SOFT_LINK = 2;
    CheckBoxView mServiceFollowButton;
    CheckBoxView mSoftLinkButton;
    private static final String KEY_DAB_SETTINGS = "key_dab_settings";
    public static int DAB_SETTINGS = SPUtils.getInstance().getInt(KEY_DAB_SETTINGS, 1);

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    public static boolean isSettingEnable(int i) {
        return (i & DAB_SETTINGS) > 0;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_dab_setting;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        this.mServiceFollowButton = new CheckBoxView(getContext()) { // from class: com.byd.music.fragment.radio.DABSettingFragment.1
            {
                setParam(getContext().getString(R.string.dab_service_following), DABSettingFragment.isSettingEnable(1), DABSettingFragment.this, 1, ((EmptyViewModel) DABSettingFragment.this.viewModel).isRight.get());
            }
        };
        this.mSoftLinkButton = new CheckBoxView(getContext()) { // from class: com.byd.music.fragment.radio.DABSettingFragment.2
            {
                setParam(getContext().getString(R.string.dab_soft_link), DABSettingFragment.isSettingEnable(2), DABSettingFragment.this, 2, ((EmptyViewModel) DABSettingFragment.this.viewModel).isRight.get());
            }
        };
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        ((FragmentDabSettingBinding) this.dataBinding).btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABSettingFragment$mK18b4K-Aw_xF3JlOyaeqwt_SEk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$DABSettingFragment(view);
            }
        });
        ((FragmentDabSettingBinding) this.dataBinding).checkBoxContainer.addView(this.mServiceFollowButton);
        ((FragmentDabSettingBinding) this.dataBinding).checkBoxContainer.addView(this.mSoftLinkButton);
        if (((FragmentDabSettingBinding) this.dataBinding).checkBoxScroll == null) {
            return;
        }
        if (((EmptyViewModel) this.viewModel).isRight.get()) {
            BindingUtils.setConstraintCustom(((FragmentDabSettingBinding) this.dataBinding).checkBoxScroll, 0, 0, 0, 1);
        } else {
            BindingUtils.setConstraintCustom(((FragmentDabSettingBinding) this.dataBinding).checkBoxScroll, 1, 0, 0, 0);
        }
    }

    public /* synthetic */ void lambda$initView$0$DABSettingFragment(View view) {
        FragmentUtils.pop(getParentFragmentManager());
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getTag() instanceof Integer) {
            if (z) {
                DAB_SETTINGS |= ((Integer) compoundButton.getTag()).intValue();
            } else {
                DAB_SETTINGS &= ~((Integer) compoundButton.getTag()).intValue();
            }
            if (((Integer) compoundButton.getTag()).intValue() != 1) {
                return;
            }
            RadioServiceWrapper.getInstance().setServiceFollow(z);
        }
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        SPUtils.getInstance().put(KEY_DAB_SETTINGS, DAB_SETTINGS, true);
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("servicefollow", this.mServiceFollowButton.isCheck());
        bundle.putBoolean("softlink", this.mSoftLinkButton.isCheck());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mServiceFollowButton.setCheck(bundle.getBoolean("servicefollow"));
            this.mSoftLinkButton.setCheck(bundle.getBoolean("softlink"));
        }
    }
}
