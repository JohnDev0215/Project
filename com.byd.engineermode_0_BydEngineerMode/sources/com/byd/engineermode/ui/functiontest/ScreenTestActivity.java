package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Bundle;
import android.provider.Settings;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/* JADX INFO: loaded from: classes.dex */
public class ScreenTestActivity extends BaseActivity {
    private TextView v;
    private SeekBar w;
    private TextView x;

    class a implements SeekBar.OnSeekBarChangeListener {
        a() {
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        @SuppressLint({"SetTextI18n"})
        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            TextView textView = ScreenTestActivity.this.v;
            StringBuilder sb = new StringBuilder();
            sb.append(ScreenTestActivity.this.getResources().getString(R.string.brightness_value_msg));
            ScreenTestActivity screenTestActivity = ScreenTestActivity.this;
            sb.append((screenTestActivity.O(screenTestActivity) * 100) / ScreenTestActivity.Q(ScreenTestActivity.this));
            sb.append(" %");
            sb.append(ScreenTestActivity.this.getResources().getString(R.string.get_panel_id_msg));
            textView.setText(sb.toString());
            ScreenTestActivity.M(i, ScreenTestActivity.this);
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            Log.i("ScreenTestActivity", "开始拖动");
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.i("ScreenTestActivity", "拖动停止");
        }
    }

