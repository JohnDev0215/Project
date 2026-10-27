package io.sentry.android.core.internal.threaddump;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import r1.m;
import r1.p;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f16193a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f16194b;
    public int c;

    public b(ArrayList arrayList) {
        this.f16194b = arrayList;
        this.f16193a = arrayList.size();
    }

    public a a() {
        int i7 = this.c;
        if (i7 < 0 || i7 >= this.f16193a) {
            return null;
        }
        this.c = i7 + 1;
        return (a) this.f16194b.get(i7);
    }

    public b(Context context, XmlResourceParser xmlResourceParser) {
        this.f16194b = new ArrayList();
        this.c = -1;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), p.f21768h);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i7);
            if (index == 0) {
                this.f16193a = typedArrayObtainStyledAttributes.getResourceId(index, this.f16193a);
            } else if (index == 1) {
                int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, this.c);
                this.c = resourceId;
                String resourceTypeName = context.getResources().getResourceTypeName(resourceId);
                context.getResources().getResourceName(resourceId);
                if ("layout".equals(resourceTypeName)) {
                    new m().b((ConstraintLayout) LayoutInflater.from(context).inflate(resourceId, (ViewGroup) null));
                }
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
