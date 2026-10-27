package p;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.spotify.music.R;
import java.util.HashMap;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class a02 extends ConstraintLayout {
    public final qf1 A;
    public final zz1 y;
    public int z;

    public a02(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.materialClockStyle);
        LayoutInflater.from(context).inflate(R.layout.material_radial_view_group, this);
        qf1 qf1Var = new qf1();
        this.A = qf1Var;
        w22 w22Var = new w22(0.5f);
        wg wgVarE = qf1Var.c.a.e();
        wgVarE.e = w22Var;
        wgVarE.f = w22Var;
        wgVarE.g = w22Var;
        wgVarE.h = w22Var;
        qf1Var.setShapeAppearanceModel(wgVarE.a());
        this.A.k(ColorStateList.valueOf(-1));
        qf1 qf1Var2 = this.A;
        WeakHashMap weakHashMap = zt2.a;
        it2.q(this, qf1Var2);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.u, R.attr.materialClockStyle, 0);
        this.z = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, 0);
        this.y = new zz1(this);
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        super.addView(view, i, layoutParams);
        if (view.getId() == -1) {
            WeakHashMap weakHashMap = zt2.a;
            view.setId(jt2.a());
        }
        Handler handler = getHandler();
        if (handler != null) {
            zz1 zz1Var = this.y;
            handler.removeCallbacks(zz1Var);
            handler.post(zz1Var);
        }
    }

    public final void n() {
        int childCount = getChildCount();
        int i = 1;
        for (int i2 = 0; i2 < childCount; i2++) {
            if ("skip".equals(getChildAt(i2).getTag())) {
                i++;
            }
        }
        py pyVar = new py();
        pyVar.b(this);
        float f = 0.0f;
        for (int i3 = 0; i3 < childCount; i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getId() != R.id.circle_center && !"skip".equals(childAt.getTag())) {
                int id = childAt.getId();
                int i4 = this.z;
                HashMap map = pyVar.c;
                if (!map.containsKey(Integer.valueOf(id))) {
                    map.put(Integer.valueOf(id), new ky());
                }
                ly lyVar = ((ky) map.get(Integer.valueOf(id))).d;
                lyVar.z = R.id.circle_center;
                lyVar.A = i4;
                lyVar.B = f;
                f = (360.0f / (childCount - i)) + f;
            }
        }
        pyVar.a(this);
        setConstraintSet(null);
        requestLayout();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        n();
    }

    @Override // androidx.constraintlayout.widget.ConstraintLayout, android.view.ViewGroup
    public final void onViewRemoved(View view) {
        super.onViewRemoved(view);
        Handler handler = getHandler();
        if (handler != null) {
            zz1 zz1Var = this.y;
            handler.removeCallbacks(zz1Var);
            handler.post(zz1Var);
        }
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        this.A.k(ColorStateList.valueOf(i));
    }
}
