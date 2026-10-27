package com.amazon.music.platform.featuregate.rules;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;

/* JADX INFO: compiled from: FalseRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/platform/featuregate/rules/FalseRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "()V", "isSatisfied", "", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class FalseRule implements Rule {
    @Override // com.amazon.music.platform.featuregate.Rule
    public boolean isSatisfied() {
        return false;
    }

    public FalseRule() {
        PlatformProviderUtilKt.freeze(this);
    }
}
