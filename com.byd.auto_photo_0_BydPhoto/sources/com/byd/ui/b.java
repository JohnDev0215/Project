package com.byd.ui;

import com.byd.taskmanager.b.c;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a f2926a = new a(0, null);

    class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f2927a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        com.byd.taskmanager.b.a f2928b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        a f2929c;

        public a(int i, com.byd.taskmanager.b.a aVar) {
            this.f2927a = i;
            this.f2928b = aVar;
        }
    }

    private void a(int i, com.byd.taskmanager.b.a aVar) {
        a aVar2 = this.f2926a;
        if (aVar2.f2928b == null) {
            aVar2.f2927a = i;
            aVar2.f2928b = aVar;
            return;
        }
        while (true) {
            a aVar3 = aVar2.f2929c;
            if (aVar3 == null) {
                break;
            }
            if (aVar2.f2927a == i) {
                aVar2.f2928b = aVar;
                return;
            } else if (aVar3 == null) {
                break;
            } else {
                aVar2 = aVar3;
            }
        }
        aVar2.f2929c = new a(i, aVar);
    }

    public <T> void b(Object obj, int i, com.byd.taskmanager.b.a<T> aVar, c<T> cVar, boolean z) {
        if (cVar != null) {
            if (z) {
                cVar.registUiObject(obj);
            }
            aVar.setCallback(cVar);
        }
        aVar.submitInUIThread();
        aVar.setTaskName(obj.getClass().getName() + obj.hashCode() + i);
        a(i, aVar);
    }

    public void c(int i) {
        com.byd.taskmanager.b.a aVarE = e(i);
        if (aVarE != null) {
            com.byd.taskmanager.a.e().a(aVarE);
        }
    }

    public void d(int i) {
        com.byd.taskmanager.a.e().d(e(i));
    }

    public com.byd.taskmanager.b.a e(int i) {
        for (a aVar = this.f2926a; aVar != null; aVar = aVar.f2929c) {
            com.byd.taskmanager.b.a aVar2 = aVar.f2928b;
            if (aVar2 == null) {
                return null;
            }
            if (aVar.f2927a == i) {
                return aVar2;
            }
        }
        return null;
    }

    public boolean f(int i) {
        return e(i) != null;
    }

    public void g(Object obj) {
        for (a aVar = this.f2926a; aVar != null; aVar = aVar.f2929c) {
            com.byd.taskmanager.b.a aVar2 = aVar.f2928b;
            if (aVar2 == null) {
                return;
            }
            ((c) aVar2.getCallback()).registUiObject(obj);
        }
    }

    public void h() {
        while (this.f2926a.f2928b != null) {
            com.byd.taskmanager.a.e().a(this.f2926a.f2928b);
            this.f2926a.f2928b.release();
            a aVar = this.f2926a;
            a aVar2 = aVar.f2929c;
            if (aVar2 != null) {
                this.f2926a = aVar2;
            } else {
                aVar.f2927a = 0;
                aVar.f2928b = null;
            }
        }
    }

    public void i() {
        for (a aVar = this.f2926a; aVar != null; aVar = aVar.f2929c) {
            com.byd.taskmanager.b.a aVar2 = aVar.f2928b;
            if (aVar2 == null) {
                return;
            }
            ((c) aVar2.getCallback()).unregistUiObject();
        }
    }
}
