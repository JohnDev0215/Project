package dagger.internal.codegen;

import com.google.common.collect.ImmutableList;
import dagger.internal.codegen.base.SourceFileGenerator;
import dagger.internal.codegen.binding.InjectBindingRegistry;
import dagger.internal.codegen.validation.ExternalBindingGraphPlugins;
import dagger.internal.codegen.validation.ValidationBindingGraphPlugins;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnvConfig;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
final class DelegateComponentProcessor {
    static final XProcessingEnvConfig PROCESSING_ENV_CONFIG = new XProcessingEnvConfig.Builder().disableAnnotatedElementValidation(true).getInstance();
    Set<Object> clearableCaches;
    ExternalBindingGraphPlugins externalBindingGraphPlugins;
    SourceFileGenerator<Object> factoryGenerator;
    InjectBindingRegistry injectBindingRegistry;
    SourceFileGenerator<Object> membersInjectorGenerator;
    ImmutableList<Object> processingSteps;
    ValidationBindingGraphPlugins validationBindingGraphPlugins;

    DelegateComponentProcessor() {
    }

    public Iterable<Object> processingSteps() {
        this.validationBindingGraphPlugins.initializePlugins();
        this.externalBindingGraphPlugins.initializePlugins();
        return this.processingSteps;
    }
}
