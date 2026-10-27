package java9.util.stream;

import java9.util.function.Supplier;

/* JADX INFO: loaded from: classes3.dex */
final /* synthetic */ class Collectors$$Lambda$19 implements Supplier {
    private final CharSequence arg$1;
    private final CharSequence arg$2;
    private final CharSequence arg$3;

    private Collectors$$Lambda$19(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        this.arg$1 = charSequence;
        this.arg$2 = charSequence2;
        this.arg$3 = charSequence3;
    }

    public static Supplier lambdaFactory$(CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3) {
        return new Collectors$$Lambda$19(charSequence, charSequence2, charSequence3);
    }

    @Override // java9.util.function.Supplier
    public Object get() {
        return Collectors.lambda$joining$11(this.arg$1, this.arg$2, this.arg$3);
    }
}
