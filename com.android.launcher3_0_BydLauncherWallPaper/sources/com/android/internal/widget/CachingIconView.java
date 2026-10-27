package com.android.internal.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.RemotableViewMethod;
import android.widget.ImageView;
import android.widget.RemoteViews;
import java.util.Objects;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes4.dex */
@RemoteViews.RemoteView
public class CachingIconView extends ImageView {
    private int mDesiredVisibility;
    private boolean mForceHidden;
    private int mIconColor;
    private boolean mInternalSetDrawable;
    private String mLastPackage;
    private int mLastResId;
    private Consumer<Boolean> mOnForceHiddenChangedListener;
    private Consumer<Integer> mOnVisibilityChangedListener;
    private boolean mWillBeForceHidden;

    public CachingIconView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod(asyncImpl = "setImageIconAsync")
    public void setImageIcon(Icon icon) {
        if (testAndSetCache(icon)) {
            return;
        }
        this.mInternalSetDrawable = true;
        super.setImageIcon(icon);
        this.mInternalSetDrawable = false;
    }

    @Override // android.widget.ImageView
    public Runnable setImageIconAsync(Icon icon) {
        resetCache();
        return super.setImageIconAsync(icon);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod(asyncImpl = "setImageResourceAsync")
    public void setImageResource(int i) {
        if (testAndSetCache(i)) {
            return;
        }
        this.mInternalSetDrawable = true;
        super.setImageResource(i);
        this.mInternalSetDrawable = false;
    }

    @Override // android.widget.ImageView
    public Runnable setImageResourceAsync(int i) {
        resetCache();
        return super.setImageResourceAsync(i);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod(asyncImpl = "setImageURIAsync")
    public void setImageURI(Uri uri) {
        resetCache();
        super.setImageURI(uri);
    }

    @Override // android.widget.ImageView
    public Runnable setImageURIAsync(Uri uri) {
        resetCache();
        return super.setImageURIAsync(uri);
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        if (!this.mInternalSetDrawable) {
            resetCache();
        }
        super.setImageDrawable(drawable);
    }

    @Override // android.widget.ImageView
    @RemotableViewMethod
    public void setImageBitmap(Bitmap bitmap) {
        resetCache();
        super.setImageBitmap(bitmap);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetCache();
    }

    private synchronized boolean testAndSetCache(Icon icon) {
        boolean z = false;
        if (icon != null) {
            if (icon.getType() == 2) {
                String strNormalizeIconPackage = normalizeIconPackage(icon);
                if (this.mLastResId != 0 && icon.getResId() == this.mLastResId && Objects.equals(strNormalizeIconPackage, this.mLastPackage)) {
                    z = true;
                }
                this.mLastPackage = strNormalizeIconPackage;
                this.mLastResId = icon.getResId();
                return z;
            }
        }
        resetCache();
        return false;
    }

    private synchronized boolean testAndSetCache(int i) {
        boolean z;
        z = false;
        if (i != 0) {
            try {
                int i2 = this.mLastResId;
                if (i2 != 0 && i == i2 && this.mLastPackage == null) {
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.mLastPackage = null;
        this.mLastResId = i;
        return z;
    }

    private String normalizeIconPackage(Icon icon) {
        if (icon == null) {
            return null;
        }
        String resPackage = icon.getResPackage();
        if (TextUtils.isEmpty(resPackage) || resPackage.equals(this.mContext.getPackageName())) {
            return null;
        }
        return resPackage;
    }

    private synchronized void resetCache() {
        this.mLastResId = 0;
        this.mLastPackage = null;
    }

    public void setForceHidden(boolean z) {
        if (z != this.mForceHidden) {
            this.mForceHidden = z;
            this.mWillBeForceHidden = false;
            updateVisibility();
            Consumer<Boolean> consumer = this.mOnForceHiddenChangedListener;
            if (consumer != null) {
                consumer.accept(Boolean.valueOf(z));
            }
        }
    }

    @Override // android.widget.ImageView, android.view.View
    @RemotableViewMethod
    public void setVisibility(int i) {
        this.mDesiredVisibility = i;
        updateVisibility();
    }

    private void updateVisibility() {
        int i = this.mDesiredVisibility;
        if (i == 0 && this.mForceHidden) {
            i = 4;
        }
        Consumer<Integer> consumer = this.mOnVisibilityChangedListener;
        if (consumer != null) {
            consumer.accept(Integer.valueOf(i));
        }
        super.setVisibility(i);
    }

    public void setOnVisibilityChangedListener(Consumer<Integer> consumer) {
        this.mOnVisibilityChangedListener = consumer;
    }

    public void setOnForceHiddenChangedListener(Consumer<Boolean> consumer) {
        this.mOnForceHiddenChangedListener = consumer;
    }

    public boolean isForceHidden() {
        return this.mForceHidden;
    }

    @RemotableViewMethod
    public void setOriginalIconColor(int i) {
        this.mIconColor = i;
    }

    public int getOriginalIconColor() {
        return this.mIconColor;
    }

    public boolean willBeForceHidden() {
        return this.mWillBeForceHidden;
    }

    public void setWillBeForceHidden(boolean z) {
        this.mWillBeForceHidden = z;
    }
}
