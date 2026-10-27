package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.detailpages.album.AlbumDetailFragment;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.adapter.PrimePlaylistTrackListAdapter;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.prime.ContentOwnershipStatus;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.cta.PrimeTrackTask;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;

/* JADX INFO: loaded from: classes3.dex */
public class PrimePlaylistTrackContextMenuProvider extends TrackContextMenuProvider {
    private boolean mStartedFromPrimeBrowse;

    public PrimePlaylistTrackContextMenuProvider(BaseActivity activity, int headerCount, boolean startedFromPrimeBrowse, TrackContextMenuProvider.TrackContextMenuProviderListener listener) {
        this.mProviderListener = listener;
        this.mHeaderCount = headerCount;
        this.mStartedFromPrimeBrowse = startedFromPrimeBrowse;
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider, com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        AbstractItem abstractItem;
        super.updateContextMenuItems(activity, menu, tag, position, sourceID, false);
        if (tag instanceof PrimePlaylistTrackListAdapter.PrimePlaylistRowViewHolder) {
            abstractItem = ((PrimePlaylistTrackListAdapter.PrimePlaylistRowViewHolder) tag).mLibraryItem;
        } else {
            abstractItem = tag instanceof AbstractItem ? (AbstractItem) tag : null;
        }
        String asin = abstractItem != null ? abstractItem.getAsin() : null;
        boolean z = abstractItem != null && abstractItem.isRemote();
        if (TextUtils.isEmpty(asin) || !StationUtils.isCustomerAllowedToPlaySFAStation()) {
            menu.removeItem(R.id.MenuTrackStationFromAnything);
        }
        menu.removeItem(R.id.MenuTrackContextDeleteFromPlaylist);
        menu.removeItem(R.id.MenuTrackContextDeleteFromCirrus);
        menu.removeItem(R.id.MenuTrackContextDelete);
        if (!z) {
            menu.removeItem(R.id.MenuTrackContextAddToPlaylist);
        }
        if ((abstractItem instanceof MusicTrack) && ((MusicTrack) abstractItem).getAssetType() == PrimePlaylistTracksTable.AssetType.VIDEO) {
            menu.removeItem(R.id.MenuTrackStationFromAnything);
            menu.removeItem(R.id.MenuTrackContextAddToCirrus);
        }
    }

    @Override // com.amazon.mp3.fragment.contextmenu.TrackContextMenuProvider, com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        MusicTrack musicTrack;
        if (menuInfo.targetView.getTag() instanceof PrimePlaylistTrackListAdapter.PrimePlaylistRowViewHolder) {
            AbstractItem abstractItem = ((PrimePlaylistTrackListAdapter.PrimePlaylistRowViewHolder) menuInfo.targetView.getTag()).mLibraryItem;
            if (!(abstractItem instanceof MusicTrack)) {
                return false;
            }
            musicTrack = (MusicTrack) abstractItem;
        } else {
            if (!(menuInfo.targetView.getTag() instanceof MusicTrack)) {
                return false;
            }
            musicTrack = (MusicTrack) menuInfo.targetView.getTag();
        }
        setClickedEntityIdAndType(musicTrack.getAsin(), EntityIdType.ASIN);
        switch (item.getItemId()) {
            case R.id.MenuTrackAddToPlayQueue /* 2131427648 */:
                sendMetricEvent(ActionType.ADD_TO_PLAY_Q_OVERFLOW);
                addToPlayQueue(musicTrack, false);
                return true;
            case R.id.MenuTrackContextAddToPlaylist /* 2131427651 */:
                sendMetricEvent(ActionType.ADD_TO_PLAYLIST_ATTEMPT_OVERFLOW);
                activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, MediaProvider.Tracks.getPrimeAdditionalTrackContentUri("cirrus", "primeplaylist", musicTrack.getAsin()), this.mPageType));
                return true;
            case R.id.MenuTrackPlayNext /* 2131427663 */:
                sendMetricEvent(ActionType.PLAY_NEXT_OVERFLOW);
                addToPlayQueue(musicTrack, true);
                return true;
            case R.id.MenuTrackShowAlbumDetail /* 2131427666 */:
                sendMetricEvent(ActionType.VIEW_ALBUM_OVERFLOW);
                changeScreenFragment(this.mActivity.getSupportFragmentManager(), getAlbumDetailFragmentForPrimeTrack(activity, musicTrack));
                return true;
            case R.id.MenuTrackStationFromAnything /* 2131427668 */:
                StationUtils.startSFA(activity, musicTrack.getAsin(), "TRACK_SEED", PlaybackPageType.SONGS_LIST, ControlSource.APP_UI);
                StationUtils.sendStartSFAUiClickMetrics(musicTrack.getAsin(), EntityIdType.SFA_TRACK_SEED);
                return true;
            case R.id.MenuVideoPlay /* 2131427673 */:
                PrimeTrack primeTrackFromTrack = PrimeTrack.fromTrack(musicTrack);
                if (!ContentAccessUtil.canContinueCatalogAction(activity, primeTrackFromTrack, ContentAccessUtil.ContentAccessOperation.BROWSE)) {
                    return false;
                }
                VideoUtil.INSTANCE.playVideo(primeTrackFromTrack);
                sendMetricEvent(ActionType.PLAY_VIDEO);
                return true;
            default:
                return super.onContextMenuItemSelected(activity, item, menuInfo, sourceID);
        }
    }

    private Fragment getAlbumDetailFragmentForPrimeTrack(Activity activity, MusicTrack track) {
        Fragment albumDetailFragment;
        Intent intentForContentUri = LibraryActivityFactory.getIntentForContentUri(activity, MediaProvider.Albums.getContentUri("cirrus", IdGenerator.generateAlbumId(track.getAlbumAsin(), track.getAlbumName())));
        if (AmazonApplication.getCapabilities().isBauhausADPEnabled()) {
            intentForContentUri.putExtra("EXTRA_ALBUM_ASIN", track.getAlbumAsin());
            albumDetailFragment = new AlbumDetailFragment();
        } else {
            intentForContentUri.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", track.getAlbumAsin());
            intentForContentUri.putExtra("com.amazon.mp3.EXTRA_ALWAYS_SHOW_NON_LIB_CONTENT", this.mStartedFromPrimeBrowse);
            intentForContentUri.putExtra("com.amazon.mp3.library.EXTRA_SHOW_HEADER", true);
            albumDetailFragment = new com.amazon.mp3.library.fragment.AlbumDetailFragment();
        }
        albumDetailFragment.setArguments(intentForContentUri.getExtras());
        return albumDetailFragment;
    }

    public void addToPlayQueue(MusicTrack track, boolean playNext) {
        if (track.getOwnershipStatus() == ContentOwnershipStatus.NOT_IN_LIBRARY) {
            PrimeTrackTask primeTrackTaskCreatePlaybackTask = PrimeTrackTask.createPlaybackTask(this.mActivity, track.getAlbumAsin(), track.getAsin(), (PlaybackMetricInformation) null, (PlaybackPageType) null, playNext ? PrimeCollectionTask.Task.PLAY_NEXT : PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE, new QuerySongByAsin(this.mActivity), (PrimeCollectionTask.OnUriReadyCallback) null);
            if (track.getAssetType() == PrimePlaylistTracksTable.AssetType.VIDEO) {
                primeTrackTaskCreatePlaybackTask.setIsVideo(true);
            }
            primeTrackTaskCreatePlaybackTask.execute();
            return;
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(track.getContentUri(), playNext);
    }
}
