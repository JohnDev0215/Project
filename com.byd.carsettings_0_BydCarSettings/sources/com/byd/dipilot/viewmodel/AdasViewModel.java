package com.byd.dipilot.viewmodel;

import android.app.Application;
import android.hardware.bydauto.dipilot.AbsBYDAutoDiPilotListener;
import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.lifecycle.AndroidViewModel;
import b.b.a.c.a;
import com.byd.base.hilt.Router;
import com.bydux.api.common.tools.log.BydLogHelper;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class AdasViewModel extends AndroidViewModel {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3626a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AbsBYDAutoDiPilotListener f3627b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ObservableBoolean f3628c;

    public AdasViewModel(@NonNull Application application) {
        super(application);
        this.f3626a = "AdasViewModel";
    }

    public ObservableBoolean a() {
        ObservableBoolean observableBoolean = this.f3628c;
        return observableBoolean == null ? new ObservableBoolean(false) : observableBoolean;
    }

    public AbsBYDAutoDiPilotListener b() {
        return this.f3627b;
    }

    public boolean c() {
        ObservableBoolean observableBoolean = this.f3628c;
        if (observableBoolean == null) {
            return false;
        }
        return Boolean.TRUE.equals(Boolean.valueOf(observableBoolean.get()));
    }

    public void d(@NonNull AbsBYDAutoDiPilotListener absBYDAutoDiPilotListener) {
        if (this.f3627b != null) {
            return;
        }
        BydLogHelper.logI("AdasViewModel", "registerListener is begin =" + absBYDAutoDiPilotListener.hashCode());
        this.f3627b = absBYDAutoDiPilotListener;
        EventBus.getDefault().register(this);
    }

    public void e(int i) {
        boolean z = i >= 1 && i <= 4;
        BydLogHelper.logI("AdasViewModel", "setInterceptTouchMask intercept =" + i);
        ObservableBoolean observableBoolean = this.f3628c;
        if (observableBoolean == null) {
            this.f3628c = new ObservableBoolean(z);
        } else if (z != observableBoolean.get()) {
            this.f3628c.set(z);
        }
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
        if (this.f3627b != null) {
            Router.b().g();
            this.f3627b = null;
            EventBus.getDefault().unregister(this);
            BydLogHelper.logI("AdasViewModel", "onCleared unRegisterListener event bus");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(a aVar) {
        if (aVar.h() == 150028) {
            e(aVar.c());
        }
    }
}
