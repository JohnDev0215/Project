package com.byd.music.fragment.radio;

import android.hardware.radio.ProgramSelector;
import android.hardware.radio.RadioManager;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.Observable;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.byd.music.BR;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.beans.MediaControlNaviBean;
import com.byd.music.databinding.FragmentDabBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.RadioPTYUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.CatchInconsistencyDetectedLinearLayoutManager;
import java.util.AbstractMap;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class DABFragment extends BaseFragment<FragmentDabBinding, DABViewModel> {
    AbstractMap.SimpleEntry<Long, Integer> dabCache;
    Observable.OnPropertyChangedCallback onPropertyChangedCallback;
    ObservableBoolean isCollectShow = new ObservableBoolean(false);
    private long finalScroll = 0;

    @Override // com.byd.music.base.BaseFragment
    protected boolean disableBackground() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected boolean isPreLoad() {
        return true;
    }

    @Override // com.byd.music.base.BaseFragment
    public boolean onBack() {
        return false;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onLazyInit() {
    }

    @Override // com.byd.music.base.BaseFragment
    protected int layoutId() {
        return R.layout.fragment_dab;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        ((FragmentDabBinding) this.dataBinding).setVariable(BR.isCollectShow, this.isCollectShow);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        LogUtil.e("DABFragment", "initView");
        ((FragmentDabBinding) this.dataBinding).rvCollectList.setFocusableInTouchMode(false);
        ((FragmentDabBinding) this.dataBinding).rvCollectList.requestFocus();
        ((FragmentDabBinding) this.dataBinding).rvChannelList.setFocusableInTouchMode(false);
        ((FragmentDabBinding) this.dataBinding).rvChannelList.requestFocus();
        ((FragmentDabBinding) this.dataBinding).rvChannelList.setVerticalScrollBarEnabled(false);
        ((FragmentDabBinding) this.dataBinding).rvCollectList.setVerticalScrollBarEnabled(false);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        ((FragmentDabBinding) this.dataBinding).rvCollectList.addOnScrollListener(anonymousClass1);
        ((FragmentDabBinding) this.dataBinding).rvChannelList.addOnScrollListener(anonymousClass1);
        ((FragmentDabBinding) this.dataBinding).testbtn.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.DABFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioServiceWrapper.getInstance().isGetDadBER = System.currentTimeMillis();
                RadioServiceWrapper.getInstance().getDabBER();
            }
        });
        if (RadioServiceWrapper.getInstance().getBgScanEnable()) {
            ((FragmentDabBinding) this.dataBinding).topSearch.setVisibility(8);
        } else {
            ((FragmentDabBinding) this.dataBinding).topSearch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.DABFragment.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (DeviceInfoUtils.isInCall()) {
                        return;
                    }
                    RadioServiceWrapper.getInstance().updateSingleSourceProgramList();
                }
            });
        }
        if (RadioServiceWrapper.getInstance().getEPGEnable()) {
            ((FragmentDabBinding) this.dataBinding).epg.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABFragment$3XTu8L_tG7pbxc31CKdNax7czLI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$0$DABFragment(view);
                }
            });
        } else {
            ((FragmentDabBinding) this.dataBinding).epg.setVisibility(8);
        }
        ((FragmentDabBinding) this.dataBinding).topSetting.setVisibility(8);
        ((FragmentDabBinding) this.dataBinding).rvChannelList.setLayoutManager(new CatchInconsistencyDetectedLinearLayoutManager(getContext(), 1, false));
        ((FragmentDabBinding) this.dataBinding).rvChannelList.setItemAnimator(null);
        ((FragmentDabBinding) this.dataBinding).rvCollectList.setLayoutManager(new CatchInconsistencyDetectedLinearLayoutManager(getContext(), 1, false));
        ((FragmentDabBinding) this.dataBinding).rvCollectList.setItemAnimator(null);
        ((FragmentDabBinding) this.dataBinding).rbgProgramList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.music.fragment.radio.DABFragment.4
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbCollect) {
                    DABFragment.this.isCollectShow.set(true);
                } else {
                    DABFragment.this.isCollectShow.set(false);
                }
            }
        });
        ((FragmentDabBinding) this.dataBinding).btnTuneDab.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.DABFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioServiceWrapper.getInstance().tune(new ProgramSelector(5, new ProgramSelector.Identifier(5, 0L), new ProgramSelector.Identifier[]{new ProgramSelector.Identifier(8, 174928L)}, null));
            }
        });
        ((FragmentDabBinding) this.dataBinding).btnUpdateList.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.DABFragment.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RadioServiceWrapper.getInstance().updateSingleSourceProgramList();
            }
        });
        updateDabStationString();
        this.onPropertyChangedCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.radio.DABFragment.7
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                DABFragment.this.updateDabStationString();
            }
        };
        RadioServiceWrapper.getInstance().getCurrentProgram().addOnPropertyChangedCallback(this.onPropertyChangedCallback);
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
    }

    /* JADX INFO: renamed from: com.byd.music.fragment.radio.DABFragment$1, reason: invalid class name */
    class AnonymousClass1 extends RecyclerView.OnScrollListener {
        AnonymousClass1() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(final RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            recyclerView.setVerticalScrollBarEnabled(true);
            if (i == 0) {
                DABFragment.this.finalScroll = System.currentTimeMillis();
                new Handler().postDelayed(new Runnable() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABFragment$1$SbifejFeMoETe5wi3re58WsNFDA
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onScrollStateChanged$0$DABFragment$1(recyclerView);
                    }
                }, 1500L);
            }
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$DABFragment$1(RecyclerView recyclerView) {
            try {
                if (recyclerView.getScrollState() != 0 || (System.currentTimeMillis() - DABFragment.this.finalScroll) - 1400 <= 0) {
                    return;
                }
                recyclerView.setVerticalScrollBarEnabled(false);
            } catch (Throwable unused) {
            }
        }
    }

    public /* synthetic */ void lambda$initView$0$DABFragment(View view) {
        FragmentUtils.replace((Fragment) this, (Fragment) new EpgListFragment(), true);
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (z) {
            ((FragmentDabBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f));
            BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupPlayingDabCard, 0, 0, 0, 0);
            ViewGroup.LayoutParams layoutParams = ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getLayoutParams();
            layoutParams.width = -1;
            layoutParams.height = SizeUtils.dp2px(298.0f);
            ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setLayoutParams(layoutParams);
            ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setPadding(SizeUtils.dp2px(6.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(6.0f), SizeUtils.dp2px(0.0f));
            BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupDabList, 0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).groupDabList.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            layoutParams2.topToTop = -1;
            layoutParams2.bottomToBottom = 0;
            layoutParams2.topToBottom = ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getId();
            ((FragmentDabBinding) this.dataBinding).groupDabList.setLayoutParams(layoutParams2);
            ((FragmentDabBinding) this.dataBinding).groupDabList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(80.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams3.height = -2;
            layoutParams3.bottomToBottom = -1;
            ((FragmentDabBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams3);
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams4.height = -1;
            layoutParams4.bottomToBottom = -1;
            ((FragmentDabBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams4);
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPic.getLayoutParams();
            layoutParams5.topToTop = 0;
            layoutParams5.bottomToBottom = 0;
            layoutParams5.topToBottom = -1;
            layoutParams5.endToEnd = -1;
            layoutParams5.setMarginStart(SizeUtils.dp2px(91.0f));
            layoutParams5.setMargins(0, 0, 0, 0);
            ((FragmentDabBinding) this.dataBinding).dabPic.setLayoutParams(layoutParams5);
            ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabService.getLayoutParams();
            layoutParams6.setMargins(0, 8, 0, 0);
            layoutParams6.setMarginStart(SizeUtils.dp2px(48.0f));
            layoutParams6.width = SizeUtils.dp2px(0.0f);
            layoutParams6.startToEnd = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams6.endToEnd = 0;
            layoutParams6.topToTop = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams6.rightToRight = -1;
            layoutParams6.leftToLeft = -1;
            ((FragmentDabBinding) this.dataBinding).dabService.setLayoutParams(layoutParams6);
            ((FragmentDabBinding) this.dataBinding).dabService.setPadding(0, SizeUtils.dp2px(8.0f), 0, 0);
            ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabEnsemble.getLayoutParams();
            layoutParams7.topToBottom = -1;
            layoutParams7.bottomToTop = ((FragmentDabBinding) this.dataBinding).dabPty.getId();
            ((FragmentDabBinding) this.dataBinding).dabEnsemble.setLayoutParams(layoutParams7);
            ((FragmentDabBinding) this.dataBinding).dabEnsemble.setPadding(0, 0, 0, SizeUtils.dp2px(2.0f));
            ConstraintLayout.LayoutParams layoutParams8 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPty.getLayoutParams();
            layoutParams8.topToBottom = -1;
            layoutParams8.setMargins(0, 0, 0, SizeUtils.dp2px(20.0f));
            ((FragmentDabBinding) this.dataBinding).dabPty.setLayoutParams(layoutParams8);
            ConstraintLayout.LayoutParams layoutParams9 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabDls.getLayoutParams();
            layoutParams9.topToBottom = -1;
            layoutParams9.bottomToBottom = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams9.setMargins(0, 0, 0, SizeUtils.dp2px(8.0f));
            ((FragmentDabBinding) this.dataBinding).dabDls.setLayoutParams(layoutParams9);
            return;
        }
        if (i == 2) {
            ((FragmentDabBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f));
            BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupPlayingDabCard, ((DABViewModel) this.viewModel).isRight.get() ? 1 : 0, 0, 0, !((DABViewModel) this.viewModel).isRight.get() ? 1 : 0);
            ViewGroup.LayoutParams layoutParams10 = ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getLayoutParams();
            layoutParams10.width = SizeUtils.dp2px(350.0f);
            layoutParams10.height = SizeUtils.dp2px(440.0f);
            ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setLayoutParams(layoutParams10);
            ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupDabList, !((DABViewModel) this.viewModel).isRight.get() ? 1 : 0, ((DABViewModel) this.viewModel).isRight.get() ? ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getId() : 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).groupDabList.getLayoutParams();
            layoutParams11.width = SizeUtils.dp2px(650.0f);
            layoutParams11.height = SizeUtils.dp2px(526.0f);
            layoutParams11.bottomToBottom = -1;
            layoutParams11.topToBottom = -1;
            layoutParams11.topToTop = -1;
            ((FragmentDabBinding) this.dataBinding).groupDabList.setLayoutParams(layoutParams11);
            ((FragmentDabBinding) this.dataBinding).groupDabList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(50.0f));
            ConstraintLayout.LayoutParams layoutParams12 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams12.height = SizeUtils.dp2px(0.0f);
            layoutParams12.bottomToBottom = 0;
            ((FragmentDabBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams12);
            ConstraintLayout.LayoutParams layoutParams13 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams13.height = SizeUtils.dp2px(0.0f);
            layoutParams13.bottomToBottom = 0;
            ((FragmentDabBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams13);
            ConstraintLayout.LayoutParams layoutParams14 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPic.getLayoutParams();
            layoutParams14.topToTop = -1;
            layoutParams14.bottomToBottom = -1;
            layoutParams14.topToBottom = ((FragmentDabBinding) this.dataBinding).dabService.getId();
            layoutParams14.endToEnd = 0;
            layoutParams14.setMarginStart(0);
            layoutParams14.setMargins(0, SizeUtils.dp2px(27.0f), 0, 0);
            ((FragmentDabBinding) this.dataBinding).dabPic.setLayoutParams(layoutParams14);
            ConstraintLayout.LayoutParams layoutParams15 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabService.getLayoutParams();
            layoutParams15.setMargins(0, 0, 0, 0);
            layoutParams15.rightToRight = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams15.leftToLeft = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams15.width = SizeUtils.dp2px(0.0f);
            layoutParams15.topToTop = 0;
            layoutParams15.startToEnd = -1;
            layoutParams15.endToEnd = -1;
            layoutParams15.setMarginStart(1);
            ((FragmentDabBinding) this.dataBinding).dabService.setLayoutParams(layoutParams15);
            ((FragmentDabBinding) this.dataBinding).dabService.setPadding(0, 0, 0, 0);
            ConstraintLayout.LayoutParams layoutParams16 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabEnsemble.getLayoutParams();
            layoutParams16.topToBottom = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
            layoutParams16.bottomToTop = -1;
            ((FragmentDabBinding) this.dataBinding).dabEnsemble.setLayoutParams(layoutParams16);
            ((FragmentDabBinding) this.dataBinding).dabEnsemble.setPadding(0, SizeUtils.dp2px(20.0f), 0, 0);
            ConstraintLayout.LayoutParams layoutParams17 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPty.getLayoutParams();
            layoutParams17.topToBottom = ((FragmentDabBinding) this.dataBinding).dabEnsemble.getId();
            layoutParams17.setMargins(0, 0, 0, 0);
            ((FragmentDabBinding) this.dataBinding).dabPty.setLayoutParams(layoutParams17);
            ConstraintLayout.LayoutParams layoutParams18 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabDls.getLayoutParams();
            layoutParams18.topToBottom = ((FragmentDabBinding) this.dataBinding).dabPty.getId();
            layoutParams18.bottomToBottom = 0;
            layoutParams18.setMargins(0, 0, 0, 0);
            ((FragmentDabBinding) this.dataBinding).dabDls.setLayoutParams(layoutParams18);
            return;
        }
        ((FragmentDabBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f));
        BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupPlayingDabCard, 0, 0, 0, 0);
        ViewGroup.LayoutParams layoutParams19 = ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getLayoutParams();
        layoutParams19.height = SizeUtils.dp2px(280.0f);
        layoutParams19.width = -1;
        ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setLayoutParams(layoutParams19);
        ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
        BindingUtils.setConstraintCustom(((FragmentDabBinding) this.dataBinding).groupDabList, 0, 0, 0, 0);
        ConstraintLayout.LayoutParams layoutParams20 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).groupDabList.getLayoutParams();
        layoutParams20.width = -1;
        layoutParams20.height = SizeUtils.dp2px(560.0f);
        layoutParams20.topToTop = -1;
        layoutParams20.bottomToBottom = 0;
        layoutParams20.topToBottom = ((FragmentDabBinding) this.dataBinding).groupPlayingDabCard.getId();
        ((FragmentDabBinding) this.dataBinding).groupDabList.setLayoutParams(layoutParams20);
        ((FragmentDabBinding) this.dataBinding).groupDabList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(60.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
        ConstraintLayout.LayoutParams layoutParams21 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).channelListContainer.getLayoutParams();
        layoutParams21.height = SizeUtils.dp2px(0.0f);
        layoutParams21.bottomToBottom = 0;
        ((FragmentDabBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams21);
        ConstraintLayout.LayoutParams layoutParams22 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).collectListContainer.getLayoutParams();
        layoutParams22.height = SizeUtils.dp2px(0.0f);
        layoutParams22.bottomToBottom = 0;
        ((FragmentDabBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams22);
        ConstraintLayout.LayoutParams layoutParams23 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPic.getLayoutParams();
        layoutParams23.topToTop = 0;
        layoutParams23.bottomToBottom = 0;
        layoutParams23.topToBottom = -1;
        layoutParams23.endToEnd = -1;
        layoutParams23.setMarginStart(SizeUtils.dp2px(81.0f));
        layoutParams23.setMargins(0, 0, 0, 0);
        ((FragmentDabBinding) this.dataBinding).dabPic.setLayoutParams(layoutParams23);
        ConstraintLayout.LayoutParams layoutParams24 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabService.getLayoutParams();
        layoutParams24.setMarginStart(SizeUtils.dp2px(48.0f));
        layoutParams24.setMargins(0, 8, 0, 0);
        layoutParams24.width = -2;
        layoutParams24.startToEnd = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
        layoutParams24.topToTop = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
        layoutParams24.rightToRight = -1;
        layoutParams24.leftToLeft = -1;
        layoutParams24.endToEnd = -1;
        ((FragmentDabBinding) this.dataBinding).dabService.setLayoutParams(layoutParams24);
        ((FragmentDabBinding) this.dataBinding).dabService.setPadding(0, 0, 0, 0);
        ConstraintLayout.LayoutParams layoutParams25 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabEnsemble.getLayoutParams();
        layoutParams25.topToBottom = -1;
        layoutParams25.bottomToTop = ((FragmentDabBinding) this.dataBinding).dabPty.getId();
        ((FragmentDabBinding) this.dataBinding).dabEnsemble.setLayoutParams(layoutParams25);
        ((FragmentDabBinding) this.dataBinding).dabEnsemble.setPadding(0, 0, 0, SizeUtils.dp2px(2.0f));
        ConstraintLayout.LayoutParams layoutParams26 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabPty.getLayoutParams();
        layoutParams26.topToBottom = -1;
        layoutParams26.setMargins(0, 0, 0, SizeUtils.dp2px(20.0f));
        ((FragmentDabBinding) this.dataBinding).dabPty.setLayoutParams(layoutParams26);
        ConstraintLayout.LayoutParams layoutParams27 = (ConstraintLayout.LayoutParams) ((FragmentDabBinding) this.dataBinding).dabDls.getLayoutParams();
        layoutParams27.topToBottom = -1;
        layoutParams27.bottomToBottom = ((FragmentDabBinding) this.dataBinding).dabPic.getId();
        layoutParams27.setMargins(0, 0, 0, SizeUtils.dp2px(8.0f));
        ((FragmentDabBinding) this.dataBinding).dabDls.setLayoutParams(layoutParams27);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        super.onWindowModeChanged(z, i, z2, i2);
        LogUtil.e(this.TAG, "onWindowModeChanged, isMultiWindowMode = " + z + ", deviceOrientation = " + i);
        applyViewByOrientation(z, i);
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        RadioServiceWrapper.getInstance().getCurrentProgram().removeOnPropertyChangedCallback(this.onPropertyChangedCallback);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMediaControlNavi(MediaControlNaviBean mediaControlNaviBean) {
        checkNavi();
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        checkNavi();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0035  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkNavi() {
        /*
            r5 = this;
            boolean r0 = r5.isOnResume
            if (r0 == 0) goto L53
            int r0 = com.byd.music.OnLineMusic.NAVI_TYPE
            r1 = 9
            if (r0 != r1) goto L53
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            if (r0 == 0) goto L53
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            int r1 = r0.hashCode()
            r2 = -1785238953(0xffffffff95976a57, float:-6.115619E-26)
            r3 = -1
            r4 = 1
            if (r1 == r2) goto L2b
            r2 = 1798032508(0x6b2bcc7c, float:2.0769197E26)
            if (r1 == r2) goto L21
            goto L35
        L21:
            java.lang.String r1 = "all_list"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = 0
            goto L36
        L2b:
            java.lang.String r1 = "favorites"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L35
            r0 = r4
            goto L36
        L35:
            r0 = r3
        L36:
            if (r0 == 0) goto L45
            if (r0 == r4) goto L3b
            goto L4e
        L3b:
            V extends androidx.databinding.ViewDataBinding r0 = r5.dataBinding
            com.byd.music.databinding.FragmentDabBinding r0 = (com.byd.music.databinding.FragmentDabBinding) r0
            android.widget.RadioButton r0 = r0.rbCollect
            r0.setChecked(r4)
            goto L4e
        L45:
            V extends androidx.databinding.ViewDataBinding r0 = r5.dataBinding
            com.byd.music.databinding.FragmentDabBinding r0 = (com.byd.music.databinding.FragmentDabBinding) r0
            android.widget.RadioButton r0 = r0.rbChannel
            r0.setChecked(r4)
        L4e:
            com.byd.music.OnLineMusic.NAVI_TYPE = r3
            r0 = 0
            com.byd.music.OnLineMusic.NAVI_DEST = r0
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.radio.DABFragment.checkNavi():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDabStationString() {
        RadioManager.ProgramInfo programInfo = RadioServiceWrapper.getInstance().getCurrentProgram().get();
        if (this.dataBinding == 0 || !RadioServiceWrapper.isDabProgram(programInfo)) {
            return;
        }
        ((FragmentDabBinding) this.dataBinding).dabService.setText(RadioServiceWrapper.getDabServiceName(programInfo));
        ((FragmentDabBinding) this.dataBinding).dabEnsemble.setText(RadioServiceWrapper.getDabEnsembleName(programInfo));
        if (RadioServiceWrapper.getDabPTY(programInfo) == 0) {
            ((FragmentDabBinding) this.dataBinding).dabPty.setText("");
        } else {
            ((FragmentDabBinding) this.dataBinding).dabPty.setText(RadioPTYUtil.getPTYString(RadioServiceWrapper.getDabPTY(programInfo)));
        }
        ((FragmentDabBinding) this.dataBinding).dabDls.setText(RadioServiceWrapper.getDabDLS(programInfo));
        ((FragmentDabBinding) this.dataBinding).dabService.setSelected(true);
        ((FragmentDabBinding) this.dataBinding).dabDls.setSelected(true);
        AbstractMap.SimpleEntry<Long, Integer> simpleEntry = this.dabCache;
        if (simpleEntry != null && simpleEntry.getValue().intValue() == RadioServiceWrapper.getDabArtNumber(programInfo) && this.dabCache.getKey().longValue() != RadioServiceWrapper.getDabSID(programInfo)) {
            LogUtil.d(this.TAG, "dab art cache mismatch number = " + RadioServiceWrapper.getDabArtNumber(programInfo));
            BindingUtils.setDabAlbumBitmap(((FragmentDabBinding) this.dataBinding).dabPic, RadioServiceWrapper.getDabAlbumPreferIcon(programInfo, true), R.drawable.album_default_248, RadioServiceWrapper.getDabServiceName(programInfo));
            return;
        }
        BindingUtils.setDabAlbumBitmap(((FragmentDabBinding) this.dataBinding).dabPic, RadioServiceWrapper.getDabAlbumPreferArt(programInfo), R.drawable.album_default_248, RadioServiceWrapper.getDabServiceName(programInfo));
        this.dabCache = new AbstractMap.SimpleEntry<>(Long.valueOf(RadioServiceWrapper.getDabSID(programInfo)), Integer.valueOf(RadioServiceWrapper.getDabArtNumber(programInfo)));
        LogUtil.d(this.TAG, "dab art cache replace number = " + RadioServiceWrapper.getDabArtNumber(programInfo));
    }
}
