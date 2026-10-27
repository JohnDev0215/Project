package com.byd.secretcode.clusterdebug;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.bydauto.container.BYDAutoContainerManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.mg;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ClusterDebugActivity extends AppCompatActivity implements View.OnClickListener {
    public final int b = 1000;
    public Map<String, Integer> c = new HashMap();
    public Button d;
    public BYDAutoContainerManager e;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ClusterDebugActivity.this.g();
        }
    }

    public class b implements DialogInterface.OnClickListener {
        public final /* synthetic */ View b;
        public final /* synthetic */ String c;

        public b(View view, String str) {
            this.b = view;
            this.c = str;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (!((EditText) this.b.findViewById(R.id.edit_text)).getText().toString().equals(this.c)) {
                mg.b(ClusterDebugActivity.this, "密码错误");
                return;
            }
            mg.b(ClusterDebugActivity.this, "密码正确");
            ClusterDebugActivity.this.startActivity(new Intent(ClusterDebugActivity.this, (Class<?>) ClusterAdvancedActivity.class));
            mg.b(ClusterDebugActivity.this, "启动高级测试");
        }
    }

    public final void f() {
        BydLogHelper.logE("ban", "infoMapInit / " + getString(R.string.cluster_0x0));
        this.c.put(getString(R.string.cluster_0x0), 1);
        this.c.put(getString(R.string.cluster_0x1), 0);
        this.c.put(getString(R.string.cluster_0x2), 2);
        this.c.put(getString(R.string.cluster_0x3), 3);
        this.c.put(getString(R.string.cluster_0x4), 4);
        this.c.put(getString(R.string.cluster_0x5), 5);
        this.c.put(getString(R.string.cluster_0x6), 6);
        this.c.put(getString(R.string.cluster_0x7), 7);
        this.c.put(getString(R.string.cluster_0x8), 8);
        this.c.put(getString(R.string.cluster_0x9), 9);
        this.c.put(getString(R.string.cluster_0x10), 10);
        this.c.put(getString(R.string.cluster_0x11), 11);
        this.c.put(getString(R.string.cluster_0x12), 12);
        this.c.put(getString(R.string.cluster_0x13), 13);
        this.c.put(getString(R.string.cluster_0x14), 14);
        this.c.put(getString(R.string.cluster_0x15), 15);
        this.c.put(getString(R.string.cluster_0x16), 16);
        this.c.put(getString(R.string.cluster_0x17), 17);
        this.c.put(getString(R.string.cluster_0x18), 18);
        this.c.put(getString(R.string.cluster_0x19), 19);
        this.c.put(getString(R.string.cluster_0x20), 20);
        this.c.put(getString(R.string.cluster_0x21), 21);
        this.c.put(getString(R.string.cluster_0x22), 22);
        this.c.put(getString(R.string.cluster_0x23), 23);
        this.c.put(getString(R.string.cluster_0x24), 24);
        this.c.put(getString(R.string.cluster_0x25), 25);
        this.c.put(getString(R.string.cluster_0x26), 26);
        this.c.put(getString(R.string.cluster_0x27), 27);
        this.c.put(getString(R.string.cluster_0x28), 28);
        this.c.put(getString(R.string.cluster_0x29), 29);
        this.c.put(getString(R.string.cluster_0x30), 30);
        this.c.put(getString(R.string.cluster_0x31), 31);
        this.c.put(getString(R.string.cluster_0x32), 32);
        this.c.put(getString(R.string.cluster_0x33), 33);
        this.c.put(getString(R.string.cluster_0x34), 34);
        this.c.put(getString(R.string.cluster_0x35), 35);
        this.c.put(getString(R.string.cluster_0x36), 36);
        this.c.put(getString(R.string.cluster_0x37), 37);
        this.c.put(getString(R.string.cluster_0x38), 38);
        this.c.put(getString(R.string.cluster_0x39), 39);
        this.c.put(getString(R.string.cluster_0x40), 40);
        this.c.put(getString(R.string.cluster_0x41), 41);
        this.c.put(getString(R.string.cluster_0x42), 42);
        this.c.put(getString(R.string.cluster_0x43), 43);
        this.c.put(getString(R.string.cluster_0x44), 44);
        this.c.put(getString(R.string.cluster_0x45), 45);
        this.c.put(getString(R.string.cluster_0x46), 46);
        this.c.put(getString(R.string.cluster_0x47), 47);
        this.c.put(getString(R.string.cluster_0x48), 48);
        this.c.put(getString(R.string.cluster_0x49), 49);
        this.c.put(getString(R.string.cluster_0x50), 50);
        this.c.put(getString(R.string.cluster_0x51), 51);
        this.c.put(getString(R.string.cluster_0x52), 52);
        this.c.put(getString(R.string.cluster_0x53), 53);
        this.c.put("nb_test", 54);
    }

    public void g() {
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        int i4 = i + i2 + i3;
        BydLogHelper.logE("year / " + i + ", month / " + i2 + ", day / " + i3 + ", k / " + i4);
        StringBuilder sb = new StringBuilder();
        sb.append("byd");
        sb.append(Integer.toString(i4));
        String string = sb.toString();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View viewInflate = LayoutInflater.from(this).inflate(R.layout.dialog_customiza, (ViewGroup) null);
        builder.setTitle("请输入密码\n换个行：");
        builder.setView(viewInflate);
        builder.setPositiveButton("确定", new b(viewInflate, string));
        builder.show();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Button button = (Button) view;
        if (button != null) {
            if (this.e == null) {
                BydLogHelper.logD("ClusterDebugActivity", "mAutoContainerManager is null ,return");
                return;
            }
            String str = (String) button.getText();
            BydLogHelper.logD("ClusterDebugActivity", str);
            try {
                if (this.c.containsKey(str)) {
                    int iIntValue = this.c.get(str).intValue();
                    BydLogHelper.logE("ClusterDebugActivity", "index" + iIntValue);
                    this.e.sendInfo(iIntValue);
                } else {
                    BydLogHelper.logD("ClusterDebugActivity", "mInfoMap don't have " + str);
                }
            } catch (SecurityException e) {
                BydLogHelper.logD("ClusterDebugActivity", e.getMessage());
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_cluster_debug);
        String str = SystemProperties.get("ro.product.name");
        BydLogHelper.logD("ClusterDebugActivity", "mProductName mm");
        if (str.equals("DiLink5.0")) {
            BydLogHelper.logD("ClusterDebugActivity", "mProductName auto_container");
        } else {
            BydLogHelper.logD("ClusterDebugActivity", "mProductName AutoContainer");
        }
        this.e = BYDAutoContainerManager.getInstance(getApplicationContext());
        f();
        Button button = (Button) findViewById(R.id.nb);
        this.d = button;
        button.setOnClickListener(new a());
    }
}
