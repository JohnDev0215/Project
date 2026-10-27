package a.k;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class d {
    public List<d> a() {
        return Collections.emptyList();
    }

    public abstract String b(int i);

    public abstract ViewDataBinding c(f fVar, View view, int i);

    public abstract ViewDataBinding d(f fVar, View[] viewArr, int i);
}
