package s3;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.ViewGroup;

/* JADX INFO: renamed from: s3.G, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class C2368G extends ViewGroup.MarginLayoutParams {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public U f22190a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Rect f22191b;
    public boolean c;
    public boolean d;

    public C2368G(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f22191b = new Rect();
        this.c = true;
        this.d = false;
    }

    public C2368G(int i7, int i10) {
        super(i7, i10);
        this.f22191b = new Rect();
        this.c = true;
        this.d = false;
    }

    public C2368G(ViewGroup.MarginLayoutParams marginLayoutParams) {
        super(marginLayoutParams);
        this.f22191b = new Rect();
        this.c = true;
        this.d = false;
    }

    public C2368G(ViewGroup.LayoutParams layoutParams) {
        super(layoutParams);
        this.f22191b = new Rect();
        this.c = true;
        this.d = false;
    }

    public C2368G(C2368G c2368g) {
        super((ViewGroup.LayoutParams) c2368g);
        this.f22191b = new Rect();
        this.c = true;
        this.d = false;
    }
}
