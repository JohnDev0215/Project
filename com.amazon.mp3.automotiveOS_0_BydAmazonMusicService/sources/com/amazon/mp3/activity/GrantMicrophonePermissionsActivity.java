package com.amazon.mp3.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.voice.onboarding.provider.AlexaMicPermissionProvider;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class GrantMicrophonePermissionsActivity extends GrantPermissionActivity {
    private static final String TAG = "GrantMicrophonePermissionsActivity";
    private boolean skipOnResume = false;

    @Override // com.amazon.mp3.activity.GrantPermissionActivity
    protected void requestPermissions(final Bundle savedInstanceState, final String[] permissions) {
        if (savedInstanceState != null) {
            return;
        }
        if (isFirstTimeRequestingPermission("first_time_microphone_dialog") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.RECORD_AUDIO")) {
            markPermissionRequested("first_time_microphone_dialog");
            ActivityCompat.requestPermissions(this, permissions, 0);
        } else {
            openSettings(this);
            this.skipOnResume = true;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.skipOnResume) {
            this.skipOnResume = false;
        } else {
            checkIfPermissionGrantedAndFinish("android.permission.RECORD_AUDIO");
        }
    }

    public static void requestIfNotGranted(final Context context, final GrantPermissionActivity.PermissionResultReceiver resultReceiver) {
        if (new AlexaMicPermissionProvider(context.getApplicationContext()).isMicPermissionGranted()) {
            Log.d(TAG, "requestIfNotGranted : mic permission already granted");
        } else {
            GrantPermissionActivity.startGrantPermissionsActivity(context, GrantMicrophonePermissionsActivity.class, new ArrayList(Arrays.asList("android.permission.RECORD_AUDIO")), resultReceiver);
        }
    }
}
