package com.byd.music.fragment.radio;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.beans.MediaControlNaviBean;
import com.byd.music.beans.RadioConfigChangeBean;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.databinding.FragmentAmBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.RepeatListener;
import com.byd.music.view.RulerView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class AMFragment extends BaseFragment<FragmentAmBinding, AMViewModel> {
    public static final String KEY_IS_MIRROR = "amfragment_is_mirror";
    ObservableBoolean isMirror = new ObservableBoolean(SPUtils.getInstance().getBoolean(KEY_IS_MIRROR, false));
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
        return R.layout.fragment_am;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        ((FragmentAmBinding) this.dataBinding).rulerView.setValidScope(RadioServiceWrapper.AM_FREQ_MIN, RadioServiceWrapper.AM_FREQ_MAX, RadioServiceWrapper.AM_FREQ_STEP);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        ((FragmentAmBinding) this.dataBinding).rvCollectList.setFocusableInTouchMode(false);
        ((FragmentAmBinding) this.dataBinding).rvCollectList.requestFocus();
        ((FragmentAmBinding) this.dataBinding).rvCollectList.setItemAnimator(null);
        ((FragmentAmBinding) this.dataBinding).rvChannelList.setFocusableInTouchMode(false);
        ((FragmentAmBinding) this.dataBinding).rvChannelList.requestFocus();
        ((FragmentAmBinding) this.dataBinding).rvChannelList.setItemAnimator(null);
        ((FragmentAmBinding) this.dataBinding).rvChannelList.setVerticalScrollBarEnabled(false);
        ((FragmentAmBinding) this.dataBinding).rvCollectList.setVerticalScrollBarEnabled(false);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        ((FragmentAmBinding) this.dataBinding).rvCollectList.addOnScrollListener(anonymousClass1);
        ((FragmentAmBinding) this.dataBinding).rvChannelList.addOnScrollListener(anonymousClass1);
        ((FragmentAmBinding) this.dataBinding).rulerView.setOnRulerValueChangedListener(new RulerView.OnRuleValueChangeListener() { // from class: com.byd.music.fragment.radio.AMFragment.2
            @Override // com.byd.music.view.RulerView.OnRuleValueChangeListener
            public void onRulerLogicValueChange(int i) {
                LogUtil.d(AMFragment.this.TAG, "onRulerLogicValueChange: freq = " + i);
                ((FragmentAmBinding) AMFragment.this.dataBinding).programFrequency.setText(i + "");
                if (i == ((AMViewModel) AMFragment.this.viewModel).currentFrequency.get()) {
                    OnLineMusic.setMusicEngine(8);
                } else {
                    OnLineMusic.setMusicEngine(8, 3);
                    RadioServiceWrapper.getInstance().tune(i);
                }
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            }

            @Override // com.byd.music.view.RulerView.OnRuleValueChangeListener
            public void onRulerViewValueChange(int i) {
                LogUtil.d(AMFragment.this.TAG, "onRulerViewValueChange: freq = " + i);
                ((FragmentAmBinding) AMFragment.this.dataBinding).programFrequency.setText(i + "");
            }
        });
        ((AMViewModel) this.viewModel).seekClickLiveEvent.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.radio.AMFragment.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d(AMFragment.this.TAG, "seekClickLiveEvent onChanged: isForeground = " + bool);
                try {
                    if (bool.booleanValue()) {
                        RadioServiceWrapper.getInstance().tuneToNext();
                    } else {
                        RadioServiceWrapper.getInstance().tuneToPre();
                    }
                } catch (Throwable th) {
                    LogUtil.e(AMFragment.this.TAG, "onChanged: seek with fatal error in AM Module!");
                    th.printStackTrace();
                }
            }
        });
        ((FragmentAmBinding) this.dataBinding).btnNext.setOnTouchListener(new RepeatListener(500, 300, new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMFragment$V8AUhCan4TDj1zgsNzzDLo8rWgA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$AMFragment(view);
            }
        }));
        ((FragmentAmBinding) this.dataBinding).btnPre.setOnTouchListener(new RepeatListener(500, 300, new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMFragment$YEtXPu3FWreXYAKkdY9oEW_pUFw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$1$AMFragment(view);
            }
        }));
        ((FragmentAmBinding) this.dataBinding).topMirror.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.AMFragment.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d(AMFragment.this.TAG, "setOnClickListener topMirror clicked...");
                AMFragment.this.isMirror.set(!AMFragment.this.isMirror.get());
                SPUtils.getInstance().put(AMFragment.KEY_IS_MIRROR, AMFragment.this.isMirror.get());
                AMFragment.this.refreshMirror();
            }
        });
        if (RadioServiceWrapper.getInstance().getBgScanEnable()) {
            ((FragmentAmBinding) this.dataBinding).topSearch.setVisibility(8);
        } else {
            ((FragmentAmBinding) this.dataBinding).topSearch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.AMFragment.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d(AMFragment.this.TAG, "setOnClickListener topSearch clicked...");
                    if (DeviceInfoUtils.isInCall()) {
                        return;
                    }
                    OnLineMusic.setMusicEngine(8);
                    RadioServiceWrapper.getInstance().updateSingleSourceProgramList();
                }
            });
        }
        ((FragmentAmBinding) this.dataBinding).rbgProgramList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.music.fragment.radio.AMFragment.6
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbCollect) {
                    ((AMViewModel) AMFragment.this.viewModel).isCollectShow.set(true);
                } else {
                    ((AMViewModel) AMFragment.this.viewModel).isCollectShow.set(false);
                }
            }
        });
        RadioServiceWrapper.getInstance().mCurrentRadio.observe(this, new Observer<RadioDBBean>() { // from class: com.byd.music.fragment.radio.AMFragment.7
            @Override // androidx.lifecycle.Observer
            public void onChanged(RadioDBBean radioDBBean) {
                LogUtil.d(AMFragment.this.TAG, "mCurrentRadio: observe value = " + radioDBBean);
                AMFragment.this.refreshAM(radioDBBean);
            }
        });
        ((AMViewModel) this.viewModel).collectClickLiveEvent.observe(this, new Observer() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMFragment$c-T2VWB3efAUaFuswaCZP3UcwN4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$2$AMFragment((Boolean) obj);
            }
        });
        refreshAM(RadioServiceWrapper.getInstance().currentAmRadioDBBean);
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
    }

    /* JADX INFO: renamed from: com.byd.music.fragment.radio.AMFragment$1, reason: invalid class name */
    class AnonymousClass1 extends RecyclerView.OnScrollListener {
        AnonymousClass1() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(final RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            recyclerView.setVerticalScrollBarEnabled(true);
            if (i == 0) {
                AMFragment.this.finalScroll = System.currentTimeMillis();
                new Handler().postDelayed(new Runnable() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMFragment$1$Nd0ireY_17BbAPFv38iMjtnaPyk
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onScrollStateChanged$0$AMFragment$1(recyclerView);
                    }
                }, 1500L);
            }
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$AMFragment$1(RecyclerView recyclerView) {
            try {
                if (recyclerView.getScrollState() != 0 || (System.currentTimeMillis() - AMFragment.this.finalScroll) - 1400 <= 0) {
                    return;
                }
                recyclerView.setVerticalScrollBarEnabled(false);
            } catch (Throwable unused) {
            }
        }
    }

    public /* synthetic */ void lambda$initView$0$AMFragment(View view) {
        ((AMViewModel) this.viewModel).btnAjdFreq(1);
    }

    public /* synthetic */ void lambda$initView$1$AMFragment(View view) {
        ((AMViewModel) this.viewModel).btnAjdFreq(-1);
    }

    public /* synthetic */ void lambda$initView$2$AMFragment(Boolean bool) {
        if (bool.booleanValue()) {
            ((FragmentAmBinding) this.dataBinding).rvCollectList.smoothScrollToPosition(0);
            if (((FragmentAmBinding) this.dataBinding).collectLottie != null) {
                ((FragmentAmBinding) this.dataBinding).collectLottie.startAnimation(true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshAM(RadioDBBean radioDBBean) {
        LogUtil.d(this.TAG, "refreshAM, mCurrentRadio: observe value = " + radioDBBean);
        if (this.dataBinding == 0 || radioDBBean == null || !RadioServiceWrapper.isAmBean(radioDBBean)) {
            return;
        }
        ((AMViewModel) this.viewModel).updateCurrentFrequency(radioDBBean.getRadioFrequency());
        ((FragmentAmBinding) this.dataBinding).rulerView.setRulerValue(radioDBBean.getRadioFrequency());
        ((FragmentAmBinding) this.dataBinding).programFrequency.setText(radioDBBean.getRadioFrequency() + "");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMediaControlNavi(MediaControlNaviBean mediaControlNaviBean) {
        checkNavi();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveRadioConfigChange(RadioConfigChangeBean radioConfigChangeBean) {
        ((FragmentAmBinding) this.dataBinding).rulerView.setValidScope(RadioServiceWrapper.AM_FREQ_MIN, RadioServiceWrapper.AM_FREQ_MAX, RadioServiceWrapper.AM_FREQ_STEP);
        refreshAM(RadioServiceWrapper.getInstance().currentAmRadioDBBean);
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (z) {
            ((FragmentAmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f));
            ((FragmentAmBinding) this.dataBinding).topMirror.setVisibility(8);
            ((FragmentAmBinding) this.dataBinding).rvChannelList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ((FragmentAmBinding) this.dataBinding).rvCollectList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams.topMargin = SizeUtils.dp2px(170.0f);
            ((FragmentAmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = ((FragmentAmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = SizeUtils.dp2px(298.0f);
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams2);
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams3.width = -1;
            layoutParams3.height = -1;
            layoutParams3.topToTop = -1;
            layoutParams3.bottomToBottom = 0;
            layoutParams3.topToBottom = ((FragmentAmBinding) this.dataBinding).groupPlayingCard.getId();
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams3);
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(70.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams4.height = -2;
            layoutParams4.bottomToBottom = -1;
            ((FragmentAmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams4);
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams5.height = -1;
            layoutParams5.bottomToBottom = -1;
            ((FragmentAmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams5);
        } else if (i == 2) {
            ((FragmentAmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(90.0f));
            ((FragmentAmBinding) this.dataBinding).topMirror.setVisibility(0);
            ((FragmentAmBinding) this.dataBinding).rvChannelList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ((FragmentAmBinding) this.dataBinding).rvCollectList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ViewGroup.LayoutParams layoutParams6 = ((FragmentAmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams6.height = -1;
            layoutParams6.width = SizeUtils.dp2px(650.0f);
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams6);
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams7.width = SizeUtils.dp2px(350.0f);
            layoutParams7.height = SizeUtils.dp2px(476.0f);
            layoutParams7.bottomToBottom = -1;
            layoutParams7.topToBottom = -1;
            layoutParams7.topToTop = 0;
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams7);
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams8 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams8.topMargin = SizeUtils.dp2px(230.0f);
            ((FragmentAmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams8);
            ConstraintLayout.LayoutParams layoutParams9 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams9.height = SizeUtils.dp2px(0.0f);
            layoutParams9.bottomToBottom = 0;
            ((FragmentAmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams9);
            ConstraintLayout.LayoutParams layoutParams10 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams10.height = SizeUtils.dp2px(0.0f);
            layoutParams10.bottomToBottom = 0;
            ((FragmentAmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams10);
        } else {
            ((FragmentAmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f));
            ((FragmentAmBinding) this.dataBinding).topMirror.setVisibility(8);
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams11.topMargin = SizeUtils.dp2px(170.0f);
            ((FragmentAmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams11);
            ViewGroup.LayoutParams layoutParams12 = ((FragmentAmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams12.height = SizeUtils.dp2px(300.0f);
            layoutParams12.width = -1;
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams12);
            ((FragmentAmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams13 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams13.width = -1;
            layoutParams13.height = SizeUtils.dp2px(614.0f);
            layoutParams13.topToTop = -1;
            layoutParams13.bottomToBottom = -1;
            layoutParams13.topToBottom = ((FragmentAmBinding) this.dataBinding).groupPlayingCard.getId();
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams13);
            ((FragmentAmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(70.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(100.0f));
            ConstraintLayout.LayoutParams layoutParams14 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams14.height = SizeUtils.dp2px(0.0f);
            layoutParams14.bottomToBottom = 0;
            ((FragmentAmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams14);
            ConstraintLayout.LayoutParams layoutParams15 = (ConstraintLayout.LayoutParams) ((FragmentAmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams15.height = SizeUtils.dp2px(0.0f);
            layoutParams15.bottomToBottom = 0;
            ((FragmentAmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams15);
        }
        refreshMirror();
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        super.onWindowModeChanged(z, i, z2, i2);
        LogUtil.e(this.TAG, "onWindowModeChanged, isMultiWindowMode = " + z + ", deviceOrientation = " + i);
        applyViewByOrientation(z, i);
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        checkNavi();
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
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
            r1 = 8
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
            com.byd.music.databinding.FragmentAmBinding r0 = (com.byd.music.databinding.FragmentAmBinding) r0
            android.widget.RadioButton r0 = r0.rbCollect
            r0.setChecked(r4)
            goto L4e
        L45:
            V extends androidx.databinding.ViewDataBinding r0 = r5.dataBinding
            com.byd.music.databinding.FragmentAmBinding r0 = (com.byd.music.databinding.FragmentAmBinding) r0
            android.widget.RadioButton r0 = r0.rbChannel
            r0.setChecked(r4)
        L4e:
            com.byd.music.OnLineMusic.NAVI_TYPE = r3
            r0 = 0
            com.byd.music.OnLineMusic.NAVI_DEST = r0
        L53:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.radio.AMFragment.checkNavi():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshMirror() {
        if (((FragmentAmBinding) this.dataBinding).topMirror.getVisibility() != 0) {
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 0);
            return;
        }
        if (this.isMirror.get()) {
            if (((AMViewModel) this.viewModel).isRight.get()) {
                BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupPlayingCard, 1, 0, 0, 0);
                BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 1);
            } else {
                BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 1);
                BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupProgramList, 1, 0, 0, 0);
            }
            ((FragmentAmBinding) this.dataBinding).topMirror.setImageResource(R.drawable.icon_radio_mirror_selected);
            return;
        }
        if (((AMViewModel) this.viewModel).isRight.get()) {
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 1);
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupProgramList, 1, 0, 0, 0);
        } else {
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupPlayingCard, 1, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentAmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 1);
        }
        ((FragmentAmBinding) this.dataBinding).topMirror.setImageResource(R.drawable.icon_radio_mirror);
    }
}
