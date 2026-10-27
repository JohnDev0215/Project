package com.amazon.mp3.prime.requester;

import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.visualon.OSMPUtils.voMimeTypes;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AudioVideo.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\t8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/prime/requester/AudioVideo;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", voMimeTypes.VOBASE_TYPE_AUDIO, "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "getAudio", "()Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", voMimeTypes.VOBASE_TYPE_VIDEO, "getVideo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class AudioVideo {
    private final PrimeTrack audio;
    private final PrimeTrack video;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AudioVideo)) {
            return false;
        }
        AudioVideo audioVideo = (AudioVideo) other;
        return Intrinsics.areEqual(this.audio, audioVideo.audio) && Intrinsics.areEqual(this.video, audioVideo.video);
    }

    public int hashCode() {
        PrimeTrack primeTrack = this.audio;
        int iHashCode = (primeTrack == null ? 0 : primeTrack.hashCode()) * 31;
        PrimeTrack primeTrack2 = this.video;
        return iHashCode + (primeTrack2 != null ? primeTrack2.hashCode() : 0);
    }

    public String toString() {
        return "AudioVideo(audio=" + this.audio + ", video=" + this.video + ')';
    }

    public final PrimeTrack getAudio() {
        return this.audio;
    }

    public final PrimeTrack getVideo() {
        return this.video;
    }
}
