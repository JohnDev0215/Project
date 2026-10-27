package androidx.mediarouter.media;

import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: MediaRouteDescriptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class d {
    public final Bundle a;
    public List<String> b;
    public List<IntentFilter> c;

    public d(Bundle bundle) {
        this.a = bundle;
    }

    public final void a() {
        if (this.c == null) {
            ArrayList parcelableArrayList = this.a.getParcelableArrayList("controlFilters");
            this.c = parcelableArrayList;
            if (parcelableArrayList == null) {
                this.c = Collections.emptyList();
            }
        }
    }

    public final List<String> b() {
        if (this.b == null) {
            ArrayList<String> stringArrayList = this.a.getStringArrayList("groupMemberIds");
            this.b = stringArrayList;
            if (stringArrayList == null) {
                this.b = Collections.emptyList();
            }
        }
        return this.b;
    }

    public final Uri c() {
        String string = this.a.getString("iconUri");
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }

    public final String d() {
        return this.a.getString("id");
    }

    public final String e() {
        return this.a.getString("name");
    }

    public final int f() {
        return this.a.getInt("volume");
    }

    public final int g() {
        return this.a.getInt("volumeHandling", 0);
    }

    public final int h() {
        return this.a.getInt("volumeMax");
    }

    public final boolean i() {
        a();
        return (TextUtils.isEmpty(d()) || TextUtils.isEmpty(e()) || this.c.contains(null)) ? false : true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("MediaRouteDescriptor{ id=");
        sb.append(d());
        sb.append(", groupMemberIds=");
        sb.append(b());
        sb.append(", name=");
        sb.append(e());
        sb.append(", description=");
        Bundle bundle = this.a;
        sb.append(bundle.getString("status"));
        sb.append(", iconUri=");
        sb.append(c());
        sb.append(", isEnabled=");
        sb.append(bundle.getBoolean("enabled", true));
        sb.append(", connectionState=");
        sb.append(bundle.getInt("connectionState", 0));
        sb.append(", controlFilters=");
        a();
        sb.append(Arrays.toString(this.c.toArray()));
        sb.append(", playbackType=");
        sb.append(bundle.getInt("playbackType", 1));
        sb.append(", playbackStream=");
        sb.append(bundle.getInt("playbackStream", -1));
        sb.append(", deviceType=");
        sb.append(bundle.getInt("deviceType"));
        sb.append(", volume=");
        sb.append(f());
        sb.append(", volumeMax=");
        sb.append(h());
        sb.append(", volumeHandling=");
        sb.append(g());
        sb.append(", presentationDisplayId=");
        sb.append(bundle.getInt("presentationDisplayId", -1));
        sb.append(", extras=");
        sb.append(bundle.getBundle("extras"));
        sb.append(", isValid=");
        sb.append(i());
        sb.append(", minClientVersion=");
        sb.append(bundle.getInt("minClientVersion", 1));
        sb.append(", maxClientVersion=");
        sb.append(bundle.getInt("maxClientVersion", Integer.MAX_VALUE));
        sb.append(" }");
        return sb.toString();
    }

    /* JADX INFO: compiled from: MediaRouteDescriptor.java */
    public static final class a {
        public final Bundle a;
        public ArrayList<String> b;
        public ArrayList<IntentFilter> c;

        public a(String str, String str2) {
            Bundle bundle = new Bundle();
            this.a = bundle;
            if (str == null) {
                throw new NullPointerException("id must not be null");
            }
            bundle.putString("id", str);
            if (str2 == null) {
                throw new NullPointerException("name must not be null");
            }
            bundle.putString("name", str2);
        }

        public final void a(List list) {
            if (list == null) {
                throw new IllegalArgumentException("filters must not be null");
            }
            if (list.isEmpty()) {
                return;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                IntentFilter intentFilter = (IntentFilter) it.next();
                if (intentFilter != null) {
                    if (this.c == null) {
                        this.c = new ArrayList<>();
                    }
                    if (!this.c.contains(intentFilter)) {
                        this.c.add(intentFilter);
                    }
                }
            }
        }

        public final d b() {
            ArrayList<IntentFilter> arrayList = this.c;
            Bundle bundle = this.a;
            if (arrayList != null) {
                bundle.putParcelableArrayList("controlFilters", arrayList);
            }
            ArrayList<String> arrayList2 = this.b;
            if (arrayList2 != null) {
                bundle.putStringArrayList("groupMemberIds", arrayList2);
            }
            return new d(bundle);
        }

        public a(d dVar) {
            this.a = new Bundle(dVar.a);
            if (!dVar.b().isEmpty()) {
                this.b = new ArrayList<>(dVar.b());
            }
            dVar.a();
            if (dVar.c.isEmpty()) {
                return;
            }
            this.c = new ArrayList<>(dVar.c);
        }
    }
}
