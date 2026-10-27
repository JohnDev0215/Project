package b.b.a.a;

import android.view.View;
import androidx.appcompat.widget.ActivityChooserView;
import com.google.android.flexbox.FlexItem;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public float j;
    public float k;
    public int l;
    public int m;
    public int o;
    public int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f952a = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f953b = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f954c = Integer.MIN_VALUE;
    public int d = Integer.MIN_VALUE;
    public List<Integer> n = new ArrayList();

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.h - this.i;
    }

    public void d(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.f952a = Math.min(this.f952a, (view.getLeft() - flexItem.i()) - i);
        this.f953b = Math.min(this.f953b, (view.getTop() - flexItem.m()) - i2);
        this.f954c = Math.max(this.f954c, view.getRight() + flexItem.a() + i3);
        this.d = Math.max(this.d, view.getBottom() + flexItem.g() + i4);
    }
}
