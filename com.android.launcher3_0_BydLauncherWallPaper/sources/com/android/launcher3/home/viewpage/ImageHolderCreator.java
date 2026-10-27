package com.android.launcher3.home.viewpage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.android.launcher3.R;
import com.android.launcher3.home.banner.HolderCreator;

/* JADX INFO: loaded from: classes4.dex */
public class ImageHolderCreator implements HolderCreator {
    @Override // com.android.launcher3.home.banner.HolderCreator
    public View createView(final Context context, final int index, WallPage wallPage) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.page_item_layout, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.iv_wallpager);
        imageView.setTag(wallPage);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.launcher3.home.viewpage.ImageHolderCreator.1
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
            }
        });
        return viewInflate;
    }
}
