package androidx.mediarouter.media;

import android.annotation.SuppressLint;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.RestrictTo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    final Bundle f983a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    List<String> f984b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    List<IntentFilter> f985c;

    p(Bundle bundle) {
        this.f983a = bundle;
    }

    public static p e(Bundle bundle) {
        if (bundle != null) {
            return new p(bundle);
        }
        return null;
    }

    public Bundle a() {
        return this.f983a;
    }

    public boolean b() {
        return this.f983a.getBoolean("canDisconnect", false);
    }

    void c() {
        if (this.f985c == null) {
            ArrayList parcelableArrayList = this.f983a.getParcelableArrayList("controlFilters");
            this.f985c = parcelableArrayList;
            if (parcelableArrayList == null) {
                this.f985c = Collections.emptyList();
            }
        }
    }

    void d() {
        if (this.f984b == null) {
            ArrayList<String> stringArrayList = this.f983a.getStringArrayList("groupMemberIds");
            this.f984b = stringArrayList;
            if (stringArrayList == null) {
                this.f984b = Collections.emptyList();
            }
        }
    }

    public int f() {
        return this.f983a.getInt("connectionState", 0);
    }

    public List<IntentFilter> g() {
        c();
        return this.f985c;
    }

    public String h() {
        return this.f983a.getString("status");
    }

    public int i() {
        return this.f983a.getInt("deviceType");
    }

    public Bundle j() {
        return this.f983a.getBundle("extras");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public List<String> k() {
        d();
        return this.f984b;
    }

    public Uri l() {
        String string = this.f983a.getString("iconUri");
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public String m() {
        return this.f983a.getString("id");
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int n() {
        return this.f983a.getInt("maxClientVersion", Integer.MAX_VALUE);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int o() {
        return this.f983a.getInt("minClientVersion", 1);
    }

    public String p() {
        return this.f983a.getString("name");
    }

    public int q() {
        return this.f983a.getInt("playbackStream", -1);
    }

    public int r() {
        return this.f983a.getInt("playbackType", 1);
    }

    public int s() {
        return this.f983a.getInt("presentationDisplayId", -1);
    }

    public IntentSender t() {
        return (IntentSender) this.f983a.getParcelable("settingsIntent");
    }

    public String toString() {
        return "MediaRouteDescriptor{ id=" + m() + ", groupMemberIds=" + k() + ", name=" + p() + ", description=" + h() + ", iconUri=" + l() + ", isEnabled=" + x() + ", connectionState=" + f() + ", controlFilters=" + Arrays.toString(g().toArray()) + ", playbackType=" + r() + ", playbackStream=" + q() + ", deviceType=" + i() + ", volume=" + u() + ", volumeMax=" + w() + ", volumeHandling=" + v() + ", presentationDisplayId=" + s() + ", extras=" + j() + ", isValid=" + y() + ", minClientVersion=" + o() + ", maxClientVersion=" + n() + " }";
    }

    public int u() {
        return this.f983a.getInt("volume");
    }

    public int v() {
        return this.f983a.getInt("volumeHandling", 0);
    }

    public int w() {
        return this.f983a.getInt("volumeMax");
    }

    public boolean x() {
        return this.f983a.getBoolean("enabled", true);
    }

    public boolean y() {
        c();
        return (TextUtils.isEmpty(m()) || TextUtils.isEmpty(p()) || this.f985c.contains(null)) ? false : true;
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Bundle f986a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private ArrayList<String> f987b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private ArrayList<IntentFilter> f988c;

        public a(String str, String str2) {
            this.f986a = new Bundle();
            k(str);
            l(str2);
        }

        public a a(IntentFilter intentFilter) {
            if (intentFilter == null) {
                throw new IllegalArgumentException("filter must not be null");
            }
            if (this.f988c == null) {
                this.f988c = new ArrayList<>();
            }
            if (!this.f988c.contains(intentFilter)) {
                this.f988c.add(intentFilter);
            }
            return this;
        }

        public a b(Collection<IntentFilter> collection) {
            if (collection == null) {
                throw new IllegalArgumentException("filters must not be null");
            }
            if (!collection.isEmpty()) {
                Iterator<IntentFilter> it = collection.iterator();
                while (it.hasNext()) {
                    a(it.next());
                }
            }
            return this;
        }

        @SuppressLint({"UnknownNullness"})
        public p c() {
            ArrayList<IntentFilter> arrayList = this.f988c;
            if (arrayList != null) {
                this.f986a.putParcelableArrayList("controlFilters", arrayList);
            }
            ArrayList<String> arrayList2 = this.f987b;
            if (arrayList2 != null) {
                this.f986a.putStringArrayList("groupMemberIds", arrayList2);
            }
            return new p(this.f986a);
        }

        public a d(boolean z) {
            this.f986a.putBoolean("canDisconnect", z);
            return this;
        }

        public a e(int i) {
            this.f986a.putInt("connectionState", i);
            return this;
        }

        public a f(String str) {
            this.f986a.putString("status", str);
            return this;
        }

        public a g(int i) {
            this.f986a.putInt("deviceType", i);
            return this;
        }

        public a h(boolean z) {
            this.f986a.putBoolean("enabled", z);
            return this;
        }

        public a i(Bundle bundle) {
            this.f986a.putBundle("extras", bundle);
            return this;
        }

        public a j(Uri uri) {
            if (uri == null) {
                throw new IllegalArgumentException("iconUri must not be null");
            }
            this.f986a.putString("iconUri", uri.toString());
            return this;
        }

        public a k(String str) {
            this.f986a.putString("id", str);
            return this;
        }

        public a l(String str) {
            this.f986a.putString("name", str);
            return this;
        }

        public a m(int i) {
            this.f986a.putInt("playbackStream", i);
            return this;
        }

        public a n(int i) {
            this.f986a.putInt("playbackType", i);
            return this;
        }

        public a o(int i) {
            this.f986a.putInt("presentationDisplayId", i);
            return this;
        }

        public a p(int i) {
            this.f986a.putInt("volume", i);
            return this;
        }

        public a q(int i) {
            this.f986a.putInt("volumeHandling", i);
            return this;
        }

        public a r(int i) {
            this.f986a.putInt("volumeMax", i);
            return this;
        }

        public a(p pVar) {
            if (pVar != null) {
                this.f986a = new Bundle(pVar.f983a);
                if (!pVar.k().isEmpty()) {
                    this.f987b = new ArrayList<>(pVar.k());
                }
                if (pVar.g().isEmpty()) {
                    return;
                }
                this.f988c = new ArrayList<>(pVar.f985c);
                return;
            }
            throw new IllegalArgumentException("descriptor must not be null");
        }
    }
}
