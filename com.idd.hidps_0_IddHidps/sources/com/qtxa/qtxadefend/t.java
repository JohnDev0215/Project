package com.qtxa.qtxadefend;

import android.content.Context;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
final class t extends g {
    private final JSONObject c;
    private final Context d;

    t(Context context) {
        this.d = context;
        this.c = e();
        d();
    }

    private void d() {
        Iterator<String> itKeys = this.c.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                w.d("config : " + next + " : " + this.c.get(next));
            } catch (JSONException unused) {
                return;
            }
        }
    }

    private JSONObject e() {
        try {
            return new JSONObject(super.b(this.d.openFileInput("config"), true));
        } catch (IOException | JSONException unused) {
            return new JSONObject();
        }
    }

    void f() {
        Context context = this.d;
        if (context == null) {
            return;
        }
        try {
            FileOutputStream fileOutputStreamOpenFileOutput = context.openFileOutput("config", 0);
            super.c(fileOutputStreamOpenFileOutput, this.c.toString(4), true);
            fileOutputStreamOpenFileOutput.close();
        } catch (IOException | JSONException unused) {
        }
    }

    protected void finalize() throws Throwable {
        try {
            f();
        } finally {
            super.finalize();
        }
    }

    synchronized void g(t tVar) {
        Iterator<String> itKeys = tVar.c.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                this.c.put(next, tVar.c.get(next));
            } catch (JSONException unused) {
                w.a("wrong config : " + tVar.toString());
            }
        }
        f();
    }

    public String toString() {
        return this.c.toString();
    }

    t(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException unused) {
            jSONObject = new JSONObject();
        }
        this.c = jSONObject;
        this.d = null;
    }
}
