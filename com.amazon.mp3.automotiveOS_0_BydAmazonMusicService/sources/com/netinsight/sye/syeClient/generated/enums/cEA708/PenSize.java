package com.netinsight.sye.syeClient.generated.enums.cEA708;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000e\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0003B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/netinsight/sye/syeClient/generated/enums/cEA708/PenSize;", "", "", "a", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "Small", "Standard", "Large", "VerySmall", "VeryLarge", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum PenSize {
    Small(0),
    Standard(1),
    Large(2),
    VerySmall(3),
    VeryLarge(4);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.generated.enums.cEA708.PenSize$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final PenSize a(int i) {
            if (i == 0) {
                return PenSize.Small;
            }
            if (i == 1) {
                return PenSize.Standard;
            }
            if (i == 2) {
                return PenSize.Large;
            }
            if (i == 3) {
                return PenSize.VerySmall;
            }
            if (i == 4) {
                return PenSize.VeryLarge;
            }
            throw new IllegalArgumentException("No PenSize with value " + i);
        }
    }

    PenSize(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final <T extends Collection<PenSize>> T parseJsonArray(String str, T t) {
        Companion companion = INSTANCE;
        companion.getClass();
        JSONArray jSONArray = new JSONArray(str);
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            t.add(companion.a(jSONArray.getInt(i)));
        }
        return t;
    }

    @JvmStatic
    public static final PenSize parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
