package com.amazon.identity.auth.device;

import android.content.Context;
import android.util.Log;
import com.amazon.identity.kcpsdk.auth.ParseErrorException;
import java.io.IOException;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public class lb implements zb {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public tc f422a;
    public final Context b;
    public final y5 c;

    public lb(Context context, tc tcVar, y5 y5Var) {
        this.f422a = tcVar;
        this.b = context;
        this.c = y5Var;
    }

    public Object a(ld ldVar, jb jbVar, tb tbVar) throws ParseErrorException, IOException {
        Context context = this.b;
        tc tcVar = this.f422a;
        y5 y5Var = this.c;
        if (ib.a(ldVar, tcVar)) {
            Log.i(ga.a(ib.f), "Starting web request");
            String.format("URL: %s", ldVar.b());
            return ib.a(context, ldVar, jbVar, y5Var);
        }
        Log.i(ga.a(ib.f), "Failed to sign request, aborting call to " + ldVar.b());
        return null;
    }
}
