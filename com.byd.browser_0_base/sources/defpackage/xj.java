package defpackage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class xj implements Function {
    public final /* synthetic */ int a;

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        switch (this.a) {
            case 0:
                return ((Class) obj).getName();
            case 1:
                return ((Integer) obj).toString();
            case 2:
                return (List) obj;
            default:
                return new ArrayList(((Map) obj).keySet());
        }
    }
}
