package t5;

import java.util.Random;

/* JADX INFO: compiled from: PlatformRandom.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends c {
    @Override // t5.c
    public int b() {
        return c().nextInt();
    }

    public abstract Random c();
}
