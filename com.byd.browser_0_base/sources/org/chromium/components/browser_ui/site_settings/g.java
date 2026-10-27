package org.chromium.components.browser_ui.site_settings;

import J.N;
import android.util.Pair;
import defpackage.d02;
import defpackage.gq2;
import defpackage.i00;
import defpackage.iv;
import defpackage.jj0;
import defpackage.kr;
import defpackage.no;
import defpackage.ts3;
import defpackage.wy;
import defpackage.yn2;
import defpackage.zn2;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.content_public.browser.BrowserContextHandle;

/* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
/* JADX INFO: loaded from: classes2.dex */
public final class g {
    public final BrowserContextHandle a;
    public final WebsitePreferenceBridge b = new WebsitePreferenceBridge();
    public final boolean c;

    /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
    public static class a extends Pair<ts3, ts3> {
        public a(ts3 ts3Var, ts3 ts3Var2) {
            super(ts3Var, ts3Var2);
        }

        @Override // android.util.Pair
        public final int hashCode() {
            Object obj = ((Pair) this).first;
            int iHashCode = ((obj == null ? 0 : ((ts3) obj).hashCode()) + 31) * 31;
            Object obj2 = ((Pair) this).second;
            return iHashCode + (obj2 != null ? ((ts3) obj2).hashCode() : 0);
        }
    }

    /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
    public interface c {
        void a(Collection<f> collection);
    }

    public g(Profile profile, boolean z) {
        this.a = profile;
        this.c = z;
    }

    public final void a(yn2 yn2Var, c cVar) {
        b bVar = new b();
        b.i iVarC = bVar.c(yn2Var);
        iVarC.add(bVar.new C0126g(cVar));
        iVarC.b();
    }

    public final void b(zn2 zn2Var, yn2 yn2Var, c cVar) {
        b bVar = new b();
        b.i iVarC = bVar.c(yn2Var);
        iVarC.add(bVar.new d(zn2Var));
        iVarC.add(bVar.new C0126g(cVar));
        iVarC.b();
    }

