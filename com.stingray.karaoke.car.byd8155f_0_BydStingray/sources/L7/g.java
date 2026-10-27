package L7;

import A7.D;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class g extends t {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4052e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ g(Member member, Type type, Class cls, Type[] typeArr, int i7) {
        super(member, type, cls, typeArr);
        this.f4052e = i7;
    }

    @Override // L7.e
    public final Object q(Object[] objArr) {
        switch (this.f4052e) {
            case 0:
                l9.d.H(this, objArr);
                Constructor constructor = (Constructor) this.f4062a;
                D d = new D(2);
                d.j(objArr);
                d.f(null);
                ArrayList arrayList = d.l;
                return constructor.newInstance(arrayList.toArray(new Object[arrayList.size()]));
            default:
                l9.d.H(this, objArr);
                return ((Constructor) this.f4062a).newInstance(Arrays.copyOf(objArr, objArr.length));
        }
    }
}
