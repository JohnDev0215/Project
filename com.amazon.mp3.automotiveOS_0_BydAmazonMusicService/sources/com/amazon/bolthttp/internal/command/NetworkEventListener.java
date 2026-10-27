package com.amazon.bolthttp.internal.command;

import com.amazon.bolthttp.EventListener;
import com.amazon.bolthttp.Request;
import com.amazon.bolthttp.internal.util.TimeUtils;
import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes2.dex */
public class NetworkEventListener extends EventListener {
    private final long mCallStartMillis;
    private long mConnectionStartTimeMillis;
    private long mDnsStartTimeMillis;
    private final Request mRequest;
    private long mRequestStartStartTimeMillis;

    @Override // okhttp3.EventListener
    public void callEnd(Call call) {
    }

    @Override // okhttp3.EventListener
    public void callStart(Call call) {
    }

    private NetworkEventListener(@Nonnull Request request, @Nonnegative long j) {
        this.mDnsStartTimeMillis = -1L;
        this.mConnectionStartTimeMillis = -1L;
        this.mRequestStartStartTimeMillis = -1L;
        this.mRequest = (Request) Preconditions.checkNotNull(request, "request");
        this.mCallStartMillis = j;
    }

    @Override // okhttp3.EventListener
    public void dnsStart(@Nonnull Call call, @Nonnull String str) {
        this.mDnsStartTimeMillis = TimeUtils.getTimeMillis();
    }

    @Override // okhttp3.EventListener
    public void dnsEnd(@Nonnull Call call, @Nonnull String str, @Nonnull List<InetAddress> list) {
        if (this.mDnsStartTimeMillis < 0) {
            throw new IllegalStateException("mDnsStartTimeMillis < 0");
        }
        reportEvent(EventListener.NetworkConnectionEvent.State.DNS_LOOKUP_ONLY, TimeUtils.getTimeMillis() - this.mDnsStartTimeMillis, null);
    }

    @Override // okhttp3.EventListener
    public void connectStart(@Nonnull Call call, @Nonnull InetSocketAddress inetSocketAddress, @Nonnull Proxy proxy) {
        this.mConnectionStartTimeMillis = TimeUtils.getTimeMillis();
    }

    @Override // okhttp3.EventListener
    public void connectEnd(@Nonnull Call call, @Nonnull InetSocketAddress inetSocketAddress, @Nonnull Proxy proxy, @Nullable Protocol protocol) {
        if (this.mConnectionStartTimeMillis < 0) {
            throw new IllegalStateException("mConnectionStartTimeMillis < 0");
        }
        reportEvent(EventListener.NetworkConnectionEvent.State.CONNECTION_SETUP_ONLY, TimeUtils.getTimeMillis() - this.mConnectionStartTimeMillis, null);
    }

    @Override // okhttp3.EventListener
    public void requestHeadersStart(@Nonnull Call call) {
        this.mRequestStartStartTimeMillis = TimeUtils.getTimeMillis();
    }

    @Override // okhttp3.EventListener
    public void requestBodyEnd(@Nonnull Call call, long j) {
        if (this.mRequestStartStartTimeMillis < 0) {
            throw new IllegalStateException("mRequestStartStartTimeMillis < 0");
        }
        reportEvent(EventListener.NetworkConnectionEvent.State.REQUEST_SEND_ONLY, TimeUtils.getTimeMillis() - this.mRequestStartStartTimeMillis, Long.valueOf(j));
    }

    @Override // okhttp3.EventListener
    public void responseHeadersStart(@Nonnull Call call) {
        reportEvent(EventListener.NetworkConnectionEvent.State.RESPONSE_FIRST_BYTE, TimeUtils.getTimeMillis() - this.mCallStartMillis, null);
    }

    @Override // okhttp3.EventListener
    public void responseBodyEnd(@Nonnull Call call, long j) {
        reportEvent(EventListener.NetworkConnectionEvent.State.RESPONSE_LAST_BYTE, TimeUtils.getTimeMillis() - this.mCallStartMillis, Long.valueOf(j));
    }

    private void reportEvent(@Nonnull EventListener.NetworkConnectionEvent.State state, @Nonnegative long j, @Nullable Long l) {
        List<com.amazon.bolthttp.EventListener> eventListeners = this.mRequest.getEventListeners();
        if (eventListeners.isEmpty()) {
            return;
        }
        EventListener.NetworkConnectionEvent networkConnectionEvent = new EventListener.NetworkConnectionEvent(state, j, l);
        Iterator<com.amazon.bolthttp.EventListener> it = eventListeners.iterator();
        while (it.hasNext()) {
            it.next().onNetworkConnectionEvent(networkConnectionEvent, this.mRequest);
        }
    }

    public static class Factory implements EventListener.Factory {
        private final long mCallStartMillis;
        private final Request mRequest;

        public Factory(@Nonnull Request request, @Nonnegative long j) {
            this.mRequest = request;
            this.mCallStartMillis = j;
        }

        @Override // okhttp3.EventListener.Factory
        public okhttp3.EventListener create(Call call) {
            return new NetworkEventListener(this.mRequest, this.mCallStartMillis);
        }
    }
}
