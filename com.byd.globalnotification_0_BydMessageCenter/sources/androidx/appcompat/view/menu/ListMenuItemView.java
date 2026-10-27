package androidx.appcompat.view.menu;

import a.b.a;
import a.b.f;
import a.b.g;
import a.b.h;
import a.b.j;
import a.b.o.i.i;
import a.b.o.i.n;
import a.b.p.x0;
import a.h.k.q;
import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements n.a, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public i f1406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public ImageView f1407b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public RadioButton f1408c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public TextView f1409d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CheckBox f1410e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public TextView f1411f;
    public ImageView g;
    public ImageView h;
    public LinearLayout i;
    public Drawable j;
    public int k;
    public Context l;
    public boolean m;
    public Drawable n;
    public boolean o;
    public LayoutInflater p;
    public boolean q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        int i = a.listMenuViewStyle;
        x0 x0VarQ = x0.q(getContext(), attributeSet, j.MenuView, i, 0);
        this.j = x0VarQ.g(j.MenuView_android_itemBackground);
        this.k = x0VarQ.l(j.MenuView_android_itemTextAppearance, -1);
        this.m = x0VarQ.a(j.MenuView_preserveIconSpacing, false);
        this.l = context;
        this.n = x0VarQ.g(j.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, a.dropDownListViewStyle, 0);
        this.o = typedArrayObtainStyledAttributes.hasValue(0);
        x0VarQ.f390b.recycle();
        typedArrayObtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.p == null) {
            this.p = LayoutInflater.from(getContext());
        }
        return this.p;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.g;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    public final void a() {
        CheckBox checkBox = (CheckBox) getInflater().inflate(g.abc_list_menu_item_checkbox, (ViewGroup) this, false);
        this.f1410e = checkBox;
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(checkBox, -1);
        } else {
            addView(checkBox, -1);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.h;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.h.getLayoutParams();
        rect.top = this.h.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    public final void b() {
        RadioButton radioButton = (RadioButton) getInflater().inflate(g.abc_list_menu_item_radio, (ViewGroup) this, false);
        this.f1408c = radioButton;
        LinearLayout linearLayout = this.i;
        if (linearLayout != null) {
            linearLayout.addView(radioButton, -1);
        } else {
            addView(radioButton, -1);
        }
    }

    @Override // a.b.o.i.n.a
    public boolean c() {
        return false;
    }

    public void d(boolean z) {
        int i;
        String string;
        int i2 = (z && this.f1406a.n()) ? 0 : 8;
        if (i2 == 0) {
            TextView textView = this.f1411f;
            i iVar = this.f1406a;
            char cE = iVar.e();
            if (cE == 0) {
                string = "";
            } else {
                Resources resources = iVar.n.f176a.getResources();
                StringBuilder sb = new StringBuilder();
                if (ViewConfiguration.get(iVar.n.f176a).hasPermanentMenuKey()) {
                    sb.append(resources.getString(h.abc_prepend_shortcut_label));
                }
                int i3 = iVar.n.n() ? iVar.k : iVar.i;
                i.c(sb, i3, 65536, resources.getString(h.abc_menu_meta_shortcut_label));
                i.c(sb, i3, 4096, resources.getString(h.abc_menu_ctrl_shortcut_label));
                i.c(sb, i3, 2, resources.getString(h.abc_menu_alt_shortcut_label));
                i.c(sb, i3, 1, resources.getString(h.abc_menu_shift_shortcut_label));
                i.c(sb, i3, 4, resources.getString(h.abc_menu_sym_shortcut_label));
                i.c(sb, i3, 8, resources.getString(h.abc_menu_function_shortcut_label));
                if (cE == '\b') {
                    i = h.abc_menu_delete_shortcut_label;
                } else if (cE == '\n') {
                    i = h.abc_menu_enter_shortcut_label;
                } else if (cE != ' ') {
                    sb.append(cE);
                    string = sb.toString();
                } else {
                    i = h.abc_menu_space_shortcut_label;
                }
                sb.append(resources.getString(i));
                string = sb.toString();
            }
            textView.setText(string);
        }
        if (this.f1411f.getVisibility() != i2) {
            this.f1411f.setVisibility(i2);
        }
    }

    @Override // a.b.o.i.n.a
    public void e(i iVar, int i) {
        this.f1406a = iVar;
        setVisibility(iVar.isVisible() ? 0 : 8);
        setTitle(c() ? iVar.getTitleCondensed() : iVar.f189e);
        setCheckable(iVar.isCheckable());
        boolean zN = iVar.n();
        iVar.e();
        d(zN);
        setIcon(iVar.getIcon());
        setEnabled(iVar.isEnabled());
        setSubMenuArrowVisible(iVar.hasSubMenu());
        setContentDescription(iVar.q);
    }

    @Override // a.b.o.i.n.a
    public i getItemData() {
        return this.f1406a;
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        q.X(this, this.j);
        TextView textView = (TextView) findViewById(f.title);
        this.f1409d = textView;
        int i = this.k;
        if (i != -1) {
            textView.setTextAppearance(this.l, i);
        }
        this.f1411f = (TextView) findViewById(f.shortcut);
        ImageView imageView = (ImageView) findViewById(f.submenuarrow);
        this.g = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.n);
        }
        this.h = (ImageView) findViewById(f.group_divider);
        this.i = (LinearLayout) findViewById(f.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int i, int i2) {
        if (this.f1407b != null && this.m) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f1407b.getLayoutParams();
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
        if (!z && this.f1408c == null && this.f1410e == null) {
            return;
        }
        if (this.f1406a.h()) {
            if (this.f1408c == null) {
                b();
            }
            compoundButton = this.f1408c;
            compoundButton2 = this.f1410e;
        } else {
            if (this.f1410e == null) {
                a();
            }
            compoundButton = this.f1410e;
            compoundButton2 = this.f1408c;
        }
        if (z) {
            compoundButton.setChecked(this.f1406a.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (compoundButton2 == null || compoundButton2.getVisibility() == 8) {
                return;
            }
            compoundButton2.setVisibility(8);
            return;
        }
        CheckBox checkBox = this.f1410e;
        if (checkBox != null) {
            checkBox.setVisibility(8);
        }
        RadioButton radioButton = this.f1408c;
        if (radioButton != null) {
            radioButton.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if (this.f1406a.h()) {
            if (this.f1408c == null) {
                b();
            }
            compoundButton = this.f1408c;
        } else {
            if (this.f1410e == null) {
                a();
            }
            compoundButton = this.f1410e;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.q = z;
        this.m = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.h;
        if (imageView != null) {
            imageView.setVisibility((this.o || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        boolean z = this.f1406a.n.s || this.q;
        if (z || this.m) {
            if (this.f1407b == null && drawable == null && !this.m) {
                return;
            }
            if (this.f1407b == null) {
                ImageView imageView = (ImageView) getInflater().inflate(g.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f1407b = imageView;
                LinearLayout linearLayout = this.i;
                if (linearLayout != null) {
                    linearLayout.addView(imageView, 0);
                } else {
                    addView(imageView, 0);
                }
            }
            if (drawable == null && !this.m) {
                this.f1407b.setVisibility(8);
                return;
            }
            ImageView imageView2 = this.f1407b;
            if (!z) {
                drawable = null;
            }
            imageView2.setImageDrawable(drawable);
            if (this.f1407b.getVisibility() != 0) {
                this.f1407b.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.f1409d.getVisibility() != 8) {
                this.f1409d.setVisibility(8);
            }
        } else {
            this.f1409d.setText(charSequence);
            if (this.f1409d.getVisibility() != 0) {
                this.f1409d.setVisibility(0);
            }
        }
    }
}
