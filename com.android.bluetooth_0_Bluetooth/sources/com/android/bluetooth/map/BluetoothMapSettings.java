package com.android.bluetooth.map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import com.android.bluetooth.R;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapSettings extends Activity {
    private static final boolean D = false;
    private static final String TAG = "BluetoothMapSettings";
    private static final boolean V = false;
    LinkedHashMap<BluetoothMapAccountItem, ArrayList<BluetoothMapAccountItem>> mGroups;
    BluetoothMapAccountLoader mLoader = new BluetoothMapAccountLoader(this);

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth_map_settings);
        this.mGroups = this.mLoader.parsePackages(true);
        ExpandableListView listView = (ExpandableListView) findViewById(R.id.bluetooth_map_settings_list_view);
        BluetoothMapSettingsAdapter adapter = new BluetoothMapSettingsAdapter(this, listView, this.mGroups, this.mLoader.getAccountsEnabledCount());
        listView.setAdapter(adapter);
    }
}
