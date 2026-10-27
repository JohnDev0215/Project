package org.chromium.components.browser_ui.widget;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.google.android.material.elevation.ElevationOverlayProvider;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes2.dex */
public class SurfaceColorDrawable extends GradientDrawable {
    public float a;
    public float b;

    @SuppressLint({"NewApi"})
    public final void a(Resources.Theme theme) {
        int i = R$attr.elevationOverlayEnabled;
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(i, typedValue, true);
        boolean z = typedValue.data != 0;
        int i2 = R$attr.elevationOverlayColor;
        TypedValue typedValue2 = new TypedValue();
        theme.resolveAttribute(i2, typedValue2, true);
        int i3 = typedValue2.data;
        int i4 = R$attr.elevationOverlayAccentColor;
        TypedValue typedValue3 = new TypedValue();
        theme.resolveAttribute(i4, typedValue3, true);
        int i5 = typedValue3.data;
        int i6 = R$attr.colorSurface;
        TypedValue typedValue4 = new TypedValue();
        theme.resolveAttribute(i6, typedValue4, true);
        int i7 = typedValue4.data;
        int iB = new ElevationOverlayProvider(z, i3, i5, i7, this.b).b(this.a, i7);
        if (getColors() == null) {
            setColor(iB);
        } else {
            setTint(iB);
        }
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public final void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
        a(theme);
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public final boolean canApplyTheme() {
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public final Drawable.Callback getCallback() {
        return null;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public final Drawable.ConstantState getConstantState() {
        return null;
    }

    @Override // android.graphics.drawable.GradientDrawable, android.graphics.drawable.Drawable
    public final void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, R$styleable.SurfaceColorDrawable);
        this.a = typedArrayObtainAttributes.getDimensionPixelSize(R$styleable.SurfaceColorDrawable_surfaceElevation, 0);
        typedArrayObtainAttributes.recycle();
        super.inflate(resources, xmlPullParser, attributeSet, theme);
        this.b = resources.getDisplayMetrics().density;
        if (theme != null) {
            a(theme);
        }
    }
}
