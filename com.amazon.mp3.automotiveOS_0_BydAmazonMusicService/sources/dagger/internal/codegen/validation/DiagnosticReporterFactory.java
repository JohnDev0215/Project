package dagger.internal.codegen.validation;

import dagger.spi.shaded.androidx.room.compiler.processing.XMessager;

/* JADX INFO: loaded from: classes7.dex */
final class DiagnosticReporterFactory {
    private final DiagnosticMessageGenerator$Factory diagnosticMessageGeneratorFactory;
    private final XMessager messager;

    DiagnosticReporterFactory(XMessager messager, DiagnosticMessageGenerator$Factory diagnosticMessageGeneratorFactory) {
        this.messager = messager;
        this.diagnosticMessageGeneratorFactory = diagnosticMessageGeneratorFactory;
    }
}
