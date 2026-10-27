package dagger.spi.model;

import com.google.auto.value.AutoValue;
import com.google.common.base.Equivalence;
import dagger.internal.codegen.xprocessing.XAnnotations;
import dagger.spi.shaded.androidx.room.compiler.processing.XAnnotation;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class DaggerAnnotation {
    abstract Equivalence.Wrapper<XAnnotation> equivalenceWrapper();

    public XAnnotation xprocessing() {
        equivalenceWrapper();
        throw null;
    }

    public final String toString() {
        return XAnnotations.toString(xprocessing());
    }
}
