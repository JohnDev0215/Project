package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class k1 implements p11 {
    public final o11 b(CharSequence charSequence) {
        int length = charSequence.length() * 2;
        if (length < 0) {
            throw new IllegalArgumentException(v03.z("expectedInputSize must be >= 0 but was %s", Integer.valueOf(length)));
        }
        zu zuVarA = a();
        zuVarA.getClass();
        int length2 = charSequence.length();
        for (int i = 0; i < length2; i++) {
            zuVarA.z0(charSequence.charAt(i));
        }
        return zuVarA.l0();
    }
}
