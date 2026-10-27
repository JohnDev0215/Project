package com.amazon.podcast.views.refinements;

import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.DividerItemDecorator;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsAdapter;
import com.google.android.material.button.MaterialButton;

/* JADX INFO: loaded from: classes5.dex */
final class CheckBoxRefinementOptionsView extends RelativeLayout implements CheckBoxRefinementOptionsAdapter.OnOptionSelectedListener {
    private CheckBoxRefinementOptionsAdapter adapter;
    private MaterialButton applyButton;
    private View bottomSpacer;
    private View containerTopStroke;
    private EmberTextView dismiss;
    private DismissListener dismissListener;
    private CheckBoxRefinementOptionElement element;
    private String initialSelection;
    private RelativeLayout innerContainer;
    private boolean isPopUp;
    private RelativeLayout layout;
    private CheckBoxRefinementEventsListener listener;
    private RecyclerView recyclerView;
    private EmberTextView reset;
    private Resources resources;
    private EmberTextView title;

    interface DismissListener {
        void onDismissClicked();
    }

    public CheckBoxRefinementOptionsView(final Context context) {
        super(context);
        init();
    }

    private void init() {
        Context context = getContext();
        View.inflate(getContext(), R$layout.podcast_check_box_refinement_options_view, this);
        this.resources = getResources();
        this.layout = (RelativeLayout) findViewById(R$id.podcast_refinement_layout);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_refinement_title);
        this.title = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.reset = (EmberTextView) findViewById(R$id.podcast_refinement_reset_button);
        this.dismiss = (EmberTextView) findViewById(R$id.podcast_refinement_dismiss);
        this.containerTopStroke = findViewById(R$id.podcast_overlay_refinement_container_top_stroke);
        this.innerContainer = (RelativeLayout) findViewById(R$id.podcast_refinement_options_container);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_refinement_items_recycler_view);
        this.bottomSpacer = findViewById(R$id.podcast_pop_up_refinement_bottom_spacer);
        MaterialButton materialButton = (MaterialButton) findViewById(R$id.podcast_refinement_positive_button);
        this.applyButton = materialButton;
        materialButton.setBackgroundTintList(getResources().getColorStateList(Podcast.getRuntimeStyleSheet().getApplyButtonSelectorStateColorId()));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        CheckBoxRefinementOptionsAdapter checkBoxRefinementOptionsAdapter = new CheckBoxRefinementOptionsAdapter(this.resources, this);
        this.adapter = checkBoxRefinementOptionsAdapter;
        this.recyclerView.setAdapter(checkBoxRefinementOptionsAdapter);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(context, R$drawable.visual_row_items_divider_view)));
    }

    public void bind(final String ownerId, final boolean isPopUp, final String selectedOption, final MethodsDispatcher methodsDispatcher, final DismissListener dismissListener, final CheckBoxRefinementEventsListener listener, final CheckBoxRefinementOptionElement element) {
        this.element = element;
        this.isPopUp = isPopUp;
        this.listener = listener;
        this.initialSelection = selectedOption;
        this.dismissListener = dismissListener;
        this.title.setText(element.getText());
        this.adapter.bind(element, methodsDispatcher, ownerId, selectedOption, isPopUp);
        this.reset.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, element.getOnReset());
                dismissListener.onDismissClicked();
                listener.onResetSelected(element);
            }
        });
        if (isPopUp) {
            this.containerTopStroke.setVisibility(8);
            this.layout.setBackground(this.resources.getDrawable(R$drawable.podcast_pop_up_corner_radius));
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.innerContainer.getLayoutParams();
            int bottomSpacerHeight = getBottomSpacerHeight();
            marginLayoutParams.setMarginStart(bottomSpacerHeight);
            marginLayoutParams.setMarginEnd(bottomSpacerHeight);
            this.innerContainer.setPadding(0, 0, 0, 0);
            this.applyButton.setVisibility(8);
            this.dismiss.setVisibility(8);
            this.bottomSpacer.setVisibility(0);
            return;
        }
        this.applyButton.setEnabled(false);
        this.applyButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, element.getOnApply());
                dismissListener.onDismissClicked();
                listener.onApply(CheckBoxRefinementOptionsView.this.adapter.getSelectedItem(), element);
            }
        });
        this.dismiss.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                methodsDispatcher.dispatch(ownerId, element.getOnDismiss());
                dismissListener.onDismissClicked();
            }
        });
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementOptionsAdapter.OnOptionSelectedListener
    public void onOptionSelected(final CheckBoxRefinementOptionItemElement item) {
        if (this.isPopUp) {
            this.listener.onOptionItemSelectedForPopUp(item, this.element);
            this.dismissListener.onDismissClicked();
        } else {
            updateApplyButtonState(item);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        if (!this.isPopUp && visibility == 0) {
            resizeRecyclerView();
        }
    }

    private void resizeRecyclerView() {
        DisplayMetrics displayMetrics = this.resources.getDisplayMetrics();
        int statusBarHeight = getStatusBarHeight();
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_divider_height);
        Resources resources = this.resources;
        int i = R$dimen.podcast_spacer_medium;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(i);
        int dimensionPixelSize3 = this.resources.getDimensionPixelSize(R$dimen.podcast_button_small);
        int dimensionPixelSize4 = this.resources.getDimensionPixelSize(i);
        int recyclerViewHeight = getRecyclerViewHeight(this.isPopUp) + dimensionPixelSize + dimensionPixelSize2 + dimensionPixelSize3;
        Resources resources2 = this.resources;
        int i2 = R$dimen.podcast_playback_speed_item_height;
        int dimensionPixelSize5 = resources2.getDimensionPixelSize(i2);
        Resources resources3 = this.resources;
        int i3 = R$dimen.podcast_spacer_small;
        int dimensionPixelSize6 = resources3.getDimensionPixelSize(i3);
        int dimensionPixelSize7 = this.resources.getDimensionPixelSize(i3);
        int dimensionPixelSize8 = this.resources.getDimensionPixelSize(i2);
        if (recyclerViewHeight + statusBarHeight + dimensionPixelSize7 + dimensionPixelSize8 + dimensionPixelSize5 + dimensionPixelSize6 + dimensionPixelSize4 >= displayMetrics.heightPixels) {
            this.recyclerView.getLayoutParams().height = ((((((displayMetrics.heightPixels - dimensionPixelSize5) - dimensionPixelSize6) - dimensionPixelSize7) - dimensionPixelSize8) - dimensionPixelSize) - statusBarHeight) - dimensionPixelSize4;
        }
    }

    private int getStatusBarHeight() {
        int identifier = this.resources.getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.resources.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private int getRecyclerViewHeight(final boolean forPopUp) {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_large_list_item_height);
        if (forPopUp) {
            dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_medium_list_item_height);
        }
        int dimensionPixelSize2 = this.resources.getDimensionPixelSize(R$dimen.podcast_divider_height);
        return (this.adapter.getItemCount() * (dimensionPixelSize + dimensionPixelSize2)) + this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_medium);
    }

    private int getBottomSpacerHeight() {
        return this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_medium);
    }

    private void updateApplyButtonState(final CheckBoxRefinementOptionItemElement currentSelectedItem) {
        if (currentSelectedItem.getId().equals(this.initialSelection)) {
            this.applyButton.setEnabled(false);
        } else {
            this.applyButton.setEnabled(true);
        }
    }
}
