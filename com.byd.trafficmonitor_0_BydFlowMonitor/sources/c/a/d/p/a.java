package c.a.d.p;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.webs.okhttp.constant.MediaType;
import f.d;
import f.f;
import f.u;
import f.w;
import f.x;
import f.y;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* JADX INFO: compiled from: FlowRequestHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public String f3612a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3613b;

    /* JADX INFO: renamed from: c.a.d.p.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: FlowRequestHelper.java */
    public class C0078a implements f {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ b f3614a;

        public C0078a(b bVar) {
            this.f3614a = bVar;
        }

        @Override // f.f
        public void onFailure(f.e eVar, IOException iOException) {
            this.f3614a.a(iOException.getMessage());
        }

        @Override // f.f
        public void onResponse(f.e eVar, y yVar) throws IOException {
            this.f3614a.b(yVar.w().T());
        }
    }

    /* JADX INFO: compiled from: FlowRequestHelper.java */
    public interface b {
        void a(String str);

        void b(String str);
    }

    public a(Context context) {
        c.a.d.o.b bVar = new c.a.d.o.b();
        bVar.f3594a = c.a.d.s.e.b(context).d();
        bVar.f3595b = c.a.d.d.e().b();
        c(new c.b.b.d().r(bVar), context);
    }

    public final String a(String str) {
        return c.a.d.s.f.f(str.getBytes());
    }

    public final String b(List<String> list) {
        String str = "";
        for (int i2 = 0; i2 < list.size(); i2++) {
            str = i2 == list.size() - 1 ? str + list.get(i2) : str + list.get(i2) + "&";
        }
        return str;
    }

    public final void c(String str, Context context) {
        c.a.d.p.b bVar = new c.a.d.p.b();
        String strE = bVar.e(c.a.d.d.e().d());
        BydLogHelper.logD("query flow  host : ****-***" + strE.substring(strE.lastIndexOf("-"), strE.lastIndexOf("-") + 3) + ".**.byd.**");
        this.f3612a = bVar.d(strE) + bVar.a();
        String strF = bVar.f();
        String str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String str3 = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + (((int) (Math.random() * 900.0d)) + 100);
        ArrayList arrayList = new ArrayList();
        arrayList.add("appKey=" + bVar.b(context));
        arrayList.add("requestId=" + str3);
        arrayList.add("serverName=" + bVar.g());
        arrayList.add("requestInfo=" + str);
        arrayList.add("requestTime=" + str2);
        arrayList.add("sign=" + e(bVar.a(), arrayList, bVar.c(context), strF));
        this.f3613b = b(arrayList);
    }

    public void d(b bVar) {
        w.a aVar = new w.a();
        new u.b().a().o(aVar.i(this.f3612a).g(x.create(MediaType.FORM, this.f3613b)).c(new d.a().c().a()).b()).w(new C0078a(bVar));
    }

    public final String e(String str, List<String> list, String str2, String str3) {
        StringBuilder sb = new StringBuilder();
        sb.append(str3);
        sb.append(str);
        StringBuilder sb2 = new StringBuilder();
        Collections.sort(list);
        for (String str4 : list) {
            if (!str4.startsWith("sign=")) {
                sb2.append(str4);
                sb2.append("&");
            }
        }
        if (sb2.length() > 0) {
            sb.append("?");
            sb.append(sb2.substring(0, sb2.length() - 1));
            sb.append(str2);
        } else {
            sb.append("?");
            sb.append(str2);
        }
        return a(sb.toString());
    }
}
