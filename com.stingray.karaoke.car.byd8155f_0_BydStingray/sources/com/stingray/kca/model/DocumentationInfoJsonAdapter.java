package com.stingray.kca.model;

import A7.m;
import E5.H;
import E5.q;
import E5.u;
import E5.x;
import G5.f;
import h2.AbstractC1347d;
import io.sentry.C1489n1;
import kotlin.Metadata;
import n7.w;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/stingray/kca/model/DocumentationInfoJsonAdapter;", "LE5/q;", "Lcom/stingray/kca/model/DocumentationInfo;", "LE5/H;", "moshi", "<init>", "(LE5/H;)V", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class DocumentationInfoJsonAdapter extends q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C1489n1 f13085a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final q f13086b;

    public DocumentationInfoJsonAdapter(H h10) {
        m.f(h10, "moshi");
        this.f13085a = C1489n1.z("markdown");
        this.f13086b = h10.c(String.class, w.l, "markdown");
    }

    @Override // E5.q
    public final Object a(u uVar) {
        m.f(uVar, "reader");
        uVar.g();
        String str = null;
        while (uVar.m()) {
            int iF = uVar.F(this.f13085a);
            if (iF == -1) {
                uVar.I();
                uVar.K();
            } else if (iF == 0 && (str = (String) this.f13086b.a(uVar)) == null) {
                throw f.l("markdown", "markdown", uVar);
            }
        }
        uVar.j();
        if (str != null) {
            return new DocumentationInfo(str);
        }
        throw f.f("markdown", "markdown", uVar);
    }

    @Override // E5.q
    public final void d(x xVar, Object obj) {
        DocumentationInfo documentationInfo = (DocumentationInfo) obj;
        m.f(xVar, "writer");
        if (documentationInfo == null) {
            throw new NullPointerException("value_ was null! Wrap in .nullSafe() to write nullable values.");
        }
        xVar.g();
        xVar.k("markdown");
        this.f13086b.d(xVar, documentationInfo.f13084a);
        xVar.i();
    }

    public final String toString() {
        return AbstractC1347d.g(39, "GeneratedJsonAdapter(DocumentationInfo)");
    }
}
