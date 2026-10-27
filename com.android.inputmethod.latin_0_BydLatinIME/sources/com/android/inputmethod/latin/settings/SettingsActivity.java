package com.android.inputmethod.latin.settings;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.app.ActivityCompat;
import com.android.inputmethod.latin.permissions.PermissionsManager;
import com.byd.ime.TestLogger;
import com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter;

/* JADX INFO: loaded from: classes2.dex */
public final class SettingsActivity extends Activity implements ActivityCompat.OnRequestPermissionsResultCallback, SubtypeSettingsActivityPresenter.CallBack {
    private static final String DEFAULT_FRAGMENT = SettingsFragment.class.getName();
    public static final String EXTRA_ENTRY_KEY = "entry";
    public static final String EXTRA_ENTRY_VALUE_APP_ICON = "app_icon";
    public static final String EXTRA_ENTRY_VALUE_LONG_PRESS_COMMA = "long_press_comma";
    public static final String EXTRA_ENTRY_VALUE_NOTICE_DIALOG = "important_notice";
    public static final String EXTRA_ENTRY_VALUE_SYSTEM_SETTINGS = "system_settings";
    public static final String EXTRA_SHOW_HOME_AS_UP = "show_home_as_up";
    private boolean mShowHomeAsUp;
    private SubtypeSettingsActivityPresenter mSubtypeSettingsPresenter;

    @Override // android.app.Activity
    protected void onCreate(final Bundle savedState) {
        super.onCreate(savedState);
        TestLogger.logE("OnCreate");
        getWindow().setFlags(512, 512);
        requestWindowFeature(1);
        SubtypeSettingsActivityPresenter subtypeSettingsActivityPresenter = new SubtypeSettingsActivityPresenter();
        this.mSubtypeSettingsPresenter = subtypeSettingsActivityPresenter;
        subtypeSettingsActivityPresenter.init(this);
        this.mSubtypeSettingsPresenter.setCallback(this);
        setContentView(this.mSubtypeSettingsPresenter.findAndInitContentView());
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(final MenuItem item) {
        TestLogger.logE("OnOptionsItemSelected");
        if (this.mShowHomeAsUp && item.getItemId() == 16908332) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // android.app.Activity, androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionsManager.get(this).onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        TestLogger.logE("OnBackPressed");
        this.mSubtypeSettingsPresenter.onBackPressed();
    }

    @Override // com.byd.ime.subtype.show.SubtypeSettingsActivityPresenter.CallBack
    public void doClose() {
        TestLogger.logE("doClose");
        finish();
    }

    @Override // android.app.Activity
    public void finish() {
        TestLogger.logE("finish");
        super.finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        TestLogger.logE("destroy");
        super.onDestroy();
    }
}
