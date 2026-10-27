package com.amazon.mp3.eux;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.prime.activity.PrimeActivityFactory;
import com.amazon.mp3.store.html5.activity.StoreFragment;
import com.amazon.music.widget.eux.EUXLauncher;

/* JADX INFO: loaded from: classes3.dex */
public class EUXDestinationLauncher extends EUXLauncher {
    private static final String TAG = "EUXDestinationLauncher";
    private final Context mContext;

    public EUXDestinationLauncher(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.widget.eux.EUXLauncher
    public void launchHome() {
        handleIntent(PrimeActivityFactory.getHomeIntent(this.mContext));
    }

    @Override // com.amazon.music.widget.eux.EUXLauncher
    public void launchStore() {
        Intent startIntent = MusicHomeActivity.getStartIntent(this.mContext);
        startIntent.putExtra("com.amazon.mp3.fragment.extra", StoreFragment.class.getSimpleName());
        handleIntent(startIntent);
    }

    @Override // com.amazon.music.widget.eux.EUXLauncher
    public void launchLibrary() {
        handleIntent(getLibraryIntent());
    }

    private Intent getLibraryIntent() {
        return MusicHomeActivity.getStartIntent(this.mContext).putExtra("com.amazon.mp3.fragment.extra", MusicHomeActivity.getMyLibraryHomeFragmentName()).putExtra("com.amazon.mp3.library.EXTRA_SOURCE_ID", "cirrus").putExtra("com.amazon.mp3.library.fragment.EXTRA_TAB_POSITION_TO_OPEN", 4);
    }

    public void launchPlaylists() {
        handleIntent(PrimeActivityFactory.getPlaylistsIntent(this.mContext));
    }

    @Override // com.amazon.music.widget.eux.EUXLauncher
    public void launchStations() {
        Intent stationsIntent = PrimeActivityFactory.getStationsIntent(this.mContext);
        stationsIntent.putExtra("com.amazon.mp3.StationsFragment.extra.CATEGORY_ID", "key_refinement_selection");
        handleIntent(stationsIntent);
    }

    private void handleIntent(final Intent intent) {
        Context context;
        if (intent != null && (context = this.mContext) != null) {
            context.startActivity(intent);
        } else {
            Log.w(TAG, "Error launching intent");
        }
    }
}
