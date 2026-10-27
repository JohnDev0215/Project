package com.byd.util;

import android.car.Car;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.media.AudioManager;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import com.byd.auto_photo.R;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f2935a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f2936b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private PopupWindow f2937c;
    private View d;
    private ImageView e;
    protected ProgressBar f;
    private AudioManager g;
    private int h = 17;
    private int i;
    private int j;
    private int k;
    protected SparseArray<a> l;
    private int m;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2938a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f2939b = -1;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f2940c = -1;
        int d;
        int e;

        a(int i) {
            this.f2938a = i;
        }
    }

    public c(Context context) {
        this.f2935a = 10;
        this.f2936b = context;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.window_bri_vol_controller, (ViewGroup) null);
        this.d = viewInflate;
        this.e = (ImageView) viewInflate.findViewById(R.id.iv_control_icon);
        this.f = (ProgressBar) this.d.findViewById(R.id.progress_control);
        this.g = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        Point point = new Point();
        windowManager.getDefaultDisplay().getRealSize(point);
        int iMin = Math.min(point.x, point.y) / 4;
        this.k = iMin;
        if (iMin > 400) {
            this.k = 400;
        }
        this.l = new SparseArray<>(5);
        PowerManager powerManager = (PowerManager) context.getSystemService(Car.POWER_SERVICE);
        try {
            Method declaredMethod = powerManager.getClass().getDeclaredMethod("getMinimumScreenBrightnessSetting", new Class[0]);
            declaredMethod.setAccessible(true);
            this.f2935a = ((Integer) declaredMethod.invoke(powerManager, new Object[0])).intValue();
            w.c("MIN_BRIGHTNESS:" + this.f2935a);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int b(int i, int i2, int i3, int i4) {
        int iRound = Math.round((i3 * i2) / i4) + i;
        if (iRound <= i2) {
            i2 = iRound < 0 ? 0 : iRound;
        }
        if (i2 != i) {
            return i2;
        }
        return -1;
    }

    public void a() {
        PopupWindow popupWindow = this.f2937c;
        if (popupWindow != null) {
            popupWindow.dismiss();
            this.f2937c = null;
            a aVar = this.l.get(this.m);
            if (aVar == null) {
                return;
            }
            aVar.f2940c = -1;
            aVar.f2939b = -1;
        }
    }

    public int c() {
        if (this.l.get(this.m) == null) {
            return 0;
        }
        return this.l.get(this.m).e;
    }

    public int d() {
        if (this.l.get(this.m) == null) {
            return 0;
        }
        return this.l.get(this.m).d;
    }

    public boolean e() {
        PopupWindow popupWindow = this.f2937c;
        return popupWindow != null && popupWindow.isShowing();
    }

    protected void f(int i, a aVar) throws Settings.SettingNotFoundException {
        int streamMaxVolume;
        if (i == 1) {
            int i2 = Settings.System.getInt(this.f2936b.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
            aVar.f2939b = i2;
            int i3 = this.f2935a;
            aVar.f2939b = i2 - i3;
            streamMaxVolume = 255 - i3;
        } else {
            aVar.f2939b = this.g.getStreamVolume(3);
            streamMaxVolume = this.g.getStreamMaxVolume(3);
        }
        aVar.f2940c = streamMaxVolume;
        w.c("onLoadValues -- mode : " + i + " current : " + aVar.f2939b + " max:" + aVar.f2940c);
    }

    protected void g(int i, int i2) {
        if (i != 1) {
            this.g.setStreamVolume(3, i2, 0);
            return;
        }
        int i3 = i2 + this.f2935a;
        w.c("set brightness value : " + i3);
        Settings.System.putInt(this.f2936b.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, i3);
    }

    public void h() {
        SparseArray<a> sparseArray = this.l;
        if (sparseArray != null) {
            sparseArray.clear();
            this.l = null;
        }
    }

    public void i(int i) {
        this.h = i;
    }

    public void j(int i) {
        this.j = i;
    }

    public void k(int i, View view, int i2) {
        l(i, view, i2, this.k);
    }

    public void l(int i, View view, int i2, int i3) {
        int i4;
        this.m = i;
        if (this.f2937c == null) {
            PopupWindow popupWindow = new PopupWindow(this.d, this.f2936b.getResources().getDimensionPixelSize(R.dimen.bright_vol_width), this.f2936b.getResources().getDimensionPixelSize(R.dimen.bright_vol_height));
            this.f2937c = popupWindow;
            popupWindow.setOutsideTouchable(true);
            this.f2937c.setFocusable(false);
            this.f2937c.setBackgroundDrawable(new ColorDrawable());
        }
        a aVar = this.l.get(i);
        if (aVar == null) {
            aVar = new a(i);
            this.l.put(i, aVar);
        }
        this.e.setImageResource(i == 1 ? R.drawable.icon_light : R.drawable.icon_volume);
        try {
            if (aVar.f2939b == -1 || (i4 = aVar.f2940c) == -1) {
                f(i, aVar);
                int i5 = aVar.f2940c;
                if (i5 * 10 < i3) {
                    i3 = i5 * 10;
                }
                aVar.d = 2;
                aVar.e = 10;
            } else if (i4 * 10 < i3) {
                i3 = i4 * 10;
            }
            int iB = b(aVar.f2939b, aVar.f2940c, i2, i3);
            w.c("mode:" + i + " current:" + aVar.f2939b + " max:" + aVar.f2940c + " new value:" + iB + " offset:" + i2 + " minStep:" + aVar.d + " maxStep:" + aVar.e + " maxOffset:" + i3);
            if (iB > -1) {
                aVar.f2939b = iB;
                g(i, iB);
            }
        } catch (Exception e) {
            e.printStackTrace();
            w.c("showController exception : " + e.getMessage());
        }
        this.f.setMax(aVar.f2940c);
        this.f.setProgress(aVar.f2939b);
        if (this.f2937c.isShowing()) {
            return;
        }
        this.f2937c.showAtLocation(view, this.h, this.i, this.j);
    }
}
