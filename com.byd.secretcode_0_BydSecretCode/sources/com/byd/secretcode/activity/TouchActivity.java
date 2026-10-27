package com.byd.secretcode.activity;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.hardware.bydauto.audio.AbsBYDAutoAudioListener;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.reflect.Method;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class TouchActivity extends AppCompatActivity {
    public WifiP2pManager b;
    public WifiP2pManager.Channel c;
    public WifiManager d;
    public BYDAutoTestDevice e;
    public ArrayList<Byte> f = new ArrayList<>();
    public ContentResolver g;
    public d h;

    public class a implements WifiP2pManager.ActionListener {
        public final /* synthetic */ String a;

        /* JADX INFO: renamed from: com.byd.secretcode.activity.TouchActivity$a$a, reason: collision with other inner class name */
        public class C0009a implements WifiP2pManager.ActionListener {
            public C0009a() {
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onFailure(int i) {
                Log.d("TouchActivity", "Change DeviceName Failed!");
            }

            @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
            public void onSuccess() {
                Log.d("TouchActivity", "Change DeviceName Success!");
            }
        }

        public a(String str) {
            this.a = str;
        }

        @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
        public void onFailure(int i) {
            Log.d("TouchActivity", "Change DeviceName Failed!" + i);
        }

        @Override // android.net.wifi.p2p.WifiP2pManager.ActionListener
        public void onSuccess() {
            Log.d("TouchActivity", "Change DeviceName Success!");
            try {
                Log.d("TouchActivity", "进入修改名字");
                Method method = TouchActivity.this.b.getClass().getMethod("setDeviceName", WifiP2pManager.Channel.class, String.class, WifiP2pManager.ActionListener.class);
                TouchActivity touchActivity = TouchActivity.this;
                method.invoke(touchActivity.b, touchActivity.c, this.a, new C0009a());
            } catch (Exception e) {
                Log.d("TouchActivity", "错误：" + e);
            }
        }
    }

    public class b extends AbsBYDAutoAudioListener {
        public b() {
        }
    }

    public class c extends AbsBYDAutoTestListener {
        public c() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onMcuStateChanged(int i) {
            super.onMcuStateChanged(i);
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s, arrayList);
            BydLogHelper.logD("ban", "onReadConfigurationWordInfo did / " + ((int) s) + ", data.get(0) / " + arrayList.get(0));
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onWriteConfigurationWordInfo(short s, int i) {
            super.onWriteConfigurationWordInfo(s, i);
            BydLogHelper.logD("ban", "onWriteConfigurationWordInfo did / " + ((int) s) + ", res / " + i);
        }
    }

    public final class d extends ContentObserver {
        public d(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z, Uri uri, String str, int i) {
            super.onChange(z, uri, str, i);
            BydLogHelper.logD("TouchActivity", "ban onChange key / " + str + ", operationFlag / " + i);
            if (CarSettings.UserTableData.BYD_APP_THEME.equals(str) && i == 3) {
                BydLogHelper.logD("TouchActivity", "ban onChange BYD_APP_THEME uuid / " + CarSettings.UserTableData.getInt(TouchActivity.this.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME));
            }
        }
    }

    public void f(String str) {
        try {
            Log.d("TouchActivity", "进入修改名字 mManager = " + this.b + "， manager / " + this.d);
            Method method = this.b.getClass().getMethod("setDeviceName", WifiP2pManager.Channel.class, String.class, WifiP2pManager.ActionListener.class);
            Log.d("TouchActivity", "进入修改名字 method / " + method + ", mManager = " + this.b);
            method.invoke(this.b, this.c, "", new a(str));
        } catch (Exception e) {
            Log.d("TouchActivity", "错误：" + e);
        }
    }

    public final void initData() {
        new b();
        this.e.registerListener((AbsBYDAutoTestListener) new c());
        this.g = getContentResolver();
        this.h = new d(null);
    }

    public final void initView() {
        f("ban3");
        CarSettings.UserTableData.createUserData(getContentResolver(), 5113);
        CarSettings.UserTableData.putBoolean(getContentResolver(), CarSettings.UserTableData.LOW_FUEL_WARNING, true);
        CarSettings.UserTableData.putSystemInt(getContentResolver(), CarSettings.UserTableData.LOW_FUEL_WARNING, 1);
        CarSettings.UserTableData.putSystemBoolean(getContentResolver(), CarSettings.UserTableData.LOW_FUEL, true);
        CarSettings.Global.putInt(getContentResolver(), CarSettings.UserTableData.MODEL_VALUE, 1);
        CarSettings.UserTableData.putInt(getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME, 0);
    }

    public void lastStep(View view) {
        this.e.readConfigurationWordInfo((short) 8576);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_touch);
        initView();
        this.d = (WifiManager) getSystemService("wifi");
        WifiP2pManager wifiP2pManager = (WifiP2pManager) getSystemService("wifip2p");
        this.b = wifiP2pManager;
        this.c = wifiP2pManager.initialize(this, getMainLooper(), null);
        this.e = BYDAutoTestDevice.getInstance(getApplicationContext());
        initData();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        ContentResolver contentResolver = this.g;
        if (contentResolver != null) {
            contentResolver.unregisterContentObserver(this.h);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        this.g.registerContentObserver(CarSettings.UserTableData.CONTENT_URI, true, this.h);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public void save(View view) {
        CarSettings.UserTableData.putInt(getContentResolver(), "laaaaaaaaaaaaaa", 1);
        boolean z = CarSettings.UserTableData.getBoolean(getContentResolver(), CarSettings.UserTableData.LOW_FUEL_WARNING);
        int systemInt = CarSettings.UserTableData.getSystemInt(getContentResolver(), CarSettings.UserTableData.LOW_FUEL_WARNING);
        boolean systemBoolean = CarSettings.UserTableData.getSystemBoolean(getContentResolver(), CarSettings.UserTableData.LOW_FUEL);
        int i = CarSettings.Global.getInt(getContentResolver(), CarSettings.UserTableData.MODEL_VALUE);
        int i2 = CarSettings.UserTableData.getInt(getContentResolver(), "laaaaaaaaaaaaaa");
        CarSettings.UserTableData.putInt(getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME, 2);
        Log.d("ban", "b / " + z + ", systemInt / " + systemInt + ", systemBoolean / " + systemBoolean + ", globalInt / " + i + ", aaaa / " + i2 + " uuid / " + CarSettings.UserTableData.getInt(getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME));
        this.f.add((byte) 1);
        this.e.writeConfigurationWordInfo((short) 8576, this.f);
    }
}
