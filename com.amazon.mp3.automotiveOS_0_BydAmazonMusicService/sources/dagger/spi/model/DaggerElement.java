package dagger.spi.model;

import com.google.auto.value.AutoValue;
import dagger.spi.shaded.androidx.room.compiler.processing.XElement;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class DaggerElement {
    public abstract XElement xprocessing();

    public final String toString() {
        return xprocessing().toString();
    }
}
