package a.b.a.a.k;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f73a = new a();
    public final a.b.a.a.k.e.b b;
    public final String c;
    public final long d;
    public final int e;
    public final int f;
    public final byte[] g;

    public static final class a {
    }

    public c(String str, byte[][] bArr) {
        a.b.a.a.k.e.b bVar;
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("codec");
        if (i == 0) {
            bVar = a.b.a.a.k.e.b.Undefined;
        } else {
            if (i != 1) {
                throw new IllegalArgumentException("No ImageCodec with value " + i);
            }
            bVar = a.b.a.a.k.e.b.JPEG;
        }
        this.b = bVar;
        String string = jSONObject.getString("mimeType");
        Intrinsics.checkExpressionValueIsNotNull(string, "jsonObject.getString(\"mimeType\")");
        this.c = string;
        this.d = jSONObject.getLong("serverUtcTimeMillis");
        this.e = jSONObject.getInt("width");
        this.f = jSONObject.getInt("height");
        this.g = bArr[jSONObject.getInt("data")];
    }

    public final a.b.a.a.k.e.b a() {
        return this.b;
    }

    public final byte[] b() {
        return this.g;
    }

    public final int c() {
        return this.f;
    }

    public final String d() {
        return this.c;
    }

    public final long e() {
        return this.d;
    }

    public final int f() {
        return this.e;
    }
}
