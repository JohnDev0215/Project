package com.android.settingslib.applications;

import android.R;
import android.app.Application;
import android.app.usage.StorageStats;
import android.app.usage.StorageStatsManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageManager;
import android.content.pm.IPackageStatsObserver;
import android.content.pm.ModuleInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageStats;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.format.Formatter;
import android.util.IconDrawableFactory;
import android.util.Log;
import android.util.SparseArray;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.OnLifecycleEvent;
import com.android.internal.util.ArrayUtils;
import com.android.settingslib.R$string;
import com.byd.mediacenter.api.media.MediaControl;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public class ApplicationsState {
    private static final Object A = new Object();
    public static final Comparator<g> B;
    public static final h C;
    public static final h D;
    public static final h E;
    public static final h F;
    public static final h G;

    @VisibleForTesting
    static ApplicationsState sInstance;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Context f1318a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final PackageManager f1319b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final IPackageManager f1320c;
    final UserManager d;
    final StorageStatsManager e;
    final int f;
    final int g;
    l h;
    boolean i;
    boolean j;
    UUID r;
    String s;
    int t;
    boolean u;
    final HandlerThread x;
    final i y;
    final ArrayList<Session> k = new ArrayList<>();
    final ArrayList<Session> l = new ArrayList<>();
    private com.android.settingslib.applications.d m = new com.android.settingslib.applications.d();
    final SparseArray<HashMap<String, g>> n = new SparseArray<>();
    final ArrayList<g> o = new ArrayList<>();
    List<ApplicationInfo> p = new ArrayList();
    long q = 1;
    final HashMap<String, Boolean> v = new HashMap<>();
    final ArrayList<WeakReference<Session>> w = new ArrayList<>();
    final k z = new k(Looper.getMainLooper());

    /* JADX INFO: compiled from: caradapter-bug */
    public class Session implements androidx.lifecycle.e {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final j f1321c;
        boolean d;
        final Object e;
        boolean f;
        boolean g;
        h h;
        Comparator<g> i;
        ArrayList<g> j;
        boolean k;
        private final boolean l;
        private int m;
        final /* synthetic */ ApplicationsState n;

        void c() {
            ArrayList<g> arrayList;
            if (this.d) {
                synchronized (this.e) {
                    if (this.f) {
                        h hVar = this.h;
                        Comparator<g> comparator = this.i;
                        this.f = false;
                        this.h = null;
                        this.i = null;
                        if (this.k) {
                            Process.setThreadPriority(-2);
                            this.k = false;
                        }
                        if (hVar != null) {
                            hVar.c(this.n.f1318a);
                        }
                        synchronized (this.n.n) {
                            arrayList = new ArrayList(this.n.o);
                        }
                        ArrayList<g> arrayList2 = new ArrayList<>();
                        for (g gVar : arrayList) {
                            if (gVar != null && (hVar == null || hVar.a(gVar))) {
                                synchronized (this.n.n) {
                                    if (comparator != null) {
                                        gVar.b(this.n.f1318a);
                                    }
                                    arrayList2.add(gVar);
                                }
                            }
                        }
                        if (comparator != null) {
                            synchronized (this.n.n) {
                                Collections.sort(arrayList2, comparator);
                            }
                        }
                        synchronized (this.e) {
                            if (!this.f) {
                                this.j = arrayList2;
                                if (!this.g) {
                                    this.e.notifyAll();
                                } else if (!this.n.z.hasMessages(1, this)) {
                                    this.n.z.sendMessage(this.n.z.obtainMessage(1, this));
                                }
                            }
                        }
                        Process.setThreadPriority(10);
                    }
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestroy() {
            if (!this.l) {
                onPause();
            }
            synchronized (this.n.n) {
                this.n.k.remove(this);
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause() {
            synchronized (this.n.n) {
                if (this.d) {
                    this.d = false;
                    this.n.u = true;
                    this.n.y.removeMessages(1, this);
                    this.n.k();
                }
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume() {
            synchronized (this.n.n) {
                if (!this.d) {
                    this.d = true;
                    this.n.u = true;
                    this.n.l();
                    this.n.m();
                }
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    @Retention(RetentionPolicy.SOURCE)
    public @interface SessionFlags {
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements h {
        a() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public boolean a(g gVar) {
            boolean z;
            synchronized (gVar.q) {
                z = ApplicationsState.r(gVar.q.flags, 33554432) || gVar.q.category == 0;
            }
            return z;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public void b() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements h {
        b() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public boolean a(g gVar) {
            boolean z;
            synchronized (gVar) {
                z = true;
                if (gVar.q.category != 1) {
                    z = false;
                }
            }
            return z;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public void b() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements h {
        c() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public boolean a(g gVar) {
            boolean z;
            synchronized (gVar) {
                z = gVar.q.category == 2;
            }
            return z;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public void b() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements Comparator<g> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Collator f1322c = Collator.getInstance();

        d() {
        }

        @Override // java.util.Comparator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compare(g gVar, g gVar2) {
            ApplicationInfo applicationInfo;
            int iCompare;
            int iCompare2 = this.f1322c.compare(gVar.h, gVar2.h);
            if (iCompare2 != 0) {
                return iCompare2;
            }
            ApplicationInfo applicationInfo2 = gVar.q;
            return (applicationInfo2 == null || (applicationInfo = gVar2.q) == null || (iCompare = this.f1322c.compare(applicationInfo2.packageName, applicationInfo.packageName)) == 0) ? gVar.q.uid - gVar2.q.uid : iCompare;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class e implements h {
        e() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public boolean a(g gVar) {
            boolean z;
            synchronized (gVar) {
                z = gVar.q.category == 3;
            }
            return z;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public void b() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class f implements h {
        f() {
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public boolean a(g gVar) {
            if (com.android.settingslib.applications.c.a(gVar.q)) {
                return false;
            }
            if (ApplicationsState.r(gVar.q.flags, 128) || !ApplicationsState.r(gVar.q.flags, 1) || gVar.n) {
                return true;
            }
            return ApplicationsState.r(gVar.q.flags, 1) && gVar.p;
        }

        @Override // com.android.settingslib.applications.ApplicationsState.h
        public void b() {
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class g extends m {
        public final File g;
        public String h;
        public long j;
        public long k;
        public String l;
        public boolean m;
        public boolean n;
        public boolean o;
        public boolean p;
        public ApplicationInfo q;
        public Drawable r;
        public String s;
        public String t;
        public String u;
        public long w;
        public long i = -1;
        public boolean v = true;

        @VisibleForTesting(otherwise = 2)
        public g(final Context context, ApplicationInfo applicationInfo, long j) {
            this.g = new File(applicationInfo.sourceDir);
            this.q = applicationInfo;
            b(context);
            com.android.settingslib.utils.b.e(new Runnable() { // from class: com.android.settingslib.applications.a
                @Override // java.lang.Runnable
                public final void run() {
                    this.f1332c.e(context);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void e(Context context) {
            if (this.r == null) {
                a(context);
            }
            if (this.l == null) {
                c(context);
            }
        }

        boolean a(Context context) {
            if (this.r == null) {
                if (this.g.exists()) {
                    this.r = com.android.settingslib.c.a(context, this.q);
                    return true;
                }
                this.m = false;
                this.r = context.getDrawable(R.drawable.pointer_wait_vector_59);
            } else if (!this.m && this.g.exists()) {
                this.m = true;
                this.r = com.android.settingslib.c.a(context, this.q);
                return true;
            }
            return false;
        }

        public void b(Context context) {
            if (this.h == null || !this.m) {
                if (!this.g.exists()) {
                    this.m = false;
                    this.h = this.q.packageName;
                } else {
                    this.m = true;
                    CharSequence charSequenceLoadLabel = this.q.loadLabel(context.getPackageManager());
                    this.h = charSequenceLoadLabel != null ? charSequenceLoadLabel.toString() : this.q.packageName;
                }
            }
        }

        public void c(Context context) {
            if (UserManager.get(context).isManagedProfile(UserHandle.getUserId(this.q.uid))) {
                this.l = context.getString(R$string.accessibility_work_profile_app_description, this.h);
            } else {
                this.l = this.h;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface h {
        boolean a(g gVar);

        void b();

        default void c(Context context) {
            b();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: caradapter-bug */
    class i extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        boolean f1323a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final IPackageStatsObserver.Stub f1324b;

        /* JADX INFO: compiled from: caradapter-bug */
        class a extends IPackageStatsObserver.Stub {
            a() {
            }

            public void onGetStatsCompleted(PackageStats packageStats, boolean z) {
                boolean z2;
                if (z) {
                    synchronized (ApplicationsState.this.n) {
                        HashMap<String, g> map = ApplicationsState.this.n.get(packageStats.userHandle);
                        if (map == null) {
                            return;
                        }
                        g gVar = map.get(packageStats.packageName);
                        if (gVar != null) {
                            synchronized (gVar) {
                                z2 = false;
                                gVar.v = false;
                                gVar.w = 0L;
                                long j = packageStats.externalCodeSize + packageStats.externalObbSize;
                                long j2 = packageStats.externalDataSize + packageStats.externalMediaSize;
                                long jQ = j + j2 + ApplicationsState.this.q(packageStats);
                                if (gVar.i != jQ || gVar.f1329a != packageStats.cacheSize || gVar.f1330b != packageStats.codeSize || gVar.f1331c != packageStats.dataSize || gVar.d != j || gVar.e != j2 || gVar.f != packageStats.externalCacheSize) {
                                    gVar.i = jQ;
                                    gVar.f1329a = packageStats.cacheSize;
                                    gVar.f1330b = packageStats.codeSize;
                                    gVar.f1331c = packageStats.dataSize;
                                    gVar.d = j;
                                    gVar.e = j2;
                                    gVar.f = packageStats.externalCacheSize;
                                    gVar.s = ApplicationsState.this.o(jQ);
                                    long jQ2 = ApplicationsState.this.q(packageStats);
                                    gVar.j = jQ2;
                                    gVar.t = ApplicationsState.this.o(jQ2);
                                    long jP = ApplicationsState.this.p(packageStats);
                                    gVar.k = jP;
                                    gVar.u = ApplicationsState.this.o(jP);
                                    z2 = true;
                                }
                            }
                            if (z2) {
                                ApplicationsState.this.z.sendMessage(ApplicationsState.this.z.obtainMessage(4, packageStats.packageName));
                            }
                        }
                        if (ApplicationsState.this.s != null && ApplicationsState.this.s.equals(packageStats.packageName) && ApplicationsState.this.t == packageStats.userHandle) {
                            ApplicationsState.this.s = null;
                            i.this.sendEmptyMessage(7);
                        }
                    }
                }
            }
        }

        i(Looper looper) {
            super(looper);
            this.f1324b = new a();
        }

        private int a(List<Session> list) {
            int i;
            synchronized (ApplicationsState.this.n) {
                i = 0;
                Iterator<Session> it = list.iterator();
                while (it.hasNext()) {
                    i |= it.next().m;
                }
            }
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void c() {
            try {
                try {
                    StorageStats storageStatsQueryStatsForPackage = ApplicationsState.this.e.queryStatsForPackage(ApplicationsState.this.r, ApplicationsState.this.s, UserHandle.of(ApplicationsState.this.t));
                    PackageStats packageStats = new PackageStats(ApplicationsState.this.s, ApplicationsState.this.t);
                    packageStats.codeSize = storageStatsQueryStatsForPackage.getCodeBytes();
                    packageStats.dataSize = storageStatsQueryStatsForPackage.getDataBytes();
                    packageStats.cacheSize = storageStatsQueryStatsForPackage.getCacheBytes();
                    this.f1324b.onGetStatsCompleted(packageStats, true);
                } catch (PackageManager.NameNotFoundException | IOException e) {
                    Log.w("ApplicationsState", "Failed to query stats: " + e);
                    this.f1324b.onGetStatsCompleted((PackageStats) null, false);
                }
            } catch (RemoteException unused) {
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:165:0x0363  */
        @Override // android.os.Handler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void handleMessage(android.os.Message r19) {
            /*
                Method dump skipped, instruction units count: 924
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.settingslib.applications.ApplicationsState.i.handleMessage(android.os.Message):void");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface j {
        void a();

        void b(String str);

        void c();

        void d();

        void e();

        void f();

        void g(ArrayList<g> arrayList);

        void h(boolean z);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class k extends Handler {
        public k(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            ApplicationsState.this.v();
            switch (message.what) {
                case 1:
                    Session session = (Session) message.obj;
                    Iterator<WeakReference<Session>> it = ApplicationsState.this.w.iterator();
                    while (it.hasNext()) {
                        Session session2 = it.next().get();
                        if (session2 != null && session2 == session) {
                            session.f1321c.g(session.j);
                        }
                    }
                    break;
                case 2:
                    Iterator<WeakReference<Session>> it2 = ApplicationsState.this.w.iterator();
                    while (it2.hasNext()) {
                        Session session3 = it2.next().get();
                        if (session3 != null) {
                            session3.f1321c.a();
                        }
                    }
                    break;
                case 3:
                    Iterator<WeakReference<Session>> it3 = ApplicationsState.this.w.iterator();
                    while (it3.hasNext()) {
                        Session session4 = it3.next().get();
                        if (session4 != null) {
                            session4.f1321c.e();
                        }
                    }
                    break;
                case 4:
                    Iterator<WeakReference<Session>> it4 = ApplicationsState.this.w.iterator();
                    while (it4.hasNext()) {
                        Session session5 = it4.next().get();
                        if (session5 != null) {
                            session5.f1321c.b((String) message.obj);
                        }
                    }
                    break;
                case 5:
                    Iterator<WeakReference<Session>> it5 = ApplicationsState.this.w.iterator();
                    while (it5.hasNext()) {
                        Session session6 = it5.next().get();
                        if (session6 != null) {
                            session6.f1321c.c();
                        }
                    }
                    break;
                case 6:
                    Iterator<WeakReference<Session>> it6 = ApplicationsState.this.w.iterator();
                    while (it6.hasNext()) {
                        Session session7 = it6.next().get();
                        if (session7 != null) {
                            session7.f1321c.h(message.arg1 != 0);
                        }
                    }
                    break;
                case 7:
                    Iterator<WeakReference<Session>> it7 = ApplicationsState.this.w.iterator();
                    while (it7.hasNext()) {
                        Session session8 = it7.next().get();
                        if (session8 != null) {
                            session8.f1321c.f();
                        }
                    }
                    break;
                case 8:
                    Iterator<WeakReference<Session>> it8 = ApplicationsState.this.w.iterator();
                    while (it8.hasNext()) {
                        Session session9 = it8.next().get();
                        if (session9 != null) {
                            session9.f1321c.d();
                        }
                    }
                    break;
            }
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    private class l extends BroadcastReceiver {
        private l() {
        }

        void a() {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addAction("android.intent.action.PACKAGE_CHANGED");
            intentFilter.addDataScheme(MediaControl.PARAM_PACKAGE);
            ApplicationsState.this.f1318a.registerReceiver(this, intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE");
            intentFilter2.addAction("android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE");
            ApplicationsState.this.f1318a.registerReceiver(this, intentFilter2);
            IntentFilter intentFilter3 = new IntentFilter();
            intentFilter3.addAction("android.intent.action.USER_ADDED");
            intentFilter3.addAction("android.intent.action.USER_REMOVED");
            ApplicationsState.this.f1318a.registerReceiver(this, intentFilter3);
        }

        void b() {
            ApplicationsState.this.f1318a.unregisterReceiver(this);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int i = 0;
            if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
                String encodedSchemeSpecificPart = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.n.size()) {
                    ApplicationsState applicationsState = ApplicationsState.this;
                    applicationsState.h(encodedSchemeSpecificPart, applicationsState.n.keyAt(i));
                    i++;
                }
                return;
            }
            if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
                String encodedSchemeSpecificPart2 = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.n.size()) {
                    ApplicationsState applicationsState2 = ApplicationsState.this;
                    applicationsState2.w(encodedSchemeSpecificPart2, applicationsState2.n.keyAt(i));
                    i++;
                }
                return;
            }
            if ("android.intent.action.PACKAGE_CHANGED".equals(action)) {
                String encodedSchemeSpecificPart3 = intent.getData().getEncodedSchemeSpecificPart();
                while (i < ApplicationsState.this.n.size()) {
                    ApplicationsState applicationsState3 = ApplicationsState.this;
                    applicationsState3.t(encodedSchemeSpecificPart3, applicationsState3.n.keyAt(i));
                    i++;
                }
                return;
            }
            if (!"android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(action) && !"android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE".equals(action)) {
                if ("android.intent.action.USER_ADDED".equals(action)) {
                    ApplicationsState.this.i(intent.getIntExtra("android.intent.extra.user_handle", -10000));
                    return;
                } else {
                    if ("android.intent.action.USER_REMOVED".equals(action)) {
                        ApplicationsState.this.x(intent.getIntExtra("android.intent.extra.user_handle", -10000));
                        return;
                    }
                    return;
                }
            }
            String[] stringArrayExtra = intent.getStringArrayExtra("android.intent.extra.changed_package_list");
            if (stringArrayExtra == null || stringArrayExtra.length == 0 || !"android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE".equals(action)) {
                return;
            }
            for (String str : stringArrayExtra) {
                for (int i2 = 0; i2 < ApplicationsState.this.n.size(); i2++) {
                    ApplicationsState applicationsState4 = ApplicationsState.this;
                    applicationsState4.t(str, applicationsState4.n.keyAt(i2));
                }
            }
        }

        /* synthetic */ l(ApplicationsState applicationsState, d dVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class m {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public long f1329a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public long f1330b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public long f1331c;
        public long d;
        public long e;
        public long f;
    }

    static {
        Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        B = new d();
        C = new f();
        D = new a();
        E = new b();
        F = new c();
        G = new e();
    }

    private ApplicationsState(Application application, IPackageManager iPackageManager) {
        this.f1318a = application;
        this.f1319b = application.getPackageManager();
        IconDrawableFactory.newInstance(this.f1318a);
        this.f1320c = iPackageManager;
        this.d = (UserManager) this.f1318a.getSystemService(UserManager.class);
        this.e = (StorageStatsManager) this.f1318a.getSystemService(StorageStatsManager.class);
        for (int i2 : this.d.getProfileIdsWithDisabled(UserHandle.myUserId())) {
            this.n.put(i2, new HashMap<>());
        }
        HandlerThread handlerThread = new HandlerThread("ApplicationsState.Loader");
        this.x = handlerThread;
        handlerThread.start();
        this.y = new i(this.x.getLooper());
        this.f = 4227584;
        this.g = 33280;
        for (ModuleInfo moduleInfo : this.f1319b.getInstalledModules(0)) {
            this.v.put(moduleInfo.getPackageName(), Boolean.valueOf(moduleInfo.isHidden()));
        }
        synchronized (this.n) {
            try {
                this.n.wait(1L);
            } catch (InterruptedException unused) {
            }
        }
    }

    @VisibleForTesting
    static ApplicationsState getInstance(Application application, IPackageManager iPackageManager) {
        ApplicationsState applicationsState;
        synchronized (A) {
            if (sInstance == null) {
                sInstance = new ApplicationsState(application, iPackageManager);
            }
            applicationsState = sInstance;
        }
        return applicationsState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        if (ArrayUtils.contains(this.d.getProfileIdsWithDisabled(UserHandle.myUserId()), i2)) {
            synchronized (this.n) {
                this.n.put(i2, new HashMap<>());
                if (this.i) {
                    l();
                    m();
                }
                if (!this.z.hasMessages(2)) {
                    this.z.sendEmptyMessage(2);
                }
            }
        }
    }

    private static boolean j(List<ApplicationInfo> list, List<ApplicationInfo> list2) {
        HashSet hashSet;
        if (list.size() == 0) {
            return false;
        }
        if (list2.size() < list.size()) {
            return true;
        }
        HashMap map = new HashMap();
        for (ApplicationInfo applicationInfo : list2) {
            String strValueOf = String.valueOf(UserHandle.getUserId(applicationInfo.uid));
            HashSet hashSet2 = (HashSet) map.get(strValueOf);
            if (hashSet2 == null) {
                hashSet2 = new HashSet();
                map.put(strValueOf, hashSet2);
            }
            if (r(applicationInfo.flags, 8388608)) {
                hashSet2.add(applicationInfo.packageName);
            }
        }
        for (ApplicationInfo applicationInfo2 : list) {
            if (r(applicationInfo2.flags, 8388608) && ((hashSet = (HashSet) map.get(String.valueOf(UserHandle.getUserId(applicationInfo2.uid)))) == null || !hashSet.remove(applicationInfo2.packageName))) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public g n(ApplicationInfo applicationInfo) {
        int userId = UserHandle.getUserId(applicationInfo.uid);
        g gVar = this.n.get(userId).get(applicationInfo.packageName);
        if (gVar != null) {
            if (gVar.q == applicationInfo) {
                return gVar;
            }
            gVar.q = applicationInfo;
            return gVar;
        }
        if (u(applicationInfo.packageName)) {
            return null;
        }
        Context context = this.f1318a;
        long j2 = this.q;
        this.q = 1 + j2;
        g gVar2 = new g(context, applicationInfo, j2);
        this.n.get(userId).put(applicationInfo.packageName, gVar2);
        this.o.add(gVar2);
        return gVar2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String o(long j2) {
        if (j2 >= 0) {
            return Formatter.formatFileSize(this.f1318a, j2);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long p(PackageStats packageStats) {
        if (packageStats != null) {
            return packageStats.externalCodeSize + packageStats.externalDataSize + packageStats.externalCacheSize + packageStats.externalMediaSize + packageStats.externalObbSize;
        }
        return -2L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long q(PackageStats packageStats) {
        if (packageStats != null) {
            return (packageStats.codeSize + packageStats.dataSize) - packageStats.cacheSize;
        }
        return -2L;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean r(int i2, int i3) {
        return (i2 & i3) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x(int i2) {
        synchronized (this.n) {
            HashMap<String, g> map = this.n.get(i2);
            if (map != null) {
                for (g gVar : map.values()) {
                    this.o.remove(gVar);
                    this.p.remove(gVar.q);
                }
                this.n.remove(i2);
                if (!this.z.hasMessages(2)) {
                    this.z.sendEmptyMessage(2);
                }
            }
        }
    }

    @VisibleForTesting
    void clearEntries() {
        for (int i2 = 0; i2 < this.n.size(); i2++) {
            this.n.valueAt(i2).clear();
        }
        this.o.clear();
    }

    void h(String str, int i2) {
        try {
            synchronized (this.n) {
                if (this.i) {
                    if (s(str, i2) >= 0) {
                        return;
                    }
                    ApplicationInfo applicationInfo = this.f1320c.getApplicationInfo(str, this.d.isUserAdmin(i2) ? this.f : this.g, i2);
                    if (applicationInfo == null) {
                        return;
                    }
                    if (applicationInfo.enabled || applicationInfo.enabledSetting == 3) {
                        if (com.android.settingslib.applications.c.a(applicationInfo)) {
                            this.j = true;
                        }
                        this.p.add(applicationInfo);
                        if (!this.y.hasMessages(2)) {
                            this.y.sendEmptyMessage(2);
                        }
                        if (!this.z.hasMessages(2)) {
                            this.z.sendEmptyMessage(2);
                        }
                    }
                }
            }
        } catch (RemoteException unused) {
        }
    }

    void k() {
        if (this.i) {
            for (int i2 = 0; i2 < this.k.size(); i2++) {
                if (this.k.get(i2).d) {
                    return;
                }
            }
            l();
        }
    }

    void l() {
        this.i = false;
        l lVar = this.h;
        if (lVar != null) {
            lVar.b();
            this.h = null;
        }
    }

    void m() {
        if (this.i) {
            return;
        }
        this.i = true;
        d dVar = null;
        if (this.h == null) {
            l lVar = new l(this, dVar);
            this.h = lVar;
            lVar.a();
        }
        List<ApplicationInfo> list = this.p;
        this.p = new ArrayList();
        for (UserInfo userInfo : this.d.getProfiles(UserHandle.myUserId())) {
            try {
                if (this.n.indexOfKey(userInfo.id) < 0) {
                    this.n.put(userInfo.id, new HashMap<>());
                }
                this.p.addAll(this.f1320c.getInstalledApplications(userInfo.isAdmin() ? this.f : this.g, userInfo.id).getList());
            } catch (Exception e2) {
                Log.e("ApplicationsState", "Error during doResumeIfNeededLocked", e2);
            }
        }
        int i2 = 0;
        if (this.m.a(this.f1318a.getResources())) {
            clearEntries();
        } else {
            for (int i3 = 0; i3 < this.o.size(); i3++) {
                this.o.get(i3).v = true;
            }
        }
        this.j = false;
        while (i2 < this.p.size()) {
            ApplicationInfo applicationInfo = this.p.get(i2);
            if (!applicationInfo.enabled && applicationInfo.enabledSetting != 3) {
                this.p.remove(i2);
                i2--;
            } else if (u(applicationInfo.packageName)) {
                this.p.remove(i2);
                i2--;
            } else {
                if (!this.j && com.android.settingslib.applications.c.a(applicationInfo)) {
                    this.j = true;
                }
                g gVar = this.n.get(UserHandle.getUserId(applicationInfo.uid)).get(applicationInfo.packageName);
                if (gVar != null) {
                    gVar.q = applicationInfo;
                }
            }
            i2++;
        }
        if (j(list, this.p)) {
            clearEntries();
        }
        this.s = null;
        if (this.y.hasMessages(2)) {
            return;
        }
        this.y.sendEmptyMessage(2);
    }

    int s(String str, int i2) {
        for (int size = this.p.size() - 1; size >= 0; size--) {
            ApplicationInfo applicationInfo = this.p.get(size);
            if (applicationInfo.packageName.equals(str) && UserHandle.getUserId(applicationInfo.uid) == i2) {
                return size;
            }
        }
        return -1;
    }

    @VisibleForTesting
    void setInterestingConfigChanges(com.android.settingslib.applications.d dVar) {
        this.m = dVar;
    }

    public void t(String str, int i2) {
        w(str, i2);
        h(str, i2);
    }

    boolean u(String str) {
        Boolean bool = this.v.get(str);
        if (bool == null) {
            return false;
        }
        return bool.booleanValue();
    }

    void v() {
        synchronized (this.n) {
            if (this.u) {
                this.w.clear();
                for (int i2 = 0; i2 < this.k.size(); i2++) {
                    Session session = this.k.get(i2);
                    if (session.d) {
                        this.w.add(new WeakReference<>(session));
                    }
                }
            }
        }
    }

    public void w(String str, int i2) {
        synchronized (this.n) {
            int iS = s(str, i2);
            if (iS >= 0) {
                g gVar = this.n.get(i2).get(str);
                if (gVar != null) {
                    this.n.get(i2).remove(str);
                    this.o.remove(gVar);
                }
                ApplicationInfo applicationInfo = this.p.get(iS);
                this.p.remove(iS);
                if (!applicationInfo.enabled) {
                    Iterator<ApplicationInfo> it = this.p.iterator();
                    while (it.hasNext() && it.next().enabled) {
                    }
                }
                if (com.android.settingslib.applications.c.a(applicationInfo)) {
                    this.j = false;
                    Iterator<ApplicationInfo> it2 = this.p.iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        } else if (com.android.settingslib.applications.c.a(it2.next())) {
                            this.j = true;
                            break;
                        }
                    }
                }
                if (!this.z.hasMessages(2)) {
                    this.z.sendEmptyMessage(2);
                }
            }
        }
    }
}
