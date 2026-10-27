package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

/* JADX INFO: compiled from: ThrottlingNotificationScheduler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class o93 {
    public final PriorityQueue<zz1> a = new PriorityQueue<>(5, new Comparator() { // from class: n93
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            zz1 zz1Var = (zz1) obj;
            zz1 zz1Var2 = (zz1) obj2;
            int i = zz1Var.b;
            int i2 = zz1Var2.b;
            return i == i2 ? (int) (zz1Var.d - zz1Var2.d) : i - i2;
        }
    });
    public final Handler b = new Handler(Looper.getMainLooper());
    public boolean c;

    /* JADX INFO: compiled from: ThrottlingNotificationScheduler.java */
    public static class a {
        public static final o93 a = new o93();
    }

    public static void a(o93 o93Var) {
        zz1 zz1VarPoll = o93Var.a.poll();
        if (zz1VarPoll == null) {
            o93Var.c = false;
        } else {
            zz1VarPoll.c.run();
            o93Var.b.postDelayed(new es3(3, o93Var), 350L);
        }
    }

    public final zz1 b(Object obj) {
        Iterator<zz1> it = this.a.iterator();
        while (it.hasNext()) {
            zz1 next = it.next();
            if (next.a.equals(obj)) {
                it.remove();
                return next;
            }
        }
        return null;
    }
}
