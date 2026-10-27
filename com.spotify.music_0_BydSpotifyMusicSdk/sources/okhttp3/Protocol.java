package okhttp3;

import java.io.IOException;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final Companion c = new Companion(0);
    private final String protocol;

    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }

        public static Protocol a(String str) throws IOException {
            Protocol protocol = Protocol.HTTP_1_0;
            if (!v03.b(str, protocol.protocol)) {
                protocol = Protocol.HTTP_1_1;
                if (!v03.b(str, protocol.protocol)) {
                    protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                    if (!v03.b(str, protocol.protocol)) {
                        protocol = Protocol.HTTP_2;
                        if (!v03.b(str, protocol.protocol)) {
                            protocol = Protocol.SPDY_3;
                            if (!v03.b(str, protocol.protocol)) {
                                protocol = Protocol.QUIC;
                                if (!v03.b(str, protocol.protocol)) {
                                    throw new IOException("Unexpected protocol: ".concat(str));
                                }
                            }
                        }
                    }
                }
            }
            return protocol;
        }
    }

    Protocol(String str) {
        this.protocol = str;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return this.protocol;
    }
}
