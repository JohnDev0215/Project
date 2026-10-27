package com.byd.data.collect.server;

import android.content.Context;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import com.byd.data.common.energy.TravelBean;
import com.byd.data.common.sqlite.o;
import com.byd.data.common.sqlite.p;

/* JADX INFO: compiled from: EnergyOperator.java */
/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile h f3240a;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Context f3242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private BYDAutoBodyworkDevice f3243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private BYDAutoStatisticDevice f3244e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private BYDAutoInstrumentDevice f3245f;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private p f3248i;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f3241b = "EnergyOperator";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f3246g = -1;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private long f3247h = 0;
    private final AbsBYDAutoBodyworkListener j = new a();

    /* JADX INFO: compiled from: EnergyOperator.java */
    class a extends AbsBYDAutoBodyworkListener {

        /* JADX INFO: renamed from: com.byd.data.collect.server.h$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: EnergyOperator.java */
        class RunnableC0062a implements Runnable {

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ TravelBean f3250b;

            RunnableC0062a(TravelBean travelBean) {
                this.f3250b = travelBean;
            }

            @Override // java.lang.Runnable
            public void run() {
                if (h.this.i()) {
                    h.this.f3248i.a(this.f3250b);
                } else {
                    com.byd.data.common.i.f.k("AbsBYDAutoBodyworkListener", "[EnergyCollect]----[onPowerLevelChanged]----isEnergyShowConfig is not support");
                }
            }
        }

        a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i2) {
            super.onPowerLevelChanged(i2);
            com.byd.data.common.i.f.g("AbsBYDAutoBodyworkListener", "[EnergyCollect]----onPowerLevelChanged：" + i2);
            if (h.this.f3246g != i2 && (i2 == 0 || i2 == 2 || i2 == 3)) {
                if (i2 == 2) {
                    h.this.f3247h = System.currentTimeMillis();
                } else if (i2 == 0) {
                    TravelBean travelBean = new TravelBean();
                    boolean zB = com.byd.data.common.energy.b.b();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    float currentTripMileage = h.this.f3244e.getCurrentTripMileage();
                    float currentTripMileageUnit = h.this.f3244e.getCurrentTripMileageUnit();
                    float currentTripElec = h.this.f3244e.getCurrentTripElec();
                    int currentTripElecUnit = h.this.f3244e.getCurrentTripElecUnit();
                    if (currentTripMileageUnit == 2.0f) {
                        currentTripMileage = com.byd.data.common.energy.c.d(currentTripMileage);
                    }
                    if (currentTripElecUnit == 2) {
                        currentTripElec = com.byd.data.common.energy.c.c(currentTripElec);
                    }
                    travelBean.J(h.this.f3247h);
                    travelBean.z(jCurrentTimeMillis);
                    travelBean.H(currentTripMileage);
                    travelBean.y(currentTripElec);
                    if (!zB) {
                        float currentTripFuel = h.this.f3244e.getCurrentTripFuel();
                        int unit = h.this.f3245f.getUnit(2);
                        if (unit == 3) {
                            currentTripFuel = com.byd.data.common.energy.c.a(currentTripFuel);
                        } else if (unit == 4) {
                            currentTripFuel = com.byd.data.common.energy.c.b(currentTripFuel);
                        }
                        travelBean.I(currentTripFuel);
                    }
                    travelBean.M(!zB ? 1 : 0);
                    com.byd.data.common.i.f.g("AbsBYDAutoBodyworkListener", "[EnergyCollect]----addTravelBean：" + travelBean.toString());
                    if (h.this.f3247h > 0 && (currentTripElec > 0.0f || currentTripMileage > 0.0f || (!zB && travelBean.t() > 0.0f))) {
                        new Thread(new RunnableC0062a(travelBean)).start();
                    }
                    h.this.f3247h = -1L;
                }
            }
            h.this.f3246g = i2;
        }
    }

    private h(Context context) {
        this.f3242c = context.getApplicationContext();
        p pVarH = o.f().h();
        this.f3248i = pVarH;
        pVarH.d();
    }

    public static h h(Context context) {
        if (f3240a == null) {
            synchronized (h.class) {
                if (f3240a == null) {
                    f3240a = new h(context);
                }
            }
        }
        return f3240a;
    }

    public boolean i() {
        return false;
    }

    public void j() {
        this.f3243d = BYDAutoBodyworkDevice.getInstance(this.f3242c);
        this.f3244e = BYDAutoStatisticDevice.getInstance(this.f3242c);
        this.f3245f = BYDAutoInstrumentDevice.getInstance(this.f3242c);
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f3243d;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.registerListener(this.j);
        } else {
            com.byd.data.common.i.f.k("EnergyOperator", "[EnergyCollect]----mBYDAutoBodyworkDevice----registerListener null");
        }
    }

    public void k() {
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.f3243d;
        if (bYDAutoBodyworkDevice != null) {
            bYDAutoBodyworkDevice.unregisterListener(this.j);
        }
    }
}
