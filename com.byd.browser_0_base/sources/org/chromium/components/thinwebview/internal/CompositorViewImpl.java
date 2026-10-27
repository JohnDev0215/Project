package org.chromium.components.thinwebview.internal;

import J.N;
import android.content.Context;
import android.view.TextureView;
import defpackage.t83;
import defpackage.yw;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes3.dex */
public class CompositorViewImpl {
    public final TextureView a;
    public long b;

    public CompositorViewImpl(Context context, WindowAndroid windowAndroid, t83 t83Var) {
        TextureView textureView = new TextureView(context);
        textureView.setSurfaceTextureListener(new yw(this));
        this.a = textureView;
        this.b = N.MPS$crjv(this, windowAndroid, t83Var.e);
    }

    @CalledByNative
    public final long getNativePtr() {
        return this.b;
    }

    @CalledByNative
    public final void onCompositorLayout() {
    }

    @CalledByNative
    public final void recreateSurface() {
    }
}
