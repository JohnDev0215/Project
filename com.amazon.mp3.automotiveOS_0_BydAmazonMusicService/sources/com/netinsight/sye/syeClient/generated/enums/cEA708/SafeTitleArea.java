package com.netinsight.sye.syeClient.generated.enums.cEA708;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000b\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0003B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\f¨\u0006\r"}, d2 = {"Lcom/netinsight/sye/syeClient/generated/enums/cEA708/SafeTitleArea;", "", "", "a", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "Percent_80", "Percent_90", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum SafeTitleArea {
    Percent_80(0),
    Percent_90(1);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.generated.enums.cEA708.SafeTitleArea$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final SafeTitleArea a(int i) {
            if (i == 0) {
                return SafeTitleArea.Percent_80;
            }
            if (i == 1) {
                return SafeTitleArea.Percent_90;
            }
            throw new IllegalArgumentException("No SafeTitleArea with value " + i);
        }
    }

    SafeTitleArea(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final <T extends Collection<SafeTitleArea>> T parseJsonArray(String str, T t) {
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
    public static final SafeTitleArea parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