    /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
    public class b {
        public final HashMap a = new HashMap();

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class a extends h {
            public final int a;

            public a(int i) {
                this.a = i != 20 ? i != 35 ? -1 : 19 : 51;
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void a() {
                int i = this.a;
                if (i == -1) {
                    return;
                }
                b bVar = b.this;
                g gVar = g.this;
                gVar.b.getClass();
                ArrayList<no> arrayList = new ArrayList();
                N.MA5QWuba(gVar.a, i, arrayList);
                for (no noVar : arrayList) {
                    String str = noVar.f;
                    if (str != null) {
                        bVar.d(str, null).m.add(noVar);
                    }
                }
            }
        }

        /* JADX INFO: renamed from: org.chromium.components.browser_ui.site_settings.g$b$b, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class C0125b extends h {

            /* JADX INFO: renamed from: org.chromium.components.browser_ui.site_settings.g$b$b$a */
            /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
            public class a implements Callback<Map<String, i00>> {
                public final /* synthetic */ i e;

                public a(i iVar) {
                    this.e = iVar;
                }

                @Override // org.chromium.base.Callback
                public final void onResult(Map<String, i00> map) {
                    for (Map.Entry<String, i00> entry : map.entrySet()) {
                        String key = entry.getKey();
                        if (key != null) {
                            b.this.d(key, null).k = entry.getValue();
                        }
                    }
                    this.e.b();
                }
            }

            public C0125b() {
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void b(i iVar) {
                g gVar = g.this;
                WebsitePreferenceBridge websitePreferenceBridge = gVar.b;
                BrowserContextHandle browserContextHandle = gVar.a;
                a aVar = new a(iVar);
                websitePreferenceBridge.getClass();
                N.M2bpUVE0(browserContextHandle, aVar);
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class c extends h {
            public final int a;

            public c(int i) {
                this.a = i;
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void a() {
                b bVar = b.this;
                g gVar = g.this;
                gVar.b.getClass();
                BrowserContextHandle browserContextHandle = gVar.a;
                int i = this.a;
                for (wy wyVar : WebsitePreferenceBridge.a(browserContextHandle, i)) {
                    String strH = wyVar.f;
                    if (strH != null) {
                        String str = wyVar.g;
                        if (!strH.equals(str) || !strH.equals("*")) {
                            if (!(strH.equals("*") || strH.startsWith("[*.]"))) {
                                strH = ts3.e(strH).h();
                            }
                            bVar.d(strH, str).n(i, wyVar);
                        }
                    }
                }
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class d extends h {
            public final zn2 a;

            public d(zn2 zn2Var) {
                this.a = zn2Var;
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void a() {
                zn2 zn2Var = this.a;
                if (zn2Var != null && ((kr) zn2Var).b() && N.MhilDEgf()) {
                    HashMap map = new HashMap();
                    b bVar = b.this;
                    for (org.chromium.components.browser_ui.site_settings.f fVar : bVar.a.values()) {
                        String strF = fVar.e.f();
                        String strH = fVar.e.h();
                        ((kr) zn2Var).getClass();
                        String strMO_kgJTV = N.MO_kgJTV(strH);
                        if (strMO_kgJTV != null) {
                            Set hashSet = (Set) map.get(strMO_kgJTV);
                            if (hashSet == null) {
                                hashSet = new HashSet();
                            }
                            hashSet.add(strF);
                            map.put(strMO_kgJTV, hashSet);
                        }
                    }
                    for (org.chromium.components.browser_ui.site_settings.f fVar2 : bVar.a.values()) {
                        String strH2 = fVar2.e.h();
                        ((kr) zn2Var).getClass();
                        String strMO_kgJTV2 = N.MO_kgJTV(strH2);
                        if (strMO_kgJTV2 != null) {
                            fVar2.j = new jj0(strMO_kgJTV2, ((Set) map.get(strMO_kgJTV2)).size());
                        }
                    }
                }
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class e extends h {

            /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
            public class a implements Callback<HashMap> {
                public final /* synthetic */ i e;

                public a(i iVar) {
                    this.e = iVar;
                }

                @Override // org.chromium.base.Callback
                public final void onResult(HashMap map) {
                    for (Map.Entry entry : map.entrySet()) {
                        String str = (String) entry.getKey();
                        if (str != null) {
                            b.this.d(str, null).i = (org.chromium.components.browser_ui.site_settings.b) entry.getValue();
                        }
                    }
                    this.e.b();
                }
            }

            public e() {
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void b(i iVar) {
                g gVar = g.this;
                WebsitePreferenceBridge websitePreferenceBridge = gVar.b;
                BrowserContextHandle browserContextHandle = gVar.a;
                a aVar = new a(iVar);
                boolean z = gVar.c;
                websitePreferenceBridge.getClass();
                N.MvKW9dxN(browserContextHandle, aVar, z);
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class f extends h {
            public final int a;

            public f(int i) {
                this.a = i;
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void a() {
                b bVar = b.this;
                g gVar = g.this;
                gVar.b.getClass();
                ArrayList<d02> arrayList = new ArrayList();
                BrowserContextHandle browserContextHandle = gVar.a;
                int i = this.a;
                N.MexN59P3(browserContextHandle, i, arrayList, (i == 4 || i == 8 || i == 9) ? !N.MB23OvTV(browserContextHandle, i) : false);
                for (d02 d02Var : arrayList) {
                    String str = d02Var.g;
                    if (str != null) {
                        bVar.d(str, i == 32 ? null : d02Var.f).h.put(Integer.valueOf(d02Var.h), d02Var);
                    }
                }
            }
        }

        /* JADX INFO: renamed from: org.chromium.components.browser_ui.site_settings.g$b$g, reason: collision with other inner class name */
        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class C0126g extends h {
            public final c a;

            public C0126g(c cVar) {
                this.a = cVar;
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void a() {
                this.a.a(b.this.a.values());
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class i extends LinkedList<h> {
            public final void b() {
                if (isEmpty()) {
                    return;
                }
                removeFirst().b(this);
            }
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public class j extends h {

            /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
            public class a implements Callback<ArrayList> {
                public final /* synthetic */ i e;

                public a(i iVar) {
                    this.e = iVar;
                }

                @Override // org.chromium.base.Callback
                public final void onResult(ArrayList arrayList) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        org.chromium.components.browser_ui.site_settings.e eVar = (org.chromium.components.browser_ui.site_settings.e) it.next();
                        String str = eVar.e;
                        if (str != null) {
                            b.this.d(ts3.e(str).h(), null).l.add(eVar);
                        }
                    }
                    this.e.b();
                }
            }

            public j() {
            }

            @Override // org.chromium.components.browser_ui.site_settings.g.b.h
            public final void b(i iVar) {
                g gVar = g.this;
                WebsitePreferenceBridge websitePreferenceBridge = gVar.b;
                BrowserContextHandle browserContextHandle = gVar.a;
                a aVar = new a(iVar);
                websitePreferenceBridge.getClass();
                N.MlZM1XeP(browserContextHandle, aVar);
            }
        }

        public b() {
        }

        public final void a(i iVar) {
            iVar.add(new e());
            iVar.add(new j());
            if (N.ManEQDnV("SiteDataImprovements")) {
                iVar.add(new C0125b());
            }
            for (int i2 = 0; i2 < 87; i2++) {
                b(iVar, i2);
            }
        }

        public final void b(i iVar, int i2) {
            int i3;
            switch (i2) {
                case 0:
                case 2:
                case 3:
                case 12:
                case 21:
                case 25:
                case 30:
                case 43:
                case 68:
                case 72:
                case 73:
                case 74:
                case 85:
                    i3 = 1;
                    break;
                case 4:
                case 5:
                case 8:
                case 9:
                case 13:
                case 15:
                case 32:
                case 38:
                case 50:
                case 52:
                case 55:
                case 56:
                    i3 = 2;
                    break;
                case 20:
                case 35:
                    i3 = 3;
                    break;
                default:
                    i3 = 0;
                    break;
            }
            if (i3 == 0) {
                return;
            }
            if (i2 != 43 || iv.e().g("enable-experimental-web-platform-features")) {
                if (i2 != 50 || N.Mudil8Bg("WebNFC")) {
                    if (i2 != 20 || N.Mudil8Bg("WebBluetoothNewPermissionsBackend")) {
                        int iM = gq2.m(i3);
                        if (iM == 0) {
                            iVar.add(new c(i2));
                        } else if (iM == 1) {
                            iVar.add(new f(i2));
                        } else {
                            if (iM != 2) {
                                return;
                            }
                            iVar.add(new a(i2));
                        }
                    }
                }
            }
        }

        public final i c(yn2 yn2Var) {
            i iVar = new i();
            int i2 = yn2Var.b;
            if (i2 == 0) {
                a(iVar);
            } else if (i2 == 22) {
                iVar.add(new e());
                iVar.add(new j());
            } else {
                b(iVar, yn2Var.f());
            }
            return iVar;
        }

        public final org.chromium.components.browser_ui.site_settings.f d(String str, String str2) {
            if (str2 != null && (str2.equals(str) || str2.equals("*"))) {
                str2 = null;
            }
            ts3 ts3VarE = ts3.e(str);
            ts3 ts3VarE2 = ts3.e(str2);
            a aVar = new a(ts3VarE, ts3VarE2);
            HashMap map = this.a;
            org.chromium.components.browser_ui.site_settings.f fVar = (org.chromium.components.browser_ui.site_settings.f) map.get(aVar);
            if (fVar != null) {
                return fVar;
            }
            org.chromium.components.browser_ui.site_settings.f fVar2 = new org.chromium.components.browser_ui.site_settings.f(ts3VarE, ts3VarE2);
            map.put(aVar, fVar2);
            return fVar2;
        }

        /* JADX INFO: compiled from: WebsitePermissionsFetcher.java */
        public abstract class h {
            public void b(i iVar) {
                a();
                iVar.b();
            }

            public void a() {
            }
        }
    }
}
