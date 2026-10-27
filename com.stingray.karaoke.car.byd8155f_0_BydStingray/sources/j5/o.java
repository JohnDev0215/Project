package j5;

import java.io.IOException;
import java.io.StringWriter;
import m5.O;
import m5.c0;
import r5.C2298b;

/* JADX INFO: loaded from: classes.dex */
public abstract class o {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            C2298b c2298b = new C2298b(stringWriter);
            c2298b.f21866s = 1;
            c0.f19219z.getClass();
            O.d(c2298b, this);
            return stringWriter.toString();
        } catch (IOException e10) {
            throw new AssertionError(e10);
        }
    }
}
