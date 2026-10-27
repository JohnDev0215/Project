package com.amazon.mp3.account.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.amazon.mp3.account.ClearAccountStateHandler;
import com.amazon.mp3.account.details.AccountDetailStorage;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.client.activity.LauncherActivity;
import com.amazon.mp3.net.cirrus.CirrusExceptions;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.voice.wakeword.WakeWordHelper;
import com.amazon.music.media.playback.Playback;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes3.dex */
public class AccountMigratedDialogActivity extends Activity {
    private static final long DIALOG_DURATION_IN_MS = TimeUnit.SECONDS.toMillis(4);
    private static final String TAG = "AccountMigratedDialogActivity";
    private AlertDialog mDialog;

    public static Intent getStartIntent(final Context context) {
        Intent intent = new Intent(context, (Class<?>) AccountMigratedDialogActivity.class);
        intent.addFlags(268435456);
        return intent;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.info(TAG, "Account migration detected. Proceeding to reload app to reflect new country.");
        stopPlayback();
        showDialog();
        clearAppState();
    }

    @Override // android.app.Activity
    public void onPause() {
        super.onPause();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private void stopPlayback() {
        NowPlayingUtil.clearAndFinishNowPlaying(this, PlayStateMutationReason.ACCOUNT_MIGRATED);
    }

    private void clearAppState() {
        final Playback playback = Playback.getInstance();
        new Handler().postDelayed(new Runnable() { // from class: com.amazon.mp3.account.activity.AccountMigratedDialogActivity.1
            @Override // java.lang.Runnable
            public void run() {
                playback.setMricToken(ChildUserUtil.INSTANCE.getMusicRequestIdentityContextToken(AccountMigratedDialogActivity.this.getApplicationContext()));
                playback.clearData();
                new PlaybackInitializationHelper(AccountMigratedDialogActivity.this.getApplicationContext()).init();
                Context applicationContext = AccountMigratedDialogActivity.this.getApplicationContext();
                new ClearAccountStateHandler(applicationContext).clearAppState();
                AccountDetailStorage.get(AccountMigratedDialogActivity.this).setLibraryHomeMarketplace(null);
                WakeWordHelper.stopWakeWordService(applicationContext);
                AccountMigratedDialogActivity.this.deactivateLegacyMigrationDetector();
                AccountMigratedDialogActivity.this.finish();
                LauncherActivity.start(AccountMigratedDialogActivity.this.getApplicationContext());
            }
        }, DIALOG_DURATION_IN_MS);
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(View.inflate(this, R.layout.common_progress_dialog, null));
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(R.string.dmusic_account_migrated_dialog_message);
        this.mDialog = builder.create();
        if (isFinishing()) {
            return;
        }
        this.mDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deactivateLegacyMigrationDetector() {
        CirrusExceptions.CloudPlayerHomeChangedException.setDetected(false);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }
}
