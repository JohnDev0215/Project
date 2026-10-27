package retrofit2;

import java.util.Objects;
import okhttp3.Response;
import p.u42;

/* JADX INFO: loaded from: classes.dex */
public class HttpException extends RuntimeException {
    private final int code;
    private final String message;

    public HttpException(u42 u42Var) {
        Objects.requireNonNull(u42Var, "response == null");
        StringBuilder sb = new StringBuilder("HTTP ");
        Response response = u42Var.a;
        sb.append(response.l);
        sb.append(" ");
        sb.append(response.k);
        super(sb.toString());
        this.code = response.l;
        this.message = response.k;
    }
}
