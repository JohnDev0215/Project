package ts.car.service.tbox.data;

/* JADX INFO: loaded from: classes.dex */
public enum CallDirection {
    UNKNOWN,
    IDLE,
    CALL_IN,
    CALL_OUT;

    static final int MAGIC_CALL_IDLE = 1;
    static final int MAGIC_CALL_IN = 2;
    static final int MAGIC_CALL_OUT = 3;
    static final int MAGIC_CALL_UNKNOWN = 0;

    public static CallDirection toCallDirection(int direction) {
        if (direction == 0) {
            return UNKNOWN;
        }
        if (direction == 1) {
            return IDLE;
        }
        if (direction == 2) {
            return CALL_IN;
        }
        if (direction == 3) {
            return CALL_OUT;
        }
        return UNKNOWN;
    }

    public static int callDirectionToInt(CallDirection dir) {
        if (dir == null) {
            return 0;
        }
        int i = AnonymousClass1.$SwitchMap$ts$car$service$tbox$data$CallDirection[dir.ordinal()];
        if (i == 1) {
            return 1;
        }
        if (i != 2) {
            return i != 3 ? 0 : 3;
        }
        return 2;
    }

    /* JADX INFO: renamed from: ts.car.service.tbox.data.CallDirection$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ts$car$service$tbox$data$CallDirection;

        static {
            int[] iArr = new int[CallDirection.values().length];
            $SwitchMap$ts$car$service$tbox$data$CallDirection = iArr;
            try {
                iArr[CallDirection.IDLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$ts$car$service$tbox$data$CallDirection[CallDirection.CALL_IN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$ts$car$service$tbox$data$CallDirection[CallDirection.CALL_OUT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }
}
