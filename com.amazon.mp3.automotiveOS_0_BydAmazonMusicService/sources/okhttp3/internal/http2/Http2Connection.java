package okhttp3.internal.http2;

import androidx.core.internal.view.SupportMenu;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.find.utils.PageUriUtils;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.google.firebase.perf.plugin.util.AsmString;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.Util;
import okhttp3.internal.concurrent.Task;
import okhttp3.internal.concurrent.TaskQueue;
import okhttp3.internal.concurrent.TaskRunner;
import okhttp3.internal.http2.Http2Connection;
import okhttp3.internal.http2.Http2Reader;
import okhttp3.internal.platform.Platform;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;

/* JADX INFO: compiled from: Http2Connection.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {}, d1 = {"\u0000²\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 «\u00012\u00020\u0001:\b¬\u0001«\u0001\u00ad\u0001®\u0001B\u0015\b\u0000\u0012\b\u0010¨\u0001\u001a\u00030§\u0001¢\u0006\u0006\b©\u0001\u0010ª\u0001J&\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\u0010\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000f\u001a\u00020\u0002J\u0019\u0010\u0014\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0011\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u001c\u0010\n\u001a\u00020\t2\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\b\u001a\u00020\u0007J-\u0010\u001e\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00072\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ(\u0010\"\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u001f2\u0006\u0010!\u001a\u00020\u0015J\u001f\u0010'\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0000¢\u0006\u0004\b%\u0010&J\u001f\u0010*\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010(\u001a\u00020#H\u0000¢\u0006\u0004\b)\u0010&J\u001f\u0010.\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u0015H\u0000¢\u0006\u0004\b,\u0010-J\u001e\u00102\u001a\u00020\r2\u0006\u0010/\u001a\u00020\u00072\u0006\u00100\u001a\u00020\u00022\u0006\u00101\u001a\u00020\u0002J\u0006\u00103\u001a\u00020\rJ\u000e\u00104\u001a\u00020\r2\u0006\u0010(\u001a\u00020#J\b\u00105\u001a\u00020\rH\u0016J)\u00105\u001a\u00020\r2\u0006\u00106\u001a\u00020#2\u0006\u00107\u001a\u00020#2\b\u00108\u001a\u0004\u0018\u00010\u000bH\u0000¢\u0006\u0004\b9\u0010:J\u001c\u0010>\u001a\u00020\r2\b\b\u0002\u0010;\u001a\u00020\u00072\b\b\u0002\u0010=\u001a\u00020<H\u0007J\u000e\u0010@\u001a\u00020\u00072\u0006\u0010?\u001a\u00020\u0015J\u000f\u0010C\u001a\u00020\rH\u0000¢\u0006\u0004\bA\u0010BJ\u0017\u0010F\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0002H\u0000¢\u0006\u0004\bD\u0010EJ%\u0010I\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000¢\u0006\u0004\bG\u0010HJ-\u0010M\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010J\u001a\u00020\u0007H\u0000¢\u0006\u0004\bK\u0010LJ/\u0010R\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010O\u001a\u00020N2\u0006\u0010!\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u0007H\u0000¢\u0006\u0004\bP\u0010QJ\u001f\u0010T\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0000¢\u0006\u0004\bS\u0010&R\u001a\u0010U\u001a\u00020\u00078\u0000X\u0080\u0004¢\u0006\f\n\u0004\bU\u0010V\u001a\u0004\bW\u0010XR\u001a\u0010Z\u001a\u00020Y8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bZ\u0010[\u001a\u0004\b\\\u0010]R&\u0010_\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\t0^8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR\u001a\u0010d\u001a\u00020c8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bd\u0010e\u001a\u0004\bf\u0010gR\"\u0010h\u001a\u00020\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bh\u0010i\u001a\u0004\bj\u0010k\"\u0004\bl\u0010mR\"\u0010n\u001a\u00020\u00028\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bn\u0010i\u001a\u0004\bo\u0010k\"\u0004\bp\u0010mR\u0016\u0010q\u001a\u00020\u00078\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bq\u0010VR\u0014\u0010=\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010rR\u0014\u0010t\u001a\u00020s8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bt\u0010uR\u0014\u0010v\u001a\u00020s8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010uR\u0014\u0010w\u001a\u00020s8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010uR\u0014\u0010y\u001a\u00020x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010zR\u0016\u0010{\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b{\u0010|R\u0016\u0010}\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010|R\u0016\u0010~\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b~\u0010|R\u0016\u0010\u007f\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u007f\u0010|R\u0018\u0010\u0080\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0080\u0001\u0010|R\u0018\u0010\u0081\u0001\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b\u0081\u0001\u0010|R\u001d\u0010\u0083\u0001\u001a\u00030\u0082\u00018\u0006¢\u0006\u0010\n\u0006\b\u0083\u0001\u0010\u0084\u0001\u001a\u0006\b\u0085\u0001\u0010\u0086\u0001R*\u0010\u0087\u0001\u001a\u00030\u0082\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b\u0087\u0001\u0010\u0084\u0001\u001a\u0006\b\u0088\u0001\u0010\u0086\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R)\u0010\u008c\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\u00158\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0005\b\u008c\u0001\u0010|\u001a\u0006\b\u008d\u0001\u0010\u008e\u0001R)\u0010\u008f\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\u00158\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0005\b\u008f\u0001\u0010|\u001a\u0006\b\u0090\u0001\u0010\u008e\u0001R)\u0010\u0091\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\u00158\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0005\b\u0091\u0001\u0010|\u001a\u0006\b\u0092\u0001\u0010\u008e\u0001R)\u0010\u0093\u0001\u001a\u00020\u00152\u0007\u0010\u008b\u0001\u001a\u00020\u00158\u0006@BX\u0086\u000e¢\u0006\u000f\n\u0005\b\u0093\u0001\u0010|\u001a\u0006\b\u0094\u0001\u0010\u008e\u0001R \u0010\u0096\u0001\u001a\u00030\u0095\u00018\u0000X\u0080\u0004¢\u0006\u0010\n\u0006\b\u0096\u0001\u0010\u0097\u0001\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R\u001d\u0010\u009b\u0001\u001a\u00030\u009a\u00018\u0006¢\u0006\u0010\n\u0006\b\u009b\u0001\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R!\u0010 \u0001\u001a\u00070\u009f\u0001R\u00020\u00008\u0006¢\u0006\u0010\n\u0006\b \u0001\u0010¡\u0001\u001a\u0006\b¢\u0001\u0010£\u0001R\u001e\u0010¥\u0001\u001a\t\u0012\u0004\u0012\u00020\u00020¤\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001¨\u0006¯\u0001"}, d2 = {"Lokhttp3/internal/http2/Http2Connection;", "Ljava/io/Closeable;", "", "associatedStreamId", "", "Lokhttp3/internal/http2/Header;", "requestHeaders", "", "out", "Lokhttp3/internal/http2/Http2Stream;", "newStream", "Ljava/io/IOException;", "e", "", "failConnection", "id", "getStream", "streamId", "removeStream$okhttp", "(I)Lokhttp3/internal/http2/Http2Stream;", "removeStream", "", "read", "updateConnectionFlowControl$okhttp", "(J)V", "updateConnectionFlowControl", "outFinished", "alternating", "writeHeaders$okhttp", "(IZLjava/util/List;)V", "writeHeaders", "Lokio/Buffer;", "buffer", "byteCount", "writeData", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "writeSynResetLater$okhttp", "(ILokhttp3/internal/http2/ErrorCode;)V", "writeSynResetLater", "statusCode", "writeSynReset$okhttp", "writeSynReset", "unacknowledgedBytesRead", "writeWindowUpdateLater$okhttp", "(IJ)V", "writeWindowUpdateLater", "reply", "payload1", "payload2", "writePing", "flush", "shutdown", "close", "connectionCode", "streamCode", "cause", "close$okhttp", "(Lokhttp3/internal/http2/ErrorCode;Lokhttp3/internal/http2/ErrorCode;Ljava/io/IOException;)V", "sendConnectionPreface", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "nowNs", "isHealthy", "sendDegradedPingLater$okhttp", "()V", "sendDegradedPingLater", "pushedStream$okhttp", "(I)Z", "pushedStream", "pushRequestLater$okhttp", "(ILjava/util/List;)V", "pushRequestLater", "inFinished", "pushHeadersLater$okhttp", "(ILjava/util/List;Z)V", "pushHeadersLater", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "pushDataLater$okhttp", "(ILokio/BufferedSource;IZ)V", "pushDataLater", "pushResetLater$okhttp", "pushResetLater", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Z", "getClient$okhttp", "()Z", "Lokhttp3/internal/http2/Http2Connection$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "", "streams", "Ljava/util/Map;", "getStreams$okhttp", "()Ljava/util/Map;", "", "connectionName", "Ljava/lang/String;", "getConnectionName$okhttp", "()Ljava/lang/String;", "lastGoodStreamId", "I", "getLastGoodStreamId$okhttp", "()I", "setLastGoodStreamId$okhttp", "(I)V", "nextStreamId", "getNextStreamId$okhttp", "setNextStreamId$okhttp", "isShutdown", "Lokhttp3/internal/concurrent/TaskRunner;", "Lokhttp3/internal/concurrent/TaskQueue;", "writerQueue", "Lokhttp3/internal/concurrent/TaskQueue;", "pushQueue", "settingsListenerQueue", "Lokhttp3/internal/http2/PushObserver;", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "intervalPingsSent", "J", "intervalPongsReceived", "degradedPingsSent", "degradedPongsReceived", "awaitPongsReceived", "degradedPongDeadlineNs", "Lokhttp3/internal/http2/Settings;", "okHttpSettings", "Lokhttp3/internal/http2/Settings;", "getOkHttpSettings", "()Lokhttp3/internal/http2/Settings;", "peerSettings", "getPeerSettings", "setPeerSettings", "(Lokhttp3/internal/http2/Settings;)V", "<set-?>", "readBytesTotal", "getReadBytesTotal", "()J", "readBytesAcknowledged", "getReadBytesAcknowledged", "writeBytesTotal", "getWriteBytesTotal", "writeBytesMaximum", "getWriteBytesMaximum", "Ljava/net/Socket;", "socket", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "Lokhttp3/internal/http2/Http2Writer;", "writer", "Lokhttp3/internal/http2/Http2Writer;", "getWriter", "()Lokhttp3/internal/http2/Http2Writer;", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "readerRunnable", "Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "getReaderRunnable", "()Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "", "currentPushRequests", "Ljava/util/Set;", "Lokhttp3/internal/http2/Http2Connection$Builder;", "builder", "<init>", "(Lokhttp3/internal/http2/Http2Connection$Builder;)V", "Companion", "Builder", "Listener", "ReaderRunnable", "okhttp"}, k = 1, mv = {1, 6, 0})
public final class Http2Connection implements Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Settings DEFAULT_SETTINGS;
    private long awaitPongsReceived;
    private final boolean client;
    private final String connectionName;
    private final Set<Integer> currentPushRequests;
    private long degradedPingsSent;
    private long degradedPongDeadlineNs;
    private long degradedPongsReceived;
    private long intervalPingsSent;
    private long intervalPongsReceived;
    private boolean isShutdown;
    private int lastGoodStreamId;
    private final Listener listener;
    private int nextStreamId;
    private final Settings okHttpSettings;
    private Settings peerSettings;
    private final PushObserver pushObserver;
    private final TaskQueue pushQueue;
    private long readBytesAcknowledged;
    private long readBytesTotal;
    private final ReaderRunnable readerRunnable;
    private final TaskQueue settingsListenerQueue;
    private final Socket socket;
    private final Map<Integer, Http2Stream> streams;
    private final TaskRunner taskRunner;
    private long writeBytesMaximum;
    private long writeBytesTotal;
    private final Http2Writer writer;
    private final TaskQueue writerQueue;

    /* JADX INFO: compiled from: Http2Connection.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000bH&¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Listener;", "", "()V", "onSettings", "", "connection", "Lokhttp3/internal/http2/Http2Connection;", "settings", "Lokhttp3/internal/http2/Settings;", "onStream", "stream", "Lokhttp3/internal/http2/Http2Stream;", "Companion", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static abstract class Listener {

        @JvmField
        public static final Listener REFUSE_INCOMING_STREAMS = new Listener() { // from class: okhttp3.internal.http2.Http2Connection$Listener$Companion$REFUSE_INCOMING_STREAMS$1
            @Override // okhttp3.internal.http2.Http2Connection.Listener
            public void onStream(Http2Stream stream) throws IOException {
                Intrinsics.checkNotNullParameter(stream, "stream");
                stream.close(ErrorCode.REFUSED_STREAM, null);
            }
        };

        public void onSettings(Http2Connection connection, Settings settings) {
            Intrinsics.checkNotNullParameter(connection, "connection");
            Intrinsics.checkNotNullParameter(settings, "settings");
        }

        public abstract void onStream(Http2Stream stream) throws IOException;
    }

    public final boolean pushedStream$okhttp(int streamId) {
        return streamId != 0 && (streamId & 1) == 0;
    }

    public Http2Connection(Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        boolean client = builder.getClient();
        this.client = client;
        this.listener = builder.getListener();
        this.streams = new LinkedHashMap();
        String connectionName$okhttp = builder.getConnectionName$okhttp();
        this.connectionName = connectionName$okhttp;
        this.nextStreamId = builder.getClient() ? 3 : 2;
        TaskRunner taskRunner = builder.getTaskRunner();
        this.taskRunner = taskRunner;
        TaskQueue taskQueueNewQueue = taskRunner.newQueue();
        this.writerQueue = taskQueueNewQueue;
        this.pushQueue = taskRunner.newQueue();
        this.settingsListenerQueue = taskRunner.newQueue();
        this.pushObserver = builder.getPushObserver();
        Settings settings = new Settings();
        if (builder.getClient()) {
            settings.set(7, 16777216);
        }
        this.okHttpSettings = settings;
        this.peerSettings = DEFAULT_SETTINGS;
        this.writeBytesMaximum = r2.getInitialWindowSize();
        this.socket = builder.getSocket$okhttp();
        this.writer = new Http2Writer(builder.getSink$okhttp(), client);
        this.readerRunnable = new ReaderRunnable(this, new Http2Reader(builder.getSource$okhttp(), client));
        this.currentPushRequests = new LinkedHashSet();
        if (builder.getPingIntervalMillis() != 0) {
            final long nanos = TimeUnit.MILLISECONDS.toNanos(builder.getPingIntervalMillis());
            final String strStringPlus = Intrinsics.stringPlus(connectionName$okhttp, " ping");
            taskQueueNewQueue.schedule(new Task(strStringPlus, this, nanos) { // from class: okhttp3.internal.http2.Http2Connection$special$$inlined$schedule$1
                final /* synthetic */ String $name;
                final /* synthetic */ long $pingIntervalNanos$inlined;
                final /* synthetic */ Http2Connection this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(strStringPlus, false, 2, null);
                    this.$name = strStringPlus;
                    this.this$0 = this;
                    this.$pingIntervalNanos$inlined = nanos;
                }

                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    boolean z;
                    synchronized (this.this$0) {
                        if (this.this$0.intervalPongsReceived < this.this$0.intervalPingsSent) {
                            z = true;
                        } else {
                            this.this$0.intervalPingsSent++;
                            z = false;
                        }
                    }
                    if (z) {
                        this.this$0.failConnection(null);
                        return -1L;
                    }
                    this.this$0.writePing(false, 1, 0);
                    return this.$pingIntervalNanos$inlined;
                }
            }, nanos);
        }
    }

    /* JADX INFO: renamed from: getClient$okhttp, reason: from getter */
    public final boolean getClient() {
        return this.client;
    }

    /* JADX INFO: renamed from: getListener$okhttp, reason: from getter */
    public final Listener getListener() {
        return this.listener;
    }

    public final Map<Integer, Http2Stream> getStreams$okhttp() {
        return this.streams;
    }

    /* JADX INFO: renamed from: getConnectionName$okhttp, reason: from getter */
    public final String getConnectionName() {
        return this.connectionName;
    }

    /* JADX INFO: renamed from: getLastGoodStreamId$okhttp, reason: from getter */
    public final int getLastGoodStreamId() {
        return this.lastGoodStreamId;
    }

    public final void setLastGoodStreamId$okhttp(int i) {
        this.lastGoodStreamId = i;
    }

    /* JADX INFO: renamed from: getNextStreamId$okhttp, reason: from getter */
    public final int getNextStreamId() {
        return this.nextStreamId;
    }

    public final void setNextStreamId$okhttp(int i) {
        this.nextStreamId = i;
    }

    public final Settings getOkHttpSettings() {
        return this.okHttpSettings;
    }

    public final Settings getPeerSettings() {
        return this.peerSettings;
    }

    public final void setPeerSettings(Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.peerSettings = settings;
    }

    public final long getWriteBytesTotal() {
        return this.writeBytesTotal;
    }

    public final long getWriteBytesMaximum() {
        return this.writeBytesMaximum;
    }

    /* JADX INFO: renamed from: getSocket$okhttp, reason: from getter */
    public final Socket getSocket() {
        return this.socket;
    }

    public final Http2Writer getWriter() {
        return this.writer;
    }

    public final synchronized Http2Stream getStream(int id) {
        return this.streams.get(Integer.valueOf(id));
    }

    public final synchronized Http2Stream removeStream$okhttp(int streamId) {
        Http2Stream http2StreamRemove;
        http2StreamRemove = this.streams.remove(Integer.valueOf(streamId));
        notifyAll();
        return http2StreamRemove;
    }

    public final synchronized void updateConnectionFlowControl$okhttp(long read) {
        long j = this.readBytesTotal + read;
        this.readBytesTotal = j;
        long j2 = j - this.readBytesAcknowledged;
        if (j2 >= this.okHttpSettings.getInitialWindowSize() / 2) {
            writeWindowUpdateLater$okhttp(0, j2);
            this.readBytesAcknowledged += j2;
        }
    }

    public final Http2Stream newStream(List<Header> requestHeaders, boolean out) throws IOException {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        return newStream(0, requestHeaders, out);
    }

    private final Http2Stream newStream(int associatedStreamId, List<Header> requestHeaders, boolean out) throws IOException {
        int nextStreamId;
        Http2Stream http2Stream;
        boolean z;
        boolean z2 = !out;
        synchronized (this.writer) {
            synchronized (this) {
                if (getNextStreamId() > 1073741823) {
                    shutdown(ErrorCode.REFUSED_STREAM);
                }
                if (this.isShutdown) {
                    throw new ConnectionShutdownException();
                }
                nextStreamId = getNextStreamId();
                setNextStreamId$okhttp(getNextStreamId() + 2);
                http2Stream = new Http2Stream(nextStreamId, this, z2, false, null);
                z = !out || getWriteBytesTotal() >= getWriteBytesMaximum() || http2Stream.getWriteBytesTotal() >= http2Stream.getWriteBytesMaximum();
                if (http2Stream.isOpen()) {
                    getStreams$okhttp().put(Integer.valueOf(nextStreamId), http2Stream);
                }
                Unit unit = Unit.INSTANCE;
            }
            if (associatedStreamId == 0) {
                getWriter().headers(z2, nextStreamId, requestHeaders);
            } else {
                if (!(true ^ getClient())) {
                    throw new IllegalArgumentException("client streams shouldn't have associated stream IDs".toString());
                }
                getWriter().pushPromise(associatedStreamId, nextStreamId, requestHeaders);
            }
        }
        if (z) {
            this.writer.flush();
        }
        return http2Stream;
    }

    public final void writeHeaders$okhttp(int streamId, boolean outFinished, List<Header> alternating) throws IOException {
        Intrinsics.checkNotNullParameter(alternating, "alternating");
        this.writer.headers(outFinished, streamId, alternating);
    }

    public final void writeData(int streamId, boolean outFinished, Buffer buffer, long byteCount) throws IOException {
        int iMin;
        long j;
        if (byteCount == 0) {
            this.writer.data(outFinished, streamId, buffer, 0);
            return;
        }
        while (byteCount > 0) {
            synchronized (this) {
                while (getWriteBytesTotal() >= getWriteBytesMaximum()) {
                    try {
                        if (!getStreams$okhttp().containsKey(Integer.valueOf(streamId))) {
                            throw new IOException("stream closed");
                        }
                        wait();
                    } catch (InterruptedException unused) {
                        Thread.currentThread().interrupt();
                        throw new InterruptedIOException();
                    }
                }
                iMin = Math.min((int) Math.min(byteCount, getWriteBytesMaximum() - getWriteBytesTotal()), getWriter().getMaxFrameSize());
                j = iMin;
                this.writeBytesTotal = getWriteBytesTotal() + j;
                Unit unit = Unit.INSTANCE;
            }
            byteCount -= j;
            this.writer.data(outFinished && byteCount == 0, streamId, buffer, iMin);
        }
    }

    public final void writeSynResetLater$okhttp(final int streamId, final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        TaskQueue taskQueue = this.writerQueue;
        final String str = this.connectionName + '[' + streamId + "] writeSynReset";
        final boolean z = true;
        taskQueue.schedule(new Task(str, z, this, streamId, errorCode) { // from class: okhttp3.internal.http2.Http2Connection$writeSynResetLater$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ ErrorCode $errorCode$inlined;
            final /* synthetic */ String $name;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ Http2Connection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.this$0 = this;
                this.$streamId$inlined = streamId;
                this.$errorCode$inlined = errorCode;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    this.this$0.writeSynReset$okhttp(this.$streamId$inlined, this.$errorCode$inlined);
                    return -1L;
                } catch (IOException e) {
                    this.this$0.failConnection(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void writeSynReset$okhttp(int streamId, ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        this.writer.rstStream(streamId, statusCode);
    }

    public final void writeWindowUpdateLater$okhttp(final int streamId, final long unacknowledgedBytesRead) {
        TaskQueue taskQueue = this.writerQueue;
        final String str = this.connectionName + '[' + streamId + "] windowUpdate";
        final boolean z = true;
        taskQueue.schedule(new Task(str, z, this, streamId, unacknowledgedBytesRead) { // from class: okhttp3.internal.http2.Http2Connection$writeWindowUpdateLater$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ String $name;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ long $unacknowledgedBytesRead$inlined;
            final /* synthetic */ Http2Connection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.this$0 = this;
                this.$streamId$inlined = streamId;
                this.$unacknowledgedBytesRead$inlined = unacknowledgedBytesRead;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    this.this$0.getWriter().windowUpdate(this.$streamId$inlined, this.$unacknowledgedBytesRead$inlined);
                    return -1L;
                } catch (IOException e) {
                    this.this$0.failConnection(e);
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void writePing(boolean reply, int payload1, int payload2) {
        try {
            this.writer.ping(reply, payload1, payload2);
        } catch (IOException e) {
            failConnection(e);
        }
    }

    public final void flush() throws IOException {
        this.writer.flush();
    }

    public final void shutdown(ErrorCode statusCode) throws IOException {
        Intrinsics.checkNotNullParameter(statusCode, "statusCode");
        synchronized (this.writer) {
            Ref.IntRef intRef = new Ref.IntRef();
            synchronized (this) {
                if (this.isShutdown) {
                    return;
                }
                this.isShutdown = true;
                intRef.element = getLastGoodStreamId();
                Unit unit = Unit.INSTANCE;
                getWriter().goAway(intRef.element, statusCode, Util.EMPTY_BYTE_ARRAY);
            }
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        close$okhttp(ErrorCode.NO_ERROR, ErrorCode.CANCEL, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void failConnection(IOException e) {
        ErrorCode errorCode = ErrorCode.PROTOCOL_ERROR;
        close$okhttp(errorCode, errorCode, e);
    }

    public static /* synthetic */ void start$default(Http2Connection http2Connection, boolean z, TaskRunner taskRunner, int i, Object obj) throws IOException {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            taskRunner = TaskRunner.INSTANCE;
        }
        http2Connection.start(z, taskRunner);
    }

    @JvmOverloads
    public final void start(boolean sendConnectionPreface, TaskRunner taskRunner) throws IOException {
        Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
        if (sendConnectionPreface) {
            this.writer.connectionPreface();
            this.writer.settings(this.okHttpSettings);
            if (this.okHttpSettings.getInitialWindowSize() != 65535) {
                this.writer.windowUpdate(0, r6 - SupportMenu.USER_MASK);
            }
        }
        TaskQueue taskQueueNewQueue = taskRunner.newQueue();
        final String str = this.connectionName;
        final ReaderRunnable readerRunnable = this.readerRunnable;
        final boolean z = true;
        taskQueueNewQueue.schedule(new Task(str, z, readerRunnable) { // from class: okhttp3.internal.concurrent.TaskQueue$execute$1
            final /* synthetic */ Function0<Unit> $block;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ String $name;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.$block = readerRunnable;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                this.$block.invoke();
                return -1L;
            }
        }, 0L);
    }

    public final synchronized boolean isHealthy(long nowNs) {
        if (this.isShutdown) {
            return false;
        }
        if (this.degradedPongsReceived < this.degradedPingsSent) {
            if (nowNs >= this.degradedPongDeadlineNs) {
                return false;
            }
        }
        return true;
    }

    public final void sendDegradedPingLater$okhttp() {
        synchronized (this) {
            long j = this.degradedPongsReceived;
            long j2 = this.degradedPingsSent;
            if (j < j2) {
                return;
            }
            this.degradedPingsSent = j2 + 1;
            this.degradedPongDeadlineNs = System.nanoTime() + ((long) 1000000000);
            Unit unit = Unit.INSTANCE;
            TaskQueue taskQueue = this.writerQueue;
            final String strStringPlus = Intrinsics.stringPlus(this.connectionName, " ping");
            final boolean z = true;
            taskQueue.schedule(new Task(strStringPlus, z, this) { // from class: okhttp3.internal.http2.Http2Connection$sendDegradedPingLater$$inlined$execute$default$1
                final /* synthetic */ boolean $cancelable;
                final /* synthetic */ String $name;
                final /* synthetic */ Http2Connection this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(strStringPlus, z);
                    this.$name = strStringPlus;
                    this.$cancelable = z;
                    this.this$0 = this;
                }

                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    this.this$0.writePing(false, 2, 0);
                    return -1L;
                }
            }, 0L);
        }
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    @Metadata(bv = {}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\bB\u0010CJ.\u0010\u0003\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\b\b\u0002\u0010\u0005\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\bH\u0007J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\u000b\u001a\u00020\nJ\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\fJ\u0006\u0010\u000f\u001a\u00020\u000eR\"\u0010\u0011\u001a\u00020\u00108\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0018\u001a\u00020\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\"\u0010\u0003\u001a\u00020\u00028\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b\u0003\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010!\u001a\u00020\u00048\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R\"\u0010\u0007\u001a\u00020\u00068\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b\u0007\u0010'\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\"\u0010\t\u001a\u00020\b8\u0000@\u0000X\u0080.¢\u0006\u0012\n\u0004\b\t\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\"\u0010\u000b\u001a\u00020\n8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\u000b\u00101\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\"\u00107\u001a\u0002068\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\"\u0010\r\u001a\u00020\f8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\b\r\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010A¨\u0006D"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Builder;", "", "Ljava/net/Socket;", "socket", "", "peerName", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "Lokio/BufferedSink;", "sink", "Lokhttp3/internal/http2/Http2Connection$Listener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "", "pingIntervalMillis", "Lokhttp3/internal/http2/Http2Connection;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "", SearchMetricsServiceDefault.CONTENT_SRC_CLIENT, "Z", "getClient$okhttp", "()Z", "setClient$okhttp", "(Z)V", "Lokhttp3/internal/concurrent/TaskRunner;", "taskRunner", "Lokhttp3/internal/concurrent/TaskRunner;", "getTaskRunner$okhttp", "()Lokhttp3/internal/concurrent/TaskRunner;", "Ljava/net/Socket;", "getSocket$okhttp", "()Ljava/net/Socket;", "setSocket$okhttp", "(Ljava/net/Socket;)V", "connectionName", "Ljava/lang/String;", "getConnectionName$okhttp", "()Ljava/lang/String;", "setConnectionName$okhttp", "(Ljava/lang/String;)V", "Lokio/BufferedSource;", "getSource$okhttp", "()Lokio/BufferedSource;", "setSource$okhttp", "(Lokio/BufferedSource;)V", "Lokio/BufferedSink;", "getSink$okhttp", "()Lokio/BufferedSink;", "setSink$okhttp", "(Lokio/BufferedSink;)V", "Lokhttp3/internal/http2/Http2Connection$Listener;", "getListener$okhttp", "()Lokhttp3/internal/http2/Http2Connection$Listener;", "setListener$okhttp", "(Lokhttp3/internal/http2/Http2Connection$Listener;)V", "Lokhttp3/internal/http2/PushObserver;", "pushObserver", "Lokhttp3/internal/http2/PushObserver;", "getPushObserver$okhttp", "()Lokhttp3/internal/http2/PushObserver;", "setPushObserver$okhttp", "(Lokhttp3/internal/http2/PushObserver;)V", "I", "getPingIntervalMillis$okhttp", "()I", "setPingIntervalMillis$okhttp", "(I)V", "<init>", "(ZLokhttp3/internal/concurrent/TaskRunner;)V", "okhttp"}, k = 1, mv = {1, 6, 0})
    public static final class Builder {
        private boolean client;
        public String connectionName;
        private Listener listener;
        private int pingIntervalMillis;
        private PushObserver pushObserver;
        public BufferedSink sink;
        public Socket socket;
        public BufferedSource source;
        private final TaskRunner taskRunner;

        public Builder(boolean z, TaskRunner taskRunner) {
            Intrinsics.checkNotNullParameter(taskRunner, "taskRunner");
            this.client = z;
            this.taskRunner = taskRunner;
            this.listener = Listener.REFUSE_INCOMING_STREAMS;
            this.pushObserver = PushObserver.CANCEL;
        }

        /* JADX INFO: renamed from: getClient$okhttp, reason: from getter */
        public final boolean getClient() {
            return this.client;
        }

        /* JADX INFO: renamed from: getTaskRunner$okhttp, reason: from getter */
        public final TaskRunner getTaskRunner() {
            return this.taskRunner;
        }

        public final Socket getSocket$okhttp() {
            Socket socket = this.socket;
            if (socket != null) {
                return socket;
            }
            Intrinsics.throwUninitializedPropertyAccessException("socket");
            return null;
        }

        public final void setSocket$okhttp(Socket socket) {
            Intrinsics.checkNotNullParameter(socket, "<set-?>");
            this.socket = socket;
        }

        public final String getConnectionName$okhttp() {
            String str = this.connectionName;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("connectionName");
            return null;
        }

        public final void setConnectionName$okhttp(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.connectionName = str;
        }

        public final BufferedSource getSource$okhttp() {
            BufferedSource bufferedSource = this.source;
            if (bufferedSource != null) {
                return bufferedSource;
            }
            Intrinsics.throwUninitializedPropertyAccessException(PageUriUtils.SOURCE_QUERY_PARAM);
            return null;
        }

        public final void setSource$okhttp(BufferedSource bufferedSource) {
            Intrinsics.checkNotNullParameter(bufferedSource, "<set-?>");
            this.source = bufferedSource;
        }

        public final BufferedSink getSink$okhttp() {
            BufferedSink bufferedSink = this.sink;
            if (bufferedSink != null) {
                return bufferedSink;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sink");
            return null;
        }

        public final void setSink$okhttp(BufferedSink bufferedSink) {
            Intrinsics.checkNotNullParameter(bufferedSink, "<set-?>");
            this.sink = bufferedSink;
        }

        /* JADX INFO: renamed from: getListener$okhttp, reason: from getter */
        public final Listener getListener() {
            return this.listener;
        }

        public final void setListener$okhttp(Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "<set-?>");
            this.listener = listener;
        }

        /* JADX INFO: renamed from: getPushObserver$okhttp, reason: from getter */
        public final PushObserver getPushObserver() {
            return this.pushObserver;
        }

        /* JADX INFO: renamed from: getPingIntervalMillis$okhttp, reason: from getter */
        public final int getPingIntervalMillis() {
            return this.pingIntervalMillis;
        }

        public final void setPingIntervalMillis$okhttp(int i) {
            this.pingIntervalMillis = i;
        }

        @JvmOverloads
        public final Builder socket(Socket socket, String peerName, BufferedSource source, BufferedSink sink) throws IOException {
            String strStringPlus;
            Intrinsics.checkNotNullParameter(socket, "socket");
            Intrinsics.checkNotNullParameter(peerName, "peerName");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(sink, "sink");
            setSocket$okhttp(socket);
            if (getClient()) {
                strStringPlus = Util.okHttpName + ' ' + peerName;
            } else {
                strStringPlus = Intrinsics.stringPlus("MockWebServer ", peerName);
            }
            setConnectionName$okhttp(strStringPlus);
            setSource$okhttp(source);
            setSink$okhttp(sink);
            return this;
        }

        public final Builder listener(Listener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            setListener$okhttp(listener);
            return this;
        }

        public final Builder pingIntervalMillis(int pingIntervalMillis) {
            setPingIntervalMillis$okhttp(pingIntervalMillis);
            return this;
        }

        public final Http2Connection build() {
            return new Http2Connection(this);
        }
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    @Metadata(bv = {}, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00030\u0002B\u0011\b\u0000\u0012\u0006\u0010-\u001a\u00020,¢\u0006\u0004\b1\u00102J\t\u0010\u0004\u001a\u00020\u0003H\u0096\u0002J(\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0007H\u0016J.\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00072\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u0018\u0010\u0014\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0018\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0016\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0016J\b\u0010\u0019\u001a\u00020\u0003H\u0016J \u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u0007H\u0016J \u0010!\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010 \u001a\u00020\u001fH\u0016J\u0018\u0010$\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010#\u001a\u00020\"H\u0016J(\u0010(\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010%\u001a\u00020\u00072\u0006\u0010&\u001a\u00020\u00072\u0006\u0010'\u001a\u00020\u0005H\u0016J&\u0010+\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010)\u001a\u00020\u00072\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016R\u001a\u0010-\u001a\u00020,8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100¨\u00063"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$ReaderRunnable;", "Lokhttp3/internal/http2/Http2Reader$Handler;", "Lkotlin/Function0;", "", "invoke", "", "inFinished", "", "streamId", "Lokio/BufferedSource;", PageUriUtils.SOURCE_QUERY_PARAM, "length", "data", "associatedStreamId", "", "Lokhttp3/internal/http2/Header;", "headerBlock", "headers", "Lokhttp3/internal/http2/ErrorCode;", "errorCode", "rstStream", "clearPrevious", "Lokhttp3/internal/http2/Settings;", "settings", "applyAndAckSettings", "ackSettings", "ack", "payload1", "payload2", "ping", "lastGoodStreamId", "Lokio/ByteString;", "debugData", "goAway", "", "windowSizeIncrement", "windowUpdate", "streamDependency", "weight", "exclusive", "priority", "promisedStreamId", "requestHeaders", "pushPromise", "Lokhttp3/internal/http2/Http2Reader;", "reader", "Lokhttp3/internal/http2/Http2Reader;", "getReader$okhttp", "()Lokhttp3/internal/http2/Http2Reader;", "<init>", "(Lokhttp3/internal/http2/Http2Connection;Lokhttp3/internal/http2/Http2Reader;)V", "okhttp"}, k = 1, mv = {1, 6, 0})
    public final class ReaderRunnable implements Http2Reader.Handler, Function0<Unit> {
        private final Http2Reader reader;
        final /* synthetic */ Http2Connection this$0;

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ackSettings() {
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void priority(int streamId, int streamDependency, int weight, boolean exclusive) {
        }

        public ReaderRunnable(Http2Connection this$0, Http2Reader reader) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            Intrinsics.checkNotNullParameter(reader, "reader");
            this.this$0 = this$0;
            this.reader = reader;
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() throws Throwable {
            invoke2();
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v0, types: [okhttp3.internal.http2.ErrorCode] */
        /* JADX WARN: Type inference failed for: r0v3 */
        /* JADX WARN: Type inference failed for: r0v5, types: [java.io.Closeable, okhttp3.internal.http2.Http2Reader] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
        public void invoke2() throws Throwable {
            ErrorCode errorCode;
            ErrorCode errorCode2;
            ErrorCode errorCode3 = ErrorCode.INTERNAL_ERROR;
            IOException e = null;
            try {
                try {
                    this.reader.readConnectionPreface(this);
                    while (this.reader.nextFrame(false, this)) {
                    }
                    errorCode2 = ErrorCode.NO_ERROR;
                } catch (IOException e2) {
                    e = e2;
                } catch (Throwable th) {
                    th = th;
                    errorCode = errorCode3;
                    this.this$0.close$okhttp(errorCode, errorCode3, e);
                    Util.closeQuietly(this.reader);
                    throw th;
                }
                try {
                    this.this$0.close$okhttp(errorCode2, ErrorCode.CANCEL, null);
                    errorCode = errorCode2;
                } catch (IOException e3) {
                    e = e3;
                    ErrorCode errorCode4 = ErrorCode.PROTOCOL_ERROR;
                    Http2Connection http2Connection = this.this$0;
                    http2Connection.close$okhttp(errorCode4, errorCode4, e);
                    errorCode = http2Connection;
                }
                errorCode3 = this.reader;
                Util.closeQuietly((Closeable) errorCode3);
            } catch (Throwable th2) {
                th = th2;
                this.this$0.close$okhttp(errorCode, errorCode3, e);
                Util.closeQuietly(this.reader);
                throw th;
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void data(boolean inFinished, int streamId, BufferedSource source, int length) throws IOException {
            Intrinsics.checkNotNullParameter(source, "source");
            if (this.this$0.pushedStream$okhttp(streamId)) {
                this.this$0.pushDataLater$okhttp(streamId, source, length, inFinished);
                return;
            }
            Http2Stream stream = this.this$0.getStream(streamId);
            if (stream == null) {
                this.this$0.writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
                long j = length;
                this.this$0.updateConnectionFlowControl$okhttp(j);
                source.skip(j);
                return;
            }
            stream.receiveData(source, length);
            if (inFinished) {
                stream.receiveHeaders(Util.EMPTY_HEADERS, true);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void headers(boolean inFinished, int streamId, int associatedStreamId, List<Header> headerBlock) {
            Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
            if (this.this$0.pushedStream$okhttp(streamId)) {
                this.this$0.pushHeadersLater$okhttp(streamId, headerBlock, inFinished);
                return;
            }
            final Http2Connection http2Connection = this.this$0;
            synchronized (http2Connection) {
                Http2Stream stream = http2Connection.getStream(streamId);
                if (stream == null) {
                    if (http2Connection.isShutdown) {
                        return;
                    }
                    if (streamId <= http2Connection.getLastGoodStreamId()) {
                        return;
                    }
                    if (streamId % 2 == http2Connection.getNextStreamId() % 2) {
                        return;
                    }
                    final Http2Stream http2Stream = new Http2Stream(streamId, http2Connection, false, inFinished, Util.toHeaders(headerBlock));
                    http2Connection.setLastGoodStreamId$okhttp(streamId);
                    http2Connection.getStreams$okhttp().put(Integer.valueOf(streamId), http2Stream);
                    TaskQueue taskQueueNewQueue = http2Connection.taskRunner.newQueue();
                    final String str = http2Connection.getConnectionName() + '[' + streamId + "] onStream";
                    final boolean z = true;
                    taskQueueNewQueue.schedule(new Task(str, z, http2Connection, http2Stream) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$headers$lambda-2$$inlined$execute$default$1
                        final /* synthetic */ boolean $cancelable;
                        final /* synthetic */ String $name;
                        final /* synthetic */ Http2Stream $newStream$inlined;
                        final /* synthetic */ Http2Connection this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(str, z);
                            this.$name = str;
                            this.$cancelable = z;
                            this.this$0 = http2Connection;
                            this.$newStream$inlined = http2Stream;
                        }

                        @Override // okhttp3.internal.concurrent.Task
                        public long runOnce() {
                            try {
                                this.this$0.getListener().onStream(this.$newStream$inlined);
                                return -1L;
                            } catch (IOException e) {
                                Platform.INSTANCE.get().log(Intrinsics.stringPlus("Http2Connection.Listener failure for ", this.this$0.getConnectionName()), 4, e);
                                try {
                                    this.$newStream$inlined.close(ErrorCode.PROTOCOL_ERROR, e);
                                    return -1L;
                                } catch (IOException unused) {
                                    return -1L;
                                }
                            }
                        }
                    }, 0L);
                    return;
                }
                Unit unit = Unit.INSTANCE;
                stream.receiveHeaders(Util.toHeaders(headerBlock), inFinished);
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void rstStream(int streamId, ErrorCode errorCode) {
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            if (this.this$0.pushedStream$okhttp(streamId)) {
                this.this$0.pushResetLater$okhttp(streamId, errorCode);
                return;
            }
            Http2Stream http2StreamRemoveStream$okhttp = this.this$0.removeStream$okhttp(streamId);
            if (http2StreamRemoveStream$okhttp == null) {
                return;
            }
            http2StreamRemoveStream$okhttp.receiveRstStream(errorCode);
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void settings(final boolean clearPrevious, final Settings settings) {
            Intrinsics.checkNotNullParameter(settings, "settings");
            TaskQueue taskQueue = this.this$0.writerQueue;
            final String strStringPlus = Intrinsics.stringPlus(this.this$0.getConnectionName(), " applyAndAckSettings");
            final boolean z = true;
            taskQueue.schedule(new Task(strStringPlus, z, this, clearPrevious, settings) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$settings$$inlined$execute$default$1
                final /* synthetic */ boolean $cancelable;
                final /* synthetic */ boolean $clearPrevious$inlined;
                final /* synthetic */ String $name;
                final /* synthetic */ Settings $settings$inlined;
                final /* synthetic */ Http2Connection.ReaderRunnable this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(strStringPlus, z);
                    this.$name = strStringPlus;
                    this.$cancelable = z;
                    this.this$0 = this;
                    this.$clearPrevious$inlined = clearPrevious;
                    this.$settings$inlined = settings;
                }

                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    this.this$0.applyAndAckSettings(this.$clearPrevious$inlined, this.$settings$inlined);
                    return -1L;
                }
            }, 0L);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v2, types: [T, okhttp3.internal.http2.Settings] */
        /* JADX WARN: Type inference failed for: r13v3 */
        public final void applyAndAckSettings(boolean clearPrevious, Settings settings) {
            ?? r13;
            long initialWindowSize;
            int i;
            Http2Stream[] http2StreamArr;
            Intrinsics.checkNotNullParameter(settings, "settings");
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            Http2Writer writer = this.this$0.getWriter();
            final Http2Connection http2Connection = this.this$0;
            synchronized (writer) {
                synchronized (http2Connection) {
                    Settings peerSettings = http2Connection.getPeerSettings();
                    if (clearPrevious) {
                        r13 = settings;
                    } else {
                        Settings settings2 = new Settings();
                        settings2.merge(peerSettings);
                        settings2.merge(settings);
                        r13 = settings2;
                    }
                    objectRef.element = r13;
                    initialWindowSize = ((long) r13.getInitialWindowSize()) - ((long) peerSettings.getInitialWindowSize());
                    i = 0;
                    if (initialWindowSize == 0 || http2Connection.getStreams$okhttp().isEmpty()) {
                        http2StreamArr = null;
                    } else {
                        Object[] array = http2Connection.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                        if (array == null) {
                            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                        }
                        http2StreamArr = (Http2Stream[]) array;
                    }
                    http2Connection.setPeerSettings((Settings) objectRef.element);
                    TaskQueue taskQueue = http2Connection.settingsListenerQueue;
                    final String strStringPlus = Intrinsics.stringPlus(http2Connection.getConnectionName(), " onSettings");
                    final boolean z = true;
                    taskQueue.schedule(new Task(strStringPlus, z, http2Connection, objectRef) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$applyAndAckSettings$lambda-7$lambda-6$$inlined$execute$default$1
                        final /* synthetic */ boolean $cancelable;
                        final /* synthetic */ String $name;
                        final /* synthetic */ Ref.ObjectRef $newPeerSettings$inlined;
                        final /* synthetic */ Http2Connection this$0;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(strStringPlus, z);
                            this.$name = strStringPlus;
                            this.$cancelable = z;
                            this.this$0 = http2Connection;
                            this.$newPeerSettings$inlined = objectRef;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // okhttp3.internal.concurrent.Task
                        public long runOnce() {
                            this.this$0.getListener().onSettings(this.this$0, (Settings) this.$newPeerSettings$inlined.element);
                            return -1L;
                        }
                    }, 0L);
                    Unit unit = Unit.INSTANCE;
                }
                try {
                    http2Connection.getWriter().applyAndAckSettings((Settings) objectRef.element);
                } catch (IOException e) {
                    http2Connection.failConnection(e);
                }
                Unit unit2 = Unit.INSTANCE;
            }
            if (http2StreamArr != null) {
                int length = http2StreamArr.length;
                while (i < length) {
                    Http2Stream http2Stream = http2StreamArr[i];
                    i++;
                    synchronized (http2Stream) {
                        http2Stream.addBytesToWriteWindow(initialWindowSize);
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void ping(boolean ack, final int payload1, final int payload2) {
            if (!ack) {
                TaskQueue taskQueue = this.this$0.writerQueue;
                final String strStringPlus = Intrinsics.stringPlus(this.this$0.getConnectionName(), " ping");
                final Http2Connection http2Connection = this.this$0;
                final boolean z = true;
                taskQueue.schedule(new Task(strStringPlus, z, http2Connection, payload1, payload2) { // from class: okhttp3.internal.http2.Http2Connection$ReaderRunnable$ping$$inlined$execute$default$1
                    final /* synthetic */ boolean $cancelable;
                    final /* synthetic */ String $name;
                    final /* synthetic */ int $payload1$inlined;
                    final /* synthetic */ int $payload2$inlined;
                    final /* synthetic */ Http2Connection this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(strStringPlus, z);
                        this.$name = strStringPlus;
                        this.$cancelable = z;
                        this.this$0 = http2Connection;
                        this.$payload1$inlined = payload1;
                        this.$payload2$inlined = payload2;
                    }

                    @Override // okhttp3.internal.concurrent.Task
                    public long runOnce() {
                        this.this$0.writePing(true, this.$payload1$inlined, this.$payload2$inlined);
                        return -1L;
                    }
                }, 0L);
                return;
            }
            Http2Connection http2Connection2 = this.this$0;
            synchronized (http2Connection2) {
                if (payload1 == 1) {
                    http2Connection2.intervalPongsReceived++;
                } else if (payload1 == 2) {
                    http2Connection2.degradedPongsReceived++;
                } else {
                    if (payload1 == 3) {
                        http2Connection2.awaitPongsReceived++;
                        http2Connection2.notifyAll();
                    }
                    Unit unit = Unit.INSTANCE;
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void goAway(int lastGoodStreamId, ErrorCode errorCode, ByteString debugData) {
            int i;
            Object[] array;
            Intrinsics.checkNotNullParameter(errorCode, "errorCode");
            Intrinsics.checkNotNullParameter(debugData, "debugData");
            debugData.size();
            Http2Connection http2Connection = this.this$0;
            synchronized (http2Connection) {
                i = 0;
                array = http2Connection.getStreams$okhttp().values().toArray(new Http2Stream[0]);
                if (array == null) {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                }
                http2Connection.isShutdown = true;
                Unit unit = Unit.INSTANCE;
            }
            Http2Stream[] http2StreamArr = (Http2Stream[]) array;
            int length = http2StreamArr.length;
            while (i < length) {
                Http2Stream http2Stream = http2StreamArr[i];
                i++;
                if (http2Stream.getId() > lastGoodStreamId && http2Stream.isLocallyInitiated()) {
                    http2Stream.receiveRstStream(ErrorCode.REFUSED_STREAM);
                    this.this$0.removeStream$okhttp(http2Stream.getId());
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void windowUpdate(int streamId, long windowSizeIncrement) {
            if (streamId == 0) {
                Http2Connection http2Connection = this.this$0;
                synchronized (http2Connection) {
                    http2Connection.writeBytesMaximum = http2Connection.getWriteBytesMaximum() + windowSizeIncrement;
                    http2Connection.notifyAll();
                    Unit unit = Unit.INSTANCE;
                }
                return;
            }
            Http2Stream stream = this.this$0.getStream(streamId);
            if (stream != null) {
                synchronized (stream) {
                    stream.addBytesToWriteWindow(windowSizeIncrement);
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }

        @Override // okhttp3.internal.http2.Http2Reader.Handler
        public void pushPromise(int streamId, int promisedStreamId, List<Header> requestHeaders) {
            Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
            this.this$0.pushRequestLater$okhttp(promisedStreamId, requestHeaders);
        }
    }

    public final void close$okhttp(ErrorCode connectionCode, ErrorCode streamCode, IOException cause) {
        int i;
        Intrinsics.checkNotNullParameter(connectionCode, "connectionCode");
        Intrinsics.checkNotNullParameter(streamCode, "streamCode");
        if (!Util.assertionsEnabled || !Thread.holdsLock(this)) {
            try {
                shutdown(connectionCode);
            } catch (IOException unused) {
            }
            Object[] array = null;
            synchronized (this) {
                if (!getStreams$okhttp().isEmpty()) {
                    array = getStreams$okhttp().values().toArray(new Http2Stream[0]);
                    if (array == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
                    }
                    getStreams$okhttp().clear();
                }
                Unit unit = Unit.INSTANCE;
            }
            Http2Stream[] http2StreamArr = (Http2Stream[]) array;
            if (http2StreamArr != null) {
                for (Http2Stream http2Stream : http2StreamArr) {
                    try {
                        http2Stream.close(streamCode, cause);
                    } catch (IOException unused2) {
                    }
                }
            }
            try {
                getWriter().close();
            } catch (IOException unused3) {
            }
            try {
                getSocket().close();
            } catch (IOException unused4) {
            }
            this.writerQueue.shutdown();
            this.pushQueue.shutdown();
            this.settingsListenerQueue.shutdown();
            return;
        }
        throw new AssertionError("Thread " + ((Object) Thread.currentThread().getName()) + " MUST NOT hold lock on " + this);
    }

    public final void pushRequestLater$okhttp(final int streamId, final List<Header> requestHeaders) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        synchronized (this) {
            if (this.currentPushRequests.contains(Integer.valueOf(streamId))) {
                writeSynResetLater$okhttp(streamId, ErrorCode.PROTOCOL_ERROR);
                return;
            }
            this.currentPushRequests.add(Integer.valueOf(streamId));
            TaskQueue taskQueue = this.pushQueue;
            final String str = this.connectionName + '[' + streamId + "] onRequest";
            final boolean z = true;
            taskQueue.schedule(new Task(str, z, this, streamId, requestHeaders) { // from class: okhttp3.internal.http2.Http2Connection$pushRequestLater$$inlined$execute$default$1
                final /* synthetic */ boolean $cancelable;
                final /* synthetic */ String $name;
                final /* synthetic */ List $requestHeaders$inlined;
                final /* synthetic */ int $streamId$inlined;
                final /* synthetic */ Http2Connection this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(str, z);
                    this.$name = str;
                    this.$cancelable = z;
                    this.this$0 = this;
                    this.$streamId$inlined = streamId;
                    this.$requestHeaders$inlined = requestHeaders;
                }

                @Override // okhttp3.internal.concurrent.Task
                public long runOnce() {
                    if (!this.this$0.pushObserver.onRequest(this.$streamId$inlined, this.$requestHeaders$inlined)) {
                        return -1L;
                    }
                    try {
                        this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                        synchronized (this.this$0) {
                            this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                        }
                        return -1L;
                    } catch (IOException unused) {
                        return -1L;
                    }
                }
            }, 0L);
        }
    }

    public final void pushHeadersLater$okhttp(final int streamId, final List<Header> requestHeaders, final boolean inFinished) {
        Intrinsics.checkNotNullParameter(requestHeaders, "requestHeaders");
        TaskQueue taskQueue = this.pushQueue;
        final String str = this.connectionName + '[' + streamId + "] onHeaders";
        final boolean z = true;
        taskQueue.schedule(new Task(str, z, this, streamId, requestHeaders, inFinished) { // from class: okhttp3.internal.http2.Http2Connection$pushHeadersLater$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ boolean $inFinished$inlined;
            final /* synthetic */ String $name;
            final /* synthetic */ List $requestHeaders$inlined;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ Http2Connection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.this$0 = this;
                this.$streamId$inlined = streamId;
                this.$requestHeaders$inlined = requestHeaders;
                this.$inFinished$inlined = inFinished;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                boolean zOnHeaders = this.this$0.pushObserver.onHeaders(this.$streamId$inlined, this.$requestHeaders$inlined, this.$inFinished$inlined);
                if (zOnHeaders) {
                    try {
                        this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                    } catch (IOException unused) {
                        return -1L;
                    }
                }
                if (!zOnHeaders && !this.$inFinished$inlined) {
                    return -1L;
                }
                synchronized (this.this$0) {
                    this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                }
                return -1L;
            }
        }, 0L);
    }

    public final void pushDataLater$okhttp(final int streamId, BufferedSource source, final int byteCount, final boolean inFinished) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        final Buffer buffer = new Buffer();
        long j = byteCount;
        source.require(j);
        source.read(buffer, j);
        TaskQueue taskQueue = this.pushQueue;
        final String str = this.connectionName + '[' + streamId + "] onData";
        final boolean z = true;
        taskQueue.schedule(new Task(str, z, this, streamId, buffer, byteCount, inFinished) { // from class: okhttp3.internal.http2.Http2Connection$pushDataLater$$inlined$execute$default$1
            final /* synthetic */ Buffer $buffer$inlined;
            final /* synthetic */ int $byteCount$inlined;
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ boolean $inFinished$inlined;
            final /* synthetic */ String $name;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ Http2Connection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.this$0 = this;
                this.$streamId$inlined = streamId;
                this.$buffer$inlined = buffer;
                this.$byteCount$inlined = byteCount;
                this.$inFinished$inlined = inFinished;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                try {
                    boolean zOnData = this.this$0.pushObserver.onData(this.$streamId$inlined, this.$buffer$inlined, this.$byteCount$inlined, this.$inFinished$inlined);
                    if (zOnData) {
                        this.this$0.getWriter().rstStream(this.$streamId$inlined, ErrorCode.CANCEL);
                    }
                    if (!zOnData && !this.$inFinished$inlined) {
                        return -1L;
                    }
                    synchronized (this.this$0) {
                        this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                    }
                    return -1L;
                } catch (IOException unused) {
                    return -1L;
                }
            }
        }, 0L);
    }

    public final void pushResetLater$okhttp(final int streamId, final ErrorCode errorCode) {
        Intrinsics.checkNotNullParameter(errorCode, "errorCode");
        TaskQueue taskQueue = this.pushQueue;
        final String str = this.connectionName + '[' + streamId + "] onReset";
        final boolean z = true;
        taskQueue.schedule(new Task(str, z, this, streamId, errorCode) { // from class: okhttp3.internal.http2.Http2Connection$pushResetLater$$inlined$execute$default$1
            final /* synthetic */ boolean $cancelable;
            final /* synthetic */ ErrorCode $errorCode$inlined;
            final /* synthetic */ String $name;
            final /* synthetic */ int $streamId$inlined;
            final /* synthetic */ Http2Connection this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(str, z);
                this.$name = str;
                this.$cancelable = z;
                this.this$0 = this;
                this.$streamId$inlined = streamId;
                this.$errorCode$inlined = errorCode;
            }

            @Override // okhttp3.internal.concurrent.Task
            public long runOnce() {
                this.this$0.pushObserver.onReset(this.$streamId$inlined, this.$errorCode$inlined);
                synchronized (this.this$0) {
                    this.this$0.currentPushRequests.remove(Integer.valueOf(this.$streamId$inlined));
                    Unit unit = Unit.INSTANCE;
                }
                return -1L;
            }
        }, 0L);
    }

    /* JADX INFO: compiled from: Http2Connection.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lokhttp3/internal/http2/Http2Connection$Companion;", "", "()V", "AWAIT_PING", "", "DEFAULT_SETTINGS", "Lokhttp3/internal/http2/Settings;", "getDEFAULT_SETTINGS", "()Lokhttp3/internal/http2/Settings;", "DEGRADED_PING", "DEGRADED_PONG_TIMEOUT_NS", "INTERVAL_PING", "OKHTTP_CLIENT_WINDOW_SIZE", "okhttp"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Settings getDEFAULT_SETTINGS() {
            return Http2Connection.DEFAULT_SETTINGS;
        }
    }

    static {
        Settings settings = new Settings();
        settings.set(7, SupportMenu.USER_MASK);
        settings.set(5, 16384);
        DEFAULT_SETTINGS = settings;
    }
}
