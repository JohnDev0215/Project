package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.R;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatImageButton extends ImageButton {
    public final AppCompatBackgroundHelper e;
    public final AppCompatImageHelper f;
    public boolean g;

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<AppCompatImageButton> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapObject("backgroundTint", R.attr.backgroundTint);
            this.c = propertyMapper.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
            this.d = propertyMapper.mapObject("tint", R.attr.tint);
            this.e = propertyMapper.mapObject("tintMode", R.attr.tintMode);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(AppCompatImageButton appCompatImageButton, PropertyReader propertyReader) {
            AppCompatImageButton appCompatImageButton2 = appCompatImageButton;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.b, appCompatImageButton2.getBackgroundTintList());
            propertyReader.readObject(this.c, appCompatImageButton2.getBackgroundTintMode());
            propertyReader.readObject(this.d, appCompatImageButton2.getImageTintList());
            propertyReader.readObject(this.e, appCompatImageButton2.getImageTintMode());
        }
    }

    public AppCompatImageButton(Context context) {
        this(context, null);
    }

    @Override // android.widget.ImageView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.a();
        }
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null) {
            appCompatImageHelper.a();
        }
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.c();
        }
        return null;
    }

    public ColorStateList getSupportImageTintList() {
        TintInfo tintInfo;
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper == null || (tintInfo = appCompatImageHelper.b) == null) {
            return null;
        }
        return tintInfo.a;
    }

    public PorterDuff.Mode getSupportImageTintMode() {
        TintInfo tintInfo;
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper == null || (tintInfo = appCompatImageHelper.b) == null) {
            return null;
        }
        return tintInfo.b;
    }

    @Override // android.widget.ImageView, android.view.View
    public final boolean hasOverlappingRendering() {
        return ((this.f.a.getBackground() instanceof RippleDrawable) ^ true) && super.hasOverlappingRendering();
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.f(i);
        }
    }

    @Override // android.widget.ImageView
    public void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null) {
            appCompatImageHelper.a();
        }
    }

    @Override // android.widget.ImageView
    public void setImageDrawable(Drawable drawable) {
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null && drawable != null && !this.g) {
            appCompatImageHelper.c = drawable.getLevel();
        }
        super.setImageDrawable(drawable);
        if (appCompatImageHelper != null) {
            appCompatImageHelper.a();
            if (this.g) {
                return;
            }
            ImageView imageView = appCompatImageHelper.a;
            if (imageView.getDrawable() != null) {
                imageView.getDrawable().setLevel(appCompatImageHelper.c);
            }
        }
    }

    @Override // android.widget.ImageView
    public void setImageLevel(int i) {
        super.setImageLevel(i);
        this.g = true;
    }

    @Override // android.widget.ImageView
    public void setImageResource(int i) {
        this.f.c(i);
    }

    @Override // android.widget.ImageView
    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null) {
            appCompatImageHelper.a();
        }
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.i(mode);
        }
    }

    public void setSupportImageTintList(ColorStateList colorStateList) {
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null) {
            if (appCompatImageHelper.b == null) {
                appCompatImageHelper.b = new TintInfo();
            }
            TintInfo tintInfo = appCompatImageHelper.b;
            tintInfo.a = colorStateList;
            tintInfo.d = true;
            appCompatImageHelper.a();
        }
    }

    public void setSupportImageTintMode(PorterDuff.Mode mode) {
        AppCompatImageHelper appCompatImageHelper = this.f;
        if (appCompatImageHelper != null) {
            if (appCompatImageHelper.b == null) {
                appCompatImageHelper.b = new TintInfo();
            }
            TintInfo tintInfo = appCompatImageHelper.b;
            tintInfo.b = mode;
            tintInfo.c = true;
            appCompatImageHelper.a();
        }
    }

    public AppCompatImageButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.imageButtonStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatImageButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintContextWrapper.a(context);
        this.g = false;
        ThemeUtils.a(getContext(), this);
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.e = appCompatBackgroundHelper;
        appCompatBackgroundHelper.d(attributeSet, i);
        AppCompatImageHelper appCompatImageHelper = new AppCompatImageHelper(this);
        this.f = appCompatImageHelper;
        appCompatImageHelper.b(attributeSet, i);
    }
}
