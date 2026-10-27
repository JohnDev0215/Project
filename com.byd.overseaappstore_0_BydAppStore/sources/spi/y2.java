package spi;

import java.util.Objects;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;
import spi.ln;

/* JADX INFO: loaded from: classes2.dex */
public final class y2 implements ag {
    public static final a b = new a(null);

    @Nullable
    public final w2 a;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static final ln a(a aVar, ln lnVar) {
            Objects.requireNonNull(aVar);
            if ((lnVar != null ? lnVar.h : null) == null) {
                return lnVar;
            }
            Objects.requireNonNull(lnVar);
            ln.a aVar2 = new ln.a(lnVar);
            aVar2.g = null;
            return aVar2.a();
        }

        public final boolean b(String str) {
            return StringsKt.h("Content-Length", str) || StringsKt.h("Content-Encoding", str) || StringsKt.h("Content-Type", str);
        }

        public final boolean c(String str) {
            return (StringsKt.h("Connection", str) || StringsKt.h("Keep-Alive", str) || StringsKt.h("Proxy-Authenticate", str) || StringsKt.h("Proxy-Authorization", str) || StringsKt.h("TE", str) || StringsKt.h("Trailers", str) || StringsKt.h("Transfer-Encoding", str) || StringsKt.h("Upgrade", str)) ? false : true;
        }
    }

    public y2(@Nullable w2 w2Var) {
        this.a = w2Var;
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x01e1, code lost:
    
        if (r7 > 0) goto L74;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0258  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0265  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0352  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x035c  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x03ac  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x03d2  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x058a  */
    /* JADX WARN: Removed duplicated region for block: B:250:0x05c0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0241  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0251  */
    /* JADX WARN: Type inference failed for: r0v14, types: [spi.w2$c] */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12, types: [spi.im, spi.ln] */
    /* JADX WARN: Type inference failed for: r3v16, types: [spi.a3, spi.w2$d] */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2 */
    /* JADX WARN: Type inference failed for: r3v20, types: [spi.l6$a] */
    /* JADX WARN: Type inference failed for: r3v21, types: [spi.l6$a] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v7 */
    /* JADX WARN: Type inference failed for: r3v9 */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18, types: [spi.l6$a] */
    /* JADX WARN: Type inference failed for: r4v20, types: [spi.l6$a] */
    /* JADX WARN: Type inference failed for: r4v32, types: [java.lang.Object, spi.l6$f] */
    /* JADX WARN: Type inference failed for: r4v33, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r4v36, types: [spi.mn] */
    /* JADX WARN: Type inference failed for: r7v34 */
    /* JADX WARN: Type inference failed for: r7v35, types: [spi.vl] */
    /* JADX WARN: Type inference failed for: r7v39 */
    /* JADX WARN: Type inference failed for: r8v33, types: [spi.w2$c] */
    @Override // spi.ag
    @org.jetbrains.annotations.NotNull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final spi.ln a(@org.jetbrains.annotations.NotNull spi.ag.a r22) throws java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1508
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.y2.a(spi.ag$a):spi.ln");
    }
}
