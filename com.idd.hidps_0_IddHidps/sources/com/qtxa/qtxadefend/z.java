package com.qtxa.qtxadefend;

import android.content.Context;
import android.os.DropBoxManager;
import java.io.BufferedReader;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    SimpleDateFormat f155a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private long f156b;
    private DropBoxManager c;
    private Context d;
    private ScheduledExecutorService e;
    private List<String> f;
    Thread g;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            z zVar = z.this;
            zVar.f156b = Utils.getLastEntryTimestamp(zVar.d);
            w.d(" get run here every time:" + z.this.f155a.format(new Date(Long.parseLong(String.valueOf(z.this.f156b)))) + "  " + z.this.f156b);
            try {
                z.this.h(null, z.this.f156b);
            } catch (Exception e) {
                w.d("getErrorByTag error: " + e.toString());
            }
            z.this.f156b = System.currentTimeMillis();
            w.d(" save getErrorByTag  time:" + z.this.f155a.format(new Date(Long.parseLong(String.valueOf(z.this.f156b)))));
            Utils.setLastEntryTimestamp(z.this.d, z.this.f156b);
        }
    }

    private static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static z f158a = new z(null);
    }

    /* synthetic */ z(a aVar) {
        this();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0058  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int f(java.lang.String r1) {
        /*
            r0 = this;
            int r0 = r1.hashCode()
            switch(r0) {
                case -1784659532: goto L4e;
                case -1467779278: goto L44;
                case -994869511: goto L3a;
                case -639360519: goto L30;
                case -27482953: goto L26;
                case 627597689: goto L1c;
                case 1710060475: goto L12;
                case 1945084645: goto L8;
                default: goto L7;
            }
        L7:
            goto L58
        L8:
            java.lang.String r0 = "SYSTEM_TOMBSTONE"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 7
            goto L59
        L12:
            java.lang.String r0 = "system_server_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 0
            goto L59
        L1c:
            java.lang.String r0 = "system_server_watchdog"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 2
            goto L59
        L26:
            java.lang.String r0 = "system_app_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 5
            goto L59
        L30:
            java.lang.String r0 = "system_app_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 3
            goto L59
        L3a:
            java.lang.String r0 = "system_server_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 1
            goto L59
        L44:
            java.lang.String r0 = "data_app_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 6
            goto L59
        L4e:
            java.lang.String r0 = "data_app_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L58
            r0 = 4
            goto L59
        L58:
            r0 = -1
        L59:
            r1 = 902(0x386, float:1.264E-42)
            switch(r0) {
                case 0: goto L5f;
                case 1: goto L5f;
                case 2: goto L5f;
                case 3: goto L61;
                case 4: goto L61;
                case 5: goto L61;
                case 6: goto L61;
                case 7: goto L61;
                default: goto L5e;
            }
        L5e:
            goto L61
        L5f:
            r1 = 901(0x385, float:1.263E-42)
        L61:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.z.f(java.lang.String):int");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x006a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private int g(java.lang.String r7) {
        /*
            r6 = this;
            int r6 = r7.hashCode()
            r0 = 6
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r6) {
                case -1784659532: goto L60;
                case -1467779278: goto L56;
                case -994869511: goto L4c;
                case -639360519: goto L42;
                case -27482953: goto L38;
                case 193809133: goto L2e;
                case 627597689: goto L23;
                case 1710060475: goto L19;
                case 1945084645: goto Lf;
                default: goto Ld;
            }
        Ld:
            goto L6a
        Lf:
            java.lang.String r6 = "SYSTEM_TOMBSTONE"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = 7
            goto L6b
        L19:
            java.lang.String r6 = "system_server_crash"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = 0
            goto L6b
        L23:
            java.lang.String r6 = "system_server_watchdog"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = 8
            goto L6b
        L2e:
            java.lang.String r6 = "system_app_native_crash"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r2
            goto L6b
        L38:
            java.lang.String r6 = "system_app_anr"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r1
            goto L6b
        L42:
            java.lang.String r6 = "system_app_crash"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r4
            goto L6b
        L4c:
            java.lang.String r6 = "system_server_anr"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r5
            goto L6b
        L56:
            java.lang.String r6 = "data_app_anr"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r0
            goto L6b
        L60:
            java.lang.String r6 = "data_app_crash"
            boolean r6 = r7.equals(r6)
            if (r6 == 0) goto L6a
            r6 = r3
            goto L6b
        L6a:
            r6 = -1
        L6b:
            switch(r6) {
                case 0: goto L77;
                case 1: goto L77;
                case 2: goto L75;
                case 3: goto L75;
                case 4: goto L75;
                case 5: goto L73;
                case 6: goto L73;
                case 7: goto L71;
                case 8: goto L6f;
                default: goto L6e;
            }
        L6e:
            goto L78
        L6f:
            r0 = r1
            goto L78
        L71:
            r0 = r2
            goto L78
        L73:
            r0 = r3
            goto L78
        L75:
            r0 = r4
            goto L78
        L77:
            r0 = r5
        L78:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.z.g(java.lang.String):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:128:0x02bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:130:0x00bd A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x02ca A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:136:0x00c8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(java.lang.String r17, long r18) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 747
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.z.h(java.lang.String, long):void");
    }

    public static z i() {
        return b.f158a;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0063  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String j(java.lang.String r1, java.lang.String r2) {
        /*
            r0 = this;
            int r0 = r1.hashCode()
            switch(r0) {
                case -1784659532: goto L59;
                case -1467779278: goto L4f;
                case -994869511: goto L45;
                case -639360519: goto L3b;
                case -27482953: goto L31;
                case 193809133: goto L27;
                case 627597689: goto L1d;
                case 1710060475: goto L13;
                case 1945084645: goto L8;
                default: goto L7;
            }
        L7:
            goto L63
        L8:
            java.lang.String r0 = "SYSTEM_TOMBSTONE"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 8
            goto L64
        L13:
            java.lang.String r0 = "system_server_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 0
            goto L64
        L1d:
            java.lang.String r0 = "system_server_watchdog"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 2
            goto L64
        L27:
            java.lang.String r0 = "system_app_native_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 7
            goto L64
        L31:
            java.lang.String r0 = "system_app_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 5
            goto L64
        L3b:
            java.lang.String r0 = "system_app_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 3
            goto L64
        L45:
            java.lang.String r0 = "system_server_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 1
            goto L64
        L4f:
            java.lang.String r0 = "data_app_anr"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 6
            goto L64
        L59:
            java.lang.String r0 = "data_app_crash"
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L63
            r0 = 4
            goto L64
        L63:
            r0 = -1
        L64:
            switch(r0) {
                case 0: goto L80;
                case 1: goto L80;
                case 2: goto L80;
                case 3: goto L79;
                case 4: goto L79;
                case 5: goto L79;
                case 6: goto L79;
                case 7: goto L79;
                case 8: goto L6a;
                default: goto L67;
            }
        L67:
            java.lang.String r0 = "others"
            goto L82
        L6a:
            java.lang.String r0 = ">>> "
            java.lang.String r0 = com.qtxa.qtxadefend.Utils.gettype(r2, r0)
            java.lang.String r1 = ""
            java.lang.String r2 = " <<<"
            java.lang.String r0 = r0.replace(r1, r2)
            goto L82
        L79:
            java.lang.String r0 = "Process: "
            java.lang.String r0 = com.qtxa.qtxadefend.Utils.gettype(r2, r0)
            goto L82
        L80:
            java.lang.String r0 = "system_server"
        L82:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qtxa.qtxadefend.z.j(java.lang.String, java.lang.String):java.lang.String");
    }

    String e(String str) {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(str));
        int length = 0;
        String line = "";
        do {
            try {
                line = bufferedReader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (line == null) {
                break;
            }
            length += line.length() + 1;
        } while (!line.equals(""));
        return str.substring(length);
    }

    public List<String> k() {
        return this.f;
    }

    public void l(Context context) {
        this.d = context;
        this.c = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
    }

    public void m(int i) {
        ScheduledExecutorService scheduledExecutorService = this.e;
        if (scheduledExecutorService != null) {
            scheduledExecutorService.shutdownNow();
            this.e = null;
        }
        ScheduledExecutorService scheduledExecutorServiceNewSingleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        this.e = scheduledExecutorServiceNewSingleThreadScheduledExecutor;
        if (scheduledExecutorServiceNewSingleThreadScheduledExecutor != null) {
            scheduledExecutorServiceNewSingleThreadScheduledExecutor.scheduleAtFixedRate(this.g, 0L, i, TimeUnit.SECONDS);
        }
    }

    private z() {
        this.f155a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss sss", Locale.CHINA);
        this.f156b = 0L;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = new Thread(new a(), "SystemError");
        this.f = new ArrayList();
    }
}
