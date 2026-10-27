package com.byd.carsettings.adapter.dilink;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.system.model.BydLanguage;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class LanguageListAdapter extends BydAdapter<BydLanguage> {

    public class a extends BydAdapter<BydLanguage>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public ImageView f2713a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f2714b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public TextView f2715c;

        public a(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2713a = (ImageView) view.findViewById(R.id.iv_check_state_language_list);
            this.f2714b = (TextView) view.findViewById(R.id.tv_text_language_list);
            this.f2715c = (TextView) view.findViewById(R.id.tv_text_description_language_list);
        }
    }

    public LanguageListAdapter(List<BydLanguage> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<BydLanguage>.BaseViewHolder initViewHolder(View view) {
        return new a(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return b.b.o.b.b.a.c() == 2 ? R.layout.item_language_list_r : R.layout.item_language_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        a aVar = (a) viewHolder;
        aVar.setId(i);
        BydLanguage bydLanguage = (BydLanguage) this.data.get(i);
        aVar.f2714b.setText(bydLanguage.getText());
        aVar.f2715c.setText(bydLanguage.getDescription());
        Context context = aVar.f2714b.getContext();
        if (bydLanguage.isChecked()) {
            aVar.f2713a.setImageResource(R.drawable.radio_pitch_regular);
            int color = context.getColor(R.color.text_color_blue_night);
            aVar.f2714b.setTextColor(color);
            aVar.f2715c.setTextColor(color);
            return;
        }
        aVar.f2713a.setImageResource(R.drawable.radio_normal_regular);
        int color2 = context.getColor(R.color.text_color_rbtn_main);
        aVar.f2714b.setTextColor(color2);
        aVar.f2715c.setTextColor(color2);
    }
}
