package com.google.android.datatransport.cct;

import android.content.Context;
import androidx.annotation.Keep;
import p.eo2;
import p.m40;
import p.oq;
import p.zh;

/* JADX INFO: loaded from: classes.dex */
@Keep
public class CctBackendFactory {
    public eo2 create(m40 m40Var) {
        Context context = ((zh) m40Var).a;
        zh zhVar = (zh) m40Var;
        return new oq(context, zhVar.b, zhVar.c);
    }
}
