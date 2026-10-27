package X;

import A7.o;
import java.util.Collection;
import java.util.List;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class b extends o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f9948m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Collection f9949n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ b(int i7, Collection collection) {
        super(1);
        this.f9948m = i7;
        this.f9949n = collection;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.f9948m) {
            case 0:
                return Boolean.valueOf(this.f9949n.contains(obj));
            case 1:
                return Boolean.valueOf(this.f9949n.contains(obj));
            default:
                return Boolean.valueOf(((List) obj).retainAll(this.f9949n));
        }
    }
}
