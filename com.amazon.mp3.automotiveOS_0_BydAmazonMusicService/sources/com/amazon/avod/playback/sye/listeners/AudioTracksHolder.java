package com.amazon.avod.playback.sye.listeners;

import com.amazon.avod.util.DLog;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.audio.ISyeAudioStreamInfo;
import com.netinsight.sye.syeClient.audio.ISyeAudioTrack;
import com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class AudioTracksHolder implements IAudioTrackListener {
    private ISyeAudioStreamInfo mCurrentAudioStreamInfo;
    private final Object mMutex = new Object();
    private Map<String, ISyeAudioTrack> mLanguageAudioTrackMap = Collections.emptyMap();

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAvailableAudioTracks(List<ISyeAudioTrack> list) {
        Preconditions.checkNotNull(list, "list of ISyeAudioTrack cannot be null");
        if (list.isEmpty()) {
            return;
        }
        synchronized (this.mMutex) {
            this.mLanguageAudioTrackMap = new HashMap(list.size());
            for (ISyeAudioTrack iSyeAudioTrack : list) {
                DLog.logf("Sye AudioTrack, codec %s, language %s:%s, channels %s", iSyeAudioTrack.getAudioCodec(), iSyeAudioTrack.getLanguage(), iSyeAudioTrack.getLanguageDescription(), Integer.valueOf(iSyeAudioTrack.getNumChannels()));
                this.mLanguageAudioTrackMap.put(iSyeAudioTrack.getLanguage(), iSyeAudioTrack);
            }
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IAudioTrackListener
    public void onAudioStreamChange(ISyeAudioStreamInfo iSyeAudioStreamInfo) {
        synchronized (this.mMutex) {
            this.mCurrentAudioStreamInfo = iSyeAudioStreamInfo;
        }
    }

    @Nonnull
    public Set<String> getAudioLanguages() {
        Set<String> set;
        synchronized (this.mMutex) {
            set = (Set) MoreObjects.firstNonNull(this.mLanguageAudioTrackMap.keySet(), Collections.emptySet());
        }
        return set;
    }

    @Nullable
    public String getCurrentAudioLanguage() {
        String language;
        synchronized (this.mMutex) {
            ISyeAudioStreamInfo iSyeAudioStreamInfo = this.mCurrentAudioStreamInfo;
            language = iSyeAudioStreamInfo != null ? iSyeAudioStreamInfo.getLanguage() : null;
        }
        return language;
    }
}
