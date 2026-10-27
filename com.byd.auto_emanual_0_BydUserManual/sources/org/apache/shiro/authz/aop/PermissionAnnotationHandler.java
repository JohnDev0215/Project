package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;

/* JADX INFO: loaded from: classes.dex */
public class PermissionAnnotationHandler extends AuthorizingAnnotationHandler {
    public PermissionAnnotationHandler() {
        super(RequiresPermissions.class);
    }

    protected String[] getAnnotationValue(Annotation annotation) {
        return ((RequiresPermissions) annotation).value();
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingAnnotationHandler
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        if (annotation instanceof RequiresPermissions) {
            RequiresPermissions requiresPermissions = (RequiresPermissions) annotation;
            String[] annotationValue = getAnnotationValue(annotation);
            Subject subject = getSubject();
            if (annotationValue.length == 1) {
                subject.checkPermission(annotationValue[0]);
                return;
            }
            if (Logical.AND.equals(requiresPermissions.logical())) {
                getSubject().checkPermissions(annotationValue);
                return;
            }
            if (Logical.OR.equals(requiresPermissions.logical())) {
                boolean z = false;
                for (String str : annotationValue) {
                    if (getSubject().isPermitted(str)) {
                        z = true;
                    }
                }
                if (z) {
                    return;
                }
                getSubject().checkPermission(annotationValue[0]);
            }
        }
    }
}
