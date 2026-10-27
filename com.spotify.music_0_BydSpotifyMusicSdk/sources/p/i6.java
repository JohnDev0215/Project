package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import com.spotify.music.R;

/* JADX INFO: loaded from: classes.dex */
public final class i6 {
    public final Context a;
    public final k6 b;
    public final Window c;
    public CharSequence d;
    public AlertController$RecycleListView e;
    public Button f;
    public Button g;
    public Button h;
    public NestedScrollView i;
    public Drawable k;
    public ImageView l;
    public TextView m;
    public TextView n;
    public View o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public ListAdapter f131p;
    public final int r;
    public final int s;
    public final int t;
    public final int u;
    public final boolean v;
    public final rh1 w;
    public int j = 0;
    public int q = -1;
    public final e6 x = new e6(0, this);

    public i6(Context context, k6 k6Var, Window window) {
        this.a = context;
        this.b = k6Var;
        this.c = window;
        this.w = new rh1(k6Var);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, yz1.e, R.attr.alertDialogStyle, 0);
        this.r = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.s = typedArrayObtainStyledAttributes.getResourceId(4, 0);
        typedArrayObtainStyledAttributes.getResourceId(5, 0);
        this.t = typedArrayObtainStyledAttributes.getResourceId(7, 0);
        this.u = typedArrayObtainStyledAttributes.getResourceId(3, 0);
        this.v = typedArrayObtainStyledAttributes.getBoolean(6, true);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(1, 0);
        typedArrayObtainStyledAttributes.recycle();
        k6Var.m().f(1);
    }

    public static void a(View view, View view2, View view3) {
        if (view2 != null) {
            view2.setVisibility(view.canScrollVertically(-1) ? 0 : 4);
        }
        if (view3 != null) {
            view3.setVisibility(view.canScrollVertically(1) ? 0 : 4);
        }
    }

    public static ViewGroup b(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }
}
