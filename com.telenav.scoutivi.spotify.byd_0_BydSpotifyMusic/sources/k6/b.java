package k6;

/* JADX INFO: compiled from: ErrorCode.kt */
/* JADX INFO: loaded from: classes.dex */
public enum b {
    NO_ERROR(0),
    PROTOCOL_ERROR(1),
    INTERNAL_ERROR(2),
    FLOW_CONTROL_ERROR(3),
    SETTINGS_TIMEOUT(4),
    STREAM_CLOSED(5),
    FRAME_SIZE_ERROR(6),
    REFUSED_STREAM(7),
    CANCEL(8),
    COMPRESSION_ERROR(9),
    CONNECT_ERROR(10),
    ENHANCE_YOUR_CALM(11),
    INADEQUATE_SECURITY(12),
    HTTP_1_1_REQUIRED(13);

    public static final a Companion = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final int f7278e;

    /* JADX INFO: compiled from: ErrorCode.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final b a(int i7) {
            for (b bVar : b.values()) {
                if (bVar.getHttpCode() == i7) {
                    return bVar;
                }
            }
            return null;
        }
    }

    b(int i7) {
        this.f7278e = i7;
    }

    public final int getHttpCode() {
        return this.f7278e;
    }
}
