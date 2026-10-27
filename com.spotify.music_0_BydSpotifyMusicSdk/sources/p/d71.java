package p;

import com.google.firebase.encoders.EncodingException;
import java.nio.charset.Charset;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class d71 implements yo1 {
    public final /* synthetic */ int a;

    public /* synthetic */ d71(int i) {
        this.a = i;
    }

    @Override // p.yd0
    public final void a(Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
            case 1:
                Map.Entry entry = (Map.Entry) obj;
                zo1 zo1Var = (zo1) obj2;
                Charset charset = uy1.f;
                zo1Var.f(uy1.g, entry.getKey());
                zo1Var.f(uy1.h, entry.getValue());
                return;
            default:
                throw new EncodingException("Couldn't find encoder for type " + obj.getClass().getCanonicalName());
        }
    }
}
