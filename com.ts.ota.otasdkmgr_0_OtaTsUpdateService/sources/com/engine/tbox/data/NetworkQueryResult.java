package com.engine.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public enum NetworkQueryResult {
    SUCCESS,
    FAILED,
    RESERVE;

    /* JADX INFO: renamed from: com.engine.tbox.data.NetworkQueryResult$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$engine$tbox$data$NetworkQueryResult;

        static {
            int[] iArr = new int[NetworkQueryResult.values().length];
            $SwitchMap$com$engine$tbox$data$NetworkQueryResult = iArr;
            try {
                iArr[NetworkQueryResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$NetworkQueryResult[NetworkQueryResult.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$engine$tbox$data$NetworkQueryResult[NetworkQueryResult.RESERVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public byte[] toByteArray() {
        int i = AnonymousClass1.$SwitchMap$com$engine$tbox$data$NetworkQueryResult[ordinal()];
        if (i == 1) {
            return new byte[]{0};
        }
        if (i == 2) {
            return new byte[]{1};
        }
        return new byte[]{2};
    }
}
