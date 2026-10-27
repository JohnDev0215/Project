package com.amazon.music.media.playback.player;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.media.VolumeProviderCompat;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.OnFrameChangedListener;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlaybackMetricsListener;
import com.amazon.music.media.playback.OnPrebufferChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.PlaybackAttributes;
import com.amazon.music.media.playback.PlaybackException;
import com.amazon.music.media.playback.PlayerCapabilities;
import com.amazon.music.media.playback.audioeffects.AudioEffectsControl;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.impl.AudioFocusManager;
import com.amazon.music.media.playback.util.StringUtils;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface Player extends Closeable {

    public interface Callback extends OnMetadataChangedListener {
        void onClearQueue();

        void onIndexChanged(int i, int i2, ChangeReason changeReason, PlaybackException playbackException);

        void onMediaItemAlmostFinished();

        void onPlayStatusChanged(PlayStatus playStatus, PlayStatus playStatus2, PlaybackException playbackException);

        void onPlaybackAttributesChanged();

        void onRepeatingMediaItem();
    }

    boolean canFastForward();

    boolean canPlay(Uri uri);

    boolean canRewind();

    boolean canSeek();

    @WorkerThread
    void clearData();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void connect(Callback callback, PlaybackConfig playbackConfig, State state, AudioFocusManager audioFocusManager);

    State disconnect();

    void fastForward(ControlSource controlSource);

    AudioEffectsControl getAudioEffectsController();

    int getAudioFocusStatus();

    ConcurrencyProvider getConcurrencyProvider();

    int getCurrentIndex();

    PlayRequest getCurrentPlayRequest();

    @NonNull
    PlaybackAttributes getCurrentPlaybackAttributes();

    long getDurationMillis();

    @NonNull
    PlaybackAttributes getDynamicBestAvailablePlaybackAttributes();

    float getMaxVolume();

    String getName();

    float getNormalVolume();

    int getNumPlayRequests();

    PlayRequest getPlayRequest(int i);

    PlayStatus getPlayStatus();

    float getPlaybackSpeed();

    @Nullable
    PlayerCapabilities getPlayerCapabilities();

    long getPositionMillis();

    List<PlayRequest> getQueue();

    VolumeProviderCompat getVolumeProvider();

    boolean isStreaming();

    void modifyQueue(int i, int i2, List<PlayRequest> list, ChangeReason changeReason, ControlSource controlSource, long j);

    void normalSpeed();

    void pause(ChangeReason changeReason, ControlSource controlSource);

    void play(ChangeReason changeReason, ControlSource controlSource, long j);

    void rewind(ControlSource controlSource);

    void seek(long j, ChangeReason changeReason, ControlSource controlSource);

    void setIndex(int i, boolean z, ChangeReason changeReason, ControlSource controlSource, long j);

    void setLoopMediaItem(boolean z);

    void setNormalVolume(float f);

    void setOnFrameChangedListener(OnFrameChangedListener onFrameChangedListener);

    void setOnPrebufferChangedListener(OnPrebufferChangedListener onPrebufferChangedListener);

    void setPlaybackMetricsListener(OnPlaybackMetricsListener onPlaybackMetricsListener);

    void setPlaybackSpeed(double d);

    void setQueue(List<PlayRequest> list, int i, long j, boolean z, ChangeReason changeReason, ControlSource controlSource, long j2);

    public static class State {
        private int curIndex;
        private ChangeReason expectedStartReason;
        private ControlSource expectedStartSource;
        private ControlSource lastSource;
        private boolean loopMediaItem;
        private final List<PlayRequest> playRequests = new ArrayList();
        private PlayStatus playStatus = PlayStatus.USER_PAUSED;
        private long expectedStartTime = Long.MIN_VALUE;

        public List<PlayRequest> getPlayRequests() {
            return this.playRequests;
        }

        public int getCurIndex() {
            return this.curIndex;
        }

        public void setCurIndex(int i) {
            this.curIndex = i;
        }

        public PlayStatus getPlayStatus() {
            return this.playStatus;
        }

        public void setPlayStatus(PlayStatus playStatus) {
            this.playStatus = playStatus;
        }

        public void setLastSource(ControlSource controlSource) {
            this.lastSource = controlSource;
        }

        public void setLoopMediaItem(boolean z) {
            this.loopMediaItem = z;
        }

        public boolean isLoopMediaItem() {
            return this.loopMediaItem;
        }

        public void expectPlayback(ChangeReason changeReason, ControlSource controlSource, long j) {
            this.expectedStartReason = changeReason;
            this.expectedStartSource = controlSource;
            this.expectedStartTime = j;
        }

        public void expectNoPlayback() {
            this.expectedStartTime = Long.MIN_VALUE;
            this.expectedStartReason = null;
            this.expectedStartSource = null;
        }

        public long getExpectedStartTime() {
            return this.expectedStartTime;
        }

        public ChangeReason getExpectedStartReason() {
            return this.expectedStartReason;
        }

        public ControlSource getExpectedStartSource() {
            return this.expectedStartSource;
        }

        public String toString() {
            return "State{playRequests=" + StringUtils.toString(this.playRequests) + ", curIndex=" + this.curIndex + ", playStatus=" + this.playStatus + ", lastSource=" + this.lastSource + ", loopMediaItem=" + this.loopMediaItem + '}';
        }
    }
}
