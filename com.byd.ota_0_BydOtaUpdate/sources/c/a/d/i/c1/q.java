package c.a.d.i.c1;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: InstallMediaFullscreenDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class q extends o {
    public LinearLayout E3;
    public LinearLayout F3;
    public TextView G3;
    public TextView H3;
    public TextView x;
    public ProgressBar y;

    public q(Context context) {
        super(context);
        setContentView(R.layout.fullscreen_dialog_install_media);
        this.x = (TextView) findViewById(R.id.install_progress_number);
        this.y = (ProgressBar) findViewById(R.id.install_progress_bar);
        this.E3 = (LinearLayout) findViewById(R.id.root_ll);
        this.F3 = (LinearLayout) findViewById(R.id.content_ll);
        this.G3 = (TextView) findViewById(R.id.tv_tips1);
        this.H3 = (TextView) findViewById(R.id.tv_tips2);
        if (this.f2533d) {
            if (!this.s) {
                this.F3.setLayoutDirection(1);
                this.G3.setGravity(8388613);
                this.H3.setGravity(8388613);
            } else {
                this.E3.setLayoutDirection(0);
                this.F3.setLayoutDirection(1);
                this.G3.setGravity(8388613);
                this.H3.setGravity(8388613);
            }
        }
    }

    public void a(int i2) {
        this.x.setText(String.format("%d%%", Integer.valueOf(i2)));
        this.y.setProgress(i2);
    }
}
