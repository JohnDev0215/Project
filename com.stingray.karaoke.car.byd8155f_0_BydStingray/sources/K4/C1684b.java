package k4;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import l3.P;
import p4.C2104c;
import z4.C2768d;

/* JADX INFO: renamed from: k4.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1684b implements q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f18182a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f18183b;
    public final Object c;

    public /* synthetic */ C1684b(Object obj, int i7, Object obj2) {
        this.f18182a = i7;
        this.c = obj;
        this.f18183b = obj2;
    }

    @Override // k4.q
    public final p a(Object obj, int i7, int i10, e4.h hVar) {
        com.bumptech.glide.load.data.k kVar;
        p pVarA;
        Uri uri;
        switch (this.f18182a) {
            case 0:
                Uri uri2 = (Uri) obj;
                String strSubstring = uri2.toString().substring(22);
                C2768d c2768d = new C2768d(uri2);
                Object obj2 = this.f18183b;
                AssetManager assetManager = (AssetManager) this.c;
                switch (((C1683a) obj2).l) {
                    case 0:
                        kVar = new com.bumptech.glide.load.data.k(assetManager, strSubstring, 0);
                        break;
                    default:
                        kVar = new com.bumptech.glide.load.data.k(assetManager, strSubstring, 1);
                        break;
                }
                return new p(c2768d, kVar);
            case 1:
                Integer num = (Integer) obj;
                Resources.Theme theme = (Resources.Theme) hVar.c(C2104c.f20898b);
                return new p(new C2768d(num), new e(theme, theme != null ? theme.getResources() : ((Context) this.c).getResources(), (P6.a) this.f18183b, num.intValue()));
            case 2:
                ArrayList arrayList = (ArrayList) this.c;
                int size = arrayList.size();
                ArrayList arrayList2 = new ArrayList(size);
                e4.e eVar = null;
                for (int i11 = 0; i11 < size; i11++) {
                    q qVar = (q) arrayList.get(i11);
                    if (qVar.b(obj) && (pVarA = qVar.a(obj, i7, i10, hVar)) != null) {
                        arrayList2.add(pVarA.c);
                        eVar = pVarA.f18205a;
                    }
                }
                if (arrayList2.isEmpty() || eVar == null) {
                    return null;
                }
                return new p(eVar, new u(arrayList2, (P) this.f18183b));
            case 3:
                Integer num2 = (Integer) obj;
                Resources resources = (Resources) this.f18183b;
                try {
                    uri = Uri.parse("android.resource://" + resources.getResourcePackageName(num2.intValue()) + '/' + resources.getResourceTypeName(num2.intValue()) + '/' + resources.getResourceEntryName(num2.intValue()));
                    break;
                } catch (Resources.NotFoundException e10) {
                    if (Log.isLoggable("ResourceLoader", 5)) {
                        h6.n.u("ResourceLoader", "Received invalid resource id: " + num2, e10);
                    }
                    uri = null;
                }
                if (uri == null) {
                    return null;
                }
                return ((q) this.c).a(uri, i7, i10, hVar);
            default:
                Uri uri3 = (Uri) obj;
                List<String> pathSegments = uri3.getPathSegments();
                int size2 = pathSegments.size();
                q qVar2 = (q) this.f18183b;
                p pVarA2 = null;
                if (size2 == 1) {
                    try {
                        int i12 = Integer.parseInt(uri3.getPathSegments().get(0));
                        if (i12 != 0) {
                            pVarA2 = qVar2.a(Integer.valueOf(i12), i7, i10, hVar);
                        } else if (Log.isLoggable("ResourceUriLoader", 5)) {
                            h6.n.t("ResourceUriLoader", "Failed to parse a valid non-0 resource id from: " + uri3);
                        }
                        return pVarA2;
                    } catch (NumberFormatException e11) {
                        if (!Log.isLoggable("ResourceUriLoader", 5)) {
                            return pVarA2;
                        }
                        h6.n.u("ResourceUriLoader", "Failed to parse resource id from: " + uri3, e11);
                        return pVarA2;
                    }
                }
                if (pathSegments.size() != 2) {
                    if (!Log.isLoggable("ResourceUriLoader", 5)) {
                        return null;
                    }
                    h6.n.t("ResourceUriLoader", "Failed to parse resource uri: " + uri3);
                    return null;
                }
                List<String> pathSegments2 = uri3.getPathSegments();
                String str = pathSegments2.get(0);
                String str2 = pathSegments2.get(1);
                Context context = (Context) this.c;
                int identifier = context.getResources().getIdentifier(str2, str, context.getPackageName());
                if (identifier != 0) {
                    return qVar2.a(Integer.valueOf(identifier), i7, i10, hVar);
                }
                if (!Log.isLoggable("ResourceUriLoader", 5)) {
                    return null;
                }
                h6.n.t("ResourceUriLoader", "Failed to find resource id for: " + uri3);
                return null;
        }
    }

    @Override // k4.q
    public final boolean b(Object obj) {
        switch (this.f18182a) {
            case 0:
                Uri uri = (Uri) obj;
                if (!"file".equals(uri.getScheme()) || uri.getPathSegments().isEmpty() || !"android_asset".equals(uri.getPathSegments().get(0))) {
                }
                break;
            case 1:
                break;
            case 2:
                Iterator it = ((ArrayList) this.c).iterator();
                while (it.hasNext()) {
                    if (((q) it.next()).b(obj)) {
                        break;
                    }
                }
                break;
            case 3:
                break;
            default:
                Uri uri2 = (Uri) obj;
                if (!"android.resource".equals(uri2.getScheme()) || !((Context) this.c).getPackageName().equals(uri2.getAuthority())) {
                }
                break;
        }
        return true;
    }

    public String toString() {
        switch (this.f18182a) {
            case 2:
                return "MultiModelLoader{modelLoaders=" + Arrays.toString(((ArrayList) this.c).toArray()) + '}';
            default:
                return super.toString();
        }
    }

    public C1684b(Resources resources, q qVar) {
        this.f18182a = 3;
        this.f18183b = resources;
        this.c = qVar;
    }

    public C1684b(Context context, P6.a aVar) {
        this.f18182a = 1;
        this.c = context.getApplicationContext();
        this.f18183b = aVar;
    }

    public C1684b(Context context, q qVar) {
        this.f18182a = 4;
        this.c = context.getApplicationContext();
        this.f18183b = qVar;
    }
}
