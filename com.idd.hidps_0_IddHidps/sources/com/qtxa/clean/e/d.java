package com.qtxa.clean.e;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private ActivityManager f38a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.qtxa.clean.e.a f39b;
    private c d;
    private Map<String, Long> c = new HashMap();
    private C0007d e = new C0007d();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if ("com.qtxa.car.radio.notify.PLAY_STATE".equals(action)) {
                int intExtra = intent.getIntExtra("play_state", -1);
                com.qtxa.clean.e.c.k(">>state change: " + intExtra);
                boolean z = d.this.e.f43b;
                d.this.e.f43b = (intExtra == 3 || intExtra == 4 || intExtra == -1) ? false : true;
                if (z != d.this.e.f43b) {
                    d.this.e.c = SystemClock.elapsedRealtime();
                    return;
                }
                return;
            }
            if ("com.qtxa.car.radio.notify.PLAY_INFO".equals(action)) {
                int intExtra2 = intent.getIntExtra("play_type", -1);
                com.qtxa.clean.e.c.k(">>source type changed: " + intExtra2);
                C0007d c0007d = d.this.e;
                if (intExtra2 != 4 && intExtra2 != 5) {
                    z = false;
                }
                c0007d.f42a = z;
            }
        }
    }

    class b implements Comparator<com.qtxa.clean.e.b> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f41a;

        b(d dVar, int i) {
            this.f41a = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:30:0x005f  */
        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public int compare(com.qtxa.clean.e.b r6, com.qtxa.clean.e.b r7) {
            /*
                r5 = this;
                boolean r0 = r6.l()
                boolean r1 = r7.l()
                r2 = -1
                r3 = 1
                if (r0 == r1) goto L16
                boolean r0 = r6.l()
                if (r0 == 0) goto L14
                r0 = r3
                goto L17
            L14:
                r0 = r2
                goto L17
            L16:
                r0 = 0
            L17:
                if (r0 != 0) goto L2c
                boolean r1 = r6.m()
                boolean r4 = r7.m()
                if (r1 == r4) goto L2c
                boolean r0 = r6.m()
                if (r0 == 0) goto L2b
                r0 = r3
                goto L2c
            L2b:
                r0 = r2
            L2c:
                if (r0 != 0) goto L43
                int r1 = r6.b()
                int r4 = r7.b()
                if (r1 == r4) goto L43
                int r0 = r6.b()
                int r1 = r5.f41a
                if (r0 != r1) goto L42
                r0 = r2
                goto L43
            L42:
                r0 = r3
            L43:
                if (r0 != 0) goto L7c
                int r1 = r5.f41a
                r4 = 2
                if (r1 != r4) goto L62
                int r1 = r6.a()
                int r4 = r7.a()
                if (r1 == r4) goto L62
                int r5 = r6.a()
                int r6 = r7.a()
                int r5 = r5 - r6
                if (r5 >= 0) goto L60
            L5f:
                r2 = r3
            L60:
                r0 = r2
                goto L7c
            L62:
                int r5 = r5.f41a
                if (r5 != r3) goto L7c
                int r5 = r6.g()
                int r1 = r7.g()
                if (r5 == r1) goto L7c
                int r5 = r6.g()
                int r6 = r7.g()
                int r5 = r5 - r6
                if (r5 >= 0) goto L60
                goto L5f
            L7c:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qtxa.clean.e.d.b.compare(com.qtxa.clean.e.b, com.qtxa.clean.e.b):int");
        }
    }

    interface c {
        int a(String str);

        int b(String str);
    }

    /* JADX INFO: renamed from: com.qtxa.clean.e.d$d, reason: collision with other inner class name */
    static class C0007d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f42a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        boolean f43b = false;
        long c = -1;

        C0007d() {
        }

        boolean a() {
            boolean z = false;
            if (this.f43b || this.f42a || (this.c != -1 && SystemClock.elapsedRealtime() - this.c < 60000)) {
                z = true;
            }
            com.qtxa.clean.e.c.k(">> radio status : isPlaying=" + this.f43b + ", sourceTypeOnGoing:" + this.f42a + ", updateTimeStamp:" + this.c + " <= " + z);
            return z;
        }
    }

    d(Context context, c cVar) {
        Context applicationContext = context.getApplicationContext();
        this.d = cVar;
        this.f38a = (ActivityManager) applicationContext.getSystemService("activity");
        this.f39b = com.qtxa.clean.e.a.b();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qtxa.car.radio.notify.PLAY_STATE");
        intentFilter.addAction("com.qtxa.car.radio.notify.PLAY_INFO");
        applicationContext.registerReceiver(new a(), intentFilter);
    }

    private List<ActivityManager.RunningAppProcessInfo> d() {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = this.f38a.getRunningAppProcesses();
        if (runningAppProcesses == null || runningAppProcesses.size() == 0) {
            return null;
        }
        return runningAppProcesses;
    }

    private boolean e(String str, List<com.qtxa.hidps.b.a> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Iterator<com.qtxa.hidps.b.a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().a().equals(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean f(String str, List<com.qtxa.hidps.b.a> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        Iterator<com.qtxa.hidps.b.a> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().b().equals(str)) {
                return true;
            }
        }
        return false;
    }

    static boolean g(int i, String str) {
        com.qtxa.clean.e.c.k("kill pid:" + i + ", reason:" + str);
        try {
            IActivityManager service = Build.VERSION.SDK_INT >= 26 ? ActivityManager.getService() : ActivityManagerNative.getDefault();
            if (service != null) {
                return service.killPids(new int[]{i}, str, true);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }

    List<Integer> b() {
        return com.qtxa.hidps.b.c.b();
    }

    List<com.qtxa.clean.e.b> c(Map<String, Integer> map, int i) {
        boolean z;
        boolean z2;
        com.qtxa.clean.e.c.k("getProcessAutoClean: " + map);
        List<Integer> listB = b();
        List<com.qtxa.hidps.b.a> listD = com.qtxa.hidps.b.c.d();
        List<ActivityManager.RunningAppProcessInfo> listD2 = d();
        Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
        long jCurrentTimeMillis = System.currentTimeMillis();
        ArrayList<com.qtxa.clean.e.b> arrayList = new ArrayList();
        while (true) {
            boolean z3 = true;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, Integer> next = it.next();
            String key = next.getKey();
            int iIntValue = next.getValue().intValue();
            if ((iIntValue == 1 || iIntValue == 2) ? false : true) {
                com.qtxa.clean.e.c.k("will't be killed, process: " + key + ", procFlags:" + iIntValue);
            } else if (!this.c.containsKey(key) || jCurrentTimeMillis - this.c.get(key).longValue() <= 120000) {
                Iterator<ActivityManager.RunningAppProcessInfo> it2 = listD2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        z3 = false;
                        break;
                    }
                    ActivityManager.RunningAppProcessInfo next2 = it2.next();
                    if (next2.processName.equals(key)) {
                        com.qtxa.clean.e.b bVarI = i(next2, listB, listD, false);
                        if (bVarI != null) {
                            bVarI.q(jCurrentTimeMillis);
                            bVarI.o(iIntValue);
                            arrayList.add(bVarI);
                        }
                    }
                }
                if (!z3) {
                    com.qtxa.clean.e.c.k("skip no such process:" + key);
                }
            } else {
                com.qtxa.clean.e.c.k("skip often:" + key);
            }
        }
        if (arrayList.size() != 0) {
            for (com.qtxa.clean.e.b bVar : arrayList) {
                if (!bVar.l() && !bVar.m()) {
                    z = false;
                    break;
                }
            }
            z = true;
        } else {
            z = true;
        }
        if (z) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : listD2) {
                Iterator it3 = arrayList.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z2 = true;
                        break;
                    }
                    if (runningAppProcessInfo.processName.equals(((com.qtxa.clean.e.b) it3.next()).f())) {
                        z2 = false;
                        break;
                    }
                }
                if (z2) {
                    com.qtxa.clean.e.b bVarI2 = i(runningAppProcessInfo, listB, listD, false);
                    StringBuilder sb = new StringBuilder();
                    sb.append("check process:");
                    sb.append(runningAppProcessInfo.processName);
                    sb.append(bVarI2 == null ? " <" : Boolean.toString(bVarI2.k()));
                    com.qtxa.clean.e.c.k(sb.toString());
                    if (bVarI2 != null && bVarI2.k()) {
                        com.qtxa.clean.e.c.k("should stop:" + bVarI2.f());
                        bVarI2.q(jCurrentTimeMillis);
                        bVarI2.o(i);
                        arrayList.add(bVarI2);
                    }
                }
            }
        }
        if (arrayList.size() != 0) {
            Collections.sort(arrayList, new b(this, i));
            com.qtxa.clean.e.c.k("auto clean flags:" + i);
            for (com.qtxa.clean.e.b bVar2 : arrayList) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("auto clean:");
                sb2.append(bVar2.e());
                sb2.append("-");
                sb2.append(bVar2.f());
                if (bVar2.l()) {
                    sb2.append(", music playing");
                }
                if (bVar2.m()) {
                    sb2.append(", is showing");
                }
                sb2.append(", flags:");
                sb2.append(bVar2.b());
                sb2.append("\n");
                com.qtxa.clean.e.c.k(sb2.toString());
            }
        }
        return arrayList;
    }

    void h(com.qtxa.clean.e.b bVar) {
        this.c.put(bVar.f(), Long.valueOf(bVar.c()));
    }

    com.qtxa.clean.e.b i(ActivityManager.RunningAppProcessInfo runningAppProcessInfo, List<Integer> list, List<com.qtxa.hidps.b.a> list2, boolean z) {
        String str = runningAppProcessInfo.processName;
        if (com.qtxa.hidps.b.b.f65a) {
            if (this.f39b.a()) {
                com.qtxa.clean.e.c.k("Checking process: " + str + ", pid:" + runningAppProcessInfo.pid + ", cpu:" + this.d.b(str) + ", mem:" + this.d.a(str));
            } else {
                com.qtxa.clean.e.c.k("Checking process: " + str + ", pid:" + runningAppProcessInfo.pid);
            }
        }
        if (runningAppProcessInfo.pid == Process.myPid()) {
            return null;
        }
        if (f(str, list2)) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", showing");
            return null;
        }
        if (this.f39b.q(str)) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", never");
            return null;
        }
        if ((runningAppProcessInfo.flags & 2) != 0) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", persistent");
            return null;
        }
        if (z && this.f39b.o(str)) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", whitelist");
            return null;
        }
        com.qtxa.clean.e.b bVar = new com.qtxa.clean.e.b(runningAppProcessInfo.pid, str);
        bVar.n(this.f39b.l(str));
        if (bVar.j()) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", audio ongoing");
            return null;
        }
        if (bVar.i()) {
            if (this.e.a()) {
                com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", audio music.");
                return null;
            }
            if (list != null && list.contains(Integer.valueOf(runningAppProcessInfo.pid))) {
                if (z) {
                    com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", audio music");
                    return null;
                }
                com.qtxa.clean.e.c.k("will be killed ? process:" + runningAppProcessInfo.processName + ", audio music");
                bVar.r(true);
            }
        }
        String[] strArr = runningAppProcessInfo.pkgList;
        int i = 0;
        if (strArr != null) {
            int length = strArr.length;
            int i2 = 0;
            while (i < length) {
                String str2 = strArr[i];
                if (this.f39b.q(str2)) {
                    com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", never app");
                    return null;
                }
                if (z && this.f39b.n(str2)) {
                    com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", whitelist app");
                    return null;
                }
                if (e(str2, list2)) {
                    com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", app showing");
                    return null;
                }
                if (i2 == 0 && this.f39b.w(str2)) {
                    com.qtxa.clean.e.c.k("will be killed, process:" + runningAppProcessInfo.processName + ", " + runningAppProcessInfo.pid + ", pkg:" + str2 + ", force stop");
                    bVar.s(str2);
                    bVar.p(true);
                    i2 = 1;
                }
                i++;
            }
            i = i2;
        }
        if (z && i == 0) {
            com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", normal");
            return null;
        }
        if (!z || !this.f39b.a() || this.d.b(str) >= this.f39b.e(str) || this.d.a(str) >= this.f39b.k(str)) {
            bVar.t(runningAppProcessInfo.pkgList);
            return bVar;
        }
        com.qtxa.clean.e.c.k("will't be killed, process:" + runningAppProcessInfo.processName + ", " + runningAppProcessInfo.pid + ", usaged");
        return null;
    }
}
