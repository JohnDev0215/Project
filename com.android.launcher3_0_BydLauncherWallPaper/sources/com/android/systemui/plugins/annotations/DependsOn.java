package com.android.systemui.plugins.annotations;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX INFO: loaded from: classes4.dex */
@Repeatable(Dependencies.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface DependsOn {
    Class<?> target();
}
