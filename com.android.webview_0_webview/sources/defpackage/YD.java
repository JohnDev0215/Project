package defpackage;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.textclassifier.TextClassification;
import android.view.textclassifier.TextSelection;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class YD {
    public int a;
    public int b;
    public CharSequence c;
    public Drawable d;
    public Intent e;
    public View.OnClickListener f;
    public TextClassification g;
    public TextSelection h;

    public boolean a() {
        return ((this.c == null && this.d == null) || (this.e == null && this.f == null)) ? false : true;
    }
}
