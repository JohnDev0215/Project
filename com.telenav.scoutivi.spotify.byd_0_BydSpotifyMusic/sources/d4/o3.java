package d4;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import i2.a;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
/* JADX INFO: loaded from: classes.dex */
public class o3 {

    /* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
    class a extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ ValueAnimator f5766a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ View f5767b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ImageView f5768c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ImageView f5769d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        final /* synthetic */ int f5770e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        final /* synthetic */ float f5771f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        final /* synthetic */ Uri f5772g;

        a(ValueAnimator valueAnimator, View view, ImageView imageView, ImageView imageView2, int i7, float f7, Uri uri) {
            this.f5766a = valueAnimator;
            this.f5767b = view;
            this.f5768c = imageView;
            this.f5769d = imageView2;
            this.f5770e = i7;
            this.f5771f = f7;
            this.f5772g = uri;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (((Float) this.f5766a.getAnimatedValue()).floatValue() == 1.0f) {
                try {
                    o3.f(this.f5767b, this.f5768c, this.f5769d, this.f5770e, this.f5771f, this.f5772g);
                } catch (Exception e7) {
                    h3.i.b("ImageViewBindingAdapterEnhancement").e(e7, "onAnimationEnd", new Object[0]);
                }
            }
        }
    }

    /* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
    class b implements g2.e<Drawable> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f5773a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ImageView f5774b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ImageView f5775c;

        b(View view, ImageView imageView, ImageView imageView2) {
            this.f5773a = view;
            this.f5774b = imageView;
            this.f5775c = imageView2;
        }

        @Override // g2.e
        public boolean b(q1.q qVar, Object obj, h2.h<Drawable> hVar, boolean z6) {
            o3.d(this.f5773a, this.f5774b, this.f5775c).start();
            return false;
        }

        @Override // g2.e
        /* JADX INFO: renamed from: c, reason: merged with bridge method [inline-methods] */
        public boolean a(Drawable drawable, Object obj, h2.h<Drawable> hVar, n1.a aVar, boolean z6) {
            o3.d(this.f5773a, this.f5774b, this.f5775c).start();
            return false;
        }
    }

    /* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
    class c extends AnimatorListenerAdapter {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ View f5776a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ ImageView f5777b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ ImageView f5778c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ ValueAnimator f5779d;

        c(View view, ImageView imageView, ImageView imageView2, ValueAnimator valueAnimator) {
            this.f5776a = view;
            this.f5777b = imageView;
            this.f5778c = imageView2;
            this.f5779d = valueAnimator;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
            if (this.f5776a.isAttachedToWindow()) {
                this.f5777b.setAlpha(1.0f);
                this.f5778c.setAlpha(0.0f);
            }
            if (this.f5776a.getTag() == this.f5779d) {
                this.f5776a.setTag(null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
            if (this.f5776a.isAttachedToWindow()) {
                this.f5777b.setAlpha(1.0f);
                this.f5778c.setAlpha(0.0f);
            }
            if (this.f5776a.getTag() == this.f5779d) {
                this.f5776a.setTag(null);
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
            if (this.f5776a.isAttachedToWindow()) {
                this.f5777b.setAlpha(0.0f);
                this.f5778c.setAlpha(1.0f);
            }
        }
    }

    /* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
    private static class d implements n1.m<LayerDrawable> {

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private static final byte[] f5780c = "com.vivid.spotify.databinding.RoundLayerDrawable".getBytes(n1.f.f8504a);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f5781b;

        /* JADX INFO: compiled from: ImageViewBindingAdapterEnhancement.java */
        private static class a extends z1.b<LayerDrawable> {

            /* JADX INFO: renamed from: g, reason: collision with root package name */
            private r1.d f5782g;

            /* JADX INFO: renamed from: h, reason: collision with root package name */
            private BitmapDrawable f5783h;

            public a(BitmapDrawable bitmapDrawable, r1.d dVar) {
                super(new LayerDrawable(new Drawable[]{bitmapDrawable}));
                this.f5783h = bitmapDrawable;
                this.f5782g = dVar;
            }

            @Override // q1.v
            public void a() {
                this.f5782g.d(this.f5783h.getBitmap());
                this.f5783h = null;
            }

            @Override // q1.v
            public int c() {
                return k2.k.g(this.f5783h.getBitmap());
            }

            @Override // q1.v
            public Class<LayerDrawable> e() {
                return LayerDrawable.class;
            }
        }

        /* synthetic */ d(int i7, a aVar) {
            this(i7);
        }

        @Override // n1.f
        public void a(MessageDigest messageDigest) {
            messageDigest.update(f5780c);
            messageDigest.update(ByteBuffer.allocate(4).putInt(this.f5781b).array());
        }

        @Override // n1.m
        public q1.v<LayerDrawable> b(Context context, q1.v<LayerDrawable> vVar, int i7, int i8) {
            try {
                LayerDrawable layerDrawable = vVar.get();
                r1.d dVarF = com.bumptech.glide.c.c(context).f();
                Bitmap bitmapC = dVarF.c(i7, i8, Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmapC);
                layerDrawable.setBounds(0, 0, i7, i8);
                layerDrawable.draw(canvas);
                canvas.setBitmap(null);
                return new a(new BitmapDrawable(context.getResources(), new x1.w(this.f5781b).b(context, new x1.e(bitmapC, dVarF), i7, i8).get()), dVarF);
            } finally {
                vVar.a();
            }
        }

        private d(int i7) {
            this.f5781b = i7;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ValueAnimator d(final View view, final ImageView imageView, final ImageView imageView2) {
        ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(150L);
        duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: d4.n3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                o3.e(view, imageView2, imageView, valueAnimator);
            }
        });
        duration.addListener(new c(view, imageView2, imageView, duration));
        view.setTag(duration);
        return duration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void e(View view, ImageView imageView, ImageView imageView2, ValueAnimator valueAnimator) {
        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        if (view.isAttachedToWindow()) {
            imageView.setAlpha(fFloatValue);
            imageView2.setAlpha(1.0f - fFloatValue);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f(View view, ImageView imageView, ImageView imageView2, int i7, float f7, Uri uri) {
        Object tag = view.getTag();
        if (tag != null && (tag instanceof ValueAnimator)) {
            ValueAnimator valueAnimator = (ValueAnimator) tag;
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
        }
        com.bumptech.glide.j<Drawable> jVarV0 = com.bumptech.glide.c.u(imageView2).m().v0(uri);
        if (f7 != 0.0f) {
            jVarV0 = (com.bumptech.glide.j) jVarV0.g0(new x1.q(), new x1.w((int) f7));
        }
        Drawable drawableA = z1.a.a(imageView2.getContext(), i7, imageView2.getContext().getTheme());
        if (i7 != 0) {
            jVarV0 = (com.bumptech.glide.j) jVarV0.g(drawableA);
        }
        jVarV0.i0(new b(view, imageView, imageView2));
        jVarV0.t0(imageView2);
    }

    public static void g(View view, boolean z6) {
        view.setEnabled(z6);
    }

    public static void h(ImageView imageView, Integer num, Drawable drawable) {
        if (num == null || num.intValue() == 0) {
            imageView.setImageDrawable(drawable);
        } else {
            com.bumptech.glide.c.u(imageView).n().h0(true).w0(num).a0(imageView.getContext().getTheme()).S(com.bumptech.glide.g.IMMEDIATE).t0(imageView);
        }
    }

    public static void i(ImageView imageView, Uri uri, Drawable drawable) {
        if (uri != null) {
            com.bumptech.glide.c.u(imageView).s(uri).t0(imageView);
        } else {
            imageView.setImageDrawable(drawable);
        }
    }

    public static void j(ImageView imageView, Uri uri, int i7, float f7) {
        com.bumptech.glide.j<Drawable> jVarA0 = com.bumptech.glide.c.u(imageView).m().v0(uri).A0(z1.c.f(new a.C0085a().b(true).a()));
        if (f7 > 0.0f) {
            int i8 = (int) f7;
            jVarA0 = (com.bumptech.glide.j) jVarA0.b0(LayerDrawable.class, new d(i8, null)).b0(Bitmap.class, new x1.w(i8));
        }
        if (i7 != 0) {
            jVarA0 = (com.bumptech.glide.j) jVarA0.g(imageView.getContext().getDrawable(i7)).R(imageView.getContext().getDrawable(i7));
        }
        jVarA0.t0(imageView);
    }

    public static void k(View view, Uri uri, float f7, int i7) {
        if (view != null) {
            Object tag = view.getTag();
            if (tag != null && (tag instanceof ValueAnimator)) {
                ValueAnimator valueAnimator = (ValueAnimator) tag;
                if (valueAnimator.isRunning()) {
                    valueAnimator.cancel();
                }
            }
            ImageView imageView = (ImageView) view.findViewById(com.vivid.spotify.t.iv_holder);
            imageView.setImageResource(i7);
            ImageView imageView2 = (ImageView) view.findViewById(com.vivid.spotify.t.iv_cover);
            if (uri != null) {
                ValueAnimator valueAnimatorD = d(view, imageView2, imageView);
                valueAnimatorD.addListener(new a(valueAnimatorD, view, imageView, imageView2, i7, f7, uri));
                valueAnimatorD.start();
                return;
            }
            com.bumptech.glide.j<Drawable> jVarV0 = com.bumptech.glide.c.u(imageView2).m().v0(uri);
            if (f7 != 0.0f) {
                jVarV0 = (com.bumptech.glide.j) jVarV0.g0(new x1.q(), new x1.w((int) f7));
            }
            Drawable drawableA = z1.a.a(imageView2.getContext(), i7, imageView2.getContext().getTheme());
            if (i7 != 0) {
                jVarV0 = (com.bumptech.glide.j) jVarV0.g(drawableA);
            }
            jVarV0.t0(imageView2);
        }
    }

    public static void l(ImageView imageView, int i7) {
        if (i7 != 0) {
            imageView.setColorFilter(v3.e.a(imageView.getContext(), i7));
        } else {
            imageView.clearColorFilter();
        }
    }
}
