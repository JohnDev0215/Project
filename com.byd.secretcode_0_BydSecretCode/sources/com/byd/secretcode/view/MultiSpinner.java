package com.byd.secretcode.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.secretcode.R;
import com.byd.secretcode.util.MessageEvent;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import defpackage.bm;
import defpackage.km;
import defpackage.xg;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"AppCompatCustomView"})
public class MultiSpinner extends TextView implements View.OnClickListener, DialogInterface.OnClickListener {
    private a adapter;
    private Set<Object> checkedSet;
    private Context context;
    private List<xg> dataList;
    private ListView listView;
    private int selectCount;
    private String title;

    public class a extends BaseAdapter implements View.OnClickListener {
        public List<xg> b;
        public Set<Object> c = new HashSet();

        /* JADX INFO: renamed from: com.byd.secretcode.view.MultiSpinner$a$a, reason: collision with other inner class name */
        public class C0011a {
            public TextView a;
            public CheckBox b;

            public C0011a() {
            }
        }

        public a(List<xg> list) {
            this.b = list;
        }

        public Set<Object> a() {
            return this.c;
        }

        public void b(Set<Object> set) {
            HashSet hashSet = new HashSet();
            this.c = hashSet;
            if (set != null) {
                hashSet.addAll(set);
            }
        }

        public void c(List<xg> list) {
            this.b = list;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<xg> list = this.b;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            List<xg> list = this.b;
            if (list == null) {
                return null;
            }
            return list.get(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            xg xgVar = (xg) getItem(i);
            if (view == null) {
                view = LayoutInflater.from(MultiSpinner.this.getContext()).inflate(R.layout.multi_spinner_item, (ViewGroup) null);
                C0011a c0011a = new C0011a();
                c0011a.a = (TextView) view.findViewById(R.id.textView);
                CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
                c0011a.b = checkBox;
                checkBox.setOnClickListener(this);
                view.setTag(c0011a);
            }
            C0011a c0011a2 = (C0011a) view.getTag();
            c0011a2.a.setText(xgVar.a());
            Set<Object> set = this.c;
            if (set != null) {
                if (set.contains(xgVar.b())) {
                    c0011a2.b.setChecked(true);
                } else {
                    c0011a2.b.setChecked(false);
                }
            }
            c0011a2.b.setTag(Integer.valueOf(i));
            return view;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            CheckBox checkBox = (CheckBox) view;
            Integer num = (Integer) checkBox.getTag();
            if (num == null) {
                return;
            }
            xg xgVar = (xg) getItem(num.intValue());
            if (!checkBox.isChecked()) {
                this.c.remove(xgVar.b());
                return;
            }
            int selectCount = MultiSpinner.this.getSelectCount();
            if (selectCount <= -1 || this.c.size() < selectCount) {
                this.c.add(xgVar.b());
            } else {
                checkBox.setChecked(false);
                Toast.makeText(MultiSpinner.this.getContext(), String.format("最多只能选择 %s 个", Integer.valueOf(MultiSpinner.this.selectCount)), 0).show();
            }
        }
    }

    public MultiSpinner(Context context) {
        super(context, null);
        this.selectCount = -1;
    }

    private boolean isEmpty() {
        List<xg> list = this.dataList;
        if (list == null) {
            return true;
        }
        return list.isEmpty();
    }

    private void showSelectedContent() {
        StringBuilder sb = new StringBuilder();
        Iterator<xg> it = getCheckedOptions().iterator();
        while (it.hasNext()) {
            sb.append(it.next().a());
            sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        setText(sb.toString());
    }

    public List<xg> getCheckedOptions() {
        Set<Object> set;
        ArrayList arrayList = new ArrayList();
        if (!isEmpty() && (set = this.checkedSet) != null && !set.isEmpty()) {
            for (xg xgVar : this.dataList) {
                if (this.checkedSet.contains(xgVar.b())) {
                    arrayList.add(xgVar);
                }
            }
        }
        return arrayList;
    }

    public List<xg> getDataList() {
        return this.dataList;
    }

    public int getSelectCount() {
        return this.selectCount;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewGroup viewGroup = (ViewGroup) this.listView.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(this.listView);
        }
        if (this.dataList == null) {
            Log.d("MultiSpinner", "no data to show");
        }
        this.adapter.b(this.checkedSet);
        new AlertDialog.Builder(this.context).setTitle(this.title).setPositiveButton(this.context.getString(R.string.obd_confirm), this).setNegativeButton(this.context.getString(R.string.obd_cancel), this).setView(this.listView).show();
    }

    public void setCheckedSet(Set<Object> set) {
        this.checkedSet = set;
        showSelectedContent();
    }

    public void setDataList(List<xg> list) {
        this.dataList = list;
        a aVar = this.adapter;
        if (aVar != null) {
            aVar.c(list);
            this.adapter.notifyDataSetChanged();
        } else {
            a aVar2 = new a(list);
            this.adapter = aVar2;
            this.listView.setAdapter((ListAdapter) aVar2);
        }
    }

    public void setSelectCount(int i) {
        this.selectCount = i;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public MultiSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.selectCount = -1;
        this.context = context;
        setOnClickListener(this);
        ListView listView = new ListView(context);
        this.listView = listView;
        listView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        a aVar = new a(null);
        this.adapter = aVar;
        this.listView.setAdapter((ListAdapter) aVar);
    }

    public MultiSpinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.selectCount = -1;
    }

    @Override // android.content.DialogInterface.OnClickListener
    @km(threadMode = ThreadMode.MAIN)
    public void onClick(DialogInterface dialogInterface, int i) {
        BydLogHelper.logE("MultiSpinner", "i / " + i);
        if (i != -1) {
            return;
        }
        this.checkedSet = this.adapter.a();
        showSelectedContent();
        bm.c().k(new MessageEvent("obd_change"));
    }
}
