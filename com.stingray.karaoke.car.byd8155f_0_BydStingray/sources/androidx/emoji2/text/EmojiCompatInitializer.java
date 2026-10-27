package androidx.emoji2.text;

import A9.b;
import P6.a;
import T1.i;
import T1.j;
import T1.s;
import android.content.Context;
import androidx.lifecycle.A;
import androidx.lifecycle.ProcessLifecycleInitializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import u3.C2523a;
import u3.InterfaceC2524b;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements InterfaceC2524b {
    @Override // u3.InterfaceC2524b
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // u3.InterfaceC2524b
    public final Object b(Context context) {
        Object objB;
        s sVar = new s(new a(context, 2));
        sVar.f8492a = 1;
        if (i.f8495k == null) {
            synchronized (i.f8494j) {
                try {
                    if (i.f8495k == null) {
                        i.f8495k = new i(sVar);
                    }
                } finally {
                }
            }
        }
        C2523a c2523aC = C2523a.c(context);
        c2523aC.getClass();
        synchronized (C2523a.f22919e) {
            try {
                objB = c2523aC.f22920a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = c2523aC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        b bVarG = ((A) objB).g();
        bVarG.M0(new j(this, bVarG));
        return Boolean.TRUE;
    }
}
