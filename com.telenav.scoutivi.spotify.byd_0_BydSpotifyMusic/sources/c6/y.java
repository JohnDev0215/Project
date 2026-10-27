package c6;

import java.io.IOException;

/* JADX INFO: compiled from: Protocol.kt */
/* JADX INFO: loaded from: classes.dex */
public enum y {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final a Companion = new a(null);

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f3922e;

    /* JADX INFO: compiled from: Protocol.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final y a(String protocol) throws IOException {
            kotlin.jvm.internal.l.e(protocol, "protocol");
            y yVar = y.HTTP_1_0;
            if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                yVar = y.HTTP_1_1;
                if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                    yVar = y.H2_PRIOR_KNOWLEDGE;
                    if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                        yVar = y.HTTP_2;
                        if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                            yVar = y.SPDY_3;
                            if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                                yVar = y.QUIC;
                                if (!kotlin.jvm.internal.l.a(protocol, yVar.f3922e)) {
                                    throw new IOException("Unexpected protocol: " + protocol);
                                }
                            }
                        }
                    }
                }
            }
            return yVar;
        }
    }

    y(String str) {
        this.f3922e = str;
    }

    public static final y get(String str) {
        return Companion.a(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.f3922e;
    }
}
