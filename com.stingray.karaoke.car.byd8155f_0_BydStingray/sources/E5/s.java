package E5;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class s extends IOException {
    public s(int i7, IOException iOException, String str) {
        super(str + ", status code: " + i7, iOException);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public s(String str) {
        super(str);
        A7.m.f(str, "message");
    }

    public s(IndexOutOfBoundsException indexOutOfBoundsException) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", indexOutOfBoundsException);
    }
}
