package com.android.car.media.browse;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.main.R$id;
import spi.s6;
import spi.x4;

/* JADX INFO: loaded from: classes.dex */
public class BrowseViewHolder extends RecyclerView.ViewHolder {
    public final TextView a;
    public final TextView b;
    public final ViewGroup c;
    public final ImageView d;
    public final ImageView e;
    public final ImageView f;
    public final ImageView g;
    public final ImageView h;
    public final x4<MediaItemMetadata.b> i;

    public BrowseViewHolder(View view) {
        super(view);
        this.a = (TextView) view.findViewById(R$id.title);
        this.b = (TextView) view.findViewById(R$id.subtitle);
        ImageView imageView = (ImageView) view.findViewById(R$id.thumbnail);
        this.c = (ViewGroup) view.findViewById(R$id.item_container);
        this.d = (ImageView) view.findViewById(R$id.right_arrow);
        this.e = (ImageView) view.findViewById(R$id.download_icon_with_title);
        this.f = (ImageView) view.findViewById(R$id.explicit_icon_with_title);
        this.g = (ImageView) view.findViewById(R$id.download_icon_with_subtitle);
        this.h = (ImageView) view.findViewById(R$id.explicit_icon_with_subtitle);
        this.i = new x4<>(s6.a(view.getContext()), imageView);
    }
}
