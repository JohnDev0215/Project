package com.amazon.music.media.auto.util;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import com.amazon.music.media.auto.R$string;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaybackStateUtility.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J;\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/media/auto/util/PlaybackStateUtility;", "", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSession", "", "setNonePlayBackState", "Landroid/content/Context;", "context", "Landroid/os/Bundle;", "extras", "", "playbackStateErrorCode", "", "message", "setErrorPlaybackState", "(Landroid/support/v4/media/session/MediaSessionCompat;Landroid/content/Context;Landroid/os/Bundle;Ljava/lang/Integer;Ljava/lang/String;)V", "<init>", "()V", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public final class PlaybackStateUtility {
    public static final PlaybackStateUtility INSTANCE = new PlaybackStateUtility();

    private PlaybackStateUtility() {
    }

    public final void setNonePlayBackState(MediaSessionCompat mediaSession) {
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        mediaSession.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0L, 0.0f).setActions(240640L).build());
    }

    public final void setErrorPlaybackState(MediaSessionCompat mediaSession, Context context, Bundle extras, Integer playbackStateErrorCode, String message) {
        Intrinsics.checkNotNullParameter(mediaSession, "mediaSession");
        Intrinsics.checkNotNullParameter(context, "context");
        PlaybackStateCompat.Builder state = new PlaybackStateCompat.Builder().setState(7, 0L, 0.0f);
        int iIntValue = playbackStateErrorCode == null ? 2 : playbackStateErrorCode.intValue();
        if (message == null) {
            message = context.getString(R$string.dmusic_automotive_voice_search_unavailable_error_message);
            Intrinsics.checkNotNullExpressionValue(message, "context.getString(R.stri…navailable_error_message)");
        }
        mediaSession.setPlaybackState(state.setErrorMessage(iIntValue, message).setExtras(extras).build());
    }
}
