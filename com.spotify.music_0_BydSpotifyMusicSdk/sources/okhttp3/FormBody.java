package okhttp3;

import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.internal.Util;
import p.fo;
import p.io;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class FormBody extends RequestBody {
    public static final MediaType d;
    public final List b;
    public final List c;

    public static final class Builder {
        public final Charset a = null;
        public final ArrayList b = new ArrayList();
        public final ArrayList c = new ArrayList();

        public final void a(String str, String str2) {
            v03.j(str, "name");
            v03.j(str2, "value");
            ArrayList arrayList = this.b;
            HttpUrl.Companion companion = HttpUrl.k;
            arrayList.add(HttpUrl.Companion.a(companion, str, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, this.a, 91));
            this.c.add(HttpUrl.Companion.a(companion, str2, 0, 0, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, false, this.a, 91));
        }
    }

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
        MediaType.d.getClass();
        d = MediaType.Companion.a("application/x-www-form-urlencoded");
    }

    public FormBody(ArrayList arrayList, ArrayList arrayList2) {
        v03.j(arrayList, "encodedNames");
        v03.j(arrayList2, "encodedValues");
        this.b = Util.y(arrayList);
        this.c = Util.y(arrayList2);
    }

    @Override // okhttp3.RequestBody
    public final long a() {
        return d(null, true);
    }

    @Override // okhttp3.RequestBody
    public final MediaType b() {
        return d;
    }

    @Override // okhttp3.RequestBody
    public final void c(io ioVar) throws EOFException {
        d(ioVar, false);
    }

    public final long d(io ioVar, boolean z) throws EOFException {
        fo foVarD;
        if (z) {
            foVarD = new fo();
        } else {
            v03.h(ioVar);
            foVarD = ioVar.d();
        }
        List list = this.b;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                foVarD.W0(38);
            }
            foVarD.d1((String) list.get(i));
            foVarD.W0(61);
            foVarD.d1((String) this.c.get(i));
        }
        if (!z) {
            return 0L;
        }
        long j = foVarD.e;
        foVarD.X();
        return j;
    }
}
