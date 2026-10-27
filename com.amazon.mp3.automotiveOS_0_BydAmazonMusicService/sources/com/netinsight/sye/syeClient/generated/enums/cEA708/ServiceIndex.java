package com.netinsight.sye.syeClient.generated.enums.cEA708;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import org.json.JSONArray;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \n2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0003B\u0011\b\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002¢\u0006\u0004\b\b\u0010\tR\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Lcom/netinsight/sye/syeClient/generated/enums/cEA708/ServiceIndex;", "", "", "a", "I", "getValue", "()I", "value", "<init>", "(Ljava/lang/String;II)V", "Companion", "PrimaryLanguage", "SecondaryLanguage", "Service3", "Service4", "Service5", "Service6", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public enum ServiceIndex {
    PrimaryLanguage(1),
    SecondaryLanguage(2),
    Service3(3),
    Service4(4),
    Service5(5),
    Service6(6);


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final int value;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.generated.enums.cEA708.ServiceIndex$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final ServiceIndex a(int i) {
            switch (i) {
                case 1:
                    return ServiceIndex.PrimaryLanguage;
                case 2:
                    return ServiceIndex.SecondaryLanguage;
                case 3:
                    return ServiceIndex.Service3;
                case 4:
                    return ServiceIndex.Service4;
                case 5:
                    return ServiceIndex.Service5;
                case 6:
                    return ServiceIndex.Service6;
                default:
                    throw new IllegalArgumentException("No ServiceIndex with value " + i);
            }
        }

        @JvmStatic
        public final <T extends Collection<ServiceIndex>> T a(String str, T t) {
            JSONArray jSONArray = new JSONArray(str);
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                t.add(a(jSONArray.getInt(i)));
            }
            return t;
        }
    }

    ServiceIndex(int i) {
        this.value = i;
    }

    @JvmStatic
    public static final <T extends Collection<ServiceIndex>> T parseJsonArray(String str, T t) {
        return (T) INSTANCE.a(str, t);
    }

    @JvmStatic
    public static final ServiceIndex parseValue(int i) {
        return INSTANCE.a(i);
    }

    public final int getValue() {
        return this.value;
    }
}
