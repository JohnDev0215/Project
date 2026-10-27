package com.byd.music.fragment.localMusic.activity;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableInt;
import com.byd.music.base.BaseViewModel;
import com.byd.music.common.SingleLiveEvent;
import com.byd.music.utils.binding.BindingAction;
import com.byd.music.utils.binding.BindingCommand;

/* JADX INFO: loaded from: classes5.dex */
public class DialogActivityViewModel extends BaseViewModel {
    SingleLiveEvent<Integer> btnClickEvent;
    ObservableBoolean isShowDialog;
    ObservableInt progress;
    ObservableInt sbarMax;
    public BindingCommand confirmCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivityViewModel.1
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            DialogActivityViewModel.this.btnClickEvent.setValue(1);
        }
    });
    public BindingCommand cancelCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivityViewModel.2
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
            DialogActivityViewModel.this.btnClickEvent.setValue(0);
        }
    });
    public BindingCommand clContentCommand = new BindingCommand(new BindingAction() { // from class: com.byd.music.fragment.localMusic.activity.DialogActivityViewModel.3
        @Override // com.byd.music.utils.binding.BindingAction
        public void call() {
        }
    });

    @Override // com.byd.music.base.BaseViewModel
    protected void onDestroy() {
    }

    public ObservableInt getSbarMax() {
        return this.sbarMax;
    }

    public void setSbarMax(int i) {
        this.sbarMax.set(i);
    }

    @Override // com.byd.music.base.BaseViewModel
    protected void onCreate() {
        this.btnClickEvent = new SingleLiveEvent<>();
        this.isShowDialog = new ObservableBoolean(true);
        this.sbarMax = new ObservableInt();
        this.progress = new ObservableInt();
    }

    public SingleLiveEvent<Integer> getBtnClickEvent() {
        return this.btnClickEvent;
    }

    public ObservableBoolean getIsShowDialog() {
        return this.isShowDialog;
    }

    public void setIsShowDialog(boolean z) {
        this.isShowDialog.set(z);
    }

    public ObservableInt getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress.set(i);
    }
}
