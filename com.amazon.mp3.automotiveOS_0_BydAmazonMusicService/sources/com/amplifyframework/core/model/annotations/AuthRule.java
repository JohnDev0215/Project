package com.amplifyframework.core.model.annotations;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.ModelOperation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* JADX INFO: loaded from: classes5.dex */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthRule {
    AuthStrategy allow();

    String groupClaim() default "";

    String[] groups() default {};

    String groupsField() default "";

    String identityClaim() default "";

    ModelOperation[] operations() default {};

    String ownerField() default "";

    String provider() default "";
}
