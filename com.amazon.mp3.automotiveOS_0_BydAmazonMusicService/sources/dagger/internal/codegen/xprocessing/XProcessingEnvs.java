package dagger.internal.codegen.xprocessing;

import com.squareup.javapoet.ClassName;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;

/* JADX INFO: loaded from: classes7.dex */
public final class XProcessingEnvs {
    public static XType wrapType(ClassName wrapper, XType type, XProcessingEnv processingEnv) {
        return processingEnv.getDeclaredType(processingEnv.requireTypeElement(wrapper), type);
    }

    private XProcessingEnvs() {
    }
}
