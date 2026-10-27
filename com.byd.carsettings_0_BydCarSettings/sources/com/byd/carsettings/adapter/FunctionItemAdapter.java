package com.byd.carsettings.adapter;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.FunctionItem;
import com.byd.mvp.ui.adapter.BydAdapter;
import com.byd.mvp.ui.widget.TintImageView;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
public class FunctionItemAdapter extends BydAdapter<FunctionItem> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public volatile String f2597a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2598b;

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2599a;

        public a(int i) {
            this.f2599a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (FunctionItemAdapter.this.mItemClickListener != null) {
                FunctionItemAdapter.this.mItemClickListener.onItemClicked(this.f2599a);
            }
        }
    }

    public class b extends BydAdapter<FunctionItem>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TintImageView f2601a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2602b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2603c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f2604d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public View f2605e;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2601a = (TintImageView) view.findViewById(R.id.iv_function_icon_item_list);
            this.f2602b = (TextView) view.findViewById(R.id.tv_function_name_item_list);
            this.f2603c = (TextView) view.findViewById(R.id.tv_function_address_item_list);
            this.f2604d = view.findViewById(R.id.v_line_function_item_list);
            this.f2605e = view.findViewById(R.id.v_line_same_type_function_item_list);
        }
    }

    public FunctionItemAdapter(List<FunctionItem> list) {
        super(list);
        this.f2598b = -1;
    }

    public static String[] e(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.contains("-")) {
            strTrim = strTrim.replace("-", " ");
        }
        if (strTrim.contains("/")) {
            strTrim = strTrim.replace("/", " ");
        }
        return strTrim.split(" ");
    }

    public final int d(int i) {
        if (i == 0) {
            return R.drawable.icon_carsetting_collect_48;
        }
        if (i == 1) {
            return R.drawable.icon_carsetting_dilink_48;
        }
        if (i == 2) {
            return R.drawable.icon_carsetting_dipilot_48;
        }
        if (i == 3) {
            return R.drawable.icon_carsetting_newenergy_48;
        }
        if (i == 4) {
            return R.drawable.icon_carsetting_car_48;
        }
        if (i != 5) {
            return -1;
        }
        return R.drawable.icon_carsetting_health_48;
    }

    public final void f(b bVar, int i) {
        if (bVar.f2601a == null) {
            return;
        }
        int iD = d(i);
        if (iD == -1) {
            bVar.f2601a.setVisibility(4);
            bVar.f2604d.setVisibility(8);
            bVar.f2605e.setVisibility(8);
        } else {
            bVar.f2601a.setVisibility(0);
            bVar.f2601a.setImageResource(iD);
            bVar.f2601a.d(1);
            bVar.f2604d.setVisibility(8);
            bVar.f2605e.setVisibility(8);
        }
    }

    public final void g(b bVar, int i, int i2) {
        if (bVar.f2601a == null) {
            return;
        }
        int iD = d(i);
        if (iD == -1) {
            bVar.f2601a.setVisibility(4);
            bVar.f2604d.setVisibility(8);
            return;
        }
        if (i == i2) {
            bVar.f2601a.setVisibility(4);
            bVar.f2604d.setVisibility(8);
            bVar.f2605e.setVisibility(0);
        } else {
            bVar.f2601a.setVisibility(0);
            bVar.f2604d.setVisibility(0);
            bVar.f2605e.setVisibility(8);
        }
        bVar.f2601a.setImageResource(iD);
        bVar.f2601a.d(1);
    }

    public void i(String str) {
        this.f2597a = str;
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<FunctionItem>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    public final void j(String str, TextView textView, String str2) {
        String str3;
        String[] strArrSplit;
        String lowerCase = null;
        if (str2 == null || (strArrSplit = str2.split(">")) == null || strArrSplit.length <= 1) {
            str3 = null;
        } else {
            str3 = strArrSplit[0];
            lowerCase = strArrSplit[1];
        }
        if (lowerCase != null) {
            lowerCase = lowerCase.toLowerCase();
        }
        o(str, textView, str2, lowerCase, str3);
    }

    public final void k(TextView textView, String str, String str2, String str3, String str4) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
            return;
        }
        if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str2)) {
            textView.setText(str);
            return;
        }
        if (this.f2598b == -1) {
            this.f2598b = textView.getContext().getColor(R.color.text_color_btn_blue);
        }
        int iIndexOf = str2.indexOf(str3);
        int length = this.f2597a.length() + iIndexOf;
        if (iIndexOf >= 0) {
            if (str4 != null) {
                iIndexOf += str4.length() + 1;
                length += str4.length() + 1;
            }
            ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(this.f2598b);
            SpannableString spannableString = new SpannableString(str.trim());
            spannableString.setSpan(foregroundColorSpan, iIndexOf, length, 33);
            textView.setText(spannableString);
        }
    }

    public final void l(TextView textView, String str, String str2, String str3, String str4) {
        if (textView == null) {
            return;
        }
        if (TextUtils.isEmpty(str)) {
            textView.setText("");
            return;
        }
        if (TextUtils.isEmpty(str3)) {
            textView.setText(str);
            return;
        }
        String[] strArrE = e(str2);
        if (strArrE == null || strArrE.length <= 0) {
            textView.setText(str);
            return;
        }
        int length = -1;
        if (this.f2598b == -1) {
            this.f2598b = textView.getContext().getColor(R.color.text_color_btn_blue);
        }
        char[] charArray = str3.toCharArray();
        SpannableString spannableString = new SpannableString(str.trim());
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (i2 < strArrE.length) {
                if (strArrE[i2].startsWith(charArray[i2] + "")) {
                    int iIndexOf = str2.toLowerCase().indexOf(charArray[i2] + "", length);
                    if (iIndexOf >= 0) {
                        if (str4 != null) {
                            iIndexOf += str4.length() + 1;
                        }
                        spannableString.setSpan(new ForegroundColorSpan(this.f2598b), iIndexOf, iIndexOf + 1, 33);
                        i++;
                    }
                    length += strArrE[i2].length();
                }
            }
        }
        if (i == charArray.length) {
            textView.setText(spannableString);
        } else {
            textView.setText(str);
        }
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_function_item_list;
    }

    public final void n(String str, TextView textView, String str2) {
        o(str, textView, str2, str2.trim().toLowerCase(), null);
    }

    public final void o(String str, TextView textView, String str2, String str3, String str4) {
        if (Locale.CHINA.equals(str)) {
            if (str2 == null || !str2.contains(this.f2597a)) {
                return;
            }
            k(textView, str2, str2, this.f2597a, str4);
            return;
        }
        String lowerCase = this.f2597a.trim().toLowerCase();
        if (str3 == null || !str3.contains(lowerCase)) {
            l(textView, str2, str3, lowerCase, str4);
        } else {
            k(textView, str2, str3, lowerCase, str4);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        b bVar = (b) viewHolder;
        FunctionItem functionItem = (FunctionItem) this.data.get(i);
        String langTag = functionItem.getLangTag();
        n(langTag, bVar.f2602b, functionItem.getName());
        j(langTag, bVar.f2603c, functionItem.getAddress());
        if (i == 0) {
            f(bVar, functionItem.getIndex0());
        } else {
            g(bVar, functionItem.getIndex0(), ((FunctionItem) this.data.get(i - 1)).getIndex0());
        }
        viewHolder.itemView.setOnClickListener(new a(i));
    }
}
