package com.amazon.music.share.menu;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ShareLoaderTarget.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001BI\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0014j\u0002`\u0015\u0012\u001a\u0010\u001c\u001a\u0016\u0012\b\u0012\u00060\bj\u0002`\t\u0012\u0004\u0012\u00020\u00060\u001aj\u0002`\u001b¢\u0006\u0004\b \u0010!J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u001e\u0010\r\u001a\u00020\u00062\n\u0010\n\u001a\u00060\bj\u0002`\t2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016J\u0012\u0010\u000e\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u000bH\u0016R\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R-\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0014j\u0002`\u00158\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R+\u0010\u001c\u001a\u0016\u0012\b\u0012\u00060\bj\u0002`\t\u0012\u0004\u0012\u00020\u00060\u001aj\u0002`\u001b8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"Lcom/amazon/music/share/menu/ShareLoaderTarget;", "Lcom/squareup/picasso/Target;", "Landroid/graphics/Bitmap;", "bitmap", "Lcom/squareup/picasso/Picasso$LoadedFrom;", "loadedFrom", "", "onBitmapLoaded", "Ljava/lang/Exception;", "Lkotlin/Exception;", "e", "Landroid/graphics/drawable/Drawable;", "drawable", "onBitmapFailed", "onPrepareLoad", "Lcom/amazon/music/share/menu/ShareableContent;", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "getContent", "()Lcom/amazon/music/share/menu/ShareableContent;", "Lkotlin/Function2;", "Lcom/amazon/music/share/menu/ShareLoaderSuccessCallback;", "successCallback", "Lkotlin/jvm/functions/Function2;", "getSuccessCallback", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/Function1;", "Lcom/amazon/music/share/menu/ShareLoaderErrorCallback;", "errorCallback", "Lkotlin/jvm/functions/Function1;", "getErrorCallback", "()Lkotlin/jvm/functions/Function1;", "<init>", "(Lcom/amazon/music/share/menu/ShareableContent;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;)V", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class ShareLoaderTarget implements Target {
    private final ShareableContent content;
    private final Function1<Exception, Unit> errorCallback;
    private final Function2<ShareableContent, Bitmap, Unit> successCallback;

    @Override // com.squareup.picasso.Target
    public void onPrepareLoad(Drawable drawable) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ShareLoaderTarget(ShareableContent content, Function2<? super ShareableContent, ? super Bitmap, Unit> successCallback, Function1<? super Exception, Unit> errorCallback) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(successCallback, "successCallback");
        Intrinsics.checkNotNullParameter(errorCallback, "errorCallback");
        this.content = content;
        this.successCallback = successCallback;
        this.errorCallback = errorCallback;
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Intrinsics.checkNotNullParameter(loadedFrom, "loadedFrom");
        this.successCallback.mo4invoke(this.content, bitmap);
    }

    @Override // com.squareup.picasso.Target
    public void onBitmapFailed(Exception e, Drawable drawable) {
        Intrinsics.checkNotNullParameter(e, "e");
        this.errorCallback.invoke(e);
    }
}
