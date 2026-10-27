package androidx.lifecycle;

import java.util.concurrent.atomic.AtomicReference;
import x0.e;

/* JADX INFO: loaded from: classes.dex */
public abstract class Lifecycle {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public AtomicReference<Object> f1437a = new AtomicReference<>();

    public enum Event {
        ON_CREATE,
        ON_START,
        ON_RESUME,
        ON_PAUSE,
        ON_STOP,
        ON_DESTROY,
        ON_ANY;

        public static Event downFrom(State state) {
            int i8 = a.f1438a[state.ordinal()];
            if (i8 == 1) {
                return ON_DESTROY;
            }
            if (i8 == 2) {
                return ON_STOP;
            }
            if (i8 != 3) {
                return null;
            }
            return ON_PAUSE;
        }

        public static Event downTo(State state) {
            int i8 = a.f1438a[state.ordinal()];
            if (i8 == 1) {
                return ON_STOP;
            }
            if (i8 == 2) {
                return ON_PAUSE;
            }
            if (i8 != 4) {
                return null;
            }
            return ON_DESTROY;
        }

        public static Event upFrom(State state) {
            int i8 = a.f1438a[state.ordinal()];
            if (i8 == 1) {
                return ON_START;
            }
            if (i8 == 2) {
                return ON_RESUME;
            }
            if (i8 != 5) {
                return null;
            }
            return ON_CREATE;
        }

        public static Event upTo(State state) {
            int i8 = a.f1438a[state.ordinal()];
            if (i8 == 1) {
                return ON_CREATE;
            }
            if (i8 == 2) {
                return ON_START;
            }
            if (i8 != 3) {
                return null;
            }
            return ON_RESUME;
        }

        public State getTargetState() {
            switch (a.f1439b[ordinal()]) {
                case 1:
                case 2:
                    return State.CREATED;
                case 3:
                case 4:
                    return State.STARTED;
                case 5:
                    return State.RESUMED;
                case 6:
                    return State.DESTROYED;
                default:
                    throw new IllegalArgumentException(this + " has no target state");
            }
        }
    }

    public enum State {
        DESTROYED,
        INITIALIZED,
        CREATED,
        STARTED,
        RESUMED;

        public boolean isAtLeast(State state) {
            return compareTo(state) >= 0;
        }
    }

    public static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f1438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final /* synthetic */ int[] f1439b;

        static {
            int[] iArr = new int[Event.values().length];
            f1439b = iArr;
            try {
                iArr[Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1439b[Event.ON_STOP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1439b[Event.ON_START.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1439b[Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1439b[Event.ON_RESUME.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1439b[Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1439b[Event.ON_ANY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            int[] iArr2 = new int[State.values().length];
            f1438a = iArr2;
            try {
                iArr2[State.CREATED.ordinal()] = 1;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f1438a[State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                f1438a[State.RESUMED.ordinal()] = 3;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                f1438a[State.DESTROYED.ordinal()] = 4;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                f1438a[State.INITIALIZED.ordinal()] = 5;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    public abstract void a(e eVar);

    public abstract State b();

    public abstract void c(e eVar);
}
