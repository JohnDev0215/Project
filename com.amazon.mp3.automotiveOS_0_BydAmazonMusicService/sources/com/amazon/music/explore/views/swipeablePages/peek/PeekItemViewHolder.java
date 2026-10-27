package com.amazon.music.explore.views.swipeablePages.peek;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.explore.R;

/* JADX INFO: loaded from: classes4.dex */
public class PeekItemViewHolder extends RecyclerView.ViewHolder {
    public ImageView image;
    public TextView text;

    public PeekItemViewHolder(@NonNull View view) {
        super(view);
        this.image = (ImageView) view.findViewById(R.id.peek_image);
        this.text = (TextView) view.findViewById(R.id.peek_title);
        setFontFamily();
    }

    private void setFontFamily() {
        this.text.setTypeface(Typeface.createFromAsset(this.text.getResources().getAssets(), this.text.getResources().getString(R.string.font_amazon_ember_bold)));
    }
}
