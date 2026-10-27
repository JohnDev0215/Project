package i4;

import A4.q;
import d4.C1070c;
import java.util.ArrayDeque;

/* JADX INFO: renamed from: i4.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1410c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayDeque f15749a;

    public C1410c(int i7) {
        switch (i7) {
            case 1:
                char[] cArr = q.f432a;
                this.f15749a = new ArrayDeque(0);
                break;
            default:
                this.f15749a = new ArrayDeque();
                break;
        }
    }

    public synchronized void a(C1070c c1070c) {
        c1070c.f13360b = null;
        c1070c.c = null;
        this.f15749a.offer(c1070c);
    }
}
