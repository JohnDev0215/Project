package com.amazon.music.featuregating.rules;

import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: WeblabRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\b\u0016\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B#\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bB\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000b\u001a\u00020\u0007¢\u0006\u0002\u0010\fB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0016\u0010\r\u001a\u0012\u0012\b\u0012\u00060\u0003j\u0002`\u000e\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\u000fJ\b\u0010\u0015\u001a\u00020\u0007H\u0016R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R$\u0010\r\u001a\u0012\u0012\b\u0012\u00060\u0003j\u0002`\u000e\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0017"}, d2 = {"Lcom/amazon/music/featuregating/rules/WeblabRule;", "Lcom/amazon/music/platform/featuregate/Rule;", "weblabName", "", "evaluateTreatment", "Lkotlin/Function1;", "Lcom/amazon/music/platform/data/WeblabTreatment;", "", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;)V", "enabledTreatment", "(Ljava/lang/String;Lcom/amazon/music/platform/data/WeblabTreatment;)V", "shouldTrigger", "(Ljava/lang/String;Ljava/lang/String;Z)V", "treatmentResolver", "Lcom/amazon/music/featuregating/rules/WeblabName;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "getEvaluateTreatment", "()Lkotlin/jvm/functions/Function1;", "getTreatmentResolver", "getWeblabName", "()Ljava/lang/String;", "isSatisfied", "Companion", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public class WeblabRule implements Rule {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Function1<WeblabTreatment, Boolean> evaluateTreatment;
    private final Function1<String, WeblabTreatment> treatmentResolver;
    private final String weblabName;

    /* JADX WARN: Multi-variable type inference failed */
    public WeblabRule(String weblabName, Function1<? super String, ? extends WeblabTreatment> treatmentResolver, Function1<? super WeblabTreatment, Boolean> evaluateTreatment) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(treatmentResolver, "treatmentResolver");
        Intrinsics.checkNotNullParameter(evaluateTreatment, "evaluateTreatment");
        this.weblabName = weblabName;
        this.treatmentResolver = treatmentResolver;
        this.evaluateTreatment = evaluateTreatment;
    }

    public String getWeblabName() {
        return this.weblabName;
    }

    public Function1<String, WeblabTreatment> getTreatmentResolver() {
        return this.treatmentResolver;
    }

    public Function1<WeblabTreatment, Boolean> getEvaluateTreatment() {
        return this.evaluateTreatment;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public WeblabRule(String weblabName, Function1<? super WeblabTreatment, Boolean> evaluateTreatment) {
        this(weblabName, (Function1<? super String, ? extends WeblabTreatment>) Companion.getDefaultTreatementResolver$default(INSTANCE, false, 1, null), evaluateTreatment);
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(evaluateTreatment, "evaluateTreatment");
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public WeblabRule(String weblabName, WeblabTreatment enabledTreatment) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(enabledTreatment, "enabledTreatment");
        Companion companion = INSTANCE;
        this(weblabName, (Function1<? super String, ? extends WeblabTreatment>) Companion.getDefaultTreatementResolver$default(companion, false, 1, null), companion.getDefaultEvaluateTreatment(enabledTreatment));
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public WeblabRule(String weblabName, String enabledTreatment, boolean z) {
        Intrinsics.checkNotNullParameter(weblabName, "weblabName");
        Intrinsics.checkNotNullParameter(enabledTreatment, "enabledTreatment");
        Companion companion = INSTANCE;
        this(weblabName, companion.getDefaultTreatementResolver(z), companion.getDefaultEvaluateTreatment(WeblabTreatment.INSTANCE.from(enabledTreatment)));
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        return getEvaluateTreatment().invoke(getTreatmentResolver().invoke(getWeblabName())).booleanValue();
    }

    /* JADX INFO: compiled from: WeblabRule.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00042\u0006\u0010\u0007\u001a\u00020\u0005J \u0010\b\u001a\u0012\u0012\b\u0012\u00060\tj\u0002`\n\u0012\u0004\u0012\u00020\u00050\u00042\b\b\u0002\u0010\u000b\u001a\u00020\u0006¨\u0006\f"}, d2 = {"Lcom/amazon/music/featuregating/rules/WeblabRule$Companion;", "", "()V", "getDefaultEvaluateTreatment", "Lkotlin/Function1;", "Lcom/amazon/music/platform/data/WeblabTreatment;", "", "enabledTreatment", "getDefaultTreatementResolver", "", "Lcom/amazon/music/featuregating/rules/WeblabName;", "shouldTrigger", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ Function1 getDefaultTreatementResolver$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = true;
            }
            return companion.getDefaultTreatementResolver(z);
        }

        public final Function1<String, WeblabTreatment> getDefaultTreatementResolver(final boolean shouldTrigger) {
            return new Function1<String, WeblabTreatment>() { // from class: com.amazon.music.featuregating.rules.WeblabRule$Companion$getDefaultTreatementResolver$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final WeblabTreatment invoke(String it) {
                    WeblabTreatment treatment;
                    Intrinsics.checkNotNullParameter(it, "it");
                    AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
                    String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
                    Intrinsics.checkNotNull(originalClassName);
                    InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
                    if (!(interfaceProvider instanceof WeblabProvider)) {
                        interfaceProvider = null;
                    }
                    WeblabProvider weblabProvider = (WeblabProvider) interfaceProvider;
                    return (weblabProvider == null || (treatment = weblabProvider.getTreatment(it, shouldTrigger)) == null) ? WeblabTreatment.C : treatment;
                }
            };
        }

        public final Function1<WeblabTreatment, Boolean> getDefaultEvaluateTreatment(final WeblabTreatment enabledTreatment) {
            Intrinsics.checkNotNullParameter(enabledTreatment, "enabledTreatment");
            return new Function1<WeblabTreatment, Boolean>() { // from class: com.amazon.music.featuregating.rules.WeblabRule$Companion$getDefaultEvaluateTreatment$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(WeblabTreatment treatment) {
                    Intrinsics.checkNotNullParameter(treatment, "treatment");
                    return Boolean.valueOf(treatment == enabledTreatment);
                }
            };
        }
    }
}
