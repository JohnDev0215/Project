package com.android.providers.media;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.ImageDecoder;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.providers.media.MediaProvider;
import com.android.providers.media.PermissionActivity;
import com.android.providers.media.util.Logging;
import com.android.providers.media.util.Metrics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes.dex */
public class PermissionActivity extends Activity {
    private static final String DATA_AUDIO = "audio";
    private static final String DATA_GENERIC = "generic";
    private static final String DATA_IMAGE = "image";
    private static final String DATA_VIDEO = "video";
    private static final Long LEAST_SHOW_PROGRESS_TIME_MS = 300L;
    private static final int ORDER_AUDIO = 3;
    private static final int ORDER_GENERIC = 4;
    private static final int ORDER_IMAGE = 1;
    private static final int ORDER_VIDEO = 2;
    public static final int REQUEST_CODE = 42;
    private static final String VERB_DELETE = "delete";
    private static final String VERB_FAVORITE = "favorite";
    private static final String VERB_TRASH = "trash";
    private static final String VERB_UNFAVORITE = "unfavorite";
    private static final String VERB_UNTRASH = "untrash";
    private static final String VERB_WRITE = "write";
    private ApplicationInfo appInfo;
    private String data;
    private CharSequence label;
    private ProgressDialog progressDialog;
    private TextView titleView;
    private List<Uri> uris;
    private ContentValues values;
    private String verb;
    private String volumeName;

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return i == 4;
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return i == 4;
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addSystemFlags(LocalCallingIdentity.PERMISSION_WRITE_AUDIO);
        setFinishOnTouchOutside(false);
        try {
            this.uris = MediaProvider.collectUris((ClipData) getIntent().getExtras().getParcelable("clip_data"));
            this.values = (ContentValues) getIntent().getExtras().getParcelable("content_values");
            ApplicationInfo applicationInfoResolveCallingAppInfo = resolveCallingAppInfo();
            this.appInfo = applicationInfoResolveCallingAppInfo;
            this.label = resolveAppLabel(applicationInfoResolveCallingAppInfo);
            this.verb = resolveVerb();
            this.data = resolveData();
            this.volumeName = MediaStore.getVolumeName(this.uris.get(0));
            this.progressDialog = new ProgressDialog(this);
            String str = this.verb;
            byte b = -1;
            int iHashCode = str.hashCode();
            if (iHashCode != -1717948619) {
                if (iHashCode == 1050790300 && str.equals(VERB_FAVORITE)) {
                    b = 0;
                }
            } else if (str.equals(VERB_UNFAVORITE)) {
                b = 1;
            }
            if (b == 0 || b == 1) {
                onPositiveAction(null, 0);
                return;
            }
            View viewInflate = getLayoutInflater().inflate(R.layout.permission_body, (ViewGroup) null, false);
            new DescriptionTask(viewInflate).execute(this.uris);
            CharSequence charSequenceResolveMessageText = resolveMessageText();
            if (!TextUtils.isEmpty(charSequenceResolveMessageText)) {
                TextView textView = (TextView) viewInflate.requireViewById(R.id.message);
                textView.setVisibility(0);
                textView.setText(charSequenceResolveMessageText);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(resolveTitleText());
            builder.setPositiveButton(R.string.allow, new DialogInterface.OnClickListener() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$fUPnFQNlHgalQFKvvxmjy07NFGg
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.onPositiveAction(dialogInterface, i);
                }
            });
            builder.setNegativeButton(R.string.deny, new DialogInterface.OnClickListener() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$ji_A5oF5LcyhYbae-uMNBPs-o9I
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.onNegativeAction(dialogInterface, i);
                }
            });
            builder.setCancelable(false);
            builder.setView(viewInflate);
            AlertDialog alertDialogShow = builder.show();
            TextView textView2 = (TextView) alertDialogShow.findViewById(android.R.id.message);
            if (textView2 != null) {
                textView2.setTextAppearance(R.style.PermissionAlertDialogTitle);
            } else {
                Log.w(Logging.TAG, "Couldn't find message element");
            }
            WindowManager.LayoutParams attributes = alertDialogShow.getWindow().getAttributes();
            attributes.width = getResources().getDimensionPixelSize(R.dimen.permission_dialog_width);
            alertDialogShow.getWindow().setAttributes(attributes);
            this.titleView = (TextView) findViewByPredicate(alertDialogShow.getWindow().getDecorView(), new Predicate() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$-eL_MvKpqaBgMdmcHbChCRt_Ysw
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return PermissionActivity.lambda$onCreate$0((View) obj);
                }
            });
        } catch (Exception e) {
            Log.w(Logging.TAG, e);
            finish();
        }
    }

    static /* synthetic */ boolean lambda$onCreate$0(View view) {
        return (view instanceof TextView) && view.isImportantForAccessibility();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPositiveAction(DialogInterface dialogInterface, int i) {
        if (dialogInterface != null) {
            AlertDialog alertDialog = (AlertDialog) dialogInterface;
            alertDialog.getButton(-1).setEnabled(false);
            alertDialog.getButton(-2).setEnabled(false);
        }
        this.progressDialog.show();
        new AnonymousClass1(System.currentTimeMillis()).execute(new Void[0]);
    }

    /* JADX INFO: renamed from: com.android.providers.media.PermissionActivity$1, reason: invalid class name */
    class AnonymousClass1 extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ long val$startTime;

        AnonymousClass1(long j) {
            this.val$startTime = j;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.os.AsyncTask
        public Void doInBackground(Void... voidArr) {
            Log.d(Logging.TAG, "User allowed grant for " + PermissionActivity.this.uris);
            Metrics.logPermissionGranted(PermissionActivity.this.volumeName, PermissionActivity.this.appInfo.uid, PermissionActivity.this.getCallingPackage(), PermissionActivity.this.uris.size());
            try {
                String action = PermissionActivity.this.getIntent().getAction();
                byte b = -1;
                switch (action.hashCode()) {
                    case -665880162:
                        if (action.equals("create_delete_request")) {
                            b = 3;
                        }
                        break;
                    case -208838705:
                        if (action.equals("create_favorite_request")) {
                            b = 2;
                        }
                        break;
                    case 508472364:
                        if (action.equals("create_write_request")) {
                            b = 0;
                        }
                        break;
                    case 1948507781:
                        if (action.equals("create_trash_request")) {
                            b = 1;
                        }
                        break;
                }
                if (b == 0) {
                    Iterator it = PermissionActivity.this.uris.iterator();
                    while (it.hasNext()) {
                        PermissionActivity.this.grantUriPermission(PermissionActivity.this.getCallingPackage(), (Uri) it.next(), 3);
                    }
                    return null;
                }
                if (b == 1 || b == 2) {
                    ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                    Iterator it2 = PermissionActivity.this.uris.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(ContentProviderOperation.newUpdate((Uri) it2.next()).withValues(PermissionActivity.this.values).withExtra("android:query-arg-allow-movement", Boolean.TRUE).withExceptionAllowed(true).build());
                    }
                    PermissionActivity.this.getContentResolver().applyBatch("media", arrayList);
                    return null;
                }
                if (b != 3) {
                    return null;
                }
                ArrayList<ContentProviderOperation> arrayList2 = new ArrayList<>();
                Iterator it3 = PermissionActivity.this.uris.iterator();
                while (it3.hasNext()) {
                    arrayList2.add(ContentProviderOperation.newDelete((Uri) it3.next()).withExceptionAllowed(true).build());
                }
                PermissionActivity.this.getContentResolver().applyBatch("media", arrayList2);
                return null;
            } catch (Exception e) {
                Log.w(Logging.TAG, e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Void r6) {
            PermissionActivity.this.setResult(-1);
            long jCurrentTimeMillis = System.currentTimeMillis() - this.val$startTime;
            if (jCurrentTimeMillis > PermissionActivity.LEAST_SHOW_PROGRESS_TIME_MS.longValue()) {
                PermissionActivity.this.progressDialog.dismiss();
                PermissionActivity.this.finish();
            } else {
                new Handler(PermissionActivity.this.getMainLooper()).postDelayed(new Runnable() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$1$IfhzNBF8a1F3zdHmg6v-wSzK28Q
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onPostExecute$0$PermissionActivity$1();
                    }
                }, PermissionActivity.LEAST_SHOW_PROGRESS_TIME_MS.longValue() - jCurrentTimeMillis);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$onPostExecute$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$onPostExecute$0$PermissionActivity$1() {
            PermissionActivity.this.progressDialog.dismiss();
            PermissionActivity.this.finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onNegativeAction(DialogInterface dialogInterface, int i) {
        new AsyncTask<Void, Void, Void>() { // from class: com.android.providers.media.PermissionActivity.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                Log.d(Logging.TAG, "User declined request for " + PermissionActivity.this.uris);
                Metrics.logPermissionDenied(PermissionActivity.this.volumeName, PermissionActivity.this.appInfo.uid, PermissionActivity.this.getCallingPackage(), 1);
                return null;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r2) {
                PermissionActivity.this.setResult(0);
                PermissionActivity.this.finish();
            }
        }.execute(new Void[0]);
    }

    private CharSequence resolveAppLabel(ApplicationInfo applicationInfo) throws PackageManager.NameNotFoundException {
        CharSequence applicationLabel = getPackageManager().getApplicationLabel(applicationInfo);
        if (TextUtils.isEmpty(applicationLabel)) {
            throw new PackageManager.NameNotFoundException("Missing calling package");
        }
        return applicationLabel;
    }

    private ApplicationInfo resolveCallingAppInfo() throws PackageManager.NameNotFoundException {
        String callingPackage = getCallingPackage();
        if (TextUtils.isEmpty(callingPackage)) {
            throw new PackageManager.NameNotFoundException("Missing calling package");
        }
        return getPackageManager().getApplicationInfo(callingPackage, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String resolveVerb() {
        /*
            r6 = this;
            android.content.Intent r0 = r6.getIntent()
            java.lang.String r0 = r0.getAction()
            int r1 = r0.hashCode()
            r2 = 3
            r3 = 2
            r4 = 1
            r5 = 0
            switch(r1) {
                case -665880162: goto L32;
                case -208838705: goto L28;
                case 508472364: goto L1e;
                case 1948507781: goto L14;
                default: goto L13;
            }
        L13:
            goto L3c
        L14:
            java.lang.String r1 = "create_trash_request"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3c
            r0 = r4
            goto L3d
        L1e:
            java.lang.String r1 = "create_write_request"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3c
            r0 = r5
            goto L3d
        L28:
            java.lang.String r1 = "create_favorite_request"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3c
            r0 = r3
            goto L3d
        L32:
            java.lang.String r1 = "create_delete_request"
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto L3c
            r0 = r2
            goto L3d
        L3c:
            r0 = -1
        L3d:
            if (r0 == 0) goto L87
            if (r0 == r4) goto L77
            if (r0 == r3) goto L67
            if (r0 != r2) goto L48
            java.lang.String r6 = "delete"
            return r6
        L48:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Invalid action: "
            r1.append(r2)
            android.content.Intent r6 = r6.getIntent()
            java.lang.String r6 = r6.getAction()
            r1.append(r6)
            java.lang.String r6 = r1.toString()
            r0.<init>(r6)
            throw r0
        L67:
            android.content.ContentValues r6 = r6.values
            java.lang.String r0 = "is_favorite"
            boolean r6 = com.android.providers.media.util.DatabaseUtils.getAsBoolean(r6, r0, r5)
            if (r6 == 0) goto L74
            java.lang.String r6 = "favorite"
            goto L76
        L74:
            java.lang.String r6 = "unfavorite"
        L76:
            return r6
        L77:
            android.content.ContentValues r6 = r6.values
            java.lang.String r0 = "is_trashed"
            boolean r6 = com.android.providers.media.util.DatabaseUtils.getAsBoolean(r6, r0, r5)
            if (r6 == 0) goto L84
            java.lang.String r6 = "trash"
            goto L86
        L84:
            java.lang.String r6 = "untrash"
        L86:
            return r6
        L87:
            java.lang.String r6 = "write"
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.providers.media.PermissionActivity.resolveVerb():java.lang.String");
    }

    private String resolveData() {
        MediaProvider.LocalUriMatcher localUriMatcher = new MediaProvider.LocalUriMatcher("media");
        int iMatchUri = localUriMatcher.matchUri(this.uris.get(0), false);
        for (int i = 1; i < this.uris.size(); i++) {
            if (localUriMatcher.matchUri(this.uris.get(i), false) != iMatchUri) {
                return DATA_GENERIC;
            }
        }
        return iMatchUri != 2 ? iMatchUri != 101 ? iMatchUri != 201 ? DATA_GENERIC : DATA_VIDEO : DATA_AUDIO : DATA_IMAGE;
    }

    private CharSequence resolveTitleText() {
        String str = "permission_" + this.verb + "_" + this.data;
        int identifier = getResources().getIdentifier(str, "plurals", getResources().getResourcePackageName(R.string.app_label));
        if (identifier != 0) {
            int size = this.uris.size();
            return TextUtils.expandTemplate(getResources().getQuantityText(identifier, size), this.label, String.valueOf(size));
        }
        throw new IllegalStateException("Invalid resource: " + str);
    }

    private CharSequence resolveMessageText() {
        int identifier = getResources().getIdentifier("permission_" + this.verb + "_" + this.data + "_info", "plurals", getResources().getResourcePackageName(R.string.app_label));
        if (identifier == 0) {
            return null;
        }
        int size = this.uris.size();
        return TextUtils.expandTemplate(getResources().getQuantityText(identifier, size), this.label, String.valueOf(size), String.valueOf((((this.values.getAsLong("date_expires").longValue() * 1000) - System.currentTimeMillis()) + 86400000) / 86400000));
    }

    private CharSequence resolvePositiveText() {
        return getResources().getText(getResources().getIdentifier("permission_" + this.verb + "_grant", "string", getResources().getResourcePackageName(R.string.app_label)));
    }

    private CharSequence resolveNegativeText() {
        return getResources().getText(getResources().getIdentifier("permission_" + this.verb + "_deny", "string", getResources().getResourcePackageName(R.string.app_label)));
    }

    private static View findViewByPredicate(View view, Predicate<View> predicate) {
        if (predicate.test(view)) {
            return view;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View viewFindViewByPredicate = findViewByPredicate(viewGroup.getChildAt(i), predicate);
            if (viewFindViewByPredicate != null) {
                return viewFindViewByPredicate;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    class DescriptionTask extends AsyncTask<List<Uri>, Void, List<Description>> {
        private static final int MAX_THUMBS = 3;
        private View bodyView;
        private Resources res;

        public DescriptionTask(View view) {
            this.bodyView = view;
            this.res = view.getContext().getResources();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public List<Description> doInBackground(List<Uri>... listArr) {
            int i;
            List<Uri> list = listArr[0];
            ArrayList arrayList = new ArrayList();
            if (list.isEmpty()) {
                return arrayList;
            }
            int i2 = 3;
            if (list.size() == 1) {
                ((ImageView) this.bodyView.requireViewById(R.id.thumb_full)).setVisibility(0);
                i = 7;
            } else {
                ((ImageView) this.bodyView.requireViewById(R.id.thumb2)).setVisibility(0);
                ((ImageView) this.bodyView.requireViewById(R.id.thumb3)).setVisibility(0);
                if (list.size() == 3) {
                    ((ImageView) this.bodyView.requireViewById(R.id.thumb1)).setVisibility(0);
                } else if (list.size() > 3) {
                    this.bodyView.requireViewById(R.id.thumb_more_container).setVisibility(0);
                }
                i = 3;
            }
            if (TextUtils.equals(PermissionActivity.this.data, PermissionActivity.DATA_GENERIC) && list.size() > 1) {
                final $$Lambda$PermissionActivity$DescriptionTask$82DszBk9KV1YuvIxtc2opZWjU9U __lambda_permissionactivity_descriptiontask_82dszbk9kv1yuvixtc2opzwju9u = new ToIntFunction() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$DescriptionTask$82DszBk9KV1YuvIxtc2opZWjU9U
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        return PermissionActivity.DescriptionTask.lambda$doInBackground$0((Uri) obj);
                    }
                };
                list.sort(new Comparator() { // from class: com.android.providers.media.-$$Lambda$PermissionActivity$DescriptionTask$byE4X0vuBRjPpFSGsgP-Gw_ItmI
                    @Override // java.util.Comparator
                    public final int compare(Object obj, Object obj2) {
                        return PermissionActivity.DescriptionTask.lambda$doInBackground$1(__lambda_permissionactivity_descriptiontask_82dszbk9kv1yuvixtc2opzwju9u, (Uri) obj, (Uri) obj2);
                    }
                });
            }
            Iterator<Uri> it = list.iterator();
            while (it.hasNext()) {
                try {
                    Description description = new Description(this.bodyView.getContext(), it.next(), i);
                    arrayList.add(description);
                    if (description.isVisual()) {
                        i2--;
                    }
                    if (i2 == 0) {
                        i = 0;
                    }
                } catch (Exception e) {
                    Log.w(Logging.TAG, e);
                }
            }
            return arrayList;
        }

        static /* synthetic */ int lambda$doInBackground$0(Uri uri) {
            int iMatchUri = new MediaProvider.LocalUriMatcher("media").matchUri(uri, false);
            if (iMatchUri == 2) {
                return 1;
            }
            if (iMatchUri != 101) {
                return iMatchUri != 201 ? 4 : 2;
            }
            return 3;
        }

        static /* synthetic */ int lambda$doInBackground$1(ToIntFunction toIntFunction, Uri uri, Uri uri2) {
            return toIntFunction.applyAsInt(uri) - toIntFunction.applyAsInt(uri2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(List<Description> list) {
            List<Description> list2 = (List) list.stream().filter(new Predicate() { // from class: com.android.providers.media.-$$Lambda$cc_OwQ5ucJ73virpuJyUmjb4GWc
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((PermissionActivity.Description) obj).isVisual();
                }
            }).collect(Collectors.toList());
            if (list.size() == 1 && list2.size() == 1) {
                bindAsFull(list.get(0));
            } else if (!list2.isEmpty()) {
                bindAsThumbs(list, list2);
            } else {
                bindAsText(list);
            }
            PermissionActivity.this.titleView.sendAccessibilityEvent(32768);
        }

        private void bindAsFull(Description description) {
            ImageView imageView = (ImageView) this.bodyView.requireViewById(R.id.thumb_full);
            if (description.full != null) {
                description.bindFull(imageView);
                return;
            }
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setBackground(new ColorDrawable(PermissionActivity.this.getColor(R.color.thumb_gray_color)));
            description.bindMimeIcon(imageView);
        }

        private void bindAsThumbs(List<Description> list, List<Description> list2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((ImageView) this.bodyView.requireViewById(R.id.thumb1));
            arrayList.add((ImageView) this.bodyView.requireViewById(R.id.thumb2));
            arrayList.add((ImageView) this.bodyView.requireViewById(R.id.thumb3));
            if (list2.size() != list.size() || list2.size() > 3) {
                View viewRequireViewById = this.bodyView.requireViewById(R.id.thumb_more_container);
                ImageView imageView = (ImageView) this.bodyView.requireViewById(R.id.thumb_more);
                TextView textView = (TextView) this.bodyView.requireViewById(R.id.thumb_more_text);
                View viewRequireViewById2 = this.bodyView.requireViewById(R.id.thumb_more_gradient);
                arrayList.remove(0);
                arrayList.add(imageView);
                int size = list.size() - Math.min(list2.size(), 2);
                textView.setText(TextUtils.expandTemplate(this.res.getQuantityText(R.plurals.permission_more_thumb, size), String.valueOf(size)));
                viewRequireViewById.setVisibility(0);
                viewRequireViewById2.setVisibility(0);
            }
            while (arrayList.size() > list2.size()) {
                arrayList.remove(0);
            }
            for (int i = 0; i < arrayList.size(); i++) {
                Description description = list2.get(i);
                ImageView imageView2 = (ImageView) arrayList.get(i);
                if (description.thumbnail != null) {
                    description.bindThumbnail(imageView2);
                } else {
                    description.bindMimeIcon(imageView2);
                }
            }
        }

        private void bindAsText(List<Description> list) {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                arrayList.add(list.get(i).contentDescription);
                if (arrayList.size() >= 3 && list.size() > arrayList.size()) {
                    int size = list.size() - arrayList.size();
                    arrayList.add(TextUtils.expandTemplate(this.res.getQuantityText(R.plurals.permission_more_text, size), String.valueOf(size)));
                    break;
                }
                i++;
            }
            TextView textView = (TextView) this.bodyView.requireViewById(R.id.list);
            textView.setText(TextUtils.join("\n", arrayList));
            textView.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class Description {
        public static final int LOAD_CONTENT_DESCRIPTION = 1;
        public static final int LOAD_FULL = 4;
        public static final int LOAD_THUMBNAIL = 2;
        public CharSequence contentDescription;
        public Bitmap full;
        public Icon mimeIcon;
        public Bitmap thumbnail;

        public Description(Context context, Uri uri, int i) {
            Resources resources = context.getResources();
            ContentResolver contentResolver = context.getContentResolver();
            if ((i & 1) != 0) {
                try {
                    Cursor cursorQuery = contentResolver.query(uri, new String[]{"_display_name"}, null, null);
                    try {
                        if (cursorQuery.moveToFirst()) {
                            this.contentDescription = cursorQuery.getString(0);
                        }
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                    } finally {
                    }
                } catch (IOException e) {
                    Log.w(Logging.TAG, e);
                    if (this.thumbnail == null && this.full == null) {
                        this.mimeIcon = contentResolver.getTypeInfo(contentResolver.getType(uri)).getIcon();
                        return;
                    }
                    return;
                }
            }
            if ((i & 2) != 0) {
                this.thumbnail = contentResolver.loadThumbnail(uri, new Size(resources.getDisplayMetrics().widthPixels, resources.getDisplayMetrics().widthPixels), null);
            }
            if ((i & 4) != 0) {
                if (ImageDecoder.isMimeTypeSupported(contentResolver.getType(uri))) {
                    this.full = ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri), new Resizer(context.getResources().getDisplayMetrics()));
                } else {
                    this.full = this.thumbnail;
                }
            }
        }

        public boolean isVisual() {
            return (this.thumbnail == null && this.full == null && this.mimeIcon == null) ? false : true;
        }

        public void bindThumbnail(ImageView imageView) {
            Objects.requireNonNull(this.thumbnail);
            imageView.setImageBitmap(this.thumbnail);
            imageView.setContentDescription(this.contentDescription);
            imageView.setVisibility(0);
            imageView.setClipToOutline(true);
        }

        public void bindFull(ImageView imageView) {
            Objects.requireNonNull(this.full);
            imageView.setImageBitmap(this.full);
            imageView.setContentDescription(this.contentDescription);
            imageView.setVisibility(0);
        }

        public void bindMimeIcon(ImageView imageView) {
            Objects.requireNonNull(this.mimeIcon);
            imageView.setImageIcon(this.mimeIcon);
            imageView.setContentDescription(this.contentDescription);
            imageView.setVisibility(0);
            imageView.setClipToOutline(true);
        }
    }

    private static class Resizer implements ImageDecoder.OnHeaderDecodedListener {
        private final int maxSize;

        public Resizer(DisplayMetrics displayMetrics) {
            this.maxSize = Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels);
        }

        @Override // android.graphics.ImageDecoder.OnHeaderDecodedListener
        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
            int iMax = Math.max(imageInfo.getSize().getWidth() / this.maxSize, imageInfo.getSize().getHeight() / this.maxSize);
            if (iMax > 1) {
                imageDecoder.setTargetSampleSize(iMax);
            }
        }
    }
}
