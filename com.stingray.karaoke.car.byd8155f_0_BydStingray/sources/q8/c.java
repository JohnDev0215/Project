package Q8;

import java.util.Iterator;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c implements P8.k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final CharSequence f7217a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7218b;
    public final int c;
    public final InterfaceC2785n d;

    public c(CharSequence charSequence, int i7, int i10, InterfaceC2785n interfaceC2785n) {
        A7.m.f(charSequence, "input");
        this.f7217a = charSequence;
        this.f7218b = i7;
        this.c = i10;
        this.d = interfaceC2785n;
    }

    @Override // P8.k
    public final Iterator iterator() {
        return new b(this);
    }
}
