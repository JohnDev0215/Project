package com.byd.uereport.widget;

import android.content.Context;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatTextView;

/* JADX INFO: loaded from: classes2.dex */
public class BydDialogTextView extends AppCompatTextView {

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            if (BydDialogTextView.this.getLineCount() >= 3) {
                BydDialogTextView.this.setGravity(8388611);
            } else {
                BydDialogTextView.this.setGravity(1);
            }
        }
    }

    public BydDialogTextView(Context context) {
        super(context);
    }

    @Override // android.view.View
    public void onFinishInflate() {
        super.onFinishInflate();
        post(new a());
    }

    public BydDialogTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public BydDialogTextView(Context context, AttributeSet attributeSet, int i8) {
        super(context, attributeSet, i8);
    }
}
