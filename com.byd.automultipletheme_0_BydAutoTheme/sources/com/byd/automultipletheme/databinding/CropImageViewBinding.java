package com.byd.automultipletheme.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.common.cropper.CropOverlayView;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class CropImageViewBinding implements ViewBinding {
    public final CropOverlayView CropOverlayView;
    public final ProgressBar CropProgressBar;
    public final ImageView ImageViewImage;
    private final View rootView;

    private CropImageViewBinding(View view, CropOverlayView cropOverlayView, ProgressBar progressBar, ImageView imageView) {
        this.rootView = view;
        this.CropOverlayView = cropOverlayView;
        this.CropProgressBar = progressBar;
        this.ImageViewImage = imageView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public View getRoot() {
        return this.rootView;
    }

    public static CropImageViewBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        Objects.requireNonNull(viewGroup, "parent");
        layoutInflater.inflate(R.layout.crop_image_view, viewGroup);
        return bind(viewGroup);
    }

    public static CropImageViewBinding bind(View view) {
        int i = R.id.CropOverlayView;
        CropOverlayView cropOverlayView = (CropOverlayView) ViewBindings.findChildViewById(view, R.id.CropOverlayView);
        if (cropOverlayView != null) {
            i = R.id.CropProgressBar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(view, R.id.CropProgressBar);
            if (progressBar != null) {
                i = R.id.ImageView_image;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.ImageView_image);
                if (imageView != null) {
                    return new CropImageViewBinding(view, cropOverlayView, progressBar, imageView);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
