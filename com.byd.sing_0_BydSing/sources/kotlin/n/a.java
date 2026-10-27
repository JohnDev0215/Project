package kotlin.n;

import java.util.Timer;

/* JADX INFO: compiled from: Timer.kt */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final Timer a(String str, boolean z) {
        return str == null ? new Timer(z) : new Timer(str, z);
    }
}
