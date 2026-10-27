package io.sentry.android.core.internal.threaddump;

import h6.r;
import io.sentry.C1505q1;
import io.sentry.F1;
import io.sentry.L1;
import io.sentry.protocol.z;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class c {
    public static final Pattern d = Pattern.compile("\"(.*)\" (.*) ?prio=(\\d+)\\s+tid=(\\d+)\\s*(.*)");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final Pattern f16195e = Pattern.compile("\"(.*)\" (.*) ?sysTid=(\\d+)");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final Pattern f16196f = Pattern.compile(" *(?:native: )?#\\d+ \\S+ [0-9a-fA-F]+\\s+(.*?)\\s+\\((.*)\\+(\\d+)\\)(?: \\(.*\\))?");

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final Pattern f16197g = Pattern.compile(" *(?:native: )?#\\d+ \\S+ [0-9a-fA-F]+\\s+(.*)\\s*\\(?(.*)\\)?(?: \\(.*\\))?");

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final Pattern f16198h = Pattern.compile(" *at (?:(.+)\\.)?([^.]+)\\.([^.]+)\\((.*):([\\d-]+)\\)");

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final Pattern f16199i = Pattern.compile(" *at (?:(.+)\\.)?([^.]+)\\.([^.]+)\\(Native method\\)");

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final Pattern f16200j = Pattern.compile(" *- locked \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final Pattern f16201k = Pattern.compile(" *- sleeping on \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");
    public static final Pattern l = Pattern.compile(" *- waiting on \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final Pattern f16202m = Pattern.compile(" *- waiting to lock \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)");

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final Pattern f16203n = Pattern.compile(" *- waiting to lock \\<([0x0-9a-fA-F]{1,16})\\> \\(a (?:(.+)\\.)?([^.]+)\\)(?: held by thread (\\d+))");
    public static final Pattern o = Pattern.compile(" *- waiting to lock an unknown object");

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final Pattern f16204p = Pattern.compile("\\s+");

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final F1 f16205a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f16206b;
    public final L1 c;

    public c(F1 f12, boolean z6) {
        this.f16205a = f12;
        this.f16206b = z6;
        this.c = new L1(f12, 0);
    }

    public static void a(z zVar, C1505q1 c1505q1) {
        Map map = zVar.f16815u;
        if (map == null) {
            map = new HashMap();
        }
        C1505q1 c1505q12 = (C1505q1) map.get(c1505q1.f16819m);
        if (c1505q12 != null) {
            c1505q12.l = Math.max(c1505q12.l, c1505q1.l);
        } else {
            String str = c1505q1.f16819m;
            C1505q1 c1505q13 = new C1505q1();
            c1505q13.l = c1505q1.l;
            c1505q13.f16819m = str;
            c1505q13.f16820n = c1505q1.f16820n;
            c1505q13.o = c1505q1.o;
            c1505q13.f16821p = c1505q1.f16821p;
            c1505q13.f16822q = r.p(c1505q1.f16822q);
            map.put(str, c1505q13);
        }
        zVar.f16815u = map;
    }

    public static Long b(Matcher matcher, int i7) {
        String strGroup = matcher.group(i7);
        if (strGroup == null || strGroup.length() == 0) {
            return null;
        }
        return Long.valueOf(Long.parseLong(strGroup));
    }

    public static boolean c(Matcher matcher, String str) {
        matcher.reset(str);
        return matcher.matches();
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x03dc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x03c7 A[EDGE_INSN: B:134:0x03c7->B:122:0x03c7 BREAK  A[LOOP:1: B:54:0x017e->B:121:0x03b5], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0258  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList d(io.sentry.android.core.internal.threaddump.b r34) {
        /*
            Method dump skipped, instruction units count: 1007
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.android.core.internal.threaddump.c.d(io.sentry.android.core.internal.threaddump.b):java.util.ArrayList");
    }
}
