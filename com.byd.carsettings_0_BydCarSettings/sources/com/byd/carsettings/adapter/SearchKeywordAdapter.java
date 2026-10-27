package com.byd.carsettings.adapter;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.carsettings.R;
import com.byd.carsettings.db.model.SearchKeyword;
import com.byd.mvp.ui.adapter.BydAdapter;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class SearchKeywordAdapter extends BydAdapter<SearchKeyword> {

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f2608a;

        public a(int i) {
            this.f2608a = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (SearchKeywordAdapter.this.mItemClickListener != null) {
                SearchKeywordAdapter.this.mItemClickListener.onItemClicked(this.f2608a);
            }
        }
    }

    public class b extends BydAdapter<SearchKeyword>.BaseViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f2610a;

        public b(View view) {
            super(view);
            a(view);
        }

        public final void a(View view) {
            this.f2610a = (TextView) view.findViewById(R.id.btn_keyword_list);
        }
    }

    public SearchKeywordAdapter(List<SearchKeyword> list) {
        super(list);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public BydAdapter<SearchKeyword>.BaseViewHolder initViewHolder(View view) {
        return new b(view);
    }

    @Override // com.byd.mvp.ui.adapter.BydAdapter
    public int loadLayoutId() {
        return R.layout.item_keyword_list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((b) viewHolder).f2610a.setText(((SearchKeyword) this.data.get(i)).getKeyword());
        viewHolder.itemView.setOnClickListener(new a(i));
    }
}
