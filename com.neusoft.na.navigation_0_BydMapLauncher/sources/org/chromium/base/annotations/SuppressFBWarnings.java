package org.chromium.base.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes3.dex */
@Retention(RetentionPolicy.CLASS)
public @interface SuppressFBWarnings {
    String justification() default "";

    String[] value() default {};
}
