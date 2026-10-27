package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.LinkNavigatorElement;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
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
final class LinkNavigatorView extends LinearLayout {
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

    public LinkNavigatorView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final CurateBootstrapProviders curateBootstrapProviders) {
        super(context);
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.curateBootstrapProviders = curateBootstrapProviders;
        View.inflate(context, R.layout.link_navigator, this);
        this.header = (EmberTextView) findViewById(R.id.link_navigator_header);
        this.recyclerView = (RecyclerView) findViewById(R.id.link_navigator_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(new LinkNavigatorItemAdapter(ownerId, methodsDispatcher, getResources()));
        this.recyclerView.addItemDecoration(new DividerItemDecorator(this.recyclerView.getContext(), 1, ContextCompat.getDrawable(context, R.drawable.row_items_divider_view)));
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
        FontUtil.INSTANCE.applyFontStyle(this.header, fontStyle);
    }

    private Subscriber<StyleSheet> getStyleSheetSubscriber() {
        return new Subscriber<StyleSheet>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.LinkNavigatorView.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override // rx.Observer
            public void onNext(final StyleSheet style) {
                LinkNavigatorView.this.styleSheet = style;
                LinkNavigatorView.this.initViewStyling();
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

    public void bind(final LinkNavigatorElement element) {
        this.header.setText(element.header());
        this.header.setContentDescription(element.header());
        ((LinkNavigatorItemAdapter) this.recyclerView.getAdapter()).bind(element.items());
    }
}
