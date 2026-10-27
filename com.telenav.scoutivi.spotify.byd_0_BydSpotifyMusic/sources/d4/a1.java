package d4;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.databinding.ViewDataBinding;
import com.vivid.spotify.rootui.MainUIViewModel;

/* JADX INFO: compiled from: FragmentLoginBinding.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a1 extends ViewDataBinding {
    public final Button G;
    public final ImageView H;
    public final TextView I;
    protected MainUIViewModel J;

    protected a1(Object obj, View view, int i7, Button button, ImageView imageView, TextView textView) {
        super(obj, view, i7);
        this.G = button;
        this.H = imageView;
        this.I = textView;
    }

    public abstract void d0(MainUIViewModel mainUIViewModel);
}
