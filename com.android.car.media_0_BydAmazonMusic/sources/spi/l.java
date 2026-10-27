package spi;

import java.util.function.Consumer;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class l implements Consumer {
    public final /* synthetic */ int a;
    public final /* synthetic */ m b;

    public /* synthetic */ l(m mVar, int i) {
        this.a = i;
        this.b = mVar;
    }

    @Override // java.util.function.Consumer
    public final void accept(Object obj) {
        switch (this.a) {
            case 0:
                m mVar = this.b;
                String str = (String) obj;
                mVar.m = str;
                mVar.j.b(str);
                break;
            case 1:
                m mVar2 = this.b;
                qa qaVar = (qa) obj;
                mVar2.n = mVar2.b.indexOf(qaVar);
                mVar2.j.e(qaVar.h);
                break;
            default:
                this.b.g();
                break;
        }
    }
}
