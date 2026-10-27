package l2;

import java.io.File;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class u extends i {

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final Pattern f18503r = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v1\\.exo$", 32);

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final Pattern f18504s = Pattern.compile("^(.+)\\.(\\d+)\\.(\\d+)\\.v2\\.exo$", 32);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final Pattern f18505t = Pattern.compile("^(\\d+)\\.(\\d+)\\.(\\d+)\\.v3\\.exo$", 32);

    /* JADX WARN: Removed duplicated region for block: B:28:0x009d A[PHI: r2
  0x009d: PHI (r2v16 java.util.regex.Matcher) = (r2v10 java.util.regex.Matcher), (r2v8 java.util.regex.Matcher) binds: [B:26:0x0093, B:22:0x0081] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00d4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static l2.u b(java.io.File r16, long r17, long r19, H0.b r21) {
        /*
            Method dump skipped, instruction units count: 324
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l2.u.b(java.io.File, long, long, H0.b):l2.u");
    }

    public static File c(File file, int i7, long j3, long j10) {
        StringBuilder sb = new StringBuilder();
        sb.append(i7);
        sb.append(".");
        sb.append(j3);
        sb.append(".");
        return new File(file, android.hardware.bydauto.ac.tsimpl.a.f(j10, ".v3.exo", sb));
    }
}
