package com.byd.vrassistant.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.byd.vrassistant.ContextHolder;
import com.byd.vrassistant.base.ConstraintLayoutLifecycle;
import com.byd.vrassistant.bean.AnimState;
import com.byd.vrassistant.bean.NoticeState;
import com.byd.vrassistant.bean.VoiceBarType;
import com.byd.vrassistant.bean.VrContent;
import com.byd.vrassistant.bean.VrExInfo;
import com.byd.vrassistant.bean.WaveStateWithArea;
import com.byd.vrassistant.bridge.BridgeHelper;
import com.byd.vrassistant.livedata.LiveDataUtils;
import com.byd.vrassistant.ui.R;
import com.byd.vrassistant.utils.RLog;
import com.byd.vrassistant.viewmodel.AnimViewModel;
import com.byd.vrassistant.viewmodel.CommonViewModel;
import com.byd.vrassistant.viewmodel.ViewManagerViewModel;
import com.byd.vrassistant.viewmodel.ViewModelHelper;
import com.byd.vrassistant.viewmodel.VoiceBarViewModel;
import java.util.Locale;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public class VoiceBar extends ConstraintLayoutLifecycle {
    private static final String TAG = "NEU_VoiceBar";
    AnimViewModel animViewModel;
    private ConstraintLayout clTaichi;
    CommonViewModel commonViewModel;
    private ConstraintLayout flContainer;
    private ImageView ivCLose;
    private ImageView ivNotice;
    private IvTaiji ivTaiji;
    private IvWave ivWave;
    private VrContent lastVrContent;
    private VrTextView mTvInput;
    private ResTextView mTvResponse;
    private ViewGroup vgNet;
    ViewManagerViewModel viewManagerViewModel;
    VoiceBarViewModel voiceBarViewModel;

    public VoiceBar(Context context) {
        super(context);
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_voice_interact, (ViewGroup) this, true);
        this.clTaichi = (ConstraintLayout) findViewById(R.id.constraint_layout);
        this.flContainer = (ConstraintLayout) findViewById(R.id.fl_container);
        this.vgNet = (ViewGroup) findViewById(R.id.fl_net_err);
        this.mTvInput = (VrTextView) findViewById(R.id.tv_input);
        this.mTvResponse = (ResTextView) findViewById(R.id.tv_response);
        this.ivNotice = (ImageView) findViewById(R.id.iv_notice);
        this.ivCLose = (ImageView) findViewById(R.id.iv_close);
        findViewById(R.id.v_click).setOnClickListener(new View.OnClickListener() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewStateManger.get().executeCancelPtt();
            }
        });
        this.ivTaiji = (IvTaiji) findViewById(R.id.iv_taiji);
        this.ivWave = (IvWave) findViewById(R.id.iv_txt_bg);
        setContentDescription("voiceBar");
        this.mTvInput.setContext(ContextHolder.getApplicationContext());
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle
    protected void initViewModel() {
        this.commonViewModel = (CommonViewModel) ViewModelHelper.getAppViewModel(CommonViewModel.class);
        this.viewManagerViewModel = (ViewManagerViewModel) ViewModelHelper.getAppViewModel(ViewManagerViewModel.class);
        this.voiceBarViewModel = (VoiceBarViewModel) ViewModelHelper.getAppViewModel(VoiceBarViewModel.class);
        this.animViewModel = (AnimViewModel) ViewModelHelper.getAppViewModel(AnimViewModel.class);
        LiveData<Context> contextLiveData = this.commonViewModel.getContextLiveData();
        final VrTextView vrTextView = this.mTvInput;
        Objects.requireNonNull(vrTextView);
        contextLiveData.observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                vrTextView.setContext((Context) obj);
            }
        });
        this.commonViewModel.getSkinType().observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setSkinType(((Integer) obj).intValue());
            }
        });
        this.voiceBarViewModel.getVrContentLiveData().observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws Throwable {
                this.f$0.setVrContent((VrContent) obj);
            }
        });
        this.voiceBarViewModel.getVrExInfoLd().observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setVrExInfo((VrExInfo) obj);
            }
        });
        Transformations.distinctUntilChanged(LiveDataUtils.zip(this.commonViewModel.getLocaleLiveData(), this.voiceBarViewModel.getVrExInfoLd(), new LiveDataUtils.Zipper() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda5
            @Override // com.byd.vrassistant.livedata.LiveDataUtils.Zipper
            public final Object apply(Object obj, Object obj2) {
                return VoiceBar.lambda$initViewModel$1((Locale) obj, (VrExInfo) obj2);
            }
        })).observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.m207lambda$initViewModel$2$combydvrassistantviewVoiceBar((Pair) obj);
            }
        });
        LiveData<AnimState> animStateLd = this.animViewModel.getAnimStateLd();
        final IvTaiji ivTaiji = this.ivTaiji;
        Objects.requireNonNull(ivTaiji);
        animStateLd.observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ivTaiji.setAnimState((AnimState) obj);
            }
        });
        LiveData<WaveStateWithArea> waveStateLd = this.animViewModel.getWaveStateLd();
        final IvWave ivWave = this.ivWave;
        Objects.requireNonNull(ivWave);
        waveStateLd.observe(this, new Observer() { // from class: com.byd.vrassistant.view.VoiceBar$$ExternalSyntheticLambda8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ivWave.setWaveState((WaveStateWithArea) obj);
            }
        });
    }

    static /* synthetic */ Pair lambda$initViewModel$1(Locale locale, VrExInfo vrExInfo) {
        boolean z = true;
        if (!vrExInfo.getArea().contains("FRONT") ? vrExInfo.getArea().contains("LEFT") : !BridgeHelper.getVrAreaStatus().isRightDriver()) {
            z = false;
        }
        return new Pair(Integer.valueOf(TextUtils.getLayoutDirectionFromLocale(locale)), Boolean.valueOf(z));
    }

    /* JADX INFO: renamed from: lambda$initViewModel$2$com-byd-vrassistant-view-VoiceBar, reason: not valid java name */
    /* synthetic */ void m207lambda$initViewModel$2$combydvrassistantviewVoiceBar(Pair pair) {
        RLog.i(TAG, "initViewModel: view direction = " + pair);
        this.flContainer.setLayoutDirection(((Integer) pair.first).intValue());
        this.mTvResponse.setGravity(((Integer) pair.first).intValue() == 1 ? 5 : 3);
        this.vgNet.setLayoutDirection(((Integer) pair.first).intValue());
        if (((Boolean) pair.second).booleanValue()) {
            this.clTaichi.setLayoutDirection(1);
        } else {
            this.clTaichi.setLayoutDirection(0);
        }
        this.mTvInput.setTextDirection(5);
    }

    public void setSkinType(int i) {
        RLog.i(TAG, "setSkin: skin =" + i);
        this.mTvInput.setSkin(i);
        if (i == 1) {
            this.ivCLose.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_close_light));
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_voicebar_light));
        } else {
            if (i != 2) {
                return;
            }
            this.ivCLose.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.icon_close_dark));
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.bg_voicebar_dark));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVrExInfo(VrExInfo vrExInfo) {
        RLog.i(TAG, "setVrExInfo: " + vrExInfo);
        if (!vrExInfo.isNetAvailable()) {
            setNoticeImgState(NoticeState.NET_OFF);
        } else {
            setNoticeImgState(NoticeState.getIfPresent(vrExInfo.getArea()));
        }
    }

    public void setNoticeImgState(NoticeState noticeState) {
        if (noticeState == null) {
            RLog.e(TAG, "setNoticeImgState: state = null");
        } else {
            this.ivNotice.setImageResource(noticeState.getDrawableRes());
        }
    }

    public void setVrContent(VrContent vrContent) throws Throwable {
        if (vrContent.equals(this.lastVrContent)) {
            return;
        }
        Log.i(TAG, "setVrContent: vrContent=" + vrContent);
        VrContent vrContentParcelClone = vrContent.parcelClone();
        this.lastVrContent = vrContentParcelClone;
        this.mTvInput.setVrContent(vrContentParcelClone);
        this.mTvResponse.setText(this.lastVrContent.getResponse());
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle, android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        BridgeHelper.getVrUiStatus().onVrShowEnd(VoiceBarType.NORMAL);
    }

    @Override // com.byd.vrassistant.base.ConstraintLayoutLifecycle, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BridgeHelper.getVrUiStatus().onVrDismissEnd(VoiceBarType.NORMAL);
    }
}
