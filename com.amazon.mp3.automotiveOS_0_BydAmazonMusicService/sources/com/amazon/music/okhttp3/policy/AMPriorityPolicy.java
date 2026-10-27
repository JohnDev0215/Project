package com.amazon.music.okhttp3.policy;

import com.amazon.net.okhttp3.PriorityPolicy;
import kotlin.Metadata;
import okhttp3.Request;

/* JADX INFO: compiled from: AMPriorityPolicy.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\tB\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy;", "Lcom/amazon/net/okhttp3/PriorityPolicy;", "()V", "prioritizer", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer;", "request", "Lokhttp3/Request;", "toString", "", "Prioritizer", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public abstract class AMPriorityPolicy implements PriorityPolicy {
    public abstract Prioritizer prioritizer(Request request);

    public String toString() {
        return "unknown capacity";
    }

    /* JADX INFO: compiled from: AMPriorityPolicy.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0006\u0007J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer;", "", "blockUntilAllowed", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer$BlockResult;", "releaseCapacity", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer$ReleaseResult;", "BlockResult", "ReleaseResult", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface Prioritizer {
        BlockResult blockUntilAllowed() throws InterruptedException;

        ReleaseResult releaseCapacity();

        /* JADX INFO: compiled from: AMPriorityPolicy.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer$BlockResult;", "", "text", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "BLOCKED", "IMMEDIATE", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum BlockResult {
            BLOCKED("the prioritizer blocked the thread to delay request start"),
            IMMEDIATE("the prioritizer did not delay request start");

            private final String text;

            BlockResult(String str) {
                this.text = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.text;
            }
        }

        /* JADX INFO: compiled from: AMPriorityPolicy.kt */
        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0003H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer$ReleaseResult;", "", "text", "", "(Ljava/lang/String;ILjava/lang/String;)V", "toString", "RELEASED", "NOOP", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
        public enum ReleaseResult {
            RELEASED("the prioritizer released the reserved capacity"),
            NOOP("the prioritizer did not reserve capacity, or already released it");

            private final String text;

            ReleaseResult(String str) {
                this.text = str;
            }

            @Override // java.lang.Enum
            public String toString() {
                return this.text;
            }
        }
    }
}
