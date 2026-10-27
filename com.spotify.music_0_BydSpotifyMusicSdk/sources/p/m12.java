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
public final class m12 implements io.reactivex.rxjava3.functions.o {
    public final /* synthetic */ n12 c;
    public final /* synthetic */ tx1 e;

    public m12(n12 n12Var, tx1 tx1Var) {
        this.c = n12Var;
        this.e = tx1Var;
    }

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        List<Entity> list;
        View view = (View) obj;
        v03.j(view, Search.Type.VIEW);
        Pager<Entity> pager = view.content;
        if (pager == null || (list = pager.items) == null) {
            return EmptyList.c;
        }
        Entity.Companion companion = Entity.Companion;
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            if (companion.isPresentValidAndPlayable((Entity) obj2)) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = new ArrayList(lu.W(arrayList));
        int i = 0;
        for (Object obj3 : arrayList) {
            int i2 = i + 1;
            if (i < 0) {
                v03.P();
                throw null;
            }
            Entity entity = (Entity) obj3;
            n12 n12Var = this.c;
            n12Var.getClass();
            Referrer referrer = Referrer.RECENTS;
            jh1 jh1VarD = wb1.d(entity, n12Var.c, referrer.a(), Integer.valueOf(i));
            p01 p01VarE = wb1.e(entity);
            p01VarE.E(1);
            p01VarE.D(2);
            String strUri = entity.uri();
            if (strUri != null) {
                kh1 kh1VarS = yn.s(strUri);
                kh1VarS.c = referrer.a();
                kh1VarS.d = Integer.valueOf(i);
                String strA = kh1VarS.a();
                p01VarE.F(n12Var.e.a(strA));
                jh1VarD.a = strA;
            }
            jh1VarD.h = (Bundle) p01VarE.e;
            arrayList2.add(new dg1(jh1VarD, n12Var.d.a(this.e).a(entity.uri())));
            i = i2;
        }
        return arrayList2;
    }
}
