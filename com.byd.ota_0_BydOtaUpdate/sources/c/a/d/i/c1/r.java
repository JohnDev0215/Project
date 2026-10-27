package c.a.d.i.c1;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: compiled from: NormalTipsDialog.java */
/* JADX INFO: loaded from: classes.dex */
public class r extends n {
    public TextView G3;
    public TextView H3;
    public Button I3;
    public b J3;
    public c K3;

    /* JADX INFO: compiled from: NormalTipsDialog.java */
    public class a extends ClickableSpan {
        public a() {
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            r.this.dismissDialog();
            if (r.this.K3 != null) {
                r.this.K3.a();
            }
        }
    }

    /* JADX INFO: compiled from: NormalTipsDialog.java */
    public interface b {
        void a();
    }

    /* JADX INFO: compiled from: NormalTipsDialog.java */
    public interface c {
        void a();
    }

    public r(Context context) {
        super(context);
        setContentView(R.layout.dialog_normal_tips);
        this.G3 = (TextView) findViewById(R.id.dialog_title);
        TextView textView = (TextView) findViewById(R.id.dialog_message);
        this.H3 = textView;
        textView.setHighlightColor(0);
        Button button = (Button) findViewById(R.id.dialog_confirm_btn);
        this.I3 = button;
        button.setOnClickListener(new View.OnClickListener() { // from class: c.a.d.i.c1.f
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f2522c.n(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: m, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void n(View view) {
        dismissDialog();
        b bVar = this.J3;
        if (bVar != null) {
            bVar.a();
        }
    }

    public void o(b bVar) {
        this.J3 = bVar;
    }

    public void p() {
        if (this.f2531d) {
            this.H3.setGravity(8388613);
        } else {
            this.H3.setGravity(8388611);
        }
    }

    public void q(int i2) {
        this.H3.setText(i2);
    }

    public void r(String str) {
        this.H3.setText(str);
    }

    public void s(c cVar, String str) {
        this.K3 = cVar;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = this.H3.getText().toString();
        spannableStringBuilder.append((CharSequence) string);
        a aVar = new a();
        if (string.lastIndexOf(str) != -1) {
            spannableStringBuilder.setSpan(aVar, string.lastIndexOf(str), string.lastIndexOf(str) + str.length(), 33);
        }
        TypedValue typedValue = new TypedValue();
        this.s.getTheme().resolveAttribute(R.attr.update_info_time_color, typedValue, true);
        if (string.lastIndexOf(str) != -1) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(getContext().getColor(typedValue.resourceId)), string.lastIndexOf(str), string.lastIndexOf(str) + str.length(), 33);
        }
        this.H3.setMovementMethod(LinkMovementMethod.getInstance());
        this.H3.setText(spannableStringBuilder);
    }
}
