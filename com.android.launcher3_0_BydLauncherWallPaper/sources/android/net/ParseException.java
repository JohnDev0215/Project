package android.net;

/* JADX INFO: loaded from: classes2.dex */
public class ParseException extends RuntimeException {
    public String response;

    ParseException(String str) {
        super(str);
        this.response = str;
    }

    ParseException(String str, Throwable th) {
        super(str, th);
        this.response = str;
    }
}
