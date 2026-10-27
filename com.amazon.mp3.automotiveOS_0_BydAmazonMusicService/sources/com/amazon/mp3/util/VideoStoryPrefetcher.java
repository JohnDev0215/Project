package com.amazon.mp3.util;

import CoreInterface.v1_0.Method;
import Touch.LiveStreamPlayerTemplateInterface.v1_0.ImmutableDisplayGenericErrorMethod;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.amazon.mp3.prime.browse.BrowseFactory;
import com.amazon.mp3.video.stories.VideoStoryFragment;
import com.amazon.music.browse.BrowseException;
import com.amazon.music.browse.page.UiPage;
import com.amazon.music.explore.Mappers;
import com.amazon.music.explore.skyfire.SkyFireUtils;
import com.amazon.music.inappmessaging.internal.model.Splash;
import com.amazon.musicensembleservice.brush.Block;
import com.amazon.musicensembleservice.brush.Content;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.PageResponse;
import com.amazon.musicensembleservice.brush.VideoStory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/* JADX INFO: compiled from: VideoStoryPrefetcher.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0013\u001a\u00020\u0012¢\u0006\u0004\b\u001e\u0010\u001fJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004H\u0002J\u0016\u0010\t\u001a\u00020\u00022\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0012\u0010\f\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\nH\u0002J\u0016\u0010\u000e\u001a\u00020\u00062\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0006\u0010\u000f\u001a\u00020\u0002J\u0018\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005R\u0017\u0010\u0013\u001a\u00020\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0018\u0010\u0017\u001a\u0004\u0018\u00010\u00068\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u0019\u0010\u0018R\u0014\u0010\u001a\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001a\u0010\u0018R\u0014\u0010\u001b\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001b\u0010\u0018R\u0014\u0010\u001c\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001c\u0010\u0018R\u0014\u0010\u001d\u001a\u00020\u00068\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001d\u0010\u0018¨\u0006 "}, d2 = {"Lcom/amazon/mp3/util/VideoStoryPrefetcher;", "", "", "fetchFirstNextToken", "Lrx/Observable;", "", "", "fetchNewAsins", "asinsList", "getMoreVideoStories", "Lcom/amazon/musicensembleservice/brush/PageResponse;", "response", "setNewNextToken", "metadataList", "getStoryAsins", "prefetchStories", "storiesMetadata", "getSerializedMethods", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "fragment", "Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "getFragment", "()Lcom/amazon/mp3/video/stories/VideoStoryFragment;", "nextToken", "Ljava/lang/String;", "SHOW_VIDEO_STORY_KEY", "STORIES_URI", "IDS_KEY", "SEPARATOR", "DEVICE_TYPE_KEY", "<init>", "(Lcom/amazon/mp3/video/stories/VideoStoryFragment;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoStoryPrefetcher {
    private final String DEVICE_TYPE_KEY;
    private final String IDS_KEY;
    private final String SEPARATOR;
    private final String SHOW_VIDEO_STORY_KEY;
    private final String STORIES_URI;
    private final VideoStoryFragment fragment;
    private String nextToken;

    public VideoStoryPrefetcher(VideoStoryFragment fragment) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        this.fragment = fragment;
        this.SHOW_VIDEO_STORY_KEY = "/showVideoStory";
        this.STORIES_URI = "uri://home/recommendation/be1f5653-3ac3-481d-ad48-91c7ae508533";
        this.IDS_KEY = "ids";
        this.SEPARATOR = ",";
        this.DEVICE_TYPE_KEY = Splash.PARAMS_DEVICE_TYPE;
        fetchFirstNextToken();
    }

    public final void prefetchStories() {
        if (this.nextToken == null) {
            return;
        }
        fetchNewAsins().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.util.VideoStoryPrefetcher$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryPrefetcher.m1653prefetchStories$lambda0(this.f$0, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: prefetchStories$lambda-0, reason: not valid java name */
    public static final void m1653prefetchStories$lambda0(VideoStoryPrefetcher this$0, List asinsList) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(asinsList, "asinsList");
        this$0.getMoreVideoStories(asinsList);
    }

    private final void fetchFirstNextToken() {
        Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.util.VideoStoryPrefetcher$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryPrefetcher.m1650fetchFirstNextToken$lambda1(this.f$0, (Subscriber) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.util.VideoStoryPrefetcher$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                VideoStoryPrefetcher.m1651fetchFirstNextToken$lambda2(this.f$0, (PageResponse) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchFirstNextToken$lambda-1, reason: not valid java name */
    public static final void m1650fetchFirstNextToken$lambda1(VideoStoryPrefetcher this$0, Subscriber subscriber) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        subscriber.onNext(BrowseFactory.createUiPage(this$0.fragment.getContext()).get(BrowseFactory.createPageRequest(this$0.fragment.getContext(), this$0.STORIES_URI, this$0.nextToken, false), Boolean.FALSE, null));
        subscriber.onCompleted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchFirstNextToken$lambda-2, reason: not valid java name */
    public static final void m1651fetchFirstNextToken$lambda2(VideoStoryPrefetcher this$0, PageResponse pageResponse) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setNewNextToken(pageResponse);
    }

    private final Observable<List<String>> fetchNewAsins() {
        Observable<List<String>> observableCreate = Observable.create(new Observable.OnSubscribe() { // from class: com.amazon.mp3.util.VideoStoryPrefetcher$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) throws BrowseException {
                VideoStoryPrefetcher.m1652fetchNewAsins$lambda3(this.f$0, (Subscriber) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableCreate, "create { subscriber ->\n …r.onCompleted()\n        }");
        return observableCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fetchNewAsins$lambda-3, reason: not valid java name */
    public static final void m1652fetchNewAsins$lambda3(VideoStoryPrefetcher this$0, Subscriber subscriber) throws BrowseException {
        Content content;
        List<Block> blocks;
        Block block;
        Content content2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        UiPage uiPageCreateUiPage = BrowseFactory.createUiPage(this$0.fragment.getContext());
        ArrayList arrayList = new ArrayList();
        List<Entity> entities = null;
        PageResponse pageResponse = uiPageCreateUiPage.get(BrowseFactory.createPageRequest(this$0.fragment.getContext(), this$0.STORIES_URI, this$0.nextToken, false), Boolean.FALSE, null);
        this$0.setNewNextToken(pageResponse);
        if (pageResponse != null && (content = pageResponse.getContent()) != null && (blocks = content.getBlocks()) != null && (block = (Block) CollectionsKt___CollectionsKt.getOrNull(blocks, 0)) != null && (content2 = block.getContent()) != null) {
            entities = content2.getEntities();
        }
        if (entities != null) {
            for (Entity entity : entities) {
                if (entity != null) {
                    String asin = ((VideoStory) entity).getAsin();
                    Intrinsics.checkNotNullExpressionValue(asin, "asin");
                    arrayList.add(asin);
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type com.amazon.musicensembleservice.brush.VideoStory");
                }
            }
        }
        subscriber.onNext(arrayList);
        subscriber.onCompleted();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void getMoreVideoStories(List<String> asinsList) {
        this.fragment.setInitialMethodsAndRun(getSerializedMethods(asinsList));
    }

    private final void setNewNextToken(PageResponse response) {
        Content content;
        List<Block> blocks;
        Block block;
        Content content2;
        String nextToken = null;
        if (response != null && (content = response.getContent()) != null && (blocks = content.getBlocks()) != null && (block = (Block) CollectionsKt___CollectionsKt.getOrNull(blocks, 0)) != null && (content2 = block.getContent()) != null) {
            nextToken = content2.getNextToken();
        }
        this.nextToken = nextToken;
    }

    public final List<?> getSerializedMethods(List<String> storiesMetadata) {
        List<?> list;
        Object value;
        Intrinsics.checkNotNullParameter(storiesMetadata, "storiesMetadata");
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format(this.SHOW_VIDEO_STORY_KEY, Arrays.copyOf(new Object[0], 0));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        String str2 = BrowseFactory.isTabletDevice() ? "tablet" : HintConstants.AUTOFILL_HINT_PHONE;
        HashMap map = new HashMap();
        map.put(this.IDS_KEY, getStoryAsins(storiesMetadata));
        map.put(this.DEVICE_TYPE_KEY, str2);
        ArrayList arrayList = new ArrayList();
        ImmutableDisplayGenericErrorMethod immutableDisplayGenericErrorMethodBuild = ImmutableDisplayGenericErrorMethod.builder().queue(SkyFireUtils.stHttpQueue()).build();
        Intrinsics.checkNotNullExpressionValue(immutableDisplayGenericErrorMethodBuild, "builder()\n              …\n                .build()");
        arrayList.add(immutableDisplayGenericErrorMethodBuild);
        ArrayList arrayList2 = new ArrayList();
        Method invokeHttpSkillMethod = SkyFireUtils.getInvokeHttpSkillMethod(str, map, arrayList);
        Intrinsics.checkNotNullExpressionValue(invokeHttpSkillMethod, "getInvokeHttpSkillMethod…th, queryParams, onError)");
        arrayList2.add(invokeHttpSkillMethod);
        try {
            value = Mappers.mapper().readValue(SkyFireUtils.getSerializedMethods(arrayList2), Mappers.mapper().getTypeFactory().constructCollectionType(List.class, Method.class));
        } catch (Exception e) {
            e.printStackTrace();
            list = null;
        }
        if (value == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<*>");
        }
        list = (List) value;
        if (list != null) {
            return list;
        }
        Intrinsics.throwUninitializedPropertyAccessException("initialMethods");
        return null;
    }

    private final String getStoryAsins(List<String> metadataList) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(metadataList);
        String strJoin = TextUtils.join(this.SEPARATOR, arrayList);
        Intrinsics.checkNotNullExpressionValue(strJoin, "join(SEPARATOR, storiesAsins)");
        return strJoin;
    }
}
