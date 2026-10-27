package p;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.datepicker.MaterialCalendar;
import com.spotify.music.R;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jh1 {
    public Object a;
    public Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;
    public Object h;

    public jh1(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(he1.O(R.attr.materialCalendarStyle, context, MaterialCalendar.class.getCanonicalName()), xz1.m);
        this.a = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(3, 0));
        this.g = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(1, 0));
        this.b = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(2, 0));
        this.c = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(4, 0));
        ColorStateList colorStateListV = ci1.v(context, typedArrayObtainStyledAttributes, 6);
        this.d = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(8, 0));
        this.e = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(7, 0));
        this.f = pv0.a(context, typedArrayObtainStyledAttributes.getResourceId(9, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListV.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }

    public static jh1 a(Context context, e31 e31Var, String str, String str2, ArrayList arrayList, g90 g90Var) throws PackageManager.NameNotFoundException {
        String packageName = context.getPackageName();
        String strE = e31Var.e();
        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
        String string = Integer.toString(packageInfo.versionCode);
        String str3 = packageInfo.versionName;
        if (str3 == null) {
            str3 = "0.0";
        }
        jh1 jh1Var = new jh1();
        jh1Var.a = str;
        jh1Var.b = str2;
        jh1Var.c = arrayList;
        jh1Var.d = strE;
        jh1Var.e = packageName;
        jh1Var.f = string;
        jh1Var.g = str3;
        jh1Var.h = g90Var;
        return jh1Var;
    }
}
