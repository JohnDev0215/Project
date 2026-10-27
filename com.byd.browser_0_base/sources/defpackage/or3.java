package defpackage;

import android.util.JsonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: WebPaymentIntentHelperType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class or3 {
    public final pr3 a;
    public final qr3 b;

    public or3(pr3 pr3Var, qr3 qr3Var) {
        this.a = pr3Var;
        this.b = qr3Var;
    }

    public final void a(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        pr3 pr3Var = this.a;
        if (pr3Var != null) {
            jsonWriter.name("total");
            jsonWriter.beginObject();
            jsonWriter.name("label").value("");
            jsonWriter.name("amount");
            pr3Var.a.a(jsonWriter);
            jsonWriter.endObject();
        } else {
            jsonWriter.name("total").nullValue();
        }
        jsonWriter.name("supportedMethods").beginArray();
        qr3 qr3Var = this.b;
        jsonWriter.value(qr3Var.a);
        jsonWriter.endArray();
        jsonWriter.name("data").value(qr3Var.b);
        jsonWriter.endObject();
    }
}
