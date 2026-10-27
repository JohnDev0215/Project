package dagger.internal.codegen.validation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import dagger.internal.codegen.compileroption.CompilerOptions;
import dagger.spi.model.BindingGraphPlugin;
import dagger.spi.model.DaggerProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public final class ValidationBindingGraphPlugins {
    private final CompilerOptions compilerOptions;
    private final DiagnosticReporterFactory diagnosticReporterFactory;
    private final ImmutableSet<BindingGraphPlugin> plugins;
    private final XProcessingEnv processingEnv;
    private final Map<String, String> processingOptions;

    ValidationBindingGraphPlugins(ImmutableSet<BindingGraphPlugin> plugins, DiagnosticReporterFactory diagnosticReporterFactory, XProcessingEnv processingEnv, CompilerOptions compilerOptions, Map<String, String> processingOptions) {
        this.plugins = plugins;
        this.diagnosticReporterFactory = diagnosticReporterFactory;
        this.processingEnv = processingEnv;
        this.compilerOptions = compilerOptions;
        this.processingOptions = processingOptions;
    }

    public void initializePlugins() {
        final DaggerProcessingEnv daggerProcessingEnvFrom = DaggerProcessingEnv.from(this.processingEnv);
        this.plugins.forEach(new Consumer() { // from class: dagger.internal.codegen.validation.ValidationBindingGraphPlugins$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$initializePlugins$1(daggerProcessingEnvFrom, (BindingGraphPlugin) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializePlugins$1(DaggerProcessingEnv daggerProcessingEnv, BindingGraphPlugin bindingGraphPlugin) {
        bindingGraphPlugin.init(daggerProcessingEnv, pluginOptions(bindingGraphPlugin));
    }

    private ImmutableMap<String, String> pluginOptions(BindingGraphPlugin plugin) {
        Set<String> setSupportedOptions = plugin.supportedOptions();
        if (setSupportedOptions.isEmpty()) {
            return ImmutableMap.of();
        }
        return ImmutableMap.copyOf(Maps.filterKeys(this.processingOptions, new ExternalBindingGraphPlugins$$ExternalSyntheticLambda2(setSupportedOptions)));
    }
}
