package L7;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class j extends k {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ int f4054e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ j(Field field, boolean z6, int i7) {
        super(field, z6);
        this.f4054e = i7;
    }

    @Override // L7.t
    public void a(Object[] objArr) {
        switch (this.f4054e) {
            case 1:
                l9.d.H(this, objArr);
                b(objArr.length == 0 ? null : objArr[0]);
                break;
            default:
                super.a(objArr);
                break;
        }
    }
}
