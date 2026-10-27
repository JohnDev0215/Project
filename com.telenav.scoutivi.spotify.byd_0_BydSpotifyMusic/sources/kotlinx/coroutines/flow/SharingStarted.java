package kotlinx.coroutines.flow;

/* JADX INFO: compiled from: SharingStarted.kt */
/* JADX INFO: loaded from: classes.dex */
public interface SharingStarted {
    public static final Companion Companion = Companion.$$INSTANCE;

    /* JADX INFO: compiled from: SharingStarted.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final SharingStarted Eagerly = new StartedEagerly();
        private static final SharingStarted Lazily = new StartedLazily();

        private Companion() {
        }

        public static /* synthetic */ SharingStarted WhileSubscribed$default(Companion companion, long j7, long j8, int i7, Object obj) {
            if ((i7 & 1) != 0) {
                j7 = 0;
            }
            if ((i7 & 2) != 0) {
                j8 = Long.MAX_VALUE;
            }
            return companion.WhileSubscribed(j7, j8);
        }

        public final SharingStarted WhileSubscribed(long j7, long j8) {
            return new StartedWhileSubscribed(j7, j8);
        }

        public final SharingStarted getEagerly() {
            return Eagerly;
        }

        public final SharingStarted getLazily() {
            return Lazily;
        }
    }

    Flow<SharingCommand> command(StateFlow<Integer> stateFlow);
}
