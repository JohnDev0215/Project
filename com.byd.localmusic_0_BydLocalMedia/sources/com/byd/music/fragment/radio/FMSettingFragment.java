package com.byd.music.fragment.radio;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.SPUtils;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.databinding.FragmentTaSettingBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.CheckBoxView;

/* JADX INFO: loaded from: classes5.dex */
public class FMSettingFragment extends BaseFragment<FragmentTaSettingBinding, EmptyViewModel> implements CompoundButton.OnCheckedChangeListener {
    CheckBoxView mAFButton;
    CheckBoxView mTAButton;

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

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return 0;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_ta_setting;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        this.mTAButton = new CheckBoxView(getContext()) { // from class: com.byd.music.fragment.radio.FMSettingFragment.1
            {
                setParam(getContext().getString(R.string.radio_ta_desc), SPUtils.getInstance().getBoolean("ta_settings_switch", true), FMSettingFragment.this, 1, ((EmptyViewModel) FMSettingFragment.this.viewModel).isRight.get());
            }
        };
        this.mAFButton = new CheckBoxView(getContext()) { // from class: com.byd.music.fragment.radio.FMSettingFragment.2
            {
                setParam(getContext().getString(R.string.radio_af_desc), SPUtils.getInstance().getBoolean("af_settings_switch", false), FMSettingFragment.this, 2, ((EmptyViewModel) FMSettingFragment.this.viewModel).isRight.get());
            }
        };
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        ((FragmentTaSettingBinding) this.dataBinding).btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMSettingFragment$wwGanT0aeRQ3jaOxuzx0eFNAujQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$FMSettingFragment(view);
            }
        });
        ((FragmentTaSettingBinding) this.dataBinding).checkBoxContainer.addView(this.mTAButton);
        ((FragmentTaSettingBinding) this.dataBinding).checkBoxContainer.addView(this.mAFButton);
        if (((EmptyViewModel) this.viewModel).isRight.get()) {
            BindingUtils.setConstraintCustom(((FragmentTaSettingBinding) this.dataBinding).checkBoxContainer, 0, 0, 0, 1);
        } else {
            BindingUtils.setConstraintCustom(((FragmentTaSettingBinding) this.dataBinding).checkBoxContainer, 1, 0, 0, 0);
        }
    }

    public /* synthetic */ void lambda$initView$0$FMSettingFragment(View view) {
        FragmentUtils.pop(getParentFragmentManager());
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton.getTag() instanceof Integer) {
            int iIntValue = ((Integer) compoundButton.getTag()).intValue();
            if (iIntValue == 1) {
                SPUtils.getInstance().put("ta_settings_switch", z);
                return;
            }
            if (iIntValue != 2) {
                return;
            }
            try {
                RadioServiceWrapper.getInstance().setAlternativeFrequency(z);
                SPUtils.getInstance().put("af_settings_switch", z);
            } catch (Throwable th) {
                LogUtil.e(this.TAG, "setAlternativeFrequency error!");
                th.printStackTrace();
            }
        }
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("ta_check", this.mTAButton.isCheck());
        bundle.putBoolean("af_check", this.mAFButton.isCheck());
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewStateRestored(Bundle bundle) {
        super.onViewStateRestored(bundle);
        if (bundle != null) {
            this.mTAButton.setCheck(bundle.getBoolean("ta_check"));
            this.mAFButton.setCheck(bundle.getBoolean("af_check"));
        }
    }
}
