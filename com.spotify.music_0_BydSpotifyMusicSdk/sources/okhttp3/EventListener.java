package okhttp3;

/* JADX INFO: loaded from: classes.dex */
public abstract class EventListener {
    public static final EventListener$Companion$NONE$1 a;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    public interface Factory {
        EventListener a(Call call);
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [okhttp3.EventListener$Companion$NONE$1] */
    static {
        new Companion(0);
        a = new EventListener() { // from class: okhttp3.EventListener$Companion$NONE$1
        };
    }
}
