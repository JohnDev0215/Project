package org.apache.shiro.event.support;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.shiro.event.Subscribe;
import org.apache.shiro.util.ClassUtils;

/* JADX INFO: loaded from: classes.dex */
public class AnnotationEventListenerResolver implements EventListenerResolver {
    private Class<? extends Annotation> annotationClass = Subscribe.class;

    @Override // org.apache.shiro.event.support.EventListenerResolver
    public List<EventListener> getEventListeners(Object obj) {
        if (obj == null) {
            return Collections.emptyList();
        }
        List<Method> annotatedMethods = ClassUtils.getAnnotatedMethods(obj.getClass(), getAnnotationClass());
        if (annotatedMethods == null || annotatedMethods.isEmpty()) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(annotatedMethods.size());
        Iterator<Method> it = annotatedMethods.iterator();
        while (it.hasNext()) {
            arrayList.add(new SingleArgumentMethodEventListener(obj, it.next()));
        }
        return arrayList;
    }

    public Class<? extends Annotation> getAnnotationClass() {
        return this.annotationClass;
    }

    public void setAnnotationClass(Class<? extends Annotation> cls) {
        this.annotationClass = cls;
    }
}
