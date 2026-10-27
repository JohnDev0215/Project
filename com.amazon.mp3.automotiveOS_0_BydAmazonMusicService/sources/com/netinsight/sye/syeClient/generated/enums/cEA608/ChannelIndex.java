package com.netinsight.sye.syeClient.generated.enums.cEA608;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\r\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0003B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lcom/netinsight/sye/syeClient/generated/enums/cEA608/ChannelIndex;", "", "", "a", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "CC1", "CC2", "CC3", "CC4", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum ChannelIndex {
    CC1(0),
    CC2(1),
    CC3(2),
    CC4(3);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.generated.enums.cEA608.ChannelIndex$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final ChannelIndex a(int i) {
            if (i == 0) {
                return ChannelIndex.CC1;
            }
            if (i == 1) {
                return ChannelIndex.CC2;
            }
            if (i == 2) {
                return ChannelIndex.CC3;
            }
            if (i == 3) {
                return ChannelIndex.CC4;
            }
            throw new IllegalArgumentException("No ChannelIndex with value " + i);
        }

        @JvmStatic
        public final <T extends Collection<ChannelIndex>> T a(String str, T t) {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                t.add(a(jSONArray.getInt(i)));
            }
            return t;
        }
    }

    ChannelIndex(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final <T extends Collection<ChannelIndex>> T parseJsonArray(String str, T t) {
        return (T) INSTANCE.a(str, t);
    }

    @JvmStatic
    public static final ChannelIndex parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
