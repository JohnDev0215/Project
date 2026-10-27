package com.fasterxml.jackson.module.kotlin;

import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.NopAnnotationIntrospector;
import com.fasterxml.jackson.databind.util.BeanUtil;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import kotlin.reflect.KFunction;
import kotlin.reflect.KParameter;
import kotlin.reflect.jvm.ReflectJvmMapping;
import kotlin.reflect.jvm.internal.KotlinReflectionInternalError;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: KotlinNamesAnnotationIntrospector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\u0002\u0010\tJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0018H\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0012\u001a\u00020 H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001b\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006!"}, d2 = {"Lcom/fasterxml/jackson/module/kotlin/KotlinNamesAnnotationIntrospector;", "Lcom/fasterxml/jackson/databind/introspect/NopAnnotationIntrospector;", "module", "Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "cache", "Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "ignoredClassesForImplyingJsonCreator", "", "Lkotlin/reflect/KClass;", "(Lcom/fasterxml/jackson/module/kotlin/KotlinModule;Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;Ljava/util/Set;)V", "getCache", "()Lcom/fasterxml/jackson/module/kotlin/ReflectionCache;", "getIgnoredClassesForImplyingJsonCreator", "()Ljava/util/Set;", "getModule", "()Lcom/fasterxml/jackson/module/kotlin/KotlinModule;", "findImplicitPropertyName", "", "member", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedMember;", "findKotlinParameterName", "param", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedParameter;", "findRenameByField", "Lcom/fasterxml/jackson/databind/PropertyName;", "config", "Lcom/fasterxml/jackson/databind/cfg/MapperConfig;", "field", "Lcom/fasterxml/jackson/databind/introspect/AnnotatedField;", "implName", "hasCreatorAnnotation", "", "Lcom/fasterxml/jackson/databind/introspect/Annotated;", "jackson-module-kotlin"}, k = 1, mv = {1, 4, 1})
public final class KotlinNamesAnnotationIntrospector extends NopAnnotationIntrospector {
    private final ReflectionCache cache;
    private final Set<KClass<?>> ignoredClassesForImplyingJsonCreator;
    private final KotlinModule module;

    /* JADX WARN: Multi-variable type inference failed */
    public KotlinNamesAnnotationIntrospector(KotlinModule module, ReflectionCache cache, Set<? extends KClass<?>> ignoredClassesForImplyingJsonCreator) {
        Intrinsics.checkNotNullParameter(module, "module");
        Intrinsics.checkNotNullParameter(cache, "cache");
        Intrinsics.checkNotNullParameter(ignoredClassesForImplyingJsonCreator, "ignoredClassesForImplyingJsonCreator");
        this.module = module;
        this.cache = cache;
        this.ignoredClassesForImplyingJsonCreator = ignoredClassesForImplyingJsonCreator;
    }

    public final ReflectionCache getCache() {
        return this.cache;
    }

    public final Set<KClass<?>> getIgnoredClassesForImplyingJsonCreator() {
        return this.ignoredClassesForImplyingJsonCreator;
    }

