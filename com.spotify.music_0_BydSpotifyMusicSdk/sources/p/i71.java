package p;

import java.io.IOException;
import java.io.StringWriter;

/* JADX INFO: loaded from: classes.dex */
public abstract class i71 {
    public final String toString() {
        try {
            StringWriter stringWriter = new StringWriter();
            z71 z71Var = new z71(stringWriter);
            z71Var.n = true;
            com.google.gson.internal.bind.a.y.c(z71Var, this);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
