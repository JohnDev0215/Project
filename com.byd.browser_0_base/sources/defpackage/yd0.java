package defpackage;

import android.content.Context;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.om3;
import java.util.ArrayList;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.ui.R$color;
import org.chromium.ui.R$dimen;
import org.chromium.ui.R$id;
import org.chromium.ui.R$layout;

/* JADX INFO: compiled from: DropdownAdapter.java */
/* JADX INFO: loaded from: classes3.dex */
public final class yd0 extends ArrayAdapter<ce0> {
    public final Context e;
    public final Set<Integer> f;
    public final boolean g;
    public final int h;

    public yd0(Context context, ArrayList arrayList) {
        super(context, R$layout.dropdown_item);
        this.e = context;
        addAll(arrayList);
        this.f = null;
        boolean z = false;
        int i = 0;
        while (true) {
            if (i >= getCount()) {
                z = true;
                break;
            }
            ce0 item = getItem(i);
            if (item.isEnabled() && !item.a()) {
                break;
            } else {
                i++;
            }
        }
        this.g = z;
        this.h = context.getResources().getDimensionPixelSize(R$dimen.dropdown_item_label_margin);
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return this.g;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        Context context = this.e;
        if (view == null) {
            view = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R$layout.dropdown_item, (ViewGroup) null);
            view.setBackground(new ae0());
        }
        ae0 ae0Var = (ae0) view.getBackground();
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.dropdown_item_height);
        if (i == 0) {
            ae0Var.a.setColor(0);
        } else {
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.dropdown_item_divider_height);
            dimensionPixelSize += dimensionPixelSize2;
            Rect rect = ae0Var.b;
            rect.set(0, 0, rect.right, dimensionPixelSize2);
            Set<Integer> set = this.f;
            ae0Var.a.setColor((set == null || !set.contains(Integer.valueOf(i))) ? context.getColor(R$color.dropdown_divider_color) : context.getColor(R$color.dropdown_dark_divider_color));
        }
        ce0 item = getItem(i);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.dropdown_label_wrapper);
        if (item.b()) {
            dimensionPixelSize = -2;
        }
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f));
        TextView textView = (TextView) view.findViewById(R$id.dropdown_label);
        textView.setText(item.f());
        textView.setSingleLine(!item.b());
        if (item.b()) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            int iE = om3.e.e(textView);
            int iD = om3.e.d(textView);
            int i2 = this.h;
            om3.e.i(textView, iE, i2, iD, i2);
        }
        textView.setEnabled(item.isEnabled());
        if (item.a() || item.k()) {
            textView.setTypeface(null, 1);
        } else {
            textView.setTypeface(null, 0);
        }
        textView.setTextColor(context.getColor(item.h()));
        textView.setTextSize(0, context.getResources().getDimension(R$dimen.text_size_large));
        TextView textView2 = (TextView) view.findViewById(R$id.dropdown_sublabel);
        String strC = item.c();
        if (TextUtils.isEmpty(strC)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(strC);
            textView2.setTextSize(0, context.getResources().getDimension(item.n()));
            textView2.setVisibility(0);
        }
        ImageView imageView = (ImageView) view.findViewById(R$id.start_dropdown_icon);
        ImageView imageView2 = (ImageView) view.findViewById(R$id.end_dropdown_icon);
        if (item.i()) {
            imageView2.setVisibility(8);
        } else {
            imageView.setVisibility(8);
        }
        if (!item.i()) {
            imageView = imageView2;
        }
        if (item.o() == 0) {
            imageView.setVisibility(8);
        } else {
            item.g();
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams.width = -2;
            marginLayoutParams.height = -2;
            int dimensionPixelSize3 = context.getResources().getDimensionPixelSize(item.d());
            marginLayoutParams.setMarginStart(dimensionPixelSize3);
            marginLayoutParams.setMarginEnd(dimensionPixelSize3);
            imageView.setLayoutParams(marginLayoutParams);
            imageView.setImageDrawable(AppCompatResources.a(context, item.o()));
            imageView.setVisibility(0);
        }
        return view;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean isEnabled(int i) {
        if (i < 0 || i >= getCount()) {
            return false;
        }
        ce0 item = getItem(i);
        return item.isEnabled() && !item.a();
    }
}
