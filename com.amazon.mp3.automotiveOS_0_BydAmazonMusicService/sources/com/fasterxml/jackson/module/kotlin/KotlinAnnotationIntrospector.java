package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KType;
import kotlin.reflect.full.KClasses;
import kotlin.reflect.full.KClassifiers;
import kotlin.reflect.jvm.ReflectJvmMapping;

/* JADX INFO: compiled from: KotlinAnnotationIntrospector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00122\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0002\u0010\u0017J#\u0010\u0018\u001a\u0004\u0018\u00010\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010\u001bJ\u001e\u0010\u001c\u001a\u0014\u0012\u0006\b\u0001\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010\u001d*\u00020\u001fH\u0002J\u001e\u0010 \u001a\u0014\u0012\u0006\b\u0001\u0012\u00020\u001e\u0012\u0006\u0012\u0004\u0018\u00010\u001e\u0018\u00010!*\u00020\u001fH\u0002J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0007*\u00020\"H\u0002¢\u0006\u0002\u0010#J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0007*\u00020\u001fH\u0002¢\u0006\u0002\u0010$J\u0013\u0010\u0014\u001a\u0004\u0018\u00010\u0007*\u00020%H\u0002¢\u0006\u0002\u0010&J\u0018\u0010'\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u00020*H\u0002J\u0010\u0010+\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030(H\u0002J\u0018\u0010,\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u00020*H\u0002J\u0018\u0010-\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030(2\u0006\u0010)\u001a\u00020*H\u0002J\f\u0010.\u001a\u00020\u0007*\u00020/H\u0002J\u0013\u00100\u001a\u0004\u0018\u00010\u0007*\u000201H\u0002¢\u0006\u0002\u00102J\u0013\u00100\u001a\u0004\u0018\u00010\u0007*\u000203H\u0002¢\u0006\u0002\u00104J\u0010\u00105\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00066"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinAnnotationIntrospector;", "Lcom/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector;", "context", "Lcom/fasterxml/jackson/databind/Module$SetupContext;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "nullToEmptyCollection", "", "nullToEmptyMap", "nullIsSameAsDefault", "(Lcom/fasterxml/jackson/databind/Module$SetupContext;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;ZZZ)V", "findCreatorAnnotation", "Lcom/fasterxml/jackson/annotation/JsonCreator$Mode;", "config", "Lcom/fasterxml/jackson/databind/cfg/MapperConfig;", "a", "Lcom/fasterxml/jackson/databind/introspect/Annotated;", "findSubtypes", "", "Lcom/fasterxml/jackson/databind/jsontype/NamedType;", "hasRequiredMarker", "m", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;)Ljava/lang/Boolean;", "requiredAnnotationOrNullability", "byAnnotation", "byNullability", "(Ljava/lang/Boolean;Ljava/lang/Boolean;)Ljava/lang/Boolean;", "getCorrespondingGetter", "Lkotlin/reflect/KProperty1;", "", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;", "getCorrespondingSetter", "Lkotlin/reflect/KMutableProperty1$Setter;", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;)Ljava/lang/Boolean;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedMethod;)Ljava/lang/Boolean;", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;", "(Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;)Ljava/lang/Boolean;", "isConstructorParameterRequired", "Lkotlin/reflect/KFunction;", "index", "", "isGetterLike", "isMethodParameterRequired", "isParameterRequired", "isRequired", "Lkotlin/reflect/KType;", "isRequiredByAnnotation", "Ljava/lang/reflect/AccessibleObject;", "(Ljava/lang/reflect/AccessibleObject;)Ljava/lang/Boolean;", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)Ljava/lang/Boolean;", "isSetterLike", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class KotlinAnnotationIntrospector extends NopAnnotationIntrospector {
    private final ReflectionCache cache;
    private final Module.SetupContext context;
    private final boolean nullIsSameAsDefault;
    private final boolean nullToEmptyCollection;
    private final boolean nullToEmptyMap;

    public KotlinAnnotationIntrospector(Module.SetupContext context, ReflectionCache cache, boolean z, boolean z2, boolean z3) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(cache, "cache");
        this.context = context;
        this.cache = cache;
        this.nullToEmptyCollection = z;
        this.nullToEmptyMap = z2;
        this.nullIsSameAsDefault = z3;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public Boolean hasRequiredMarker(final AnnotatedMember m) {
        Intrinsics.checkNotNullParameter(m, "m");
        return this.cache.javaMemberIsRequired(m, new Function1<AnnotatedMember, Boolean>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector$hasRequiredMarker$hasRequired$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Removed duplicated region for block: B:14:0x003c A[Catch: UnsupportedOperationException -> 0x007e, TryCatch #0 {UnsupportedOperationException -> 0x007e, blocks: (B:3:0x0006, B:6:0x0010, B:8:0x001f, B:9:0x0022, B:11:0x002a, B:13:0x0039, B:14:0x003c, B:16:0x0056, B:18:0x005c, B:19:0x0065, B:21:0x0069, B:22:0x0072, B:24:0x0076), top: B:28:0x0006 }] */
            /* JADX WARN: Removed duplicated region for block: B:9:0x0022 A[Catch: UnsupportedOperationException -> 0x007e, TryCatch #0 {UnsupportedOperationException -> 0x007e, blocks: (B:3:0x0006, B:6:0x0010, B:8:0x001f, B:9:0x0022, B:11:0x002a, B:13:0x0039, B:14:0x003c, B:16:0x0056, B:18:0x005c, B:19:0x0065, B:21:0x0069, B:22:0x0072, B:24:0x0076), top: B:28:0x0006 }] */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final java.lang.Boolean invoke(com.fasterxml.jackson.databind.introspect.AnnotatedMember r3) throws java.lang.reflect.InvocationTargetException {
                /*
                    r2 = this;
                    java.lang.String r0 = "it"
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                    r3 = 0
                    com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector r0 = r2.this$0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r0 = com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector.access$getNullToEmptyCollection$p(r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.String r1 = "m.type"
                    if (r0 == 0) goto L22
                    com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r2     // Catch: java.lang.UnsupportedOperationException -> L7e
                    com.fasterxml.jackson.databind.JavaType r0 = r0.getType()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r0 = r0.isCollectionLikeType()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r0 == 0) goto L22
                    java.lang.Boolean r3 = java.lang.Boolean.FALSE     // Catch: java.lang.UnsupportedOperationException -> L7e
                    goto L7e
                L22:
                    com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector r0 = r2.this$0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r0 = com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector.access$getNullToEmptyMap$p(r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r0 == 0) goto L3c
                    com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r2     // Catch: java.lang.UnsupportedOperationException -> L7e
                    com.fasterxml.jackson.databind.JavaType r0 = r0.getType()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r0 = r0.isMapLikeType()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r0 == 0) goto L3c
                    java.lang.Boolean r3 = java.lang.Boolean.FALSE     // Catch: java.lang.UnsupportedOperationException -> L7e
                    goto L7e
                L3c:
                    com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r2     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.reflect.Member r0 = r0.getMember()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.String r1 = "m.member"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.Class r0 = r0.getDeclaringClass()     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.String r1 = "m.member.declaringClass"
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r0 = com.fasterxml.jackson.module.kotlin.KotlinModuleKt.isKotlinClass(r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r0 == 0) goto L7e
                    com.fasterxml.jackson.databind.introspect.AnnotatedMember r0 = r2     // Catch: java.lang.UnsupportedOperationException -> L7e
                    boolean r1 = r0 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedField     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r1 == 0) goto L65
                    com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector r1 = r2.this$0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    com.fasterxml.jackson.databind.introspect.AnnotatedField r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedField) r0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.Boolean r3 = com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector.access$hasRequiredMarker(r1, r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    goto L7e
                L65:
                    boolean r1 = r0 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r1 == 0) goto L72
                    com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector r1 = r2.this$0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.Boolean r3 = com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector.access$hasRequiredMarker(r1, r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                    goto L7e
                L72:
                    boolean r1 = r0 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedParameter     // Catch: java.lang.UnsupportedOperationException -> L7e
                    if (r1 == 0) goto L7e
                    com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector r1 = r2.this$0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    com.fasterxml.jackson.databind.introspect.AnnotatedParameter r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedParameter) r0     // Catch: java.lang.UnsupportedOperationException -> L7e
                    java.lang.Boolean r3 = com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector.access$hasRequiredMarker(r1, r0)     // Catch: java.lang.UnsupportedOperationException -> L7e
                L7e:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinAnnotationIntrospector$hasRequiredMarker$hasRequired$1.invoke(com.fasterxml.jackson.databind.introspect.AnnotatedMember):java.lang.Boolean");
            }
        });
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public JsonCreator.Mode findCreatorAnnotation(MapperConfig<?> config, Annotated a2) {
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(a2, "a");
        return super.findCreatorAnnotation(config, a2);
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public List<NamedType> findSubtypes(Annotated a2) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Class<?> rawType = a2.getRawType();
        Intrinsics.checkNotNullExpressionValue(rawType, "rawType");
        if (!KotlinModuleKt.isKotlinClass(rawType)) {
            return null;
        }
        KClass kotlinClass = JvmClassMappingKt.getKotlinClass(rawType);
        if (!kotlinClass.isSealed()) {
            return null;
        }
        List sealedSubclasses = kotlinClass.getSealedSubclasses();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(sealedSubclasses, 10));
        Iterator it = sealedSubclasses.iterator();
        while (it.hasNext()) {
            arrayList.add(new NamedType(JvmClassMappingKt.getJavaClass((KClass) it.next())));
        }
        return CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedField annotatedField) throws InvocationTargetException {
        KType returnType;
        Member member = annotatedField.getMember();
        if (member == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Field");
        }
        Boolean boolIsRequiredByAnnotation = isRequiredByAnnotation((Field) member);
        Member member2 = annotatedField.getMember();
        if (member2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.reflect.Field");
        }
        KProperty<?> kotlinProperty = ReflectJvmMapping.getKotlinProperty((Field) member2);
        return requiredAnnotationOrNullability(boolIsRequiredByAnnotation, (kotlinProperty == null || (returnType = kotlinProperty.getReturnType()) == null) ? null : Boolean.valueOf(isRequired(returnType)));
    }

    private final Boolean isRequiredByAnnotation(AccessibleObject accessibleObject) {
        Annotation annotation;
        Annotation[] annotations = accessibleObject.getAnnotations();
        if (annotations == null) {
            return null;
        }
        int length = annotations.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            annotation = annotations[i];
            if (Intrinsics.areEqual(JvmClassMappingKt.getAnnotationClass(annotation), Reflection.getOrCreateKotlinClass(JsonProperty.class))) {
                break;
            }
            i++;
        }
        if (annotation != null) {
            return Boolean.valueOf(((JsonProperty) annotation).required());
        }
        return null;
    }

    private final Boolean requiredAnnotationOrNullability(Boolean byAnnotation, Boolean byNullability) {
        if (byAnnotation == null || byNullability == null) {
            return byNullability != null ? byNullability : byAnnotation;
        }
        return Boolean.valueOf(byAnnotation.booleanValue() || byNullability.booleanValue());
    }

    private final Boolean isRequiredByAnnotation(Method method) {
        Annotation annotation;
        Annotation[] annotations = method.getAnnotations();
        Intrinsics.checkNotNullExpressionValue(annotations, "this.annotations");
        int length = annotations.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                annotation = null;
                break;
            }
            annotation = annotations[i];
            if (Intrinsics.areEqual(JvmClassMappingKt.getJavaClass(JvmClassMappingKt.getAnnotationClass(annotation)), JsonProperty.class)) {
                break;
            }
            i++;
        }
        if (!(annotation instanceof JsonProperty)) {
            annotation = null;
        }
        JsonProperty jsonProperty = (JsonProperty) annotation;
        if (jsonProperty != null) {
            return Boolean.valueOf(jsonProperty.required());
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedMethod annotatedMethod) throws InvocationTargetException {
        KProperty1<? extends Object, Object> correspondingGetter = getCorrespondingGetter(annotatedMethod);
        if (correspondingGetter != null) {
            Method javaGetter = ReflectJvmMapping.getJavaGetter(correspondingGetter);
            return requiredAnnotationOrNullability(javaGetter != null ? isRequiredByAnnotation(javaGetter) : null, Boolean.valueOf(isRequired(correspondingGetter.getReturnType())));
        }
        KMutableProperty1.Setter<? extends Object, Object> correspondingSetter = getCorrespondingSetter(annotatedMethod);
        if (correspondingSetter != null) {
            Method javaMethod = ReflectJvmMapping.getJavaMethod(correspondingSetter);
            return requiredAnnotationOrNullability(javaMethod != null ? isRequiredByAnnotation(javaMethod) : null, Boolean.valueOf(isMethodParameterRequired(correspondingSetter, 0)));
        }
        Method member = annotatedMethod.getMember();
        Intrinsics.checkNotNullExpressionValue(member, "this.member");
        KFunction<?> kotlinFunction = ReflectJvmMapping.getKotlinFunction(member);
        if (kotlinFunction != null) {
            Method javaMethod2 = ReflectJvmMapping.getJavaMethod(kotlinFunction);
            Boolean boolIsRequiredByAnnotation = javaMethod2 != null ? isRequiredByAnnotation(javaMethod2) : null;
            if (isGetterLike(kotlinFunction)) {
                return requiredAnnotationOrNullability(boolIsRequiredByAnnotation, Boolean.valueOf(isRequired(kotlinFunction.getReturnType())));
            }
            if (isSetterLike(kotlinFunction)) {
                return requiredAnnotationOrNullability(boolIsRequiredByAnnotation, Boolean.valueOf(isMethodParameterRequired(kotlinFunction, 0)));
            }
        }
        return null;
    }

    private final boolean isGetterLike(KFunction<?> kFunction) {
        return kFunction.getParameters().size() == 1;
    }

    private final boolean isSetterLike(KFunction<?> kFunction) {
        return kFunction.getParameters().size() == 2 && Intrinsics.areEqual(kFunction.getReturnType(), KClassifiers.createType$default(Reflection.getOrCreateKotlinClass(Unit.class), null, false, null, 7, null));
    }

    private final KProperty1<? extends Object, Object> getCorrespondingGetter(AnnotatedMethod annotatedMethod) {
        Object next;
        Method member = annotatedMethod.getMember();
        Intrinsics.checkNotNullExpressionValue(member, "member");
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        Iterator it = KClasses.getDeclaredMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass)).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (Intrinsics.areEqual(ReflectJvmMapping.getJavaMethod(((KProperty1) next).getGetter()), annotatedMethod.getMember())) {
                break;
            }
        }
        return (KProperty1) next;
    }

    private final KMutableProperty1.Setter<? extends Object, Object> getCorrespondingSetter(AnnotatedMethod annotatedMethod) {
        Object next;
        Method member = annotatedMethod.getMember();
        Intrinsics.checkNotNullExpressionValue(member, "member");
        Class<?> declaringClass = member.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        Iterator it = KClasses.getDeclaredMemberProperties(JvmClassMappingKt.getKotlinClass(declaringClass)).iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            KProperty1 kProperty1 = (KProperty1) next;
            if (kProperty1 instanceof KMutableProperty1 ? Intrinsics.areEqual(ReflectJvmMapping.getJavaSetter((KMutableProperty) kProperty1), annotatedMethod.getMember()) : false) {
                break;
            }
        }
        KProperty1 kProperty12 = (KProperty1) next;
        if (!(kProperty12 instanceof KMutableProperty1)) {
            kProperty12 = null;
        }
        KMutableProperty1 kMutableProperty1 = (KMutableProperty1) kProperty12;
        if (kMutableProperty1 != null) {
            return kMutableProperty1.getSetter();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean hasRequiredMarker(AnnotatedParameter annotatedParameter) {
        KFunction<?> kotlinFunction;
        Member member = annotatedParameter.getMember();
        JsonProperty jsonProperty = (JsonProperty) annotatedParameter.getAnnotation(JsonProperty.class);
        Boolean boolValueOf = null;
        Boolean boolValueOf2 = jsonProperty != null ? Boolean.valueOf(jsonProperty.required()) : null;
        if (member instanceof Constructor) {
            KFunction<?> kotlinFunction2 = ReflectJvmMapping.getKotlinFunction((Constructor) member);
            if (kotlinFunction2 != null) {
                boolValueOf = Boolean.valueOf(isConstructorParameterRequired(kotlinFunction2, annotatedParameter.getIndex()));
            }
        } else if ((member instanceof Method) && (kotlinFunction = ReflectJvmMapping.getKotlinFunction((Method) member)) != null) {
            boolValueOf = Boolean.valueOf(isMethodParameterRequired(kotlinFunction, annotatedParameter.getIndex()));
        }
        return requiredAnnotationOrNullability(boolValueOf2, boolValueOf);
    }

    private final boolean isConstructorParameterRequired(KFunction<?> kFunction, int i) {
        return isParameterRequired(kFunction, i);
    }

    private final boolean isMethodParameterRequired(KFunction<?> kFunction, int i) {
        return isParameterRequired(kFunction, i + 1);
    }

    private final boolean isParameterRequired(KFunction<?> kFunction, int i) {
        KParameter kParameter = kFunction.getParameters().get(i);
        KType type = kParameter.getType();
        Type javaType = ReflectJvmMapping.getJavaType(type);
        boolean zIsPrimitive = javaType instanceof Class ? ((Class) javaType).isPrimitive() : false;
        if (type.isMarkedNullable() || kParameter.isOptional()) {
            return false;
        }
        return !zIsPrimitive || this.context.isEnabled(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES);
    }

    private final boolean isRequired(KType kType) {
        return !kType.isMarkedNullable();
    }
}
