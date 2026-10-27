package com.amazon.music.identity.profiles.view.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.views.library.binders.ImageBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import com.google.android.gms.common.internal.ImagesContract;
import com.squareup.picasso.Picasso;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.Regex;

/* JADX INFO: compiled from: ViewUtil.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ*\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ8\u0010\u000f\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0010\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/identity/profiles/view/util/ViewUtil;", "", "()V", "setImageViewByResId", "", "imageView", "Landroid/widget/ImageView;", "resIdString", "", "setImageViewByUrl", "context", "Landroid/content/Context;", ImagesContract.URL, "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "setImageViewRound", "location", "bitmap", "Landroid/graphics/Bitmap;", "MusicIdentityAndroid_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ViewUtil {
    public static final ViewUtil INSTANCE = new ViewUtil();

    private ViewUtil() {
    }

    public final void setImageViewRound(Context context, ImageView imageView, String location, Bitmap bitmap, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        if (bitmap != null) {
            RoundedBitmapDrawable roundedBitmapDrawableCreate = RoundedBitmapDrawableFactory.create(imageView.getResources(), bitmap);
            Intrinsics.checkNotNullExpressionValue(roundedBitmapDrawableCreate, "create(imageView.resources, bitmap)");
            roundedBitmapDrawableCreate.setCornerRadius(RangesKt___RangesKt.coerceAtLeast(bitmap.getWidth(), bitmap.getHeight()));
            imageView.setImageDrawable(roundedBitmapDrawableCreate);
            return;
        }
        setImageViewByUrl(context, imageView, location, styleSheet);
    }

    public final void setImageViewByUrl(Context context, ImageView imageView, String url, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        PicassoImageLoader picassoImageLoader = PicassoImageLoader.builder(context, Picasso.get()).withTag(context).build();
        Intrinsics.checkNotNullExpressionValue(picassoImageLoader, "picassoImageLoader");
        ImageBinder.bind$default(new ImageBinder(context, styleSheet, picassoImageLoader), imageView, url, 4, 0, 8, null);
    }

    public final void setImageViewByResId(ImageView imageView, String resIdString) {
        List listEmptyList;
        Intrinsics.checkNotNullParameter(imageView, "imageView");
        Intrinsics.checkNotNullParameter(resIdString, "resIdString");
        Context context = imageView.getContext();
        List<String> listSplit = new Regex("\\.").split(resIdString, 0);
        if (listSplit.isEmpty()) {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        } else {
            ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
            while (listIterator.hasPrevious()) {
                if (!(listIterator.previous().length() == 0)) {
                    listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                    break;
                }
            }
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        Object[] array = listEmptyList.toArray(new String[0]);
        if (array == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        }
        String[] strArr = (String[]) array;
        imageView.setImageResource(context.getResources().getIdentifier(strArr[1], strArr[0], context.getPackageName()));
    }
}
