package androidx.compose.ui.platform;

import A.C0006c;
import A.G0;
import A.I0;
import A2.l;
import A7.m;
import B5.RunnableC0079c;
import B8.A;
import C0.W;
import C0.Z;
import D0.c;
import E0.AbstractC0156f;
import E0.AbstractC0164n;
import E0.C0168s;
import E0.C0170u;
import E0.F;
import E0.H;
import E0.U;
import E0.Y;
import E0.d0;
import E0.i0;
import E0.k0;
import E0.n0;
import E0.o0;
import E0.q0;
import F0.A0;
import F0.B0;
import F0.C0;
import F0.C0196c0;
import F0.C0198d0;
import F0.C0203g;
import F0.C0205h;
import F0.C0212k0;
import F0.C0215m;
import F0.C0218n0;
import F0.C0219o;
import F0.C0220o0;
import F0.C0221p;
import F0.C0223q;
import F0.C0226s;
import F0.C0229t0;
import F0.C0230u;
import F0.C0232v;
import F0.C0234w;
import F0.C0236x;
import F0.C0237x0;
import F0.M;
import F0.N;
import F0.N0;
import F0.P;
import F0.R0;
import F0.S0;
import F0.T;
import F0.ViewOnDragListenerC0235w0;
import F0.ViewTreeObserverOnGlobalLayoutListenerC0209j;
import F0.ViewTreeObserverOnScrollChangedListenerC0211k;
import F0.ViewTreeObserverOnTouchModeChangeListenerC0213l;
import F0.W0;
import F0.X0;
import F0.Z0;
import F0.a1;
import F0.d1;
import F0.e1;
import F0.r;
import L0.p;
import M.t;
import N0.K;
import N1.C0420a;
import P.C;
import S0.InterfaceC0701m;
import S0.InterfaceC0702n;
import T.C0735d;
import T.C0738e0;
import T.D;
import T.S;
import T0.B;
import T0.z;
import T1.i;
import V.d;
import a.AbstractC0904a;
import a1.a;
import a1.k;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.bydauto.ota.tsimpl.TestOtaManagerImplCallback;
import android.os.Build;
import android.os.Trace;
import android.util.LongSparseArray;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.animation.AnimationUtils;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import androidx.compose.ui.focus.b;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.semantics.EmptySemanticsElement;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.a0;
import e0.C1182v;
import e0.C1184x;
import g0.n;
import g0.o;
import g1.AbstractC1274a;
import h0.C1339a;
import h0.InterfaceC1340b;
import h0.f;
import h3.y;
import h6.e;
import i0.C1398b;
import i0.ViewOnAttachStateChangeListenerC1400d;
import j0.InterfaceC1533a;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import kotlin.Metadata;
import l0.AbstractC1713d;
import l0.C1711b;
import l0.C1715f;
import l0.C1727r;
import l0.InterfaceC1716g;
import l3.C1764u;
import l5.C1778f;
import m7.InterfaceC1849a;
import m7.h;
import m7.q;
import n0.AbstractC1861K;
import n0.C1854D;
import n0.C1863M;
import n0.C1876e;
import n0.InterfaceC1897z;
import n7.AbstractC1999n;
import q0.C2164c;
import q7.InterfaceC2194h;
import u0.C2488b;
import u0.InterfaceC2487a;
import v0.C2531a;
import v0.C2533c;
import v0.InterfaceC2532b;
import w8.C2607b;
import x.AbstractC2641j;
import x3.AbstractC2673a;
import y0.C2722B;
import y0.C2727e;
import y0.InterfaceC2736n;
import y0.u;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000´\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00032\u00020\u0004:\u0003î\u0001\u000bJ\u0017\u0010\b\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0005H\u0016¢\u0006\u0004\b\b\u0010\tJ!\u0010\r\u001a\u00020\u00072\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00070\n¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0019\u001a\u00020\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018R+\u0010\"\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001a8V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010(\u001a\u00020#8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b&\u0010'R*\u00101\u001a\u00020)2\u0006\u0010*\u001a\u00020)8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00107\u001a\u0002028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b3\u00104\u001a\u0004\b5\u00106R\u001a\u0010=\u001a\u0002088\u0016X\u0096\u0004¢\u0006\f\n\u0004\b9\u0010:\u001a\u0004\b;\u0010<R\u001a\u0010C\u001a\u00020>8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b?\u0010@\u001a\u0004\bA\u0010BR\u001a\u0010I\u001a\u00020D8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bE\u0010F\u001a\u0004\bG\u0010HR\"\u0010Q\u001a\u00020J8\u0000@\u0000X\u0080\u000e¢\u0006\u0012\n\u0004\bK\u0010L\u001a\u0004\bM\u0010N\"\u0004\bO\u0010PR\u001a\u0010W\u001a\u00020R8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bU\u0010VR\u001a\u0010]\u001a\u00020X8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bY\u0010Z\u001a\u0004\b[\u0010\\R\u001a\u0010c\u001a\u00020^8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b_\u0010`\u001a\u0004\ba\u0010bR.\u0010j\u001a\u000e\u0012\u0004\u0012\u00020d\u0012\u0004\u0012\u00020\u00070\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\be\u0010f\u001a\u0004\bg\u0010h\"\u0004\bi\u0010\u000eR\u001a\u0010p\u001a\u00020k8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bl\u0010m\u001a\u0004\bn\u0010oR\u001a\u0010v\u001a\u00020q8\u0016X\u0096\u0004¢\u0006\f\n\u0004\br\u0010s\u001a\u0004\bt\u0010uR)\u0010\u0080\u0001\u001a\u00020w8\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0004\bx\u0010y\u0012\u0004\b~\u0010\u007f\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R \u0010\u0086\u0001\u001a\u00030\u0081\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0082\u0001\u0010\u0083\u0001\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R/\u0010\u008d\u0001\u001a\u00020\u00058\u0000@\u0000X\u0081\u000e¢\u0006\u001e\n\u0006\b\u0087\u0001\u0010\u0088\u0001\u0012\u0005\b\u008c\u0001\u0010\u007f\u001a\u0006\b\u0089\u0001\u0010\u008a\u0001\"\u0005\b\u008b\u0001\u0010\tR5\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000b8B@BX\u0082\u008e\u0002¢\u0006\u0017\n\u0005\b\u008e\u0001\u0010\u001d\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R\"\u0010\u0097\u0001\u001a\u0004\u0018\u00010\u000b8FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\b\u0094\u0001\u0010\u0095\u0001\u001a\u0006\b\u0096\u0001\u0010\u0090\u0001R \u0010\u009d\u0001\u001a\u00030\u0098\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0099\u0001\u0010\u009a\u0001\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R \u0010£\u0001\u001a\u00030\u009e\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u009f\u0001\u0010 \u0001\u001a\u0006\b¡\u0001\u0010¢\u0001R'\u0010ª\u0001\u001a\u00030¤\u00018\u0016X\u0097\u0004¢\u0006\u0017\n\u0006\b¥\u0001\u0010¦\u0001\u0012\u0005\b©\u0001\u0010\u007f\u001a\u0006\b§\u0001\u0010¨\u0001R3\u0010±\u0001\u001a\u00030«\u00012\u0007\u0010\u001b\u001a\u00030«\u00018V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b¬\u0001\u0010\u001d\u001a\u0006\b\u00ad\u0001\u0010®\u0001\"\u0006\b¯\u0001\u0010°\u0001R3\u0010¸\u0001\u001a\u00030²\u00012\u0007\u0010\u001b\u001a\u00030²\u00018V@RX\u0096\u008e\u0002¢\u0006\u0017\n\u0005\b³\u0001\u0010\u001d\u001a\u0006\b´\u0001\u0010µ\u0001\"\u0006\b¶\u0001\u0010·\u0001R \u0010¾\u0001\u001a\u00030¹\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bº\u0001\u0010»\u0001\u001a\u0006\b¼\u0001\u0010½\u0001R \u0010Ä\u0001\u001a\u00030¿\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÀ\u0001\u0010Á\u0001\u001a\u0006\bÂ\u0001\u0010Ã\u0001R \u0010Ê\u0001\u001a\u00030Å\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÆ\u0001\u0010Ç\u0001\u001a\u0006\bÈ\u0001\u0010É\u0001R \u0010Ð\u0001\u001a\u00030Ë\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001R\u0017\u0010Ó\u0001\u001a\u00020\u00118VX\u0096\u0004¢\u0006\b\u001a\u0006\bÑ\u0001\u0010Ò\u0001R\u0018\u0010×\u0001\u001a\u00030Ô\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÕ\u0001\u0010Ö\u0001R\u001a\u0010Û\u0001\u001a\u0005\u0018\u00010Ø\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bÙ\u0001\u0010Ú\u0001R\u0018\u0010ß\u0001\u001a\u00030Ü\u00018@X\u0080\u0004¢\u0006\b\u001a\u0006\bÝ\u0001\u0010Þ\u0001R\u0017\u0010á\u0001\u001a\u00020\u00058VX\u0096\u0004¢\u0006\b\u001a\u0006\bà\u0001\u0010\u008a\u0001R\u0016\u0010ã\u0001\u001a\u00020w8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bâ\u0001\u0010{R\u0018\u0010ç\u0001\u001a\u00030ä\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bå\u0001\u0010æ\u0001R\u0018\u0010ë\u0001\u001a\u00030è\u00018VX\u0096\u0004¢\u0006\b\u001a\u0006\bé\u0001\u0010ê\u0001R\u0016\u0010í\u0001\u001a\u00020w8@X\u0080\u0004¢\u0006\u0007\u001a\u0005\bì\u0001\u0010{¨\u0006ï\u0001"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeView;", "Landroid/view/ViewGroup;", "Landroidx/compose/ui/node/Owner;", "", "Landroidx/lifecycle/DefaultLifecycleObserver;", "", "intervalMillis", "Lm7/q;", "setAccessibilityEventBatchIntervalMillis", "(J)V", "Lkotlin/Function1;", "LF0/m;", "callback", "setOnViewTreeOwnersAvailable", "(Lz7/k;)V", "", "accessibilityId", "Landroid/view/View;", "findViewByAccessibilityIdTraversal", "(I)Landroid/view/View;", "LE0/H;", "n", "LE0/H;", "getSharedDrawScope", "()LE0/H;", "sharedDrawScope", "La1/b;", "<set-?>", "o", "LT/X;", "getDensity", "()La1/b;", "setDensity", "(La1/b;)V", "density", "Ll0/g;", "p", "Ll0/g;", "getFocusOwner", "()Ll0/g;", "focusOwner", "Lq7/h;", "value", "q", "Lq7/h;", "getCoroutineContext", "()Lq7/h;", "setCoroutineContext", "(Lq7/h;)V", "coroutineContext", "Lj0/a;", "r", "Lj0/a;", "getDragAndDropManager", "()Lj0/a;", "dragAndDropManager", "LE0/F;", "u", "LE0/F;", "getRoot", "()LE0/F;", "root", "LE0/o0;", "v", "LE0/o0;", "getRootForTest", "()LE0/o0;", "rootForTest", "LL0/p;", "w", "LL0/p;", "getSemanticsOwner", "()LL0/p;", "semanticsOwner", "Li0/d;", "y", "Li0/d;", "getContentCaptureManager$ui_release", "()Li0/d;", "setContentCaptureManager$ui_release", "(Li0/d;)V", "contentCaptureManager", "LF0/g;", "z", "LF0/g;", "getAccessibilityManager", "()LF0/g;", "accessibilityManager", "Ln0/z;", "A", "Ln0/z;", "getGraphicsContext", "()Ln0/z;", "graphicsContext", "Lh0/f;", "B", "Lh0/f;", "getAutofillTree", "()Lh0/f;", "autofillTree", "Landroid/content/res/Configuration;", "I", "Lz7/k;", "getConfigurationChangeObserver", "()Lz7/k;", "setConfigurationChangeObserver", "configurationChangeObserver", "LF0/h;", "L", "LF0/h;", "getClipboardManager", "()LF0/h;", "clipboardManager", "LE0/k0;", "M", "LE0/k0;", "getSnapshotObserver", "()LE0/k0;", "snapshotObserver", "", "N", "Z", "getShowLayoutBounds", "()Z", "setShowLayoutBounds", "(Z)V", "getShowLayoutBounds$annotations", "()V", "showLayoutBounds", "LF0/W0;", "T", "LF0/W0;", "getViewConfiguration", "()LF0/W0;", "viewConfiguration", "c0", "J", "getLastMatrixRecalculationAnimationTime$ui_release", "()J", "setLastMatrixRecalculationAnimationTime$ui_release", "getLastMatrixRecalculationAnimationTime$ui_release$annotations", "lastMatrixRecalculationAnimationTime", "g0", "get_viewTreeOwners", "()LF0/m;", "set_viewTreeOwners", "(LF0/m;)V", "_viewTreeOwners", "h0", "LT/Q0;", "getViewTreeOwners", "viewTreeOwners", "LT0/z;", "n0", "LT0/z;", "getTextInputService", "()LT0/z;", "textInputService", "LF0/R0;", "p0", "LF0/R0;", "getSoftwareKeyboardController", "()LF0/R0;", "softwareKeyboardController", "LS0/m;", "q0", "LS0/m;", "getFontLoader", "()LS0/m;", "getFontLoader$annotations", "fontLoader", "LS0/n;", "r0", "getFontFamilyResolver", "()LS0/n;", "setFontFamilyResolver", "(LS0/n;)V", "fontFamilyResolver", "La1/k;", "t0", "getLayoutDirection", "()La1/k;", "setLayoutDirection", "(La1/k;)V", "layoutDirection", "Lu0/a;", "u0", "Lu0/a;", "getHapticFeedBack", "()Lu0/a;", "hapticFeedBack", "LD0/c;", "w0", "LD0/c;", "getModifierLocalManager", "()LD0/c;", "modifierLocalManager", "LF0/S0;", "x0", "LF0/S0;", "getTextToolbar", "()LF0/S0;", "textToolbar", "Ly0/n;", "J0", "Ly0/n;", "getPointerIconService", "()Ly0/n;", "pointerIconService", "getView", "()Landroid/view/View;", "view", "LF0/d1;", "getWindowInfo", "()LF0/d1;", "windowInfo", "Lh0/b;", "getAutofill", "()Lh0/b;", "autofill", "LF0/n0;", "getAndroidViewsHandler$ui_release", "()LF0/n0;", "androidViewsHandler", "getMeasureIteration", "measureIteration", "getHasPendingMeasureOrLayout", "hasPendingMeasureOrLayout", "LC0/U;", "getPlacementScope", "()LC0/U;", "placementScope", "Lv0/b;", "getInputModeManager", "()Lv0/b;", "inputModeManager", "getScrollCaptureInProgress$ui_release", "scrollCaptureInProgress", "F0/B0", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AndroidComposeView extends ViewGroup implements Owner, o0, DefaultLifecycleObserver {

    /* JADX INFO: renamed from: K0, reason: collision with root package name */
    public static Class f11616K0;

    /* JADX INFO: renamed from: L0, reason: collision with root package name */
    public static Method f11617L0;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C1876e f11618A;

    /* JADX INFO: renamed from: A0, reason: collision with root package name */
    public final t f11619A0;

    /* JADX INFO: renamed from: B, reason: from kotlin metadata */
    public final f autofillTree;
    public final d B0;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final ArrayList f11620C;

    /* JADX INFO: renamed from: C0, reason: collision with root package name */
    public final RunnableC0079c f11621C0;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public ArrayList f11622D;

    /* JADX INFO: renamed from: D0, reason: collision with root package name */
    public final l f11623D0;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public boolean f11624E;

    /* JADX INFO: renamed from: E0, reason: collision with root package name */
    public boolean f11625E0;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public boolean f11626F;

    /* JADX INFO: renamed from: F0, reason: collision with root package name */
    public final C0236x f11627F0;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final C2727e f11628G;
    public final C0220o0 G0;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C f11629H;

    /* JADX INFO: renamed from: H0, reason: collision with root package name */
    public boolean f11630H0;

    /* JADX INFO: renamed from: I, reason: collision with root package name and from kotlin metadata */
    public InterfaceC2782k configurationChangeObserver;

    /* JADX INFO: renamed from: I0, reason: collision with root package name */
    public final K0.l f11632I0;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final C1339a f11633J;

    /* JADX INFO: renamed from: J0, reason: collision with root package name */
    public final C0232v f11634J0;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public boolean f11635K;

    /* JADX INFO: renamed from: L, reason: collision with root package name and from kotlin metadata */
    public final C0205h clipboardManager;

    /* JADX INFO: renamed from: M, reason: collision with root package name and from kotlin metadata */
    public final k0 snapshotObserver;

    /* JADX INFO: renamed from: N, reason: collision with root package name and from kotlin metadata */
    public boolean showLayoutBounds;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public C0218n0 f11639O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public C0237x0 f11640P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public a f11641Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public boolean f11642R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public final U f11643S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public final C0212k0 f11644T;

    /* JADX INFO: renamed from: U, reason: collision with root package name */
    public long f11645U;

    /* JADX INFO: renamed from: V, reason: collision with root package name */
    public final int[] f11646V;

    /* JADX INFO: renamed from: W, reason: collision with root package name */
    public final float[] f11647W;

    /* JADX INFO: renamed from: a0, reason: collision with root package name */
    public final float[] f11648a0;

    /* JADX INFO: renamed from: b0, reason: collision with root package name */
    public final float[] f11649b0;

    /* JADX INFO: renamed from: c0, reason: collision with root package name and from kotlin metadata */
    public long lastMatrixRecalculationAnimationTime;

    /* JADX INFO: renamed from: d0, reason: collision with root package name */
    public boolean f11651d0;

    /* JADX INFO: renamed from: e0, reason: collision with root package name */
    public long f11652e0;

    /* JADX INFO: renamed from: f0, reason: collision with root package name */
    public boolean f11653f0;

    /* JADX INFO: renamed from: g0, reason: collision with root package name */
    public final C0738e0 f11654g0;

    /* JADX INFO: renamed from: h0, reason: collision with root package name */
    public final D f11655h0;

    /* JADX INFO: renamed from: i0, reason: collision with root package name */
    public InterfaceC2782k f11656i0;

    /* JADX INFO: renamed from: j0, reason: collision with root package name */
    public final ViewTreeObserverOnGlobalLayoutListenerC0209j f11657j0;

    /* JADX INFO: renamed from: k0, reason: collision with root package name */
    public final ViewTreeObserverOnScrollChangedListenerC0211k f11658k0;
    public long l;

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public final ViewTreeObserverOnTouchModeChangeListenerC0213l f11659l0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f11660m;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public final B f11661m0;

    /* JADX INFO: renamed from: n, reason: collision with root package name and from kotlin metadata */
    public final H sharedDrawScope;

    /* JADX INFO: renamed from: n0, reason: collision with root package name and from kotlin metadata */
    public final z textInputService;
    public final C0738e0 o;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public final AtomicReference f11664o0;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final b f11665p;

    /* JADX INFO: renamed from: p0, reason: collision with root package name */
    public final C0229t0 f11666p0;

    /* JADX INFO: renamed from: q, reason: collision with root package name and from kotlin metadata */
    public InterfaceC2194h coroutineContext;

    /* JADX INFO: renamed from: q0, reason: collision with root package name */
    public final B0 f11668q0;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ViewOnDragListenerC0235w0 f11669r;

    /* JADX INFO: renamed from: r0, reason: collision with root package name */
    public final C0738e0 f11670r0;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final e1 f11671s;

    /* JADX INFO: renamed from: s0, reason: collision with root package name */
    public int f11672s0;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C1764u f11673t;

    /* JADX INFO: renamed from: t0, reason: collision with root package name */
    public final C0738e0 f11674t0;

    /* JADX INFO: renamed from: u, reason: collision with root package name and from kotlin metadata */
    public final F root;

    /* JADX INFO: renamed from: u0, reason: collision with root package name */
    public final C2488b f11676u0;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final AndroidComposeView f11677v;

    /* JADX INFO: renamed from: v0, reason: collision with root package name */
    public final C2533c f11678v0;

    /* JADX INFO: renamed from: w, reason: collision with root package name and from kotlin metadata */
    public final p semanticsOwner;

    /* JADX INFO: renamed from: w0, reason: collision with root package name and from kotlin metadata */
    public final c modifierLocalManager;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final M f11681x;

    /* JADX INFO: renamed from: x0, reason: collision with root package name */
    public final C0198d0 f11682x0;

    /* JADX INFO: renamed from: y, reason: collision with root package name and from kotlin metadata */
    public ViewOnAttachStateChangeListenerC1400d contentCaptureManager;

    /* JADX INFO: renamed from: y0, reason: collision with root package name */
    public MotionEvent f11684y0;

    /* JADX INFO: renamed from: z, reason: collision with root package name and from kotlin metadata */
    public final C0203g accessibilityManager;

    /* JADX INFO: renamed from: z0, reason: collision with root package name */
    public long f11686z0;

    /* JADX WARN: Type inference failed for: r2v18, types: [F0.j] */
    /* JADX WARN: Type inference failed for: r2v19, types: [F0.k] */
    /* JADX WARN: Type inference failed for: r2v20, types: [F0.l] */
    public AndroidComposeView(Context context, InterfaceC2194h interfaceC2194h) {
        super(context);
        this.l = 9205357640488583168L;
        this.f11660m = true;
        this.sharedDrawScope = new H();
        a1.d dVarF = AbstractC1274a.f(context);
        S s6 = S.o;
        this.o = C0735d.O(dVarF, s6);
        L0.d dVar = new L0.d();
        EmptySemanticsElement emptySemanticsElement = new EmptySemanticsElement(dVar);
        this.f11665p = new b(new C0223q(1, this, AndroidComposeView.class, "registerOnEndApplyChangesListener", "registerOnEndApplyChangesListener(Lkotlin/jvm/functions/Function0;)V", 0, 0), new r(2, this, AndroidComposeView.class, "onRequestFocusForOwner", "onRequestFocusForOwner-7o62pno(Landroidx/compose/ui/focus/FocusDirection;Landroidx/compose/ui/geometry/Rect;)Z", 0, 0), new C0223q(1, this, AndroidComposeView.class, "onMoveFocusInChildren", "onMoveFocusInChildren-3ESFkO8(I)Z", 0, 1), new C0221p(0, this, AndroidComposeView.class, "onClearFocusForOwner", "onClearFocusForOwner()V", 0, 1), new C0221p(0, this, AndroidComposeView.class, "onFetchFocusRect", "onFetchFocusRect()Landroidx/compose/ui/geometry/Rect;", 0, 2), new C0226s(this, AndroidComposeView.class, "layoutDirection", "getLayoutDirection()Landroidx/compose/ui/unit/LayoutDirection;", 0));
        ViewOnDragListenerC0235w0 viewOnDragListenerC0235w0 = new ViewOnDragListenerC0235w0();
        this.coroutineContext = interfaceC2194h;
        this.f11669r = viewOnDragListenerC0235w0;
        this.f11671s = new e1();
        o oVarA = androidx.compose.ui.input.key.a.a(g0.l.f14684a, new C0230u(this, 0));
        o oVarA2 = androidx.compose.ui.input.rotary.a.a();
        this.f11673t = new C1764u(6);
        F f10 = new F(3, 0, false);
        f10.x0(Z.f880b);
        f10.v0(getDensity());
        f10.y0(emptySemanticsElement.k(oVarA2).k(oVarA).k(((b) getFocusOwner()).f11572i).k(viewOnDragListenerC0235w0.c));
        this.root = f10;
        this.f11677v = this;
        this.semanticsOwner = new p(getRoot(), dVar);
        M m10 = new M(this);
        this.f11681x = m10;
        this.contentCaptureManager = new ViewOnAttachStateChangeListenerC1400d(this, new C0221p(0, this, T.class, "getContentCaptureSessionCompat", "getContentCaptureSessionCompat(Landroid/view/View;)Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", 1, 0));
        C0203g c0203g = new C0203g();
        Object systemService = context.getSystemService("accessibility");
        m.d(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = c0203g;
        this.f11618A = new C1876e(this);
        this.autofillTree = new f();
        this.f11620C = new ArrayList();
        this.f11628G = new C2727e();
        F root = getRoot();
        C c = new C();
        c.f5203b = root;
        c.c = new y((C0170u) root.f1469H.c);
        c.d = new C2607b(6);
        c.f5204e = new C0168s();
        this.f11629H = c;
        this.configurationChangeObserver = C0219o.f2235n;
        this.f11633J = new C1339a(this, getAutofillTree());
        this.clipboardManager = new C0205h(context);
        this.snapshotObserver = new k0(new C0230u(this, 1));
        this.f11643S = new U(getRoot());
        this.f11644T = new C0212k0(ViewConfiguration.get(context));
        this.f11645U = AbstractC2673a.d(Integer.MAX_VALUE, Integer.MAX_VALUE);
        this.f11646V = new int[]{0, 0};
        this.f11647W = C1854D.a();
        this.f11648a0 = C1854D.a();
        this.f11649b0 = C1854D.a();
        this.lastMatrixRecalculationAnimationTime = -1L;
        this.f11652e0 = 9187343241974906880L;
        this.f11653f0 = true;
        S s10 = S.f8224q;
        this.f11654g0 = C0735d.O(null, s10);
        this.f11655h0 = C0735d.G(new C0236x(this, 1));
        this.f11657j0 = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: F0.j
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public final void onGlobalLayout() {
                Class cls = AndroidComposeView.f11616K0;
                this.l.F();
            }
        };
        this.f11658k0 = new ViewTreeObserver.OnScrollChangedListener() { // from class: F0.k
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                Class cls = AndroidComposeView.f11616K0;
                this.f2216a.F();
            }
        };
        this.f11659l0 = new ViewTreeObserver.OnTouchModeChangeListener() { // from class: F0.l
            @Override // android.view.ViewTreeObserver.OnTouchModeChangeListener
            public final void onTouchModeChanged(boolean z6) {
                C2533c c2533c = this.l.f11678v0;
                int i7 = z6 ? 1 : 2;
                c2533c.getClass();
                c2533c.f22941a.setValue(new C2531a(i7));
            }
        };
        B b10 = new B(getView(), this);
        this.f11661m0 = b10;
        this.textInputService = new z(b10);
        this.f11664o0 = new AtomicReference(null);
        this.f11666p0 = new C0229t0(getTextInputService());
        this.f11668q0 = new B0();
        this.f11670r0 = C0735d.O(io.sentry.config.a.V(context), s6);
        Configuration configuration = context.getResources().getConfiguration();
        int i7 = Build.VERSION.SDK_INT;
        this.f11672s0 = i7 >= 31 ? configuration.fontWeightAdjustment : 0;
        int layoutDirection = context.getResources().getConfiguration().getLayoutDirection();
        k kVar = k.l;
        k kVar2 = layoutDirection != 0 ? layoutDirection != 1 ? null : k.f11038m : kVar;
        this.f11674t0 = C0735d.O(kVar2 != null ? kVar2 : kVar, s10);
        this.f11676u0 = new C2488b(this);
        this.f11678v0 = new C2533c(isInTouchMode() ? 1 : 2);
        this.modifierLocalManager = new c(this);
        this.f11682x0 = new C0198d0(this);
        this.f11619A0 = new t(6);
        this.B0 = new d(new InterfaceC2772a[16]);
        this.f11621C0 = new RunnableC0079c(this, 3);
        this.f11623D0 = new l(this, 1);
        this.f11627F0 = new C0236x(this, 0);
        this.G0 = new C0220o0();
        addOnAttachStateChangeListener(this.contentCaptureManager);
        setWillNotDraw(false);
        setFocusable(true);
        F0.S.f2109a.a(this, 1, false);
        setFocusableInTouchMode(true);
        setClipChildren(false);
        F1.H.g(this, m10);
        setOnDragListener(viewOnDragListenerC0235w0);
        getRoot().b(this);
        N.f2086a.a(this);
        this.f11632I0 = i7 >= 31 ? new K0.l() : null;
        this.f11634J0 = new C0232v(this);
    }

    public static final void a(AndroidComposeView androidComposeView, int i7, AccessibilityNodeInfo accessibilityNodeInfo, String str) {
        int iE;
        M m10 = androidComposeView.f11681x;
        if (m.a(str, m10.f2056E)) {
            int iE2 = m10.f2054C.e(i7);
            if (iE2 != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, iE2);
                return;
            }
            return;
        }
        if (!m.a(str, m10.f2057F) || (iE = m10.f2055D.e(i7)) == -1) {
            return;
        }
        accessibilityNodeInfo.getExtras().putInt(str, iE);
    }

    public static final boolean e(AndroidComposeView androidComposeView, C1711b c1711b, m0.d dVar) {
        Integer numI;
        if (androidComposeView.isFocused() || androidComposeView.hasFocus()) {
            return true;
        }
        return super.requestFocus((c1711b == null || (numI = AbstractC1713d.I(c1711b.f18325a)) == null) ? 130 : numI.intValue(), dVar != null ? AbstractC1861K.B(dVar) : null);
    }

    public static void f(ViewGroup viewGroup) {
        int childCount = viewGroup.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = viewGroup.getChildAt(i7);
            if (childAt instanceof AndroidComposeView) {
                ((AndroidComposeView) childAt).s();
            } else if (childAt instanceof ViewGroup) {
                f((ViewGroup) childAt);
            }
        }
    }

    public static long g(int i7) {
        long j3;
        long j10;
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (mode == Integer.MIN_VALUE) {
            j3 = ((long) 0) << 32;
        } else {
            if (mode != 0) {
                if (mode != 1073741824) {
                    throw new IllegalStateException();
                }
                j10 = size;
                j3 = j10 << 32;
                return j3 | j10;
            }
            j3 = ((long) 0) << 32;
            size = Integer.MAX_VALUE;
        }
        j10 = size;
        return j3 | j10;
    }

    @InterfaceC1849a
    public static /* synthetic */ void getFontLoader$annotations() {
    }

    public static /* synthetic */ void getLastMatrixRecalculationAnimationTime$ui_release$annotations() {
    }

    public static /* synthetic */ void getShowLayoutBounds$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final C0215m get_viewTreeOwners() {
        return (C0215m) this.f11654g0.getValue();
    }

    public static void j(F f10) {
        f10.P();
        d dVarL = f10.L();
        int i7 = dVarL.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVarL.l;
            int i10 = 0;
            do {
                j((F) objArr[i10]);
                i10++;
            } while (i10 < i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x007c A[LOOP:0: B:22:0x004c->B:37:0x007c, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x007f A[EDGE_INSN: B:39:0x007f->B:38:0x007f BREAK  A[LOOP:0: B:22:0x004c->B:37:0x007c], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean l(android.view.MotionEvent r6) {
        /*
            float r0 = r6.getX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getRawX()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            float r0 = r6.getRawY()
            boolean r1 = java.lang.Float.isInfinite(r0)
            if (r1 != 0) goto L44
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L44
            r0 = r2
            goto L45
        L44:
            r0 = r3
        L45:
            if (r0 != 0) goto L7f
            int r1 = r6.getPointerCount()
            r4 = r3
        L4c:
            if (r4 >= r1) goto L7f
            float r0 = r6.getX(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L79
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L79
            float r0 = r6.getY(r4)
            boolean r5 = java.lang.Float.isInfinite(r0)
            if (r5 != 0) goto L79
            boolean r0 = java.lang.Float.isNaN(r0)
            if (r0 != 0) goto L79
            F0.G0 r0 = F0.G0.f2023a
            boolean r0 = r0.a(r6, r4)
            if (r0 != 0) goto L77
            goto L79
        L77:
            r0 = r2
            goto L7a
        L79:
            r0 = r3
        L7a:
            if (r0 != 0) goto L7f
            int r4 = r4 + 1
            goto L4c
        L7f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.l(android.view.MotionEvent):boolean");
    }

    private void setDensity(a1.b bVar) {
        this.o.setValue(bVar);
    }

    private void setFontFamilyResolver(InterfaceC0702n interfaceC0702n) {
        this.f11670r0.setValue(interfaceC0702n);
    }

    private void setLayoutDirection(k kVar) {
        this.f11674t0.setValue(kVar);
    }

    private final void set_viewTreeOwners(C0215m c0215m) {
        this.f11654g0.setValue(c0215m);
    }

    public final void A(F f10) {
        if (isLayoutRequested() || !isAttachedToWindow()) {
            return;
        }
        if (f10 != null) {
            while (f10 != null && f10.C() == 1) {
                if (!this.f11642R) {
                    F fG = f10.G();
                    if (fG == null) {
                        break;
                    }
                    long j3 = ((C0170u) fG.f1469H.c).o;
                    if (a.f(j3) && a.e(j3)) {
                        break;
                    }
                }
                f10 = f10.G();
            }
            if (f10 == getRoot()) {
                requestLayout();
                return;
            }
        }
        if (getWidth() == 0 || getHeight() == 0) {
            requestLayout();
        } else {
            invalidate();
        }
    }

    public final long B(long j3) {
        y();
        float fD = m0.c.d(j3) - m0.c.d(this.f11652e0);
        float fE = m0.c.e(j3) - m0.c.e(this.f11652e0);
        return C1854D.b(e.a(fD, fE), this.f11649b0);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int C(android.view.MotionEvent r8) {
        /*
            r7 = this;
            boolean r0 = r7.f11630H0
            r1 = 0
            if (r0 == 0) goto L1a
            r7.f11630H0 = r1
            int r0 = r8.getMetaState()
            F0.e1 r2 = r7.f11671s
            r2.getClass()
            y0.u r2 = new y0.u
            r2.<init>(r0)
            T.e0 r0 = F0.e1.f2192b
            r0.setValue(r2)
        L1a:
            y0.e r0 = r7.f11628G
            l5.f r2 = r0.a(r8, r7)
            P.C r3 = r7.f11629H
            if (r2 == 0) goto L75
            java.lang.Object r1 = r2.f18638m
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            int r4 = r1.size()
            int r4 = r4 + (-1)
            if (r4 < 0) goto L43
        L30:
            int r5 = r4 + (-1)
            java.lang.Object r4 = r1.get(r4)
            r6 = r4
            y0.s r6 = (y0.s) r6
            boolean r6 = r6.f24091e
            if (r6 == 0) goto L3e
            goto L44
        L3e:
            if (r5 >= 0) goto L41
            goto L43
        L41:
            r4 = r5
            goto L30
        L43:
            r4 = 0
        L44:
            y0.s r4 = (y0.s) r4
            if (r4 == 0) goto L4c
            long r4 = r4.d
            r7.l = r4
        L4c:
            boolean r1 = r7.m(r8)
            int r1 = r3.m(r2, r7, r1)
            int r7 = r8.getActionMasked()
            if (r7 == 0) goto L5d
            r2 = 5
            if (r7 != r2) goto L78
        L5d:
            r7 = r1 & 1
            if (r7 == 0) goto L62
            goto L78
        L62:
            int r7 = r8.getActionIndex()
            int r7 = r8.getPointerId(r7)
            android.util.SparseBooleanArray r8 = r0.c
            r8.delete(r7)
            android.util.SparseLongArray r8 = r0.f24055b
            r8.delete(r7)
            goto L78
        L75:
            r3.n()
        L78:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.C(android.view.MotionEvent):int");
    }

    public final void D(MotionEvent motionEvent, int i7, long j3, boolean z6) {
        int actionMasked = motionEvent.getActionMasked();
        int actionIndex = -1;
        if (actionMasked != 1) {
            if (actionMasked == 6) {
                actionIndex = motionEvent.getActionIndex();
            }
        } else if (i7 != 9 && i7 != 10) {
            actionIndex = 0;
        }
        int pointerCount = motionEvent.getPointerCount() - (actionIndex >= 0 ? 1 : 0);
        if (pointerCount == 0) {
            return;
        }
        MotionEvent.PointerProperties[] pointerPropertiesArr = new MotionEvent.PointerProperties[pointerCount];
        for (int i10 = 0; i10 < pointerCount; i10++) {
            pointerPropertiesArr[i10] = new MotionEvent.PointerProperties();
        }
        MotionEvent.PointerCoords[] pointerCoordsArr = new MotionEvent.PointerCoords[pointerCount];
        for (int i11 = 0; i11 < pointerCount; i11++) {
            pointerCoordsArr[i11] = new MotionEvent.PointerCoords();
        }
        int i12 = 0;
        while (i12 < pointerCount) {
            int i13 = ((actionIndex < 0 || i12 < actionIndex) ? 0 : 1) + i12;
            motionEvent.getPointerProperties(i13, pointerPropertiesArr[i12]);
            MotionEvent.PointerCoords pointerCoords = pointerCoordsArr[i12];
            motionEvent.getPointerCoords(i13, pointerCoords);
            long jO = o(e.a(pointerCoords.x, pointerCoords.y));
            pointerCoords.x = m0.c.d(jO);
            pointerCoords.y = m0.c.e(jO);
            i12++;
        }
        MotionEvent motionEventObtain = MotionEvent.obtain(motionEvent.getDownTime() == motionEvent.getEventTime() ? j3 : motionEvent.getDownTime(), j3, i7, pointerCount, pointerPropertiesArr, pointerCoordsArr, motionEvent.getMetaState(), z6 ? 0 : motionEvent.getButtonState(), motionEvent.getXPrecision(), motionEvent.getYPrecision(), motionEvent.getDeviceId(), motionEvent.getEdgeFlags(), motionEvent.getSource(), motionEvent.getFlags());
        C1778f c1778fA = this.f11628G.a(motionEventObtain, this);
        m.c(c1778fA);
        this.f11629H.m(c1778fA, this, true);
        motionEventObtain.recycle();
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(M.e r6, s7.AbstractC2410c r7) throws java.lang.Throwable {
        /*
            r5 = this;
            boolean r0 = r7 instanceof F0.C0240z
            if (r0 == 0) goto L13
            r0 = r7
            F0.z r0 = (F0.C0240z) r0
            int r1 = r0.f2300n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f2300n = r1
            goto L18
        L13:
            F0.z r0 = new F0.z
            r0.<init>(r5, r7)
        L18:
            java.lang.Object r7 = r0.l
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f2300n
            r3 = 1
            if (r2 == 0) goto L2f
            if (r2 == r3) goto L2b
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L2b:
            h8.AbstractC1386c.u(r7)
            goto L49
        L2f:
            h8.AbstractC1386c.u(r7)
            java.util.concurrent.atomic.AtomicReference r7 = r5.f11664o0
            F0.u r2 = new F0.u
            r4 = 2
            r2.<init>(r5, r4)
            r0.f2300n = r3
            g0.r r5 = new g0.r
            r3 = 0
            r5.<init>(r2, r7, r6, r3)
            java.lang.Object r5 = S8.AbstractC0727y.h(r5, r0)
            if (r5 != r1) goto L49
            return
        L49:
            A9.k r5 = new A9.k
            r5.<init>()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.E(M.e, s7.c):void");
    }

    public final void F() {
        int[] iArr = this.f11646V;
        getLocationOnScreen(iArr);
        long j3 = this.f11645U;
        int i7 = (int) (j3 >> 32);
        int i10 = (int) (j3 & 4294967295L);
        boolean z6 = false;
        int i11 = iArr[0];
        if (i7 != i11 || i10 != iArr[1]) {
            this.f11645U = AbstractC2673a.d(i11, iArr[1]);
            if (i7 != Integer.MAX_VALUE && i10 != Integer.MAX_VALUE) {
                getRoot().t().f1568r.w0();
                z6 = true;
            }
        }
        this.f11643S.a(z6);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view) {
        addView(view, -1);
    }

    @Override // android.view.View
    public final void autofill(SparseArray sparseArray) {
        C1339a c1339a = this.f11633J;
        if (c1339a != null) {
            int size = sparseArray.size();
            for (int i7 = 0; i7 < size; i7++) {
                int iKeyAt = sparseArray.keyAt(i7);
                AutofillValue autofillValue = (AutofillValue) sparseArray.get(iKeyAt);
                h0.d dVar = h0.d.f15154a;
                if (dVar.d(autofillValue)) {
                    dVar.i(autofillValue).toString();
                    if (c1339a.f15152b.f15156a.get(Integer.valueOf(iKeyAt)) != null) {
                        throw new ClassCastException();
                    }
                } else {
                    if (dVar.b(autofillValue)) {
                        throw new h("An operation is not implemented: b/138604541: Add onFill() callback for date");
                    }
                    if (dVar.c(autofillValue)) {
                        throw new h("An operation is not implemented: b/138604541: Add onFill() callback for list");
                    }
                    if (dVar.e(autofillValue)) {
                        throw new h("An operation is not implemented: b/138604541:  Add onFill() callback for toggle");
                    }
                }
            }
        }
    }

    @Override // android.view.View
    public final boolean canScrollHorizontally(int i7) {
        return this.f11681x.m(i7, this.l, false);
    }

    @Override // android.view.View
    public final boolean canScrollVertically(int i7) {
        return this.f11681x.m(i7, this.l, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0029  */
    @Override // android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void dispatchDraw(android.graphics.Canvas r7) {
        /*
            r6 = this;
            boolean r0 = r6.isAttachedToWindow()
            if (r0 != 0) goto Ld
            E0.F r0 = r6.getRoot()
            j(r0)
        Ld:
            r0 = 1
            r6.p(r0)
            java.lang.Object r1 = e0.AbstractC1174n.f13721b
            monitor-enter(r1)
            java.util.concurrent.atomic.AtomicReference r2 = e0.AbstractC1174n.f13726i     // Catch: java.lang.Throwable -> L2b
            java.lang.Object r2 = r2.get()     // Catch: java.lang.Throwable -> L2b
            e0.b r2 = (e0.C1162b) r2     // Catch: java.lang.Throwable -> L2b
            u.A r2 = r2.f13696h     // Catch: java.lang.Throwable -> L2b
            r3 = 0
            if (r2 == 0) goto L29
            boolean r2 = r2.h()     // Catch: java.lang.Throwable -> L2b
            if (r2 != r0) goto L29
            r2 = r0
            goto L2d
        L29:
            r2 = r3
            goto L2d
        L2b:
            r6 = move-exception
            goto L92
        L2d:
            monitor-exit(r1)
            if (r2 == 0) goto L33
            e0.AbstractC1174n.a()
        L33:
            r6.f11624E = r0
            l3.u r0 = r6.f11673t
            java.lang.Object r1 = r0.f18608m
            n0.b r1 = (n0.C1873b) r1
            android.graphics.Canvas r2 = r1.f19426a
            r1.f19426a = r7
            E0.F r4 = r6.getRoot()
            r5 = 0
            r4.k(r1, r5)
            java.lang.Object r0 = r0.f18608m
            n0.b r0 = (n0.C1873b) r0
            r0.f19426a = r2
            java.util.ArrayList r0 = r6.f11620C
            boolean r0 = r0.isEmpty()
            if (r0 != 0) goto L6c
            java.util.ArrayList r0 = r6.f11620C
            int r0 = r0.size()
            r1 = r3
        L5c:
            if (r1 >= r0) goto L6c
            java.util.ArrayList r2 = r6.f11620C
            java.lang.Object r2 = r2.get(r1)
            E0.i0 r2 = (E0.i0) r2
            r2.i()
            int r1 = r1 + 1
            goto L5c
        L6c:
            boolean r0 = F0.Z0.f2154E
            if (r0 == 0) goto L7e
            int r0 = r7.save()
            r1 = 0
            r7.clipRect(r1, r1, r1, r1)
            super.dispatchDraw(r7)
            r7.restoreToCount(r0)
        L7e:
            java.util.ArrayList r7 = r6.f11620C
            r7.clear()
            r6.f11624E = r3
            java.util.ArrayList r7 = r6.f11622D
            if (r7 == 0) goto L91
            java.util.ArrayList r6 = r6.f11620C
            r6.addAll(r7)
            r7.clear()
        L91:
            return
        L92:
            monitor-exit(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeView.dispatchDraw(android.graphics.Canvas):void");
    }

    @Override // android.view.View
    public final boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        A0.a aVar;
        int size;
        Y y10;
        n nVarF;
        Y y11;
        if (this.f11625E0) {
            l lVar = this.f11623D0;
            removeCallbacks(lVar);
            if (motionEvent.getActionMasked() == 8) {
                this.f11625E0 = false;
            } else {
                lVar.run();
            }
        }
        if (motionEvent.getActionMasked() != 8) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (l(motionEvent) || !isAttachedToWindow()) {
            return super.dispatchGenericMotionEvent(motionEvent);
        }
        if (!motionEvent.isFromSource(4194304)) {
            return (i(motionEvent) & 1) != 0;
        }
        ViewConfiguration viewConfiguration = ViewConfiguration.get(getContext());
        motionEvent.getAxisValue(26);
        getContext();
        viewConfiguration.getScaledVerticalScrollFactor();
        getContext();
        viewConfiguration.getScaledHorizontalScrollFactor();
        motionEvent.getEventTime();
        motionEvent.getDeviceId();
        b bVar = (b) getFocusOwner();
        if (bVar.f11570g.a()) {
            throw new IllegalStateException("Dispatching rotary event while focus system is invalidated.");
        }
        C1727r c1727rG = AbstractC1713d.g(bVar.f11569f);
        if (c1727rG != null) {
            n nVar = c1727rG.l;
            if (!nVar.f14695x) {
                throw new IllegalStateException("visitAncestors called on an unattached node");
            }
            F fT = AbstractC0156f.t(c1727rG);
            loop0: while (true) {
                if (fT == null) {
                    nVarF = null;
                    break;
                }
                if ((((n) fT.f1469H.f1603f).o & 16384) != 0) {
                    while (nVar != null) {
                        if ((nVar.f14686n & 16384) != 0) {
                            d dVar = null;
                            nVarF = nVar;
                            while (nVarF != null) {
                                if (nVarF instanceof A0.a) {
                                    break loop0;
                                }
                                if ((nVarF.f14686n & 16384) != 0 && (nVarF instanceof AbstractC0164n)) {
                                    int i7 = 0;
                                    for (n nVar2 = ((AbstractC0164n) nVarF).f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
                                        if ((nVar2.f14686n & 16384) != 0) {
                                            i7++;
                                            if (i7 == 1) {
                                                nVarF = nVar2;
                                            } else {
                                                if (dVar == null) {
                                                    dVar = new d(new n[16]);
                                                }
                                                if (nVarF != null) {
                                                    dVar.b(nVarF);
                                                    nVarF = null;
                                                }
                                                dVar.b(nVar2);
                                            }
                                        }
                                    }
                                    if (i7 == 1) {
                                    }
                                }
                                nVarF = AbstractC0156f.f(dVar);
                            }
                        }
                        nVar = nVar.f14687p;
                    }
                }
                fT = fT.G();
                nVar = (fT == null || (y11 = fT.f1469H) == null) ? null : (q0) y11.f1602e;
            }
            aVar = (A0.a) nVarF;
        } else {
            aVar = null;
        }
        if (aVar == null) {
            return false;
        }
        A0.a aVar2 = aVar;
        n nVar3 = aVar2.l;
        if (!nVar3.f14695x) {
            throw new IllegalStateException("visitAncestors called on an unattached node");
        }
        n nVar4 = nVar3.f14687p;
        F fT2 = AbstractC0156f.t(aVar);
        ArrayList arrayList = null;
        while (fT2 != null) {
            if ((((n) fT2.f1469H.f1603f).o & 16384) != 0) {
                while (nVar4 != null) {
                    if ((nVar4.f14686n & 16384) != 0) {
                        n nVarF2 = nVar4;
                        d dVar2 = null;
                        while (nVarF2 != null) {
                            if (nVarF2 instanceof A0.a) {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                arrayList.add(nVarF2);
                            } else if ((nVarF2.f14686n & 16384) != 0 && (nVarF2 instanceof AbstractC0164n)) {
                                int i10 = 0;
                                for (n nVar5 = ((AbstractC0164n) nVarF2).f1690z; nVar5 != null; nVar5 = nVar5.f14688q) {
                                    if ((nVar5.f14686n & 16384) != 0) {
                                        i10++;
                                        if (i10 == 1) {
                                            nVarF2 = nVar5;
                                        } else {
                                            if (dVar2 == null) {
                                                dVar2 = new d(new n[16]);
                                            }
                                            if (nVarF2 != null) {
                                                dVar2.b(nVarF2);
                                                nVarF2 = null;
                                            }
                                            dVar2.b(nVar5);
                                        }
                                    }
                                }
                                if (i10 == 1) {
                                }
                            }
                            nVarF2 = AbstractC0156f.f(dVar2);
                        }
                    }
                    nVar4 = nVar4.f14687p;
                }
            }
            fT2 = fT2.G();
            nVar4 = (fT2 == null || (y10 = fT2.f1469H) == null) ? null : (q0) y10.f1602e;
        }
        if (arrayList != null && arrayList.size() - 1 >= 0) {
            while (true) {
                int i11 = size - 1;
                ((A0.a) arrayList.get(size)).getClass();
                if (i11 < 0) {
                    break;
                }
                size = i11;
            }
        }
        n nVarF3 = aVar2.l;
        d dVar3 = null;
        while (nVarF3 != null) {
            if (nVarF3 instanceof A0.a) {
            } else if ((nVarF3.f14686n & 16384) != 0 && (nVarF3 instanceof AbstractC0164n)) {
                int i12 = 0;
                for (n nVar6 = ((AbstractC0164n) nVarF3).f1690z; nVar6 != null; nVar6 = nVar6.f14688q) {
                    if ((nVar6.f14686n & 16384) != 0) {
                        i12++;
                        if (i12 == 1) {
                            nVarF3 = nVar6;
                        } else {
                            if (dVar3 == null) {
                                dVar3 = new d(new n[16]);
                            }
                            if (nVarF3 != null) {
                                dVar3.b(nVarF3);
                                nVarF3 = null;
                            }
                            dVar3.b(nVar6);
                        }
                    }
                }
                if (i12 == 1) {
                }
            }
            nVarF3 = AbstractC0156f.f(dVar3);
        }
        n nVarF4 = aVar2.l;
        d dVar4 = null;
        while (nVarF4 != null) {
            if (nVarF4 instanceof A0.a) {
            } else if ((nVarF4.f14686n & 16384) != 0 && (nVarF4 instanceof AbstractC0164n)) {
                int i13 = 0;
                for (n nVar7 = ((AbstractC0164n) nVarF4).f1690z; nVar7 != null; nVar7 = nVar7.f14688q) {
                    if ((nVar7.f14686n & 16384) != 0) {
                        i13++;
                        if (i13 == 1) {
                            nVarF4 = nVar7;
                        } else {
                            if (dVar4 == null) {
                                dVar4 = new d(new n[16]);
                            }
                            if (nVarF4 != null) {
                                dVar4.b(nVarF4);
                                nVarF4 = null;
                            }
                            dVar4.b(nVar7);
                        }
                    }
                }
                if (i13 == 1) {
                }
            }
            nVarF4 = AbstractC0156f.f(dVar4);
        }
        if (arrayList == null) {
            return false;
        }
        int size2 = arrayList.size();
        for (int i14 = 0; i14 < size2; i14++) {
            C0219o c0219o = ((A0.a) arrayList.get(i14)).f305y;
        }
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchHoverEvent(MotionEvent motionEvent) {
        int i7;
        boolean z6 = this.f11625E0;
        l lVar = this.f11623D0;
        if (z6) {
            removeCallbacks(lVar);
            lVar.run();
        }
        if (l(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        M m10 = this.f11681x;
        AccessibilityManager accessibilityManager = m10.f2067g;
        if (accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled()) {
            int action = motionEvent.getAction();
            AndroidComposeView androidComposeView = m10.d;
            if (action == 7 || action == 9) {
                float x8 = motionEvent.getX();
                float y10 = motionEvent.getY();
                androidComposeView.p(true);
                C0168s c0168s = new C0168s();
                F root = androidComposeView.getRoot();
                long jA = e.a(x8, y10);
                Y y11 = root.f1469H;
                d0 d0Var = (d0) y11.d;
                C1863M c1863m = d0.f1628Q;
                ((d0) y11.d).U0(d0.f1632U, d0Var.N0(jA, true), c0168s, true, true);
                for (int iC = AbstractC1999n.C(c0168s); -1 < iC; iC--) {
                    Object obj = c0168s.l[iC];
                    m.d(obj, "null cannot be cast to non-null type androidx.compose.ui.Modifier.Node");
                    F fT = AbstractC0156f.t((n) obj);
                    if (androidComposeView.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(fT) != null) {
                        break;
                    }
                    if (fT.f1469H.f(8)) {
                        int iE = m10.E(fT.f1481m);
                        if (T.t(AbstractC0904a.p(fT, false))) {
                            i7 = iE;
                            break;
                        }
                    }
                }
                i7 = Integer.MIN_VALUE;
                androidComposeView.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                int i10 = m10.f2065e;
                if (i10 != i7) {
                    m10.f2065e = i7;
                    M.I(m10, i7, 128, null, 12);
                    M.I(m10, i10, 256, null, 12);
                }
            } else if (action == 10) {
                int i11 = m10.f2065e;
                if (i11 == Integer.MIN_VALUE) {
                    androidComposeView.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(motionEvent);
                } else if (i11 != Integer.MIN_VALUE) {
                    m10.f2065e = Integer.MIN_VALUE;
                    M.I(m10, Integer.MIN_VALUE, 128, null, 12);
                    M.I(m10, i11, 256, null, 12);
                }
            }
        }
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked != 7) {
            if (actionMasked == 10 && m(motionEvent)) {
                if (motionEvent.getToolType(0) == 3 && motionEvent.getButtonState() != 0) {
                    return false;
                }
                MotionEvent motionEvent2 = this.f11684y0;
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                this.f11684y0 = MotionEvent.obtainNoHistory(motionEvent);
                this.f11625E0 = true;
                postDelayed(lVar, 8L);
                return false;
            }
        } else if (!n(motionEvent)) {
            return false;
        }
        return (i(motionEvent) & 1) != 0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (!isFocused()) {
            return ((b) getFocusOwner()).c(keyEvent, new A(this, 6, keyEvent));
        }
        int metaState = keyEvent.getMetaState();
        this.f11671s.getClass();
        e1.f2192b.setValue(new u(metaState));
        return ((b) getFocusOwner()).c(keyEvent, C1715f.f18331n) || super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEventPreIme(KeyEvent keyEvent) {
        Y y10;
        if (isFocused()) {
            b bVar = (b) getFocusOwner();
            if (bVar.f11570g.a()) {
                System.out.println((Object) "FocusRelatedWarning: Dispatching intercepted soft keyboard event while the focus system is invalidated.");
            } else {
                C1727r c1727rG = AbstractC1713d.g(bVar.f11569f);
                if (c1727rG != null) {
                    n nVar = c1727rG.l;
                    if (!nVar.f14695x) {
                        throw new IllegalStateException("visitAncestors called on an unattached node");
                    }
                    F fT = AbstractC0156f.t(c1727rG);
                    while (fT != null) {
                        if ((((n) fT.f1469H.f1603f).o & 131072) != 0) {
                            while (nVar != null) {
                                if ((nVar.f14686n & 131072) != 0) {
                                    n nVarF = nVar;
                                    d dVar = null;
                                    while (nVarF != null) {
                                        if ((nVarF.f14686n & 131072) != 0 && (nVarF instanceof AbstractC0164n)) {
                                            int i7 = 0;
                                            for (n nVar2 = ((AbstractC0164n) nVarF).f1690z; nVar2 != null; nVar2 = nVar2.f14688q) {
                                                if ((nVar2.f14686n & 131072) != 0) {
                                                    i7++;
                                                    if (i7 == 1) {
                                                        nVarF = nVar2;
                                                    } else {
                                                        if (dVar == null) {
                                                            dVar = new d(new n[16]);
                                                        }
                                                        if (nVarF != null) {
                                                            dVar.b(nVarF);
                                                            nVarF = null;
                                                        }
                                                        dVar.b(nVar2);
                                                    }
                                                }
                                            }
                                            if (i7 == 1) {
                                            }
                                        }
                                        nVarF = AbstractC0156f.f(dVar);
                                    }
                                }
                                nVar = nVar.f14687p;
                            }
                        }
                        fT = fT.G();
                        nVar = (fT == null || (y10 = fT.f1469H) == null) ? null : (q0) y10.f1602e;
                    }
                }
            }
        }
        return super.dispatchKeyEventPreIme(keyEvent);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.f11625E0) {
            l lVar = this.f11623D0;
            removeCallbacks(lVar);
            MotionEvent motionEvent2 = this.f11684y0;
            m.c(motionEvent2);
            if (motionEvent.getActionMasked() == 0 && motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) {
                this.f11625E0 = false;
            } else {
                lVar.run();
            }
        }
        if (l(motionEvent) || !isAttachedToWindow()) {
            return false;
        }
        if (motionEvent.getActionMasked() == 2 && !n(motionEvent)) {
            return false;
        }
        int i7 = i(motionEvent);
        if ((i7 & 2) != 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        }
        return (i7 & 1) != 0;
    }

    public final View findViewByAccessibilityIdTraversal(int accessibilityId) throws IllegalAccessException, InvocationTargetException {
        try {
            Method declaredMethod = View.class.getDeclaredMethod("findViewByAccessibilityIdTraversal", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(this, Integer.valueOf(accessibilityId));
            if (objInvoke instanceof View) {
                return (View) objInvoke;
            }
            return null;
        } catch (NoSuchMethodException unused) {
            return null;
        }
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i7) {
        if (view != null) {
            m0.d dVarD = AbstractC1713d.d(view);
            C1711b c1711bJ = AbstractC1713d.J(i7);
            if (m.a(((b) getFocusOwner()).d(c1711bJ != null ? c1711bJ.f18325a : 6, dVarD, C0219o.o), Boolean.TRUE)) {
                return this;
            }
        }
        return super.focusSearch(view, i7);
    }

    public final C0218n0 getAndroidViewsHandler$ui_release() {
        if (this.f11639O == null) {
            C0218n0 c0218n0 = new C0218n0(getContext());
            this.f11639O = c0218n0;
            addView(c0218n0, -1);
            requestLayout();
        }
        C0218n0 c0218n02 = this.f11639O;
        m.c(c0218n02);
        return c0218n02;
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC1340b getAutofill() {
        return this.f11633J;
    }

    @Override // androidx.compose.ui.node.Owner
    public f getAutofillTree() {
        return this.autofillTree;
    }

    public final InterfaceC2782k getConfigurationChangeObserver() {
        return this.configurationChangeObserver;
    }

    /* JADX INFO: renamed from: getContentCaptureManager$ui_release, reason: from getter */
    public final ViewOnAttachStateChangeListenerC1400d getContentCaptureManager() {
        return this.contentCaptureManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC2194h getCoroutineContext() {
        return this.coroutineContext;
    }

    @Override // androidx.compose.ui.node.Owner
    public a1.b getDensity() {
        return (a1.b) this.o.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC1533a getDragAndDropManager() {
        return this.f11669r;
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC1716g getFocusOwner() {
        return this.f11665p;
    }

    @Override // android.view.View
    public final void getFocusedRect(Rect rect) {
        q qVar;
        m0.d dVarT = t();
        if (dVarT != null) {
            rect.left = Math.round(dVarT.f18882a);
            rect.top = Math.round(dVarT.f18883b);
            rect.right = Math.round(dVarT.c);
            rect.bottom = Math.round(dVarT.d);
            qVar = q.f19267a;
        } else {
            qVar = null;
        }
        if (qVar == null) {
            super.getFocusedRect(rect);
        }
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC0702n getFontFamilyResolver() {
        return (InterfaceC0702n) this.f11670r0.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC0701m getFontLoader() {
        return this.f11668q0;
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC1897z getGraphicsContext() {
        return this.f11618A;
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC2487a getHapticFeedBack() {
        return this.f11676u0;
    }

    public boolean getHasPendingMeasureOrLayout() {
        return this.f11643S.f1589b.H();
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC2532b getInputModeManager() {
        return this.f11678v0;
    }

    /* JADX INFO: renamed from: getLastMatrixRecalculationAnimationTime$ui_release, reason: from getter */
    public final long getLastMatrixRecalculationAnimationTime() {
        return this.lastMatrixRecalculationAnimationTime;
    }

    @Override // android.view.View, android.view.ViewParent, androidx.compose.ui.node.Owner
    public k getLayoutDirection() {
        return (k) this.f11674t0.getValue();
    }

    public long getMeasureIteration() {
        U u4 = this.f11643S;
        if (u4.c) {
            return u4.f1592g;
        }
        l9.d.g0("measureIteration should be only used during the measure/layout pass");
        throw null;
    }

    @Override // androidx.compose.ui.node.Owner
    public c getModifierLocalManager() {
        return this.modifierLocalManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public C0.U getPlacementScope() {
        int i7 = W.f875b;
        return new C0.H(this, 1);
    }

    @Override // androidx.compose.ui.node.Owner
    public InterfaceC2736n getPointerIconService() {
        return this.f11634J0;
    }

    @Override // androidx.compose.ui.node.Owner
    public F getRoot() {
        return this.root;
    }

    public o0 getRootForTest() {
        return this.f11677v;
    }

    public final boolean getScrollCaptureInProgress$ui_release() {
        K0.l lVar;
        if (Build.VERSION.SDK_INT < 31 || (lVar = this.f11632I0) == null) {
            return false;
        }
        return ((Boolean) lVar.f3503a.getValue()).booleanValue();
    }

    public p getSemanticsOwner() {
        return this.semanticsOwner;
    }

    @Override // androidx.compose.ui.node.Owner
    public H getSharedDrawScope() {
        return this.sharedDrawScope;
    }

    @Override // androidx.compose.ui.node.Owner
    public boolean getShowLayoutBounds() {
        return this.showLayoutBounds;
    }

    @Override // androidx.compose.ui.node.Owner
    public k0 getSnapshotObserver() {
        return this.snapshotObserver;
    }

    @Override // androidx.compose.ui.node.Owner
    public R0 getSoftwareKeyboardController() {
        return this.f11666p0;
    }

    @Override // androidx.compose.ui.node.Owner
    public z getTextInputService() {
        return this.textInputService;
    }

    @Override // androidx.compose.ui.node.Owner
    public S0 getTextToolbar() {
        return this.f11682x0;
    }

    public View getView() {
        return this;
    }

    @Override // androidx.compose.ui.node.Owner
    public W0 getViewConfiguration() {
        return this.f11644T;
    }

    public final C0215m getViewTreeOwners() {
        return (C0215m) this.f11655h0.getValue();
    }

    @Override // androidx.compose.ui.node.Owner
    public d1 getWindowInfo() {
        return this.f11671s;
    }

    public final i0 h(G0 g02, I0 i02, C2164c c2164c) {
        Reference referencePoll;
        d dVar;
        Object obj;
        if (c2164c != null) {
            return new A0(c2164c, null, this, g02, i02);
        }
        do {
            t tVar = this.f11619A0;
            referencePoll = ((ReferenceQueue) tVar.f4151m).poll();
            dVar = (d) tVar.l;
            if (referencePoll != null) {
                dVar.n(referencePoll);
            }
        } while (referencePoll != null);
        while (true) {
            if (!dVar.m()) {
                obj = null;
                break;
            }
            obj = ((Reference) dVar.o(dVar.f9279n - 1)).get();
            if (obj != null) {
                break;
            }
        }
        i0 i0Var = (i0) obj;
        if (i0Var != null) {
            i0Var.j(g02, i02);
            return i0Var;
        }
        if (isHardwareAccelerated()) {
            return new A0(getGraphicsContext().a(), getGraphicsContext(), this, g02, i02);
        }
        if (isHardwareAccelerated() && this.f11653f0) {
            try {
                return new N0(this, g02, i02);
            } catch (Throwable unused) {
                this.f11653f0 = false;
            }
        }
        if (this.f11640P == null) {
            if (!Z0.f2153D) {
                T.B(new View(getContext()));
            }
            C0237x0 c0237x0 = Z0.f2154E ? new C0237x0(getContext()) : new a1(getContext());
            this.f11640P = c0237x0;
            addView(c0237x0, -1);
        }
        C0237x0 c0237x02 = this.f11640P;
        m.c(c0237x02);
        return new Z0(this, c0237x02, g02, i02);
    }

    public final int i(MotionEvent motionEvent) {
        int i7;
        int actionMasked;
        float[] fArr = this.f11648a0;
        removeCallbacks(this.f11621C0);
        try {
            this.lastMatrixRecalculationAnimationTime = AnimationUtils.currentAnimationTimeMillis();
            this.G0.a(this, fArr);
            T.s(fArr, this.f11649b0);
            long jB = C1854D.b(e.a(motionEvent.getX(), motionEvent.getY()), fArr);
            this.f11652e0 = e.a(motionEvent.getRawX() - m0.c.d(jB), motionEvent.getRawY() - m0.c.e(jB));
            boolean z6 = true;
            this.f11651d0 = true;
            p(false);
            Trace.beginSection("AndroidOwner:onTouch");
            try {
                int actionMasked2 = motionEvent.getActionMasked();
                MotionEvent motionEvent2 = this.f11684y0;
                boolean z10 = motionEvent2 != null && motionEvent2.getToolType(0) == 3;
                C c = this.f11629H;
                if (motionEvent2 != null) {
                    if ((motionEvent2.getSource() == motionEvent.getSource() && motionEvent2.getToolType(0) == motionEvent.getToolType(0)) ? false : true) {
                        if (motionEvent2.getButtonState() != 0 || (actionMasked = motionEvent2.getActionMasked()) == 0 || actionMasked == 2 || actionMasked == 6) {
                            c.n();
                        } else if (motionEvent2.getActionMasked() != 10 && z10) {
                            D(motionEvent2, 10, motionEvent2.getEventTime(), true);
                        }
                    }
                }
                boolean z11 = motionEvent.getToolType(0) == 3;
                if (z10 || !z11 || actionMasked2 == 3 || actionMasked2 == 9 || !m(motionEvent)) {
                    i7 = 9;
                } else {
                    i7 = 9;
                    D(motionEvent, 9, motionEvent.getEventTime(), true);
                }
                if (motionEvent2 != null) {
                    motionEvent2.recycle();
                }
                MotionEvent motionEvent3 = this.f11684y0;
                if (motionEvent3 != null && motionEvent3.getAction() == 10) {
                    MotionEvent motionEvent4 = this.f11684y0;
                    int pointerId = motionEvent4 != null ? motionEvent4.getPointerId(0) : -1;
                    int action = motionEvent.getAction();
                    C2727e c2727e = this.f11628G;
                    if (action == i7 && motionEvent.getHistorySize() == 0) {
                        if (pointerId >= 0) {
                            c2727e.c.delete(pointerId);
                            c2727e.f24055b.delete(pointerId);
                        }
                    } else if (motionEvent.getAction() == 0 && motionEvent.getHistorySize() == 0) {
                        MotionEvent motionEvent5 = this.f11684y0;
                        float x8 = motionEvent5 != null ? motionEvent5.getX() : Float.NaN;
                        MotionEvent motionEvent6 = this.f11684y0;
                        boolean z12 = (x8 == motionEvent.getX() && (motionEvent6 != null ? motionEvent6.getY() : Float.NaN) == motionEvent.getY()) ? false : true;
                        MotionEvent motionEvent7 = this.f11684y0;
                        if ((motionEvent7 != null ? motionEvent7.getEventTime() : -1L) == motionEvent.getEventTime()) {
                            z6 = false;
                        }
                        if (z12 || z6) {
                            if (pointerId >= 0) {
                                c2727e.c.delete(pointerId);
                                c2727e.f24055b.delete(pointerId);
                            }
                            ((C0006c) ((y) c.c).f15500n).f158a.g();
                        }
                    }
                }
                this.f11684y0 = MotionEvent.obtainNoHistory(motionEvent);
                return C(motionEvent);
            } finally {
                Trace.endSection();
            }
        } finally {
            this.f11651d0 = false;
        }
    }

    public final void k(F f10) {
        int i7 = 0;
        this.f11643S.p(f10, false);
        d dVarL = f10.L();
        int i10 = dVarL.f9279n;
        if (i10 > 0) {
            Object[] objArr = dVarL.l;
            do {
                k((F) objArr[i7]);
                i7++;
            } while (i7 < i10);
        }
    }

    public final boolean m(MotionEvent motionEvent) {
        float x8 = motionEvent.getX();
        float y10 = motionEvent.getY();
        return 0.0f <= x8 && x8 <= ((float) getWidth()) && 0.0f <= y10 && y10 <= ((float) getHeight());
    }

    public final boolean n(MotionEvent motionEvent) {
        MotionEvent motionEvent2;
        return (motionEvent.getPointerCount() == 1 && (motionEvent2 = this.f11684y0) != null && motionEvent2.getPointerCount() == motionEvent.getPointerCount() && motionEvent.getRawX() == motionEvent2.getRawX() && motionEvent.getRawY() == motionEvent2.getRawY()) ? false : true;
    }

    public final long o(long j3) {
        y();
        long jB = C1854D.b(j3, this.f11648a0);
        return e.a(m0.c.d(this.f11652e0) + m0.c.d(jB), m0.c.e(this.f11652e0) + m0.c.e(jB));
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        A9.b bVarG;
        androidx.lifecycle.A a10;
        super.onAttachedToWindow();
        this.f11671s.f2193a.setValue(Boolean.valueOf(hasWindowFocus()));
        k(getRoot());
        j(getRoot());
        getSnapshotObserver().f1682a.e();
        C1339a c1339a = this.f11633J;
        if (c1339a != null) {
            h0.e.f15155a.a(c1339a);
        }
        androidx.lifecycle.A aF = a0.f(this);
        t3.e eVarS = h6.q.s(this);
        C0215m viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners == null || (aF != null && eVarS != null && (aF != (a10 = viewTreeOwners.f2221a) || eVarS != a10))) {
            if (aF == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagate ViewTreeLifecycleOwner!");
            }
            if (eVarS == null) {
                throw new IllegalStateException("Composed into the View which doesn't propagateViewTreeSavedStateRegistryOwner!");
            }
            if (viewTreeOwners != null && (bVarG = viewTreeOwners.f2221a.g()) != null) {
                bVarG.V0(this);
            }
            aF.g().M0(this);
            C0215m c0215m = new C0215m(aF, eVarS);
            set_viewTreeOwners(c0215m);
            InterfaceC2782k interfaceC2782k = this.f11656i0;
            if (interfaceC2782k != null) {
                interfaceC2782k.b(c0215m);
            }
            this.f11656i0 = null;
        }
        int i7 = isInTouchMode() ? 1 : 2;
        C2533c c2533c = this.f11678v0;
        c2533c.getClass();
        c2533c.f22941a.setValue(new C2531a(i7));
        C0215m viewTreeOwners2 = getViewTreeOwners();
        A9.b bVarG2 = viewTreeOwners2 != null ? viewTreeOwners2.f2221a.g() : null;
        if (bVarG2 == null) {
            l9.d.i0("No lifecycle owner exists");
            throw null;
        }
        bVarG2.M0(this);
        bVarG2.M0(this.contentCaptureManager);
        getViewTreeObserver().addOnGlobalLayoutListener(this.f11657j0);
        getViewTreeObserver().addOnScrollChangedListener(this.f11658k0);
        getViewTreeObserver().addOnTouchModeChangeListener(this.f11659l0);
        if (Build.VERSION.SDK_INT >= 31) {
            P.f2103a.b(this);
        }
    }

    @Override // android.view.View
    public final boolean onCheckIsTextEditor() {
        g0.q qVar = (g0.q) this.f11664o0.get();
        C0196c0 c0196c0 = (C0196c0) (qVar != null ? qVar.f14697b : null);
        if (c0196c0 == null) {
            return this.f11661m0.d;
        }
        g0.q qVar2 = (g0.q) c0196c0.o.get();
        C0 c02 = (C0) (qVar2 != null ? qVar2.f14697b : null);
        return c02 != null && (c02.f2012e ^ true);
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setDensity(AbstractC1274a.f(getContext()));
        int i7 = Build.VERSION.SDK_INT;
        if ((i7 >= 31 ? configuration.fontWeightAdjustment : 0) != this.f11672s0) {
            this.f11672s0 = i7 >= 31 ? configuration.fontWeightAdjustment : 0;
            setFontFamilyResolver(io.sentry.config.a.V(getContext()));
        }
        this.configurationChangeObserver.b(configuration);
    }

    @Override // android.view.View
    public final InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        g0.q qVar = (g0.q) this.f11664o0.get();
        InputConnection qVar2 = null;
        C0196c0 c0196c0 = (C0196c0) (qVar != null ? qVar.f14697b : null);
        if (c0196c0 != null) {
            g0.q qVar3 = (g0.q) c0196c0.o.get();
            C0 c02 = (C0) (qVar3 != null ? qVar3.f14697b : null);
            if (c02 != null) {
                synchronized (c02.c) {
                    if (!c02.f2012e) {
                        M.B bA = c02.f2010a.a(editorInfo);
                        A.F f10 = new A.F(c02, 13);
                        qVar2 = Build.VERSION.SDK_INT >= 34 ? new T0.q(bA, f10) : new T0.p(bA, f10);
                        c02.d.b(new WeakReference(qVar2));
                    }
                }
            }
            return qVar2;
        }
        B b10 = this.f11661m0;
        if (!b10.d) {
            return null;
        }
        T0.m mVar = b10.f8416h;
        T0.y yVar = b10.f8415g;
        int i7 = mVar.f8462e;
        boolean zA = T0.l.a(i7, 1);
        boolean z6 = mVar.f8460a;
        int i10 = 0;
        if (zA) {
            if (z6) {
                i10 = 6;
            }
        } else if (T0.l.a(i7, 0)) {
            i10 = 1;
        } else if (T0.l.a(i7, 2)) {
            i10 = 2;
        } else if (T0.l.a(i7, 6)) {
            i10 = 5;
        } else if (T0.l.a(i7, 5)) {
            i10 = 7;
        } else if (T0.l.a(i7, 3)) {
            i10 = 3;
        } else if (T0.l.a(i7, 4)) {
            i10 = 4;
        } else {
            if (!T0.l.a(i7, 7)) {
                throw new IllegalStateException("invalid ImeAction");
            }
            i10 = 6;
        }
        editorInfo.imeOptions = i10;
        int i11 = mVar.d;
        if (T0.o.a(i11, 1)) {
            editorInfo.inputType = 1;
        } else if (T0.o.a(i11, 2)) {
            editorInfo.inputType = 1;
            editorInfo.imeOptions |= Integer.MIN_VALUE;
        } else if (T0.o.a(i11, 3)) {
            editorInfo.inputType = 2;
        } else if (T0.o.a(i11, 4)) {
            editorInfo.inputType = 3;
        } else if (T0.o.a(i11, 5)) {
            editorInfo.inputType = 17;
        } else if (T0.o.a(i11, 6)) {
            editorInfo.inputType = 33;
        } else if (T0.o.a(i11, 7)) {
            editorInfo.inputType = 129;
        } else if (T0.o.a(i11, 8)) {
            editorInfo.inputType = 18;
        } else {
            if (!T0.o.a(i11, 9)) {
                throw new IllegalStateException("Invalid Keyboard Type");
            }
            editorInfo.inputType = 8194;
        }
        if (!z6) {
            int i12 = editorInfo.inputType;
            if ((i12 & 1) == 1) {
                editorInfo.inputType = i12 | 131072;
                if (T0.l.a(mVar.f8462e, 1)) {
                    editorInfo.imeOptions |= TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED;
                }
            }
        }
        if ((editorInfo.inputType & 1) == 1) {
            int i13 = mVar.f8461b;
            if (T0.n.a(i13, 1)) {
                editorInfo.inputType |= 4096;
            } else if (T0.n.a(i13, 2)) {
                editorInfo.inputType |= 8192;
            } else if (T0.n.a(i13, 3)) {
                editorInfo.inputType |= 16384;
            }
            if (mVar.c) {
                editorInfo.inputType |= 32768;
            }
        }
        long j3 = yVar.f8482b;
        int i14 = K.c;
        editorInfo.initialSelStart = (int) (j3 >> 32);
        editorInfo.initialSelEnd = (int) (j3 & 4294967295L);
        H7.H.P(editorInfo, yVar.f8481a.l);
        editorInfo.imeOptions |= 33554432;
        if (i.c()) {
            i.a().h(editorInfo);
        }
        T0.u uVar = new T0.u(b10.f8415g, new C0420a(b10), b10.f8416h.c);
        b10.f8417i.add(new WeakReference(uVar));
        return uVar;
    }

    @Override // android.view.View
    public final void onCreateVirtualViewTranslationRequests(long[] jArr, int[] iArr, Consumer consumer) {
        ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d = this.contentCaptureManager;
        viewOnAttachStateChangeListenerC1400d.getClass();
        C1398b.f15648a.b(viewOnAttachStateChangeListenerC1400d, jArr, iArr, consumer);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        C1184x c1184x = getSnapshotObserver().f1682a;
        G2.a aVar = c1184x.f13750g;
        if (aVar != null) {
            aVar.k();
        }
        c1184x.b();
        C0215m viewTreeOwners = getViewTreeOwners();
        A9.b bVarG = viewTreeOwners != null ? viewTreeOwners.f2221a.g() : null;
        if (bVarG == null) {
            l9.d.i0("No lifecycle owner exists");
            throw null;
        }
        bVarG.V0(this.contentCaptureManager);
        bVarG.V0(this);
        C1339a c1339a = this.f11633J;
        if (c1339a != null) {
            h0.e.f15155a.b(c1339a);
        }
        getViewTreeObserver().removeOnGlobalLayoutListener(this.f11657j0);
        getViewTreeObserver().removeOnScrollChangedListener(this.f11658k0);
        getViewTreeObserver().removeOnTouchModeChangeListener(this.f11659l0);
        if (Build.VERSION.SDK_INT >= 31) {
            P.f2103a.a(this);
        }
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
    }

    @Override // android.view.View
    public final void onFocusChanged(boolean z6, int i7, Rect rect) {
        super.onFocusChanged(z6, i7, rect);
        if (z6 || hasFocus()) {
            return;
        }
        b bVar = (b) getFocusOwner();
        t4.e eVar = bVar.f11571h;
        boolean z10 = eVar.f22462m;
        C1727r c1727r = bVar.f11569f;
        if (z10) {
            AbstractC1713d.e(c1727r, true, true);
            return;
        }
        try {
            eVar.f22462m = true;
            AbstractC1713d.e(c1727r, true, true);
        } finally {
            t4.e.c(eVar);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z6, int i7, int i10, int i11, int i12) {
        this.f11643S.j(this.f11627F0);
        this.f11641Q = null;
        F();
        if (this.f11639O != null) {
            getAndroidViewsHandler$ui_release().layout(0, 0, i11 - i7, i12 - i10);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i7, int i10) {
        U u4 = this.f11643S;
        Trace.beginSection("AndroidOwner:onMeasure");
        try {
            if (!isAttachedToWindow()) {
                k(getRoot());
            }
            long jG = g(i7);
            int i11 = (int) (jG >>> 32);
            int i12 = (int) (jG & 4294967295L);
            long jG2 = g(i10);
            int i13 = (int) (4294967295L & jG2);
            int iMin = Math.min((int) (jG2 >>> 32), 262142);
            int iMin2 = Integer.MAX_VALUE;
            int iMin3 = i13 == Integer.MAX_VALUE ? Integer.MAX_VALUE : Math.min(i13, 262142);
            int iJ = io.sentry.config.a.J(iMin3 == Integer.MAX_VALUE ? iMin : iMin3);
            if (i12 != Integer.MAX_VALUE) {
                iMin2 = Math.min(iJ, i12);
            }
            long jE = io.sentry.config.a.e(Math.min(iJ, i11), iMin2, iMin, iMin3);
            a aVar = this.f11641Q;
            if (aVar == null) {
                this.f11641Q = new a(jE);
                this.f11642R = false;
            } else if (!a.b(aVar.f11027a, jE)) {
                this.f11642R = true;
            }
            u4.q(jE);
            u4.l();
            setMeasuredDimension(getRoot().I(), getRoot().s());
            if (this.f11639O != null) {
                getAndroidViewsHandler$ui_release().measure(View.MeasureSpec.makeMeasureSpec(getRoot().I(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED), View.MeasureSpec.makeMeasureSpec(getRoot().s(), TestOtaManagerImplCallback.ONPOWEROFFINFORMCHANGED));
            }
            Trace.endSection();
        } catch (Throwable th) {
            Trace.endSection();
            throw th;
        }
    }

    @Override // android.view.View
    public final void onProvideAutofillVirtualStructure(ViewStructure viewStructure, int i7) {
        C1339a c1339a;
        if (viewStructure == null || (c1339a = this.f11633J) == null) {
            return;
        }
        h0.c cVar = h0.c.f15153a;
        f fVar = c1339a.f15152b;
        int iA = cVar.a(viewStructure, fVar.f15156a.size());
        for (Map.Entry entry : fVar.f15156a.entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            if (entry.getValue() != null) {
                throw new ClassCastException();
            }
            ViewStructure viewStructureB = cVar.b(viewStructure, iA);
            if (viewStructureB != null) {
                h0.d dVar = h0.d.f15154a;
                AutofillId autofillIdA = dVar.a(viewStructure);
                m.c(autofillIdA);
                dVar.g(viewStructureB, autofillIdA, iIntValue);
                cVar.d(viewStructureB, iIntValue, c1339a.f15151a.getContext().getPackageName(), null, null);
                dVar.h(viewStructureB, 1);
                throw null;
            }
            iA++;
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public final void onResume(androidx.lifecycle.A a10) {
        setShowLayoutBounds(B0.a());
    }

    @Override // android.view.View
    public final void onRtlPropertiesChanged(int i7) {
        if (this.f11660m) {
            k kVar = k.l;
            k kVar2 = i7 != 0 ? i7 != 1 ? null : k.f11038m : kVar;
            if (kVar2 != null) {
                kVar = kVar2;
            }
            setLayoutDirection(kVar);
        }
    }

    @Override // android.view.View
    public final void onScrollCaptureSearch(Rect rect, Point point, Consumer consumer) {
        K0.l lVar;
        if (Build.VERSION.SDK_INT < 31 || (lVar = this.f11632I0) == null) {
            return;
        }
        lVar.a(this, getSemanticsOwner(), getCoroutineContext(), consumer);
    }

    @Override // android.view.View
    public final void onVirtualViewTranslationResponses(LongSparseArray longSparseArray) {
        ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d = this.contentCaptureManager;
        viewOnAttachStateChangeListenerC1400d.getClass();
        C1398b.f15648a.c(viewOnAttachStateChangeListenerC1400d, longSparseArray);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z6) {
        boolean zA;
        this.f11671s.f2193a.setValue(Boolean.valueOf(z6));
        this.f11630H0 = true;
        super.onWindowFocusChanged(z6);
        if (!z6 || getShowLayoutBounds() == (zA = B0.a())) {
            return;
        }
        setShowLayoutBounds(zA);
        j(getRoot());
    }

    public final void p(boolean z6) {
        C0236x c0236x;
        U u4 = this.f11643S;
        if (u4.f1589b.H() || ((d) u4.f1590e.l).m()) {
            Trace.beginSection("AndroidOwner:measureAndLayout");
            if (z6) {
                try {
                    c0236x = this.f11627F0;
                } catch (Throwable th) {
                    Trace.endSection();
                    throw th;
                }
            } else {
                c0236x = null;
            }
            if (u4.j(c0236x)) {
                requestLayout();
            }
            u4.a(false);
            if (this.f11626F) {
                getViewTreeObserver().dispatchOnGlobalLayout();
                this.f11626F = false;
            }
            Trace.endSection();
        }
    }

    public final void q(F f10, long j3) {
        U u4 = this.f11643S;
        Trace.beginSection("AndroidOwner:measureAndLayout");
        try {
            u4.k(f10, j3);
            if (!u4.f1589b.H()) {
                u4.a(false);
                if (this.f11626F) {
                    getViewTreeObserver().dispatchOnGlobalLayout();
                    this.f11626F = false;
                }
            }
        } finally {
            Trace.endSection();
        }
    }

    public final void r(i0 i0Var, boolean z6) {
        ArrayList arrayList = this.f11620C;
        if (!z6) {
            if (this.f11624E) {
                return;
            }
            arrayList.remove(i0Var);
            ArrayList arrayList2 = this.f11622D;
            if (arrayList2 != null) {
                arrayList2.remove(i0Var);
                return;
            }
            return;
        }
        if (!this.f11624E) {
            arrayList.add(i0Var);
            return;
        }
        ArrayList arrayList3 = this.f11622D;
        if (arrayList3 == null) {
            arrayList3 = new ArrayList();
            this.f11622D = arrayList3;
        }
        arrayList3.add(i0Var);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean requestFocus(int i7, Rect rect) {
        if (isFocused()) {
            return true;
        }
        int iOrdinal = ((b) getFocusOwner()).f11569f.I0().ordinal();
        if (iOrdinal == 0 || iOrdinal == 1 || iOrdinal == 2) {
            return super.requestFocus(i7, rect);
        }
        if (iOrdinal != 3) {
            throw new A9.k();
        }
        C1711b c1711bJ = AbstractC1713d.J(i7);
        int i10 = c1711bJ != null ? c1711bJ.f18325a : 7;
        Boolean boolD = ((b) getFocusOwner()).d(i10, rect != null ? new m0.d(rect.left, rect.top, rect.right, rect.bottom) : null, new C0234w(i10, 0));
        if (boolD != null) {
            return boolD.booleanValue();
        }
        return false;
    }

    public final void s() {
        if (this.f11635K) {
            C1184x c1184x = getSnapshotObserver().f1682a;
            synchronized (c1184x.f13749f) {
                try {
                    d dVar = c1184x.f13749f;
                    int i7 = dVar.f9279n;
                    int i10 = 0;
                    for (int i11 = 0; i11 < i7; i11++) {
                        C1182v c1182v = (C1182v) dVar.l[i11];
                        c1182v.e();
                        if (!(c1182v.f13738f.f22645e != 0)) {
                            i10++;
                        } else if (i10 > 0) {
                            Object[] objArr = dVar.l;
                            objArr[i11 - i10] = objArr[i11];
                        }
                    }
                    int i12 = i7 - i10;
                    Arrays.fill(dVar.l, i12, i7, (Object) null);
                    dVar.f9279n = i12;
                } catch (Throwable th) {
                    throw th;
                }
            }
            this.f11635K = false;
        }
        C0218n0 c0218n0 = this.f11639O;
        if (c0218n0 != null) {
            f(c0218n0);
        }
        while (this.B0.m()) {
            int i13 = this.B0.f9279n;
            for (int i14 = 0; i14 < i13; i14++) {
                Object[] objArr2 = this.B0.l;
                InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objArr2[i14];
                objArr2[i14] = null;
                if (interfaceC2772a != null) {
                    interfaceC2772a.a();
                }
            }
            this.B0.p(0, i13);
        }
    }

    public void setAccessibilityEventBatchIntervalMillis(long intervalMillis) {
        this.f11681x.f2068h = intervalMillis;
    }

    public final void setConfigurationChangeObserver(InterfaceC2782k interfaceC2782k) {
        this.configurationChangeObserver = interfaceC2782k;
    }

    public final void setContentCaptureManager$ui_release(ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d) {
        this.contentCaptureManager = viewOnAttachStateChangeListenerC1400d;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v11 */
    /* JADX WARN: Type inference failed for: r6v12, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v14 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16 */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18 */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v7 */
    /* JADX WARN: Type inference failed for: r6v8, types: [g0.n] */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v3, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6, types: [V.d] */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v5 */
    public void setCoroutineContext(InterfaceC2194h interfaceC2194h) {
        int i7;
        int i10;
        this.coroutineContext = interfaceC2194h;
        n nVar = (n) getRoot().f1469H.f1603f;
        if (nVar instanceof C2722B) {
            ((C2722B) nVar).J0();
        }
        n nVar2 = nVar.l;
        if (!nVar2.f14695x) {
            l9.d.h0("visitSubtree called on an unattached node");
            throw null;
        }
        n nVar3 = nVar2.f14688q;
        F fT = AbstractC0156f.t(nVar);
        int[] iArrCopyOf = new int[16];
        d[] dVarArr = new d[16];
        int i11 = 0;
        while (fT != null) {
            if (nVar3 == null) {
                nVar3 = (n) fT.f1469H.f1603f;
            }
            if ((nVar3.o & 16) != 0) {
                while (nVar3 != null) {
                    if ((nVar3.f14686n & 16) != 0) {
                        ?? F9 = nVar3;
                        ?? dVar = 0;
                        while (F9 != 0) {
                            if (F9 instanceof n0) {
                                n0 n0Var = (n0) F9;
                                if (n0Var instanceof C2722B) {
                                    ((C2722B) n0Var).J0();
                                }
                            } else if ((F9.f14686n & 16) != 0 && (F9 instanceof AbstractC0164n)) {
                                n nVar4 = ((AbstractC0164n) F9).f1690z;
                                int i12 = 0;
                                F9 = F9;
                                dVar = dVar;
                                while (nVar4 != null) {
                                    if ((nVar4.f14686n & 16) != 0) {
                                        i12++;
                                        dVar = dVar;
                                        if (i12 == 1) {
                                            F9 = nVar4;
                                        } else {
                                            if (dVar == 0) {
                                                dVar = new d(new n[16]);
                                            }
                                            if (F9 != 0) {
                                                dVar.b(F9);
                                                F9 = 0;
                                            }
                                            dVar.b(nVar4);
                                        }
                                    }
                                    nVar4 = nVar4.f14688q;
                                    F9 = F9;
                                    dVar = dVar;
                                }
                                if (i12 == 1) {
                                }
                            }
                            F9 = AbstractC0156f.f(dVar);
                        }
                    }
                    nVar3 = nVar3.f14688q;
                }
            }
            d dVarL = fT.L();
            if (!dVarL.l()) {
                if (i11 >= iArrCopyOf.length) {
                    iArrCopyOf = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length * 2);
                    m.e(iArrCopyOf, "copyOf(this, newSize)");
                    Object[] objArrCopyOf = Arrays.copyOf(dVarArr, dVarArr.length * 2);
                    m.e(objArrCopyOf, "copyOf(this, newSize)");
                    dVarArr = (d[]) objArrCopyOf;
                }
                iArrCopyOf[i11] = dVarL.f9279n - 1;
                dVarArr[i11] = dVarL;
                i11++;
            }
            if (i11 <= 0 || (i10 = iArrCopyOf[i11 - 1]) < 0) {
                fT = null;
            } else {
                if (i11 <= 0) {
                    throw new IllegalStateException("Cannot call pop() on an empty stack. Guard with a call to isNotEmpty()");
                }
                d dVar2 = dVarArr[i7];
                m.c(dVar2);
                if (i10 > 0) {
                    iArrCopyOf[i7] = iArrCopyOf[i7] - 1;
                } else if (i10 == 0) {
                    dVarArr[i7] = null;
                    i11--;
                }
                fT = (F) dVar2.l[i10];
            }
            nVar3 = null;
        }
    }

    public final void setLastMatrixRecalculationAnimationTime$ui_release(long j3) {
        this.lastMatrixRecalculationAnimationTime = j3;
    }

    public final void setOnViewTreeOwnersAvailable(InterfaceC2782k callback) {
        C0215m viewTreeOwners = getViewTreeOwners();
        if (viewTreeOwners != null) {
            callback.b(viewTreeOwners);
        }
        if (isAttachedToWindow()) {
            return;
        }
        this.f11656i0 = callback;
    }

    @Override // androidx.compose.ui.node.Owner
    public void setShowLayoutBounds(boolean z6) {
        this.showLayoutBounds = z6;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public final m0.d t() {
        if (isFocused()) {
            C1727r c1727rG = AbstractC1713d.g(((b) getFocusOwner()).f11569f);
            if (c1727rG != null) {
                return AbstractC1713d.j(c1727rG);
            }
            return null;
        }
        View viewFindFocus = findFocus();
        if (viewFindFocus != null) {
            return AbstractC1713d.d(viewFindFocus);
        }
        return null;
    }

    public final void u(F f10) {
        M m10 = this.f11681x;
        m10.f2083y = true;
        if (m10.y()) {
            m10.A(f10);
        }
        ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d = this.contentCaptureManager;
        viewOnAttachStateChangeListenerC1400d.f15658s = true;
        if (viewOnAttachStateChangeListenerC1400d.d() && viewOnAttachStateChangeListenerC1400d.f15659t.add(f10)) {
            viewOnAttachStateChangeListenerC1400d.f15660u.p(q.f19267a);
        }
    }

    public final void v(F f10, boolean z6, boolean z10, boolean z11) {
        F fG;
        F fG2;
        U u4 = this.f11643S;
        if (!z6) {
            if (u4.p(f10, z10) && z11) {
                A(f10);
                return;
            }
            return;
        }
        u4.getClass();
        if (f10.f1482n == null) {
            l9.d.h0("Error: requestLookaheadRemeasure cannot be called on a node outside LookaheadScope");
            throw null;
        }
        int iC = AbstractC2641j.c(f10.v());
        if (iC != 0) {
            if (iC == 1) {
                return;
            }
            if (iC != 2 && iC != 3) {
                if (iC != 4) {
                    throw new A9.k();
                }
                if (!f10.x() || z10) {
                    f10.e0();
                    f10.f0();
                    if (f10.f1478Q) {
                        return;
                    }
                    boolean zA = m.a(f10.Y(), Boolean.TRUE);
                    t tVar = u4.f1589b;
                    if ((zA || (f10.x() && U.i(f10))) && ((fG = f10.G()) == null || !fG.x())) {
                        tVar.l(f10, true);
                    } else if ((f10.W() || (f10.B() && U.h(f10))) && ((fG2 = f10.G()) == null || !fG2.B())) {
                        tVar.l(f10, false);
                    }
                    if (u4.d || !z11) {
                        return;
                    }
                    A(f10);
                    return;
                }
                return;
            }
        }
        u4.f1593h.b(new E0.T(f10, true, z10));
    }

    public final void w(F f10, boolean z6, boolean z10) {
        U u4 = this.f11643S;
        if (!z6) {
            u4.getClass();
            int iC = AbstractC2641j.c(f10.v());
            if (iC == 0 || iC == 1 || iC == 2 || iC == 3) {
                return;
            }
            if (iC != 4) {
                throw new A9.k();
            }
            if (!z10 && f10.W() == f10.X() && (f10.B() || f10.u())) {
                return;
            }
            f10.c0();
            if (!f10.f1478Q && f10.X()) {
                F fG = f10.G();
                if ((fG == null || !fG.u()) && (fG == null || !fG.B())) {
                    u4.f1589b.l(f10, false);
                }
                if (u4.d) {
                    return;
                }
                A(null);
                return;
            }
            return;
        }
        u4.getClass();
        int iC2 = AbstractC2641j.c(f10.v());
        if (iC2 != 0) {
            if (iC2 == 1) {
                return;
            }
            if (iC2 != 2) {
                if (iC2 == 3) {
                    return;
                }
                if (iC2 != 4) {
                    throw new A9.k();
                }
            }
        }
        if ((f10.x() || f10.w()) && !z10) {
            return;
        }
        f10.d0();
        f10.c0();
        if (f10.f1478Q) {
            return;
        }
        F fG2 = f10.G();
        boolean zA = m.a(f10.Y(), Boolean.TRUE);
        t tVar = u4.f1589b;
        if (zA && ((fG2 == null || !fG2.x()) && (fG2 == null || !fG2.w()))) {
            tVar.l(f10, true);
        } else if (f10.W() && ((fG2 == null || !fG2.u()) && (fG2 == null || !fG2.B()))) {
            tVar.l(f10, false);
        }
        if (u4.d) {
            return;
        }
        A(null);
    }

    public final void x() {
        M m10 = this.f11681x;
        m10.f2083y = true;
        if (m10.y() && !m10.f2061J) {
            m10.f2061J = true;
            m10.l.post(m10.f2062K);
        }
        ViewOnAttachStateChangeListenerC1400d viewOnAttachStateChangeListenerC1400d = this.contentCaptureManager;
        viewOnAttachStateChangeListenerC1400d.f15658s = true;
        if (!viewOnAttachStateChangeListenerC1400d.d() || viewOnAttachStateChangeListenerC1400d.f15652A) {
            return;
        }
        viewOnAttachStateChangeListenerC1400d.f15652A = true;
        viewOnAttachStateChangeListenerC1400d.f15661v.post(viewOnAttachStateChangeListenerC1400d.B);
    }

    public final void y() {
        if (this.f11651d0) {
            return;
        }
        long jCurrentAnimationTimeMillis = AnimationUtils.currentAnimationTimeMillis();
        if (jCurrentAnimationTimeMillis != this.lastMatrixRecalculationAnimationTime) {
            this.lastMatrixRecalculationAnimationTime = jCurrentAnimationTimeMillis;
            C0220o0 c0220o0 = this.G0;
            float[] fArr = this.f11648a0;
            c0220o0.a(this, fArr);
            T.s(fArr, this.f11649b0);
            ViewParent parent = getParent();
            View view = this;
            while (parent instanceof ViewGroup) {
                view = (View) parent;
                parent = ((ViewGroup) view).getParent();
            }
            int[] iArr = this.f11646V;
            view.getLocationOnScreen(iArr);
            float f10 = iArr[0];
            float f11 = iArr[1];
            view.getLocationInWindow(iArr);
            this.f11652e0 = e.a(f10 - iArr[0], f11 - iArr[1]);
        }
    }

    public final void z(i0 i0Var) {
        t tVar;
        Reference referencePoll;
        d dVar;
        if (this.f11640P != null) {
            X0 x02 = Z0.f2151A;
        }
        do {
            tVar = this.f11619A0;
            referencePoll = ((ReferenceQueue) tVar.f4151m).poll();
            dVar = (d) tVar.l;
            if (referencePoll != null) {
                dVar.n(referencePoll);
            }
        } while (referencePoll != null);
        dVar.b(new WeakReference(i0Var, (ReferenceQueue) tVar.f4151m));
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7) {
        m.c(view);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = generateDefaultLayoutParams();
        }
        addViewInLayout(view, i7, layoutParams, true);
    }

    @Override // androidx.compose.ui.node.Owner
    public C0203g getAccessibilityManager() {
        return this.accessibilityManager;
    }

    @Override // androidx.compose.ui.node.Owner
    public C0205h getClipboardManager() {
        return this.clipboardManager;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, int i10) {
        ViewGroup.LayoutParams layoutParamsGenerateDefaultLayoutParams = generateDefaultLayoutParams();
        layoutParamsGenerateDefaultLayoutParams.width = i7;
        layoutParamsGenerateDefaultLayoutParams.height = i10;
        addViewInLayout(view, -1, layoutParamsGenerateDefaultLayoutParams, true);
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i7, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, i7, layoutParams, true);
    }

    @Override // android.view.ViewGroup, android.view.ViewManager
    public final void addView(View view, ViewGroup.LayoutParams layoutParams) {
        addViewInLayout(view, -1, layoutParams, true);
    }
}
