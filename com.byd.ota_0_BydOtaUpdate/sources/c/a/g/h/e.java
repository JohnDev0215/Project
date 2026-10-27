package c.a.g.h;

import android.text.TextUtils;
import c.a.g.l.h;
import com.byd.repository.exception.ApiException;
import com.byd.repository.exception.InvalidTokenException;
import com.byd.repository.exception.LatestVersionException;
import com.byd.repository.exception.NoRegisterException;
import com.byd.repository.exception.NonexistentException;
import com.byd.repository.exception.ParamsErrorException;
import com.google.gson.JsonParseException;
import d.a.i;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;
import org.json.JSONException;
import org.xmlpull.v1.XmlPullParserException;
import retrofit2.HttpException;

/* JADX INFO: compiled from: ResponseObserver.java */
/* JADX INFO: loaded from: classes.dex */
public class e<Body> implements i<String> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final String f2693c = "e";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Class<Body> f2694d;
    public d<Body, String> s;

    public e(Class<Body> cls, d<Body, String> dVar) {
        this.f2694d = cls;
        this.s = dVar;
    }

    @Override // d.a.i
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onNext(String str) {
        if (TextUtils.isEmpty(str)) {
            c.a.b.d.e.b(f2693c, "Server failed to return data!");
            return;
        }
        Object objD = h.d(str, this.f2694d);
        if (objD != null) {
            this.s.b((Body) objD);
        }
    }

    @Override // d.a.i
    public void onComplete() {
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        String message;
        c.a.b.d.e.b(f2693c, "onError->msg = " + th.getMessage() + ", e = " + th.toString());
        if (th instanceof HttpException) {
            message = "Network failed to connect. Please check network";
        } else if ((th instanceof ConnectException) || (th instanceof UnknownHostException)) {
            message = "connect error";
        } else if (th instanceof InterruptedIOException) {
            message = "connect timeout";
        } else if ((th instanceof JsonParseException) || (th instanceof JSONException) || (th instanceof ParseException) || (th instanceof XmlPullParserException)) {
            message = "parse json data error";
        } else if (th instanceof NonexistentException) {
            message = "invalid status";
        } else if (th instanceof ParamsErrorException) {
            message = "Http response params error!";
        } else if (th instanceof LatestVersionException) {
            message = "Http response local version is the latest!";
        } else if (th instanceof NoRegisterException) {
            message = "Http response not register!";
        } else if (th instanceof InvalidTokenException) {
            message = "Http response invalid token!";
        } else if (th instanceof ApiException) {
            ApiException apiException = (ApiException) th;
            apiException.status();
            message = apiException.description();
        } else {
            message = th.getMessage();
        }
        this.s.a(message, th);
    }

    @Override // d.a.i
    public void onSubscribe(d.a.m.b bVar) {
    }
}
