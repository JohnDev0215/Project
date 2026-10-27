package okhttp3;

import java.io.IOException;
import kotlin.jvm.internal.f;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: Protocol.kt */
/* JADX INFO: loaded from: classes.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final Companion Companion = new Companion(null);
    private final String protocol;

    /* JADX INFO: compiled from: Protocol.kt */
    public static final class Companion {
        private Companion() {
        }

        public final Protocol get(String str) throws IOException {
            i.c(str, "protocol");
            Protocol protocol = Protocol.HTTP_1_0;
            if (!i.a(str, protocol.protocol)) {
                protocol = Protocol.HTTP_1_1;
                if (!i.a(str, protocol.protocol)) {
                    protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                    if (!i.a(str, protocol.protocol)) {
                        protocol = Protocol.HTTP_2;
                        if (!i.a(str, protocol.protocol)) {
                            protocol = Protocol.SPDY_3;
                            if (!i.a(str, protocol.protocol)) {
                                protocol = Protocol.QUIC;
                                if (!i.a(str, protocol.protocol)) {
                                    throw new IOException("Unexpected protocol: " + str);
                                }
                            }
                        }
                    }
                }
            }
            return protocol;
        }

        public /* synthetic */ Companion(f fVar) {
            this();
        }
    }

    Protocol(String str) {
        this.protocol = str;
    }

    public static final Protocol get(String str) throws IOException {
        return Companion.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
