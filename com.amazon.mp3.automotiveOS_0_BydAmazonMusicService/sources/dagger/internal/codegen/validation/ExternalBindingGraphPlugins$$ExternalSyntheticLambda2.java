package dagger.internal.codegen.validation;

import com.google.common.base.Predicate;
import java.util.Set;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes7.dex */
public final /* synthetic */ class ExternalBindingGraphPlugins$$ExternalSyntheticLambda2 implements Predicate {
    public final /* synthetic */ Set f$0;

    @Override // com.google.common.base.Predicate
    public final boolean apply(Object obj) {
        return this.f$0.contains((String) obj);
    }
}
