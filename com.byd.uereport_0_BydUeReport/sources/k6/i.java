package k6;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: compiled from: JsonElement.java */
/* JADX INFO: loaded from: classes2.dex */
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
            q6.b bVar = new q6.b(stringWriter);
            bVar.N(true);
            m6.g.b(this, bVar);
            return stringWriter.toString();
        } catch (IOException e8) {
            throw new AssertionError(e8);
        }
    }
}
