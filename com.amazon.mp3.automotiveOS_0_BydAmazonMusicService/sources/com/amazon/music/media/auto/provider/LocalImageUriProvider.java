package com.amazon.music.media.auto.provider;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.amazon.music.media.auto.util.AutoLog;
import com.amazon.music.msautils.MsaUrl;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.google.firebase.perf.network.FirebasePerfUrlConnection;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: compiled from: LocalImageUriProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 +2\u00020\u0001:\u0001+B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0010H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0018\u0010 \u001a\u00020\u001d2\u0006\u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\u000fH\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u00102\b\u0010\u001a\u001a\u0004\u0018\u00010\u0010J#\u0010$\u001a\u0004\u0018\u00010\u00102\u0006\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001a\u001a\u00020\u0010H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010%J\u0006\u0010&\u001a\u00020'J\u0011\u0010(\u001a\u00020)H\u0082@ø\u0001\u0000¢\u0006\u0002\u0010*R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00160\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006,"}, d2 = {"Lcom/amazon/music/media/auto/provider/LocalImageUriProvider;", "", "context", "Landroid/content/Context;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "(Landroid/content/Context;Lkotlinx/coroutines/CoroutineScope;)V", "cacheDir", "Ljava/io/File;", "getCacheDir", "()Ljava/io/File;", "cacheDir$delegate", "Lkotlin/Lazy;", "cachedImages", "", "", "Landroid/net/Uri;", "getCoroutineScope", "()Lkotlinx/coroutines/CoroutineScope;", "defaultImageUriProvider", "Lcom/amazon/music/media/auto/provider/DefaultImageUriProvider;", "downloadJobs", "Lkotlinx/coroutines/Job;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "createLocalImageUri", "remoteImageUrl", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downloadUsingMSA", "", "fileOutputStream", "Ljava/io/FileOutputStream;", "downloadUsingPicasso", "getFileForKey", "key", "getLocalImageUri", "loadImage", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "numberOfPendingJobs", "", "trimAndIndexStorage", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LocalImageUriProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LocalImageUriProvider.class.getSimpleName();

    /* JADX INFO: renamed from: cacheDir$delegate, reason: from kotlin metadata */
    private final Lazy cacheDir;
    private final Map<String, Uri> cachedImages;
    private final Context context;
    private final CoroutineScope coroutineScope;
    private final DefaultImageUriProvider defaultImageUriProvider;
    private final Map<String, Job> downloadJobs;
    private final ReentrantReadWriteLock lock;

    /* JADX INFO: renamed from: com.amazon.music.media.auto.provider.LocalImageUriProvider$loadImage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LocalImageUriProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.media.auto.provider.LocalImageUriProvider", f = "LocalImageUriProvider.kt", i = {0, 0}, l = {236}, m = "loadImage", n = {"this", "key"}, s = {"L$0", "L$1"})
    static final class C04321 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C04321(Continuation<? super C04321> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocalImageUriProvider.this.loadImage(null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.provider.LocalImageUriProvider$trimAndIndexStorage$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: LocalImageUriProvider.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.music.media.auto.provider.LocalImageUriProvider", f = "LocalImageUriProvider.kt", i = {0, 0}, l = {104}, m = "trimAndIndexStorage", n = {"this", "start$iv"}, s = {"L$0", "J$0"})
    static final class C04331 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C04331(Continuation<? super C04331> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return LocalImageUriProvider.this.trimAndIndexStorage(this);
        }
    }

    public LocalImageUriProvider(Context context, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(coroutineScope, "coroutineScope");
        this.context = context;
        this.coroutineScope = coroutineScope;
        this.cacheDir = LazyKt__LazyJVMKt.lazy(new Function0<File>() { // from class: com.amazon.music.media.auto.provider.LocalImageUriProvider$cacheDir$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                File file = new File(this.this$0.context.getCacheDir(), "img_cache");
                file.mkdirs();
                return file;
            }
        });
        this.defaultImageUriProvider = new DefaultImageUriProvider(context);
        this.cachedImages = new LinkedHashMap();
        this.downloadJobs = new LinkedHashMap();
        this.lock = new ReentrantReadWriteLock();
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new AnonymousClass1(null), 3, null);
    }

    public final CoroutineScope getCoroutineScope() {
        return this.coroutineScope;
    }

    /* JADX INFO: compiled from: LocalImageUriProvider.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/media/auto/provider/LocalImageUriProvider$Companion;", "", "()V", "CACHE_TRIM_MAX_FILES", "", "IMAGE_SIZE", "IMG_CACHE_FOLDER", "", "JPEG_QUALITY", "MAX_DOWNLOAD_THREADS", "TAG", "kotlin.jvm.PlatformType", "calculateKey", "uri", "Landroid/net/Uri;", "DMMAutoMediaBrowser_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String calculateKey(Uri uri) {
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment != null) {
                uri = lastPathSegment;
            }
            return String.valueOf(Math.abs(uri.hashCode()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getCacheDir() {
        return (File) this.cacheDir.getValue();
    }

    /* JADX INFO: renamed from: com.amazon.music.media.auto.provider.LocalImageUriProvider$1, reason: invalid class name */
    /* JADX INFO: compiled from: LocalImageUriProvider.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.media.auto.provider.LocalImageUriProvider$1", f = "LocalImageUriProvider.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LocalImageUriProvider.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                LocalImageUriProvider localImageUriProvider = LocalImageUriProvider.this;
                this.label = 1;
                if (localImageUriProvider.trimAndIndexStorage(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    public final int numberOfPendingJobs() {
        ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
        lock.lock();
        try {
            return this.downloadJobs.size();
        } finally {
            lock.unlock();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00d9 A[LOOP:1: B:37:0x00d7->B:38:0x00d9, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object trimAndIndexStorage(kotlin.coroutines.Continuation<? super kotlin.Unit> r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 227
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.provider.LocalImageUriProvider.trimAndIndexStorage(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File getFileForKey(String key) {
        return new File(getCacheDir(), Intrinsics.stringPlus(new Regex("\\W").replace(key, Attributes.PREDEFINED_ATTRIBUTE_PREFIX), ".jpg"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean downloadUsingMSA(Uri remoteImageUrl, FileOutputStream fileOutputStream) {
        if (!MsaUrl.isMsaUrl(remoteImageUrl.toString())) {
            return false;
        }
        try {
            URL url = new URL(MsaUrl.with(remoteImageUrl.toString()).scaleToLongest(640).qualityPercent(75).toUrl());
            AutoLog autoLog = AutoLog.INSTANCE;
            String TAG2 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
            autoLog.debug(TAG2, "Downloading image from MSA, Url = '" + url + '\'');
            InputStream inputStreamOpenStream = FirebasePerfUrlConnection.openStream(url);
            try {
                ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(inputStreamOpenStream);
                try {
                    fileOutputStream.getChannel().transferFrom(readableByteChannelNewChannel, 0L, Long.MAX_VALUE);
                    CloseableKt.closeFinally(readableByteChannelNewChannel, null);
                    CloseableKt.closeFinally(inputStreamOpenStream, null);
                    return true;
                } finally {
                }
            } finally {
            }
        } catch (Exception e) {
            AutoLog autoLog2 = AutoLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.error(TAG3, "Failed to download image from MSA, Url = '" + remoteImageUrl + '\'', e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean downloadUsingPicasso(Uri remoteImageUrl, FileOutputStream fileOutputStream) {
        AutoLog autoLog = AutoLog.INSTANCE;
        String TAG2 = TAG;
        Intrinsics.checkNotNullExpressionValue(TAG2, "TAG");
        autoLog.debug(TAG2, "Downloading image using Picasso, Url = '" + remoteImageUrl + '\'');
        try {
            Picasso.get().load(remoteImageUrl).resize(640, 640).onlyScaleDown().centerInside().get().compress(Bitmap.CompressFormat.JPEG, 75, fileOutputStream);
            return true;
        } catch (Exception e) {
            AutoLog autoLog2 = AutoLog.INSTANCE;
            String TAG3 = TAG;
            Intrinsics.checkNotNullExpressionValue(TAG3, "TAG");
            autoLog2.error(TAG3, "Failed to download image using Picasso, Url = '" + remoteImageUrl + '\'', e);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object loadImage(java.lang.String r13, android.net.Uri r14, kotlin.coroutines.Continuation<? super android.net.Uri> r15) {
        /*
            Method dump skipped, instruction units count: 244
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.auto.provider.LocalImageUriProvider.loadImage(java.lang.String, android.net.Uri, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final Uri getLocalImageUri(Uri remoteImageUrl) {
        if (remoteImageUrl != null) {
            String strCalculateKey = INSTANCE.calculateKey(remoteImageUrl);
            ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
            lock.lock();
            try {
                return this.cachedImages.get(strCalculateKey);
            } finally {
                lock.unlock();
            }
        }
        return this.defaultImageUriProvider.getDefaultMediaIconLocalUri();
    }

    public final Object createLocalImageUri(Uri uri, Continuation<? super Uri> continuation) {
        if (uri != null) {
            String strCalculateKey = INSTANCE.calculateKey(uri);
            ReentrantReadWriteLock.ReadLock lock = this.lock.readLock();
            lock.lock();
            try {
                Uri uri2 = this.cachedImages.get(strCalculateKey);
                return uri2 == null ? loadImage(strCalculateKey, uri, continuation) : uri2;
            } finally {
                lock.unlock();
            }
        }
        return this.defaultImageUriProvider.getDefaultMediaIconLocalUri();
    }
}
