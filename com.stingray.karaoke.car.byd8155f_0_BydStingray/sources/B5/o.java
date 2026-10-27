package B5;

import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public final class o extends IOException {
    public final int l;

    public o(String str, int i7, String str2) {
        super("HTTP " + i7 + ": " + str + ". Response: " + str2);
        this.l = i7;
    }
}
