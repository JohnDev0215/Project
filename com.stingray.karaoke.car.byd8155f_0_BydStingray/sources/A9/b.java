package A9;

import F8.AbstractC0287w;
import T.C0735d;
import T.S;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0987z;
import b4.C0995a;
import c7.InterfaceC1035a;
import h4.InterfaceC1365g;
import h8.m;
import h8.n;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicReference;
import k4.C1685c;
import k4.q;
import k4.r;
import k4.w;
import k4.z;
import o8.C2057b;
import o8.C2061f;
import x.x0;
import z8.InterfaceC2796d;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements R7.a, V3.e, n, r, InterfaceC2796d {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f476m;

    public /* synthetic */ b(Object obj, int i7) {
        this.l = i7;
        this.f476m = obj;
    }

    public static /* synthetic */ void K0(int i7) {
        String str = i7 != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i7 != 1 ? 3 : 2];
        if (i7 != 1) {
            objArr[0] = "annotations";
        } else {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        }
        if (i7 != 1) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/descriptors/annotations/AnnotatedImpl";
        } else {
            objArr[1] = "getAnnotations";
        }
        if (i7 != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public static /* synthetic */ void L0(int i7) {
        String str = (i7 == 1 || i7 == 2) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i7 == 1 || i7 == 2) ? 2 : 3];
        if (i7 == 1 || i7 == 2) {
            objArr[0] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[0] = "receiverType";
        }
        if (i7 == 1) {
            objArr[1] = "getType";
        } else if (i7 != 2) {
            objArr[1] = "kotlin/reflect/jvm/internal/impl/resolve/scopes/receivers/AbstractReceiverValue";
        } else {
            objArr[1] = "getOriginal";
        }
        if (i7 != 1 && i7 != 2) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i7 != 1 && i7 != 2) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    @Override // k4.r
    public q A(w wVar) {
        return new C1685c((z) this.f476m, 2);
    }

    @Override // h8.n
    public void B0(Object obj) {
        if (obj instanceof String) {
            ((ArrayList) this.f476m).add((String) obj);
        }
    }

    @Override // V3.e
    public List F0() {
        return (List) this.f476m;
    }

    @Override // V3.e
    public boolean I0() {
        List list = (List) this.f476m;
        if (list.isEmpty()) {
            return true;
        }
        return list.size() == 1 && ((C0995a) list.get(0)).c();
    }

    public abstract void M0(InterfaceC0987z interfaceC0987z);

    public void N0(String str, Object... objArr) {
        ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) ((ConcurrentHashMap) this.f476m).get(str);
        if (concurrentLinkedQueue != null) {
            Iterator it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                ((InterfaceC1035a) it.next()).q(objArr);
            }
        }
    }

    public abstract EnumC0981t O0();

    public abstract Object P0();

    public abstract Object Q0();

    public void R0(String str, InterfaceC1035a interfaceC1035a) {
        ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) ((ConcurrentHashMap) this.f476m).get(str);
        if (concurrentLinkedQueue != null) {
            Iterator it = concurrentLinkedQueue.iterator();
            while (it.hasNext()) {
                InterfaceC1035a interfaceC1035a2 = (InterfaceC1035a) it.next();
                if (interfaceC1035a.equals(interfaceC1035a2) ? true : interfaceC1035a2 instanceof c7.b ? interfaceC1035a.equals(((c7.b) interfaceC1035a2).f12410b) : false) {
                    it.remove();
                    return;
                }
            }
        }
    }

    public void S0(InterfaceC1365g interfaceC1365g) {
        ArrayDeque arrayDeque = (ArrayDeque) this.f476m;
        if (arrayDeque.size() < 20) {
            arrayDeque.offer(interfaceC1365g);
        }
    }

    public void T0(String str, InterfaceC1035a interfaceC1035a) {
        ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f476m;
        ConcurrentLinkedQueue concurrentLinkedQueue = (ConcurrentLinkedQueue) concurrentHashMap.get(str);
        if (concurrentLinkedQueue == null) {
            concurrentLinkedQueue = new ConcurrentLinkedQueue();
            ConcurrentLinkedQueue concurrentLinkedQueue2 = (ConcurrentLinkedQueue) concurrentHashMap.putIfAbsent(str, concurrentLinkedQueue);
            if (concurrentLinkedQueue2 != null) {
                concurrentLinkedQueue = concurrentLinkedQueue2;
            }
        }
        concurrentLinkedQueue.add(interfaceC1035a);
    }

    public void U0(String str, InterfaceC1035a interfaceC1035a) {
        T0(str, new c7.b(this, str, interfaceC1035a));
    }

    public abstract void V0(InterfaceC0987z interfaceC0987z);

    @Override // h8.n
    public m W(C2057b c2057b) {
        return null;
    }

    public abstract void W0(Object obj);

    public abstract void X0(x0 x0Var);

    public abstract void Y0();

    public abstract void Z0(String[] strArr);

    @Override // z8.InterfaceC2796d
    public AbstractC0287w b() {
        AbstractC0287w abstractC0287w = (AbstractC0287w) this.f476m;
        if (abstractC0287w != null) {
            return abstractC0287w;
        }
        L0(1);
        throw null;
    }

    @Override // h8.n
    public void i() {
        Z0((String[]) ((ArrayList) this.f476m).toArray(new String[0]));
    }

    @Override // h8.n
    public void i0(C2057b c2057b, C2061f c2061f) {
    }

    @Override // R7.a
    public R7.h k() {
        R7.h hVar = (R7.h) this.f476m;
        if (hVar != null) {
            return hVar;
        }
        K0(1);
        throw null;
    }

    @Override // h8.n
    public void o0(t8.f fVar) {
    }

    public String toString() {
        switch (this.l) {
            case 3:
                StringBuilder sb = new StringBuilder();
                List list = (List) this.f476m;
                if (!list.isEmpty()) {
                    sb.append("values=");
                    sb.append(Arrays.toString(list.toArray()));
                }
                return sb.toString();
            default:
                return super.toString();
        }
    }

    public b(R7.h hVar) {
        this.l = 2;
        if (hVar != null) {
            this.f476m = hVar;
        } else {
            K0(0);
            throw null;
        }
    }

    public b(AbstractC0287w abstractC0287w) {
        this.l = 10;
        if (abstractC0287w != null) {
            this.f476m = abstractC0287w;
        } else {
            L0(0);
            throw null;
        }
    }

    public b(int i7) {
        this.l = i7;
        switch (i7) {
            case 5:
                this.f476m = new ConcurrentHashMap();
                break;
            case 6:
                char[] cArr = A4.q.f432a;
                this.f476m = new ArrayDeque(20);
                break;
            case 7:
                this.f476m = new ArrayList();
                break;
            case 8:
            default:
                this.f476m = new AtomicReference(null);
                break;
            case 9:
                this.f476m = C0735d.O(Boolean.FALSE, S.f8224q);
                break;
        }
    }
}
