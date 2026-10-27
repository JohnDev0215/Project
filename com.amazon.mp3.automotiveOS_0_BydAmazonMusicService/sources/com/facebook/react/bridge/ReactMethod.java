package com.facebook.react.bridge;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes6.dex */
@Retention(RetentionPolicy.RUNTIME)
public @interface ReactMethod {
    boolean isBlockingSynchronousMethod() default false;
}
