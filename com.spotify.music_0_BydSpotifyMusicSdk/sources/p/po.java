package p;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.RectF;
import android.graphics.Shader;
import com.google.common.base.Optional;
import com.spotify.player.esperanto.proto.EsResponseWithReasons$ResponseWithReasons;
import okhttp3.ResponseBody;

/* JADX INFO: loaded from: classes.dex */
public final class po implements h00, io.reactivex.rxjava3.functions.o, io.reactivex.rxjava3.functions.c, ku2, on2 {
    public static final po c = new po();
    public static final po e = new po();
    public static final po k = new po();

    @Override // p.ku2
    public iu2 a(Class cls) {
        return new vb1();
    }

    @Override // io.reactivex.rxjava3.functions.o
    public Object apply(Object obj) {
        EsResponseWithReasons$ResponseWithReasons esResponseWithReasons$ResponseWithReasons = (EsResponseWithReasons$ResponseWithReasons) obj;
        v03.j(esResponseWithReasons$ResponseWithReasons, "p0");
        return yu.a(esResponseWithReasons$ResponseWithReasons);
    }

    @Override // p.h00
    public Object convert(Object obj) {
        return (ResponseBody) obj;
    }

    @Override // p.on2
    public Bitmap i(Bitmap bitmap, Integer num, Integer num2) {
        v03.j(bitmap, "input");
        float fIntValue = num.intValue() * 0.1f;
        Paint paint = new Paint(3);
        int iIntValue = num.intValue();
        int iIntValue2 = num2.intValue();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iIntValue, iIntValue2, bitmap.getConfig());
        v03.i(bitmapCreateBitmap, "createBitmap(outputWidth…tputHeight, input.config)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        Matrix matrix = new Matrix();
        float fMax = (float) Math.max(((double) iIntValue) / ((double) bitmap.getWidth()), ((double) iIntValue2) / ((double) bitmap.getHeight()));
        float f = 2;
        matrix.setTranslate((iIntValue - (bitmap.getWidth() * fMax)) / f, (iIntValue2 - (bitmap.getHeight() * fMax)) / f);
        matrix.preScale(fMax, fMax);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        float[] fArr = {fIntValue, fIntValue, fIntValue, fIntValue, fIntValue, fIntValue, fIntValue, fIntValue};
        RectF rectF = new RectF(0.0f, 0.0f, canvas.getWidth(), canvas.getHeight());
        Path path = new Path();
        path.addRoundRect(rectF, fArr, Path.Direction.CW);
        canvas.drawPath(path, paint);
        if (!v03.b(bitmap, bitmapCreateBitmap)) {
            bitmap.recycle();
        }
        return bitmapCreateBitmap;
    }

    @Override // p.on2
    public String j() {
        return "rounded_corner";
    }

    @Override // io.reactivex.rxjava3.functions.c
    public Object k(Object obj, Object obj2) {
        return (Optional) obj2;
    }
}
