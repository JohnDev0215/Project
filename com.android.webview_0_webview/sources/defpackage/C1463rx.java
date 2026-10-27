package defpackage;

import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/* JADX INFO: renamed from: rx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1463rx {
    public final View a;
    public final SeekBar b;
    public int[] c;
    public GradientDrawable d;
    public final TextView e;

    public C1463rx(View view, int i, int i2, SeekBar.OnSeekBarChangeListener onSeekBarChangeListener) {
        this.a = view.findViewById(AbstractC0371Um.I1);
        TextView textView = (TextView) view.findViewById(AbstractC0371Um.H4);
        this.e = textView;
        textView.setText(i);
        this.d = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, null);
        SeekBar seekBar = (SeekBar) view.findViewById(AbstractC0371Um.N3);
        this.b = seekBar;
        seekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        this.b.setMax(i2);
        this.b.setThumbOffset(AbstractC1623us.c(view.getContext().getResources(), AbstractC0317Rm.N0).getIntrinsicWidth() / 2);
    }

    public float a() {
        return this.b.getProgress();
    }

    public void b(int[] iArr) {
        int[] iArr2 = (int[]) iArr.clone();
        this.c = iArr2;
        this.d.setColors(iArr2);
        this.a.setBackground(this.d);
    }

    public void c(float f) {
        this.b.setProgress((int) f);
    }
}
