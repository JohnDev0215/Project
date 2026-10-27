package com.byd.filemanager.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.filemanager.R;
import com.byd.filemanager.ThreadManager;
import com.byd.filemanager.ui.view.FlowLayoutManager;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchHistoryFragment extends BaseFragment implements View.OnClickListener {
    private RecyclerView c0;
    private HistoryAdapter d0;
    private List<String> e0;
    private com.byd.filemanager.ui.a<String> f0;
    private ImageView g0;
    private TextView h0;

    protected static class HistoryAdapter extends RecyclerView.g<ViewHolder> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private com.byd.filemanager.ui.a<String> f3840c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private List<String> f3841d;

        protected static class ViewHolder extends RecyclerView.c0 {
            TextView t;

            public ViewHolder(View view) {
                super(view);
                this.t = (TextView) view.findViewById(R.id.tv_name);
            }
        }

        public HistoryAdapter(List<String> list) {
            this.f3841d = list;
        }

        private String x(String str) {
            if (str.length() <= 10) {
                return str;
            }
            return str.substring(0, 10) + "...";
        }

        public void A(com.byd.filemanager.ui.a<String> aVar) {
            this.f3840c = aVar;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        public int c() {
            List<String> list = this.f3841d;
            if (list != null) {
                return list.size();
            }
            return 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: y, reason: merged with bridge method [inline-methods] */
        public void l(final ViewHolder viewHolder, int i) {
            final String str = this.f3841d.get(i);
            viewHolder.t.setText(x(str));
            viewHolder.f1300b.setOnClickListener(new View.OnClickListener() { // from class: com.byd.filemanager.ui.fragment.SearchHistoryFragment.HistoryAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (HistoryAdapter.this.f3840c != null) {
                        HistoryAdapter.this.f3840c.a(str, viewHolder.j());
                    }
                }
            });
        }

        @Override // androidx.recyclerview.widget.RecyclerView.g
        /* JADX INFO: renamed from: z, reason: merged with bridge method [inline-methods] */
        public ViewHolder n(ViewGroup viewGroup, int i) {
            return com.byd.filemanager.f.h().v() ? new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_history, viewGroup, false)) : new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_history_denza, viewGroup, false));
        }
    }

    private void G1() {
        List<String> list = this.e0;
        if (list == null || list.size() <= 0) {
            this.h0.setVisibility(8);
            this.g0.setVisibility(8);
        } else {
            this.h0.setVisibility(0);
            this.g0.setVisibility(0);
        }
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected void A1(View view) {
        this.c0 = (RecyclerView) view.findViewById(R.id.recyclerView);
        this.h0 = (TextView) view.findViewById(R.id.tv_history);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_delete);
        this.g0 = imageView;
        imageView.setOnClickListener(this);
        this.e0 = com.byd.filemanager.k.f.c();
        com.byd.filemanager.k.b.b("initView mList:" + this.e0);
        this.d0 = new HistoryAdapter(this.e0);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(x().getResources().getDimensionPixelOffset(R.dimen.dp_32), new FlowLayoutManager.a() { // from class: com.byd.filemanager.ui.fragment.SearchHistoryFragment.1
            @Override // com.byd.filemanager.ui.view.FlowLayoutManager.a
            public void a(int i) {
                com.byd.filemanager.k.b.b("onLayoutComplete count:" + i);
                com.byd.filemanager.k.f.h(i);
                if (i <= 0 || SearchHistoryFragment.this.e0.size() <= i) {
                    return;
                }
                com.byd.filemanager.k.f.f((String) SearchHistoryFragment.this.e0.remove(SearchHistoryFragment.this.e0.size() - 1));
                ThreadManager.h().j(new Runnable() { // from class: com.byd.filemanager.ui.fragment.SearchHistoryFragment.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SearchHistoryFragment.this.d0.h();
                    }
                });
            }
        });
        flowLayoutManager.W1(2);
        this.c0.setLayoutManager(flowLayoutManager);
        androidx.recyclerview.widget.d dVar = new androidx.recyclerview.widget.d(j1(), 0);
        dVar.l(androidx.core.content.a.d(j1(), R.drawable.bg_divider_flow));
        this.c0.h(dVar);
        this.c0.setAdapter(this.d0);
        this.d0.A(new com.byd.filemanager.ui.a<String>() { // from class: com.byd.filemanager.ui.fragment.SearchHistoryFragment.2
            @Override // com.byd.filemanager.ui.a
            /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
            public void a(String str, int i) {
                if (SearchHistoryFragment.this.f0 != null) {
                    SearchHistoryFragment.this.f0.a(str, i);
                }
            }
        });
        G1();
    }

    @Override // com.byd.filemanager.ui.fragment.BaseFragment
    protected int B1() {
        return R.layout.fragment_search_history;
    }

    public void F1(com.byd.filemanager.ui.a<String> aVar) {
        this.f0 = aVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.iv_delete) {
            com.byd.filemanager.k.f.b();
            this.e0.clear();
            this.d0.h();
            G1();
        }
    }
}
