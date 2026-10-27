package com.amazon.avod.perf;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Conditional {

    enum Result {
        START,
        PENDING,
        FINISHED,
        FAILED
    }

    abstract Result evaluate();

    abstract void reset();

    private static final class Sequence extends Conditional {
        private int mConditionalPos;
        private final Conditional[] mConditionals;

        private Sequence(Conditional[] conditionalArr) {
            this.mConditionalPos = 0;
            this.mConditionals = conditionalArr;
        }

        @Override // com.amazon.avod.perf.Conditional
        final Result evaluate() {
            int i = AnonymousClass7.$SwitchMap$com$amazon$avod$perf$Conditional$Result[this.mConditionals[this.mConditionalPos].evaluate().ordinal()];
            if (i != 1) {
                if (i == 2) {
                    this.mConditionalPos = 0;
                    return Result.FAILED;
                }
                if (this.mConditionalPos == 0) {
                    return Result.FAILED;
                }
                return Result.PENDING;
            }
            int i2 = this.mConditionalPos + 1;
            this.mConditionalPos = i2;
            if (i2 == 1) {
                return Result.START;
            }
            if (this.mConditionals.length == i2) {
                return Result.FINISHED;
            }
            return Result.PENDING;
        }

        @Override // com.amazon.avod.perf.Conditional
        final void reset() {
            for (Conditional conditional : this.mConditionals) {
                conditional.reset();
            }
            this.mConditionalPos = 0;
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.perf.Conditional$7, reason: invalid class name */
    static /* synthetic */ class AnonymousClass7 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$perf$Conditional$Result;

        static {
            int[] iArr = new int[Result.values().length];
            $SwitchMap$com$amazon$avod$perf$Conditional$Result = iArr;
            try {
                iArr[Result.FINISHED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Result.FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Result.START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$perf$Conditional$Result[Result.PENDING.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public static final Conditional sequence(Conditional... conditionalArr) {
        return new Sequence(conditionalArr);
    }

    public static final Conditional is(final Marker marker) {
        return new Conditional() { // from class: com.amazon.avod.perf.Conditional.1
            @Override // com.amazon.avod.perf.Conditional
            void reset() {
            }

            @Override // com.amazon.avod.perf.Conditional
            public final Result evaluate() {
                return Profiler.mCurrentMarker == marker ? Result.FINISHED : Result.PENDING;
            }
        };
    }
}
