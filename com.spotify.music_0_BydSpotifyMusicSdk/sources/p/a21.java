package p;

import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.views.View;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public final class a21 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ b21 c;
    public final /* synthetic */ int e;
    public final /* synthetic */ int k;
    public final /* synthetic */ tx1 l;

    public a21(b21 b21Var, int i, int i2, tx1 tx1Var) {
        this.c = b21Var;
        this.e = i;
        this.k = i2;
        this.l = tx1Var;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [kotlin.collections.EmptyList] */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.Iterable, java.util.List] */
    /* JADX WARN: Type inference failed for: r6v3, types: [java.util.ArrayList] */
    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        List<Entity> list;
        ?? arrayList;
        List<Entity> list2;
        View view = (View) obj;
        v03.j(view, Search.Type.VIEW);
        b21 b21Var = this.c;
        b21Var.getClass();
        Pager<Entity> pager = view.content;
        if (pager == null || (list = pager.items) == null) {
            return EmptyList.c;
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj2 : list) {
            if (((Entity) obj2).hasChildren()) {
                arrayList2.add(obj2);
            }
        }
        List<Entity> listL0 = ou.l0(arrayList2, this.e);
        ArrayList arrayList3 = new ArrayList();
        int size = 0;
        for (Entity entity : listL0) {
            Pager<Entity> children = entity.getChildren();
            if (children == null || (list2 = children.items) == null) {
                arrayList = EmptyList.c;
            } else {
                Entity.Companion companion = Entity.Companion;
                ArrayList arrayList4 = new ArrayList();
                for (Object obj3 : list2) {
                    if (companion.isPresentValidAndPlayable((Entity) obj3)) {
                        arrayList4.add(obj3);
                    }
                }
                List listL02 = ou.l0(arrayList4, this.k);
                arrayList = new ArrayList(lu.W(listL02));
                int i = 0;
                for (Object obj4 : listL02) {
                    int i2 = i + 1;
                    if (i < 0) {
                        v03.P();
                        throw null;
                    }
                    Entity entity2 = (Entity) obj4;
                    int i3 = i + size;
                    Referrer referrer = Referrer.HOME;
                    jh1 jh1VarD = wb1.d(entity2, b21Var.d, referrer.a(), Integer.valueOf(i3));
                    p01 p01VarE = wb1.e(entity2);
                    p01VarE.H(entity.name());
                    p01VarE.D(2);
                    p01VarE.E(1);
                    String strUri = entity2.uri();
                    if (strUri != null) {
                        kh1 kh1VarS = yn.s(strUri);
                        kh1VarS.c = referrer.a();
                        kh1VarS.d = Integer.valueOf(i3);
                        String strA = kh1VarS.a();
                        p01VarE.F(b21Var.g.a(strA));
                        jh1VarD.a = strA;
                    }
                    jh1VarD.h = (Bundle) p01VarE.e;
                    arrayList.add(new dg1(jh1VarD, b21Var.e.a(this.l).a(entity2.uri())));
                    i = i2;
                }
            }
            size += arrayList.size();
            nu.X(arrayList, arrayList3);
        }
        return arrayList3;
    }
}
