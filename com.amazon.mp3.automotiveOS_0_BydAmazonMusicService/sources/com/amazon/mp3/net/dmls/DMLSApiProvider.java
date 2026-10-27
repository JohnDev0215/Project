package com.amazon.mp3.net.dmls;

/* JADX INFO: loaded from: classes3.dex */
public class DMLSApiProvider {
    private static DMLSApiType sType = DMLSApiType.DEFAULT;

    public enum DMLSApiType {
        DEFAULT,
        TEST,
        HARDCODE
    }

    public static DMLSApiType getType() {
        return sType;
    }

    /* JADX INFO: renamed from: com.amazon.mp3.net.dmls.DMLSApiProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$net$dmls$DMLSApiProvider$DMLSApiType;

        static {
            int[] iArr = new int[DMLSApiType.values().length];
            $SwitchMap$com$amazon$mp3$net$dmls$DMLSApiProvider$DMLSApiType = iArr;
            try {
                iArr[DMLSApiType.TEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$net$dmls$DMLSApiProvider$DMLSApiType[DMLSApiType.HARDCODE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static DMLSApi get() {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$net$dmls$DMLSApiProvider$DMLSApiType[getType().ordinal()];
        if (i == 1) {
            return TestDMLSApi.getInstance();
        }
        if (i == 2) {
            return HardcodeDMLSApi.getInstance();
        }
        return DefaultDMLSApi.getInstance();
    }
}
