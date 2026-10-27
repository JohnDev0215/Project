package b.b.k.a.b;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import b.b.m.e.c;
import com.byd.dipilot.model.BaseViewModel;
import com.byd.dipilot.model.DriverAssistModel;
import java.util.List;

/* JADX INFO: compiled from: IAdasBaseView.java */
/* JADX INFO: loaded from: classes2.dex */
public interface a<P, T extends BaseViewModel> extends c<P> {
    FragmentActivity d();

    void g(List<T> list);

    Context getContext();

    boolean i();

    void j(DriverAssistModel driverAssistModel, int i, int i2, int i3, b.b.k.a.a aVar);
}
