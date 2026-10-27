package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.util.Base64;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class pv0 {
    public final /* synthetic */ int a = 2;
    public final int b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ pv0(int i, Boolean bool, yc ycVar, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler, Boolean bool2, int i2) {
        this(i, bool, ycVar, onAudioFocusChangeListener, handler, bool2);
    }

    public static pv0 a(Context context, int i) {
        p02.c("Cannot create a CalendarItemStyle with a styleResId of 0", i != 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, xz1.n);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(2, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(3, 0));
        ColorStateList colorStateListV = ci1.v(context, typedArrayObtainStyledAttributes, 4);
        ColorStateList colorStateListV2 = ci1.v(context, typedArrayObtainStyledAttributes, 9);
        ColorStateList colorStateListV3 = ci1.v(context, typedArrayObtainStyledAttributes, 7);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(8, 0);
        oc2 oc2VarA = oc2.a(context, typedArrayObtainStyledAttributes.getResourceId(5, 0), typedArrayObtainStyledAttributes.getResourceId(6, 0), new y0(0)).a();
        typedArrayObtainStyledAttributes.recycle();
        return new pv0(colorStateListV, colorStateListV2, colorStateListV3, dimensionPixelSize, oc2VarA, rect);
    }

    public final String toString() {
        switch (this.a) {
            case 0:
                StringBuilder sb = new StringBuilder();
                sb.append("FontRequest {mProviderAuthority: " + ((String) this.c) + ", mProviderPackage: " + ((String) this.d) + ", mQuery: " + ((String) this.e) + ", mCertificates:");
                int i = 0;
                while (true) {
                    List list = (List) this.g;
                    if (i >= list.size()) {
                        sb.append("}");
                        sb.append("mCertificatesArray: " + this.b);
                        return sb.toString();
                    }
                    sb.append(" [");
                    List list2 = (List) list.get(i);
                    for (int i2 = 0; i2 < list2.size(); i2++) {
                        sb.append(" \"");
                        sb.append(Base64.encodeToString((byte[]) list2.get(i2), 0));
                        sb.append("\"");
                    }
                    sb.append(" ]");
                    i++;
                }
                break;
            default:
                return super.toString();
        }
    }

    public pv0(int i, Boolean bool, yc ycVar, AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener, Handler handler, Boolean bool2) {
        this.b = i;
        this.c = bool;
        this.d = ycVar;
        this.e = onAudioFocusChangeListener;
        this.f = handler;
        this.g = bool2;
    }

    public pv0(String str, List list, String str2, String str3) {
        str.getClass();
        this.c = str;
        str2.getClass();
        this.d = str2;
        this.e = str3;
        list.getClass();
        this.g = list;
        this.b = 0;
        this.f = str + "-" + str2 + "-" + str3;
    }

    public pv0(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, oc2 oc2Var, Rect rect) {
        p02.d(rect.left);
        p02.d(rect.top);
        p02.d(rect.right);
        p02.d(rect.bottom);
        this.c = rect;
        this.d = colorStateList2;
        this.e = colorStateList;
        this.f = colorStateList3;
        this.b = i;
        this.g = oc2Var;
    }
}
