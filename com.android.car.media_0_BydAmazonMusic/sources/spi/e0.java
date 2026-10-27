package spi;

import com.android.car.media.common.MediaItemMetadata;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class e0 implements Predicate {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ e0(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                return ((MediaItemMetadata) obj).g().equals((String) this.b);
            case 1:
                return ((MediaItemMetadata) obj).g().equals((String) this.b);
            default:
                w8 w8Var = com.android.car.ui.pluginsupport.c.a;
                Objects.requireNonNull((b9) obj);
                return true;
        }
    }
}
