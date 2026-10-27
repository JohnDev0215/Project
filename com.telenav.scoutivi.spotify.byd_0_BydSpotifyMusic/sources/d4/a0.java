package d4;

import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.media.MediaBrowserCompat;
import android.text.BidiFormatter;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import java.util.List;

/* JADX INFO: compiled from: DataBindingUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final View.OnTouchListener f5665a = new View.OnTouchListener() { // from class: d4.z
        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            return a0.e(view, motionEvent);
        }
    };

    public static void b(View view, Integer num) {
        TypedArray typedArrayObtainStyledAttributes;
        if (num == null || (typedArrayObtainStyledAttributes = view.getContext().obtainStyledAttributes(new int[]{num.intValue()})) == null) {
            return;
        }
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(0, -1);
        if (resourceId != -1) {
            view.setBackgroundResource(resourceId);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void c(View view, boolean z6, int i7) {
        view.setVerticalFadingEdgeEnabled(z6);
        view.setHorizontalFadingEdgeEnabled(!z6);
        if (i7 == 0) {
            i7 = (int) view.getContext().getResources().getDimension(com.vivid.spotify.r.fading_edge_size);
        }
        view.setFadingEdgeLength(i7);
    }

    public static void d(RecyclerView recyclerView, RecyclerView.l lVar) {
        recyclerView.setItemAnimator(lVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean e(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            view.setAlpha(0.5f);
            return false;
        }
        if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return false;
        }
        view.setAlpha(1.0f);
        return false;
    }

    public static void f(TextView textView, List<MediaBrowserCompat.MediaItem> list) {
        if (list != null) {
            int color = textView.getContext().getColor(com.vivid.spotify.q.colorSharedWhite);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            for (MediaBrowserCompat.MediaItem mediaItem : list) {
                String strF = i3.c.f(mediaItem);
                String strG = i3.c.g(mediaItem);
                if (!i3.c.d(mediaItem).startsWith("spotify:episode")) {
                    spannableStringBuilder.append((CharSequence) strF);
                    spannableStringBuilder.setSpan(new ForegroundColorSpan(color), spannableStringBuilder.length() - strF.length(), spannableStringBuilder.length(), 17);
                    spannableStringBuilder.append((CharSequence) " ");
                }
                spannableStringBuilder.append((CharSequence) strG);
                spannableStringBuilder.setSpan(new ForegroundColorSpan(color), spannableStringBuilder.length() - strG.length(), spannableStringBuilder.length(), 17);
                spannableStringBuilder.append((CharSequence) " · ");
                spannableStringBuilder.setSpan(new ForegroundColorSpan(color), spannableStringBuilder.length() - 2, spannableStringBuilder.length(), 17);
            }
            if (spannableStringBuilder.length() > 0) {
                spannableStringBuilder.delete(spannableStringBuilder.length() - 2, spannableStringBuilder.length());
            }
            textView.setText(BidiFormatter.getInstance().unicodeWrap(spannableStringBuilder));
        }
    }

    public static void g(RecyclerView recyclerView, RecyclerView.n nVar) {
        int itemDecorationCount = recyclerView.getItemDecorationCount();
        for (int i7 = 0; i7 < itemDecorationCount; i7++) {
            recyclerView.Y0(i7);
        }
        if (nVar != null) {
            recyclerView.h(nVar);
        }
    }

    public static void h(TextView textView, Boolean bool) {
        textView.setTypeface(Typeface.defaultFromStyle(bool.booleanValue() ? 1 : 0));
    }

    public static void i(View view, Boolean bool) {
        view.setOnTouchListener(f5665a);
    }

    public static void j(ViewPager2 viewPager2, FragmentStateAdapter fragmentStateAdapter) {
        viewPager2.setAdapter(fragmentStateAdapter);
    }
}
