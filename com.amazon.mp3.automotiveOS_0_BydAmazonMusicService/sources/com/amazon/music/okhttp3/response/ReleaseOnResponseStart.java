package com.amazon.music.okhttp3.response;

import com.amazon.music.okhttp3.policy.AMPriorityPolicy;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: compiled from: ReleaseOnResponseStart.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J,\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\r"}, d2 = {"Lcom/amazon/music/okhttp3/response/ReleaseOnResponseStart;", "Lcom/amazon/music/okhttp3/response/AMResponseRewriter;", "()V", "rewrite", "Lokhttp3/Response;", "request", "Lokhttp3/Request;", "prioritizer", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer;", "response", "exception", "Ljava/lang/Exception;", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ReleaseOnResponseStart implements AMResponseRewriter {

    @JvmField
    public static final ReleaseOnResponseStart INSTANCE = new ReleaseOnResponseStart();

    private ReleaseOnResponseStart() {
    }

    @Override // com.amazon.music.okhttp3.response.AMResponseRewriter
    public Response rewrite(Request request, AMPriorityPolicy.Prioritizer prioritizer, Response response, Exception exception) throws IOException {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(prioritizer, "prioritizer");
        prioritizer.releaseCapacity();
        return response == null ? AMResponseRewriterKt.failureResponse(request, exception) : response;
    }
}
