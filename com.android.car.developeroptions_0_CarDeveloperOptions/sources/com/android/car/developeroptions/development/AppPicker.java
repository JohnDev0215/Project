package com.android.car.developeroptions.development;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.Collator;
import java.util.Comparator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AppPicker extends ListActivity {
    private static final Comparator<MyApplicationInfo> sDisplayNameComparator = new Comparator<MyApplicationInfo>() { // from class: com.android.car.developeroptions.development.AppPicker.1
        private final Collator collator = Collator.getInstance();

        @Override // java.util.Comparator
        public final int compare(MyApplicationInfo myApplicationInfo, MyApplicationInfo myApplicationInfo2) {
            return this.collator.compare(myApplicationInfo.label, myApplicationInfo2.label);
        }
    };
    private AppListAdapter mAdapter;
    private boolean mDebuggableOnly;
    private boolean mNonSystemOnly;
    private String mPermissionName;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mPermissionName = getIntent().getStringExtra("com.android.car.developeroptions.extra.REQUESTIING_PERMISSION");
        this.mDebuggableOnly = getIntent().getBooleanExtra("com.android.car.developeroptions.extra.DEBUGGABLE", false);
        this.mNonSystemOnly = getIntent().getBooleanExtra("com.android.car.developeroptions.extra.NON_SYSTEM", false);
        AppListAdapter appListAdapter = new AppListAdapter(this);
        this.mAdapter = appListAdapter;
        if (appListAdapter.getCount() <= 0) {
            finish();
        } else {
            setListAdapter(this.mAdapter);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
    }

    @Override // android.app.ListActivity
    protected void onListItemClick(ListView listView, View view, int i, long j) {
        MyApplicationInfo item = this.mAdapter.getItem(i);
        Intent intent = new Intent();
        ApplicationInfo applicationInfo = item.info;
        if (applicationInfo != null) {
            intent.setAction(applicationInfo.packageName);
        }
        setResult(-1, intent);
        finish();
    }

    class MyApplicationInfo {
        ApplicationInfo info;
        CharSequence label;

        MyApplicationInfo(AppPicker appPicker) {
        }
    }

    public class AppListAdapter extends ArrayAdapter<MyApplicationInfo> {
        private final LayoutInflater mInflater;
        private final List<MyApplicationInfo> mPackageInfoList;

        /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public AppListAdapter(android.content.Context r11) {
            /*
                Method dump skipped, instruction units count: 202
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.development.AppPicker.AppListAdapter.<init>(com.android.car.developeroptions.development.AppPicker, android.content.Context):void");
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            AppViewHolder appViewHolderCreateOrRecycle = AppViewHolder.createOrRecycle(this.mInflater, view);
            View view2 = appViewHolderCreateOrRecycle.rootView;
            MyApplicationInfo item = getItem(i);
            appViewHolderCreateOrRecycle.appName.setText(item.label);
            ApplicationInfo applicationInfo = item.info;
            if (applicationInfo != null) {
                appViewHolderCreateOrRecycle.appIcon.setImageDrawable(applicationInfo.loadIcon(AppPicker.this.getPackageManager()));
                appViewHolderCreateOrRecycle.summary.setText(item.info.packageName);
            } else {
                appViewHolderCreateOrRecycle.appIcon.setImageDrawable(null);
                appViewHolderCreateOrRecycle.summary.setText("");
            }
            appViewHolderCreateOrRecycle.disabled.setVisibility(8);
            return view2;
        }
    }
}
