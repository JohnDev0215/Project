package com.sun.xml.bind.v2.model.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class RuntimeInlineAnnotationReader extends AbstractInlineAnnotationReaderImpl<Type, Class, Field, Method> implements RuntimeAnnotationReader {
    private final Map<Class<? extends Annotation>, Map<Package, Annotation>> packageCache = new HashMap();

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public /* bridge */ /* synthetic */ boolean hasClassAnnotation(Class cls, Class cls2) {
        return hasClassAnnotation2(cls, (Class<? extends Annotation>) cls2);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public /* bridge */ /* synthetic */ boolean hasFieldAnnotation(Class cls, Field field) {
        return hasFieldAnnotation2((Class<? extends Annotation>) cls, field);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public /* bridge */ /* synthetic */ boolean hasMethodAnnotation(Class cls, Method method) {
        return hasMethodAnnotation2((Class<? extends Annotation>) cls, method);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public <A extends Annotation> A getFieldAnnotation(Class<A> cls, Field field, Locatable locatable) {
        return (A) LocatableAnnotation.create(field.getAnnotation(cls), locatable);
    }

    /* JADX INFO: renamed from: hasFieldAnnotation, reason: avoid collision after fix types in other method */
    public boolean hasFieldAnnotation2(Class<? extends Annotation> cls, Field field) {
        return field.isAnnotationPresent(cls);
    }

    /* JADX INFO: renamed from: hasClassAnnotation, reason: avoid collision after fix types in other method */
    public boolean hasClassAnnotation2(Class cls, Class<? extends Annotation> cls2) {
        return cls.isAnnotationPresent(cls2);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public Annotation[] getAllFieldAnnotations(Field field, Locatable locatable) {
        Annotation[] annotations = field.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = LocatableAnnotation.create(annotations[i], locatable);
        }
        return annotations;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public <A extends Annotation> A getMethodAnnotation(Class<A> cls, Method method, Locatable locatable) {
        return (A) LocatableAnnotation.create(method.getAnnotation(cls), locatable);
    }

    /* JADX INFO: renamed from: hasMethodAnnotation, reason: avoid collision after fix types in other method */
    public boolean hasMethodAnnotation2(Class<? extends Annotation> cls, Method method) {
        return method.isAnnotationPresent(cls);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public Annotation[] getAllMethodAnnotations(Method method, Locatable locatable) {
        Annotation[] annotations = method.getAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = LocatableAnnotation.create(annotations[i], locatable);
        }
        return annotations;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public <A extends Annotation> A getMethodParameterAnnotation(Class<A> cls, Method method, int i, Locatable locatable) {
        for (Annotation annotation : method.getParameterAnnotations()[i]) {
            if (annotation.annotationType() == cls) {
                return (A) LocatableAnnotation.create(annotation, locatable);
            }
        }
        return null;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public <A extends Annotation> A getClassAnnotation(Class<A> cls, Class cls2, Locatable locatable) {
        return (A) LocatableAnnotation.create(cls2.getAnnotation(cls), locatable);
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public <A extends Annotation> A getPackageAnnotation(Class<A> cls, Class cls2, Locatable locatable) {
        Package r4 = cls2.getPackage();
        if (r4 == null) {
            return null;
        }
        Map<Package, Annotation> map = this.packageCache.get(cls);
        if (map == null) {
            map = new HashMap<>();
            this.packageCache.put((Class<? extends Annotation>) cls, map);
        }
        if (map.containsKey(r4)) {
            return (A) map.get(r4);
        }
        A a = (A) LocatableAnnotation.create(r4.getAnnotation(cls), locatable);
        map.put(r4, a);
        return a;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    /* JADX INFO: renamed from: getClassValue, reason: merged with bridge method [inline-methods] */
    public Type getClassValue2(Annotation annotation, String str) {
        try {
            return (Class) annotation.annotationType().getMethod(str, new Class[0]).invoke(annotation, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (NoSuchMethodException e2) {
            throw new NoSuchMethodError(e2.getMessage());
        } catch (InvocationTargetException e3) {
            throw new InternalError(Messages.CLASS_NOT_FOUND.format(annotation.annotationType(), e3.getMessage()));
        }
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    /* JADX INFO: renamed from: getClassArrayValue, reason: merged with bridge method [inline-methods] */
    public Type[] getClassArrayValue2(Annotation annotation, String str) {
        try {
            return (Class[]) annotation.annotationType().getMethod(str, new Class[0]).invoke(annotation, new Object[0]);
        } catch (IllegalAccessException e) {
            throw new IllegalAccessError(e.getMessage());
        } catch (NoSuchMethodException e2) {
            throw new NoSuchMethodError(e2.getMessage());
        } catch (InvocationTargetException e3) {
            throw new InternalError(e3.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sun.xml.bind.v2.model.annotation.AbstractInlineAnnotationReaderImpl
    public String fullName(Method method) {
        return method.getDeclaringClass().getName() + '#' + method.getName();
    }
}
