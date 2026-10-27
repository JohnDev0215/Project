package spi;

import android.car.XCarRoute;
import android.car.test.ITestViewControl;
import android.view.View;
import android.widget.ImageView;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.car.apps.common.UxrButton;
import com.android.car.apps.common.UxrTextView;
import com.android.car.media.main.R$id;
import com.android.car.media.main.R$integer;

/* JADX INFO: compiled from: BydPlaybackErrorViewController.java */
/* JADX INFO: loaded from: classes.dex */
public final class v0 {
    public final UxrTextView a;
    public final UxrButton b;
    public final UxrTextView c;
    public final ImageView d;
    public final View e;
    public final int f;
    public ITestViewControl g = null;

    /* JADX INFO: compiled from: BydPlaybackErrorViewController.java */
    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            v0 v0Var = v0.this;
            if (v0Var.g == null) {
                v0Var.g = (ITestViewControl) ARouter.getInstance().build(XCarRoute.CAR_TEST_SETTING).navigation();
            }
            v0 v0Var2 = v0.this;
            v0Var2.g.onClicked(v0Var2.d.getContext());
        }
    }

    public v0(View view) {
        this.a = (UxrTextView) view.findViewById(R$id.error_message);
        this.b = (UxrButton) view.findViewById(R$id.error_button);
        ImageView imageView = (ImageView) view.findViewById(R$id.error_logo);
        this.d = imageView;
        imageView.setOnClickListener(new a());
        this.c = (UxrTextView) view.findViewById(R$id.error_message_only);
        this.e = view.findViewById(R$id.fragment_cl_error);
        this.f = view.getContext().getResources().getInteger(R$integer.new_album_art_fade_in_duration);
    }
}
