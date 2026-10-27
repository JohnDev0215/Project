package dagger.spi;

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Filer;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/* JADX INFO: loaded from: classes7.dex */
public interface BindingGraphPlugin {
    default void initElements(Elements elements) {
    }

    default void initFiler(Filer filer) {
    }

    default void initOptions(Map<String, String> options) {
    }

    default void initTypes(Types types) {
    }

    default Set<String> supportedOptions() {
        return Collections.emptySet();
    }
}
