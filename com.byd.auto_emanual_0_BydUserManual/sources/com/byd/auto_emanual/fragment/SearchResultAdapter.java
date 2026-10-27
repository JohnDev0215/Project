package com.byd.auto_emanual.fragment;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.auto_emanual.ContentItem;
import com.byd.auto_emanual.MApp;
import com.byd.auto_emanual.R;
import com.byd.auto_emanual.image.KImageView;
import com.byd.auto_emanual.util.ListUtil;
import com.byd.auto_emanual.util.MLog;
import com.byd.auto_emanual.util.SearchHistoryUtil;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SearchResultAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String TAG = "SearchResultAdapter";
    private ClassifyData classifyData;
    private List<ContentItem> data;
    private String searchKey;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        onBindViewHolderImpl(holder, position);
        if (TextUtils.isEmpty(this.data.get(position).getCoverPath())) {
            holder.cover.setImageDrawable(ContextCompat.getDrawable(holder.itemView.getContext(), R.drawable.manual_cover));
        }
        updateHeaderColor(holder);
        updateFooterColor(holder);
        updateLineColor(holder);
        registerClickEvent(holder, position);
    }

    private void updateFooterColor(ViewHolder holder) {
        holder.footer.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.search_result_item_hint_text_color));
    }

    private void updateLineColor(ViewHolder holder) {
        holder.line.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.search_result_line_color));
    }

    private void updateHeaderColor(ViewHolder holder) {
        holder.header.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.search_result_item_text_color));
        int color = holder.itemView.getResources().getColor(R.color.search_key_color_in_result, null);
        CharSequence text = holder.header.getText();
        int iIndexOf = text.toString().indexOf(this.searchKey);
        int length = this.searchKey.length() + iIndexOf;
        if (iIndexOf >= 0 && length <= text.length()) {
            SpannableString spannableString = new SpannableString(holder.header.getText());
            spannableString.setSpan(new ForegroundColorSpan(color), iIndexOf, length, 33);
            holder.header.setText(spannableString);
            return;
        }
        int iIndexOf2 = text.toString().toUpperCase().indexOf(this.searchKey.toUpperCase());
        int length2 = this.searchKey.toUpperCase().length() + iIndexOf2;
        if (iIndexOf2 >= 0 && length2 <= text.length()) {
            SpannableString spannableString2 = new SpannableString(holder.header.getText());
            spannableString2.setSpan(new ForegroundColorSpan(color), iIndexOf2, length2, 33);
            holder.header.setText(spannableString2);
            return;
        }
        int iIndexOf3 = text.toString().toLowerCase().indexOf(this.searchKey.toLowerCase());
        int length3 = this.searchKey.toLowerCase().length() + iIndexOf3;
        if (iIndexOf3 < 0 || length3 > text.length()) {
            return;
        }
        SpannableString spannableString3 = new SpannableString(holder.header.getText());
        spannableString3.setSpan(new ForegroundColorSpan(color), iIndexOf3, length3, 33);
        holder.header.setText(spannableString3);
    }

    private void registerClickEvent(final ViewHolder holder, final int position) {
        holder.item.setOnClickListener(new View.OnClickListener() { // from class: com.byd.auto_emanual.fragment.-$$Lambda$SearchResultAdapter$vhp74PfOvsHeKd3W4CZvO393wdE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$registerClickEvent$0$SearchResultAdapter(holder, position, view);
            }
        });
    }

    public /* synthetic */ void lambda$registerClickEvent$0$SearchResultAdapter(ViewHolder viewHolder, int i, View view) {
        String classify = this.classifyData.getClassify();
        String string = viewHolder.header.getText().toString();
        String string2 = viewHolder.cover.getTag() == null ? "" : viewHolder.cover.getTag().toString();
        String string3 = viewHolder.path.getText().toString();
        ContentItem.ContentItemType contentItemType = (ContentItem.ContentItemType) viewHolder.path.getTag();
        String modelTypeName = this.classifyData.getModelTypeName();
        MLog.v(TAG, "view = " + view + "\n " + string + "\n " + string + "\n " + string2 + "\n " + string3 + "\n " + contentItemType);
        NormalDataAdapter.startMediaActivity(classify, string, string2, string3, contentItemType, i, modelTypeName);
        SearchHistoryUtil.updateHistory(MApp.context, classify);
    }

    private void onBindViewHolderImpl(ViewHolder holder, int position) {
        NormalDataAdapter.bindHolderItem(holder.item, holder.header, holder.cover, holder.footer, holder.path, holder.playLogo, this.data, position, -1);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (ListUtil.isNullOrEmpty(this.data)) {
            return 0;
        }
        return this.data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private KImageView cover;
        private TextView footer;
        private TextView header;
        private RelativeLayout item;
        private View line;
        private TextView path;
        private ImageView playLogo;

        public ViewHolder(View itemView) {
            super(itemView);
            this.item = (RelativeLayout) itemView.findViewById(R.id.result_item);
            this.cover = (KImageView) itemView.findViewById(R.id.cover);
            this.header = (TextView) itemView.findViewById(R.id.header);
            this.footer = (TextView) itemView.findViewById(R.id.footer);
            this.playLogo = (ImageView) itemView.findViewById(R.id.play_logo);
            this.path = (TextView) itemView.findViewById(R.id.path);
            this.line = itemView.findViewById(R.id.search_result_item_line);
        }
    }

    public void setData(ClassifyData data, String searchKey) {
        this.classifyData = data;
        this.data = NormalDataAdapter.getSearchData(data);
        this.searchKey = searchKey;
        notifyDataSetChanged();
    }
}
