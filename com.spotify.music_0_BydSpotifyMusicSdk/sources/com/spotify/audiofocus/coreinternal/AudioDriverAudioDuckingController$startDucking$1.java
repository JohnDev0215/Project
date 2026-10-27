package com.spotify.audiofocus.coreinternal;

import com.spotify.playbacknative.AudioDriver;
import kotlin.jvm.internal.Lambda;
import p.lq2;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
final class AudioDriverAudioDuckingController$startDucking$1 extends Lambda implements ry0 {
    public static final AudioDriverAudioDuckingController$startDucking$1 c = new AudioDriverAudioDuckingController$startDucking$1();

    public AudioDriverAudioDuckingController$startDucking$1() {
        super(1);
    }

    @Override // p.ry0
    public final Object invoke(Object obj) {
        AudioDriver.startDuckingAudioSession(((Number) obj).intValue());
        return lq2.a;
    }
}
