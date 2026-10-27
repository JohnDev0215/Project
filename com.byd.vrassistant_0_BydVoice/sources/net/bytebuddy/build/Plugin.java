package net.bytebuddy.build;

import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.matcher.ElementMatcher;

/* JADX INFO: loaded from: classes3.dex */
public interface Plugin extends ElementMatcher<TypeDescription> {
    DynamicType.Builder<?> apply(DynamicType.Builder<?> builder, TypeDescription typeDescription);
}
