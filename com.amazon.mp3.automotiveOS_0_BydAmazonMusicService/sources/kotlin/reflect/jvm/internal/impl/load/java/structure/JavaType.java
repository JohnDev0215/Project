package kotlin.reflect.jvm.internal.impl.load.java.structure;

import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.load.java.structure.ListBasedJavaAnnotationOwner;
import kotlin.reflect.jvm.internal.impl.name.FqName;

/* JADX INFO: compiled from: javaTypes.kt */
/* JADX INFO: loaded from: classes7.dex */
public interface JavaType extends ListBasedJavaAnnotationOwner {

    /* JADX INFO: compiled from: javaTypes.kt */
    public static final class DefaultImpls {
        public static JavaAnnotation findAnnotation(JavaType javaType, FqName fqName) {
            Intrinsics.checkNotNullParameter(javaType, "this");
            Intrinsics.checkNotNullParameter(fqName, "fqName");
            return ListBasedJavaAnnotationOwner.DefaultImpls.findAnnotation(javaType, fqName);
        }
    }
}
