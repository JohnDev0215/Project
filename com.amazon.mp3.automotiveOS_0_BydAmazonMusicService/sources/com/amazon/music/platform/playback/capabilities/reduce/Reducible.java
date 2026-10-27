package com.amazon.music.platform.playback.capabilities.reduce;

import com.amazon.music.platform.Outcome;
import kotlin.Metadata;

/* JADX INFO: compiled from: Reducible.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J*\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H&¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/playback/capabilities/reduce/Reducible;", "T", "", "reduce", "Lcom/amazon/music/platform/Outcome;", "outcome1", "outcome2", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface Reducible<T> {
    Outcome<T> reduce(Outcome<? extends T> outcome1, Outcome<? extends T> outcome2);
}
