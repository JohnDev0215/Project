package defpackage;

import java.io.IOException;

/* JADX INFO: renamed from: Il, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C0155Il extends IOException {
    public C0155Il(String str) {
        super(str);
    }

    public static C0155Il a() {
        return new C0155Il("Protocol message end-group tag did not match expected tag.");
    }

    public static C0155Il b() {
        return new C0155Il("Protocol message had invalid UTF-8.");
    }

    public static C0137Hl c() {
        return new C0137Hl("Protocol message tag had invalid wire type.");
    }

    public static C0155Il d() {
        return new C0155Il("CodedInputStream encountered a malformed varint.");
    }

    public static C0155Il e() {
        return new C0155Il("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static C0155Il f() {
        return new C0155Il("Failed to parse the message.");
    }

    public static C0155Il g() {
        return new C0155Il("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }
}
