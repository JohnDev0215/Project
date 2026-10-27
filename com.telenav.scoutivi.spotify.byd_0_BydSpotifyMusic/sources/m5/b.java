package m5;

import kotlin.jvm.internal.l;
import y5.q;

/* JADX INFO: compiled from: PlatformImplementations.kt */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final a f8405a;

    /* JADX WARN: Removed duplicated region for block: B:34:0x00b7 A[Catch: ClassCastException -> 0x00bc, ClassNotFoundException -> 0x00f2, TRY_ENTER, TryCatch #4 {ClassCastException -> 0x00bc, blocks: (B:34:0x00b7, B:37:0x00be, B:38:0x00c3), top: B:64:0x00b5, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00be A[Catch: ClassCastException -> 0x00bc, ClassNotFoundException -> 0x00f2, TryCatch #4 {ClassCastException -> 0x00bc, blocks: (B:34:0x00b7, B:37:0x00be, B:38:0x00c3), top: B:64:0x00b5, outer: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ac A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            Method dump skipped, instruction units count: 323
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: m5.b.<clinit>():void");
    }

    private static final int a() {
        String property = System.getProperty("java.specification.version");
        if (property == null) {
            return 65542;
        }
        int iP = q.P(property, '.', 0, false, 6, null);
        if (iP < 0) {
            try {
                return Integer.parseInt(property) * 65536;
            } catch (NumberFormatException unused) {
                return 65542;
            }
        }
        int i7 = iP + 1;
        int iP2 = q.P(property, '.', i7, false, 4, null);
        if (iP2 < 0) {
            iP2 = property.length();
        }
        String strSubstring = property.substring(0, iP);
        l.d(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        String strSubstring2 = property.substring(i7, iP2);
        l.d(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        try {
            return (Integer.parseInt(strSubstring) * 65536) + Integer.parseInt(strSubstring2);
        } catch (NumberFormatException unused2) {
            return 65542;
        }
    }
}
