package Y;

/* JADX INFO: loaded from: classes.dex */
public final class o extends n {
    public final /* synthetic */ int o;

    @Override // java.util.Iterator
    public final Object next() {
        switch (this.o) {
            case 0:
                int i7 = this.f10604n;
                this.f10604n = i7 + 2;
                Object[] objArr = this.l;
                return new a(objArr[i7], objArr[i7 + 1]);
            case 1:
                int i10 = this.f10604n;
                this.f10604n = i10 + 2;
                return this.l[i10];
            default:
                int i11 = this.f10604n;
                this.f10604n = i11 + 2;
                return this.l[i11 + 1];
        }
    }
}
