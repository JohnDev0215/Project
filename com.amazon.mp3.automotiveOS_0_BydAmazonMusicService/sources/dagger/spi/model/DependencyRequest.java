package dagger.spi.model;

import com.google.auto.value.AutoValue;
import java.util.Optional;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class DependencyRequest {
    public abstract Key key();

    public abstract RequestKind kind();

    public abstract Optional<DaggerElement> requestElement();
}
