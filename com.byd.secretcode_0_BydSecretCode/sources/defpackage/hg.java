package defpackage;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.byd.secretcode.R;
import com.byd.secretcode.activity.OFFLineConfigActivity;
import com.byd.secretcode.view.BydSwitch;
import com.byd.secretcode.view.TintRadioButton;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: ConfigAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public class hg extends BaseAdapter {
    public final OFFLineConfigActivity.d b;
    public final Context c;
    public final LayoutInflater d;
    public List<ig> e;

    /* JADX INFO: compiled from: ConfigAdapter.java */
    public class a implements RadioGroup.OnCheckedChangeListener {
        public final /* synthetic */ int b;

        public a(int i) {
            this.b = i;
        }

        @Override // android.widget.RadioGroup.OnCheckedChangeListener
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            int i2 = 0;
            switch (i) {
                case R.id.config_item_radioitem1 /* 2131296437 */:
                    i2 = 1;
                    break;
                case R.id.config_item_radioitem2 /* 2131296438 */:
                    i2 = 2;
                    break;
                case R.id.config_item_radioitem3 /* 2131296439 */:
                    i2 = 3;
                    break;
            }
            if (hg.this.b != null) {
                hg.this.b.b(this.b, i2);
            }
        }
    }

    /* JADX INFO: compiled from: ConfigAdapter.java */
    public class b implements CompoundButton.OnCheckedChangeListener {
        public b() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        }
    }

    /* JADX INFO: compiled from: ConfigAdapter.java */
    public static class c {
        public TextView a;
        public TextView b;
        public RadioGroup c;
        public Spinner d;
        public BydSwitch e;
        public TextView f;
        public TintRadioButton g;
        public TintRadioButton h;
        public TintRadioButton i;
        public TintRadioButton j;
    }

    /* JADX INFO: compiled from: ConfigAdapter.java */
    public class d implements AdapterView.OnItemSelectedListener {
        public int b;

        public d(int i) {
            this.b = i;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            try {
                if (hg.this.b != null) {
                    hg.this.b.c(this.b, i);
                }
            } catch (Exception e) {
                Log.i("ConfigAdapter", "onItemSelected err::" + e);
            }
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    /* JADX INFO: compiled from: ConfigAdapter.java */
    public class e implements CompoundButton.OnCheckedChangeListener {
        public int b;

        public e(int i) {
            this.b = i;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (hg.this.b != null) {
                hg.this.b.a(this.b, z);
            }
        }
    }

    public hg(Context context, OFFLineConfigActivity.d dVar) {
        this.c = context;
        this.b = dVar;
        this.d = LayoutInflater.from(context);
    }

    public void b(List<ig> list) {
        this.e = list;
        notifyDataSetChanged();
    }

    @Override // android.widget.Adapter
    public int getCount() {
        List<ig> list = this.e;
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return this.e.get(i);
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        c cVar;
        ig igVar = this.e.get(i);
        int iF = igVar.f();
        BydLogHelper.logD("ConfigAdapter", "getView:::" + i + ";;;type:" + iF);
        if (view == null) {
            view = this.d.inflate(R.layout.offline_config_item_view, viewGroup, false);
            cVar = new c();
            cVar.a = (TextView) view.findViewById(R.id.config_item_content);
            cVar.c = (RadioGroup) view.findViewById(R.id.config_item_radiogroup);
            cVar.g = (TintRadioButton) view.findViewById(R.id.config_item_radioitem0);
            cVar.h = (TintRadioButton) view.findViewById(R.id.config_item_radioitem1);
            cVar.i = (TintRadioButton) view.findViewById(R.id.config_item_radioitem2);
            cVar.j = (TintRadioButton) view.findViewById(R.id.config_item_radioitem3);
            cVar.e = (BydSwitch) view.findViewById(R.id.config_item_switch);
            cVar.f = (TextView) view.findViewById(R.id.config_item_value);
            cVar.b = (TextView) view.findViewById(R.id.config_item_note);
            cVar.d = (Spinner) view.findViewById(R.id.config_item_spinner);
            view.setTag(cVar);
        } else {
            cVar = (c) view.getTag();
        }
        cVar.a.setText(igVar.c());
        if (iF == 1) {
            cVar.c.setVisibility(8);
            cVar.e.setVisibility(8);
            cVar.d.setVisibility(8);
            cVar.f.setVisibility(0);
            cVar.f.setText(igVar.g());
        } else if (iF == 2) {
            cVar.f.setVisibility(0);
            cVar.e.setVisibility(8);
            cVar.d.setVisibility(8);
            cVar.c.setVisibility(0);
            cVar.f.setText(igVar.g());
            cVar.c.setOnCheckedChangeListener(null);
            if (igVar.d() == null) {
                return view;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(cVar.g);
            arrayList.add(cVar.h);
            arrayList.add(cVar.i);
            arrayList.add(cVar.j);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(Integer.valueOf(R.id.config_item_radioitem0));
            arrayList2.add(Integer.valueOf(R.id.config_item_radioitem1));
            arrayList2.add(Integer.valueOf(R.id.config_item_radioitem2));
            arrayList2.add(Integer.valueOf(R.id.config_item_radioitem3));
            int size = igVar.d().size();
            boolean z = false;
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 <= size - 1) {
                    String str = igVar.d().get(i2);
                    if (TextUtils.isEmpty(str)) {
                        ((TintRadioButton) arrayList.get(i2)).setVisibility(8);
                    } else {
                        ((TintRadioButton) arrayList.get(i2)).setVisibility(0);
                        ((TintRadioButton) arrayList.get(i2)).setText(str);
                        if (igVar.b() == i2) {
                            cVar.c.check(((Integer) arrayList2.get(i2)).intValue());
                            z = true;
                        }
                    }
                } else {
                    ((TintRadioButton) arrayList.get(i2)).setVisibility(8);
                }
            }
            if (!z) {
                cVar.c.clearCheck();
            }
            cVar.c.setEnabled(igVar.a());
            if (igVar.a()) {
                cVar.c.setOnCheckedChangeListener(new a(i));
            }
        } else if (iF == 3) {
            cVar.e.setOnCheckedChangeListener(new b());
            cVar.f.setVisibility(0);
            cVar.c.setVisibility(8);
            cVar.d.setVisibility(8);
            cVar.e.setVisibility(0);
            cVar.f.setText(igVar.g());
            cVar.e.setChecked(igVar.b() != 0);
            cVar.e.setEnabled(igVar.a());
            if (igVar.a()) {
                cVar.e.setOnCheckedChangeListener(new e(i));
            }
        } else if (iF == 4) {
            cVar.d.setOnItemSelectedListener(null);
            cVar.f.setVisibility(0);
            cVar.c.setVisibility(8);
            cVar.e.setVisibility(8);
            cVar.d.setVisibility(0);
            cVar.f.setText(igVar.g());
            ArrayAdapter arrayAdapter = new ArrayAdapter(this.c, android.R.layout.simple_spinner_item, 0, igVar.d());
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            cVar.d.setAdapter((SpinnerAdapter) arrayAdapter);
            cVar.d.setSelection(igVar.b(), true);
            cVar.d.setEnabled(true);
            if (igVar.a()) {
                cVar.d.setOnItemSelectedListener(new d(i));
            }
        }
        cVar.b.setText(igVar.e());
        return view;
    }
}
