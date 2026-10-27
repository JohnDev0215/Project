package kotlinx.serialization.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Polymorphic;
import kotlinx.serialization.PolymorphicSerializer;
import kotlinx.serialization.Serializable;

/* JADX INFO: compiled from: Platform.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\u001a$\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000\u001aM\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00010\t\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\n\b\u0001\u0010\u0005*\u0004\u0018\u00018\u0000*\u0012\u0012\u0004\u0012\u00028\u00010\u0006j\b\u0012\u0004\u0012\u00028\u0001`\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0000¢\u0006\u0004\b\n\u0010\u000b\u001a\u0010\u0010\r\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u0002H\u0000\u001aO\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00022\"\u0010\u000e\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00030\t\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0003H\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001aO\u0010\u000f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u00112\"\u0010\u000e\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00030\t\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0003H\u0000¢\u0006\u0004\b\u000f\u0010\u0012\u001a\u001c\u0010\u0014\u001a\u00020\u0013\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002\u001a\u001c\u0010\u0015\u001a\u00020\u0013\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002\u001a$\u0010\u0016\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002\u001aQ\u0010\u0018\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u00002\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00112\"\u0010\u000e\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u00030\t\"\n\u0012\u0006\u0012\u0004\u0018\u00010\u00000\u0003H\u0002¢\u0006\u0004\b\u0018\u0010\u0012\u001a\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u0000*\u0006\u0012\u0002\b\u00030\u0011H\u0002\u001a\"\u0010\u001a\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002\u001a$\u0010\u001b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003\"\b\b\u0000\u0010\u0001*\u00020\u0000*\b\u0012\u0004\u0012\u00028\u00000\u0011H\u0002\u001a\u0018\u0010\u001d\u001a\u00020\u0013*\u00020\u00002\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u0002H\u0000\u001a\u0016\u0010\u001f\u001a\u00020\u00132\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00000\u0002H\u0000¨\u0006 "}, d2 = {"", "T", "Lkotlin/reflect/KClass;", "Lkotlinx/serialization/KSerializer;", "compiledSerializerImpl", "E", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "eClass", "", "toNativeArrayImpl", "(Ljava/util/ArrayList;Lkotlin/reflect/KClass;)[Ljava/lang/Object;", "", "platformSpecificSerializerNotRegistered", "args", "constructSerializerForGivenTypeArgs", "(Lkotlin/reflect/KClass;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "Ljava/lang/Class;", "(Ljava/lang/Class;[Lkotlinx/serialization/KSerializer;)Lkotlinx/serialization/KSerializer;", "", "isNotAnnotated", "isPolymorphicSerializer", "interfaceSerializer", "jClass", "invokeSerializerOnCompanion", "companionOrNull", "createEnumSerializer", "findObjectSerializer", "kclass", "isInstanceOf", "rootClass", "isReferenceArray", "kotlinx-serialization-core"}, k = 2, mv = {1, 7, 1})
public final class PlatformKt {
    public static final <T> KSerializer<T> compiledSerializerImpl(KClass<T> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        return constructSerializerForGivenTypeArgs(kClass, (KSerializer<Object>[]) new KSerializer[0]);
    }

    public static final <T, E extends T> E[] toNativeArrayImpl(ArrayList<E> arrayList, KClass<T> eClass) {
        Intrinsics.checkNotNullParameter(arrayList, "<this>");
        Intrinsics.checkNotNullParameter(eClass, "eClass");
        Object objNewInstance = Array.newInstance((Class<?>) JvmClassMappingKt.getJavaClass((KClass) eClass), arrayList.size());
        Intrinsics.checkNotNull(objNewInstance, "null cannot be cast to non-null type kotlin.Array<E of kotlinx.serialization.internal.PlatformKt.toNativeArrayImpl>");
        E[] eArr = (E[]) arrayList.toArray((Object[]) objNewInstance);
        Intrinsics.checkNotNullExpressionValue(eArr, "toArray(java.lang.reflec….java, size) as Array<E>)");
        return eArr;
    }

    public static final Void platformSpecificSerializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Platform_commonKt.serializerNotRegistered(kClass);
        throw new KotlinNothingValueException();
    }

    public static final <T> KSerializer<T> constructSerializerForGivenTypeArgs(KClass<T> kClass, KSerializer<Object>... args) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        return constructSerializerForGivenTypeArgs(JvmClassMappingKt.getJavaClass((KClass) kClass), (KSerializer<Object>[]) Arrays.copyOf(args, args.length));
    }

    public static final <T> KSerializer<T> constructSerializerForGivenTypeArgs(Class<T> cls, KSerializer<Object>... args) throws IllegalAccessException, InvocationTargetException {
        Object obj;
        Field field;
        KSerializer<T> kSerializerInterfaceSerializer;
        Intrinsics.checkNotNullParameter(cls, "<this>");
        Intrinsics.checkNotNullParameter(args, "args");
        if (cls.isEnum() && isNotAnnotated(cls)) {
            return createEnumSerializer(cls);
        }
        if (cls.isInterface() && (kSerializerInterfaceSerializer = interfaceSerializer(cls)) != null) {
            return kSerializerInterfaceSerializer;
        }
        KSerializer<T> kSerializerInvokeSerializerOnCompanion = invokeSerializerOnCompanion(cls, (KSerializer[]) Arrays.copyOf(args, args.length));
        if (kSerializerInvokeSerializerOnCompanion != null) {
            return kSerializerInvokeSerializerOnCompanion;
        }
        KSerializer<T> kSerializerFindObjectSerializer = findObjectSerializer(cls);
        if (kSerializerFindObjectSerializer != null) {
            return kSerializerFindObjectSerializer;
        }
        try {
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            Intrinsics.checkNotNullExpressionValue(declaredClasses, "declaredClasses");
            int length = declaredClasses.length;
            int i = 0;
            Class<?> cls2 = null;
            boolean z = false;
            while (true) {
                if (i >= length) {
                    if (!z) {
                        break;
                    }
                } else {
                    Class<?> cls3 = declaredClasses[i];
                    if (Intrinsics.areEqual(cls3.getSimpleName(), "$serializer")) {
                        if (z) {
                            break;
                        }
                        z = true;
                        cls2 = cls3;
                    }
                    i++;
                }
            }
            cls2 = null;
            Class<?> cls4 = cls2;
            obj = (cls4 == null || (field = cls4.getField("INSTANCE")) == null) ? null : field.get(null);
        } catch (NoSuchFieldException unused) {
        }
        KSerializer<T> kSerializer = obj instanceof KSerializer ? (KSerializer) obj : null;
        if (kSerializer != null) {
            return kSerializer;
        }
        if (isPolymorphicSerializer(cls)) {
            return new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(cls));
        }
        return null;
    }

    private static final <T> boolean isNotAnnotated(Class<T> cls) {
        return cls.getAnnotation(Serializable.class) == null && cls.getAnnotation(Polymorphic.class) == null;
    }

    private static final <T> boolean isPolymorphicSerializer(Class<T> cls) {
        if (cls.getAnnotation(Polymorphic.class) != null) {
            return true;
        }
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        return serializable != null && Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class));
    }

    private static final <T> KSerializer<T> interfaceSerializer(Class<T> cls) {
        Serializable serializable = (Serializable) cls.getAnnotation(Serializable.class);
        if (serializable == null || Intrinsics.areEqual(Reflection.getOrCreateKotlinClass(serializable.with()), Reflection.getOrCreateKotlinClass(PolymorphicSerializer.class))) {
            return new PolymorphicSerializer(JvmClassMappingKt.getKotlinClass(cls));
        }
        return null;
    }

    private static final <T> KSerializer<T> invokeSerializerOnCompanion(Class<?> cls, KSerializer<Object>... kSerializerArr) throws IllegalAccessException, InvocationTargetException {
        Class[] clsArr;
        Object objCompanionOrNull = companionOrNull(cls);
        if (objCompanionOrNull == null) {
            return null;
        }
        try {
            if (kSerializerArr.length == 0) {
                clsArr = new Class[0];
            } else {
                int length = kSerializerArr.length;
                Class[] clsArr2 = new Class[length];
                for (int i = 0; i < length; i++) {
                    clsArr2[i] = KSerializer.class;
                }
                clsArr = clsArr2;
            }
            Object objInvoke = objCompanionOrNull.getClass().getDeclaredMethod("serializer", (Class[]) Arrays.copyOf(clsArr, clsArr.length)).invoke(objCompanionOrNull, Arrays.copyOf(kSerializerArr, kSerializerArr.length));
            if (objInvoke instanceof KSerializer) {
                return (KSerializer) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        } catch (InvocationTargetException e) {
            Throwable cause = e.getCause();
            if (cause == null) {
                throw e;
            }
            String message = cause.getMessage();
            if (message == null) {
                message = e.getMessage();
            }
            throw new InvocationTargetException(cause, message);
        }
    }

    private static final Object companionOrNull(Class<?> cls) {
        try {
            Field declaredField = cls.getDeclaredField("Companion");
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (Throwable unused) {
            return null;
        }
    }

    private static final <T> KSerializer<T> createEnumSerializer(Class<T> cls) {
        T[] enumConstants = cls.getEnumConstants();
        String canonicalName = cls.getCanonicalName();
        Intrinsics.checkNotNullExpressionValue(canonicalName, "canonicalName");
        Intrinsics.checkNotNull(enumConstants, "null cannot be cast to non-null type kotlin.Array<out kotlin.Enum<*>>");
        return new EnumSerializer(canonicalName, (Enum[]) enumConstants);
    }

    /* JADX WARN: Code restructure failed: missing block: B:42:0x0096, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final <T> kotlinx.serialization.KSerializer<T> findObjectSerializer(java.lang.Class<T> r11) throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            java.lang.reflect.Field[] r0 = r11.getDeclaredFields()
            java.lang.String r1 = "declaredFields"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            int r1 = r0.length
            r2 = 0
            r3 = 0
            r4 = r2
            r5 = r4
            r6 = r3
        Lf:
            r7 = 1
            if (r4 >= r1) goto L41
            r8 = r0[r4]
            java.lang.String r9 = r8.getName()
            java.lang.String r10 = "INSTANCE"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
            if (r9 == 0) goto L36
            java.lang.Class r9 = r8.getType()
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r11)
            if (r9 == 0) goto L36
            int r9 = r8.getModifiers()
            boolean r9 = java.lang.reflect.Modifier.isStatic(r9)
            if (r9 == 0) goto L36
            r9 = r7
            goto L37
        L36:
            r9 = r2
        L37:
            if (r9 == 0) goto L3e
            if (r5 == 0) goto L3c
            goto L43
        L3c:
            r5 = r7
            r6 = r8
        L3e:
            int r4 = r4 + 1
            goto Lf
        L41:
            if (r5 != 0) goto L44
        L43:
            r6 = r3
        L44:
            java.lang.reflect.Field r6 = (java.lang.reflect.Field) r6
            if (r6 != 0) goto L49
            return r3
        L49:
            java.lang.Object r0 = r6.get(r3)
            java.lang.reflect.Method[] r11 = r11.getMethods()
            java.lang.String r1 = "methods"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r1)
            int r1 = r11.length
            r4 = r2
            r5 = r4
            r6 = r3
        L5a:
            if (r4 >= r1) goto L94
            r8 = r11[r4]
            java.lang.String r9 = r8.getName()
            java.lang.String r10 = "serializer"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
            if (r9 == 0) goto L89
            java.lang.Class[] r9 = r8.getParameterTypes()
            java.lang.String r10 = "it.parameterTypes"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            int r9 = r9.length
            if (r9 != 0) goto L78
            r9 = r7
            goto L79
        L78:
            r9 = r2
        L79:
            if (r9 == 0) goto L89
            java.lang.Class r9 = r8.getReturnType()
            java.lang.Class<kotlinx.serialization.KSerializer> r10 = kotlinx.serialization.KSerializer.class
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
            if (r9 == 0) goto L89
            r9 = r7
            goto L8a
        L89:
            r9 = r2
        L8a:
            if (r9 == 0) goto L91
            if (r5 == 0) goto L8f
            goto L96
        L8f:
            r5 = r7
            r6 = r8
        L91:
            int r4 = r4 + 1
            goto L5a
        L94:
            if (r5 != 0) goto L97
        L96:
            r6 = r3
        L97:
            java.lang.reflect.Method r6 = (java.lang.reflect.Method) r6
            if (r6 != 0) goto L9c
            return r3
        L9c:
            java.lang.Object[] r11 = new java.lang.Object[r2]
            java.lang.Object r11 = r6.invoke(r0, r11)
            boolean r0 = r11 instanceof kotlinx.serialization.KSerializer
            if (r0 == 0) goto La9
            r3 = r11
            kotlinx.serialization.KSerializer r3 = (kotlinx.serialization.KSerializer) r3
        La9:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.PlatformKt.findObjectSerializer(java.lang.Class):kotlinx.serialization.KSerializer");
    }

    public static final boolean isInstanceOf(Object obj, KClass<?> kclass) {
        Intrinsics.checkNotNullParameter(obj, "<this>");
        Intrinsics.checkNotNullParameter(kclass, "kclass");
        return JvmClassMappingKt.getJavaObjectType(kclass).isInstance(obj);
    }

    public static final boolean isReferenceArray(KClass<Object> rootClass) {
        Intrinsics.checkNotNullParameter(rootClass, "rootClass");
        return JvmClassMappingKt.getJavaClass((KClass) rootClass).isArray();
    }
}
