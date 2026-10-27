package com.amazon.music.platform.featuregate.rules;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FailableRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\b\b\u0002\u0010\b\u001a\u00020\u0002\u0012\b\b\u0002\u0010\f\u001a\u00020\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0004\u001a\u00020\u00018\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\f\u0010\t\u001a\u0004\b\r\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/platform/featuregate/rules/FailableRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "", "isSatisfied", "rule", "Lcom/amazon/music/platform/featuregate/Rule;", "getRule", "()Lcom/amazon/music/platform/featuregate/Rule;", "condition", "Z", "getCondition", "()Z", "defaultValue", "getDefaultValue", "<init>", "(Lcom/amazon/music/platform/featuregate/Rule;ZZ)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class FailableRule implements Rule {
    private final boolean condition;
    private final boolean defaultValue;
    private final Rule rule;

    public FailableRule(Rule rule, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(rule, "rule");
        this.rule = rule;
        this.condition = z;
        this.defaultValue = z2;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ FailableRule(Rule rule, boolean z, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(rule, (i & 2) != 0 ? true : z, (i & 4) != 0 ? false : z2);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        try {
            return this.rule.getValue() == this.condition;
        } catch (Exception unused) {
            return this.defaultValue;
        }
    }
}
