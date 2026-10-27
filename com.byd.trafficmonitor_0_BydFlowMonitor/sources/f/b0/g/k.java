package f.b0.g;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.Protocol;

/* JADX INFO: compiled from: StatusLine.java */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Protocol f5093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f5094b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f5095c;

    public k(Protocol protocol, int i2, String str) {
        this.f5093a = protocol;
        this.f5094b = i2;
        this.f5095c = str;
    }

    public static k a(String str) throws IOException {
        Protocol protocol;
        String strSubstring;
        int i2 = 9;
        if (str.startsWith("HTTP/1.")) {
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                protocol = Protocol.HTTP_1_1;
            }
        } else {
            if (!str.startsWith("ICY ")) {
                throw new ProtocolException("Unexpected status line: " + str);
            }
            protocol = Protocol.HTTP_1_0;
            i2 = 4;
        }
        int i3 = i2 + 3;
        if (str.length() < i3) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
        try {
            int i4 = Integer.parseInt(str.substring(i2, i3));
            if (str.length() <= i3) {
                strSubstring = "";
            } else {
                if (str.charAt(i3) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + str);
                }
                strSubstring = str.substring(i2 + 4);
            }
            return new k(protocol, i4, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: " + str);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5093a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.f5094b);
        if (this.f5095c != null) {
            sb.append(' ');
            sb.append(this.f5095c);
        }
        return sb.toString();
    }
}
