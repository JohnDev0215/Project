package com.sun.xml.bind.v2.model.annotation;

import com.sun.xml.bind.v2.model.core.ErrorHandler;
import com.sun.xml.bind.v2.runtime.IllegalAnnotationException;
import java.lang.annotation.Annotation;

/* JADX INFO: loaded from: classes3.dex */
public abstract class AbstractInlineAnnotationReaderImpl<T, C, F, M> implements AnnotationReader<T, C, F, M> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private ErrorHandler errorHandler;

    protected abstract String fullName(M m);

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public void setErrorHandler(ErrorHandler errorHandler) {
        if (errorHandler == null) {
            throw new IllegalArgumentException();
        }
        this.errorHandler = errorHandler;
    }

    public final ErrorHandler getErrorHandler() {
        return this.errorHandler;
    }

    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public final <A extends Annotation> A getMethodAnnotation(Class<A> cls, M m, M m2, Locatable locatable) {
        A a = m == null ? null : (A) getMethodAnnotation(cls, m, locatable);
        A a2 = m2 == null ? null : (A) getMethodAnnotation(cls, m2, locatable);
        if (a == null) {
            if (a2 == null) {
                return null;
            }
            return a2;
        }
        if (a2 == null) {
            return a;
        }
        getErrorHandler().error(new IllegalAnnotationException(Messages.DUPLICATE_ANNOTATIONS.format(cls.getName(), fullName(m), fullName(m2)), a, a2));
        return a;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.sun.xml.bind.v2.model.annotation.AnnotationReader
    public boolean hasMethodAnnotation(Class<? extends Annotation> cls, String str, M m, M m2, Locatable locatable) {
        boolean z = m != null && hasMethodAnnotation(cls, m);
        boolean z2 = m2 != null && hasMethodAnnotation(cls, m2);
        if (z && z2) {
            getMethodAnnotation(cls, m, m2, locatable);
        }
        return z || z2;
    }
}
