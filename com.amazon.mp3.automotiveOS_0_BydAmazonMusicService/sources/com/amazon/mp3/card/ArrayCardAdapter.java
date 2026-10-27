package com.amazon.mp3.card;

import android.content.Context;
import android.view.View;
import com.amazon.mp3.card.ArrayCardAdapter.CardViewHolder;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.view.PlayNotificationIcon;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public abstract class ArrayCardAdapter<T, H extends CardViewHolder> implements CardAdapter<T> {
    protected final Context mContext;
    private List<T> mData;
    protected Map<T, H> mHolders = new HashMap();
    private final int mLayoutResId;
    private OnPlayStateChangedListener mOnPlayStateChangedListener;
    private final PlaybackController mPlaybackController;
    protected final boolean mPrefixNumbers;
    private boolean mSavedIsPlaying;
    private MediaItem mSavedMediaItem;
    protected final boolean mShowRecommendationReason;

    public static class CardViewHolder {
        public PlayNotificationIcon mPlaystateIcon = null;
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public int getOverflowMenuButtonResId() {
        return -1;
    }

    protected boolean isItemPlaying(H holder) {
        return false;
    }

    public ArrayCardAdapter(Context context, List<T> data, int layoutResId, boolean prefixNumbers, boolean showRecommendationReason) {
        PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        this.mPlaybackController = playbackController;
        this.mOnPlayStateChangedListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.card.ArrayCardAdapter.1
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public void onPlayStateChanged() {
                if (NowPlayingUtil.isPlayingChanged(ArrayCardAdapter.this.mSavedMediaItem, ArrayCardAdapter.this.mSavedIsPlaying)) {
                    ArrayCardAdapter arrayCardAdapter = ArrayCardAdapter.this;
                    arrayCardAdapter.mSavedMediaItem = arrayCardAdapter.mPlaybackController.getCurrentMediaItem();
                    ArrayCardAdapter.this.mSavedIsPlaying = PlayNotificationIcon.shouldAnimate();
                    ArrayCardAdapter.this.updatePlaystate();
                }
            }
        };
        this.mContext = context;
        this.mLayoutResId = layoutResId;
        this.mData = data;
        this.mPrefixNumbers = prefixNumbers;
        this.mShowRecommendationReason = showRecommendationReason;
        this.mSavedMediaItem = playbackController.getCurrentMediaItem();
        this.mSavedIsPlaying = PlayNotificationIcon.shouldAnimate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePlaystate() {
        if (this.mHolders != null) {
            for (T t : getData()) {
                updatePlaystateIcon(t, this.mHolders.get(t));
            }
        }
    }

    protected void updatePlaystateIcon(T item, H viewHolder) {
        this.mHolders.put(item, viewHolder);
        if (isItemPlaying(viewHolder)) {
            viewHolder.mPlaystateIcon.setVisibility(0);
            viewHolder.mPlaystateIcon.setIsPlaying(PlayNotificationIcon.shouldAnimate());
        } else {
            viewHolder.mPlaystateIcon.setVisibility(8);
        }
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public List<T> getData() {
        return this.mData;
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public View createView() {
        return View.inflate(this.mContext, this.mLayoutResId, null);
    }

    public void checkContentEnabled(View view, boolean enabled) {
        if (enabled) {
            view.setAlpha(1.0f);
        } else {
            view.setAlpha(0.3f);
        }
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void onAttachedToWindow() {
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).addOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
        this.mOnPlayStateChangedListener.onPlayStateChanged();
    }

    @Override // com.amazon.mp3.card.CardAdapter
    public void onDetachedFromWindow() {
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).removeOnPlayStateChangedListener(this.mOnPlayStateChangedListener);
    }
}
