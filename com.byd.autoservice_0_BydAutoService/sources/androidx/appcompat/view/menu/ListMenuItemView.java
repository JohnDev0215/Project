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
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import l0.t;
import n.i;
import n.n;
import o.e0;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public i f303b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ImageView f304c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public RadioButton f305d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public TextView f306e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CheckBox f307f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f308g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ImageView f309h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public ImageView f310i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public LinearLayout f311j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Drawable f312k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f313l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Context f314m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f315n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Drawable f316o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f317p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public LayoutInflater f318q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f319r;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.f318q == null) {
            this.f318q = LayoutInflater.from(getContext());
        }
        return this.f318q;
    }

    private void setSubMenuArrowVisible(boolean z3) {
        ImageView imageView = this.f309h;
        if (imageView != null) {
            imageView.setVisibility(z3 ? 0 : 8);
        }
    }

    public final void a(View view) {
        b(view, -1);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f310i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f310i.getLayoutParams();
        rect.top += this.f310i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final void b(View view, int i3) {
        LinearLayout linearLayout = this.f311j;
        if (linearLayout != null) {
            linearLayout.addView(view, i3);
        } else {
            addView(view, i3);
        }
    }

    public final void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f307f = checkBox;
        a(checkBox);
    }

    @Override // n.n.a
    public boolean d() {
        return false;
    }

    @Override // n.n.a
    public void e(i iVar, int i3) {
        this.f303b = iVar;
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
        ImageView imageView = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.f304c = imageView;
        b(imageView, 0);
    }

    public final void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f305d = radioButton;
        a(radioButton);
    }

    @Override // n.n.a
    public i getItemData() {
        return this.f303b;
    }

    public void h(boolean z3, char c3) {
        int i3 = (z3 && this.f303b.z()) ? 0 : 8;
        if (i3 == 0) {
            this.f308g.setText(this.f303b.h());
        }
        if (this.f308g.getVisibility() != i3) {
            this.f308g.setVisibility(i3);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        t.m0(this, this.f312k);
        TextView textView = (TextView) findViewById(R$id.title);
        this.f306e = textView;
        int i3 = this.f313l;
        if (i3 != -1) {
            textView.setTextAppearance(this.f314m, i3);
        }
        this.f308g = (TextView) findViewById(R$id.shortcut);
        ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
        this.f309h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f316o);
        }
        this.f310i = (ImageView) findViewById(R$id.group_divider);
        this.f311j = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i3, int i4) {
        if (this.f304c != null && this.f315n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f304c.getLayoutParams();
            int i5 = layoutParams.height;
            if (i5 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i5;
            }
        }
        super.onMeasure(i3, i4);
    }

    public void setCheckable(boolean z3) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z3 && this.f305d == null && this.f307f == null) {
            return;
        }
        if (this.f303b.m()) {
            if (this.f305d == null) {
                g();
            }
            compoundButton = this.f305d;
            compoundButton2 = this.f307f;
        } else {
            if (this.f307f == null) {
                c();
            }
            compoundButton = this.f307f;
            compoundButton2 = this.f305d;
        }
        if (z3) {
            compoundButton.setChecked(this.f303b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f307f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f305d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z3) {
        CompoundButton compoundButton;
        if (this.f303b.m()) {
            if (this.f305d == null) {
                g();
            }
            compoundButton = this.f305d;
        } else {
            if (this.f307f == null) {
                c();
            }
            compoundButton = this.f307f;
        }
        compoundButton.setChecked(z3);
    }

    public void setForceShowIcon(boolean z3) {
        this.f319r = z3;
        this.f315n = z3;
    }

    public void setGroupDividerEnabled(boolean z3) {
        ImageView imageView = this.f310i;
        if (imageView != null) {
            imageView.setVisibility((this.f317p || !z3) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z3 = this.f303b.y() || this.f319r;
        if (z3 || this.f315n) {
            ImageView imageView = this.f304c;
            if (imageView == null && drawable == null && !this.f315n) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.f315n) {
                this.f304c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f304c;
            if (!z3) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f304c.getVisibility() != 0) {
                this.f304c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f306e.getVisibility() != 8) {
                this.f306e.setVisibility(8);
            }
        } else {
            this.f306e.setText(charSequence);
            if (this.f306e.getVisibility() != 0) {
                this.f306e.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i3) {
        super(context, attributeSet);
        e0 e0VarV = e0.v(getContext(), attributeSet, R$styleable.MenuView, i3, 0);
        this.f312k = e0VarV.g(R$styleable.MenuView_android_itemBackground);
        this.f313l = e0VarV.n(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.f315n = e0VarV.a(R$styleable.MenuView_preserveIconSpacing, false);
        this.f314m = context;
        this.f316o = e0VarV.g(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.f317p = typedArrayObtainStyledAttributes.hasValue(0);
        e0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
