package com.amazon.podcast.views.ptcPodcastsTemplate;

import Podcast.FollowInterface.v1_0.FollowWriteElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.AboutElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.AddGridElementsMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.GridItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCPodcastsTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTrailerPlaybackErrorMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.ShowPTCTrailerMethod;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.transformations.CircularTransformation;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder;
import com.amazon.podcast.views.ptcTemplate.PTCTemplateActivity;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCPodcastsTemplateView extends BaseView<PTCPodcastsTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private final int END_OF_LIST_THRESHOLD;
    private final int IMAGE_STACK_LIMIT;
    private String bottomSheetPodcastId;
    private RecyclerView categoryRecyclerView;
    private PTCGridElementAdapter categoryWidgetsAdapter;
    private Map<String, GridItemElement> deselectedGridItems;
    private EmberTextView doneButton;
    private LinearLayout headerCirclesLayout;
    private ImageView headerImage1;
    private ImageView headerImage2;
    private ImageView headerImage3;
    private ImageView headerImage4;
    private EmberTextView headerImageText;
    private Map<String, ImageView> imageUrlToViewMap;
    private boolean isEndOfListTriggered;
    private LinearLayoutManager layoutManager;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private PTCBottomSheetTemplateView ptcBottomSheetView;
    private View ptcPodcastsTemplateView;
    private Map<String, GridItemElement> selectedGridItems;
    private EmberTextView skipButton;
    private List<PTCPodcastsTemplateSection> templateSections;

    static {
        String simpleName = PTCPodcastsTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PTCPodcastsTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.END_OF_LIST_THRESHOLD = 2;
        this.IMAGE_STACK_LIMIT = 4;
        this.selectedGridItems = new LinkedHashMap();
        this.deselectedGridItems = new LinkedHashMap();
        this.imageUrlToViewMap = new HashMap();
        init();
    }

    private void init() {
        this.ptcPodcastsTemplateView = View.inflate(getContext(), R$layout.ptc_podcast_selection_view, this);
        this.categoryRecyclerView = (RecyclerView) findViewById(R$id.ptc_podcasts_category_view);
        this.skipButton = (EmberTextView) findViewById(R$id.podcast_skip_view_button);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_done_view_button);
        this.doneButton = emberTextView;
        emberTextView.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getAccentRoundedCornersDrawableId()));
        setLayoutManagerAndAdapter();
        this.headerCirclesLayout = (LinearLayout) findViewById(R$id.ptc_header_circles_layout);
        this.headerImage1 = (ImageView) findViewById(R$id.ptc_header_circle_image1);
        this.headerImage2 = (ImageView) findViewById(R$id.ptc_header_circle_image2);
        this.headerImage3 = (ImageView) findViewById(R$id.ptc_header_circle_image3);
        this.headerImage4 = (ImageView) findViewById(R$id.ptc_header_circle_image4);
        this.headerImageText = (EmberTextView) findViewById(R$id.ptc_podcast_header_image_text);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final PTCPodcastsTemplate template, final boolean hideActionBar) {
        bindLeadingButton(template.getLeadingButton());
        bindTrailingButton(template.getTrailingButton());
        this.headerImageText.setText("+1");
        if (smallScreen()) {
            this.headerCirclesLayout.setVisibility(4);
        }
        bindOnViewed(template);
        this.templateSections = buildTemplateSections(template.getTitle(), template.getSubtitle(), template.getItems());
        selectCachedPodcasts();
        this.categoryWidgetsAdapter.bind(this.templateSections, this.selectedGridItems);
        this.onEndOfList = template.getOnEndOfList();
        this.isEndOfListTriggered = false;
        this.categoryRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (PTCPodcastsTemplateView.this.categoryWidgetsAdapter.getItemCount() - PTCPodcastsTemplateView.this.layoutManager.findLastVisibleItemPosition() > 2 || PTCPodcastsTemplateView.this.isEndOfListTriggered) {
                    return;
                }
                PTCPodcastsTemplateView.this.getMethodsDispatcher().dispatch(PTCPodcastsTemplateView.this.getOwnerId(), PTCPodcastsTemplateView.this.onEndOfList);
                PTCPodcastsTemplateView.this.isEndOfListTriggered = true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSelections(final GridItemElement gridItemElement, final boolean isSelected) {
        if (isSelected) {
            handleSelected(gridItemElement);
        } else {
            handleDeselected(gridItemElement);
        }
    }

    private void handleSelected(final GridItemElement element) {
        if (!this.selectedGridItems.containsKey(element.getId())) {
            this.selectedGridItems.put(element.getId(), element);
        }
        this.deselectedGridItems.remove(element.getId());
    }

    private void handleDeselected(final GridItemElement element) {
        Map<String, GridItemElement> cachedPodcastSelections = getCachedPodcastSelections();
        this.selectedGridItems.remove(element.getId());
        if (cachedPodcastSelections.containsKey(element.getId())) {
            this.deselectedGridItems.put(element.getId(), element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePodcastImageCirclesHeader(final GridItemElement gridItemElement, final boolean isSelected) {
        if (isSelected) {
            addItemtoHeaderCircles(gridItemElement);
        } else {
            removeItemFromHeaderCircles(gridItemElement);
        }
    }

    private void addItemtoHeaderCircles(final GridItemElement gridItemElement) {
        if (this.selectedGridItems.size() > 4) {
            this.headerImageText.setText(MqttTopic.SINGLE_LEVEL_WILDCARD + (this.selectedGridItems.size() - 4));
            this.headerImageText.setVisibility(0);
            return;
        }
        displayNewImage(gridItemElement.getImage());
    }

    private void removeItemFromHeaderCircles(final GridItemElement gridItemElement) {
        if (this.imageUrlToViewMap.containsKey(gridItemElement.getImage())) {
            clearImageFromMap(gridItemElement.getImage());
            displayAndClearImagesFromList();
        }
        if (this.selectedGridItems.size() <= 4) {
            this.headerImageText.setVisibility(8);
            return;
        }
        this.headerImageText.setText(MqttTopic.SINGLE_LEVEL_WILDCARD + (this.selectedGridItems.size() - 4));
    }

    private void displayNewImage(final String imageUrl) {
        if (this.imageUrlToViewMap.containsKey(imageUrl)) {
            return;
        }
        int size = this.imageUrlToViewMap.size();
        if (size == 0) {
            loadImage(this.headerImage1, imageUrl);
            return;
        }
        if (size == 1) {
            loadImage(this.headerImage2, imageUrl);
        } else if (size == 2) {
            loadImage(this.headerImage3, imageUrl);
        } else {
            if (size != 3) {
                return;
            }
            loadImage(this.headerImage4, imageUrl);
        }
    }

    private void clearImageFromMap(String image) {
        this.imageUrlToViewMap.get(image).setVisibility(8);
        this.imageUrlToViewMap.remove(image);
    }

    private void displayAndClearImagesFromList() {
        int i = 0;
        for (GridItemElement gridItemElement : this.selectedGridItems.values()) {
            if (i >= 4) {
                break;
            }
            if (i == 0) {
                loadImage(this.headerImage1, gridItemElement.getImage());
            } else if (i == 1) {
                loadImage(this.headerImage2, gridItemElement.getImage());
            } else if (i == 2) {
                loadImage(this.headerImage3, gridItemElement.getImage());
            } else if (i == 3) {
                loadImage(this.headerImage4, gridItemElement.getImage());
            }
            i++;
        }
        while (i < 4) {
            if (i == 0) {
                this.headerImage1.setVisibility(8);
            } else {
                if (i != 1) {
                    if (i != 2) {
                        if (i != 3) {
                        }
                        i++;
                    }
                    this.headerImage4.setVisibility(8);
                    i++;
                }
                this.headerImage3.setVisibility(8);
                this.headerImage4.setVisibility(8);
                i++;
            }
            this.headerImage2.setVisibility(8);
            this.headerImage3.setVisibility(8);
            this.headerImage4.setVisibility(8);
            i++;
        }
    }

    private void loadImage(final ImageView podcastImage, final String image) {
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.ptc_header_circle_diameter);
        Picasso.get().load(image).centerCrop().placeholder(Podcast.getRuntimeStyleSheet().getPTCCircularPlaceholderDrawableId()).transform(new CircularTransformation()).error(Podcast.getRuntimeStyleSheet().getPTCCircularPlaceholderDrawableId()).resize(dimensionPixelSize, dimensionPixelSize).into(podcastImage);
        podcastImage.setVisibility(0);
        this.imageUrlToViewMap.put(image, podcastImage);
    }

    private boolean smallScreen() {
        Resources resources = getResources();
        return convertPixelsToDp((float) resources.getDisplayMetrics().widthPixels, resources) < 360.0f;
    }

    private static float convertPixelsToDp(final float px, final Resources resources) {
        return px / (resources.getDisplayMetrics().densityDpi / 160.0f);
    }

    private void selectCachedPodcasts() {
        Iterator<GridItemElement> it = getCachedPodcastSelections().values().iterator();
        while (it.hasNext()) {
            handleSelected(it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deselectAll() {
        Iterator<GridItemElement> it = this.selectedGridItems.values().iterator();
        while (it.hasNext()) {
            GridItemElement next = it.next();
            it.remove();
            handleDeselected(next);
        }
    }

    private void bindOnViewed(final PTCPodcastsTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.ptcPodcastsTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private void bindLeadingButton(final LeadingButtonElement element) {
        if (element == null) {
            return;
        }
        this.skipButton.setText(element.getText());
        this.skipButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCTemplateActivity pTCTemplate = PTCPodcastsTemplateView.this.getPTCTemplate();
                PTCPodcastsTemplateView.this.deselectAll();
                PTCPodcastsTemplateView.this.dispatchPodcastFollows();
                pTCTemplate.cachePodcastSelections(PTCPodcastsTemplateView.this.selectedGridItems);
                PTCPodcastsTemplateView.this.deselectedGridItems.clear();
                PTCPodcastsTemplateView.this.getMethodsDispatcher().dispatch(pTCTemplate.getNextOwnerId(), element.getOnItemSelected());
                if (pTCTemplate.getCachedCategorySelections().size() > 0) {
                    pTCTemplate.handleNextSelected();
                }
                PTCPodcastsTemplateView.this.categoryWidgetsAdapter.bind(PTCPodcastsTemplateView.this.templateSections, PTCPodcastsTemplateView.this.selectedGridItems);
            }
        });
    }

    private void bindTrailingButton(final TrailingButtonElement element) {
        if (element == null) {
            return;
        }
        this.doneButton.setText(element.getText());
        this.doneButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCTemplateActivity pTCTemplate = PTCPodcastsTemplateView.this.getPTCTemplate();
                PTCPodcastsTemplateView.this.dispatchPodcastFollows();
                pTCTemplate.cachePodcastSelections(PTCPodcastsTemplateView.this.selectedGridItems);
                PTCPodcastsTemplateView.this.deselectedGridItems.clear();
                PTCPodcastsTemplateView.this.getMethodsDispatcher().dispatch(pTCTemplate.getNextOwnerId(), element.getOnItemSelected());
                pTCTemplate.handleNextSelected();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PTCTemplateActivity getPTCTemplate() {
        return (PTCTemplateActivity) getTemplateContext().getFragmentManager().getFragments().get(r0.size() - 1).getActivity();
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(String ownerId, Method method) {
        if (method instanceof AddGridElementsMethod) {
            addGridElementsMethod((AddGridElementsMethod) method);
        } else if (method instanceof ShowPTCTrailerMethod) {
            showTrailerMethod((ShowPTCTrailerMethod) method);
        } else if (method instanceof PTCTrailerPlaybackErrorMethod) {
            showPTCTrailerPlaybackError((PTCTrailerPlaybackErrorMethod) method);
        }
    }

    private void showPTCTrailerPlaybackError(final PTCTrailerPlaybackErrorMethod method) {
        String str;
        String podcastId = method.getPodcastId();
        if (podcastId == null || (str = this.bottomSheetPodcastId) == null || this.ptcBottomSheetView == null || !podcastId.equals(str)) {
            return;
        }
        this.ptcBottomSheetView.displayPlaybackErrorToast(method.getText());
    }

    private void showTrailerMethod(final ShowPTCTrailerMethod method) {
        String str;
        AboutElement aboutElement = method.getAboutElement();
        String podcastId = method.getPodcastId();
        if (aboutElement == null || podcastId == null || (str = this.bottomSheetPodcastId) == null || this.ptcBottomSheetView == null || !podcastId.equals(str)) {
            return;
        }
        this.ptcBottomSheetView.bind(aboutElement);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
        if (smallScreen()) {
            this.headerCirclesLayout.setVisibility(4);
        } else {
            this.headerCirclesLayout.setVisibility(0);
        }
        setLayoutManagerAndAdapter();
        this.categoryWidgetsAdapter.bind(this.templateSections, this.selectedGridItems);
    }

    private void setLayoutManagerAndAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        this.layoutManager = linearLayoutManager;
        this.categoryRecyclerView.setLayoutManager(linearLayoutManager);
        PTCGridElementAdapter pTCGridElementAdapter = new PTCGridElementAdapter(getOwnerId(), getTemplateContext(), getMethodsDispatcher(), getTemplateContext().getFragmentManager(), new PTCGridElementViewHolder.OnGridItemUpdateListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView.4
            @Override // com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder.OnGridItemUpdateListener
            public void onGridItemStateUpdate(final GridItemElement gridItemElement, final boolean isSelected) {
                PTCPodcastsTemplateView.this.updateSelections(gridItemElement, isSelected);
                PTCPodcastsTemplateView.this.updatePodcastImageCirclesHeader(gridItemElement, isSelected);
                if (PTCPodcastsTemplateView.this.selectedGridItems.size() > 0) {
                    PTCPodcastsTemplateView.this.doneButton.setVisibility(0);
                } else {
                    PTCPodcastsTemplateView.this.doneButton.setVisibility(8);
                }
            }
        }, new PTCGridElementViewHolder.OnBottomSheetSelectedListener() { // from class: com.amazon.podcast.views.ptcPodcastsTemplate.PTCPodcastsTemplateView.5
            @Override // com.amazon.podcast.views.ptcPodcastsTemplate.PTCGridElementViewHolder.OnBottomSheetSelectedListener
            public void onAboutSelected(final String podcastId, final PTCBottomSheetTemplateView bottomSheetView) {
                PTCPodcastsTemplateView.this.bottomSheetPodcastId = podcastId;
                PTCPodcastsTemplateView.this.ptcBottomSheetView = bottomSheetView;
            }
        });
        this.categoryWidgetsAdapter = pTCGridElementAdapter;
        this.categoryRecyclerView.setAdapter(pTCGridElementAdapter);
    }

    private Map<String, GridItemElement> getCachedPodcastSelections() {
        return getPTCTemplate().getCachedPodcastSelections();
    }

    private void addGridElementsMethod(final AddGridElementsMethod method) {
        List<PTCPodcastsTemplateSection> listBuildTemplateSections = buildTemplateSections(method.getItems());
        this.onEndOfList = method.getOnEndOfList();
        this.categoryWidgetsAdapter.addItems(listBuildTemplateSections);
        this.isEndOfListTriggered = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchPodcastFollows() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(buildPodcastFollowWriteElements(this.selectedGridItems, true));
        arrayList.addAll(buildPodcastFollowWriteElements(this.deselectedGridItems, false));
        getPTCTemplate().buildAndDispatchWriteMethod(arrayList, Queues.followSync());
    }

    private List<FollowWriteElement> buildPodcastFollowWriteElements(final Map<String, GridItemElement> items, final boolean isSelected) {
        ArrayList arrayList = new ArrayList();
        Map<String, GridItemElement> cachedPodcastSelections = getCachedPodcastSelections();
        for (GridItemElement gridItemElement : items.values()) {
            if (!isSelected || !cachedPodcastSelections.containsKey(gridItemElement.getId())) {
                arrayList.add(buildPodcastFollowWriteElement(gridItemElement, isSelected));
            }
        }
        return arrayList;
    }

    private FollowWriteElement buildPodcastFollowWriteElement(final GridItemElement element, final boolean isSelected) {
        return FollowWriteElement.builder().withId(element.getId()).withTitle(element.getAltText()).withImage(element.getImage()).withFollowed(isSelected).withPodcastShowVariantId(element.getPodcastShowVariantId()).build();
    }

    private List<PTCPodcastsTemplateSection> buildTemplateSections(final String title, final String subtitle, final List<GridElement> items) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new PTCPodcastsTemplateSection(title, subtitle));
        arrayList.addAll(buildTemplateSections(items));
        return arrayList;
    }

    private List<PTCPodcastsTemplateSection> buildTemplateSections(final List<GridElement> items) {
        ArrayList arrayList = new ArrayList();
        Iterator<GridElement> it = items.iterator();
        while (it.hasNext()) {
            arrayList.add(new PTCPodcastsTemplateSection(it.next()));
        }
        return arrayList;
    }
}
