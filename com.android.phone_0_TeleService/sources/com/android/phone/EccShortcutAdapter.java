package com.android.phone;

import android.content.Context;
import android.telephony.emergency.EmergencyNumber;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.android.phone.ShortcutViewUtils;
import com.google.common.collect.LinkedListMultimap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class EccShortcutAdapter extends BaseAdapter {
    private CharSequence mAmbulanceDescription;
    private List<EccDisplayMaterial> mEccDisplayMaterialList = new ArrayList();
    private CharSequence mFireDescription;
    private CharSequence mPoliceDescription;

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    public abstract View inflateView(View view, ViewGroup viewGroup, CharSequence charSequence, CharSequence charSequence2, int i);

    private static class EccDisplayMaterial {
        public CharSequence description;
        public int iconRes;
        public CharSequence number;

        private EccDisplayMaterial() {
            this.number = null;
            this.iconRes = 0;
            this.description = null;
        }
    }

    public EccShortcutAdapter(Context context) {
        this.mPoliceDescription = context.getText(R.string.police_type_description);
        this.mAmbulanceDescription = context.getText(R.string.ambulance_type_description);
        this.mFireDescription = context.getText(R.string.fire_type_description);
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return this.mEccDisplayMaterialList.size();
    }

    @Override // android.widget.Adapter
    public EccDisplayMaterial getItem(int i) {
        return this.mEccDisplayMaterialList.get(i);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        EccDisplayMaterial item = getItem(i);
        return inflateView(view, viewGroup, item.number, item.description, item.iconRes);
    }

    public void updateCountryEccInfo(Context context, ShortcutViewUtils.PhoneInfo phoneInfo) {
        ArrayList arrayList = new ArrayList();
        if (phoneInfo == null) {
            return;
        }
        try {
            LinkedListMultimap linkedListMultimapCreate = LinkedListMultimap.create();
            for (int i : ShortcutViewUtils.PROMOTED_CATEGORIES) {
                String strPickEmergencyNumberForCategory = pickEmergencyNumberForCategory(i, phoneInfo.getPromotedEmergencyNumbers());
                if (strPickEmergencyNumberForCategory != null) {
                    linkedListMultimapCreate.put(strPickEmergencyNumberForCategory, Integer.valueOf(i));
                }
            }
            for (K k : linkedListMultimapCreate.keySet()) {
                EccDisplayMaterial eccDisplayMaterialPrepareEccMaterial = prepareEccMaterial(context, k, linkedListMultimapCreate.get(k));
                if (eccDisplayMaterialPrepareEccMaterial != null) {
                    arrayList.add(eccDisplayMaterialPrepareEccMaterial);
                }
            }
        } finally {
            this.mEccDisplayMaterialList = arrayList;
            notifyDataSetChanged();
        }
    }

    boolean hasShortcut(String str) {
        List<EccDisplayMaterial> list = this.mEccDisplayMaterialList;
        if (list == null) {
            return false;
        }
        Iterator<EccDisplayMaterial> it = list.iterator();
        while (it.hasNext()) {
            if (it.next().number.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String pickEmergencyNumberForCategory(int i, List<EmergencyNumber> list) {
        for (EmergencyNumber emergencyNumber : list) {
            if ((emergencyNumber.getEmergencyServiceCategoryBitmask() & i) != 0) {
                return emergencyNumber.getNumber();
            }
        }
        return null;
    }

    private EccDisplayMaterial prepareEccMaterial(Context context, String str, List<Integer> list) {
        CharSequence charSequence;
        EccDisplayMaterial eccDisplayMaterial = new EccDisplayMaterial();
        eccDisplayMaterial.number = str;
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (iIntValue == 1) {
                charSequence = this.mPoliceDescription;
                eccDisplayMaterial.iconRes = R.drawable.ic_local_police_gm2_24px;
            } else if (iIntValue == 2) {
                charSequence = this.mAmbulanceDescription;
                eccDisplayMaterial.iconRes = R.drawable.ic_local_hospital_gm2_24px;
            } else if (iIntValue == 4) {
                charSequence = this.mFireDescription;
                eccDisplayMaterial.iconRes = R.drawable.ic_local_fire_department_gm2_24px;
            }
            if (TextUtils.isEmpty(eccDisplayMaterial.description)) {
                eccDisplayMaterial.description = charSequence;
            } else {
                eccDisplayMaterial.iconRes = R.drawable.ic_local_hospital_gm2_24px;
                eccDisplayMaterial.description = context.getString(R.string.description_concat_format, eccDisplayMaterial.description, charSequence);
            }
        }
        if (TextUtils.isEmpty(eccDisplayMaterial.description) || eccDisplayMaterial.iconRes == 0) {
            return null;
        }
        return eccDisplayMaterial;
    }
}
