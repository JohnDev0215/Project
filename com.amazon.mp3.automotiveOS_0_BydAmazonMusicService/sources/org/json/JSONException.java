package org.json;

/* JADX INFO: loaded from: classes6.dex */
public class JSONException extends RuntimeException {
    public JSONException(String str) {
        super(str);
    }

    public JSONException(String str, Throwable th) {
        super(str, th);
    }

    public JSONException(Throwable th) {
        super(th.getMessage(), th);
    }
}
