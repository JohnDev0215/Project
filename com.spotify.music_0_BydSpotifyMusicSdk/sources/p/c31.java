package p;

import androidx.work.impl.WorkDatabase;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class c31 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ Object e;

    public /* synthetic */ c31(int i, Object obj) {
        this.c = i;
        this.e = obj;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() throws Throwable {
        String str;
        BufferedWriter bufferedWriter;
        Map mapUnmodifiableMap;
        BufferedWriter bufferedWriter2;
        String string;
        BufferedWriter bufferedWriter3 = null;
        switch (this.c) {
            case 0:
                oh1 oh1Var = (oh1) this.e;
                v03.j(oh1Var, "this$0");
                WorkDatabase workDatabase = (WorkDatabase) oh1Var.e;
                Long lV = workDatabase.r().v("next_alarm_manager_id");
                int iLongValue = lV != null ? (int) lV.longValue() : 0;
                workDatabase.r().y(new vw1("next_alarm_manager_id", Long.valueOf(iLongValue != Integer.MAX_VALUE ? iLongValue + 1 : 0)));
                return Integer.valueOf(iLongValue);
            case 1:
                gu0 gu0Var = (gu0) this.e;
                synchronized (((AtomicMarkableReference) gu0Var.f)) {
                    try {
                        if (((AtomicMarkableReference) gu0Var.f).isMarked()) {
                            str = (String) ((AtomicMarkableReference) gu0Var.f).getReference();
                            ((AtomicMarkableReference) gu0Var.f).set(str, false);
                            i = 1;
                        } else {
                            str = null;
                        }
                    } finally {
                    }
                    break;
                }
                if (i != 0) {
                    File fileL = ((ak1) gu0Var.a).a.l((String) gu0Var.c, "user-data");
                    try {
                        String string2 = new zj1(str).toString();
                        bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileL), ak1.b));
                        try {
                            bufferedWriter.write(string2);
                            bufferedWriter.flush();
                            break;
                        } catch (Exception unused) {
                        } catch (Throwable th) {
                            th = th;
                            bufferedWriter3 = bufferedWriter;
                            zu.q(bufferedWriter3, "Failed to close user metadata file.");
                            throw th;
                        }
                    } catch (Exception unused2) {
                        bufferedWriter = null;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    zu.q(bufferedWriter, "Failed to close user metadata file.");
                }
                return null;
            default:
                aa0 aa0Var = (aa0) this.e;
                ((AtomicReference) aa0Var.c).set(null);
                synchronized (aa0Var) {
                    try {
                        if (((AtomicMarkableReference) aa0Var.b).isMarked()) {
                            o81 o81Var = (o81) ((AtomicMarkableReference) aa0Var.b).getReference();
                            synchronized (o81Var) {
                                mapUnmodifiableMap = Collections.unmodifiableMap(new HashMap(o81Var.a));
                            }
                            Object obj = aa0Var.b;
                            ((AtomicMarkableReference) obj).set((o81) ((AtomicMarkableReference) obj).getReference(), false);
                        } else {
                            mapUnmodifiableMap = null;
                        }
                    } finally {
                    }
                }
                if (mapUnmodifiableMap != null) {
                    gu0 gu0Var2 = (gu0) aa0Var.d;
                    ak1 ak1Var = (ak1) gu0Var2.a;
                    String str2 = (String) gu0Var2.c;
                    File fileL2 = aa0Var.a ? ak1Var.a.l(str2, "internal-keys") : ak1Var.a.l(str2, "keys");
                    try {
                        string = new JSONObject(mapUnmodifiableMap).toString();
                        bufferedWriter2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileL2), ak1.b));
                    } catch (Exception unused3) {
                        bufferedWriter2 = null;
                    } catch (Throwable th3) {
                        th = th3;
                        zu.q(bufferedWriter3, "Failed to close key/value metadata file.");
                        throw th;
                    }
                    try {
                        try {
                            bufferedWriter2.write(string);
                            bufferedWriter2.flush();
                        } catch (Throwable th4) {
                            th = th4;
                            bufferedWriter3 = bufferedWriter2;
                            zu.q(bufferedWriter3, "Failed to close key/value metadata file.");
                            throw th;
                        }
                    } catch (Exception unused4) {
                        ak1.d(fileL2);
                    }
                    zu.q(bufferedWriter2, "Failed to close key/value metadata file.");
                    break;
                }
                return null;
        }
    }
}
