package defpackage;

import android.graphics.drawable.Drawable;
import defpackage.wp2;
import org.chromium.chrome.browser.ui.messages.R$string;

/* JADX INFO: compiled from: Snackbar.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tp2 {
    public wp2.b a;
    public CharSequence b;
    public String c;
    public String d;
    public Object e;
    public String f;
    public int g;
    public int h;
    public int j;
    public Drawable k;
    public int l;
    public boolean i = true;
    public int m = -1;
    public int n = 0;

    public static tp2 a(CharSequence charSequence, wp2.b bVar, int i, int i2) {
        tp2 tp2Var = new tp2();
        tp2Var.b = charSequence;
        tp2Var.a = bVar;
        tp2Var.l = i;
        tp2Var.m = i2;
        if (i == 2) {
            tp2Var.d = xz.a.getResources().getString(R$string.ok);
        }
        return tp2Var;
    }
}
