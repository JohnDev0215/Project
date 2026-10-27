package b.c.a;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: compiled from: JsonElement.java */
/* JADX INFO: loaded from: classes3.dex */
public abstract class i {
    public f a() {
        if (d()) {
            return (f) this;
        }
        throw new IllegalStateException("This is not a JSON Array.");
    }

    public k b() {
        if (f()) {
            return (k) this;
        }
        throw new IllegalStateException("Not a JSON Object: " + this);
    }

    public l c() {
        if (g()) {
            return (l) this;
        }
        throw new IllegalStateException("This is not a JSON Primitive.");
    }

    public boolean d() {
        return this instanceof f;
    }

    public boolean e() {
        return this instanceof j;
    }

    public boolean f() {
        return this instanceof k;
    }

    public boolean g() {
        return this instanceof l;
    }

    public boolean h() {
        return false;
    }

    public String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            b.c.a.t.b bVar = new b.c.a.t.b(stringWriter);
            bVar.z(true);
            b.c.a.r.g.b(this, bVar);
            return stringWriter.toString();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
