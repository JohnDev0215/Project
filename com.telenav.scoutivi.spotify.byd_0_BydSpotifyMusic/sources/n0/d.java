package n0;

import android.widget.SeekBar;
import androidx.databinding.h;

/* JADX INFO: compiled from: SeekBarBindingAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: compiled from: SeekBarBindingAdapter.java */
    class a implements SeekBar.OnSeekBarChangeListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ b f8492a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ h f8493b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final /* synthetic */ c f8494c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final /* synthetic */ InterfaceC0132d f8495d;

        a(b bVar, h hVar, c cVar, InterfaceC0132d interfaceC0132d) {
            this.f8492a = bVar;
            this.f8493b = hVar;
            this.f8494c = cVar;
            this.f8495d = interfaceC0132d;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int i7, boolean z6) {
            b bVar = this.f8492a;
            if (bVar != null) {
                bVar.onProgressChanged(seekBar, i7, z6);
            }
            h hVar = this.f8493b;
            if (hVar != null) {
                hVar.a();
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
            c cVar = this.f8494c;
            if (cVar != null) {
                cVar.onStartTrackingTouch(seekBar);
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            InterfaceC0132d interfaceC0132d = this.f8495d;
            if (interfaceC0132d != null) {
                interfaceC0132d.onStopTrackingTouch(seekBar);
            }
        }
    }

    /* JADX INFO: compiled from: SeekBarBindingAdapter.java */
    public interface b {
        void onProgressChanged(SeekBar seekBar, int i7, boolean z6);
    }

    /* JADX INFO: compiled from: SeekBarBindingAdapter.java */
    public interface c {
        void onStartTrackingTouch(SeekBar seekBar);
    }

    /* JADX INFO: renamed from: n0.d$d, reason: collision with other inner class name */
    /* JADX INFO: compiled from: SeekBarBindingAdapter.java */
    public interface InterfaceC0132d {
        void onStopTrackingTouch(SeekBar seekBar);
    }

    public static void a(SeekBar seekBar, c cVar, InterfaceC0132d interfaceC0132d, b bVar, h hVar) {
        if (cVar == null && interfaceC0132d == null && bVar == null && hVar == null) {
            seekBar.setOnSeekBarChangeListener(null);
        } else {
            seekBar.setOnSeekBarChangeListener(new a(bVar, hVar, cVar, interfaceC0132d));
        }
    }

    public static void b(SeekBar seekBar, int i7) {
        if (i7 != seekBar.getProgress()) {
            seekBar.setProgress(i7);
        }
    }
}
