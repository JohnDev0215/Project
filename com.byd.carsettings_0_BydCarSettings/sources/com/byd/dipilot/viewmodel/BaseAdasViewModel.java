package com.byd.dipilot.viewmodel;

import android.app.Application;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import b.b.k.b.a;
import com.byd.base.hilt.Router;
import com.byd.base.hilt.impl.BodyDeviceImpl;
import com.byd.base.view.BydSwitch;
import com.byd.dipilot.bpresenter.R$dimen;
import com.byd.dipilot.bpresenter.R$string;
import com.byd.dipilot.model.DriverAssistModel;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class BaseAdasViewModel<T extends DriverAssistModel, P extends a> extends AndroidViewModel implements BydSwitch.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3629a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public List<T> f3630b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public P f3631c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public MutableLiveData<Boolean> f3632d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public MutableLiveData<Boolean> f3633e;

    public BaseAdasViewModel(@NonNull Application application) {
        super(application);
        this.f3629a = getClass().getSimpleName();
        BodyDeviceImpl bodyDeviceImplE = Router.b().e();
        this.f3632d = bodyDeviceImplE.i();
        this.f3633e = bodyDeviceImplE.h();
    }

    @Override // com.byd.base.view.BydSwitch.a
    public void OnBydSwitchTouch(View view, MotionEvent motionEvent, boolean z) {
        if (view.isEnabled() && (motionEvent.getAction() == 1)) {
            o(view);
        }
    }

    public void a(T t) {
        List<T> list;
        if (t == null || (list = this.f3630b) == null) {
            return;
        }
        list.add(t);
        t.setVm(this);
        int iE = e(t.getTag());
        if (l(iE)) {
            return;
        }
        t.normal = Router.b().d().h(iE);
    }

    public MutableLiveData<Boolean> b() {
        return this.f3633e;
    }

    public <T extends DriverAssistModel> T c(int i) {
        List<T> list = this.f3630b;
        if (list != null && !list.isEmpty()) {
            for (int i2 = 0; i2 < this.f3630b.size(); i2++) {
                T t = this.f3630b.get(i2);
                if (t != null && i == t.getTag()) {
                    return t;
                }
            }
        }
        return null;
    }

    public List<T> d() {
        return this.f3630b;
    }

    public int e(int i) {
        if (R$string.dipilot_smart_navigation_title == i) {
            return 68;
        }
        if (R$string.dipilot_interactive_lane_title == i) {
            return 67;
        }
        if (R$string.dipilot_urban_navigation_title == i) {
            return 65;
        }
        if (R$string.dipilot_highway_navigation_title == i) {
            return 64;
        }
        if (R$string.dipilot_end_to_end_navigation_title == i) {
            return 66;
        }
        if (R$string.dipilot_urgent_lane_title == i) {
            return 0;
        }
        if (R$string.dipilot_blind_spot_detection == i) {
            return 1;
        }
        if (R$string.dipilot_title_open_door_warning == i) {
            return 2;
        }
        if (R$string.dipilot_traffic_light_title == i) {
            return 3;
        }
        if (R$string.dipilot_high_beam_title == i || R$string.dipilot_smart_matrix_light == i) {
            return 4;
        }
        if (R$string.dipilot_title_steady_body_system == i) {
            return 5;
        }
        if (R$string.dipilot_title_HDC == i) {
            return 6;
        }
        if (R$string.dipilot_title_mistake_gas == i) {
            return 7;
        }
        if (R$string.dipilot_title_panoramic_images == i) {
            return 80;
        }
        if (R$string.dipilot_iop_title == i) {
            return 69;
        }
        if (R$string.dipilot_acc_title == i) {
            return 70;
        }
        return i;
    }

    public MutableLiveData<Boolean> f() {
        return this.f3632d;
    }

    public float g(boolean z) {
        int i = R$dimen.dp_32;
        if (!z && (k() || b.b.a.i.a.d())) {
            i = R$dimen.dp_28;
        }
        return getApplication().getResources().getDimensionPixelSize(i);
    }

    public P h() {
        return this.f3631c;
    }

    public long i() {
        return Router.b().f().h();
    }

    public boolean j() {
        return b.b.a.i.a.d();
    }

    public boolean k() {
        return b.b.a.i.a.f531a;
    }

    public boolean l(int i) {
        if (i == 4 && Router.b().f().d(i)) {
            return true;
        }
        return i > 4 && i < 64;
    }

    public void m(List<T> list) {
        this.f3630b = list;
    }

    public void n(P p) {
        this.f3631c = p;
    }

    public void o(View view) {
        view.playSoundEffect(0);
        ToastUtils.show(getApplication(), R$string.special_model_feature_unavailable);
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        BydLogHelper.logI(this.f3629a, "onCleared is run");
        P p = this.f3631c;
        if (p != null) {
            p.a();
        }
        this.f3631c = null;
        List<T> list = this.f3630b;
        if (list != null && !list.isEmpty()) {
            this.f3630b.clear();
        }
        this.f3630b = null;
        this.f3632d = null;
    }
}
