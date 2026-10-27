package com.byd.music.fragment.radio;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.FragmentUtils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.databinding.FragmentEpgListBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DipUtil;
import com.byd.music.utils.RecyclerViewCornerRadius;

/* JADX INFO: loaded from: classes5.dex */
public class EpgListFragment extends BaseFragment<FragmentEpgListBinding, EpgListViewModel> {
    ActivityResultLauncher<Intent> launcher;

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() { // from class: com.byd.music.fragment.radio.EpgListFragment.1
            @Override // androidx.activity.result.ActivityResultCallback
            public void onActivityResult(ActivityResult activityResult) {
                if (activityResult.getResultCode() == -1) {
                    ((EpgListViewModel) EpgListFragment.this.viewModel).replaceSubscribeEpgBean(true);
                } else {
                    ((EpgListViewModel) EpgListFragment.this.viewModel).replaceSubscribeEpgBean(false);
                }
            }
        });
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_epg_list;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        RecyclerViewCornerRadius recyclerViewCornerRadius = new RecyclerViewCornerRadius(((FragmentEpgListBinding) this.dataBinding).rvCurrentDabList);
        recyclerViewCornerRadius.setCornerRadius(DipUtil.dp2px(this.context.getResources().getDimension(R.dimen.dp_6)));
        ((FragmentEpgListBinding) this.dataBinding).rvCurrentDabList.addItemDecoration(recyclerViewCornerRadius);
        ((FragmentEpgListBinding) this.dataBinding).btnBack.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$EpgListFragment$JMdoi8Kvs3VQaAu38sSKDRStF7w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$EpgListFragment(view);
            }
        });
        ((EpgListViewModel) this.viewModel).replaceClickEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.radio.-$$Lambda$EpgListFragment$Ja0sAOvm4mnGdNrXlvSfsKY5jTg
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$1$EpgListFragment((RadioEpgDBBean) obj);
            }
        });
    }

    public /* synthetic */ void lambda$initView$0$EpgListFragment(View view) {
        FragmentUtils.pop(getParentFragmentManager());
    }

    public /* synthetic */ void lambda$initView$1$EpgListFragment(RadioEpgDBBean radioEpgDBBean) {
        this.launcher.launch(new Intent(getActivity(), (Class<?>) EpgReplaceDialogActivity.class).putExtra("RadioTitle", radioEpgDBBean.getDesc()));
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        RadioServiceWrapper.getInstance().subscribeEpgWriteToDB();
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        RadioServiceWrapper.getInstance().restartEpgCountDown();
    }
}
