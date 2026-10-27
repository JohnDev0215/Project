package b.a.i;

import android.content.Context;
import android.view.Surface;
import android.widget.SeekBar;
import com.byd.util.w;
import com.byd.view.OnVideoViewListener;
import com.byd.view.VideoPlayerBaseView;
import java.io.FileDescriptor;

/* JADX INFO: loaded from: classes2.dex */
public class c implements OnVideoViewListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VideoPlayerBaseView f517a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private b f518b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private a f519c;

    public c(Context context) {
        this.f518b = new b(context);
    }

    public void a() {
        this.f518b.a();
    }

    public int b() {
        return this.f518b.q();
    }

    public FileDescriptor c() {
        return this.f518b.r();
    }

    public byte d() {
        return this.f518b.s();
    }

    public String e() {
        return this.f518b.t();
    }

    public int f() {
        return this.f518b.u();
    }

    public boolean g() {
        return this.f518b.w();
    }

    public boolean h() {
        return this.f518b.x();
    }

    public void i() {
        this.f518b.y();
    }

    public void j(String str, FileDescriptor fileDescriptor) {
        this.f518b.A(str, fileDescriptor);
    }

    public void k() {
        this.f518b.B();
    }

    public void l(int i) {
        this.f518b.E(i);
    }

    public void m(FileDescriptor fileDescriptor) {
        this.f518b.H(fileDescriptor);
    }

    public void n(a aVar) {
        this.f519c = aVar;
        this.f518b.I(aVar);
        VideoPlayerBaseView videoPlayerBaseView = this.f517a;
        if (videoPlayerBaseView != null) {
            videoPlayerBaseView.setOnPlayerListener(aVar);
        }
    }

    public void o(byte b2) {
        this.f518b.J(b2);
    }

    @Override // com.byd.view.OnVideoViewListener
    public void onMotionEventDown() {
        this.f518b.R();
    }

    @Override // com.byd.view.OnVideoViewListener
    public void onMotionEventUp() {
        if (this.f518b.x()) {
            this.f518b.D();
        }
    }

    @Override // com.byd.view.OnVideoViewListener
    public void onSurfaceDestroy() {
        this.f518b.T();
    }

    @Override // com.byd.view.OnVideoViewListener
    public void onSurfaceTextureAvailable(Surface surface, int i, int i2) {
        this.f518b.S(surface);
    }

    @Override // com.byd.view.OnVideoViewListener
    public void onViewSizeChange(int i, int i2) {
        w.c("video view size change : " + i + " " + i2);
    }

    public void p(String str) {
        this.f518b.K(str);
    }

    public void q(SeekBar seekBar) {
        this.f518b.L(seekBar);
    }

    public void r(int i) {
        this.f518b.M(i);
    }

    public void s(int i) {
        this.f518b.N(i);
    }

    public void t(VideoPlayerBaseView videoPlayerBaseView) {
        this.f517a = videoPlayerBaseView;
        videoPlayerBaseView.setOnVideoViewListener(this);
        videoPlayerBaseView.setOnPlayerListener(this.f519c);
    }

    public void u() {
        this.f518b.P();
    }

    public void v() {
        this.f518b.Q();
    }

    public void w() {
        b bVar = this.f518b;
        if (bVar == null || !bVar.x()) {
            return;
        }
        this.f518b.R();
    }
}
