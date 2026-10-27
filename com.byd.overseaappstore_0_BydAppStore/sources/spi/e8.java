package spi;

import androidx.annotation.NonNull;
import java.security.MessageDigest;

/* JADX INFO: loaded from: classes.dex */
public final class e8 implements ig {
    public static final e8 b = new e8();

    private e8() {
    }

    @Override // spi.ig
    public final void a(@NonNull MessageDigest messageDigest) {
    }

    public final String toString() {
        return "EmptySignature";
    }
}
