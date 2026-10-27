package com.byd.music.fragment.bluetooth.activity;

import com.byd.music.base.BaseViewModel;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.utils.binding.BindingAction;
import com.byd.music.utils.binding.BindingCommand;

/* JADX INFO: loaded from: classes5.dex */
public class OpenBTViewModel extends BaseViewModel {
    SingleLiveEvent<Integer> btnClickEvent;
    SingleLiveEvent outsizeClickEvent;
    public BindingCommand confirmCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.bluetooth.activity.OpenBTViewModel.1
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            OpenBTViewModel.this.btnClickEvent.setValue(1);
        }
    });
    public BindingCommand cancelCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.bluetooth.activity.OpenBTViewModel.2
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            OpenBTViewModel.this.btnClickEvent.setValue(0);
        }
    });
    public BindingCommand outsizeClickCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.bluetooth.activity.OpenBTViewModel.3
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            OpenBTViewModel.this.outsizeClickEvent.call();
        }
    });
    public BindingCommand inClickCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.bluetooth.activity.OpenBTViewModel.4
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.btnClickEvent = new SingleLiveEvent<>();
        this.outsizeClickEvent = new SingleLiveEvent();
    }
}
