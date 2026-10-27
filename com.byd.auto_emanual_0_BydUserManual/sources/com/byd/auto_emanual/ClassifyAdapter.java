package com.byd.auto_emanual;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.MediaActivity;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.ViewUtil;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ClassifyAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private static final String TAG = "ClassifyAdapter";
    private MediaActivity.ClickListener clickListener;
    private final Context context;
    private List<ContentItem> data = new ArrayList();
    private int orientation;
    private VideoViewHolder selectedHolder;
    private int selectedPosition;

    public ClassifyAdapter(Context context, int orientation) {
        this.context = context;
        this.orientation = orientation;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public VideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VideoViewHolder(LayoutInflater.from(MApp.context).inflate(R.layout.classify_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(VideoViewHolder holder, int position) {
        bindViewHolderImpl(holder, position);
        registerClickEvent(holder, position);
    }

    private void bindViewHolderImpl(final VideoViewHolder holder, int position) {
        ContentItem.bindHolderItem(holder.header, holder.cover, holder.footer, holder.path, holder.playLogo, this.data.get(position));
        holder.header.setText((position + 1) + "." + ((Object) holder.header.getText()));
        holder.itemView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.byd.auto_emanual.ClassifyAdapter.1
            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                holder.itemView.getViewTreeObserver().removeOnPreDrawListener(this);
                int lineCount = holder.header.getLineCount();
                ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
                if (ViewUtil.isPort(ClassifyAdapter.this.orientation)) {
                    layoutParams.height = holder.itemView.getResources().getDimensionPixelOffset(R.dimen.classify_item_height_port);
                    layoutParams.width = holder.itemView.getResources().getDimensionPixelOffset(R.dimen.classify_item_width_port);
                } else {
                    layoutParams.height = holder.itemView.getResources().getDimensionPixelOffset(R.dimen.classify_item_height);
                    layoutParams.height += (lineCount - 1) * holder.header.getResources().getDimensionPixelOffset(R.dimen.classify_item_height_increment);
                    layoutParams.width = holder.itemView.getResources().getDimensionPixelOffset(R.dimen.classify_item_width);
                }
                holder.itemView.setLayoutParams(layoutParams);
                return true;
            }
        });
        if (TextUtils.isEmpty(this.data.get(position).getCoverPath())) {
            holder.cover.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.manual_cover));
            holder.cover.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
        if (position == this.selectedPosition && this.selectedHolder == null) {
            setItemViewBackground(holder);
            holder.header.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.classify_title_text_selected));
            this.selectedPosition = position;
            this.selectedHolder = holder;
        }
        if (position != this.selectedPosition) {
            holder.itemView.setBackgroundColor(0);
            holder.header.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.classify_title_text));
        } else {
            setItemViewBackground(holder);
            holder.header.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.classify_title_text_selected));
            this.selectedHolder = holder;
        }
        holder.line.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.classify_line));
        MLog.v(TAG, "\n" + this.data.get(position).getHeader() + "\n" + this.data.get(position).getCoverUri() + "\n" + this.data.get(position).getCoverPath() + "\n" + this.data.get(position).getFooter() + "\n" + this.data.get(position).getPath());
    }

    private void registerClickEvent(final VideoViewHolder holder, final int position) {
        holder.item.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.-$$Lambda$ClassifyAdapter$cqYiwyKHgUo_KdsOTpyoMQnFW-Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$registerClickEvent$0$ClassifyAdapter(holder, position, view);
            }
        });
    }

    public /* synthetic */ void lambda$registerClickEvent$0$ClassifyAdapter(VideoViewHolder videoViewHolder, int i, View view) {
        String string = videoViewHolder.header.getText().toString();
        String strValueOf = String.valueOf(R.drawable.video_loading_background);
        if (videoViewHolder.cover.getTag() != null) {
            strValueOf = videoViewHolder.cover.getTag().toString();
        }
        String string2 = videoViewHolder.path.getText().toString();
        ContentItem.ContentItemType contentItemType = (ContentItem.ContentItemType) videoViewHolder.path.getTag();
        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + strValueOf + "\n " + string2);
        VideoViewHolder videoViewHolder2 = this.selectedHolder;
        if (videoViewHolder2 != null) {
            videoViewHolder2.itemView.setBackgroundColor(0);
            this.selectedHolder.header.setTextColor(ContextCompat.getColor(videoViewHolder.itemView.getContext(), R.color.classify_title_text));
        }
        setItemViewBackground(videoViewHolder);
        videoViewHolder.header.setTextColor(ContextCompat.getColor(videoViewHolder.itemView.getContext(), R.color.color_primary));
        this.selectedHolder = videoViewHolder;
        this.selectedPosition = i;
        if (this.clickListener != null) {
            Intent intent = new Intent();
            intent.setClass(MApp.context, MediaActivity.class);
            intent.putExtra("1", string);
            intent.putExtra(Parameters.CONTENT_ITEM_COVER_PATH, strValueOf);
            intent.putExtra(Parameters.CONTENT_ITEM_PATH, string2);
            intent.putExtra(Parameters.CONTENT_TYPE, contentItemType);
            this.clickListener.onClick(intent);
        }
    }

    public void setItemViewBackground(VideoViewHolder holder) {
        if (ViewUtil.isRtl(holder.itemView.getContext().getResources().getConfiguration().getLayoutDirection())) {
            holder.itemView.setBackground(ContextCompat.getDrawable(this.context, R.drawable.content_item_background_rtl));
        } else {
            holder.itemView.setBackground(ContextCompat.getDrawable(this.context, R.drawable.content_item_header_background));
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ContentItem> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public void setData(List<ContentItem> data, int selectedPosition, int orientation) {
        this.data = data;
        this.selectedPosition = selectedPosition;
        this.orientation = orientation;
        notifyDataSetChanged();
    }

    public void updateOrientation(int orientation) {
        this.orientation = orientation;
        notifyDataSetChanged();
    }

    public void setClickListener(MediaActivity.ClickListener listener) {
        this.clickListener = listener;
    }

    public class VideoViewHolder extends RecyclerView.ViewHolder {
        private final KImageView cover;
        private final TextView footer;
        private final ImageView foreground;
        private final TextView header;
        private final RelativeLayout item;
        private final View line;
        private final TextView path;
        private final ImageView playLogo;

        public VideoViewHolder(View itemView) {
            super(itemView);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.video_view_item);
            this.item = relativeLayout;
            this.header = (TextView) relativeLayout.findViewById(R.id.content_item_header);
            this.cover = (KImageView) relativeLayout.findViewById(R.id.content_item_cover);
            this.footer = (TextView) relativeLayout.findViewById(R.id.content_item_footer);
            this.path = (TextView) relativeLayout.findViewById(R.id.content_item_path);
            this.foreground = (ImageView) itemView.findViewById(R.id.foreground);
            this.playLogo = (ImageView) itemView.findViewById(R.id.content_item_play_logo);
            this.line = itemView.findViewById(R.id.content_item_line);
        }
    }
}
