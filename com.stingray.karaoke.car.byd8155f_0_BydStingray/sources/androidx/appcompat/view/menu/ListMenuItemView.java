package androidx.appcompat.view.menu;

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
import com.stingray.drivemodedetector.R;
import h3.y;
import n.AbstractC1850a;
import q.InterfaceC2158o;
import q.MenuItemC2152i;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements InterfaceC2158o, AbsListView.SelectionBoundsAdjuster {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public LayoutInflater f11276A;
    public boolean B;
    public MenuItemC2152i l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public ImageView f11277m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public RadioButton f11278n;
    public TextView o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public CheckBox f11279p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public TextView f11280q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public ImageView f11281r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public ImageView f11282s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public LinearLayout f11283t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final Drawable f11284u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final int f11285v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final Context f11286w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public boolean f11287x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final Drawable f11288y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final boolean f11289z;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        y yVarF = y.F(getContext(), attributeSet, AbstractC1850a.o, R.attr.listMenuViewStyle);
        this.f11284u = yVarF.s(5);
        TypedArray typedArray = (TypedArray) yVarF.f15500n;
        this.f11285v = typedArray.getResourceId(1, -1);
        this.f11287x = typedArray.getBoolean(7, false);
        this.f11286w = context;
        this.f11288y = yVarF.s(8);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{android.R.attr.divider}, R.attr.dropDownListViewStyle, 0);
        this.f11289z = typedArrayObtainStyledAttributes.hasValue(0);
        yVarF.G();
        typedArrayObtainStyledAttributes.recycle();
    }

    private LayoutInflater getInflater() {
        if (this.f11276A == null) {
            this.f11276A = LayoutInflater.from(getContext());
        }
        return this.f11276A;
    }

    private void setSubMenuArrowVisible(boolean z6) {
        ImageView imageView = this.f11281r;
        if (imageView != null) {
            imageView.setVisibility(z6 ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.f11282s;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f11282s.getLayoutParams();
        rect.top = this.f11282s.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005e  */
    @Override // q.InterfaceC2158o
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b(q.MenuItemC2152i r11) {
        /*
            Method dump skipped, instruction units count: 325
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.b(q.i):void");
    }

    @Override // q.InterfaceC2158o
    public MenuItemC2152i getItemData() {
        return this.l;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        setBackground(this.f11284u);
        TextView textView = (TextView) findViewById(R.id.title);
        this.o = textView;
        int i7 = this.f11285v;
        if (i7 != -1) {
            textView.setTextAppearance(this.f11286w, i7);
        }
        this.f11280q = (TextView) findViewById(R.id.shortcut);
        ImageView imageView = (ImageView) findViewById(R.id.submenuarrow);
        this.f11281r = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.f11288y);
        }
        this.f11282s = (ImageView) findViewById(R.id.group_divider);
        this.f11283t = (LinearLayout) findViewById(R.id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i7, int i10) {
        if (this.f11277m != null && this.f11287x) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.f11277m.getLayoutParams();
            int i11 = layoutParams.height;
            if (i11 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i11;
            }
        }
        super.onMeasure(i7, i10);
    }

    public void setCheckable(boolean z6) {
        CompoundButton compoundButton;
        View view;
        if (!z6 && this.f11278n == null && this.f11279p == null) {
            return;
        }
        if ((this.l.f21086x & 4) != 0) {
            if (this.f11278n == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f11278n = radioButton;
                LinearLayout linearLayout = this.f11283t;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f11278n;
            view = this.f11279p;
        } else {
            if (this.f11279p == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f11279p = checkBox;
                LinearLayout linearLayout2 = this.f11283t;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f11279p;
            view = this.f11278n;
        }
        if (z6) {
            compoundButton.setChecked(this.l.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.f11279p;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.f11278n;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z6) {
        CompoundButton compoundButton;
        if ((this.l.f21086x & 4) != 0) {
            if (this.f11278n == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R.layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.f11278n = radioButton;
                LinearLayout linearLayout = this.f11283t;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.f11278n;
        } else {
            if (this.f11279p == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R.layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.f11279p = checkBox;
                LinearLayout linearLayout2 = this.f11283t;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.f11279p;
        }
        compoundButton.setChecked(z6);
    }

    public void setForceShowIcon(boolean z6) {
        this.B = z6;
        this.f11287x = z6;
    }

    public void setGroupDividerEnabled(boolean z6) {
        ImageView imageView = this.f11282s;
        if (imageView != null) {
            imageView.setVisibility((this.f11289z || !z6) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        this.l.f21077n.getClass();
        boolean z6 = this.B;
        if (z6 || this.f11287x) {
            ImageView imageView = this.f11277m;
            if (imageView == null && drawable == null && !this.f11287x) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R.layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.f11277m = imageView2;
                LinearLayout linearLayout = this.f11283t;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.f11287x) {
                this.f11277m.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.f11277m;
            if (!z6) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.f11277m.getVisibility() != 0) {
                this.f11277m.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.o.getVisibility() != 8) {
                this.o.setVisibility(8);
            }
        } else {
            this.o.setText(charSequence);
            if (this.o.getVisibility() != 0) {
                this.o.setVisibility(0);
            }
        }
    }
}
