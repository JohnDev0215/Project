package i6;

import c6.y;
import java.net.ProtocolException;
import kotlin.jvm.internal.l;
import y5.p;

/* JADX INFO: compiled from: StatusLine.kt */
/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final a f7067d = new a(null);

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final y f7068a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f7069b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final String f7070c;

    /* JADX INFO: compiled from: StatusLine.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(kotlin.jvm.internal.g gVar) {
            this();
        }

        public final k a(String statusLine) throws ProtocolException {
            y yVar;
            String strSubstring;
            l.e(statusLine, "statusLine");
            int i7 = 9;
            if (p.A(statusLine, "HTTP/1.", false, 2, null)) {
                if (statusLine.length() < 9 || statusLine.charAt(8) != ' ') {
                    throw new ProtocolException("Unexpected status line: " + statusLine);
                }
                int iCharAt = statusLine.charAt(7) - '0';
                if (iCharAt == 0) {
                    yVar = y.HTTP_1_0;
                } else {
                    if (iCharAt != 1) {
                        throw new ProtocolException("Unexpected status line: " + statusLine);
                    }
                    yVar = y.HTTP_1_1;
                }
            } else {
                if (!p.A(statusLine, "ICY ", false, 2, null)) {
                    throw new ProtocolException("Unexpected status line: " + statusLine);
                }
                yVar = y.HTTP_1_0;
                i7 = 4;
            }
            int i8 = i7 + 3;
            if (statusLine.length() < i8) {
                throw new ProtocolException("Unexpected status line: " + statusLine);
            }
            try {
                String strSubstring2 = statusLine.substring(i7, i8);
                l.d(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int i9 = Integer.parseInt(strSubstring2);
                if (statusLine.length() <= i8) {
                    strSubstring = "";
                } else {
                    if (statusLine.charAt(i8) != ' ') {
                        throw new ProtocolException("Unexpected status line: " + statusLine);
                    }
                    strSubstring = statusLine.substring(i7 + 4);
                    l.d(strSubstring, "(this as java.lang.String).substring(startIndex)");
                }
                return new k(yVar, i9, strSubstring);
            } catch (NumberFormatException unused) {
                throw new ProtocolException("Unexpected status line: " + statusLine);
            }
        }
    }

    public k(y protocol, int i7, String message) {
        l.e(protocol, "protocol");
        l.e(message, "message");
        this.f7068a = protocol;
        this.f7069b = i7;
        this.f7070c = message;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f7068a == y.HTTP_1_0) {
            sb.append("HTTP/1.0");
        } else {
            sb.append("HTTP/1.1");
        }
        sb.append(' ');
        sb.append(this.f7069b);
        sb.append(' ');
        sb.append(this.f7070c);
        String string = sb.toString();
        l.d(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
