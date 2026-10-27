package org.chromium.chrome.browser.dom_distiller;

import J.N;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import defpackage.hb0;
import defpackage.ib0;
import defpackage.ob0;
import defpackage.yo;
import defpackage.z7;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import org.chromium.base.ThreadUtils;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.profiles.Profile;
import org.chromium.chrome.browser.profiles.b;
import org.chromium.components.dom_distiller.core.DistilledPagePrefs;
import org.chromium.components.dom_distiller.core.DomDistillerService;

/* JADX INFO: loaded from: classes.dex */
public class DistilledPagePrefsView extends LinearLayout implements DistilledPagePrefs.a, SeekBar.OnSeekBarChangeListener {
    public static final int l = R$layout.distilled_page_prefs_view;
    public RadioGroup e;
    public final HashMap f;
    public final DistilledPagePrefs g;
    public TextView h;
    public SeekBar i;
    public Spinner j;
    public final NumberFormat k;

    public class a implements View.OnClickListener {
        public final /* synthetic */ int e;

        public a(int i) {
            this.e = i;
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            DistilledPagePrefs distilledPagePrefs = DistilledPagePrefsView.this.g;
            distilledPagePrefs.getClass();
            int i = this.e;
            z7.o0(i);
            N.MJBehZGI(distilledPagePrefs.a, distilledPagePrefs, i);
        }
    }

    public DistilledPagePrefsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Profile profileD = Profile.d();
        b<DomDistillerService> bVar = ob0.a;
        Object obj = ThreadUtils.a;
        this.g = ob0.a.a(profileD, new yo(6, profileD)).a;
        this.f = new HashMap();
        this.k = NumberFormat.getPercentInstance(Locale.getDefault());
    }

    private void setFontScaleProgress(float f) {
        double d = f;
        this.i.setProgress((int) Math.round((d - 0.5d) * 20.0d));
        String string = getContext().getResources().getString(R$string.font_size_accessibility_label, this.k.format(d));
        if (Build.VERSION.SDK_INT >= 30) {
            this.i.setStateDescription(string);
        } else {
            this.i.setContentDescription(string);
        }
    }

    private void setFontScaleTextView(float f) {
        this.h.setText(this.k.format(f));
    }

    public final RadioButton a(int i, int i2) {
        z7.o0(i2);
        RadioButton radioButton = (RadioButton) findViewById(i);
        radioButton.setOnClickListener(new a(i2));
        return radioButton;
    }

    public final void b(float f) {
        setFontScaleTextView(f);
        setFontScaleProgress(f);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        DistilledPagePrefs distilledPagePrefs = this.g;
        HashMap map = distilledPagePrefs.b;
        if (map.containsKey(this)) {
            return;
        }
        DistilledPagePrefs.DistilledPagePrefsObserverWrapper distilledPagePrefsObserverWrapper = new DistilledPagePrefs.DistilledPagePrefsObserverWrapper(this);
        N.MznRD745(distilledPagePrefs.a, distilledPagePrefs, distilledPagePrefsObserverWrapper.b);
        map.put(this, distilledPagePrefsObserverWrapper);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DistilledPagePrefs distilledPagePrefs = this.g;
        DistilledPagePrefs.DistilledPagePrefsObserverWrapper distilledPagePrefsObserverWrapper = (DistilledPagePrefs.DistilledPagePrefsObserverWrapper) distilledPagePrefs.b.remove(this);
        if (distilledPagePrefsObserverWrapper == null) {
            return;
        }
        long j = distilledPagePrefs.a;
        long j2 = distilledPagePrefsObserverWrapper.b;
        N.M_HmEv0F(j, distilledPagePrefs, j2);
        N.MGXAfNxO(j2);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        this.e = (RadioGroup) findViewById(R$id.radio_button_group);
        HashMap map = this.f;
        map.put(0, a(R$id.light_mode, 0));
        map.put(1, a(R$id.dark_mode, 1));
        map.put(2, a(R$id.sepia_mode, 2));
        DistilledPagePrefs distilledPagePrefs = this.g;
        ((RadioButton) map.get(Integer.valueOf(N.Mi1cN$gk(distilledPagePrefs.a, distilledPagePrefs)))).setChecked(true);
        this.i = (SeekBar) findViewById(R$id.font_size);
        this.h = (TextView) findViewById(R$id.font_size_percentage);
        this.j = (Spinner) findViewById(R$id.font_family);
        hb0 hb0Var = new hb0(getContext(), new String[]{getResources().getString(R$string.sans_serif), getResources().getString(R$string.serif), getResources().getString(R$string.monospace)});
        hb0Var.setDropDownViewResource(R$layout.distilled_page_font_family_spinner);
        this.j.setAdapter((SpinnerAdapter) hb0Var);
        Spinner spinner = this.j;
        long j = distilledPagePrefs.a;
        spinner.setSelection(N.MSGVGQGo(j, distilledPagePrefs));
        this.j.setOnItemSelectedListener(new ib0(this));
        b(N.MGNXZIUg(j, distilledPagePrefs));
        this.i.setOnSeekBarChangeListener(this);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        this.e.setOrientation(0);
        HashMap map = this.f;
        Iterator it = map.values().iterator();
        while (it.hasNext()) {
            ((RadioButton) it.next()).getLayoutParams().width = 0;
        }
        super.onMeasure(i, i2);
        Iterator it2 = map.values().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            if (((RadioButton) it2.next()).getLineCount() > 1) {
                this.e.setOrientation(1);
                Iterator it3 = map.values().iterator();
                while (it3.hasNext()) {
                    ((RadioButton) it3.next()).getLayoutParams().width = -1;
                }
            }
        }
        super.onMeasure(i, i2);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        float f = (i / 20.0f) + 0.5f;
        setFontScaleTextView(f);
        if (z) {
            DistilledPagePrefs distilledPagePrefs = this.g;
            N.MaB$bTgz(distilledPagePrefs.a, distilledPagePrefs, f);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
    }
}
