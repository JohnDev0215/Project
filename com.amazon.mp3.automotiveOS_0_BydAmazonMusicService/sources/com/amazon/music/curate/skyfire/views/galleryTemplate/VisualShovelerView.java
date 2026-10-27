package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.IngressButtonElement;
import Touch.WidgetsInterface.v1_0.VerticalItemElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.account.SonicRushFeature;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.FontFaceUtil;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.EmberTextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.binders.GridLayoutUtils;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
final class VisualShovelerView extends LinearLayout {
    private final Context context;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private GridLayoutUtils gridLayoutUtils;
    private EmberTextView header;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private EmberTextView newPlaylistButton;
    private ImageView newPlaylistIcon;
    private final String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView seeMoreButton;
    private StyleSheet styleSheet;
    private Subscriber<StyleSheet> styleSheetSubscriber;
    private Subscription stylesheetSubscription;
    private Integer visibleVerticalItemCount;

    public VisualShovelerView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final CurateBootstrapProviders curateBootstrapProviders) {
        super(context);
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.curateBootstrapProviders = curateBootstrapProviders;
        init();
    }

    private void initViewStyling() {
        MutableLiveData<StyleSheet> styleSheet;
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        if (this.styleSheet == null) {
            getStyleSheetFromObservable(new Action1() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VisualShovelerView$$ExternalSyntheticLambda0
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$initViewStyling$0((StyleSheet) obj);
                }
            });
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 != null) {
            this.gridLayoutUtils = new GridLayoutUtils(this.context, styleSheet2);
            return;
        }
        CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
        if (curateBootstrapProviders == null || (styleSheet = curateBootstrapProviders.getStyleSheetProvider().getStyleSheet()) == null || styleSheet.getValue() == null) {
            return;
        }
        StyleSheet value = styleSheet.getValue();
        this.styleSheet = value;
        this.gridLayoutUtils = new GridLayoutUtils(this.context, value);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initViewStyling$0(StyleSheet styleSheet) {
        initViewStyling();
    }

    private void getStyleSheetFromObservable(final Action1<StyleSheet> onNextAction) {
        Subscriber<StyleSheet> subscriber = this.styleSheetSubscriber;
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
        this.styleSheetSubscriber = new Subscriber<StyleSheet>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VisualShovelerView.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                Log.e(VisualShovelerView.class.getSimpleName(), "Failed to fetch stylesheet", throwable);
            }

            @Override // rx.Observer
            public void onNext(final StyleSheet style) {
                VisualShovelerView.this.styleSheet = style;
                Action1 action1 = onNextAction;
                if (action1 != null) {
                    action1.call(style);
                }
            }
        };
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
            if (curateBootstrapProviders == null || curateBootstrapProviders.getStyleSheetProvider() == null) {
                return;
            }
            this.stylesheetSubscription = this.curateBootstrapProviders.getStyleSheetProvider().getStyleSheetObservable().observeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe((Subscriber<? super StyleSheet>) this.styleSheetSubscriber);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
            this.stylesheetSubscription = null;
        }
        Subscriber<StyleSheet> subscriber = this.styleSheetSubscriber;
        if (subscriber != null) {
            subscriber.unsubscribe();
            this.styleSheetSubscriber = null;
        }
    }

    private void init() {
        View.inflate(getContext(), R.layout.visual_shoveler, this);
        initViewStyling();
        this.visibleVerticalItemCount = Integer.valueOf(getResources().getInteger(R.integer.vertical_item_count));
        this.header = (EmberTextView) findViewById(R.id.visual_shoveler_header);
        EmberTextView emberTextView = (EmberTextView) findViewById(R.id.visual_shoveler_see_all_button);
        this.seeMoreButton = emberTextView;
        FontFaceUtil.FontSizeKey fontSizeKey = FontFaceUtil.FontSizeKey.INDEX;
        emberTextView.applyFontChangesAsPerSizeKey(fontSizeKey);
        this.seeMoreButton.setVisibility(4);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.visual_shoveler_recycler_view);
        this.recyclerView = recyclerView;
        recyclerView.setHasFixedSize(false);
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R.id.visual_shoveler_new_playlist_button);
        this.newPlaylistButton = emberTextView2;
        if (emberTextView2 != null) {
            emberTextView2.applyFontChangesAsPerSizeKey(fontSizeKey);
            this.newPlaylistButton.setVisibility(4);
        }
        ImageView imageView = (ImageView) findViewById(R.id.visual_shoveler_new_playlist_icon);
        this.newPlaylistIcon = imageView;
        if (imageView != null) {
            imageView.setVisibility(4);
        }
    }

    public void bind(final VisualShovelerElement element) {
        this.header.setText(element.primaryHeader());
        Resources resources = getContext().getResources();
        if (new SonicRushFeature().isEnabled()) {
            this.header.applyFontChangesAsPerSizeKey(FontFaceUtil.FontSizeKey.HEADLINE_4);
        } else {
            this.header.setTypeface(Typeface.createFromAsset(resources.getAssets(), resources.getString(R.string.font_amazon_ember_bold)));
        }
        final List<VerticalItemElement> listItems = element.items();
        if (listItems.size() < 20) {
            this.seeMoreButton.setVisibility(4);
            setAdditionalActionButtons(element, Boolean.TRUE, Boolean.FALSE);
        } else if (element.seeMore() != null && !CollectionUtils.isEmpty(element.seeMore().onItemSelected())) {
            this.seeMoreButton.setVisibility(0);
            setAdditionalActionButtons(element, Boolean.FALSE, Boolean.TRUE);
            if (StringUtils.isNotBlank(element.seeMore().text())) {
                String strText = element.seeMore().text();
                this.seeMoreButton.setText(strText);
                this.seeMoreButton.setContentDescription(strText);
            }
            this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VisualShovelerView.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VisualShovelerView.this.methodsDispatcher.dispatchMethods(VisualShovelerView.this.ownerId, element.seeMore().onItemSelected());
                }
            });
        }
        int i = element.isCompact() ? 17 : 5;
        GridLayoutUtils gridLayoutUtils = this.gridLayoutUtils;
        final int shovelerItemWidth = gridLayoutUtils != null ? gridLayoutUtils.getShovelerItemWidth(20, i) : 0;
        GridLayoutUtils gridLayoutUtils2 = this.gridLayoutUtils;
        if (gridLayoutUtils2 != null) {
            gridLayoutUtils2.setLayoutManager(this.recyclerView, i, 20);
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet != null) {
            lambda$bind$1(shovelerItemWidth, listItems, styleSheet);
        } else {
            getStyleSheetFromObservable(new Action1() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VisualShovelerView$$ExternalSyntheticLambda1
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.lambda$bind$1(shovelerItemWidth, listItems, (StyleSheet) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: bindVisualShovelerAdapter, reason: merged with bridge method [inline-methods] */
    public void lambda$bind$1(int widthInPix, List<VerticalItemElement> items, StyleSheet styleSheet) {
        if (this.curateBootstrapProviders.isAtLeastOneProviderNull()) {
            return;
        }
        VisualShovelerAdapter visualShovelerAdapter = new VisualShovelerAdapter(getContext(), this.ownerId, this.methodsDispatcher, getResources(), widthInPix, this.lifecycleOwner, this.curateBootstrapProviders, styleSheet);
        this.recyclerView.setAdapter(visualShovelerAdapter);
        visualShovelerAdapter.bind(items);
    }

    public void setAdditionalActionButtons(VisualShovelerElement element, Boolean newPlaylistButtonVisible, Boolean newPlaylistIconVisible) {
        if (element.additionalActionButtons() == null || element.additionalActionButtons().size() <= 0 || CollectionUtils.isEmpty(element.additionalActionButtons().get(0).onItemSelected())) {
            return;
        }
        final IngressButtonElement ingressButtonElement = (IngressButtonElement) element.additionalActionButtons().get(0);
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.VisualShovelerView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                VisualShovelerView.this.methodsDispatcher.dispatchMethods(VisualShovelerView.this.ownerId, ingressButtonElement.onItemSelected());
            }
        };
        if (this.newPlaylistButton != null) {
            if (newPlaylistButtonVisible.booleanValue()) {
                this.newPlaylistButton.setText(ingressButtonElement.text());
                this.newPlaylistButton.setContentDescription(ingressButtonElement.text());
                this.newPlaylistButton.setOnClickListener(onClickListener);
                this.newPlaylistButton.setVisibility(0);
            } else {
                this.newPlaylistButton.setVisibility(4);
            }
        }
        if (this.newPlaylistIcon != null) {
            if (newPlaylistIconVisible.booleanValue()) {
                this.newPlaylistIcon.setOnClickListener(onClickListener);
                this.newPlaylistIcon.setVisibility(0);
            } else {
                this.newPlaylistIcon.setVisibility(4);
            }
        }
    }
}
