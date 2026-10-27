package com.amazon.podcast.views.ptcCategoriesTemplate;

import Podcast.CategoryFollowInterface.v1_0.CategoryFollowWriteElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.LeadingButtonElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCCategoriesTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement;
import Podcast.Touch.PTCTemplateInterface.v1_0.TrailingButtonElement;
import SOACoreInterface.v1_0.Method;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.RuntimeStyleSheet;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.ptcCategoriesTemplate.PillItemAdapter;
import com.amazon.podcast.views.ptcTemplate.PTCTemplateActivity;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PTCCategoriesTemplateView extends BaseView<PTCCategoriesTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private PillItemAdapter adapter;
    private final List<PillItemElement> deselectedItems;
    private EmberTextView leadingButton;
    private List<Method> onViewed;
    private View ptcCategoriesView;
    private RecyclerView recyclerView;
    private final List<PillItemElement> selectedItems;
    private TextView subtitle;
    private PTCCategoriesTemplate template;
    private final TemplateContext templateContext;
    private TextView title;
    private EmberTextView trailingButton;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = PTCCategoriesTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PTCCategoriesTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        this.selectedItems = new ArrayList();
        this.deselectedItems = new ArrayList();
        init();
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final PTCCategoriesTemplate template, final boolean hideActionBar) {
        this.template = template;
        bindLeadingButton(template.getLeadingButton());
        bindTrailingButton(template.getTrailingButton());
        bindPillItems(template.getItems());
        if (PodcastFeatureGating.IS_SONIC_RUSH.isEnabled()) {
            this.title.setText(WordUtils.capitalize(template.getTitle()));
        } else {
            this.title.setText(template.getTitle());
        }
        this.subtitle.setText(template.getSubtitle());
        bindOnViewed(template);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void init() {
        this.ptcCategoriesView = View.inflate(getContext(), R$layout.ptc_categories_template_view, this);
        this.leadingButton = (EmberTextView) findViewById(R$id.podcast_ptc_categories_leading_button);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_ptc_categories_trailing_button);
        this.trailingButton = emberTextView;
        emberTextView.setBackground(getResources().getDrawable(Podcast.getRuntimeStyleSheet().getAccentRoundedCornersDrawableId()));
        this.title = (TextView) findViewById(R$id.podcast_ptc_categories_title);
        RuntimeStyleSheet runtimeStyleSheet = Podcast.getRuntimeStyleSheet();
        this.title.setTypeface(runtimeStyleSheet.getHeadline2TypeFace());
        this.title.setTextSize(0, runtimeStyleSheet.getHeadline2TextSize());
        this.title.setAllCaps(runtimeStyleSheet.isHeadline2AllCaps());
        if (Build.VERSION.SDK_INT >= 28) {
            this.title.setLineHeight(runtimeStyleSheet.getHeadline2LineHeight());
        }
        this.subtitle = (TextView) findViewById(R$id.podcast_ptc_categories_subtitle);
        this.recyclerView = (RecyclerView) findViewById(R$id.podcast_ptc_pill_listing_recycler_view);
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setFlexDirection(0);
        flexboxLayoutManager.setFlexWrap(1);
        this.recyclerView.setLayoutManager(flexboxLayoutManager);
        this.trailingButton.setVisibility(4);
        PillItemAdapter pillItemAdapter = new PillItemAdapter(getOwnerId(), getMethodsDispatcher(), new PillItemAdapter.OnPillStateUpdateListener() { // from class: com.amazon.podcast.views.ptcCategoriesTemplate.PTCCategoriesTemplateView.1
            @Override // com.amazon.podcast.views.ptcCategoriesTemplate.PillItemAdapter.OnPillStateUpdateListener
            public void onStateUpdate(final PillItemElement element, final boolean isSelected) {
                PTCCategoriesTemplateView.this.updatePillItemState(element, isSelected);
                if (PTCCategoriesTemplateView.this.selectedItems.size() > 0) {
                    PTCCategoriesTemplateView.this.trailingButton.setVisibility(0);
                } else {
                    PTCCategoriesTemplateView.this.trailingButton.setVisibility(4);
                }
            }
        });
        this.adapter = pillItemAdapter;
        this.recyclerView.setAdapter(pillItemAdapter);
        addOnViewedListener(this.ptcCategoriesView);
    }

    private void bindOnViewed(final PTCCategoriesTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.ptcCategoriesView)) {
                handleOnViewed();
            }
        }
    }

    private void bindLeadingButton(final LeadingButtonElement element) {
        if (element == null) {
            return;
        }
        this.leadingButton.setText(element.getText());
        this.leadingButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcCategoriesTemplate.PTCCategoriesTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCTemplateActivity pTCTemplate = PTCCategoriesTemplateView.this.getPTCTemplate();
                PTCCategoriesTemplateView.this.deselectAll();
                PTCCategoriesTemplateView.this.dispatchCategoryFollows();
                pTCTemplate.cacheCategorySelections(PTCCategoriesTemplateView.this.selectedItems);
                PTCCategoriesTemplateView.this.deselectedItems.clear();
                PTCCategoriesTemplateView.this.getMethodsDispatcher().dispatch(pTCTemplate.getNextOwnerId(), element.getOnItemSelected());
                pTCTemplate.handleNextSelected();
                PTCCategoriesTemplateView.this.adapter.bind(PTCCategoriesTemplateView.this.template.getItems(), PTCCategoriesTemplateView.this.getCachedCategorySelections());
            }
        });
    }

    private void bindTrailingButton(final TrailingButtonElement element) {
        if (element == null) {
            return;
        }
        this.trailingButton.setText(element.getText());
        this.trailingButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.ptcCategoriesTemplate.PTCCategoriesTemplateView.3
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                PTCTemplateActivity pTCTemplate = PTCCategoriesTemplateView.this.getPTCTemplate();
                PTCCategoriesTemplateView.this.dispatchCategoryFollows();
                pTCTemplate.cacheCategorySelections(PTCCategoriesTemplateView.this.selectedItems);
                PTCCategoriesTemplateView.this.deselectedItems.clear();
                PTCCategoriesTemplateView.this.getMethodsDispatcher().dispatch(pTCTemplate.getNextOwnerId(), element.getOnItemSelected());
                pTCTemplate.handleNextSelected();
            }
        });
    }

    private void bindPillItems(final List<PillItemElement> pills) {
        if (CollectionUtils.isEmpty(pills)) {
            return;
        }
        this.adapter.bind(pills, getCachedCategorySelections());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> getCachedCategorySelections() {
        return getPTCTemplate().getCachedCategorySelections();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PTCTemplateActivity getPTCTemplate() {
        return (PTCTemplateActivity) this.templateContext.getFragmentManager().getFragments().get(r0.size() - 1).getActivity();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePillItemState(final PillItemElement element, final boolean isSelected) {
        if (isSelected) {
            handleSelected(element);
        } else {
            handleDeselected(element);
        }
    }

    private void handleSelected(final PillItemElement element) {
        this.selectedItems.add(element);
        this.deselectedItems.remove(element);
    }

    private void handleDeselected(final PillItemElement element) {
        List<String> cachedCategorySelections = getCachedCategorySelections();
        this.selectedItems.remove(element);
        if (cachedCategorySelections.contains(element.getId())) {
            this.deselectedItems.add(element);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deselectAll() {
        Iterator<PillItemElement> it = this.selectedItems.iterator();
        while (it.hasNext()) {
            PillItemElement next = it.next();
            it.remove();
            handleDeselected(next);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchCategoryFollows() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(buildCategoryFollowWriteElements(this.selectedItems, true));
        arrayList.addAll(buildCategoryFollowWriteElements(this.deselectedItems, false));
        getPTCTemplate().buildAndDispatchWriteMethod(arrayList, Queues.categoryFollowSync());
    }

    private List<CategoryFollowWriteElement> buildCategoryFollowWriteElements(final List<PillItemElement> items, final boolean isSelected) {
        ArrayList arrayList = new ArrayList();
        List<String> cachedCategorySelections = getCachedCategorySelections();
        for (PillItemElement pillItemElement : items) {
            if (!isSelected || !cachedCategorySelections.contains(pillItemElement.getId())) {
                arrayList.add(buildCategoryFollowWriteElement(pillItemElement, isSelected));
            }
        }
        return arrayList;
    }

    private CategoryFollowWriteElement buildCategoryFollowWriteElement(final PillItemElement element, final boolean isSelected) {
        return CategoryFollowWriteElement.builder().withId(element.getId()).withTitle(element.getText()).withFollowed(isSelected).build();
    }
}
