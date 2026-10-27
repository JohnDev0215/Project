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
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import com.android.car.radio.bands.ProgramType;
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
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import me.tatarka.bindingcollectionadapter2.ItemBinding;

/* JADX INFO: loaded from: classes5.dex */
public class AMViewModel extends BaseViewModel {
    private static final int POSITION_UNSELECTED_CHANNEL = -1;
    private static final int WHAT_UPDATE_CHANNEL_POSITION = 1;
    private static final int WHAT_UPDATE_COLLECTION_POSITION = 2;
    private Object FM_CHANNEL_LOCK;
    public ItemBinding<RadioDBBean> amCardBeanItemBinding;
    public ObservableArrayList<RadioDBBean> amChannelList;
    public ObservableInt amChannelSelectedPosition;
    public ObservableArrayList<RadioDBBean> amCollectList;
    public ObservableInt amCollectSelectedPosition;
    public AsyncDifferConfig<RadioDBBean> asyncDifferConfig;
    public SingleLiveEvent<Boolean> collectClickLiveEvent;
    public ObservableInt currentFrequency;
    public SingleLiveEvent favoriteListInsertEvent;
    public SingleLiveEvent<RadioDBBean> fmCardClickLiveEvent;
    Observable.OnPropertyChangedCallback frequencyCallback;
    public ObservableBoolean isCollectShow;
    public ObservableBoolean isMirror;
    public ObservableBoolean isPlayingCollect;
    ObservableList.OnListChangedCallback onListChangedCallback;
    private DiffUtil.ItemCallback<RadioDBBean> radioDiffCallback;
    private IRadioProgramListener radioProgramListener;
    public SingleLiveEvent<Boolean> seekClickLiveEvent;
    private final Handler mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.fragment.radio.AMViewModel.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            if (message.what != 1 || AMViewModel.this.amChannelSelectedPosition.get() == (i = message.arg1)) {
                return;
            }
            AMViewModel.this.amChannelSelectedPosition.set(i);
        }
    };
    public BindingCommand<Boolean> btnSeekClickCommand = new BindingCommand<>(new BindingConsumer<Boolean>() { // from class: com.byd.music.fragment.radio.AMViewModel.4
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(Boolean bool) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            if (RadioServiceWrapper.getInstance().getBgScanEnable() && (AMViewModel.this.amChannelList == null || AMViewModel.this.amChannelList.size() == 0)) {
                ToastUtil.showCenter(Utils.getApp().getString(R.string.hint_radio_channel_empty));
                return;
            }
            OnLineMusic.setMusicEngine(8, 3);
            if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
                AMViewModel.this.seekClickLiveEvent.setValue(bool);
            } else {
                AMViewModel.this.seekClickLiveEvent.postValue(bool);
            }
        }
    });
    public BindingCommand<RadioDBBean> btnCollectClickCommand = new BindingCommand<>(new BindingAction() { // from class: com.byd.music.fragment.radio.AMViewModel.5
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            if (RadioServiceWrapper.getInstance().cancelSeek()) {
                return;
            }
            if (AMViewModel.this.isPlayingCollect.get()) {
                RadioServiceWrapper.getInstance().deleteTargetFreqFromCollect(AMViewModel.this.currentFrequency.get());
            } else {
                RadioServiceWrapper.getInstance().addTargetFreqToCollect(AMViewModel.this.currentFrequency.get());
                AMViewModel.this.collectClickLiveEvent.postValue(true);
            }
        }
    });
    public BindingCommand<Object> btnPlayOrStopCommand = new BindingCommand<>(new BindingAction() { // from class: com.byd.music.fragment.radio.AMViewModel.6
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            OnLineMusic.setMusicEngine(8);
            RadioServiceWrapper.getInstance().setRadioPlayState(!RadioServiceWrapper.getInstance().getAMPlaying().get());
        }
    });
    public BindingCommand<RadioDBBean> cardClickCommand = new BindingCommand<>(new BindingConsumer<RadioDBBean>() { // from class: com.byd.music.fragment.radio.AMViewModel.7
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(RadioDBBean radioDBBean) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            if (radioDBBean.getRadioFrequency() == AMViewModel.this.currentFrequency.get()) {
                OnLineMusic.setMusicEngine(8);
                RadioServiceWrapper.getInstance().setRadioPlayState(!RadioServiceWrapper.getInstance().getAMPlaying().get());
            } else {
                OnLineMusic.setMusicEngine(8, 3);
                RadioServiceWrapper.getInstance().tune(radioDBBean.getRadioFrequency());
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            }
        }
    });
    public BindingCommand<RadioDBBean> listCollectClickCommand = new BindingCommand<>(new BindingConsumerWithView<RadioDBBean>() { // from class: com.byd.music.fragment.radio.AMViewModel.8
        @Override // com.byd.music.utils.binding.BindingConsumerWithView
        public void call(View view, RadioDBBean radioDBBean) {
            CollectLottieView collectLottieView;
            LogUtil.d(AMViewModel.this.TAG, "call: item click enter");
            if (AMViewModel.this.amCollectList.contains(radioDBBean)) {
                LogUtil.d(AMViewModel.this.TAG, "call: fmCollectList deleteTargetBeanFromCollect");
                String strDeleteTargetBeanFromCollect = RadioServiceWrapper.getInstance().deleteTargetBeanFromCollect(radioDBBean);
                LogUtil.d(AMViewModel.this.TAG, "delete target result = " + strDeleteTargetBeanFromCollect);
                return;
            }
            LogUtil.d(AMViewModel.this.TAG, "call: fmCollectList addTargetBeanToCollect");
            boolean zIsEmpty = TextUtils.isEmpty(RadioServiceWrapper.getInstance().addTargetBeanToCollect(radioDBBean));
            if (zIsEmpty && (collectLottieView = (CollectLottieView) ((ViewGroup) view.getParent()).findViewById(R.id.collectLottie)) != null) {
                collectLottieView.startAnimation(true);
            }
            LogUtil.d(AMViewModel.this.TAG, "add target result = " + zIsEmpty);
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        initData();
        try {
            this.currentFrequency = new ObservableInt(RadioServiceWrapper.getInstance().currentAmRadioDBBean.getRadioFrequency());
        } catch (Throwable unused) {
            this.currentFrequency = new ObservableInt(RadioServiceWrapper.AM_FREQ_MIN);
        }
        ItemBinding<RadioDBBean> itemBindingOf = ItemBinding.of(BR.fmCardBean, R.layout.item_am_collect);
        this.amCardBeanItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        this.frequencyCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.radio.AMViewModel.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                synchronized (AMViewModel.this.FM_CHANNEL_LOCK) {
                    AMViewModel.this.setCollectStateByCollectList();
                    if (!RadioServiceWrapper.getInstance().isSeeking) {
                        AMViewModel.this.updateChannelSelectedPosition();
                    }
                }
            }
        };
        this.onListChangedCallback = new ObservableList.OnListChangedCallback() { // from class: com.byd.music.fragment.radio.AMViewModel.3
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
                AMViewModel.this.setCollectStateByCollectList();
            }

            @Override // androidx.databinding.ObservableList.OnListChangedCallback
            public void onItemRangeRemoved(ObservableList observableList, int i, int i2) {
                AMViewModel.this.setCollectStateByCollectList();
            }
        };
        observeRadioProgram();
        setCollectStateByCollectList();
        this.amCollectList.addOnListChangedCallback(this.onListChangedCallback);
    }

    private void initData() {
        this.FM_CHANNEL_LOCK = new Object();
        this.radioDiffCallback = new RadioDiffCallback();
        this.asyncDifferConfig = new AsyncDifferConfig.Builder(this.radioDiffCallback).setBackgroundThreadExecutor(AppExecutors.getInstance().diskIO()).build();
        this.amCollectList = RadioServiceWrapper.getInstance().amCollectList;
        this.amChannelList = RadioServiceWrapper.getInstance().amValidList;
        this.amChannelSelectedPosition = new ObservableInt(-1);
        this.amCollectSelectedPosition = new ObservableInt(-1);
        this.fmCardClickLiveEvent = new SingleLiveEvent<>();
        this.seekClickLiveEvent = new SingleLiveEvent<>();
        this.collectClickLiveEvent = new SingleLiveEvent<>();
        this.favoriteListInsertEvent = new SingleLiveEvent();
        this.isMirror = new ObservableBoolean(false);
        this.isCollectShow = new ObservableBoolean(false);
        this.isPlayingCollect = new ObservableBoolean();
    }

    public void observeRadioProgram() {
        if (this.radioProgramListener == null) {
            this.radioProgramListener = new IRadioProgramListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMViewModel$W4cAVehJR7ATBQzQHNrLUfRdXFU
                @Override // com.byd.music.fragment.radio.IRadioProgramListener
                public final void onRadioProgramUpdated(ProgramType programType, List list) {
                    this.f$0.lambda$observeRadioProgram$0$AMViewModel(programType, list);
                }
            };
        }
        if (RadioServiceWrapper.getInstance().amValidList != null) {
            LogUtil.d(this.TAG, "init amChannelList! amValidList = " + Arrays.toString(RadioServiceWrapper.getInstance().amValidList.toArray()));
        }
        RadioServiceWrapper.getInstance().registerRadioProgramListener(this.radioProgramListener);
        this.currentFrequency.addOnPropertyChangedCallback(this.frequencyCallback);
    }

    public /* synthetic */ void lambda$observeRadioProgram$0$AMViewModel(ProgramType programType, List list) {
        if (ProgramType.AM == programType) {
            LogUtil.d(this.TAG, "onAMProgramUpdated! programType = " + programType);
            LogUtil.d(this.TAG, "onAMProgramUpdated! radioDBBeanList = " + Arrays.toString(list.toArray()));
            synchronized (this.FM_CHANNEL_LOCK) {
                updateChannelSelectedPosition();
            }
        }
    }

    public void updateCollectRadioList(List<RadioDBBean> list) {
        if (list != null) {
            List list2 = (List) list.stream().filter(new Predicate() { // from class: com.byd.music.fragment.radio.-$$Lambda$AMViewModel$hn3o3eQXxyAe7Q5l3G51Fe67l8U
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return AMViewModel.lambda$updateCollectRadioList$1((RadioDBBean) obj);
                }
            }).collect(Collectors.toList());
            LogUtil.d(this.TAG, "AM Radio Collection Updated! size = " + list2.size());
            LogUtil.d(this.TAG, "AM Radio Collection Updated! AMRadioList = " + Arrays.toString(list2.stream().map($$Lambda$8Y32Tgs7xewHImORUk5dK_WSc.INSTANCE).toArray()));
            setCollectStateByCollectList();
        }
    }

    static /* synthetic */ boolean lambda$updateCollectRadioList$1(RadioDBBean radioDBBean) {
        return radioDBBean.getType() == 1;
    }

    public void updateCurrentFrequency(int i) {
        if (this.currentFrequency.get() != i) {
            this.currentFrequency.set(i);
        }
    }

    public String getAmDesc(RadioDBBean radioDBBean) {
        return radioDBBean.getRadioFrequency() + " KHz";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChannelSelectedPosition() {
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, currentFrequency = " + this.currentFrequency.get());
        ObservableArrayList<RadioDBBean> observableArrayList = this.amChannelList;
        if (observableArrayList == null || observableArrayList.size() == 0 || this.currentFrequency.get() == 0) {
            return;
        }
        int i = -1;
        for (int i2 = 0; i2 < this.amChannelList.size(); i2++) {
            if (this.amChannelList.get(i2).getUniqueFrequency() == this.currentFrequency.get()) {
                i = i2;
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

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
        if (this.radioProgramListener != null) {
            RadioServiceWrapper.getInstance().unregisterRadioProgramListener(this.radioProgramListener);
        }
        this.amCollectList.removeOnListChangedCallback(this.onListChangedCallback);
        this.currentFrequency.removeOnPropertyChangedCallback(this.frequencyCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCollectStateByCollectList() {
        LogUtil.d(this.TAG, "AM setCollectStateByCollectList! currentFrequency = " + this.currentFrequency.get());
        Iterator<RadioDBBean> it = this.amCollectList.iterator();
        while (it.hasNext()) {
            if (it.next().getRadioFrequency() == this.currentFrequency.get()) {
                this.isPlayingCollect.set(true);
                return;
            }
        }
        this.isPlayingCollect.set(false);
    }

    public String btnAjdFreq(int i) {
        if (DeviceInfoUtils.isInCall()) {
            return MediaControl.REASON_UNSUPPORTED_OPERATION;
        }
        OnLineMusic.setMusicEngine(8, 3);
        return RadioServiceWrapper.getInstance().fineTune(i > 0);
    }
}
