package dagger.spi.model;

import com.google.auto.value.AutoValue;
import com.google.common.base.Joiner;
import dagger.internal.codegen.xprocessing.XAnnotations;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation;
import java.util.Optional;
import java.util.function.Function;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class Key {
    public abstract Optional<Object> multibindingContributionIdentifier();

    public abstract Optional<DaggerAnnotation> qualifier();

    public abstract DaggerType type();

    public final String toString() {
        return Joiner.on(' ').skipNulls().join(qualifier().map(new Function() { // from class: dagger.spi.model.Key$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DaggerAnnotation) obj).xprocessing();
            }
        }).map(new Function() { // from class: dagger.spi.model.Key$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XAnnotations.toStableString((XAnnotation) obj);
            }
        }).orElse(null), type(), multibindingContributionIdentifier().orElse(null));
    }
}
