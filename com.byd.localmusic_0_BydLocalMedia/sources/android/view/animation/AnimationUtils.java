package android.view.animation;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.SystemClock;
import android.util.Xml;
import com.android.internal.R;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes3.dex */
public class AnimationUtils {
    private static final int SEQUENTIALLY = 1;
    private static final int TOGETHER = 0;
    private static ThreadLocal<AnimationState> sAnimationState = new ThreadLocal<AnimationState>() { // from class: android.view.animation.AnimationUtils.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public AnimationState initialValue() {
            return new AnimationState();
        }
    };

    private static class AnimationState {
        boolean animationClockLocked;
        long currentVsyncTimeMillis;
        long lastReportedTimeMillis;

        private AnimationState() {
        }
    }

    public static void lockAnimationClock(long j) {
        AnimationState animationState = sAnimationState.get();
        animationState.animationClockLocked = true;
        animationState.currentVsyncTimeMillis = j;
    }

    public static void unlockAnimationClock() {
        sAnimationState.get().animationClockLocked = false;
    }

    public static long currentAnimationTimeMillis() {
        AnimationState animationState = sAnimationState.get();
        if (animationState.animationClockLocked) {
            return Math.max(animationState.currentVsyncTimeMillis, animationState.lastReportedTimeMillis);
        }
        animationState.lastReportedTimeMillis = SystemClock.uptimeMillis();
        return animationState.lastReportedTimeMillis;
    }

    public static Animation loadAnimation(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(i);
                return createAnimationFromXml(context, animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    private static Animation createAnimationFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return createAnimationFromXml(context, xmlPullParser, null, Xml.asAttributeSet(xmlPullParser));
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x009b, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.Animation createAnimationFromXml(android.content.Context r4, org.xmlpull.v1.XmlPullParser r5, android.view.animation.AnimationSet r6, android.util.AttributeSet r7) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r5.getDepth()
            r1 = 0
        L5:
            int r2 = r5.next()
            r3 = 3
            if (r2 != r3) goto L12
            int r3 = r5.getDepth()
            if (r3 <= r0) goto L9b
        L12:
            r3 = 1
            if (r2 == r3) goto L9b
            r3 = 2
            if (r2 == r3) goto L19
            goto L5
        L19:
            java.lang.String r1 = r5.getName()
            java.lang.String r2 = "set"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L32
            android.view.animation.AnimationSet r1 = new android.view.animation.AnimationSet
            r1.<init>(r4, r7)
            r2 = r1
            android.view.animation.AnimationSet r2 = (android.view.animation.AnimationSet) r2
            createAnimationFromXml(r4, r5, r2, r7)
            goto L7a
        L32:
            java.lang.String r2 = "alpha"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L40
            android.view.animation.AlphaAnimation r1 = new android.view.animation.AlphaAnimation
            r1.<init>(r4, r7)
            goto L7a
        L40:
            java.lang.String r2 = "scale"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L4f
            android.view.animation.ScaleAnimation r1 = new android.view.animation.ScaleAnimation
            r1.<init>(r4, r7)
            goto L7a
        L4f:
            java.lang.String r2 = "rotate"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L5e
            android.view.animation.RotateAnimation r1 = new android.view.animation.RotateAnimation
            r1.<init>(r4, r7)
            goto L7a
        L5e:
            java.lang.String r2 = "translate"
            boolean r2 = r1.equals(r2)
            if (r2 == 0) goto L6d
            android.view.animation.TranslateAnimation r1 = new android.view.animation.TranslateAnimation
            r1.<init>(r4, r7)
            goto L7a
        L6d:
            java.lang.String r2 = "cliprect"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L80
            android.view.animation.ClipRectAnimation r1 = new android.view.animation.ClipRectAnimation
            r1.<init>(r4, r7)
        L7a:
            if (r6 == 0) goto L5
            r6.addAnimation(r1)
            goto L5
        L80:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unknown animation name: "
            r6.append(r7)
            java.lang.String r5 = r5.getName()
            r6.append(r5)
            java.lang.String r5 = r6.toString()
            r4.<init>(r5)
            throw r4
        L9b:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.animation.AnimationUtils.createAnimationFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser, android.view.animation.AnimationSet, android.util.AttributeSet):android.view.animation.Animation");
    }

    public static LayoutAnimationController loadLayoutAnimation(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(i);
                return createLayoutAnimationFromXml(context, animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    private static LayoutAnimationController createLayoutAnimationFromXml(Context context, XmlPullParser xmlPullParser) throws XmlPullParserException, IOException {
        return createLayoutAnimationFromXml(context, xmlPullParser, Xml.asAttributeSet(xmlPullParser));
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0050, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.LayoutAnimationController createLayoutAnimationFromXml(android.content.Context r4, org.xmlpull.v1.XmlPullParser r5, android.util.AttributeSet r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            int r0 = r5.getDepth()
            r1 = 0
        L5:
            int r2 = r5.next()
            r3 = 3
            if (r2 != r3) goto L12
            int r3 = r5.getDepth()
            if (r3 <= r0) goto L50
        L12:
            r3 = 1
            if (r2 == r3) goto L50
            r3 = 2
            if (r2 == r3) goto L19
            goto L5
        L19:
            java.lang.String r1 = r5.getName()
            java.lang.String r2 = "layoutAnimation"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L2b
            android.view.animation.LayoutAnimationController r1 = new android.view.animation.LayoutAnimationController
            r1.<init>(r4, r6)
            goto L5
        L2b:
            java.lang.String r2 = "gridLayoutAnimation"
            boolean r2 = r2.equals(r1)
            if (r2 == 0) goto L39
            android.view.animation.GridLayoutAnimationController r1 = new android.view.animation.GridLayoutAnimationController
            r1.<init>(r4, r6)
            goto L5
        L39:
            java.lang.RuntimeException r4 = new java.lang.RuntimeException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "Unknown layout animation name: "
            r5.append(r6)
            r5.append(r1)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L50:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.animation.AnimationUtils.createLayoutAnimationFromXml(android.content.Context, org.xmlpull.v1.XmlPullParser, android.util.AttributeSet):android.view.animation.LayoutAnimationController");
    }

    public static Animation makeInAnimation(Context context, boolean z) {
        Animation animationLoadAnimation;
        if (z) {
            animationLoadAnimation = loadAnimation(context, 17432578);
        } else {
            animationLoadAnimation = loadAnimation(context, R.anim.slide_in_right);
        }
        animationLoadAnimation.setInterpolator(new DecelerateInterpolator());
        animationLoadAnimation.setStartTime(currentAnimationTimeMillis());
        return animationLoadAnimation;
    }

    public static Animation makeOutAnimation(Context context, boolean z) {
        Animation animationLoadAnimation;
        if (z) {
            animationLoadAnimation = loadAnimation(context, 17432579);
        } else {
            animationLoadAnimation = loadAnimation(context, R.anim.slide_out_left);
        }
        animationLoadAnimation.setInterpolator(new AccelerateInterpolator());
        animationLoadAnimation.setStartTime(currentAnimationTimeMillis());
        return animationLoadAnimation;
    }

    public static Animation makeInChildBottomAnimation(Context context) {
        Animation animationLoadAnimation = loadAnimation(context, R.anim.slide_in_child_bottom);
        animationLoadAnimation.setInterpolator(new AccelerateInterpolator());
        animationLoadAnimation.setStartTime(currentAnimationTimeMillis());
        return animationLoadAnimation;
    }

    public static Interpolator loadInterpolator(Context context, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                animation = context.getResources().getAnimation(i);
                return createInterpolatorFromXml(context.getResources(), context.getTheme(), animation);
            } catch (IOException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException.initCause(e);
                throw notFoundException;
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    public static Interpolator loadInterpolator(Resources resources, Resources.Theme theme, int i) throws Resources.NotFoundException {
        XmlResourceParser animation = null;
        try {
            try {
                try {
                    animation = resources.getAnimation(i);
                    return createInterpolatorFromXml(resources, theme, animation);
                } catch (IOException e) {
                    Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                    notFoundException.initCause(e);
                    throw notFoundException;
                }
            } catch (XmlPullParserException e2) {
                Resources.NotFoundException notFoundException2 = new Resources.NotFoundException("Can't load animation resource ID #0x" + Integer.toHexString(i));
                notFoundException2.initCause(e2);
                throw notFoundException2;
            }
        } finally {
            if (animation != null) {
                animation.close();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x00cc, code lost:
    
        return r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static android.view.animation.Interpolator createInterpolatorFromXml(android.content.res.Resources r4, android.content.res.Resources.Theme r5, org.xmlpull.v1.XmlPullParser r6) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.view.animation.AnimationUtils.createInterpolatorFromXml(android.content.res.Resources, android.content.res.Resources$Theme, org.xmlpull.v1.XmlPullParser):android.view.animation.Interpolator");
    }
}
