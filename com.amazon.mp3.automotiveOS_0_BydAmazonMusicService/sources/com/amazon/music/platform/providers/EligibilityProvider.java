package com.amazon.music.platform.providers;

import kotlin.Metadata;

/* JADX INFO: compiled from: EligibilityProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J\u000f\u0010\u0003\u001a\u0004\u0018\u00018\u0000H&¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/platform/providers/EligibilityProvider;", "T", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "getEligibility", "()Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface EligibilityProvider<T> extends InterfaceProvider {
    T getEligibility();
}
