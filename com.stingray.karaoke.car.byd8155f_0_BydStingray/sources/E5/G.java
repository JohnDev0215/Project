package E5;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class G {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f1770a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayDeque f1771b = new ArrayDeque();
    public boolean c;
    public final /* synthetic */ H d;

    public G(H h10) {
        this.d = h10;
    }

    public final IllegalArgumentException a(IllegalArgumentException illegalArgumentException) {
        if (this.c) {
            return illegalArgumentException;
        }
        this.c = true;
        ArrayDeque arrayDeque = this.f1771b;
        if (arrayDeque.size() == 1 && ((F) arrayDeque.getFirst()).f1769b == null) {
            return illegalArgumentException;
        }
        StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
        Iterator itDescendingIterator = arrayDeque.descendingIterator();
        while (itDescendingIterator.hasNext()) {
            F f10 = (F) itDescendingIterator.next();
            sb.append("\nfor ");
            sb.append(f10.f1768a);
            String str = f10.f1769b;
            if (str != null) {
                sb.append(' ');
                sb.append(str);
            }
        }
        return new IllegalArgumentException(sb.toString(), illegalArgumentException);
    }

    public final void b(boolean z6) {
        this.f1771b.removeLast();
        if (this.f1771b.isEmpty()) {
            this.d.f1773b.remove();
            if (z6) {
                synchronized (this.d.c) {
                    try {
                        int size = this.f1770a.size();
                        for (int i7 = 0; i7 < size; i7++) {
                            F f10 = (F) this.f1770a.get(i7);
                            q qVar = (q) this.d.c.put(f10.c, f10.d);
                            if (qVar != null) {
                                f10.d = qVar;
                                this.d.c.put(f10.c, qVar);
                            }
                        }
                    } finally {
                    }
                }
            }
        }
    }
}
