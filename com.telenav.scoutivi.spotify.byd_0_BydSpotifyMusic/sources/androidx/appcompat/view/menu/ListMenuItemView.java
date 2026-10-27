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
import androidx.appcompat.view.menu.k;
import androidx.appcompat.widget.s0;
import i0.u;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements k.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private g f588e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private ImageView f589g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private RadioButton f590h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private TextView f591i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private CheckBox f592j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private TextView f593k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private ImageView f594l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private ImageView f595m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private LinearLayout f596n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private Drawable f597o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f598p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private Context f599q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private boolean f600r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    private Drawable f601s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    private boolean f602t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    private LayoutInflater f603u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    private boolean f604v;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, e.a.listMenuViewStyle);
    }

    private void a(View view) {
        b(view, -1);
    }

    private void b(View view, int i7) {
        LinearLayout linearLayout = this.f596n;
        if (linearLayout != null) {
            linearLayout.addView(view, i7);
        } else {
            addView(view, i7);
        }
    }

    private void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(e.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f592j = checkBox;
        a(checkBox);
    }

    private void f() {
        ImageView imageView = (ImageView) getInflater().inflate(e.g.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.f589g = imageView;
        b(imageView, 0);
    }

    private void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(e.g.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f590h = radioButton;
        a(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.f603u == null) {
            this.f603u = LayoutInflater.from(getContext());
        }
        return this.f603u;
    }

    private void setSubMenuArrowVisible(boolean z6) {
        ImageView imageView = this.f594l;
        if (imageView != null) {
            imageView.setVisibility(z6 ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f595m;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f595m.getLayoutParams();
        rect.top += this.f595m.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.k.a
    public void e(g gVar, int i7) {
        this.f588e = gVar;
        setVisibility(gVar.isVisible() ? 0 : 8);
        setTitle(gVar.i(this));
        setCheckable(gVar.isCheckable());
        h(gVar.A(), gVar.g());
        setIcon(gVar.getIcon());
        setEnabled(gVar.isEnabled());
        setSubMenuArrowVisible(gVar.hasSubMenu());
        setContentDescription(gVar.getContentDescription());
    }

    @Override // androidx.appcompat.view.menu.k.a
    public g getItemData() {
        return this.f588e;
    }

    public void h(boolean z6, char c7) {
        int i7 = (z6 && this.f588e.A()) ? 0 : 8;
        if (i7 == 0) {
            this.f593k.setText(this.f588e.h());
        }
        if (this.f593k.getVisibility() != i7) {
            this.f593k.setVisibility(i7);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        u.n0(this, this.f597o);
        TextView textView = (TextView) findViewById(e.f.title);
        this.f591i = textView;
        int i7 = this.f598p;
        if (i7 != -1) {
            textView.setTextAppearance(this.f599q, i7);
        }
        this.f593k = (TextView) findViewById(e.f.shortcut);
        ImageView imageView = (ImageView) findViewById(e.f.submenuarrow);
        this.f594l = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f601s);
        }
        this.f595m = (ImageView) findViewById(e.f.group_divider);
        this.f596n = (LinearLayout) findViewById(e.f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i7, int i8) {
        if (this.f589g != null && this.f600r) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f589g.getLayoutParams();
            int i9 = layoutParams.height;
            if (i9 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i9;
            }
        }
        super.onMeasure(i7, i8);
    }

    public void setCheckable(boolean z6) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z6 && this.f590h == null && this.f592j == null) {
            return;
        }
        if (this.f588e.m()) {
            if (this.f590h == null) {
                g();
            }
            compoundButton = this.f590h;
            compoundButton2 = this.f592j;
        } else {
            if (this.f592j == null) {
                c();
            }
            compoundButton = this.f592j;
            compoundButton2 = this.f590h;
        }
        if (z6) {
            compoundButton.setChecked(this.f588e.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f592j;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f590h;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z6) {
        CompoundButton compoundButton;
        if (this.f588e.m()) {
            if (this.f590h == null) {
                g();
            }
            compoundButton = this.f590h;
        } else {
            if (this.f592j == null) {
                c();
            }
            compoundButton = this.f592j;
        }
        compoundButton.setChecked(z6);
    }

    public void setForceShowIcon(boolean z6) {
        this.f604v = z6;
        this.f600r = z6;
    }

    public void setGroupDividerEnabled(boolean z6) {
        ImageView imageView = this.f595m;
        if (imageView != null) {
            imageView.setVisibility((this.f602t || !z6) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z6 = this.f588e.z() || this.f604v;
        if (z6 || this.f600r) {
            ImageView imageView = this.f589g;
            if (imageView == null && drawable == null && !this.f600r) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.f600r) {
                this.f589g.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f589g;
            if (!z6) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f589g.getVisibility() != 0) {
                this.f589g.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f591i.getVisibility() != 8) {
                this.f591i.setVisibility(8);
            }
        } else {
            this.f591i.setText(charSequence);
            if (this.f591i.getVisibility() != 0) {
                this.f591i.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet);
        s0 s0VarV = s0.v(getContext(), attributeSet, e.j.MenuView, i7, 0);
        this.f597o = s0VarV.g(e.j.MenuView_android_itemBackground);
        this.f598p = s0VarV.n(e.j.MenuView_android_itemTextAppearance, -1);
        this.f600r = s0VarV.a(e.j.MenuView_preserveIconSpacing, false);
        this.f599q = context;
        this.f601s = s0VarV.g(e.j.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, e.a.dropDownListViewStyle, 0);
        this.f602t = typedArrayObtainStyledAttributes.hasValue(0);
        s0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
