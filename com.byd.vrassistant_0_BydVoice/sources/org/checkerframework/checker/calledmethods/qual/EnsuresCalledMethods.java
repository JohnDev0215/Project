package org.checkerframework.checker.calledmethods.qual;

import com.byd.common.bean.WuwInfoBean;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import org.checkerframework.framework.qual.PostconditionAnnotation;
import org.checkerframework.framework.qual.QualifierArgument;

/* JADX INFO: loaded from: classes4.dex */
@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@PostconditionAnnotation(qualifier = CalledMethods.class)
public @interface EnsuresCalledMethods {
    @QualifierArgument(WuwInfoBean.ValueKey)
    String[] methods();

    String[] value();
}
