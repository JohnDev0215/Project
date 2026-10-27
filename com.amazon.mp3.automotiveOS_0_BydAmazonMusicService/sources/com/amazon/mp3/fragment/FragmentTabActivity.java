package com.amazon.mp3.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.activity.BaseActivity;

/* JADX INFO: loaded from: classes3.dex */
public abstract class FragmentTabActivity extends BaseActivity {
    private FragmentTabHost mTabHost = null;
    private boolean mHidden = false;

    public abstract Fragment lazyCreateFragmentForTab(int tabId);

    protected void onSelectedTabChanged(int index) {
    }

    public FragmentTabHost getTabHost() {
        FragmentTabHost fragmentTabHost = this.mTabHost;
        if (fragmentTabHost == null) {
            FragmentTabHost fragmentTabHost2 = new FragmentTabHost(this);
            this.mTabHost = fragmentTabHost2;
            fragmentTabHost2.hideOrShowElements(this.mHidden);
            setContentView(this.mTabHost);
        } else {
            fragmentTabHost.hideOrShowElements(this.mHidden);
        }
        return this.mTabHost;
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(1);
        super.onCreate(savedInstanceState);
        FragmentTabHost fragmentTabHost = new FragmentTabHost(this);
        this.mTabHost = fragmentTabHost;
        setContentView(fragmentTabHost);
        this.mTabHost.restoreInstanceState(savedInstanceState);
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            this.mTabHost.saveInstanceState(outState);
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        refreshTabs();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            refreshTabs();
        }
    }

    private void refreshTabs() {
        this.mTabHost.unlockTabs();
        if (this.mTabHost.getSelectedTab() != -1) {
            this.mTabHost.getTabWidget().defocusAllTabs();
            FragmentTabHost fragmentTabHost = this.mTabHost;
            fragmentTabHost.setSelectedTab(fragmentTabHost.getSelectedTab());
        }
    }

    @Override // com.amazon.mp3.activity.BaseActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        this.mTabHost.lockTabs();
    }

    @Override // com.amazon.mp3.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4 && getTabHost() != null && ((BaseFragment) getTabHost().getSelectedTabFragment()).onBackPressed()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
