package b.b.a.f;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;

/* JADX INFO: compiled from: GetImageDrawableTask.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends AsyncTask<Integer, Void, Drawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public ImageView f518a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f519b;

    public d(ImageView imageView, Context context) {
        this.f518a = imageView;
        this.f519b = context;
    }

    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Drawable doInBackground(Integer... numArr) {
        return ContextCompat.getDrawable(this.f519b, numArr[0].intValue());
    }

    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void onPostExecute(Drawable drawable) {
        super.onPostExecute(drawable);
        if (drawable != null) {
            this.f518a.setImageDrawable(drawable);
        }
    }
}
