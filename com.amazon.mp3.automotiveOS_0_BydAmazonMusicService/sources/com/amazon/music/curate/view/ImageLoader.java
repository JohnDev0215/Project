package com.amazon.music.curate.view;

import android.widget.ImageView;
import com.amazon.music.curate.data.PlaylistCollage;
import com.amazon.music.curate.view.ImageLoader;
import com.amazon.music.msautils.MsaUrl;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Transformation;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: ImageLoader.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0002\u0003\u0004B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0005"}, d2 = {"Lcom/amazon/music/curate/view/ImageLoader;", "", "()V", "Builder", "Companion", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ImageLoader {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Logger LOG;
    public static final int MAX_ATTEMPTS = 1;

    /* JADX INFO: compiled from: ImageLoader.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/curate/view/ImageLoader$Companion;", "", "()V", "LOG", "Lorg/slf4j/Logger;", "MAX_ATTEMPTS", "", "loadImage", "Lcom/amazon/music/curate/view/ImageLoader$Builder;", ImagesContract.URL, "", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Builder loadImage(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            return new Builder(url);
        }
    }

    static {
        Logger logger = LoggerFactory.getLogger(ImageLoader.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(ImageLoader::class.java.simpleName)");
        LOG = logger;
    }

    /* JADX INFO: compiled from: ImageLoader.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ \u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0018\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0006H\u0002J\u0016\u0010\u0012\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0013\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\tR\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u0014"}, d2 = {"Lcom/amazon/music/curate/view/ImageLoader$Builder;", "", ImagesContract.URL, "", "(Ljava/lang/String;)V", "height", "", "Ljava/lang/Integer;", "transformation", "Lcom/squareup/picasso/Transformation;", "width", "into", "", "view", "Landroid/widget/ImageView;", "load", "loadIfSized", "attempts", "withSize", "withTransformation", "DMMCurateExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Builder {
        private Integer height;
        private Transformation transformation;
        private final String url;
        private Integer width;

        public Builder(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            this.url = url;
        }

        public final Builder withTransformation(Transformation transformation) {
            Intrinsics.checkNotNullParameter(transformation, "transformation");
            this.transformation = transformation;
            return this;
        }

        public final Builder withSize(int width, int height) {
            this.width = Integer.valueOf(width);
            this.height = Integer.valueOf(height);
            return this;
        }

        public final void into(ImageView view) {
            Intrinsics.checkNotNullParameter(view, "view");
            Integer num = this.width;
            if (num == null || this.height == null) {
                loadIfSized(view, 0);
                return;
            }
            Intrinsics.checkNotNull(num);
            int iIntValue = num.intValue();
            Integer num2 = this.height;
            Intrinsics.checkNotNull(num2);
            load(view, iIntValue, num2.intValue());
        }

        private final void loadIfSized(final ImageView view, final int attempts) {
            view.post(new Runnable() { // from class: com.amazon.music.curate.view.ImageLoader$Builder$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    ImageLoader.Builder.m1942loadIfSized$lambda2(view, this, attempts);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: loadIfSized$lambda-2, reason: not valid java name */
        public static final void m1942loadIfSized$lambda2(ImageView view, Builder this$0, int i) {
            Intrinsics.checkNotNullParameter(view, "$view");
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            if (view.getWidth() <= 0 || view.getHeight() <= 0) {
                Logger logger = ImageLoader.LOG;
                StringBuilder sb = new StringBuilder();
                sb.append("image size is zero on attempt ");
                int i2 = i + 1;
                sb.append(i2);
                sb.append(", not loading url ");
                sb.append(this$0.url);
                logger.trace(sb.toString());
                if (i < 1) {
                    this$0.loadIfSized(view, i2);
                    return;
                } else {
                    this$0.load(view, view.getWidth(), view.getHeight());
                    return;
                }
            }
            this$0.load(view, view.getWidth(), view.getHeight());
        }

        private final void load(ImageView view, int width, int height) {
            String url;
            PlaylistCollage.Companion companion = PlaylistCollage.INSTANCE;
            if (companion.isCollageUrl(this.url)) {
                url = companion.updateSize(this.url, width);
            } else {
                url = (!MsaUrl.isMsaUrl(this.url) || width <= 0) ? this.url : MsaUrl.with(this.url).scaleToWidth(width).toUrl();
            }
            RequestCreator requestCreatorLoad = Picasso.get().load(url);
            Transformation transformation = this.transformation;
            if (transformation != null) {
                requestCreatorLoad.transform(transformation);
            }
            if (width > 0) {
                requestCreatorLoad.resize(width, height);
            } else {
                requestCreatorLoad.fit();
            }
            requestCreatorLoad.into(view);
        }
    }
}
