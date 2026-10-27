package com.byd.provider;

import android.car.VehicleAreaDoor;
import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.QrCodeShareActivity;
import com.byd.auto_photo.R;
import com.byd.bean.FileBean;
import com.byd.bean.VideoControllerInfo;
import com.byd.util.e;
import com.byd.util.m;
import com.byd.util.w;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile a f2879a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private boolean f2880b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private HashMap<String, Boolean> f2881c = new HashMap<>();
    private Context d;

    private a(ContentProvider contentProvider) {
        Context applicationContext = contentProvider.getContext().getApplicationContext();
        this.d = applicationContext;
        this.f2880b = applicationContext.getResources().getInteger(R.integer.activity_main_enable) == 1;
    }

    public static a c(ContentProvider contentProvider) {
        if (f2879a == null) {
            synchronized (a.class) {
                if (f2879a == null) {
                    f2879a = new a(contentProvider);
                }
            }
        }
        return f2879a;
    }

    private boolean d(String str, Bundle bundle) {
        if ("resize_player".equals(str)) {
            b.a.f.a.e().f(new VideoControllerInfo(bundle.getInt("opt"), bundle.getBoolean("fullstate")));
            return true;
        }
        if (!"seek_to".equals(str)) {
            return false;
        }
        b.a.f.a.e().f(new VideoControllerInfo(bundle.getInt("opt"), bundle.getInt("time"), bundle.getBoolean("baseCurrent")));
        return true;
    }

    private Bundle e(Bundle bundle) {
        w.c("share by qrCode from : " + bundle.getString("pkg_name"));
        ArrayList<String> stringArrayList = bundle.getStringArrayList("share_beans");
        if (stringArrayList == null || stringArrayList.size() == 0) {
            w.c("not share data receive in PhotoProvider");
            return null;
        }
        int size = stringArrayList.size();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>(size);
        int i = 0;
        while (true) {
            int i2 = 1;
            if (i >= size) {
                Intent intent = new Intent(this.d, (Class<?>) QrCodeShareActivity.class);
                intent.setFlags(67108864);
                intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
                intent.putParcelableArrayListExtra("extras_share_beans", arrayList);
                this.d.startActivity(intent);
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("success", true);
                return bundle2;
            }
            File file = new File(stringArrayList.get(i));
            if (file.exists() && file.length() != 0) {
                String strA = m.a(file.getName());
                if (m.d(strA)) {
                    i2 = 2;
                } else if (!m.b(strA)) {
                    w.c("illegal file type : " + strA);
                }
                arrayList.add(new FileBean(file.getName(), file.getAbsolutePath(), 2, i2, file.lastModified()));
            }
            i++;
        }
    }

    private void f(ContentProvider contentProvider, Bundle bundle) {
        if (!this.f2880b) {
            w.c("MainActivity disable , function break");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(contentProvider.getContext().getApplicationInfo().packageName, "com.byd.auto_photo.MainActivity");
        intent.setFlags(67108864);
        intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
        intent.putExtras(bundle);
        contentProvider.getContext().startActivity(intent);
    }

    public Bundle a(PhotoProvider photoProvider, String str, String str2, String str3, String str4, Bundle bundle) {
        String str5;
        if (!str.equals(str2)) {
            str5 = "authority is error : " + str2;
        } else if (bundle == null) {
            str5 = "extras is null";
        } else if ("qrcode_share".equals(str3)) {
            if (photoProvider.d()) {
                return e(bundle);
            }
            str5 = "This platform does not support QR code share";
        } else {
            if (!"activityState".equals(str3)) {
                Bundle bundle2 = new Bundle();
                String string = bundle.getString("pkgName");
                w.c("Photo Provider call : " + str2 + " " + str3 + " by app : " + string);
                if (!e.I(string)) {
                    w.c("not support for this app : " + string);
                    return bundle2;
                }
                bundle2.putBoolean("success", true);
                if (d(str3, bundle)) {
                    return bundle2;
                }
                if ("close_album".equals(str3)) {
                    LocalBroadcastManager.getInstance(photoProvider.getContext()).sendBroadcast(new Intent("com.byd.ACTION_ALBUM_CLOSE"));
                    return bundle2;
                }
                if (!this.f2880b) {
                    w.c("MainActivity disable");
                    return bundle2;
                }
                if ("open_album".equals(str3)) {
                    f(photoProvider, bundle);
                    return bundle2;
                }
                if ("search".equals(str3)) {
                    if (!(this.f2881c.containsKey(MainActivity.class.getName()) ? this.f2881c.get(MainActivity.class.getName()).booleanValue() : false)) {
                        w.c("to MainActivity --search");
                        f(photoProvider, bundle);
                        return bundle2;
                    }
                }
                w.c("post msg");
                b.a.f.a.e().f(bundle);
                return bundle2;
            }
            String string2 = bundle.getString("activityName");
            boolean z = bundle.getBoolean("activityStart");
            this.f2881c.put(string2, Boolean.valueOf(z));
            str5 = string2 + " -> " + z;
        }
        w.c(str5);
        return null;
    }

    public void b() {
        this.f2881c.clear();
    }
}
