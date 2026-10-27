package f3;

import android.content.Context;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.os.Handler;
import android.os.Message;
import com.byd.carsettings.db.model.EnergyBean;

/* JADX INFO: compiled from: EnergyHandler.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static volatile d f4949a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f4950b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public BYDAutoEnergyDevice f4951c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4952d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4953e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AbsBYDAutoEnergyListener f4954f = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Handler f4955g = new b();

    /* JADX INFO: compiled from: EnergyHandler.java */
    public class a extends AbsBYDAutoEnergyListener {
        public a() {
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onChangePHintChanged(int i8) {
            super.onChangePHintChanged(i8);
            g5.a.a("onChangePHintChanged:" + i8);
            if (i8 == 0) {
                y4.d.n(d.this.f4950b).q(203);
            }
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onElectricPointFlagChanged(int i8) {
            super.onElectricPointFlagChanged(i8);
            g5.a.j("[曲线数据]----onElectricPointFlagChanged----flag = " + i8);
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 1;
            d.this.f4955g.sendMessage(messageObtain);
            if (d.this.f4955g.hasMessages(5)) {
                return;
            }
            d.this.f4955g.sendEmptyMessageDelayed(5, 300000L);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onHEVElectricPointFlagChanged(int i8) {
            super.onHEVElectricPointFlagChanged(i8);
            g5.a.j("[曲线数据]----onHEVElectricPointFlagChanged----flag = " + i8);
            Message messageObtain = Message.obtain();
            messageObtain.what = 3;
            messageObtain.arg1 = i8;
            messageObtain.arg2 = 0;
            d.this.f4955g.sendMessage(messageObtain);
            if (d.this.f4955g.hasMessages(5)) {
                return;
            }
            d.this.f4955g.sendEmptyMessageDelayed(5, 300000L);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onHEVLast200MElectricConChanged(float f8) {
            super.onHEVLast200MElectricConChanged(f8);
            g5.a.j("[曲线数据]----onHEVLast200MElectricConChanged----value = " + f8);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast200MElectricConChanged(float f8) {
            super.onLast200MElectricConChanged(f8);
            g5.a.j("[曲线数据]----onLast200MElectricConChanged----value = " + f8);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onLast200MOilConChanged(float f8) {
            super.onLast200MOilConChanged(f8);
            g5.a.j("[曲线数据]----onLast200MOilConChanged----value = " + f8);
        }

        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onOilPointFlagChanged(int i8) {
            super.onOilPointFlagChanged(i8);
            g5.a.j("[曲线数据]----onOilPointFlagChanged----flag = " + i8);
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.arg1 = i8;
            d.this.f4955g.sendMessage(messageObtain);
            if (d.this.f4955g.hasMessages(5)) {
                return;
            }
            d.this.f4955g.sendEmptyMessageDelayed(5, 300000L);
        }
    }

    /* JADX INFO: compiled from: EnergyHandler.java */
    public class b extends Handler {
        public b() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            try {
                int i8 = message.what;
                if (i8 == 3) {
                    d.this.f4952d = message.arg1;
                    boolean z7 = message.arg2 != 0;
                    if (d.this.f4952d != 1 || d.this.f4951c == null) {
                        return;
                    }
                    EnergyBean energyBean = new EnergyBean();
                    energyBean.A(1);
                    energyBean.q(z7 ? d.this.f4951c.getLast200MElectricCon() : d.this.f4951c.getHEVLast200MElectricCon());
                    energyBean.z(System.currentTimeMillis());
                    c.i().d(energyBean);
                    return;
                }
                if (i8 != 4) {
                    if (i8 != 5) {
                        return;
                    }
                    c.i().g();
                    f.a().c(d.this.f4950b, false);
                    return;
                }
                d.this.f4953e = message.arg1;
                if (d.this.f4953e != 1 || d.this.f4951c == null) {
                    return;
                }
                EnergyBean energyBean2 = new EnergyBean();
                energyBean2.A(2);
                energyBean2.q(d.this.f4951c.getLast200MOilCon());
                energyBean2.z(System.currentTimeMillis());
                c.i().d(energyBean2);
            } catch (Exception e8) {
                g5.a.c(e8.getMessage());
            }
        }
    }

    public d(Context context) {
        this.f4950b = context.getApplicationContext();
    }

    public static d h(Context context) {
        if (f4949a == null) {
            synchronized (d.class) {
                if (f4949a == null) {
                    f4949a = new d(context);
                }
            }
        }
        return f4949a;
    }

    public void i() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(this.f4950b);
        this.f4951c = bYDAutoEnergyDevice;
        if (bYDAutoEnergyDevice != null) {
            bYDAutoEnergyDevice.registerListener(this.f4954f);
        } else {
            g5.a.c("[曲线数据]----mBYDAutoEnergyDevice----registerListener null");
        }
    }

    public void j() {
        BYDAutoEnergyDevice bYDAutoEnergyDevice = this.f4951c;
        if (bYDAutoEnergyDevice != null) {
            bYDAutoEnergyDevice.unregisterListener(this.f4954f);
        }
        this.f4955g.removeCallbacksAndMessages(null);
        this.f4955g = null;
    }
}
