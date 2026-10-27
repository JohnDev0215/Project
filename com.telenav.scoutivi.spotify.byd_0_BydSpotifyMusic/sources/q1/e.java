package q1;

import java.io.File;
import s1.a;

/* JADX INFO: compiled from: DataCacheWriter.java */
/* JADX INFO: loaded from: classes.dex */
class e<DataType> implements a.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n1.d<DataType> f8961a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final DataType f8962b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final n1.i f8963c;

    e(n1.d<DataType> dVar, DataType datatype, n1.i iVar) {
        this.f8961a = dVar;
        this.f8962b = datatype;
        this.f8963c = iVar;
    }

    @Override // s1.a.b
    public boolean a(File file) {
        return this.f8961a.b(this.f8962b, file, this.f8963c);
    }
}
