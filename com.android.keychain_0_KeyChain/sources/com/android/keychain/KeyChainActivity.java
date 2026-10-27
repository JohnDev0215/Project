package com.android.keychain;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.admin.IDevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.security.Credentials;
import android.security.IKeyChainAliasCallback;
import android.security.KeyChain;
import android.security.KeyStore;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.android.internal.annotations.VisibleForTesting;
import com.android.keychain.internal.KeyInfoProvider;
import com.android.org.bouncycastle.asn1.x509.X509Name;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.security.auth.x500.X500Principal;

/* JADX INFO: loaded from: classes.dex */
public class KeyChainActivity extends Activity {
    private KeyStore mKeyStore = KeyStore.getInstance();
    AlertDialog mLoadingDialog;
    private PendingIntent mSender;
    private int mSenderUid;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(524288);
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
        PendingIntent pendingIntent = (PendingIntent) getIntent().getParcelableExtra("sender");
        this.mSender = pendingIntent;
        if (pendingIntent == null) {
            finish(null);
            return;
        }
        try {
            this.mSenderUid = getPackageManager().getPackageInfo(this.mSender.getIntentSender().getTargetPackage(), 0).applicationInfo.uid;
            chooseCertificate();
        } catch (PackageManager.NameNotFoundException unused) {
            finish(null);
        }
    }

    private void showLoadingDialog() {
        AlertDialog alertDialogCreate = new AlertDialog.Builder(new ContextThemeWrapper(this, android.R.style.Theme.Translucent.NoTitleBar)).setTitle(R.string.app_name).setMessage(R.string.loading_certs_message).create();
        this.mLoadingDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    private void chooseCertificate() {
        KeyInfoProvider keyInfoProvider = new KeyInfoProvider() { // from class: com.android.keychain.KeyChainActivity.1
            @Override // com.android.keychain.internal.KeyInfoProvider
            public boolean isUserSelectable(String str) {
                try {
                    KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(KeyChainActivity.this);
                    try {
                        boolean zIsUserSelectable = keyChainConnectionBind.getService().isUserSelectable(str);
                        if (keyChainConnectionBind != null) {
                            keyChainConnectionBind.close();
                        }
                        return zIsUserSelectable;
                    } catch (Throwable th) {
                        if (keyChainConnectionBind != null) {
                            try {
                                keyChainConnectionBind.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                } catch (InterruptedException e) {
                    Log.e("KeyChain", "interrupted while checking if key is user-selectable", e);
                    Thread.currentThread().interrupt();
                    return false;
                } catch (Exception e2) {
                    Log.e("KeyChain", "error while checking if key is user-selectable", e2);
                    return false;
                }
            }
        };
        Log.i("KeyChain", String.format("Requested by app uid %d to provide a private key alias", Integer.valueOf(this.mSenderUid)));
        String[] stringArrayExtra = getIntent().getStringArrayExtra("key_types");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        Log.i("KeyChain", String.format("Key types specified: %s", Arrays.toString(stringArrayExtra)));
        ArrayList arrayList = (ArrayList) getIntent().getSerializableExtra("issuers");
        if (arrayList == null) {
            arrayList = new ArrayList();
        } else {
            Log.i("KeyChain", "Issuers specified, will be listed later.");
        }
        KeyStore keyStore = this.mKeyStore;
        final AliasLoader aliasLoader = new AliasLoader(keyStore, this, keyInfoProvider, new CertificateParametersFilter(keyStore, stringArrayExtra, arrayList));
        aliasLoader.execute(new Void[0]);
        IKeyChainAliasCallback.Stub stub = new IKeyChainAliasCallback.Stub() { // from class: com.android.keychain.KeyChainActivity.2
            public void alias(String str) {
                Log.i("KeyChain", String.format("Alias provided by device policy client: %s", str));
                if (str != null) {
                    KeyChainActivity.this.finishWithAliasFromPolicy(str);
                    return;
                }
                try {
                    final CertificateAdapter certificateAdapter = aliasLoader.get();
                    if (!certificateAdapter.hasKeysToChoose()) {
                        Log.i("KeyChain", "No keys to choose from");
                        KeyChainActivity.this.finish(null);
                    } else {
                        KeyChainActivity.this.runOnUiThread(new Runnable() { // from class: com.android.keychain.KeyChainActivity.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                AlertDialog alertDialog = KeyChainActivity.this.mLoadingDialog;
                                if (alertDialog != null) {
                                    alertDialog.dismiss();
                                    KeyChainActivity.this.mLoadingDialog = null;
                                }
                                KeyChainActivity.this.displayCertChooserDialog(certificateAdapter);
                            }
                        });
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.e("KeyChain", "Loading certificate aliases interrupted", e);
                    KeyChainActivity.this.finish(null);
                }
            }
        };
        showLoadingDialog();
        try {
            IDevicePolicyManager.Stub.asInterface(ServiceManager.getService("device_policy")).choosePrivateKeyAlias(this.mSenderUid, (Uri) getIntent().getParcelableExtra("uri"), getIntent().getStringExtra("alias"), stub);
        } catch (RemoteException e) {
            Log.e("KeyChain", "Unable to request alias from DevicePolicyManager", e);
            try {
                stub.alias((String) null);
            } catch (RemoteException unused) {
                finish(null);
            }
        }
    }

    @VisibleForTesting
    public static class CertificateParametersFilter {
        private final List<X500Principal> mIssuers = new ArrayList();
        private final KeyStore mKeyStore;
        private final List<String> mKeyTypes;

        public CertificateParametersFilter(KeyStore keyStore, String[] strArr, ArrayList<byte[]> arrayList) {
            this.mKeyStore = keyStore;
            this.mKeyTypes = Arrays.asList(strArr);
            for (byte[] bArr : arrayList) {
                try {
                    Log.i("KeyChain", "Added issuer: " + new X500Principal(bArr).getName());
                    this.mIssuers.add(new X500Principal(bArr));
                } catch (IllegalArgumentException e) {
                    Log.w("KeyChain", "Skipping invalid issuer", e);
                }
            }
        }

        public boolean shouldPresentCertificate(String str) {
            X509Certificate x509CertificateLoadCertificate = KeyChainActivity.loadCertificate(this.mKeyStore, str);
            if (x509CertificateLoadCertificate == null) {
                Log.i("KeyChain", String.format("No certificate associated with alias %s", str));
                return false;
            }
            ArrayList arrayList = new ArrayList(KeyChainActivity.loadCertificateChain(this.mKeyStore, str));
            Log.i("KeyChain", String.format("Inspecting certificate %s aliased with %s, chain length %d", x509CertificateLoadCertificate.getSubjectDN().getName(), str, Integer.valueOf(arrayList.size())));
            String algorithm = x509CertificateLoadCertificate.getPublicKey().getAlgorithm();
            Log.i("KeyChain", String.format("Certificate key algorithm: %s", algorithm));
            if (!this.mKeyTypes.isEmpty() && !this.mKeyTypes.contains(algorithm)) {
                return false;
            }
            ArrayList<X500Principal> arrayList2 = new ArrayList();
            arrayList2.add(x509CertificateLoadCertificate.getIssuerX500Principal());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                X500Principal subjectX500Principal = ((X509Certificate) it.next()).getSubjectX500Principal();
                Log.i("KeyChain", String.format("Subject of intermediate in client certificate chain: %s", subjectX500Principal.getName()));
                arrayList2.add(subjectX500Principal);
            }
            if (this.mIssuers.isEmpty()) {
                return true;
            }
            for (X500Principal x500Principal : arrayList2) {
                if (this.mIssuers.contains(x500Principal)) {
                    Log.i("KeyChain", String.format("Requested issuer found: %s", x500Principal));
                    return true;
                }
            }
            return false;
        }
    }

    @VisibleForTesting
    static class AliasLoader extends AsyncTask<Void, Void, CertificateAdapter> {
        private final CertificateParametersFilter mCertificateFilter;
        private final Context mContext;
        private final KeyInfoProvider mInfoProvider;
        private final KeyStore mKeyStore;

        public AliasLoader(KeyStore keyStore, Context context, KeyInfoProvider keyInfoProvider, CertificateParametersFilter certificateParametersFilter) {
            this.mKeyStore = keyStore;
            this.mContext = context;
            this.mInfoProvider = keyInfoProvider;
            this.mCertificateFilter = certificateParametersFilter;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public CertificateAdapter doInBackground(Void... voidArr) {
            List listAsList;
            String[] list = this.mKeyStore.list("USRPKEY_");
            if (list == null) {
                listAsList = Collections.emptyList();
            } else {
                listAsList = Arrays.asList(list);
            }
            KeyStore keyStore = this.mKeyStore;
            Context context = this.mContext;
            Stream stream = listAsList.stream();
            final KeyInfoProvider keyInfoProvider = this.mInfoProvider;
            Objects.requireNonNull(keyInfoProvider);
            Stream streamFilter = stream.filter(new Predicate() { // from class: com.android.keychain.-$$Lambda$weVG-KQSk-7Gu752kdmGT8uHroE
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return keyInfoProvider.isUserSelectable((String) obj);
                }
            });
            final CertificateParametersFilter certificateParametersFilter = this.mCertificateFilter;
            Objects.requireNonNull(certificateParametersFilter);
            return new CertificateAdapter(keyStore, context, (List) streamFilter.filter(new Predicate() { // from class: com.android.keychain.-$$Lambda$QKjJylqdBin4FRhNMtjM6rlN1Kc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return certificateParametersFilter.shouldPresentCertificate((String) obj);
                }
            }).sorted().collect(Collectors.toList()));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void displayCertChooserDialog(final com.android.keychain.KeyChainActivity.CertificateAdapter r10) {
        /*
            Method dump skipped, instruction units count: 265
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.keychain.KeyChainActivity.displayCertChooserDialog(com.android.keychain.KeyChainActivity$CertificateAdapter):void");
    }

    @VisibleForTesting
    static class CertificateAdapter extends BaseAdapter {
        private final List<String> mAliases;
        private final Context mContext;
        private final KeyStore mKeyStore;
        private final List<String> mSubjects;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        private CertificateAdapter(KeyStore keyStore, Context context, List<String> list) {
            ArrayList arrayList = new ArrayList();
            this.mSubjects = arrayList;
            this.mAliases = list;
            arrayList.addAll(Collections.nCopies(list.size(), null));
            this.mKeyStore = keyStore;
            this.mContext = context;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mAliases.size();
        }

        @Override // android.widget.Adapter
        public String getItem(int i) {
            return this.mAliases.get(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(this.mContext).inflate(R.layout.cert_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.mAliasTextView = (TextView) view.findViewById(R.id.cert_item_alias);
                viewHolder.mSubjectTextView = (TextView) view.findViewById(R.id.cert_item_subject);
                viewHolder.mRadioButton = (RadioButton) view.findViewById(R.id.cert_item_selected);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.mAliasTextView.setText(this.mAliases.get(i));
            String str = this.mSubjects.get(i);
            if (str == null) {
                new CertLoader(i, viewHolder.mSubjectTextView).execute(new Void[0]);
            } else {
                viewHolder.mSubjectTextView.setText(str);
            }
            viewHolder.mRadioButton.setChecked(i == ((ListView) viewGroup).getCheckedItemPosition() - 1);
            return view;
        }

        public boolean hasKeysToChoose() {
            return !this.mAliases.isEmpty();
        }

        private class CertLoader extends AsyncTask<Void, Void, String> {
            private final int mAdapterPosition;
            private final TextView mSubjectView;

            private CertLoader(int i, TextView textView) {
                this.mAdapterPosition = i;
                this.mSubjectView = textView;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public String doInBackground(Void... voidArr) {
                X509Certificate x509CertificateLoadCertificate = KeyChainActivity.loadCertificate(CertificateAdapter.this.mKeyStore, (String) CertificateAdapter.this.mAliases.get(this.mAdapterPosition));
                if (x509CertificateLoadCertificate == null) {
                    return null;
                }
                return X509Name.getInstance(x509CertificateLoadCertificate.getSubjectX500Principal().getEncoded()).toString(true, X509Name.DefaultSymbols);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(String str) {
                CertificateAdapter.this.mSubjects.set(this.mAdapterPosition, str);
                this.mSubjectView.setText(str);
            }
        }
    }

    private static class ViewHolder {
        TextView mAliasTextView;
        RadioButton mRadioButton;
        TextView mSubjectTextView;

        private ViewHolder() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish(String str) {
        finish(str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishWithAliasFromPolicy(String str) {
        finish(str, true);
    }

    private void finish(String str, boolean z) {
        String str2;
        AlertDialog alertDialog = this.mLoadingDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            this.mLoadingDialog = null;
        }
        if (str == null || str.equals("android:alias-selection-denied")) {
            setResult(0);
            str2 = null;
        } else {
            Intent intent = new Intent();
            intent.putExtra("android.intent.extra.TEXT", str);
            setResult(-1, intent);
            str2 = str;
        }
        IKeyChainAliasCallback iKeyChainAliasCallbackAsInterface = IKeyChainAliasCallback.Stub.asInterface(getIntent().getIBinderExtra("response"));
        if (iKeyChainAliasCallbackAsInterface != null) {
            new ResponseSender(iKeyChainAliasCallbackAsInterface, str2, z).execute(new Void[0]);
        } else {
            finish();
        }
    }

    private class ResponseSender extends AsyncTask<Void, Void, Void> {
        private String mAlias;
        private boolean mFromPolicy;
        private IKeyChainAliasCallback mKeyChainAliasResponse;

        private ResponseSender(IKeyChainAliasCallback iKeyChainAliasCallback, String str, boolean z) {
            this.mKeyChainAliasResponse = iKeyChainAliasCallback;
            this.mAlias = str;
            this.mFromPolicy = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            try {
                if (this.mAlias != null) {
                    KeyChain.KeyChainConnection keyChainConnectionBind = KeyChain.bind(KeyChainActivity.this);
                    try {
                        if (!this.mFromPolicy && !keyChainConnectionBind.getService().isUserSelectable(this.mAlias)) {
                            Log.w("KeyChain", String.format("Alias %s not user-selectable.", this.mAlias));
                            return null;
                        }
                        keyChainConnectionBind.getService().setGrant(KeyChainActivity.this.mSenderUid, this.mAlias, true);
                        keyChainConnectionBind.close();
                    } finally {
                        keyChainConnectionBind.close();
                    }
                }
                this.mKeyChainAliasResponse.alias(this.mAlias);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                Log.d("KeyChain", "interrupted while granting access", e);
            } catch (Exception e2) {
                Log.e("KeyChain", "error while granting access", e2);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r1) {
            KeyChainActivity.this.finish();
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        finish(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static X509Certificate loadCertificate(KeyStore keyStore, String str) {
        byte[] bArr = keyStore.get("USRCERT_" + str);
        if (bArr == null) {
            Log.i("KeyChain", String.format("Missing user certificate for key alias %s", str));
            return null;
        }
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            Log.w("KeyChain", "Error generating certificate", e);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<X509Certificate> loadCertificateChain(KeyStore keyStore, String str) {
        byte[] bArr = keyStore.get("CACERT_" + str);
        if (bArr == null) {
            Log.i("KeyChain", String.format("Missing certificate chain for key alias %s", str));
            return Collections.emptyList();
        }
        try {
            return Credentials.convertFromPem(bArr);
        } catch (IOException | CertificateException e) {
            Log.w("KeyChain", String.format("Error parsing certificate chain for alias %s", str), e);
            return Collections.emptyList();
        }
    }
}
