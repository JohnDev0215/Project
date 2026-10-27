package butterknife;

import android.view.View;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class ButterKnife {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final Map<Class<?>, Constructor<? extends Unbinder>> f2071a = new LinkedHashMap();

    public interface Action<T extends View> {
    }

    public interface Setter<T extends View, V> {
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }
}
