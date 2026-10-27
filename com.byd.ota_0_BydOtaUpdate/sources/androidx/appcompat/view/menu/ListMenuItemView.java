package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import b.b.a;
import b.b.f;
import b.b.g;
import b.b.j;
import b.b.p.j.i;
import b.b.p.j.n;
import b.b.q.e0;
import b.f.m.s;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {
    public TextView E3;
    public ImageView F3;
    public ImageView G3;
    public LinearLayout H3;
    public Drawable I3;
    public int J3;
    public Context K3;
    public boolean L3;
    public Drawable M3;
    public boolean N3;
    public LayoutInflater O3;
    public boolean P3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public i f153c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ImageView f154d;
    public RadioButton s;
    public TextView x;
    public CheckBox y;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.D);
    }

    private LayoutInflater getInflater() {
        if (this.O3 == null) {
            this.O3 = LayoutInflater.from(getContext());
        }
        return this.O3;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.F3;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public final void a(View view) {
        b(view, -1);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.G3;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.G3.getLayoutParams();
        rect.top += this.G3.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final void b(View view, int i2) {
        LinearLayout linearLayout = this.H3;
        if (linearLayout != null) {
            linearLayout.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    @Override // b.b.p.j.n.a
    public boolean c() {
        return false;
    }

    @Override // b.b.p.j.n.a
    public void d(i iVar, int i2) {
        this.f153c = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(iVar.i(this));
        setCheckable(iVar.isCheckable());
        h(iVar.z(), iVar.g());
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.getContentDescription());
    }

    public final void e() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(g.j, (ViewGroup) this, false);
        this.y = checkBox;
        a(checkBox);
    }

    public final void f() {
        ImageView imageView = (ImageView) getInflater().inflate(g.k, (ViewGroup) this, false);
        this.f154d = imageView;
        b(imageView, 0);
    }

    public final void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(g.m, (ViewGroup) this, false);
        this.s = radioButton;
        a(radioButton);
    }

    @Override // b.b.p.j.n.a
    public i getItemData() {
        return this.f153c;
    }

    public void h(boolean z, char c2) {
        int i2 = (z && this.f153c.z()) ? 0 : 8;
        if (i2 == 0) {
            this.E3.setText(this.f153c.h());
        }
        if (this.E3.getVisibility() != i2) {
            this.E3.setVisibility(i2);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        s.U(this, this.I3);
        TextView textView = (TextView) findViewById(f.S);
        this.x = textView;
        int i2 = this.J3;
        if (i2 != -1) {
            textView.setTextAppearance(this.K3, i2);
        }
        this.E3 = (TextView) findViewById(f.L);
        ImageView imageView = (ImageView) findViewById(f.O);
        this.F3 = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.M3);
        }
        this.G3 = (ImageView) findViewById(f.u);
        this.H3 = (LinearLayout) findViewById(f.m);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.f154d != null && this.L3) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f154d.getLayoutParams();
            int i4 = layoutParams.height;
            if (i4 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i4;
            }
        }
        super.onMeasure(i2, i3);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.s == null && this.y == null) {
            return;
        }
        if (this.f153c.m()) {
            if (this.s == null) {
                g();
            }
            compoundButton = this.s;
            compoundButton2 = this.y;
        } else {
            if (this.y == null) {
                e();
            }
            compoundButton = this.y;
            compoundButton2 = this.s;
        }
        if (z) {
            compoundButton.setChecked(this.f153c.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.y;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.s;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f153c.m()) {
            if (this.s == null) {
                g();
            }
            compoundButton = this.s;
        } else {
            if (this.y == null) {
                e();
            }
            compoundButton = this.y;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.P3 = z;
        this.L3 = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.G3;
        if (imageView != null) {
            imageView.setVisibility((this.N3 || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f153c.y() || this.P3;
        if (z || this.L3) {
            ImageView imageView = this.f154d;
            if (imageView == null && drawable == null && !this.L3) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.L3) {
                this.f154d.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f154d;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f154d.getVisibility() != 0) {
                this.f154d.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.x.getVisibility() != 8) {
                this.x.setVisibility(8);
            }
        } else {
            this.x.setText(charSequence);
            if (this.x.getVisibility() != 0) {
                this.x.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        e0 e0VarU = e0.u(getContext(), attributeSet, j.Y1, i2, 0);
        this.I3 = e0VarU.f(j.a2);
        this.J3 = e0VarU.m(j.Z1, -1);
        this.L3 = e0VarU.a(j.b2, false);
        this.K3 = context;
        this.M3 = e0VarU.f(j.c2);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.A, 0);
        this.N3 = typedArrayObtainStyledAttributes.hasValue(0);
        e0VarU.v();
        typedArrayObtainStyledAttributes.recycle();
    }
}
