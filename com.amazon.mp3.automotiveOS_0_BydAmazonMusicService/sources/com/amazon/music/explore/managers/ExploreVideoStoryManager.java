package com.amazon.music.explore.managers;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Queue;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import Touch.WidgetsInterface.v1_0.VideoCarouselElement;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.viewpager.widget.ViewPager;
import com.amazon.music.explore.activity.BaseExploreActivity;
import com.amazon.music.explore.activity.VideoQueueActivity;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.explore.skyfire.OwnerIdGenerator;
import com.amazon.music.explore.skyfire.SkyFireApplicationFactory;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.explore.skyfire.containers.ContainerManager;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback;
import com.amazon.music.explore.views.videoPreview.VideoPreviewItemAdapter;
import com.amazon.music.explore.views.videoqueue.StackingPageTransformer;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.platform.providers.MetricsProvider;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.ui.providers.EnvironmentProvider;
import com.amazon.music.skyfire.ui.providers.NavigationProvider;
import com.amazon.music.skyfire.ui.providers.PlaybackProvider;
import com.amazon.music.skyfire.ui.providers.StorageProvider;
import com.amazon.music.skyfire.ui.skyfire.Methods;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreVideoStoryManager extends ContainerManager implements VideoQueuePlayback.PositionCallback, VideoQueuePlayback.VideoScreenCallback, VideoQueuePlayback.ContentStateCallback {
    private final AuthenticationProvider authenticationProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final Context context;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final EnvironmentProvider environmentProvider;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private final MetricsProvider metricsProvider;
    private final NavigationProvider navigationProvider;
    private float onTouchEndX;
    private float onTouchStartX;
    private PageHeaderUpdateCallback pageHeaderUpdateCallback;
    private final PlaybackProvider playbackProvider;
    public final SkyFireApplication skyFireApplication;
    private final StorageProvider storageProvider;
    private WidgetsTemplate template;
    private VideoCarouselElement videoCarouselElement;
    private int videoCount;
    private VideoPreviewItemAdapter videoPreviewItemAdapter;
    private VideoQueuePlayback videoQueuePlayback;
    private ViewPager viewPager;
    private final String ownerId = OwnerIdGenerator.forTemplateContainer();
    private boolean hasVideoStory = false;
    private boolean areModelsReady = false;
    private final SingleLiveEvent<Boolean> onModelsReady = new SingleLiveEvent<>();
    private final float MAX_CLICK_DISTANCE = 10.0f;

    public interface PageHeaderUpdateCallback {
        void showStoryCoachMarkTutorial();

        void updatePagination(int i, int i2);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.ContentStateCallback
    public void onContentLoading() {
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.ContentStateCallback
    public void onContentReady() {
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackPositionChanged(int i, long j) {
    }

    public ExploreVideoStoryManager(Context context, AuthenticationProvider authenticationProvider, CustomerMetadataProvider customerMetadataProvider, DeviceInfoProvider deviceInfoProvider, BuildInfoProvider buildInfoProvider, NavigationProvider navigationProvider, StorageProvider storageProvider, EnvironmentProvider environmentProvider, MetricsProvider metricsProvider, PlaybackProvider playbackProvider, LifecycleOwner lifecycleOwner) {
        this.context = context;
        this.authenticationProvider = authenticationProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
        this.navigationProvider = navigationProvider;
        this.storageProvider = storageProvider;
        this.environmentProvider = environmentProvider;
        this.metricsProvider = metricsProvider;
        this.playbackProvider = playbackProvider;
        this.skyFireApplication = SkyFireApplicationFactory.getSkyFireApplication(this, authenticationProvider, customerMetadataProvider, deviceInfoProvider, buildInfoProvider, storageProvider, environmentProvider);
        lifecycleOwner.getLifecycle().addObserver(new ContainerManagerLifecycleObserver());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerMethodOwner
    public String getOwnerId() {
        return this.ownerId;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createTemplate(String str, Template template) {
        this.skyFireApplication.dispatchMethods(str, template.onCreated());
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void createAndBindTemplate(String str, Template template) {
        bindTemplate(str, template);
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    protected void bindTemplate(String str, Template template) {
        if (template instanceof WidgetsTemplate) {
            this.template = (WidgetsTemplate) template;
            this.hasVideoStory = true;
            setVideoBackgroundData();
            this.skyFireApplication.dispatchMethods(str, template.onBound());
            this.skyFireApplication.dispatchMethods(str, template.onViewed());
        }
    }

    public void requestVideoBackgroundMethod(String str) {
        this.skyFireApplication.dispatchMethods(this.ownerId, Collections.singletonList(SkyFireUtils.getInvokeHttpSkillMethod(String.format("/showVideoStoryPreview/%s", str), (List<Method>) Collections.singletonList(new TriggerExternalActionMethod() { // from class: com.amazon.music.explore.managers.ExploreVideoStoryManager.1
            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public Map<String, String> context() {
                return null;
            }

            @Override // ExternalActionInterface.v1_0.TriggerExternalActionMethod
            public String name() {
                return Methods.modelsAreReady;
            }

            @Override // CoreInterface.v1_0.Method
            public Queue queue() {
                return SkyFireUtils.mtHttpQueue();
            }
        }))));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void initVideoBackground(Fragment fragment, ViewPager viewPager, FragmentManager fragmentManager) {
        this.fragment = fragment;
        this.pageHeaderUpdateCallback = (PageHeaderUpdateCallback) fragment;
        this.viewPager = viewPager;
        this.fragmentManager = fragmentManager;
        this.videoQueuePlayback = new VideoQueuePlayback(this.context);
        viewPager.setPageTransformer(false, new StackingPageTransformer());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.amazon.music.explore.managers.ExploreVideoStoryManager.2
            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                ExploreVideoStoryManager.this.videoQueuePlayback.playPreviewQueuePosition(i);
            }
        });
        VideoPreviewItemAdapter videoPreviewItemAdapter = new VideoPreviewItemAdapter(fragmentManager);
        this.videoPreviewItemAdapter = videoPreviewItemAdapter;
        videoPreviewItemAdapter.setVideoQueuePlayback(this);
        ViewCompat.setOnApplyWindowInsetsListener(viewPager, new OnApplyWindowInsetsListener() { // from class: com.amazon.music.explore.managers.ExploreVideoStoryManager$$ExternalSyntheticLambda1
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowInsetsCompat) {
                return ExploreVideoStoryManager.lambda$initVideoBackground$0(view, windowInsetsCompat);
            }
        });
        viewPager.setAdapter(this.videoPreviewItemAdapter);
        handleNotch();
        if (this.hasVideoStory) {
            setVideoBackgroundData();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ WindowInsetsCompat lambda$initVideoBackground$0(View view, WindowInsetsCompat windowInsetsCompat) {
        ((VideoPreviewItemAdapter) ((ViewPager) view).getAdapter()).setWindowInsets(windowInsetsCompat);
        return windowInsetsCompat.consumeSystemWindowInsets();
    }

    public void startPlayback() {
        this.videoQueuePlayback.addPositionCallback(this);
        this.videoQueuePlayback.addContentStateCallback(this);
        this.videoQueuePlayback.setVideoScreenCallback(this);
        this.videoQueuePlayback.play();
    }

    public void shutdown() {
        this.skyFireApplication.unregisterOwner(this.ownerId);
        VideoQueuePlayback videoQueuePlayback = this.videoQueuePlayback;
        if (videoQueuePlayback != null) {
            videoQueuePlayback.removePositionCallback(this);
            this.videoQueuePlayback.removeContentStateCallback(this);
            this.videoQueuePlayback.removeVideoScreenCallback();
            this.videoQueuePlayback.shutdown();
        }
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.PositionCallback
    public void onPlaybackFinished() {
        int currentItem = this.viewPager.getCurrentItem();
        int i = currentItem - 2;
        if (currentItem == 0) {
            this.viewPager.setCurrentItem(1, false);
            return;
        }
        if (currentItem == this.videoCount - 1) {
            if (i > 0) {
                this.viewPager.setCurrentItem(2, false);
                this.pageHeaderUpdateCallback.updatePagination(i, 0);
                return;
            } else {
                this.videoQueuePlayback.playPreviewQueuePosition(currentItem);
                return;
            }
        }
        if (currentItem == 1) {
            this.pageHeaderUpdateCallback.showStoryCoachMarkTutorial();
        }
        if (i >= 0) {
            this.pageHeaderUpdateCallback.updatePagination(i, i + 1);
        }
        this.viewPager.setCurrentItem(currentItem + 1, true);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public View.OnTouchListener getTextContainerOnTouchListener() {
        return new View.OnTouchListener() { // from class: com.amazon.music.explore.managers.ExploreVideoStoryManager$$ExternalSyntheticLambda0
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$getTextContainerOnTouchListener$1(view, motionEvent);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$getTextContainerOnTouchListener$1(View view, MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            this.onTouchStartX = motionEvent.getX();
            return true;
        }
        if (action != 1 && action != 3) {
            return false;
        }
        float x = motionEvent.getX();
        this.onTouchEndX = x;
        handleTouch(this.onTouchStartX, x);
        return true;
    }

    private void handleTouch(float f, float f2) {
        int currentItem = this.viewPager.getCurrentItem();
        int i = currentItem - 2;
        if (Math.abs(f - f2) <= 10.0f) {
            openVideoStory(currentItem);
            return;
        }
        if (f <= f2) {
            if (currentItem > 2) {
                this.viewPager.setCurrentItem(currentItem - 1, true);
                this.pageHeaderUpdateCallback.updatePagination(i, i - 1);
                return;
            }
            return;
        }
        if (currentItem == 0) {
            this.viewPager.setCurrentItem(2, true);
            this.pageHeaderUpdateCallback.showStoryCoachMarkTutorial();
        } else if (currentItem < this.videoCount - 1) {
            if (currentItem == 1) {
                this.pageHeaderUpdateCallback.showStoryCoachMarkTutorial();
            }
            if (currentItem > 1) {
                this.pageHeaderUpdateCallback.updatePagination(i, i + 1);
            }
            this.viewPager.setCurrentItem(currentItem + 1, true);
        }
    }

    public int getPaginationCount() {
        return this.videoCount - 2;
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback.VideoScreenCallback
    public TextureView getTextureView(int i) {
        return this.videoPreviewItemAdapter.getTextureView(i);
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.skyFireApplication;
    }

    public VideoQueuePlayback getVideoQueuePlayback() {
        return this.videoQueuePlayback;
    }

    public VideoCarouselElement getVideoCarouselElement() {
        return this.videoCarouselElement;
    }

    public Context getContext() {
        return this.context;
    }

    public boolean getHasVideoStory() {
        return this.hasVideoStory;
    }

    public SingleLiveEvent<Boolean> onModelsReady() {
        return this.onModelsReady;
    }

    public ViewPager getViewPager() {
        return this.viewPager;
    }

    @Override // com.amazon.music.explore.skyfire.containers.ContainerManager
    public void handleTemplateMethod(String str, Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            handleExternalAction((TriggerExternalActionMethod) method);
        }
    }

    private void setVideoBackgroundData() {
        this.viewPager.setVisibility(0);
        VideoCarouselElement videoCarouselElement = (VideoCarouselElement) this.template.widgets().get(0);
        this.videoCarouselElement = videoCarouselElement;
        this.videoQueuePlayback.setVideoPreviewQueue(videoCarouselElement);
        this.videoPreviewItemAdapter.setVideoPreviewQueue(this);
        this.videoCount = getVideoCount(this.videoCarouselElement);
    }

    private int getVideoCount(VideoCarouselElement videoCarouselElement) {
        int size = videoCarouselElement.items().size();
        this.videoCount = size;
        return size + 2;
    }

    private void handleNotch() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        this.fragment.getActivity().getWindow().getAttributes().layoutInDisplayCutoutMode = 1;
    }

    private class ContainerManagerLifecycleObserver implements LifecycleObserver {
        private ContainerManagerLifecycleObserver() {
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void closeManager() {
            ExploreVideoStoryManager.this.onClose();
        }
    }

    private void handleExternalAction(TriggerExternalActionMethod triggerExternalActionMethod) {
        if (Methods.modelsAreReady.equals(triggerExternalActionMethod.name())) {
            this.areModelsReady = true;
            this.onModelsReady.setValue(Boolean.TRUE);
        }
    }

    public void openVideoStory(int i) {
        int i2 = i < 2 ? 0 : i - 2;
        Intent intent = new Intent(this.fragment.getActivity(), (Class<?>) VideoQueueActivity.class);
        intent.setFlags(268435456);
        try {
            intent.putExtra(BaseExploreActivity.METHODS_TO_INVOKE, SkyFireUtils.getSerializedMethods(this.videoCarouselElement.items().get(i2).onItemSelected()));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        this.fragment.getActivity().startActivity(intent);
    }
}
