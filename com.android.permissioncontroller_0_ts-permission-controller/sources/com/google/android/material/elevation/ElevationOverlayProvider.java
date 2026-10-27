package com.google.android.material.elevation;

import android.content.Context;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;

/* JADX INFO: loaded from: classes8.dex */
public class ElevationOverlayProvider {
    private static final float ELEVATION_OVERLAY_MULTIPLIER = 4.5f;
    private final int colorSurface;
    private final float displayDensity;
    private final int elevationOverlaysColor;
    private final boolean elevationOverlaysEnabled;

    public ElevationOverlayProvider(Context context) {
        this.elevationOverlaysEnabled = MaterialAttributes.resolveBooleanAttribute(context, R.attr.elevationOverlaysEnabled);
        this.elevationOverlaysColor = MaterialColors.getColor(context, R.attr.elevationOverlaysColor, 0);
        this.colorSurface = MaterialColors.getColor(context, R.attr.colorSurface, 0);
        this.displayDensity = context.getResources().getDisplayMetrics().density;
    }

    public int layerOverlayIfNeeded(int backgroundColor, float elevation) {
        if (this.elevationOverlaysEnabled && isSurfaceColor(backgroundColor)) {
            return layerOverlay(backgroundColor, elevation);
        }
        return backgroundColor;
    }

    public int layerOverlay(int backgroundColor, float elevation) {
        float overlayAlpha = calculateOverlayAlphaFraction(elevation);
        return MaterialColors.layer(backgroundColor, this.elevationOverlaysColor, overlayAlpha);
    }

    public int calculateOverlayAlpha(float elevation) {
        return Math.round(calculateOverlayAlphaFraction(elevation) * 255.0f);
    }

    public float calculateOverlayAlphaFraction(float elevation) {
        float f = this.displayDensity;
        if (f <= 0.0f || elevation <= 0.0f) {
            return 0.0f;
        }
        float elevationDp = elevation / f;
        float alphaFraction = (((float) Math.log1p(elevationDp)) * ELEVATION_OVERLAY_MULTIPLIER) / 100.0f;
        return Math.min(alphaFraction, 1.0f);
    }

    public boolean isOverlaysEnabled() {
        return this.elevationOverlaysEnabled;
    }

    public int getOverlaysColor() {
        return this.elevationOverlaysColor;
    }

    public int getColorSurface() {
        return this.colorSurface;
    }

    private boolean isSurfaceColor(int color) {
        return ColorUtils.setAlphaComponent(color, 255) == this.colorSurface;
    }
}
