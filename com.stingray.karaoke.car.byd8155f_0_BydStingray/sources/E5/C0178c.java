package E5;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;

/* JADX INFO: renamed from: E5.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0178c extends AbstractC0179d {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final /* synthetic */ int f1794h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public q f1795i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final /* synthetic */ Type[] f1796j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final /* synthetic */ Type f1797k;
    public final /* synthetic */ Set l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Set f1798m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C0178c(Type type, Set set, Object obj, Method method, int i7, boolean z6, Type[] typeArr, Type type2, Set set2, Set set3, int i10) {
        super(type, set, obj, method, i7, 1, z6);
        this.f1794h = i10;
        this.f1796j = typeArr;
        this.f1797k = type2;
        this.l = set2;
        this.f1798m = set3;
    }

    @Override // E5.AbstractC0179d
    public final void a(H h10, C0180e c0180e) {
        switch (this.f1794h) {
            case 0:
                super.a(h10, c0180e);
                Type type = this.f1796j[0];
                Type type2 = this.f1797k;
                boolean zC = M.c(type, type2);
                Set set = this.f1798m;
                this.f1795i = (zC && this.l.equals(set)) ? h10.d(c0180e, type2, set) : h10.c(type2, set, null);
                break;
            default:
                super.a(h10, c0180e);
                Type[] typeArr = this.f1796j;
                boolean zC2 = M.c(typeArr[0], this.f1797k);
                Set set2 = this.l;
                this.f1795i = (zC2 && set2.equals(this.f1798m)) ? h10.d(c0180e, typeArr[0], set2) : h10.c(typeArr[0], set2, null);
                break;
        }
    }

    @Override // E5.AbstractC0179d
    public Object b(u uVar) {
        switch (this.f1794h) {
            case 1:
                return c(this.f1795i.a(uVar));
            default:
                return super.b(uVar);
        }
    }

    @Override // E5.AbstractC0179d
    public void d(x xVar, Object obj) {
        switch (this.f1794h) {
            case 0:
                this.f1795i.d(xVar, c(obj));
                break;
            default:
                super.d(xVar, obj);
                break;
        }
    }
}
