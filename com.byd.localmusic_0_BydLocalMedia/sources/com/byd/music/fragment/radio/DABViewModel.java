package com.byd.music.fragment.radio;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.Observable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableInt;
import androidx.databinding.ObservableLong;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import com.android.car.radio.bands.ProgramType;
import com.blankj.utilcode.util.GsonUtils;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import com.byd.music.utils.binding.BindingConsumerWithView;
import com.byd.music.view.CollectLottieView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/* JADX INFO: loaded from: classes5.dex */
public class DABViewModel extends BaseViewModel {
    private static final int POSITION_UNSELECTED_CHANNEL = -1;
    private static final int WHAT_UPDATE_POSITION = 1;
    private Object FM_CHANNEL_LOCK;
    public ItemBinding<RadioDBBean> collectDabItemBinding;
    public ObservableLong currentDabSID;
    Observable.OnPropertyChangedCallback currentProgramCallback;
    public ObservableArrayList<RadioDBBean> dabChannelList;
    public ObservableArrayList<RadioDBBean> dabCollectList;
    public OnItemBind<RadioDBBean> dabItemBinding;
    public ObservableInt dabSelectedPosition;
    public AsyncDifferConfig<RadioDBBean> differConfig;
    private DiffUtil.ItemCallback<RadioDBBean> radioDiffCallback;
    private IRadioProgramListener radioProgramListener;
    private final Handler mainHandler = new Handler(Looper.getMainLooper()) { // from class: com.byd.music.fragment.radio.DABViewModel.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i;
            if (message.what != 1 || DABViewModel.this.dabSelectedPosition.get() == (i = message.arg1)) {
                return;
            }
            DABViewModel.this.dabSelectedPosition.set(i);
        }
    };
    public BindingCommand<RadioDBBean> itemClickCommand = new BindingCommand<>(new BindingConsumer<RadioDBBean>() { // from class: com.byd.music.fragment.radio.DABViewModel.3
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(RadioDBBean radioDBBean) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            if (DABViewModel.this.currentDabSID.get() != radioDBBean.getDAB_SID() || RadioServiceWrapper.getInstance().tuningDABSID != -1) {
                OnLineMusic.setMusicEngine(9, 3);
                RadioServiceWrapper.getInstance().tune(RadioServiceWrapper.generateDABSelector(radioDBBean));
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            } else {
                OnLineMusic.setMusicEngine(9);
                if (RadioServiceWrapper.getInstance().getDABPlaying().get()) {
                    RadioServiceWrapper.getInstance().setRadioPlayState(false);
                } else {
                    RadioServiceWrapper.getInstance().setRadioPlayState(true);
                }
            }
        }
    });
    public BindingCommand<RadioDBBean> collectClickCommand = new BindingCommand<>(new BindingConsumerWithView<RadioDBBean>() { // from class: com.byd.music.fragment.radio.DABViewModel.4
        @Override // com.byd.music.utils.binding.BindingConsumerWithView
        public void call(View view, RadioDBBean radioDBBean) {
            CollectLottieView collectLottieView;
            if (DABViewModel.this.dabCollectList.contains(radioDBBean)) {
                RadioServiceWrapper.getInstance().deleteTargetDABSIDFromCollect(RadioServiceWrapper.getDabSID(radioDBBean));
            } else {
                if (!TextUtils.isEmpty(RadioServiceWrapper.getInstance().addTargetBeanToCollect(radioDBBean)) || (collectLottieView = (CollectLottieView) ((ViewGroup) view.getParent()).findViewById(R.id.collectLottie)) == null) {
                    return;
                }
                collectLottieView.startAnimation(true);
            }
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        initData();
        if (RadioServiceWrapper.isDabProgram(RadioServiceWrapper.getInstance().getCurrentProgram().get())) {
            this.currentDabSID = new ObservableLong(RadioServiceWrapper.getDabSID(RadioServiceWrapper.getInstance().getCurrentProgram().get()));
        } else {
            this.currentDabSID = new ObservableLong();
        }
        this.dabItemBinding = new OnItemBind() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABViewModel$aJB5aDSO2ztvzoWyfHiJ78P7lVI
            @Override // me.tatarka.bindingcollectionadapter2.OnItemBind
            public final void onItemBind(ItemBinding itemBinding, int i, Object obj) {
                this.f$0.lambda$onCreate$0$DABViewModel(itemBinding, i, (RadioDBBean) obj);
            }
        };
        ItemBinding<RadioDBBean> itemBindingOf = ItemBinding.of(BR.programInfo, R.layout.item_dab_station);
        this.collectDabItemBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
        this.collectDabItemBinding.bindExtra(BR.isCollect, true);
        this.currentProgramCallback = new Observable.OnPropertyChangedCallback() { // from class: com.byd.music.fragment.radio.DABViewModel.2
            @Override // androidx.databinding.Observable.OnPropertyChangedCallback
            public void onPropertyChanged(Observable observable, int i) {
                if (RadioServiceWrapper.isDabProgram(RadioServiceWrapper.getInstance().getCurrentProgram().get())) {
                    DABViewModel.this.currentDabSID.set(RadioServiceWrapper.getDabSID(RadioServiceWrapper.getInstance().getCurrentProgram().get()));
                    LogUtil.d(DABViewModel.this.TAG, "onPropertyChanged: after set currentDabSID = " + DABViewModel.this.currentDabSID.get());
                    synchronized (DABViewModel.this.FM_CHANNEL_LOCK) {
                        DABViewModel.this.updateChannelSelectedPosition();
                    }
                }
            }
        };
        observeRadioProgram();
    }

    public /* synthetic */ void lambda$onCreate$0$DABViewModel(ItemBinding itemBinding, int i, RadioDBBean radioDBBean) {
        if (radioDBBean.getType() == 4) {
            itemBinding.set(BR.programInfo, R.layout.item_dab_station);
        } else if (radioDBBean.getType() == 5) {
            itemBinding.set(BR.programInfo, R.layout.item_dab_pty);
        }
        itemBinding.bindExtra(BR.viewModel, this);
        itemBinding.bindExtra(BR.isCollect, false);
    }

    private void initData() {
        this.FM_CHANNEL_LOCK = new Object();
        this.radioDiffCallback = new RadioDiffCallback();
        this.differConfig = new AsyncDifferConfig.Builder(this.radioDiffCallback).build();
        this.dabChannelList = RadioServiceWrapper.getInstance().dabValidList;
        this.dabCollectList = RadioServiceWrapper.getInstance().dabCollectList;
        this.dabSelectedPosition = new ObservableInt(-1);
    }

    public void observeRadioProgram() {
        if (this.radioProgramListener == null) {
            this.radioProgramListener = new IRadioProgramListener() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABViewModel$VjxW5QxOU-zOxeQqqWecl7M3ZU0
                @Override // com.byd.music.fragment.radio.IRadioProgramListener
                public final void onRadioProgramUpdated(ProgramType programType, List list) {
                    this.f$0.lambda$observeRadioProgram$1$DABViewModel(programType, list);
                }
            };
        }
        if (RadioServiceWrapper.getInstance().dabValidList != null) {
            LogUtil.d(this.TAG, "init amChannelList! dabValidList = " + Arrays.toString(RadioServiceWrapper.getInstance().dabValidList.toArray()));
        }
        RadioServiceWrapper.getInstance().registerRadioProgramListener(this.radioProgramListener);
        RadioServiceWrapper.getInstance().getCurrentProgram().addOnPropertyChangedCallback(this.currentProgramCallback);
    }

    public /* synthetic */ void lambda$observeRadioProgram$1$DABViewModel(ProgramType programType, List list) {
        if (ProgramType.DAB == programType) {
            LogUtil.d(this.TAG, "onDABProgramUpdated! programType = " + programType);
            LogUtil.d(this.TAG, "onDABProgramUpdated! dabRadioChannels name = " + Arrays.toString(list.stream().map($$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc.INSTANCE).toArray()));
            LogUtil.d(this.TAG, "onDABProgramUpdated! dabRadioChannels dab_sid= " + Arrays.toString(list.stream().map($$Lambda$0NCT_R1obf6cWJg741T3VmWwNo.INSTANCE).toArray()));
            synchronized (this.FM_CHANNEL_LOCK) {
                updateChannelSelectedPosition();
            }
        }
    }

    public void updateCollectRadioList(List<RadioDBBean> list) {
        if (list != null) {
            List list2 = (List) list.stream().filter(new Predicate() { // from class: com.byd.music.fragment.radio.-$$Lambda$DABViewModel$xXm6r9zMr48XEqJ8h1oo-pe3yz8
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return DABViewModel.lambda$updateCollectRadioList$2((RadioDBBean) obj);
                }
            }).collect(Collectors.toList());
            LogUtil.d(this.TAG, "DAB Radio Collection Updated! size = " + list2.size());
            LogUtil.d(this.TAG, "DAB Radio Collection Updated! dabList name = " + Arrays.toString(list2.stream().map($$Lambda$ccde0yMQVLdbtn913mYKPZzCHBc.INSTANCE).toArray()));
            LogUtil.d(this.TAG, "DAB Radio Collection Updated! dabList dab_sid" + Arrays.toString(list2.stream().map($$Lambda$0NCT_R1obf6cWJg741T3VmWwNo.INSTANCE).toArray()));
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
        }
    }

    static /* synthetic */ boolean lambda$updateCollectRadioList$2(RadioDBBean radioDBBean) {
        return radioDBBean.getType() == 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateChannelSelectedPosition() {
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, currentDabSID = " + this.currentDabSID.get());
        if (this.dabChannelList.size() == 0 || this.currentDabSID.get() == 0) {
            return;
        }
        int i = -1;
        for (int i2 = 0; i2 < this.dabChannelList.size(); i2++) {
            if (this.dabChannelList.get(i2).getUniqueFrequency() == this.currentDabSID.get()) {
                i = i2;
            }
        }
        LogUtil.d(this.TAG, "updateChannelSelectedPosition, position = " + i);
        if (i > -1) {
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
        RadioServiceWrapper.getInstance().getCurrentProgram().removeOnPropertyChangedCallback(this.currentProgramCallback);
    }

    public Bitmap getDABAlbum(RadioDBBean radioDBBean) {
        return RadioServiceWrapper.getDabAlbum(radioDBBean.getIconId());
    }
}
