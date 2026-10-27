package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.Log;
import android.util.TypedValue;
import android.widget.TextView;
import com.android.webview.R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: m0, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1138m0 {
    public static final RectF k = new RectF();
    public static ConcurrentHashMap l = new ConcurrentHashMap();
    public int a = 0;
    public boolean b = false;
    public float c = -1.0f;
    public float d = -1.0f;
    public float e = -1.0f;
    public int[] f = new int[0];
    public boolean g = false;
    public TextPaint h;
    public final TextView i;
    public final Context j;

    public C1138m0(TextView textView) {
        this.i = textView;
        this.j = textView.getContext();
    }

    public void a() {
        if (i() && this.a != 0) {
            if (this.b) {
                if (this.i.getMeasuredHeight() <= 0 || this.i.getMeasuredWidth() <= 0) {
                    return;
                }
                int measuredWidth = ((Boolean) e(this.i, "getHorizontallyScrolling", Boolean.FALSE)).booleanValue() ? R.raw.empty : (this.i.getMeasuredWidth() - this.i.getTotalPaddingLeft()) - this.i.getTotalPaddingRight();
                int height = (this.i.getHeight() - this.i.getCompoundPaddingBottom()) - this.i.getCompoundPaddingTop();
                if (measuredWidth <= 0 || height <= 0) {
                    return;
                }
                synchronized (k) {
                    k.setEmpty();
                    k.right = measuredWidth;
                    k.bottom = height;
                    float fC = c(k);
                    if (fC != this.i.getTextSize()) {
                        f(0, fC);
                    }
                }
            }
            this.b = true;
        }
    }

    public final int[] b(int[] iArr) {
        int length = iArr.length;
        if (length == 0) {
            return iArr;
        }
        Arrays.sort(iArr);
        ArrayList arrayList = new ArrayList();
        for (int i : iArr) {
            if (i > 0 && Collections.binarySearch(arrayList, Integer.valueOf(i)) < 0) {
                arrayList.add(Integer.valueOf(i));
            }
        }
        if (length == arrayList.size()) {
            return iArr;
        }
        int size = arrayList.size();
        int[] iArr2 = new int[size];
        for (int i2 = 0; i2 < size; i2++) {
            iArr2[i2] = ((Integer) arrayList.get(i2)).intValue();
        }
        return iArr2;
    }

    public final int c(RectF rectF) {
        int i;
        StaticLayout staticLayout;
        CharSequence transformation;
        int length = this.f.length;
        if (length == 0) {
            throw new IllegalStateException("No available text sizes to choose from.");
        }
        int i2 = length - 1;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        while (i4 <= i2) {
            int i6 = (i4 + i2) / 2;
            int i7 = this.f[i6];
            CharSequence text = this.i.getText();
            TransformationMethod transformationMethod = this.i.getTransformationMethod();
            if (transformationMethod != null && (transformation = transformationMethod.getTransformation(text, this.i)) != null) {
                text = transformation;
            }
            int maxLines = this.i.getMaxLines();
            TextPaint textPaint = this.h;
            if (textPaint == null) {
                this.h = new TextPaint();
            } else {
                textPaint.reset();
            }
            this.h.set(this.i.getPaint());
            this.h.setTextSize(i7);
            Layout.Alignment alignment = (Layout.Alignment) e(this.i, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
            if (Build.VERSION.SDK_INT >= 23) {
                staticLayout = StaticLayout.Builder.obtain(text, i3, text.length(), this.h, Math.round(rectF.right)).setAlignment(alignment).setLineSpacing(this.i.getLineSpacingExtra(), this.i.getLineSpacingMultiplier()).setIncludePad(this.i.getIncludeFontPadding()).setBreakStrategy(this.i.getBreakStrategy()).setHyphenationFrequency(this.i.getHyphenationFrequency()).setMaxLines(maxLines == -1 ? Integer.MAX_VALUE : maxLines).setTextDirection((TextDirectionHeuristic) e(this.i, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR)).build();
                i = -1;
            } else {
                i = -1;
                staticLayout = new StaticLayout(text, this.h, Math.round(rectF.right), alignment, this.i.getLineSpacingMultiplier(), this.i.getLineSpacingExtra(), this.i.getIncludeFontPadding());
            }
            if ((maxLines == i || (staticLayout.getLineCount() <= maxLines && staticLayout.getLineEnd(staticLayout.getLineCount() - 1) == text.length())) && ((float) staticLayout.getHeight()) <= rectF.bottom) {
                int i8 = i6 + 1;
                i3 = 0;
                i5 = i4;
                i4 = i8;
            } else {
                i5 = i6 - 1;
                i2 = i5;
                i3 = 0;
            }
        }
        return this.f[i5];
    }

    public final Method d(String str) {
        try {
            Method declaredMethod = (Method) l.get(str);
            if (declaredMethod == null && (declaredMethod = TextView.class.getDeclaredMethod(str, new Class[0])) != null) {
                declaredMethod.setAccessible(true);
                l.put(str, declaredMethod);
            }
            return declaredMethod;
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + str + "() method", e);
            return null;
        }
    }

    public final Object e(Object obj, String str, Object obj2) {
        try {
            return d(str).invoke(obj, new Object[0]);
        } catch (Exception e) {
            Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + str + "() method", e);
            return obj2;
        }
    }

    public void f(int i, float f) {
        Context context = this.j;
        float fApplyDimension = TypedValue.applyDimension(i, f, (context == null ? Resources.getSystem() : context.getResources()).getDisplayMetrics());
        if (fApplyDimension != this.i.getPaint().getTextSize()) {
            this.i.getPaint().setTextSize(fApplyDimension);
            boolean zIsInLayout = this.i.isInLayout();
            if (this.i.getLayout() != null) {
                this.b = false;
                try {
                    Method methodD = d("nullLayouts");
                    if (methodD != null) {
                        methodD.invoke(this.i, new Object[0]);
                    }
                } catch (Exception e) {
                    Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", e);
                }
                if (zIsInLayout) {
                    this.i.forceLayout();
                } else {
                    this.i.requestLayout();
                }
                this.i.invalidate();
            }
        }
    }

    public final boolean g() {
        if (i() && this.a == 1) {
            if (!this.g || this.f.length == 0) {
                float fRound = Math.round(this.d);
                int i = 1;
                while (Math.round(this.c + fRound) <= Math.round(this.e)) {
                    i++;
                    fRound += this.c;
                }
                int[] iArr = new int[i];
                float f = this.d;
                for (int i2 = 0; i2 < i; i2++) {
                    iArr[i2] = Math.round(f);
                    f += this.c;
                }
                this.f = b(iArr);
            }
            this.b = true;
        } else {
            this.b = false;
        }
        return this.b;
    }

    public final boolean h() {
        boolean z = this.f.length > 0;
        this.g = z;
        if (z) {
            this.a = 1;
            int[] iArr = this.f;
            this.d = iArr[0];
            this.e = iArr[r0 - 1];
            this.c = -1.0f;
        }
        return this.g;
    }

    public final boolean i() {
        return !(this.i instanceof AbstractC0691e0);
    }

    public final void j(float f, float f2, float f3) {
        if (f <= 0.0f) {
            throw new IllegalArgumentException("Minimum auto-size text size (" + f + "px) is less or equal to (0px)");
        }
        if (f2 <= f) {
            throw new IllegalArgumentException("Maximum auto-size text size (" + f2 + "px) is less or equal to minimum auto-size text size (" + f + "px)");
        }
        if (f3 <= 0.0f) {
            throw new IllegalArgumentException("The auto-size step granularity (" + f3 + "px) is less or equal to (0px)");
        }
        this.a = 1;
        this.d = f;
        this.e = f2;
        this.c = f3;
        this.g = false;
    }
}
