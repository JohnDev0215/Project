package com.android.car.media.browse;

import android.car.utils.Logs;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.media.browse.BrowseAdapter;
import com.android.car.media.common.MediaItemMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import spi.a0;
import spi.j0;
import spi.q6;
import spi.x4;

/* JADX INFO: loaded from: classes.dex */
public class BrowseAdapter extends ListAdapter<j0, BrowseViewHolder> {
    public static final DiffUtil.ItemCallback<j0> h = new a();

    @NonNull
    public final Context a;

    @NonNull
    public List<c> b;

    @Nullable
    public MediaItemMetadata c;
    public a0 d;
    public a0 e;
    public boolean f;
    public d g;

    public class a extends DiffUtil.ItemCallback<j0> {
        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public final boolean areContentsTheSame(@NonNull j0 j0Var, @NonNull j0 j0Var2) {
            return j0Var.equals(j0Var2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        public final boolean areItemsTheSame(@NonNull j0 j0Var, @NonNull j0 j0Var2) {
            j0 j0Var3 = j0Var;
            j0 j0Var4 = j0Var2;
            return Objects.equals(j0Var3.a, j0Var4.a) && Objects.equals(j0Var3.c, j0Var4.c);
        }
    }

    public static abstract class c {
        public void a(@NonNull MediaItemMetadata mediaItemMetadata) {
        }

        public void b(@NonNull MediaItemMetadata mediaItemMetadata) {
        }
    }

    public interface d {
    }

    public BrowseAdapter(@NonNull Context context) {
        super(h);
        this.b = new ArrayList();
        a0 a0Var = a0.LIST_ITEM;
        this.d = a0Var;
        this.e = a0Var;
        this.f = false;
        this.a = context;
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [java.util.ArrayList, java.util.List<com.android.car.media.browse.BrowseAdapter$c>] */
    public static void d(BrowseAdapter browseAdapter, Consumer consumer) {
        Iterator it = browseAdapter.b.iterator();
        while (it.hasNext()) {
            consumer.accept((c) it.next());
        }
    }

    public final a0 e(int i) {
        return i != 2 ? i != 3 ? i != 4 ? a0.LIST_ITEM : a0.ICON_GRID_ITEM : a0.ICON_LIST_ITEM : a0.GRID_ITEM;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return getItem(i).b.ordinal();
    }

    /* JADX WARN: Removed duplicated region for block: B:65:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x012f  */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onBindViewHolder(@androidx.annotation.NonNull androidx.recyclerview.widget.RecyclerView.ViewHolder r9, int r10) {
        /*
            Method dump skipped, instruction units count: 351
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.browse.BrowseAdapter.onBindViewHolder(androidx.recyclerview.widget.RecyclerView$ViewHolder, int):void");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public final RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int i2 = a0.values()[i].a;
        Logs.d("BrowseAdapter", "BrowseViewHolder onCreateViewHolder layoutId:" + i2 + ",viewType:" + i);
        return new BrowseViewHolder(LayoutInflater.from(this.a).inflate(i2, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.ListAdapter
    public final void onCurrentListChanged(@NonNull List<j0> list, @NonNull List<j0> list2) {
        super.onCurrentListChanged(list, list2);
        d dVar = this.g;
        if (dVar != null) {
            LimitedBrowseAdapter limitedBrowseAdapter = (LimitedBrowseAdapter) ((q6) dVar).a;
            Objects.requireNonNull(limitedBrowseAdapter);
            int size = list2.size();
            int i = 0;
            if (limitedBrowseAdapter.l != null) {
                List<j0> currentList = limitedBrowseAdapter.j.getCurrentList();
                int i2 = 0;
                while (true) {
                    if (i2 >= currentList.size()) {
                        limitedBrowseAdapter.l = null;
                        break;
                    }
                    MediaItemMetadata mediaItemMetadata = currentList.get(i2).a;
                    if (mediaItemMetadata != null && limitedBrowseAdapter.l.equals(mediaItemMetadata.b())) {
                        i = i2;
                        break;
                    }
                    i2++;
                }
            }
            limitedBrowseAdapter.b.a(size, i);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewAttachedToWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        BrowseViewHolder browseViewHolder = (BrowseViewHolder) viewHolder;
        super.onViewAttachedToWindow(browseViewHolder);
        Context context = this.a;
        x4<MediaItemMetadata.b> x4Var = browseViewHolder.i;
        if (x4Var.k) {
            x4Var.d(context, x4Var.j);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onViewDetachedFromWindow(@NonNull RecyclerView.ViewHolder viewHolder) {
        BrowseViewHolder browseViewHolder = (BrowseViewHolder) viewHolder;
        super.onViewDetachedFromWindow(browseViewHolder);
        browseViewHolder.i.e(this.a);
    }

    public void setOnListChangedListener(d dVar) {
        this.g = dVar;
    }

    public class b {
        public List<j0> a;

        private b() {
            this.a = new ArrayList();
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [java.util.ArrayList, java.util.List<spi.j0>] */
        public final void a(MediaItemMetadata mediaItemMetadata, a0 a0Var, final Consumer consumer, boolean z) {
            final int i = 1;
            this.a.add(new j0(mediaItemMetadata, a0Var, new View.OnClickListener(this) { // from class: spi.z
                public final /* synthetic */ BrowseAdapter.b b;

                {
                    this.b = this;
                }

                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    switch (i) {
                        case 0:
                            BrowseAdapter.b bVar = this.b;
                            BrowseAdapter.d(BrowseAdapter.this, consumer);
                            break;
                        default:
                            BrowseAdapter.b bVar2 = this.b;
                            BrowseAdapter.d(BrowseAdapter.this, consumer);
                            break;
                    }
                }
            }, false, z));
        }

        /* JADX WARN: Type inference failed for: r6v1, types: [java.util.ArrayList, java.util.List<spi.j0>] */
        public final void b(CharSequence charSequence) {
            if (charSequence == null) {
                charSequence = "";
            }
            this.a.add(new j0(charSequence, a0.HEADER, (View.OnClickListener) null, false, false));
        }

        public /* synthetic */ b(BrowseAdapter browseAdapter, a aVar) {
            this();
        }
    }
}
