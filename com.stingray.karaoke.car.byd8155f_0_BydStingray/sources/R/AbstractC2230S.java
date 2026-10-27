package r;

import android.widget.AbsListView;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: r.S, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2230S {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Field f21445a;

    static {
        Field declaredField = null;
        try {
            declaredField = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
            declaredField.setAccessible(true);
        } catch (NoSuchFieldException e10) {
            e10.printStackTrace();
        }
        f21445a = declaredField;
    }
}
