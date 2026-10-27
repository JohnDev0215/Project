package r1;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;

/* JADX INFO: renamed from: r1.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2274f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f21659a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f21660b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f21661e;

    public C2274f(Context context, XmlResourceParser xmlResourceParser) {
        this.f21659a = Float.NaN;
        this.f21660b = Float.NaN;
        this.c = Float.NaN;
        this.d = Float.NaN;
        this.f21661e = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), p.f21770j);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == 0) {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.f21661e);
                this.f21661e = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                if ("layout".equals(resourceTypeName)) {
                    new m().b((ConstraintLayout) LayoutInflater.from(context).inflate(resourceId, (ViewGroup) null));
                }
            } else if (index == 1) {
                this.d = typedArrayObtainStyledAttributes.getDimension(index, this.d);
            } else if (index == 2) {
                this.f21660b = typedArrayObtainStyledAttributes.getDimension(index, this.f21660b);
            } else if (index == 3) {
                this.c = typedArrayObtainStyledAttributes.getDimension(index, this.c);
            } else if (index == 4) {
                this.f21659a = typedArrayObtainStyledAttributes.getDimension(index, this.f21659a);
            } else {
                Log.v("ConstraintLayoutStates", "Unknown tag");
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
