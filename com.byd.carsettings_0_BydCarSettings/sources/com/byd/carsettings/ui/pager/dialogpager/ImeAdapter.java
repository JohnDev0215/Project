package com.byd.carsettings.ui.pager.dialogpager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.h.j;
import b.b.a.j.d.c;
import b.b.g.m.d.b;
import com.byd.carsettings.R;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ImeAdapter extends RecyclerView.Adapter<ImeViewHolder> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Context f3276a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final List<b> f3277b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c f3278c;

    public static class ImeViewHolder extends RecyclerView.ViewHolder {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public TextView f3279a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public TextView f3280b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public ImageView f3281c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public View f3282d;

        public ImeViewHolder(View view) {
            super(view);
            this.f3282d = view;
            this.f3279a = (TextView) view.findViewById(R.id.ime_lable);
            this.f3280b = (TextView) view.findViewById(R.id.ime_sub_lable);
            this.f3281c = (ImageView) view.findViewById(R.id.iv_check);
        }
    }

    public class a implements View.OnClickListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ ImeViewHolder f3283a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ int f3284b;

        public a(ImeViewHolder imeViewHolder, int i) {
            this.f3283a = imeViewHolder;
            this.f3284b = i;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.f3283a.f3281c.isSelected() || ImeAdapter.this.f3278c == null) {
                return;
            }
            ImeAdapter.this.f3278c.a(this.f3283a.f3282d, this.f3284b);
        }
    }

    public ImeAdapter(Context context, List<b> list) {
        this.f3276a = context;
        this.f3277b = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(@NonNull ImeViewHolder imeViewHolder, int i) {
        b bVar = this.f3277b.get(i);
        imeViewHolder.f3279a.setText(bVar.b());
        int dimensionPixelOffset = this.f3276a.getResources().getDimensionPixelOffset(R.dimen.dp_5);
        if (j.c(bVar.c())) {
            imeViewHolder.f3280b.setVisibility(8);
            imeViewHolder.f3279a.setPadding(0, dimensionPixelOffset, 0, 0);
        } else {
            imeViewHolder.f3280b.setVisibility(0);
            imeViewHolder.f3280b.setText(bVar.c());
            imeViewHolder.f3279a.setPadding(0, 0, 0, 0);
        }
        imeViewHolder.f3281c.setSelected(bVar.e() || bVar.d());
        imeViewHolder.f3279a.setSelected(bVar.e() || bVar.d());
        imeViewHolder.f3280b.setSelected(bVar.e() || bVar.d());
        imeViewHolder.f3282d.setOnClickListener(new a(imeViewHolder, i));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public ImeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View viewInflate = LayoutInflater.from(this.f3276a).inflate(R.layout.item_check_ime, viewGroup, false);
        if (b.b.o.b.b.a.c() == 2) {
            viewInflate = LayoutInflater.from(this.f3276a).inflate(R.layout.item_check_ime_r, viewGroup, false);
        }
        return new ImeViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.f3277b.size();
    }

    public void setOnItemClickListener(c cVar) {
        this.f3278c = cVar;
    }
}
