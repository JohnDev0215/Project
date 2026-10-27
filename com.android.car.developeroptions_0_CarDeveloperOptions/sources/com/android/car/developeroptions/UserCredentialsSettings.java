package com.android.car.developeroptions;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.UserHandle;
import android.security.KeyChain;
import android.security.KeyStore;
import android.security.keymaster.KeyCharacteristics;
import android.security.keymaster.KeymasterBlob;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.developeroptions.core.instrumentation.InstrumentedDialogFragment;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import java.security.UnrecoverableKeyException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public class UserCredentialsSettings extends SettingsPreferenceFragment implements View.OnClickListener {
    private static final SparseArray<Credential.Type> credentialViewTypes;

    @Override // com.android.settingslib.core.instrumentation.Instrumentable
    public int getMetricsCategory() {
        return 285;
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.car.developeroptions.core.InstrumentedPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        refreshItems();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Credential credential = (Credential) view.getTag();
        if (credential != null) {
            CredentialDialogFragment.show(this, credential);
        }
    }

    @Override // com.android.car.developeroptions.SettingsPreferenceFragment, com.android.settingslib.core.lifecycle.ObservablePreferenceFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getActivity().setTitle(R.string.user_credentials);
    }

    protected void announceRemoval(String str) {
        if (isAdded()) {
            getListView().announceForAccessibility(getString(R.string.user_credential_removed, str));
        }
    }

    protected void refreshItems() {
        if (isAdded()) {
            new AliasLoader().execute(new Void[0]);
        }
    }

    public static class CredentialDialogFragment extends InstrumentedDialogFragment {
        @Override // com.android.settingslib.core.instrumentation.Instrumentable
        public int getMetricsCategory() {
            return 533;
        }

        public static void show(Fragment fragment, Credential credential) {
            Bundle bundle = new Bundle();
            bundle.putParcelable("credential", credential);
            if (fragment.getFragmentManager().findFragmentByTag("CredentialDialogFragment") == null) {
                CredentialDialogFragment credentialDialogFragment = new CredentialDialogFragment();
                credentialDialogFragment.setTargetFragment(fragment, -1);
                credentialDialogFragment.setArguments(bundle);
                credentialDialogFragment.show(fragment.getFragmentManager(), "CredentialDialogFragment");
            }
        }

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle bundle) {
            final Credential credential = (Credential) getArguments().getParcelable("credential");
            View viewInflate = getActivity().getLayoutInflater().inflate(R.layout.user_credential_dialog, (ViewGroup) null);
            ViewGroup viewGroup = (ViewGroup) viewInflate.findViewById(R.id.credential_container);
            viewGroup.addView(UserCredentialsSettings.getCredentialView(credential, R.layout.user_credential, null, viewGroup, true));
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(viewInflate);
            builder.setTitle(R.string.user_credential_title);
            builder.setPositiveButton(R.string.done, (DialogInterface.OnClickListener) null);
            final int iMyUserId = UserHandle.myUserId();
            if (!RestrictedLockUtilsInternal.hasBaseUserRestriction(getContext(), "no_config_credentials", iMyUserId)) {
                builder.setNegativeButton(R.string.trusted_credentials_remove_label, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.UserCredentialsSettings.CredentialDialogFragment.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        RestrictedLockUtils.EnforcedAdmin enforcedAdminCheckIfRestrictionEnforced = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(CredentialDialogFragment.this.getContext(), "no_config_credentials", iMyUserId);
                        if (enforcedAdminCheckIfRestrictionEnforced != null) {
                            RestrictedLockUtils.sendShowAdminSupportDetailsIntent(CredentialDialogFragment.this.getContext(), enforcedAdminCheckIfRestrictionEnforced);
                        } else {
                            CredentialDialogFragment credentialDialogFragment = CredentialDialogFragment.this;
                            credentialDialogFragment.new RemoveCredentialsTask(credentialDialogFragment.getContext(), CredentialDialogFragment.this.getTargetFragment()).execute(credential);
                        }
                        dialogInterface.dismiss();
                    }
                });
            }
            return builder.create();
        }

        private class RemoveCredentialsTask extends AsyncTask<Credential, Void, Credential[]> {
            private Fragment targetFragment;

            @Override // android.os.AsyncTask
            protected /* bridge */ /* synthetic */ Credential[] doInBackground(Credential[] credentialArr) {
                Credential[] credentialArr2 = credentialArr;
                doInBackground2(credentialArr2);
                return credentialArr2;
            }

            public RemoveCredentialsTask(Context context, Fragment fragment) {
                this.targetFragment = fragment;
            }

            /* JADX INFO: renamed from: doInBackground, reason: avoid collision after fix types in other method */
            protected Credential[] doInBackground2(Credential... credentialArr) {
                for (Credential credential : credentialArr) {
                    if (credential.isSystem()) {
                        removeGrantsAndDelete(credential);
                    } else {
                        deleteWifiCredential(credential);
                    }
                }
                return credentialArr;
            }

            private void deleteWifiCredential(Credential credential) {
                KeyStore keyStore = KeyStore.getInstance();
                EnumSet<Credential.Type> storedTypes = credential.getStoredTypes();
                if (storedTypes.contains(Credential.Type.USER_KEY)) {
                    keyStore.delete("USRPKEY_" + credential.getAlias(), 1010);
                }
                if (storedTypes.contains(Credential.Type.USER_CERTIFICATE)) {
                    keyStore.delete("USRCERT_" + credential.getAlias(), 1010);
                }
                if (storedTypes.contains(Credential.Type.CA_CERTIFICATE)) {
                    keyStore.delete("CACERT_" + credential.getAlias(), 1010);
                }
            }

            private void removeGrantsAndDelete(Credential credential) {
                try {
                    KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(CredentialDialogFragment.this.getContext());
                    try {
                        try {
                            keyChainConnectionBind.getService().removeKeyPair(credential.alias);
                        } catch (RemoteException e) {
                            Log.w("CredentialDialogFragment", "Removing credentials", e);
                        }
                    } finally {
                        keyChainConnectionBind.close();
                    }
                } catch (InterruptedException e2) {
                    Log.w("CredentialDialogFragment", "Connecting to KeyChain", e2);
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Credential... credentialArr) {
                Fragment fragment = this.targetFragment;
                if ((fragment instanceof UserCredentialsSettings) && fragment.isAdded()) {
                    UserCredentialsSettings userCredentialsSettings = (UserCredentialsSettings) this.targetFragment;
                    for (Credential credential : credentialArr) {
                        userCredentialsSettings.announceRemoval(credential.alias);
                    }
                    userCredentialsSettings.refreshItems();
                }
            }
        }
    }

    private class AliasLoader extends AsyncTask<Void, Void, List<Credential>> {
        private AliasLoader() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<Credential> doInBackground(Void... voidArr) {
            KeyStore keyStore = KeyStore.getInstance();
            int iMyUserId = UserHandle.myUserId();
            int uid = UserHandle.getUid(iMyUserId, 1000);
            int uid2 = UserHandle.getUid(iMyUserId, 1010);
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(getCredentialsForUid(keyStore, uid).values());
            arrayList.addAll(getCredentialsForUid(keyStore, uid2).values());
            return arrayList;
        }

        private boolean isAsymmetric(KeyStore keyStore, String str, int i) throws UnrecoverableKeyException {
            KeyCharacteristics keyCharacteristics = new KeyCharacteristics();
            int keyCharacteristics2 = keyStore.getKeyCharacteristics(str, (KeymasterBlob) null, (KeymasterBlob) null, i, keyCharacteristics);
            if (keyCharacteristics2 != 1) {
                throw ((UnrecoverableKeyException) new UnrecoverableKeyException("Failed to obtain information about key").initCause(KeyStore.getKeyStoreException(keyCharacteristics2)));
            }
            Integer num = keyCharacteristics.getEnum(268435458);
            if (num != null) {
                return num.intValue() == 1 || num.intValue() == 3;
            }
            throw new UnrecoverableKeyException("Key algorithm unknown");
        }

        private SortedMap<String, Credential> getCredentialsForUid(KeyStore keyStore, int i) {
            KeyStore keyStore2 = keyStore;
            int i2 = i;
            TreeMap treeMap = new TreeMap();
            Credential.Type[] typeArrValues = Credential.Type.values();
            int length = typeArrValues.length;
            int i3 = 0;
            while (i3 < length) {
                Credential.Type type = typeArrValues[i3];
                String[] strArr = type.prefix;
                int length2 = strArr.length;
                int i4 = 0;
                while (i4 < length2) {
                    String str = strArr[i4];
                    String[] list = keyStore2.list(str, i2);
                    int length3 = list.length;
                    int i5 = 0;
                    while (i5 < length3) {
                        String str2 = list[i5];
                        Credential.Type[] typeArr = typeArrValues;
                        if (UserHandle.getAppId(i) != 1000 || (!str2.startsWith("profile_key_name_encrypt_") && !str2.startsWith("profile_key_name_decrypt_") && !str2.startsWith("synthetic_password_"))) {
                            try {
                                if (type == Credential.Type.USER_KEY) {
                                    try {
                                        if (!isAsymmetric(keyStore2, str + str2, i2)) {
                                        }
                                    } catch (UnrecoverableKeyException e) {
                                        e = e;
                                        Log.e("UserCredentialsSettings", "Unable to determine algorithm of key: " + str + str2, e);
                                    }
                                }
                                Credential credential = (Credential) treeMap.get(str2);
                                if (credential == null) {
                                    credential = new Credential(str2, i2);
                                    treeMap.put(str2, credential);
                                }
                                credential.storedTypes.add(type);
                            } catch (UnrecoverableKeyException e2) {
                                e = e2;
                            }
                        }
                        i5++;
                        keyStore2 = keyStore;
                        i2 = i;
                        typeArrValues = typeArr;
                    }
                    i4++;
                    keyStore2 = keyStore;
                    i2 = i;
                    typeArrValues = typeArrValues;
                }
                i3++;
                keyStore2 = keyStore;
                i2 = i;
                typeArrValues = typeArrValues;
            }
            return treeMap;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(List<Credential> list) {
            if (UserCredentialsSettings.this.isAdded()) {
                if (list == null || list.size() == 0) {
                    TextView textView = (TextView) UserCredentialsSettings.this.getActivity().findViewById(android.R.id.empty);
                    textView.setText(R.string.user_credential_none_installed);
                    UserCredentialsSettings.this.setEmptyView(textView);
                } else {
                    UserCredentialsSettings.this.setEmptyView(null);
                }
                UserCredentialsSettings.this.getListView().setAdapter(new CredentialAdapter(list, UserCredentialsSettings.this));
            }
        }
    }

    private static class CredentialAdapter extends RecyclerView.Adapter<ViewHolder> {
        private final List<Credential> mItems;
        private final View.OnClickListener mListener;

        public CredentialAdapter(List<Credential> list, View.OnClickListener onClickListener) {
            this.mItems = list;
            this.mListener = onClickListener;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.user_credential_preference, viewGroup, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            UserCredentialsSettings.getCredentialView(this.mItems.get(i), R.layout.user_credential_preference, viewHolder.itemView, null, false);
            viewHolder.itemView.setTag(this.mItems.get(i));
            viewHolder.itemView.setOnClickListener(this.mListener);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.mItems.size();
        }
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    static {
        SparseArray<Credential.Type> sparseArray = new SparseArray<>();
        credentialViewTypes = sparseArray;
        sparseArray.put(R.id.contents_userkey, Credential.Type.USER_KEY);
        credentialViewTypes.put(R.id.contents_usercrt, Credential.Type.USER_CERTIFICATE);
        credentialViewTypes.put(R.id.contents_cacrt, Credential.Type.CA_CERTIFICATE);
    }

    protected static View getCredentialView(Credential credential, int i, View view, ViewGroup viewGroup, boolean z) {
        if (view == null) {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
        }
        ((TextView) view.findViewById(R.id.alias)).setText(credential.alias);
        ((TextView) view.findViewById(R.id.purpose)).setText(credential.isSystem() ? R.string.credential_for_vpn_and_apps : R.string.credential_for_wifi);
        view.findViewById(R.id.contents).setVisibility(z ? 0 : 8);
        if (z) {
            for (int i2 = 0; i2 < credentialViewTypes.size(); i2++) {
                view.findViewById(credentialViewTypes.keyAt(i2)).setVisibility(credential.storedTypes.contains(credentialViewTypes.valueAt(i2)) ? 0 : 8);
            }
        }
        return view;
    }

    static class Credential implements Parcelable {
        public static final Parcelable.Creator<Credential> CREATOR = new Parcelable.Creator<Credential>() { // from class: com.android.car.developeroptions.UserCredentialsSettings.Credential.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Credential createFromParcel(Parcel parcel) {
                return new Credential(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Credential[] newArray(int i) {
                return new Credential[i];
            }
        };
        final String alias;
        final EnumSet<Type> storedTypes;
        final int uid;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        enum Type {
            CA_CERTIFICATE("CACERT_"),
            USER_CERTIFICATE("USRCERT_"),
            USER_KEY("USRPKEY_", "USRSKEY_");

            final String[] prefix;

            Type(String... strArr) {
                this.prefix = strArr;
            }
        }

        Credential(String str, int i) {
            this.storedTypes = EnumSet.noneOf(Type.class);
            this.alias = str;
            this.uid = i;
        }

        Credential(Parcel parcel) {
            this(parcel.readString(), parcel.readInt());
            long j = parcel.readLong();
            for (Type type : Type.values()) {
                if (((1 << type.ordinal()) & j) != 0) {
                    this.storedTypes.add(type);
                }
            }
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.alias);
            parcel.writeInt(this.uid);
            Iterator it = this.storedTypes.iterator();
            long jOrdinal = 0;
            while (it.hasNext()) {
                jOrdinal |= 1 << ((Type) it.next()).ordinal();
            }
            parcel.writeLong(jOrdinal);
        }

        public boolean isSystem() {
            return UserHandle.getAppId(this.uid) == 1000;
        }

        public String getAlias() {
            return this.alias;
        }

        public EnumSet<Type> getStoredTypes() {
            return this.storedTypes;
        }
    }
}
