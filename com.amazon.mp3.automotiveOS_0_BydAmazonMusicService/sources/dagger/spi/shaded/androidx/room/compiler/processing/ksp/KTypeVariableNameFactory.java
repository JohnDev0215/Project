package dagger.spi.shaded.androidx.room.compiler.processing.ksp;

import com.squareup.kotlinpoet.TypeName;
import com.squareup.kotlinpoet.TypeVariableName;
import java.util.List;

/* JADX INFO: loaded from: classes7.dex */
final class KTypeVariableNameFactory {
    private KTypeVariableNameFactory() {
    }

    static TypeVariableName newInstance(String str, List<TypeName> list) {
        return TypeVariableName.INSTANCE.of$kotlinpoet(str, list, null);
    }
}
