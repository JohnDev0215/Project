package com.loostone.libtuning.channel.byd.data;

import OooO00o.OooO00o.OooO0O0.OooO0o0.OooOOO0.OooO00o;
import OooO00o.OooO00o.OooO0O0.OooO0o0.OooOOO0.a;
import kotlin.d;

/* JADX INFO: loaded from: classes.dex */
public final class BYDCache extends a {
    private static final String KARAOKE_EFFECT = "karaoke_effect";
    private static final String REVERB_VOLUME = "reverb_volume";
    private static final String TAG = "BYDCache";
    public static final BYDCache INSTANCE = new BYDCache();
    private static final kotlin.a reverbVolume$delegate = d.b(new kotlin.jvm.b.a<OooO00o<Integer>>() { // from class: com.loostone.libtuning.channel.byd.data.BYDCache$reverbVolume$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.b.a
        public final OooO00o<Integer> invoke() {
            return a.intPref$default(BYDCache.INSTANCE, "reverb_volume", 0, 2, null);
        }
    });
    private static final kotlin.a karaokeEffect$delegate = d.b(new kotlin.jvm.b.a<OooO00o<Integer>>() { // from class: com.loostone.libtuning.channel.byd.data.BYDCache$karaokeEffect$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.b.a
        public final OooO00o<Integer> invoke() {
            return a.intPref$default(BYDCache.INSTANCE, "karaoke_effect", 0, 2, null);
        }
    });

    private BYDCache() {
        super(TAG);
    }

    public final OooO00o<Integer> getKaraokeEffect() {
        return (OooO00o) karaokeEffect$delegate.getValue();
    }

    public final OooO00o<Integer> getReverbVolume() {
        return (OooO00o) reverbVolume$delegate.getValue();
    }
}
