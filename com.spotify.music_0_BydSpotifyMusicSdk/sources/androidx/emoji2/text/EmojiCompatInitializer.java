package androidx.emoji2.text;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import androidx.lifecycle.ProcessLifecycleInitializer;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import p.jc0;
import p.l91;
import p.lw;
import p.mc0;
import p.n41;
import p.n70;
import p.qa;
import p.s91;
import p.sv0;
import p.y3;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements n41 {
    @Override // p.n41
    public final List a() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }

    @Override // p.n41
    /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
    public final Boolean b(Context context) {
        Object objB;
        sv0 sv0Var = new sv0(new y3(context, 0));
        sv0Var.b = 1;
        if (jc0.j == null) {
            synchronized (jc0.i) {
                try {
                    if (jc0.j == null) {
                        jc0.j = new jc0(sv0Var);
                    }
                } finally {
                }
            }
        }
        qa qaVarC = qa.c(context);
        qaVarC.getClass();
        synchronized (qa.e) {
            try {
                objB = qaVarC.a.get(ProcessLifecycleInitializer.class);
                if (objB == null) {
                    objB = qaVarC.b(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        final l91 l91VarL = ((s91) objB).l();
        l91VarL.a(new n70() { // from class: androidx.emoji2.text.EmojiCompatInitializer.1
            @Override // p.n70
            public final void onResume(s91 s91Var) {
                EmojiCompatInitializer.this.getClass();
                (Build.VERSION.SDK_INT >= 28 ? lw.a(Looper.getMainLooper()) : new Handler(Looper.getMainLooper())).postDelayed(new mc0(0), 500L);
                l91VarL.c(this);
            }
        });
        return Boolean.TRUE;
    }
}
