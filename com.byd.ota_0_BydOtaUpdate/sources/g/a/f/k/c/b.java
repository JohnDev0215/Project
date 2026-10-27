package g.a.f.k.c;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bouncycastle.util.io.pem.PemGenerationException;

/* JADX INFO: loaded from: classes.dex */
public class b implements c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final List f5741a = Collections.unmodifiableList(new ArrayList());

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f5742b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public List f5743c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f5744d;

    public b(String str, List list, byte[] bArr) {
        this.f5742b = str;
        this.f5743c = Collections.unmodifiableList(list);
        this.f5744d = bArr;
    }

    public b(String str, byte[] bArr) {
        this(str, f5741a, bArr);
    }

    @Override // g.a.f.k.c.c
    public b a() throws PemGenerationException {
        return this;
    }

    public byte[] b() {
        return this.f5744d;
    }

    public List c() {
        return this.f5743c;
    }

    public String d() {
        return this.f5742b;
    }
}
