package com.spotify.appstorage.sharedpreferencesimpl;

import android.content.SharedPreferences;
import p.ge2;
import p.he2;
import p.ie2;
import p.le2;
import p.oh1;
import p.py0;
import p.u6;
import p.v03;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements he2 {
    public final xy1 a;
    public final boolean b;
    public final oh1 c;

    public a(ie2 ie2Var, boolean z, oh1 oh1Var) {
        v03.j(oh1Var, "mUtils");
        this.a = ie2Var;
        this.b = z;
        this.c = oh1Var;
    }

    public final le2 a() {
        Object obj = this.a.get();
        v03.i(obj, "mSharedPreferences.get()");
        SharedPreferences.Editor editorEdit = ((SharedPreferences) obj).edit();
        v03.i(editorEdit, "getSharedPreferences().edit()");
        return new le2(editorEdit, this.b, this.c);
    }

    public final boolean b(final ge2 ge2Var) throws Exception {
        v03.j(ge2Var, "key");
        Object objD = d(new py0() { // from class: com.spotify.appstorage.sharedpreferencesimpl.SpSharedPreferencesImpl$getBoolean$1
            final /* synthetic */ boolean $defaultValue = false;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                Object obj = this.this$0.a.get();
                v03.i(obj, "mSharedPreferences.get()");
                return Boolean.valueOf(((SharedPreferences) obj).getBoolean(ge2Var.a, this.$defaultValue));
            }
        });
        v03.h(objD);
        return ((Boolean) objD).booleanValue();
    }

    public final String c(final ge2 ge2Var) {
        v03.j(ge2Var, "key");
        return (String) d(new py0() { // from class: com.spotify.appstorage.sharedpreferencesimpl.SpSharedPreferencesImpl$getString$1
            final /* synthetic */ String $defaultValue = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final Object invoke() {
                Object obj = this.this$0.a.get();
                v03.i(obj, "mSharedPreferences.get()");
                return ((SharedPreferences) obj).getString(ge2Var.a, this.$defaultValue);
            }
        });
    }

    public final Object d(py0 py0Var) throws Exception {
        oh1 oh1Var = this.c;
        ((u6) oh1Var.e).getClass();
        System.currentTimeMillis();
        Object objInvoke = null;
        try {
            objInvoke = py0Var.invoke();
            e = null;
        } catch (Exception e) {
            e = e;
        }
        ((u6) oh1Var.e).getClass();
        System.currentTimeMillis();
        if (e == null) {
            return objInvoke;
        }
        throw e;
    }
}
