package p;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.os.StrictMode;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Base64;
import android.util.Log;
import android.util.Xml;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewParent;
import android.widget.TextView;
import androidx.constraintlayout.core.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.core.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.work.impl.WorkDatabase;
import com.google.common.collect.ImmutableMap;
import com.spotify.automotive.pathfinder.type.ContentRatingLabel;
import com.spotify.automotive.pathfinder.type.EpisodePlaybackState;
import com.spotify.base.java.logging.Logger;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.player.model.ContextTrack;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public abstract class f60 {
    public static final ke2 a = new ke2(3);
    public static final ml b = new ml();
    public static final int[] c = {R.attr.theme, com.spotify.music.R.attr.theme};
    public static final int[] d = {com.spotify.music.R.attr.materialThemeOverlay};
    public static final ke2 e = new ke2(12);
    public static final ke2 f = new ke2(18);
    public static boolean g;

    public static yv0 A(XmlResourceParser xmlResourceParser, Resources resources) throws XmlPullParserException, IOException {
        int next;
        do {
            next = xmlResourceParser.next();
            if (next == 2) {
                break;
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        xmlResourceParser.require(2, null, "font-family");
        if (xmlResourceParser.getName().equals("font-family")) {
            TypedArray typedArrayObtainAttributes = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), uz1.b);
            String string = typedArrayObtainAttributes.getString(0);
            String string2 = typedArrayObtainAttributes.getString(4);
            String string3 = typedArrayObtainAttributes.getString(5);
            int resourceId = typedArrayObtainAttributes.getResourceId(1, 0);
            int integer = typedArrayObtainAttributes.getInteger(2, 1);
            int integer2 = typedArrayObtainAttributes.getInteger(3, 500);
            String string4 = typedArrayObtainAttributes.getString(6);
            typedArrayObtainAttributes.recycle();
            if (string != null && string2 != null && string3 != null) {
                while (xmlResourceParser.next() != 3) {
                    J(xmlResourceParser);
                }
                return new bw0(new pv0(string, D(resources, resourceId), string2, string3), integer, integer2, string4);
            }
            ArrayList arrayList = new ArrayList();
            while (xmlResourceParser.next() != 3) {
                if (xmlResourceParser.getEventType() == 2) {
                    if (xmlResourceParser.getName().equals("font")) {
                        TypedArray typedArrayObtainAttributes2 = resources.obtainAttributes(Xml.asAttributeSet(xmlResourceParser), uz1.c);
                        int i = typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(8) ? 8 : 1, 400);
                        boolean z = 1 == typedArrayObtainAttributes2.getInt(typedArrayObtainAttributes2.hasValue(6) ? 6 : 2, 0);
                        int i2 = typedArrayObtainAttributes2.hasValue(9) ? 9 : 3;
                        String string5 = typedArrayObtainAttributes2.getString(typedArrayObtainAttributes2.hasValue(7) ? 7 : 4);
                        int i3 = typedArrayObtainAttributes2.getInt(i2, 0);
                        int i4 = typedArrayObtainAttributes2.hasValue(5) ? 5 : 0;
                        int resourceId2 = typedArrayObtainAttributes2.getResourceId(i4, 0);
                        String string6 = typedArrayObtainAttributes2.getString(i4);
                        typedArrayObtainAttributes2.recycle();
                        while (xmlResourceParser.next() != 3) {
                            J(xmlResourceParser);
                        }
                        arrayList.add(new aw0(i, i3, resourceId2, string6, string5, z));
                    } else {
                        J(xmlResourceParser);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                return new zv0((aw0[]) arrayList.toArray(new aw0[0]));
            }
        } else {
            J(xmlResourceParser);
        }
        return null;
    }

    public static PorterDuff.Mode B(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    public static void C(AnimatorSet animatorSet, ArrayList arrayList) {
        int size = arrayList.size();
        long jMax = 0;
        for (int i = 0; i < size; i++) {
            Animator animator = (Animator) arrayList.get(i);
            jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
        valueAnimatorOfInt.setDuration(jMax);
        arrayList.add(0, valueAnimatorOfInt);
        animatorSet.playTogether(arrayList);
    }

    public static List D(Resources resources, int i) {
        if (i == 0) {
            return Collections.emptyList();
        }
        TypedArray typedArrayObtainTypedArray = resources.obtainTypedArray(i);
        try {
            if (typedArrayObtainTypedArray.length() == 0) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList();
            if (xv0.a(typedArrayObtainTypedArray, 0) == 1) {
                for (int i2 = 0; i2 < typedArrayObtainTypedArray.length(); i2++) {
                    int resourceId = typedArrayObtainTypedArray.getResourceId(i2, 0);
                    if (resourceId != 0) {
                        String[] stringArray = resources.getStringArray(resourceId);
                        ArrayList arrayList2 = new ArrayList();
                        for (String str : stringArray) {
                            arrayList2.add(Base64.decode(str, 0));
                        }
                        arrayList.add(arrayList2);
                    }
                }
            } else {
                String[] stringArray2 = resources.getStringArray(i);
                ArrayList arrayList3 = new ArrayList();
                for (String str2 : stringArray2) {
                    arrayList3.add(Base64.decode(str2, 0));
                }
                arrayList.add(arrayList3);
            }
            return arrayList;
        } finally {
            typedArrayObtainTypedArray.recycle();
        }
    }

    public static final void E(int i, int i2, Object[] objArr) {
        v03.j(objArr, "<this>");
        while (i < i2) {
            objArr[i] = null;
            i++;
        }
    }

    public static final String F(ContextTrack contextTrack) {
        return contextTrack.e() + contextTrack.f() + contextTrack.c() + ((String) contextTrack.b().get("iteration"));
    }

    public static void G(TextView textView, int i) {
        p02.d(i);
        if (Build.VERSION.SDK_INT >= 28) {
            ek2.d(textView, i);
            return;
        }
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = ak2.a(textView) ? fontMetricsInt.top : fontMetricsInt.ascent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), i + i2, textView.getPaddingRight(), textView.getPaddingBottom());
        }
    }

    public static void H(TextView textView, int i) {
        p02.d(i);
        Paint.FontMetricsInt fontMetricsInt = textView.getPaint().getFontMetricsInt();
        int i2 = ak2.a(textView) ? fontMetricsInt.bottom : fontMetricsInt.descent;
        if (i > Math.abs(i2)) {
            textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), i - i2);
        }
    }

    public static void I(View view, qf1 qf1Var) {
        cc0 cc0Var = qf1Var.c.b;
        if (cc0Var == null || !cc0Var.a) {
            return;
        }
        float fI = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            WeakHashMap weakHashMap = zt2.a;
            fI += ot2.i((View) parent);
        }
        pf1 pf1Var = qf1Var.c;
        if (pf1Var.m != fI) {
            pf1Var.m = fI;
            qf1Var.n();
        }
    }

    public static void J(XmlResourceParser xmlResourceParser) throws XmlPullParserException, IOException {
        int i = 1;
        while (i > 0) {
            int next = xmlResourceParser.next();
            if (next == 2) {
                i++;
            } else if (next == 3) {
                i--;
            }
        }
    }

    public static void K(int i, ty tyVar, ey eyVar, boolean z) {
        float f2 = tyVar.d0;
        xx xxVar = tyVar.H;
        int iC = xxVar.f.c();
        xx xxVar2 = tyVar.J;
        int iC2 = xxVar2.f.c();
        int iD = xxVar.d() + iC;
        int iD2 = iC2 - xxVar2.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int iO = tyVar.o();
        int i2 = (iC2 - iC) - iO;
        if (iC > iC2) {
            i2 = (iC - iC2) - iO;
        }
        int i3 = ((int) (i2 > 0 ? (f2 * i2) + 0.5f : f2 * i2)) + iC;
        int i4 = i3 + iO;
        if (iC > iC2) {
            i4 = i3 - iO;
        }
        tyVar.F(i3, i4);
        w(i + 1, tyVar, eyVar, z);
    }

    public static void L(int i, ty tyVar, ey eyVar, ty tyVar2, boolean z) {
        float f2 = tyVar2.d0;
        xx xxVar = tyVar2.H;
        int iD = xxVar.d() + xxVar.f.c();
        xx xxVar2 = tyVar2.J;
        int iC = xxVar2.f.c() - xxVar2.d();
        if (iC >= iD) {
            int iO = tyVar2.o();
            if (tyVar2.g0 != 8) {
                int i2 = tyVar2.r;
                if (i2 == 2) {
                    iO = (int) (tyVar2.d0 * 0.5f * (tyVar instanceof uy ? tyVar.o() : tyVar.T.o()));
                } else if (i2 == 0) {
                    iO = iC - iD;
                }
                iO = Math.max(tyVar2.u, iO);
                int i3 = tyVar2.v;
                if (i3 > 0) {
                    iO = Math.min(i3, iO);
                }
            }
            int i4 = iD + ((int) ((f2 * ((iC - iD) - iO)) + 0.5f));
            tyVar2.F(i4, iO + i4);
            w(i + 1, tyVar2, eyVar, z);
        }
    }

    public static void M(int i, ty tyVar, ey eyVar) {
        float f2 = tyVar.e0;
        xx xxVar = tyVar.I;
        int iC = xxVar.f.c();
        xx xxVar2 = tyVar.K;
        int iC2 = xxVar2.f.c();
        int iD = xxVar.d() + iC;
        int iD2 = iC2 - xxVar2.d();
        if (iC == iC2) {
            f2 = 0.5f;
        } else {
            iC = iD;
            iC2 = iD2;
        }
        int i2 = tyVar.i();
        int i3 = (iC2 - iC) - i2;
        if (iC > iC2) {
            i3 = (iC - iC2) - i2;
        }
        int i4 = (int) (i3 > 0 ? (f2 * i3) + 0.5f : f2 * i3);
        int i5 = iC + i4;
        int i6 = i5 + i2;
        if (iC > iC2) {
            i5 = iC - i4;
            i6 = i5 - i2;
        }
        tyVar.G(i5, i6);
        R(i + 1, tyVar, eyVar);
    }

    public static void N(int i, ty tyVar, ey eyVar, ty tyVar2) {
        float f2 = tyVar2.e0;
        xx xxVar = tyVar2.I;
        int iD = xxVar.d() + xxVar.f.c();
        xx xxVar2 = tyVar2.K;
        int iC = xxVar2.f.c() - xxVar2.d();
        if (iC >= iD) {
            int i2 = tyVar2.i();
            if (tyVar2.g0 != 8) {
                int i3 = tyVar2.s;
                if (i3 == 2) {
                    i2 = (int) (f2 * 0.5f * (tyVar instanceof uy ? tyVar.i() : tyVar.T.i()));
                } else if (i3 == 0) {
                    i2 = iC - iD;
                }
                i2 = Math.max(tyVar2.x, i2);
                int i4 = tyVar2.y;
                if (i4 > 0) {
                    i2 = Math.min(i4, i2);
                }
            }
            int i5 = iD + ((int) ((f2 * ((iC - iD) - i2)) + 0.5f));
            tyVar2.G(i5, i2 + i5);
            R(i + 1, tyVar2, eyVar);
        }
    }

    public static final int O(EpisodePlaybackState episodePlaybackState) {
        v03.j(episodePlaybackState, "<this>");
        int iOrdinal = episodePlaybackState.ordinal();
        if (iOrdinal == 0) {
            return 2;
        }
        if (iOrdinal == 1) {
            return 1;
        }
        if (iOrdinal == 2) {
            return 0;
        }
        if (iOrdinal == 3) {
            return -1;
        }
        throw new NoWhenBranchMatchedException();
    }

    public static final String P(c00 c00Var) {
        Object objB;
        if (c00Var instanceof ea0) {
            return c00Var.toString();
        }
        try {
            objB = c00Var + '@' + s(c00Var);
        } catch (Throwable th) {
            objB = kotlin.a.b(th);
        }
        if (Result.a(objB) != null) {
            objB = c00Var.getClass().getName() + '@' + s(c00Var);
        }
        return (String) objB;
    }

    public static final dg1 Q(pe0 pe0Var, if2 if2Var, String str, int i, ki1 ki1Var, vj vjVar, String str2, String str3) {
        String strC;
        ContentRatingLabel contentRatingLabel;
        ContentRatingLabel contentRatingLabel2;
        v03.j(pe0Var, "<this>");
        v03.j(ki1Var, "mediaUriUtil");
        he0 he0Var = pe0Var.c;
        Uri uriB = ki1Var.b(he1.r(he0Var != null ? he0Var.b : null), MediaUriUtil$Transformation.e);
        oe0 oe0Var = pe0Var.d;
        Date dateF = oe0Var != null ? wb1.f(oe0Var.a) : null;
        je0 je0Var = pe0Var.e;
        int i2 = je0Var.a;
        me0 me0Var = pe0Var.h;
        int i3 = i2 - me0Var.a;
        p01 p01Var = new p01(25);
        p01Var.H(str2);
        if (TextUtils.isEmpty(str3)) {
            ((Bundle) p01Var.e).remove("com.spotify.music.extra.CONTEXT_TITLE");
        } else {
            ((Bundle) p01Var.e).putString("com.spotify.music.extra.CONTEXT_TITLE", str3);
        }
        ge0 ge0Var = pe0Var.f;
        kz kzVar = ge0Var != null ? ge0Var.b : null;
        p01Var.I((kzVar == null || (contentRatingLabel2 = kzVar.a) == null) ? false : contentRatingLabel2.equals(ContentRatingLabel.NINETEEN_PLUS));
        kz kzVar2 = ge0Var != null ? ge0Var.b : null;
        p01Var.J((kzVar2 == null || (contentRatingLabel = kzVar2.a) == null) ? false : contentRatingLabel.equals(ContentRatingLabel.EXPLICIT));
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.PLUS);
        int i4 = je0Var.a;
        p01Var.G(i4);
        p01Var.C(i4 > 0 ? ((double) me0Var.a) / ((double) i4) : 0.0d);
        EpisodePlaybackState episodePlaybackState = me0Var.b;
        int iO = O(episodePlaybackState);
        if (iO == -1) {
            ((Bundle) p01Var.e).remove("android.media.extra.PLAYBACK_STATUS");
        } else {
            ((Bundle) p01Var.e).putInt("android.media.extra.PLAYBACK_STATUS", iO);
        }
        long j = i3;
        if (j <= 0) {
            ((Bundle) p01Var.e).remove("com.spotify.music.extra.TIME_LEFT_MS");
        } else {
            ((Bundle) p01Var.e).putLong("com.spotify.music.extra.TIME_LEFT_MS", j);
        }
        Bundle bundle = (Bundle) p01Var.e;
        if (vjVar != null) {
            TimeUnit timeUnit = TimeUnit.MILLISECONDS;
            int seconds = (int) timeUnit.toSeconds(i4);
            int seconds2 = (int) timeUnit.toSeconds(j);
            boolean z = episodePlaybackState == EpisodePlaybackState.COMPLETED;
            if (dateF == null) {
                dateF = new Date(0L);
            }
            strC = vjVar.c(new rv1(seconds, seconds2, z, dateF, false, false, false, 480));
        } else {
            ke0 ke0Var = pe0Var.i.a.b;
            strC = ke0Var != null ? ke0Var.a : null;
        }
        String str4 = pe0Var.b;
        kh1 kh1VarS = yn.s(str4);
        kh1VarS.b = if2Var != null ? if2Var.toString() : null;
        kh1VarS.c = str;
        kh1VarS.d = Integer.valueOf(i);
        String strA = kh1VarS.a();
        jh1 jh1Var = new jh1();
        jh1Var.a = strA;
        jh1Var.g = Uri.parse(str4);
        jh1Var.f = uriB;
        jh1Var.b = pe0Var.a;
        jh1Var.c = strC;
        jh1Var.h = bundle;
        return new dg1(jh1Var, 2);
    }

    public static void R(int i, ty tyVar, ey eyVar) {
        xx xxVar;
        xx xxVar2;
        xx xxVar3;
        xx xxVar4;
        xx xxVar5;
        if (tyVar.n) {
            return;
        }
        if (!(tyVar instanceof uy) && tyVar.x() && d(tyVar)) {
            uy.R(tyVar, eyVar, new ml());
        }
        xx xxVarG = tyVar.g(ConstraintAnchor$Type.e);
        xx xxVarG2 = tyVar.g(ConstraintAnchor$Type.l);
        int iC = xxVarG.c();
        int iC2 = xxVarG2.c();
        HashSet<xx> hashSet = xxVarG.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.k;
        if (hashSet != null && xxVarG.c) {
            for (xx xxVar6 : hashSet) {
                ty tyVar2 = xxVar6.d;
                int i2 = i + 1;
                boolean zD = d(tyVar2);
                if (tyVar2.x() && zD) {
                    uy.R(tyVar2, eyVar, new ml());
                }
                xx xxVar7 = tyVar2.I;
                xx xxVar8 = tyVar2.K;
                boolean z = (xxVar6 == xxVar7 && (xxVar5 = xxVar8.f) != null && xxVar5.c) || (xxVar6 == xxVar8 && (xxVar4 = xxVar7.f) != null && xxVar4.c);
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = tyVar2.S[1];
                if (constraintWidget$DimensionBehaviour2 != constraintWidget$DimensionBehaviour || zD) {
                    if (!tyVar2.x()) {
                        if (xxVar6 == xxVar7 && xxVar8.f == null) {
                            int iD = xxVar7.d() + iC;
                            tyVar2.G(iD, tyVar2.i() + iD);
                            R(i2, tyVar2, eyVar);
                        } else if (xxVar6 == xxVar8 && xxVar7.f == null) {
                            int iD2 = iC - xxVar8.d();
                            tyVar2.G(iD2 - tyVar2.i(), iD2);
                            R(i2, tyVar2, eyVar);
                        } else if (z && !tyVar2.w()) {
                            M(i2, tyVar2, eyVar);
                        }
                    }
                } else if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour && tyVar2.y >= 0 && tyVar2.x >= 0 && (tyVar2.g0 == 8 || (tyVar2.s == 0 && tyVar2.W == 0.0f))) {
                    if (!tyVar2.w() && z && !tyVar2.w()) {
                        N(i2, tyVar, eyVar, tyVar2);
                    }
                }
            }
        }
        if (tyVar instanceof d11) {
            return;
        }
        HashSet<xx> hashSet2 = xxVarG2.a;
        if (hashSet2 != null && xxVarG2.c) {
            for (xx xxVar9 : hashSet2) {
                ty tyVar3 = xxVar9.d;
                int i3 = i + 1;
                boolean zD2 = d(tyVar3);
                if (tyVar3.x() && zD2) {
                    uy.R(tyVar3, eyVar, new ml());
                }
                xx xxVar10 = tyVar3.I;
                xx xxVar11 = tyVar3.K;
                boolean z2 = (xxVar9 == xxVar10 && (xxVar3 = xxVar11.f) != null && xxVar3.c) || (xxVar9 == xxVar11 && (xxVar2 = xxVar10.f) != null && xxVar2.c);
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = tyVar3.S[1];
                if (constraintWidget$DimensionBehaviour3 != constraintWidget$DimensionBehaviour || zD2) {
                    if (!tyVar3.x()) {
                        if (xxVar9 == xxVar10 && xxVar11.f == null) {
                            int iD3 = xxVar10.d() + iC2;
                            tyVar3.G(iD3, tyVar3.i() + iD3);
                            R(i3, tyVar3, eyVar);
                        } else if (xxVar9 == xxVar11 && xxVar10.f == null) {
                            int iD4 = iC2 - xxVar11.d();
                            tyVar3.G(iD4 - tyVar3.i(), iD4);
                            R(i3, tyVar3, eyVar);
                        } else if (z2 && !tyVar3.w()) {
                            M(i3, tyVar3, eyVar);
                        }
                    }
                } else if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour && tyVar3.y >= 0 && tyVar3.x >= 0) {
                    if (tyVar3.g0 != 8) {
                        if (tyVar3.s == 0) {
                            if (tyVar3.W == 0.0f) {
                            }
                        }
                    }
                    if (!tyVar3.w() && z2 && !tyVar3.w()) {
                        N(i3, tyVar, eyVar, tyVar3);
                    }
                }
            }
        }
        xx xxVarG3 = tyVar.g(ConstraintAnchor$Type.m);
        if (xxVarG3.a != null && xxVarG3.c) {
            int iC3 = xxVarG3.c();
            for (xx xxVar12 : xxVarG3.a) {
                ty tyVar4 = xxVar12.d;
                int i4 = i + 1;
                boolean zD3 = d(tyVar4);
                if (tyVar4.x() && zD3) {
                    uy.R(tyVar4, eyVar, new ml());
                }
                if (tyVar4.S[1] != constraintWidget$DimensionBehaviour || zD3) {
                    if (!tyVar4.x() && xxVar12 == (xxVar = tyVar4.L)) {
                        int iD5 = xxVar12.d() + iC3;
                        if (tyVar4.E) {
                            int i5 = iD5 - tyVar4.a0;
                            int i6 = tyVar4.V + i5;
                            tyVar4.Z = i5;
                            tyVar4.I.i(i5);
                            tyVar4.K.i(i6);
                            xxVar.i(iD5);
                            tyVar4.l = true;
                        }
                        R(i4, tyVar4, eyVar);
                    }
                }
            }
        }
        tyVar.n = true;
    }

    public static Context S(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, d, i, i2);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, 0);
        typedArrayObtainStyledAttributes.recycle();
        boolean z = (context instanceof a00) && ((a00) context).a == resourceId;
        if (resourceId == 0 || z) {
            return context;
        }
        a00 a00Var = new a00(context, resourceId);
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, c);
        int resourceId2 = typedArrayObtainStyledAttributes2.getResourceId(0, 0);
        int resourceId3 = typedArrayObtainStyledAttributes2.getResourceId(1, 0);
        typedArrayObtainStyledAttributes2.recycle();
        if (resourceId2 == 0) {
            resourceId2 = resourceId3;
        }
        if (resourceId2 != 0) {
            a00Var.getTheme().applyStyle(resourceId2, true);
        }
        return a00Var;
    }

    public static ActionMode.Callback T(ActionMode.Callback callback, TextView textView) {
        return (Build.VERSION.SDK_INT > 27 || (callback instanceof fk2) || callback == null) ? callback : new fk2(callback, textView);
    }

    public static final void U(a81 a81Var, Object obj) {
        v03.j(a81Var, "<this>");
        if (obj == null) {
            a81Var.i0();
            return;
        }
        if (obj instanceof Map) {
            a81Var.f();
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                a81Var.l0(String.valueOf(key));
                U(a81Var, value);
            }
            a81Var.k();
            return;
        }
        if (obj instanceof List) {
            a81Var.j();
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                U(a81Var, it.next());
            }
            a81Var.h();
            return;
        }
        if (obj instanceof Boolean) {
            a81Var.n0(((Boolean) obj).booleanValue());
            return;
        }
        if (obj instanceof Integer) {
            a81Var.A(((Number) obj).intValue());
            return;
        }
        if (obj instanceof Long) {
            a81Var.z(((Number) obj).longValue());
            return;
        }
        if (obj instanceof Double) {
            a81Var.G(((Number) obj).doubleValue());
            return;
        }
        if (!(obj instanceof o71)) {
            if (obj instanceof String) {
                a81Var.N((String) obj);
                return;
            }
            throw new IllegalStateException(("Cannot write " + obj + " to Json").toString());
        }
        a81Var.m0((o71) obj);
    }

    public static final void V(Bitmap bitmap, File file) {
        Object objB;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                ci1.j(!bitmap.isRecycled());
                boolean zCompress = bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
                ux2.e(fileOutputStream, null);
                objB = Boolean.valueOf(zCompress);
            } finally {
            }
        } catch (Throwable th) {
            objB = kotlin.a.b(th);
        }
        Throwable thA = Result.a(objB);
        if (thA != null) {
            Logger.c(thA, "Couldn't write image to file \"%s\".", file.getAbsolutePath());
        }
    }

    public static String W(File file) {
        if (!file.getName().endsWith(".apk")) {
            throw new IllegalArgumentException("Non-apk found in splits directory.");
        }
        String strReplaceFirst = file.getName().replaceFirst("(_\\d+)?\\.apk", "");
        return (strReplaceFirst.equals("base-master") || strReplaceFirst.equals("base-main")) ? "" : strReplaceFirst.startsWith("base-") ? strReplaceFirst.replace("base-", "config.") : strReplaceFirst.replace("-", ".config.").replace(".config.master", "").replace(".config.main", "");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static int a(gl glVar, boolean z) {
        int i = z ? glVar.c : glVar.b;
        int i2 = z ? glVar.b : glVar.c;
        byte[][] bArr = (byte[][]) glVar.d;
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            byte b2 = -1;
            int i5 = 0;
            for (int i6 = 0; i6 < i2; i6++) {
                byte b3 = z ? bArr[i4][i6] : bArr[i6][i4];
                if (b3 == b2) {
                    i5++;
                } else {
                    if (i5 >= 5) {
                        i3 += i5 - 2;
                    }
                    i5 = 1;
                    b2 = b3;
                }
            }
            if (i5 >= 5) {
                i3 = (i5 - 2) + i3;
            }
        }
        return i3;
    }

    public static final String b(ContextTrack contextTrack) {
        ImmutableMap immutableMapB = contextTrack.b();
        v03.i(immutableMapB, "metadata()");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator it = immutableMapB.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String str = (String) entry.getKey();
            v03.i(str, "key");
            if (ng2.g0(str, "artist_name", false)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        Collection collectionValues = new TreeMap(linkedHashMap).values();
        v03.i(collectionValues, "metadata()\n        .filt…rtedMap()\n        .values");
        ArrayList arrayList = new ArrayList();
        for (Object obj : collectionValues) {
            v03.i((String) obj, "it");
            if (!ng2.c0(r2)) {
                arrayList.add(obj);
            }
        }
        return ou.h0(arrayList, null, null, null, null, 63);
    }

    public static ExecutorService c(String str) {
        ExecutorService executorServiceUnconfigurableExecutorService = Executors.unconfigurableExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new a80(str, new AtomicLong(1L)), new ThreadPoolExecutor.DiscardPolicy()));
        Runtime.getRuntime().addShutdownHook(new Thread(new yk0(str, executorServiceUnconfigurableExecutorService, TimeUnit.SECONDS), "Crashlytics Shutdown Hook for ".concat(str)));
        return executorServiceUnconfigurableExecutorService;
    }

    public static boolean d(ty tyVar) {
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = tyVar.S;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = constraintWidget$DimensionBehaviourArr[1];
        ty tyVar2 = tyVar.T;
        uy uyVar = tyVar2 != null ? (uy) tyVar2 : null;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = ConstraintWidget$DimensionBehaviour.c;
        if (uyVar != null) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour4 = uyVar.S[0];
        }
        if (uyVar != null) {
            ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour5 = uyVar.S[1];
        }
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour6 = ConstraintWidget$DimensionBehaviour.k;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour7 = ConstraintWidget$DimensionBehaviour.e;
        boolean z = constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour3 || tyVar.y() || constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour7 || (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour6 && tyVar.r == 0 && tyVar.W == 0.0f && tyVar.r(0)) || (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour6 && tyVar.r == 1 && tyVar.s(0, tyVar.o()));
        boolean z2 = constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour3 || tyVar.z() || constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour7 || (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour6 && tyVar.s == 0 && tyVar.W == 0.0f && tyVar.r(1)) || (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour6 && tyVar.s == 1 && tyVar.s(1, tyVar.i()));
        if (tyVar.W <= 0.0f || !(z || z2)) {
            return z && z2;
        }
        return true;
    }

    public static void e(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static final void f(WorkDatabase workDatabase, sw swVar, hx2 hx2Var) {
        int i;
        v03.j(swVar, "configuration");
        v03.j(hx2Var, "continuation");
        ArrayList arrayListD = v03.D(hx2Var);
        int i2 = 0;
        while (!arrayListD.isEmpty()) {
            if (arrayListD.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            List list = ((hx2) arrayListD.remove(v03.q(arrayListD))).g;
            v03.i(list, "current.work");
            if (list.isEmpty()) {
                i = 0;
            } else {
                Iterator it = list.iterator();
                i = 0;
                while (it.hasNext()) {
                    if ((!((gy2) it.next()).b.j.h.isEmpty()) && (i = i + 1) < 0) {
                        throw new ArithmeticException("Count overflow has happened.");
                    }
                }
            }
            i2 += i;
        }
        if (i2 == 0) {
            return;
        }
        my2 my2VarV = workDatabase.v();
        my2VarV.getClass();
        m52 m52VarI = m52.i(0, "Select COUNT(*) FROM workspec WHERE LENGTH(content_uri_triggers)<>0 AND state NOT IN (2, 3, 5)");
        i52 i52Var = my2VarV.a;
        i52Var.b();
        Cursor cursorM = i52Var.m(m52VarI, null);
        try {
            int i3 = cursorM.moveToFirst() ? cursorM.getInt(0) : 0;
            cursorM.close();
            m52VarI.q();
            int i4 = i3 + i2;
            int i5 = swVar.i;
            if (i4 <= i5) {
                return;
            }
            StringBuilder sb = new StringBuilder("Too many workers with contentUriTriggers are enqueued:\ncontentUriTrigger workers limit: ");
            sb.append(i5);
            sb.append(";\nalready enqueued count: ");
            sb.append(i3);
            sb.append(";\ncurrent enqueue operation count: ");
            throw new IllegalArgumentException(u4.l(sb, i2, ".\nTo address this issue you can: \n1. enqueue less workers or batch some of workers with content uri triggers together;\n2. increase limit via Configuration.Builder.setContentUriTriggerWorkersLimit;\nPlease beware that workers with content uri triggers immediately occupy slots in JobScheduler so no updates to content uris are missed."));
        } catch (Throwable th) {
            cursorM.close();
            m52VarI.q();
            throw th;
        }
    }

    public static void g() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IllegalStateException("Called on main looper");
        }
    }

    public static void h() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("Not called on main looper");
        }
    }

    public static boolean i(boolean z, boolean z2, Observer observer, io.reactivex.rxjava3.operators.g gVar, Disposable disposable, io.reactivex.rxjava3.internal.observers.k kVar) {
        if (kVar.l) {
            gVar.clear();
            disposable.dispose();
            return true;
        }
        if (!z || !z2) {
            return false;
        }
        if (disposable != null) {
            disposable.dispose();
        }
        observer.onComplete();
        return true;
    }

    public static void j(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    public static boolean k(File file, Resources resources, int i) throws Throwable {
        InputStream inputStreamOpenRawResource;
        try {
            inputStreamOpenRawResource = resources.openRawResource(i);
        } catch (Throwable th) {
            th = th;
            inputStreamOpenRawResource = null;
        }
        try {
            boolean zL = l(file, inputStreamOpenRawResource);
            j(inputStreamOpenRawResource);
            return zL;
        } catch (Throwable th2) {
            th = th2;
            j(inputStreamOpenRawResource);
            throw th;
        }
    }

    public static boolean l(File file, InputStream inputStream) throws Throwable {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        FileOutputStream fileOutputStream = null;
        try {
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file, false);
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            j(fileOutputStream2);
                            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                            return true;
                        }
                        fileOutputStream2.write(bArr, 0, i);
                    }
                } catch (IOException e2) {
                    e = e2;
                    fileOutputStream = fileOutputStream2;
                    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + e.getMessage());
                    j(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return false;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    j(fileOutputStream);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e3) {
            e = e3;
        }
    }

    public static l13 m(int i) {
        if (i != 0 && i == 1) {
            return new b50();
        }
        return new q52();
    }

    public static final h52 n(Context context, Class cls, String str) {
        v03.j(context, "context");
        if (!ng2.c0(str)) {
            return new h52(context, cls, str);
        }
        throw new IllegalArgumentException("Cannot build a database with null or empty name. If you are trying to create an in memory database, use Room.inMemoryDatabaseBuilder".toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x002d, code lost:
    
        r1 = ((java.util.concurrent.atomic.AtomicInteger) r13.c).addAndGet(-r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0036, code lost:
    
        if (r1 != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0038, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o(io.reactivex.rxjava3.operators.f r10, io.reactivex.rxjava3.core.Observer r11, io.reactivex.rxjava3.disposables.Disposable r12, io.reactivex.rxjava3.internal.observers.k r13) {
        /*
            r0 = 1
            r1 = r0
        L2:
            boolean r2 = r13.m
            boolean r3 = r10.isEmpty()
            r4 = r11
            r5 = r10
            r6 = r12
            r7 = r13
            boolean r2 = i(r2, r3, r4, r5, r6, r7)
            if (r2 == 0) goto L13
            return
        L13:
            boolean r3 = r13.m
            java.lang.Object r2 = r10.poll()
            if (r2 != 0) goto L1d
            r9 = r0
            goto L1f
        L1d:
            r4 = 0
            r9 = r4
        L1f:
            r4 = r9
            r5 = r11
            r6 = r10
            r7 = r12
            r8 = r13
            boolean r3 = i(r3, r4, r5, r6, r7, r8)
            if (r3 == 0) goto L2b
            return
        L2b:
            if (r9 == 0) goto L39
            int r1 = -r1
            java.lang.Object r2 = r13.c
            java.util.concurrent.atomic.AtomicInteger r2 = (java.util.concurrent.atomic.AtomicInteger) r2
            int r1 = r2.addAndGet(r1)
            if (r1 != 0) goto L2
            return
        L39:
            r13.d(r11, r2)
            goto L13
        */
        throw new UnsupportedOperationException("Method not decompiled: p.f60.o(io.reactivex.rxjava3.operators.f, io.reactivex.rxjava3.core.Observer, io.reactivex.rxjava3.disposables.Disposable, io.reactivex.rxjava3.internal.observers.k):void");
    }

    public static boolean p(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int q(View view, int i) {
        return he1.O(i, view.getContext(), view.getClass().getCanonicalName());
    }

    public static final int r(Cursor cursor, String str) {
        String strS0;
        v03.j(cursor, "c");
        int columnIndex = cursor.getColumnIndex(str);
        if (columnIndex < 0) {
            columnIndex = cursor.getColumnIndex("`" + str + '`');
            if (columnIndex < 0) {
                columnIndex = -1;
            }
        }
        if (columnIndex >= 0) {
            return columnIndex;
        }
        try {
            String[] columnNames = cursor.getColumnNames();
            v03.i(columnNames, "c.columnNames");
            strS0 = kotlin.collections.b.s0(columnNames);
        } catch (Exception unused) {
            strS0 = "unknown";
        }
        throw new IllegalArgumentException("column '" + str + "' does not exist. Available columns: " + strS0);
    }

    public static final String s(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static byte[] t(Context context, String str) throws PackageManager.NameNotFoundException {
        y3 y3Var;
        MessageDigest messageDigest;
        bz2 bz2Var = bz2.b;
        synchronized (bz2Var) {
            try {
                if (bz2Var.a == null) {
                    if (context.getApplicationContext() != null) {
                        context = context.getApplicationContext();
                    }
                    bz2Var.a = new y3(context);
                }
                y3Var = bz2Var.a;
            } catch (Throwable th) {
                throw th;
            }
        }
        PackageInfo packageInfo = y3Var.a.getPackageManager().getPackageInfo(str, 64);
        Signature[] signatureArr = packageInfo.signatures;
        if (signatureArr != null && signatureArr.length == 1) {
            int i = 0;
            while (true) {
                if (i >= 2) {
                    messageDigest = null;
                    break;
                }
                try {
                    messageDigest = MessageDigest.getInstance("SHA1");
                } catch (NoSuchAlgorithmException unused) {
                }
                if (messageDigest != null) {
                    break;
                }
                i++;
            }
            if (messageDigest != null) {
                return messageDigest.digest(packageInfo.signatures[0].toByteArray());
            }
        }
        return null;
    }

    public static File u(Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
        for (int i = 0; i < 100; i++) {
            File file = new File(cacheDir, str + i);
            if (file.createNewFile()) {
                return file;
            }
        }
        return null;
    }

    public static sw1 v(TextView textView) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            return new sw1(ek2.c(textView));
        }
        TextPaint textPaint = new TextPaint(textView.getPaint());
        TextDirectionHeuristic textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_LTR;
        int iA = ck2.a(textView);
        int iD = ck2.d(textView);
        if (textView.getTransformationMethod() instanceof PasswordTransformationMethod) {
            textDirectionHeuristic = TextDirectionHeuristics.LTR;
        } else if (i < 28 || (textView.getInputType() & 15) != 3) {
            boolean z = bk2.b(textView) == 1;
            switch (bk2.c(textView)) {
                case 2:
                    textDirectionHeuristic = TextDirectionHeuristics.ANYRTL_LTR;
                    break;
                case 3:
                    textDirectionHeuristic = TextDirectionHeuristics.LTR;
                    break;
                case 4:
                    textDirectionHeuristic = TextDirectionHeuristics.RTL;
                    break;
                case 5:
                    textDirectionHeuristic = TextDirectionHeuristics.LOCALE;
                    break;
                case 6:
                    break;
                case 7:
                    textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    break;
                default:
                    if (z) {
                        textDirectionHeuristic = TextDirectionHeuristics.FIRSTSTRONG_RTL;
                    }
                    break;
            }
        } else {
            byte directionality = Character.getDirectionality(ek2.b(dk2.a(bk2.d(textView)))[0].codePointAt(0));
            textDirectionHeuristic = (directionality == 1 || directionality == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
        }
        return new sw1(textPaint, textDirectionHeuristic, iA, iD);
    }

    public static void w(int i, ty tyVar, ey eyVar, boolean z) {
        xx xxVar;
        xx xxVar2;
        Iterator it;
        boolean z2;
        xx xxVar3;
        xx xxVar4;
        if (tyVar.m) {
            return;
        }
        if (!(tyVar instanceof uy) && tyVar.x() && d(tyVar)) {
            uy.R(tyVar, eyVar, new ml());
        }
        xx xxVarG = tyVar.g(ConstraintAnchor$Type.c);
        xx xxVarG2 = tyVar.g(ConstraintAnchor$Type.k);
        int iC = xxVarG.c();
        int iC2 = xxVarG2.c();
        HashSet hashSet = xxVarG.a;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.k;
        if (hashSet != null && xxVarG.c) {
            Iterator it2 = hashSet.iterator();
            while (it2.hasNext()) {
                xx xxVar5 = (xx) it2.next();
                ty tyVar2 = xxVar5.d;
                int i2 = i + 1;
                boolean zD = d(tyVar2);
                if (tyVar2.x() && zD) {
                    uy.R(tyVar2, eyVar, new ml());
                }
                xx xxVar6 = tyVar2.H;
                xx xxVar7 = tyVar2.J;
                if ((xxVar5 == xxVar6 && (xxVar4 = xxVar7.f) != null && xxVar4.c) || (xxVar5 == xxVar7 && (xxVar3 = xxVar6.f) != null && xxVar3.c)) {
                    it = it2;
                    z2 = true;
                } else {
                    it = it2;
                    z2 = false;
                }
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = tyVar2.S[0];
                if (constraintWidget$DimensionBehaviour2 != constraintWidget$DimensionBehaviour || zD) {
                    if (!tyVar2.x()) {
                        if (xxVar5 == xxVar6 && xxVar7.f == null) {
                            int iD = xxVar6.d() + iC;
                            tyVar2.F(iD, tyVar2.o() + iD);
                            w(i2, tyVar2, eyVar, z);
                        } else if (xxVar5 == xxVar7 && xxVar6.f == null) {
                            int iD2 = iC - xxVar7.d();
                            tyVar2.F(iD2 - tyVar2.o(), iD2);
                            w(i2, tyVar2, eyVar, z);
                        } else if (z2 && !tyVar2.v()) {
                            K(i2, tyVar2, eyVar, z);
                        }
                    }
                } else if (constraintWidget$DimensionBehaviour2 == constraintWidget$DimensionBehaviour && tyVar2.v >= 0 && tyVar2.u >= 0 && ((tyVar2.g0 == 8 || (tyVar2.r == 0 && tyVar2.W == 0.0f)) && !tyVar2.v() && z2 && !tyVar2.v())) {
                    L(i2, tyVar, eyVar, tyVar2, z);
                }
                it2 = it;
            }
        }
        if (tyVar instanceof d11) {
            return;
        }
        HashSet<xx> hashSet2 = xxVarG2.a;
        if (hashSet2 != null && xxVarG2.c) {
            for (xx xxVar8 : hashSet2) {
                ty tyVar3 = xxVar8.d;
                int i3 = i + 1;
                boolean zD2 = d(tyVar3);
                if (tyVar3.x() && zD2) {
                    uy.R(tyVar3, eyVar, new ml());
                }
                xx xxVar9 = tyVar3.H;
                xx xxVar10 = tyVar3.J;
                boolean z3 = (xxVar8 == xxVar9 && (xxVar2 = xxVar10.f) != null && xxVar2.c) || (xxVar8 == xxVar10 && (xxVar = xxVar9.f) != null && xxVar.c);
                ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour3 = tyVar3.S[0];
                if (constraintWidget$DimensionBehaviour3 != constraintWidget$DimensionBehaviour || zD2) {
                    if (!tyVar3.x()) {
                        if (xxVar8 == xxVar9 && xxVar10.f == null) {
                            int iD3 = xxVar9.d() + iC2;
                            tyVar3.F(iD3, tyVar3.o() + iD3);
                            w(i3, tyVar3, eyVar, z);
                        } else if (xxVar8 == xxVar10 && xxVar9.f == null) {
                            int iD4 = iC2 - xxVar10.d();
                            tyVar3.F(iD4 - tyVar3.o(), iD4);
                            w(i3, tyVar3, eyVar, z);
                        } else if (z3 && !tyVar3.v()) {
                            K(i3, tyVar3, eyVar, z);
                        }
                    }
                } else if (constraintWidget$DimensionBehaviour3 == constraintWidget$DimensionBehaviour && tyVar3.v >= 0 && tyVar3.u >= 0) {
                    if (tyVar3.g0 != 8) {
                        if (tyVar3.r == 0) {
                            if (tyVar3.W == 0.0f) {
                            }
                        }
                    }
                    if (!tyVar3.v() && z3 && !tyVar3.v()) {
                        L(i3, tyVar, eyVar, tyVar3, z);
                    }
                }
            }
        }
        tyVar.m = true;
    }

    public static int x(int i, int i2, float f2) {
        return qu.b(qu.d(i2, Math.round(Color.alpha(i2) * f2)), i);
    }

    public static MappedByteBuffer y(Context context, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorA = xo2.a(context.getContentResolver(), uri, "r", null);
            if (parcelFileDescriptorA == null) {
                if (parcelFileDescriptorA != null) {
                    parcelFileDescriptorA.close();
                }
                return null;
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(parcelFileDescriptorA.getFileDescriptor());
                try {
                    FileChannel channel = fileInputStream.getChannel();
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size());
                    fileInputStream.close();
                    parcelFileDescriptorA.close();
                    return map;
                } finally {
                }
            } finally {
            }
        } catch (IOException unused) {
            return null;
        }
    }

    public static final String z(ContextTrack contextTrack, String str) {
        String str2 = (String) contextTrack.b().get(str);
        if (str2 == null) {
            return null;
        }
        if (ng2.c0(str2)) {
            str2 = null;
        }
        return str2;
    }
}
