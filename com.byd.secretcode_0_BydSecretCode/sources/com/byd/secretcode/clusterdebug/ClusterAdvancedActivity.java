package com.byd.secretcode.clusterdebug;

import android.hardware.bydauto.container.BYDAutoContainerManager;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ClusterAdvancedActivity extends AppCompatActivity implements View.OnClickListener {
    public final int b = 1000;
    public Map<String, Integer> c = new HashMap();
    public BYDAutoContainerManager d;

    public final void f() {
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Button button = (Button) view;
        if (button != null) {
            if (this.d == null) {
                BydLogHelper.logD("clusterDebug", "mAutoContainerManager is null ,return");
                return;
            }
            String str = (String) button.getText();
            BydLogHelper.logD("clusterDebug", str);
            try {
                if (this.c.containsKey(str)) {
                    int iIntValue = this.c.get(str).intValue();
                    BydLogHelper.logE("clusterDebug", "index" + iIntValue);
                    this.d.sendInfo(iIntValue);
                } else {
                    BydLogHelper.logD("clusterDebug", "mInfoMap don't have " + str);
                }
            } catch (SecurityException e) {
                BydLogHelper.logD("clusterDebug", e.getMessage());
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.activity_second);
        SystemProperties.get("ro.product.name");
        BydLogHelper.logD("clusterDebug", "mProductName mm");
        this.d = BYDAutoContainerManager.getInstance(getApplicationContext());
        f();
    }
}
