package com.amazon.music.okhttp3.policy;

import com.amazon.music.okhttp3.policy.AMPriorityPolicy;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Request;

/* JADX INFO: compiled from: NoOpPriority.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/amazon/music/okhttp3/policy/NoOpPriority;", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy;", "()V", "prioritizer", "Lcom/amazon/music/okhttp3/policy/AMPriorityPolicy$Prioritizer;", "request", "Lokhttp3/Request;", "toString", "", "Companion", "DMMOkHttpClient_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class NoOpPriority extends AMPriorityPolicy {

    @JvmField
    public static final NoOpPriority INSTANCE = new NoOpPriority();
    private static final NoOpPriority$Companion$PRIORITIZER$1 PRIORITIZER = new AMPriorityPolicy.Prioritizer() { // from class: com.amazon.music.okhttp3.policy.NoOpPriority$Companion$PRIORITIZER$1
        @Override // com.amazon.music.okhttp3.policy.AMPriorityPolicy.Prioritizer
        public AMPriorityPolicy.Prioritizer.BlockResult blockUntilAllowed() {
            return AMPriorityPolicy.Prioritizer.BlockResult.IMMEDIATE;
        }

        @Override // com.amazon.music.okhttp3.policy.AMPriorityPolicy.Prioritizer
        public AMPriorityPolicy.Prioritizer.ReleaseResult releaseCapacity() {
            return AMPriorityPolicy.Prioritizer.ReleaseResult.NOOP;
        }
    };

    @Override // com.amazon.music.okhttp3.policy.AMPriorityPolicy
    public String toString() {
        return "unlimited capacity";
    }

    private NoOpPriority() {
    }

    @Override // com.amazon.music.okhttp3.policy.AMPriorityPolicy
    public AMPriorityPolicy.Prioritizer prioritizer(Request request) {
        Intrinsics.checkNotNullParameter(request, "request");
        return PRIORITIZER;
    }
}
