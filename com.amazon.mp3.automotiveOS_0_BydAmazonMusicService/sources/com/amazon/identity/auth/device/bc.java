package com.amazon.identity.auth.device;

import android.util.Log;
import com.amazon.identity.kcpsdk.common.ParseError;
import com.amazon.identity.kcpsdk.common.WebResponseParser;
import com.amazon.music.destination.parser.ParserUtil;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public abstract class bc<T> extends WebResponseParser<T> {
    public static final String j = "com.amazon.identity.auth.device.bc";
    public final ByteArrayOutputStream g;
    public final ic<T> h;
    public T i;

    public bc(String str, ic<T> icVar) {
        super(str);
        this.g = new ByteArrayOutputStream();
        this.i = null;
        this.h = icVar;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void a(byte[] bArr, long j2) {
        this.g.write(bArr, 0, (int) j2);
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public T b() {
        return this.i;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public boolean b(md mdVar) {
        return true;
    }

    @Override // com.amazon.identity.kcpsdk.common.WebResponseParser
    public void c() {
        try {
            this.g.close();
        } catch (IOException unused) {
            Log.e(ga.a(j), " Failed to close buffer");
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(this.g.toByteArray(), ParserUtil.UTF_8));
            ga.a(j, " Panda JSON Response: %s", jSONObject.toString());
            this.i = this.h.a(jSONObject);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JSONException unused2) {
            a(ParseError.ParseErrorMalformedBody);
        }
    }
}
