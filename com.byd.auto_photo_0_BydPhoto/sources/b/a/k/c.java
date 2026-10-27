package b.a.k;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.byd.auto_photo.R;
import com.byd.bean.AlbumItem;
import com.byd.fragment.AlbumBaseFragment;
import com.byd.util.j;
import com.byd.util.w;
import com.byd.util.x;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static volatile c f530a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Uri f531b = Uri.parse("db:///collect");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private List<AlbumItem> f532c;
    private List<AlbumItem> d;
    private Context g;
    private AlbumItem l;
    private List<AlbumItem> n;
    private String q;
    private C0019c r;
    private int s;
    private List<e> u;
    private com.byd.taskmanager.b.b<List<AlbumItem>> t = new b();
    private String h = Environment.DIRECTORY_DCIM + "/Camera";
    private HashMap<String, Integer> o = new HashMap<>();
    private AlbumItem i = new AlbumItem(R.drawable.icon_def_folder, "photos", MediaStore.Images.Media.EXTERNAL_CONTENT_URI, 1, 1);
    private AlbumItem j = new AlbumItem(R.drawable.icon_def_folder, "videos", MediaStore.Video.Media.EXTERNAL_CONTENT_URI, 1, 1);
    private AlbumItem k = new AlbumItem(R.drawable.icon_def_folder, "collects", f531b, 3, 1);
    private AlbumItem m = new AlbumItem(R.drawable.icon_new_folder, "new", -1, null, 0, 0);
    private AlbumItem p = new AlbumItem("more", 4);
    private List<AlbumItem> e = new LinkedList();
    private List<AlbumItem> f = new LinkedList();

    class a implements com.byd.taskmanager.b.b<Boolean> {
        a() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Boolean bool) {
            if (bool == null || bool.booleanValue()) {
                return;
            }
            Intent intent = new Intent(AlbumBaseFragment.ACTION_ALBUM_DATA_CHANGE);
            intent.putExtra("want_reload", true);
            LocalBroadcastManager.getInstance(c.this.g).sendBroadcast(intent);
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class b implements com.byd.taskmanager.b.b<List<AlbumItem>> {
        b() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, List<AlbumItem> list) {
            if (c.this.u != null) {
                Iterator it = c.this.u.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).onComplete();
                }
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onCanceled(String str) {
            if (c.this.u != null) {
                Iterator it = c.this.u.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).onCancel();
                }
            }
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
            if (c.this.u != null) {
                Iterator it = c.this.u.iterator();
                while (it.hasNext()) {
                    ((e) it.next()).onStart();
                }
            }
        }
    }

    /* JADX INFO: renamed from: b.a.k.c$c, reason: collision with other inner class name */
    private class C0019c extends com.byd.taskmanager.b.a<List<AlbumItem>> {
        public C0019c(com.byd.taskmanager.b.b<List<AlbumItem>> bVar) {
            super(bVar);
            this.name = "album_loader";
            this.submitInUIThread = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<AlbumItem> doTask() throws InterruptedException {
            long jCurrentTimeMillis = System.currentTimeMillis();
            w.c("===AlbumTask==doTask");
            c.this.o.clear();
            c.this.u();
            c.this.Q();
            w.c("albums : " + c.this.o.size() + " " + (System.currentTimeMillis() - jCurrentTimeMillis));
            List<String> listI = com.byd.util.f.k(c.this.g).i();
            if (!listI.isEmpty()) {
                for (String str : listI) {
                    File file = new File(str);
                    c.this.o.remove(str);
                    c.this.f.add(new AlbumItem(R.drawable.icon_def_folder, file.getName(), -1, Uri.parse(str), 2, 2));
                }
            }
            w.c("user albums : " + c.this.f.size() + " " + c.this.o.size() + " " + (System.currentTimeMillis() - jCurrentTimeMillis));
            Iterator it = c.this.o.entrySet().iterator();
            while (it.hasNext()) {
                File file2 = new File((String) ((Map.Entry) it.next()).getKey());
                if (file2.exists()) {
                    c.this.e.add(new AlbumItem(R.drawable.icon_def_folder, file2.getName(), -1, Uri.parse(file2.getPath()), 2, 3));
                }
            }
            w.c("album load finish ->" + c.this.e.size() + " " + (System.currentTimeMillis() - jCurrentTimeMillis));
            return null;
        }
    }

    private static class d extends com.byd.taskmanager.b.a<Boolean> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<AlbumItem> f536a;

        public d(com.byd.taskmanager.b.b<Boolean> bVar, List<AlbumItem> list) {
            super(bVar);
            this.name = "check_album_exist";
            this.submitInUIThread = true;
            this.f536a = list;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Boolean doTask() {
            w.c("-->checkAlbumExist<--");
            int size = this.f536a.size();
            for (int i = 0; i < size; i++) {
                AlbumItem albumItem = this.f536a.get(i);
                if (albumItem.getUri() != null && ((albumItem.getAlbumType() == 2 || albumItem.getAlbumType() == 3) && !new File(albumItem.getUri().getPath()).exists())) {
                    return Boolean.FALSE;
                }
            }
            return Boolean.TRUE;
        }
    }

    public interface e {
        void onCancel();

        void onComplete();

        void onStart();
    }

    private c(Context context) {
        this.g = context.getApplicationContext();
        this.q = com.byd.util.g.k(context);
    }

    private List<AlbumItem> B(boolean z) {
        StringBuilder sb;
        String str;
        List<AlbumItem> list;
        if (this.f532c == null) {
            this.f532c = new LinkedList();
        }
        if (z) {
            if (this.f532c.contains(this.p)) {
                this.f532c.remove(this.p);
            }
            if (this.f532c.size() == 0) {
                this.f532c.addAll(this.f);
                this.f532c.addAll(this.e);
                this.f532c.add(this.m);
            }
            int iIndexOf = this.f532c.indexOf(this.m);
            if (iIndexOf != this.f532c.size() - 1) {
                if (iIndexOf > -1) {
                    this.f532c.remove(iIndexOf);
                }
                this.f532c.add(this.m);
            }
            sb = new StringBuilder();
            str = "album in copy mode : ";
        } else {
            this.f532c.clear();
            this.f532c.add(this.m);
            List<AlbumItem> list2 = this.f;
            if ((list2 != null && list2.size() > 0) || ((list = this.e) != null && list.size() > 0)) {
                this.f532c.add(this.p);
            } else if (this.f532c.contains(this.p)) {
                this.f532c.remove(this.p);
            }
            this.f532c.addAll(this.f);
            this.f532c.addAll(this.e);
            sb = new StringBuilder();
            str = "album not in copy mode : ";
        }
        sb.append(str);
        sb.append(this.f532c.size());
        w.c(sb.toString());
        return this.f532c;
    }

    public static c D(Context context) {
        synchronized (c.class) {
            if (f530a == null) {
                f530a = new c(context);
            }
        }
        return f530a;
    }

    private void M(String str) {
        int size = this.f532c.size();
        for (int i = 0; i < size; i++) {
            AlbumItem albumItem = this.f532c.get(i);
            int albumType = albumItem.getAlbumType();
            if (albumType != 0 && albumType != 4 && albumItem.getUri().getPath().equals(str)) {
                this.f532c.remove(i);
                return;
            }
        }
    }

    private boolean O(List<AlbumItem> list, File file, File file2) {
        int size = list.size();
        String path = file.getPath();
        for (int i = 0; i < size; i++) {
            if (path.equals(list.get(i).getUri().getPath())) {
                AlbumItem albumItem = list.get(i);
                albumItem.setName(file2.getName());
                albumItem.setUri(Uri.parse(file2.getPath()));
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Q() throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        ContentResolver contentResolver = this.g.getContentResolver();
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        String[] strArr = {"_data"};
        List<String> listC = com.byd.util.j.e(this.g).c();
        z(contentResolver, uri, strArr, listC);
        z(contentResolver, MediaStore.Video.Media.EXTERNAL_CONTENT_URI, strArr, listC);
        w.c("load album finish : " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    private boolean j(List<AlbumItem> list, String str, int i) {
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (str.equals(list.get(i2).getUri().getPath())) {
                k(list.get(i2), str, i);
                return true;
            }
        }
        return false;
    }

    private void k(AlbumItem albumItem, String str, int i) {
        File[] fileArrListFiles;
        if (albumItem.getNum() > 0) {
            albumItem.setNum(albumItem.getNum() + i);
            return;
        }
        albumItem.setNum(i);
        if (albumItem.getSourceType() == 5 || albumItem.getSourceType() == 4 || (fileArrListFiles = new File(str).listFiles()) == null || fileArrListFiles.length <= 0) {
            return;
        }
        Arrays.sort(fileArrListFiles, com.byd.util.n.f2983a);
        albumItem.setCoverPath(fileArrListFiles[0].getPath());
    }

    private void o(AlbumItem albumItem, int i) {
        if (albumItem == null || this.d.contains(albumItem)) {
            return;
        }
        if (i <= this.d.size()) {
            this.d.add(i, albumItem);
            return;
        }
        w.c("checkExternalCard fail ,index : " + i + " size : " + this.d.size());
    }

    private void p() {
        int i;
        int i2;
        com.byd.util.j jVarE = com.byd.util.j.e(this.g);
        jVarE.r();
        List<j.a> listJ = jVarE.j();
        List<AlbumItem> list = this.n;
        if (list == null) {
            this.n = new LinkedList();
        } else {
            list.clear();
        }
        if (listJ == null || listJ.size() <= 0) {
            return;
        }
        for (j.a aVar : listJ) {
            if (aVar.e()) {
                i = R.drawable.icon_def_folder_sd;
                i2 = 4;
            } else {
                i = R.drawable.icon_def_folder_usb;
                i2 = 5;
            }
            this.n.add(new AlbumItem(i, aVar.c(), Uri.parse(aVar.b()), i2, 1));
        }
    }

    private void r() {
        File file = new File(this.q);
        File[] fileArrListFiles = file.listFiles();
        boolean z = false;
        if (fileArrListFiles != null && fileArrListFiles.length > 0) {
            for (File file2 : fileArrListFiles) {
                String strA = com.byd.util.m.a(file2.getName());
                if (com.byd.util.m.b(strA) || com.byd.util.m.d(strA)) {
                    this.l = new AlbumItem(R.drawable.icon_def_folder, file.getName(), -1, Uri.parse(this.q), 2, 3);
                    z = true;
                    break;
                }
            }
        }
        if (z) {
            return;
        }
        List<AlbumItem> list = this.d;
        if (list != null && list.contains(this.l)) {
            this.d.remove(this.l);
        }
        this.l = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u() {
        List<AlbumItem> list = this.e;
        if (list != null && list.size() > 0) {
            this.e.clear();
        }
        List<AlbumItem> list2 = this.f;
        if (list2 != null && list2.size() > 0) {
            this.f.clear();
        }
        List<AlbumItem> list3 = this.f532c;
        if (list3 == null || list3.size() <= 0) {
            return;
        }
        this.f532c.clear();
    }

    private void v() {
        List<AlbumItem> list = this.n;
        if (list != null) {
            Iterator<AlbumItem> it = list.iterator();
            while (it.hasNext()) {
                it.next().setNum(-1);
            }
        }
    }

    private boolean w(List<AlbumItem> list, List<String> list2) {
        boolean z;
        int i = 0;
        while (i < list.size()) {
            int size = list2.size();
            String path = list.get(i).getUri().getPath();
            int i2 = 0;
            while (true) {
                if (i2 >= size) {
                    z = false;
                    break;
                }
                if (path.equals(list2.get(i2))) {
                    list.remove(i);
                    M(path);
                    list2.remove(i2);
                    z = true;
                    break;
                }
                i2++;
            }
            if (list2.size() == 0) {
                break;
            }
            if (!z) {
                i++;
            }
        }
        return list2.size() == 0;
    }

    private void z(ContentResolver contentResolver, Uri uri, String[] strArr, List<String> list) throws InterruptedException {
        long jCurrentTimeMillis = System.currentTimeMillis();
        w.c("start " + uri);
        Cursor cursorQuery = contentResolver.query(uri, strArr, null, null, null);
        w.c("query end " + uri + " ,cost " + (System.currentTimeMillis() - jCurrentTimeMillis));
        if (cursorQuery != null) {
            while (cursorQuery.moveToNext()) {
                Thread.sleep(0L);
                boolean z = false;
                String string = cursorQuery.getString(0);
                String strSubstring = null;
                int iLastIndexOf = string.lastIndexOf(File.separator);
                if (iLastIndexOf > 0 && iLastIndexOf < string.length()) {
                    strSubstring = string.substring(0, iLastIndexOf);
                }
                if (strSubstring != null && !this.o.containsKey(strSubstring) && !strSubstring.contains(this.h) && !strSubstring.startsWith(this.q)) {
                    if (list != null) {
                        Iterator<String> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            } else if (strSubstring.startsWith(it.next())) {
                                z = true;
                                break;
                            }
                        }
                    }
                    if (!z && x.j(string) != null) {
                        this.o.put(strSubstring, -1);
                    }
                }
            }
            cursorQuery.close();
        }
        w.c("end " + uri + " ,cost " + (System.currentTimeMillis() - jCurrentTimeMillis));
    }

    public AlbumItem A(int i) {
        if (this.f532c == null || i < 0 || i > r0.size() - 1) {
            return null;
        }
        return this.f532c.get(i);
    }

    public List<AlbumItem> C(boolean z) {
        LinkedList linkedList = new LinkedList();
        linkedList.addAll(G(z));
        linkedList.addAll(B(z));
        return linkedList;
    }

    public AlbumItem E() {
        if (this.p == null) {
            this.p = new AlbumItem("more", 4);
        }
        return this.p;
    }

    public int F() {
        List<AlbumItem> list = this.d;
        if (list != null) {
            return list.size();
        }
        return 0;
    }

    public List<AlbumItem> G(boolean z) {
        if (this.d == null) {
            this.d = new LinkedList();
        }
        if (z) {
            this.s = 0;
            if (this.d.contains(this.i)) {
                this.d.remove(this.i);
                this.d.remove(this.j);
                this.d.remove(this.k);
            }
        } else {
            this.d.clear();
            this.d.add(this.i);
            this.d.add(this.j);
            this.d.add(this.k);
            this.s = 3;
        }
        r();
        AlbumItem albumItem = this.l;
        if (albumItem != null && !this.d.contains(albumItem)) {
            this.d.add(this.l);
        }
        p();
        List<AlbumItem> list = this.n;
        if (list != null && list.size() > 0) {
            Iterator<AlbumItem> it = this.n.iterator();
            while (it.hasNext()) {
                o(it.next(), this.s);
            }
        }
        return this.d;
    }

    public boolean H() {
        List<AlbumItem> list;
        List<AlbumItem> list2 = this.f532c;
        return (list2 != null && list2.size() > 0) || ((list = this.d) != null && list.size() > 0);
    }

    public boolean I() {
        String str;
        List<AlbumItem> list = this.f532c;
        if (list == null) {
            str = "albums is null in check hasMoreAlbumTips";
        } else {
            AlbumItem albumItem = this.p;
            if (albumItem != null) {
                return list.contains(albumItem);
            }
            str = "moreAlbumTips is null in check hasMoreAlbumTips";
        }
        w.c(str);
        return false;
    }

    public void J() {
        if (this.r == null) {
            this.r = new C0019c(this.t);
        }
        com.byd.taskmanager.a.e().d(this.r);
    }

    public void K(e eVar) {
        if (this.u == null) {
            this.u = new ArrayList(2);
        }
        if (this.u.contains(eVar)) {
            return;
        }
        this.u.add(eVar);
    }

    public void L() {
        if (this.r != null) {
            l();
            this.r = null;
        }
        List<AlbumItem> list = this.f532c;
        if (list != null) {
            list.clear();
        }
        List<AlbumItem> list2 = this.f;
        if (list2 != null) {
            list2.clear();
        }
        List<AlbumItem> list3 = this.e;
        if (list3 != null) {
            list3.clear();
        }
        List<AlbumItem> list4 = this.d;
        if (list4 != null) {
            list4.clear();
        }
        f530a = null;
    }

    public void N(File file, File file2) {
        if (O(this.f, file, file2)) {
            return;
        }
        O(this.e, file, file2);
    }

    public void P() {
        if (this.e.isEmpty() && this.f.isEmpty()) {
            return;
        }
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            this.e.get(i).setSelect(false);
        }
        int size2 = this.f.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.f.get(i2).setSelect(false);
        }
    }

    public void R(int i, String str) {
        List<AlbumItem> list = this.n;
        if (list != null) {
            Iterator<AlbumItem> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                AlbumItem next = it.next();
                if (str.equals(next.getUri().getPath())) {
                    this.n.remove(next);
                    this.d.remove(next);
                    break;
                }
            }
        }
        w.c("AlbumLoaderManager remove external : " + i + " | " + str);
    }

    public void S(e eVar) {
        List<e> list = this.u;
        if (list != null) {
            list.remove(eVar);
        }
    }

    public int h(File file, boolean z) {
        StringBuilder sb;
        String str;
        AlbumItem albumItem = new AlbumItem(R.drawable.icon_def_folder, file.getName(), -1, Uri.parse(file.getPath()), 2, 2);
        if (!z && this.f.size() == 0 && !this.f532c.contains(this.p)) {
            this.f532c.add(this.p);
            w.c("addAlbum -> add moreAlbumTips");
        }
        int size = 2;
        if (2 < this.f532c.size()) {
            this.f532c.add(2, albumItem);
            sb = new StringBuilder();
            str = "albums add item : ";
        } else {
            this.f532c.add(albumItem);
            size = this.f532c.size() - 1;
            sb = new StringBuilder();
            str = "albums add item to last : ";
        }
        sb.append(str);
        sb.append(size);
        w.c(sb.toString());
        if (this.f.size() == 0) {
            this.f.add(albumItem);
        } else {
            this.f.add(0, albumItem);
        }
        return size;
    }

    public void i(String str, int i) {
        if (i <= 0 || j(this.d, str, i) || j(this.f, str, i)) {
            return;
        }
        j(this.e, str, i);
    }

    public void l() {
        if (this.r == null || com.byd.taskmanager.a.e().f("album_loader") != 0) {
            return;
        }
        com.byd.taskmanager.a.e().a(this.r);
        w.c("cancel album loader");
    }

    public void m() {
        com.byd.taskmanager.a.e().d(new d(new a(), this.f532c));
    }

    public void n(List<String> list) {
        w(this.e, list);
        List<AlbumItem> list2 = this.f;
        if (list2 != null && list2.size() == 0 && this.e.size() == 0) {
            this.f532c.remove(this.p);
        }
    }

    public boolean q(Context context, String str) {
        boolean z = false;
        if (this.f532c != null && context != null && str != null) {
            w.c("checkIsNewAlbums:" + str);
            String parent = new File(str).getParent();
            if (parent == null) {
                return false;
            }
            if (parent.equals(this.q) && this.l == null) {
                int size = this.f532c.size();
                int i = 0;
                while (true) {
                    if (i < size) {
                        Uri uri = this.f532c.get(i).getUri();
                        if (uri != null && parent.equals(uri.getPath())) {
                            break;
                        }
                        i++;
                    } else {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.l = new AlbumItem(R.drawable.icon_def_folder, new File(this.q).getName(), -1, Uri.parse(this.q), 2, 3);
                    Log.w("m_tag", "create screenshot album!!!");
                }
            }
        }
        return z;
    }

    public void s() {
        w.c("AlbumLoaderManager clear");
        u();
        this.i.setNum(-1);
        this.j.setNum(-1);
        this.k.setNum(-1);
        AlbumItem albumItem = this.l;
        if (albumItem != null) {
            albumItem.setNum(-1);
        }
        v();
    }

    public void t() {
        w.c("clearAllFolderNumber");
        this.i.setNum(-1);
        this.j.setNum(-1);
        this.k.setNum(-1);
        AlbumItem albumItem = this.l;
        if (albumItem != null) {
            albumItem.setNum(-1);
        }
        v();
        if (this.e.isEmpty() && this.f.isEmpty()) {
            return;
        }
        int size = this.e.size();
        for (int i = 0; i < size; i++) {
            this.e.get(i).setNum(-1);
        }
        int size2 = this.f.size();
        for (int i2 = 0; i2 < size2; i2++) {
            this.f.get(i2).setNum(-1);
        }
    }

    public void x(List<String> list) {
        if (w(this.f, list)) {
            if (this.f.size() == 0 && this.e.size() == 0 && this.f532c.contains(this.p)) {
                this.f532c.remove(this.p);
                return;
            }
            return;
        }
        if (w(this.e, list) && this.f.size() == 0 && this.e.size() == 0 && this.f532c.contains(this.p)) {
            this.f532c.remove(this.p);
        }
    }

    public void y() {
        AlbumItem albumItem = this.l;
        if (albumItem != null) {
            if (this.d.contains(albumItem)) {
                this.d.remove(this.l);
            }
            this.l = null;
        }
    }
}
