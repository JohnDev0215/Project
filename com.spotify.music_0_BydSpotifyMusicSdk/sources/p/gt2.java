package p;

import android.os.Build;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class gt2 {
    public final int a;
    public final Class b;
    public final int c;
    public final int d;

    public gt2(int i, Class cls, int i2, int i3) {
        this.a = i;
        this.b = cls;
        this.d = i2;
        this.c = i3;
    }

    public final Object a(View view) {
        if (Build.VERSION.SDK_INT < this.c) {
            Object tag = view.getTag(this.a);
            if (this.b.isInstance(tag)) {
                return tag;
            }
            return null;
        }
        int i = ((et2) this).e;
        switch (i) {
            case 0:
                switch (i) {
                    case 0:
                        return Boolean.valueOf(st2.d(view));
                    default:
                        return Boolean.valueOf(st2.c(view));
                }
            case 1:
                switch (i) {
                    case 1:
                        return st2.b(view);
                    default:
                        return ut2.b(view);
                }
            case 2:
                switch (i) {
                    case 1:
                        return st2.b(view);
                    default:
                        return ut2.b(view);
                }
            default:
                switch (i) {
                    case 0:
                        return Boolean.valueOf(st2.d(view));
                    default:
                        return Boolean.valueOf(st2.c(view));
                }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:23:0x0065. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(android.view.View r6, java.lang.Object r7) {
        /*
            Method dump skipped, instruction units count: 376
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p.gt2.b(android.view.View, java.lang.Object):void");
    }
}
