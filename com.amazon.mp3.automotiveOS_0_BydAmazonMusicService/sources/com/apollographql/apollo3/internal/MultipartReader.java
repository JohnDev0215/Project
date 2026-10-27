package com.apollographql.apollo3.internal;

import com.amazon.music.find.utils.PageUriUtils;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.exception.ApolloException;
import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Options;
import okio.Source;
import okio.Timeout;

/* JADX INFO: compiled from: MultipartReader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \"2\u00060\u0001j\u0002`\u0002:\u0003\"#$B\u0017\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b \u0010!J\u0010\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0003H\u0002J\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006J\b\u0010\t\u001a\u00020\bH\u0016R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0014R\u0016\u0010\u0017\u001a\u00020\u00168\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\u001a\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001bR\u001c\u0010\u001e\u001a\b\u0018\u00010\u001dR\u00020\u00008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001f¨\u0006%"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader;", "Ljava/io/Closeable;", "Lokio/Closeable;", "", "maxResult", "currentPartBytesRemaining", "Lcom/apollographql/apollo3/internal/MultipartReader$Part;", "nextPart", "", "close", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/BufferedSource;", "", "boundary", "Ljava/lang/String;", "getBoundary", "()Ljava/lang/String;", "Lokio/ByteString;", "dashDashBoundary", "Lokio/ByteString;", "crlfDashDashBoundary", "", "partCount", "I", "", "closed", "Z", "noMoreParts", "Lcom/apollographql/apollo3/internal/MultipartReader$PartSource;", "currentPart", "Lcom/apollographql/apollo3/internal/MultipartReader$PartSource;", "<init>", "(Lokio/BufferedSource;Ljava/lang/String;)V", "Companion", "Part", "PartSource", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class MultipartReader implements Closeable {
    private static final Companion Companion = new Companion(null);

    @Deprecated
    private static final Options afterBoundaryOptions;
    private final String boundary;
    private boolean closed;
    private final ByteString crlfDashDashBoundary;
    private PartSource currentPart;
    private final ByteString dashDashBoundary;
    private boolean noMoreParts;
    private int partCount;
    private final BufferedSource source;

    public MultipartReader(BufferedSource source, String boundary) {
        Intrinsics.checkNotNullParameter(source, "source");
        Intrinsics.checkNotNullParameter(boundary, "boundary");
        this.source = source;
        this.boundary = boundary;
        this.dashDashBoundary = new Buffer().writeUtf8("--").writeUtf8(boundary).readByteString();
        this.crlfDashDashBoundary = new Buffer().writeUtf8("\r\n--").writeUtf8(boundary).readByteString();
    }

    public final Part nextPart() throws IOException {
        if (!(!this.closed)) {
            throw new IllegalStateException("closed".toString());
        }
        if (this.noMoreParts) {
            return null;
        }
        if (this.partCount == 0 && this.source.rangeEquals(0L, this.dashDashBoundary)) {
            this.source.skip(this.dashDashBoundary.size());
        } else {
            while (true) {
                long jCurrentPartBytesRemaining = currentPartBytesRemaining(8192L);
                if (jCurrentPartBytesRemaining == 0) {
                    break;
                }
                this.source.skip(jCurrentPartBytesRemaining);
            }
            this.source.skip(this.crlfDashDashBoundary.size());
        }
        boolean z = false;
        while (true) {
            int iSelect = this.source.select(afterBoundaryOptions);
            if (iSelect == -1) {
                throw new ApolloException("unexpected characters after boundary", null, 2, null);
            }
            if (iSelect == 0) {
                this.partCount++;
                List headers = Companion.readHeaders(this.source);
                PartSource partSource = new PartSource(this);
                this.currentPart = partSource;
                return new Part(headers, Okio.buffer(partSource));
            }
            if (iSelect == 1) {
                if (z) {
                    throw new ApolloException("unexpected characters after boundary", null, 2, null);
                }
                if (this.partCount == 0) {
                    throw new ApolloException("expected at least 1 part", null, 2, null);
                }
                this.noMoreParts = true;
                return null;
            }
            if (iSelect == 2 || iSelect == 3) {
                z = true;
            }
        }
    }

    /* JADX INFO: compiled from: MultipartReader.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006H\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$PartSource;", "Lokio/Source;", "(Lcom/apollographql/apollo3/internal/MultipartReader;)V", "close", "", "read", "", "sink", "Lokio/Buffer;", "byteCount", "timeout", "Lokio/Timeout;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    private final class PartSource implements Source {
        final /* synthetic */ MultipartReader this$0;

        public PartSource(MultipartReader this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this.this$0 = this$0;
        }

        @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (Intrinsics.areEqual(this.this$0.currentPart, this)) {
                this.this$0.currentPart = null;
            }
        }

        @Override // okio.Source
        public long read(Buffer sink, long byteCount) throws IOException {
            Intrinsics.checkNotNullParameter(sink, "sink");
            if (byteCount >= 0) {
                if (Intrinsics.areEqual(this.this$0.currentPart, this)) {
                    long jCurrentPartBytesRemaining = this.this$0.currentPartBytesRemaining(byteCount);
                    if (jCurrentPartBytesRemaining == 0) {
                        return -1L;
                    }
                    return this.this$0.source.read(sink, jCurrentPartBytesRemaining);
                }
                throw new IllegalStateException("closed".toString());
            }
            throw new IllegalArgumentException(Intrinsics.stringPlus("byteCount < 0: ", Long.valueOf(byteCount)).toString());
        }

        @Override // okio.Source
        /* JADX INFO: renamed from: timeout */
        public Timeout getTimeout() {
            return this.this$0.source.getTimeout();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long currentPartBytesRemaining(long maxResult) throws IOException {
        this.source.require(this.crlfDashDashBoundary.size());
        long jIndexOf = this.source.getBuffer().indexOf(this.crlfDashDashBoundary);
        if (jIndexOf == -1) {
            return Math.min(maxResult, (this.source.getBuffer().getSize() - ((long) this.crlfDashDashBoundary.size())) + 1);
        }
        return Math.min(maxResult, jIndexOf);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.currentPart = null;
        this.source.close();
    }

    /* JADX INFO: compiled from: MultipartReader.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00060\u0001j\u0002`\u0002B\u001d\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\t\u0010\u0004\u001a\u00020\u0003H\u0096\u0001R\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\f\u001a\u00020\u000b8\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0012"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$Part;", "Ljava/io/Closeable;", "Lokio/Closeable;", "", "close", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "getHeaders", "()Ljava/util/List;", "Lokio/BufferedSource;", "body", "Lokio/BufferedSource;", "getBody", "()Lokio/BufferedSource;", "<init>", "(Ljava/util/List;Lokio/BufferedSource;)V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
    public static final class Part implements Closeable {
        private final BufferedSource body;
        private final List<HttpHeader> headers;

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.body.close();
        }

        public Part(List<HttpHeader> headers, BufferedSource body) {
            Intrinsics.checkNotNullParameter(headers, "headers");
            Intrinsics.checkNotNullParameter(body, "body");
            this.headers = headers;
            this.body = body;
        }

        public final BufferedSource getBody() {
            return this.body;
        }
    }

    /* JADX INFO: compiled from: MultipartReader.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0016\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/internal/MultipartReader$Companion;", "", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "readHeaders", "<init>", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<HttpHeader> readHeaders(BufferedSource source) throws IOException {
            ArrayList arrayList = new ArrayList();
            while (true) {
                String utf8LineStrict = source.readUtf8LineStrict();
                if (utf8LineStrict.length() == 0) {
                    return arrayList;
                }
                int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) utf8LineStrict, ':', 0, false, 6, (Object) null);
                if (!(iIndexOf$default != -1)) {
                    throw new IllegalStateException(Intrinsics.stringPlus("Unexpected header: ", utf8LineStrict).toString());
                }
                String strSubstring = utf8LineStrict.substring(0, iIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                String string = StringsKt__StringsKt.trim((CharSequence) strSubstring).toString();
                String strSubstring2 = utf8LineStrict.substring(iIndexOf$default + 1);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String).substring(startIndex)");
                arrayList.add(new HttpHeader(string, StringsKt__StringsKt.trim((CharSequence) strSubstring2).toString()));
            }
        }
    }

    static {
        Options.Companion companion = Options.INSTANCE;
        ByteString.Companion companion2 = ByteString.INSTANCE;
        afterBoundaryOptions = companion.of(companion2.encodeUtf8("\r\n"), companion2.encodeUtf8("--"), companion2.encodeUtf8(" "), companion2.encodeUtf8("\t"));
    }
}
