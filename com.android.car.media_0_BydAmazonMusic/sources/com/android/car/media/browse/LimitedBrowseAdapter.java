package com.android.car.media.browse;

import android.util.Log;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import com.android.car.media.browse.BrowseAdapter;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.main.R$id;
import com.android.car.ui.recyclerview.CarUiRecyclerView;
import com.android.car.ui.recyclerview.DelegatingContentLimitingAdapter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import spi.a0;
import spi.e1;
import spi.j0;
import spi.q6;

/* JADX INFO: loaded from: classes.dex */
public class LimitedBrowseAdapter extends DelegatingContentLimitingAdapter<BrowseViewHolder> {
    public final CarUiRecyclerView i;
    public final BrowseAdapter j;
    public int k;

    @Nullable
    public String l;
    public final a m;

    public class a extends GridLayoutManager.SpanSizeLookup {
        public a() {
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public final int getSpanSize(int i) {
            int itemViewType = LimitedBrowseAdapter.this.getItemViewType(i);
            LimitedBrowseAdapter limitedBrowseAdapter = LimitedBrowseAdapter.this;
            if (itemViewType == limitedBrowseAdapter.f) {
                return limitedBrowseAdapter.k;
            }
            int iD = limitedBrowseAdapter.b.d(i);
            LimitedBrowseAdapter limitedBrowseAdapter2 = LimitedBrowseAdapter.this;
            BrowseAdapter browseAdapter = limitedBrowseAdapter2.j;
            int i2 = limitedBrowseAdapter2.k;
            int i3 = browseAdapter.getItem(iD).b.b;
            return i3 < 0 ? i2 : i3;
        }
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [java.util.ArrayList, java.util.List<com.android.car.media.browse.BrowseAdapter$c>] */
    public LimitedBrowseAdapter(CarUiRecyclerView carUiRecyclerView, BrowseAdapter browseAdapter, BrowseAdapter.c cVar) {
        super(browseAdapter, R$id.browse_list_uxr_config);
        this.k = 1;
        a aVar = new a();
        this.m = aVar;
        this.i = carUiRecyclerView;
        this.j = browseAdapter;
        e1 e1Var = (e1) carUiRecyclerView.getLayoutStyle();
        if (e1Var != null) {
            this.k = e1Var.a;
            e1Var.d = aVar;
        } else {
            Log.e("LimitedBrowseAdapter", "layoutStyle == null !!!");
        }
        carUiRecyclerView.setLayoutStyle(e1Var);
        browseAdapter.b.add(cVar);
        browseAdapter.setOnListChangedListener(new q6(this));
    }

    @Override // com.android.car.ui.recyclerview.DelegatingContentLimitingAdapter, com.android.car.ui.recyclerview.ContentLimitingAdapter
    public final int d() {
        List<j0> currentList = this.j.getCurrentList();
        if (currentList.size() <= 0) {
            this.l = null;
            return 0;
        }
        int iFindFirstCompletelyVisibleItemPosition = this.i.findFirstCompletelyVisibleItemPosition();
        if (iFindFirstCompletelyVisibleItemPosition == -1) {
            iFindFirstCompletelyVisibleItemPosition = this.i.findFirstVisibleItemPosition();
        }
        int iFindLastCompletelyVisibleItemPosition = this.i.findLastCompletelyVisibleItemPosition();
        if (iFindLastCompletelyVisibleItemPosition == -1) {
            iFindLastCompletelyVisibleItemPosition = this.i.findLastVisibleItemPosition();
        }
        int i = (iFindFirstCompletelyVisibleItemPosition + iFindLastCompletelyVisibleItemPosition) / 2;
        if (i < 0 || i >= currentList.size()) {
            this.l = null;
            return 0;
        }
        MediaItemMetadata mediaItemMetadata = currentList.get(i).a;
        this.l = mediaItemMetadata != null ? mediaItemMetadata.b() : null;
        return i;
    }

    public final void o(@Nullable MediaItemMetadata mediaItemMetadata, @Nullable List<MediaItemMetadata> list) {
        BrowseAdapter browseAdapter = this.j;
        browseAdapter.c = mediaItemMetadata;
        final int i = 0;
        if (list == null) {
            browseAdapter.submitList(Collections.emptyList());
        } else {
            String str = null;
            BrowseAdapter.b bVar = new BrowseAdapter.b(browseAdapter, 0 == true ? 1 : 0);
            final int i2 = 1;
            if (Log.isLoggable("BrowseAdapter", 2)) {
                Log.v("BrowseAdapter", "Generating browse view from:");
                for (MediaItemMetadata mediaItemMetadata2 : list) {
                    Object[] objArr = new Object[4];
                    String str2 = " ";
                    objArr[0] = mediaItemMetadata2.c ? "B" : " ";
                    if (mediaItemMetadata2.d) {
                        str2 = "P";
                    }
                    objArr[1] = str2;
                    objArr[2] = mediaItemMetadata2.f();
                    objArr[3] = mediaItemMetadata2.b();
                    Log.v("BrowseAdapter", String.format("[%s%s] '%s' (%s)", objArr));
                }
            }
            if (!list.isEmpty()) {
                list.get(0).g();
            }
            for (final MediaItemMetadata mediaItemMetadata3 : list) {
                String strG = mediaItemMetadata3.g();
                if (!Objects.equals(str, strG) && !browseAdapter.f) {
                    bVar.b(strG);
                    str = strG;
                }
                if (mediaItemMetadata3.c) {
                    MediaItemMetadata mediaItemMetadata4 = browseAdapter.c;
                    bVar.a(mediaItemMetadata3, mediaItemMetadata4 == null ? a0.LIST_ITEM : mediaItemMetadata4.a() == 0 ? browseAdapter.d : browseAdapter.e(mediaItemMetadata4.a()), new Consumer() { // from class: spi.y
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            switch (i) {
                                case 0:
                                    MediaItemMetadata mediaItemMetadata5 = mediaItemMetadata3;
                                    DiffUtil.ItemCallback<j0> itemCallback = BrowseAdapter.h;
                                    ((BrowseAdapter.c) obj).a(mediaItemMetadata5);
                                    break;
                                default:
                                    MediaItemMetadata mediaItemMetadata6 = mediaItemMetadata3;
                                    DiffUtil.ItemCallback<j0> itemCallback2 = BrowseAdapter.h;
                                    ((BrowseAdapter.c) obj).b(mediaItemMetadata6);
                                    break;
                            }
                        }
                    }, browseAdapter.f);
                } else if (mediaItemMetadata3.d) {
                    MediaItemMetadata mediaItemMetadata5 = browseAdapter.c;
                    bVar.a(mediaItemMetadata3, mediaItemMetadata5 == null ? a0.LIST_ITEM : mediaItemMetadata5.d() == 0 ? browseAdapter.e : browseAdapter.e(mediaItemMetadata5.d()), new Consumer() { // from class: spi.y
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            switch (i2) {
                                case 0:
                                    MediaItemMetadata mediaItemMetadata52 = mediaItemMetadata3;
                                    DiffUtil.ItemCallback<j0> itemCallback = BrowseAdapter.h;
                                    ((BrowseAdapter.c) obj).a(mediaItemMetadata52);
                                    break;
                                default:
                                    MediaItemMetadata mediaItemMetadata6 = mediaItemMetadata3;
                                    DiffUtil.ItemCallback<j0> itemCallback2 = BrowseAdapter.h;
                                    ((BrowseAdapter.c) obj).b(mediaItemMetadata6);
                                    break;
                            }
                        }
                    }, browseAdapter.f);
                }
            }
            browseAdapter.submitList(bVar.a);
        }
        if (list == null) {
            this.b.a(0, 0);
        }
    }
}
