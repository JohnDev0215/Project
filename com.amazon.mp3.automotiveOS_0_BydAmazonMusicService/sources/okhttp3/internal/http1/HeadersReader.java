package okhttp3.internal.http1;

import com.amazon.music.find.utils.PageUriUtils;
import com.google.android.gms.cast.MediaStatus;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Headers;
import okio.BufferedSource;

/* JADX INFO: compiled from: HeadersReader.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0006\u0010\u0003\u001a\u00020\u0002J\u0006\u0010\u0005\u001a\u00020\u0004R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0016\u0010\f\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\r¨\u0006\u0011"}, d2 = {"Lokhttp3/internal/http1/HeadersReader;", "", "", "readLine", "Lokhttp3/Headers;", "readHeaders", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/BufferedSource;", "getSource", "()Lokio/BufferedSource;", "", "headerLimit", "J", "<init>", "(Lokio/BufferedSource;)V", "Companion", "okhttp"}, k = 1, mv = {1, 6, 0})
public final class HeadersReader {
    private long headerLimit;
    private final BufferedSource source;

    public HeadersReader(BufferedSource source) {
        Intrinsics.checkNotNullParameter(source, "source");
        this.source = source;
        this.headerLimit = MediaStatus.COMMAND_STREAM_TRANSFER;
    }

    public final String readLine() throws IOException {
        String utf8LineStrict = this.source.readUtf8LineStrict(this.headerLimit);
        this.headerLimit -= (long) utf8LineStrict.length();
        return utf8LineStrict;
    }

    public final Headers readHeaders() throws IOException {
        Headers.Builder builder = new Headers.Builder();
        while (true) {
            String line = readLine();
            if (!(line.length() == 0)) {
                builder.addLenient$okhttp(line);
            } else {
                return builder.build();
            }
        }
    }
}
