package dagger.spi.model;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes7.dex */
public interface BindingGraphPlugin {
    default void init(DaggerProcessingEnv processingEnv, Map<String, String> options) {
    }

    default Set<String> supportedOptions() {
        return Collections.emptySet();
    }
}
