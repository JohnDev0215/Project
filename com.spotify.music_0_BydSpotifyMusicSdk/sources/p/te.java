package p;

import com.google.common.base.Optional;

/* JADX INFO: loaded from: classes.dex */
public final class te implements ri0 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ te(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    public final Optional a() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                Optional optionalB = Optional.b(((as2) obj).c);
                v03.i(optionalB, "fromNullable(\n          …sername\n                )");
                return optionalB;
            default:
                Optional optionalB2 = Optional.b(((n) ((ut) obj)).a);
                v03.i(optionalB2, "fromNullable(\n          …eviceId\n                )");
                return optionalB2;
        }
    }
}
