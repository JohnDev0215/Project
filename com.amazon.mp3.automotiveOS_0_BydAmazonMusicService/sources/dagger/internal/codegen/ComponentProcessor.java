package dagger.internal.codegen;

import com.google.auto.service.AutoService;
import com.google.common.collect.ImmutableSet;
import dagger.spi.BindingGraphPlugin;
import dagger.spi.shaded.androidx.room.compiler.processing.XProcessingEnvConfig;
import dagger.spi.shaded.androidx.room.compiler.processing.javac.JavacBasicAnnotationProcessor;
import java.util.Map;
import java.util.Optional;
import javax.annotation.processing.Processor;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: classes7.dex */
@AutoService({Processor.class})
public final class ComponentProcessor extends JavacBasicAnnotationProcessor {
    private final DelegateComponentProcessor delegate;
    private final Optional<ImmutableSet<BindingGraphPlugin>> legacyTestingPlugins;
    private final Optional<ImmutableSet<dagger.spi.model.BindingGraphPlugin>> testingPlugins;

    public ComponentProcessor() {
        this(Optional.empty(), Optional.empty());
    }

    private ComponentProcessor(Optional<ImmutableSet<dagger.spi.model.BindingGraphPlugin>> testingPlugins, Optional<ImmutableSet<BindingGraphPlugin>> legacyTestingPlugins) {
        super(new Function1() { // from class: dagger.internal.codegen.ComponentProcessor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ComponentProcessor.lambda$new$0((Map) obj);
            }
        });
        this.delegate = new DelegateComponentProcessor();
        this.testingPlugins = testingPlugins;
        this.legacyTestingPlugins = legacyTestingPlugins;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ XProcessingEnvConfig lambda$new$0(Map map) {
        return DelegateComponentProcessor.PROCESSING_ENV_CONFIG;
    }

    @Override // dagger.spi.shaded.androidx.room.compiler.processing.XBasicAnnotationProcessor
    public Iterable<Object> processingSteps() {
        return this.delegate.processingSteps();
    }
}
