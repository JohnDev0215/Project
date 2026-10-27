package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
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
import java.util.HashSet;
import java.util.Set;
import java.util.WeakHashMap;
import org.chromium.components.autofill.R$color;
import org.chromium.components.autofill.R$dimen;
import org.chromium.components.autofill.R$id;
import org.chromium.components.autofill.R$layout;

/* JADX INFO: compiled from: AutofillDropdownAdapter.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ua extends ArrayAdapter<ce0> {
    public final Context e;
    public final Set<Integer> f;
    public final boolean g;
    public final int h;

    public ua(Context context, ArrayList arrayList, HashSet hashSet) {
        super(context, R$layout.autofill_dropdown_item);
        this.e = context;
        addAll(arrayList);
        this.f = hashSet;
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
        this.h = context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_item_label_margin);
    }

    public static TextView a(View view, int i, String str, boolean z) {
        TextView textView = (TextView) view.findViewById(i);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
            return null;
        }
        textView.setText(str);
        textView.setEnabled(z);
        textView.setVisibility(0);
        return textView;
    }

    @Override // android.widget.BaseAdapter, android.widget.ListAdapter
    public final boolean areAllItemsEnabled() {
        return this.g;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = null;
        Context context = this.e;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R$layout.autofill_dropdown_item, (ViewGroup) null);
            view.setBackground(new ae0());
        }
        ce0 item = getItem(i);
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_item_height);
        ae0 ae0Var = (ae0) view.getBackground();
        if (i == 0) {
            ae0Var.a.setColor(0);
        } else {
            int dimensionPixelSize2 = context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_item_divider_height);
            dimensionPixelSize += dimensionPixelSize2;
            Rect rect = ae0Var.b;
            rect.set(0, 0, rect.right, dimensionPixelSize2);
            Set<Integer> set = this.f;
            ae0Var.a.setColor((set == null || !set.contains(Integer.valueOf(i))) ? context.getColor(R$color.dropdown_divider_color) : context.getColor(R$color.dropdown_dark_divider_color));
        }
        TextView textViewA = a(view, R$id.dropdown_item_tag, item.e(), false);
        if (textViewA != null) {
            textViewA.setTextSize(0, context.getResources().getDimension(item.n()));
            dimensionPixelSize += context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_item_tag_height);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R$id.dropdown_label_wrapper);
        if (item.b()) {
            dimensionPixelSize = -2;
        }
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(0, dimensionPixelSize, 1.0f));
        TextView textViewA2 = a(view, R$id.dropdown_label, item.f(), item.isEnabled());
        TextView textViewA3 = a(view, R$id.dropdown_secondary_label, item.l(), item.isEnabled());
        textViewA2.setSingleLine(!item.b());
        if (item.b()) {
            WeakHashMap<View, in3> weakHashMap = om3.a;
            int iE = om3.e.e(textViewA2);
            int iD = om3.e.d(textViewA2);
            int i2 = this.h;
            om3.e.i(textViewA2, iE, i2, iD, i2);
        }
        if (item.a() || item.k()) {
            textViewA2.setTypeface(null, 1);
            if (textViewA3 != null) {
                textViewA3.setTypeface(null, 1);
            }
        } else {
            textViewA2.setTypeface(null, 0);
            if (textViewA3 != null) {
                textViewA3.setTypeface(null, 0);
            }
        }
        textViewA2.setTextColor(context.getColor(item.h()));
        Resources resources = context.getResources();
        int i3 = R$dimen.text_size_large;
        textViewA2.setTextSize(0, resources.getDimension(i3));
        if (textViewA3 != null) {
            textViewA3.setTextColor(context.getColor(item.h()));
            textViewA3.setTextSize(0, context.getResources().getDimension(i3));
        }
        TextView textViewA4 = a(view, R$id.dropdown_sublabel, item.c(), false);
        if (textViewA4 != null) {
            textViewA4.setTextSize(0, context.getResources().getDimension(item.n()));
        }
        TextView textViewA5 = a(view, R$id.dropdown_secondary_sublabel, item.j(), false);
        if (textViewA5 != null) {
            textViewA5.setTextSize(0, context.getResources().getDimension(item.n()));
        }
        ImageView imageView = (ImageView) view.findViewById(R$id.start_dropdown_icon);
        ImageView imageView2 = (ImageView) view.findViewById(R$id.end_dropdown_icon);
        if (item.i()) {
            imageView2.setVisibility(8);
            imageView.setVisibility(0);
        } else {
            imageView.setVisibility(8);
            imageView2.setVisibility(0);
        }
        if (!item.i()) {
            imageView = imageView2;
        }
        if (item.o() == 0 && item.m() == null) {
            imageView.setVisibility(8);
        } else {
            if (item.m() != null) {
                imageView.setImageBitmap(Bitmap.createScaledBitmap(item.m(), context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_icon_width), context.getResources().getDimensionPixelSize(R$dimen.autofill_dropdown_icon_height), true));
            } else {
                imageView.setImageDrawable(AppCompatResources.a(context, item.o()));
            }
            imageView.setVisibility(0);
            view2 = imageView;
        }
        if (view2 != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view2.getLayoutParams();
            item.g();
            marginLayoutParams.width = -2;
            marginLayoutParams.height = -2;
            int dimensionPixelSize3 = context.getResources().getDimensionPixelSize(item.d());
            marginLayoutParams.setMarginStart(dimensionPixelSize3);
            marginLayoutParams.setMarginEnd(dimensionPixelSize3);
            view2.setLayoutParams(marginLayoutParams);
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
