package com.byd.auto_emanual;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.util.HtmlTagHandler;
import com.byd.auto_emanual.util.ListUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ImageTextAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<String> data;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.media_image_text_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        String str = this.data.get(position);
        holder.imageView.setVisibility(8);
        holder.text.setVisibility(8);
        if (!str.startsWith(HtmlUtil.IMAGE)) {
            if (str.startsWith(HtmlUtil.TEXT)) {
                holder.text.setVisibility(0);
                holder.text.setText(Html.fromHtml(str.substring(5), 0, null, new HtmlTagHandler(holder.text.getContext(), HtmlTagHandler.HTML_TAG)));
                holder.text.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.media_image_text_context_text_color));
                return;
            }
            return;
        }
        holder.imageView.setVisibility(0);
        holder.imageView.setImagePath(str.substring(6));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (ListUtil.isNullOrEmpty(this.data)) {
            return 0;
        }
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private KImageView imageView;
        private TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            this.text = (TextView) itemView.findViewById(R.id.content_text);
            KImageView kImageView = (KImageView) itemView.findViewById(R.id.content_image);
            this.imageView = kImageView;
            kImageView.setCalculateHeight(true);
            this.imageView.setErrorResId(R.drawable.net_error_image);
        }
    }

    public void setData(List<String> data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
