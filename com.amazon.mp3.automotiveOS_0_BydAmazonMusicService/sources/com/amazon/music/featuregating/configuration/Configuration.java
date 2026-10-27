package com.amazon.music.featuregating.configuration;

import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.music.platform.providers.ConfigurationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;

/* JADX INFO: compiled from: Configuration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001JC\u0010\n\u001a\u0004\u0018\u00018\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0010\b\u0002\u0010\t\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\bH&¢\u0006\u0004\b\n\u0010\u000bJ7\u0010\n\u001a\u00028\u0000\"\b\b\u0000\u0010\u0003*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\f\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\n\u0010\rJ\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J\u0018\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0011H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0018\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004H\u0016¨\u0006\u0015"}, d2 = {"Lcom/amazon/music/featuregating/configuration/Configuration;", "Lcom/amazon/music/platform/providers/ConfigurationProvider;", "", "T", "", "key", "Lkotlin/reflect/KClass;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Lkotlinx/serialization/KSerializer;", "serializer", "value", "(Ljava/lang/String;Lkotlin/reflect/KClass;Lkotlinx/serialization/KSerializer;)Ljava/lang/Object;", "defaultValue", "(Ljava/lang/String;Lkotlin/reflect/KClass;Ljava/lang/Object;)Ljava/lang/Object;", "", "bool", "(Ljava/lang/String;)Ljava/lang/Boolean;", "", "int", "(Ljava/lang/String;)Ljava/lang/Integer;", "string", "MPFeatureGating_release"}, k = 1, mv = {1, 7, 1})
public interface Configuration extends ConfigurationProvider {
    Boolean bool(String key);

    /* JADX INFO: renamed from: int */
    int mo2012int(String key, int defaultValue);

    /* JADX INFO: renamed from: int */
    Integer mo2013int(String key);

    String string(String key);

    String string(String key, String defaultValue);

    <T> T value(String key, KClass<T> type, T defaultValue);

    <T> T value(String key, KClass<T> type, KSerializer<T> serializer);

    /* JADX INFO: compiled from: Configuration.kt */
    @Metadata(k = 3, mv = {1, 7, 1}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ Object value$default(Configuration configuration, String str, KClass kClass, KSerializer kSerializer, int i, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: value");
            }
            if ((i & 4) != 0) {
                kSerializer = null;
            }
            return configuration.value(str, kClass, kSerializer);
        }

        public static <T> T value(Configuration configuration, String key, KClass<T> type, T defaultValue) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(type, "type");
            Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
            T t = (T) value$default(configuration, key, type, null, 4, null);
            return t == null ? defaultValue : t;
        }

        public static Boolean bool(Configuration configuration, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (Boolean) value$default(configuration, key, Reflection.getOrCreateKotlinClass(Boolean.TYPE), null, 4, null);
        }

        /* JADX INFO: renamed from: int, reason: not valid java name */
        public static Integer m2015int(Configuration configuration, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (Integer) value$default(configuration, key, Reflection.getOrCreateKotlinClass(Integer.TYPE), null, 4, null);
        }

        /* JADX INFO: renamed from: int, reason: not valid java name */
        public static int m2014int(Configuration configuration, String key, int i) {
            Intrinsics.checkNotNullParameter(key, "key");
            return ((Number) configuration.value(key, (KClass<Integer>) Reflection.getOrCreateKotlinClass(Integer.TYPE), Integer.valueOf(i))).intValue();
        }

        public static String string(Configuration configuration, String key) {
            Intrinsics.checkNotNullParameter(key, "key");
            return (String) value$default(configuration, key, Reflection.getOrCreateKotlinClass(String.class), null, 4, null);
        }

        public static String string(Configuration configuration, String key, String defaultValue) {
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(defaultValue, "defaultValue");
            return (String) configuration.value(key, (KClass<String>) Reflection.getOrCreateKotlinClass(String.class), defaultValue);
        }
    }
}
