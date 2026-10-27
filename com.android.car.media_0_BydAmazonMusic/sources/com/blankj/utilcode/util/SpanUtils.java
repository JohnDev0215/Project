package com.blankj.utilcode.util;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.CharacterStyle;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.LineHeightSpan;
import android.text.style.MaskFilterSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public final class SpanUtils {
    public static final int ALIGN_BASELINE = 1;
    public static final int ALIGN_BOTTOM = 0;
    public static final int ALIGN_CENTER = 2;
    public static final int ALIGN_TOP = 3;
    private static final int COLOR_DEFAULT = -16777217;
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");
    private int alignImage;
    private int alignLine;
    private Layout.Alignment alignment;
    private int backgroundColor;
    private float blurRadius;
    private int bulletColor;
    private int bulletGapWidth;
    private int bulletRadius;
    private ClickableSpan clickSpan;
    private int first;
    private int flag;
    private String fontFamily;
    private int fontSize;
    private int foregroundColor;
    private Bitmap imageBitmap;
    private Drawable imageDrawable;
    private int imageResourceId;
    private Uri imageUri;
    private boolean isBold;
    private boolean isBoldItalic;
    private boolean isCreated;
    private boolean isItalic;
    private boolean isStrikethrough;
    private boolean isSubscript;
    private boolean isSuperscript;
    private boolean isUnderline;
    private int lineHeight;
    private g mBuilder;
    private CharSequence mText;
    private TextView mTextView;
    private int mType;
    private final int mTypeCharSequence;
    private final int mTypeImage;
    private final int mTypeSpace;
    private float proportion;
    private int quoteColor;
    private int quoteGapWidth;
    private int rest;
    private Shader shader;
    private int shadowColor;
    private float shadowDx;
    private float shadowDy;
    private float shadowRadius;
    private int spaceColor;
    private int spaceSize;
    private Object[] spans;
    private int stripeWidth;
    private BlurMaskFilter.Blur style;
    private Typeface typeface;
    private String url;
    private int verticalAlign;
    private float xProportion;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Align {
    }

    @SuppressLint({"ParcelCreator"})
    public static class CustomTypefaceSpan extends TypefaceSpan {
        public final Typeface a;

        public /* synthetic */ CustomTypefaceSpan(Typeface typeface, a aVar) {
            this(typeface);
        }

        public final void a(Paint paint, Typeface typeface) {
            Typeface typeface2 = paint.getTypeface();
            int style = (typeface2 == null ? 0 : typeface2.getStyle()) & (~typeface.getStyle());
            if ((style & 1) != 0) {
                paint.setFakeBoldText(true);
            }
            if ((style & 2) != 0) {
                paint.setTextSkewX(-0.25f);
            }
            paint.getShader();
            paint.setTypeface(typeface);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.CharacterStyle
        public final void updateDrawState(TextPaint textPaint) {
            a(textPaint, this.a);
        }

        @Override // android.text.style.TypefaceSpan, android.text.style.MetricAffectingSpan
        public final void updateMeasureState(TextPaint textPaint) {
            a(textPaint, this.a);
        }

        private CustomTypefaceSpan(Typeface typeface) {
            super("");
            this.a = typeface;
        }
    }

    public class a extends ClickableSpan {
        public final /* synthetic */ int a;
        public final /* synthetic */ boolean b;
        public final /* synthetic */ View.OnClickListener c;

        public a(SpanUtils spanUtils, int i, boolean z, View.OnClickListener onClickListener) {
            this.a = i;
            this.b = z;
            this.c = onClickListener;
        }

        @Override // android.text.style.ClickableSpan
        public final void onClick(@NonNull View view) {
            View.OnClickListener onClickListener = this.c;
            if (onClickListener != null) {
                onClickListener.onClick(view);
            }
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public final void updateDrawState(@NonNull TextPaint textPaint) {
            textPaint.setColor(this.a);
            textPaint.setUnderlineText(this.b);
        }
    }

    public static class b implements LeadingMarginSpan {
        public final int a;
        public final int b;
        public final int c;
        public Path d;

        public /* synthetic */ b(int i, int i2, int i3, a aVar) {
            this(i, i2, i3);
        }

        @Override // android.text.style.LeadingMarginSpan
        public final void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            if (((Spanned) charSequence).getSpanStart(this) == i6) {
                Paint.Style style = paint.getStyle();
                int color = paint.getColor();
                paint.setColor(this.a);
                paint.setStyle(Paint.Style.FILL);
                if (canvas.isHardwareAccelerated()) {
                    if (this.d == null) {
                        Path path = new Path();
                        this.d = path;
                        path.addCircle(0.0f, 0.0f, this.b, Path.Direction.CW);
                    }
                    canvas.save();
                    canvas.translate((i2 * this.b) + i, (i3 + i5) / 2.0f);
                    canvas.drawPath(this.d, paint);
                    canvas.restore();
                } else {
                    canvas.drawCircle((i2 * r1) + i, (i3 + i5) / 2.0f, this.b, paint);
                }
                paint.setColor(color);
                paint.setStyle(style);
            }
        }

        @Override // android.text.style.LeadingMarginSpan
        public final int getLeadingMargin(boolean z) {
            return (this.b * 2) + this.c;
        }

        private b(int i, int i2, int i3) {
            this.d = null;
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public static abstract class c extends ReplacementSpan {
        public final int a;
        public WeakReference<Drawable> b;

        public /* synthetic */ c(int i, a aVar) {
            this(i);
        }

        public final Drawable a() {
            WeakReference<Drawable> weakReference = this.b;
            Drawable drawable = weakReference != null ? weakReference.get() : null;
            if (drawable != null) {
                return drawable;
            }
            Drawable drawableB = b();
            this.b = new WeakReference<>(drawableB);
            return drawableB;
        }

        public abstract Drawable b();

        @Override // android.text.style.ReplacementSpan
        public final void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            int iHeight;
            float fHeight;
            Drawable drawableA = a();
            Rect bounds = drawableA.getBounds();
            canvas.save();
            if (bounds.height() < i5 - i3) {
                int i6 = this.a;
                if (i6 == 3) {
                    fHeight = i3;
                } else {
                    if (i6 == 2) {
                        iHeight = ((i5 + i3) - bounds.height()) / 2;
                    } else if (i6 == 1) {
                        fHeight = i4 - bounds.height();
                    } else {
                        iHeight = i5 - bounds.height();
                    }
                    fHeight = iHeight;
                }
                canvas.translate(f, fHeight);
            } else {
                canvas.translate(f, i3);
            }
            drawableA.draw(canvas);
            canvas.restore();
        }

        @Override // android.text.style.ReplacementSpan
        public final int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            int i3;
            Rect bounds = a().getBounds();
            if (fontMetricsInt != null && (i3 = fontMetricsInt.bottom - fontMetricsInt.top) < bounds.height()) {
                int i4 = this.a;
                if (i4 == 3) {
                    fontMetricsInt.top = fontMetricsInt.top;
                    fontMetricsInt.bottom = bounds.height() + fontMetricsInt.top;
                } else if (i4 == 2) {
                    int i5 = i3 / 4;
                    fontMetricsInt.top = ((-bounds.height()) / 2) - i5;
                    fontMetricsInt.bottom = (bounds.height() / 2) - i5;
                } else {
                    int i6 = -bounds.height();
                    int i7 = fontMetricsInt.bottom;
                    fontMetricsInt.top = i6 + i7;
                    fontMetricsInt.bottom = i7;
                }
                fontMetricsInt.ascent = fontMetricsInt.top;
                fontMetricsInt.descent = fontMetricsInt.bottom;
            }
            return bounds.right;
        }

        private c() {
            this.a = 0;
        }

        private c(int i) {
            this.a = i;
        }
    }

    public static class d extends c {
        public Drawable c;
        public Uri d;
        public int e;

        public /* synthetic */ d(int i, int i2, a aVar) {
            this(i, i2);
        }

        @Override // com.blankj.utilcode.util.SpanUtils.c
        public final Drawable b() {
            InputStream inputStreamOpenInputStream;
            BitmapDrawable bitmapDrawable;
            Drawable drawable;
            Drawable drawable2 = this.c;
            if (drawable2 != null) {
                return drawable2;
            }
            BitmapDrawable bitmapDrawable2 = null;
            if (this.d == null) {
                try {
                    drawable = ContextCompat.getDrawable(Utils.getApp(), this.e);
                } catch (Exception unused) {
                    drawable = null;
                }
                try {
                    drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    return drawable;
                } catch (Exception unused2) {
                    StringBuilder sbK = spi.c.k("Unable to find resource: ");
                    sbK.append(this.e);
                    Log.e("sms", sbK.toString());
                    return drawable;
                }
            }
            try {
                inputStreamOpenInputStream = Utils.getApp().getContentResolver().openInputStream(this.d);
                bitmapDrawable = new BitmapDrawable(Utils.getApp().getResources(), BitmapFactory.decodeStream(inputStreamOpenInputStream));
            } catch (Exception e) {
                e = e;
            }
            try {
                bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), bitmapDrawable.getIntrinsicHeight());
                if (inputStreamOpenInputStream != null) {
                    inputStreamOpenInputStream.close();
                }
                return bitmapDrawable;
            } catch (Exception e2) {
                e = e2;
                bitmapDrawable2 = bitmapDrawable;
                StringBuilder sbK2 = spi.c.k("Failed to loaded content ");
                sbK2.append(this.d);
                Log.e("sms", sbK2.toString(), e);
                return bitmapDrawable2;
            }
        }

        public /* synthetic */ d(Bitmap bitmap, int i, a aVar) {
            this(bitmap, i);
        }

        public /* synthetic */ d(Drawable drawable, int i, a aVar) {
            this(drawable, i);
        }

        public /* synthetic */ d(Uri uri, int i, a aVar) {
            this(uri, i);
        }

        private d(Bitmap bitmap, int i) {
            super(i, null);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(Utils.getApp().getResources(), bitmap);
            this.c = bitmapDrawable;
            bitmapDrawable.setBounds(0, 0, bitmapDrawable.getIntrinsicWidth(), this.c.getIntrinsicHeight());
        }

        private d(Drawable drawable, int i) {
            super(i, null);
            this.c = drawable;
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), this.c.getIntrinsicHeight());
        }

        private d(Uri uri, int i) {
            super(i, null);
            this.d = uri;
        }

        private d(@DrawableRes int i, int i2) {
            super(i2, null);
            this.e = i;
        }
    }

    public static class e implements LineHeightSpan {
        public static Paint.FontMetricsInt c;
        public final int a;
        public final int b;

        public e(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // android.text.style.LineHeightSpan
        public final void chooseHeight(CharSequence charSequence, int i, int i2, int i3, int i4, Paint.FontMetricsInt fontMetricsInt) {
            Paint.FontMetricsInt fontMetricsInt2 = c;
            if (fontMetricsInt2 == null) {
                Paint.FontMetricsInt fontMetricsInt3 = new Paint.FontMetricsInt();
                c = fontMetricsInt3;
                fontMetricsInt3.top = fontMetricsInt.top;
                fontMetricsInt3.ascent = fontMetricsInt.ascent;
                fontMetricsInt3.descent = fontMetricsInt.descent;
                fontMetricsInt3.bottom = fontMetricsInt.bottom;
                fontMetricsInt3.leading = fontMetricsInt.leading;
            } else {
                fontMetricsInt.top = fontMetricsInt2.top;
                fontMetricsInt.ascent = fontMetricsInt2.ascent;
                fontMetricsInt.descent = fontMetricsInt2.descent;
                fontMetricsInt.bottom = fontMetricsInt2.bottom;
                fontMetricsInt.leading = fontMetricsInt2.leading;
            }
            int i5 = this.a;
            int i6 = fontMetricsInt.descent;
            int i7 = fontMetricsInt.ascent;
            int i8 = i5 - (((i4 + i6) - i7) - i3);
            if (i8 > 0) {
                int i9 = this.b;
                if (i9 == 3) {
                    fontMetricsInt.descent = i6 + i8;
                } else if (i9 == 2) {
                    int i10 = i8 / 2;
                    fontMetricsInt.descent = i6 + i10;
                    fontMetricsInt.ascent = i7 - i10;
                } else {
                    fontMetricsInt.ascent = i7 - i8;
                }
            }
            int i11 = fontMetricsInt.bottom;
            int i12 = fontMetricsInt.top;
            int i13 = i5 - (((i4 + i11) - i12) - i3);
            if (i13 > 0) {
                int i14 = this.b;
                if (i14 == 3) {
                    fontMetricsInt.bottom = i11 + i13;
                } else if (i14 == 2) {
                    int i15 = i13 / 2;
                    fontMetricsInt.bottom = i11 + i15;
                    fontMetricsInt.top = i12 - i15;
                } else {
                    fontMetricsInt.top = i12 - i13;
                }
            }
            if (i2 == ((Spanned) charSequence).getSpanEnd(this)) {
                c = null;
            }
        }
    }

    public static class f implements LeadingMarginSpan {
        public final int a;
        public final int b;
        public final int c;

        public /* synthetic */ f(int i, int i2, int i3, a aVar) {
            this(i, i2, i3);
        }

        @Override // android.text.style.LeadingMarginSpan
        public final void drawLeadingMargin(Canvas canvas, Paint paint, int i, int i2, int i3, int i4, int i5, CharSequence charSequence, int i6, int i7, boolean z, Layout layout) {
            Paint.Style style = paint.getStyle();
            int color = paint.getColor();
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(this.a);
            canvas.drawRect(i, i3, (this.b * i2) + i, i5, paint);
            paint.setStyle(style);
            paint.setColor(color);
        }

        @Override // android.text.style.LeadingMarginSpan
        public final int getLeadingMargin(boolean z) {
            return this.b + this.c;
        }

        private f(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    public static class g extends SpannableStringBuilder implements Serializable {
        private static final long serialVersionUID = 4909567650765875771L;

        private g() {
        }

        public /* synthetic */ g(a aVar) {
            this();
        }
    }

    public static class h extends CharacterStyle implements UpdateAppearance {
        public Shader a;

        public /* synthetic */ h(Shader shader, a aVar) {
            this(shader);
        }

        @Override // android.text.style.CharacterStyle
        public final void updateDrawState(TextPaint textPaint) {
            textPaint.setShader(this.a);
        }

        private h(Shader shader) {
            this.a = shader;
        }
    }

    public static class i extends CharacterStyle implements UpdateAppearance {
        public float a;
        public float b;
        public float c;
        public int d;

        public /* synthetic */ i(float f, float f2, float f3, int i, a aVar) {
            this(f, f2, f3, i);
        }

        @Override // android.text.style.CharacterStyle
        public final void updateDrawState(TextPaint textPaint) {
            textPaint.setShadowLayer(this.a, this.b, this.c, this.d);
        }

        private i(float f, float f2, float f3, int i) {
            this.a = f;
            this.b = f2;
            this.c = f3;
            this.d = i;
        }
    }

    public static class j extends ReplacementSpan {
        public final int a;
        public final Paint b;

        public /* synthetic */ j(int i, int i2, a aVar) {
            this(i, i2);
        }

        @Override // android.text.style.ReplacementSpan
        public final void draw(@NonNull Canvas canvas, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            canvas.drawRect(f, i3, f + this.a, i5, this.b);
        }

        @Override // android.text.style.ReplacementSpan
        public final int getSize(@NonNull Paint paint, CharSequence charSequence, @IntRange(from = 0) int i, @IntRange(from = 0) int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            return this.a;
        }

        private j(int i) {
            this(i, 0);
        }

        private j(int i, int i2) {
            Paint paint = new Paint();
            this.b = paint;
            this.a = i;
            paint.setColor(i2);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    public static class k extends ReplacementSpan {
        public k(int i) {
        }

        @Override // android.text.style.ReplacementSpan
        public final void draw(@NonNull Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, @NonNull Paint paint) {
            CharSequence charSequenceSubSequence = charSequence.subSequence(i, i2);
            Paint.FontMetricsInt fontMetricsInt = paint.getFontMetricsInt();
            canvas.drawText(charSequenceSubSequence.toString(), f, i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2)), paint);
        }

        @Override // android.text.style.ReplacementSpan
        public final int getSize(@NonNull Paint paint, CharSequence charSequence, int i, int i2, @Nullable Paint.FontMetricsInt fontMetricsInt) {
            return (int) paint.measureText(charSequence.subSequence(i, i2).toString());
        }
    }

    private SpanUtils(TextView textView) {
        this();
        this.mTextView = textView;
    }

    private void apply(int i2) {
        applyLast();
        this.mType = i2;
    }

    private void applyLast() {
        if (this.isCreated) {
            return;
        }
        int i2 = this.mType;
        if (i2 == 0) {
            updateCharCharSequence();
        } else if (i2 == 1) {
            updateImage();
        } else if (i2 == 2) {
            updateSpace();
        }
        setDefault();
    }

    private void setDefault() {
        this.flag = 33;
        this.foregroundColor = COLOR_DEFAULT;
        this.backgroundColor = COLOR_DEFAULT;
        this.lineHeight = -1;
        this.quoteColor = COLOR_DEFAULT;
        this.first = -1;
        this.bulletColor = COLOR_DEFAULT;
        this.fontSize = -1;
        this.proportion = -1.0f;
        this.xProportion = -1.0f;
        this.isStrikethrough = false;
        this.isUnderline = false;
        this.isSuperscript = false;
        this.isSubscript = false;
        this.isBold = false;
        this.isItalic = false;
        this.isBoldItalic = false;
        this.fontFamily = null;
        this.typeface = null;
        this.alignment = null;
        this.verticalAlign = -1;
        this.clickSpan = null;
        this.url = null;
        this.blurRadius = -1.0f;
        this.shader = null;
        this.shadowRadius = -1.0f;
        this.spans = null;
        this.imageBitmap = null;
        this.imageDrawable = null;
        this.imageUri = null;
        this.imageResourceId = -1;
        this.spaceSize = -1;
    }

    private void setMovementMethodIfNeed() {
        TextView textView = this.mTextView;
        if (textView == null || textView.getMovementMethod() != null) {
            return;
        }
        this.mTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void updateCharCharSequence() {
        if (this.mText.length() == 0) {
            return;
        }
        int length = this.mBuilder.length();
        if (length == 0 && this.lineHeight != -1) {
            this.mBuilder.append((CharSequence) Character.toString((char) 2)).append((CharSequence) "\n").setSpan(new AbsoluteSizeSpan(0), 0, 2, 33);
            length = 2;
        }
        this.mBuilder.append(this.mText);
        int length2 = this.mBuilder.length();
        if (this.verticalAlign != -1) {
            this.mBuilder.setSpan(new k(this.verticalAlign), length, length2, this.flag);
        }
        if (this.foregroundColor != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), length, length2, this.flag);
        }
        if (this.backgroundColor != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), length, length2, this.flag);
        }
        if (this.first != -1) {
            this.mBuilder.setSpan(new LeadingMarginSpan.Standard(this.first, this.rest), length, length2, this.flag);
        }
        int i2 = this.quoteColor;
        a aVar = null;
        if (i2 != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new f(i2, this.stripeWidth, this.quoteGapWidth, aVar), length, length2, this.flag);
        }
        int i3 = this.bulletColor;
        if (i3 != COLOR_DEFAULT) {
            this.mBuilder.setSpan(new b(i3, this.bulletRadius, this.bulletGapWidth, aVar), length, length2, this.flag);
        }
        if (this.fontSize != -1) {
            this.mBuilder.setSpan(new AbsoluteSizeSpan(this.fontSize, false), length, length2, this.flag);
        }
        if (this.proportion != -1.0f) {
            this.mBuilder.setSpan(new RelativeSizeSpan(this.proportion), length, length2, this.flag);
        }
        if (this.xProportion != -1.0f) {
            this.mBuilder.setSpan(new ScaleXSpan(this.xProportion), length, length2, this.flag);
        }
        int i4 = this.lineHeight;
        if (i4 != -1) {
            this.mBuilder.setSpan(new e(i4, this.alignLine), length, length2, this.flag);
        }
        if (this.isStrikethrough) {
            this.mBuilder.setSpan(new StrikethroughSpan(), length, length2, this.flag);
        }
        if (this.isUnderline) {
            this.mBuilder.setSpan(new UnderlineSpan(), length, length2, this.flag);
        }
        if (this.isSuperscript) {
            this.mBuilder.setSpan(new SuperscriptSpan(), length, length2, this.flag);
        }
        if (this.isSubscript) {
            this.mBuilder.setSpan(new SubscriptSpan(), length, length2, this.flag);
        }
        if (this.isBold) {
            this.mBuilder.setSpan(new StyleSpan(1), length, length2, this.flag);
        }
        if (this.isItalic) {
            this.mBuilder.setSpan(new StyleSpan(2), length, length2, this.flag);
        }
        if (this.isBoldItalic) {
            this.mBuilder.setSpan(new StyleSpan(3), length, length2, this.flag);
        }
        if (this.fontFamily != null) {
            this.mBuilder.setSpan(new TypefaceSpan(this.fontFamily), length, length2, this.flag);
        }
        if (this.typeface != null) {
            this.mBuilder.setSpan(new CustomTypefaceSpan(this.typeface, aVar), length, length2, this.flag);
        }
        if (this.alignment != null) {
            this.mBuilder.setSpan(new AlignmentSpan.Standard(this.alignment), length, length2, this.flag);
        }
        ClickableSpan clickableSpan = this.clickSpan;
        if (clickableSpan != null) {
            this.mBuilder.setSpan(clickableSpan, length, length2, this.flag);
        }
        if (this.url != null) {
            this.mBuilder.setSpan(new URLSpan(this.url), length, length2, this.flag);
        }
        if (this.blurRadius != -1.0f) {
            this.mBuilder.setSpan(new MaskFilterSpan(new BlurMaskFilter(this.blurRadius, this.style)), length, length2, this.flag);
        }
        if (this.shader != null) {
            this.mBuilder.setSpan(new h(this.shader, aVar), length, length2, this.flag);
        }
        if (this.shadowRadius != -1.0f) {
            this.mBuilder.setSpan(new i(this.shadowRadius, this.shadowDx, this.shadowDy, this.shadowColor, null), length, length2, this.flag);
        }
        Object[] objArr = this.spans;
        if (objArr != null) {
            for (Object obj : objArr) {
                this.mBuilder.setSpan(obj, length, length2, this.flag);
            }
        }
    }

    private void updateImage() {
        int length = this.mBuilder.length();
        this.mText = "<img>";
        updateCharCharSequence();
        int length2 = this.mBuilder.length();
        a aVar = null;
        if (this.imageBitmap != null) {
            this.mBuilder.setSpan(new d(this.imageBitmap, this.alignImage, aVar), length, length2, this.flag);
            return;
        }
        if (this.imageDrawable != null) {
            this.mBuilder.setSpan(new d(this.imageDrawable, this.alignImage, aVar), length, length2, this.flag);
        } else if (this.imageUri != null) {
            this.mBuilder.setSpan(new d(this.imageUri, this.alignImage, aVar), length, length2, this.flag);
        } else if (this.imageResourceId != -1) {
            this.mBuilder.setSpan(new d(this.imageResourceId, this.alignImage, aVar), length, length2, this.flag);
        }
    }

    private void updateSpace() {
        int length = this.mBuilder.length();
        this.mText = "< >";
        updateCharCharSequence();
        this.mBuilder.setSpan(new j(this.spaceSize, this.spaceColor, null), length, this.mBuilder.length(), this.flag);
    }

    public static SpanUtils with(TextView textView) {
        return new SpanUtils(textView);
    }

    public SpanUtils append(@NonNull CharSequence charSequence) {
        apply(0);
        this.mText = charSequence;
        return this;
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap) {
        return appendImage(bitmap, 0);
    }

    public SpanUtils appendLine() {
        apply(0);
        this.mText = LINE_SEPARATOR;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i2) {
        return appendSpace(i2, 0);
    }

    public SpannableStringBuilder create() {
        applyLast();
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(this.mBuilder);
        }
        this.isCreated = true;
        return this.mBuilder;
    }

    public SpannableStringBuilder get() {
        return this.mBuilder;
    }

    public SpanUtils setBackgroundColor(@ColorInt int i2) {
        this.backgroundColor = i2;
        return this;
    }

    public SpanUtils setBlur(@FloatRange(from = 0.0d, fromInclusive = false) float f2, BlurMaskFilter.Blur blur) {
        this.blurRadius = f2;
        this.style = blur;
        return this;
    }

    public SpanUtils setBold() {
        this.isBold = true;
        return this;
    }

    public SpanUtils setBoldItalic() {
        this.isBoldItalic = true;
        return this;
    }

    public SpanUtils setBullet(@IntRange(from = 0) int i2) {
        return setBullet(0, 3, i2);
    }

    public SpanUtils setClickSpan(@NonNull ClickableSpan clickableSpan) {
        setMovementMethodIfNeed();
        this.clickSpan = clickableSpan;
        return this;
    }

    public SpanUtils setFlag(int i2) {
        this.flag = i2;
        return this;
    }

    public SpanUtils setFontFamily(@NonNull String str) {
        this.fontFamily = str;
        return this;
    }

    public SpanUtils setFontProportion(float f2) {
        this.proportion = f2;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i2) {
        return setFontSize(i2, false);
    }

    public SpanUtils setFontXProportion(float f2) {
        this.xProportion = f2;
        return this;
    }

    public SpanUtils setForegroundColor(@ColorInt int i2) {
        this.foregroundColor = i2;
        return this;
    }

    public SpanUtils setHorizontalAlign(@NonNull Layout.Alignment alignment) {
        this.alignment = alignment;
        return this;
    }

    public SpanUtils setItalic() {
        this.isItalic = true;
        return this;
    }

    public SpanUtils setLeadingMargin(@IntRange(from = 0) int i2, @IntRange(from = 0) int i3) {
        this.first = i2;
        this.rest = i3;
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i2) {
        return setLineHeight(i2, 2);
    }

    public SpanUtils setQuoteColor(@ColorInt int i2) {
        return setQuoteColor(i2, 2, 2);
    }

    public SpanUtils setShader(@NonNull Shader shader) {
        this.shader = shader;
        return this;
    }

    public SpanUtils setShadow(@FloatRange(from = 0.0d, fromInclusive = false) float f2, float f3, float f4, int i2) {
        this.shadowRadius = f2;
        this.shadowDx = f3;
        this.shadowDy = f4;
        this.shadowColor = i2;
        return this;
    }

    public SpanUtils setSpans(@NonNull Object... objArr) {
        if (objArr.length > 0) {
            this.spans = objArr;
        }
        return this;
    }

    public SpanUtils setStrikethrough() {
        this.isStrikethrough = true;
        return this;
    }

    public SpanUtils setSubscript() {
        this.isSubscript = true;
        return this;
    }

    public SpanUtils setSuperscript() {
        this.isSuperscript = true;
        return this;
    }

    public SpanUtils setTypeface(@NonNull Typeface typeface) {
        this.typeface = typeface;
        return this;
    }

    public SpanUtils setUnderline() {
        this.isUnderline = true;
        return this;
    }

    public SpanUtils setUrl(@NonNull String str) {
        setMovementMethodIfNeed();
        this.url = str;
        return this;
    }

    public SpanUtils setVerticalAlign(int i2) {
        this.verticalAlign = i2;
        return this;
    }

    public SpanUtils appendImage(@NonNull Bitmap bitmap, int i2) {
        apply(1);
        this.imageBitmap = bitmap;
        this.alignImage = i2;
        return this;
    }

    public SpanUtils appendSpace(@IntRange(from = 0) int i2, @ColorInt int i3) {
        apply(2);
        this.spaceSize = i2;
        this.spaceColor = i3;
        return this;
    }

    public SpanUtils setBullet(@ColorInt int i2, @IntRange(from = 0) int i3, @IntRange(from = 0) int i4) {
        this.bulletColor = i2;
        this.bulletRadius = i3;
        this.bulletGapWidth = i4;
        return this;
    }

    public SpanUtils setFontSize(@IntRange(from = 0) int i2, boolean z) {
        if (z) {
            this.fontSize = (int) ((i2 * Resources.getSystem().getDisplayMetrics().scaledDensity) + 0.5f);
        } else {
            this.fontSize = i2;
        }
        return this;
    }

    public SpanUtils setLineHeight(@IntRange(from = 0) int i2, int i3) {
        this.lineHeight = i2;
        this.alignLine = i3;
        return this;
    }

    public SpanUtils setQuoteColor(@ColorInt int i2, @IntRange(from = 1) int i3, @IntRange(from = 0) int i4) {
        this.quoteColor = i2;
        this.stripeWidth = i3;
        this.quoteGapWidth = i4;
        return this;
    }

    public SpanUtils() {
        this.mTypeCharSequence = 0;
        this.mTypeImage = 1;
        this.mTypeSpace = 2;
        this.mBuilder = new g(null);
        this.mText = "";
        this.mType = -1;
        setDefault();
    }

    public SpanUtils appendLine(@NonNull CharSequence charSequence) {
        apply(0);
        this.mText = ((Object) charSequence) + LINE_SEPARATOR;
        return this;
    }

    public SpanUtils setClickSpan(@ColorInt int i2, boolean z, View.OnClickListener onClickListener) {
        setMovementMethodIfNeed();
        this.clickSpan = new a(this, i2, z, onClickListener);
        return this;
    }

    public SpanUtils appendImage(@NonNull Drawable drawable) {
        return appendImage(drawable, 0);
    }

    public SpanUtils appendImage(@NonNull Drawable drawable, int i2) {
        apply(1);
        this.imageDrawable = drawable;
        this.alignImage = i2;
        return this;
    }

    public SpanUtils appendImage(@NonNull Uri uri) {
        return appendImage(uri, 0);
    }

    public SpanUtils appendImage(@NonNull Uri uri, int i2) {
        apply(1);
        this.imageUri = uri;
        this.alignImage = i2;
        return this;
    }

    public SpanUtils appendImage(@DrawableRes int i2) {
        return appendImage(i2, 0);
    }

    public SpanUtils appendImage(@DrawableRes int i2, int i3) {
        apply(1);
        this.imageResourceId = i2;
        this.alignImage = i3;
        return this;
    }
}
