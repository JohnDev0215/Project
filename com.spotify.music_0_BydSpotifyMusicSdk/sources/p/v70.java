package p;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class v70 implements bq, h62 {
    public final /* synthetic */ Object c;
    public final /* synthetic */ Object e;
    public final /* synthetic */ Object k;
    public final /* synthetic */ Object l;

    public /* synthetic */ v70(View view, Object obj, w70 w70Var, bf2 bf2Var) {
        this.c = view;
        this.e = obj;
        this.k = w70Var;
        this.l = bf2Var;
    }

    @Override // p.h62
    public final Object apply(Object obj) {
        j62 j62Var = (j62) this.c;
        String str = (String) this.e;
        Map map = (Map) this.k;
        gh2 gh2Var = (gh2) this.l;
        ae0 ae0Var = j62.n;
        j62Var.getClass();
        return (wt) j62.o0(((SQLiteDatabase) obj).rawQuery(str, new String[0]), new r70(j62Var, map, gh2Var, 3));
    }

    @Override // p.bq
    public final void c() {
        View view = (View) this.c;
        androidx.fragment.app.a aVar = (androidx.fragment.app.a) this.e;
        w70 w70Var = (w70) this.k;
        bf2 bf2Var = (bf2) this.l;
        v03.j(aVar, "this$0");
        v03.j(w70Var, "$animationInfo");
        v03.j(bf2Var, "$operation");
        view.clearAnimation();
        aVar.a.endViewTransition(view);
        w70Var.b();
        if (Log.isLoggable("FragmentManager", 2)) {
            bf2Var.toString();
        }
    }
}
