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
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class StartSubAdapter extends RecyclerView.Adapter<StartSubHolder> {
    private static final String TAG = "StartSubAdapter";
    private ClassifyData classifyData;
    private List<ContentItem> data;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public StartSubHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new StartSubHolder(LayoutInflater.from(MApp.context).inflate(R.layout.start_sub_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(StartSubHolder holder, int position) {
        NormalDataAdapter.bindHolderItem(holder.item, holder.header, holder.cover, holder.footer, holder.path, holder.playLogo, this.data, position, 2);
        registerClickEvent(holder, position);
    }

    private void registerClickEvent(final StartSubHolder holder, final int position) {
        holder.item.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$StartSubAdapter$JFKQqfKsTYUmEUUD-Zwgl0sjIRY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$registerClickEvent$0$StartSubAdapter(holder, position, view);
            }
        });
    }

    public /* synthetic */ void lambda$registerClickEvent$0$StartSubAdapter(StartSubHolder startSubHolder, int i, View view) {
        String classify = this.classifyData.getClassify();
        String string = startSubHolder.header.getText().toString();
        String string2 = startSubHolder.cover.getTag() == null ? "" : startSubHolder.cover.getTag().toString();
        String string3 = startSubHolder.path.getText().toString();
        ContentItem.ContentItemType contentItemType = (ContentItem.ContentItemType) startSubHolder.path.getTag();
        String modelTypeName = this.classifyData.getModelTypeName();
        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + string + "\n " + string2 + "\n " + string3 + "\n " + contentItemType);
        NormalDataAdapter.startMediaActivity(classify, string, string2, string3, contentItemType, i, modelTypeName);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        List<ContentItem> list = this.data;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    public class StartSubHolder extends RecyclerView.ViewHolder {
        private KImageView cover;
        private TextView footer;
        private TextView header;
        private RelativeLayout item;
        private TextView path;
        private ImageView playLogo;

        public StartSubHolder(View itemView) {
            super(itemView);
            RelativeLayout relativeLayout = (RelativeLayout) itemView.findViewById(R.id.start_sub_item);
            this.item = relativeLayout;
            this.header = (TextView) relativeLayout.findViewById(R.id.content_item_header);
            this.cover = (KImageView) this.item.findViewById(R.id.content_item_cover);
            this.footer = (TextView) this.item.findViewById(R.id.content_item_footer);
            this.path = (TextView) this.item.findViewById(R.id.content_item_path);
            this.playLogo = (ImageView) this.item.findViewById(R.id.content_item_play_logo);
        }
    }

    public void setData(ClassifyData classifyData) {
        MLog.d(TAG, "setData");
        this.classifyData = classifyData;
        this.data = NormalDataAdapter.getStartData(classifyData);
        notifyDataSetChanged();
    }
}
