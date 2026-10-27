package com.byd.music.fragment.radio;

import android.hardware.radio.RadioManager;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import com.byd.music.BR;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.base.BaseViewModel;
import com.byd.music.beans.RadioDBBean;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.service.RadioServiceWrapper;
import com.byd.music.utils.DeviceInfoUtils;
import com.byd.music.utils.LogUtil;
import com.byd.music.utils.binding.BindingCommand;
import com.byd.music.utils.binding.BindingConsumer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;
import me.tatarka.bindingcollectionadapter2.collections.DiffObservableList;

/* JADX INFO: loaded from: classes5.dex */
public class EpgListViewModel extends BaseViewModel {
    public ObservableField<RadioManager.ProgramInfo> currentDabInfo;
    public ItemBinding<RadioEpgDBBean> currentEpgBinding;
    public DiffObservableList<RadioEpgDBBean> currentEpgList;
    public OnItemBind<RadioDBBean> dabItemBinding;
    public ObservableArrayList<RadioEpgDBBean> subscribeEpgList;
    public RadioEpgDBBean tempDeleteRadioEpgDBBean;
    public RadioEpgDBBean tempReplaceRadioEpgDBBean;
    public SingleLiveEvent<RadioEpgDBBean> replaceClickEvent = new SingleLiveEvent<>();
    public ObservableBoolean isSubscribe = new ObservableBoolean(false);
    public BindingCommand<RadioDBBean> dabItemClickCommand = new BindingCommand<>(new BindingConsumer<RadioDBBean>() { // from class: com.byd.music.fragment.radio.EpgListViewModel.1
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(RadioDBBean radioDBBean) {
            if (DeviceInfoUtils.isInCall()) {
                return;
            }
            OnLineMusic.setMusicEngine(9);
            if (RadioServiceWrapper.getDabSID(EpgListViewModel.this.currentDabInfo.get()) == radioDBBean.getDAB_SID() && RadioServiceWrapper.getInstance().getDABPlaying().get()) {
                RadioServiceWrapper.getInstance().setRadioPlayState(false);
            } else {
                RadioServiceWrapper.getInstance().tune(RadioServiceWrapper.generateDABSelector(radioDBBean));
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
            }
        }
    });
    public BindingCommand<RadioEpgDBBean> subscribeCommand = new BindingCommand<>(new BindingConsumer<RadioEpgDBBean>() { // from class: com.byd.music.fragment.radio.EpgListViewModel.2
        @Override // com.byd.music.utils.binding.BindingConsumer
        public void call(RadioEpgDBBean radioEpgDBBean) {
            if (EpgListViewModel.this.isEpgBeanSubscribe(radioEpgDBBean)) {
                LogUtil.d(EpgListViewModel.this.TAG, "subscribeCommand go to delete with bean = " + radioEpgDBBean);
                RadioServiceWrapper.getInstance().deleteEpgFromSubscribeList(radioEpgDBBean);
                return;
            }
            LogUtil.d(EpgListViewModel.this.TAG, "subscribeCommand go to add with bean = " + radioEpgDBBean);
            for (RadioEpgDBBean radioEpgDBBean2 : EpgListViewModel.this.subscribeEpgList) {
                if (radioEpgDBBean2.getEpgStartTime() == radioEpgDBBean.getEpgStartTime()) {
                    EpgListViewModel.this.tempReplaceRadioEpgDBBean = radioEpgDBBean;
                    EpgListViewModel.this.tempDeleteRadioEpgDBBean = radioEpgDBBean2;
                    EpgListViewModel.this.replaceClickEvent.postValue(radioEpgDBBean2);
                    return;
                }
            }
            RadioServiceWrapper.getInstance().addEpgToSubscribeListWithSort(radioEpgDBBean);
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.currentEpgList = RadioServiceWrapper.getInstance().radioCurrentEpgList;
        this.subscribeEpgList = RadioServiceWrapper.getInstance().radioSubscribeEpgList;
        this.currentDabInfo = RadioServiceWrapper.getInstance().getCurrentProgram();
        this.dabItemBinding = new OnItemBind() { // from class: com.byd.music.fragment.radio.-$$Lambda$EpgListViewModel$VX3HErHBAvf9TXuy9cwkL01uZCw
            @Override // me.tatarka.bindingcollectionadapter2.OnItemBind
            public final void onItemBind(ItemBinding itemBinding, int i, Object obj) {
                this.f$0.lambda$onCreate$0$EpgListViewModel(itemBinding, i, (RadioDBBean) obj);
            }
        };
        ItemBinding<RadioEpgDBBean> itemBindingOf = ItemBinding.of(BR.epgBean, R.layout.item_epg_current);
        this.currentEpgBinding = itemBindingOf;
        itemBindingOf.bindExtra(BR.viewModel, this);
    }

    public /* synthetic */ void lambda$onCreate$0$EpgListViewModel(ItemBinding itemBinding, int i, RadioDBBean radioDBBean) {
        if (radioDBBean.getType() == 4) {
            itemBinding.set(BR.setBean, R.layout.item_epg_dab_list);
            itemBinding.bindExtra(BR.viewModel, this);
        } else {
            itemBinding.set(BR.bean, R.layout.item_gone);
        }
    }

    public ObservableBoolean isStationSubScribe(long j) {
        Iterator<RadioEpgDBBean> it = this.subscribeEpgList.iterator();
        while (it.hasNext()) {
            if (it.next().getDabSid() == j) {
                return new ObservableBoolean(true);
            }
        }
        return new ObservableBoolean(false);
    }

    public String longTransFormString(Long l) {
        return new SimpleDateFormat("MM-dd  HH:mm").format(new Date(l.longValue() * 1000));
    }

    public boolean replaceSubscribeEpgBean(boolean z) {
        if (z && this.tempDeleteRadioEpgDBBean != null && this.tempReplaceRadioEpgDBBean != null) {
            RadioServiceWrapper.getInstance().deleteEpgFromSubscribeList(this.tempDeleteRadioEpgDBBean);
            RadioServiceWrapper.getInstance().addEpgToSubscribeListWithSort(this.tempReplaceRadioEpgDBBean);
        }
        this.tempDeleteRadioEpgDBBean = null;
        this.tempReplaceRadioEpgDBBean = null;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isEpgBeanSubscribe(RadioEpgDBBean radioEpgDBBean) {
        return RadioServiceWrapper.getInstance().getEpgSubscribeBean(radioEpgDBBean) != null;
    }

    public long getDabSID(RadioManager.ProgramInfo programInfo) {
        return RadioServiceWrapper.getDabSID(programInfo);
    }
}
