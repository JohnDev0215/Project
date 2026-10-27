package com.byd.music.fragment.radio;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import com.blankj.utilcode.util.FragmentUtils;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.databinding.FragmentHolderBinding;

/* JADX INFO: loaded from: classes5.dex */
public class HolderFragment extends BaseFragment<FragmentHolderBinding, EmptyViewModel> {
    int type;

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

    public HolderFragment() {
    }

    public HolderFragment(int i) {
        this.type = i;
        Bundle bundle = new Bundle();
        bundle.putInt("type", i);
        setArguments(bundle);
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_holder;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        this.type = getArguments().getInt("type", 0);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        Fragment fMFragment;
        int i = this.type;
        if (i == 0) {
            fMFragment = new FMFragment();
        } else if (i == 1) {
            fMFragment = new AMFragment();
        } else {
            fMFragment = i != 2 ? null : new DABFragment();
        }
        if (fMFragment == null || FragmentUtils.findFragment(getChildFragmentManager(), (Class<? extends Fragment>) fMFragment.getClass()) != null) {
            return;
        }
        FragmentUtils.add(getChildFragmentManager(), fMFragment, R.id.fl_holder);
    }
}
