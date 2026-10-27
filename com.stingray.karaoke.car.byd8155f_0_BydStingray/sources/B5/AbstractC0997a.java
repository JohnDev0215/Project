package b5;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import p.C2066a;

/* JADX INFO: renamed from: b5.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0997a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final int[] f12225a = {R.attr.theme, com.stingray.drivemodedetector.R.attr.theme};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final int[] f12226b = {com.stingray.drivemodedetector.R.attr.materialThemeOverlay};

    public static Context a(Context context, AttributeSet attributeSet, int i7, int i10) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, f12226b, i7, i10);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        boolean z6 = (context instanceof C2066a) && ((C2066a) context).f20602a == resourceId;
        if (resourceId == 0 || z6) {
            return context;
        }
        C2066a c2066a = new C2066a(context);
        c2066a.f20602a = resourceId;
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, f12225a);
        int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
        int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(1, 0);
        typedArrayObtainStyledAttributes2.recycle();
        if (resourceId2 == 0) {
            resourceId2 = resourceId3;
        }
        if (resourceId2 != 0) {
            c2066a.getTheme().applyStyle(resourceId2, true);
        }
        return c2066a;
    }
}
