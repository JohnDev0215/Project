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
import androidx.appcompat.widget.f0;
import b.g.k.t;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private i f245b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private ImageView f246c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private RadioButton f247d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private TextView f248e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private CheckBox f249f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private TextView f250g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private ImageView f251h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private ImageView f252i;
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
        this(context, attributeSet, b.a.a.listMenuViewStyle);
    }

    private void a(View view) {
        b(view, -1);
    }

    private void b(View view, int i2) {
        LinearLayout linearLayout = this.j;
        if (linearLayout != null) {
            linearLayout.addView(view, i2);
        } else {
            addView(view, i2);
        }
    }

    private void c() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(b.a.g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f249f = checkBox;
        a(checkBox);
    }

    private void f() {
        ImageView imageView = (ImageView) getInflater().inflate(b.a.g.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.f246c = imageView;
        b(imageView, 0);
    }

    private void g() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(b.a.g.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f247d = radioButton;
        a(radioButton);
    }

    private LayoutInflater getInflater() {
        if (this.q == null) {
            this.q = LayoutInflater.from(getContext());
        }
        return this.q;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.f251h;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f252i;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f252i.getLayoutParams();
        rect.top += this.f252i.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public boolean d() {
        return false;
    }

    @Override // androidx.appcompat.view.menu.n.a
    public void e(i iVar, int i2) {
        this.f245b = iVar;
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
        return this.f245b;
    }

    public void h(boolean z, char c2) {
        int i2 = (z && this.f245b.z()) ? 0 : 8;
        if (i2 == 0) {
            this.f250g.setText(this.f245b.h());
        }
        if (this.f250g.getVisibility() != i2) {
            this.f250g.setVisibility(i2);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        t.m0(this, this.k);
        TextView textView = (TextView) findViewById(b.a.f.title);
        this.f248e = textView;
        int i2 = this.l;
        if (i2 != -1) {
            textView.setTextAppearance(this.m, i2);
        }
        this.f250g = (TextView) findViewById(b.a.f.shortcut);
        ImageView imageView = (ImageView) findViewById(b.a.f.submenuarrow);
        this.f251h = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.o);
        }
        this.f252i = (ImageView) findViewById(b.a.f.group_divider);
        this.j = (LinearLayout) findViewById(b.a.f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i2, int i3) {
        if (this.f246c != null && this.n) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f246c.getLayoutParams();
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
        if (!z && this.f247d == null && this.f249f == null) {
            return;
        }
        if (this.f245b.m()) {
            if (this.f247d == null) {
                g();
            }
            compoundButton = this.f247d;
            compoundButton2 = this.f249f;
        } else {
            if (this.f249f == null) {
                c();
            }
            compoundButton = this.f249f;
            compoundButton2 = this.f247d;
        }
        if (z) {
            compoundButton.setChecked(this.f245b.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f249f;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f247d;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f245b.m()) {
            if (this.f247d == null) {
                g();
            }
            compoundButton = this.f247d;
        } else {
            if (this.f249f == null) {
                c();
            }
            compoundButton = this.f249f;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.r = z;
        this.n = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.f252i;
        if (imageView != null) {
            imageView.setVisibility((this.p || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f245b.y() || this.r;
        if (z || this.n) {
            ImageView imageView = this.f246c;
            if (imageView == null && drawable == null && !this.n) {
                return;
            }
            if (imageView == null) {
                f();
            }
            if (drawable == null && !this.n) {
                this.f246c.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f246c;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f246c.getVisibility() != 0) {
                this.f246c.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f248e.getVisibility() != 8) {
                this.f248e.setVisibility(8);
            }
        } else {
            this.f248e.setText(charSequence);
            if (this.f248e.getVisibility() != 0) {
                this.f248e.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet);
        f0 f0VarV = f0.v(getContext(), attributeSet, b.a.j.MenuView, i2, 0);
        this.k = f0VarV.g(b.a.j.MenuView_android_itemBackground);
        this.l = f0VarV.n(b.a.j.MenuView_android_itemTextAppearance, -1);
        this.n = f0VarV.a(b.a.j.MenuView_preserveIconSpacing, false);
        this.m = context;
        this.o = f0VarV.g(b.a.j.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, b.a.a.dropDownListViewStyle, 0);
        this.p = typedArrayObtainStyledAttributes.hasValue(0);
        f0VarV.w();
        typedArrayObtainStyledAttributes.recycle();
    }
}
