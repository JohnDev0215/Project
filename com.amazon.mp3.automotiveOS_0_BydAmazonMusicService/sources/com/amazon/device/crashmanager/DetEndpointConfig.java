package com.amazon.device.crashmanager;

/* JADX INFO: loaded from: classes2.dex */
public class DetEndpointConfig {
    private static final String DET_BETA_URN = "https://det-ta-g7g.integ.amazon.com:443";
    private static final String DET_PROD_URN = "https://det-ta-g7g.amazon.com:443";

    /* JADX INFO: renamed from: com.amazon.device.crashmanager.DetEndpointConfig$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$crashmanager$Domain;

        static {
            int[] iArr = new int[Domain.values().length];
            $SwitchMap$com$amazon$device$crashmanager$Domain = iArr;
            try {
                iArr[Domain.PROD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$device$crashmanager$Domain[Domain.BETA.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static String getServiceEndpoint(Domain domain) {
        return AnonymousClass1.$SwitchMap$com$amazon$device$crashmanager$Domain[domain.ordinal()] != 1 ? DET_BETA_URN : DET_PROD_URN;
    }
}
