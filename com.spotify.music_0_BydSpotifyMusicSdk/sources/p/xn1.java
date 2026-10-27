package p;

import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class xn1 {
    public Context a;
    public ArrayList b;
    public ArrayList c;
    public ArrayList d;
    public CharSequence e;
    public int f;
    public boolean g;
    public zn1 h;
    public Bundle i;
    public String j;
    public boolean k;
    public Notification l;
    public ArrayList m;

    public final void a(yn1 yn1Var) {
        if (this.h != yn1Var) {
            this.h = yn1Var;
            if (yn1Var.a != this) {
                yn1Var.a = this;
                a(yn1Var);
            }
        }
    }
}
