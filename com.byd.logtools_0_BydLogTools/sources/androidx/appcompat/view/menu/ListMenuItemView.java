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
import androidx.appcompat.view.menu.n;
import androidx.appcompat.widget.e0;
import b.g.k.t;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f240b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageView f241c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RadioButton f242d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f243e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f244f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f245g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ImageView f246h;
    private ImageView i;
    private LinearLayout j;
    private Drawable k;
    private int l;
    private Context m;
    private boolean n;
    private Drawable o;
    private boolean p;
    private LayoutInflater q;
    private boolean r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, b.a.a.E);
    }

    private void a(View view) {
        b(view, -1);
    }

    private void b(View view, int i) {
        LinearLayout linearLayout = this.j;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    private void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(b.a.g.j, (ViewGroup) this, false);
        this.f244f = checkBox;
        a(checkBox);
    }

    private void f() {
        ImageView imageView = (ImageView) getInflater().inflate(b.a.g.k, (ViewGroup) this, false);
        this.f241c = imageView;
        b(imageView, 0);
    }

    private void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(b.a.g.m, (ViewGroup) this, false);
        this.f242d = radioButton;
        a(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.f246h;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.i.getLayoutParams();
        rect.top += this.i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void e(i iVar, int i) {
        this.f240b = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(iVar.i(this));
        setCheckable(iVar.isCheckable());
        h(iVar.z(), iVar.g());
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.getContentDescription());
    }

    @Override // androidx.appcompat.view.menu.n.a
    public i getItemData() {
        return this.f240b;
    }

    public void h(boolean z, char c2) {
        int i = (z && this.f240b.z()) ? 0 : 8;
        if (i == 0) {
            this.f245g.setText(this.f240b.h());
        }
        if (this.f245g.getVisibility() != i) {
            this.f245g.setVisibility(i);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        t.m0(this, this.k);
        TextView textView = (TextView) findViewById(b.a.f.S);
        this.f243e = textView;
        int i = this.l;
        if (i != -1) {
            textView.setTextAppearance(this.m, i);
        }
        this.f245g = (TextView) findViewById(b.a.f.L);
        ImageView imageView = (ImageView) findViewById(b.a.f.O);
        this.f246h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.i = (ImageView) findViewById(b.a.f.u);
        this.j = (LinearLayout) findViewById(b.a.f.m);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f241c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f241c.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.f242d == null && this.f244f == null) {
            return;
        }
        if (this.f240b.m()) {
            if (this.f242d == null) {
                g();
            }
            compoundButton = this.f242d;
            compoundButton2 = this.f244f;
        } else {
            if (this.f244f == null) {
                c();
            }
            compoundButton = this.f244f;
            compoundButton2 = this.f242d;
        }
        if (z) {
            compoundButton.setChecked(this.f240b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f244f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f242d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f240b.m()) {
            if (this.f242d == null) {
                g();
            }
            compoundButton = this.f242d;
        } else {
            if (this.f244f == null) {
                c();
            }
            compoundButton = this.f244f;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.n = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setVisibility((this.p || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f240b.y() || this.r;
        if (z || this.n) {
            ImageView imageView = this.f241c;
            if (imageView == null && drawable == null && !this.n) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.n) {
                this.f241c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f241c;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f241c.getVisibility() != 0) {
                this.f241c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f243e.getVisibility() != 8) {
                this.f243e.setVisibility(8);
            }
        } else {
            this.f243e.setText(charSequence);
            if (this.f243e.getVisibility() != 0) {
                this.f243e.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        e0 e0VarV = e0.v(getContext(), attributeSet, b.a.j.Y1, i, 0);
        this.k = e0VarV.g(b.a.j.a2);
        this.l = e0VarV.n(b.a.j.Z1, -1);
        this.n = e0VarV.a(b.a.j.b2, false);
        this.m = context;
        this.o = e0VarV.g(b.a.j.c2);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, b.a.a.B, 0);
        this.p = typedArrayObtainStyledAttributes.hasValue(0);
        e0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
