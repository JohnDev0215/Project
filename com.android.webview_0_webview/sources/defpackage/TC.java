package defpackage;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class TC implements View.OnClickListener, ZC {
    public final int A;
    public final int B;
    public final int C;
    public final View s;
    public final YC t;
    public final Context u;
    public final PopupWindow v;
    public int w;
    public int x;
    public int y;
    public View z;

    public TC(Context context, View view, YC yc) {
        this.s = view;
        this.t = yc;
        this.u = context;
        PopupWindow popupWindow = new PopupWindow(this.u, (AttributeSet) null, R.attr.textSelectHandleWindowStyle);
        this.v = popupWindow;
        popupWindow.setSplitTouchEnabled(true);
        this.v.setClippingEnabled(false);
        this.v.setAnimationStyle(0);
        this.v.setWidth(-2);
        this.v.setHeight(-2);
        TypedArray typedArrayObtainStyledAttributes = this.u.getTheme().obtainStyledAttributes(new int[]{R.attr.textEditPasteWindowLayout});
        this.A = typedArrayObtainStyledAttributes.getResourceId(typedArrayObtainStyledAttributes.getIndex(0), 0);
        typedArrayObtainStyledAttributes.recycle();
        this.B = (int) TypedValue.applyDimension(1, 5.0f, this.u.getResources().getDisplayMetrics());
        this.C = (int) TypedValue.applyDimension(1, 30.0f, this.u.getResources().getDisplayMetrics());
        int iB = AbstractC1077kw.b(this.u.getResources(), "status_bar_height", "dimen", "android");
        if (iB > 0) {
            this.y = this.u.getResources().getDimensionPixelSize(iB);
        }
    }

    @Override // defpackage.ZC
    public void a() {
        this.v.dismiss();
    }

    @Override // defpackage.ZC
    public void b(Rect rect) {
        int iMin;
        this.v.dismiss();
        if (this.z == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.u.getSystemService("layout_inflater");
            if (layoutInflater != null) {
                this.z = layoutInflater.inflate(this.A, (ViewGroup) null);
            }
            if (this.z == null) {
                throw new IllegalArgumentException("Unable to inflate TextEdit paste window");
            }
            int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            this.z.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
            this.z.measure(iMakeMeasureSpec, iMakeMeasureSpec);
            this.z.setOnClickListener(this);
        }
        this.v.setContentView(this.z);
        int i = rect.left;
        int i2 = rect.bottom;
        if (this.w == i && this.x == i2) {
            return;
        }
        this.w = i;
        this.x = i2;
        View contentView = this.v.getContentView();
        int measuredWidth = contentView.getMeasuredWidth();
        int measuredHeight = contentView.getMeasuredHeight();
        int i3 = (int) (i - (measuredWidth / 2.0f));
        int i4 = (i2 - measuredHeight) - this.B;
        int i5 = this.s.getSystemUiVisibility() == 0 ? this.y : 0;
        int i6 = this.u.getResources().getDisplayMetrics().widthPixels;
        if (i4 < i5) {
            i4 = i4 + measuredHeight + this.B;
            int i7 = this.C / 2;
            iMin = i + measuredWidth < i6 ? (measuredWidth / 2) + i7 + i3 : i3 - ((measuredWidth / 2) + i7);
        } else {
            iMin = Math.min(i6 - measuredWidth, Math.max(0, i3));
        }
        int[] iArr = new int[2];
        this.s.getLocationInWindow(iArr);
        this.v.showAtLocation(this.s, 0, iMin + iArr[0], i4 + iArr[1]);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ((C0704eD) this.t).a();
        this.v.dismiss();
    }
}
