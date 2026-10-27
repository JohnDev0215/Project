package com.amazon.mp3.dagger;

import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.starlight.subcomponent.NowPlayingComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DaggerSubcomponents.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/dagger/DaggerSubcomponents;", "", "()V", "nowPlayingComponent", "Lcom/amazon/mp3/starlight/subcomponent/NowPlayingComponent;", "getNowPlayingComponent", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DaggerSubcomponents {
    public static final DaggerSubcomponents INSTANCE = new DaggerSubcomponents();
    private static NowPlayingComponent nowPlayingComponent;

    private DaggerSubcomponents() {
    }

    public final synchronized NowPlayingComponent getNowPlayingComponent() {
        NowPlayingComponent nowPlayingComponent2;
        if (nowPlayingComponent == null) {
            nowPlayingComponent = AmazonApplication.getApplication().getApplicationComponent().nowPlayingComponent().create();
        }
        nowPlayingComponent2 = nowPlayingComponent;
        Intrinsics.checkNotNull(nowPlayingComponent2);
        return nowPlayingComponent2;
    }
}
