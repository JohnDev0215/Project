package org.apache.shiro.authz.aop;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;

/* JADX INFO: loaded from: classes.dex */
public class RoleAnnotationHandler extends AuthorizingAnnotationHandler {
    public RoleAnnotationHandler() {
        super(RequiresRoles.class);
    }

    @Override // org.apache.shiro.authz.aop.AuthorizingAnnotationHandler
    public void assertAuthorized(Annotation annotation) throws AuthorizationException {
        if (annotation instanceof RequiresRoles) {
            RequiresRoles requiresRoles = (RequiresRoles) annotation;
            String[] strArrValue = requiresRoles.value();
            if (strArrValue.length == 1) {
                getSubject().checkRole(strArrValue[0]);
                return;
            }
            if (Logical.AND.equals(requiresRoles.logical())) {
                getSubject().checkRoles(Arrays.asList(strArrValue));
                return;
            }
            if (Logical.OR.equals(requiresRoles.logical())) {
                boolean z = false;
                for (String str : strArrValue) {
                    if (getSubject().hasRole(str)) {
                        z = true;
                    }
                }
                if (z) {
                    return;
                }
                getSubject().checkRole(strArrValue[0]);
            }
        }
    }
}
