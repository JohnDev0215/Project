package com.amazon.mp3.actionbar.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.StringRes;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.library.activity.LibraryActivityFactory;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.view.ContentDisabledHelper;
import com.amazon.mp3.view.DownloadProgressBadgeView;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerControlsView extends LinearLayout {
    private static final String TAG = PlayerControlsView.class.getSimpleName();
    private Activity mActivity;
    private View mAddAllButton;
    private AddClickListener mAddClickListener;
    private BigButtonConfig mBigButtonConfig;
    private LinearLayout mBigButtonContainer;
    private View.OnClickListener mBigButtonListener;
    private ImageView mBigButtonShuffle;
    private ImageView mBigButtonStation;
    private TextView mBigButtonTextView;
    private View mDeleteButton;
    private View.OnClickListener mDeleteListener;
    private View mDownloadButton;
    private DownloadProgressBadgeView mDownloadProgress;
    private View.OnClickListener mDownloadProgressListener;
    private boolean mDownloaded;
    private View mFollowButton;
    private LinearLayout mFollowContainer;
    private View.OnClickListener mFollowListener;
    private boolean mFreeTierUserPlaylistEditingEnabled;
    private boolean mIsEnabled;
    private Boolean mIsFollowed;
    private boolean mIsNightwingOnlyUser;
    private final PlaybackMetricInformation mMetricInformation;
    private Intent mNowPlayingIntent;
    private boolean mOffline;
    private View mPlayButton;
    private View.OnClickListener mPlayListener;
    private View mSFAButton;
    private View.OnClickListener mSFAClickListener;
    private SelectionProvider mSelectionProvider;
    private View mShareButton;
    private View.OnClickListener mShareClickListener;
    private boolean mShouldBlockPlaybackAndDownload;
    private View mShuffleButton;
    private View.OnClickListener mShuffleListener;
    private String mSortOrder;
    private TrackInformationFetcher mTrackFetcher;
    private Uri mUri;

    public enum BigButtonConfig {
        BIG_SHUFFLE_BUTTON,
        BIG_SFA_BUTTON,
        NONE
    }

    public interface SelectionProvider {
        String[] getSelectionArgsArray();

        String getSelectionString();
    }

    public interface TrackInformationFetcher {
        List<MusicTrack> getAllTracks();

        String getCollectionName();

        MusicTrack getTrack(int position);

        int getTrackPositionForPlayback(boolean shuffled);
    }

    private void updateHeartFollowState() {
    }

    public PlayerControlsView(Activity activity, Intent intent, boolean z, PlaybackPageType playbackPageType, boolean z2, boolean z3) {
        this(activity, intent, z ? 1 : 0, false, playbackPageType, false, z2, z3);
    }

    public PlayerControlsView(final Activity activity, final Intent nowPlayingIntent, final boolean isDownloadEnabled, final PlaybackPageType playbackPageType) {
        this(activity, nowPlayingIntent, isDownloadEnabled, playbackPageType, !ScreenUtil.isPortrait(), false);
    }

    public PlayerControlsView(final Activity activity, final Intent nowPlayingIntent, final int downloadButtonToShow, final boolean isFollowed) {
        this(activity, nowPlayingIntent, downloadButtonToShow, isFollowed, PlaybackPageType.PRIME_PLAYLIST_DETAIL, true, !ScreenUtil.isPortrait(), false);
    }

    public PlayerControlsView(final Activity activity, final Intent nowPlayingIntent, final BigButtonConfig bigButtonConfig, final PlaybackPageType playbackPageType, final Boolean isFollowed) {
        this(activity, nowPlayingIntent, bigButtonConfig, playbackPageType, !ScreenUtil.isPortrait(), isFollowed);
    }

    private PlayerControlsView(final Activity activity, Intent nowPlayingIntent, BigButtonConfig bigButtonConfig, PlaybackPageType playbackPageType, boolean spanLayout, Boolean isFollowed) {
        super(activity);
        this.mActivity = activity;
        this.mNowPlayingIntent = nowPlayingIntent;
        this.mIsFollowed = isFollowed;
        this.mIsNightwingOnlyUser = UserSubscriptionUtil.isNightwingOnly();
        this.mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        LayoutInflater.from(getContext()).inflate(spanLayout ? R.layout.fragment_prime_playlist_detail_player_controls_relative : R.layout.fragment_prime_playlist_detail_player_controls, (ViewGroup) this, true);
        this.mBigButtonConfig = bigButtonConfig;
        initNightwingLayout();
        this.mMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackPageType);
    }

    private PlayerControlsView(final Activity activity, Intent nowPlayingIntent, int downloadButtonToShow, boolean isFollowed, PlaybackPageType playbackPageType, boolean forPrimePlaylist, boolean spanLayout, boolean isShareEnabled) {
        super(activity);
        this.mIsFollowed = Boolean.valueOf(isFollowed);
        this.mActivity = activity;
        this.mNowPlayingIntent = nowPlayingIntent;
        this.mIsNightwingOnlyUser = UserSubscriptionUtil.isNightwingOnly();
        this.mFreeTierUserPlaylistEditingEnabled = AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled();
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0f));
        LayoutInflater.from(getContext()).inflate(spanLayout ? R.layout.fragment_prime_playlist_detail_player_controls_relative : R.layout.fragment_prime_playlist_detail_player_controls, (ViewGroup) this, true);
        initPlayerButtonClickListeners();
        initPlayButtonState();
        initShuffleButtonState();
        initSFAButtonState();
        if (isShareEnabled) {
            initShareButtonState();
            hideDownloadFrame();
        } else {
            initDownloadButtonState(downloadButtonToShow);
        }
        if (forPrimePlaylist) {
            initFollowPlaylistButtonStates();
        } else {
            initAddAllButton();
        }
        this.mMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.NOT_DIRECTED, playbackPageType);
        getArgumentsForPlayback();
    }

    public void setShouldBlockPlaybackAndDownload(final boolean shouldBlockPlaybackAndDownload) {
        this.mShouldBlockPlaybackAndDownload = shouldBlockPlaybackAndDownload;
    }

    public void setOffline(final boolean offline) {
        this.mOffline = offline;
    }

    public void setPlayListener(View.OnClickListener playListener) {
        this.mPlayListener = playListener;
    }

    public void setShuffleListener(View.OnClickListener shuffleListener) {
        this.mShuffleListener = shuffleListener;
    }

    public void setSFAListener(View.OnClickListener onClickListener) {
        this.mSFAClickListener = onClickListener;
    }

    public void setShareListener(View.OnClickListener onClickListener) {
        this.mShareClickListener = onClickListener;
    }

    public void setDownloadListener(View.OnClickListener downloadListener) {
        View view = this.mDownloadButton;
        if (view != null) {
            view.setOnClickListener(downloadListener);
        }
    }

    public void setDownloadProgressListener(View.OnClickListener downloadListener) {
        this.mDownloadProgressListener = downloadListener;
        DownloadProgressBadgeView downloadProgressBadgeView = this.mDownloadProgress;
        if (downloadProgressBadgeView != null) {
            downloadProgressBadgeView.setOnClickListener(downloadListener);
        }
    }

    public void setBigButtonListener(View.OnClickListener bigButtonListener) {
        this.mBigButtonListener = bigButtonListener;
        this.mBigButtonContainer.setOnClickListener(bigButtonListener);
    }

    public void setDownloadingState(int groupDownloadState) {
        View view = this.mDownloadButton;
        if (view == null) {
            return;
        }
        this.mDownloaded = groupDownloadState == 0;
        if (groupDownloadState == -2 || groupDownloadState == 0 || groupDownloadState == 1 || groupDownloadState == 2 || groupDownloadState == 3 || groupDownloadState == 4) {
            view.setVisibility(8);
            this.mDownloadProgress.setVisibility(0);
            this.mDownloadProgress.setDownloadState(groupDownloadState);
        } else {
            view.setVisibility(0);
            this.mDownloadProgress.setVisibility(8);
        }
    }

    public void setDownloadingProgress(int progress) {
        DownloadProgressBadgeView downloadProgressBadgeView = this.mDownloadProgress;
        if (downloadProgressBadgeView != null) {
            downloadProgressBadgeView.setProgress(progress);
        }
    }

    protected void initDownloadButtonState(int downloadButtonToShow) {
        if (downloadButtonToShow == 1) {
            initDownloadButton(false);
        } else if (downloadButtonToShow == 2) {
            initDownloadButton(true);
        } else {
            hideDownloadFrame();
        }
    }

    public void updateDownloadButtonToShow(int downloadButtonToShow) {
        View view = this.mDownloadButton;
        if (view != null) {
            view.setVisibility(8);
        }
        initDownloadButtonState(downloadButtonToShow);
    }

    public void setTrackInformationFetcher(TrackInformationFetcher fetcher) {
        this.mTrackFetcher = fetcher;
    }

    public void setSelectionProvider(SelectionProvider provider) {
        this.mSelectionProvider = provider;
    }

    public void setDeleteListener(View.OnClickListener deleteListener) {
        this.mDeleteListener = deleteListener;
        this.mDeleteButton.setOnClickListener(deleteListener);
        initFollowPlaylistButtonStates();
    }

    public void setFollowListener(View.OnClickListener followListener) {
        this.mFollowListener = followListener;
        this.mFollowButton.setOnClickListener(followListener);
        initFollowPlaylistButtonStates();
    }

    public void setHeartFollowListener(View.OnClickListener followListener) {
        this.mFollowContainer.setOnClickListener(followListener);
    }

    public void disablePlaybackControlState() {
        setPlayEnabled(false);
        setShuffleEnabled(false);
    }

    protected void initDownloadButton(final boolean shouldRefreshDownload) {
        View viewFindViewById = findViewById(shouldRefreshDownload ? R.id.RefreshDownloadButton : R.id.DownloadButton);
        this.mDownloadButton = viewFindViewById;
        viewFindViewById.setVisibility(0);
        setEnabled(this.mDownloadButton, this.mIsEnabled);
        if (this.mDownloadProgress == null) {
            initDownloadProgress();
        }
    }

    protected void hideDownloadFrame() {
        findViewById(R.id.DownloadViews).setVisibility(8);
    }

    protected void initDownloadProgress() {
        DownloadProgressBadgeView downloadProgressBadgeView = (DownloadProgressBadgeView) findViewById(R.id.DownloadProgress);
        this.mDownloadProgress = downloadProgressBadgeView;
        downloadProgressBadgeView.setOnClickListener(this.mDownloadProgressListener);
    }

    private void initFollowPlaylistButtonStates() {
        this.mDeleteButton = findViewById(R.id.DeleteButton);
        View viewFindViewById = findViewById(R.id.AddOrFollowButton);
        this.mFollowButton = viewFindViewById;
        setContentDescription(viewFindViewById, R.string.follow_content_description);
        if (this.mIsFollowed.booleanValue() && this.mDeleteListener != null) {
            this.mDeleteButton.setVisibility(0);
            this.mFollowButton.setVisibility(8);
        } else if (!this.mIsFollowed.booleanValue() && this.mFollowListener != null) {
            this.mDeleteButton.setVisibility(8);
            this.mFollowButton.setVisibility(0);
        } else {
            this.mDeleteButton.setVisibility(8);
            this.mFollowButton.setVisibility(8);
        }
        setFollowEnabled(this.mIsEnabled);
    }

    private class AddClickListener implements View.OnClickListener {
        private View.OnClickListener mOnClickListener;

        private AddClickListener() {
            this.mOnClickListener = null;
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.mOnClickListener = onClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(final View v) {
            View.OnClickListener onClickListener = this.mOnClickListener;
            if (onClickListener != null) {
                onClickListener.onClick(v);
                this.mOnClickListener = null;
            }
            PlayerControlsView.this.hideAddAllButton();
        }
    }

    private void initAddAllButton() {
        View viewFindViewById = findViewById(R.id.AddOrFollowButton);
        this.mAddAllButton = viewFindViewById;
        setContentDescription(viewFindViewById, R.string.add_all_to_library_content_description);
        AddClickListener addClickListener = new AddClickListener();
        this.mAddClickListener = addClickListener;
        this.mAddAllButton.setOnClickListener(addClickListener);
        setAddAllEnabled(this.mIsEnabled);
    }

    public void showAddAllButton() {
        View view = this.mAddAllButton;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void hideAddAllButton() {
        View view = this.mAddAllButton;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public synchronized void disableSFA() {
        hideSFAButton();
        this.mSFAButton = null;
    }

    public void showSFAButton() {
        View view = this.mSFAButton;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    public void hideSFAButton() {
        View view = this.mSFAButton;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void setAddAllButtonOnClickListener(View.OnClickListener onClickListener) {
        AddClickListener addClickListener = this.mAddClickListener;
        if (addClickListener != null) {
            addClickListener.setOnClickListener(onClickListener);
        }
    }

    private void initPlayerButtonClickListeners() {
        findViewById(R.id.PlayButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.PlayerControlsView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PlayerControlsView.this.mPlayListener != null) {
                    PlayerControlsView.this.mPlayListener.onClick(v);
                }
                if (PlayerControlsView.this.mShouldBlockPlaybackAndDownload) {
                    return;
                }
                PlayerControlsView.this.startNowPlayingActivity(Boolean.FALSE);
            }
        });
        findViewById(R.id.ShuffleButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.PlayerControlsView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PlayerControlsView.this.mShuffleListener != null) {
                    PlayerControlsView.this.mShuffleListener.onClick(v);
                }
                if (PlayerControlsView.this.mShouldBlockPlaybackAndDownload) {
                    return;
                }
                PlayerControlsView.this.startNowPlayingActivity(Boolean.TRUE);
            }
        });
        findViewById(R.id.StationFromAnythingButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.PlayerControlsView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PlayerControlsView.this.mSFAClickListener != null) {
                    PlayerControlsView.this.mSFAClickListener.onClick(v);
                }
            }
        });
        findViewById(R.id.ShareButton).setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.PlayerControlsView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (PlayerControlsView.this.mShareClickListener != null) {
                    PlayerControlsView.this.mShareClickListener.onClick(v);
                }
            }
        });
    }

    private void setDownloadEnabled(boolean enabled) {
        View view = this.mDownloadButton;
        if (view != null) {
            setEnabled(view, enabled);
        }
    }

    private void setEnabled(View button, boolean enabled) {
        if (this.mIsNightwingOnlyUser && this.mFreeTierUserPlaylistEditingEnabled) {
            ContentDisabledHelper.setContentEnabled(button, enabled);
        } else {
            button.setEnabled(enabled);
        }
    }

    @Override // android.view.View
    public void setEnabled(boolean enabled) {
        setEnabledWithoutSFA(enabled);
        setSFAEnabled(enabled);
    }

    public void setEnabledWithoutSFA(boolean enabled) {
        this.mIsEnabled = enabled;
        setShuffleEnabled(enabled);
        setPlayEnabled(enabled);
        setDownloadEnabled(enabled);
        setFollowEnabled(enabled);
        setAddAllEnabled(enabled);
    }

    private void initShuffleButtonState() {
        this.mShuffleButton = findViewById(R.id.ShuffleButton);
        setShuffleEnabled(this.mIsEnabled);
    }

    private void initPlayButtonState() {
        View viewFindViewById = findViewById(R.id.PlayButton);
        this.mPlayButton = viewFindViewById;
        setEnabled(viewFindViewById, this.mIsEnabled);
    }

    private void initSFAButtonState() {
        this.mSFAButton = findViewById(R.id.StationFromAnythingButton);
    }

    private void initShareButtonState() {
        View viewFindViewById = findViewById(R.id.ShareButton);
        this.mShareButton = viewFindViewById;
        viewFindViewById.setVisibility(0);
    }

    private void initNightwingLayout() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ButtonContainer);
        linearLayout.setVisibility(8);
        this.mBigButtonShuffle = (ImageView) findViewById(R.id.MainButtonShuffle);
        this.mBigButtonStation = (ImageView) findViewById(R.id.MainButtonStation);
        this.mBigButtonContainer = (LinearLayout) findViewById(R.id.MainButtonContainer);
        this.mBigButtonTextView = (TextView) findViewById(R.id.MainButtonTextView);
        this.mFollowContainer = (LinearLayout) findViewById(R.id.followButtonContainer);
        BigButtonConfig bigButtonConfig = this.mBigButtonConfig;
        if (bigButtonConfig == BigButtonConfig.NONE) {
            linearLayout.setVisibility(0);
            initPlayerButtonClickListeners();
            initSFAButtonState();
            hideDownloadFrame();
            updateHeartFollowState();
            return;
        }
        if (bigButtonConfig == BigButtonConfig.BIG_SHUFFLE_BUTTON) {
            this.mBigButtonShuffle.setEnabled(true);
            this.mBigButtonShuffle.setVisibility(0);
            this.mBigButtonTextView.setText(getContext().getString(R.string.content_description_shuffle_button));
        } else if (bigButtonConfig == BigButtonConfig.BIG_SFA_BUTTON) {
            this.mBigButtonStation.setEnabled(true);
            this.mBigButtonStation.setVisibility(0);
            this.mBigButtonTextView.setText(getContext().getString(R.string.start_station_from_anything));
        }
        updateHeartFollowState();
        this.mBigButtonContainer.setVisibility(0);
    }

    private void getArgumentsForPlayback() {
        this.mNowPlayingIntent.setExtrasClassLoader(PlayerControlsView.class.getClassLoader());
        Bundle extras = this.mNowPlayingIntent.getExtras();
        this.mSortOrder = extras.getString("com.amazon.mp3.library.EXTRA_COLLECTION_SORT_ORDER");
        this.mUri = (Uri) extras.getParcelable("com.amazon.mp3.library.EXTRA_COLLECTION_URI");
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"CheckResult"})
    public void startNowPlayingActivity(final Boolean shuffleOn) {
        final long jNow = Clock.now();
        if (!this.mDownloaded && !this.mOffline && !ConnectivityUtil.hasAnyInternetConnection(this.mActivity)) {
            ConnectivityUtil.checkConnectivityAndShowDialog(this.mActivity);
            return;
        }
        final int trackPositionForPlayback = this.mTrackFetcher.getTrackPositionForPlayback(shuffleOn.booleanValue());
        final MusicTrack track = this.mTrackFetcher.getTrack(trackPositionForPlayback);
        SelectionProvider selectionProvider = this.mSelectionProvider;
        final String selectionString = selectionProvider == null ? null : selectionProvider.getSelectionString();
        SelectionProvider selectionProvider2 = this.mSelectionProvider;
        final String[] selectionArgsArray = selectionProvider2 == null ? null : selectionProvider2.getSelectionArgsArray();
        StartPlaybackUtil.startCollectionPlayback(this.mActivity, this.mTrackFetcher.getCollectionName(), this.mUri, this.mTrackFetcher.getAllTracks(), trackPositionForPlayback, shuffleOn.booleanValue(), true, null, this.mMetricInformation.getPageType(), false, ControlSource.APP_UI, null, false, null, null, false, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.actionbar.view.PlayerControlsView$$ExternalSyntheticLambda0
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public final void handlePlayback() {
                this.f$0.lambda$startNowPlayingActivity$0(selectionString, selectionArgsArray, trackPositionForPlayback, shuffleOn, track, jNow);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: startPlayQueueSequencerAndNowPlaying, reason: merged with bridge method [inline-methods] */
    public void lambda$startNowPlayingActivity$0(final String selection, final String[] args, final int pos, final Boolean shuffleOn, final MusicTrack track, final long startTime) {
        PlayQueueSequencer.getInstance().start(ControlSource.APP_UI, this.mUri, selection, args, null, pos, shuffleOn.booleanValue(), this.mMetricInformation, track, startTime);
        this.mActivity.startActivity(LibraryActivityFactory.getIntentForContentUri(this.mActivity, MediaProvider.NowPlaying.CONTENT_URI));
        this.mActivity.overridePendingTransition(R.anim.slide_in_up, R.anim.fade_out);
    }

    private void setAddAllEnabled(boolean addAllEnabled) {
        View view = this.mAddAllButton;
        if (view != null) {
            view.setEnabled(addAllEnabled);
        }
    }

    private void setSFAEnabled(final boolean sfaEnabled) {
        if (this.mSFAButton == null) {
            return;
        }
        if (sfaEnabled) {
            showSFAButton();
        } else {
            hideSFAButton();
        }
    }

    private void setFollowEnabled(boolean followEnabled) {
        View view = this.mFollowButton;
        if (view != null) {
            view.setEnabled(followEnabled);
        }
    }

    private void setShuffleEnabled(boolean shuffleEnabled) {
        View view = this.mShuffleButton;
        if (view != null) {
            setEnabled(view, shuffleEnabled);
        }
    }

    private void setPlayEnabled(boolean playEnabled) {
        View view = this.mPlayButton;
        if (view != null) {
            setEnabled(view, playEnabled);
        }
    }

    public void setContentUri(Uri contentUri) {
        this.mUri = contentUri;
    }

    public void setIsFollowedState(boolean isFollowed) {
        this.mIsFollowed = Boolean.valueOf(isFollowed);
        initFollowPlaylistButtonStates();
        updateHeartFollowState();
    }

    private void setContentDescription(final View view, @StringRes final int resourceId) {
        Context context = getContext();
        if (context == null || view == null) {
            return;
        }
        view.setContentDescription(context.getString(resourceId));
    }
}
