package T6;

import B8.t;
import N0.q;
import S6.k;
import V6.h;
import V6.i;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import n3.z;
import w9.g;
import w9.l;
import w9.m;
import w9.p;
import w9.r;
import w9.s;
import w9.u;
import w9.v;

/* JADX INFO: loaded from: classes.dex */
public final class c extends S6.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ArrayList f8747a = new ArrayList(0);

    public static void e(t tVar, String str, String str2, z zVar) {
        tVar.M();
        int iF0 = tVar.f0();
        k kVar = (k) tVar.f809p;
        StringBuilder sb = kVar.l;
        sb.append((char) 160);
        sb.append('\n');
        ((Y4.e) ((q) tVar.f808n).f4400n).getClass();
        kVar.b(sb.length(), str2);
        sb.append((CharSequence) str2);
        tVar.M();
        kVar.a((char) 160);
        e.f8754g.b((S6.c) tVar.o, str);
        tVar.p0(zVar, iF0);
        tVar.A(zVar);
    }

    @Override // S6.a
    public final void a(TextView textView) {
        if (textView.getMovementMethod() == null) {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    @Override // S6.a
    public final void b(TextView textView, SpannableStringBuilder spannableStringBuilder) {
        h[] hVarArr = (h[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), h.class);
        if (hVarArr != null) {
            TextPaint paint = textView.getPaint();
            for (h hVar : hVarArr) {
                hVar.o = (int) (paint.measureText(hVar.f9545m) + 0.5f);
            }
        }
        i[] iVarArr = (i[]) spannableStringBuilder.getSpans(0, spannableStringBuilder.length(), i.class);
        if (iVarArr != null) {
            for (i iVar : iVarArr) {
                spannableStringBuilder.removeSpan(iVar);
            }
        }
        i iVar2 = new i();
        new WeakReference(textView);
        spannableStringBuilder.setSpan(iVar2, 0, spannableStringBuilder.length(), 18);
    }

    @Override // S6.a
    public final void c(S6.c cVar) {
        U6.a aVar = new U6.a(1);
        cVar.b(w9.t.class, new U6.a(7));
        cVar.b(w9.e.class, new U6.a(3));
        cVar.b(w9.b.class, new U6.a(0));
        cVar.b(w9.d.class, new U6.a(2));
        cVar.b(w9.f.class, aVar);
        cVar.b(l.class, aVar);
        cVar.b(p.class, new U6.a(6));
        cVar.b(w9.h.class, new U6.a(4));
        cVar.b(m.class, new U6.a(5));
        cVar.b(v.class, new U6.a(8));
    }

    @Override // S6.a
    public final void d(S6.c cVar) {
        cVar.a(u.class, new b(this));
        cVar.a(w9.t.class, new a(6));
        cVar.a(w9.e.class, new a(7));
        cVar.a(w9.b.class, new a(8));
        cVar.a(w9.d.class, new a(9));
        cVar.a(w9.f.class, new a(10));
        cVar.a(l.class, new a(11));
        cVar.a(w9.k.class, new a(12));
        cVar.a(w9.c.class, new a(14));
        cVar.a(w9.q.class, new a(14));
        cVar.a(p.class, new a(13));
        cVar.a(v.class, new a(0));
        cVar.a(w9.h.class, new a(1));
        cVar.a(s.class, new a(2));
        cVar.a(g.class, new a(3));
        cVar.a(r.class, new a(4));
        cVar.a(m.class, new a(5));
    }
}
