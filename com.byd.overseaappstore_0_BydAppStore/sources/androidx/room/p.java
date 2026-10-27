package androidx.room;

import androidx.sqlite.db.SupportSQLiteQuery;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class p implements Runnable {
    public final /* synthetic */ int a;
    public final /* synthetic */ QueryInterceptorDatabase b;
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object d;

    public /* synthetic */ p(QueryInterceptorDatabase queryInterceptorDatabase, Object obj, Object obj2, int i) {
        this.a = i;
        this.b = queryInterceptorDatabase;
        this.c = obj;
        this.d = obj2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.a) {
            case 0:
                this.b.lambda$query$7((String) this.c, (List) this.d);
                break;
            default:
                this.b.lambda$query$8((SupportSQLiteQuery) this.c, (QueryInterceptorProgram) this.d);
                break;
        }
    }
}
