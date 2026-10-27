package com.vivid.labels;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.databinding.g;
import h3.i;
import java.util.ArrayList;
import java.util.List;
import o3.b;
import o3.c;

/* JADX INFO: loaded from: classes.dex */
public class LabelsView extends ViewGroup implements View.OnClickListener {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Context f5169e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private int f5170g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private int f5171h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private int f5172i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    private ArrayList<String> f5173j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    private int f5174k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private int f5175l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private int f5176m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    private int f5177n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    private int f5178o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    private int f5179p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    private int f5180q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    private a f5181r;

    public interface a {
        void a(String str, int i7);
    }

    public LabelsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f5173j = new ArrayList<>();
        this.f5175l = 0;
        this.f5176m = 0;
        this.f5177n = 0;
        this.f5180q = -1;
        this.f5169e = context;
        b(context, attributeSet);
    }

    private <T> void a(String str, int i7) {
        p3.a aVar = (p3.a) g.i(getInflater(), b.labels_item, null, false);
        View viewG = aVar.G();
        int i8 = this.f5180q;
        if (i8 != -1) {
            viewG.setBackground(this.f5169e.getDrawable(i8));
        }
        viewG.setTag(Integer.valueOf(i7));
        d(viewG);
        c(aVar.G);
        addView(viewG);
        aVar.G.setText(str);
    }

    private void b(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, c.LabelsView);
            this.f5172i = typedArrayObtainStyledAttributes.getInteger(c.LabelsView_max_lines, 3);
            this.f5171h = typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.LabelsView_line_margin, 0);
            this.f5170g = typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.LabelsView_word_margin, 0);
            this.f5175l = typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.LabelsView_item_padding, 30);
            this.f5176m = typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.LabelsView_item_height, -2);
            this.f5177n = typedArrayObtainStyledAttributes.getDimensionPixelOffset(c.LabelsView_item_max_width, -1);
            this.f5178o = typedArrayObtainStyledAttributes.getResourceId(c.LabelsView_textAppearance, -1);
            this.f5179p = typedArrayObtainStyledAttributes.getColor(c.LabelsView_textColor, -1);
            this.f5180q = typedArrayObtainStyledAttributes.getResourceId(c.LabelsView_item_background, -1);
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    private void c(TextView textView) {
        textView.setHeight(this.f5176m);
        int i7 = this.f5177n;
        if (i7 > 0) {
            textView.setMaxWidth(i7);
        }
        int i8 = this.f5178o;
        if (i8 > 0) {
            textView.setTextAppearance(i8);
        }
        textView.setTextColor(this.f5179p);
    }

    private void d(View view) {
        view.setOnClickListener(this);
        int i7 = this.f5175l;
        if (i7 != 0) {
            view.setPadding(i7, view.getPaddingTop(), this.f5175l, view.getPaddingBottom());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x006b A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void e(int r12, int r13) {
        /*
            r11 = this;
            int r0 = r11.getChildCount()
            int r1 = android.view.View.MeasureSpec.getSize(r12)
            int r2 = r11.getPaddingStart()
            int r1 = r1 - r2
            int r2 = r11.getPaddingEnd()
            int r1 = r1 - r2
            r2 = 1
            r3 = 0
            r4 = r3
            r5 = r4
            r6 = r5
            r7 = r6
            r8 = r7
        L19:
            if (r4 >= r0) goto L6e
            android.view.View r9 = r11.getChildAt(r4)
            r11.measureChild(r9, r12, r13)
            int r10 = r9.getMeasuredWidth()
            int r10 = r10 + r5
            if (r10 <= r1) goto L3e
            int r2 = r2 + 1
            int r10 = r11.f5172i
            if (r10 <= 0) goto L34
            if (r2 <= r10) goto L34
        L31:
            int r2 = r2 + (-1)
            goto L6e
        L34:
            int r10 = r11.f5171h
            int r7 = r7 + r10
            int r7 = r7 + r6
            int r8 = java.lang.Math.max(r8, r5)
            r5 = r3
            r6 = r5
        L3e:
            int r10 = r9.getMeasuredWidth()
            int r5 = r5 + r10
            int r9 = r9.getMeasuredHeight()
            int r6 = java.lang.Math.max(r6, r9)
            int r9 = r0 + (-1)
            if (r4 == r9) goto L6b
            int r9 = r11.f5170g
            int r10 = r5 + r9
            if (r10 <= r1) goto L6a
            int r2 = r2 + 1
            int r9 = r11.f5172i
            if (r9 <= 0) goto L5e
            if (r2 <= r9) goto L5e
            goto L31
        L5e:
            int r9 = r11.f5171h
            int r7 = r7 + r9
            int r7 = r7 + r6
            int r5 = java.lang.Math.max(r8, r5)
            r6 = r3
            r8 = r5
            r5 = r6
            goto L6b
        L6a:
            int r5 = r5 + r9
        L6b:
            int r4 = r4 + 1
            goto L19
        L6e:
            int r7 = r7 + r6
            int r1 = java.lang.Math.max(r8, r5)
            int r4 = r11.getPaddingStart()
            int r1 = r1 + r4
            int r4 = r11.getPaddingEnd()
            int r1 = r1 + r4
            int r12 = r11.f(r12, r1)
            int r1 = r11.getPaddingTop()
            int r7 = r7 + r1
            int r1 = r11.getPaddingBottom()
            int r7 = r7 + r1
            int r13 = r11.f(r13, r7)
            r11.setMeasuredDimension(r12, r13)
            if (r0 <= 0) goto L95
            r3 = r2
        L95:
            r11.f5174k = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivid.labels.LabelsView.e(int, int):void");
    }

    private int f(int i7, int i8) {
        int mode = View.MeasureSpec.getMode(i7);
        int size = View.MeasureSpec.getSize(i7);
        if (mode == 1073741824) {
            i8 = size;
        } else if (mode == Integer.MIN_VALUE) {
            i8 = Math.min(i8, size);
        }
        return ViewGroup.resolveSizeAndState(Math.max(i8, getSuggestedMinimumWidth()), i7, 0);
    }

    public static void g(LabelsView labelsView, List<String> list) {
        labelsView.setLabels(list);
    }

    private LayoutInflater getInflater() {
        return (LayoutInflater) this.f5169e.getApplicationContext().getSystemService("layout_inflater");
    }

    public List<String> getLabels() {
        return this.f5173j;
    }

    public int getLines() {
        return this.f5174k;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f5181r != null) {
            int iIntValue = ((Integer) view.getTag()).intValue();
            if (iIntValue >= 0 && iIntValue < this.f5173j.size()) {
                this.f5181r.a(this.f5173j.get(iIntValue), iIntValue);
                return;
            }
            i.b("LabelsView").d("onClick  error  index = " + iIntValue + "  data size = " + this.f5173j.size(), new Object[0]);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z6, int i7, int i8, int i9, int i10) {
        int paddingTop = getPaddingTop();
        int i11 = i9 - i7;
        int i12 = 1;
        boolean z7 = getLayoutDirection() == 1;
        int childCount = getChildCount();
        if (z7) {
            int paddingStart = i11 - getPaddingStart();
            int iMax = 0;
            for (int i13 = 0; i13 < childCount; i13++) {
                View childAt = getChildAt(i13);
                if (paddingStart < childAt.getMeasuredWidth() + getPaddingEnd()) {
                    i12++;
                    int i14 = this.f5172i;
                    if (i14 > 0 && i12 > i14) {
                        return;
                    }
                    paddingStart = i11 - getPaddingStart();
                    paddingTop = paddingTop + this.f5171h + iMax;
                    iMax = 0;
                }
                childAt.layout(paddingStart - childAt.getMeasuredWidth(), paddingTop, paddingStart, childAt.getMeasuredHeight() + paddingTop);
                paddingStart = (paddingStart - childAt.getMeasuredWidth()) - this.f5170g;
                iMax = Math.max(iMax, childAt.getMeasuredHeight());
            }
            return;
        }
        int paddingStart2 = getPaddingStart();
        int iMax2 = 0;
        for (int i15 = 0; i15 < childCount; i15++) {
            View childAt2 = getChildAt(i15);
            if (i11 < childAt2.getMeasuredWidth() + paddingStart2 + getPaddingEnd()) {
                i12++;
                int i16 = this.f5172i;
                if (i16 > 0 && i12 > i16) {
                    return;
                }
                paddingStart2 = getPaddingStart();
                paddingTop = paddingTop + this.f5171h + iMax2;
                iMax2 = 0;
            }
            childAt2.layout(paddingStart2, paddingTop, childAt2.getMeasuredWidth() + paddingStart2, childAt2.getMeasuredHeight() + paddingTop);
            paddingStart2 = paddingStart2 + childAt2.getMeasuredWidth() + this.f5170g;
            iMax2 = Math.max(iMax2, childAt2.getMeasuredHeight());
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i7, int i8) {
        e(i7, i8);
    }

    public void setLabels(List<String> list) {
        removeAllViews();
        this.f5173j.clear();
        if (list != null) {
            this.f5173j.addAll(list);
            int size = list.size();
            for (int i7 = 0; i7 < size; i7++) {
                a(list.get(i7), i7);
            }
        }
    }

    public void setOnLabelClickListener(a aVar) {
        this.f5181r = aVar;
    }
}
