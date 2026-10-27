package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.LinkNavigatorItemElement;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/* JADX INFO: loaded from: classes4.dex */
final class LinkNavigatorItemViewHolder extends RecyclerView.ViewHolder {
    private final ImageView icon;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final Resources resources;
    private final TextView text;

    public LinkNavigatorItemViewHolder(final View view, final String ownerId, final MethodsDispatcher methodsDispatcher, final Resources resources) {
        super(view);
        this.resources = resources;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.text = (TextView) view.findViewById(R.id.link_navigator_item_text);
        this.icon = (ImageView) view.findViewById(R.id.link_navigator_item_icon);
    }

    public void bind(final LinkNavigatorItemElement element) {
        RequestCreator requestCreatorLoad = Picasso.get().load(element.icon());
        int i = R.drawable.ic_chevron_right;
        RequestCreator requestCreatorError = requestCreatorLoad.placeholder(i).error(i);
        Resources resources = this.resources;
        int i2 = R.dimen.spacer_24;
        requestCreatorError.resize((int) resources.getDimension(i2), (int) this.resources.getDimension(i2)).into(this.icon);
        this.text.setText(element.mainText());
        this.itemView.setContentDescription(element.mainText());
        this.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.LinkNavigatorItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LinkNavigatorItemViewHolder.this.methodsDispatcher.dispatchMethods(LinkNavigatorItemViewHolder.this.ownerId, element.onItemSelected());
            }
        });
    }
}
