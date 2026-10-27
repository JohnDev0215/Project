package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.aop.AnnotationHandler;
import org.apache.shiro.authz.AuthorizationException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AuthorizingAnnotationHandler extends AnnotationHandler {
    public abstract void assertAuthorized(Annotation annotation) throws AuthorizationException;

    public AuthorizingAnnotationHandler(Class<? extends Annotation> cls) {
        super(cls);
    }
}
