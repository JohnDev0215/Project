package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import android.view.WindowMetrics;
import androidx.window.core.PredicateAdapter;
import androidx.window.extensions.embedding.ActivityRule;
import androidx.window.extensions.embedding.SplitPairRule;
import androidx.window.extensions.embedding.SplitPlaceholderRule;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: EmbeddingAdapter.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\b0\tJ\u001a\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bJ\u0016\u0010\u000f\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0003J\u0016\u0010\u0012\u001a\u00020\u00012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u000bH\u0003J\u0016\u0010\u0013\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000bH\u0003J\u001c\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0002J\u0016\u0010\u001c\u001a\u00020\u00012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u000bH\u0003J\u0010\u0010\u001d\u001a\u00020\u00012\u0006\u0010\u001e\u001a\u00020\u001fH\u0003J\u001c\u0010 \u001a\u00020!2\u0006\u0010\u0018\u001a\u00020\"2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0003J\u001c\u0010#\u001a\u00020$2\u0006\u0010\u0018\u001a\u00020%2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH\u0003R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Landroidx/window/embedding/EmbeddingAdapter;", "", "predicateAdapter", "Landroidx/window/core/PredicateAdapter;", "(Landroidx/window/core/PredicateAdapter;)V", "translate", "Landroidx/window/embedding/SplitInfo;", "splitInfo", "Landroidx/window/extensions/embedding/SplitInfo;", "", "splitInfoList", "", "Landroidx/window/extensions/embedding/EmbeddingRule;", "rules", "Landroidx/window/embedding/EmbeddingRule;", "translateActivityIntentPredicates", "splitPairFilters", "Landroidx/window/embedding/SplitPairFilter;", "translateActivityPairPredicates", "translateActivityPredicates", "activityFilters", "Landroidx/window/embedding/ActivityFilter;", "translateActivityRule", "Landroidx/window/extensions/embedding/ActivityRule;", "rule", "Landroidx/window/embedding/ActivityRule;", "predicateClass", "Ljava/lang/Class;", "translateIntentPredicates", "translateParentMetricsPredicate", "splitRule", "Landroidx/window/embedding/SplitRule;", "translateSplitPairRule", "Landroidx/window/extensions/embedding/SplitPairRule;", "Landroidx/window/embedding/SplitPairRule;", "translateSplitPlaceholderRule", "Landroidx/window/extensions/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitPlaceholderRule;", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class EmbeddingAdapter {
    private final PredicateAdapter predicateAdapter;

    public EmbeddingAdapter(PredicateAdapter predicateAdapter) {
        Intrinsics.checkNotNullParameter(predicateAdapter, "predicateAdapter");
        this.predicateAdapter = predicateAdapter;
    }

    public final List<SplitInfo> translate(List<? extends androidx.window.extensions.embedding.SplitInfo> splitInfoList) {
        Intrinsics.checkNotNullParameter(splitInfoList, "splitInfoList");
        List<? extends androidx.window.extensions.embedding.SplitInfo> list = splitInfoList;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(translate((androidx.window.extensions.embedding.SplitInfo) it.next()));
        }
        return arrayList;
    }

    private final SplitInfo translate(androidx.window.extensions.embedding.SplitInfo splitInfo) {
        boolean zIsEmpty;
        androidx.window.extensions.embedding.ActivityStack primaryActivityStack = splitInfo.getPrimaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(primaryActivityStack, "splitInfo.primaryActivityStack");
        boolean zIsEmpty2 = false;
        try {
            zIsEmpty = primaryActivityStack.isEmpty();
        } catch (NoSuchMethodError unused) {
            zIsEmpty = false;
        }
        List activities = primaryActivityStack.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities, "primaryActivityStack.activities");
        ActivityStack activityStack = new ActivityStack(activities, zIsEmpty);
        androidx.window.extensions.embedding.ActivityStack secondaryActivityStack = splitInfo.getSecondaryActivityStack();
        Intrinsics.checkNotNullExpressionValue(secondaryActivityStack, "splitInfo.secondaryActivityStack");
        try {
            zIsEmpty2 = secondaryActivityStack.isEmpty();
        } catch (NoSuchMethodError unused2) {
        }
        List activities2 = secondaryActivityStack.getActivities();
        Intrinsics.checkNotNullExpressionValue(activities2, "secondaryActivityStack.activities");
        return new SplitInfo(activityStack, new ActivityStack(activities2, zIsEmpty2), splitInfo.getSplitRatio());
    }

    private final Object translateActivityPairPredicates(final Set<SplitPairFilter> splitPairFilters) {
        return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Activity.class), new Function2<Activity, Activity, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter.translateActivityPairPredicates.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(Activity first, Activity second) {
                Intrinsics.checkNotNullParameter(first, "first");
                Intrinsics.checkNotNullParameter(second, "second");
                Set<SplitPairFilter> set = splitPairFilters;
                boolean z = false;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    Iterator<T> it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (((SplitPairFilter) it.next()).matchesActivityPair(first, second)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Object translateActivityIntentPredicates(final Set<SplitPairFilter> splitPairFilters) {
        return this.predicateAdapter.buildPairPredicate(Reflection.getOrCreateKotlinClass(Activity.class), Reflection.getOrCreateKotlinClass(Intent.class), new Function2<Activity, Intent, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter.translateActivityIntentPredicates.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(Activity first, Intent second) {
                Intrinsics.checkNotNullParameter(first, "first");
                Intrinsics.checkNotNullParameter(second, "second");
                Set<SplitPairFilter> set = splitPairFilters;
                boolean z = false;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    Iterator<T> it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (((SplitPairFilter) it.next()).matchesActivityIntentPair(first, second)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Object translateParentMetricsPredicate(final SplitRule splitRule) {
        return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(WindowMetrics.class), new Function1<WindowMetrics, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter.translateParentMetricsPredicate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(WindowMetrics windowMetrics) {
                Intrinsics.checkNotNullParameter(windowMetrics, "windowMetrics");
                return Boolean.valueOf(splitRule.checkParentMetrics(windowMetrics));
            }
        });
    }

    private final Object translateActivityPredicates(final Set<ActivityFilter> activityFilters) {
        return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Activity.class), new Function1<Activity, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter.translateActivityPredicates.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Activity activity) {
                Intrinsics.checkNotNullParameter(activity, "activity");
                Set<ActivityFilter> set = activityFilters;
                boolean z = false;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    Iterator<T> it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (((ActivityFilter) it.next()).matchesActivity(activity)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final Object translateIntentPredicates(final Set<ActivityFilter> activityFilters) {
        return this.predicateAdapter.buildPredicate(Reflection.getOrCreateKotlinClass(Intent.class), new Function1<Intent, Boolean>() { // from class: androidx.window.embedding.EmbeddingAdapter.translateIntentPredicates.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Intent intent) {
                Intrinsics.checkNotNullParameter(intent, "intent");
                Set<ActivityFilter> set = activityFilters;
                boolean z = false;
                if (!(set instanceof Collection) || !set.isEmpty()) {
                    Iterator<T> it = set.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            break;
                        }
                        if (((ActivityFilter) it.next()).matchesIntent(intent)) {
                            z = true;
                            break;
                        }
                    }
                }
                return Boolean.valueOf(z);
            }
        });
    }

    private final androidx.window.extensions.embedding.SplitPairRule translateSplitPairRule(SplitPairRule rule, Class<?> predicateClass) {
        SplitPairRule.Builder finishSecondaryWithPrimary = ((SplitPairRule.Builder) SplitPairRule.Builder.class.getConstructor(predicateClass, predicateClass, predicateClass).newInstance(translateActivityPairPredicates(rule.getFilters()), translateActivityIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(rule))).setSplitRatio(rule.getSplitRatio()).setLayoutDirection(rule.getLayoutDirection()).setShouldClearTop(rule.getClearTop()).setFinishPrimaryWithSecondary(rule.getFinishPrimaryWithSecondary()).setFinishSecondaryWithPrimary(rule.getFinishSecondaryWithPrimary());
        Intrinsics.checkNotNullExpressionValue(finishSecondaryWithPrimary, "SplitPairRuleBuilder::cl…nishSecondaryWithPrimary)");
        androidx.window.extensions.embedding.SplitPairRule splitPairRuleBuild = finishSecondaryWithPrimary.build();
        Intrinsics.checkNotNullExpressionValue(splitPairRuleBuild, "builder.build()");
        return splitPairRuleBuild;
    }

    private final androidx.window.extensions.embedding.SplitPlaceholderRule translateSplitPlaceholderRule(SplitPlaceholderRule rule, Class<?> predicateClass) {
        SplitPlaceholderRule.Builder finishPrimaryWithSecondary = ((SplitPlaceholderRule.Builder) SplitPlaceholderRule.Builder.class.getConstructor(Intent.class, predicateClass, predicateClass, predicateClass).newInstance(rule.getPlaceholderIntent(), translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()), translateParentMetricsPredicate(rule))).setSplitRatio(rule.getSplitRatio()).setLayoutDirection(rule.getLayoutDirection()).setSticky(rule.getIsSticky()).setFinishPrimaryWithSecondary(rule.getFinishPrimaryWithSecondary());
        Intrinsics.checkNotNullExpressionValue(finishPrimaryWithSecondary, "SplitPlaceholderRuleBuil…nishPrimaryWithSecondary)");
        androidx.window.extensions.embedding.SplitPlaceholderRule splitPlaceholderRuleBuild = finishPrimaryWithSecondary.build();
        Intrinsics.checkNotNullExpressionValue(splitPlaceholderRuleBuild, "builder.build()");
        return splitPlaceholderRuleBuild;
    }

    private final androidx.window.extensions.embedding.ActivityRule translateActivityRule(ActivityRule rule, Class<?> predicateClass) {
        androidx.window.extensions.embedding.ActivityRule activityRuleBuild = ((ActivityRule.Builder) ActivityRule.Builder.class.getConstructor(predicateClass, predicateClass).newInstance(translateActivityPredicates(rule.getFilters()), translateIntentPredicates(rule.getFilters()))).setShouldAlwaysExpand(rule.getAlwaysExpand()).build();
        Intrinsics.checkNotNullExpressionValue(activityRuleBuild, "ActivityRuleBuilder::cla…and)\n            .build()");
        return activityRuleBuild;
    }

    public final Set<androidx.window.extensions.embedding.EmbeddingRule> translate(Set<? extends EmbeddingRule> rules) {
        androidx.window.extensions.embedding.SplitPairRule splitPairRuleTranslateActivityRule;
        Intrinsics.checkNotNullParameter(rules, "rules");
        Class<?> clsPredicateClassOrNull$window_release = this.predicateAdapter.predicateClassOrNull$window_release();
        if (clsPredicateClassOrNull$window_release == null) {
            return SetsKt.emptySet();
        }
        Set<? extends EmbeddingRule> set = rules;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        for (EmbeddingRule embeddingRule : set) {
            if (embeddingRule instanceof SplitPairRule) {
                splitPairRuleTranslateActivityRule = translateSplitPairRule((SplitPairRule) embeddingRule, clsPredicateClassOrNull$window_release);
            } else if (embeddingRule instanceof SplitPlaceholderRule) {
                splitPairRuleTranslateActivityRule = translateSplitPlaceholderRule((SplitPlaceholderRule) embeddingRule, clsPredicateClassOrNull$window_release);
            } else {
                if (!(embeddingRule instanceof ActivityRule)) {
                    throw new IllegalArgumentException("Unsupported rule type");
                }
                splitPairRuleTranslateActivityRule = translateActivityRule((ActivityRule) embeddingRule, clsPredicateClassOrNull$window_release);
            }
            arrayList.add((androidx.window.extensions.embedding.EmbeddingRule) splitPairRuleTranslateActivityRule);
        }
        return CollectionsKt.toSet(arrayList);
    }
}
