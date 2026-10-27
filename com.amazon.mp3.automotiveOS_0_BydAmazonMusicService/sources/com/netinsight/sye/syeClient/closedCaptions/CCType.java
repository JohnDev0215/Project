package com.netinsight.sye.syeClient.closedCaptions;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000bB\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"Lcom/netinsight/sye/syeClient/closedCaptions/CCType;", "", "", "b", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "a", "NTSCC", "DTVCC", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum CCType {
    NTSCC(0),
    DTVCC(1);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.closedCaptions.CCType$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final CCType a(int i) {
            if (i == 0) {
                return CCType.NTSCC;
            }
            if (i == 1) {
                return CCType.DTVCC;
            }
            throw new IllegalArgumentException("No ServiceIndex with value " + i);
        }
    }

    CCType(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final CCType parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
