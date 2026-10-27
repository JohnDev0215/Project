package b.c.a.d;

import a.b.k.i;
import android.content.res.Resources;
import androidx.databinding.ViewDataBinding;
import b.c.a.d.b;
import f.a.a.c;
import f.a.a.m;
import f.a.a.q;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;

/* JADX INFO: loaded from: classes.dex */
public abstract class a<T extends ViewDataBinding, VM extends b> extends i {
    public T p;
    public VM q;
    public boolean r;
    public Resources s;

    public a() {
        getClass().getSimpleName();
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
    }

    @Override // a.b.k.i, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        if (this.s == null) {
            this.s = new b.c.a.g.i(this, super.getResources());
        }
        return this.s;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:74:0x026d  */
    @Override // a.b.k.i, a.l.d.e, androidx.activity.ComponentActivity, a.h.c.b, android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onCreate(android.os.Bundle r14) {
        /*
            Method dump skipped, instruction units count: 780
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: b.c.a.d.a.onCreate(android.os.Bundle):void");
    }

    @Override // a.b.k.i, a.l.d.e, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (this.r) {
            c cVarB = c.b();
            synchronized (cVarB) {
                List<Class<?>> list = cVarB.f2540b.get(this);
                if (list != null) {
                    Iterator<Class<?>> it = list.iterator();
                    while (it.hasNext()) {
                        CopyOnWriteArrayList<q> copyOnWriteArrayList = cVarB.f2539a.get(it.next());
                        if (copyOnWriteArrayList != null) {
                            int size = copyOnWriteArrayList.size();
                            int i = 0;
                            while (i < size) {
                                q qVar = copyOnWriteArrayList.get(i);
                                if (qVar.f2588a == this) {
                                    qVar.f2590c = false;
                                    copyOnWriteArrayList.remove(i);
                                    i--;
                                    size--;
                                }
                                i++;
                            }
                        }
                    }
                    cVarB.f2540b.remove(this);
                } else {
                    cVarB.r.a(Level.WARNING, "Subscriber to unregister was not registered before: " + getClass());
                }
            }
        }
    }

    @Override // a.l.d.e, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // a.l.d.e, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    public final boolean u(Class cls) {
        this.r = false;
        for (Method method : cls.getDeclaredMethods()) {
            if (method.isAnnotationPresent(m.class)) {
                return true;
            }
        }
        if (cls.getSuperclass() != null) {
            return u(cls.getSuperclass());
        }
        return false;
    }
}
