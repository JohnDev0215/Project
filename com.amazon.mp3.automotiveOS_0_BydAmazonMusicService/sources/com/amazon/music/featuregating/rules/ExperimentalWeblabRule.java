package com.amazon.music.featuregating.rules;

import com.amazon.music.platform.featuregate.Rule;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExperimentalWeblabRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\t\u001a\u00020\u0004¢\u0006\u0004\b\u000b\u0010\fJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\t\u0010\u0006\u001a\u0004\b\n\u0010\b¨\u0006\r"}, d2 = {"Lcom/amazon/music/featuregating/rules/ExperimentalWeblabRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "", "isSatisfied", "Lcom/amazon/music/featuregating/rules/WeblabRule;", "aaWeblabRule", "Lcom/amazon/music/featuregating/rules/WeblabRule;", "getAaWeblabRule", "()Lcom/amazon/music/featuregating/rules/WeblabRule;", "abWeblabRule", "getAbWeblabRule", "<init>", "(Lcom/amazon/music/featuregating/rules/WeblabRule;Lcom/amazon/music/featuregating/rules/WeblabRule;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public class ExperimentalWeblabRule implements Rule {
    private final WeblabRule aaWeblabRule;
    private final WeblabRule abWeblabRule;

    public ExperimentalWeblabRule(WeblabRule aaWeblabRule, WeblabRule abWeblabRule) {
        Intrinsics.checkNotNullParameter(aaWeblabRule, "aaWeblabRule");
        Intrinsics.checkNotNullParameter(abWeblabRule, "abWeblabRule");
        this.aaWeblabRule = aaWeblabRule;
        this.abWeblabRule = abWeblabRule;
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        this.aaWeblabRule.getValue();
        return this.abWeblabRule.getValue();
    }
}
