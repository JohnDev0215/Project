package androidx.appcompat.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ListView;
import p.yz1;

/* JADX INFO: loaded from: classes.dex */
public class AlertController$RecycleListView extends ListView {
    public final int c;
    public final int e;

    public AlertController$RecycleListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, yz1.t);
        this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(0, -1);
        this.c = typedArrayObtainStyledAttributes.getDimensionPixelOffset(1, -1);
    }
}
