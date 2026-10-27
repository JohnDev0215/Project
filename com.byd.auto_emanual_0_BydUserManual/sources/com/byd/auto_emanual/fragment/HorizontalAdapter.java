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
public abstract class HorizontalAdapter extends RecyclerView.Adapter<RecommendHorizontalHolder> implements IAdapter {
    public static int DEFAULT_HORIZONTAL_COLUMN_COUNT = 4;
    public static int FROM_INDEX_IN_ALL = NormalDataAdapter.VIDEO_PLAYER_COUNT + (RecommendVerticalAdapter.DEFAULT_VERTICAL_COLUMN_COUNT * RecommendVerticalAdapter.DEFAULT_VERTICAL_ROW_COUNT);
    private static final String TAG = "HorizontalAdapter";
    protected ClassifyData classifyData;
    protected List<ContentItem> data = new ArrayList();
    protected int orientation;

    @Override // com.byd.auto_emanual.fragment.IAdapter
    public abstract void setData(ClassifyData data, int orientation);

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecommendHorizontalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecommendHorizontalHolder(LayoutInflater.from(MApp.context).inflate(R.layout.recommend_horizontal_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecommendHorizontalHolder holder, int position) {
        onBindViewHolderImpl(holder, position);
        registerClickEvent(holder, position);
    }

    private void onBindViewHolderImpl(RecommendHorizontalHolder holder, int position) {
        int i = DEFAULT_HORIZONTAL_COLUMN_COUNT * position;
        NormalDataAdapter.bindHolderItem(holder.item1, holder.header1, holder.cover1, holder.footer1, holder.path1, holder.playLogo1, this.data, i, this.orientation);
        int i2 = i + 1;
        NormalDataAdapter.bindHolderItem(holder.item2, holder.header2, holder.cover2, holder.footer2, holder.path2, holder.playLogo2, this.data, i2, this.orientation);
        int i3 = i2 + 1;
        NormalDataAdapter.bindHolderItem(holder.item3, holder.header3, holder.cover3, holder.footer3, holder.path3, holder.playLogo3, this.data, i3, this.orientation);
        NormalDataAdapter.bindHolderItem(holder.item4, holder.header4, holder.cover4, holder.footer4, holder.path4, holder.playLogo4, this.data, i3 + 1, this.orientation);
    }

    private void registerClickEvent(final RecommendHorizontalHolder holder, final int position) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$HorizontalAdapter$epQ5Mjl2wGaNCnVzVmzCngEA35Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$registerClickEvent$0$HorizontalAdapter(position, holder, view);
            }
        };
        holder.item1.setOnClickListener(onClickListener);
        holder.item2.setOnClickListener(onClickListener);
        holder.item3.setOnClickListener(onClickListener);
        holder.item4.setOnClickListener(onClickListener);
    }

    public /* synthetic */ void lambda$registerClickEvent$0$HorizontalAdapter(int i, RecommendHorizontalHolder recommendHorizontalHolder, View view) {
        String string;
        ContentItem.ContentItemType contentItemType;
        String str;
        String str2;
        String string2;
        String string3;
        ContentItem.ContentItemType contentItemType2;
        String classify = this.classifyData.getClassify();
        int i2 = i * DEFAULT_HORIZONTAL_COLUMN_COUNT;
        string = "";
        if (view.getId() != R.id.horizontal_item_1) {
            if (view.getId() != R.id.horizontal_item_2) {
                if (view.getId() != R.id.horizontal_item_3) {
                    if (view.getId() != R.id.horizontal_item_4) {
                        contentItemType = null;
                        str = "";
                        str2 = str;
                        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + string + "\n " + str + "\n " + str2 + "\n " + contentItemType);
                        NormalDataAdapter.startMediaActivity(classify, string, str, str2, contentItemType, i2 + getFromIndexInAll(), null);
                    }
                    string2 = recommendHorizontalHolder.header4.getText().toString();
                    string = recommendHorizontalHolder.cover4.getTag() != null ? recommendHorizontalHolder.cover4.getTag().toString() : "";
                    string3 = recommendHorizontalHolder.path4.getText().toString();
                    contentItemType2 = (ContentItem.ContentItemType) recommendHorizontalHolder.path4.getTag();
                    i2 += 3;
                } else {
                    string2 = recommendHorizontalHolder.header3.getText().toString();
                    string = recommendHorizontalHolder.cover3.getTag() != null ? recommendHorizontalHolder.cover3.getTag().toString() : "";
                    string3 = recommendHorizontalHolder.path3.getText().toString();
                    contentItemType2 = (ContentItem.ContentItemType) recommendHorizontalHolder.path3.getTag();
                    i2 += 2;
                }
            } else {
                string2 = recommendHorizontalHolder.header2.getText().toString();
                string = recommendHorizontalHolder.cover2.getTag() != null ? recommendHorizontalHolder.cover2.getTag().toString() : "";
                string3 = recommendHorizontalHolder.path2.getText().toString();
                contentItemType2 = (ContentItem.ContentItemType) recommendHorizontalHolder.path2.getTag();
                i2++;
            }
        } else {
            string2 = recommendHorizontalHolder.header1.getText().toString();
            string = recommendHorizontalHolder.cover1.getTag() != null ? recommendHorizontalHolder.cover1.getTag().toString() : "";
            string3 = recommendHorizontalHolder.path1.getText().toString();
            contentItemType2 = (ContentItem.ContentItemType) recommendHorizontalHolder.path1.getTag();
            i2 += 0;
        }
        contentItemType = contentItemType2;
        str2 = string3;
        str = string;
        string = string2;
        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + string + "\n " + str + "\n " + str2 + "\n " + contentItemType);
        NormalDataAdapter.startMediaActivity(classify, string, str, str2, contentItemType, i2 + getFromIndexInAll(), null);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int size = this.data.size();
        return ((size + r1) - 1) / DEFAULT_HORIZONTAL_COLUMN_COUNT;
    }

    public class RecommendHorizontalHolder extends RecyclerView.ViewHolder {
        private KImageView cover1;
        private KImageView cover2;
        private KImageView cover3;
        private KImageView cover4;
        private TextView footer1;
        private TextView footer2;
        private TextView footer3;
        private TextView footer4;
        private TextView header1;
        private TextView header2;
        private TextView header3;
        private TextView header4;
        private RelativeLayout item1;
        private RelativeLayout item2;
        private RelativeLayout item3;
        private RelativeLayout item4;
        private TextView path1;
        private TextView path2;
        private TextView path3;
        private TextView path4;
        private ImageView playLogo1;
        private ImageView playLogo2;
        private ImageView playLogo3;
        private ImageView playLogo4;

        public RecommendHorizontalHolder(View itemView) {
            super(itemView);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.horizontal_item_1);
            this.item1 = relativeLayout;
            this.header1 = (TextView) relativeLayout.findViewById(R.id.content_item_header);
            this.cover1 = (KImageView) this.item1.findViewById(R.id.content_item_cover);
            this.footer1 = (TextView) this.item1.findViewById(R.id.content_item_footer);
            this.path1 = (TextView) this.item1.findViewById(R.id.content_item_path);
            this.playLogo1 = (ImageView) this.item1.findViewById(R.id.content_item_play_logo);
            RelativeLayout relativeLayout2 = (RelativeLayout) itemView.findViewById(R.id.horizontal_item_2);
            this.item2 = relativeLayout2;
            this.header2 = (TextView) relativeLayout2.findViewById(R.id.content_item_header);
            this.cover2 = (KImageView) this.item2.findViewById(R.id.content_item_cover);
            this.footer2 = (TextView) this.item2.findViewById(R.id.content_item_footer);
            this.path2 = (TextView) this.item2.findViewById(R.id.content_item_path);
            this.playLogo2 = (ImageView) this.item2.findViewById(R.id.content_item_play_logo);
            RelativeLayout relativeLayout3 = (RelativeLayout) itemView.findViewById(R.id.horizontal_item_3);
            this.item3 = relativeLayout3;
            this.header3 = (TextView) relativeLayout3.findViewById(R.id.content_item_header);
            this.cover3 = (KImageView) this.item3.findViewById(R.id.content_item_cover);
            this.footer3 = (TextView) this.item3.findViewById(R.id.content_item_footer);
            this.path3 = (TextView) this.item3.findViewById(R.id.content_item_path);
            this.playLogo3 = (ImageView) this.item3.findViewById(R.id.content_item_play_logo);
            RelativeLayout relativeLayout4 = (RelativeLayout) itemView.findViewById(R.id.horizontal_item_4);
            this.item4 = relativeLayout4;
            this.header4 = (TextView) relativeLayout4.findViewById(R.id.content_item_header);
            this.cover4 = (KImageView) this.item4.findViewById(R.id.content_item_cover);
            this.footer4 = (TextView) this.item4.findViewById(R.id.content_item_footer);
            this.path4 = (TextView) this.item4.findViewById(R.id.content_item_path);
            this.playLogo4 = (ImageView) this.item4.findViewById(R.id.content_item_play_logo);
        }
    }

    public int getFromIndexInAll() {
        return FROM_INDEX_IN_ALL;
    }
}
