package org.apache.shiro.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotationHandler {
    protected Class<? extends Annotation> annotationClass;

    public AnnotationHandler(Class<? extends Annotation> cls) {
        setAnnotationClass(cls);
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected void setAnnotationClass(Class<? extends Annotation> cls) throws IllegalArgumentException {
        if (cls == null) {
            throw new IllegalArgumentException("annotationClass argument cannot be null");
        }
        this.annotationClass = cls;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return this.annotationClass;
    }
}
