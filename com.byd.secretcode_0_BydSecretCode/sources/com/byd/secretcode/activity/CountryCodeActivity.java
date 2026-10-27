package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.power.BYDAutoPowerDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.LauncherManager;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.window.ToastUtils;
import defpackage.bg;
import defpackage.mg;
import defpackage.wc;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CountryCodeActivity extends Activity implements View.OnClickListener {
    public AbsBYDAutoTestListener D;
    public BYDAutoPowerDevice K;
    public BYDAutoAudioDevice L;
    public LinearLayoutManager Q;
    public Context b;
    public String c;
    public String d;
    public List<HashMap> e;
    public RecyclerView f;
    public bg g;
    public Button h;
    public TextView i;
    public TextView j;
    public Button k;
    public Button l;
    public EditText m;
    public Button n;
    public int o = -1;
    public boolean p = false;
    public String q = "CountryCodeTool";
    public BYDAutoTestDevice r = null;
    public d s = null;
    public String t = "";
    public int u = 0;
    public String v = "47";
    public String w = "/storage/emulated/0/collect2/";
    public String x = "countrycode.txt";
    public final String y = BYDAutoSysPropertiesDevice.COUNTRY_CODE;
    public final String z = "persist.sys.byd.countrycode";
    public final String A = "persist.vendor.byd.cfg.huc.config.area";
    public String B = BYDAutoSysPropertiesDevice.RIGHT_RUDDER_VALUE;
    public LauncherManager C = null;
    public Dialog E = null;
    public ArrayList<Byte> F = new ArrayList<>();
    public final int G = 1001;
    public final int H = 1002;
    public final int I = 1003;
    public final int J = 1004;
    public String M = "byd_pad://byd_car_settings:2933/launcher?action=restore_factory_settings&pkg=com.byd.secretcode";
    public final int N = 1;
    public final int O = 0;
    public String P = null;
    public List<String> R = Arrays.asList("47", "31", "44", "45", "351", "32", "46", "41", "43", "39", "34", "358", "353", "30", "49", "33", "359", "357", "385", "420", "36", "372", "371", "370", "352", "356", "48", "40", "421", "386", "354", "423", "377", "379", "382", "355", "55", "56", "598", "57", "506", "51", "840", "593", "595", "591", "1242", "507", "52", "1", "502", "1876", "65", "977", "61", "66", "92", "60", "62", "63", "64", "84", "997");
    public List<String> S = Arrays.asList("86", "95", "856", "855", "63", "689", "687", "84", "692", "976", "82", "886", "850", "56", "598", "57", "1890", "506", "51", "840", "593", "595", "55", "591", "1242", "507", "599", "52", "502", "966", "971", "965", "968", "20", "233", "974", "212", "973", "238", "216", "961", "962", "262", "261", "244", "47", "45", "31", "351", "32", "46", "41", "43", "39", "34", "358", "30", "49", "33", "359", "385", "420", "372", "36", "371", "370", "352", "48", "40", "421", "386", "354", "423", "90", "380", "972", "382", "381", "387", "389", "355", "374", "994", "375", "373", "997", "996", "992", "998", "377", "7", "379", "503", "504", "505", "297", "970", "131", "132", "133", "134", "135", "136", "137", "225", "221", "250", "234", "229", "241", "54", "5997", "251", "237", "227", "228", "253", "252", "223", "220", "224", "226", "240", "243", "242", "257", "236", "222", "235", "211");
    public List<String> T = Arrays.asList("65", "852", "853", "977", "61", "66", "81", "94", "92", "91", "60", "62", "64", "975", "673", "1876", "27", "263", "260", "230", "267", "44", "353", "357", "356", "1868", "1268", "1246", "1345", "597", "1758", "592", "880", "960", "254");
    public final List<String> U = Arrays.asList("51", "52", "55", "57", "60", "63", "66", "91", "92", "94", "95", "212", "216", "502", "505", "506", "507", "591", "593", "595", "598", "599", "673", "687", "689", "853", "855", "856", "961", "962", "965", "966", "968", "971", "973", "974", "975", "976", "977", "998", "1242", "1876", "1890", "27", "230", "263", "260", "267", "997", "355", "373", "379", "377", "503", "54", "1868", "1268", "297", "1246", "1345", "597", "1758", "592", "5997", "504", "254", "248", "256", "255", "258", "264");

    public class a extends RecyclerView.t {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void a(RecyclerView recyclerView, int i) {
            super.a(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.t
        public void b(RecyclerView recyclerView, int i, int i2) {
            super.b(recyclerView, i, i2);
            for (int i3 = 0; i3 < recyclerView.getChildCount(); i3++) {
                recyclerView.getChildAt(i3).invalidate();
            }
        }
    }

    public class b extends AbsBYDAutoTestListener {
        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onChangedConfigArea(int i) {
            super.onChangedConfigArea(i);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onChangedConfigArea state = " + i);
            if (i == 0) {
                CountryCodeActivity.this.s.removeMessages(1003);
                CountryCodeActivity.this.s.sendEmptyMessageDelayed(1003, 1000L);
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s, arrayList);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onReadConfigurationWordInfo did = " + ((int) s) + ",data =" + arrayList.size());
            if (8448 == s) {
                CountryCodeActivity.this.F.clear();
                for (int i = 0; i < arrayList.size(); i++) {
                    CountryCodeActivity.this.F.add(arrayList.get(i));
                }
            }
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onWriteConfigurationWordInfo(short s, int i) {
            super.onWriteConfigurationWordInfo(s, i);
            BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "onWriteConfigurationWordInfo did = " + ((int) s) + ",res =" + i);
            if (s == -3679) {
                if (i == 0) {
                    CountryCodeActivity.this.s.removeMessages(1002);
                    CountryCodeActivity.this.s.sendEmptyMessageDelayed(1002, 1000L);
                    return;
                }
                return;
            }
            if (s == 8448) {
                if (i != 0) {
                    if (i == 1) {
                        CountryCodeActivity.this.s.removeMessages(1004);
                        CountryCodeActivity.this.s.sendEmptyMessageDelayed(1004, 1000L);
                        return;
                    }
                    return;
                }
                BydLogHelper.logD(AbsBYDAutoTestListener.TAG, "OFFLINE_DID Number = " + CountryCodeActivity.this.v);
                CountryCodeActivity.this.r.setConfigArea(CountryCodeActivity.this.v);
            }
        }
    }

    public class c implements TextView.OnEditorActionListener {
        public c() {
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            BydLogHelper.logE(CountryCodeActivity.this.q, "onEditorAction actionId =" + i);
            if (i != 6 && i != 5) {
                return false;
            }
            if (CountryCodeActivity.this.m != null) {
                CountryCodeActivity.this.m.clearFocus();
            }
            CountryCodeActivity.this.v(textView.getWindowToken());
            return false;
        }
    }

    public class d extends Handler {
        public d() {
        }

        @Override // android.os.Handler
        @SuppressLint({"SetTextI18n"})
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 1001) {
                CountryCodeActivity.this.i.setText(CountryCodeActivity.this.getResources().getString(R.string.current_countrycode) + CountryCodeActivity.this.c + "-" + CountryCodeActivity.this.d + "-" + CountryCodeActivity.this.t);
                return;
            }
            if (i == 1002) {
                mg.b(CountryCodeActivity.this.getApplicationContext(), CountryCodeActivity.this.getApplicationContext().getString(R.string.offline_write_country));
                CountryCodeActivity.this.F();
            } else if (i != 1003) {
                if (i == 1004) {
                    mg.b(CountryCodeActivity.this.getApplicationContext(), CountryCodeActivity.this.getApplicationContext().getString(R.string.offline_write_country_fail));
                }
            } else {
                SystemProperties.set(CountryCodeActivity.this.B, CountryCodeActivity.this.T.contains(CountryCodeActivity.this.t) ? "true" : "false");
                CountryCodeActivity countryCodeActivity = CountryCodeActivity.this;
                countryCodeActivity.r(countryCodeActivity.M);
                BydLogHelper.logD(CountryCodeActivity.this.q, "MESSAGE_MCU_RESET");
            }
        }
    }

    @SuppressLint({"WrongConstant"})
    public final void A() {
        LauncherManager launcherManager = (LauncherManager) this.b.getSystemService("launcher");
        this.C = launcherManager;
        launcherManager.setDefaultLauncher("com.android.launcher3");
    }

    public void B(int i) {
        this.u = i;
    }

    public byte C(String str, int i) throws Exception {
        int iIntValue = Integer.valueOf(str, i).intValue();
        if (iIntValue <= 255) {
            return (byte) (-((iIntValue ^ 255) + 1));
        }
        throw new Exception("字符串超出八位");
    }

    public final void D() {
        ArrayList<Byte> arrayList = this.F;
        arrayList.set(7, Byte.valueOf((byte) (((((((arrayList.get(0).byteValue() + this.F.get(1).byteValue()) + this.F.get(2).byteValue()) + this.F.get(3).byteValue()) + this.F.get(4).byteValue()) + this.F.get(5).byteValue()) + this.F.get(6).byteValue()) ^ 85)));
    }

    public final void E() {
        if (this.F.size() < 8) {
            BydLogHelper.logD(this.q, "handler whiteConfigData offlineDataSize =" + this.F.size());
            return;
        }
        byte bZ = z(this.F.get(4).byteValue(), 128, 7);
        byte bU = u((byte) 0, bZ, 7);
        BydLogHelper.logD(this.q, "setOFFLineConfig rdsGroupIndex : " + ((int) bZ));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_RDS", bZ);
        byte bZ2 = z(this.F.get(4).byteValue(), 64, 6);
        byte bU2 = u(bU, bZ2, 6);
        BydLogHelper.logD(this.q, "setOFFLineConfig dabGroupIndex : " + ((int) bZ2));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_DAB", bZ2);
        byte bZ3 = z(this.F.get(4).byteValue(), 32, 5);
        byte bU3 = u(bU2, bZ3, 5);
        BydLogHelper.logD(this.q, "setOFFLineConfig hdGroupIndex : " + ((int) bZ3));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_HD", bZ3);
        byte bZ4 = z(this.F.get(4).byteValue(), 16, 4);
        byte bU4 = u(bU3, bZ4, 4);
        BydLogHelper.logD(this.q, "setOFFLineConfig drmGroupIndex : " + ((int) bZ4));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_DRM", bZ4);
        boolean zContains = this.T.contains(this.t);
        byte bU5 = u(bU4, zContains ? 1 : 0, 3);
        BydLogHelper.logD(this.q, "setOFFLineConfig steeringWheelPositionGroupIndex : " + (zContains ? 1 : 0));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_STEERING_WHEEL_POSITION", zContains ? 1 : 0);
        byte bZ5 = z(this.F.get(4).byteValue(), 4, 2);
        byte bU6 = u(bU5, bZ5, 2);
        BydLogHelper.logD(this.q, "setOFFLineConfig amGroupIndex : " + ((int) bZ5));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_AM", bZ5);
        byte bZ6 = z(this.F.get(4).byteValue(), 2, 1);
        byte bU7 = u(bU6, bZ6, 1);
        BydLogHelper.logD(this.q, "setOFFLineConfig singleFmGroupIndex : " + ((int) bZ6));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_SINGLE_FM", bZ6);
        byte bZ7 = z(this.F.get(4).byteValue(), 1, 0);
        byte bU8 = u(bU7, bZ7, 0);
        BydLogHelper.logD(this.q, "setOFFLineConfig twoFmGroupIndex : " + ((int) bZ7));
        Settings.Global.putInt(getContentResolver(), "GLOBAL_TWO_FM", bZ7);
        this.F.set(4, Byte.valueOf(bU8));
    }

    public final void F() {
        if (this.F.size() < 8) {
            BydLogHelper.logE(this.q, "writeConfigData offlineDataSize =" + this.F.size());
            return;
        }
        try {
            BydLogHelper.logE(this.q, "writeConfigData mNumber =" + this.v);
            byte bC = C(this.v, 10);
            BydLogHelper.logE(this.q, "writeConfigData area =" + ((int) bC));
            this.F.set(3, Byte.valueOf(bC));
            D();
            this.F.size();
            this.r.writeConfigurationWordInfo((short) 8448, this.F);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (x(currentFocus, motionEvent)) {
                v(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_search /* 2131296398 */:
                String strTrim = this.m.getText().toString().trim();
                this.P = strTrim;
                if (TextUtils.isEmpty(strTrim)) {
                    ToastUtils.show(this, "code is empty!");
                } else {
                    int size = this.e.size();
                    for (int i = 0; i < size; i++) {
                        if (this.e.get(i).get("countrycode").toString().equals(this.P) || this.e.get(i).get("countryandregion").toString().equals(this.P)) {
                            BydLogHelper.logD(this.q, "i = " + i + ", countrycode = " + this.e.get(i).get("countrycode"));
                            BydLogHelper.logD(this.q, "i = " + i + ", countryandregion = " + this.e.get(i).get("countryandregion"));
                            this.Q.C2(i, 200);
                        }
                    }
                }
                break;
            case R.id.country_cancel /* 2131296449 */:
                this.E.dismiss();
                break;
            case R.id.country_confirm /* 2131296451 */:
                SystemProperties.set(BYDAutoSysPropertiesDevice.COUNTRY_CODE, this.t);
                SystemProperties.set("persist.sys.byd.countrycode", this.t);
                boolean zContains = this.U.contains(this.t);
                int vSPState = this.L.setVSPState(!zContains ? 1 : 0);
                BydLogHelper.logD(this.q, "vspStateCode " + vSPState);
                BydLogHelper.logD(this.q, "insideAVASCode " + zContains);
                this.s.sendEmptyMessage(1001);
                if (this.R.indexOf(this.t) == -1) {
                    SystemProperties.set("persist.sys.byd.navi_launcher", "false");
                    A();
                } else {
                    SystemProperties.set("persist.sys.byd.navi_launcher", "true");
                }
                q();
                this.E.dismiss();
                break;
            case R.id.ok_button /* 2131296675 */:
                this.t = this.e.get(this.u).get("countrycode").toString();
                this.c = this.e.get(this.u).get("countryandregion").toString();
                this.d = this.e.get(this.u).get("internationaldomain").toString();
                this.v = this.e.get(this.u).get("number").toString();
                this.E = new Dialog(this);
                View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_countrycode, (ViewGroup) null);
                this.j = (TextView) viewInflate.findViewById(R.id.country_code_tx);
                this.k = (Button) viewInflate.findViewById(R.id.country_cancel);
                this.l = (Button) viewInflate.findViewById(R.id.country_confirm);
                this.j.setText(getString(R.string.current_countrycode) + this.t + "-" + this.c + "-" + this.d + getString(R.string.dialog_country_code));
                this.E.addContentView(viewInflate, new WindowManager.LayoutParams());
                this.k = (Button) this.E.findViewById(R.id.country_cancel);
                this.l = (Button) this.E.findViewById(R.id.country_confirm);
                this.k.setOnClickListener(this);
                this.l.setOnClickListener(this);
                this.E.show();
                break;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_countrycode);
        Context applicationContext = getApplicationContext();
        this.b = applicationContext;
        this.r = BYDAutoTestDevice.getInstance(applicationContext);
        this.K = BYDAutoPowerDevice.getInstance(this);
        this.L = BYDAutoAudioDevice.getInstance(this);
        w();
        y();
        this.s = new d();
        this.g = new bg(this, this.e);
        this.f.setLayoutManager(new LinearLayoutManager(this));
        this.f.h(new wc(this.b, 1));
        this.f.setAdapter(this.g);
        this.f.l(new a());
        this.h.setOnClickListener(this);
        t();
        this.r = BYDAutoTestDevice.getInstance(this);
        this.D = new b();
        this.r.readConfigurationWordInfo((short) 8448);
        this.m = (EditText) findViewById(R.id.edit_find_code);
        Button button = (Button) findViewById(R.id.button_search);
        this.n = button;
        button.setOnClickListener(this);
        this.Q = (LinearLayoutManager) this.f.getLayoutManager();
        this.m.setOnEditorActionListener(new c());
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.r.unregisterListener(this.D);
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        BYDAutoTestDevice bYDAutoTestDevice = this.r;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.registerListener(this.D);
            this.r.readConfigurationWordInfo((short) 8448);
        }
        BydLogHelper.logD(this.q, "onResume");
    }

    @Override // android.app.Activity
    public void onStart() {
        super.onStart();
        BydLogHelper.logD(this.q, "onStart");
    }

    @Override // android.app.Activity
    public void onStop() {
        super.onStop();
        BydLogHelper.logD(this.q, "onStop");
        this.r.unregisterListener(this.D);
        Intent intent = new Intent();
        intent.putExtra("name", true);
        setResult(-1, intent);
        this.P = null;
    }

    public final void q() {
        ArrayList<Byte> arrayList = new ArrayList<>();
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.add((byte) -1);
        arrayList.size();
        BydLogHelper.logD(this.q, "clearConfig data");
        this.r.writeConfigurationWordInfo((short) -3679, arrayList);
        E();
    }

    public final void r(String str) {
        PackageManager packageManager = this.b.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
        intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 0);
        BydLogHelper.logD(this.q, "----factory----resolveInfos = " + listQueryIntentServices);
        if (listQueryIntentServices.isEmpty()) {
            return;
        }
        this.b.startService(intent);
    }

    public final int s(int i) {
        for (int i2 = 0; i2 < this.e.size(); i2++) {
            if (this.e.get(i2).get("number").equals(String.valueOf(i))) {
                return i2;
            }
        }
        return -1;
    }

    public final void t() {
        int iS = s(SystemProperties.getInt("persist.vendor.byd.cfg.huc.config.area", 49));
        BydLogHelper.logD(this.q, "getCurCountryCodeInfo position =" + iS);
        if (iS != -1) {
            this.c = this.e.get(iS).get("countryandregion").toString();
            this.d = this.e.get(iS).get("internationaldomain").toString();
            this.o = Integer.parseInt(String.valueOf(this.e.get(iS).get("countrycode")));
            this.i.setText(getResources().getString(R.string.current_countrycode) + this.c + "-" + this.d + "-" + this.o);
        }
    }

    public final byte u(byte b2, int i, int i2) {
        return (byte) (b2 | ((i | 0) << i2));
    }

    public final void v(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public void w() {
        this.i = (TextView) findViewById(R.id.curr_ccode);
        this.f = (RecyclerView) findViewById(R.id.list_ccode);
        this.h = (Button) findViewById(R.id.ok_button);
        this.e = new ArrayList();
    }

    public final boolean x(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public final void y() {
        String str;
        String language = Build.VERSION.SDK_INT >= 24 ? getResources().getConfiguration().getLocales().get(0).getLanguage() : getResources().getConfiguration().locale.getLanguage();
        try {
            InputStream inputStreamOpen = language.equals("zh") ? getResources().getAssets().open("countrycode.json") : language.equals("en") ? getResources().getAssets().open("countrycode_us.json") : getResources().getAssets().open("countrycode_us.json");
            byte[] bArr = new byte[inputStreamOpen.available()];
            inputStreamOpen.read(bArr);
            str = new String(bArr);
            try {
                inputStreamOpen.close();
            } catch (IOException e) {
                e = e;
                language = str;
                e.printStackTrace();
                str = language;
            }
        } catch (IOException e2) {
            e = e2;
        }
        if (str == null) {
            Log.d(this.q, "content is null ");
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String string = jSONObject.getString("countryandregionname");
                String string2 = jSONObject.getString("internationaldomain");
                String string3 = jSONObject.getString("countrycode");
                String string4 = jSONObject.getString("number");
                HashMap map = new HashMap();
                map.put("countryandregion", string);
                map.put("internationaldomain", string2);
                map.put("countrycode", string3);
                map.put("number", string4);
                this.e.add(map);
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
        }
    }

    public final byte z(byte b2, int i, int i2) {
        return (byte) ((b2 & i) >> i2);
    }
}
