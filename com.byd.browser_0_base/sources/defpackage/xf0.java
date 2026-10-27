package defpackage;

import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: EditorFieldModel.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xf0 {
    public final int a;
    public List<a> b;
    public HashMap<String, CharSequence> c;
    public HashMap<String, String> d;
    public HashSet e;
    public ArrayList f;
    public TextWatcher g;
    public b h;
    public CharSequence i;
    public CharSequence j;
    public CharSequence k;
    public CharSequence l;
    public CharSequence m;
    public CharSequence n;
    public CharSequence o;
    public Callback<Pair<String, Runnable>> p;
    public boolean q = true;
    public int r = 0;

    /* JADX INFO: compiled from: EditorFieldModel.java */
    public static class a extends Pair<String, CharSequence> {
        public a(String str, CharSequence charSequence) {
            super(str, charSequence);
        }

        @Override // android.util.Pair
        public final String toString() {
            return ((CharSequence) ((Pair) this).second).toString();
        }
    }

    /* JADX INFO: compiled from: EditorFieldModel.java */
    public interface b {
        void a();

        boolean isValid(CharSequence charSequence);
    }

    public xf0(int i) {
        this.a = i;
    }

    public static xf0 a(String str, ArrayList arrayList, String str2) {
        xf0 xf0Var = new xf0(12);
        xf0Var.m = str;
        xf0Var.o = str2;
        xf0Var.b = arrayList;
        xf0Var.e = new HashSet();
        xf0Var.c = new HashMap<>();
        xf0Var.d = new HashMap<>();
        for (int i = 0; i < xf0Var.b.size(); i++) {
            xf0Var.e.add((String) ((Pair) xf0Var.b.get(i)).first);
            xf0Var.d.put(((CharSequence) ((Pair) xf0Var.b.get(i)).second).toString(), (String) ((Pair) xf0Var.b.get(i)).first);
            xf0Var.c.put((String) ((Pair) xf0Var.b.get(i)).first, (CharSequence) ((Pair) xf0Var.b.get(i)).second);
        }
        return xf0Var;
    }

    public static xf0 b() {
        return new xf0(0);
    }

    public static xf0 c(int i, String str, HashSet hashSet, v02 v02Var, b bVar, String str2, String str3, String str4) {
        xf0 xf0Var = new xf0(i);
        xf0Var.f = hashSet == null ? null : new ArrayList(hashSet);
        xf0Var.g = v02Var;
        xf0Var.h = bVar;
        xf0Var.j = str3;
        xf0Var.i = str2;
        xf0Var.m = str;
        xf0Var.n = str4;
        xf0Var.r = Math.max(0, 0);
        return xf0Var;
    }

    public final boolean d() {
        if (!TextUtils.isEmpty(this.k)) {
            this.l = this.k;
            return false;
        }
        if ((!TextUtils.isEmpty(this.i)) && (TextUtils.isEmpty(this.n) || TextUtils.getTrimmedLength(this.n) == 0)) {
            this.l = this.i;
            return false;
        }
        b bVar = this.h;
        if (bVar == null || bVar.isValid(this.n)) {
            this.l = null;
            return true;
        }
        this.l = this.j;
        return false;
    }
}
