package spi;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Protocol;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class fs {
    public static final a d = new a(null);

    @JvmField
    @NotNull
    public final Protocol a;

    @JvmField
    public final int b;

    @JvmField
    @NotNull
    public final String c;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final fs a(@NotNull String statusLine) throws IOException {
            Protocol protocol;
            String strSubstring;
            Intrinsics.checkNotNullParameter(statusLine, "statusLine");
            int i = 9;
            if (StringsKt.x(statusLine, "HTTP/1.")) {
                if (statusLine.length() < 9 || statusLine.charAt(8) != ' ') {
                    throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
                }
                int iCharAt = statusLine.charAt(7) - '0';
                if (iCharAt == 0) {
                    protocol = Protocol.HTTP_1_0;
                } else {
                    if (iCharAt != 1) {
                        throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
                    }
                    protocol = Protocol.HTTP_1_1;
                }
            } else {
                if (!StringsKt.x(statusLine, "ICY ")) {
                    throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
                }
                protocol = Protocol.HTTP_1_0;
                i = 4;
            }
            int i2 = i + 3;
            if (statusLine.length() < i2) {
                throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
            }
            try {
                String strSubstring2 = statusLine.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                int i3 = Integer.parseInt(strSubstring2);
                if (statusLine.length() <= i2) {
                    strSubstring = "";
                } else {
                    if (statusLine.charAt(i2) != ' ') {
                        throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
                    }
                    strSubstring = statusLine.substring(i + 4);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                }
                return new fs(protocol, i3, strSubstring);
            } catch (NumberFormatException unused) {
                throw new ProtocolException(d.f("Unexpected status line: ", statusLine));
            }
        }
    }

    public fs(@NotNull Protocol protocol, int i, @NotNull String message) {
        Intrinsics.checkNotNullParameter(protocol, "protocol");
        Intrinsics.checkNotNullParameter(message, "message");
        this.a = protocol;
        this.b = i;
        this.c = message;
    }

    @NotNull
    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.a == Protocol.HTTP_1_0 ? "HTTP/1.0" : "HTTP/1.1");
        sb.append(' ');
        sb.append(this.b);
        sb.append(' ');
        sb.append(this.c);
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "StringBuilder().apply(builderAction).toString()");
        return string;
    }
}
