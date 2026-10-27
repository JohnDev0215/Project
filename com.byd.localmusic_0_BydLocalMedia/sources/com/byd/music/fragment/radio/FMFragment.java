package com.byd.music.fragment.radio;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.ObservableBoolean;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseFragment;
import com.byd.music.beans.MediaControlNaviBean;
import com.byd.music.beans.RadioConfigChangeBean;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.databinding.FragmentFmBinding;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.RadioPTYUtil;
import com.byd.music.utils.binding.BindingUtils;
import com.byd.music.view.RepeatListener;
import com.byd.music.view.RulerView;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes5.dex */
public class FMFragment extends BaseFragment<FragmentFmBinding, FMViewModel> {
    public static final String KEY_IS_MIRROR = "fmfragment_is_mirror";
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
        return R.layout.fragment_fm;
    }

    @Override // com.byd.music.base.BaseFragment
    protected int viewModelId() {
        return BR.viewModel;
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initData() {
        ((FragmentFmBinding) this.dataBinding).rulerView.setValidScope(RadioServiceWrapper.FM_FREQ_MIN, RadioServiceWrapper.FM_FREQ_MAX, RadioServiceWrapper.FM_FREQ_STEP);
    }

    @Override // com.byd.music.base.BaseFragment
    protected void initView() {
        LogUtil.d(this.TAG, "initView: enter...");
        ((FragmentFmBinding) this.dataBinding).rvCollectList.setFocusableInTouchMode(false);
        ((FragmentFmBinding) this.dataBinding).rvCollectList.requestFocus();
        ((FragmentFmBinding) this.dataBinding).rvCollectList.setItemAnimator(null);
        ((FragmentFmBinding) this.dataBinding).rvChannelList.setFocusableInTouchMode(false);
        ((FragmentFmBinding) this.dataBinding).rvChannelList.requestFocus();
        ((FragmentFmBinding) this.dataBinding).rvChannelList.setItemAnimator(null);
        ((FragmentFmBinding) this.dataBinding).rvChannelList.setVerticalScrollBarEnabled(false);
        ((FragmentFmBinding) this.dataBinding).rvCollectList.setVerticalScrollBarEnabled(false);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1();
        ((FragmentFmBinding) this.dataBinding).rvCollectList.addOnScrollListener(anonymousClass1);
        ((FragmentFmBinding) this.dataBinding).rvChannelList.addOnScrollListener(anonymousClass1);
        ((FragmentFmBinding) this.dataBinding).rulerView.setOnRulerValueChangedListener(new RulerView.OnRuleValueChangeListener() { // from class: com.byd.music.fragment.radio.FMFragment.2
            @Override // com.byd.music.view.RulerView.OnRuleValueChangeListener
            public void onRulerLogicValueChange(int i) {
                LogUtil.d(FMFragment.this.TAG, "onRulerLogicValueChange: freq = " + i);
                ((FragmentFmBinding) FMFragment.this.dataBinding).programFrequency.setText(RadioServiceWrapper.parseFreqI18N(i));
                if (i == ((FMViewModel) FMFragment.this.viewModel).currentFrequency.get()) {
                    OnLineMusic.setMusicEngine(7);
                } else {
                    OnLineMusic.setMusicEngine(7, 3);
                    RadioServiceWrapper.getInstance().tune(i);
                }
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            }

            @Override // com.byd.music.view.RulerView.OnRuleValueChangeListener
            public void onRulerViewValueChange(int i) {
                LogUtil.d(FMFragment.this.TAG, "onRulerViewValueChange: freq = " + i);
                ((FragmentFmBinding) FMFragment.this.dataBinding).programFrequency.setText(RadioServiceWrapper.parseFreqI18N(i));
            }
        });
        ((FMViewModel) this.viewModel).seekClickLiveEvent.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.radio.FMFragment.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                LogUtil.d(FMFragment.this.TAG, "seekClickLiveEvent onChanged: isForeground = " + bool);
                if (bool.booleanValue()) {
                    RadioServiceWrapper.getInstance().tuneToNext();
                } else {
                    RadioServiceWrapper.getInstance().tuneToPre();
                }
            }
        });
        ((FMViewModel) this.viewModel).collectClickLiveEvent.observe(this, new Observer<Boolean>() { // from class: com.byd.music.fragment.radio.FMFragment.4
            @Override // androidx.lifecycle.Observer
            public void onChanged(Boolean bool) {
                if (bool.booleanValue()) {
                    ((FragmentFmBinding) FMFragment.this.dataBinding).rvCollectList.smoothScrollToPosition(0);
                    if (((FragmentFmBinding) FMFragment.this.dataBinding).collectLottie != null) {
                        ((FragmentFmBinding) FMFragment.this.dataBinding).collectLottie.startAnimation(true);
                    }
                }
            }
        });
        ((FragmentFmBinding) this.dataBinding).topSetting.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$QeW6AMWrZs850BliqlVeaO7En-M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$0$FMFragment(view);
            }
        });
        ((FragmentFmBinding) this.dataBinding).topMirror.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$Q2HZyTTWSaUWUkiv1RpMjI5C2nY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$1$FMFragment(view);
            }
        });
        if (RadioServiceWrapper.getInstance().getBgScanEnable()) {
            ((FragmentFmBinding) this.dataBinding).topSearch.setVisibility(8);
        } else {
            ((FragmentFmBinding) this.dataBinding).topSearch.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$dyASazjriItVn02eNzcS-DoZd2w
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$initView$2$FMFragment(view);
                }
            });
        }
        ((FragmentFmBinding) this.dataBinding).rbgProgramList.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.music.fragment.radio.FMFragment.5
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbCollect) {
                    ((FMViewModel) FMFragment.this.viewModel).isCollectShow.set(true);
                } else {
                    ((FMViewModel) FMFragment.this.viewModel).isCollectShow.set(false);
                }
            }
        });
        ((FragmentFmBinding) this.dataBinding).btnNext.setOnTouchListener(new RepeatListener(500, 300, new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$ebrvDf6bSbtuWAbO683_6lLyv_c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$3$FMFragment(view);
            }
        }));
        ((FragmentFmBinding) this.dataBinding).btnPre.setOnTouchListener(new RepeatListener(500, 300, new View.OnClickListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$numSEB1KUNsfGTE0R_KWCssBrpQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initView$4$FMFragment(view);
            }
        }));
        refreshFM(RadioServiceWrapper.getInstance().currentFMRadioDBBean);
        RadioServiceWrapper.getInstance().mCurrentRadio.observe(this, new Observer() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$G2-ASxdYNFQ7pK7xLGeA9laZ7vQ
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$initView$5$FMFragment((RadioDBBean) obj);
            }
        });
        applyViewByOrientation(this.lastIsMultiMode, this.lastDeviceOrientation);
    }

    /* JADX INFO: renamed from: com.byd.music.fragment.radio.FMFragment$1, reason: invalid class name */
    class AnonymousClass1 extends RecyclerView.OnScrollListener {
        AnonymousClass1() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(final RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
            recyclerView.setVerticalScrollBarEnabled(true);
            if (i == 0) {
                FMFragment.this.finalScroll = System.currentTimeMillis();
                new Handler().postDelayed(new Runnable() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMFragment$1$AS7eoENO6IEOb6rKkufdUELgiA8
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onScrollStateChanged$0$FMFragment$1(recyclerView);
                    }
                }, 1500L);
            }
        }

        public /* synthetic */ void lambda$onScrollStateChanged$0$FMFragment$1(RecyclerView recyclerView) {
            try {
                if (recyclerView.getScrollState() != 0 || (System.currentTimeMillis() - FMFragment.this.finalScroll) - 1400 <= 0) {
                    return;
                }
                recyclerView.setVerticalScrollBarEnabled(false);
            } catch (Throwable unused) {
            }
        }
    }

    public /* synthetic */ void lambda$initView$0$FMFragment(View view) {
        LogUtil.d(this.TAG, "setOnClickListener topSetting clicked...");
        FragmentUtils.replace((Fragment) this, (Fragment) new FMSettingFragment(), true);
    }

    public /* synthetic */ void lambda$initView$1$FMFragment(View view) {
        LogUtil.d(this.TAG, "setOnClickListener topMirror clicked...");
        this.isMirror.set(!r3.get());
        SPUtils.getInstance().put(KEY_IS_MIRROR, this.isMirror.get());
        refreshMirror();
    }

    public /* synthetic */ void lambda$initView$2$FMFragment(View view) {
        LogUtil.d(this.TAG, "setOnClickListener topSearch clicked ... go to updateProgramList");
        if (DeviceInfoUtils.isInCall()) {
            return;
        }
        OnLineMusic.setMusicEngine(7);
        RadioServiceWrapper.getInstance().updateSingleSourceProgramList();
    }

    public /* synthetic */ void lambda$initView$3$FMFragment(View view) {
        ((FMViewModel) this.viewModel).btnAjdFreq(1);
    }

    public /* synthetic */ void lambda$initView$4$FMFragment(View view) {
        ((FMViewModel) this.viewModel).btnAjdFreq(-1);
    }

    public /* synthetic */ void lambda$initView$5$FMFragment(RadioDBBean radioDBBean) {
        LogUtil.d(this.TAG, "mCurrentRadio: observe value = " + radioDBBean);
        refreshFM(radioDBBean);
    }

    private void applyViewByOrientation(boolean z, int i) {
        if (z) {
            ((FragmentFmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(15.0f));
            ((FragmentFmBinding) this.dataBinding).topMirror.setVisibility(8);
            ((FragmentFmBinding) this.dataBinding).rvChannelList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ((FragmentFmBinding) this.dataBinding).rvCollectList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams.topMargin = SizeUtils.dp2px(170.0f);
            ((FragmentFmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams);
            ViewGroup.LayoutParams layoutParams2 = ((FragmentFmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = SizeUtils.dp2px(298.0f);
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams2);
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(15.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(15.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams3 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams3.width = -1;
            layoutParams3.height = -1;
            layoutParams3.topToTop = -1;
            layoutParams3.bottomToBottom = 0;
            layoutParams3.topToBottom = ((FragmentFmBinding) this.dataBinding).groupPlayingCard.getId();
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams3);
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(70.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams4.height = -2;
            layoutParams4.bottomToBottom = -1;
            ((FragmentFmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams4);
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams5.height = -1;
            layoutParams5.bottomToBottom = -1;
            ((FragmentFmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams5);
        } else if (i == 2) {
            ((FragmentFmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(90.0f));
            ((FragmentFmBinding) this.dataBinding).topMirror.setVisibility(0);
            ((FragmentFmBinding) this.dataBinding).rvChannelList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ((FragmentFmBinding) this.dataBinding).rvCollectList.setPadding(SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(0.0f));
            ViewGroup.LayoutParams layoutParams6 = ((FragmentFmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams6.height = -1;
            layoutParams6.width = SizeUtils.dp2px(650.0f);
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams6);
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams7 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams7.width = SizeUtils.dp2px(350.0f);
            layoutParams7.height = SizeUtils.dp2px(476.0f);
            layoutParams7.bottomToBottom = -1;
            layoutParams7.topToBottom = -1;
            layoutParams7.topToTop = 0;
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams7);
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams8 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams8.topMargin = SizeUtils.dp2px(230.0f);
            ((FragmentFmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams8);
            ConstraintLayout.LayoutParams layoutParams9 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams9.height = SizeUtils.dp2px(0.0f);
            layoutParams9.bottomToBottom = 0;
            ((FragmentFmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams9);
            ConstraintLayout.LayoutParams layoutParams10 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams10.height = SizeUtils.dp2px(0.0f);
            layoutParams10.bottomToBottom = 0;
            ((FragmentFmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams10);
        } else {
            ((FragmentFmBinding) this.dataBinding).rootContainer.setPadding(SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f), SizeUtils.dp2px(35.0f));
            ((FragmentFmBinding) this.dataBinding).topMirror.setVisibility(8);
            ConstraintLayout.LayoutParams layoutParams11 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).rulerView.getLayoutParams();
            layoutParams11.topMargin = SizeUtils.dp2px(170.0f);
            ((FragmentFmBinding) this.dataBinding).rulerView.setLayoutParams(layoutParams11);
            ViewGroup.LayoutParams layoutParams12 = ((FragmentFmBinding) this.dataBinding).groupPlayingCard.getLayoutParams();
            layoutParams12.height = SizeUtils.dp2px(300.0f);
            layoutParams12.width = -1;
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setLayoutParams(layoutParams12);
            ((FragmentFmBinding) this.dataBinding).groupPlayingCard.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(0.0f));
            ConstraintLayout.LayoutParams layoutParams13 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).groupProgramList.getLayoutParams();
            layoutParams13.width = -1;
            layoutParams13.height = SizeUtils.dp2px(614.0f);
            layoutParams13.topToTop = -1;
            layoutParams13.bottomToBottom = -1;
            layoutParams13.topToBottom = ((FragmentFmBinding) this.dataBinding).groupPlayingCard.getId();
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setLayoutParams(layoutParams13);
            ((FragmentFmBinding) this.dataBinding).groupProgramList.setPadding(SizeUtils.dp2px(10.0f), SizeUtils.dp2px(70.0f), SizeUtils.dp2px(10.0f), SizeUtils.dp2px(100.0f));
            ConstraintLayout.LayoutParams layoutParams14 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).channelListContainer.getLayoutParams();
            layoutParams14.height = SizeUtils.dp2px(0.0f);
            layoutParams14.bottomToBottom = 0;
            ((FragmentFmBinding) this.dataBinding).channelListContainer.setLayoutParams(layoutParams14);
            ConstraintLayout.LayoutParams layoutParams15 = (ConstraintLayout.LayoutParams) ((FragmentFmBinding) this.dataBinding).collectListContainer.getLayoutParams();
            layoutParams15.height = SizeUtils.dp2px(0.0f);
            layoutParams15.bottomToBottom = 0;
            ((FragmentFmBinding) this.dataBinding).collectListContainer.setLayoutParams(layoutParams15);
        }
        refreshMirror();
    }

    @Override // com.byd.music.base.BaseFragment
    protected void onWindowModeChanged(boolean z, int i, boolean z2, int i2) {
        super.onWindowModeChanged(z, i, z2, i2);
        LogUtil.e(this.TAG, "onWindowModeChanged, isMultiWindowMode = " + z + ", deviceOrientation = " + i);
        applyViewByOrientation(z, i);
    }

    private void refreshFM(RadioDBBean radioDBBean) {
        LogUtil.d(this.TAG, "refreshFM, mCurrentRadio: observe value = " + radioDBBean);
        if (this.dataBinding == 0 || radioDBBean == null || !RadioServiceWrapper.isFmBean(radioDBBean)) {
            return;
        }
        ((FMViewModel) this.viewModel).updateCurrentFrequency(radioDBBean.getRadioFrequency(), radioDBBean.getRDS_PI());
        ((FragmentFmBinding) this.dataBinding).rulerView.setRulerValue(radioDBBean.getRadioFrequency());
        ((FragmentFmBinding) this.dataBinding).programFrequency.setText(RadioServiceWrapper.parseFreqI18N(radioDBBean.getRadioFrequency()));
        ((FragmentFmBinding) this.dataBinding).RDSPS.setText(radioDBBean.getRDS_PS());
        ((FragmentFmBinding) this.dataBinding).RDSFreq.setText(RadioServiceWrapper.parseFreqI18N(radioDBBean.getRadioFrequency()) + " MHz");
        ((FragmentFmBinding) this.dataBinding).RDSPTY.setText(RadioPTYUtil.getPTYString(RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getRDS_PTY()));
        ((FragmentFmBinding) this.dataBinding).RDSRT.setText(radioDBBean.getRDS_RT());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveMediaControlNavi(MediaControlNaviBean mediaControlNaviBean) {
        checkNavi();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveRadioConfigChange(RadioConfigChangeBean radioConfigChangeBean) {
        ((FragmentFmBinding) this.dataBinding).rulerView.setValidScope(RadioServiceWrapper.FM_FREQ_MIN, RadioServiceWrapper.FM_FREQ_MAX, RadioServiceWrapper.FM_FREQ_STEP);
        refreshFM(RadioServiceWrapper.getInstance().currentFMRadioDBBean);
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

    /* JADX WARN: Removed duplicated region for block: B:19:0x0034  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void checkNavi() {
        /*
            r5 = this;
            boolean r0 = r5.isOnResume
            if (r0 == 0) goto L52
            int r0 = com.byd.music.OnLineMusic.NAVI_TYPE
            r1 = 7
            if (r0 != r1) goto L52
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            if (r0 == 0) goto L52
            java.lang.String r0 = com.byd.music.OnLineMusic.NAVI_DEST
            int r1 = r0.hashCode()
            r2 = -1785238953(0xffffffff95976a57, float:-6.115619E-26)
            r3 = -1
            r4 = 1
            if (r1 == r2) goto L2a
            r2 = 1798032508(0x6b2bcc7c, float:2.0769197E26)
            if (r1 == r2) goto L20
            goto L34
        L20:
            java.lang.String r1 = "all_list"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L34
            r0 = 0
            goto L35
        L2a:
            java.lang.String r1 = "favorites"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L34
            r0 = r4
            goto L35
        L34:
            r0 = r3
        L35:
            if (r0 == 0) goto L44
            if (r0 == r4) goto L3a
            goto L4d
        L3a:
            V extends androidx.databinding.ViewDataBinding r0 = r5.dataBinding
            com.byd.music.databinding.FragmentFmBinding r0 = (com.byd.music.databinding.FragmentFmBinding) r0
            android.widget.RadioButton r0 = r0.rbCollect
            r0.setChecked(r4)
            goto L4d
        L44:
            V extends androidx.databinding.ViewDataBinding r0 = r5.dataBinding
            com.byd.music.databinding.FragmentFmBinding r0 = (com.byd.music.databinding.FragmentFmBinding) r0
            android.widget.RadioButton r0 = r0.rbChannel
            r0.setChecked(r4)
        L4d:
            com.byd.music.OnLineMusic.NAVI_TYPE = r3
            r0 = 0
            com.byd.music.OnLineMusic.NAVI_DEST = r0
        L52:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.music.fragment.radio.FMFragment.checkNavi():void");
    }

    private void refreshMirror() {
        if (((FragmentFmBinding) this.dataBinding).topMirror.getVisibility() != 0) {
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 0);
            return;
        }
        if (this.isMirror.get()) {
            if (((FMViewModel) this.viewModel).isRight.get()) {
                BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupPlayingCard, 1, 0, 0, 0);
                BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 1);
            } else {
                BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 1);
                BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupProgramList, 1, 0, 0, 0);
            }
            ((FragmentFmBinding) this.dataBinding).topMirror.setImageResource(R.drawable.icon_radio_mirror_selected);
            return;
        }
        if (((FMViewModel) this.viewModel).isRight.get()) {
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupPlayingCard, 0, 0, 0, 1);
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupProgramList, 1, 0, 0, 0);
        } else {
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupPlayingCard, 1, 0, 0, 0);
            BindingUtils.setConstraintCustom(((FragmentFmBinding) this.dataBinding).groupProgramList, 0, 0, 0, 1);
        }
        ((FragmentFmBinding) this.dataBinding).topMirror.setImageResource(R.drawable.icon_radio_mirror);
    }

    @Override // com.byd.music.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.d(this.TAG, "onDestroyView: ");
    }
}
