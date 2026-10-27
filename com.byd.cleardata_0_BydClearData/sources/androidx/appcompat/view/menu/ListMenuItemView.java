package androidx.appcompat.view.menu;

import a.g.k.t;
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
import androidx.appcompat.widget.v0;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f779b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageView f780c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RadioButton f781d;
    private TextView e;
    private CheckBox f;
    private TextView g;
    private ImageView h;
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
        this(context, attributeSet, a.a.a.C);
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        v0 v0VarV = v0.v(getContext(), attributeSet, a.a.j.W1, i, 0);
        this.k = v0VarV.g(a.a.j.Y1);
        this.l = v0VarV.n(a.a.j.X1, -1);
        this.n = v0VarV.a(a.a.j.Z1, false);
        this.m = context;
        this.o = v0VarV.g(a.a.j.a2);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.a.a.z, 0);
        this.p = typedArrayObtainStyledAttributes.hasValue(0);
        v0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
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
        CheckBox checkBox = (CheckBox) getInflater().inflate(a.a.g.h, (ViewGroup) this, false);
        this.f = checkBox;
        a(checkBox);
    }

    private void f() {
        ImageView imageView = (ImageView) getInflater().inflate(a.a.g.i, (ViewGroup) this, false);
        this.f780c = imageView;
        b(imageView, 0);
    }

    private void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(a.a.g.k, (ViewGroup) this, false);
        this.f781d = radioButton;
        a(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.h;
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
        this.f779b = iVar;
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
        return this.f779b;
    }

    public void h(boolean z, char c2) {
        int i = (z && this.f779b.z()) ? 0 : 8;
        if (i == 0) {
            this.g.setText(this.f779b.h());
        }
        if (this.g.getVisibility() != i) {
            this.g.setVisibility(i);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        t.k0(this, this.k);
        TextView textView = (TextView) findViewById(a.a.f.M);
        this.e = textView;
        int i = this.l;
        if (i != -1) {
            textView.setTextAppearance(this.m, i);
        }
        this.g = (TextView) findViewById(a.a.f.F);
        ImageView imageView = (ImageView) findViewById(a.a.f.I);
        this.h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.i = (ImageView) findViewById(a.a.f.r);
        this.j = (LinearLayout) findViewById(a.a.f.l);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f780c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f780c.getLayoutParams();
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
        if (!z && this.f781d == null && this.f == null) {
            return;
        }
        if (this.f779b.m()) {
            if (this.f781d == null) {
                g();
            }
            compoundButton = this.f781d;
            compoundButton2 = this.f;
        } else {
            if (this.f == null) {
                c();
            }
            compoundButton = this.f;
            compoundButton2 = this.f781d;
        }
        if (z) {
            compoundButton.setChecked(this.f779b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f781d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f779b.m()) {
            if (this.f781d == null) {
                g();
            }
            compoundButton = this.f781d;
        } else {
            if (this.f == null) {
                c();
            }
            compoundButton = this.f;
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
        boolean z = this.f779b.y() || this.r;
        if (z || this.n) {
            ImageView imageView = this.f780c;
            if (imageView == null && drawable == null && !this.n) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.n) {
                this.f780c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f780c;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f780c.getVisibility() != 0) {
                this.f780c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        int i;
        TextView textView;
        if (charSequence != null) {
            this.e.setText(charSequence);
            if (this.e.getVisibility() == 0) {
                return;
            }
            textView = this.e;
            i = 0;
        } else {
            i = 8;
            if (this.e.getVisibility() == 8) {
                return;
            } else {
                textView = this.e;
            }
        }
        textView.setVisibility(i);
    }
}
