package com.amazon.music.featuregating.rules;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.featuregating.configuration.Configuration;
import com.amazon.music.platform.featuregate.Rule;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ConfigurationProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: ConfigurationRule.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003BK\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0015\u0012\u0014\u0010\u001b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u00040\u001a¢\u0006\u0004\b\u001f\u0010 BE\b\u0016\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0010\u0012\u000e\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0015\u0012\u0014\u0010\u001b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u00040\u001a¢\u0006\u0004\b\u001f\u0010!J\b\u0010\u0005\u001a\u00020\u0004H\u0016R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001a\u0010\f\u001a\u00020\u000b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u001f\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R%\u0010\u001b\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020\u00040\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e¨\u0006\""}, d2 = {"Lcom/amazon/music/featuregating/rules/ConfigurationRule;", "", "T", "Lcom/amazon/music/platform/featuregate/Rule;", "", "isSatisfied", "Lcom/amazon/music/featuregating/configuration/Configuration;", "configuration", "Lcom/amazon/music/featuregating/configuration/Configuration;", "getConfiguration", "()Lcom/amazon/music/featuregating/configuration/Configuration;", "", "key", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlin/reflect/KClass;", "getType", "()Lkotlin/reflect/KClass;", "Lkotlinx/serialization/KSerializer;", "serializer", "Lkotlinx/serialization/KSerializer;", "getSerializer", "()Lkotlinx/serialization/KSerializer;", "Lkotlin/Function1;", "evaluate", "Lkotlin/jvm/functions/Function1;", "getEvaluate", "()Lkotlin/jvm/functions/Function1;", "<init>", "(Lcom/amazon/music/featuregating/configuration/Configuration;Ljava/lang/String;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/String;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;Lkotlin/jvm/functions/Function1;)V", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public class ConfigurationRule<T> implements Rule {
    private final Configuration configuration;
    private final Function1<T, Boolean> evaluate;
    private final String key;
    private final KSerializer<T> serializer;
    private final KClass<T> type;

    /* JADX WARN: Multi-variable type inference failed */
    public ConfigurationRule(Configuration configuration, String key, KClass<T> type, KSerializer<T> kSerializer, Function1<? super T, Boolean> evaluate) {
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(evaluate, "evaluate");
        this.configuration = configuration;
        this.key = key;
        this.type = type;
        this.serializer = kSerializer;
        this.evaluate = evaluate;
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    public String getKey() {
        return this.key;
    }

    public final KClass<T> getType() {
        return this.type;
    }

    public final Function1<T, Boolean> getEvaluate() {
        return this.evaluate;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ConfigurationRule(String key, KClass<T> type, KSerializer<T> kSerializer, Function1<? super T, Boolean> evaluate) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(type, "type");
        Intrinsics.checkNotNullParameter(evaluate, "evaluate");
        InterfaceProvider interfaceProvider = Providers.INSTANCE.get(Reflection.getOrCreateKotlinClass(ConfigurationProvider.class));
        Intrinsics.checkNotNull(interfaceProvider, "null cannot be cast to non-null type com.amazon.music.featuregating.configuration.Configuration");
        this((Configuration) interfaceProvider, key, type, kSerializer, evaluate);
    }

    @Override // com.amazon.music.platform.featuregate.Rule
    /* JADX INFO: renamed from: isSatisfied */
    public boolean getValue() {
        return this.evaluate.invoke((T) getConfiguration().value(getKey(), (KClass) this.type, (KSerializer) this.serializer)).booleanValue();
    }
}
