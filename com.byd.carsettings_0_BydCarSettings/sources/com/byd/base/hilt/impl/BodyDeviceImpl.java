package com.byd.base.hilt.impl;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import androidx.lifecycle.MutableLiveData;
import b.b.a.c.a;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class BodyDeviceImpl extends AbsBYDAutoBodyworkListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2432b = "BodyDeviceImpl";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2433c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public BYDAutoBodyworkDevice f2434d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public BYDAutoEnergyDevice f2435e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbsBYDAutoEnergyListener f2436f;
    public CountDownLatch g;
    public Context h;
    public boolean i;
    public volatile boolean j;
    public MutableLiveData<Boolean> k;
    public MutableLiveData<Boolean> l;

    public BodyDeviceImpl(Context context) {
        this.h = context;
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(context);
        this.f2434d = bYDAutoBodyworkDevice;
        int autoType = bYDAutoBodyworkDevice.getAutoType();
        this.i = autoType == 182 || autoType == 208;
        this.k = new MutableLiveData<>(Boolean.FALSE);
        this.l = new MutableLiveData<>(Boolean.TRUE);
        this.f2434d.registerListener((AbsBYDAutoBodyworkListener) this);
        if (this.i) {
            this.f2435e = BYDAutoEnergyDevice.getInstance(context);
            AbsBYDAutoEnergyListener absBYDAutoEnergyListener = new AbsBYDAutoEnergyListener() { // from class: com.byd.base.hilt.impl.BodyDeviceImpl.1
                /* JADX WARN: Type inference failed for: r4v0 */
                /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
                /* JADX WARN: Type inference failed for: r4v2 */
                @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
                public void onRollBenchModeStatusChanged(int i) {
                    super.onRollBenchModeStatusChanged(i);
                    if (BodyDeviceImpl.this.f2434d == null) {
                        BydLogHelper.logW(AbsBYDAutoEnergyListener.TAG, "onRollBenchModeStatusChanged bodyworkDevice is null!");
                        return;
                    }
                    int iHasMessage = BodyDeviceImpl.this.f2434d.hasMessage(10);
                    boolean z = false;
                    ?? r4 = i != 1 ? 1 : 0;
                    BodyDeviceImpl bodyDeviceImpl = BodyDeviceImpl.this;
                    if (iHasMessage == 1 && r4 != 0) {
                        z = true;
                    }
                    bodyDeviceImpl.j = z;
                    BodyDeviceImpl.this.k.postValue(Boolean.valueOf(BodyDeviceImpl.this.j));
                    BodyDeviceImpl.this.l.postValue(Boolean.valueOf((boolean) r4));
                    EventBus.getDefault().post(new a(250001, (int) r4));
                    BydLogHelper.logW(AbsBYDAutoEnergyListener.TAG, "onRollBenchModeStatusChanged uke 0x432 state = " + iHasMessage + ",isRollBenchModeStatus = " + i);
                }
            };
            this.f2436f = absBYDAutoEnergyListener;
            this.f2435e.registerListener(absBYDAutoEnergyListener);
        }
        BydLogHelper.logD("BodyDeviceImpl", "BodyDeviceImpl autoType = " + autoType + ", isUKE = " + this.i);
    }

    public final void f() {
        if (this.f2433c != Integer.MIN_VALUE) {
            return;
        }
        if (this.g == null) {
            this.g = new CountDownLatch(1);
        }
        boolean zAwait = false;
        try {
            zAwait = this.g.await(300L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        if (this.f2433c == Integer.MIN_VALUE) {
            BydLogHelper.logE("BodyDeviceImpl", " delay 300 millis second bind not connect ret =" + zAwait);
        }
    }

    public int g() {
        f();
        return this.f2433c;
    }

    public MutableLiveData<Boolean> h() {
        return this.l;
    }

    public MutableLiveData<Boolean> i() {
        return this.k;
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener, a.b.d
    public void isConnected(boolean z) {
        if (z) {
            this.f2433c = this.f2434d.getAutoType();
            k();
            CountDownLatch countDownLatch = this.g;
            if (countDownLatch != null) {
                countDownLatch.countDown();
                this.g = null;
            }
        }
    }

    public boolean j(int i) {
        if (this.f2434d == null) {
            return false;
        }
        f();
        boolean z = this.f2434d.hasMessage(i) == 1;
        BydLogHelper.logW("BodyDeviceImpl", "hasOnline msgId = " + i + " , hasOnline = " + z);
        return z;
    }

    public final void k() {
        if (this.f2434d == null) {
            return;
        }
        n(!l(10));
        BydLogHelper.logW("BodyDeviceImpl", "initOnline, end!");
    }

    public final boolean l(int i) {
        return this.f2434d.hasMessage(i) != 1;
    }

    public void m() {
        BydLogHelper.logW("BodyDeviceImpl", "onCleared");
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f2434d;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.unregisterListener((AbsBYDAutoBodyworkListener) this);
            if (this.i) {
                this.f2435e.unregisterListener(this.f2436f);
                this.f2435e = null;
                this.f2436f = null;
            }
            this.f2434d = null;
        }
        this.k = null;
        this.l = null;
    }

    public final void n(boolean z) {
        if (!this.i) {
            this.j = z;
            this.k.postValue(Boolean.valueOf(this.j));
            BydLogHelper.logW("BodyDeviceImpl", "setMpcAndRollBenchMode not uke, mpc = " + this.j);
            return;
        }
        boolean z2 = false;
        boolean z3 = BYDAutoEnergyDevice.getInstance(this.h).getRollBenchModeStatus() != 1;
        if (z && z3) {
            z2 = true;
        }
        this.j = z2;
        BydLogHelper.logW("BodyDeviceImpl", "setMpcAndRollBenchMode uke, mpc = " + this.j + ", isNotRollBenchModeStatus = " + z3);
        this.k.postValue(Boolean.valueOf(this.j));
        this.l.postValue(Boolean.valueOf(z3));
    }

    @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
    public void onHasMessageChanged(int i, int i2) {
        boolean z = 1 == i2;
        if (i == 10) {
            n(z);
            BydLogHelper.logW("BodyDeviceImpl", "onHasMessageChanged 0x432,online = " + z);
        }
        BydLogHelper.logW("BodyDeviceImpl", "onHasMessageChanged = " + i + ",online = " + z);
    }
}
