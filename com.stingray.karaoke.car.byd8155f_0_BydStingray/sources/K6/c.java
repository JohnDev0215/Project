package K6;

import B8.t;
import N0.L;
import N0.q;
import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c implements InterfaceC2782k {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f3680m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ L f3681n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f3682p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ int f3683q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f3684r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ Object f3685s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ Object f3686t;

    public /* synthetic */ c(long j3, long j10, int i7, int i10, L l, Integer num, boolean z6, InterfaceC2772a interfaceC2772a) {
        this.f3680m = j3;
        this.o = j10;
        this.f3682p = i7;
        this.f3683q = i10;
        this.f3681n = l;
        this.f3685s = num;
        this.f3684r = z6;
        this.f3686t = interfaceC2772a;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        switch (this.l) {
            case 0:
                Context context = (Context) obj;
                A7.m.f(context, "ctx");
                TextView textView = new TextView(context);
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.f3686t;
                if (interfaceC2772a != null) {
                    textView.setOnClickListener(new e(interfaceC2772a, 0));
                }
                Integer num = (Integer) this.f3685s;
                if (num != null) {
                    textView.setId(num.intValue());
                }
                w0.c.h0(textView, this.f3680m, this.f3681n, this.o, this.f3683q, this.f3682p, this.f3684r);
                return textView;
            default:
                TextView textView2 = (TextView) obj;
                A7.m.f(textView2, "textView");
                String strC = new Q8.g("\\[\\p{P}(.+?)\\p{P}]\\(#.+?\\)").c((String) this.f3685s, new A3.c(12));
                S6.b bVar = (S6.b) this.f3686t;
                List list = bVar.d;
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    ((S6.a) it.next()).getClass();
                }
                t tVar = bVar.f8069b;
                if (strC == null) {
                    throw new NullPointerException("input must not be null");
                }
                t9.f fVar = new t9.f((ArrayList) tVar.f808n, (l9.n) tVar.f809p, (ArrayList) tVar.o);
                int i7 = 0;
                while (true) {
                    int length = strC.length();
                    int i10 = i7;
                    while (true) {
                        if (i10 < length) {
                            char cCharAt = strC.charAt(i10);
                            if (cCharAt != '\n' && cCharAt != '\r') {
                                i10++;
                            }
                        } else {
                            i10 = -1;
                        }
                    }
                    if (i10 == -1) {
                        if (strC.length() > 0 && (i7 == 0 || i7 < strC.length())) {
                            fVar.i(strC.substring(i7));
                        }
                        fVar.f(fVar.f22536n);
                        m2.b bVar2 = new m2.b(fVar.f22534k, fVar.f22535m);
                        fVar.f22533j.getClass();
                        t9.j jVar = new t9.j(bVar2);
                        Iterator it2 = fVar.o.iterator();
                        while (it2.hasNext()) {
                            ((y9.a) it2.next()).f(jVar);
                        }
                        w9.i iVar = (w9.i) fVar.l.f22523b;
                        Iterator it3 = ((ArrayList) tVar.f807m).iterator();
                        if (it3.hasNext()) {
                            it3.next().getClass();
                            throw new ClassCastException();
                        }
                        Iterator it4 = list.iterator();
                        while (it4.hasNext()) {
                            ((S6.a) it4.next()).getClass();
                        }
                        io.sentry.internal.debugmeta.c cVar = bVar.c;
                        S6.c cVar2 = new S6.c(2);
                        S6.c cVar3 = (S6.c) cVar.f16569m;
                        t tVar2 = new t((q) cVar.f16570n, cVar2, new S6.k(), Collections.unmodifiableMap(cVar3.f8070a), new Y4.e(9));
                        tVar2.r0(iVar);
                        Iterator it5 = list.iterator();
                        while (it5.hasNext()) {
                            ((S6.a) it5.next()).getClass();
                        }
                        S6.k kVar = (S6.k) tVar2.f809p;
                        SpannableStringBuilder jVar2 = new S6.j(kVar.l);
                        for (S6.i iVar2 : kVar.f8076m) {
                            jVar2.setSpan(iVar2.f8074a, iVar2.f8075b, iVar2.c, iVar2.d);
                        }
                        if (TextUtils.isEmpty(jVar2) && !TextUtils.isEmpty(strC)) {
                            jVar2 = new SpannableStringBuilder(strC);
                        }
                        Iterator it6 = list.iterator();
                        while (it6.hasNext()) {
                            ((S6.a) it6.next()).b(textView2, jVar2);
                        }
                        textView2.setText(jVar2, bVar.f8068a);
                        Iterator it7 = list.iterator();
                        while (it7.hasNext()) {
                            ((S6.a) it7.next()).a(textView2);
                        }
                        w0.c.h0(textView2, this.f3680m, this.f3681n, this.o, this.f3682p, this.f3683q, this.f3684r);
                        return m7.q.f19267a;
                    }
                    fVar.i(strC.substring(i7, i10));
                    i7 = i10 + 1;
                    if (i7 < strC.length() && strC.charAt(i10) == '\r' && strC.charAt(i7) == '\n') {
                        i7 = i10 + 2;
                    }
                }
                break;
        }
    }

    public /* synthetic */ c(String str, S6.b bVar, long j3, L l, long j10, int i7, int i10, boolean z6) {
        this.f3685s = str;
        this.f3686t = bVar;
        this.f3680m = j3;
        this.f3681n = l;
        this.o = j10;
        this.f3682p = i7;
        this.f3683q = i10;
        this.f3684r = z6;
    }
}
