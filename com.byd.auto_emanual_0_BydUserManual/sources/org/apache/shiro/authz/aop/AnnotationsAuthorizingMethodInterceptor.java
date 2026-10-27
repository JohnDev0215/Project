package org.apache.shiro.authz.aop;

import java.util.ArrayList;
import java.util.Collection;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.AuthorizationException;

/* JADX INFO: loaded from: classes.dex */
public abstract class AnnotationsAuthorizingMethodInterceptor extends AuthorizingMethodInterceptor {
    protected Collection<AuthorizingAnnotationMethodInterceptor> methodInterceptors;

    public AnnotationsAuthorizingMethodInterceptor() {
        ArrayList arrayList = new ArrayList(5);
        this.methodInterceptors = arrayList;
        arrayList.add(new RoleAnnotationMethodInterceptor());
        this.methodInterceptors.add(new PermissionAnnotationMethodInterceptor());
        this.methodInterceptors.add(new AuthenticatedAnnotationMethodInterceptor());
        this.methodInterceptors.add(new UserAnnotationMethodInterceptor());
        this.methodInterceptors.add(new GuestAnnotationMethodInterceptor());
    }

    public Collection<AuthorizingAnnotationMethodInterceptor> getMethodInterceptors() {
        return this.methodInterceptors;
    }

    public void setMethodInterceptors(Collection<AuthorizingAnnotationMethodInterceptor> collection) {
        this.methodInterceptors = collection;
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingMethodInterceptor
    protected void assertAuthorized(MethodInvocation methodInvocation) throws AuthorizationException {
        Collection<AuthorizingAnnotationMethodInterceptor> methodInterceptors = getMethodInterceptors();
        if (methodInterceptors == null || methodInterceptors.isEmpty()) {
            return;
        }
        for (AuthorizingAnnotationMethodInterceptor authorizingAnnotationMethodInterceptor : methodInterceptors) {
            if (authorizingAnnotationMethodInterceptor.supports(methodInvocation)) {
                authorizingAnnotationMethodInterceptor.assertAuthorized(methodInvocation);
            }
        }
    }
}
