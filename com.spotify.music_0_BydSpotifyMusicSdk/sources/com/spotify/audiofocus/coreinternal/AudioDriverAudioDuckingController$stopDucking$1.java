package com.spotify.audiofocus.coreinternal;

import com.spotify.playbacknative.AudioDriver;
import kotlin.jvm.internal.Lambda;
import p.lq2;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
final class AudioDriverAudioDuckingController$stopDucking$1 extends Lambda implements ry0 {
    public static final AudioDriverAudioDuckingController$stopDucking$1 c = new AudioDriverAudioDuckingController$stopDucking$1();

    public AudioDriverAudioDuckingController$stopDucking$1() {
        super(1);
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        AudioDriver.stopDuckingAudioSession(((Number) obj).intValue());
        return lq2.a;
    }
}
