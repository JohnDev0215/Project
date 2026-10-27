package com.amazon.mp3.playlist.api;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.mp3.account.credentials.AccountCredentialStorage;
import com.amazon.mp3.environment.Environment;
import com.amazon.mp3.environment.url.EndPointURL;
import com.amazon.mp3.net.RequestInterceptorFactory;
import com.amazon.music.library.AbstractLibraryServiceImpl;
import com.amazon.music.library.LibraryFactory;
import com.amazon.music.library.LibraryServiceImpl;
import com.amazon.music.library.PlaylistLibrary;
import java.net.MalformedURLException;
import java.net.URL;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistFactory {
    private static final String TAG = "PlaylistFactory";

    public static PlaylistLibrary createPlaylistLibrary(final FragmentActivity activity) {
        return new LibraryFactory(new LibraryServiceImpl(getConfiguration(activity))).getPlaylistLibrary();
    }

    private static AbstractLibraryServiceImpl.Configuration getConfiguration(final FragmentActivity activity) {
        final AccountCredentialStorage accountCredentialStorage = AccountCredentialStorage.get();
        return new AbstractLibraryServiceImpl.Configuration() { // from class: com.amazon.mp3.playlist.api.PlaylistFactory.1
            @Override // com.amazon.music.library.AbstractLibraryServiceImpl.Configuration
            public RequestInterceptor getRequestInterceptor() {
                return new RequestInterceptorFactory(activity).provideRequestInterceptor();
            }

            @Override // com.amazon.music.library.AbstractLibraryServiceImpl.Configuration
            public URL getPlaylistServiceUrl() {
                return PlaylistFactory.getURL(Environment.MUSIC_PLAYLIST_SERVICE.get());
            }

            @Override // com.amazon.music.library.AbstractLibraryServiceImpl.Configuration
            public String getDeviceId() {
                return accountCredentialStorage.getDeviceId();
            }

            @Override // com.amazon.music.library.AbstractLibraryServiceImpl.Configuration
            public String getDeviceType() {
                return accountCredentialStorage.getDeviceType();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static URL getURL(final EndPointURL endPointURL) {
        try {
            return new URL(endPointURL.toURL());
        } catch (MalformedURLException e) {
            Log.e(TAG, "Malformed URL: " + endPointURL.toString(), e);
            return null;
        }
    }
}
