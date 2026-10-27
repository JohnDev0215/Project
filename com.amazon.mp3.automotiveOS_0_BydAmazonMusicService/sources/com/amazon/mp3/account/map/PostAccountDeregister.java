package com.amazon.mp3.account.map;

import android.content.Context;
import android.os.Looper;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.ClearAccountStateHandler;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.account.signintasks.BranchIdentityTask;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.language.preference.util.LocaleManager;
import com.amazon.mp3.library.dialog.PurchasedStorageLocationChangeDialog;
import com.amazon.mp3.lyrics.LyricsManager;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.languagepreference.LanguagePreference;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.signin.SignInTaskCache;

/* JADX INFO: loaded from: classes3.dex */
public class PostAccountDeregister {
    private static final String TAG = "PostAccountDeregister";

    public void onSignOut(final Context context) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            throw new RuntimeException("Cannot call this method on the main thread.");
        }
        String str = TAG;
        Log.info(str, "Executing on user sign out tasks");
        clearPlaybackState(context);
        MetricsHolder.getManager().handleSignout();
        clearAccountState(context);
        CarModeUtility.INSTANCE.clearCarModePreferences(context);
        AmazonApplication.getAuthInfoMAPR5().resetSessionId();
        PurchasedStorageLocationChangeDialog.INSTANCE.resetDialogShownState(context);
        BranchIdentityTask.clearBranchIdentity();
        Log.debug(str, "onSignOut is processed in PostAccountDeregister");
    }

    private void clearPlaybackState(Context context) {
        NowPlayingUtil.clearAndFinishNowPlaying(context, PlayStateMutationReason.ACCOUNT_REMOVED);
        if (PlayerWidgetController.isSupported()) {
            PlayerWidgetController.getInstance().updateWidget();
        }
        LyricsManager.removeAllLyrics(context);
    }

    private void clearAccountState(final Context context) {
        if (!context.getPackageName().equals("com.amazon.mp3.automotiveOS")) {
            Log.debug(TAG, "clearAccountState: clearing playback data and starting PlaybackInitializationHelper");
            Playback.getInstance().clearData();
            new PlaybackInitializationHelper(context).init();
        } else {
            Log.debug(TAG, "clearAccountState: Not clearing playback data");
        }
        AccountCredentialUtil.get(context).removeCredentialsAndSettings();
        AccountManagerSingleton.get().onUserSignedOut();
        new ClearAccountStateHandler(context).clearAppState();
        new SignInTaskCache(context).saveSignOutEvent();
        LanguagePreference.onUserSignOut(context);
        LocaleManager.INSTANCE.onUserSignOut();
        Log.debug(TAG, "clearAccountState is done");
    }
}
