package com.byd.automultipletheme.common.adapter;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.byd.automultipletheme.common.Const;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutoBaseHolder.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J!\u0010\u0007\u001a\u0002H\b\"\b\b\u0000\u0010\b*\u00020\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u0002¢\u0006\u0002\u0010\u000bJ!\u0010\f\u001a\u0004\u0018\u0001H\b\"\b\b\u0000\u0010\b*\u00020\u00032\b\b\u0001\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001a\u0010\r\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000e\u001a\u00020\nJ\u001a\u0010\u000f\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u0010\u001a\u00020\nJ\u0018\u0010\u0011\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0014\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0013J\u0018\u0010\u0016\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\u0018J\u0018\u0010\u0016\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\u001aJ\u001a\u0010\u001b\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u001a\u0010\u001e\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\u0010\u001f\u001a\u0004\u0018\u00010 J\u001a\u0010!\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\"\u001a\u00020\nJ\u001a\u0010#\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010%J\u001a\u0010#\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010&\u001a\u00020\nJ\u001a\u0010#\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\u0010$\u001a\u0004\u0018\u00010\u001aJ\u0018\u0010'\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010(\u001a\u00020)J\u001a\u0010'\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\u000e\u001a\u00020\nJ\u001a\u0010*\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\b\b\u0001\u0010+\u001a\u00020\nJ\u0018\u0010,\u001a\u00020\u00002\b\b\u0001\u0010\t\u001a\u00020\n2\u0006\u0010-\u001a\u00020\u0013R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/byd/automultipletheme/common/adapter/AutoBaseHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "views", "Landroid/util/SparseArray;", "getView", ExifInterface.GPS_DIRECTION_TRUE, "viewId", "", "(I)Landroid/view/View;", "getViewOrNull", "setBackgroundColor", "color", "setBackgroundResource", "backgroundRes", "setEnabled", "isEnabled", "", "setGone", "isGone", "setImage", "uri", "Landroid/net/Uri;", "url", "", "setImageBitmap", "bitmap", "Landroid/graphics/Bitmap;", "setImageDrawable", "drawable", "Landroid/graphics/drawable/Drawable;", "setImageResource", "imageResId", "setText", Const.CONTENT_SYSTEM_SETTING_COLUMN, "", "strId", "setTextColor", "colors", "Landroid/content/res/ColorStateList;", "setTextColorRes", "colorRes", "setVisible", "isVisible", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AutoBaseHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AutoBaseHolder(View itemView) {
        super(itemView);
        Intrinsics.checkNotNullParameter(itemView, "itemView");
        this.views = new SparseArray<>();
    }

    private final <T extends View> T getView(int viewId) {
        T t = (T) getViewOrNull(viewId);
        if (t != null) {
            return t;
        }
        throw new IllegalStateException(("No view found with id " + viewId).toString());
    }

    public final <T extends View> T getViewOrNull(int viewId) {
        T t;
        T t2 = (T) this.views.get(viewId);
        if (t2 == null && (t = (T) this.itemView.findViewById(viewId)) != null) {
            this.views.put(viewId, t);
            return t;
        }
        if (t2 == null) {
            return null;
        }
        return t2;
    }

    public final AutoBaseHolder setText(int viewId, CharSequence value) {
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

    public final AutoBaseHolder setText(int viewId, String value) {
        ((TextView) getView(viewId)).setText(value);
        return this;
    }

    public final AutoBaseHolder setText(int viewId, int strId) {
        ((TextView) getView(viewId)).setText(strId);
        return this;
    }

    public final AutoBaseHolder setTextColor(int viewId, ColorStateList colors) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        ((TextView) getView(viewId)).setTextColor(colors);
        return this;
    }

    public final AutoBaseHolder setTextColor(int viewId, int color) {
        ((TextView) getView(viewId)).setTextColor(color);
        return this;
    }

    public final AutoBaseHolder setTextColorRes(int viewId, int colorRes) {
        ((TextView) getView(viewId)).setTextColor(ContextCompat.getColor(this.itemView.getContext(), colorRes));
        return this;
    }

    public final AutoBaseHolder setImage(int viewId, String url) {
        Intrinsics.checkNotNullParameter(url, "url");
        ImageView imageView = (ImageView) getView(viewId);
        Glide.with(imageView).load(url).into(imageView);
        return this;
    }

    public final AutoBaseHolder setImage(int viewId, Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        ImageView imageView = (ImageView) getView(viewId);
        Glide.with(imageView).load(uri).into(imageView);
        return this;
    }

    public final AutoBaseHolder setImageResource(int viewId, int imageResId) {
        ((ImageView) getView(viewId)).setImageResource(imageResId);
        return this;
    }

    public final AutoBaseHolder setImageDrawable(int viewId, Drawable drawable) {
        ((ImageView) getView(viewId)).setImageDrawable(drawable);
        return this;
    }

    public final AutoBaseHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ((ImageView) getView(viewId)).setImageBitmap(bitmap);
        return this;
    }

    public final AutoBaseHolder setBackgroundColor(int viewId, int color) {
        getView(viewId).setBackgroundColor(color);
        return this;
    }

    public final AutoBaseHolder setBackgroundResource(int viewId, int backgroundRes) {
        getView(viewId).setBackgroundResource(backgroundRes);
        return this;
    }

    public final AutoBaseHolder setVisible(int viewId, boolean isVisible) {
        getView(viewId).setVisibility(isVisible ? 0 : 4);
        return this;
    }

    public final AutoBaseHolder setGone(int viewId, boolean isGone) {
        getView(viewId).setVisibility(isGone ? 8 : 0);
        return this;
    }

    public final AutoBaseHolder setEnabled(int viewId, boolean isEnabled) {
        getView(viewId).setEnabled(isEnabled);
        return this;
    }
}
