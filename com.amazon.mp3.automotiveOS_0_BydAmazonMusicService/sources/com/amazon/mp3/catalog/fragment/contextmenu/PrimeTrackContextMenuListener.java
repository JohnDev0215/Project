package com.amazon.mp3.catalog.fragment.contextmenu;

import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.fragment.contextmenu.ContextMenuPageType;
import com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider;
import com.amazon.mp3.library.fragment.OnItemFinishedAddingListener;
import com.amazon.mp3.library.provider.LibraryStateProvider;
import com.amazon.mp3.library.provider.TrackStateProvider;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeTrackContextMenuListener extends PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener {
    private final FragmentActivity activity;
    private final TrackStateProvider defaultTrackStateProvider;
    private LibraryStateProvider libraryStateProvider;
    private final Map<String, PrimeTrack> trackMap = new HashMap();
    private OnItemFinishedAddingListener mAddingListener = new OnItemFinishedAddingListener() { // from class: com.amazon.mp3.catalog.fragment.contextmenu.PrimeTrackContextMenuListener.1
        @Override // com.amazon.mp3.library.fragment.OnItemFinishedAddingListener
        public void onItemAdded(final String item, final boolean downloadAfterAdd) {
            PrimeTrack primeTrack = (PrimeTrack) PrimeTrackContextMenuListener.this.trackMap.get(item);
            if (primeTrack != null) {
                PrimeTrackContextMenuListener.this.defaultTrackStateProvider.requestState(primeTrack, PrimeTrackContextMenuListener.this.libraryStateProvider.getTrackStateListener());
                PrimeTrackContextMenuListener.this.trackMap.remove(item);
            }
        }
    };

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public void updateTrackStatusForPlaylist(final PrimeTrack track) {
    }

    public PrimeTrackContextMenuListener(final FragmentActivity activity, final LibraryStateProvider libraryStateProvider) {
        this.libraryStateProvider = null;
        this.activity = activity;
        this.libraryStateProvider = libraryStateProvider;
        this.defaultTrackStateProvider = libraryStateProvider.getTrackStateProvider();
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public TrackStateProvider getTrackStateProvider() {
        return this.defaultTrackStateProvider;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public void updateTrackStatus(final PrimeTrack track) {
        if (TextUtils.isEmpty(track.getAsin())) {
            return;
        }
        this.defaultTrackStateProvider.requestState(track, null);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public OnItemFinishedAddingListener getOnAddedListener() {
        if (this.libraryStateProvider != null) {
            return this.mAddingListener;
        }
        return null;
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public void setAdapterStatus(final PrimeTrack track, final boolean adding, final boolean downloading) {
        if (track == null || track.getAsin() == null) {
            return;
        }
        this.trackMap.put(track.getAsin(), track);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.PrimeTrackContextMenuProvider.PrimeTrackContextMenuProviderListener
    public ContextMenuPageType getContextMenupageType() {
        return ContextMenuPageType.LONG_PRESS;
    }
}
