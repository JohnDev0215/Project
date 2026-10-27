package com.byd.bluetoothcall.view.c;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Intent;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.r;
import com.byd.bluetoothcall.view.BlueToothApplication;
import com.bydux.api.R;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import java.util.Locale;

/* JADX INFO: compiled from: DialKeyBoardFragment.java */
/* JADX INFO: loaded from: classes.dex */
public class c extends Fragment implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private com.byd.bluetoothcall.view.c.f f3388b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f3389c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private EditText f3390d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private ImageView f3391e;
    private TextView f;
    private Editable g;
    private View h;
    private g i;
    private TextView l;
    private boolean j = false;
    private int k = 0;
    private String m = "97481c2717ea2b68f542b63b5546899a3d2cbca68796fe3f555bdc82ab9f9c52";
    private String n = "122f15ace7d0b8b24c82ed6ab2b5de430f8054d5836ba9437af6cc59e62d5e36";

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    class a implements View.OnFocusChangeListener {
        a() {
        }

        @Override // android.view.View.OnFocusChangeListener
        public void onFocusChange(View view, boolean z) {
            if (z || c.this.f3390d == null) {
                return;
            }
            c.this.f3390d.setSelection(c.this.f3390d.getText().length());
        }
    }

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    class b implements ViewTreeObserver.OnGlobalLayoutListener {
        b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            if (com.byd.bluetoothcall.c.a.F().Z() || com.byd.bluetoothcall.c.a.F().R() != 3) {
                return;
            }
            com.byd.bluetoothcall.c.a.F().V(BlueToothApplication.a().getApplicationContext(), c.this.f3390d);
        }
    }

    /* JADX INFO: renamed from: com.byd.bluetoothcall.view.c.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    class ViewOnTouchListenerC0086c implements View.OnTouchListener {
        ViewOnTouchListenerC0086c() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            c.this.f3390d.setCursorVisible(true);
            return false;
        }
    }

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    class d implements View.OnLongClickListener {
        d() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            c.this.f3390d.setText("");
            com.byd.bluetoothcall.c.a.F().G0("");
            return false;
        }
    }

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    class e implements View.OnLongClickListener {
        e() {
        }

        @Override // android.view.View.OnLongClickListener
        public boolean onLongClick(View view) {
            com.byd.bluetoothcall.f.f.c(c.this.getActivity(), 12);
            c.this.m("+");
            return true;
        }
    }

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    private class f implements ActionMode.Callback {
        private f() {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode actionMode) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        /* synthetic */ f(c cVar, a aVar) {
            this();
        }
    }

    /* JADX INFO: compiled from: DialKeyBoardFragment.java */
    private class g extends PhoneNumberFormattingTextWatcher {
        @TargetApi(21)
        public g(String str) {
            super(str);
        }

        @Override // android.telephony.PhoneNumberFormattingTextWatcher, android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            super.onTextChanged(charSequence, i, i2, i3);
            if (charSequence.length() <= 0) {
                if (c.this.f3391e != null) {
                    c.this.f3391e.setVisibility(4);
                }
                if (c.this.f3390d != null) {
                    c.this.f3390d.setCursorVisible(false);
                }
            } else if (c.this.f3391e != null) {
                c.this.f3391e.setVisibility(0);
            }
            if (charSequence.length() < 3) {
                c.this.f.setText("");
                return;
            }
            if (charSequence.length() > 18) {
                c.this.f.setText("");
                return;
            }
            if (c.a.b.b.f0().n0() == 2) {
                c.this.f.setText(com.byd.bluetoothcall.f.d.b(BlueToothApplication.a().getApplicationContext(), charSequence.toString().replace(" ", "")));
            }
            if (charSequence.length() == 15) {
                if (com.byd.bluetoothcall.f.f.j(charSequence.toString()).equals(c.this.m)) {
                    c.this.a("com.byd.secretcode", "com.byd.secretcode.activity.PasswordActivity");
                    c.this.f.setText("");
                    c.this.k = 0;
                    c.this.j = false;
                    return;
                }
                if (com.byd.bluetoothcall.f.f.j(charSequence.toString()).equals(c.this.n)) {
                    c.this.a("com.byd.secretcode", "com.byd.secretcode.activity.OBDActivity");
                    c.this.f.setText("");
                    c.this.k = 0;
                    c.this.j = false;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        EditText editText = this.f3390d;
        if (editText != null) {
            editText.setText("");
        }
        if (com.byd.bluetoothcall.f.f.o(1000)) {
            return;
        }
        try {
            ComponentName componentName = new ComponentName(str, str2);
            Intent intent = new Intent();
            intent.setFlags(268435456);
            intent.setComponent(componentName);
            startActivity(intent);
        } catch (Exception unused) {
        }
    }

    private void k() {
        EditText editText = this.f3390d;
        if (editText != null) {
            editText.setText("");
        }
        com.byd.bluetoothcall.c.a.F().H0("");
        com.byd.bluetoothcall.c.a.F().G0("");
    }

    private void l(View view) {
        this.f = (TextView) view.findViewById(R.id.address_of_number);
        EditText editText = (EditText) view.findViewById(R.id.keyboard_iput);
        this.f3390d = editText;
        editText.addTextChangedListener(this.i);
        this.f3390d.setCustomSelectionActionModeCallback(new f(this, null));
        this.f3390d.setOnFocusChangeListener(new a());
        this.f3390d.setLongClickable(false);
        this.f3390d.setShowSoftInputOnFocus(false);
        this.f3390d.getViewTreeObserver().addOnGlobalLayoutListener(new b());
        this.f3390d.setOnTouchListener(new ViewOnTouchListenerC0086c());
        ImageView imageView = (ImageView) view.findViewById(R.id.keyboard_delete);
        this.f3391e = imageView;
        imageView.setOnClickListener(this);
        this.f3391e.setOnLongClickListener(new d());
        view.findViewById(R.id.keyboard_number_1).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_2).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_3).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_4).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_5).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_6).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_7).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_8).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_9).setOnClickListener(this);
        view.findViewById(R.id.keyboard_number_x).setOnClickListener(this);
        TextView textView = (TextView) view.findViewById(R.id.keyboard_number_0);
        this.l = textView;
        textView.setOnClickListener(this);
        this.l.setOnLongClickListener(new e());
        view.findViewById(R.id.keyboard_number_j).setOnClickListener(this);
        view.findViewById(R.id.bt_dial_number_btn).setOnClickListener(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(String str) {
        if (c.a.b.b.f0().n0() == 2 || this.j) {
            this.f3389c = this.f3390d.getSelectionStart();
            Editable text = this.f3390d.getText();
            this.g = text;
            if (text == null || str == null) {
                return;
            }
            text.insert(this.f3389c, str);
            ImageView imageView = this.f3391e;
            if (imageView != null) {
                imageView.setVisibility(0);
            }
        }
        com.byd.bluetoothcall.c.a.F().G0(this.f3390d.getText().toString().replace(" ", ""));
    }

    public static c n(int i) {
        c cVar = new c();
        Bundle bundle = new Bundle();
        bundle.putInt("section_number", i);
        cVar.setArguments(bundle);
        return cVar;
    }

    public void o(String str) {
        EditText editText = this.f3390d;
        if (editText != null) {
            editText.setText(str);
            EditText editText2 = this.f3390d;
            editText2.setSelection(editText2.getText().length());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_dial_number_btn /* 2131296369 */:
                if (!com.byd.bluetoothcall.f.f.o(1000)) {
                    if (c.a.b.b.f0().n0() != 2) {
                        int i = this.k;
                        if (i < 10) {
                            this.k = i + 1;
                        } else {
                            this.j = true;
                            this.k = 0;
                        }
                        break;
                    } else {
                        com.byd.bluetoothcall.f.f.c(getActivity(), 11);
                        if (this.f3390d.getText().length() != 0) {
                            c.a.b.b.f0().c(this.f3390d.getText().toString());
                            k();
                            break;
                        } else {
                            String strO = c.a.b.c.t().x().o();
                            if (!TextUtils.isEmpty(strO) && !strO.equals("unknown")) {
                                this.f3389c = this.f3390d.getSelectionStart();
                                Editable text = this.f3390d.getText();
                                this.g = text;
                                text.insert(this.f3389c, strO);
                                break;
                            }
                        }
                    }
                }
                break;
            case R.id.keyboard_delete /* 2131296579 */:
                this.f3389c = this.f3390d.getSelectionStart();
                int selectionEnd = this.f3390d.getSelectionEnd();
                int i2 = this.f3389c;
                if (i2 > 0 && i2 == selectionEnd) {
                    Editable text2 = this.f3390d.getText();
                    this.g = text2;
                    int i3 = this.f3389c;
                    text2.delete(i3 - 1, i3);
                } else if (i2 == 0 && selectionEnd > 0) {
                    Editable text3 = this.f3390d.getText();
                    this.g = text3;
                    text3.delete(this.f3389c, selectionEnd);
                }
                this.f3389c = this.f3390d.getSelectionStart();
                com.byd.bluetoothcall.c.a.F().G0(this.f3390d.getText().toString().replace(" ", ""));
                break;
            case R.id.keyboard_number_0 /* 2131296585 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 12);
                m("0");
                break;
            case R.id.keyboard_number_1 /* 2131296587 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 14);
                m("1");
                break;
            case R.id.keyboard_number_2 /* 2131296589 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 12);
                m("2");
                break;
            case R.id.keyboard_number_3 /* 2131296591 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 13);
                m("3");
                break;
            case R.id.keyboard_number_4 /* 2131296593 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 14);
                m("4");
                break;
            case R.id.keyboard_number_5 /* 2131296595 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 12);
                m("5");
                break;
            case R.id.keyboard_number_6 /* 2131296597 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 13);
                m("6");
                break;
            case R.id.keyboard_number_7 /* 2131296599 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 14);
                m("7");
                break;
            case R.id.keyboard_number_8 /* 2131296601 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 12);
                m("8");
                break;
            case R.id.keyboard_number_9 /* 2131296603 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 13);
                m("9");
                break;
            case R.id.keyboard_number_j /* 2131296605 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 13);
                m("#");
                break;
            case R.id.keyboard_number_x /* 2131296607 */:
                com.byd.bluetoothcall.f.f.c(getActivity(), 14);
                m("*");
                break;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3388b = (com.byd.bluetoothcall.view.c.f) new r(this, r.a.b(getActivity().getApplication())).a(com.byd.bluetoothcall.view.c.f.class);
        this.f3388b.d(getArguments() != null ? getArguments().getInt("section_number") : 1);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View view = this.h;
        if (view != null) {
            return view;
        }
        if (com.byd.bluetoothcall.c.a.F().T() == 2) {
            this.h = layoutInflater.inflate(R.layout.land_keyboard_fragment_layout, viewGroup, false);
        } else if (com.byd.bluetoothcall.f.f.r() && com.byd.bluetoothcall.c.a.F().b0()) {
            this.h = layoutInflater.inflate(R.layout.keyboard_ar_fragment_layout, viewGroup, false);
        } else {
            this.h = layoutInflater.inflate(R.layout.keyboard_fragment_layout, viewGroup, false);
        }
        String strI = com.byd.bluetoothcall.f.f.i(BYDAutoSysPropertiesDevice.COUNTRY_CODE, "");
        if (TextUtils.isEmpty(strI) || strI.equals("unknown")) {
            this.i = new g(Locale.getDefault().getCountry());
        } else {
            this.i = new g(PhoneNumberUtil.k().s(Integer.parseInt(strI)));
        }
        l(this.h);
        return this.h;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        g gVar;
        super.onDestroy();
        this.k = 0;
        this.j = false;
        EditText editText = this.f3390d;
        if (editText == null || (gVar = this.i) == null) {
            return;
        }
        editText.removeTextChangedListener(gVar);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.Fragment
    public void onMultiWindowModeChanged(boolean z) {
        super.onMultiWindowModeChanged(z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        o("");
        o(com.byd.bluetoothcall.c.a.F().S());
        EditText editText = this.f3390d;
        if (editText != null) {
            editText.clearFocus();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
    }
}
