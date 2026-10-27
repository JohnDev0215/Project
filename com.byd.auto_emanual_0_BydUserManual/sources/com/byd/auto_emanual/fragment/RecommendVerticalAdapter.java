package com.byd.auto_emanual.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.util.MLog;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RecommendVerticalAdapter extends RecyclerView.Adapter<RecommendVerticalHolder> implements IAdapter {
    public static int DEFAULT_VERTICAL_COLUMN_COUNT = 2;
    public static int DEFAULT_VERTICAL_ROW_COUNT = 2;
    public static int FROM_INDEX_IN_ALL = NormalDataAdapter.VIDEO_PLAYER_COUNT;
    private static final String TAG = "RecommendVerticalAdapter";
    private ClassifyData classifyData;
    private List<ContentItem> data = new ArrayList();
    private int orientation;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecommendVerticalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendVerticalHolder(LayoutInflater.from(MApp.context).inflate(R.layout.recommend_vertical_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecommendVerticalHolder holder, int position) {
        bindViewHolderImpl(holder, position);
        registerClickEvent(holder, position);
    }

    private void bindViewHolderImpl(RecommendVerticalHolder holder, int position) {
        MLog.v(TAG, "bindViewHolderImpl dataFromIndex = " + position + ", index = " + position);
        NormalDataAdapter.bindHolderItem(holder.item1, holder.header1, holder.cover1, holder.footer1, holder.path1, holder.playLogo1, this.data, position, this.orientation);
        NormalDataAdapter.bindHolderItem(holder.item2, holder.header2, holder.cover2, holder.footer2, holder.path2, holder.playLogo2, this.data, position + DEFAULT_VERTICAL_COLUMN_COUNT, this.orientation);
        if (position == getItemCount() - 1) {
            holder.interval.setVisibility(8);
        }
    }

    private void registerClickEvent(final RecommendVerticalHolder holder, final int position) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$RecommendVerticalAdapter$wCquMO6bl-XZI0egXhtNAAxkxLQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$registerClickEvent$0$RecommendVerticalAdapter(position, holder, view);
            }
        };
        holder.item1.setOnClickListener(onClickListener);
        holder.item2.setOnClickListener(onClickListener);
    }

    public /* synthetic */ void lambda$registerClickEvent$0$RecommendVerticalAdapter(int i, RecommendVerticalHolder recommendVerticalHolder, View view) {
        String string;
        ContentItem.ContentItemType contentItemType;
        String str;
        String str2;
        String string2;
        String string3;
        ContentItem.ContentItemType contentItemType2;
        String classify = this.classifyData.getClassify();
        string = "";
        if (view.getId() != R.id.vertical_item_1) {
            if (view.getId() != R.id.vertical_item_2) {
                contentItemType = null;
                str = "";
                str2 = str;
                MLog.v(TAG, "view = " + view + "\n " + string + "\n " + str + "\n " + str2 + "\n " + contentItemType);
                NormalDataAdapter.startMediaActivity(classify, string, str, str2, contentItemType, i + FROM_INDEX_IN_ALL, null);
            }
            string2 = recommendVerticalHolder.header2.getText().toString();
            string = recommendVerticalHolder.cover2.getTag() != null ? recommendVerticalHolder.cover2.getTag().toString() : "";
            string3 = recommendVerticalHolder.path2.getText().toString();
            contentItemType2 = (ContentItem.ContentItemType) recommendVerticalHolder.path2.getTag();
            i += DEFAULT_VERTICAL_ROW_COUNT;
        } else {
            string2 = recommendVerticalHolder.header1.getText().toString();
            string = recommendVerticalHolder.cover1.getTag() != null ? recommendVerticalHolder.cover1.getTag().toString() : "";
            string3 = recommendVerticalHolder.path1.getText().toString();
            contentItemType2 = (ContentItem.ContentItemType) recommendVerticalHolder.path1.getTag();
            i += 0;
        }
        contentItemType = contentItemType2;
        str2 = string3;
        str = string;
        string = string2;
        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + str + "\n " + str2 + "\n " + contentItemType);
        NormalDataAdapter.startMediaActivity(classify, string, str, str2, contentItemType, i + FROM_INDEX_IN_ALL, null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return Math.min(DEFAULT_VERTICAL_COLUMN_COUNT, this.data.size());
    }

    public class RecommendVerticalHolder extends RecyclerView.ViewHolder {
        private KImageView cover1;
        private KImageView cover2;
        private TextView footer1;
        private TextView footer2;
        private TextView header1;
        private TextView header2;
        private View interval;
        private RelativeLayout item1;
        private RelativeLayout item2;
        private TextView path1;
        private TextView path2;
        private ImageView playLogo1;
        private ImageView playLogo2;

        public RecommendVerticalHolder(View itemView) {
            super(itemView);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.vertical_item_1);
            this.item1 = relativeLayout;
            this.header1 = (TextView) relativeLayout.findViewById(R.id.content_item_header);
            this.cover1 = (KImageView) this.item1.findViewById(R.id.content_item_cover);
            this.footer1 = (TextView) this.item1.findViewById(R.id.content_item_footer);
            this.path1 = (TextView) this.item1.findViewById(R.id.content_item_path);
            this.playLogo1 = (ImageView) this.item1.findViewById(R.id.content_item_play_logo);
            RelativeLayout relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.vertical_item_2);
            this.item2 = relativeLayout2;
            this.header2 = (TextView) relativeLayout2.findViewById(R.id.content_item_header);
            this.cover2 = (KImageView) this.item2.findViewById(R.id.content_item_cover);
            this.footer2 = (TextView) this.item2.findViewById(R.id.content_item_footer);
            this.path2 = (TextView) this.item2.findViewById(R.id.content_item_path);
            this.playLogo2 = (ImageView) this.item2.findViewById(R.id.content_item_play_logo);
            this.interval = itemView.findViewById(R.id.interval_width);
        }
    }

    @Override // com.byd.auto_emanual.fragment.IAdapter
    public void setData(ClassifyData data, int orientation) {
        this.orientation = orientation;
        this.classifyData = data;
        this.data = NormalDataAdapter.getVerticalData(data);
        notifyDataSetChanged();
    }
}
