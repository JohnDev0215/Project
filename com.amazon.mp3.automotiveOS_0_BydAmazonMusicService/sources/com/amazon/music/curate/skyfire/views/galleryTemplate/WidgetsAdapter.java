package com.amazon.music.curate.skyfire.views.galleryTemplate;

import Touch.WidgetsInterface.v1_0.LinkNavigatorElement;
import Touch.WidgetsInterface.v1_0.MessageElement;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetElement;
import Touch.WidgetsInterface.v1_0.VisualShovelerElement;
import Touch.WidgetsInterface.v1_0.WidgetElement;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.Curate;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;

/* JADX INFO: loaded from: classes4.dex */
final class WidgetsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "WidgetsAdapter";
    private static final Logger logger = LoggerFactory.getLogger(WidgetsAdapter.class.getSimpleName());
    private CurateBootstrapProviders curateBootstrapProviders;
    private String header;
    private Integer headerPosition;
    private HeaderViewHolder headerViewHolder;
    private Integer itemCount = 0;
    private final LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private Resources resources;
    private int screenWidth;
    private StyleSheet styleSheet;
    private Subscriber<StyleSheet> styleSheetSubscriber;
    private Subscription stylesheetSubscription;
    private List<WidgetElement> widgets;

    private enum ViewType {
        header(0),
        pillNavigator(1),
        visualShoveler(2),
        linkNavigator(3),
        messageElement(4);

        private final int value;

        ViewType(final int value) {
            this.value = value;
        }
    }

    public WidgetsAdapter(final LifecycleOwner lifecycleOwner, final String ownerId, final Resources resources, final MethodsDispatcher methodsDispatcher, final CurateBootstrapProviders curateBootstrapProviders) {
        this.lifecycleOwner = lifecycleOwner;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.resources = resources;
        this.curateBootstrapProviders = curateBootstrapProviders;
        this.screenWidth = resources.getDisplayMetrics().widthPixels;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int viewType) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(viewGroup.getContext());
        initViewStyling();
        if (ViewType.pillNavigator.value != viewType) {
            if (ViewType.visualShoveler.value != viewType) {
                if (ViewType.linkNavigator.value != viewType) {
                    if (ViewType.messageElement.value == viewType) {
                        return new MessageViewHolder(new MessageView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.curateBootstrapProviders));
                    }
                    HeaderViewHolder headerViewHolder = new HeaderViewHolder(layoutInflaterFrom.inflate(R.layout.library_gallery_template_header, viewGroup, false));
                    this.headerViewHolder = headerViewHolder;
                    return headerViewHolder;
                }
                return new LinkNavigatorViewHolder(new LinkNavigatorView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.curateBootstrapProviders));
            }
            return new VisualShovelerViewHolder(new VisualShovelerView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.lifecycleOwner, this.curateBootstrapProviders));
        }
        return new PillNavigatorViewHolder(new PillNavigatorView(viewGroup.getContext(), this.ownerId, this.methodsDispatcher, this.curateBootstrapProviders));
    }

    private void checkProviders() {
        LifecycleOwner lifecycleOwner;
        if (this.curateBootstrapProviders.getStyleSheetProvider() == null) {
            logger.debug("StyleSheet provider was null. Assigning value...");
            this.curateBootstrapProviders.setStyleSheetProvider(Curate.getStaticStyleSheetProvider());
        }
        if (this.curateBootstrapProviders.getPlaybackProvider() == null) {
            logger.debug("Playback provider was null. Assigning value...");
            this.curateBootstrapProviders.setPlaybackProvider(Curate.getStaticPlaybackProvider());
        }
        if (this.curateBootstrapProviders.getSubscriptionProvider() == null) {
            logger.debug("Subscriptions provider was null. Assigning value...");
            this.curateBootstrapProviders.setSubscriptionProvider(Curate.getSubscriptionStaticProvider());
        }
        if (this.curateBootstrapProviders.getNetworkProvider() == null) {
            logger.debug("Network provider was null. Assigning value...");
            this.curateBootstrapProviders.setNetworkProvider(Curate.getNetworkStaticProvider());
        }
        if (!this.curateBootstrapProviders.isAtLeastOneProviderNull() || (lifecycleOwner = this.lifecycleOwner) == null) {
            return;
        }
        logger.error("Lifecycle state was {}", lifecycleOwner.getLifecycle().getCurrentState());
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int position) {
        initViewStyling();
        if (this.headerPosition != null) {
            position--;
        }
        if (viewHolder instanceof PillNavigatorViewHolder) {
            ((PillNavigatorViewHolder) viewHolder).bind((PillNavigatorWidgetElement) this.widgets.get(position));
            return;
        }
        if (viewHolder instanceof LinkNavigatorViewHolder) {
            ((LinkNavigatorViewHolder) viewHolder).bind((LinkNavigatorElement) this.widgets.get(position));
        } else if (viewHolder instanceof VisualShovelerViewHolder) {
            ((VisualShovelerViewHolder) viewHolder).bind((VisualShovelerElement) this.widgets.get(position));
        } else if (viewHolder instanceof MessageViewHolder) {
            ((MessageViewHolder) viewHolder).bind((MessageElement) this.widgets.get(position));
        } else {
            ((HeaderViewHolder) viewHolder).bind(this.header);
        }
    }

    public HeaderViewHolder getHeaderViewHolder() {
        return this.headerViewHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.itemCount.intValue();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(final int position) {
        Integer num = this.headerPosition;
        if (num != null && num.intValue() == position) {
            return ViewType.header.value;
        }
        if (this.headerPosition != null) {
            position--;
        }
        if (this.widgets.get(position) instanceof PillNavigatorWidgetElement) {
            return ViewType.pillNavigator.value;
        }
        if (this.widgets.get(position) instanceof VisualShovelerElement) {
            return ViewType.visualShoveler.value;
        }
        if (this.widgets.get(position) instanceof LinkNavigatorElement) {
            return ViewType.linkNavigator.value;
        }
        return this.widgets.get(position) instanceof MessageElement ? ViewType.messageElement.value : ViewType.header.value;
    }

    public void bind(final List<WidgetElement> widgets, final String header) {
        ArrayList arrayList = new ArrayList(widgets);
        this.widgets = arrayList;
        this.header = header;
        this.itemCount = Integer.valueOf(arrayList.size());
        this.headerPosition = null;
        if (StringUtils.isNotBlank(header)) {
            this.headerPosition = 0;
            this.itemCount = Integer.valueOf(this.itemCount.intValue() + 1);
        }
        notifyDataSetChanged();
    }

    void addItems(final List<WidgetElement> items) {
        if (this.widgets == null || items == null || items.isEmpty()) {
            return;
        }
        this.widgets.addAll(items);
        this.itemCount = Integer.valueOf(this.itemCount.intValue() + items.size());
        notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initViewStyling() {
        if (this.curateBootstrapProviders == null) {
            return;
        }
        checkProviders();
        if (this.styleSheet == null && this.stylesheetSubscription == null) {
            if (this.styleSheetSubscriber == null) {
                this.styleSheetSubscriber = getStyleSheetSubscriber();
            }
            CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
            if (curateBootstrapProviders == null || curateBootstrapProviders.getStyleSheetProvider() == null) {
                return;
            }
            this.stylesheetSubscription = this.curateBootstrapProviders.getStyleSheetProvider().getStyleSheetObservable().observeOn(Schedulers.io()).subscribe((Subscriber<? super StyleSheet>) this.styleSheetSubscriber);
        }
    }

    private Subscriber<StyleSheet> getStyleSheetSubscriber() {
        return new Subscriber<StyleSheet>() { // from class: com.amazon.music.curate.skyfire.views.galleryTemplate.WidgetsAdapter.1
            @Override // rx.Observer
            public void onCompleted() {
            }

            @Override // rx.Observer
            public void onError(final Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override // rx.Observer
            public void onNext(final StyleSheet style) {
                Log.d(WidgetsAdapter.class.getSimpleName(), "On Next was called");
                WidgetsAdapter.this.styleSheet = style;
                WidgetsAdapter.this.initViewStyling();
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onViewDetachedFromWindow(@NonNull final RecyclerView.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        Subscription subscription = this.stylesheetSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        Subscriber<StyleSheet> subscriber = this.styleSheetSubscriber;
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        logger.debug("onDetachedFromRecyclerView set providers to null");
        this.curateBootstrapProviders = null;
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
}
