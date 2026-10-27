package com.facebook.drawee.view;

import android.net.Uri;
import androidx.annotation.DrawableRes;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.imagepipeline.request.ImageRequest;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SimpleDraweeView extends GenericDraweeView {
    private static Supplier<? extends AbstractDraweeControllerBuilder> sDraweecontrollerbuildersupplier;
    private AbstractDraweeControllerBuilder mControllerBuilder;

    public static void initialize(Supplier<? extends AbstractDraweeControllerBuilder> draweeControllerBuilderSupplier) {
        sDraweecontrollerbuildersupplier = draweeControllerBuilderSupplier;
    }

    protected AbstractDraweeControllerBuilder getControllerBuilder() {
        return this.mControllerBuilder;
    }

    public void setImageRequest(ImageRequest request) {
        setController(this.mControllerBuilder.setImageRequest(request).setOldController(getController()).build());
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageURI(Uri uri) {
        setImageURI(uri, (Object) null);
    }

    public void setImageURI(@Nullable String uriString) {
        setImageURI(uriString, (Object) null);
    }

    public void setImageURI(Uri uri, @Nullable Object callerContext) {
        setController(this.mControllerBuilder.setCallerContext(callerContext).setUri(uri).setOldController(getController()).build());
    }

    public void setImageURI(@Nullable String uriString, @Nullable Object callerContext) {
        setImageURI(uriString != null ? Uri.parse(uriString) : null, callerContext);
    }

    public void setActualImageResource(@DrawableRes int resourceId) {
        setActualImageResource(resourceId, null);
    }

    public void setActualImageResource(@DrawableRes int resourceId, @Nullable Object callerContext) {
        setImageURI(UriUtil.getUriForResourceId(resourceId), callerContext);
    }

    @Override // com.facebook.drawee.view.DraweeView, android.widget.ImageView
    public void setImageResource(int resId) {
        super.setImageResource(resId);
    }
}
