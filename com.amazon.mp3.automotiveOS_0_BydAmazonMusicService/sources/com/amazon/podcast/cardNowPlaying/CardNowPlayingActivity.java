package com.amazon.podcast.cardNowPlaying;

import Podcast.FollowInterface.v1_0.FooterElement;
import Podcast.PlaybackInterface.v1_0.ClearMediaMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.AddCardsMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardElement;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplate;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.NextCardMethod;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.PreviousCardMethod;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.Template;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.TemplateCache;
import com.amazon.podcast.TemplateCacheEntry;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.views.TemplateActivity;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class CardNowPlayingActivity extends TemplateActivity<CardNowPlayingTemplate> {
    private static final Logger logger = LoggerFactory.getLogger(CardNowPlayingActivity.class.getSimpleName());
    private ActionBarProvider actionBarProvider;
    private CardNowPlayingTemplateAdapter adapter;
    private List<CardElement> cards;
    private ImageButton headerCloseButton;
    private RecyclerView innerRecyclerView;
    private boolean isCreateAndBind;
    private boolean isEndOfListTriggered;
    private RelativeLayout leftPreview;
    private RelativeLayout leftPreviewGradient;
    private RelativeLayout loadingScreen;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onDismissed;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private Playback playback;
    private Resources resources;
    private RelativeLayout rightPreview;
    private RelativeLayout rightPreviewGradient;
    private int screenHeight;
    private int screenWidth;
    private CardNowPlayingTemplate template;
    private ViewPager2 viewPager2;
    private Integer lastPosition = null;
    private boolean shouldClearMedia = true;
    private boolean isPreviewTapped = false;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ownerId = null;
        this.playback = Podcast.getPlayback();
        this.resources = getResources();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.screenWidth = displayMetrics.widthPixels;
        this.screenHeight = displayMetrics.heightPixels;
        Intent intent = getIntent();
        if (intent != null) {
            this.ownerId = intent.getStringExtra("ownerId");
        } else if (savedInstanceState != null) {
            this.ownerId = savedInstanceState.getString("ownerId");
        }
        Podcast.getTemplates().setCardNowPlayingOwner(this.ownerId);
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        bindFromCache(str);
        initViews();
        this.ownerRegistration.registerOwner(this.ownerId);
        this.methodsDispatcher.dispatch(this.ownerId, this.template.getOnCreated());
        this.playback.pause();
        if (this.isCreateAndBind) {
            bind(this.template, true);
        } else {
            this.loadingScreen.setVisibility(0);
        }
        bindCloseButton();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(final Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        outState.putString("ownerId", str);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        List<Method> list = this.onViewed;
        if (list == null || list.isEmpty() || !StringUtils.isNotBlank(this.ownerId)) {
            return;
        }
        handleOnViewed();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.playback.getPlaybackState() == 2) {
            return;
        }
        this.playback.pause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        CardNowPlayingViewHolder cardViewHolder;
        super.onDestroy();
        this.playback.setBitesPlaybackStartingIndex(0);
        if (this.shouldClearMedia) {
            this.playback.dispatch(getOwnerId(), this.onDismissed);
        }
        Podcast.getTemplates().setCardNowPlayingOwner(null);
        Integer num = this.lastPosition;
        if (num != null && (cardViewHolder = getCardViewHolder(num.intValue())) != null) {
            cardViewHolder.removePlaybackCallbacks();
        }
        String str = this.ownerId;
        if (str == null) {
            return;
        }
        TemplateCache.INSTANCE.delete(str);
        OwnerRegistration ownerRegistration = this.ownerRegistration;
        if (ownerRegistration == null) {
            return;
        }
        ownerRegistration.deregisterOwner(this.ownerId);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof NextCardMethod) {
            moveToNextCard();
        } else if (method instanceof PreviousCardMethod) {
            moveToPreviousCard();
        } else if (method instanceof AddCardsMethod) {
            addCardsMethod((AddCardsMethod) method);
        }
    }

    @Override // com.amazon.podcast.views.TemplateActivity
    public final String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.podcast.views.TemplateActivity
    public final Template getTemplate() {
        return this.template;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final CardNowPlayingTemplate template, final boolean hideActionBar) {
        this.loadingScreen.setVisibility(8);
        this.cards = template.getCards();
        this.onViewed = template.getOnViewed();
        handleOnViewed();
        this.onEndOfList = template.getOnEndOfList();
        this.onDismissed = template.getOnDismissed();
        this.isEndOfListTriggered = false;
        CardNowPlayingTemplateAdapter cardNowPlayingTemplateAdapter = (CardNowPlayingTemplateAdapter) this.viewPager2.getAdapter();
        this.adapter = cardNowPlayingTemplateAdapter;
        cardNowPlayingTemplateAdapter.bindCards(this.cards);
        List<Method> list = this.onEndOfList;
        if (list == null || list.isEmpty()) {
            this.adapter.addPTCIngress();
            this.isEndOfListTriggered = true;
        }
        setCardPeekBehavior();
        this.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingActivity.1
            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (CardNowPlayingActivity.this.lastPosition == null || CardNowPlayingActivity.this.lastPosition.intValue() != position) {
                    CardNowPlayingActivity.this.setPreviewButtonsAccessibilityState(position);
                    CardNowPlayingActivity.this.previousCardCleanup();
                    if (CardNowPlayingActivity.this.isPreviewTapped) {
                        CardNowPlayingActivity.this.dispatchOnTapMethods(position);
                    } else {
                        CardNowPlayingActivity.this.dispatchOnSwipeMethods(position);
                    }
                    if (CardNowPlayingActivity.this.adapter.getItem(position) != null) {
                        if (CardNowPlayingActivity.this.adapter.getItem(position) instanceof CardElement) {
                            CardElement cardElement = (CardElement) CardNowPlayingActivity.this.adapter.getItem(position);
                            if (CardNowPlayingActivity.this.methodsDispatcher != null) {
                                CardNowPlayingActivity.this.methodsDispatcher.dispatch(CardNowPlayingActivity.this.ownerId, cardElement.getOnCardViewed());
                            }
                            if (!CardNowPlayingActivity.this.adapter.isInitialBind()) {
                                CardNowPlayingActivity.this.handleCurrentCardPlayback(cardElement, position);
                            }
                        } else {
                            if (CardNowPlayingActivity.this.adapter.getItem(position) instanceof FooterElement) {
                                CardNowPlayingActivity.this.dispatchOnPTCIngressViewed();
                            }
                            CardNowPlayingActivity.this.playback.dispatch(CardNowPlayingActivity.this.getOwnerId(), Collections.singletonList(ClearMediaMethod.builder().withForced(Boolean.TRUE).withQueue(Queues.playback()).build()));
                        }
                    }
                    CardNowPlayingActivity.this.isPreviewTapped = false;
                    CardNowPlayingActivity.this.lastPosition = Integer.valueOf(position);
                }
            }

            @Override // androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
            public void onPageScrolled(int position, float positionOffset, @Px int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (CardNowPlayingActivity.this.adapter.getItemCount() - position > 5 || CardNowPlayingActivity.this.isEndOfListTriggered) {
                    return;
                }
                if (CardNowPlayingActivity.this.onEndOfList == null || CardNowPlayingActivity.this.onEndOfList.isEmpty()) {
                    CardNowPlayingActivity.this.adapter.addPTCIngress();
                } else {
                    CardNowPlayingActivity.this.methodsDispatcher.dispatch(CardNowPlayingActivity.this.getOwnerId(), CardNowPlayingActivity.this.onEndOfList);
                }
                CardNowPlayingActivity.this.isEndOfListTriggered = true;
            }
        });
        if (this.playback.getBitesPlaybackStartingIndex() == 0 || !this.adapter.isInitialBind()) {
            return;
        }
        this.viewPager2.setCurrentItem(this.playback.getBitesPlaybackStartingIndex());
    }

    private void bindFromCache(final String cacheId) {
        if (cacheId == null) {
            return;
        }
        TemplateCacheEntry templateCacheEntry = TemplateCache.INSTANCE.read(cacheId);
        if (templateCacheEntry == null) {
            logger.debug("cache is null for {}", cacheId);
            return;
        }
        Template template = templateCacheEntry.getTemplate();
        logger.debug("cache restored for template {}", template.getClass().getSimpleName());
        this.ownerId = cacheId;
        this.template = (CardNowPlayingTemplate) template;
        this.methodsDispatcher = templateCacheEntry.getMethodsDispatcher();
        this.ownerRegistration = templateCacheEntry.getOwnerRegistration();
        this.actionBarProvider = templateCacheEntry.getActionBarProvider();
        this.isCreateAndBind = templateCacheEntry.isCreateAndBind();
    }

    private void initViews() {
        setContentView(R$layout.podcast_card_now_playing_template_view);
        this.headerCloseButton = (ImageButton) findViewById(R$id.podcast_card_now_playing_screen_dismiss_button);
        this.loadingScreen = (RelativeLayout) findViewById(R$id.podcast_card_now_playing_view_loading_screen);
        this.viewPager2 = (ViewPager2) findViewById(R$id.podcast_card_now_playing_view_Pager2);
        this.leftPreviewGradient = (RelativeLayout) findViewById(R$id.podcast_card_now_playing_large_device_left_preview_gradient);
        this.rightPreviewGradient = (RelativeLayout) findViewById(R$id.podcast_card_now_playing_large_device_right_preview_gradient);
        this.leftPreview = (RelativeLayout) findViewById(R$id.podcast_card_now_playing_left_preview_button);
        this.rightPreview = (RelativeLayout) findViewById(R$id.podcast_card_now_playing_right_preview_button);
        this.viewPager2.setAdapter(new CardNowPlayingTemplateAdapter(getOwnerId(), this.resources, this.methodsDispatcher, getSupportFragmentManager(), this, getCardWidth(), getCardHeight()));
    }

    private void bindCloseButton() {
        this.headerCloseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CardNowPlayingActivity.this.finish();
            }
        });
        this.headerCloseButton.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToPreviousCard() {
        int currentItem = this.viewPager2.getCurrentItem() - 1;
        if (currentItem < 0) {
            return;
        }
        this.viewPager2.setCurrentItem(currentItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveToNextCard() {
        int currentItem = this.viewPager2.getCurrentItem() + 1;
        if (currentItem >= this.adapter.getItemCount()) {
            return;
        }
        this.viewPager2.setCurrentItem(currentItem);
        ContextMenus.dismissContextMenus();
    }

    private CardNowPlayingViewHolder getCardViewHolder(int position) {
        if (position >= 0 && position < this.adapter.getItemCount()) {
            RecyclerView.ViewHolder viewHolderFindViewHolderForAdapterPosition = this.innerRecyclerView.findViewHolderForAdapterPosition(position);
            if (viewHolderFindViewHolderForAdapterPosition instanceof CardNowPlayingViewHolder) {
                return (CardNowPlayingViewHolder) viewHolderFindViewHolderForAdapterPosition;
            }
        }
        return null;
    }

    private int getPeekSpaceWidth() {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_card_max_width);
        int dimensionPixelSize2 = this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_left_margin) + this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_right_margin);
        int i = this.screenWidth;
        if (i - (dimensionPixelSize2 * 4) <= dimensionPixelSize) {
            return dimensionPixelSize2 + (dimensionPixelSize2 / 2);
        }
        return ((i - dimensionPixelSize) - (dimensionPixelSize2 * 2)) / 2;
    }

    private int getCardWidth() {
        return (this.screenWidth - (getPeekSpaceWidth() * 2)) - (this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_left_margin) + this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_right_margin));
    }

    private int getCardHeight() {
        int dimensionPixelSize = this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_bottom_spacer);
        return (this.screenHeight - dimensionPixelSize) - this.resources.getDimensionPixelSize(R$dimen.podcast_spacer_epic);
    }

    private void setCardPeekBehavior() {
        this.viewPager2.setOffscreenPageLimit(1);
        RecyclerView recyclerView = (RecyclerView) this.viewPager2.getChildAt(0);
        this.innerRecyclerView = recyclerView;
        recyclerView.setClipToPadding(false);
        int peekSpaceWidth = getPeekSpaceWidth();
        this.innerRecyclerView.setPadding(peekSpaceWidth, 0, peekSpaceWidth, 0);
        ViewGroup.LayoutParams layoutParams = this.leftPreview.getLayoutParams();
        layoutParams.width = peekSpaceWidth - this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_left_margin);
        this.leftPreview.setLayoutParams(layoutParams);
        this.leftPreview.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardNowPlayingActivity.this.viewPager2.getCurrentItem() - 1 < 0) {
                    return;
                }
                CardNowPlayingActivity.this.isPreviewTapped = true;
                CardNowPlayingActivity.this.moveToPreviousCard();
            }
        });
        ViewGroup.LayoutParams layoutParams2 = this.rightPreview.getLayoutParams();
        layoutParams2.width = peekSpaceWidth - this.resources.getDimensionPixelSize(R$dimen.podcast_card_outside_right_margin);
        this.rightPreview.setLayoutParams(layoutParams2);
        this.rightPreview.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.cardNowPlaying.CardNowPlayingActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (CardNowPlayingActivity.this.viewPager2.getCurrentItem() + 1 >= CardNowPlayingActivity.this.adapter.getItemCount()) {
                    return;
                }
                CardNowPlayingActivity.this.isPreviewTapped = true;
                CardNowPlayingActivity.this.moveToNextCard();
            }
        });
        if (this.resources.getDisplayMetrics().densityDpi >= 640) {
            this.leftPreviewGradient.setLayoutParams(layoutParams);
            this.leftPreviewGradient.setVisibility(0);
            this.rightPreviewGradient.setLayoutParams(layoutParams2);
            this.rightPreviewGradient.setVisibility(0);
        }
    }

    public void setShouldClearMedia(boolean shouldClearMedia) {
        this.shouldClearMedia = shouldClearMedia;
    }

    private void addCardsMethod(final AddCardsMethod method) {
        this.onEndOfList = method.getOnEndOfList();
        this.adapter.addItems(method.getCards());
        this.isEndOfListTriggered = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void previousCardCleanup() {
        CardNowPlayingViewHolder cardViewHolder;
        Integer num = this.lastPosition;
        if (num == null || (cardViewHolder = getCardViewHolder(num.intValue())) == null) {
            return;
        }
        cardViewHolder.onPlaybackStateChange(2);
        cardViewHolder.removePlaybackCallbacks();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleCurrentCardPlayback(final CardElement cardElement, final int position) {
        CardNowPlayingViewHolder cardViewHolder;
        if (cardElement.getOnCardCentered() == null || (cardViewHolder = getCardViewHolder(position)) == null) {
            return;
        }
        cardViewHolder.onPlaybackStateChange(8);
        cardViewHolder.addMediaMetadataCallbacks();
        this.playback.dispatch(getOwnerId(), cardElement.getOnCardCentered());
    }

    public List<Method> getOnDismissed() {
        return this.onDismissed;
    }

    private void handleOnViewed() {
        MethodsDispatcher methodsDispatcher = this.methodsDispatcher;
        if (methodsDispatcher != null) {
            methodsDispatcher.dispatch(this.ownerId, this.onViewed);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnSwipeMethods(final int position) {
        Integer num = this.lastPosition;
        if (num != null && (this.adapter.getItem(num.intValue()) instanceof CardElement)) {
            CardElement cardElement = (CardElement) this.adapter.getItem(this.lastPosition.intValue());
            if (position > this.lastPosition.intValue()) {
                this.methodsDispatcher.dispatch(getOwnerId(), cardElement.getOnSwipeNext());
            } else if (position < this.lastPosition.intValue()) {
                this.methodsDispatcher.dispatch(getOwnerId(), cardElement.getOnSwipePrevious());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnTapMethods(final int position) {
        Integer num = this.lastPosition;
        if (num != null && (this.adapter.getItem(num.intValue()) instanceof CardElement)) {
            CardElement cardElement = (CardElement) this.adapter.getItem(this.lastPosition.intValue());
            if (position > this.lastPosition.intValue()) {
                this.methodsDispatcher.dispatch(getOwnerId(), cardElement.getOnTapNext());
            } else if (position < this.lastPosition.intValue()) {
                this.methodsDispatcher.dispatch(getOwnerId(), cardElement.getOnTapPrevious());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchOnPTCIngressViewed() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_CARD_NOW_PLAYING, UiMetricAttributes$ContainerType.CARD_NOW_PLAYING, UiMetricAttributes$ContentName.PODCAST_CARD_PTC));
        MethodsDispatcher methodsDispatcher = this.methodsDispatcher;
        if (methodsDispatcher != null) {
            methodsDispatcher.dispatch(this.ownerId, arrayList);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPreviewButtonsAccessibilityState(final int position) {
        List<Method> list;
        if (position == 0) {
            this.leftPreview.setImportantForAccessibility(2);
        } else if (this.leftPreview.getImportantForAccessibility() != 1) {
            this.leftPreview.setImportantForAccessibility(1);
        }
        if (position == this.adapter.getItemCount() - 1 && ((list = this.onEndOfList) == null || list.isEmpty())) {
            this.rightPreview.setImportantForAccessibility(2);
        } else if (this.rightPreview.getImportantForAccessibility() != 1) {
            this.rightPreview.setImportantForAccessibility(1);
        }
    }
}
