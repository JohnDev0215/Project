package com.amazon.mp3.view.lyrics.sharing.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.fasterxml.jackson.core.JsonPointer;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LyricsSharingAssetManager.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000E\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007*\u0001\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u001e\u0010\u001fJ\u000f\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\b\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002J\u0006\u0010\f\u001a\u00020\u0007J\u0006\u0010\r\u001a\u00020\u0007R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u00058\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR \u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00050\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R \u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00140\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010\u0013R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u001c\u0010\u001d\u001a\n \u001a*\u0004\u0018\u00010\u00190\u00198BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006 "}, d2 = {"Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingAssetManager;", "", "com/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingAssetManager$getTarget$1", "getTarget", "()Lcom/amazon/mp3/view/lyrics/sharing/utils/LyricsSharingAssetManager$getTarget$1;", "", "imageUrl", "", "downloadImage", "", "isImageDirectoryPresent", "getFileNameFromUrl", "downloadAndCacheLyricsSharingBackgroundAssets", "destroy", "TAG", "Ljava/lang/String;", "", "Lcom/squareup/picasso/Target;", "targets", "Ljava/util/Map;", "Lkotlinx/coroutines/Job;", "jobs", "", "imageUrls", "Ljava/util/List;", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "getContext", "()Landroid/content/Context;", "context", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsSharingAssetManager {
    public static final LyricsSharingAssetManager INSTANCE = new LyricsSharingAssetManager();
    private static final String TAG = Reflection.getOrCreateKotlinClass(LyricsSharingAssetManager.class).getSimpleName();
    private static final Map<Target, String> targets = new LinkedHashMap();
    private static final Map<Target, Job> jobs = new LinkedHashMap();
    private static final List<String> imageUrls = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"https://m.media-amazon.com/images/G/01/VIX/Ripple_Cyan.jpg", "https://m.media-amazon.com/images/G/01/VIX/Ripple_Orange.jpg", "https://m.media-amazon.com/images/G/01/VIX/Ripple_Purple.jpg", "https://m.media-amazon.com/images/G/01/VIX/Ripple_Red.jpg", "https://m.media-amazon.com/images/G/01/VIX/Ripple_Violet.jpg", "https://m.media-amazon.com/images/G/01/VIX/Ripple_Yellow.jpg"});

    private LyricsSharingAssetManager() {
    }

    private final Context getContext() {
        return AmazonApplication.getApplication().getApplicationContext();
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingAssetManager$getTarget$1] */
    private final AnonymousClass1 getTarget() {
        return new Target() { // from class: com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingAssetManager.getTarget.1
            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                LyricsSharingAssetManager.jobs.put(this, BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new LyricsSharingAssetManager$getTarget$1$onBitmapLoaded$1(this, bitmap, null), 2, null));
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e(LyricsSharingAssetManager.TAG, Intrinsics.stringPlus("Failed loading lyrics share card background , ", e));
            }
        };
    }

    public final void downloadAndCacheLyricsSharingBackgroundAssets() {
        if (isImageDirectoryPresent()) {
            return;
        }
        Iterator<String> it = imageUrls.iterator();
        while (it.hasNext()) {
            downloadImage(it.next());
        }
    }

    private final void downloadImage(String imageUrl) {
        AnonymousClass1 target = getTarget();
        targets.put(target, imageUrl);
        Picasso.get().load(imageUrl).placeholder(R.drawable.placeholder).into(target);
    }

    private final boolean isImageDirectoryPresent() {
        File file = new File(getContext().getFilesDir(), "lyrics_share");
        return file.exists() && file.isDirectory();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getFileNameFromUrl(String imageUrl) {
        String strSubstring = imageUrl.substring(StringsKt__StringsKt.lastIndexOf$default((CharSequence) imageUrl, JsonPointer.SEPARATOR, 0, false, 6, (Object) null) + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        return strSubstring;
    }

    public final void destroy() {
        Iterator<Map.Entry<Target, String>> it = targets.entrySet().iterator();
        while (it.hasNext()) {
            Picasso.get().cancelRequest(it.next().getKey());
        }
        Iterator<Map.Entry<Target, Job>> it2 = jobs.entrySet().iterator();
        while (it2.hasNext()) {
            Job.DefaultImpls.cancel$default(it2.next().getValue(), (CancellationException) null, 1, (Object) null);
        }
        targets.clear();
        jobs.clear();
    }
}
