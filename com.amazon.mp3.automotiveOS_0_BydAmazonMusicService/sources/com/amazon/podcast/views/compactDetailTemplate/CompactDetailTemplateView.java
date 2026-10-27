package com.amazon.podcast.views.compactDetailTemplate;

import Podcast.Touch.CompactDetailTemplateInterface.v1_0.CompactDetailTemplate;
import Podcast.Touch.CompactDetailTemplateInterface.v1_0.ShowVisualShovelerMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import Podcast.Touch.WidgetsInterface.v1_0.VisualShovelerCompactElement;
import SOACoreInterface.v1_0.Method;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.ExpandableTextView;
import com.amazon.podcast.views.RibbonNotificationView;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.galleryTemplate.VisualShovelerCompactView;
import com.amazon.podcast.views.galleryTemplate.clientInterfaceConverters.VisualShovelerCompactElementConverter;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class CompactDetailTemplateView extends BaseView<CompactDetailTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private AppBarLayout appBarLayout;
    private ImageView background;
    private View compactDetailTemplateView;
    private VisualShovelerCompactView compactVisualShoveler;
    private ExpandableTextView description;
    private View descriptionBackground;
    private HeaderView headerView;
    private List<Method> onViewed;
    private RibbonNotificationView ribbonNotificationView;
    private ScrollView scrollView;
    private final TemplateContext templateContext;
    private BlurTransformation transformation;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = CompactDetailTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public CompactDetailTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        this.transformation = new BlurTransformation(templateContext.getContext());
        init();
    }

    private void init() {
        this.compactDetailTemplateView = View.inflate(getContext(), R$layout.podcast_compact_detail_template_view, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.appBarLayout = (AppBarLayout) findViewById(R$id.app_bar_layout);
        this.headerView = (HeaderView) findViewById(R$id.compact_detail_template_header);
        this.description = (ExpandableTextView) findViewById(R$id.compact_detail_template_description);
        this.background = (ImageView) findViewById(R$id.compact_detail_template_view_background);
        this.descriptionBackground = findViewById(R$id.compact_detail_template_description_background);
        ScrollView scrollView = (ScrollView) findViewById(R$id.compact_detail_template_scroll_view);
        this.scrollView = scrollView;
        scrollView.setVerticalScrollBarEnabled(false);
        this.actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.compactVisualShoveler = (VisualShovelerCompactView) findViewById(R$id.compact_detail_visual_shoveler);
        this.ribbonNotificationView = (RibbonNotificationView) findViewById(R$id.podcast_compact_detail_template_ribbon_notification_view);
        this.actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CompactDetailTemplateView.this.handleBackPressed();
            }
        });
        initCompactVisualShovelerView();
        this.headerView.setOwnerId(getOwnerId());
        this.headerView.setLifeCycleOwner(getLifecycleOwner());
        this.headerView.setMethodsDispatcher(getMethodsDispatcher());
        this.headerView.setTemplateContext(this.templateContext);
        addOnViewedListener(this.compactDetailTemplateView);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final CompactDetailTemplate template, final boolean hideActionBar) {
        String description;
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        bindActionBar(template);
        Picasso.get().load(template.getBackgroundImage()).placeholder(R$drawable.artwork_placeholder).transform(this.transformation).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).centerCrop().into(this.background);
        this.headerView.bind(template.getHeader());
        this.headerView.bindBookmark(template.getBookmark(), template.getPlaybackMode());
        this.headerView.bindSave(template.getSave());
        this.headerView.bindDownloadElement(template.getDownload());
        this.headerView.bindContextMenuButton(template.getEpisodeOptions());
        this.headerView.bindRedhoodPlusElements(template.getText(), template.getButton(), template.getBadge());
        this.headerView.bindShareElement(template.getShare());
        if (StringUtils.isBlank(template.getDescription())) {
            description = getResources().getString(R$string.podcast_description_placeholder_text);
        } else {
            description = template.getDescription();
        }
        ExpandableTextView expandableTextView = this.description;
        expandableTextView.setText(Strings.convertToSpannable(description, expandableTextView));
        this.description.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                CompactDetailTemplateView.this.getMethodsDispatcher().dispatch(CompactDetailTemplateView.this.getOwnerId(), UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_EPISODE_DETAIL, UiMetricAttributes$ActionType.EXPAND_READ_MORE_READ_LESS, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, template.getSave().getId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_EPISODE_DETAIL));
            }
        });
        bindOnViewed(template);
    }

    private void bindActionBar(final CompactDetailTemplate detailsTemplate) {
        this.actionBarView.bindMiniHeader(detailsTemplate.getBackgroundImage());
        ViewTreeObserver viewTreeObserver = this.scrollView.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        viewTreeObserver.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() { // from class: com.amazon.podcast.views.compactDetailTemplate.CompactDetailTemplateView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.lambda$bindActionBar$0();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindActionBar$0() {
        this.scrollView.getHitRect(new Rect());
        this.appBarLayout.setAlpha(Math.max(1.0f, 1.0f - Math.min(1.0f, this.scrollView.getScrollY() / r0.height())));
        if (this.headerView.isHeaderImageViewOnScreen()) {
            this.actionBarView.showMiniHeader(false);
        } else {
            this.actionBarView.showMiniHeader(true);
        }
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof ShowVisualShovelerMethod) {
            showVisualShoveler((ShowVisualShovelerMethod) method);
            return;
        }
        if (method instanceof ShowNotificationMethod) {
            NotificationElement notification = ((ShowNotificationMethod) method).getNotification();
            if (notification instanceof RibbonNotificationElement) {
                this.ribbonNotificationView.bind((RibbonNotificationElement) notification, getMethodsDispatcher(), getOwnerId());
                this.ribbonNotificationView.setVisibility(0);
                return;
            }
            return;
        }
        if (method instanceof CloseNotificationMethod) {
            this.ribbonNotificationView.setVisibility(8);
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void bindOnViewed(CompactDetailTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.compactDetailTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private void showVisualShoveler(final ShowVisualShovelerMethod method) {
        VisualShovelerCompactElement visualShoveler = method.getVisualShoveler();
        if (visualShoveler == null) {
            return;
        }
        this.compactVisualShoveler.setVisibility(0);
        this.compactVisualShoveler.bind(VisualShovelerCompactElementConverter.convert(visualShoveler));
    }

    private void initCompactVisualShovelerView() {
        this.compactVisualShoveler.setOwnerId(getOwnerId());
        this.compactVisualShoveler.setLifeCycleOwner(getLifecycleOwner());
        this.compactVisualShoveler.setMethodsDispatcher(getMethodsDispatcher());
        this.compactVisualShoveler.initInflatedView();
        this.compactVisualShoveler.setVisibility(8);
    }
}
