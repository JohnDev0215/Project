package c.a.d.p;

import android.content.Context;
import android.text.TextUtils;
import c.a.d.s.h;
import c.a.d.s.l;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
import f.f;
import f.u;
import f.w;
import f.x;
import f.y;
import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: SimInfoUploadHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f3628a = "SimInfoUploadHelper";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3629b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3630c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3631d;

    /* JADX INFO: compiled from: SimInfoUploadHelper.java */
    public class a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f3632a;

        public a(b bVar) {
            this.f3632a = bVar;
        }

        @Override // f.f
        public void onFailure(f.e eVar, IOException iOException) {
            this.f3632a.a(iOException.getMessage());
        }

        @Override // f.f
        public void onResponse(f.e eVar, y yVar) throws IOException {
            this.f3632a.b(yVar.w().T());
        }
    }

    /* JADX INFO: compiled from: SimInfoUploadHelper.java */
    public interface b {
        void a(String str);

        void b(String str);
    }

    public d(Context context) {
        c.a.d.o.d dVar = new c.a.d.o.d();
        dVar.f3609c = l.d();
        dVar.f3611e = l.e();
        b(dVar, context);
    }

    public final String a(Context context) {
        if (c.a.d.d.e().a() != 0) {
            if (h.a(context)) {
                return "WIFI";
            }
            return null;
        }
        int iF = c.a.d.d.e().f();
        if (iF == 2) {
            return "2G";
        }
        if (iF == 3) {
            return "3G";
        }
        if (iF != 4) {
            return null;
        }
        return "4G";
    }

    public final void b(c.a.d.o.d dVar, Context context) {
        e eVar = new e();
        String strD = c.a.d.d.e().d();
        String strE = eVar.e(strD, context);
        if (TextUtils.isEmpty(strE)) {
            BydLogHelper.logE("!!! no host matching for imsi:" + strD);
            return;
        }
        BydLogHelper.logD("SIM card upload host:" + strE.substring(0, 3) + "****-****." + strE.substring(17));
        StringBuilder sb = new StringBuilder();
        sb.append(eVar.b(strE));
        sb.append(eVar.a());
        this.f3629b = sb.toString();
        dVar.f3608b = eVar.f();
        TreeMap treeMap = new TreeMap();
        treeMap.put("token", eVar.h(context));
        treeMap.put("timestamp", dVar.f3611e);
        treeMap.put("randomNumber", dVar.f3609c);
        c.b.b.d dVar2 = new c.b.b.d();
        c cVar = new c();
        cVar.f3621a = c.a.d.s.e.b(context).d();
        cVar.f3622b = c.a.d.d.e().b();
        cVar.f3623c = c.a.d.d.e().d();
        cVar.f3624d = c.a.d.d.e().c();
        this.f3631d = c.a.d.s.e.b(context).a(context);
        cVar.f3625e = this.f3631d + "";
        BydLogHelper.logE("countryCode:" + this.f3631d);
        cVar.f3626f = c.a.d.s.e.b(context).c();
        cVar.f3627g = a(context);
        String strB = c.a.d.s.a.b(dVar2.r(cVar), eVar.g(context));
        treeMap.put("encryData", strB);
        String strA = l.a(treeMap);
        dVar.f3607a = strB;
        dVar.f3610d = strA;
        this.f3630c = dVar2.r(dVar);
    }

    public void c(b bVar) {
        if (this.f3631d == -1) {
            bVar.a("countryCode is -1 !!!");
            return;
        }
        w.a aVar = new w.a();
        w wVarB = aVar.i(this.f3629b).g(x.create(MediaType.JSON, this.f3630c)).b();
        u.b bVar2 = new u.b();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        bVar2.c(10L, timeUnit).g(20L, timeUnit).a().o(wVarB).w(new a(bVar));
    }
}
