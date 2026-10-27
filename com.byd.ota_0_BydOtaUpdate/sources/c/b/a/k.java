package c.b.a;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: compiled from: JsonElement.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class k {
    public h a() {
        if (e()) {
            return (h) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public m b() {
        if (g()) {
            return (m) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public n c() {
        if (h()) {
            return (n) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public String d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    public boolean e() {
        return this instanceof h;
    }

    public boolean f() {
        return this instanceof l;
    }

    public boolean g() {
        return this instanceof m;
    }

    public boolean h() {
        return this instanceof n;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            c.b.a.v.b bVar = new c.b.a.v.b(stringWriter);
            bVar.W(true);
            c.b.a.t.g.b(this, bVar);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
