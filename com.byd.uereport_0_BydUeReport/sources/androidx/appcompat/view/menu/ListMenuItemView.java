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
import o0.t;
import q.i;
import q.n;
import r.e0;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public i f410d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ImageView f411e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public RadioButton f412f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public TextView f413g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public CheckBox f414h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public TextView f415i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public ImageView f416j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public ImageView f417k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public LinearLayout f418l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Drawable f419m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f420n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public Context f421o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f422p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Drawable f423q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public boolean f424r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public LayoutInflater f425s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f426t;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.f425s == null) {
            this.f425s = LayoutInflater.from(getContext());
        }
        return this.f425s;
    }

    private void setSubMenuArrowVisible(boolean z7) {
        ImageView imageView = this.f416j;
        if (imageView != null) {
            imageView.setVisibility(z7 ? 0 : 8);
        }
    }

    public final void a(View view) {
        b(view, -1);
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f417k;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f417k.getLayoutParams();
        rect.top += this.f417k.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    public final void b(View view, int i8) {
        LinearLayout linearLayout = this.f418l;
        if (linearLayout != null) {
            linearLayout.addView(view, i8);
        } else {
            addView(view, i8);
        }
    }

    public final void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f414h = checkBox;
        a(checkBox);
    }

    @Override // q.n.a
    public boolean d() {
        return false;
    }

    @Override // q.n.a
    public void e(i iVar, int i8) {
        this.f410d = iVar;
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
        this.f411e = imageView;
        b(imageView, 0);
    }

    public final void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f412f = radioButton;
        a(radioButton);
    }

    @Override // q.n.a
    public i getItemData() {
        return this.f410d;
    }

    public void h(boolean z7, char c8) {
        int i8 = (z7 && this.f410d.z()) ? 0 : 8;
        if (i8 == 0) {
            this.f415i.setText(this.f410d.h());
        }
        if (this.f415i.getVisibility() != i8) {
            this.f415i.setVisibility(i8);
        }
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        t.o0(this, this.f419m);
        TextView textView = (TextView) findViewById(R$id.title);
        this.f413g = textView;
        int i8 = this.f420n;
        if (i8 != -1) {
            textView.setTextAppearance(this.f421o, i8);
        }
        this.f415i = (TextView) findViewById(R$id.shortcut);
        ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
        this.f416j = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f423q);
        }
        this.f417k = (ImageView) findViewById(R$id.group_divider);
        this.f418l = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i8, int i9) {
        if (this.f411e != null && this.f422p) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f411e.getLayoutParams();
            int i10 = layoutParams.height;
            if (i10 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i10;
            }
        }
        super.onMeasure(i8, i9);
    }

    public void setCheckable(boolean z7) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z7 && this.f412f == null && this.f414h == null) {
            return;
        }
        if (this.f410d.m()) {
            if (this.f412f == null) {
                g();
            }
            compoundButton = this.f412f;
            compoundButton2 = this.f414h;
        } else {
            if (this.f414h == null) {
                c();
            }
            compoundButton = this.f414h;
            compoundButton2 = this.f412f;
        }
        if (z7) {
            compoundButton.setChecked(this.f410d.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f414h;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f412f;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z7) {
        CompoundButton compoundButton;
        if (this.f410d.m()) {
            if (this.f412f == null) {
                g();
            }
            compoundButton = this.f412f;
        } else {
            if (this.f414h == null) {
                c();
            }
            compoundButton = this.f414h;
        }
        compoundButton.setChecked(z7);
    }

    public void setForceShowIcon(boolean z7) {
        this.f426t = z7;
        this.f422p = z7;
    }

    public void setGroupDividerEnabled(boolean z7) {
        ImageView imageView = this.f417k;
        if (imageView != null) {
            imageView.setVisibility((this.f424r || !z7) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z7 = this.f410d.y() || this.f426t;
        if (z7 || this.f422p) {
            ImageView imageView = this.f411e;
            if (imageView == null && drawable == null && !this.f422p) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.f422p) {
                this.f411e.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f411e;
            if (!z7) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f411e.getVisibility() != 0) {
                this.f411e.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f413g.getVisibility() != 8) {
                this.f413g.setVisibility(8);
            }
        } else {
            this.f413g.setText(charSequence);
            if (this.f413g.getVisibility() != 0) {
                this.f413g.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet);
        e0 e0VarV = e0.v(getContext(), attributeSet, R$styleable.MenuView, i8, 0);
        this.f419m = e0VarV.g(R$styleable.MenuView_android_itemBackground);
        this.f420n = e0VarV.n(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.f422p = e0VarV.a(R$styleable.MenuView_preserveIconSpacing, false);
        this.f421o = context;
        this.f423q = e0VarV.g(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.f424r = typedArrayObtainStyledAttributes.hasValue(0);
        e0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
