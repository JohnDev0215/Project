package com.spotify.cosmos.queuingrouter;

import com.spotify.cosmos.cosmos.Lifetime;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.ResolveCallback;
import com.spotify.cosmos.cosmos.Router;
import java.util.concurrent.ConcurrentLinkedQueue;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public class CosmosRequestQueue {
    protected final ConcurrentLinkedQueue<QueuedRequest> queuedRequests = new ConcurrentLinkedQueue<>();

    public static final class QueuedRequest {
        private final ResolveCallback callback;
        private final Request request;

        public QueuedRequest(Request request, ResolveCallback resolveCallback) {
            v03.j(request, "request");
            v03.j(resolveCallback, "callback");
            this.request = request;
            this.callback = resolveCallback;
        }

        public static /* synthetic */ QueuedRequest copy$default(QueuedRequest queuedRequest, Request request, ResolveCallback resolveCallback, int i, Object obj) {
            if ((i & 1) != 0) {
                request = queuedRequest.request;
            }
            if ((i & 2) != 0) {
                resolveCallback = queuedRequest.callback;
            }
            return queuedRequest.copy(request, resolveCallback);
        }

        public final Request component1() {
            return this.request;
        }

        public final ResolveCallback component2() {
            return this.callback;
        }

        public final QueuedRequest copy(Request request, ResolveCallback resolveCallback) {
            v03.j(request, "request");
            v03.j(resolveCallback, "callback");
            return new QueuedRequest(request, resolveCallback);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof QueuedRequest)) {
                return false;
            }
            QueuedRequest queuedRequest = (QueuedRequest) obj;
            return v03.b(this.request, queuedRequest.request) && v03.b(this.callback, queuedRequest.callback);
        }

        public final ResolveCallback getCallback() {
            return this.callback;
        }

        public final Request getRequest() {
            return this.request;
        }

        public int hashCode() {
            return this.callback.hashCode() + (this.request.hashCode() * 31);
        }

        public String toString() {
            return "QueuedRequest(request=" + this.request + ", callback=" + this.callback + ')';
        }
    }

    public final void destroy() {
        this.queuedRequests.clear();
    }

    public final Lifetime queue(Request request, ResolveCallback resolveCallback) {
        v03.j(request, "request");
        v03.j(resolveCallback, "callback");
        final QueuedRequest queuedRequest = new QueuedRequest(request, resolveCallback);
        this.queuedRequests.add(queuedRequest);
        return new Lifetime() { // from class: com.spotify.cosmos.queuingrouter.CosmosRequestQueue.queue.1
            @Override // com.spotify.cosmos.cosmos.Lifetime
            public void release() {
                CosmosRequestQueue.this.queuedRequests.remove(queuedRequest);
            }
        };
    }

    public final void replayRequests(Router router) {
        v03.j(router, "router");
        for (QueuedRequest queuedRequest : this.queuedRequests) {
            router.resolve(queuedRequest.getRequest(), queuedRequest.getCallback());
        }
        this.queuedRequests.clear();
    }
}
