package com.amazon.mp3.voice.deeplink;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.activity.BootstrapSingletonTask;
import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: AlexaDeeplinkMediaBrowseHandler.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/voice/deeplink/AlexaDeeplinkMediaBrowseHandler;", "", "context", "Landroid/content/Context;", "mediaSession", "Landroid/support/v4/media/session/MediaSessionCompat;", "(Landroid/content/Context;Landroid/support/v4/media/session/MediaSessionCompat;)V", "onMediaBrowse", "", "uri", "Landroid/net/Uri;", "bundle", "Landroid/os/Bundle;", "startPlayback", "", "setUserNotAuthenticatedPlaybackError", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaDeeplinkMediaBrowseHandler {
    private static final String TAG = AlexaDeeplinkMediaBrowseHandler.class.getSimpleName();
    private final Context context;
    private final MediaSessionCompat mediaSession;

    public AlexaDeeplinkMediaBrowseHandler(Context context, MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        this.context = context;
        this.mediaSession = mediaSession;
    }

    public final void onMediaBrowse(final Uri uri, final Bundle bundle, boolean startPlayback) {
        if (!AccountCredentialUtil.get(this.context).isSignedIn()) {
            Log.error(TAG, "User not logged in, setting playback state to error in media session", uri);
            setUserNotAuthenticatedPlaybackError(this.mediaSession);
        } else {
            BootstrapSingletonTask.get().registerFeatureFlagObserver().take(1).subscribe(new Action1() { // from class: com.amazon.mp3.voice.deeplink.AlexaDeeplinkMediaBrowseHandler$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    AlexaDeeplinkMediaBrowseHandler.m1814onMediaBrowse$lambda2(uri, bundle, this, (Void) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onMediaBrowse$lambda-2, reason: not valid java name */
    public static final void m1814onMediaBrowse$lambda2(final Uri uri, final Bundle bundle, final AlexaDeeplinkMediaBrowseHandler this$0, Void r4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.voice.deeplink.AlexaDeeplinkMediaBrowseHandler$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                AlexaDeeplinkMediaBrowseHandler.m1815onMediaBrowse$lambda2$lambda1(uri, bundle, this$0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onMediaBrowse$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1815onMediaBrowse$lambda2$lambda1(Uri uri, Bundle bundle, AlexaDeeplinkMediaBrowseHandler this$0) {
        String string;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (uri == null) {
            return;
        }
        VoiceDeeplinkResolver voiceDeeplinkResolver = new VoiceDeeplinkResolver();
        if (voiceDeeplinkResolver.canResolve(uri)) {
            String str = "";
            if (bundle != null && (string = bundle.getString("com.amazon.alexa.externalmediaplayer.playbackSessionId")) != null) {
                str = string;
            }
            Log.debug(TAG, "PlaybackSessionId:%s", str);
            voiceDeeplinkResolver.resolve(this$0.context, uri, str, false);
        }
    }

    private final void setUserNotAuthenticatedPlaybackError(MediaSessionCompat mediaSession) {
        PlaybackStateCompat.Builder builder = new PlaybackStateCompat.Builder();
        builder.setState(7, 0, 0.0f);
        builder.setErrorMessage(3, "User not logged in");
        mediaSession.setPlaybackState(builder.build());
    }
}
