package com.amazon.mp3.playback.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.activity.NetworkErrorDialogActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.library.provider.DefaultUriMatcher;
import com.amazon.mp3.library.provider.source.local.DialogUtil;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.util.ShopLinkUtil;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;

/* JADX INFO: loaded from: classes3.dex */
public class NowPlayingContextMenu {
    private static final String TAG = "NowPlayingContextMenu";
    private static final PlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);

    public static void onCreateContextMenu(ContextMenu menu, FragmentActivity activity) {
        MediaItem currentMediaItem = mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return;
        }
        String name = currentMediaItem.getName();
        String artistName = currentMediaItem.getArtistName();
        activity.getMenuInflater().inflate(R.menu.library_now_playing_context, menu);
        menu.setHeaderTitle(new BauhausTextStyler().styleText(name));
        if (!AmazonApplication.getCapabilities().isStoreSupported()) {
            menu.removeItem(R.id.MenuNowPlayingContextExploreArtist);
        }
        updateContextMenuItems(menu, artistName, activity);
    }

    private static void updateContextMenuItems(Menu menu, String artistName, Context context) {
        menu.removeItem(R.id.MenuNowPlayingContextAddToPlaylist);
        if (ShopLinkUtil.isArtistBlacklisted(artistName) || !AmazonApplication.getCapabilities().isStoreSupported() || ChildUserUtil.INSTANCE.isChildUser(context)) {
            menu.removeItem(R.id.MenuNowPlayingContextExploreArtist);
        } else {
            menu.findItem(R.id.MenuNowPlayingContextExploreArtist).setTitle(context.getString(R.string.dmusic_context_shop_for, artistName));
        }
        MediaItem currentMediaItem = mPlaybackController.getCurrentMediaItem();
        boolean zIsFromCirrus = MediaItemHelper.isFromCirrus(currentMediaItem);
        boolean zIsInLibrary = currentMediaItem != null ? Playback.getInstance().getPlaybackConfig().getLibraryAccessProvider().isInLibrary(currentMediaItem) : false;
        if (zIsFromCirrus) {
            if (!zIsInLibrary) {
                menu.removeItem(R.id.MenuNowPlayingContextDeleteFromCirrus);
            }
            menu.removeItem(R.id.MenuNowPlayingContextDelete);
        } else {
            menu.removeItem(R.id.MenuNowPlayingContextDeleteFromCirrus);
        }
        if (CastingUtil.isCastingToAlexa()) {
            menu.removeItem(R.id.MenuNowPlayingContextClearNowPlaying);
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuNowPlayingContextGoToArtist);
        }
        Uri currentUri = NowPlayingManager.getInstance().getCurrentUri();
        int iMatch = currentUri != null ? DefaultUriMatcher.match(currentUri) : -1;
        if (!zIsInLibrary || iMatch == 28 || iMatch == 33) {
            menu.removeItem(R.id.MenuNowPlayingContextDelete);
            menu.removeItem(R.id.MenuNowPlayingContextDeleteFromCirrus);
        }
        if ((!zIsInLibrary && !zIsFromCirrus) || (!zIsFromCirrus && currentMediaItem != null && currentMediaItem.getType() == MediaItem.Type.OWNED_CONTENT && currentMediaItem.getMediaItemId(MediaItemId.Type.ASIN) == null)) {
            menu.removeItem(R.id.MenuNowPlayingContextAddToPlaylist);
        }
        if (!zIsInLibrary) {
            menu.removeItem(R.id.MenuNowPlayingContextAlbumDetail);
        }
        if (currentMediaItem instanceof AdMediaItem) {
            menu.removeItem(R.id.MenuNowPlayingContextAddToPlaylist);
        }
        if (MediaItemExtensions.isVideo(currentMediaItem)) {
            menu.removeItem(R.id.MenuNowPlayingContextClearNowPlaying);
            menu.removeItem(R.id.MenuNowPlayingContextAddToPlaylist);
            menu.removeItem(R.id.MenuNowPlayingContextExploreArtist);
        }
    }

    public static boolean onContextItemSelected(MenuItem item, FragmentActivity activity, DeleteContentHandler deleteHandler) {
        MediaItem currentMediaItem = mPlaybackController.getCurrentMediaItem();
        if (currentMediaItem == null) {
            return false;
        }
        Playback.getInstance().getPlaybackConfig().getLibraryAccessProvider().isInLibrary(currentMediaItem);
        boolean zIsPureCatalog = MediaItemHelper.isPureCatalog(currentMediaItem);
        switch (item.getItemId()) {
            case R.id.MenuNowPlayingContextAddToPlaylist /* 2131427599 */:
                Intent startIntentForCurrentItem = AddToPlaylistPopupActivity.getStartIntentForCurrentItem(activity, PageType.NOW_PLAYING);
                if (startIntentForCurrentItem == null) {
                    Log.error(TAG, "Unable to add current item to playlist");
                } else {
                    activity.startActivity(startIntentForCurrentItem);
                }
                break;
            case R.id.MenuNowPlayingContextAlbumDetail /* 2131427600 */:
                Intent albumDetailIntent = NowPlayingUtil.getAlbumDetailIntent(activity);
                if (zIsPureCatalog) {
                    albumDetailIntent.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", MediaItemHelper.getAlbumAsin(currentMediaItem));
                }
                activity.startActivity(albumDetailIntent);
                break;
            case R.id.MenuNowPlayingContextClearNowPlaying /* 2131427601 */:
                NowPlayingUtil.clearAndFinishNowPlaying(activity, PlayStateMutationReason.CLEARED_QUEUE_FROM_UI);
                break;
            case R.id.MenuNowPlayingContextDelete /* 2131427602 */:
                String name = currentMediaItem.getName();
                String mediaItemIdString = MediaItem.getMediaItemIdString(currentMediaItem, MediaItemId.Type.CONTENT_URI, null);
                if (!TextUtils.isEmpty(mediaItemIdString)) {
                    DialogUtil.buildConfirmDeleteTrackDialog(activity, Uri.parse(mediaItemIdString), name).show();
                }
                break;
            case R.id.MenuNowPlayingContextDeleteFromCirrus /* 2131427603 */:
                if (!ConnectivityUtil.hasAnyInternetConnection()) {
                    activity.startActivity(NetworkErrorDialogActivity.getStartIntent(activity));
                } else {
                    ((BaseActivity) activity).showFragmentDialog(DialogUtil.CONFIRM_DELETE_CONTENT_ID, DialogUtil.buildConfirmDeleteTrackFromCloudDialog(activity, deleteHandler));
                }
                break;
            case R.id.MenuNowPlayingContextExploreArtist /* 2131427604 */:
                activity.startActivity(NowPlayingUtil.getArtistExploreIntent(activity));
                MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
                break;
            case R.id.MenuNowPlayingContextGoToArtist /* 2131427605 */:
                activity.startActivity(LibraryActivityFactory.getArtistDetailsIntent(activity, currentMediaItem.getArtistName(), MediaItemHelper.getArtistAsin(currentMediaItem)));
                break;
        }
        return false;
    }
}
