package com.byd.dipilot.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;
import androidx.lifecycle.LiveData;
import b.b.k.b.a;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.inter.IAdasDataRepository;
import com.byd.dipilot.bpresenter.R$string;
import com.byd.dipilot.viewmodel.BaseAdasViewModel;
import java.io.Serializable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseViewModel extends BaseObservable implements Serializable {
    public boolean assistState;
    public boolean enable = true;
    public boolean normal = true;
    public int tag;
    public BaseAdasViewModel<? extends DriverAssistModel, ? extends a> vm;

    public int convertTagFromTitle() {
        BaseAdasViewModel<? extends DriverAssistModel, ? extends a> baseAdasViewModel = this.vm;
        if (baseAdasViewModel != null) {
            return baseAdasViewModel.e(this.tag);
        }
        int i = this.tag;
        if (i == R$string.dipilot_title_panoramic_images) {
            return 80;
        }
        return i;
    }

    @Bindable
    public boolean getAssistState() {
        return this.assistState;
    }

    @Bindable
    public boolean getEnable() {
        return this.enable;
    }

    public LiveData<Boolean> getMpc() {
        if (this.vm == null) {
            Router.b().d();
            return IAdasDataRepository.f2444a;
        }
        int iConvertTagFromTitle = convertTagFromTitle();
        return (6 == iConvertTagFromTitle || iConvertTagFromTitle == 5) ? Router.b().e().h() : this.vm.f();
    }

    public BaseAdasViewModel<? extends DriverAssistModel, ? extends a> getVm() {
        return this.vm;
    }

    @Bindable
    public boolean isAlpha() {
        return this.enable & this.normal;
    }

    @Bindable
    public boolean isTouch() {
        return this.enable & (!this.normal);
    }

    public void setAssistState(boolean z) {
        this.assistState = z;
        notifyPropertyChanged(BR.assistState);
    }

    public void setEnable(boolean z) {
        this.enable = z;
        notifyPropertyChanged(BR.enable);
        notifyPropertyChanged(BR.touch);
        notifyPropertyChanged(BR.alpha);
    }

    public void setNormal(boolean z) {
        this.normal = z;
        notifyPropertyChanged(BR.touch);
        notifyPropertyChanged(BR.alpha);
    }

    public void setVm(BaseAdasViewModel<? extends DriverAssistModel, ? extends a> baseAdasViewModel) {
        this.vm = baseAdasViewModel;
    }
}
