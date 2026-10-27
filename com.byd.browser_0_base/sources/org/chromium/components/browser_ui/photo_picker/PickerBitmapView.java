package org.chromium.components.browser_ui.photo_picker;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.SpannableString;
import android.text.style.TextAppearanceSpan;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import defpackage.b12;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.chromium.components.browser_ui.photo_picker.PickerVideoPlayer;
import org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase;

/* JADX INFO: loaded from: classes2.dex */
public class PickerBitmapView extends SelectableItemViewBase<b12> {
    public static Animation.AnimationListener E;
    public ImageView A;
    public TextView B;
    public boolean C;
    public boolean D;
    public final Context m;
    public PickerCategoryView n;
    public org.chromium.components.browser_ui.widget.selectable_list.a<b12> o;
    public b12 p;
    public ImageView q;
    public float r;
    public ViewGroup s;
    public TextView t;
    public ImageView u;
    public ImageView v;
    public ImageView w;
    public ImageView x;
    public ImageView y;
    public View z;

    public PickerBitmapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.r = -1.0f;
        this.m = context;
    }

    public static void setAnimationListenerForTest(Animation.AnimationListener animationListener) {
        E = animationListener;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, org.chromium.components.browser_ui.widget.selectable_list.a.InterfaceC0128a
    public final void f(ArrayList arrayList) {
        b12 b12Var = this.p;
        if (b12Var == null) {
            return;
        }
        u(arrayList.contains(b12Var) != super.isChecked());
        super.f(arrayList);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final void m() {
        b12 b12Var = this.p;
        if (b12Var == null) {
            return;
        }
        int i = b12Var.g;
        if (i == 2) {
            this.n.d(3, null, 3);
            return;
        }
        if (i == 1) {
            return;
        }
        onLongClick(this);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase
    public final boolean n(b12 b12Var) {
        b12 b12Var2 = b12Var;
        int i = this.p.g;
        if (i == 2) {
            return false;
        }
        if ((i == 1) || this.n.x) {
            return false;
        }
        return super.n(b12Var2);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View.OnClickListener
    public final void onClick(View view) {
        if (view != this.u && view != this.v) {
            super.onClick(view);
            return;
        }
        PickerCategoryView pickerCategoryView = this.n;
        Uri uri = this.p.e;
        h hVar = pickerCategoryView.e;
        if (hVar == null) {
            return;
        }
        Window window = hVar.getWindow();
        final PickerVideoPlayer pickerVideoPlayer = pickerCategoryView.G;
        pickerVideoPlayer.e = window;
        pickerVideoPlayer.g(true);
        SpannableString spannableString = new SpannableString(uri.toString());
        spannableString.setSpan(new TextAppearanceSpan(pickerVideoPlayer.f, R$style.TextAppearance_TextMedium_Secondary), 0, uri.getScheme().length(), 33);
        pickerVideoPlayer.h.setText(spannableString, TextView.BufferType.SPANNABLE);
        pickerVideoPlayer.setVisibility(0);
        VideoView videoView = pickerVideoPlayer.i;
        videoView.setVisibility(0);
        videoView.setVideoURI(uri);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: g12
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer) {
                final PickerVideoPlayer pickerVideoPlayer2 = pickerVideoPlayer;
                pickerVideoPlayer2.j = mediaPlayer;
                pickerVideoPlayer2.f();
                pickerVideoPlayer2.j.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() { // from class: h12
                    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
                    public final void onVideoSizeChanged(MediaPlayer mediaPlayer2, int i, int i2) {
                        boolean z = PickerVideoPlayer.E;
                        PickerVideoPlayer pickerVideoPlayer3 = pickerVideoPlayer2;
                        pickerVideoPlayer3.a();
                        pickerVideoPlayer3.k.setVisibility(0);
                    }
                });
            }
        });
        videoView.setOnCompletionListener(new j(pickerVideoPlayer));
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.D = false;
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.w = (ImageView) findViewById(R$id.scrim);
        this.q = (ImageView) findViewById(R$id.bitmap_view);
        this.x = (ImageView) findViewById(R$id.selected);
        this.y = (ImageView) findViewById(R$id.unselected);
        this.z = findViewById(R$id.special_tile);
        this.A = (ImageView) findViewById(R$id.special_tile_icon);
        this.B = (TextView) findViewById(R$id.special_tile_label);
        this.s = (ViewGroup) findViewById(R$id.video_controls_small);
        this.t = (TextView) findViewById(R$id.video_duration);
        ImageView imageView = (ImageView) findViewById(R$id.small_play_button);
        this.u = imageView;
        imageView.setOnClickListener(this);
        ImageView imageView2 = (ImageView) findViewById(R$id.large_play_button);
        this.v = imageView2;
        imageView2.setOnClickListener(this);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        if (s()) {
            accessibilityNodeInfo.setCheckable(true);
            accessibilityNodeInfo.setChecked(isChecked());
            StringBuilder sb = new StringBuilder();
            String path = this.p.e.getPath();
            int iLastIndexOf = path.lastIndexOf("/");
            if (iLastIndexOf != -1) {
                path = path.substring(iLastIndexOf + 1, path.length());
            }
            sb.append(path);
            sb.append(" ");
            b12 b12Var = this.p;
            b12Var.getClass();
            sb.append(DateFormat.getDateTimeInstance().format(new Date(b12Var.f)));
            accessibilityNodeInfo.setText(sb.toString());
        }
    }

    @Override // org.chromium.ui.widget.OptimizedFrameLayout, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        PickerCategoryView pickerCategoryView = this.n;
        if (pickerCategoryView == null) {
            return;
        }
        if (pickerCategoryView.w) {
            setMeasuredDimension(this.n.getImageWidth(), s() ? (int) (this.r * this.n.getImageWidth()) : this.n.getSpecialTileHeight());
        } else {
            setMeasuredDimension(pickerCategoryView.getImageWidth(), this.n.getImageWidth());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0061  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void q(defpackage.b12 r6, java.util.List<android.graphics.Bitmap> r7, java.lang.String r8, boolean r9, float r10) {
        /*
            r5 = this;
            r0 = 0
            r5.p = r0
            android.widget.ImageView r1 = r5.q
            r1.setImageBitmap(r0)
            android.widget.ImageView r1 = r5.v
            r2 = 8
            r1.setVisibility(r2)
            android.widget.TextView r1 = r5.t
            java.lang.String r3 = ""
            r1.setText(r3)
            android.view.ViewGroup r1 = r5.s
            r1.setVisibility(r2)
            android.widget.ImageView r1 = r5.y
            r1.setVisibility(r2)
            android.widget.ImageView r1 = r5.x
            r1.setVisibility(r2)
            android.widget.ImageView r1 = r5.w
            r1.setVisibility(r2)
            android.view.View r1 = r5.z
            r1.setVisibility(r2)
            android.widget.ImageView r1 = r5.A
            r1.setVisibility(r2)
            android.widget.TextView r1 = r5.B
            r1.setVisibility(r2)
            r1 = 0
            r5.D = r1
            r2 = 1
            r5.setEnabled(r2)
            r5.p = r6
            r5.setItem(r6)
            b12 r6 = r5.p
            int r6 = r6.g
            if (r6 != r2) goto L4d
            r3 = r2
            goto L4e
        L4d:
            r3 = r1
        L4e:
            r4 = 2
            if (r3 != 0) goto L61
            if (r6 != r4) goto L55
            r6 = r2
            goto L56
        L55:
            r6 = r1
        L56:
            if (r6 == 0) goto L59
            goto L61
        L59:
            r5.t(r7, r8, r10)
            r6 = r9 ^ 1
            r5.C = r6
            goto Lc1
        L61:
            android.content.Context r6 = r5.m
            android.content.res.Resources r7 = r6.getResources()
            b12 r8 = r5.p
            int r8 = r8.g
            if (r8 != r2) goto L6f
            r9 = r2
            goto L70
        L6f:
            r9 = r1
        L70:
            if (r9 == 0) goto L7f
            int r8 = org.chromium.components.browser_ui.photo_picker.R$drawable.ic_photo_camera_grey
            android.content.res.Resources$Theme r9 = r6.getTheme()
            el3 r0 = defpackage.re3.a(r7, r8, r9)
            int r7 = org.chromium.components.browser_ui.photo_picker.R$string.photo_picker_camera
            goto L94
        L7f:
            if (r8 != r4) goto L83
            r8 = r2
            goto L84
        L83:
            r8 = r1
        L84:
            if (r8 == 0) goto L93
            int r8 = org.chromium.components.browser_ui.photo_picker.R$drawable.ic_collections_grey
            android.content.res.Resources$Theme r9 = r6.getTheme()
            el3 r0 = defpackage.re3.a(r7, r8, r9)
            int r7 = org.chromium.components.browser_ui.photo_picker.R$string.photo_picker_browse
            goto L94
        L93:
            r7 = r1
        L94:
            android.widget.ImageView r8 = r5.A
            r8.setImageDrawable(r0)
            android.widget.ImageView r8 = r5.A
            int r9 = org.chromium.components.browser_ui.photo_picker.R$color.default_icon_color_secondary_tint_list
            android.content.res.ColorStateList r6 = defpackage.bz.b(r6, r9)
            r8.setImageTintList(r6)
            android.widget.ImageView r6 = r5.A
            android.graphics.PorterDuff$Mode r8 = android.graphics.PorterDuff.Mode.SRC_IN
            r6.setImageTintMode(r8)
            android.widget.TextView r6 = r5.B
            r6.setText(r7)
            android.view.View r6 = r5.z
            r6.setVisibility(r1)
            android.widget.ImageView r6 = r5.A
            r6.setVisibility(r1)
            android.widget.TextView r6 = r5.B
            r6.setVisibility(r1)
            r5.C = r2
        Lc1:
            r5.u(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.components.browser_ui.photo_picker.PickerBitmapView.q(b12, java.util.List, java.lang.String, boolean, float):void");
    }

    public final boolean s() {
        int i = this.p.g;
        return i == 0 || i == 3;
    }

    public void setCategoryView(PickerCategoryView pickerCategoryView) {
        this.n = pickerCategoryView;
        org.chromium.components.browser_ui.widget.selectable_list.a<b12> selectionDelegate = pickerCategoryView.getSelectionDelegate();
        this.o = selectionDelegate;
        setSelectionDelegate(selectionDelegate);
    }

    @Override // org.chromium.components.browser_ui.widget.selectable_list.SelectableItemViewBase, android.widget.Checkable
    public void setChecked(boolean z) {
        if (s()) {
            super.setChecked(z);
            u(false);
        }
    }

    public final boolean t(List<Bitmap> list, String str, float f) {
        if (str == null || list.size() == 1) {
            this.q.setImageBitmap(list == null ? null : list.get(0));
        } else {
            AnimationDrawable animationDrawable = new AnimationDrawable();
            for (int i = 0; i < list.size(); i++) {
                animationDrawable.addFrame(new BitmapDrawable(this.m.getResources(), list.get(i)), 250);
            }
            animationDrawable.setOneShot(false);
            this.q.setImageDrawable(animationDrawable);
            animationDrawable.start();
        }
        this.t.setText(str);
        if (list != null && list.size() > 0) {
            this.r = f;
        }
        boolean z = !this.C;
        this.C = true;
        u(false);
        return z;
    }

    public final void u(boolean z) {
        int i;
        float f;
        float f2;
        float dimensionPixelSize;
        float f3;
        float f4;
        float f5;
        float f6;
        boolean z2 = !s();
        org.chromium.components.browser_ui.widget.selectable_list.a<b12> aVar = this.o;
        boolean z3 = aVar != null && aVar.e();
        Context context = this.m;
        context.getResources();
        if (z2) {
            i = R$color.photo_picker_special_tile_bg_color;
            boolean z4 = !z3;
            this.B.setEnabled(z4);
            this.A.setEnabled(z4);
            setEnabled(z4);
        } else {
            i = R$color.photo_picker_tile_bg_color;
        }
        int color = context.getColor(i);
        if (this.n.x && !z2) {
            color = 0;
        }
        setBackgroundColor(color);
        boolean zD = this.o.d(this.p);
        int i2 = 8;
        this.x.setVisibility((z2 || !zD) ? 8 : 0);
        boolean z5 = !z2 && !zD && this.C && (z3 || this.n.w) && this.n.i;
        this.y.setVisibility(z5 ? 0 : 8);
        this.w.setVisibility(z5 ? 0 : 8);
        boolean z6 = this.C && this.p.g == 3;
        this.s.setVisibility((!z6 || this.n.w) ? 8 : 0);
        ImageView imageView = this.v;
        if (z6 && this.n.w) {
            i2 = 0;
        }
        imageView.setVisibility(i2);
        if (z2 || !isAttachedToWindow()) {
            return;
        }
        boolean zD2 = this.o.d(this.p);
        PickerCategoryView pickerCategoryView = this.n;
        boolean z7 = pickerCategoryView.w;
        if (z7) {
            zD2 = false;
        }
        if (zD2 == this.D) {
            return;
        }
        this.D = zD2;
        float f7 = 1.0f;
        if (z7) {
            float imageWidth = pickerCategoryView.getImageWidth();
            f = 1.0f - ((0.07999998f * imageWidth) / (imageWidth * this.r));
            f2 = 0.92f;
        } else {
            f = 0.8f;
            f2 = 0.8f;
        }
        if (zD2) {
            dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.photo_picker_video_duration_offset);
            f5 = f;
            f3 = 1.0f;
            f6 = -dimensionPixelSize;
            f4 = f2;
        } else {
            dimensionPixelSize = 0.0f;
            f3 = f;
            f4 = 1.0f;
            f5 = 1.0f;
            f6 = 0.0f;
            f7 = f2;
        }
        ScaleAnimation scaleAnimation = new ScaleAnimation(f7, f4, f3, f5, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setDuration(z ? 100L : 0L);
        scaleAnimation.setFillAfter(true);
        Animation.AnimationListener animationListener = E;
        if (animationListener != null) {
            scaleAnimation.setAnimationListener(animationListener);
        }
        this.q.startAnimation(scaleAnimation);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.s, (Property<ViewGroup, Float>) View.TRANSLATION_X, f6);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.s, (Property<ViewGroup, Float>) View.TRANSLATION_Y, dimensionPixelSize);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        animatorSet.setDuration(z ? 100L : 0L);
        animatorSet.start();
    }
}
