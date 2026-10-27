package org.chromium.third_party.android.media;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import java.util.Formatter;
import java.util.Locale;
import org.chromium.components.media_router.caf.remoting.CafExpandedControllerActivity;

/* JADX INFO: loaded from: classes3.dex */
public class MediaController extends FrameLayout {
    public e e;
    public SeekBar f;
    public boolean g;
    public final boolean h;
    public StringBuilder i;
    public ImageButton j;
    public ImageButton k;
    public ImageButton l;
    public ImageButton m;
    public ImageButton n;
    public final a o;
    public final b p;
    public final c q;
    public final d r;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            e eVar = MediaController.this.e;
            if (eVar == null) {
                return;
            }
            CafExpandedControllerActivity.this.getClass();
            throw null;
        }
    }

    public class b implements SeekBar.OnSeekBarChangeListener {
        public b() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            e eVar = MediaController.this.e;
            if (eVar != null && z) {
                CafExpandedControllerActivity.this.getClass();
                throw null;
            }
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStartTrackingTouch(SeekBar seekBar) {
            MediaController.this.g = true;
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public final void onStopTrackingTouch(SeekBar seekBar) {
            MediaController mediaController = MediaController.this;
            mediaController.g = false;
            mediaController.c();
            mediaController.b();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            e eVar = MediaController.this.e;
            if (eVar == null) {
                return;
            }
            CafExpandedControllerActivity.this.getClass();
            throw null;
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            e eVar = MediaController.this.e;
            if (eVar == null) {
                return;
            }
            CafExpandedControllerActivity.this.getClass();
            throw null;
        }
    }

    public interface e {
    }

    public MediaController(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.o = new a();
        this.p = new b();
        this.q = new c();
        this.r = new d();
        this.h = true;
        ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.media_controller, (ViewGroup) this, true);
        a();
    }

    public final void a() {
        ImageButton imageButton = (ImageButton) findViewById(R.id.pause);
        this.j = imageButton;
        if (imageButton != null) {
            imageButton.requestFocus();
            this.j.setOnClickListener(this.o);
        }
        ImageButton imageButton2 = (ImageButton) findViewById(R.id.ffwd);
        this.k = imageButton2;
        if (imageButton2 != null) {
            imageButton2.setOnClickListener(this.r);
            this.k.setVisibility(this.h ? 0 : 8);
        }
        ImageButton imageButton3 = (ImageButton) findViewById(R.id.rew);
        this.l = imageButton3;
        if (imageButton3 != null) {
            imageButton3.setOnClickListener(this.q);
            this.l.setVisibility(this.h ? 0 : 8);
        }
        ImageButton imageButton4 = (ImageButton) findViewById(R.id.next);
        this.m = imageButton4;
        if (imageButton4 != null) {
            imageButton4.setVisibility(8);
        }
        ImageButton imageButton5 = (ImageButton) findViewById(R.id.prev);
        this.n = imageButton5;
        if (imageButton5 != null) {
            imageButton5.setVisibility(8);
        }
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.mediacontroller_progress_container);
        if (viewGroup != null) {
            SeekBar seekBar = (SeekBar) viewGroup.findViewById(R.id.mediacontroller_progress_bar);
            this.f = seekBar;
            if (seekBar != null) {
                seekBar.setOnSeekBarChangeListener(this.p);
                this.f.setMax(1000);
            }
        }
        this.i = new StringBuilder();
        new Formatter(this.i, Locale.getDefault());
        ImageButton imageButton6 = this.m;
        if (imageButton6 != null) {
            imageButton6.setOnClickListener(null);
            this.m.setEnabled(false);
        }
        ImageButton imageButton7 = this.n;
        if (imageButton7 != null) {
            imageButton7.setOnClickListener(null);
            this.n.setEnabled(false);
        }
    }

    public final void b() {
        e eVar = this.e;
        if (eVar == null || this.j == null) {
            return;
        }
        CafExpandedControllerActivity.this.getClass();
        throw null;
    }

    public final void c() {
        e eVar = this.e;
        if (eVar == null || this.g) {
            return;
        }
        CafExpandedControllerActivity.this.getClass();
        throw null;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(MediaController.class.getName());
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(MediaController.class.getName());
    }

    public void setDelegate(e eVar) {
        this.e = eVar;
        b();
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        e eVar = this.e;
        if (eVar == null) {
            return;
        }
        CafExpandedControllerActivity.this.getClass();
        throw null;
    }

    public MediaController(Context context) {
        super(context);
        this.o = new a();
        this.p = new b();
        this.q = new c();
        this.r = new d();
        this.h = true;
    }
}
