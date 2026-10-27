package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.Touch.PTCTemplateInterface.v1_0.GridElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import android.content.res.Resources;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.gridlayout.widget.GridLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
final class PTCGridElementViewHolder extends RecyclerView.ViewHolder {
    private OnBottomSheetSelectedListener bottomSheetSelectedListener;
    private EmberTextView categoryTitleText;
    private FragmentManager fragmentManager;
    private GridElement gridElement;
    private OnGridItemUpdateListener gridItemUpdateListener;
    private GridLayout gridLayout;
    private View itemView;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private Resources resources;
    private TemplateContext templateContext;

    public interface OnBottomSheetSelectedListener {
        void onAboutSelected(final String podcastId, final PTCBottomSheetTemplateView bottomSheetView);
    }

    public interface OnGridItemUpdateListener {
        void onGridItemStateUpdate(final GridItemElement gridItemElement, final boolean isSelected);
    }

    public PTCGridElementViewHolder(@NonNull View itemView, final String ownerId, final TemplateContext templateContext, final MethodsDispatcher methodsDispatcher, final FragmentManager fragmentManager, final OnGridItemUpdateListener gridItemUpdateListener, final OnBottomSheetSelectedListener bottomSheetSelectedListener) {
        super(itemView);
        this.itemView = itemView;
        this.ownerId = ownerId;
        this.templateContext = templateContext;
        this.methodsDispatcher = methodsDispatcher;
        this.fragmentManager = fragmentManager;
        this.gridItemUpdateListener = gridItemUpdateListener;
        this.bottomSheetSelectedListener = bottomSheetSelectedListener;
        this.resources = templateContext.getContext().getResources();
        init();
    }

    private void init() {
        EmberTextView emberTextView = (EmberTextView) this.itemView.findViewById(R$id.podcast_category_title);
        this.categoryTitleText = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.gridLayout = (GridLayout) this.itemView.findViewById(R$id.ptc_grid_element);
    }

    public void bind(final GridElement gridElement, final Map<String, GridItemElement> selectedGridItems) {
        if (gridElement == null) {
            return;
        }
        this.gridElement = gridElement;
        this.categoryTitleText.setText(gridElement.getTitle());
        buildGridLayout(gridElement.getItems(), selectedGridItems);
        this.categoryTitleText.setVisibility(0);
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.gridElement.getOnViewed());
    }

    private void buildGridLayout(final List<GridItemElement> items, final Map<String, GridItemElement> selectedGridItems) {
        this.gridLayout.removeAllViews();
        int integer = this.resources.getInteger(R$integer.ptc_podcast_grid_columns);
        Map<Integer, Integer> arrangement = PTCGridLayouts.getArrangement(integer);
        this.gridLayout.setColumnCount(integer);
        int dimension = getDimension();
        for (Integer numValueOf = 0; numValueOf.intValue() < items.size(); numValueOf = Integer.valueOf(numValueOf.intValue() + 1)) {
            GridItemElement gridItemElement = items.get(numValueOf.intValue());
            PTCGridItemView pTCGridItemView = new PTCGridItemView(this.templateContext.getContext());
            Integer numValueOf2 = Integer.valueOf(arrangement.containsKey(numValueOf) ? arrangement.get(numValueOf).intValue() : 1);
            GridLayout.Spec spec = GridLayout.spec(Integer.MIN_VALUE, numValueOf2.intValue());
            GridLayout.LayoutParams layoutParams = new GridLayout.LayoutParams(spec, spec);
            layoutParams.setGravity(119);
            this.gridLayout.addView(pTCGridItemView, layoutParams);
            pTCGridItemView.bind(this.ownerId, this.resources, this.methodsDispatcher, this.fragmentManager, this.gridItemUpdateListener, this.bottomSheetSelectedListener, numValueOf2.intValue() * dimension, gridItemElement, selectedGridItems.containsKey(gridItemElement.getId()));
        }
        this.gridLayout.setVisibility(0);
    }

    private int getDimension() {
        return ((this.resources.getDisplayMetrics().widthPixels - ((this.resources.getDimensionPixelSize(R$dimen.gutter) + this.resources.getDimensionPixelSize(R$dimen.margin)) * 2)) + this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_base)) / this.resources.getInteger(R$integer.ptc_podcast_grid_columns);
    }
}
