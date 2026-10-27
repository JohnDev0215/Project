package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.InputFilter;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inspector.InspectionCompanion;
import android.view.inspector.PropertyMapper;
import android.view.inspector.PropertyReader;
import android.view.textclassifier.TextClassifier;
import android.widget.TextView;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import defpackage.ag0;
import defpackage.gh3;
import defpackage.hh3;
import defpackage.j32;
import defpackage.n3;
import defpackage.o83;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: classes.dex */
public class AppCompatTextView extends TextView implements EmojiCompatConfigurationView {
    public final AppCompatBackgroundHelper e;
    public final AppCompatTextHelper f;
    public AppCompatEmojiTextHelper g;
    public boolean h;
    public SuperCallerApi28 i;
    public Future<j32> j;

    public final class InspectionCompanion implements android.view.inspector.InspectionCompanion<AppCompatTextView> {
        public boolean a = false;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;

        @Override // android.view.inspector.InspectionCompanion
        public final void mapProperties(PropertyMapper propertyMapper) {
            this.b = propertyMapper.mapInt("autoSizeMaxTextSize", R.attr.autoSizeMaxTextSize);
            this.c = propertyMapper.mapInt("autoSizeMinTextSize", R.attr.autoSizeMinTextSize);
            this.d = propertyMapper.mapInt("autoSizeStepGranularity", R.attr.autoSizeStepGranularity);
            this.e = propertyMapper.mapIntEnum("autoSizeTextType", R.attr.autoSizeTextType, new IntFunction<String>() { // from class: androidx.appcompat.widget.AppCompatTextView.InspectionCompanion.1
                @Override // java.util.function.IntFunction
                public final String apply(int i) {
                    return i != 0 ? i != 1 ? String.valueOf(i) : "uniform" : "none";
                }
            });
            this.f = propertyMapper.mapObject("backgroundTint", R.attr.backgroundTint);
            this.g = propertyMapper.mapObject("backgroundTintMode", R.attr.backgroundTintMode);
            this.h = propertyMapper.mapObject("drawableTint", R.attr.drawableTint);
            this.i = propertyMapper.mapObject("drawableTintMode", R.attr.drawableTintMode);
            this.a = true;
        }

        @Override // android.view.inspector.InspectionCompanion
        public final void readProperties(AppCompatTextView appCompatTextView, PropertyReader propertyReader) {
            AppCompatTextView appCompatTextView2 = appCompatTextView;
            if (!this.a) {
                throw new InspectionCompanion.UninitializedPropertyMapException();
            }
            propertyReader.readInt(this.b, appCompatTextView2.getAutoSizeMaxTextSize());
            propertyReader.readInt(this.c, appCompatTextView2.getAutoSizeMinTextSize());
            propertyReader.readInt(this.d, appCompatTextView2.getAutoSizeStepGranularity());
            propertyReader.readIntEnum(this.e, appCompatTextView2.getAutoSizeTextType());
            propertyReader.readObject(this.f, appCompatTextView2.getBackgroundTintList());
            propertyReader.readObject(this.g, appCompatTextView2.getBackgroundTintMode());
            propertyReader.readObject(this.h, appCompatTextView2.getCompoundDrawableTintList());
            propertyReader.readObject(this.i, appCompatTextView2.getCompoundDrawableTintMode());
        }
    }

    public interface SuperCaller {
        void a(int[] iArr, int i);

        int[] b();

        TextClassifier c();

        int d();

        void e(TextClassifier textClassifier);

        void f(int i);

        void g(int i, int i2, int i3, int i4);

        int h();

        int i();

        void j(int i);

        int k();

        void l(int i);
    }

