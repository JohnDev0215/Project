package dagger.internal.codegen.validation;

import dagger.internal.codegen.base.ElementFormatter;
import dagger.internal.codegen.binding.DependencyRequestFormatter;

/* JADX INFO: loaded from: classes7.dex */
public final class DiagnosticMessageGenerator$Factory {
    private final DependencyRequestFormatter dependencyRequestFormatter;
    private final ElementFormatter elementFormatter;

    DiagnosticMessageGenerator$Factory(DependencyRequestFormatter dependencyRequestFormatter, ElementFormatter elementFormatter) {
        this.dependencyRequestFormatter = dependencyRequestFormatter;
        this.elementFormatter = elementFormatter;
    }
}
