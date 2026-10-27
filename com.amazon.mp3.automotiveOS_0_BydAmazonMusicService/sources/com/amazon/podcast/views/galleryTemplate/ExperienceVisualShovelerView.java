package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElement;
import Podcast.Touch.ExperienceVisualShovelerElementInterface.v1_0.ExperienceVisualShovelerElement;
import Podcast.Touch.MediaItemsInterface.v1_0.MediaItemElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.views.EmberTextView;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class ExperienceVisualShovelerView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private EmberTextView header;
    private boolean isFirstView;
    private int itemDimensions;
    private LinearLayoutManager layoutManager;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onLaunchBitesExperience;
    private List<Method> onViewed;
    private String ownerId;
    private final float peakItemPercentage;
    private LinearLayout primaryButton;
    private ImageView primaryButtonIcon;
    private EmberTextView primaryButtonText;
    private RecyclerView recyclerView;
    private Resources resources;
    private EmberTextView subtitle;
    private int visibleItemCount;
    private String widgetId;

    public ExperienceVisualShovelerView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.peakItemPercentage = 0.3f;
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_experience_visual_shoveler, this);
        Resources resources = getResources();
        this.resources = resources;
        this.visibleItemCount = resources.getInteger(R$integer.podcast_experience_visual_shoveler_item_count);
        this.itemDimensions = getItemDimension(this.resources);
        this.header = (EmberTextView) findViewById(R$id.experience_visual_shoveler_header);
        this.subtitle = (EmberTextView) findViewById(R$id.experience_visual_shoveler_subtitle);
        this.primaryButton = (LinearLayout) findViewById(R$id.experience_visual_shoveler_primary_button);
        this.primaryButtonIcon = (ImageView) findViewById(R$id.experience_visual_shoveler_primary_button_icon);
        this.primaryButtonText = (EmberTextView) findViewById(R$id.experience_visual_shoveler_primary_button_text);
        this.recyclerView = (RecyclerView) findViewById(R$id.experience_visual_shoveler_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(new ExperienceVisualShovelerAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.itemDimensions, this.lifecycleOwner));
    }

    public void bind(final ExperienceVisualShovelerElement element) {
        if (element == null) {
            return;
        }
        String header = element.getHeader();
        this.header.setText(header);
        this.header.setContentDescription(String.format(this.resources.getString(R$string.podcast_bites_visual_shoveler_header), header));
        String secondaryText = element.getSecondaryText();
        this.subtitle.setText(secondaryText);
        this.subtitle.setContentDescription(String.format(this.resources.getString(R$string.podcast_bites_visual_shoveler_subtitle), secondaryText));
        List<MediaItemElement> items = element.getItems();
        bindPrimaryButton(element);
        ExperienceVisualShovelerAdapter experienceVisualShovelerAdapter = (ExperienceVisualShovelerAdapter) this.recyclerView.getAdapter();
        this.widgetId = element.getId();
        List<Method> onItemSelected = element.getOnItemSelected();
        this.onLaunchBitesExperience = onItemSelected;
        experienceVisualShovelerAdapter.setOnLaunchBitesExperience(onItemSelected);
        experienceVisualShovelerAdapter.bind(items);
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        if (StringUtils.isBlank(this.widgetId)) {
            return;
        }
        this.contentViewCache.addWidget(this.widgetId);
        this.contentViewCache.updateNumItemsAccessible(this.widgetId, items.size());
        handleOnContentFirstViewed();
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
    }

    private void handleOnContentFirstViewed() {
        if (this.isFirstView) {
            addOnScrollListener(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            handleFirstOnContentFirstViewed(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            this.isFirstView = false;
        }
    }

    private void bindPrimaryButton(final ExperienceVisualShovelerElement element) {
        final PrimaryButtonElement primaryButton = element.getPrimaryButton();
        if (primaryButton != null && primaryButton.getOnItemSelected() != null) {
            this.primaryButton.setVisibility(0);
            String text = primaryButton.getText();
            this.primaryButtonText.setText(text);
            if (primaryButton.isIconEnabled()) {
                this.primaryButtonIcon.setVisibility(0);
            } else {
                this.primaryButtonIcon.setVisibility(8);
            }
            this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.ExperienceVisualShovelerView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ExperienceVisualShovelerView.this.methodsDispatcher.dispatch(ExperienceVisualShovelerView.this.ownerId, primaryButton.getOnItemSelected());
                }
            });
            this.primaryButton.setContentDescription(String.format(this.resources.getString(R$string.podcast_card_now_playing_primary_button), text));
            return;
        }
        this.primaryButton.setVisibility(8);
    }

    private int getItemDimension(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = this.visibleItemCount * resources.getDimensionPixelSize(R$dimen.podcast_spacer_base);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.gutter);
        return Math.max(Math.round((i - ((dimensionPixelSize + dimensionPixelSize2) + dimensionPixelSize3)) / (this.visibleItemCount + 0.3f)), resources.getDimensionPixelSize(R$dimen.podcast_min_small_item_width));
    }
}
