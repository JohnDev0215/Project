package androidx.mediarouter.media;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import androidx.mediarouter.R$string;
import androidx.mediarouter.media.MediaRouteProvider;
import androidx.mediarouter.media.d;
import androidx.mediarouter.media.h;
import defpackage.ae1;
import defpackage.be1;
import defpackage.rd1;
import defpackage.td1;
import defpackage.wd1;
import defpackage.yd1;
import defpackage.zd1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public abstract class SystemMediaRouteProvider extends MediaRouteProvider {

    public static class LegacyImpl extends SystemMediaRouteProvider {
        public static final ArrayList<IntentFilter> o;
        public final AudioManager m;
        public int n;

        public final class a extends MediaRouteProvider.e {
            public a() {
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.e
            public final void g(int i) {
                LegacyImpl legacyImpl = LegacyImpl.this;
                legacyImpl.m.setStreamVolume(3, i, 0);
                legacyImpl.r();
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.e
            public final void j(int i) {
                LegacyImpl legacyImpl = LegacyImpl.this;
                int streamVolume = legacyImpl.m.getStreamVolume(3);
                if (Math.min(legacyImpl.m.getStreamMaxVolume(3), Math.max(0, i + streamVolume)) != streamVolume) {
                    legacyImpl.m.setStreamVolume(3, streamVolume, 0);
                }
                legacyImpl.r();
            }
        }

        public final class b extends BroadcastReceiver {
            public b() {
            }

            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context, Intent intent) {
                int intExtra;
                if (intent.getAction().equals("android.media.VOLUME_CHANGED_ACTION") && intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_TYPE", -1) == 3 && (intExtra = intent.getIntExtra("android.media.EXTRA_VOLUME_STREAM_VALUE", -1)) >= 0) {
                    LegacyImpl legacyImpl = LegacyImpl.this;
                    if (intExtra != legacyImpl.n) {
                        legacyImpl.r();
                    }
                }
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            intentFilter.addCategory("android.media.intent.category.LIVE_VIDEO");
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            o = arrayList;
            arrayList.add(intentFilter);
        }

        public LegacyImpl(Context context) {
            super(context);
            this.n = -1;
            this.m = (AudioManager) context.getSystemService("audio");
            context.registerReceiver(new b(), new IntentFilter("android.media.VOLUME_CHANGED_ACTION"));
            r();
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public final MediaRouteProvider.e m(String str) {
            if (str.equals("DEFAULT_ROUTE")) {
                return new a();
            }
            return null;
        }

        public final void r() {
            Resources resources = this.e.getResources();
            AudioManager audioManager = this.m;
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            this.n = audioManager.getStreamVolume(3);
            d.a aVar = new d.a("DEFAULT_ROUTE", resources.getString(R$string.mr_system_route_name));
            aVar.a(o);
            Bundle bundle = aVar.a;
            bundle.putInt("playbackStream", 3);
            bundle.putInt("playbackType", 0);
            bundle.putInt("volumeHandling", 1);
            bundle.putInt("volumeMax", streamMaxVolume);
            bundle.putInt("volume", this.n);
            androidx.mediarouter.media.d dVarB = aVar.b();
            ArrayList arrayList = new ArrayList();
            if (arrayList.contains(dVarB)) {
                throw new IllegalArgumentException("route descriptor already added");
            }
            arrayList.add(dVarB);
            p(new td1(arrayList, false));
        }
    }

    public static class a extends d {
        public a(Context context, h.d dVar) {
            super(context, dVar);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.d, androidx.mediarouter.media.SystemMediaRouteProvider.c, androidx.mediarouter.media.SystemMediaRouteProvider.b
        public final void x(b.C0015b c0015b, d.a aVar) {
            super.x(c0015b, aVar);
            aVar.a.putInt("deviceType", ((MediaRouter.RouteInfo) c0015b.a).getDeviceType());
        }
    }

    public static class c extends b implements ae1 {
        public c(Context context, h.d dVar) {
            super(context, dVar);
        }

        public boolean G(b.C0015b c0015b) {
            throw null;
        }

        @Override // defpackage.ae1
        public final void f(Object obj) {
            Display presentationDisplay;
            int iS = s(obj);
            if (iS >= 0) {
                b.C0015b c0015b = this.u.get(iS);
                try {
                    presentationDisplay = ((MediaRouter.RouteInfo) obj).getPresentationDisplay();
                } catch (NoSuchMethodError e) {
                    Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
                    presentationDisplay = null;
                }
                int displayId = presentationDisplay != null ? presentationDisplay.getDisplayId() : -1;
                if (displayId != c0015b.c.a.getInt("presentationDisplayId", -1)) {
                    androidx.mediarouter.media.d dVar = c0015b.c;
                    if (dVar == null) {
                        throw new IllegalArgumentException("descriptor must not be null");
                    }
                    Bundle bundle = new Bundle(dVar.a);
                    ArrayList<String> arrayList = !dVar.b().isEmpty() ? new ArrayList<>(dVar.b()) : null;
                    dVar.a();
                    ArrayList<? extends Parcelable> arrayList2 = dVar.c.isEmpty() ? null : new ArrayList<>(dVar.c);
                    bundle.putInt("presentationDisplayId", displayId);
                    if (arrayList2 != null) {
                        bundle.putParcelableArrayList("controlFilters", arrayList2);
                    }
                    if (arrayList != null) {
                        bundle.putStringArrayList("groupMemberIds", arrayList);
                    }
                    c0015b.c = new androidx.mediarouter.media.d(bundle);
                    B();
                }
            }
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.b
        public void x(b.C0015b c0015b, d.a aVar) {
            Display presentationDisplay;
            super.x(c0015b, aVar);
            Object obj = c0015b.a;
            boolean zIsEnabled = ((MediaRouter.RouteInfo) obj).isEnabled();
            Bundle bundle = aVar.a;
            if (!zIsEnabled) {
                bundle.putBoolean("enabled", false);
            }
            if (G(c0015b)) {
                bundle.putInt("connectionState", 1);
            }
            try {
                presentationDisplay = ((MediaRouter.RouteInfo) obj).getPresentationDisplay();
            } catch (NoSuchMethodError e) {
                Log.w("MediaRouterJellybeanMr1", "Cannot get presentation display for the route.", e);
                presentationDisplay = null;
            }
            if (presentationDisplay != null) {
                bundle.putInt("presentationDisplayId", presentationDisplay.getDisplayId());
            }
        }
    }

    public static class d extends c {
        public d(Context context, h.d dVar) {
            super(context, dVar);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.b
        public final void C(Object obj) {
            ((MediaRouter) this.n).selectRoute(8388611, (MediaRouter.RouteInfo) obj);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.b
        public final void D() {
            boolean z = this.t;
            Object obj = this.o;
            Object obj2 = this.n;
            if (z) {
                ((MediaRouter) obj2).removeCallback((MediaRouter.Callback) obj);
            }
            this.t = true;
            ((MediaRouter) obj2).addCallback(this.r, (MediaRouter.Callback) obj, (this.s ? 1 : 0) | 2);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.b
        public final void F(b.c cVar) {
            super.F(cVar);
            ((MediaRouter.UserRouteInfo) cVar.b).setDescription(cVar.a.e);
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.c
        public final boolean G(b.C0015b c0015b) {
            return ((MediaRouter.RouteInfo) c0015b.a).isConnecting();
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.b
        public final MediaRouter.RouteInfo v() {
            return ((MediaRouter) this.n).getDefaultRoute();
        }

        @Override // androidx.mediarouter.media.SystemMediaRouteProvider.c, androidx.mediarouter.media.SystemMediaRouteProvider.b
        public void x(b.C0015b c0015b, d.a aVar) {
            super.x(c0015b, aVar);
            CharSequence description = ((MediaRouter.RouteInfo) c0015b.a).getDescription();
            if (description != null) {
                aVar.a.putString("status", description.toString());
            }
        }
    }

    public interface e {
    }

    public SystemMediaRouteProvider(Context context) {
        super(context, new MediaRouteProvider.d(new ComponentName("android", SystemMediaRouteProvider.class.getName())));
    }

    public static class b extends SystemMediaRouteProvider implements wd1, yd1 {
        public static final ArrayList<IntentFilter> w;
        public static final ArrayList<IntentFilter> x;
        public final e m;
        public final Object n;
        public final Object o;
        public final zd1 p;
        public final MediaRouter.RouteCategory q;
        public int r;
        public boolean s;
        public boolean t;
        public final ArrayList<C0015b> u;
        public final ArrayList<c> v;

        public static final class a extends MediaRouteProvider.e {
            public final Object a;

            public a(Object obj) {
                this.a = obj;
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.e
            public final void g(int i) {
                ((MediaRouter.RouteInfo) this.a).requestSetVolume(i);
            }

            @Override // androidx.mediarouter.media.MediaRouteProvider.e
            public final void j(int i) {
                ((MediaRouter.RouteInfo) this.a).requestUpdateVolume(i);
            }
        }

        /* JADX INFO: renamed from: androidx.mediarouter.media.SystemMediaRouteProvider$b$b, reason: collision with other inner class name */
        public static final class C0015b {
            public final Object a;
            public final String b;
            public androidx.mediarouter.media.d c;

            public C0015b(Object obj, String str) {
                this.a = obj;
                this.b = str;
            }
        }

        public static final class c {
            public final h.C0020h a;
            public final Object b;

            public c(h.C0020h c0020h, MediaRouter.UserRouteInfo userRouteInfo) {
                this.a = c0020h;
                this.b = userRouteInfo;
            }
        }

        static {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addCategory("android.media.intent.category.LIVE_AUDIO");
            ArrayList<IntentFilter> arrayList = new ArrayList<>();
            w = arrayList;
            arrayList.add(intentFilter);
            IntentFilter intentFilter2 = new IntentFilter();
            intentFilter2.addCategory("android.media.intent.category.LIVE_VIDEO");
            ArrayList<IntentFilter> arrayList2 = new ArrayList<>();
            x = arrayList2;
            arrayList2.add(intentFilter2);
        }

        public b(Context context, h.d dVar) {
            super(context);
            this.u = new ArrayList<>();
            this.v = new ArrayList<>();
            this.m = dVar;
            Object systemService = context.getSystemService("media_router");
            this.n = systemService;
            this.o = new be1((c) this);
            this.p = new zd1(this);
            this.q = ((MediaRouter) systemService).createRouteCategory((CharSequence) context.getResources().getString(R$string.mr_user_route_category_name), false);
            E();
        }

        public static c w(Object obj) {
            Object tag = ((MediaRouter.RouteInfo) obj).getTag();
            if (tag instanceof c) {
                return (c) tag;
            }
            return null;
        }

        public final void A(h.C0020h c0020h) {
            if (c0020h.i()) {
                if (c0020h.d() != this) {
                    int iU = u(c0020h);
                    if (iU >= 0) {
                        C(this.v.get(iU).b);
                        return;
                    }
                    return;
                }
                int iT = t(c0020h.b);
                if (iT >= 0) {
                    C(this.u.get(iT).a);
                }
            }
        }

        public final void B() {
            ArrayList arrayList = new ArrayList();
            ArrayList<C0015b> arrayList2 = this.u;
            int size = arrayList2.size();
            for (int i = 0; i < size; i++) {
                androidx.mediarouter.media.d dVar = arrayList2.get(i).c;
                if (dVar == null) {
                    throw new IllegalArgumentException("route must not be null");
                }
                if (arrayList.contains(dVar)) {
                    throw new IllegalArgumentException("route descriptor already added");
                }
                arrayList.add(dVar);
            }
            p(new td1(arrayList, false));
        }

        public void C(Object obj) {
            throw null;
        }

        public void D() {
            throw null;
        }

        public final void E() {
            D();
            MediaRouter mediaRouter = (MediaRouter) this.n;
            int routeCount = mediaRouter.getRouteCount();
            ArrayList arrayList = new ArrayList(routeCount);
            boolean zR = false;
            for (int i = 0; i < routeCount; i++) {
                arrayList.add(mediaRouter.getRouteAt(i));
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                zR |= r(it.next());
            }
            if (zR) {
                B();
            }
        }

        public void F(c cVar) {
            Object obj = cVar.b;
            h.C0020h c0020h = cVar.a;
            ((MediaRouter.UserRouteInfo) obj).setName(c0020h.d);
            int i = c0020h.k;
            MediaRouter.UserRouteInfo userRouteInfo = (MediaRouter.UserRouteInfo) cVar.b;
            userRouteInfo.setPlaybackType(i);
            userRouteInfo.setPlaybackStream(c0020h.l);
            userRouteInfo.setVolume(c0020h.o);
            userRouteInfo.setVolumeMax(c0020h.p);
            userRouteInfo.setVolumeHandling(c0020h.e());
        }

        @Override // defpackage.wd1
        public final void b(Object obj) {
            int iS;
            if (w(obj) != null || (iS = s(obj)) < 0) {
                return;
            }
            C0015b c0015b = this.u.get(iS);
            String str = c0015b.b;
            CharSequence name = ((MediaRouter.RouteInfo) c0015b.a).getName(this.e);
            d.a aVar = new d.a(str, name != null ? name.toString() : "");
            x(c0015b, aVar);
            c0015b.c = aVar.b();
            B();
        }

        @Override // defpackage.yd1
        public final void c(int i, Object obj) {
            c cVarW = w(obj);
            if (cVarW != null) {
                cVarW.a.m(i);
            }
        }

        @Override // defpackage.wd1
        public final void d(Object obj) {
            int iS;
            if (w(obj) != null || (iS = s(obj)) < 0) {
                return;
            }
            this.u.remove(iS);
            B();
        }

        @Override // defpackage.wd1
        public final void e(Object obj) {
            h.C0020h c0020hA;
            if (obj != ((MediaRouter) this.n).getSelectedRoute(8388611)) {
                return;
            }
            c cVarW = w(obj);
            if (cVarW != null) {
                cVarW.a.n();
                return;
            }
            int iS = s(obj);
            if (iS >= 0) {
                String str = this.u.get(iS).b;
                h.d dVar = (h.d) this.m;
                dVar.m.removeMessages(262);
                h.g gVarE = dVar.e(dVar.c);
                if (gVarE == null || (c0020hA = gVarE.a(str)) == null) {
                    return;
                }
                c0020hA.n();
            }
        }

        @Override // defpackage.yd1
        public final void i(int i, Object obj) {
            c cVarW = w(obj);
            if (cVarW != null) {
                cVarW.a.l(i);
            }
        }

        @Override // defpackage.wd1
        public final void j(Object obj) {
            if (r(obj)) {
                B();
            }
        }

        @Override // defpackage.wd1
        public final void k(Object obj) {
            int iS;
            if (w(obj) != null || (iS = s(obj)) < 0) {
                return;
            }
            C0015b c0015b = this.u.get(iS);
            int volume = ((MediaRouter.RouteInfo) obj).getVolume();
            if (volume != c0015b.c.f()) {
                androidx.mediarouter.media.d dVar = c0015b.c;
                if (dVar == null) {
                    throw new IllegalArgumentException("descriptor must not be null");
                }
                Bundle bundle = new Bundle(dVar.a);
                ArrayList<String> arrayList = !dVar.b().isEmpty() ? new ArrayList<>(dVar.b()) : null;
                dVar.a();
                ArrayList<? extends Parcelable> arrayList2 = dVar.c.isEmpty() ? null : new ArrayList<>(dVar.c);
                bundle.putInt("volume", volume);
                if (arrayList2 != null) {
                    bundle.putParcelableArrayList("controlFilters", arrayList2);
                }
                if (arrayList != null) {
                    bundle.putStringArrayList("groupMemberIds", arrayList);
                }
                c0015b.c = new androidx.mediarouter.media.d(bundle);
                B();
            }
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public final MediaRouteProvider.e m(String str) {
            int iT = t(str);
            if (iT >= 0) {
                return new a(this.u.get(iT).a);
            }
            return null;
        }

        @Override // androidx.mediarouter.media.MediaRouteProvider
        public final void o(rd1 rd1Var) {
            boolean zB;
            int i = 0;
            if (rd1Var != null) {
                rd1Var.a();
                ArrayList arrayListB = rd1Var.b.b();
                int size = arrayListB.size();
                int i2 = 0;
                while (i < size) {
                    String str = (String) arrayListB.get(i);
                    i2 = str.equals("android.media.intent.category.LIVE_AUDIO") ? i2 | 1 : str.equals("android.media.intent.category.LIVE_VIDEO") ? i2 | 2 : i2 | 8388608;
                    i++;
                }
                zB = rd1Var.b();
                i = i2;
            } else {
                zB = false;
            }
            if (this.r == i && this.s == zB) {
                return;
            }
            this.r = i;
            this.s = zB;
            E();
        }

        public final boolean r(Object obj) {
            String str;
            String str2;
            if (w(obj) != null || s(obj) >= 0) {
                return false;
            }
            boolean z = v() == obj;
            Context context = this.e;
            if (z) {
                str = "DEFAULT_ROUTE";
            } else {
                Locale locale = Locale.US;
                Object[] objArr = new Object[1];
                CharSequence name = ((MediaRouter.RouteInfo) obj).getName(context);
                objArr[0] = Integer.valueOf((name != null ? name.toString() : "").hashCode());
                str = String.format(locale, "ROUTE_%08x", objArr);
            }
            if (t(str) >= 0) {
                int i = 2;
                while (true) {
                    str2 = String.format(Locale.US, "%s_%d", str, Integer.valueOf(i));
                    if (t(str2) < 0) {
                        break;
                    }
                    i++;
                }
                str = str2;
            }
            C0015b c0015b = new C0015b(obj, str);
            CharSequence name2 = ((MediaRouter.RouteInfo) obj).getName(context);
            d.a aVar = new d.a(str, name2 != null ? name2.toString() : "");
            x(c0015b, aVar);
            c0015b.c = aVar.b();
            this.u.add(c0015b);
            return true;
        }

        public final int s(Object obj) {
            ArrayList<C0015b> arrayList = this.u;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).a == obj) {
                    return i;
                }
            }
            return -1;
        }

        public final int t(String str) {
            ArrayList<C0015b> arrayList = this.u;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).b.equals(str)) {
                    return i;
                }
            }
            return -1;
        }

        public final int u(h.C0020h c0020h) {
            ArrayList<c> arrayList = this.v;
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (arrayList.get(i).a == c0020h) {
                    return i;
                }
            }
            return -1;
        }

        public MediaRouter.RouteInfo v() {
            throw null;
        }

        public void x(C0015b c0015b, d.a aVar) {
            int supportedTypes = ((MediaRouter.RouteInfo) c0015b.a).getSupportedTypes();
            if ((supportedTypes & 1) != 0) {
                aVar.a(w);
            }
            if ((supportedTypes & 2) != 0) {
                aVar.a(x);
            }
            MediaRouter.RouteInfo routeInfo = (MediaRouter.RouteInfo) c0015b.a;
            int playbackType = routeInfo.getPlaybackType();
            Bundle bundle = aVar.a;
            bundle.putInt("playbackType", playbackType);
            bundle.putInt("playbackStream", routeInfo.getPlaybackStream());
            bundle.putInt("volume", routeInfo.getVolume());
            bundle.putInt("volumeMax", routeInfo.getVolumeMax());
            bundle.putInt("volumeHandling", routeInfo.getVolumeHandling());
        }

        public final void y(h.C0020h c0020h) {
            MediaRouteProvider mediaRouteProviderD = c0020h.d();
            Object obj = this.n;
            if (mediaRouteProviderD == this) {
                int iS = s(((MediaRouter) obj).getSelectedRoute(8388611));
                if (iS < 0 || !this.u.get(iS).b.equals(c0020h.b)) {
                    return;
                }
                c0020h.n();
                return;
            }
            MediaRouter mediaRouter = (MediaRouter) obj;
            MediaRouter.UserRouteInfo userRouteInfoCreateUserRoute = mediaRouter.createUserRoute(this.q);
            c cVar = new c(c0020h, userRouteInfoCreateUserRoute);
            userRouteInfoCreateUserRoute.setTag(cVar);
            userRouteInfoCreateUserRoute.setVolumeCallback(this.p);
            F(cVar);
            this.v.add(cVar);
            mediaRouter.addUserRoute(userRouteInfoCreateUserRoute);
        }

        public final void z(h.C0020h c0020h) {
            int iU;
            if (c0020h.d() == this || (iU = u(c0020h)) < 0) {
                return;
            }
            c cVarRemove = this.v.remove(iU);
            ((MediaRouter.RouteInfo) cVarRemove.b).setTag(null);
            Object obj = cVarRemove.b;
            ((MediaRouter.UserRouteInfo) obj).setVolumeCallback(null);
            try {
                ((MediaRouter) this.n).removeUserRoute((MediaRouter.UserRouteInfo) obj);
            } catch (IllegalArgumentException e) {
                Log.w("MediaRouterJellybean", "Failed to remove user route", e);
            }
        }

        @Override // defpackage.wd1
        public final void a() {
        }

        @Override // defpackage.wd1
        public final void g() {
        }

        @Override // defpackage.wd1
        public final void h() {
        }
    }
}
