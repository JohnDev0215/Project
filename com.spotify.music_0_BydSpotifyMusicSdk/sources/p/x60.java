package p;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.zip.GZIPOutputStream;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class x60 implements Callable {
    public final /* synthetic */ int c;
    public final /* synthetic */ y60 e;

    public /* synthetic */ x60(y60 y60Var, int i) {
        this.c = i;
        this.e = y60Var;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Set setUnmodifiableSet;
        Set setUnmodifiableSet2;
        String string;
        switch (this.c) {
            case 0:
                y60 y60Var = this.e;
                synchronized (y60Var) {
                    x11 x11Var = (x11) y60Var.a.get();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    d80 d80Var = (d80) y60Var.c.get();
                    p01 p01Var = d80Var.b;
                    synchronized (((Set) p01Var.e)) {
                        setUnmodifiableSet = Collections.unmodifiableSet((Set) p01Var.e);
                        break;
                    }
                    boolean zIsEmpty = setUnmodifiableSet.isEmpty();
                    String string2 = d80Var.a;
                    if (!zIsEmpty) {
                        StringBuilder sb = new StringBuilder();
                        sb.append(string2);
                        sb.append(' ');
                        synchronized (((Set) p01Var.e)) {
                            setUnmodifiableSet2 = Collections.unmodifiableSet((Set) p01Var.e);
                            break;
                        }
                        sb.append(d80.a(setUnmodifiableSet2));
                        string2 = sb.toString();
                    }
                    x11Var.f(string2, jCurrentTimeMillis);
                }
                return null;
            default:
                y60 y60Var2 = this.e;
                synchronized (y60Var2) {
                    try {
                        x11 x11Var2 = (x11) y60Var2.a.get();
                        ArrayList arrayListC = x11Var2.c();
                        x11Var2.b();
                        JSONArray jSONArray = new JSONArray();
                        for (int i = 0; i < arrayListC.size(); i++) {
                            ci ciVar = (ci) arrayListC.get(i);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("agent", ciVar.a);
                            jSONObject.put("dates", new JSONArray((Collection) ciVar.b));
                            jSONArray.put(jSONObject);
                        }
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("heartbeats", jSONArray);
                        jSONObject2.put("version", "2");
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        Base64OutputStream base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 11);
                        try {
                            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(base64OutputStream);
                            try {
                                gZIPOutputStream.write(jSONObject2.toString().getBytes("UTF-8"));
                                gZIPOutputStream.close();
                                base64OutputStream.close();
                                string = byteArrayOutputStream.toString("UTF-8");
                            } finally {
                                try {
                                    break;
                                } catch (Throwable th) {
                                }
                            }
                        } finally {
                            try {
                                break;
                            } catch (Throwable th2) {
                            }
                        }
                    } finally {
                    }
                }
                return string;
        }
    }
}
