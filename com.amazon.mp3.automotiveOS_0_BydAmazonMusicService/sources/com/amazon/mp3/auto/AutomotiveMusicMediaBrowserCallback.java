package com.amazon.mp3.auto;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.core.app.NotificationCompat;
import androidx.media.utils.MediaConstants;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.auto.signin.AutomotiveSignInFlowLauncherActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.find.dagger.SearchServiceConfigurationProvider;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.auto.AutoMediaBrowser;
import com.amazon.music.media.auto.AutoMusicMediaBrowserCallback;
import com.amazon.music.media.auto.util.CredentialStorage;
import com.amazon.music.media.auto.util.PlaybackStateUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutomotiveMusicMediaBrowserCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0016J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/AutomotiveMusicMediaBrowserCallback;", "Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;", "Landroid/content/Context;", "context", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "autoMediaBrowser", "", "configureMediaBrowser", "onAuthenticationCompleted", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSession", "onAuthenticationRequired", "onSignOut", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "credentialStorage", "onLocalChange", "", "onConnectivityChange", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotiveMusicMediaBrowserCallback implements AutoMusicMediaBrowserCallback {
    private static final String TAG = AutomotiveMusicMediaBrowserCallback.class.getSimpleName();

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public void configureMediaBrowser(Context context, AutoMediaBrowser autoMediaBrowser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(autoMediaBrowser, "autoMediaBrowser");
        CredentialUtil.INSTANCE.configureCredentials(context, autoMediaBrowser);
        autoMediaBrowser.configureSearchService(new SearchServiceConfigurationProvider(context).getSearchConfiguration());
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public void onAuthenticationCompleted(Context context, AutoMediaBrowser autoMediaBrowser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(autoMediaBrowser, "autoMediaBrowser");
        Log.debug(TAG, "AutomotiveMusicMediaBrowserCallback.onAuthenticationCompleted() called");
        configureMediaBrowser(context, autoMediaBrowser);
        AutoMediaBrowser.fetchHomePage$default(autoMediaBrowser, false, 1, null);
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public void onAuthenticationRequired(Context context, MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        Log.debug(TAG, "AutomotiveMusicMediaBrowserCallback.onAuthenticationRequired() called");
        PendingIntent activity = PendingIntentUtil.INSTANCE.getActivity(context, 0, new Intent(context, (Class<?>) AutomotiveSignInFlowLauncherActivity.class), 0, true);
        Bundle bundle = new Bundle();
        bundle.putString(MediaConstants.PLAYBACK_STATE_EXTRAS_KEY_ERROR_RESOLUTION_ACTION_LABEL, context.getString(R.string.dmusic_automotive_authentication_sign_in_button));
        bundle.putParcelable(MediaConstants.PLAYBACK_STATE_EXTRAS_KEY_ERROR_RESOLUTION_ACTION_INTENT, activity);
        PlaybackStateUtility.INSTANCE.setErrorPlaybackState(mediaSession, context, bundle, 3, context.getString(R.string.dmusic_automotive_authentication_message));
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public void onSignOut(Context context, MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        String str = TAG;
        Log.debug(str, "onSignOut() called");
        if (Intrinsics.areEqual(context.getPackageName(), "com.amazon.mp3.automotiveOS")) {
            mediaSession.release();
            Log.debug(str, "Restarting app process for a clean media session");
            Intent intent = new Intent(context, (Class<?>) AutoMusicService.class);
            PendingIntent service = PendingIntentUtil.INSTANCE.getService(context, (int) System.currentTimeMillis(), intent, 268435456, true);
            Object systemService = context.getSystemService(NotificationCompat.CATEGORY_ALARM);
            if (systemService == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.app.AlarmManager");
            }
            ((AlarmManager) systemService).set(1, System.currentTimeMillis() + ((long) 100), service);
            System.exit(0);
            throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
        }
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public void onLocalChange(Context context, CredentialStorage credentialStorage, AutoMediaBrowser autoMediaBrowser) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(credentialStorage, "credentialStorage");
        Intrinsics.checkNotNullParameter(autoMediaBrowser, "autoMediaBrowser");
        if (!AccountCredentialUtil.get(context).isSignedIn()) {
            Log.error(TAG, "onLocaleChange called but user is not signed in.Unable to initialize credentials");
            return;
        }
        Log.debug(TAG, "AutomotiveMusicMediaBrowserCallback.onLocaleChange() called");
        configureMediaBrowser(context, autoMediaBrowser);
        autoMediaBrowser.fetchHomePage(true);
    }

    @Override // com.amazon.music.media.auto.AutoMusicMediaBrowserCallback
    public boolean onConnectivityChange(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return ConnectivityUtil.hasAnyInternetConnection(context) && AccountCredentialUtil.get(context).isSignedIn() && AccountCredentialUtil.get(context).isSignInComplete();
    }
}
