package com.amazon.music.platform.featuregate.rules;

import com.amazon.music.platform.PlatformProviderUtilKt;
import com.amazon.music.platform.featuregate.Rule;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AndRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\b\b\u0002\u0010\t\u001a\u00020\u0002¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u001d\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/platform/featuregate/rules/AndRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "", "isSatisfied", "", "rules", "Ljava/util/List;", "getRules", "()Ljava/util/List;", "condition", "Z", "getCondition", "()Z", "<init>", "(Ljava/util/List;Z)V", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public final class AndRule implements Rule {
    private final boolean condition;
    private final List<Rule> rules;

    /* JADX WARN: Multi-variable type inference failed */
    public AndRule(List<? extends Rule> rules, boolean z) {
        Intrinsics.checkNotNullParameter(rules, "rules");
        this.rules = rules;
        this.condition = z;
        PlatformProviderUtilKt.freeze(this);
    }

    public /* synthetic */ AndRule(List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, (i & 2) != 0 ? true : z);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        boolean z;
        List<Rule> list = this.rules;
        if ((list instanceof Collection) && list.isEmpty()) {
            z = true;
        } else {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (!((Rule) it.next()).getValue()) {
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z == this.condition;
    }
}
