package okhttp3.internal.http;

import java.io.IOException;
import kotlin.Metadata;
import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.connection.RealConnection;
import okio.Sink;
import okio.Source;

/* JADX INFO: compiled from: ExchangeCodec.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H&J\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u0002H&J\b\u0010\n\u001a\u00020\bH&J\b\u0010\u000b\u001a\u00020\bH&J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\r\u001a\u00020\fH&J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0010H&J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0011\u001a\u00020\u0010H&J\b\u0010\u0016\u001a\u00020\u0015H&J\b\u0010\u0017\u001a\u00020\bH&R\u0014\u0010\u001b\u001a\u00020\u00188&X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lokhttp3/internal/http/ExchangeCodec;", "", "Lokhttp3/Request;", "request", "", "contentLength", "Lokio/Sink;", "createRequestBody", "", "writeRequestHeaders", "flushRequest", "finishRequest", "", "expectContinue", "Lokhttp3/Response$Builder;", "readResponseHeaders", "Lokhttp3/Response;", "response", "reportedContentLength", "Lokio/Source;", "openResponseBodySource", "Lokhttp3/Headers;", "trailers", "cancel", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "connection", "okhttp"}, k = 1, mv = {1, 6, 0})
public interface ExchangeCodec {
    void cancel();

    Sink createRequestBody(Request request, long contentLength) throws IOException;

    void finishRequest() throws IOException;

    void flushRequest() throws IOException;

    RealConnection getConnection();

    Source openResponseBodySource(Response response) throws IOException;

    Response.Builder readResponseHeaders(boolean expectContinue) throws IOException;

    long reportedContentLength(Response response) throws IOException;

    Headers trailers() throws IOException;

    void writeRequestHeaders(Request request) throws IOException;
}
