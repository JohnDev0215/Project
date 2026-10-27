package p;

import android.net.Uri;
import android.os.Bundle;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.common.uri.SpotifyUri$Kind;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.music.R;
import com.spotify.webapi.service.models.Entity;
import com.spotify.webapi.service.models.Pager;
import com.spotify.webapi.service.models.PlaylistSimple;
import com.spotify.webapi.service.models.Search;
import com.spotify.webapi.service.models.views.Link;
import com.spotify.webapi.service.models.views.View;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class bo implements tb1 {
    public final Bundle a;

    public bo() {
        p01 p01Var = new p01(25);
        p01Var.D(2);
        this.a = (Bundle) p01Var.e;
    }

    public final void e(Entity entity, ArrayList arrayList, ki1 ki1Var, int i) {
        v03.j(entity, Search.Type.LINK);
        v03.j(ki1Var, "mediaUriUtil");
        String strHref = entity.href();
        dg1 dg1Var = null;
        String lastPathSegment = strHref != null ? Uri.parse(strHref).getLastPathSegment() : null;
        if (lastPathSegment != null) {
            jh1 jh1Var = new jh1();
            String string = new if2(SpotifyUri$Kind.GENRE, lastPathSegment, null).toString();
            v03.i(string, "uri.toString()");
            String strA = Referrer.BROWSE.a();
            Integer numValueOf = Integer.valueOf(i);
            Uri.Builder builderEncodedPath = new Uri.Builder().encodedPath(string);
            if (strA != null && strA.length() != 0) {
                builderEncodedPath.appendQueryParameter("ref", strA);
            }
            if (numValueOf != null) {
                builderEncodedPath.appendQueryParameter("pos", String.valueOf(numValueOf));
            }
            String string2 = builderEncodedPath.build().toString();
            v03.i(string2, "builder.build().toString()");
            jh1Var.a = string2;
            jh1Var.b = entity.name();
            jh1Var.h = this.a;
            jh1Var.f = wb1.a(entity.images(), ki1Var, MediaUriUtil$Transformation.k, null, "ICON_OVERLAY");
            dg1Var = new dg1(jh1Var, 1);
        }
        if (dg1Var != null) {
            arrayList.add(dg1Var);
        }
    }

    public final void f(List list, ArrayList arrayList, ki1 ki1Var, lh1 lh1Var, w40 w40Var) {
        List list2;
        v03.j(list, "contentItems");
        v03.j(ki1Var, "mediaUriUtil");
        v03.j(w40Var, "customBrowseActionHandler");
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Entity entity = (Entity) it.next();
            int size = arrayList.size();
            if (entity instanceof PlaylistSimple) {
                String strUri = entity.uri();
                dg1 dg1Var = null;
                if (strUri != null) {
                    kh1 kh1VarS = yn.s(strUri);
                    kh1VarS.b(Referrer.BROWSE);
                    kh1VarS.d = Integer.valueOf(size);
                    String strA = kh1VarS.a();
                    ArrayList arrayListA = w40Var.a(strA);
                    jh1 jh1Var = new jh1();
                    jh1Var.a = strA;
                    jh1Var.b = entity.name();
                    jh1Var.g = Uri.parse(strUri);
                    p01 p01Var = new p01(25);
                    p01Var.E(1);
                    p01Var.F(arrayListA);
                    jh1Var.h = (Bundle) p01Var.e;
                    jh1Var.f = wb1.a(entity.images(), ki1Var, MediaUriUtil$Transformation.k, ki1Var.a(R.drawable.playlist_placeholder), null);
                    dg1Var = new dg1(jh1Var, lh1Var.a(strUri));
                }
                if (dg1Var != null) {
                    arrayList.add(dg1Var);
                }
            } else if (entity instanceof Link) {
                e(entity, arrayList, ki1Var, size);
            } else if (entity instanceof View) {
                Pager<Entity> pager = ((View) entity).content;
                if (pager == null || (list2 = pager.items) == null) {
                    list2 = EmptyList.c;
                }
                List list3 = list2;
                if (!list3.isEmpty()) {
                    f(list3, arrayList, ki1Var, lh1Var, w40Var);
                }
            }
        }
    }
}