    public class SuperCallerApi28 extends SuperCallerApi26 {
        public SuperCallerApi28() {
            super();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void f(int i) {
            AppCompatTextView.super.setLastBaselineToBottomHeight(i);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCallerApi26, androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void j(int i) {
            AppCompatTextView.super.setFirstBaselineToTopHeight(i);
        }
    }

    public AppCompatTextView(Context context) {
        this(context, null);
    }

    private AppCompatEmojiTextHelper getEmojiTextViewHelper() {
        if (this.g == null) {
            this.g = new AppCompatEmojiTextHelper(this);
        }
        return this.g;
    }

    @Override // android.widget.TextView, android.view.View
    public void drawableStateChanged() {
        super.drawableStateChanged();
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.e;
        if (appCompatBackgroundHelper != null) {
            appCompatBackgroundHelper.a();
        }
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public int getAutoSizeMaxTextSize() {
        Method method = ViewUtils.a;
        return getSuperCaller().d();
    }

    @Override // android.widget.TextView
    public int getAutoSizeMinTextSize() {
        Method method = ViewUtils.a;
        return getSuperCaller().i();
    }

    @Override // android.widget.TextView
    public int getAutoSizeStepGranularity() {
        Method method = ViewUtils.a;
        return getSuperCaller().k();
    }

    @Override // android.widget.TextView
    public int[] getAutoSizeTextAvailableSizes() {
        Method method = ViewUtils.a;
        return getSuperCaller().b();
    }

    @Override // android.widget.TextView
    @SuppressLint({"WrongConstant"})
    public int getAutoSizeTextType() {
        Method method = ViewUtils.a;
        return getSuperCaller().h() == 1 ? 1 : 0;
    }

    @Override // android.widget.TextView
    public ActionMode.Callback getCustomSelectionActionModeCallback() {
        return o83.a(super.getCustomSelectionActionModeCallback());
    }

    @Override // android.widget.TextView
    public int getFirstBaselineToTopHeight() {
        return getPaddingTop() - getPaint().getFontMetricsInt().top;
    }

    @Override // android.widget.TextView
    public int getLastBaselineToBottomHeight() {
        return getPaddingBottom() + getPaint().getFontMetricsInt().bottom;
    }

    public SuperCaller getSuperCaller() {
        if (this.i == null) {
            this.i = new SuperCallerApi28();
        }
        return this.i;
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

    public ColorStateList getSupportCompoundDrawablesTintList() {
        return this.f.d();
    }

    public PorterDuff.Mode getSupportCompoundDrawablesTintMode() {
        return this.f.e();
    }

    @Override // android.widget.TextView
    public CharSequence getText() {
        Future<j32> future = this.j;
        if (future != null) {
            try {
                this.j = null;
                future.get().getClass();
                setText((CharSequence) null);
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        return super.getText();
    }

    @Override // android.widget.TextView
    public TextClassifier getTextClassifier() {
        return getSuperCaller().c();
    }

    public j32.a getTextMetricsParamsCompat() {
        return new j32.a(getTextMetricsParams());
    }

    @Override // android.widget.TextView, android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        InputConnection inputConnectionOnCreateInputConnection = super.onCreateInputConnection(editorInfo);
        this.f.getClass();
        if (Build.VERSION.SDK_INT < 30 && inputConnectionOnCreateInputConnection != null) {
            ag0.a(editorInfo, getText());
        }
        AppCompatHintHelper.a(this, editorInfo, inputConnectionOnCreateInputConnection);
        return inputConnectionOnCreateInputConnection;
    }

    @Override // android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.getClass();
            Method method = ViewUtils.a;
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int i, int i2) {
        Future<j32> future = this.j;
        if (future != null) {
            try {
                this.j = null;
                future.get().getClass();
                setText((CharSequence) null);
            } catch (InterruptedException | ExecutionException unused) {
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        if (this.f != null) {
            Method method = ViewUtils.a;
        }
    }

    @Override // android.widget.TextView
    public void setAllCaps(boolean z) {
        super.setAllCaps(z);
        getEmojiTextViewHelper().c(z);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithConfiguration(int i, int i2, int i3, int i4) {
        Method method = ViewUtils.a;
        getSuperCaller().g(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public final void setAutoSizeTextTypeUniformWithPresetSizes(int[] iArr, int i) {
        Method method = ViewUtils.a;
        getSuperCaller().a(iArr, i);
    }

    @Override // android.widget.TextView
    public void setAutoSizeTextTypeWithDefaults(int i) {
        Method method = ViewUtils.a;
        getSuperCaller().l(i);
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

    @Override // android.widget.TextView
    public final void setCompoundDrawables(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawables(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelative(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelative(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesRelativeWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public final void setCompoundDrawablesWithIntrinsicBounds(Drawable drawable, Drawable drawable2, Drawable drawable3, Drawable drawable4) {
        super.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable2, drawable3, drawable4);
        AppCompatTextHelper appCompatTextHelper = this.f;
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

    @Override // android.widget.TextView
    public void setFilters(InputFilter[] inputFilterArr) {
        super.setFilters(getEmojiTextViewHelper().a(inputFilterArr));
    }

    @Override // android.widget.TextView
    public void setFirstBaselineToTopHeight(int i) {
        getSuperCaller().j(i);
    }

    @Override // android.widget.TextView
    public void setLastBaselineToBottomHeight(int i) {
        getSuperCaller().f(i);
    }

    @Override // android.widget.TextView
    public void setLineHeight(int i) {
        n3.t(i);
        if (i != getPaint().getFontMetricsInt(null)) {
            setLineSpacing(i - r0, 1.0f);
        }
    }

    public void setPrecomputedText(j32 j32Var) {
        j32Var.getClass();
        setText((CharSequence) null);
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

    public void setSupportCompoundDrawablesTintList(ColorStateList colorStateList) {
        AppCompatTextHelper appCompatTextHelper = this.f;
        appCompatTextHelper.h(colorStateList);
        appCompatTextHelper.b();
    }

    public void setSupportCompoundDrawablesTintMode(PorterDuff.Mode mode) {
        AppCompatTextHelper appCompatTextHelper = this.f;
        appCompatTextHelper.i(mode);
        appCompatTextHelper.b();
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        super.setTextAppearance(context, i);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.g(context, i);
        }
    }

    @Override // android.widget.TextView
    public void setTextClassifier(TextClassifier textClassifier) {
        getSuperCaller().e(textClassifier);
    }

    public void setTextFuture(Future<j32> future) {
        this.j = future;
        if (future != null) {
            requestLayout();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0030  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void setTextMetricsParamsCompat(j32.a r3) {
        /*
            r2 = this;
            android.text.TextDirectionHeuristic r0 = r3.b
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.FIRSTSTRONG_RTL
            if (r0 != r1) goto L7
            goto L30
        L7:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            if (r0 != r1) goto Lc
            goto L30
        Lc:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.ANYRTL_LTR
            if (r0 != r1) goto L12
            r0 = 2
            goto L31
        L12:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.LTR
            if (r0 != r1) goto L18
            r0 = 3
            goto L31
        L18:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.RTL
            if (r0 != r1) goto L1e
            r0 = 4
            goto L31
        L1e:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.LOCALE
            if (r0 != r1) goto L24
            r0 = 5
            goto L31
        L24:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.FIRSTSTRONG_LTR
            if (r0 != r1) goto L2a
            r0 = 6
            goto L31
        L2a:
            android.text.TextDirectionHeuristic r1 = android.text.TextDirectionHeuristics.FIRSTSTRONG_RTL
            if (r0 != r1) goto L30
            r0 = 7
            goto L31
        L30:
            r0 = 1
        L31:
            r2.setTextDirection(r0)
            android.text.TextPaint r0 = r2.getPaint()
            android.text.TextPaint r1 = r3.a
            r0.set(r1)
            int r0 = r3.c
            r2.setBreakStrategy(r0)
            int r3 = r3.d
            r2.setHyphenationFrequency(r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.widget.AppCompatTextView.setTextMetricsParamsCompat(j32$a):void");
    }

    @Override // android.widget.TextView
    public final void setTextSize(int i, float f) {
        Method method = ViewUtils.a;
        super.setTextSize(i, f);
    }

    @Override // android.widget.TextView
    public final void setTypeface(Typeface typeface, int i) {
        Typeface typefaceCreate;
        if (this.h) {
            return;
        }
        if (typeface == null || i <= 0) {
            typefaceCreate = null;
        } else {
            Context context = getContext();
            hh3 hh3Var = gh3.a;
            if (context == null) {
                throw new IllegalArgumentException("Context cannot be null");
            }
            typefaceCreate = Typeface.create(typeface, i);
        }
        this.h = true;
        if (typefaceCreate != null) {
            typeface = typefaceCreate;
        }
        try {
            super.setTypeface(typeface, i);
        } finally {
            this.h = false;
        }
    }

    public AppCompatTextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, android.R.attr.textViewStyle);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AppCompatTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TintContextWrapper.a(context);
        this.h = false;
        this.i = null;
        ThemeUtils.a(getContext(), this);
        AppCompatBackgroundHelper appCompatBackgroundHelper = new AppCompatBackgroundHelper(this);
        this.e = appCompatBackgroundHelper;
        appCompatBackgroundHelper.d(attributeSet, i);
        AppCompatTextHelper appCompatTextHelper = new AppCompatTextHelper(this);
        this.f = appCompatTextHelper;
        appCompatTextHelper.f(attributeSet, i);
        appCompatTextHelper.b();
        new AppCompatTextClassifierHelper(this);
        getEmojiTextViewHelper().b(attributeSet, i);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesRelativeWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesRelativeWithIntrinsicBounds(i != 0 ? AppCompatResources.a(context, i) : null, i2 != 0 ? AppCompatResources.a(context, i2) : null, i3 != 0 ? AppCompatResources.a(context, i3) : null, i4 != 0 ? AppCompatResources.a(context, i4) : null);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablesWithIntrinsicBounds(int i, int i2, int i3, int i4) {
        Context context = getContext();
        setCompoundDrawablesWithIntrinsicBounds(i != 0 ? AppCompatResources.a(context, i) : null, i2 != 0 ? AppCompatResources.a(context, i2) : null, i3 != 0 ? AppCompatResources.a(context, i3) : null, i4 != 0 ? AppCompatResources.a(context, i4) : null);
        AppCompatTextHelper appCompatTextHelper = this.f;
        if (appCompatTextHelper != null) {
            appCompatTextHelper.b();
        }
    }

    public class SuperCallerApi26 implements SuperCaller {
        public SuperCallerApi26() {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void a(int[] iArr, int i) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithPresetSizes(iArr, i);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final int[] b() {
            return AppCompatTextView.super.getAutoSizeTextAvailableSizes();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final TextClassifier c() {
            return AppCompatTextView.super.getTextClassifier();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final int d() {
            return AppCompatTextView.super.getAutoSizeMaxTextSize();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void e(TextClassifier textClassifier) {
            AppCompatTextView.super.setTextClassifier(textClassifier);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void g(int i, int i2, int i3, int i4) {
            AppCompatTextView.super.setAutoSizeTextTypeUniformWithConfiguration(i, i2, i3, i4);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final int h() {
            return AppCompatTextView.super.getAutoSizeTextType();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final int i() {
            return AppCompatTextView.super.getAutoSizeMinTextSize();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final int k() {
            return AppCompatTextView.super.getAutoSizeStepGranularity();
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public final void l(int i) {
            AppCompatTextView.super.setAutoSizeTextTypeWithDefaults(i);
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void f(int i) {
        }

        @Override // androidx.appcompat.widget.AppCompatTextView.SuperCaller
        public void j(int i) {
        }
    }
}
