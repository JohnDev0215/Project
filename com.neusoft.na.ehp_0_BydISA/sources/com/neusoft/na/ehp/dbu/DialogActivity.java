package com.neusoft.na.ehp.dbu;

import android.app.UiModeManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import com.neusoft.na.ehp.EhpService;
import com.neusoft.na.ehp.R;
import com.neusoft.na.ehp.manager.NativeManager;

/* JADX INFO: loaded from: classes.dex */
public class DialogActivity extends AppCompatActivity {
    private static final int ERROR = 2;
    private static final int LAUNCH = 1;
    private static final int PROGRESS = 0;
    private static final String TAG = "NeuEhpDialog";
    FrameLayout fl_root;
    private NativeManager manager;
    private int mType = 1;
    private int mValue = 0;
    private int mUpdateType = -1;
    private ServiceConnection connection = new AnonymousClass1();

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_dialog);
        Log.i(TAG, "onCreate");
        this.fl_root = (FrameLayout) findViewById(R.id.fl_root);
        bindService(new Intent(this, (Class<?>) EhpService.class), this.connection, 1);
        if (bundle != null && bundle.containsKey(NotificationCompat.CATEGORY_STATUS)) {
            Log.i(TAG, "onCreate: savedInstanceState");
            this.mType = bundle.getInt(NotificationCompat.CATEGORY_STATUS);
            this.mValue = bundle.getInt("value");
            int i = bundle.getInt("type");
            this.mUpdateType = i;
            showFragment(this.mType, this.mValue, i);
        } else {
            changeFragment(getIntent());
        }
        UiModeManager uiModeManager = (UiModeManager) getSystemService("uimode");
        Log.e("UiModeManager", "UiModeManager=" + uiModeManager.getNightMode());
        this.fl_root.setBackgroundResource(2 == uiModeManager.getNightMode() ? R.drawable.pop_bg_night : R.drawable.pop_bg);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle bundle) {
        Log.i(TAG, "onSaveInstanceState");
        bundle.putInt(NotificationCompat.CATEGORY_STATUS, this.mType);
        bundle.putInt("value", this.mValue);
        bundle.putInt("type", this.mUpdateType);
        super.onSaveInstanceState(bundle);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
        changeFragment(intent);
    }

    private void changeFragment(Intent intent) {
        showFragment((int) intent.getLongExtra(NotificationCompat.CATEGORY_STATUS, 1L), (int) intent.getLongExtra("value", 0L), (int) intent.getLongExtra("type", -1L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showFragment(int i, int i2, int i3) {
        Fragment fragmentNewInstance;
        Log.i(TAG, "showFragment: " + i + "," + i2);
        this.mType = i;
        this.mValue = i2;
        this.mUpdateType = i3;
        if (i == 1) {
            fragmentNewInstance = new LaunchFragment();
        } else if (i == 0) {
            fragmentNewInstance = ProgressFragment.newInstance(i2);
        } else {
            fragmentNewInstance = ErrorFragment.newInstance(i2, i3);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_root, fragmentNewInstance, "fragment" + i).commitAllowingStateLoss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T extends Fragment> T getFragmentByTag(int i) {
        return (T) getSupportFragmentManager().findFragmentByTag("fragment" + i);
    }

    public void startDbUpdate() {
        Log.i(TAG, "startDbUpdate: " + this.manager);
        NativeManager nativeManager = this.manager;
        if (nativeManager != null) {
            nativeManager.startUSBUpdate();
        }
        showFragment(0, 0, -1);
    }

    /* JADX INFO: renamed from: com.neusoft.na.ehp.dbu.DialogActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(DialogActivity.TAG, "onServiceConnected");
            DialogActivity.this.manager = (NativeManager) iBinder;
            DialogActivity.this.manager.setListener(new NativeManager.IProgressListener() { // from class: com.neusoft.na.ehp.dbu.DialogActivity$1$$ExternalSyntheticLambda0
                @Override // com.neusoft.na.ehp.manager.NativeManager.IProgressListener
                public final void action(int i, int i2, int i3) {
                    this.f$0.m58xdea76a1c(i, i2, i3);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$1$com-neusoft-na-ehp-dbu-DialogActivity$1, reason: not valid java name */
        /* synthetic */ void m58xdea76a1c(final int i, final int i2, final int i3) {
            Log.i(DialogActivity.TAG, "onServiceConnected: " + i + "," + i2);
            DialogActivity.this.runOnUiThread(new Runnable() { // from class: com.neusoft.na.ehp.dbu.DialogActivity$1$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m57x52073f1b(i, i2, i3);
                }
            });
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$0$com-neusoft-na-ehp-dbu-DialogActivity$1, reason: not valid java name */
        /* synthetic */ void m57x52073f1b(int i, int i2, int i3) {
            ErrorFragment errorFragment;
            if (i == 3) {
                if (DialogActivity.this.mType == 1) {
                    DialogActivity.this.showFragment(2, -10086, -10086);
                    return;
                }
                return;
            }
            if (DialogActivity.this.mType != i) {
                DialogActivity.this.showFragment(i, i2, i3);
                DialogActivity.this.mType = i;
            }
            if (i == 0) {
                ProgressFragment progressFragment = (ProgressFragment) DialogActivity.this.getFragmentByTag(i);
                if (progressFragment != null) {
                    progressFragment.updateProgress(i2);
                    return;
                }
                return;
            }
            if (i != 2 || (errorFragment = (ErrorFragment) DialogActivity.this.getFragmentByTag(i)) == null) {
                return;
            }
            errorFragment.setErrorType(i2, i3);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(DialogActivity.TAG, "onServiceDisconnected");
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        Log.i(TAG, "DialogActivity onConfigurationChanged: " + configuration.uiMode);
        this.fl_root.setBackgroundResource((configuration.uiMode & 48) == 32 ? R.drawable.pop_bg_night : R.drawable.pop_bg);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        NativeManager nativeManager = this.manager;
        if (nativeManager != null) {
            nativeManager.setListener(null);
        }
        unbindService(this.connection);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        Log.i(TAG, "onBackPressed");
    }
}
