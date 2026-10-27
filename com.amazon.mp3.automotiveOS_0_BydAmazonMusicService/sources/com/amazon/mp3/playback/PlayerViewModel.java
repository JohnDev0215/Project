package com.amazon.mp3.playback;

import android.graphics.Bitmap;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.playback.view.PlayerViewConfig;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.RepeatMode;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PlayerViewModel extends ViewModel {
    protected MutableLiveData<PlayerState> mPlayerState = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mHasNext = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mHasPrevious = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mCanRestart = new MutableLiveData<>();
    protected MutableLiveData<Long> mProgress = new MutableLiveData<>();
    protected MutableLiveData<Long> mDuration = new MutableLiveData<>();
    protected MutableLiveData<List<PlayerContentViewModel>> mContentViewModelList = new MutableLiveData<>();
    protected MutableLiveData<Bitmap> mBitmapCurrent = new MutableLiveData<>();
    protected MutableLiveData<Bitmap> mBitmapNext = new MutableLiveData<>();
    protected MutableLiveData<Bitmap> mBitmapPrev = new MutableLiveData<>();
    protected MutableLiveData<RepeatMode> mRepeatMode = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mShuffled = new MutableLiveData<>();
    protected MutableLiveData<Integer> mRating = new MutableLiveData<>();
    protected MutableLiveData<Boolean> mIsCasting = new MutableLiveData<>();
    private MutableLiveData<PlayerViewConfig> mPlayerViewConfig = new MutableLiveData<>();

    enum ArtworkBitmapType {
        PREVIOUS,
        CURRENT,
        NEXT
    }

    public void refreshConfigState(final MediaItem mediaItem) {
        if (mediaItem == null) {
            return;
        }
        PlayerViewConfig value = this.mPlayerViewConfig.getValue();
        if (value == null || value.getPlayerViewContentType() != PlayerViewConfig.PlayerViewContentType.fromMediaItem(mediaItem)) {
            this.mPlayerViewConfig.postValue(PlayerViewConfig.createPlayerViewConfig(PlayerViewConfig.PlayerViewContentType.fromMediaItem(mediaItem)));
        }
    }

    public void setPlayerViewConfigObserver(final LifecycleOwner lifecycleOwner, final Observer<PlayerViewConfig> observer) {
        this.mPlayerViewConfig.observe(lifecycleOwner, observer);
    }

    public void setHasNextObserver(final LifecycleOwner lifecycleOwner, final Observer<Boolean> observer) {
        this.mHasNext.observe(lifecycleOwner, observer);
    }

    public void setHasPrevObserver(final LifecycleOwner lifecycleOwner, final Observer<Boolean> observer) {
        this.mHasPrevious.observe(lifecycleOwner, observer);
    }

    public void setPlayerContentListObserver(final LifecycleOwner lifecycleOwner, final Observer<List<PlayerContentViewModel>> observer) {
        this.mContentViewModelList.observe(lifecycleOwner, observer);
    }

    public void setCurrentBitmapObserver(final LifecycleOwner lifecycleOwner, final Observer<Bitmap> observer) {
        this.mBitmapCurrent.observe(lifecycleOwner, observer);
    }

    public void setPrevBitmapObserver(final LifecycleOwner lifecycleOwner, final Observer<Bitmap> observer) {
        this.mBitmapPrev.observe(lifecycleOwner, observer);
    }

    public void setNextBitmapObserver(final LifecycleOwner lifecycleOwner, final Observer<Bitmap> observer) {
        this.mBitmapNext.observe(lifecycleOwner, observer);
    }

    public void setRepeatModeObserver(final LifecycleOwner lifecycleOwner, final Observer<RepeatMode> observer) {
        this.mRepeatMode.observe(lifecycleOwner, observer);
    }

    public void setShuffleObserver(final LifecycleOwner lifecycleOwner, final Observer<Boolean> observer) {
        this.mShuffled.observe(lifecycleOwner, observer);
    }

    public void setIsCastingObserver(final LifecycleOwner lifecycleOwner, final Observer<Boolean> observer) {
        this.mIsCasting.observe(lifecycleOwner, observer);
    }

    public void setProgressObserver(final LifecycleOwner lifecycleOwner, final Observer<Long> observer) {
        this.mProgress.observe(lifecycleOwner, observer);
    }

    public void setDurationObserver(final LifecycleOwner lifecycleOwner, final Observer<Long> observer) {
        this.mDuration.observe(lifecycleOwner, observer);
    }

    public void setRatingObserver(final LifecycleOwner lifecycleOwner, final Observer<Integer> observer) {
        this.mRating.observe(lifecycleOwner, observer);
    }

    public void setPlayerStateObserver(final LifecycleOwner lifecycleOwner, final Observer<PlayerState> observer) {
        this.mPlayerState.observe(lifecycleOwner, observer);
    }

    public boolean getCanRestartValue() {
        Boolean value = this.mCanRestart.getValue();
        if (value != null) {
            return value.booleanValue();
        }
        return false;
    }

    public void updateHasNext(final boolean hasNext) {
        Boolean value = this.mHasNext.getValue();
        if (value == null || value.booleanValue() != hasNext) {
            this.mHasNext.postValue(Boolean.valueOf(hasNext));
        }
    }

    public void updateHasPrevious(final boolean hasPrevious) {
        Boolean value = this.mHasPrevious.getValue();
        if (value == null || value.booleanValue() != hasPrevious) {
            this.mHasPrevious.postValue(Boolean.valueOf(hasPrevious));
        }
    }

    public void updateCanRestart(final boolean canRestart) {
        Boolean value = this.mCanRestart.getValue();
        if (value == null || value.booleanValue() != canRestart) {
            this.mCanRestart.postValue(Boolean.valueOf(canRestart));
        }
    }

    public void updatePlayerContentViewModelList(final List<PlayerContentViewModel> playerContentViewModelList) {
        if (playerContentViewModelList != null) {
            MutableLiveData<List<PlayerContentViewModel>> mutableLiveData = this.mContentViewModelList;
            if (mutableLiveData == null || !mutableLiveData.equals(playerContentViewModelList)) {
                this.mContentViewModelList.postValue(playerContentViewModelList);
            }
        }
    }

    public void updatePlayerState(final PlayStatus playStatus) {
        PlayerState playerStateFromPlayStatus = PlayerState.fromPlayStatus(playStatus);
        PlayerState value = this.mPlayerState.getValue();
        if (value == null || value != playerStateFromPlayStatus) {
            this.mPlayerState.postValue(playerStateFromPlayStatus);
        }
    }

    public void updateShuffle(final boolean shuffled) {
        Boolean value = this.mShuffled.getValue();
        if (value == null || value.booleanValue() != shuffled) {
            this.mShuffled.postValue(Boolean.valueOf(shuffled));
        }
    }

    public void updateRepeat(final RepeatMode repeatMode) {
        RepeatMode value = this.mRepeatMode.getValue();
        if (value == null || value != repeatMode) {
            this.mRepeatMode.postValue(repeatMode);
        }
    }

    public void updateIsCasting(final boolean isCasting) {
        Boolean value = this.mIsCasting.getValue();
        if (value == null || value.booleanValue() != isCasting) {
            this.mIsCasting.postValue(Boolean.valueOf(isCasting));
        }
    }

    public void updateRating(final int rating) {
        Integer value = this.mRating.getValue();
        if (value == null || value.intValue() != rating) {
            this.mRating.postValue(Integer.valueOf(rating));
        }
    }

    public void updateProgress(final long progress) {
        Long value = this.mProgress.getValue();
        if (value == null || value.longValue() != progress) {
            this.mProgress.postValue(Long.valueOf(progress));
        }
    }

    public void updateDuration(final long duration) {
        Long value = this.mDuration.getValue();
        if (value == null || value.longValue() != duration) {
            this.mDuration.postValue(Long.valueOf(duration));
        }
    }

    public void updateCurrentBitmap(final Bitmap bitmap) {
        Bitmap value = this.mBitmapCurrent.getValue();
        if (value == null || value != bitmap) {
            this.mBitmapCurrent.postValue(bitmap);
        }
    }

    public void updatePrevBitmap(final Bitmap bitmap) {
        Bitmap value = this.mBitmapPrev.getValue();
        if (value == null || value != bitmap) {
            this.mBitmapPrev.postValue(bitmap);
        }
    }

    public void updateNextBitmap(final Bitmap bitmap) {
        Bitmap value = this.mBitmapNext.getValue();
        if (value == null || value != bitmap) {
            this.mBitmapNext.postValue(bitmap);
        }
    }

    public enum PlayerState {
        LOADING,
        PLAYING,
        PAUSED;

        public static PlayerState fromPlayStatus(final PlayStatus playStatus) {
            if (playStatus.isDelayed()) {
                return LOADING;
            }
            if (!playStatus.willPlay()) {
                return PAUSED;
            }
            return PLAYING;
        }
    }

    public static class PlayerContentViewModel {
        private String artworkUrl;
        private String subtitle;
        private String title;

        public PlayerContentViewModel(final String title, final String subtitle, final String artworkUrl) {
            this.title = title;
            this.subtitle = subtitle;
            this.artworkUrl = artworkUrl;
        }

        public boolean equals(Object o) {
            return super.equals(o);
        }

        public String getTitle() {
            return this.title;
        }

        public String getSubtitle() {
            return this.subtitle;
        }

        public static PlayerContentViewModel fromMediaItem(final MediaItem mediaItem) {
            if (mediaItem == null) {
                return null;
            }
            PlayerViewConfig.PlayerViewContentType playerViewContentTypeFromMediaItem = PlayerViewConfig.PlayerViewContentType.fromMediaItem(mediaItem);
            String artistName = mediaItem.getArtistName();
            MediaCollectionInfo sourceMediaCollectionInfo = mediaItem.getSourceMediaCollectionInfo();
            if (playerViewContentTypeFromMediaItem == PlayerViewConfig.PlayerViewContentType.AD) {
                artistName = sourceMediaCollectionInfo.getSubtitle();
            } else if (sourceMediaCollectionInfo != null && !sourceMediaCollectionInfo.getName().equals(mediaItem.getName())) {
                StringBuilder sb = new StringBuilder();
                sb.append(artistName);
                sb.append(TextUtils.isEmpty(artistName) ? "" : " - ");
                sb.append(sourceMediaCollectionInfo.getName());
                artistName = sb.toString();
            }
            if (MediaItemHelper.isInterludeMediaItem(mediaItem)) {
                artistName = mediaItem.getSourceMediaCollectionInfo().getSubtitle();
                if (TextUtils.isEmpty(artistName) && !TextUtils.isEmpty(mediaItem.getArtistName())) {
                    artistName = mediaItem.getArtistName();
                }
            }
            return new PlayerContentViewModel(mediaItem.getName(), artistName, "");
        }
    }
}
