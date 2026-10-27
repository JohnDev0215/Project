package p;

import android.text.Editable;
import android.text.Selection;
import android.text.TextPaint;
import android.view.KeyEvent;
import com.google.android.play.core.tasks.RuntimeExecutionException;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public final class wc0 {
    public boolean a;
    public final Object b;
    public final Object c;
    public Object d;
    public Object e;

    public wc0(int i) {
        if (i == 3) {
            this.b = new Object();
            this.c = new s23(1);
            return;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.b = linkedHashMap;
        this.c = linkedHashMap;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        this.d = linkedHashSet;
        this.e = linkedHashSet;
        this.a = true;
    }

    public static void a(Map map, Map map2) {
        for (Map.Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            Object value = entry.getValue();
            if (map.containsKey(str)) {
                Object obj = map.get(str);
                if ((obj instanceof Map) && (!(obj instanceof g81) || (obj instanceof i81))) {
                    Object obj2 = map.get(str);
                    if (obj2 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>");
                    }
                    Map mapB = he1.b(obj2);
                    Map map3 = value instanceof Map ? (Map) value : null;
                    if (map3 == null) {
                        throw new IllegalStateException(("'" + str + "' is an object in destination but not in map").toString());
                    }
                    a(mapB, map3);
                }
            }
            map.put(str, value);
        }
    }

    public static boolean b(Editable editable, KeyEvent keyEvent, boolean z) {
        yo2[] yo2VarArr;
        if (!KeyEvent.metaStateHasNoModifiers(keyEvent.getMetaState())) {
            return false;
        }
        int selectionStart = Selection.getSelectionStart(editable);
        int selectionEnd = Selection.getSelectionEnd(editable);
        if (selectionStart != -1 && selectionEnd != -1 && selectionStart == selectionEnd && (yo2VarArr = (yo2[]) editable.getSpans(selectionStart, selectionEnd, yo2.class)) != null && yo2VarArr.length > 0) {
            for (yo2 yo2Var : yo2VarArr) {
                int spanStart = editable.getSpanStart(yo2Var);
                int spanEnd = editable.getSpanEnd(yo2Var);
                if ((z && spanStart == selectionStart) || ((!z && spanEnd == selectionStart) || (selectionStart > spanStart && selectionStart < spanEnd))) {
                    editable.delete(spanStart, spanEnd);
                    return true;
                }
            }
        }
        return false;
    }

    public final Object c() {
        Object obj;
        synchronized (this.b) {
            try {
                if (!this.a) {
                    throw new IllegalStateException("Task is not yet complete");
                }
                Exception exc = (Exception) this.e;
                if (exc != null) {
                    throw new RuntimeExecutionException(exc);
                }
                obj = this.d;
            } catch (Throwable th) {
                throw th;
            }
        }
        return obj;
    }

    public final boolean d(CharSequence charSequence, int i, int i2, uc0 uc0Var) {
        if (uc0Var.c == 0) {
            gc0 gc0Var = (gc0) this.d;
            dk1 dk1VarD = uc0Var.d();
            int iA = dk1VarD.a(8);
            if (iA != 0) {
                dk1VarD.b.getShort(iA + dk1VarD.a);
            }
            w60 w60Var = (w60) gc0Var;
            w60Var.getClass();
            ThreadLocal threadLocal = w60.b;
            if (threadLocal.get() == null) {
                threadLocal.set(new StringBuilder());
            }
            StringBuilder sb = (StringBuilder) threadLocal.get();
            sb.setLength(0);
            while (i < i2) {
                sb.append(charSequence.charAt(i));
                i++;
            }
            TextPaint textPaint = w60Var.a;
            String string = sb.toString();
            int i3 = or1.a;
            uc0Var.c = nr1.a(textPaint, string) ? 2 : 1;
        }
        return uc0Var.c == 2;
    }

    public final boolean e() {
        boolean z;
        synchronized (this.b) {
            z = false;
            if (this.a && ((Exception) this.e) == null) {
                z = true;
            }
        }
        return z;
    }

    public final Map f(Map map) {
        Map map2 = (Map) this.c;
        boolean zIsEmpty = map2.isEmpty();
        Object obj = this.b;
        if (zIsEmpty) {
            ((Map) obj).putAll(map);
            return map2;
        }
        Map map3 = (Map) map.get("data");
        Object obj2 = map.get("path");
        if (obj2 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.List<kotlin.Any>");
        }
        List list = (List) obj2;
        Object obj3 = map2.get("data");
        if (obj3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
        }
        Object obj4 = (Map) obj3;
        Boolean bool = (Boolean) map.get("hasNext");
        this.a = bool != null ? bool.booleanValue() : false;
        if (map3 != null) {
            for (Object obj5 : list) {
                if (obj4 instanceof List) {
                    obj4 = ((List) obj4).get(((Integer) obj5).intValue());
                } else {
                    if (obj4 == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any?>");
                    }
                    obj4 = ((Map) obj4).get(obj5);
                }
            }
            if (obj4 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.MutableMap<kotlin.String, kotlin.Any?>");
            }
            a(he1.b(obj4), map3);
            ((Set) this.d).add(new l80((String) map.get("label"), list));
        }
        if (map.containsKey("errors")) {
            ((Map) obj).put("errors", map.get("errors"));
        } else {
            ((Map) obj).remove("errors");
        }
        if (map.containsKey("extensions")) {
            ((Map) obj).put("extensions", map.get("extensions"));
        } else {
            ((Map) obj).remove("extensions");
        }
        return map2;
    }

    public final void g(Exception exc) {
        synchronized (this.b) {
            if (!(!this.a)) {
                throw new IllegalStateException("Task is already complete");
            }
            this.a = true;
            this.e = exc;
        }
        ((s23) this.c).c(this);
    }

    public final void h(Object obj) {
        synchronized (this.b) {
            if (!(!this.a)) {
                throw new IllegalStateException("Task is already complete");
            }
            this.a = true;
            this.d = obj;
        }
        ((s23) this.c).c(this);
    }

    public final void i() {
        synchronized (this.b) {
            try {
                if (this.a) {
                    ((s23) this.c).c(this);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public wc0(gh2 gh2Var, zb0 zb0Var, w60 w60Var) {
        this.b = zb0Var;
        this.c = gh2Var;
        this.d = w60Var;
        this.a = false;
        this.e = null;
    }
}
