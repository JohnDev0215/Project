package com.byd.util;

import android.car.Car;
import android.car.media.CarAudioManager;
import android.content.Context;
import android.provider.Settings;
import com.byd.util.c;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class b extends c {
    private CarAudioManager n;
    private int o;
    private a p;

    private static class a implements Car.CarServiceLifecycleListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<b> f2934a;

        a(b bVar) {
            this.f2934a = new WeakReference<>(bVar);
        }

        void a() {
            WeakReference<b> weakReference = this.f2934a;
            if (weakReference != null) {
                weakReference.clear();
                this.f2934a = null;
            }
        }

        @Override // android.car.Car.CarServiceLifecycleListener
        public void onLifecycleChanged(Car car, boolean z) {
            WeakReference<b> weakReference;
            if (!z || (weakReference = this.f2934a) == null || weakReference.get() == null) {
                return;
            }
            this.f2934a.get().n(car);
        }
    }

    public b(Context context) {
        super(context);
        this.p = new a(this);
        Car.createCar(context.getApplicationContext(), null, 5000L, this.p);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(Car car) {
        this.n = (CarAudioManager) car.getCarManager(Car.AUDIO_SERVICE);
        w.c("init CarAudioManager : " + this.n);
        this.o = this.n.getVolumeGroupIdForUsage(1);
    }

    @Override // com.byd.util.c
    protected void f(int i, c.a aVar) throws Settings.SettingNotFoundException {
        String str;
        if (i != 2) {
            super.f(i, aVar);
            return;
        }
        CarAudioManager carAudioManager = this.n;
        if (carAudioManager == null) {
            str = "carAudioManager is null --onLoadValues";
        } else {
            aVar.f2940c = carAudioManager.getGroupMaxVolume(this.o);
            aVar.f2939b = this.n.getGroupVolume(this.o);
            str = "max volume : " + aVar.f2940c + " min volume : " + this.n.getGroupMinVolume(this.o) + " current : " + aVar.f2939b;
        }
        w.c(str);
    }

    @Override // com.byd.util.c
    protected void g(int i, int i2) {
        if (i != 2) {
            super.g(i, i2);
            return;
        }
        CarAudioManager carAudioManager = this.n;
        if (carAudioManager == null) {
            w.c("carAudioManager is null --onValueSet");
        } else {
            carAudioManager.setGroupVolume(this.o, i2, 0);
        }
    }

    @Override // com.byd.util.c
    public void h() {
        super.h();
        a aVar = this.p;
        if (aVar != null) {
            aVar.a();
            this.p = null;
        }
        this.n = null;
    }
}
