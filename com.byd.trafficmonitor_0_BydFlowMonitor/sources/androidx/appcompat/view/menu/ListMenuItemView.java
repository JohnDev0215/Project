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
import b.h.m.t;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f337c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RadioButton f338d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f339e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CheckBox f340f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f341g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView f342h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageView f343i;
    public LinearLayout j;
    public Drawable k;
    public int l;
    public Context m;
    public boolean n;
    public Drawable o;
    public boolean p;
    public LayoutInflater q;
    public boolean r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, a.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.f342h;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public final void a(View view) {
        b(view, -1);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f343i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f343i.getLayoutParams();
        rect.top += this.f343i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final void b(View view, int i2) {
        LinearLayout linearLayout = this.j;
        if (linearLayout != null) {
            linearLayout.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    public final void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f340f = checkBox;
        a(checkBox);
    }

    @Override // b.b.p.j.n.a
    public boolean d() {
        return false;
    }

    @Override // b.b.p.j.n.a
    public void e(i iVar, int i2) {
        this.f336b = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(iVar.i(this));
        setCheckable(iVar.isCheckable());
        h(iVar.z(), iVar.g());
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.getContentDescription());
    }

    public final void f() {
        ImageView imageView = (ImageView) getInflater().inflate(g.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.f337c = imageView;
        b(imageView, 0);
    }

    public final void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(g.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f338d = radioButton;
        a(radioButton);
    }

    @Override // b.b.p.j.n.a
    public i getItemData() {
        return this.f336b;
    }

    public void h(boolean z, char c2) {
        int i2 = (z && this.f336b.z()) ? 0 : 8;
        if (i2 == 0) {
            this.f341g.setText(this.f336b.h());
        }
        if (this.f341g.getVisibility() != i2) {
            this.f341g.setVisibility(i2);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        t.n0(this, this.k);
        TextView textView = (TextView) findViewById(f.title);
        this.f339e = textView;
        int i2 = this.l;
        if (i2 != -1) {
            textView.setTextAppearance(this.m, i2);
        }
        this.f341g = (TextView) findViewById(f.shortcut);
        ImageView imageView = (ImageView) findViewById(f.submenuarrow);
        this.f342h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.f343i = (ImageView) findViewById(f.group_divider);
        this.j = (LinearLayout) findViewById(f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i2, int i3) {
        if (this.f337c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f337c.getLayoutParams();
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
        if (!z && this.f338d == null && this.f340f == null) {
            return;
        }
        if (this.f336b.m()) {
            if (this.f338d == null) {
                g();
            }
            compoundButton = this.f338d;
            compoundButton2 = this.f340f;
        } else {
            if (this.f340f == null) {
                c();
            }
            compoundButton = this.f340f;
            compoundButton2 = this.f338d;
        }
        if (z) {
            compoundButton.setChecked(this.f336b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f340f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f338d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f336b.m()) {
            if (this.f338d == null) {
                g();
            }
            compoundButton = this.f338d;
        } else {
            if (this.f340f == null) {
                c();
            }
            compoundButton = this.f340f;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.n = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.f343i;
        if (imageView != null) {
            imageView.setVisibility((this.p || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f336b.y() || this.r;
        if (z || this.n) {
            ImageView imageView = this.f337c;
            if (imageView == null && drawable == null && !this.n) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.n) {
                this.f337c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f337c;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f337c.getVisibility() != 0) {
                this.f337c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f339e.getVisibility() != 8) {
                this.f339e.setVisibility(8);
            }
        } else {
            this.f339e.setText(charSequence);
            if (this.f339e.getVisibility() != 0) {
                this.f339e.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        e0 e0VarV = e0.v(getContext(), attributeSet, j.MenuView, i2, 0);
        this.k = e0VarV.g(j.MenuView_android_itemBackground);
        this.l = e0VarV.n(j.MenuView_android_itemTextAppearance, -1);
        this.n = e0VarV.a(j.MenuView_preserveIconSpacing, false);
        this.m = context;
        this.o = e0VarV.g(j.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.dropDownListViewStyle, 0);
        this.p = typedArrayObtainStyledAttributes.hasValue(0);
        e0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
