package b.c.a;

import a.k.d;
import a.k.f;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import b.c.a.e.e;
import b.c.a.e.h;
import b.c.a.e.i;
import b.c.a.e.k;
import b.c.a.e.m;
import b.c.a.e.n;
import b.c.a.e.p;
import com.byd.globalnotification.R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class a extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f1726a;

    /* JADX INFO: renamed from: b.c.a.a$a, reason: collision with other inner class name */
    public static class C0032a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final SparseArray<String> f1727a;

        static {
            SparseArray<String> sparseArray = new SparseArray<>(3);
            f1727a = sparseArray;
            sparseArray.put(0, "_all");
            f1727a.put(1, "bean");
            f1727a.put(2, "viewModel");
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(6);
        f1726a = sparseIntArray;
        sparseIntArray.put(R.layout.activity_notification, 1);
        f1726a.put(R.layout.item_custom_wrap, 2);
        f1726a.put(R.layout.item_custom_wrap_big, 3);
        f1726a.put(R.layout.item_custom_wrap_sub, 4);
        f1726a.put(R.layout.item_notify_msg, 5);
        f1726a.put(R.layout.title_header, 6);
    }

    @Override // a.k.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new a.k.r.b.a());
        arrayList.add(new e.a.a.d());
        arrayList.add(new e.a.a.g.a());
        return arrayList;
    }

    @Override // a.k.d
    public String b(int i) {
        return C0032a.f1727a.get(i);
    }

    @Override // a.k.d
    public ViewDataBinding c(f fVar, View view, int i) {
        int i2 = f1726a.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        switch (i2) {
            case 1:
                if ("layout-port/activity_notification_0".equals(tag)) {
                    return new b.c.a.e.c(fVar, view);
                }
                if ("layout/activity_notification_0".equals(tag)) {
                    return new b.c.a.e.b(fVar, view);
                }
                throw new IllegalArgumentException("The tag for activity_notification is invalid. Received: " + tag);
            case 2:
                if ("layout/item_custom_wrap_0".equals(tag)) {
                    return new h(fVar, view);
                }
                if ("layout-port/item_custom_wrap_0".equals(tag)) {
                    return new i(fVar, view);
                }
                throw new IllegalArgumentException("The tag for item_custom_wrap is invalid. Received: " + tag);
            case 3:
                if ("layout/item_custom_wrap_big_0".equals(tag)) {
                    return new e(fVar, view);
                }
                if ("layout-port/item_custom_wrap_big_0".equals(tag)) {
                    return new b.c.a.e.f(fVar, view);
                }
                throw new IllegalArgumentException("The tag for item_custom_wrap_big is invalid. Received: " + tag);
            case 4:
                if ("layout/item_custom_wrap_sub_0".equals(tag)) {
                    return new k(fVar, view);
                }
                throw new IllegalArgumentException("The tag for item_custom_wrap_sub is invalid. Received: " + tag);
            case 5:
                if ("layout-port/item_notify_msg_0".equals(tag)) {
                    return new n(fVar, view);
                }
                if ("layout/item_notify_msg_0".equals(tag)) {
                    return new m(fVar, view);
                }
                throw new IllegalArgumentException("The tag for item_notify_msg is invalid. Received: " + tag);
            case 6:
                if ("layout/title_header_0".equals(tag)) {
                    return new p(fVar, view);
                }
                throw new IllegalArgumentException("The tag for title_header is invalid. Received: " + tag);
            default:
                return null;
        }
    }

    @Override // a.k.d
    public ViewDataBinding d(f fVar, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || f1726a.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }
}
