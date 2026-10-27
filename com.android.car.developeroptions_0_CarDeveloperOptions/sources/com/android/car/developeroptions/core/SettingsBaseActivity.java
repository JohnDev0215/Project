package com.android.car.developeroptions.core;

import android.R;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.dashboard.CategoryManager;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SettingsBaseActivity extends FragmentActivity {
    private static ArraySet<ComponentName> sTileBlacklist = new ArraySet<>();
    private final PackageReceiver mPackageReceiver = new PackageReceiver();
    private final List<CategoryListener> mCategoryListeners = new ArrayList();

    public interface CategoryListener {
        void onCategoriesChanged();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        System.currentTimeMillis();
        getLifecycle().addObserver(new HideNonSystemOverlayMixin(this));
        TypedArray typedArrayObtainStyledAttributes = getTheme().obtainStyledAttributes(R.styleable.Theme);
        if (!typedArrayObtainStyledAttributes.getBoolean(38, false)) {
            requestWindowFeature(1);
        }
        super.setContentView(com.android.car.developeroptions.R.layout.settings_base_layout);
        Toolbar toolbar = (Toolbar) findViewById(com.android.car.developeroptions.R.id.action_bar);
        if (typedArrayObtainStyledAttributes.getBoolean(38, false)) {
            toolbar.setVisibility(8);
        } else {
            setActionBar(toolbar);
        }
    }

    @Override // android.app.Activity
    public boolean onNavigateUp() {
        if (super.onNavigateUp()) {
            return true;
        }
        finish();
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme("package");
        registerReceiver(this.mPackageReceiver, intentFilter);
        new CategoriesUpdateTask().execute(new Void[0]);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        unregisterReceiver(this.mPackageReceiver);
        super.onPause();
    }

    public void addCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.add(categoryListener);
    }

    public void remCategoryListener(CategoryListener categoryListener) {
        this.mCategoryListeners.remove(categoryListener);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int i) {
        ViewGroup viewGroup = (ViewGroup) findViewById(com.android.car.developeroptions.R.id.content_frame);
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        LayoutInflater.from(this).inflate(i, viewGroup);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        ((ViewGroup) findViewById(com.android.car.developeroptions.R.id.content_frame)).addView(view);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        ((ViewGroup) findViewById(com.android.car.developeroptions.R.id.content_frame)).addView(view, layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onCategoriesChanged() {
        int size = this.mCategoryListeners.size();
        for (int i = 0; i < size; i++) {
            this.mCategoryListeners.get(i).onCategoriesChanged();
        }
    }

    public boolean setTileEnabled(ComponentName componentName, boolean z) {
        PackageManager packageManager = getPackageManager();
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if ((componentEnabledSetting == 1) == z && componentEnabledSetting != 0) {
            return false;
        }
        if (z) {
            sTileBlacklist.remove(componentName);
        } else {
            sTileBlacklist.add(componentName);
        }
        packageManager.setComponentEnabledSetting(componentName, z ? 1 : 2, 1);
        return true;
    }

    public void updateCategories() {
        new CategoriesUpdateTask().execute(new Void[0]);
    }

    private class CategoriesUpdateTask extends AsyncTask<Void, Void, Void> {
        private final CategoryManager mCategoryManager;

        public CategoriesUpdateTask() {
            this.mCategoryManager = CategoryManager.get(SettingsBaseActivity.this);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            this.mCategoryManager.reloadAllCategories(SettingsBaseActivity.this);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r2) {
            this.mCategoryManager.updateCategoryFromBlacklist(SettingsBaseActivity.sTileBlacklist);
            SettingsBaseActivity.this.onCategoriesChanged();
        }
    }

    private class PackageReceiver extends BroadcastReceiver {
        private PackageReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            SettingsBaseActivity.this.new CategoriesUpdateTask().execute(new Void[0]);
        }
    }
}
