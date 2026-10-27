package p;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertController$RecycleListView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.encoders.proto.Protobuf$IntEncoding;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public final class j6 implements p3 {
    public int c;
    public Object e;

    public /* synthetic */ j6(int i, Serializable serializable) {
        this.c = i;
        this.e = serializable;
    }

    public static j6 c() {
        j6 j6Var = new j6();
        j6Var.e = Protobuf$IntEncoding.c;
        return j6Var;
    }

    @Override // p.p3
    public final boolean a(View view) {
        ((BottomSheetBehavior) this.e).z(this.c);
        return true;
    }

    public final vc b() {
        return new vc(this.c, (Protobuf$IntEncoding) this.e);
    }

    public final k6 d() {
        g6 g6Var = (g6) this.e;
        k6 k6Var = new k6(g6Var.a, this.c);
        View view = g6Var.e;
        i6 i6Var = k6Var.n;
        int i = 0;
        if (view != null) {
            i6Var.o = view;
        } else {
            CharSequence charSequence = g6Var.d;
            if (charSequence != null) {
                i6Var.d = charSequence;
                TextView textView = i6Var.m;
                if (textView != null) {
                    textView.setText(charSequence);
                }
            }
            Drawable drawable = g6Var.c;
            if (drawable != null) {
                i6Var.k = drawable;
                i6Var.j = 0;
                ImageView imageView = i6Var.l;
                if (imageView != null) {
                    imageView.setVisibility(0);
                    i6Var.l.setImageDrawable(drawable);
                }
            }
        }
        if (g6Var.g != null) {
            AlertController$RecycleListView alertController$RecycleListView = (AlertController$RecycleListView) g6Var.b.inflate(i6Var.s, (ViewGroup) null);
            int i2 = g6Var.i ? i6Var.t : i6Var.u;
            ListAdapter h6Var = g6Var.g;
            if (h6Var == null) {
                h6Var = new h6(g6Var.a, i2);
            }
            i6Var.f131p = h6Var;
            i6Var.q = g6Var.j;
            if (g6Var.h != null) {
                alertController$RecycleListView.setOnItemClickListener(new f6(g6Var, i, i6Var));
            }
            if (g6Var.i) {
                alertController$RecycleListView.setChoiceMode(1);
            }
            i6Var.e = alertController$RecycleListView;
        }
        g6Var.getClass();
        k6Var.setCancelable(true);
        g6Var.getClass();
        k6Var.setCanceledOnTouchOutside(true);
        g6Var.getClass();
        k6Var.setOnCancelListener(null);
        g6Var.getClass();
        k6Var.setOnDismissListener(null);
        DialogInterface.OnKeyListener onKeyListener = g6Var.f;
        if (onKeyListener != null) {
            k6Var.setOnKeyListener(onKeyListener);
        }
        return k6Var;
    }

    public /* synthetic */ j6(int i, Object obj) {
        this.e = obj;
        this.c = i;
    }

    public j6(Context context) {
        int iR = k6.r(context, 0);
        this.e = new g6(new ContextThemeWrapper(context, k6.r(context, iR)));
        this.c = iR;
    }
}
