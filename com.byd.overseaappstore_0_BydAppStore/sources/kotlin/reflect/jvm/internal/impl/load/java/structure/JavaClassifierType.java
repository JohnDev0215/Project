package kotlin.reflect.jvm.internal.impl.load.java.structure;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface JavaClassifierType extends JavaType {
    @Nullable
    JavaClassifier getClassifier();

    @NotNull
    String getClassifierQualifiedName();

    @NotNull
    String getPresentableText();

    @NotNull
    List<JavaType> getTypeArguments();

    boolean isRaw();
}
