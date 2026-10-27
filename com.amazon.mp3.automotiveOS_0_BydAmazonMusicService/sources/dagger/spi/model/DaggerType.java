package dagger.spi.model;

import com.google.auto.value.AutoValue;
import com.google.common.base.Equivalence;
import dagger.internal.codegen.xprocessing.XTypes;
import dagger.spi.shaded.androidx.room.compiler.processing.XType;

/* JADX INFO: loaded from: classes7.dex */
@AutoValue
public abstract class DaggerType {
    abstract Equivalence.Wrapper<XType> equivalenceWrapper();

    public XType xprocessing() {
        equivalenceWrapper();
        throw null;
    }

    public final String toString() {
        return XTypes.toStableString(xprocessing());
    }
}
