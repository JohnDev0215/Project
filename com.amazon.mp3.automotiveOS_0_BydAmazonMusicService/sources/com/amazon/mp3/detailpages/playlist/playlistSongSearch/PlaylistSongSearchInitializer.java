package com.amazon.mp3.detailpages.playlist.playlistSongSearch;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.reactnative.AmazonReactActivity;
import com.amazon.mp3.reactnative.ReactInitializer;
import com.amazon.mp3.reactnative.ReactInitializerRegistry;
import com.amazon.mp3.reactnativemodule.provider.ToastProvider;
import com.amazon.music.connect.feed.DefaultReactIdentityProvider;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlaylistSongSearchInitializer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\bJ\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/detailpages/playlist/playlistSongSearch/PlaylistSongSearchInitializer;", "", "toastProvider", "Lcom/amazon/mp3/reactnativemodule/provider/ToastProvider;", "(Lcom/amazon/mp3/reactnativemodule/provider/ToastProvider;)V", "INITIAL_PROP_LOCALE", "", "playlistData", "Landroid/os/Bundle;", "getReactActivityIntent", "Landroid/content/Intent;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "setPlaylistData", "", "entityData", "startSongSearch", "hostActivity", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaylistSongSearchInitializer {
    private final String INITIAL_PROP_LOCALE;
    private Bundle playlistData;
    private final ToastProvider toastProvider;

    public PlaylistSongSearchInitializer(ToastProvider toastProvider) {
        Intrinsics.checkNotNullParameter(toastProvider, "toastProvider");
        this.toastProvider = toastProvider;
        this.INITIAL_PROP_LOCALE = Splash.PARAMS_LOCALE;
    }

    public final void setPlaylistData(Bundle entityData) {
        this.playlistData = entityData;
    }

    public final void startSongSearch(FragmentActivity hostActivity) {
        Intrinsics.checkNotNullParameter(hostActivity, "hostActivity");
        hostActivity.startActivity(getReactActivityIntent(hostActivity));
    }

    private final Intent getReactActivityIntent(FragmentActivity activity) {
        Intent intent = new Intent(activity, (Class<?>) AmazonReactActivity.class);
        ReactInitializer reactInitializer = new ReactInitializer(activity.getApplication());
        reactInitializer.setIdentityProvider(new DefaultReactIdentityProvider(activity));
        reactInitializer.setToastProvider(this.toastProvider);
        reactInitializer.setHostActivity(activity);
        Map<ReactInitializerRegistry.Key, ReactInitializer> Registry = ReactInitializerRegistry.Registry;
        Intrinsics.checkNotNullExpressionValue(Registry, "Registry");
        ReactInitializerRegistry.Key key = ReactInitializerRegistry.Key.PLAYLIST_SONG_SEARCH;
        Registry.put(key, reactInitializer);
        Bundle bundle = new Bundle();
        bundle.putSerializable(AmazonReactActivity.ReactInitializerNameExtra, key);
        bundle.putString(AmazonReactActivity.ModuleNameExtra, "Playlist-Song-Search");
        Bundle bundle2 = this.playlistData;
        if (bundle2 != null) {
            bundle2.putString(this.INITIAL_PROP_LOCALE, Locale.getDefault().toString());
        }
        bundle.putBundle(AmazonReactActivity.InitialPropsExtra, this.playlistData);
        intent.putExtras(bundle);
        return intent;
    }
}