    public final KotlinModule getModule() {
        return this.module;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0085  */
    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String findImplicitPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedMember r8) {
        /*
            r7 = this;
            java.lang.String r0 = "member"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            boolean r0 = r8 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedMethod
            r1 = 0
            if (r0 == 0) goto L85
            r0 = r8
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r0 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethod) r0
            boolean r2 = com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospectorKt.access$isInlineClass(r0)
            if (r2 == 0) goto L85
            java.lang.String r8 = r0.getName()
            java.lang.String r2 = "member.name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            java.lang.String r3 = "get"
            r4 = 0
            r5 = 2
            boolean r8 = kotlin.text.StringsKt.startsWith$default(r8, r3, r4, r5, r1)
            r6 = 45
            if (r8 == 0) goto L4f
            java.lang.String r8 = r0.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            boolean r8 = kotlin.text.StringsKt.contains$default(r8, r6, r4, r5, r1)
            if (r8 == 0) goto L4f
            int r8 = r0.getParameterCount()
            if (r8 != 0) goto L4f
            java.lang.String r8 = r0.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            java.lang.String r8 = kotlin.text.StringsKt.substringAfter$default(r8, r3, r1, r5, r1)
            java.lang.String r8 = kotlin.text.StringsKt.decapitalize(r8)
            java.lang.String r8 = kotlin.text.StringsKt.substringBefore$default(r8, r6, r1, r5, r1)
            return r8
        L4f:
            java.lang.String r8 = r0.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            java.lang.String r3 = "is"
            boolean r8 = kotlin.text.StringsKt.startsWith$default(r8, r3, r4, r5, r1)
            if (r8 == 0) goto L90
            java.lang.String r8 = r0.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            boolean r8 = kotlin.text.StringsKt.contains$default(r8, r6, r4, r5, r1)
            if (r8 == 0) goto L90
            int r8 = r0.getParameterCount()
            if (r8 != 0) goto L90
            java.lang.String r8 = r0.getName()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r2)
            java.lang.String r8 = kotlin.text.StringsKt.substringAfter$default(r8, r3, r1, r5, r1)
            java.lang.String r8 = kotlin.text.StringsKt.decapitalize(r8)
            java.lang.String r8 = kotlin.text.StringsKt.substringBefore$default(r8, r6, r1, r5, r1)
            return r8
        L85:
            boolean r0 = r8 instanceof com.fasterxml.jackson.databind.introspect.AnnotatedParameter
            if (r0 == 0) goto L90
            com.fasterxml.jackson.databind.introspect.AnnotatedParameter r8 = (com.fasterxml.jackson.databind.introspect.AnnotatedParameter) r8
            java.lang.String r8 = r7.findKotlinParameterName(r8)
            return r8
        L90:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.findImplicitPropertyName(com.fasterxml.jackson.databind.introspect.AnnotatedMember):java.lang.String");
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public PropertyName findRenameByField(MapperConfig<?> config, AnnotatedField field, PropertyName implName) {
        String strStdManglePropertyName;
        Intrinsics.checkNotNullParameter(config, "config");
        Intrinsics.checkNotNullParameter(field, "field");
        Intrinsics.checkNotNullParameter(implName, "implName");
        String origSimple = implName.getSimpleName();
        Class<?> declaringClass = field.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "field.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass)) {
            Intrinsics.checkNotNullExpressionValue(origSimple, "origSimple");
            if (StringsKt__StringsJVMKt.startsWith$default(origSimple, "is", false, 2, null) && (strStdManglePropertyName = BeanUtil.stdManglePropertyName(origSimple, 2)) != null && !strStdManglePropertyName.equals(origSimple)) {
                return PropertyName.construct(strStdManglePropertyName);
            }
        }
        return null;
    }

    @Override // com.fasterxml.jackson.databind.AnnotationIntrospector
    public boolean hasCreatorAnnotation(final Annotated member) {
        Intrinsics.checkNotNullParameter(member, "member");
        if (!(member instanceof AnnotatedConstructor)) {
            return false;
        }
        AnnotatedConstructor annotatedConstructor = (AnnotatedConstructor) member;
        Class<?> declaringClass = annotatedConstructor.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "member.declaringClass");
        if (declaringClass.isEnum() || annotatedConstructor.getParameterCount() <= 0) {
            return false;
        }
        Class<?> declaringClass2 = annotatedConstructor.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass2, "member.declaringClass");
        if (KotlinModuleKt.isKotlinClass(declaringClass2)) {
            return this.cache.checkConstructorIsCreatorAnnotated(annotatedConstructor, new Function1<AnnotatedConstructor, Boolean>() { // from class: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.hasCreatorAnnotation.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX INFO: renamed from: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector$hasCreatorAnnotation$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: KotlinNamesAnnotationIntrospector.kt */
                @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"isPossibleSingleString", "", "Lkotlin/reflect/KFunction;", "invoke"}, k = 3, mv = {1, 4, 1})
                static final class C01491 extends Lambda implements Function1<KFunction<?>, Boolean> {
                    final /* synthetic */ Set $propertyNames;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C01491(Set set) {
                        super(1);
                        this.$propertyNames = set;
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Boolean invoke(KFunction<?> kFunction) {
                        return Boolean.valueOf(invoke2(kFunction));
                    }

                    /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final boolean invoke2(kotlin.reflect.KFunction<?> r5) {
                        /*
                            r4 = this;
                            java.lang.String r0 = "$this$isPossibleSingleString"
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                            java.util.List r0 = r5.getParameters()
                            int r0 = r0.size()
                            r1 = 1
                            r2 = 0
                            if (r0 != r1) goto L7f
                            java.util.Set r0 = r4.$propertyNames
                            java.util.List r3 = r5.getParameters()
                            java.lang.Object r3 = r3.get(r2)
                            kotlin.reflect.KParameter r3 = (kotlin.reflect.KParameter) r3
                            java.lang.String r3 = r3.getName()
                            boolean r0 = kotlin.collections.CollectionsKt.contains(r0, r3)
                            if (r0 != 0) goto L7f
                            java.util.List r0 = r5.getParameters()
                            java.lang.Object r0 = r0.get(r2)
                            kotlin.reflect.KParameter r0 = (kotlin.reflect.KParameter) r0
                            kotlin.reflect.KType r0 = r0.getType()
                            java.lang.reflect.Type r0 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r0)
                            java.lang.Class<java.lang.String> r3 = java.lang.String.class
                            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                            if (r0 == 0) goto L7f
                            java.util.List r5 = r5.getParameters()
                            java.lang.Object r5 = r5.get(r2)
                            kotlin.reflect.KParameter r5 = (kotlin.reflect.KParameter) r5
                            java.util.List r5 = r5.getAnnotations()
                            boolean r0 = r5 instanceof java.util.Collection
                            if (r0 == 0) goto L5b
                            boolean r0 = r5.isEmpty()
                            if (r0 == 0) goto L5b
                        L59:
                            r5 = r1
                            goto L7c
                        L5b:
                            java.util.Iterator r5 = r5.iterator()
                        L5f:
                            boolean r0 = r5.hasNext()
                            if (r0 == 0) goto L59
                            java.lang.Object r0 = r5.next()
                            java.lang.annotation.Annotation r0 = (java.lang.annotation.Annotation) r0
                            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getAnnotationClass(r0)
                            java.lang.Class r0 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r0)
                            java.lang.Class<com.fasterxml.jackson.annotation.JsonProperty> r3 = com.fasterxml.jackson.annotation.JsonProperty.class
                            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                            if (r0 == 0) goto L5f
                            r5 = r2
                        L7c:
                            if (r5 == 0) goto L7f
                            goto L80
                        L7f:
                            r1 = r2
                        L80:
                            return r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.AnonymousClass1.C01491.invoke2(kotlin.reflect.KFunction):boolean");
                    }
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Boolean invoke(AnnotatedConstructor annotatedConstructor2) {
                    return Boolean.valueOf(invoke2(annotatedConstructor2));
                }

                /* JADX WARN: Removed duplicated region for block: B:117:0x018b A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:121:? A[LOOP:1: B:52:0x0114->B:121:?, LOOP_END, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:135:0x00db A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:139:? A[LOOP:5: B:27:0x009c->B:139:?, LOOP_END, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:66:0x0153  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x0186  */
                /* JADX WARN: Removed duplicated region for block: B:79:0x0188  */
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final boolean invoke2(com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r12) {
                    /*
                        Method dump skipped, instruction units count: 507
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.module.kotlin.KotlinNamesAnnotationIntrospector.AnonymousClass1.invoke2(com.fasterxml.jackson.databind.introspect.AnnotatedConstructor):boolean");
                }
            });
        }
        return false;
    }

    private final String findKotlinParameterName(AnnotatedParameter param) throws InvocationTargetException {
        List<KParameter> parameters;
        KParameter kParameter;
        List<KParameter> parameters2;
        List<KParameter> parameters3;
        KParameter kParameter2;
        KFunction kotlinFunction;
        List<KParameter> parameters4;
        KParameter kParameter3;
        List<KParameter> parameters5;
        Class<?> declaringClass = param.getDeclaringClass();
        Intrinsics.checkNotNullExpressionValue(declaringClass, "param.declaringClass");
        if (!KotlinModuleKt.isKotlinClass(declaringClass)) {
            return null;
        }
        AnnotatedWithParams owner = param.getOwner();
        Intrinsics.checkNotNullExpressionValue(owner, "param.owner");
        Member member = owner.getMember();
        int size = 0;
        if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            int length = constructor.getParameterTypes().length;
            try {
                KFunction kotlinFunction2 = ReflectJvmMapping.getKotlinFunction(constructor);
                if (kotlinFunction2 != null && (parameters5 = kotlinFunction2.getParameters()) != null) {
                    size = parameters5.size();
                }
            } catch (UnsupportedOperationException | KotlinReflectionInternalError unused) {
            }
            if (size <= 0 || size != length || (kotlinFunction = ReflectJvmMapping.getKotlinFunction(constructor)) == null || (parameters4 = kotlinFunction.getParameters()) == null || (kParameter3 = parameters4.get(param.getIndex())) == null) {
                return null;
            }
            return kParameter3.getName();
        }
        if (!(member instanceof Method)) {
            return null;
        }
        try {
            KFunction<?> kotlinFunction3 = ReflectJvmMapping.getKotlinFunction((Method) member);
            int index = ((kotlinFunction3 == null || (parameters3 = kotlinFunction3.getParameters()) == null || (kParameter2 = (KParameter) CollectionsKt___CollectionsKt.firstOrNull((List) parameters3)) == null) ? null : kParameter2.getKind()) != KParameter.Kind.VALUE ? param.getIndex() + 1 : param.getIndex();
            if (kotlinFunction3 != null && (parameters2 = kotlinFunction3.getParameters()) != null) {
                size = parameters2.size();
            }
            if (size <= index || kotlinFunction3 == null || (parameters = kotlinFunction3.getParameters()) == null || (kParameter = parameters.get(index)) == null) {
                return null;
            }
            return kParameter.getName();
        } catch (KotlinReflectionInternalError unused2) {
            return null;
        }
    }
}
