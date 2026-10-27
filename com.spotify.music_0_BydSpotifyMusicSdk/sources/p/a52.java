package p;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.work.impl.foreground.SystemForegroundService;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.play.core.common.LocalTestingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class a52 implements Runnable {
    public final /* synthetic */ int c;
    public final int e;
    public final Object k;
    public final Object l;

    public /* synthetic */ a52(int i, int i2, Object obj, Object obj2) {
        this.c = i2;
        this.l = obj;
        this.k = obj2;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int i = this.c;
        Object obj = this.l;
        int i2 = this.e;
        Object obj2 = this.k;
        switch (i) {
            case 0:
                ((b52) obj).a(i2, (Bundle) obj2);
                break;
            case 1:
                zu zuVar = (zu) ((hk0) obj2).e;
                if (zuVar != null) {
                    zuVar.u0(i2);
                }
                break;
            case 2:
                List list = (List) obj2;
                int size = list.size();
                int i3 = 0;
                if (i2 == 1) {
                    while (i3 < size) {
                        ((hc0) list.get(i3)).a();
                        i3++;
                    }
                } else {
                    while (i3 < size) {
                        ((hc0) list.get(i3)).getClass();
                        i3++;
                    }
                }
                break;
            case 3:
                ((zh2) obj2).a((Intent) obj, i2);
                break;
            case 4:
                ((SystemForegroundService) obj).m.notify(i2, (Notification) obj2);
                break;
            case 5:
                ((BottomSheetBehavior) obj).B((View) obj2, i2);
                break;
            default:
                t13 t13Var = (t13) obj2;
                String str = (String) obj;
                t13Var.getClass();
                try {
                    t13Var.h(str, i2);
                } catch (LocalTestingException e) {
                    t13.f.f("notifyModuleCompleted failed", e);
                }
                break;
        }
    }

    public /* synthetic */ a52(SystemForegroundService systemForegroundService, int i, Notification notification) {
        this.c = 4;
        this.l = systemForegroundService;
        this.e = i;
        this.k = notification;
    }

    public /* synthetic */ a52(t13 t13Var, int i, String str) {
        this.c = 6;
        this.k = t13Var;
        this.e = i;
        this.l = str;
    }

    public a52(int i, Intent intent, zh2 zh2Var) {
        this.c = 3;
        this.k = zh2Var;
        this.l = intent;
        this.e = i;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public a52(hc0 hc0Var, int i) {
        this.c = 2;
        hc0[] hc0VarArr = new hc0[1];
        if (hc0Var != null) {
            hc0VarArr[0] = hc0Var;
            this(Arrays.asList(hc0VarArr), i, (Throwable) null);
            return;
        }
        throw new NullPointerException("initCallback cannot be null");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public a52(int i, ArrayList arrayList) {
        this(arrayList, i, (Throwable) null);
        this.c = 2;
    }

    public a52(List list, int i, Throwable th) {
        this.c = 2;
        if (list != null) {
            this.k = new ArrayList(list);
            this.e = i;
            this.l = th;
            return;
        }
        throw new NullPointerException("initCallbacks cannot be null");
    }
}
