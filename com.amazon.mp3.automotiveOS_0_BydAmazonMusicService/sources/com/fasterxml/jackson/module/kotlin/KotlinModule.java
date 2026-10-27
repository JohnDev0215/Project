package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.CharRange;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.IntRange;
import kotlin.ranges.LongRange;
import kotlin.reflect.KClass;

/* JADX INFO: compiled from: KotlinModule.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \"2\u00020\u0001:\u0002!\"B%\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B/\b\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005¢\u0006\u0002\u0010\tB\u000f\b\u0012\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fBA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0005¢\u0006\u0002\u0010\u0010J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u0018\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00130\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0011\u0010\u000f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0015¨\u0006#"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "Lcom/fasterxml/jackson/databind/module/SimpleModule;", "reflectionCacheSize", "", "nullToEmptyCollection", "", "nullToEmptyMap", "(IZZ)V", "nullIsSameAsDefault", "(IZZZ)V", "builder", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "(Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;)V", "singletonSupport", "Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "strictNullChecks", "(IZZZLcom/fasterxml/jackson/module/kotlin/SingletonSupport;Z)V", "ignoredClassesForImplyingJsonCreator", "", "Lkotlin/reflect/KClass;", "getNullIsSameAsDefault", "()Z", "getNullToEmptyCollection", "getNullToEmptyMap", "getReflectionCacheSize", "()I", "getSingletonSupport", "()Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "getStrictNullChecks", "setupModule", "", "context", "Lcom/fasterxml/jackson/databind/Module$SetupContext;", "Builder", "Companion", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class KotlinModule extends SimpleModule {
    public static final long serialVersionUID = 1;
    private final Set<KClass<?>> ignoredClassesForImplyingJsonCreator;
    private final boolean nullIsSameAsDefault;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final int reflectionCacheSize;
    private final SingletonSupport singletonSupport;
    private final boolean strictNullChecks;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 4, 1})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SingletonSupport.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[SingletonSupport.DISABLED.ordinal()] = 1;
            iArr[SingletonSupport.CANONICALIZE.ordinal()] = 2;
        }
    }

    public KotlinModule() {
        this(0, false, false, false, null, false, 63, null);
    }

    public /* synthetic */ KotlinModule(Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(builder);
    }

    public final int getReflectionCacheSize() {
        return this.reflectionCacheSize;
    }

    public final boolean getNullToEmptyCollection() {
        return this.nullToEmptyCollection;
    }

    public final boolean getNullToEmptyMap() {
        return this.nullToEmptyMap;
    }

    public final boolean getNullIsSameAsDefault() {
        return this.nullIsSameAsDefault;
    }

    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2, boolean z3, SingletonSupport singletonSupport, boolean z4, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 512 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? false : z3, (i2 & 16) != 0 ? SingletonSupport.DISABLED : singletonSupport, (i2 & 32) == 0 ? z4 : false);
    }

    public final SingletonSupport getSingletonSupport() {
        return this.singletonSupport;
    }

    public final boolean getStrictNullChecks() {
        return this.strictNullChecks;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinModule(int i, boolean z, boolean z2, boolean z3, SingletonSupport singletonSupport, boolean z4) {
        super(PackageVersion.VERSION);
        Intrinsics.checkNotNullParameter(singletonSupport, "singletonSupport");
        this.reflectionCacheSize = i;
        this.nullToEmptyCollection = z;
        this.nullToEmptyMap = z2;
        this.nullIsSameAsDefault = z3;
        this.singletonSupport = singletonSupport;
        this.strictNullChecks = z4;
        this.ignoredClassesForImplyingJsonCreator = SetsKt__SetsKt.emptySet();
    }

    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 512 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "For ABI compatibility")
    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2) {
        this(i, z, z2, false, null, false, 48, null);
    }

    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2, boolean z3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 512 : i, (i2 & 2) != 0 ? false : z, (i2 & 4) != 0 ? false : z2, (i2 & 8) != 0 ? false : z3);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "For ABI compatibility")
    public /* synthetic */ KotlinModule(int i, boolean z, boolean z2, boolean z3) {
        this(i, z, z2, z3, null, false, 48, null);
    }

    private KotlinModule(Builder builder) {
        this(builder.getReflectionCacheSize(), builder.getNullToEmptyCollection(), builder.getNullToEmptyMap(), builder.getNullIsSameAsDefault(), builder.getSingletonSupport(), builder.getStrictNullChecks());
    }

    @Override // com.fasterxml.jackson.databind.module.SimpleModule, com.fasterxml.jackson.databind.Module
    public void setupModule(final Module.SetupContext context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.setupModule(context);
        if (!context.isEnabled(MapperFeature.USE_ANNOTATIONS)) {
            throw new IllegalStateException("The Jackson Kotlin module requires USE_ANNOTATIONS to be true or it cannot function");
        }
        ReflectionCache reflectionCache = new ReflectionCache(this.reflectionCacheSize);
        context.addValueInstantiators(new KotlinInstantiators(reflectionCache, this.nullToEmptyCollection, this.nullToEmptyMap, this.nullIsSameAsDefault, this.strictNullChecks));
        if (WhenMappings.$EnumSwitchMapping$0[this.singletonSupport.ordinal()] == 2) {
            context.addBeanDeserializerModifier(KotlinBeanDeserializerModifier.INSTANCE);
        }
        context.insertAnnotationIntrospector(new KotlinAnnotationIntrospector(context, reflectionCache, this.nullToEmptyCollection, this.nullToEmptyMap, this.nullIsSameAsDefault));
        context.appendAnnotationIntrospector(new KotlinNamesAnnotationIntrospector(this, reflectionCache, this.ignoredClassesForImplyingJsonCreator));
        context.addDeserializers(new KotlinDeserializers());
        context.addSerializers(new KotlinSerializers());
        Function2<Class<?>, Class<?>, Unit> function2 = new Function2<Class<?>, Class<?>, Unit>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinModule.setupModule.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(Class<?> cls, Class<?> cls2) {
                invoke2(cls, cls2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Class<?> clazz, Class<?> mixin) {
                Intrinsics.checkNotNullParameter(clazz, "clazz");
                Intrinsics.checkNotNullParameter(mixin, "mixin");
                context.setMixInAnnotations(clazz, mixin);
            }
        };
        function2.invoke2(IntRange.class, ClosedRangeMixin.class);
        function2.invoke2(CharRange.class, ClosedRangeMixin.class);
        function2.invoke2(LongRange.class, ClosedRangeMixin.class);
        function2.invoke2(ClosedRange.class, ClosedRangeMixin.class);
    }

    /* JADX INFO: compiled from: KotlinModule.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004J\u000e\u0010\n\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0004J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0004R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0007¨\u0006\u0018"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinModule$Builder;", "", "()V", "<set-?>", "", "nullIsSameAsDefault", "getNullIsSameAsDefault", "()Z", "nullToEmptyCollection", "getNullToEmptyCollection", "nullToEmptyMap", "getNullToEmptyMap", "", "reflectionCacheSize", "getReflectionCacheSize", "()I", "Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "singletonSupport", "getSingletonSupport", "()Lcom/fasterxml/jackson/module/kotlin/SingletonSupport;", "strictNullChecks", "getStrictNullChecks", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
    public static final class Builder {
        private boolean nullIsSameAsDefault;
        private boolean nullToEmptyCollection;
        private boolean nullToEmptyMap;
        private int reflectionCacheSize = 512;
        private SingletonSupport singletonSupport = SingletonSupport.DISABLED;
        private boolean strictNullChecks;

        public final int getReflectionCacheSize() {
            return this.reflectionCacheSize;
        }

        public final boolean getNullToEmptyCollection() {
            return this.nullToEmptyCollection;
        }

        public final boolean getNullToEmptyMap() {
            return this.nullToEmptyMap;
        }

        public final boolean getNullIsSameAsDefault() {
            return this.nullIsSameAsDefault;
        }

        public final SingletonSupport getSingletonSupport() {
            return this.singletonSupport;
        }

        public final boolean getStrictNullChecks() {
            return this.strictNullChecks;
        }

        public final Builder reflectionCacheSize(int reflectionCacheSize) {
            this.reflectionCacheSize = reflectionCacheSize;
            return this;
        }

        public final Builder nullToEmptyCollection(boolean nullToEmptyCollection) {
            this.nullToEmptyCollection = nullToEmptyCollection;
            return this;
        }

        public final Builder nullToEmptyMap(boolean nullToEmptyMap) {
            this.nullToEmptyMap = nullToEmptyMap;
            return this;
        }

        public final Builder nullIsSameAsDefault(boolean nullIsSameAsDefault) {
            this.nullIsSameAsDefault = nullIsSameAsDefault;
            return this;
        }

        public final Builder singletonSupport(SingletonSupport singletonSupport) {
            Intrinsics.checkNotNullParameter(singletonSupport, "singletonSupport");
            this.singletonSupport = singletonSupport;
            return this;
        }

        public final Builder strictNullChecks(boolean strictNullChecks) {
            this.strictNullChecks = strictNullChecks;
            return this;
        }

        public final KotlinModule build() {
            return new KotlinModule(this, null);
        }
    }
}
