package com.amazon.music.share.menu;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.net.Uri;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.core.content.FileProvider;
import androidx.core.view.ViewCompat;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.picassoimageloader.ImageBlur;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.GradientStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.GradientStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.visualon.OSMPUtils.voOSType;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: compiled from: StoryAssetsGenerator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 h2\u00020\u0001:\u0001hB\u007f\u0012\u0006\u0010-\u001a\u00020,\u0012\u0006\u0010e\u001a\u00020d\u0012\u0006\u00102\u001a\u000201\u0012\u001c\u00109\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000206j\u0002`8\u00128\u0010A\u001a4\u0012&\u0012$0\rj\u0011`\u000e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000f¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00020=j\u0002`@\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\bf\u0010gJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0018\u0010\b\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0014\u0010\u0010\u001a\u00020\u00022\n\u0010\u000f\u001a\u00060\rj\u0002`\u000eH\u0002J\u0018\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0006H\u0002J0\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u0018H\u0002J\u0010\u0010\u001f\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0018\u0010\"\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\u0018H\u0002J\u0018\u0010&\u001a\u00020\u00062\u0006\u0010#\u001a\u00020\u00062\u0006\u0010%\u001a\u00020$H\u0002J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0002J\u0006\u0010(\u001a\u00020\u0002J\u000e\u0010*\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018J\u0006\u0010+\u001a\u00020\u0002R\u0017\u0010-\u001a\u00020,8\u0006¢\u0006\f\n\u0004\b-\u0010.\u001a\u0004\b/\u00100R\u0017\u00102\u001a\u0002018\u0006¢\u0006\f\n\u0004\b2\u00103\u001a\u0004\b4\u00105R-\u00109\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u00020\u000206j\u0002`88\u0006¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<RI\u0010A\u001a4\u0012&\u0012$0\rj\u0011`\u000e¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000f¢\u0006\f\b>\u0012\b\b?\u0012\u0004\b\b(\u000f\u0012\u0004\u0012\u00020\u00020=j\u0002`@8\u0006¢\u0006\f\n\u0004\bA\u0010B\u001a\u0004\bC\u0010DR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010E\u001a\u0004\bF\u0010GR\u0014\u0010I\u001a\u00020H8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bI\u0010JR\u0014\u0010L\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020K8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010MR\u0014\u0010P\u001a\u00020O8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bP\u0010QR\u0014\u0010R\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bR\u0010SR\u0014\u0010T\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010SR\u0014\u0010V\u001a\u00020U8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bV\u0010WR\u0018\u0010X\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bX\u0010YR\u0018\u0010Z\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010YR\u001e\u0010[\u001a\n\u0018\u00010\rj\u0004\u0018\u0001`\u000e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b[\u0010\\R\u0018\u0010^\u001a\u0004\u0018\u00010]8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0014\u0010a\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010c\u001a\u00020`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010b¨\u0006i"}, d2 = {"Lcom/amazon/music/share/menu/StoryAssetGenerator;", "", "", "updateAssets", "Lcom/amazon/music/share/menu/ShareableContent;", "content", "Landroid/graphics/Bitmap;", "coverBitmap", "stickerReadyCallback", "bitmap", "Landroid/net/Uri;", "writeBitmapToUri", "backgroundReadyCallback", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "fetchFailedCallback", "prepareSticker", "backgroundBitmap", "prepareBackground", "", "text", "Landroid/text/TextPaint;", "paint", "", "width", "Landroid/text/Layout$Alignment;", "alignment", "maxLines", "Landroid/text/StaticLayout;", "createStaticLayout", "writeBitmap", PageUriUtils.SOURCE_QUERY_PARAM, "size", "boxBitmap", "originalBmp", "Landroid/graphics/Rect;", "finalRect", "upscaleImage", "applyGradient", "generateAssets", "dp", "dpToPx", "cancel", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "getEnvironmentProvider", "()Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "Lkotlin/Function2;", "Lcom/amazon/music/share/menu/StoryAssets;", "Lcom/amazon/music/share/menu/AssetsReadyCallback;", "readyCallback", "Lkotlin/jvm/functions/Function2;", "getReadyCallback", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "Lcom/amazon/music/share/menu/AssetsFailedCallback;", "failedCallback", "Lkotlin/jvm/functions/Function1;", "getFailedCallback", "()Lkotlin/jvm/functions/Function1;", "Lcom/amazon/music/share/menu/ShareableContent;", "getContent", "()Lcom/amazon/music/share/menu/ShareableContent;", "", "cornerRadiusSize", "F", "Lcom/amazon/ui/foundations/styles/FontStyle;", "titleFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "subtitleFontStyle", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "gradientStyle", "Lcom/amazon/ui/foundations/styles/GradientStyle;", "gradientTint", "I", "outlineColor", "Ljava/util/concurrent/CountDownLatch;", "latch", "Ljava/util/concurrent/CountDownLatch;", "stickerUri", "Landroid/net/Uri;", "backgroundUri", "error", "Ljava/lang/Exception;", "Lkotlinx/coroutines/Job;", "job", "Lkotlinx/coroutines/Job;", "Lcom/amazon/music/share/menu/ShareLoaderTarget;", "stickerTarget", "Lcom/amazon/music/share/menu/ShareLoaderTarget;", "backgroundTarget", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/share/menu/providers/EnvironmentProvider;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lcom/amazon/music/share/menu/ShareableContent;)V", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class StoryAssetGenerator {
    private final ShareLoaderTarget backgroundTarget;
    private Uri backgroundUri;
    private final ShareableContent content;
    private final Context context;
    private final float cornerRadiusSize;
    private final EnvironmentProvider environmentProvider;
    private Exception error;
    private final Function1<Exception, Unit> failedCallback;
    private final GradientStyle gradientStyle;
    private final int gradientTint;
    private Job job;
    private final CountDownLatch latch;
    private final int outlineColor;
    private final Function2<ShareableContent, StoryAssets, Unit> readyCallback;
    private final ShareLoaderTarget stickerTarget;
    private Uri stickerUri;
    private final FontStyle subtitleFontStyle;
    private final FontStyle titleFontStyle;

    /* JADX WARN: Multi-variable type inference failed */
    public StoryAssetGenerator(Context context, StyleSheet styleSheet, EnvironmentProvider environmentProvider, Function2<? super ShareableContent, ? super StoryAssets, Unit> readyCallback, Function1<? super Exception, Unit> failedCallback, ShareableContent content) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(environmentProvider, "environmentProvider");
        Intrinsics.checkNotNullParameter(readyCallback, "readyCallback");
        Intrinsics.checkNotNullParameter(failedCallback, "failedCallback");
        Intrinsics.checkNotNullParameter(content, "content");
        this.context = context;
        this.environmentProvider = environmentProvider;
        this.readyCallback = readyCallback;
        this.failedCallback = failedCallback;
        this.content = content;
        this.latch = new CountDownLatch(2);
        this.stickerTarget = new ShareLoaderTarget(content, new StoryAssetGenerator$stickerTarget$1(this), new StoryAssetGenerator$stickerTarget$2(this));
        this.backgroundTarget = new ShareLoaderTarget(content, new StoryAssetGenerator$backgroundTarget$1(this), new StoryAssetGenerator$backgroundTarget$2(this));
        Intrinsics.checkNotNull(styleSheet.getCornerSize(CornerSizeKey.MEDIUM));
        this.cornerRadiusSize = r4.intValue() / context.getResources().getDisplayMetrics().density;
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_1);
        Intrinsics.checkNotNull(fontStyle);
        this.titleFontStyle = fontStyle;
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.PRIMARY);
        Intrinsics.checkNotNull(fontStyle2);
        this.subtitleFontStyle = fontStyle2;
        GradientStyle gradientStyle = styleSheet.getGradientStyle(GradientStyleKey.GRADIENT_1);
        Intrinsics.checkNotNull(gradientStyle);
        this.gradientStyle = gradientStyle;
        Integer color = styleSheet.getColor(ColorKey.COLOR_SECONDARY, AlphaKey.GLASS_1);
        Intrinsics.checkNotNull(color);
        this.gradientTint = color.intValue();
        Integer color2 = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_2);
        Intrinsics.checkNotNull(color2);
        this.outlineColor = color2.intValue();
    }

    public final Function1<Exception, Unit> getFailedCallback() {
        return this.failedCallback;
    }

    public final void generateAssets() {
        if (this.content.getImageBitmap() != null && this.content.getBackgroundBitmap() != null) {
            Bitmap imageBitmap = this.content.getImageBitmap();
            if (imageBitmap != null) {
                try {
                    this.stickerUri = writeBitmapToUri(imageBitmap);
                } catch (IOException e) {
                    this.error = e;
                }
            }
            Bitmap backgroundBitmap = this.content.getBackgroundBitmap();
            if (backgroundBitmap != null) {
                try {
                    this.backgroundUri = writeBitmapToUri(backgroundBitmap);
                } catch (IOException e2) {
                    this.error = e2;
                }
            }
        } else if (this.content.getImage() != null) {
            RequestCreator requestCreatorResize = Picasso.get().load(this.content.getImage()).resize(dpToPx(400), dpToPx((int) (400 / this.content.getImageAspectRatio())));
            ImageRoundCornerTransformation imageRoundCornerTransformation = new ImageRoundCornerTransformation();
            imageRoundCornerTransformation.setCornerRadius(Float.valueOf(this.cornerRadiusSize));
            requestCreatorResize.transform(imageRoundCornerTransformation).into(this.stickerTarget);
            Picasso picasso = Picasso.get();
            String backgroundImage = this.content.getBackgroundImage();
            if (backgroundImage == null) {
                backgroundImage = this.content.getImage();
            }
            picasso.load(backgroundImage).into(this.backgroundTarget);
        }
        this.job = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new AnonymousClass4(null), 2, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.StoryAssetGenerator$generateAssets$4, reason: invalid class name */
    /* JADX INFO: compiled from: StoryAssetsGenerator.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.StoryAssetGenerator$generateAssets$4", f = "StoryAssetsGenerator.kt", i = {}, l = {voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return StoryAssetGenerator.this.new AnonymousClass4(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass4) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (!StoryAssetGenerator.this.latch.await(5L, TimeUnit.SECONDS)) {
                        throw new TimeoutException("Timed out waiting for assets to be loaded/generated");
                    }
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    AnonymousClass1 anonymousClass1 = new AnonymousClass1(StoryAssetGenerator.this, null);
                    this.label = 1;
                    if (BuildersKt.withContext(main2, anonymousClass1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (Exception e) {
                StoryAssetGenerator.this.error = e;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.music.share.menu.StoryAssetGenerator$generateAssets$4$1, reason: invalid class name */
        /* JADX INFO: compiled from: StoryAssetsGenerator.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.StoryAssetGenerator$generateAssets$4$1", f = "StoryAssetsGenerator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ StoryAssetGenerator this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass1(StoryAssetGenerator storyAssetGenerator, Continuation<? super AnonymousClass1> continuation) {
                super(2, continuation);
                this.this$0 = storyAssetGenerator;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass1(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.updateAssets();
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAssets() {
        Exception exc = this.error;
        if (exc != null) {
            getFailedCallback().invoke(exc);
            return;
        }
        Uri uri = this.stickerUri;
        Uri uri2 = this.backgroundUri;
        if (uri == null || uri2 == null) {
            this.failedCallback.invoke(new IllegalStateException("Assets not available"));
        } else {
            this.readyCallback.mo4invoke(this.content, new StoryAssets(uri, uri2, "image/png"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void stickerReadyCallback(ShareableContent content, Bitmap coverBitmap) {
        try {
            try {
                this.stickerUri = prepareSticker(content, coverBitmap);
            } catch (IOException e) {
                this.error = e;
            }
        } finally {
            this.latch.countDown();
        }
    }

    private final Uri writeBitmapToUri(Bitmap bitmap) {
        try {
            return writeBitmap(bitmap);
        } finally {
            bitmap.recycle();
            this.latch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void backgroundReadyCallback(ShareableContent content, Bitmap bitmap) {
        try {
            try {
                this.backgroundUri = prepareBackground(bitmap);
            } catch (IOException e) {
                this.error = e;
            }
        } finally {
            this.latch.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fetchFailedCallback(Exception e) {
        this.error = e;
        this.latch.countDown();
    }

    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    private final Uri prepareSticker(ShareableContent content, Bitmap coverBitmap) {
        float width = coverBitmap.getWidth() / coverBitmap.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(dpToPx(480), dpToPx(640), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        TextPaint paint = UIExtensionsKt.toPaint(this.titleFontStyle, this.context);
        TextPaint paint2 = UIExtensionsKt.toPaint(this.subtitleFontStyle, this.context);
        String title = content.getTitle();
        if (title == null) {
            title = "";
        }
        String subtitle = content.getSubtitle();
        String str = subtitle == null ? "" : subtitle;
        Paint paint3 = new Paint();
        paint3.setStyle(Paint.Style.STROKE);
        paint3.setColor(this.outlineColor);
        paint3.setAntiAlias(true);
        Paint paint4 = new Paint();
        paint4.setShadowLayer(this.cornerRadiusSize, 0.0f, 0.0f, ViewCompat.MEASURED_STATE_MASK);
        int iDpToPx = dpToPx(480) - (dpToPx(48) * 2);
        StaticLayout staticLayoutCreateStaticLayout = createStaticLayout(title, paint, iDpToPx, Layout.Alignment.ALIGN_CENTER, 2);
        float f = iDpToPx;
        int i = (int) (f / width);
        int iDpToPx2 = dpToPx(20) + i;
        int iMin = Math.min(2, staticLayoutCreateStaticLayout.getLineCount());
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        int i2 = ((int) ((fontMetrics.bottom - fontMetrics.top) + fontMetrics.leading)) * iMin;
        int iDpToPx3 = iDpToPx2 + i2 + dpToPx(12);
        Paint.FontMetrics fontMetrics2 = paint2.getFontMetrics();
        float f2 = iDpToPx3 + ((int) ((fontMetrics2.bottom - fontMetrics2.top) + fontMetrics2.leading));
        canvas.save();
        canvas.translate(dpToPx(48), (dpToPx(640) - f2) / 2);
        RectF rectF = new RectF(0.0f, 0.0f, f, i);
        float f3 = this.cornerRadiusSize;
        canvas.drawRoundRect(rectF, f3, f3, paint4);
        canvas.drawBitmap(coverBitmap, (Rect) null, rectF, (Paint) null);
        float f4 = this.cornerRadiusSize;
        canvas.drawRoundRect(rectF, f4, f4, paint3);
        canvas.save();
        canvas.translate(0.0f, iDpToPx2);
        canvas.clipRect(new Rect(0, 0, iDpToPx, i2));
        staticLayoutCreateStaticLayout.draw(canvas);
        canvas.restore();
        String string = TextUtils.ellipsize(str, paint2, f, TextUtils.TruncateAt.END).toString();
        canvas.drawText(string, 0, string.length(), (f - paint2.measureText(string)) / 2.0f, f2, (Paint) paint2);
        canvas.restore();
        try {
            Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
            Uri uriWriteBitmap = writeBitmap(bitmap);
            bitmap.recycle();
            return uriWriteBitmap;
        } catch (Throwable th) {
            bitmap.recycle();
            throw th;
        }
    }

    private final Uri prepareBackground(Bitmap backgroundBitmap) {
        Bitmap blurredBitmap = ImageBlur.generateBoxBlurredBitmap(boxBitmap(backgroundBitmap, dpToPx(320)), 5, 4);
        DisplayMetrics displayMetrics = this.context.getResources().getDisplayMetrics();
        Rect rect = new Rect(0, 0, dpToPx(720), (int) (dpToPx(720) * (displayMetrics.heightPixels / displayMetrics.widthPixels)));
        try {
            Intrinsics.checkNotNullExpressionValue(blurredBitmap, "blurredBitmap");
            Bitmap bitmapUpscaleImage = upscaleImage(blurredBitmap, rect);
            blurredBitmap.recycle();
            applyGradient(bitmapUpscaleImage);
            try {
                return writeBitmap(bitmapUpscaleImage);
            } finally {
                bitmapUpscaleImage.recycle();
            }
        } catch (Throwable th) {
            blurredBitmap.recycle();
            throw th;
        }
    }

    private final StaticLayout createStaticLayout(String text, TextPaint paint, int width, Layout.Alignment alignment, int maxLines) {
        StaticLayout staticLayoutBuild = StaticLayout.Builder.obtain(text, 0, text.length(), paint, width).setAlignment(alignment).setMaxLines(maxLines).setEllipsize(TextUtils.TruncateAt.END).build();
        Intrinsics.checkNotNullExpressionValue(staticLayoutBuild, "{\n            StaticLayo…       .build()\n        }");
        return staticLayoutBuild;
    }

    private final Uri writeBitmap(Bitmap bitmap) {
        File file = new File(this.environmentProvider.getStorageRoot(), "ShareStory-" + System.currentTimeMillis() + ".png");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG, 80, fileOutputStream);
            CloseableKt.closeFinally(fileOutputStream, null);
            Uri uriForFile = FileProvider.getUriForFile(this.context, this.environmentProvider.getAuthority(), file);
            Intrinsics.checkNotNullExpressionValue(uriForFile, "getUriForFile(\n         …Authority, file\n        )");
            return uriForFile;
        } finally {
        }
    }

    private final Bitmap boxBitmap(Bitmap source, int size) {
        Bitmap bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        int iMin = Math.min(source.getWidth(), source.getHeight());
        Rect rect = new Rect(0, 0, iMin, iMin);
        rect.offset((source.getWidth() - iMin) / 2, (source.getHeight() - iMin) / 2);
        float f = size;
        canvas.drawBitmap(source, rect, new RectF(0.0f, 0.0f, f, f), (Paint) null);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    private final Bitmap upscaleImage(Bitmap originalBmp, Rect finalRect) {
        int width;
        int height;
        Bitmap bitmap = Bitmap.createBitmap(finalRect.width(), finalRect.height(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        float width2 = bitmap.getWidth() / bitmap.getHeight();
        if (width2 < 1.0f) {
            width = (int) (originalBmp.getWidth() * width2);
        } else {
            width = originalBmp.getWidth();
        }
        if (width2 <= 1.0f) {
            height = originalBmp.getHeight();
        } else {
            height = (int) (originalBmp.getHeight() * width2);
        }
        int height2 = (originalBmp.getHeight() - height) / 2;
        int width3 = (originalBmp.getWidth() - width) / 2;
        canvas.drawBitmap(originalBmp, new Rect(width3, height2, width + width3, height + height2), finalRect, (Paint) null);
        Intrinsics.checkNotNullExpressionValue(bitmap, "bitmap");
        return bitmap;
    }

    private final void applyGradient(Bitmap bitmap) {
        RectF rectF = new RectF(0.0f, 0.0f, bitmap.getWidth(), bitmap.getHeight());
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        float f = rectF.left;
        paint.setShader(new LinearGradient(f, rectF.top, f, rectF.bottom, this.gradientStyle.getColors().get(0).intValue(), this.gradientStyle.getColors().get(1).intValue(), Shader.TileMode.CLAMP));
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(this.gradientTint);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(rectF, paint2);
        canvas.drawRect(rectF, paint);
    }

    public final int dpToPx(int dp) {
        return (int) ((dp * this.context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public final void cancel() {
        Job job = this.job;
        if (job == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }
}
