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
import androidx.annotation.RestrictTo;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.m;
import androidx.appcompat.widget.U;
import androidx.core.view.ViewCompat;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
public class ListMenuItemView extends LinearLayout implements m.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private h f211c;
    private ImageView d;
    private RadioButton e;
    private TextView f;
    private CheckBox g;
    private TextView h;
    private ImageView i;
    private ImageView j;
    private LinearLayout k;
    private Drawable l;
    private int m;
    private Context n;
    private boolean o;
    private Drawable p;
    private boolean q;
    private LayoutInflater r;
    private boolean s;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private void a(View view) {
        c(view, -1);
    }

    private void c(View view, int i) {
        LinearLayout linearLayout = this.k;
        if (linearLayout != null) {
            linearLayout.addView(view, i);
        } else {
            addView(view, i);
        }
    }

    private LayoutInflater e() {
        if (this.r == null) {
            this.r = LayoutInflater.from(getContext());
        }
        return this.r;
    }

    private void f() {
        CheckBox checkBox = (CheckBox) e().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.g = checkBox;
        a(checkBox);
    }

    private void g() {
        ImageView imageView = (ImageView) e().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup) this, false);
        this.d = imageView;
        c(imageView, 0);
    }

    private void h() {
        RadioButton radioButton = (RadioButton) e().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.e = radioButton;
        a(radioButton);
    }

    private void o(boolean z) {
        ImageView imageView = this.i;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.j;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.j.getLayoutParams();
        rect.top += this.j.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin;
    }

    @Override // androidx.appcompat.view.menu.m.a
    public h b() {
        return this.f211c;
    }

    @Override // androidx.appcompat.view.menu.m.a
    public boolean d() {
        return false;
    }

    public void i(boolean z) {
        CompoundButton compoundButton;
        CompoundButton compoundButton2;
        if (!z && this.e == null && this.g == null) {
            return;
        }
        if (this.f211c.l()) {
            if (this.e == null) {
                h();
            }
            compoundButton = this.e;
            compoundButton2 = this.g;
        } else {
            if (this.g == null) {
                f();
            }
            compoundButton = this.g;
            compoundButton2 = this.e;
        }
        if (z) {
            compoundButton.setChecked(this.f211c.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.g;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.e;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    @Override // androidx.appcompat.view.menu.m.a
    public void j(h hVar, int i) {
        this.f211c = hVar;
        setVisibility(hVar.isVisible() ? 0 : 8);
        p(hVar.h(this));
        i(hVar.isCheckable());
        n(hVar.y(), hVar.f());
        m(hVar.getIcon());
        setEnabled(hVar.isEnabled());
        o(hVar.hasSubMenu());
        setContentDescription(hVar.getContentDescription());
    }

    public void k(boolean z) {
        this.s = z;
        this.o = z;
    }

    public void l(boolean z) {
        ImageView imageView = this.j;
        if (imageView != null) {
            imageView.setVisibility((this.q || !z) ? 8 : 0);
        }
    }

    public void m(Drawable drawable) {
        boolean z = this.f211c.x() || this.s;
        if (z || this.o) {
            if (this.d == null && drawable == null && !this.o) {
                return;
            }
            if (this.d == null) {
                g();
            }
            if (drawable == null && !this.o) {
                this.d.setVisibility(8);
                return;
            }
            ImageView imageView = this.d;
            if (!z) {
                drawable = null;
            }
            imageView.setImageDrawable(drawable);
            if (this.d.getVisibility() != 0) {
                this.d.setVisibility(0);
            }
        }
    }

    public void n(boolean z, char c2) {
        int i = (z && this.f211c.y()) ? 0 : 8;
        if (i == 0) {
            this.h.setText(this.f211c.g());
        }
        if (this.h.getVisibility() != i) {
            this.h.setVisibility(i);
        }
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        ViewCompat.N(this, this.l);
        TextView textView = (TextView) findViewById(R$id.title);
        this.f = textView;
        int i = this.m;
        if (i != -1) {
            textView.setTextAppearance(this.n, i);
        }
        this.h = (TextView) findViewById(R$id.shortcut);
        ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
        this.i = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.p);
        }
        this.j = (ImageView) findViewById(R$id.group_divider);
        this.k = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.d != null && this.o) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.d.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }

    public void p(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f.getVisibility() != 8) {
                this.f.setVisibility(8);
            }
        } else {
            this.f.setText(charSequence);
            if (this.f.getVisibility() != 0) {
                this.f.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        U u = U.u(getContext(), attributeSet, R$styleable.MenuView, i, 0);
        this.l = u.f(R$styleable.MenuView_android_itemBackground);
        this.m = u.m(R$styleable.MenuView_android_itemTextAppearance, -1);
        this.o = u.a(R$styleable.MenuView_preserveIconSpacing, false);
        this.n = context;
        this.p = u.f(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.q = typedArrayObtainStyledAttributes.hasValue(0);
        u.v();
        typedArrayObtainStyledAttributes.recycle();
    }
}
