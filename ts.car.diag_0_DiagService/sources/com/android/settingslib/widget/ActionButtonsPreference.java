package com.android.settingslib.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.f;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class ActionButtonsPreference extends Preference {
    private final a P;
    private final a Q;
    private final a R;
    private final a S;

    /* JADX INFO: compiled from: caradapter-bug */
    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private Button f1378a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private CharSequence f1379b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private Drawable f1380c;
        private View.OnClickListener d;
        private boolean e = true;
        private boolean f = true;

        a() {
        }

        private boolean c() {
            return this.f && !(TextUtils.isEmpty(this.f1379b) && this.f1380c == null);
        }

        void b() {
            this.f1378a.setText(this.f1379b);
            this.f1378a.setOnClickListener(this.d);
            this.f1378a.setEnabled(this.e);
            this.f1378a.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, this.f1380c, (Drawable) null, (Drawable) null);
            if (c()) {
                this.f1378a.setVisibility(0);
            } else {
                this.f1378a.setVisibility(8);
            }
        }
    }

    public ActionButtonsPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.P = new a();
        this.Q = new a();
        this.R = new a();
        this.S = new a();
        E0();
    }

    private void E0() {
        m0(R$layout.settings_action_buttons);
        s0(false);
    }

    @Override // androidx.preference.Preference
    public void M(f fVar) {
        super.M(fVar);
        fVar.Q(true);
        fVar.R(true);
        this.P.f1378a = (Button) fVar.M(R$id.button1);
        this.Q.f1378a = (Button) fVar.M(R$id.button2);
        this.R.f1378a = (Button) fVar.M(R$id.button3);
        this.S.f1378a = (Button) fVar.M(R$id.button4);
        this.P.b();
        this.Q.b();
        this.R.b();
        this.S.b();
    }
}
