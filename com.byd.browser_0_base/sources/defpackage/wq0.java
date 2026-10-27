package defpackage;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Trace;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.helper.widget.LogJson;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.emoji2.text.d;
import androidx.emoji2.text.g;
import androidx.emoji2.text.h;
import androidx.mediarouter.media.h;
import com.google.android.apps.chrome.appwidget.bookmarks.BookmarkThumbnailWidgetProvider;
import defpackage.aa0;
import defpackage.fc;
import defpackage.h20;
import defpackage.mh;
import defpackage.t90;
import java.nio.MappedByteBuffer;
import java.util.ArrayList;
import org.chromium.base.MemoryPressureListener;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.b;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.DelayedScreenLockIntentHandler;
import org.chromium.chrome.browser.bookmarkswidget.BookmarkWidgetProvider;
import org.chromium.chrome.browser.compositor.bottombar.ephemeraltab.EphemeralTabCoordinator;
import org.chromium.chrome.browser.content_creation.notes.NoteCreationDialog;
import org.chromium.chrome.browser.customtabs.BaseCustomTabActivity;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.content_public.browser.LoadUrlParams;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class wq0 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ Object f;

    public /* synthetic */ wq0(int i, Object obj) {
        this.e = i;
        this.f = obj;
    }

    private final void a() {
        g.b bVar = (g.b) this.f;
        synchronized (bVar.d) {
            if (bVar.h == null) {
                return;
            }
            try {
                xo0 xo0VarD = bVar.d();
                int i = xo0VarD.e;
                if (i == 2) {
                    synchronized (bVar.d) {
                    }
                }
                if (i != 0) {
                    throw new RuntimeException("fetchFonts result is not OK. (" + i + ")");
                }
                try {
                    Trace.beginSection("EmojiCompat.FontRequestEmojiCompatConfig.buildTypeface");
                    g.a aVar = bVar.c;
                    Context context = bVar.a;
                    aVar.getClass();
                    Typeface typefaceA = gh3.a(context, new xo0[]{xo0VarD}, 0);
                    MappedByteBuffer mappedByteBufferA = jh3.a(bVar.a, xo0VarD.a);
                    if (mappedByteBufferA == null || typefaceA == null) {
                        throw new RuntimeException("Unable to open file.");
                    }
                    try {
                        Trace.beginSection("EmojiCompat.MetadataRepo.create");
                        h hVar = new h(typefaceA, oh1.a(mappedByteBufferA));
                        Trace.endSection();
                        synchronized (bVar.d) {
                            d.i iVar = bVar.h;
                            if (iVar != null) {
                                iVar.b(hVar);
                            }
                        }
                        bVar.b();
                    } finally {
                        Trace.endSection();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } catch (Throwable th2) {
                synchronized (bVar.d) {
                    d.i iVar2 = bVar.h;
                    if (iVar2 != null) {
                        iVar2.a(th2);
                    }
                    bVar.b();
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        Integer num;
        Object obj = this.f;
        switch (this.e) {
            case 1:
                AppCompatDelegate.c((Context) obj);
                break;
            case 2:
                ((LogJson) obj).v();
                break;
            case 3:
                int i = ContentLoadingProgressBar.g;
                ((ContentLoadingProgressBar) obj).setVisibility(8);
                break;
            case 4:
                a();
                break;
            case 5:
                ArrayList arrayList = (ArrayList) obj;
                i21.e(arrayList, "$transitioningViews");
                vp0.b(4, arrayList);
                break;
            case 6:
                ((h.f) obj).a();
                break;
            case 7:
                ((TraceEvent.a) obj).b();
                break;
            case 8:
                ve1 ve1Var = (ve1) obj;
                ve1Var.d = false;
                Integer num2 = ve1Var.c;
                if (num2 != null && ve1Var.b != num2.intValue()) {
                    int iIntValue = ve1Var.c.intValue();
                    ve1Var.c = null;
                    ve1Var.b(iIntValue);
                    break;
                } else if (ve1Var.e && ve1Var.b == 2 && (num = (Integer) ve1Var.f.get()) != null) {
                    ve1Var.b(num.intValue());
                    break;
                }
                break;
            case 9:
                qe1 qe1Var = (qe1) obj;
                Object obj2 = ThreadUtils.a;
                if (MemoryPressureListener.a == null) {
                    MemoryPressureListener.a = new b<>();
                }
                MemoryPressureListener.a.a(qe1Var);
                break;
            case 10:
                int i2 = DelayedScreenLockIntentHandler.f;
                ((DelayedScreenLockIntentHandler) obj).a(null);
                break;
            case 11:
                tf0 tf0Var = (tf0) obj;
                int i3 = tf0.D;
                tf0Var.getClass();
                if (!qo.f().c()) {
                    ArrayList arrayListC = tf0Var.c(false);
                    if (arrayListC.isEmpty()) {
                        tf0Var.p.requestFocus();
                    } else {
                        ((yf0) arrayListC.get(0)).a();
                    }
                }
                if (tf0Var.getCurrentFocus() != null) {
                    t41.f.i(tf0Var.getCurrentFocus());
                    if (tf0Var.getCurrentFocus() instanceof EditText) {
                        EditText editText = (EditText) tf0Var.getCurrentFocus();
                        editText.setSelection(editText.getText().length());
                    }
                }
                break;
            case 12:
                ((fc.a) obj).b(false);
                break;
            case 13:
                ((i7) ((i6) obj).f).c(null);
                break;
            case 14:
                vg vgVar = (vg) obj;
                vgVar.k = true;
                vgVar.e.c(vgVar.f, true);
                break;
            case 15:
                mh.b bVar = (mh.b) obj;
                bVar.getClass();
                Context context = bVar.e;
                context.sendBroadcast(new Intent(BookmarkWidgetProvider.a(context), null, context, BookmarkThumbnailWidgetProvider.class).putExtra("appWidgetId", bVar.f));
                break;
            case 16:
                ((aa0.q) ((t90.d) obj)).a(5, new r72[0]);
                break;
            case 17:
                vx.a((t90.a) obj);
                break;
            case 18:
                oa0 oa0Var = (oa0) obj;
                h20.b bVar2 = oa0Var.f.h;
                boolean z = (bVar2 == null || bVar2.c == 2) ? false : true;
                ag3 ag3Var = oa0Var.e;
                if (!z) {
                    ag3Var.p(ag3.d, null);
                    PropertyModel.p pVar = ag3.c;
                    if (ag3Var.h(pVar) == 1) {
                        ag3Var.n(pVar, 0);
                    }
                } else {
                    ag3Var.p(ag3.d, bVar2.a);
                    oa0Var.e();
                }
                break;
            case 19:
                TraceEvent.I0(((sq2) obj).hashCode(), "SplashScreen.visible");
                break;
            case 20:
                xf3 xf3Var = (xf3) obj;
                h20.b bVar3 = xf3Var.b.h;
                if (bVar3 != null && bVar3.c == 1) {
                    xf3Var.c.a(xf3Var.d.e, bVar3.b, ft1.b(bVar3.a));
                    break;
                }
                break;
            case 21:
                EphemeralTabCoordinator ephemeralTabCoordinator = (EphemeralTabCoordinator) obj;
                if (ephemeralTabCoordinator.l && ephemeralTabCoordinator.q != null) {
                    ephemeralTabCoordinator.j.a(ephemeralTabCoordinator.o, true, 7);
                    GURL gurl = ephemeralTabCoordinator.r;
                    if (gurl == null) {
                        gurl = ephemeralTabCoordinator.q;
                    }
                    ephemeralTabCoordinator.i.get().b(0, (Tab) ephemeralTabCoordinator.h.f, new LoadUrlParams(gurl.j(), 0));
                    break;
                }
                break;
            case 22:
                int i4 = NoteCreationDialog.z0;
                ((NoteCreationDialog) obj).W0();
                break;
            case 23:
                BaseCustomTabActivity baseCustomTabActivity = (BaseCustomTabActivity) obj;
                int i5 = BaseCustomTabActivity.o1;
                if (!baseCustomTabActivity.d()) {
                    md mdVar = baseCustomTabActivity.a1;
                    mdVar.getClass();
                    if (!fb2.b(Profile.d(), mdVar.W, mdVar.g) && mdVar.h != null) {
                        mdVar.L0 = t80.a(mdVar.g, mdVar.j, mdVar.k, Profile.d(), mdVar.x.h(), mdVar.h.c, mdVar.g());
                        break;
                    }
                }
                break;
            case 24:
                ((ow1) obj).h();
                break;
            case 25:
                ((aw1) obj).M(true);
                break;
            case 26:
                ((cw1) obj).getClass();
                break;
            case 27:
                lw1 lw1Var = (lw1) obj;
                lw1Var.p.run();
                lw1Var.p = null;
                break;
            case 28:
                ((i7) ((t80) obj).b).c(Integer.valueOf(R$id.request_desktop_site_id));
                break;
            default:
                ye0 ye0Var = (ye0) obj;
                ye0Var.a.b(3, ye0Var.b);
                ye0.a(3, false);
                break;
        }
    }
}
