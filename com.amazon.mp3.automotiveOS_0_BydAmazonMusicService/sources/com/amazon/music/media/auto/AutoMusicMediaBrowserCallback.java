package com.amazon.music.media.auto;

import android.content.Context;
import android.support.v4.media.session.MediaSessionCompat;
import com.amazon.music.media.auto.util.CredentialStorage;
import kotlin.Metadata;

/* JADX INFO: compiled from: AutoMusicMediaBrowserCallback.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH&J\u0018\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH&J \u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0002H&¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/media/auto/AutoMusicMediaBrowserCallback;", "", "Landroid/content/Context;", "context", "Lcom/amazon/music/media/auto/AutoMediaBrowser;", "autoMediaBrowser", "", "configureMediaBrowser", "Landroid/support/v4/media/session/MediaSessionCompat;", "mediaSession", "onAuthenticationRequired", "onAuthenticationCompleted", "onSignOut", "Lcom/amazon/music/media/auto/util/CredentialStorage;", "credentialStorage", "onLocalChange", "", "onConnectivityChange", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0})
public interface AutoMusicMediaBrowserCallback {
    void configureMediaBrowser(Context context, AutoMediaBrowser autoMediaBrowser);

    void onAuthenticationCompleted(Context context, AutoMediaBrowser autoMediaBrowser);

    void onAuthenticationRequired(Context context, MediaSessionCompat mediaSession);

    boolean onConnectivityChange(Context context);

    void onLocalChange(Context context, CredentialStorage credentialStorage, AutoMediaBrowser autoMediaBrowser);

    void onSignOut(Context context, MediaSessionCompat mediaSession);
}
