package b.d.a.a.z;

import android.graphics.Typeface;
import android.text.TextPaint;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class i {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f2183c;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public WeakReference<b> f2185e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public b.d.a.a.b0.b f2186f;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final TextPaint f2181a = new TextPaint(1);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b.d.a.a.b0.d f2182b = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2184d = true;

    public class a extends b.d.a.a.b0.d {
        public a() {
        }

        @Override // b.d.a.a.b0.d
        public void a(int i) {
            i iVar = i.this;
            iVar.f2184d = true;
            b bVar = iVar.f2185e.get();
            if (bVar != null) {
                bVar.a();
            }
        }

        @Override // b.d.a.a.b0.d
        public void b(Typeface typeface, boolean z) {
            if (z) {
                return;
            }
            i iVar = i.this;
            iVar.f2184d = true;
            b bVar = iVar.f2185e.get();
            if (bVar != null) {
                bVar.a();
            }
        }
    }

    public interface b {
        void a();

        int[] getState();

        boolean onStateChange(int[] iArr);
    }

    public i(b bVar) {
        this.f2185e = new WeakReference<>(null);
        this.f2185e = new WeakReference<>(bVar);
    }

    public float a(String str) {
        if (!this.f2184d) {
            return this.f2183c;
        }
        float fMeasureText = str == null ? 0.0f : this.f2181a.measureText((CharSequence) str, 0, str.length());
        this.f2183c = fMeasureText;
        this.f2184d = false;
        return fMeasureText;
    }
}
