package defpackage;

import java.util.Random;

/* JADX INFO: compiled from: chromium-webapk8.dex-default-8 */
/* JADX INFO: loaded from: assets/webapk8.dex */
public final class X extends ThreadLocal {
    X() {
    }

    @Override // java.lang.ThreadLocal
    public final Object initialValue() {
        return new Random();
    }
}
