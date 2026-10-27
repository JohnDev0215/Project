package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.widget.CheckedTextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.in3;
import defpackage.o83;
import defpackage.om3;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatCheckedTextView extends CheckedTextView implements EmojiCompatConfigurationView {
    public final AppCompatCheckedTextViewHelper e;
    public final AppCompatBackgroundHelper f;
    public final AppCompatTextHelper g;
    public AppCompatEmojiTextHelper h;

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<AppCompatCheckedTextView> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapObject("backgroundTint", R.attr.backgroundTint);
            this.c = propertyMapper.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
            this.d = propertyMapper.mapObject("checkMarkTint", R.attr.checkMarkTint);
            this.e = propertyMapper.mapObject("checkMarkTintMode", R.attr.checkMarkTintMode);
            this.f = propertyMapper.mapObject("drawableTint", R.attr.drawableTint);
            this.g = propertyMapper.mapObject("drawableTintMode", R.attr.drawableTintMode);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(AppCompatCheckedTextView appCompatCheckedTextView, PropertyReader propertyReader) {
            AppCompatCheckedTextView appCompatCheckedTextView2 = appCompatCheckedTextView;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readObject(this.b, appCompatCheckedTextView2.getBackgroundTintList());
            propertyReader.readObject(this.c, appCompatCheckedTextView2.getBackgroundTintMode());
            propertyReader.readObject(this.d, appCompatCheckedTextView2.getCheckMarkTintList());
            propertyReader.readObject(this.e, appCompatCheckedTextView2.getCheckMarkTintMode());
            propertyReader.readObject(this.f, appCompatCheckedTextView2.getCompoundDrawableTintList());
            propertyReader.readObject(this.g, appCompatCheckedTextView2.getCompoundDrawableTintMode());
        }
    }

    public AppCompatCheckedTextView(Context context) {
        this(context, null);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.h == null) {
            this.h = new AppCompatEmojiTextHelper(this);
        }
        return this.h;
    }

    @Override // android.widget.CheckedTextView, android.widget.TextView, android.view.View
    public final void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatTextHelper appCompatTextHelper = this.g;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.a();
        }
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            appCompatCheckedTextViewHelper.a();
        }
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return o83.a(super.getCustomSelectionActionModeCallback());
    }

    public ColorStateList getSupportBackgroundTintList() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.b();
        }
        return null;
    }

    public PorterDuff.Mode getSupportBackgroundTintMode() {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            return appCompatBackgroundHelper.c();
        }
        return null;
    }

    public ColorStateList getSupportCheckMarkTintList() {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            return appCompatCheckedTextViewHelper.b;
        }
        return null;
    }

    public PorterDuff.Mode getSupportCheckMarkTintMode() {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            return appCompatCheckedTextViewHelper.c;
        }
        return null;
    }

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.g.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.g.e();
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        AppCompatHintHelper.a(this, editorInfo, inputConnectionOnCreateInputConnection);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.e();
        }
    }

    @Override // android.view.View
    public void setBackgroundResource(int i) {
        super.setBackgroundResource(i);
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.f(i);
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(Drawable drawable) {
        super.setCheckMarkDrawable(drawable);
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            if (appCompatCheckedTextViewHelper.f) {
                appCompatCheckedTextViewHelper.f = false;
            } else {
                appCompatCheckedTextViewHelper.f = true;
                appCompatCheckedTextViewHelper.a();
            }
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.g;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.g;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public void setCustomSelectionActionModeCallback(ActionMode.Callback callback) {
        super.setCustomSelectionActionModeCallback(callback);
    }

    public void setEmojiCompatEnabled(boolean z) {
        getEmojiTextViewHelper().d(z);
    }

    public void setSupportBackgroundTintList(ColorStateList colorStateList) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.h(colorStateList);
        }
    }

    public void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.f;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.i(mode);
        }
    }

    public void setSupportCheckMarkTintList(ColorStateList colorStateList) {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            appCompatCheckedTextViewHelper.b = colorStateList;
            appCompatCheckedTextViewHelper.d = true;
            appCompatCheckedTextViewHelper.a();
        }
    }

    public void setSupportCheckMarkTintMode(PorterDuff.Mode mode) {
        AppCompatCheckedTextViewHelper appCompatCheckedTextViewHelper = this.e;
        if (appCompatCheckedTextViewHelper != null) {
            appCompatCheckedTextViewHelper.c = mode;
            appCompatCheckedTextViewHelper.e = true;
            appCompatCheckedTextViewHelper.a();
        }
    }

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        AppCompatTextHelper appCompatTextHelper = this.g;
        appCompatTextHelper.h(colorStateList);
        appCompatTextHelper.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        AppCompatTextHelper appCompatTextHelper = this.g;
        appCompatTextHelper.i(mode);
        appCompatTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.g;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.g(context, i);
        }
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.checkedTextViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        boolean z;
        int i2;
        int i3;
        super(context, attributeSet, i);
        TintContextWrapper.a(context);
        ThemeUtils.a(getContext(), this);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.g = appCompatTextHelper;
        appCompatTextHelper.f(attributeSet, i);
        appCompatTextHelper.b();
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.f = appCompatBackgroundHelper;
        appCompatBackgroundHelper.d(attributeSet, i);
        this.e = new AppCompatCheckedTextViewHelper(this);
        Context context2 = getContext();
        int[] iArr = R.styleable.CheckedTextView;
        TintTypedArray tintTypedArrayM = TintTypedArray.m(context2, attributeSet, iArr, i);
        Context context3 = getContext();
        TypedArray typedArray = tintTypedArrayM.b;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.n.b(this, context3, iArr, attributeSet, typedArray, i, 0);
        try {
            int i4 = R.styleable.CheckedTextView_checkMarkCompat;
            if (!tintTypedArrayM.l(i4) || (i3 = tintTypedArrayM.i(i4, 0)) == 0) {
                z = false;
            } else {
                try {
                    setCheckMarkDrawable(AppCompatResources.a(getContext(), i3));
                    z = true;
                } catch (Resources.NotFoundException unused) {
                    z = false;
                }
            }
            if (!z) {
                int i5 = R.styleable.CheckedTextView_android_checkMark;
                if (tintTypedArrayM.l(i5) && (i2 = tintTypedArrayM.i(i5, 0)) != 0) {
                    setCheckMarkDrawable(AppCompatResources.a(getContext(), i2));
                }
            }
            int i6 = R.styleable.CheckedTextView_checkMarkTint;
            if (tintTypedArrayM.l(i6)) {
                setCheckMarkTintList(tintTypedArrayM.b(i6));
            }
            int i7 = R.styleable.CheckedTextView_checkMarkTintMode;
            if (tintTypedArrayM.l(i7)) {
                setCheckMarkTintMode(DrawableUtils.c(tintTypedArrayM.h(i7, -1), null));
            }
            tintTypedArrayM.n();
            getEmojiTextViewHelper().b(attributeSet, i);
        } catch (Throwable th) {
            tintTypedArrayM.n();
            throw th;
        }
    }

    @Override // android.widget.CheckedTextView
    public void setCheckMarkDrawable(int i) {
        setCheckMarkDrawable(AppCompatResources.a(getContext(), i));
    }
}
