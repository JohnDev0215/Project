package com.amazon.mp3.library.data.impl;

import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.VisibleForTesting;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.library.cache.image.CacheManager;
import com.amazon.mp3.library.data.ContributorManager;
import com.amazon.mp3.library.data.FireFlyServiceUtil;
import com.amazon.mp3.library.data.impl.ContributorAccessObject;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabase;
import com.amazon.mp3.service.ClearCacheService;
import com.amazon.music.platform.graphql.generated.ArtistsImagesQuery;
import com.amazon.music.platform.graphql.generated.fragment.Image;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt__MathJVMKt;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: ContributorManagerImpl.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b&\u0010'J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004H\u0002J\u001e\u0010\u000e\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\r\u001a\u00020\fH\u0002J0\u0010\u0016\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0010H\u0002J\u0016\u0010\u0018\u001a\u00020\u00072\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00020\u0004H\u0002J\u001a\u0010\u001b\u001a\u00020\u00072\u0010\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u0019H\u0016JR\u0010#\u001a\u00020\u00072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\n0\u00042\u0010\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\f\u0018\u00010\u00042!\u0010 \u001a\u001d\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u001e\u0012\b\b\u001f\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00070\u001dH\u0001¢\u0006\u0004\b!\u0010\"R\u0014\u0010$\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006("}, d2 = {"Lcom/amazon/mp3/library/data/impl/ContributorManagerImpl;", "Lcom/amazon/mp3/library/data/ContributorManager;", "Lcom/amazon/mp3/library/data/impl/ContributorAccessObject$Contributor;", "contributor", "", "Lcom/amazon/music/platform/graphql/generated/ArtistsImagesQuery$Image;", "artistImages", "", "setContributorImages", "", "", "unMatchedAsins", "Lcom/amazon/mp3/library/data/impl/ContributorAccessObject$ContributorData;", "contributorData", "updateCallback", "imageUrl", "", "height", "width", "", "isHero", "requestedSize", "generateResizedImageUrl", "contributorList", "updateArtistAsins", "", "contributorDataList", "update", "artistAsins", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "callback", "refreshContributorInfo$DigitalMusicAndroid3P_automotiveProdRelease", "(Ljava/util/List;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "refreshContributorInfo", "LARGEST_IMAGE_SIZE", "I", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ContributorManagerImpl extends ContributorManager {
    public static final ContributorManagerImpl INSTANCE = new ContributorManagerImpl();
    private static final int LARGEST_IMAGE_SIZE = CacheManager.GRID_VIEW_IMAGE_SIZE;

    private ContributorManagerImpl() {
    }

    @Override // com.amazon.mp3.library.data.ContributorManager
    public void update(List<ContributorAccessObject.ContributorData> contributorDataList) {
        HashSet<ContributorAccessObject.Contributor> allContributors;
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        if (contributorDataList != null) {
            for (ContributorAccessObject.ContributorData contributorData : contributorDataList) {
                if (contributorData != null && (allContributors = contributorData.getAllContributors()) != null) {
                    for (ContributorAccessObject.Contributor contributor : allContributors) {
                        if (ClearCacheService.isRunning() || Thread.interrupted()) {
                            return;
                        }
                        if (contributor != null) {
                            String asin = contributor.getAsin();
                            Intrinsics.checkNotNullExpressionValue(asin, "it.asin");
                            linkedHashSet.add(asin);
                            String artistAsin = contributor.getArtistAsin();
                            Intrinsics.checkNotNullExpressionValue(artistAsin, "it.artistAsin");
                            linkedHashSet2.add(artistAsin);
                            arrayList.add(contributor);
                        }
                    }
                }
            }
        }
        updateArtistAsins(arrayList);
        refreshContributorInfo$DigitalMusicAndroid3P_automotiveProdRelease(new ArrayList<>(linkedHashSet2), contributorDataList, new Function1<ContributorAccessObject.ContributorData, Unit>() { // from class: com.amazon.mp3.library.data.impl.ContributorManagerImpl.update.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContributorAccessObject.ContributorData contributorData2) {
                invoke2(contributorData2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContributorAccessObject.ContributorData data) {
                Intrinsics.checkNotNullParameter(data, "data");
                ContributorManagerImpl.INSTANCE.updateCallback(linkedHashSet, data);
            }
        });
    }

    @VisibleForTesting
    public final void refreshContributorInfo$DigitalMusicAndroid3P_automotiveProdRelease(List<String> artistAsins, final List<? extends ContributorAccessObject.ContributorData> contributorDataList, final Function1<? super ContributorAccessObject.ContributorData, Unit> callback) {
        Intrinsics.checkNotNullParameter(artistAsins, "artistAsins");
        Intrinsics.checkNotNullParameter(callback, "callback");
        if (!artistAsins.isEmpty()) {
            FireFlyServiceUtil.INSTANCE.fetchArtistsImages(artistAsins).subscribe(new SingleObserver<HashMap<String, List<? extends ArtistsImagesQuery.Image>>>() { // from class: com.amazon.mp3.library.data.impl.ContributorManagerImpl$refreshContributorInfo$1
                @Override // io.reactivex.rxjava3.core.SingleObserver
                public void onError(Throwable e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                }

                @Override // io.reactivex.rxjava3.core.SingleObserver
                public void onSubscribe(Disposable d) {
                    Intrinsics.checkNotNullParameter(d, "d");
                }

                @Override // io.reactivex.rxjava3.core.SingleObserver
                public /* bridge */ /* synthetic */ void onSuccess(HashMap<String, List<? extends ArtistsImagesQuery.Image>> map) {
                    onSuccess2((HashMap<String, List<ArtistsImagesQuery.Image>>) map);
                }

                /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
                public void onSuccess2(HashMap<String, List<ArtistsImagesQuery.Image>> artistsMap) {
                    HashSet<ContributorAccessObject.Contributor> allContributors;
                    Intrinsics.checkNotNullParameter(artistsMap, "artistsMap");
                    List<ContributorAccessObject.ContributorData> list = contributorDataList;
                    if (list == null) {
                        return;
                    }
                    Function1<ContributorAccessObject.ContributorData, Unit> function1 = callback;
                    for (ContributorAccessObject.ContributorData contributorData : list) {
                        if (contributorData != null && (allContributors = contributorData.getAllContributors()) != null) {
                            for (ContributorAccessObject.Contributor contributor : allContributors) {
                                List<ArtistsImagesQuery.Image> list2 = artistsMap.get(contributor == null ? null : contributor.getArtistAsin());
                                if (list2 != null) {
                                    ContributorManagerImpl contributorManagerImpl = ContributorManagerImpl.INSTANCE;
                                    Intrinsics.checkNotNullExpressionValue(contributor, "contributor");
                                    contributorManagerImpl.setContributorImages(contributor, list2);
                                }
                                function1.invoke(contributorData);
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setContributorImages(ContributorAccessObject.Contributor contributor, List<ArtistsImagesQuery.Image> artistImages) {
        for (ArtistsImagesQuery.Image image : artistImages) {
            if (image != null) {
                Image image2 = image.getImage();
                Double height = image2.getHeight();
                int iRoundToInt = height == null ? 0 : MathKt__MathJVMKt.roundToInt(height.doubleValue());
                Double width = image2.getWidth();
                int iRoundToInt2 = width == null ? 0 : MathKt__MathJVMKt.roundToInt(width.doubleValue());
                String url = image2.getUrl();
                String imageType = image2.getImageType();
                if (url != null) {
                    if (StringsKt__StringsJVMKt.equals$default(imageType, "PROFILE", false, 2, null) && iRoundToInt > contributor.getThumbnailImageMaxHeight()) {
                        contributor.setThumbnailImageURL(INSTANCE.generateResizedImageUrl(url, iRoundToInt, iRoundToInt2, false, LARGEST_IMAGE_SIZE));
                        contributor.setThumbnailImageMaxHeight(iRoundToInt);
                        contributor.setThumbnailImageMaxWidth(iRoundToInt2);
                    } else if (StringsKt__StringsJVMKt.equals$default(imageType, "BACKGROUND", false, 2, null) && iRoundToInt > contributor.getHeroImageMaxHeight()) {
                        contributor.setHeroImageURL(INSTANCE.generateResizedImageUrl(url, iRoundToInt, iRoundToInt2, true, LARGEST_IMAGE_SIZE));
                        contributor.setHeroImageMaxHeight(iRoundToInt);
                        contributor.setHeroImageMaxWidth(iRoundToInt2);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateCallback(Set<String> unMatchedAsins, ContributorAccessObject.ContributorData contributorData) {
        SQLiteDatabase writableDatabase = CirrusDatabase.getWritableDatabase(AmazonApplication.getContext());
        writableDatabase.beginTransaction();
        try {
            ContributorAccessObject.insertOrUpdate(contributorData);
            writableDatabase.setTransactionSuccessful();
            writableDatabase.endTransaction();
            Set<String> setKeySet = contributorData.getArtistContributorMap().keySet();
            Intrinsics.checkNotNullExpressionValue(setKeySet, "contributorData.artistContributorMap.keys");
            Iterator<ContributorAccessObject.Contributor> it = contributorData.getArtistContributorMap().values().iterator();
            while (it.hasNext()) {
                unMatchedAsins.remove(it.next().getAsin());
            }
            prefetchImages(unMatchedAsins, setKeySet);
        } catch (Throwable th) {
            writableDatabase.endTransaction();
            throw th;
        }
    }

    private final String generateResizedImageUrl(String imageUrl, int height, int width, boolean isHero, int requestedSize) {
        String regImageServerCommand;
        if (isHero) {
            regImageServerCommand = getHeroImageServerCommand(width, height, requestedSize);
            Intrinsics.checkNotNullExpressionValue(regImageServerCommand, "{\n            getHeroIma… requestedSize)\n        }");
        } else {
            regImageServerCommand = getRegImageServerCommand(width, height, requestedSize);
            Intrinsics.checkNotNullExpressionValue(regImageServerCommand, "{\n            getRegImag… requestedSize)\n        }");
        }
        return StringsKt__StringsJVMKt.replace$default(imageUrl, ".*_", regImageServerCommand, false, 4, (Object) null);
    }

    private final void updateArtistAsins(List<? extends ContributorAccessObject.Contributor> contributorList) {
        Object next;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Object obj : contributorList) {
            String asin = ((ContributorAccessObject.Contributor) obj).getAsin();
            Object arrayList = linkedHashMap.get(asin);
            if (arrayList == null) {
                arrayList = new ArrayList();
                linkedHashMap.put(asin, arrayList);
            }
            ((List) arrayList).add(obj);
        }
        ArrayList arrayList2 = new ArrayList(linkedHashMap.size());
        Iterator it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            arrayList2.add((List) ((Map.Entry) it.next()).getValue());
        }
        ArrayList<List> arrayList3 = new ArrayList();
        Iterator it2 = arrayList2.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (((List) next2).size() > 1) {
                arrayList3.add(next2);
            }
        }
        for (List list : arrayList3) {
            Iterator it3 = list.iterator();
            while (true) {
                if (it3.hasNext()) {
                    next = it3.next();
                    if (((ContributorAccessObject.Contributor) next).getPrimary()) {
                        break;
                    }
                } else {
                    next = null;
                    break;
                }
            }
            ContributorAccessObject.Contributor contributor = (ContributorAccessObject.Contributor) next;
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : list) {
                if (!((ContributorAccessObject.Contributor) obj2).getPrimary()) {
                    arrayList4.add(obj2);
                }
            }
            if (contributor != null) {
                Iterator it4 = arrayList4.iterator();
                while (it4.hasNext()) {
                    ((ContributorAccessObject.Contributor) it4.next()).setArtistAsin(contributor.getArtistAsin());
                }
            }
        }
    }
}