    public static void M(int i, Activity activity) {
        Settings.System.putInt(activity.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, i);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0104 A[Catch: IOException | InterruptedException -> 0x045f, IOException -> 0x0461, TryCatch #2 {IOException | InterruptedException -> 0x045f, blocks: (B:3:0x002d, B:7:0x0091, B:9:0x009a, B:11:0x00a0, B:14:0x00b1, B:17:0x00eb, B:19:0x00f2, B:21:0x00f8, B:23:0x0107, B:26:0x01b9, B:28:0x01c0, B:30:0x01c6, B:32:0x01d5, B:35:0x02ad, B:37:0x02b4, B:39:0x02ba, B:41:0x02c9, B:44:0x0303, B:46:0x030a, B:48:0x0310, B:50:0x031f, B:53:0x03cf, B:55:0x03d6, B:57:0x03dc, B:59:0x03eb, B:58:0x03e8, B:49:0x031c, B:40:0x02c6, B:31:0x01d2, B:22:0x0104, B:13:0x00ae), top: B:66:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x01d2 A[Catch: IOException | InterruptedException -> 0x045f, IOException -> 0x0461, TryCatch #2 {IOException | InterruptedException -> 0x045f, blocks: (B:3:0x002d, B:7:0x0091, B:9:0x009a, B:11:0x00a0, B:14:0x00b1, B:17:0x00eb, B:19:0x00f2, B:21:0x00f8, B:23:0x0107, B:26:0x01b9, B:28:0x01c0, B:30:0x01c6, B:32:0x01d5, B:35:0x02ad, B:37:0x02b4, B:39:0x02ba, B:41:0x02c9, B:44:0x0303, B:46:0x030a, B:48:0x0310, B:50:0x031f, B:53:0x03cf, B:55:0x03d6, B:57:0x03dc, B:59:0x03eb, B:58:0x03e8, B:49:0x031c, B:40:0x02c6, B:31:0x01d2, B:22:0x0104, B:13:0x00ae), top: B:66:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x02c6 A[Catch: IOException | InterruptedException -> 0x045f, IOException -> 0x0461, TryCatch #2 {IOException | InterruptedException -> 0x045f, blocks: (B:3:0x002d, B:7:0x0091, B:9:0x009a, B:11:0x00a0, B:14:0x00b1, B:17:0x00eb, B:19:0x00f2, B:21:0x00f8, B:23:0x0107, B:26:0x01b9, B:28:0x01c0, B:30:0x01c6, B:32:0x01d5, B:35:0x02ad, B:37:0x02b4, B:39:0x02ba, B:41:0x02c9, B:44:0x0303, B:46:0x030a, B:48:0x0310, B:50:0x031f, B:53:0x03cf, B:55:0x03d6, B:57:0x03dc, B:59:0x03eb, B:58:0x03e8, B:49:0x031c, B:40:0x02c6, B:31:0x01d2, B:22:0x0104, B:13:0x00ae), top: B:66:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x031c A[Catch: IOException | InterruptedException -> 0x045f, IOException -> 0x0461, TryCatch #2 {IOException | InterruptedException -> 0x045f, blocks: (B:3:0x002d, B:7:0x0091, B:9:0x009a, B:11:0x00a0, B:14:0x00b1, B:17:0x00eb, B:19:0x00f2, B:21:0x00f8, B:23:0x0107, B:26:0x01b9, B:28:0x01c0, B:30:0x01c6, B:32:0x01d5, B:35:0x02ad, B:37:0x02b4, B:39:0x02ba, B:41:0x02c9, B:44:0x0303, B:46:0x030a, B:48:0x0310, B:50:0x031f, B:53:0x03cf, B:55:0x03d6, B:57:0x03dc, B:59:0x03eb, B:58:0x03e8, B:49:0x031c, B:40:0x02c6, B:31:0x01d2, B:22:0x0104, B:13:0x00ae), top: B:66:0x002d }] */
    /* JADX WARN: Removed duplicated region for block: B:58:0x03e8 A[Catch: IOException | InterruptedException -> 0x045f, IOException -> 0x0461, TryCatch #2 {IOException | InterruptedException -> 0x045f, blocks: (B:3:0x002d, B:7:0x0091, B:9:0x009a, B:11:0x00a0, B:14:0x00b1, B:17:0x00eb, B:19:0x00f2, B:21:0x00f8, B:23:0x0107, B:26:0x01b9, B:28:0x01c0, B:30:0x01c6, B:32:0x01d5, B:35:0x02ad, B:37:0x02b4, B:39:0x02ba, B:41:0x02c9, B:44:0x0303, B:46:0x030a, B:48:0x0310, B:50:0x031f, B:53:0x03cf, B:55:0x03d6, B:57:0x03dc, B:59:0x03eb, B:58:0x03e8, B:49:0x031c, B:40:0x02c6, B:31:0x01d2, B:22:0x0104, B:13:0x00ae), top: B:66:0x002d }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String P() {
        /*
            Method dump skipped, instruction units count: 1129
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.engineermode.ui.functiontest.ScreenTestActivity.P():java.lang.String");
    }

    public static int Q(Context context) {
        return context.getResources().getInteger(context.getResources().getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android"));
    }

    @SuppressLint({"SetTextI18n"})
    private String R(String[] strArr) throws InterruptedException, IOException {
        if (strArr == null) {
            com.byd.engineermode.utils.a.i("cmd is null");
            return "";
        }
        Process processExec = Runtime.getRuntime().exec("sh");
        DataOutputStream dataOutputStream = new DataOutputStream(processExec.getOutputStream());
        for (String str : strArr) {
            if (str != null) {
                dataOutputStream.write(str.getBytes());
                dataOutputStream.writeBytes("\n");
                dataOutputStream.flush();
            }
            dataOutputStream.writeBytes("exit\n");
            dataOutputStream.flush();
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        processExec.waitFor();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(processExec.getErrorStream()));
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
        }
        while (true) {
            String line2 = bufferedReader2.readLine();
            if (line2 == null) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(sb.toString());
                sb3.append(sb2.toString());
                com.byd.engineermode.utils.a.c(sb3.toString());
                return sb3.toString();
            }
            sb2.append(line2);
        }
    }

    @SuppressLint({"SetTextI18n"})
    private void S() {
        this.v = (TextView) findViewById(R.id.textView);
        this.w = (SeekBar) findViewById(R.id.seekBar);
        TextView textView = (TextView) findViewById(R.id.tv_eeprom_version);
        this.x = textView;
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        this.v.setText(getResources().getString(R.string.brightness_value_msg) + ((O(this) * 100) / Q(this)) + " %" + getResources().getString(R.string.get_panel_id_msg));
        this.w.setMax(Q(this));
        this.w.setProgress(O(this));
    }

    public int O(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void OnButtonClick(View view) {
        Log.d("ScreenTestActivity", "view.getId()  = " + view.getId());
        int id = view.getId();
        if (id == R.id.test_fail_btn) {
            I();
            return;
        }
        if (id == R.id.test_pass_btn) {
            J();
            return;
        }
        if (id != R.id.textView) {
            return;
        }
        this.x.setText(getResources().getString(R.string.str_get_panel_id_result) + P());
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_screen_test);
        S();
        this.w.setOnSeekBarChangeListener(new a());
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
