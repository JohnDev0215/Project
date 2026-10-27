package defpackage;

import android.util.JsonWriter;
import java.io.IOException;

/* JADX INFO: compiled from: WebPaymentIntentHelperType.java */
/* JADX INFO: loaded from: classes3.dex */
public final class nr3 {
    public final String a;
    public final String b;

    public nr3(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final void a(JsonWriter jsonWriter) throws IOException {
        jsonWriter.beginObject();
        jsonWriter.name("currency").value(this.a);
        jsonWriter.name("value").value(this.b);
        jsonWriter.endObject();
    }
}
