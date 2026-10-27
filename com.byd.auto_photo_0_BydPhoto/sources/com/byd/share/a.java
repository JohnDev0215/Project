package com.byd.share;

import android.app.vehicle.CustomVehicleConfig;
import android.car.Car;
import android.car.VehicleAreaDoor;
import android.content.Context;
import android.content.Intent;
import android.cross.BYDCrossEventValue;
import android.cross.device.AbsBYDCrossListener;
import android.cross.device.BYDCrossDevice;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.rse.AbsBYDAutoRSEListener;
import android.hardware.bydauto.rse.BYDAutoRSEDevice;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import androidx.annotation.NonNull;
import com.byd.auto_photo.ImageShowActivity;
import com.byd.auto_photo.R;
import com.byd.auto_photo.VideoPlayActivity;
import com.byd.bean.FileBean;
import com.byd.util.t;
import com.byd.util.v;
import com.byd.util.w;
import com.google.gson.Gson;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2894a;
    private f A;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2895b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2896c;
    private boolean d;
    private boolean e;
    private boolean f = true;
    private boolean g;
    private boolean h;
    private boolean i;
    private Context j;
    private boolean k;
    private AbsBYDCrossListener l;
    private Gson m;
    private boolean n;
    private Handler o;
    private int p;
    private PowerManager q;
    private BYDAutoRSEDevice r;
    private BYDAutoBodyworkDevice s;
    private boolean t;
    private boolean u;
    private boolean v;
    private Handler w;
    private AbsBYDAutoRSEListener x;
    private AbsBYDAutoBodyworkListener y;
    private String z;

    /* JADX INFO: renamed from: com.byd.share.a$a, reason: collision with other inner class name */
    class HandlerC0079a extends Handler {
        HandlerC0079a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            ShareItem shareItem;
            ArrayList<Integer> arrayList;
            String str = (String) message.obj;
            w.c(a.this.p + " receive msg info : " + str);
            try {
                shareItem = (ShareItem) a.this.m.fromJson(str, ShareItem.class);
            } catch (Exception e) {
                e.printStackTrace();
                w.c("parse from json exception : " + e.getMessage());
                shareItem = null;
            }
            if (shareItem == null) {
                w.c("data transformation fail!");
                return;
            }
            if (a.this.p == 1 && (arrayList = shareItem.toIdList) != null && arrayList.contains(1)) {
                if (shareItem.getFromId() == 1) {
                    w.c("from dev and target is ivi !!!");
                    return;
                }
                String strA = v.d().a("sys.byd.pano_start");
                w.c("sys.byd.pano_start : " + strA);
                if (strA != null && "1".equals(strA.trim())) {
                    ShareItem shareItem2 = new ShareItem();
                    shareItem2.setFromId(1).setToIds(shareItem.getFromId()).setCode(-1).setPaths(shareItem.getPaths()).setDataType(shareItem.getDataType());
                    a.this.S(shareItem2);
                    return;
                }
            }
            ArrayList<Integer> arrayList2 = shareItem.toIdList;
            if (arrayList2 != null) {
                Iterator<Integer> it = arrayList2.iterator();
                while (it.hasNext()) {
                    if (it.next().intValue() == a.this.p) {
                        if (shareItem.getCode() == -1) {
                            a.this.w.sendEmptyMessage(2);
                            return;
                        }
                        a.this.n(shareItem);
                        w.c("start show : " + shareItem);
                        a.this.w.obtainMessage(1, shareItem).sendToTarget();
                        return;
                    }
                }
            }
        }
    }

    class b extends AbsBYDAutoBodyworkListener {
        b() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
            if (i == BYDAutoFeatureIds.Bodywork.BODYWORK_ONLINE_HAS_0x03A000) {
                w.c("fse online state change!");
                a aVar = a.this;
                aVar.L(aVar.s);
                a.this.Q();
            }
        }
    }

    class c extends AbsBYDAutoRSEListener {
        c() {
        }

        @Override // android.hardware.bydauto.rse.AbsBYDAutoRSEListener
        public void onDataEventChanged(int i, BYDAutoEventValue bYDAutoEventValue) {
            if (i == BYDAutoFeatureIds.RSE_L_TO_IVI_PAD_SWITCH_MACHINE_STATUS || i == BYDAutoFeatureIds.RSE_R_TO_IVI_PAD_SWITCH_MACHINE_STATE) {
                w.c("rse switch status change!");
                a aVar = a.this;
                aVar.O(aVar.r);
                a.this.Q();
            }
        }
    }

    class d extends AbsBYDCrossListener {
        d() {
        }

        public void onDataEventChanged(int i, BYDCrossEventValue bYDCrossEventValue) {
            a.this.H(i, bYDCrossEventValue);
        }

        public void onError(int i, String str) {
        }
    }

    private static class e extends b.a.d<a> {
        public e(a aVar) {
            super(aVar);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(a aVar, Message message) {
            int i = message.what;
            if (i == 1) {
                ShareItem shareItem = (ShareItem) message.obj;
                w.c("handleMessage startActivity ");
                aVar.I(shareItem);
            } else if (i == 2) {
                aVar.T();
            } else {
                if (i != 3) {
                    return;
                }
                aVar.G();
            }
        }
    }

    public interface f {
        void a();

        void b();
    }

    private a(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.j = applicationContext;
        this.t = applicationContext.getResources().getBoolean(R.bool.share_more_screen_by_event_center);
        this.w = new e(this);
        this.q = (PowerManager) this.j.getSystemService(Car.POWER_SERVICE);
        if (this.j.getResources().getInteger(R.integer.support_share_more_screen) == 1) {
            try {
                Class.forName("android.cross.device.BYDCrossDevice");
                this.k = true;
                U();
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
                this.k = false;
            }
        } else {
            this.k = false;
        }
        this.m = new Gson();
        this.p = q(this.j);
        w.c("current device : " + this.p);
    }

    private boolean E() {
        return this.q.isInteractive();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G() {
        f fVar = this.A;
        if (fVar == null) {
            return;
        }
        fVar.b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void I(ShareItem shareItem) {
        Intent intent;
        t.a(this.j);
        t.b(this.j);
        if (shareItem.isMoreThanOneItem() || shareItem.getDataType() != 2) {
            intent = new Intent(this.j, (Class<?>) ImageShowActivity.class);
        } else {
            intent = new Intent(this.j, (Class<?>) VideoPlayActivity.class);
            ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
            String str = shareItem.getPaths().get(0);
            File file = new File(str);
            arrayList.add(new FileBean(file.getName(), str, 2, 2, file.lastModified()));
            intent.putParcelableArrayListExtra("share_items", arrayList);
            intent.putExtra("current_index", 0);
            intent.putExtra("from_service", true);
        }
        intent.putExtra("share_item", shareItem);
        intent.putExtra("more_dev_share", true);
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        intent.addFlags(32768);
        intent.addFlags(8388608);
        this.j.startActivity(intent);
    }

    private void K(BYDCrossDevice bYDCrossDevice) {
        this.f2896c = bYDCrossDevice.get(-15728638, Integer.TYPE).intValue == 1;
        w.c("fse online : " + this.f2896c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void L(BYDAutoBodyworkDevice bYDAutoBodyworkDevice) {
        boolean z = true;
        try {
            int i = bYDAutoBodyworkDevice.get(new int[]{BYDAutoFeatureIds.Bodywork.BODYWORK_ONLINE_HAS_0x03A000}, Integer.TYPE).intValue;
            if (i != 1) {
                z = false;
            }
            this.g = z;
            w.c("fse switch state : " + i + " | fse on : " + this.g);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void M(BYDCrossDevice bYDCrossDevice) {
        this.f2895b = bYDCrossDevice.get(-15728639, Integer.TYPE).intValue == 1;
        w.c("ivi online : " + this.f2895b);
    }

    private void N(BYDCrossDevice bYDCrossDevice) {
        Class cls = Integer.TYPE;
        this.d = bYDCrossDevice.get(-15728637, cls).intValue == 1;
        this.e = bYDCrossDevice.get(-15728636, cls).intValue == 1;
        w.c("left rse online : " + this.d + " | right rse online : " + this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(BYDAutoRSEDevice bYDAutoRSEDevice) {
        boolean z = true;
        try {
            int[] iArr = {BYDAutoFeatureIds.RSE_L_TO_IVI_PAD_SWITCH_MACHINE_STATUS};
            Class<?> cls = Integer.TYPE;
            this.h = bYDAutoRSEDevice.get(iArr, cls).intValue == 0;
            if (bYDAutoRSEDevice.get(new int[]{BYDAutoFeatureIds.RSE_R_TO_IVI_PAD_SWITCH_MACHINE_STATE}, cls).intValue != 0) {
                z = false;
            }
            this.i = z;
            w.c("left rse on : " + this.h + " | right rse on : " + this.i);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() {
        this.w.removeMessages(3);
        this.w.sendEmptyMessageDelayed(3, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void T() {
        f fVar = this.A;
        if (fVar == null) {
            return;
        }
        fVar.a();
    }

    private void U() {
        HandlerThread handlerThread = new HandlerThread("share_msg");
        handlerThread.start();
        this.o = new HandlerC0079a(handlerThread.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n(ShareItem shareItem) {
        String strR = r();
        if (strR == null) {
            return;
        }
        if (this.z == null) {
            this.z = Environment.getExternalStoragePublicDirectory("/").getAbsolutePath();
        }
        ArrayList<String> paths = shareItem.getPaths();
        if (paths == null) {
            w.c("no share paths");
            return;
        }
        int size = paths.size();
        for (int i = 0; i < size; i++) {
            if (paths.get(i).startsWith(strR)) {
                shareItem.getPaths().set(i, paths.get(i).replace(strR, this.z));
            }
        }
    }

    public static int q(Context context) {
        try {
            Class.forName("android.app.vehicle.CustomVehicleConfig");
            int deviceType = CustomVehicleConfig.getInstance(context).getDeviceType();
            w.c("initCurrentDev : " + deviceType);
            int i = 1;
            if (deviceType != 1) {
                i = 2;
                if (deviceType != 2) {
                    if (deviceType == 3) {
                        return 4;
                    }
                    if (deviceType != 4) {
                        return (deviceType == 7 || deviceType == 8) ? 16 : 0;
                    }
                    return 8;
                }
            }
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("getCurrentDevType Exception : " + e2.getMessage());
            return 0;
        }
    }

    private String r() {
        int i = this.p;
        if (i == 1) {
            return "/storage/FFFF-FFFF";
        }
        if (i == 2) {
            return "/storage/FFFF-FFFC";
        }
        if (i == 4) {
            return "/storage/FFFF-FFFE";
        }
        if (i == 8 || i == 16) {
            return "/storage/FFFF-FFFD";
        }
        return null;
    }

    public static a s(Context context) {
        if (f2894a == null) {
            synchronized (a.class) {
                if (f2894a == null) {
                    f2894a = new a(context);
                }
            }
        }
        return f2894a;
    }

    public static boolean t() {
        return f2894a != null;
    }

    public boolean A() {
        if (this.p == 16) {
            return true;
        }
        return this.f2895b;
    }

    public boolean B() {
        return this.d;
    }

    public boolean C() {
        return this.e && !this.v;
    }

    public boolean D() {
        return this.i;
    }

    public boolean F() {
        return this.k && (this.f2895b || this.f2896c || this.d || this.e || this.v);
    }

    public void H(int i, BYDCrossEventValue bYDCrossEventValue) {
        w.c("onDataEventChanged: " + bYDCrossEventValue.isIntValid() + " " + bYDCrossEventValue.isFloatValid() + " " + bYDCrossEventValue.isBufferValid());
        if (i == -13631473) {
            if (bYDCrossEventValue.isBufferValid()) {
                if (!E()) {
                    w.c("screen off , return");
                    return;
                } else {
                    this.o.obtainMessage(1, new String(bYDCrossEventValue.bufferDataValue, StandardCharsets.UTF_8)).sendToTarget();
                    return;
                }
            }
            return;
        }
        switch (i) {
            case -15728639:
                w.c("ivi online state change !");
                M(BYDCrossDevice.getInstance(this.j));
                break;
            case -15728638:
                w.c("fse online state change !");
                K(BYDCrossDevice.getInstance(this.j));
                break;
            case -15728637:
            case -15728636:
                w.c("rse online state change !");
                N(BYDCrossDevice.getInstance(this.j));
                break;
            default:
                return;
        }
        Q();
    }

    public void J() {
        w.c("more dev state : " + this.k + " " + this.f2895b + " " + this.f2896c + " " + this.d + " " + this.e + " " + this.v);
    }

    public void P() {
        w.c("MoreDevShareManager release");
        if (this.k) {
            if (this.t || this.l != null) {
                Handler handler = this.o;
                if (handler != null) {
                    handler.getLooper().quit();
                    this.o = null;
                }
                if (!this.t) {
                    BYDCrossDevice.getInstance(this.j).unregisterListener(this.l);
                }
                BYDAutoRSEDevice bYDAutoRSEDevice = this.r;
                if (bYDAutoRSEDevice != null) {
                    AbsBYDAutoRSEListener absBYDAutoRSEListener = this.x;
                    if (absBYDAutoRSEListener != null) {
                        try {
                            bYDAutoRSEDevice.unregisterListener(absBYDAutoRSEListener);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                        this.x = null;
                    }
                    this.r = null;
                }
                BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.s;
                if (bYDAutoBodyworkDevice != null) {
                    AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener = this.y;
                    if (absBYDAutoBodyworkListener != null) {
                        try {
                            bYDAutoBodyworkDevice.unregisterListener(absBYDAutoBodyworkListener);
                        } catch (Exception e3) {
                            e3.printStackTrace();
                        }
                        this.y = null;
                    }
                    this.s = null;
                }
                this.n = false;
                f2894a = null;
            }
        }
    }

    public void R(f fVar) {
        this.A = fVar;
    }

    public void S(ShareItem shareItem) {
        String json = this.m.toJson(shareItem);
        w.c("share info : " + json);
        w.c("share to CROSS_ID_ALBUM result : " + BYDCrossDevice.getInstance(this.j).set(-13631473, new BYDCrossEventValue(json.getBytes(StandardCharsets.UTF_8))));
    }

    public void m() {
        String str;
        if (this.k) {
            if (this.p == 0) {
                this.p = q(this.j);
                w.c("checkOnlineState getDeviceType : " + this.p);
            }
            if (!this.n) {
                u();
            }
            BYDCrossDevice bYDCrossDevice = BYDCrossDevice.getInstance(this.j);
            if (com.byd.util.e.F()) {
                int i = this.p;
                if (i == 1) {
                    this.f2896c = true;
                } else if (i == 2 || i == 16) {
                    this.f2895b = true;
                }
                this.g = true;
            } else {
                M(bYDCrossDevice);
                K(bYDCrossDevice);
                try {
                    if (this.s == null) {
                        this.s = BYDAutoBodyworkDevice.getInstance(this.j);
                    }
                    L(this.s);
                    if (this.y == null) {
                        b bVar = new b();
                        this.y = bVar;
                        this.s.registerListener((AbsBYDAutoBodyworkListener) bVar, new int[]{BYDAutoFeatureIds.Bodywork.BODYWORK_ONLINE_HAS_0x03A000});
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    w.c("get fse power state exception : " + e2.getMessage());
                }
            }
            N(bYDCrossDevice);
            if (this.r == null) {
                this.r = BYDAutoRSEDevice.getInstance(this.j);
            }
            try {
                O(this.r);
                if (this.x == null) {
                    c cVar = new c();
                    this.x = cVar;
                    this.r.registerListener((AbsBYDAutoRSEListener) cVar, new int[]{BYDAutoFeatureIds.RSE_L_TO_IVI_PAD_SWITCH_MACHINE_STATUS, BYDAutoFeatureIds.RSE_R_TO_IVI_PAD_SWITCH_MACHINE_STATE});
                }
            } catch (Exception e3) {
                e3.printStackTrace();
                w.c("check rse switch state exception : " + e3.getMessage());
            }
            str = "onlineIVI:" + this.f2895b + " onlineFSE:" + this.f2896c + " onlineLeftRse:" + this.d + " onlineRightRse:" + this.e + " " + this.g + " " + this.h + " " + this.i;
        } else {
            str = "not crossDevice api";
        }
        w.c(str);
    }

    public String o(String str) {
        String str2;
        String str3;
        if (this.z == null) {
            this.z = Environment.getExternalStoragePublicDirectory("/").getAbsolutePath();
        }
        int i = this.p;
        if (i == 1) {
            str2 = this.z;
            str3 = "/storage/FFFF-FFFF";
        } else if (i == 2) {
            str2 = this.z;
            str3 = "/storage/FFFF-FFFC";
        } else if (i == 4) {
            str2 = this.z;
            str3 = "/storage/FFFF-FFFE";
        } else {
            if (i != 8 && i != 16) {
                return str;
            }
            str2 = this.z;
            str3 = "/storage/FFFF-FFFD";
        }
        return str.replace(str2, str3);
    }

    public int p() {
        return this.p;
    }

    public void u() {
        if (!this.n && this.k) {
            BYDAutoRSEDevice bYDAutoRSEDevice = BYDAutoRSEDevice.getInstance(this.j);
            try {
                Class<?> cls = Class.forName("android.hardware.bydauto.BYDAutoFeatureIds$Rse");
                int iJ = com.byd.util.e.j(cls, "RSE_TV_SCREEN_CONFIG_SET");
                int iJ2 = com.byd.util.e.j(cls, "RSE_OHS_SCREEN_CONFIG_SET");
                int[] iArr = {iJ};
                Class<?> cls2 = Integer.TYPE;
                this.u = bYDAutoRSEDevice.get(iArr, cls2).intValue == 1;
                this.v = bYDAutoRSEDevice.get(new int[]{iJ2}, cls2).intValue == 1;
                w.c("has tv screen : " + this.u + " has over head screen : " + this.v);
                if (this.u) {
                    this.k = false;
                    return;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (this.t) {
                this.n = true;
                return;
            }
            if (this.l == null) {
                this.l = new d();
            }
            try {
                BYDCrossDevice.getInstance(this.j).registerListener(this.l, new int[]{-13631473, -15728639, -15728638, -15728637, -15728636});
                w.c("Photo MoreDevShareManager init success");
                this.n = true;
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public boolean v() {
        return this.g;
    }

    public boolean w() {
        return this.v;
    }

    public boolean x() {
        return this.f;
    }

    public boolean y() {
        return this.h;
    }

    public boolean z() {
        return this.f2896c;
    }
}
