package org.chromium.content_public.browser;

import defpackage.nw2;
import defpackage.on2;
import java.util.Locale;
import java.util.Map;
import org.chromium.content_public.common.ResourceRequestBody;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public final class LoadUrlParams {
    public String a;
    public Origin b;
    public int c;
    public int d;
    public on2 e;
    public Map<String, String> f;
    public String g;
    public int h;
    public ResourceRequestBody i;
    public String j;
    public String k;
    public String l;
    public boolean m;
    public boolean n;
    public boolean o;
    public long p;
    public long q;
    public boolean r;
    public boolean s;
    public nw2<Long> t;

    public LoadUrlParams() {
        throw null;
    }

    public LoadUrlParams(String str, int i) {
        this.a = str;
        this.d = i;
        this.c = 0;
        this.h = 0;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }

    public final String a() {
        if (this.f == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : this.f.entrySet()) {
            if (sb.length() > 0) {
                sb.append("\n");
            }
            sb.append(entry.getKey().toLowerCase(Locale.US));
            sb.append(":");
            sb.append(entry.getValue());
        }
        return sb.toString();
    }
}
