package com.amazon.mp3.fragment.contextmenu;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.widget.AdapterView;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.environment.url.EndpointsProvider;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.library.provider.source.nowplaying.SequencerHelper;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playlist.PlaylistSeedEntityType;
import com.amazon.mp3.prime.browse.PrimeArtistPlaybackHandler;
import com.amazon.mp3.prime.browse.metadata.PrimeArtist;
import com.amazon.mp3.prime.upsell.nightwing.UpsellUtil;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.util.IdGenerator;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.share.ArtistShareProvider;

/* JADX INFO: loaded from: classes3.dex */
public class PrimeArtistContextMenuProvider extends ContextMenuProvider<PrimeArtist> {
    private final PrimeArtistPlaybackHandler mPlaybackHandler;

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected int getContextMenuLayoutId() {
        return R.menu.prime_artist_context;
    }

    public PrimeArtistContextMenuProvider(final FragmentActivity activity, final PrimeArtistPlaybackHandler playbackHandler) {
        this.mPlaybackHandler = playbackHandler;
        init(activity);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    protected void updateContextMenuItems(Activity activity, ContextMenu menu, Object tag, int position, String sourceID) {
        PrimeArtist primeArtist = (PrimeArtist) tag;
        setStyledHeaderTitle(primeArtist.getTitle(), menu);
        if (TextUtils.isEmpty(primeArtist.getAsin()) || ShopLinkUtil.isArtistBlacklisted(primeArtist.getName()) || !AmazonApplication.getCapabilities().isSharingSupported()) {
            menu.removeItem(R.id.MenuArtistContextShare);
        }
        if (!primeArtist.isAllOwned()) {
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuArtistPlayNext), this.mActivity);
            StringUtil.tryResetMenuTitleColorForNightwing(menu.findItem(R.id.MenuArtistAddToPlayQueue), this.mActivity);
        }
        if (TextUtils.isEmpty(primeArtist.getAsin())) {
            menu.removeItem(R.id.MenuArtistStationFromAnything);
            menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
        }
        if (AmazonApplication.getCapabilities().isEntityPlaylistSupported()) {
            menu.removeItem(R.id.MenuArtistStationFromAnything);
        } else {
            menu.removeItem(R.id.MenuArtistGoToEntityPlaylist);
        }
        if (SequencerHelper.canModifyQueue()) {
            return;
        }
        menu.removeItem(R.id.MenuArtistAddToPlayQueue);
        menu.removeItem(R.id.MenuArtistPlayNext);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, AdapterView.AdapterContextMenuInfo menuInfo, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeArtist) menuInfo.targetView.getTag(), sourceID);
    }

    @Override // com.amazon.mp3.fragment.contextmenu.ContextMenuProvider
    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, Object tag, String sourceID) {
        return onContextMenuItemSelected(activity, item, (PrimeArtist) tag, sourceID);
    }

    public boolean onContextMenuItemSelected(FragmentActivity activity, MenuItem item, PrimeArtist artist, String sourceID) {
        if (artist == null) {
            return false;
        }
        setClickedEntityIdAndType(artist.getAsin(), EntityIdType.ASIN);
        int itemId = item.getItemId();
        if (itemId == R.id.MenuArtistPlayNext) {
            return addToPlayQueue(activity, artist, sourceID, ActionType.PLAY_NEXT_OVERFLOW, true);
        }
        if (itemId == R.id.MenuArtistAddToPlayQueue) {
            return addToPlayQueue(activity, artist, sourceID, ActionType.ADD_TO_PLAY_Q_OVERFLOW, false);
        }
        if (itemId == R.id.MenuArtistContextShare) {
            new ArtistShareProvider(activity, EndpointsProvider.get().getMusicDomain(), artist.getAsin(), artist.getName(), artist.getArtworkUri()).startShare();
            return true;
        }
        if (itemId == R.id.MenuArtistStationFromAnything) {
            StationUtils.startSFA(activity, artist.getAsin(), "ARTIST_SEED", PlaybackPageType.ARTISTS_LIST, ControlSource.APP_UI);
            if (artist.getAsin() != null) {
                StationUtils.sendStartSFAUiClickMetrics(artist.getAsin(), EntityIdType.SFA_ARTIST_SEED, getPageType());
            }
            return true;
        }
        if (itemId != R.id.MenuArtistGoToEntityPlaylist) {
            return false;
        }
        goToEntityPlaylistFragment(artist.getAsin(), PlaylistSeedEntityType.ARTIST);
        return true;
    }

    private boolean addToPlayQueue(FragmentActivity activity, PrimeArtist artist, String sourceID, ActionType actionType, boolean playNext) {
        sendMetricEvent(actionType);
        if (UserSubscriptionUtil.isNightwingOnly() && !artist.isAllOwned()) {
            UpsellUtil.showBlockingUpsell(activity, artist.getAsin(), UpsellReason.ADD_TO_PLAY_QUEUE, UpsellSourceEntity.ARTIST);
            return true;
        }
        NowPlayingQueueUtil.getInstance().addToPlayQueue(MediaProvider.Artists.Tracks.getContentUri(sourceID, MediaProvider.Artists.getArtistId(MediaProvider.Artists.getContentUri(artist.getSource(), IdGenerator.generateArtistId(artist.getTitle())))), playNext);
        return true;
    }
}
