package a.b.p;

import android.R;
import android.annotation.SuppressLint;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.SearchView;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedAPI"})
public class r0 extends a.i.a.c implements View.OnClickListener {
    public final SearchView l;
    public final SearchableInfo m;
    public final Context n;
    public final WeakHashMap<String, Drawable.ConstantState> o;
    public final int p;
    public boolean q;
    public int r;
    public ColorStateList s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int x;
    public int y;

    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final TextView f349a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final TextView f350b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final ImageView f351c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final ImageView f352d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public final ImageView f353e;

        public a(View view) {
            this.f349a = (TextView) view.findViewById(R.id.text1);
            this.f350b = (TextView) view.findViewById(R.id.text2);
            this.f351c = (ImageView) view.findViewById(R.id.icon1);
            this.f352d = (ImageView) view.findViewById(R.id.icon2);
            this.f353e = (ImageView) view.findViewById(a.b.f.edit_query);
        }
    }

    public r0(Context context, SearchView searchView, SearchableInfo searchableInfo, WeakHashMap<String, Drawable.ConstantState> weakHashMap) {
        super(context, searchView.getSuggestionRowLayout(), null, true);
        this.q = false;
        this.r = 1;
        this.t = -1;
        this.u = -1;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.l = searchView;
        this.m = searchableInfo;
        this.p = searchView.getSuggestionCommitIconResId();
        this.n = context;
        this.o = weakHashMap;
    }

    public static String h(Cursor cursor, int i) {
        if (i == -1) {
            return null;
        }
        try {
            return cursor.getString(i);
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", e2);
            return null;
        }
    }

    @Override // a.i.a.a
    public void a(View view, Context context, Cursor cursor) throws FileNotFoundException {
        Drawable drawableF;
        String string;
        Drawable drawable;
        ActivityInfo activityInfo;
        int iconResource;
        CharSequence charSequenceH;
        a aVar = (a) view.getTag();
        int i = this.y;
        int i2 = i != -1 ? cursor.getInt(i) : 0;
        if (aVar.f349a != null) {
            String strH = h(cursor, this.t);
            TextView textView = aVar.f349a;
            textView.setText(strH);
            textView.setVisibility(TextUtils.isEmpty(strH) ? 8 : 0);
        }
        if (aVar.f350b != null) {
            String strH2 = h(cursor, this.v);
            if (strH2 != null) {
                if (this.s == null) {
                    TypedValue typedValue = new TypedValue();
                    this.f885d.getTheme().resolveAttribute(a.b.a.textColorSearchUrl, typedValue, true);
                    this.s = this.f885d.getResources().getColorStateList(typedValue.resourceId);
                }
                SpannableString spannableString = new SpannableString(strH2);
                spannableString.setSpan(new TextAppearanceSpan(null, 0, 0, this.s, null), 0, strH2.length(), 33);
                charSequenceH = spannableString;
            } else {
                charSequenceH = h(cursor, this.u);
            }
            if (TextUtils.isEmpty(charSequenceH)) {
                TextView textView2 = aVar.f349a;
                if (textView2 != null) {
                    textView2.setSingleLine(false);
                    aVar.f349a.setMaxLines(2);
                }
            } else {
                TextView textView3 = aVar.f349a;
                if (textView3 != null) {
                    textView3.setSingleLine(true);
                    aVar.f349a.setMaxLines(1);
                }
            }
            TextView textView4 = aVar.f350b;
            textView4.setText(charSequenceH);
            textView4.setVisibility(TextUtils.isEmpty(charSequenceH) ? 8 : 0);
        }
        ImageView imageView = aVar.f351c;
        if (imageView != null) {
            int i3 = this.w;
            if (i3 == -1) {
                drawableF = null;
            } else {
                drawableF = f(cursor.getString(i3));
                if (drawableF == null) {
                    ComponentName searchActivity = this.m.getSearchActivity();
                    String strFlattenToShortString = searchActivity.flattenToShortString();
                    if (this.o.containsKey(strFlattenToShortString)) {
                        Drawable.ConstantState constantState = this.o.get(strFlattenToShortString);
                        drawableF = constantState == null ? null : constantState.newDrawable(this.n.getResources());
                    } else {
                        PackageManager packageManager = this.f885d.getPackageManager();
                        try {
                            activityInfo = packageManager.getActivityInfo(searchActivity, 128);
                            iconResource = activityInfo.getIconResource();
                        } catch (PackageManager.NameNotFoundException e2) {
                            string = e2.toString();
                        }
                        if (iconResource != 0) {
                            drawable = packageManager.getDrawable(searchActivity.getPackageName(), iconResource, activityInfo.applicationInfo);
                            if (drawable == null) {
                                string = "Invalid icon resource " + iconResource + " for " + searchActivity.flattenToShortString();
                                Log.w("SuggestionsAdapter", string);
                                drawable = null;
                            }
                        } else {
                            drawable = null;
                        }
                        this.o.put(strFlattenToShortString, drawable == null ? null : drawable.getConstantState());
                        drawableF = drawable;
                    }
                    if (drawableF == null) {
                        drawableF = this.f885d.getPackageManager().getDefaultActivityIcon();
                    }
                }
            }
            imageView.setImageDrawable(drawableF);
            if (drawableF == null) {
                imageView.setVisibility(4);
            } else {
                imageView.setVisibility(0);
                drawableF.setVisible(false, false);
                drawableF.setVisible(true, false);
            }
        }
        ImageView imageView2 = aVar.f352d;
        if (imageView2 != null) {
            int i4 = this.x;
            Drawable drawableF2 = i4 != -1 ? f(cursor.getString(i4)) : null;
            imageView2.setImageDrawable(drawableF2);
            if (drawableF2 == null) {
                imageView2.setVisibility(8);
            } else {
                imageView2.setVisibility(0);
                drawableF2.setVisible(false, false);
                drawableF2.setVisible(true, false);
            }
        }
        int i5 = this.r;
        if (i5 != 2 && (i5 != 1 || (i2 & 1) == 0)) {
            aVar.f353e.setVisibility(8);
            return;
        }
        aVar.f353e.setVisibility(0);
        aVar.f353e.setTag(aVar.f349a.getText());
        aVar.f353e.setOnClickListener(this);
    }

    @Override // a.i.a.a
    public void b(Cursor cursor) {
        if (this.q) {
            Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
            if (cursor != null) {
                cursor.close();
                return;
            }
            return;
        }
        try {
            super.b(cursor);
            if (cursor != null) {
                this.t = cursor.getColumnIndex("suggest_text_1");
                this.u = cursor.getColumnIndex("suggest_text_2");
                this.v = cursor.getColumnIndex("suggest_text_2_url");
                this.w = cursor.getColumnIndex("suggest_icon_1");
                this.x = cursor.getColumnIndex("suggest_icon_2");
                this.y = cursor.getColumnIndex("suggest_flags");
            }
        } catch (Exception e2) {
            Log.e("SuggestionsAdapter", "error changing cursor and caching columns", e2);
        }
    }

    @Override // a.i.a.a
    public CharSequence c(Cursor cursor) {
        String strH;
        String strH2;
        if (cursor == null) {
            return null;
        }
        String strH3 = h(cursor, cursor.getColumnIndex("suggest_intent_query"));
        if (strH3 != null) {
            return strH3;
        }
        if (this.m.shouldRewriteQueryFromData() && (strH2 = h(cursor, cursor.getColumnIndex("suggest_intent_data"))) != null) {
            return strH2;
        }
        if (!this.m.shouldRewriteQueryFromText() || (strH = h(cursor, cursor.getColumnIndex("suggest_text_1"))) == null) {
            return null;
        }
        return strH;
    }

    @Override // a.i.a.c, a.i.a.a
    public View d(Context context, Cursor cursor, ViewGroup viewGroup) {
        View viewInflate = this.k.inflate(this.i, viewGroup, false);
        viewInflate.setTag(new a(viewInflate));
        ((ImageView) viewInflate.findViewById(a.b.f.edit_query)).setImageResource(this.p);
        return viewInflate;
    }

    public Drawable e(Uri uri) throws FileNotFoundException {
        int identifier;
        String authority = uri.getAuthority();
        if (TextUtils.isEmpty(authority)) {
            throw new FileNotFoundException("No authority: " + uri);
        }
        try {
            Resources resourcesForApplication = this.f885d.getPackageManager().getResourcesForApplication(authority);
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                throw new FileNotFoundException("No path: " + uri);
            }
            int size = pathSegments.size();
            if (size == 1) {
                try {
                    identifier = Integer.parseInt(pathSegments.get(0));
                } catch (NumberFormatException unused) {
                    throw new FileNotFoundException("Single path segment is not a resource ID: " + uri);
                }
            } else {
                if (size != 2) {
                    throw new FileNotFoundException("More than two path segments: " + uri);
                }
                identifier = resourcesForApplication.getIdentifier(pathSegments.get(1), pathSegments.get(0), authority);
            }
            if (identifier != 0) {
                return resourcesForApplication.getDrawable(identifier);
            }
            throw new FileNotFoundException("No resource found for: " + uri);
        } catch (PackageManager.NameNotFoundException unused2) {
            throw new FileNotFoundException("No package found for authority: " + uri);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0131  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.drawable.Drawable f(java.lang.String r8) throws java.io.FileNotFoundException {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: a.b.p.r0.f(java.lang.String):android.graphics.drawable.Drawable");
    }

    public Cursor g(SearchableInfo searchableInfo, String str, int i) {
        String suggestAuthority;
        String[] strArr = null;
        if (searchableInfo == null || (suggestAuthority = searchableInfo.getSuggestAuthority()) == null) {
            return null;
        }
        Uri.Builder builderFragment = new Uri.Builder().scheme("content").authority(suggestAuthority).query("").fragment("");
        String suggestPath = searchableInfo.getSuggestPath();
        if (suggestPath != null) {
            builderFragment.appendEncodedPath(suggestPath);
        }
        builderFragment.appendPath("search_suggest_query");
        String suggestSelection = searchableInfo.getSuggestSelection();
        if (suggestSelection != null) {
            strArr = new String[]{str};
        } else {
            builderFragment.appendPath(str);
        }
        String[] strArr2 = strArr;
        if (i > 0) {
            builderFragment.appendQueryParameter("limit", String.valueOf(i));
        }
        return this.f885d.getContentResolver().query(builderFragment.build(), null, suggestSelection, strArr2, null);
    }

    @Override // a.i.a.a, android.widget.BaseAdapter, android.widget.SpinnerAdapter
    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getDropDownView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewInflate = this.k.inflate(this.j, viewGroup, false);
            if (viewInflate != null) {
                ((a) viewInflate.getTag()).f349a.setText(e2.toString());
            }
            return viewInflate;
        }
    }

    @Override // a.i.a.a, android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        try {
            return super.getView(i, view, viewGroup);
        } catch (RuntimeException e2) {
            Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", e2);
            View viewD = this.d(this.f885d, this.f884c, viewGroup);
            ((a) viewD.getTag()).f349a.setText(e2.toString());
            return viewD;
        }
    }

    @Override // android.widget.BaseAdapter, android.widget.Adapter
    public boolean hasStableIds() {
        return false;
    }

    public final void i(Cursor cursor) {
        Bundle extras = cursor != null ? cursor.getExtras() : null;
        if (extras == null || extras.getBoolean("in_progress")) {
        }
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        i(this.f884c);
    }

    @Override // android.widget.BaseAdapter
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
        i(this.f884c);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag instanceof CharSequence) {
            this.l.x((CharSequence) tag);
        }
    }
}
