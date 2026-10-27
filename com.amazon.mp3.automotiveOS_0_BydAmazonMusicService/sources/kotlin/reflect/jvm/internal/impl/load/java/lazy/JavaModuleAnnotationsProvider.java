package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.List;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaAnnotation;
import kotlin.reflect.jvm.internal.impl.name.ClassId;

/* JADX INFO: compiled from: JavaModuleAnnotationsProvider.kt */
/* JADX INFO: loaded from: classes7.dex */
public interface JavaModuleAnnotationsProvider {
    List<JavaAnnotation> getAnnotationsForModuleOwnerOfClass(ClassId classId);
}
