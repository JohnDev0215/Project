package B5;

import Q7.N;
import T.C0746i0;
import android.content.Context;
import android.content.SharedPreferences;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import l8.InterfaceC1788f;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class K {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f669a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f670b;
    public final Object c;
    public Object d;

    public K(InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, N n10) {
        this.f670b = interfaceC1788f;
        this.c = c0746i0;
        this.d = n10;
    }

    public abstract L a(LinkedHashMap linkedHashMap);

    public abstract C2058c b();

    public L c() {
        if (((L) this.d) == null) {
            String string = ((SharedPreferences) this.f670b).getString((String) this.c, null);
            if (D5.g.g(string)) {
                return null;
            }
            try {
                m mVar = m.f725a;
                if (string == null) {
                    throw new IllegalArgumentException("json == null");
                }
                if (string.length() == 0) {
                    throw new IllegalArgumentException("json empty");
                }
                this.d = a(mVar.a(new StringReader(string)));
            } catch (IOException unused) {
                return null;
            }
        }
        return (L) this.d;
    }

    public void d(L l) {
        this.d = l;
        m mVar = m.f725a;
        StringWriter stringWriter = new StringWriter();
        try {
            mVar.f(l, stringWriter);
            ((SharedPreferences) this.f670b).edit().putString((String) this.c, stringWriter.toString()).apply();
        } catch (IOException e10) {
            throw new AssertionError(e10);
        }
    }

    public String toString() {
        switch (this.f669a) {
            case 1:
                return getClass().getSimpleName() + ": " + b();
            default:
                return super.toString();
        }
    }

    public K(Context context, String str, String str2) {
        this.f670b = D5.g.d(context, str2);
        this.c = str;
    }
}
