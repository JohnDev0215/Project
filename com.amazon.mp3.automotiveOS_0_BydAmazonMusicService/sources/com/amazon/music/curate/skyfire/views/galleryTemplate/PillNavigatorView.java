package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.account.SonicRushFeature;
import com.amazon.music.curate.R;
import com.amazon.music.curate.featuregating.CurateFeatureGating;
import com.amazon.music.curate.skyfire.FontFaceUtil;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.EmberTextView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
final class PillNavigatorView extends LinearLayout {
    private static final String TAG;
    private static final Logger logger;
    private final CurateBootstrapProviders curateBootstrapProviders;
    private EmberTextView header;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private RecyclerView recyclerView;
    private StyleSheet styleSheet;
    private Subscriber<StyleSheet> styleSheetSubscriber;
    private Subscription stylesheetSubscription;

    static {
        String simpleName = LinkNavigatorView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PillNavigatorView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final CurateBootstrapProviders curateBootstrapProviders) {
        super(context);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.curateBootstrapProviders = curateBootstrapProviders;
        View.inflate(context, R.layout.library_pill_navigator, this);
        this.header = (EmberTextView) findViewById(R.id.library_pill_navigator_header);
        this.recyclerView = (RecyclerView) findViewById(R.id.library_pill_navigator_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.recyclerView.setAdapter(new PillNavigatorItemAdapter(ownerId, methodsDispatcher, curateBootstrapProviders));
        initViewStyling();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewStyling() {
        FontStyle fontStyle;
        if (this.styleSheet == null) {
            if (this.stylesheetSubscription == null && this.styleSheetSubscriber == null) {
                this.styleSheetSubscriber = getStyleSheetSubscriber();
            }
            CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
            if (curateBootstrapProviders != null && curateBootstrapProviders.getStyleSheetProvider() != null) {
                this.stylesheetSubscription = this.curateBootstrapProviders.getStyleSheetProvider().getStyleSheetObservable().observeOn(Schedulers.io()).subscribe((Subscriber<? super StyleSheet>) this.styleSheetSubscriber);
            }
        }
        StyleSheet styleSheet = this.styleSheet;
        if (styleSheet == null || (fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4)) == null) {
            return;
        }
        if (new SonicRushFeature().isEnabled()) {
            this.header.applyFontChangesAsPerSizeKey(FontFaceUtil.FontSizeKey.HEADLINE_4);
        } else {
            FontUtil.INSTANCE.applyFontStyle(this.header, fontStyle);
        }
    }

    private Subscriber<StyleSheet> getStyleSheetSubscriber() {
        return new Subscriber<StyleSheet>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.PillNavigatorView.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override // rx.Observer
            public void onNext(final StyleSheet style) {
                PillNavigatorView.this.styleSheet = style;
                PillNavigatorView.this.initViewStyling();
            }
        };
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscriber<StyleSheet> subscriber = this.styleSheetSubscriber;
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    public void bind(final PillNavigatorWidgetElement element) {
        if (!CurateFeatureGating.NATIVE_PROFILE_MY_LIBRARY_PAGE_ANDROID.isEnabled()) {
            this.header.setText(element.header());
        } else {
            this.header.setVisibility(8);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.library_pill_navigator);
            if (linearLayout != null) {
                linearLayout.setPadding(linearLayout.getPaddingLeft(), linearLayout.getPaddingTop(), (int) linearLayout.getResources().getDimension(R.dimen.spacer_base), linearLayout.getPaddingBottom());
            }
        }
        setContentDescription(element.header());
        ((PillNavigatorItemAdapter) this.recyclerView.getAdapter()).bind(element.items());
    }
}
