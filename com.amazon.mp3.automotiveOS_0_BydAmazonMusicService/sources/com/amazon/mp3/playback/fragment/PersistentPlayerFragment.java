package com.amazon.mp3.playback.fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.BaseActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.activity.AddToPlaylistPopupActivity;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.source.local.DialogUtil;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingManager;
import com.amazon.mp3.library.util.PlaylistUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.playback.PlayStateMutationReason;
import com.amazon.mp3.playback.PlaybackErrorReceiver;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.PersistentPlayer;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.store.dialog.NoConnectionDialog;
import com.amazon.mp3.styles.BauhausTextStyler;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.ChildUserUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.voice.NowPlayingVoiceAccessProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.mts.event.types.StoreLinkType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
@Deprecated
public class PersistentPlayerFragment extends BaseFragment {
    private BaseButton.StyleBuilder likeButtonActiveBuilder;
    private BaseButton.StyleBuilder likeButtonBuilder;
    private int mFlags;
    private boolean mInitialWindowFocusHandled;
    private BaseButton mLikeButton;
    private View.OnClickListener mLikeButtonOnClickListener;
    private NoConnectionDialog mNoConnectionDialog;
    private PersistentPlayer mPersistentPlayer;
    private PersistentPlayer.PlayerType mPersistentPlayerType;
    private PopupMenu mPopupMenu;
    private volatile boolean mReceiverIsBound;
    private StyleSheet mStyleSheet;
    private View mView;
    private NowPlayingVoiceAccessProvider voiceAccessProvider;
    private static final String TAG = PersistentPlayerFragment.class.getSimpleName();
    private static final IntentFilter PLAYBACK_ERROR_FILTER = new IntentFilter("com.amazon.mp3.playbackerror");
    private final ActionValidatedPlaybackController mPlaybackController = PlaybackControllerProvider.getController(ControlSource.APP_UI);
    private boolean mRegisterForPlaybackError = true;
    private final Observer<StyleSheet> observer = new Observer<StyleSheet>() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.1
        @Override // androidx.lifecycle.Observer
        public void onChanged(final StyleSheet styleSheet) {
            PersistentPlayerFragment.this.mStyleSheet = styleSheet;
            PersistentPlayerFragment persistentPlayerFragment = PersistentPlayerFragment.this;
            PersistentPlayer.PlayerType playerType = persistentPlayerFragment.mPersistentPlayerType;
            View view = PersistentPlayerFragment.this.mView;
            ActionValidatedPlaybackController actionValidatedPlaybackController = PersistentPlayerFragment.this.mPlaybackController;
            View.OnCreateContextMenuListener onCreateContextMenuListener = PersistentPlayerFragment.this.mOnCreateNowPlayingContextMenuListener;
            View.OnLongClickListener onLongClickListener = PersistentPlayerFragment.this.mOnLongClickListener;
            PersistentPlayerFragment persistentPlayerFragment2 = PersistentPlayerFragment.this;
            persistentPlayerFragment.mPersistentPlayer = PersistentPlayer.create(playerType, view, actionValidatedPlaybackController, onCreateContextMenuListener, onLongClickListener, persistentPlayerFragment2, styleSheet, persistentPlayerFragment2.voiceAccessProvider);
            Iterator it = PersistentPlayerFragment.this.mPlayerListeners.iterator();
            while (it.hasNext()) {
                ((PlayerListener) it.next()).onPlayerCreated();
            }
            PersistentPlayerFragment.this.updateDisplay();
            if (ScreenUtil.isLandscape()) {
                PersistentPlayerFragment.this.initializeLikeButton();
            }
        }
    };
    private List<PlayerListener> mPlayerListeners = new ArrayList();
    private View.OnCreateContextMenuListener mOnCreateNowPlayingContextMenuListener = new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.2
        @Override // android.view.View.OnCreateContextMenuListener
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            if (PersistentPlayerFragment.this.mPlaybackController.canPlay()) {
                boolean zIsDownloaded = NowPlayingUtil.isDownloaded(PersistentPlayerFragment.this.mPlaybackController.getCurrentMediaItem());
                String trackString = NowPlayingUtil.getTrackString();
                MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
                boolean zIsInLibrary = currentTrack != null ? currentTrack.getOwnershipStatus().isInLibrary() : false;
                PersistentPlayerFragment.this.getActivity().getMenuInflater().inflate(R.menu.library_miniplayer_context, menu);
                BauhausTextStyler bauhausTextStyler = new BauhausTextStyler();
                menu.setHeaderTitle(bauhausTextStyler.styleText(trackString));
                PersistentPlayerFragment.this.updateContextMenuItems(menu, zIsDownloaded, zIsInLibrary);
                bauhausTextStyler.styleMenu(menu);
            }
        }
    };
    private BroadcastReceiver mNowPlayingFinishedReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.3
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
            String source = currentTrack == null ? null : currentTrack.getSource();
            if (action.equals("com.amazon.mp3.library.activity.nowplaying.clear_and_finish") && "cirrus".equals(source)) {
                NowPlayingUtil.clearAndFinishNowPlaying(PersistentPlayerFragment.this.getActivity(), PlayStateMutationReason.CLEAR_CACHE);
            } else if (action.equals("com.amazon.mp3.library.activity.nowplaying.finish")) {
                PersistentPlayerFragment.this.hide();
            }
        }
    };
    private View.OnLongClickListener mOnLongClickListener = new View.OnLongClickListener() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.4
        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            if (!PersistentPlayerFragment.this.mPlaybackController.canPlay()) {
                return false;
            }
            boolean zIsDownloaded = NowPlayingUtil.isDownloaded(PersistentPlayerFragment.this.mPlaybackController.getCurrentMediaItem());
            MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
            boolean zIsInLibrary = currentTrack != null ? currentTrack.getOwnershipStatus().isInLibrary() : false;
            PersistentPlayerFragment.this.mPopupMenu = new PopupMenu(PersistentPlayerFragment.this.getActivity(), view);
            Menu menu = PersistentPlayerFragment.this.mPopupMenu.getMenu();
            PersistentPlayerFragment.this.mPopupMenu.getMenuInflater().inflate(R.menu.library_miniplayer_context, menu);
            PersistentPlayerFragment.this.updateContextMenuItems(menu, zIsDownloaded, zIsInLibrary);
            PersistentPlayerFragment.this.mPopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.4.1
                @Override // android.widget.PopupMenu.OnMenuItemClickListener
                public boolean onMenuItemClick(MenuItem item) {
                    return PersistentPlayerFragment.this.onContextItemSelected(item);
                }
            });
            PersistentPlayerFragment.this.mPopupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.4.2
                @Override // android.widget.PopupMenu.OnDismissListener
                public void onDismiss(PopupMenu menu2) {
                    PersistentPlayerFragment.this.mPopupMenu = null;
                }
            });
            PersistentPlayerFragment.this.mPopupMenu.show();
            return true;
        }
    };
    private BroadcastReceiver mPlaybackStateChanged = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.5
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (PersistentPlayer.isVisible()) {
                Log.debug(PersistentPlayerFragment.TAG, "PlaybackStateChanged: persistent player is visible. Do nothing");
                return;
            }
            String action = intent.getAction();
            if (action.equals("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_MUTATED")) {
                if (intent.getIntExtra("com.amazon.mp3.library.provider.NowPlaying.EXTRA_MUTATION_FLAGS", 0) == 16) {
                    Log.debug(PersistentPlayerFragment.TAG, "PlaybackStateChanged: now playing mutated");
                    PersistentPlayerFragment.this.updateDisplay();
                    return;
                }
                return;
            }
            if (action.equals("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_REFRESH")) {
                Log.debug(PersistentPlayerFragment.TAG, "PlaybackStateChanged: now playing refresh");
                PersistentPlayerFragment.this.updateDisplay();
            }
        }
    };
    private PlaybackErrorReceiver.PlaybackErrorReceiverListener playbackErrorReceiverListener = new PlaybackErrorReceiver.PlaybackErrorReceiverListener() { // from class: com.amazon.mp3.playback.fragment.PersistentPlayerFragment.6
        @Override // com.amazon.mp3.playback.PlaybackErrorReceiver.PlaybackErrorReceiverListener
        public void startNoNetworkDialog() {
            PersistentPlayerFragment.this.showNoNetwork();
        }

        @Override // com.amazon.mp3.playback.PlaybackErrorReceiver.PlaybackErrorReceiverListener
        public void startActivityForIntent(final Intent intent) {
            PersistentPlayerFragment.this.startActivity(intent);
        }
    };
    private BroadcastReceiver mPlaybackErrorReceiver = new PlaybackErrorReceiver(this.playbackErrorReceiverListener);

    public interface PlayerListener {
        void onPlayerCreated();
    }

    public void addPlayerListener(PlayerListener playerListener) {
        this.mPlayerListeners.add(playerListener);
    }

    public void removePlayerListener(PlayerListener playerListener) {
        this.mPlayerListeners.remove(playerListener);
    }

    public static String getFragmentTag() {
        return TAG;
    }

    public void setPersistentPlayerType(PersistentPlayer.PlayerType persistentPlayerType) {
        this.mPersistentPlayerType = persistentPlayerType;
        this.mFlags = 2;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider = new NowPlayingVoiceAccessProvider(getActivity());
        this.voiceAccessProvider = nowPlayingVoiceAccessProvider;
        nowPlayingVoiceAccessProvider.initAlexa();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPersistentPlayer();
    }

    private void initPersistentPlayer() {
        StyleSheetProvider.getStyleSheet().observe(getViewLifecycleOwner(), this.observer);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.MenuMiniPlayerContextAddToPlaylist /* 2131427591 */:
                Uri trackContentUri = NowPlayingUtil.getTrackContentUri();
                FragmentActivity activity = getActivity();
                activity.startActivity(AddToPlaylistPopupActivity.getStartIntent(activity, trackContentUri, PageType.NOW_PLAYING));
                return true;
            case R.id.MenuMiniPlayerContextAlbumDetail /* 2131427592 */:
                FragmentActivity activity2 = getActivity();
                Intent albumDetailIntent = NowPlayingUtil.getAlbumDetailIntent(activity2);
                MusicTrack currentTrack = NowPlayingManager.getInstance().getCurrentTrack();
                if (currentTrack.isPureCatalog()) {
                    albumDetailIntent.putExtra("com.amazon.mp3.library.activity.EXTRA_ALBUM_ASIN", currentTrack.getAlbumAsin());
                }
                activity2.startActivity(albumDetailIntent);
                return true;
            case R.id.MenuMiniPlayerContextClearNowPlaying /* 2131427593 */:
                NowPlayingUtil.clearAndFinishNowPlaying(getActivity(), PlayStateMutationReason.CLEARED_QUEUE_FROM_UI);
                return true;
            case R.id.MenuMiniPlayerContextDelete /* 2131427594 */:
                FragmentActivity activity3 = getActivity();
                if (activity3 instanceof BaseActivity) {
                    MediaItem currentMediaItem = this.mPlaybackController.getCurrentMediaItem();
                    ((BaseActivity) activity3).showFragmentDialog(DialogUtil.CONFIRM_DELETE_CONTENT_ID, DialogUtil.buildConfirmDeleteTrackDialog(activity3, NowPlayingUtil.getTrackContentUri(), currentMediaItem == null ? "" : currentMediaItem.getName()));
                }
                return true;
            case R.id.MenuMiniPlayerContextExploreArtist /* 2131427595 */:
                FragmentActivity activity4 = getActivity();
                activity4.startActivity(NowPlayingUtil.getArtistExploreIntent(activity4));
                MetricsLogger.clickedOnStoreLink(StoreLinkType.ARTIST);
                return true;
            case R.id.MenuMiniPlayerContextGoToArtist /* 2131427596 */:
                MusicTrack currentTrack2 = NowPlayingManager.getInstance().getCurrentTrack();
                getActivity().startActivity(LibraryActivityFactory.getArtistDetailsIntent(getActivity(), currentTrack2.getArtistName(), currentTrack2.getArtistAsin()));
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.mPersistentPlayerType = PersistentPlayer.PlayerType.values()[savedInstanceState.getInt("com.amazon.mp3.EXTRA_PLAYER_TYPE")];
            this.mFlags = savedInstanceState.getInt("com.amazon.mp3.EXTRA_PLAYER_FLAGS", 2);
            this.mRegisterForPlaybackError = savedInstanceState.getBoolean("com.amazon.mp3.EXTRA_REGISTER_PLAYBACK_ERROR");
        }
        MediaItem currentMediaItem = PlaybackControllerProvider.getController(ControlSource.APP_UI).getCurrentMediaItem();
        if (currentMediaItem != null && MediaItemExtensions.isVideo(currentMediaItem) && ScreenUtil.isPortrait()) {
            this.mView = inflater.inflate(R.layout.now_playing_video_player_controls, (ViewGroup) null);
        } else {
            this.mView = inflater.inflate(R.layout.now_playing_player_controls, (ViewGroup) null);
        }
        this.mView.setVisibility(8);
        return this.mView;
    }

    @Override // com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        PersistentPlayer.PlayerType playerType = this.mPersistentPlayerType;
        if (playerType != null) {
            outState.putInt("com.amazon.mp3.EXTRA_PLAYER_TYPE", playerType.ordinal());
        }
        outState.putInt("com.amazon.mp3.EXTRA_PLAYER_FLAGS", this.mFlags);
        outState.putBoolean("com.amazon.mp3.EXTRA_REGISTER_PLAYBACK_ERROR", this.mRegisterForPlaybackError);
    }

    @Override // com.amazon.mp3.fragment.BaseFragment
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus || this.mInitialWindowFocusHandled) {
            return;
        }
        updateDisplay();
        this.mInitialWindowFocusHandled = true;
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        PersistentPlayer persistentPlayer = this.mPersistentPlayer;
        if (persistentPlayer != null) {
            persistentPlayer.close();
        }
        StyleSheetProvider.getStyleSheet().removeObserver(this.observer);
        NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider = this.voiceAccessProvider;
        if (nowPlayingVoiceAccessProvider != null) {
            nowPlayingVoiceAccessProvider.onDestroy();
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        PopupMenu popupMenu = this.mPopupMenu;
        if (popupMenu != null) {
            popupMenu.dismiss();
        }
        if (this.mReceiverIsBound) {
            this.mReceiverIsBound = false;
            try {
                getApplication().unregisterReceiver(this.mPlaybackStateChanged);
            } catch (Exception e) {
                Log.error(TAG, "Error while unregistering playback state receiver", e);
            }
        }
        try {
            unregisterReceiver(this.mNowPlayingFinishedReceiver);
        } catch (Exception e2) {
            Log.error(TAG, "Error while unregistering NowPlaying finished receiver", e2);
        }
        if (this.mRegisterForPlaybackError) {
            try {
                unregisterReceiver(this.mPlaybackErrorReceiver);
            } catch (Exception e3) {
                Log.error(TAG, "Error while unregistering Playback error receiver", e3);
            }
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mInitialWindowFocusHandled = false;
        updateDisplay();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_MUTATED");
        intentFilter.addAction("com.amazon.mp3.library.provider.NowPlaying.ACTION_NOW_PLAYING_REFRESH");
        registerReceiver(this.mPlaybackStateChanged, intentFilter);
        this.mReceiverIsBound = true;
        IntentFilter intentFilter2 = new IntentFilter();
        intentFilter2.addAction("com.amazon.mp3.library.activity.nowplaying.finish");
        intentFilter2.addAction("com.amazon.mp3.library.activity.nowplaying.clear_and_finish");
        registerReceiver(this.mNowPlayingFinishedReceiver, intentFilter2);
        if (this.mRegisterForPlaybackError) {
            registerReceiver(this.mPlaybackErrorReceiver, PLAYBACK_ERROR_FILTER);
        }
    }

    public void hide() {
        PersistentPlayer persistentPlayer = this.mPersistentPlayer;
        if (persistentPlayer != null) {
            persistentPlayer.hide();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDisplay() {
        String str = TAG;
        android.util.Log.d(str, "updatedDisplay");
        if (this.mPersistentPlayer == null) {
            return;
        }
        int i = this.mFlags;
        if (i == 1) {
            android.util.Log.d(str, "updatedDisplay: force show");
            this.mPersistentPlayer.show();
        } else if (i == 2) {
            android.util.Log.d(str, "updatedDisplay: auto show");
            this.mPersistentPlayer.showIfRequired();
        } else {
            throw new IllegalArgumentException("mFlags value is unknown!");
        }
        updateLikeButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateContextMenuItems(final Menu menu, final boolean downloaded, final boolean inLibrary) {
        if (!downloaded) {
            menu.removeItem(R.id.MenuMiniPlayerContextDelete);
        }
        String artistName = NowPlayingUtil.getArtistName();
        if (!AmazonApplication.getCapabilities().isStoreSupported() || TextUtils.isEmpty(artistName) || ChildUserUtil.INSTANCE.isChildUser(requireContext())) {
            menu.removeItem(R.id.MenuMiniPlayerContextExploreArtist);
        } else {
            menu.findItem(R.id.MenuMiniPlayerContextExploreArtist).setTitle(getActivity().getString(R.string.dmusic_context_shop_for, artistName));
        }
        if (AmazonApplication.getCapabilities().shouldHideArtistLink()) {
            menu.removeItem(R.id.MenuMiniPlayerContextGoToArtist);
        }
        if (PlaylistUtil.getPlaylistBeingEdited() != null) {
            menu.removeItem(R.id.MenuMiniPlayerContextAddToPlaylist);
        }
        if (inLibrary) {
            return;
        }
        menu.removeItem(R.id.MenuMiniPlayerContextAlbumDetail);
    }

    public PersistentPlayer getPersistentPlayer() {
        return this.mPersistentPlayer;
    }

    public PersistentPlayer.PlayerType getPersistentPlayerType() {
        return this.mPersistentPlayerType;
    }

    public void registerForPlaybackError(boolean registerForPlaybackError) {
        this.mRegisterForPlaybackError = registerForPlaybackError;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showNoNetwork() {
        if (this.mNoConnectionDialog == null) {
            this.mNoConnectionDialog = NoConnectionDialog.newInstance(false);
        }
        if (this.mNoConnectionDialog.isShowing()) {
            return;
        }
        this.mNoConnectionDialog.show(getFragmentManager(), "NoConnectionDialog");
    }

    public void updateLikeButton() {
        if (this.mLikeButton == null) {
            return;
        }
        if (!ScreenUtil.isLandscape()) {
            showLikeButton(Boolean.FALSE);
            return;
        }
        ActionValidatedPlaybackController actionValidatedPlaybackController = this.mPlaybackController;
        if (actionValidatedPlaybackController == null) {
            showLikeButton(Boolean.FALSE);
            return;
        }
        MediaItem currentMediaItem = actionValidatedPlaybackController.getCurrentMediaItem();
        if (!LikesServiceProxy.INSTANCE.shouldShowLikesFeature(currentMediaItem)) {
            showLikeButton(Boolean.FALSE);
            return;
        }
        boolean z = currentMediaItem.getLikeState() == LikeState.LIKE;
        boolean zHasAnyInternetConnection = ConnectivityUtil.hasAnyInternetConnection();
        setLikeButtonState(Boolean.valueOf(z));
        showLikeButton(Boolean.TRUE);
        setLikeButtonEnabledState(Boolean.valueOf(zHasAnyInternetConnection));
        setLikeButtonOnClickListener(this.mLikeButtonOnClickListener);
    }

    public void initializeLikeButton() {
        Drawable drawable;
        BaseButton baseButton = (BaseButton) this.mView.findViewById(R.id.LikeButton);
        this.mLikeButton = baseButton;
        if (this.mStyleSheet == null || baseButton == null) {
            return;
        }
        Boolean boolValueOf = Boolean.valueOf(AmazonApplication.getCapabilities().isLikesEverywhereEnabled());
        StyleSheet styleSheet = this.mStyleSheet;
        IconSizeKey iconSizeKey = IconSizeKey.SMALL;
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        this.likeButtonBuilder = styleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        this.likeButtonActiveBuilder = this.mStyleSheet.getIconBuilder(iconSizeKey, iconStyleKey);
        ButtonStyle iconStyle = this.mStyleSheet.getIconStyle(IconStyleKey.ACCENT);
        if (boolValueOf.booleanValue()) {
            drawable = getActivity().getDrawable(R.drawable.ic_action_favorite_svg);
        } else {
            drawable = getActivity().getDrawable(R.drawable.ic_action_like);
        }
        Drawable drawable2 = getActivity().getDrawable(R.drawable.ic_action_favoriteon_svg);
        if (drawable != null) {
            this.likeButtonBuilder.withIcon(drawable);
        }
        if (drawable2 != null) {
            this.likeButtonActiveBuilder.withIcon(drawable2);
        }
        if (iconStyle != null) {
            if (boolValueOf.booleanValue()) {
                this.likeButtonActiveBuilder.withActiveStyle(iconStyle);
            } else {
                this.likeButtonBuilder.withActiveStyle(iconStyle);
            }
        }
        this.likeButtonBuilder.applyStyle(this.mLikeButton);
    }

    public void setLikeButtonOnClickListener(final View.OnClickListener onClickListener) {
        this.mLikeButtonOnClickListener = onClickListener;
        BaseButton baseButton = this.mLikeButton;
        if (baseButton != null) {
            baseButton.setOnClickListener(onClickListener);
        }
    }

    public void setLikeButtonState(final Boolean active) {
        if (this.mLikeButton != null) {
            if (AmazonApplication.getCapabilities().isLikesEverywhereEnabled()) {
                if (active.booleanValue()) {
                    this.likeButtonActiveBuilder.applyStyle(this.mLikeButton);
                } else {
                    this.likeButtonBuilder.applyStyle(this.mLikeButton);
                }
            }
            this.mLikeButton.setActive(active.booleanValue());
            this.mLikeButton.setContentDescription(getActivity().getResources().getString(active.booleanValue() ? R.string.dmusic_like_button_liked_state_desc : R.string.dmusic_like_button_neutral_state_desc));
        }
    }

    public void setLikeButtonEnabledState(final Boolean isEnabled) {
        BaseButton baseButton = this.mLikeButton;
        if (baseButton != null) {
            baseButton.setEnabled(isEnabled.booleanValue());
        }
    }

    public void showLikeButton(final Boolean show) {
        if (this.mLikeButton == null) {
            return;
        }
        if (show.booleanValue()) {
            this.mLikeButton.setVisibility(0);
        } else {
            this.mLikeButton.setVisibility(8);
        }
    }
}
