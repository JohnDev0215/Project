package com.amazon.mp3.fragment.view.recyclerview.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.amazon.mp3.activity.TwoLinesListPreferenceModel;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.fragment.view.recyclerview.listener.RecyclerViewItemClickListener;
import com.amazon.mp3.fragment.view.recyclerview.viewholder.AbstractViewHolder;
import com.amazon.mp3.util.StringUtil;

/* JADX INFO: loaded from: classes3.dex */
public class TwoLinesListPreferenceAdapter extends AbstractRecyclerViewAdapter<TwoLinesListPreferenceModel, RecyclerViewItemClickListener, ViewHolder> {
    public TwoLinesListPreferenceAdapter(Context context, RecyclerViewItemClickListener listener) {
        super(context, listener);
    }

    @Override // com.amazon.mp3.fragment.view.recyclerview.adapter.AbstractRecyclerViewAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(inflate(R.layout.two_lines_list_preference_row, parent, false), getListener());
    }

    public class ViewHolder extends AbstractViewHolder<TwoLinesListPreferenceModel, RecyclerViewItemClickListener> {
        final ImageView infoIcon;
        final RadioButton isChecked;
        final TextView subtitle;
        final TextView title;

        public ViewHolder(View view, final RecyclerViewItemClickListener listener) {
            super(view, listener);
            this.title = (TextView) view.findViewById(R.id.custom_list_view_row_text_view);
            this.subtitle = (TextView) view.findViewById(R.id.custom_list_view_row_subtext_view);
            this.isChecked = (RadioButton) view.findViewById(R.id.preference_radio);
            ImageView imageView = (ImageView) view.findViewById(R.id.info_icon);
            this.infoIcon = imageView;
            if (listener != null) {
                imageView.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.view.recyclerview.adapter.TwoLinesListPreferenceAdapter$ViewHolder$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$new$0(listener, view2);
                    }
                });
                view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.fragment.view.recyclerview.adapter.TwoLinesListPreferenceAdapter$ViewHolder$$ExternalSyntheticLambda1
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        this.f$0.lambda$new$1(listener, view2);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(RecyclerViewItemClickListener recyclerViewItemClickListener, View view) {
            recyclerViewItemClickListener.onClick(view, getAdapterPosition());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$1(RecyclerViewItemClickListener recyclerViewItemClickListener, View view) {
            recyclerViewItemClickListener.onClick(view, getAdapterPosition());
        }

        @Override // com.amazon.mp3.fragment.view.recyclerview.viewholder.AbstractViewHolder
        public void onBind(final TwoLinesListPreferenceModel twoLinesListPreferenceModel) {
            this.title.setText(twoLinesListPreferenceModel.getTitle());
            if (StringUtil.isNullOrWhiteSpaces(twoLinesListPreferenceModel.getDescription())) {
                this.infoIcon.setVisibility(8);
            } else {
                this.infoIcon.setVisibility(0);
            }
            this.isChecked.setChecked(twoLinesListPreferenceModel.getIsChecked());
        }
    }
}
