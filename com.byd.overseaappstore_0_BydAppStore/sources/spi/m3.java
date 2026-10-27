package spi;

import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.StringsKt__StringsKt;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public final class m3 {

    @NotNull
    public final Set<c> a;

    @Nullable
    public final l3 b;
    public static final b d = new b(null);

    @JvmField
    @NotNull
    public static final m3 c = new m3(CollectionsKt.toSet(new a().a), 0 == true ? 1 : 0, 2, 0 == true ? 1 : 0);

    public static final class a {

        @NotNull
        public final List<c> a = new ArrayList();
    }

    public static final class b {
        private b() {
        }

        public /* synthetic */ b(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        @NotNull
        public final String a(@NotNull Certificate certificate) {
            Intrinsics.checkNotNullParameter(certificate, "certificate");
            if (!(certificate instanceof X509Certificate)) {
                throw new IllegalArgumentException("Certificate pinning requires X509 certificates".toString());
            }
            StringBuilder sbK = e.k("sha256/");
            sbK.append(b((X509Certificate) certificate).base64());
            return sbK.toString();
        }

        @JvmStatic
        @NotNull
        public final ByteString b(@NotNull X509Certificate sha256Hash) {
            Intrinsics.checkNotNullParameter(sha256Hash, "$this$sha256Hash");
            ByteString.Companion companion = ByteString.INSTANCE;
            PublicKey publicKey = sha256Hash.getPublicKey();
            Intrinsics.checkNotNullExpressionValue(publicKey, "publicKey");
            byte[] encoded = publicKey.getEncoded();
            Intrinsics.checkNotNullExpressionValue(encoded, "publicKey.encoded");
            return companion.e(encoded, 0, encoded.length).sha256();
        }
    }

    public static final class c {

        @NotNull
        public final String a;

        @NotNull
        public final String b;

        @NotNull
        public final ByteString c;

        public c(@NotNull String pattern, @NotNull String pin) {
            ByteString byteStringA;
            Intrinsics.checkNotNullParameter(pattern, "pattern");
            Intrinsics.checkNotNullParameter(pin, "pin");
            boolean z = true;
            if ((!StringsKt.x(pattern, "*.") || StringsKt__StringsKt.indexOf$default(pattern, "*", 1, false, 4, (Object) null) != -1) && ((!StringsKt.x(pattern, "**.") || StringsKt__StringsKt.indexOf$default(pattern, "*", 2, false, 4, (Object) null) != -1) && StringsKt__StringsKt.indexOf$default(pattern, "*", 0, false, 6, (Object) null) != -1)) {
                z = false;
            }
            if (!z) {
                throw new IllegalArgumentException(d.f("Unexpected pattern: ", pattern).toString());
            }
            String strG = spi.c.g(pattern);
            if (strG == null) {
                throw new IllegalArgumentException(d.f("Invalid pattern: ", pattern));
            }
            this.a = strG;
            if (StringsKt.x(pin, "sha1/")) {
                this.b = "sha1";
                ByteString.Companion companion = ByteString.INSTANCE;
                String strSubstring = pin.substring(5);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "(this as java.lang.String).substring(startIndex)");
                byteStringA = companion.a(strSubstring);
                if (byteStringA == null) {
                    throw new IllegalArgumentException(d.f("Invalid pin hash: ", pin));
                }
            } else {
                if (!StringsKt.x(pin, "sha256/")) {
                    throw new IllegalArgumentException(d.f("pins must start with 'sha256/' or 'sha1/': ", pin));
                }
                this.b = "sha256";
                ByteString.Companion companion2 = ByteString.INSTANCE;
                String strSubstring2 = pin.substring(7);
                Intrinsics.checkNotNullExpressionValue(strSubstring2, "(this as java.lang.String).substring(startIndex)");
                byteStringA = companion2.a(strSubstring2);
                if (byteStringA == null) {
                    throw new IllegalArgumentException(d.f("Invalid pin hash: ", pin));
                }
            }
            this.c = byteStringA;
        }

        public final boolean equals(@Nullable Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return ((Intrinsics.areEqual(this.a, cVar.a) ^ true) || (Intrinsics.areEqual(this.b, cVar.b) ^ true) || (Intrinsics.areEqual(this.c, cVar.c) ^ true)) ? false : true;
        }

        public final int hashCode() {
            return this.c.hashCode() + xi.b(this.b, this.a.hashCode() * 31, 31);
        }

        @NotNull
        public final String toString() {
            return this.b + '/' + this.c.base64();
        }
    }

    public m3(@NotNull Set<c> pins, @Nullable l3 l3Var) {
        Intrinsics.checkNotNullParameter(pins, "pins");
        this.a = pins;
        this.b = l3Var;
    }

    public /* synthetic */ m3(Set set, l3 l3Var, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i & 2) != 0 ? null : l3Var);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x008b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(@org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function0<? extends java.util.List<? extends java.security.cert.X509Certificate>> r13) {
        /*
            Method dump skipped, instruction units count: 446
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.m3.a(java.lang.String, kotlin.jvm.functions.Function0):void");
    }

    @NotNull
    public final m3 b(@NotNull l3 certificateChainCleaner) {
        Intrinsics.checkNotNullParameter(certificateChainCleaner, "certificateChainCleaner");
        return Intrinsics.areEqual(this.b, certificateChainCleaner) ? this : new m3(this.a, certificateChainCleaner);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof m3) {
            m3 m3Var = (m3) obj;
            if (Intrinsics.areEqual(m3Var.a, this.a) && Intrinsics.areEqual(m3Var.b, this.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int iHashCode = (this.a.hashCode() + 1517) * 41;
        l3 l3Var = this.b;
        return iHashCode + (l3Var != null ? l3Var.hashCode() : 0);
    }
}
