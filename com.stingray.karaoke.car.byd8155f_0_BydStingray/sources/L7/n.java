package L7;

import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes.dex */
public final class n extends o {

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final /* synthetic */ int f4056g;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ n(Field field, boolean z6, boolean z10, int i7) {
        super(field, z6, z10);
        this.f4056g = i7;
    }

    @Override // L7.o, L7.t
    public void a(Object[] objArr) {
        switch (this.f4056g) {
            case 1:
                super.a(objArr);
                b(objArr.length == 0 ? null : objArr[0]);
                break;
            default:
                super.a(objArr);
                break;
        }
    }
}
