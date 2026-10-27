package com.android.launcher3.util;

/* JADX INFO: loaded from: classes4.dex */
public interface FlagOp {
    public static final FlagOp NO_OP = new FlagOp() { // from class: com.android.launcher3.util.-$$Lambda$FlagOp$1c6G2SMqOpqJgTve8Qb8m29oSGo
        @Override // com.android.launcher3.util.FlagOp
        public final int apply(int i) {
            return FlagOp.lambda$static$0(i);
        }
    };

    static /* synthetic */ int lambda$addFlag$1(int i, int i2) {
        return i | i2;
    }

    static /* synthetic */ int lambda$removeFlag$2(int i, int i2) {
        return (~i) & i2;
    }

    static /* synthetic */ int lambda$static$0(int i) {
        return i;
    }

    int apply(int flags);

    static FlagOp addFlag(final int flag) {
        return new FlagOp() { // from class: com.android.launcher3.util.-$$Lambda$FlagOp$GoTKoEs5qFV9WTaGhXNN6uJFgvE
            @Override // com.android.launcher3.util.FlagOp
            public final int apply(int i) {
                return FlagOp.lambda$addFlag$1(flag, i);
            }
        };
    }

    static FlagOp removeFlag(final int flag) {
        return new FlagOp() { // from class: com.android.launcher3.util.-$$Lambda$FlagOp$-B1ybcvOsAcDWtte4zJ-xQpbKdU
            @Override // com.android.launcher3.util.FlagOp
            public final int apply(int i) {
                return FlagOp.lambda$removeFlag$2(flag, i);
            }
        };
    }
}
