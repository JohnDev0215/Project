package com.google.protobuf;

import java.io.IOException;

/* JADX INFO: compiled from: InvalidProtocolBufferException.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 extends IOException {
    public static final /* synthetic */ int f = 0;
    private static final long serialVersionUID = -1616151763072450476L;
    public r0 e;

    /* JADX INFO: compiled from: InvalidProtocolBufferException.java */
    public static class a extends d0 {
        private static final long serialVersionUID = 3283890091615336259L;

        public a() {
            super("Protocol message tag had invalid wire type.");
        }
    }

    public d0(String str) {
        super(str);
        this.e = null;
    }

    public static d0 a() {
        return new d0("Protocol message end-group tag did not match expected tag.");
    }

    public static d0 b() {
        return new d0("Protocol message contained an invalid tag (zero).");
    }

    public static d0 c() {
        return new d0("Protocol message had invalid UTF-8.");
    }

    public static a d() {
        return new a();
    }

    public static d0 e() {
        return new d0("CodedInputStream encountered a malformed varint.");
    }

    public static d0 f() {
        return new d0("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    public static d0 h() {
        return new d0("Failed to parse the message.");
    }

    public static d0 k() {
        return new d0("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    public final IOException l() {
        return getCause() instanceof IOException ? (IOException) getCause() : this;
    }

    public d0(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.e = null;
    }
}
