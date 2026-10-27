package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import java.lang.reflect.Type;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.ReflectJvmMapping;

/* JADX INFO: compiled from: KotlinValueInstantiator.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0002\u0010\nJ/\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016¢\u0006\u0002\u0010\u0014J\f\u0010\u0015\u001a\u00020\u0006*\u00020\u0011H\u0002J\f\u0010\u0016\u001a\u00020\u0006*\u00020\u0017H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinValueInstantiator;", "Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;", "src", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "strictNullChecks", "(Lcom/fasterxml/jackson/databind/deser/std/StdValueInstantiator;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZZ)V", "createFromObjectWith", "", "ctxt", "Lcom/fasterxml/jackson/databind/DeserializationContext;", "props", "", "Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;", "buffer", "Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;", "(Lcom/fasterxml/jackson/databind/DeserializationContext;[Lcom/fasterxml/jackson/databind/deser/SettableBeanProperty;Lcom/fasterxml/jackson/databind/deser/impl/PropertyValueBuffer;)Ljava/lang/Object;", "hasInjectableValueId", "isPrimitive", "Lkotlin/reflect/KParameter;", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class KotlinValueInstantiator extends StdValueInstantiator {
    private final ReflectionCache cache;
    private final boolean nullIsSameAsDefault;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;
    private final boolean strictNullChecks;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KotlinValueInstantiator(StdValueInstantiator src, ReflectionCache cache, boolean z, boolean z2, boolean z3, boolean z4) {
        super(src);
        Intrinsics.checkNotNullParameter(src, "src");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.cache = cache;
        this.nullToEmptyCollection = z;
        this.nullToEmptyMap = z2;
        this.nullIsSameAsDefault = z3;
        this.strictNullChecks = z4;
    }

    /* JADX WARN: Removed duplicated region for block: B:133:0x021a  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02b7  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0306  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x030f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0189  */
    @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object createFromObjectWith(com.fasterxml.jackson.databind.DeserializationContext r23, com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r24, com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer r25) throws java.lang.IllegalAccessException, com.fasterxml.jackson.databind.JsonMappingException, java.lang.reflect.InvocationTargetException {
        /*
            Method dump skipped, instruction units count: 1099
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinValueInstantiator.createFromObjectWith(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.deser.SettableBeanProperty[], com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer):java.lang.Object");
    }

    private final boolean isPrimitive(KParameter kParameter) {
        Type javaType = ReflectJvmMapping.getJavaType(kParameter.getType());
        if (javaType instanceof Class) {
            return ((Class) javaType).isPrimitive();
        }
        return false;
    }

    private final boolean hasInjectableValueId(SettableBeanProperty settableBeanProperty) {
        return settableBeanProperty.getInjectableValueId() != null;
    }
}
