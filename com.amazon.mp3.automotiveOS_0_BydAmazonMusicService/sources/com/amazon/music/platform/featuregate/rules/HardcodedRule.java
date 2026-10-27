package com.amazon.music.platform.featuregate.rules;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;

/* JADX INFO: compiled from: HardcodedRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/amazon/music/platform/featuregate/rules/HardcodedRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "", "isSatisfied", "value", "Z", "getValue", "()Z", "<init>", "(Z)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class HardcodedRule implements Rule {
    private final boolean value;

    public HardcodedRule(boolean z) {
        this.value = z;
        PlatformProviderUtilKt.freeze(this);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied, reason: from getter */
    public boolean getValue() {
        return this.value;
    }
}
