package com.byd.util;

import android.app.Activity;
import android.app.Application;
import android.car.VehicleAreaDoor;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.os.Message;
import com.byd.auto_photo.AutoFinishActivity;
import com.byd.auto_photo.BaseAutoFinishActivity;
import com.byd.auto_photo.PhotoBaseApp;
import com.byd.auto_photo.R;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public class GearboxUtil {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile GearboxUtil f2930a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Application f2931b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private BYDAutoGearboxDevice f2932c;
    private MyAbsBYDAutoGearboxListener d;
    private boolean f;
    private b h;
    private LinkedList<Activity> e = new LinkedList<>();
    private c g = new c(this);

    class MyAbsBYDAutoGearboxListener extends AbsBYDAutoGearboxListener {
        private boolean isBind;

        MyAbsBYDAutoGearboxListener() {
        }

        private void doGearboxChange(int i) {
            GearboxUtil gearboxUtil;
            int i2 = 1;
            if (i == 2) {
                gearboxUtil = GearboxUtil.this;
            } else if (i == 1) {
                GearboxUtil.this.n(2);
                return;
            } else {
                gearboxUtil = GearboxUtil.this;
                i2 = 3;
            }
            gearboxUtil.n(i2);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener, a.b.d
        public void isConnected(boolean z) {
            this.isBind = z;
            w.c("PhotoApp GearListener isConnected : " + z);
            if (z) {
                GearboxUtil.this.d();
            }
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearChanged(int i) {
            w.c("PhotoApp onGearChanged: level:" + i);
            doGearboxChange(i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeLevelChanged(int i) {
            w.c("CameraApp onGearboxAutoModeLevelChanged: level:" + i);
            doGearboxChange(i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxAutoModeTypeChanged(int i) {
            if (com.byd.auto_photo.a.f1178a.intValue() == 53) {
                return;
            }
            w.c("PhotoApp onGearboxAutoModeTypeChanged: level:" + i);
            doGearboxChange(i);
        }

        @Override // android.hardware.bydauto.gearbox.AbsBYDAutoGearboxListener
        public void onGearboxManualModeLevelChanged(int i) {
            GearboxUtil gearboxUtil;
            if (com.byd.auto_photo.a.f1178a.intValue() == 53) {
                return;
            }
            w.c("PhotoApp onGearboxManualModeLevelChanged: level:" + i);
            int i2 = 1;
            if (i == 1) {
                gearboxUtil = GearboxUtil.this;
            } else {
                gearboxUtil = GearboxUtil.this;
                i2 = 3;
            }
            gearboxUtil.n(i2);
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                w.c("GearboxChecker receive ACTION_CLOSE_SYSTEM_DIALOGS : " + intent.getStringExtra("reason"));
                GearboxUtil.this.g.removeCallbacksAndMessages(null);
            }
        }
    }

    private static class c extends b.a.d<GearboxUtil> {
        public c(GearboxUtil gearboxUtil) {
            super(gearboxUtil);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(GearboxUtil gearboxUtil, Message message) {
            if (message.what != 1) {
                return;
            }
            gearboxUtil.r();
        }
    }

    private GearboxUtil(Application application) {
        this.f2931b = application;
        this.f = application.getResources().getBoolean(R.bool.oversea_check_p_gear);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        MyAbsBYDAutoGearboxListener myAbsBYDAutoGearboxListener;
        if (com.byd.auto_photo.a.f1178a.intValue() == 53 && ((myAbsBYDAutoGearboxListener = this.d) == null || !myAbsBYDAutoGearboxListener.isBind)) {
            w.c("PhotoApp: Gearbox server not bind");
            return;
        }
        int iG = g();
        int iH = h();
        w.c("PhotoApp: GearBoxAutoModeType:" + iG + " GEARBOX_AUTO_MODE_P : 1 GEARBOX_AUTO_MODE_R : 2");
        if (iG == 2 || iH == 1) {
            n(1);
        } else if (iG != 1) {
            n(3);
        }
    }

    private void e() {
        this.g.removeCallbacksAndMessages(null);
        if (BaseAutoFinishActivity.u()) {
            BaseAutoFinishActivity.q();
        }
    }

    private int f() {
        String strA = v.d().a("persist.sys.energytype");
        w.c("persist.sys.energytype : " + strA);
        if (strA == null || "".equals(strA)) {
            return 0;
        }
        try {
            return Integer.parseInt(strA);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static GearboxUtil i(Application application) {
        if (f2930a == null) {
            synchronized (GearboxUtil.class) {
                if (f2930a == null) {
                    f2930a = new GearboxUtil(application);
                }
            }
        }
        return f2930a;
    }

    private void j() {
        try {
            Class.forName("android.hardware.bydauto.gearbox.BYDAutoGearboxDevice");
            this.f2932c = BYDAutoGearboxDevice.getInstance(this.f2931b);
            if (this.f) {
                this.d = new MyAbsBYDAutoGearboxListener();
                this.f2932c.registerListener((AbsBYDAutoGearboxListener) this.d, new int[]{e.k(BYDAutoFeatureIds.class, "GEARBOX_AUTO_MODE_TYPE", 555745336), e.k(BYDAutoFeatureIds.class, "GEARBOX_MANUAL_MODE_LEVEL", BYDAutoFeatureIds.GEARBOX_MANUAL_MODE_LEVEL)});
                if (com.byd.auto_photo.a.f1178a.intValue() == 53) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.c("startListener Exception : " + e.getMessage());
        }
    }

    public static boolean k() {
        return f2930a != null;
    }

    private void p() {
        if (this.f2932c != null) {
            w.c("==resumeCheck==");
            d();
        }
    }

    private void q() {
        this.g.removeMessages(1);
        this.g.sendEmptyMessageDelayed(1, 500L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        w.c("show AutoFinish dialog : " + this.e.size());
        if (this.e.size() <= 0) {
            w.c("can't show tips for stop status");
            return;
        }
        Intent intent = new Intent(this.f2931b, (Class<?>) AutoFinishActivity.class);
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        this.f2931b.startActivity(intent);
    }

    public int g() {
        if (!this.f && this.f2932c == null) {
            j();
        }
        if (this.f2932c != null) {
            if (com.byd.auto_photo.a.f1178a.intValue() == 53) {
                int iF = f();
                try {
                    Class<?> cls = this.f2932c.getClass();
                    int iIntValue = ((Integer) (iF != 0 ? iF != 2 ? cls.getMethod("getGear", new Class[0]) : cls.getMethod("getGearboxAutoModeLevel", new Class[0]) : cls.getMethod("getGearboxAutoModeType", new Class[0])).invoke(this.f2932c, new Object[0])).intValue();
                    w.c("getGear -> " + iIntValue);
                    return iIntValue;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                return this.f2932c.getGearboxAutoModeType();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            w.c("mBYDAutoGearboxDevice is null");
        }
        return 0;
    }

    public int h() {
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f2932c;
        if (bYDAutoGearboxDevice == null) {
            return 0;
        }
        try {
            return bYDAutoGearboxDevice.getGearboxManualModeLevel();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public void l(Activity activity) {
        StringBuilder sb;
        if (this.e.contains(activity)) {
            sb = new StringBuilder();
            sb.append("onStart ");
            sb.append(activity.getClass().getName());
            sb.append(" but exist");
        } else {
            this.e.addLast(activity);
            if (this.h == null) {
                b bVar = new b();
                this.h = bVar;
                this.f2931b.registerReceiver(bVar, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
            }
            sb = new StringBuilder();
            sb.append("onStart : ");
            sb.append(activity.getClass().getName());
            sb.append(" ");
            sb.append(this.e.size());
        }
        w.c(sb.toString());
        p();
    }

    public void m(Activity activity) {
        Iterator<Activity> it = this.e.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Activity next = it.next();
            if (next.equals(activity)) {
                this.e.remove(next);
                break;
            }
        }
        w.c("onStop : " + activity.getClass().getName() + " " + this.e.size());
        if (this.e.size() == 0) {
            this.g.removeCallbacksAndMessages(null);
            b bVar = this.h;
            if (bVar != null) {
                this.f2931b.unregisterReceiver(bVar);
                this.h = null;
            }
            w.c("auto finish showing : " + BaseAutoFinishActivity.u());
        }
    }

    public void n(int i) {
        w.c("PhotoApp: optForGearboxState :" + i);
        if (i != 1) {
            if (i != 3) {
                e();
                return;
            } else {
                q();
                return;
            }
        }
        e();
        w.c("PhotoApp: receive GEARBOX_AUTO_MODE_R ---> moveTaskToBack");
        Set<Activity> setB = PhotoBaseApp.b();
        if (setB.size() > 0) {
            int size = setB.size();
            Activity[] activityArr = new Activity[size];
            setB.toArray(activityArr);
            for (int i2 = 0; i2 < size; i2++) {
                w.c(activityArr[i2].getClass().getName() + " moveTaskToBack " + activityArr[i2].moveTaskToBack(true));
            }
        }
    }

    public void o() {
        w.c("PhotoApp: release gearbox listen");
        this.g.removeCallbacksAndMessages(null);
        this.g.release();
        BYDAutoGearboxDevice bYDAutoGearboxDevice = this.f2932c;
        if (bYDAutoGearboxDevice != null) {
            MyAbsBYDAutoGearboxListener myAbsBYDAutoGearboxListener = this.d;
            if (myAbsBYDAutoGearboxListener != null) {
                try {
                    bYDAutoGearboxDevice.unregisterListener((AbsBYDAutoGearboxListener) myAbsBYDAutoGearboxListener);
                } catch (Exception unused) {
                }
                this.d = null;
            }
            this.f2932c = null;
        }
        f2930a = null;
    }

    public void s() {
        if (this.f2932c != null) {
            d();
            return;
        }
        w.c("PhotoApp: start gearbox listen");
        j();
        d();
    }
}
