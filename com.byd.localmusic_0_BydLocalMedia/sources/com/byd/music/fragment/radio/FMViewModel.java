package com.byd.music.fragment.radio;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableList;
import androidx.databinding.ObservableLong;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import com.android.car.radio.bands.ProgramType;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.mediacenter.api.media.MediaControl;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.common.AppExecutors;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.ToastUtil;
import com.byd.music.utils.binding.BindingAction;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import com.byd.music.utils.binding.BindingConsumerWithView;
import com.byd.music.view.CollectLottieView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class FMViewModel extends BaseViewModel {
    private static final int POSITION_UNSELECTED_CHANNEL = -1;
    private static final int WHAT_UPDATE_CHANNEL_POSITION = 1;
    private static final int WHAT_UPDATE_COLLECTION_POSITION = 2;
    private Object FM_CHANNEL_LOCK;
    public AsyncDifferConfig<RadioDBBean> asyncDifferConfig;
    public SingleLiveEvent<Boolean> collectClickLiveEvent;
    public ObservableInt currentFrequency;
    public ObservableLong currentPI;
    public ObservableArrayList<RadioDBBean> fmChannelList;
    public ItemBinding<RadioDBBean> fmChannelRadioItemBinding;
    public ObservableInt fmChannelSelectedPosition;
    public ObservableArrayList<RadioDBBean> fmCollectList;
    public ItemBinding<RadioDBBean> fmCollectRadioItemBinding;
    public ObservableInt fmCollectSelectedPosition;
    Observable.OnPropertyChangedCallback frequencyCallback;
    public ObservableBoolean isCollectShow;
    public ObservableBoolean isMirror;
    public ObservableBoolean isPlayingCollect;
    ObservableList.OnListChangedCallback onListChangedCallback;
    public DiffUtil.ItemCallback<RadioDBBean> radioDiffCallback;
    private IRadioProgramListener radioProgramListener;
    public SingleLiveEvent<Boolean> seekClickLiveEvent;
    private final Handler mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.fragment.radio.FMViewModel.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            if (message.what == 1) {
                int i2 = message.arg1;
                if (FMViewModel.this.fmChannelSelectedPosition.get() != i2) {
                    FMViewModel.this.fmChannelSelectedPosition.set(i2);
                    return;
                }
                return;
            }
            if (message.what != 2 || FMViewModel.this.fmCollectSelectedPosition.get() == (i = message.arg1)) {
                return;
            }
            FMViewModel.this.fmCollectSelectedPosition.set(i);
        }
    };
    public BindingCommand<Boolean> btnSeekClickCommand = new BindingCommand<>(new BindingConsumer<Boolean>() { // from class: com.byd.music.fragment.radio.FMViewModel.4
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(Boolean bool) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            if (RadioServiceWrapper.getInstance().getBgScanEnable() && (FMViewModel.this.fmChannelList == null || FMViewModel.this.fmChannelList.size() == 0)) {
                ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                return;
            }
            OnLineMusic.setMusicEngine(7, 3);
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                FMViewModel.this.seekClickLiveEvent.setValue(bool);
            } else {
                FMViewModel.this.seekClickLiveEvent.postValue(bool);
            }
        }
    });
    public BindingCommand<RadioDBBean> btnCollectClickCommand = new BindingCommand<>(new BindingAction() { // from class: com.byd.music.fragment.radio.FMViewModel.5
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            LogUtil.d(FMViewModel.this.TAG, "btnCollectClickCommand!");
            if (RadioServiceWrapper.getInstance().cancelSeek()) {
                return;
            }
            if (FMViewModel.this.isPlayingCollect.get()) {
                RadioServiceWrapper.getInstance().deleteTargetFreqFromCollect(FMViewModel.this.currentFrequency.get());
            } else {
                RadioServiceWrapper.getInstance().addTargetFreqToCollect(FMViewModel.this.currentFrequency.get());
                FMViewModel.this.collectClickLiveEvent.postValue(true);
            }
        }
    });
    public BindingCommand<Object> btnPlayOrStopCommand = new BindingCommand<>(new BindingAction() { // from class: com.byd.music.fragment.radio.FMViewModel.6
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            OnLineMusic.setMusicEngine(7);
            RadioServiceWrapper.getInstance().setRadioPlayState(!RadioServiceWrapper.getInstance().getFMPlaying().get());
        }
    });
    public BindingCommand<RadioDBBean> cardClickCommand = new BindingCommand<>(new BindingConsumer<RadioDBBean>() { // from class: com.byd.music.fragment.radio.FMViewModel.7
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(RadioDBBean radioDBBean) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            FMViewModel fMViewModel = FMViewModel.this;
            if (fMViewModel.isCurrentRadio(radioDBBean, fMViewModel.currentFrequency.get(), FMViewModel.this.currentPI.get())) {
                OnLineMusic.setMusicEngine(7);
                RadioServiceWrapper.getInstance().setRadioPlayState(!RadioServiceWrapper.getInstance().getFMPlaying().get());
            } else {
                OnLineMusic.setMusicEngine(7, 3);
                RadioServiceWrapper.getInstance().tune(radioDBBean.getRadioFrequency());
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            }
        }
    });
    public BindingCommand<RadioDBBean> listCollectClickCommand = new BindingCommand<>(new BindingConsumerWithView<RadioDBBean>() { // from class: com.byd.music.fragment.radio.FMViewModel.8
        @Override // com.byd.music.utils.binding.BindingConsumerWithView
        public void call(View view, RadioDBBean radioDBBean) {
            CollectLottieView collectLottieView;
            LogUtil.d(FMViewModel.this.TAG, "call: item click enter " + radioDBBean.toString());
            if (FMViewModel.this.fmCollectList.contains(radioDBBean)) {
                LogUtil.d(FMViewModel.this.TAG, "call: fmCollectList deleteTargetBeanFromCollect");
                boolean zIsEmpty = TextUtils.isEmpty(RadioServiceWrapper.getInstance().deleteTargetBeanFromCollect(radioDBBean));
                LogUtil.d(FMViewModel.this.TAG, "delete target result = " + zIsEmpty);
                return;
            }
            LogUtil.d(FMViewModel.this.TAG, "call: fmCollectList addTargetBeanToCollect");
            boolean zIsEmpty2 = TextUtils.isEmpty(RadioServiceWrapper.getInstance().addTargetBeanToCollect(radioDBBean));
            if (zIsEmpty2 && (collectLottieView = (CollectLottieView) ((ViewGroup) view.getParent()).findViewById(R.id.collectLottie)) != null) {
                collectLottieView.startAnimation(true);
            }
            LogUtil.d(FMViewModel.this.TAG, "delete target result = " + zIsEmpty2);
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        LogUtil.d(this.TAG, "onCreate!");
        initData();
        RadioDBBean radioDBBean = RadioServiceWrapper.getInstance().currentFMRadioDBBean;
        this.currentFrequency = new ObservableInt(radioDBBean == null ? RadioServiceWrapper.FM_FREQ_MIN : radioDBBean.getRadioFrequency());
        this.currentPI = new ObservableLong(0L);
        ItemBinding<RadioDBBean> itemBindingOf = ItemBinding.of(BR.fmCardBean, R.layout.item_fm_collect);
        this.fmChannelRadioItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        this.fmChannelRadioItemBinding.bindExtra(BR.isCollect, false);
        ItemBinding<RadioDBBean> itemBindingOf2 = ItemBinding.of(BR.fmCardBean, R.layout.item_fm_collect);
        this.fmCollectRadioItemBinding = itemBindingOf2;
        itemBindingOf2.bindExtra(BR.viewModel, this);
        this.fmCollectRadioItemBinding.bindExtra(BR.isCollect, true);
        this.onListChangedCallback = new ObservableList.OnListChangedCallback() { // from class: com.byd.music.fragment.radio.FMViewModel.2
            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onChanged(ObservableList observableList) {
            }

            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onItemRangeChanged(ObservableList observableList, int i, int i2) {
            }

            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onItemRangeMoved(ObservableList observableList, int i, int i2, int i3) {
            }

            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onItemRangeInserted(ObservableList observableList, int i, int i2) {
                FMViewModel.this.setCollectStateByCollectList();
            }

            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
                FMViewModel.this.setCollectStateByCollectList();
            }
        };
        this.frequencyCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.radio.FMViewModel.3
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                synchronized (FMViewModel.this.FM_CHANNEL_LOCK) {
                    FMViewModel.this.setCollectStateByCollectList();
                    if (!RadioServiceWrapper.getInstance().isSeeking) {
                        FMViewModel.this.updateChannelSelectedPosition();
                    }
                }
            }
        };
        observeRadioProgram();
        setCollectStateByCollectList();
        this.fmCollectList.addOnListChangedCallback(this.onListChangedCallback);
    }

    private void initData() {
        this.isCollectShow = new ObservableBoolean(false);
        this.isPlayingCollect = new ObservableBoolean();
        this.FM_CHANNEL_LOCK = new Object();
        this.radioDiffCallback = new RadioDiffCallback();
        this.asyncDifferConfig = new AsyncDifferConfig.Builder(this.radioDiffCallback).setBackgroundThreadExecutor(AppExecutors.getInstance().mainThread()).build();
        this.fmCollectList = RadioServiceWrapper.getInstance().fmCollectList;
        this.fmChannelList = RadioServiceWrapper.getInstance().fmValidList;
        this.fmChannelSelectedPosition = new ObservableInt(-1);
        this.fmCollectSelectedPosition = new ObservableInt(-1);
        this.seekClickLiveEvent = new SingleLiveEvent<>();
        this.collectClickLiveEvent = new SingleLiveEvent<>();
        this.isMirror = new ObservableBoolean(false);
    }

    public void observeRadioProgram() {
        if (this.radioProgramListener == null) {
            this.radioProgramListener = new IRadioProgramListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMViewModel$A2DFVqNC8LY2K_LwJESgtPEx_DU
                @Override // com.byd.music.fragment.radio.IRadioProgramListener
                public final void onRadioProgramUpdated(ProgramType programType, List list) {
                    this.f$0.lambda$observeRadioProgram$0$FMViewModel(programType, list);
                }
            };
        }
        if (RadioServiceWrapper.getInstance().fmValidList != null) {
            LogUtil.d(this.TAG, "init fmChannelList! fmValidList = " + Arrays.toString(RadioServiceWrapper.getInstance().fmValidList.toArray()));
        }
        RadioServiceWrapper.getInstance().registerRadioProgramListener(this.radioProgramListener);
        this.currentFrequency.addOnPropertyChangedCallback(this.frequencyCallback);
    }

    public /* synthetic */ void lambda$observeRadioProgram$0$FMViewModel(ProgramType programType, List list) {
        if (ProgramType.FM == programType) {
            LogUtil.d(this.TAG, "onFMProgramUpdated! programType = " + programType);
            LogUtil.d(this.TAG, "onFMProgramUpdated! radioDBBeanList = " + Arrays.toString(list.toArray()));
            synchronized (this.FM_CHANNEL_LOCK) {
                updateChannelSelectedPosition();
            }
        }
    }

    public void updateCollectRadioList(List<RadioDBBean> list) {
        if (list != null) {
            List list2 = (List) list.stream().filter(new Predicate() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMViewModel$Eo5QbCGZ38PE5GeQzkQ4WRSBMZg
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return FMViewModel.lambda$updateCollectRadioList$1((RadioDBBean) obj);
                }
            }).collect(Collectors.toList());
            LogUtil.d(this.TAG, "FM Radio Collection Updated! size = " + list2.size());
            LogUtil.d(this.TAG, "FM Radio Collection Updated! FMRadioList ps = " + Arrays.toString(list2.stream().map(new Function() { // from class: com.byd.music.fragment.radio.-$$Lambda$1wyEAF6dX32IHWc4e0VMkuspyaY
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((RadioDBBean) obj).getRDS_PS();
                }
            }).toArray()));
            LogUtil.d(this.TAG, "FM Radio Collection Updated! FMRadioList radioFrequency = " + Arrays.toString(list2.stream().map($$Lambda$8Y32Tgs7xewHImORUk5dK_WSc.INSTANCE).toArray()));
            LogUtil.d(this.TAG, "FM Radio Collection Updated! FMRadioList RDS_PI = " + Arrays.toString(list2.stream().map(new Function() { // from class: com.byd.music.fragment.radio.-$$Lambda$oVccwAsvoHAitLvoGXCaNHag9aw
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Long.valueOf(((RadioDBBean) obj).getRDS_PI());
                }
            }).toArray()));
            ArrayList arrayList = new ArrayList();
            try {
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    arrayList.add((RadioDBBean) GsonUtils.fromJson(GsonUtils.toJson((RadioDBBean) it.next()), RadioDBBean.class));
                }
            } catch (Exception e) {
                LogUtil.e(this.TAG, "json exception " + e);
                arrayList.addAll(list2);
            }
            setCollectStateByCollectList();
        }
    }

    static /* synthetic */ boolean lambda$updateCollectRadioList$1(RadioDBBean radioDBBean) {
        return radioDBBean.getType() == 2 || radioDBBean.getType() == 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectStateByCollectList() {
        LogUtil.d(this.TAG, "FM setCollectStateByCollectList! currentFrequency = " + this.currentFrequency.get());
        LogUtil.d(this.TAG, "FM setCollectStateByCollectList! currentPI = " + this.currentPI.get());
        if (RadioServiceWrapper.getInstance().mCurrentRadio.getValue() == null) {
            LogUtil.d(this.TAG, "current Radio is null!");
            return;
        }
        ObservableArrayList<RadioDBBean> observableArrayList = this.fmCollectList;
        boolean z = observableArrayList != null && observableArrayList.stream().anyMatch(new Predicate() { // from class: com.byd.music.fragment.radio.-$$Lambda$FMViewModel$7b9NsMiPOv1pgQMP1twWh5uRTHk
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$setCollectStateByCollectList$2$FMViewModel((RadioDBBean) obj);
            }
        });
        LogUtil.d(this.TAG, "setCollectStateByCollectList, isCollected = " + z);
        this.isPlayingCollect.set(z);
    }

    public /* synthetic */ boolean lambda$setCollectStateByCollectList$2$FMViewModel(RadioDBBean radioDBBean) {
        return isCurrentRadio(radioDBBean, this.currentFrequency.get(), this.currentPI.get());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChannelSelectedPosition() {
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, currentFrequency = " + this.currentFrequency.get());
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, currentPI = " + this.currentPI.get());
        ObservableArrayList<RadioDBBean> observableArrayList = this.fmChannelList;
        if (observableArrayList == null || observableArrayList.size() == 0 || this.currentFrequency.get() == 0) {
            return;
        }
        int i = -1;
        for (int i2 = 0; i2 < this.fmChannelList.size(); i2++) {
            RadioDBBean radioDBBean = this.fmChannelList.get(i2);
            if (radioDBBean.getRadioFrequency() == this.currentFrequency.get() || (this.currentPI.get() > 0 && radioDBBean.getRDS_PI() == this.currentPI.get())) {
                LogUtil.d(this.TAG, "updateChannelSelectedPosition, selected radio = " + radioDBBean);
                i = i2;
                break;
            }
        }
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, position = " + i);
        if (i >= 0) {
            this.mainHandler.removeMessages(1);
            Message messageObtain = Message.obtain(this.mainHandler);
            messageObtain.what = 1;
            messageObtain.arg1 = i;
            this.mainHandler.sendMessageDelayed(messageObtain, 100L);
        }
    }

    private void updateCollectSelectedPosition() {
        LogUtil.d(this.TAG, "updateCollectSelectedPosition, currentFrequency = " + this.currentFrequency.get());
        ObservableArrayList<RadioDBBean> observableArrayList = this.fmCollectList;
        if (observableArrayList == null || observableArrayList.size() == 0 || this.currentFrequency.get() == 0) {
            return;
        }
        int i = -1;
        for (int i2 = 0; i2 < this.fmCollectList.size(); i2++) {
            RadioDBBean radioDBBean = this.fmCollectList.get(i2);
            if (radioDBBean.getUniqueFrequency() == this.currentFrequency.get() || (this.currentPI.get() > 0 && radioDBBean.getRDS_PI() == this.currentPI.get())) {
                LogUtil.d(this.TAG, "updateChannelSelectedPosition, selected radio = " + radioDBBean);
                i = i2;
                break;
            }
        }
        LogUtil.d(this.TAG, "updateCollectSelectedPosition, position = " + i);
        if (i >= 0) {
            this.mainHandler.removeMessages(2);
            Message messageObtain = Message.obtain(this.mainHandler);
            messageObtain.what = 2;
            messageObtain.arg1 = i;
            this.mainHandler.sendMessageDelayed(messageObtain, 100L);
        }
    }

    public boolean isCollectedRadio(List<RadioDBBean> list, RadioDBBean radioDBBean, boolean z) {
        return z || (list != null && list.contains(radioDBBean));
    }

    public boolean isCurrentRadio(RadioDBBean radioDBBean, int i, long j) {
        return i == radioDBBean.getRadioFrequency();
    }

    public String getRadioFrequency(RadioDBBean radioDBBean) {
        return RadioServiceWrapper.parseFreqI18N(radioDBBean.getRadioFrequency()) + " MHz";
    }

    public String getRDSPs(RadioDBBean radioDBBean) {
        return TextUtils.isEmpty(radioDBBean.getRDS_PS()) ? "" : radioDBBean.getRDS_PS();
    }

    public void updateCurrentFrequency(int i, long j) {
        if (this.currentPI.get() != j) {
            this.currentPI.set(j);
        }
        if (this.currentFrequency.get() != i) {
            this.currentFrequency.set(i);
        }
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
        LogUtil.d(this.TAG, "onDestroy!");
        this.fmCollectList.removeOnListChangedCallback(this.onListChangedCallback);
        this.currentFrequency.removeOnPropertyChangedCallback(this.frequencyCallback);
        RadioServiceWrapper.getInstance().unregisterRadioProgramListener(this.radioProgramListener);
    }

    public String btnAjdFreq(int i) {
        if (DeviceInfoUtils.isInCall()) {
            return MediaControl.REASON_UNSUPPORTED_OPERATION;
        }
        OnLineMusic.setMusicEngine(7, 3);
        return RadioServiceWrapper.getInstance().fineTune(i > 0);
    }
}
