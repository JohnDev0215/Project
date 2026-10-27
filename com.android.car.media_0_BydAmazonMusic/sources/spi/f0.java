package spi;

import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class f0 implements Predicate {
    public final /* synthetic */ int a;
    public final /* synthetic */ Map b;
    public final /* synthetic */ Function c;

    public /* synthetic */ f0(Map map, Function function, int i) {
        this.a = i;
        this.b = map;
        this.c = function;
    }

    @Override // java.util.function.Predicate
    public final boolean test(Object obj) {
        switch (this.a) {
            case 0:
                if (this.b.putIfAbsent(this.c.apply(obj), Boolean.TRUE) != null) {
                    break;
                }
                break;
            default:
                if (this.b.putIfAbsent(this.c.apply(obj), Boolean.TRUE) != null) {
                    break;
                }
                break;
        }
        return false;
    }
}
