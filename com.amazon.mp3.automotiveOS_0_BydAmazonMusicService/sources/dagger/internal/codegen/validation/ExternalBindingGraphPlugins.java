package dagger.internal.codegen.validation;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import dagger.spi.BindingGraphPlugin;
import dagger.spi.model.DaggerProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.XFiler;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnv;
import dagger.spi.shaded.androidx.room.compiler.processing.compat.XConverters;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public final class ExternalBindingGraphPlugins {
    private final DiagnosticReporterFactory diagnosticReporterFactory;
    private final XFiler filer;
    private final ImmutableSet<BindingGraphPlugin> legacyPlugins;
    private final ImmutableSet<dagger.spi.model.BindingGraphPlugin> plugins;
    private final XProcessingEnv processingEnv;
    private final Map<String, String> processingOptions;

    ExternalBindingGraphPlugins(ImmutableSet<BindingGraphPlugin> legacyPlugins, ImmutableSet<dagger.spi.model.BindingGraphPlugin> plugins, DiagnosticReporterFactory diagnosticReporterFactory, XFiler filer, XProcessingEnv processingEnv, Map<String, String> processingOptions) {
        this.legacyPlugins = legacyPlugins;
        this.plugins = plugins;
        this.diagnosticReporterFactory = diagnosticReporterFactory;
        this.filer = filer;
        this.processingEnv = processingEnv;
        this.processingOptions = processingOptions;
    }

    public void initializePlugins() {
        this.plugins.forEach(new Consumer() { // from class: dagger.internal.codegen.validation.ExternalBindingGraphPlugins$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.initializePlugin((dagger.spi.model.BindingGraphPlugin) obj);
            }
        });
        this.legacyPlugins.forEach(new Consumer() { // from class: dagger.internal.codegen.validation.ExternalBindingGraphPlugins$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.f$0.initializeLegacyPlugin((BindingGraphPlugin) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializePlugin(dagger.spi.model.BindingGraphPlugin plugin) {
        Map<String, String> mapFilterKeys;
        Set<String> setSupportedOptions = plugin.supportedOptions();
        if (setSupportedOptions.isEmpty()) {
            mapFilterKeys = ImmutableMap.of();
        } else {
            mapFilterKeys = Maps.filterKeys(this.processingOptions, new ExternalBindingGraphPlugins$$ExternalSyntheticLambda2(setSupportedOptions));
        }
        plugin.init(DaggerProcessingEnv.from(this.processingEnv), mapFilterKeys);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initializeLegacyPlugin(BindingGraphPlugin plugin) {
        plugin.initFiler(XConverters.toJavac(this.filer));
        plugin.initTypes(XConverters.toJavac(this.processingEnv).getTypeUtils());
        plugin.initElements(XConverters.toJavac(this.processingEnv).getElementUtils());
        Set<String> setSupportedOptions = plugin.supportedOptions();
        if (setSupportedOptions.isEmpty()) {
            return;
        }
        plugin.initOptions(Maps.filterKeys(this.processingOptions, new ExternalBindingGraphPlugins$$ExternalSyntheticLambda2(setSupportedOptions)));
    }
}
