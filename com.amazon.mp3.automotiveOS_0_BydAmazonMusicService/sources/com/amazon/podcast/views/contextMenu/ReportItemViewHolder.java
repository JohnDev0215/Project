package com.amazon.podcast.views.contextMenu;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$string;
import com.amazon.podcast.views.EmberTextView;

/* JADX INFO: loaded from: classes5.dex */
final class ReportItemViewHolder extends ContextMenuItemViewHolder {
    private final Context context;
    private final Resources resources;

    public ReportItemViewHolder(@NonNull final View itemView, final Resources resources, final Context context) {
        super(itemView);
        this.resources = resources;
        this.context = context;
    }

    public void bind(final EpisodeOptionsElement episodeOptionsElement, final MethodsDispatcher methodsDispatcher, final String ownerId, final EmberTextView dismiss, final boolean isPopUp) {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.contextMenu.ReportItemViewHolder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, episodeOptionsElement.getReport().getOnReport());
                EmberTextView emberTextView = dismiss;
                if (emberTextView == null) {
                    return;
                }
                emberTextView.performClick();
            }
        };
        super.bindImage(R$drawable.ic_action_report);
        String string = this.resources.getString(R$string.podcast_report);
        super.bindText(string, isPopUp);
        super.bindClickerListener(onClickListener);
        this.itemView.setContentDescription(string);
        this.itemView.setId(R$id.context_menu_report_element);
    }
}
