package p;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.util.Log;
import com.apollographql.apollo3.api.http.HttpMethod;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Scheduler;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class z90 implements i13, d00, q6, fu0, kc2 {
    public static z90 e;
    public static z90 k;
    public final /* synthetic */ int c;

    public z90() {
        this.c = 21;
    }

    public static final LinkedHashMap h(a81 a81Var, tq1 tq1Var, com.apollographql.apollo3.api.c cVar, boolean z, String str) {
        int i = d70.b;
        a81Var.f();
        a81Var.l0("operationName");
        a81Var.N(tq1Var.name());
        a81Var.l0("variables");
        iu0 iu0Var = new iu0(a81Var);
        iu0Var.f();
        tq1Var.a(iu0Var, cVar);
        iu0Var.k();
        LinkedHashMap linkedHashMap = iu0Var.e;
        if (str != null) {
            a81Var.l0("query");
            a81Var.N(str);
        }
        if (z) {
            a81Var.l0("extensions");
            a81Var.f();
            a81Var.l0("persistedQuery");
            a81Var.f();
            a81Var.l0("version").A(1);
            a81Var.l0("sha256Hash").N(tq1Var.c());
            a81Var.k();
            a81Var.k();
        }
        a81Var.k();
        return linkedHashMap;
    }

    public static void k(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static cc2 l(yn ynVar) {
        sm1 sm1Var = new sm1(8, 4, 2);
        ac2 ac2Var = new ac2(true, false, false);
        ynVar.getClass();
        return new cc2(System.currentTimeMillis() + ((long) 3600000), sm1Var, ac2Var, 10.0d, 1.2d, 60);
    }

    public static ww1 m(Context context, String[] strArr, String str, r02 r02Var) {
        String[] strArrR = r(context);
        int length = strArrR.length;
        int i = 0;
        while (true) {
            ZipFile zipFile = null;
            if (i >= length) {
                return null;
            }
            String str2 = strArrR[i];
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                if (i2 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i2 = i3;
                }
            }
            if (zipFile != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    if (i4 < 5) {
                        for (String str3 : strArr) {
                            StringBuilder sb = new StringBuilder("lib");
                            char c = File.separatorChar;
                            sb.append(c);
                            sb.append(str3);
                            sb.append(c);
                            sb.append(str);
                            String string = sb.toString();
                            r02Var.getClass();
                            r02.d("Looking for %s in APK %s...", string, str2);
                            ZipEntry entry = zipFile.getEntry(string);
                            if (entry != null) {
                                return new ww1(zipFile, 4, entry);
                            }
                        }
                        i4 = i5;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
            i++;
        }
    }

    public static String[] n(Context context, String str) {
        StringBuilder sb = new StringBuilder("lib");
        char c = File.separatorChar;
        sb.append(c);
        sb.append("([^\\");
        sb.append(c);
        sb.append("]*)");
        sb.append(c);
        sb.append(str);
        Pattern patternCompile = Pattern.compile(sb.toString());
        HashSet hashSet = new HashSet();
        for (String str2 : r(context)) {
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = new ZipFile(new File(str2), 1).entries();
                while (enumerationEntries.hasMoreElements()) {
                    Matcher matcher = patternCompile.matcher(enumerationEntries.nextElement().getName());
                    if (matcher.matches()) {
                        hashSet.add(matcher.group(1));
                    }
                }
            } catch (IOException unused) {
            }
        }
        return (String[]) hashSet.toArray(new String[hashSet.size()]);
    }

    public static ge2 p(String str) {
        if (ng2.g0(str, "__", false)) {
            throw new AssertionError("Cannot create key in protected namespace");
        }
        HashSet hashSet = ge2.c;
        if (hashSet.contains(str)) {
            throw new AssertionError("Requesting same string for a key previously defined somewhere else: ".concat(str));
        }
        hashSet.add(str);
        return new ge2(str);
    }

    public static io.reactivex.rxjava3.internal.operators.flowable.k q(jh1 jh1Var) {
        int i = v52.a;
        Scheduler scheduler = io.reactivex.rxjava3.schedulers.e.c;
        v03.i(scheduler, "io()");
        tq1 tq1Var = (tq1) jh1Var.b;
        v03.j(tq1Var, "operation");
        v03.i(UUID.randomUUID(), "randomUUID()");
        tk0 tk0Var = (tk0) jh1Var.c;
        v03.j(tk0Var, "executionContext");
        HttpMethod httpMethod = (HttpMethod) jh1Var.d;
        List list = (List) jh1Var.e;
        Boolean bool = (Boolean) jh1Var.f;
        Boolean bool2 = (Boolean) jh1Var.g;
        Boolean bool3 = (Boolean) jh1Var.h;
        i7 i7Var = (i7) jh1Var.a;
        i7Var.getClass();
        mw mwVar = i7Var.e;
        com.apollographql.apollo3.api.c cVar = i7Var.a;
        tk0 tk0VarD = mwVar.d(cVar).d(i7Var.c).d(tk0Var);
        UUID uuidRandomUUID = UUID.randomUUID();
        v03.i(uuidRandomUUID, "randomUUID()");
        td0.b.d(mwVar);
        tk0 tk0VarD2 = mwVar.d(cVar);
        v03.j(tk0VarD2, "<set-?>");
        v03.j(tk0VarD, "executionContext");
        tk0 tk0VarD3 = tk0VarD2.d(tk0VarD);
        v03.j(tk0VarD3, "<set-?>");
        tk0 tk0VarD4 = tk0VarD3.d(tk0Var);
        v03.j(tk0VarD4, "<set-?>");
        m7 m7Var = new m7(tq1Var, uuidRandomUUID, tk0VarD4, httpMethod != null ? httpMethod : null, list != null ? list : null, bool != null ? bool : null, bool2 != null ? bool2 : null, bool3 != null ? bool3 : i7Var.d, null);
        ArrayList arrayListJ0 = ou.j0(i7Var.b, i7Var.f);
        if (arrayListJ0.size() <= 0) {
            throw new IllegalStateException("Check failed.".toString());
        }
        dv0 dv0VarA = ((j7) arrayListJ0.get(0)).a(m7Var, new e70(1, arrayListJ0));
        e72 e72Var = new e72(scheduler);
        int i2 = s02.a;
        kotlinx.coroutines.h hVar = ha0.b;
        hVar.getClass();
        Flowable flowableJ = Flowable.j(new ev0(kotlin.coroutines.a.a(hVar, e72Var), dv0VarA));
        flowableJ.getClass();
        return new io.reactivex.rxjava3.internal.operators.flowable.k(flowableJ);
    }

    public static String[] r(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String[] strArr = applicationInfo.splitSourceDirs;
        if (strArr == null || strArr.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr2 = new String[strArr.length + 1];
        strArr2[0] = applicationInfo.sourceDir;
        System.arraycopy(strArr, 0, strArr2, 1, strArr.length);
        return strArr2;
    }

    @Override // p.i13
    public final Object a() {
        switch (this.c) {
            case 13:
                return new s03();
            case 14:
                ExecutorService executorServiceNewSingleThreadExecutor = Executors.newSingleThreadExecutor(p23.c);
                ci1.M(executorServiceNewSingleThreadExecutor);
                return executorServiceNewSingleThreadExecutor;
            default:
                return new pz2();
        }
    }

    @Override // p.fu0
    public final void b() {
    }

    @Override // p.fu0
    public final String c() {
        return null;
    }

    @Override // p.fu0
    public final byte[] d() {
        return null;
    }

    @Override // p.fu0
    public final void e() {
    }

    @Override // p.fu0
    public final void f(String str, long j) {
    }

    @Override // p.kc2
    public final cc2 g(yn ynVar, JSONObject jSONObject) {
        return l(ynVar);
    }

    @Override // p.q6
    public final void i(Bundle bundle) {
        Log.isLoggable("FirebaseCrashlytics", 3);
    }

    @Override // p.d00
    public final Object j(aj2 aj2Var) {
        if (aj2Var.d()) {
            return null;
        }
        Log.e("FirebaseCrashlytics", "Error fetching settings.", aj2Var.b());
        return null;
    }

    public final synchronized ge2 o(String str) {
        return p(str);
    }

    public /* synthetic */ z90(int i) {
        this.c = i;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z90(int i, int i2) {
        this(0);
        this.c = i;
        int i3 = 11;
        if (i == 11) {
            this(i3);
            return;
        }
        int i4 = 22;
        if (i == 22) {
            this(i4);
            return;
        }
        int i5 = 24;
        if (i == 24) {
            this(i5);
            return;
        }
        int i6 = 25;
        if (i == 25) {
            this(i6);
            return;
        }
        int i7 = 27;
        if (i == 27) {
            this(i7);
            return;
        }
        int i8 = 28;
        if (i != 28) {
            switch (i) {
                case 1:
                    this(1);
                    break;
                case 2:
                    this(2);
                    break;
                case 3:
                    this(3);
                    break;
                case 4:
                    this(4);
                    break;
                case 5:
                    this(5);
                    break;
                case 6:
                    this(6);
                    break;
                case 7:
                    this(7);
                    break;
                case 8:
                    this(8);
                    break;
                case 9:
                    this(9);
                    break;
                default:
                    break;
            }
            return;
        }
        this(i8);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ z90(Object obj) {
        this(18);
        this.c = 18;
    }
}
