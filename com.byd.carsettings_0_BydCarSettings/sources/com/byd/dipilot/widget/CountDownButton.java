package com.byd.dipilot.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.AppCompatButton;
import androidx.exifinterface.media.ExifInterface;
import com.byd.dipilot.bpresenter.R$string;
import com.byd.dipilot.bpresenter.R$styleable;

/* JADX INFO: loaded from: classes2.dex */
public class CountDownButton extends AppCompatButton implements View.OnClickListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f3640a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f3641b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3642c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3643d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3644e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CountDownTimer f3645f;
    public b.b.k.d.b.a g;

    public class a extends CountDownTimer {
        public a(long j, long j2) {
            super(j, j2);
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // android.os.CountDownTimer
        public void onFinish() {
            CountDownButton.this.setAlpha(1.0f);
            CountDownButton.this.setText(R$string.dialog_btn_ok);
            CountDownButton countDownButton = CountDownButton.this;
            countDownButton.setOnClickListener(countDownButton);
            if (CountDownButton.this.g != null) {
                CountDownButton.this.g.onFinish();
            }
            CountDownButton.this.f3645f = null;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            StringBuilder sb = new StringBuilder();
            sb.append(CountDownButton.this.f3642c);
            if (CountDownButton.this.f3643d) {
                sb.append("(");
                sb.append(CountDownButton.this.f3641b);
                sb.append(ExifInterface.LATITUDE_SOUTH);
                sb.append(")");
            }
            CountDownButton.d(CountDownButton.this);
            CountDownButton.this.setText(sb);
        }
    }

    public CountDownButton(Context context) {
        super(context);
        this.f3640a = 5L;
        this.f3644e = CountDownButton.class.getSimpleName();
        h(context, null);
    }

    public static /* synthetic */ long d(CountDownButton countDownButton) {
        long j = countDownButton.f3641b;
        countDownButton.f3641b = j - 1;
        return j;
    }

    public final void g(AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CountDownButton);
        this.f3640a = typedArrayObtainStyledAttributes.getInt(R$styleable.CountDownButton_duration, 5);
        this.f3642c = typedArrayObtainStyledAttributes.getString(R$styleable.CountDownButton_text);
        this.f3643d = typedArrayObtainStyledAttributes.getBoolean(R$styleable.CountDownButton_addSuffix, true);
        typedArrayObtainStyledAttributes.recycle();
        Log.i(this.f3644e, "getAttributes duration =" + this.f3640a);
    }

    public final void h(Context context, AttributeSet attributeSet) {
        setOnClickListener(this);
        g(attributeSet);
        if (this.f3640a > 0) {
            i();
        } else {
            setText(this.f3642c);
        }
    }

    public final void i() {
        long j = this.f3640a;
        this.f3641b = j;
        a aVar = new a(j * 1000, 1000L);
        this.f3645f = aVar;
        aVar.start();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.k.d.b.a aVar = this.g;
        if (aVar != null) {
            aVar.a();
        }
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        CountDownTimer countDownTimer = this.f3645f;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    public void setBtnBeforeText(String str) {
        this.f3642c = str;
    }

    public void setDuration(long j) {
        this.f3640a = j;
        if (j <= 0 || this.f3645f != null) {
            return;
        }
        i();
    }

    public void setOnBtnCallBackListener(b.b.k.d.b.a aVar) {
        this.g = aVar;
    }

    public CountDownButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3640a = 5L;
        this.f3644e = CountDownButton.class.getSimpleName();
        h(context, attributeSet);
    }
}
