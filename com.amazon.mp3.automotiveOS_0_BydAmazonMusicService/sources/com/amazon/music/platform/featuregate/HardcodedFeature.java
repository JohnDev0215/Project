package com.amazon.music.platform.featuregate;

import com.amazon.music.platform.featuregate.rules.HardcodedRule;
import kotlin.Metadata;

/* JADX INFO: compiled from: HardcodedFeature.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/amazon/music/platform/featuregate/HardcodedFeature;", "Lcom/amazon/music/platform/featuregate/Feature;", "value", "", "(Z)V", "getRule", "Lcom/amazon/music/platform/featuregate/Rule;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class HardcodedFeature implements Feature {
    private final boolean value;

    public HardcodedFeature(boolean z) {
        this.value = z;
    }

    @Override // com.amazon.music.platform.featuregate.Feature
    public Rule getRule() {
        return new HardcodedRule(this.value);
    }
}
