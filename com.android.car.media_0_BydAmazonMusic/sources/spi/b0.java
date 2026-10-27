package spi;

import android.app.Application;
import android.car.utils.Logs;
import android.support.v4.media.MediaBrowserCompat;
import android.support.v4.media.session.MediaControllerCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import androidx.lifecycle.Observer;
import com.alibaba.android.arouter.utils.Consts;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.MinimizedPlaybackControlBar;
import com.android.car.media.common.PlaybackFragment;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.common.source.MediaTrampolineHelper;
import com.android.car.media.common.source.a;
import com.android.car.media.d;
import com.android.car.media.main.R$string;
import com.android.car.ui.toolbar.TabLayout;
import com.android.car.ui.toolbar.g;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/* JADX INFO: compiled from: R8$$SyntheticClass */
/* JADX INFO: loaded from: classes2.dex */
public final /* synthetic */ class b0 implements Observer {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ b0(Object obj, int i) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Type inference failed for: r12v33, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    /* JADX WARN: Type inference failed for: r12v35, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    /* JADX WARN: Type inference failed for: r13v62, types: [java.util.HashMap, java.util.Map<java.lang.String, com.android.car.media.common.browse.MediaItemsRepository$d>] */
    @Override // androidx.lifecycle.Observer
    public final void onChanged(Object obj) {
        boolean z;
        Collection collectionEmptyList;
        boolean z2 = false;
        switch (this.a) {
            case 0:
                final com.android.car.media.a aVar = (com.android.car.media.a) this.b;
                d4 d4Var = (d4) obj;
                Objects.requireNonNull(aVar);
                Logs.d("BrowseViewController", "onItemsUpdate enter");
                if (d4Var == null || (z = d4Var.a)) {
                    uc.b(aVar.i, 0, null);
                    uc.b(aVar.j, 0, null);
                    aVar.k.setRotate(false);
                    uc.b(aVar.k, 0, null);
                    aVar.h.setAlpha(0.0f);
                    uc.b((View) aVar.h, 0, null);
                    aVar.m.o(null, null);
                    if (d4Var != null) {
                        if (aVar.s.hasCallbacks(aVar.x)) {
                            Logs.d("BrowseViewController", "mHandler.hasCallbacks(mLoadingIndicatorRunnable) go to remove");
                            aVar.s.removeCallbacks(aVar.x);
                        }
                        aVar.s.postDelayed(aVar.x, aVar.o);
                    }
                    aVar.j(8);
                    Logs.d("BrowseViewController", "onItemsUpdate no data ,return");
                } else {
                    T t = d4Var.c;
                    if (t != 0 || z) {
                        if (((List) t).size() == 0) {
                            aVar.j(8);
                            Logs.d("BrowseViewController", "onItemsUpdate no data ,return 3");
                        }
                        aVar.k();
                        Logs.d("BrowseViewController", "onItemsUpdate mDisplayMediaItems:" + aVar.e);
                        final List<MediaItemMetadata> listA = u6.a(aVar.e ^ true, (List) d4Var.c);
                        for (MediaItemMetadata mediaItemMetadata : listA) {
                            StringBuilder sbK = c.k("onItemsUpdate item:");
                            sbK.append(mediaItemMetadata.toString());
                            sbK.append(",type:");
                            sbK.append(mediaItemMetadata.g());
                            Logs.d("BrowseViewController", sbK.toString());
                        }
                        if (aVar.e) {
                            if (!aVar.q) {
                                aVar.m.o(aVar.c, listA);
                            } else if (listA.size() > 0) {
                                if (listA.size() != 0) {
                                    List list = (List) listA.stream().filter(new f0(new ConcurrentHashMap(), d0.b, 0)).collect(Collectors.toList());
                                    final ArrayList arrayList = new ArrayList();
                                    list.forEach(new Consumer() { // from class: spi.c0
                                        @Override // java.util.function.Consumer
                                        public final void accept(Object obj2) {
                                            com.android.car.media.a aVar2 = aVar;
                                            List list2 = arrayList;
                                            List list3 = listA;
                                            MediaItemMetadata mediaItemMetadata2 = (MediaItemMetadata) obj2;
                                            Objects.requireNonNull(aVar2);
                                            g.b bVarA = com.android.car.ui.toolbar.g.a();
                                            bVarA.a = mediaItemMetadata2.g();
                                            bVarA.c = new com.android.car.media.b(aVar2, mediaItemMetadata2, list3, list2);
                                            list2.add(bVarA.a());
                                        }
                                    });
                                    if (TextUtils.isEmpty(aVar.r)) {
                                        int i = aVar.t.c().h;
                                        if (i >= arrayList.size()) {
                                            aVar.t.g(0);
                                            i = 0;
                                        }
                                        Logs.d("BrowseViewController", "update tab goto index:" + i);
                                        aVar.l.c(arrayList, i);
                                        aVar.l.post(new i0(aVar, i));
                                        aVar.l.setActivated(false);
                                    } else {
                                        int iA = aVar.a((List) arrayList.stream().map(new h0(aVar)).collect(Collectors.toList()), aVar.r, true);
                                        aVar.l.c(arrayList, iA);
                                        aVar.l.setActivated(false);
                                        aVar.r = "";
                                        aVar.t.g(iA);
                                    }
                                }
                                int selectedTab = aVar.l.getSelectedTab();
                                Logs.d("BrowseViewController", "onItemsUpdate currentIndex:" + selectedTab);
                                if (selectedTab >= aVar.l.getTabs().size()) {
                                    aVar.t.g(0);
                                    TabLayout tabLayout = aVar.l;
                                    tabLayout.c(tabLayout.getTabs(), 0);
                                    selectedTab = 0;
                                }
                                Logs.d("BrowseViewController", "onItemsUpdate currentIndex end:" + selectedTab);
                                aVar.m.o(aVar.c, aVar.d(listA, aVar.l.getTabs().get(selectedTab).a));
                            } else {
                                aVar.l.c(null, 0);
                                aVar.l.c(null, 0);
                                aVar.t.g(0);
                            }
                            StringBuilder sbK2 = c.k("onItemsUpdate submitItems mParentItem:");
                            sbK2.append(aVar.c);
                            Logs.d("BrowseViewController", sbK2.toString());
                            List list2 = (List) d4Var.b;
                            List list3 = list2 == null ? null : (List) list2.stream().filter(n7.d).collect(Collectors.toList());
                            if (list3 == null || list3.isEmpty()) {
                                collectionEmptyList = Collections.emptyList();
                            } else if (listA.isEmpty()) {
                                collectionEmptyList = new ArrayList(list3);
                            } else {
                                HashSet hashSet = new HashSet(list3);
                                hashSet.removeAll(listA);
                                collectionEmptyList = hashSet;
                            }
                            if (!collectionEmptyList.isEmpty()) {
                                d.e eVar = (d.e) aVar.a;
                                if (com.android.car.media.d.this.s.contains(aVar.c)) {
                                    Iterator it = collectionEmptyList.iterator();
                                    while (true) {
                                        if (it.hasNext()) {
                                            int iIndexOf = com.android.car.media.d.this.s.indexOf((MediaItemMetadata) it.next());
                                            if (iIndexOf >= 0) {
                                                com.android.car.media.d dVar = com.android.car.media.d.this;
                                                Stack<MediaItemMetadata> stack = dVar.s;
                                                dVar.e(stack.subList(iIndexOf, stack.size()));
                                                if (!com.android.car.media.d.this.u.f()) {
                                                    com.android.car.media.d.this.m(true);
                                                    com.android.car.media.d.this.q();
                                                }
                                            }
                                        }
                                    }
                                }
                                Logs.d("BrowseViewController", "onItemsUpdate submitItems onChildrenNodesRemoved:" + collectionEmptyList);
                            }
                        }
                        int i2 = aVar.n;
                        if (listA.isEmpty()) {
                            aVar.j.setText(R$string.nothing_to_play);
                            uc.b(aVar.h.getView(), i2, null);
                            uc.b(aVar.i, i2, null);
                            uc.g(aVar.j, i2, null);
                        } else {
                            uc.g(aVar.h.getView(), i2, null);
                            uc.b(aVar.i, i2, null);
                            uc.b(aVar.j, i2, null);
                            aVar.k.setRotate(false);
                            uc.b(aVar.k, 0, null);
                        }
                        if (!aVar.r.isEmpty()) {
                            Logs.d("BrowseViewController", "updateUIWhenVoiceSearch hideKeyboard and clear voiceCmd filter");
                            ((InputMethodManager) aVar.c().getSystemService("input_method")).hideSoftInputFromWindow(aVar.g.getWindowToken(), 0);
                            aVar.r = "";
                        }
                        aVar.j(0);
                        StringBuilder sb = new StringBuilder();
                        sb.append("onItemsUpdate ");
                        StringBuilder sbK3 = c.k("[");
                        MediaItemMetadata mediaItemMetadata2 = aVar.c;
                        sbK3.append(mediaItemMetadata2 != null ? mediaItemMetadata2.f() : Consts.SUFFIX_ROOT);
                        sbK3.append("]");
                        d4<List<MediaItemMetadata>> value = aVar.d.getValue();
                        if (value == null) {
                            sbK3.append(" null future data");
                        } else if (value.a) {
                            sbK3.append(" loading");
                        } else {
                            List<MediaItemMetadata> list4 = value.c;
                            if (list4 == null) {
                                sbK3.append(" null list");
                            } else {
                                List<MediaItemMetadata> list5 = list4;
                                sbK3.append(" ");
                                sbK3.append(list5.size());
                                sbK3.append(" {");
                                if (list5.size() > 0) {
                                    CharSequence charSequenceF = list5.get(0).f();
                                    sbK3.append(charSequenceF == null ? "" : charSequenceF.toString());
                                }
                                if (list5.size() > 1) {
                                    sbK3.append(", ");
                                    CharSequence charSequenceF2 = list5.get(1).f();
                                    sbK3.append(charSequenceF2 != null ? charSequenceF2.toString() : "");
                                }
                                if (list5.size() > 2) {
                                    sbK3.append(", ...");
                                }
                                sbK3.append(" }");
                            }
                        }
                        sb.append(sbK3.toString());
                        Logs.v("BrowseViewController", sb.toString());
                    } else {
                        aVar.k();
                        uc.b(aVar.i, 0, null);
                        uc.b(aVar.j, 0, null);
                        aVar.k.setRotate(false);
                        uc.b(aVar.k, 0, null);
                        aVar.h.setAlpha(0.0f);
                        uc.b((View) aVar.h, 0, null);
                        aVar.m.o(null, null);
                        aVar.j(8);
                        Logs.d("BrowseViewController", "onItemsUpdate no data ,return 2");
                    }
                }
                break;
            case 1:
                ProgressBar progressBar = (ProgressBar) this.b;
                u8 u8Var = (u8) obj;
                progressBar.setMax((int) u8Var.b);
                progressBar.setProgress((int) u8Var.a);
                break;
            case 2:
                MinimizedPlaybackControlBar minimizedPlaybackControlBar = (MinimizedPlaybackControlBar) this.b;
                MediaItemMetadata mediaItemMetadata3 = (MediaItemMetadata) obj;
                minimizedPlaybackControlBar.j.d(minimizedPlaybackControlBar.getContext(), mediaItemMetadata3 != null ? mediaItemMetadata3.g : null);
                break;
            case 3:
                PlaybackFragment.ViewModel viewModel = (PlaybackFragment.ViewModel) this.b;
                d4 d4Var2 = (d4) obj;
                Objects.requireNonNull(viewModel);
                if (d4Var2.a) {
                    viewModel.f.setValue(null);
                } else {
                    List<MediaItemMetadata> listA2 = u6.a(true, (List) d4Var2.c);
                    if (listA2 != null && !listA2.isEmpty()) {
                        z2 = true;
                    }
                    viewModel.f.setValue(Boolean.valueOf(z2));
                }
                break;
            case 4:
                MediaItemsRepository mediaItemsRepository = (MediaItemsRepository) this.b;
                a.c cVar = (a.c) obj;
                mediaItemsRepository.a = cVar;
                if (cVar == null) {
                    Logs.e("MediaItemsRepository", "Null browsing state (no media source!)");
                } else {
                    StringBuilder sbK4 = c.k("onMediaBrowsingStateChanged mConnectionStatus:");
                    sbK4.append(mediaItemsRepository.a.c);
                    Logs.w("MediaItemsRepository", sbK4.toString());
                    mediaItemsRepository.c.setValue(mediaItemsRepository.a);
                    int i3 = MediaItemsRepository.c.a[mediaItemsRepository.a.c.ordinal()];
                    if (i3 == 1) {
                        MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData = mediaItemsRepository.d;
                        int i4 = MediaItemsRepository.MediaItemsLiveData.a;
                        Objects.requireNonNull(mediaItemsLiveData);
                        mediaItemsLiveData.setValue(new d4(true, null));
                    } else if (i3 == 2) {
                        String root = mediaItemsRepository.a.b.getRoot();
                        mediaItemsRepository.b().a = root;
                        mediaItemsRepository.c(root);
                    } else if (i3 == 3) {
                        Iterator it2 = mediaItemsRepository.b().b.keySet().iterator();
                        while (it2.hasNext()) {
                            mediaItemsRepository.a.b.unsubscribe((String) it2.next());
                        }
                        MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData2 = mediaItemsRepository.e;
                        int i5 = MediaItemsRepository.MediaItemsLiveData.a;
                        mediaItemsLiveData2.setValue(null);
                        mediaItemsRepository.b().b.clear();
                    } else if (i3 == 4 || i3 == 5) {
                        mediaItemsRepository.d(mediaItemsRepository.b().a, null);
                        MediaItemsRepository.MediaItemsLiveData mediaItemsLiveData3 = mediaItemsRepository.e;
                        int i6 = MediaItemsRepository.MediaItemsLiveData.a;
                        mediaItemsLiveData3.setValue(null);
                        mediaItemsRepository.b().b.clear();
                    }
                }
                break;
            case 5:
                PlaybackViewModel.c cVar2 = (PlaybackViewModel.c) this.b;
                a.c cVar3 = (a.c) obj;
                Objects.requireNonNull(cVar2);
                if (cVar3 == null) {
                    Logs.e("PlaybackViewModel", "newBrowsingState is null.");
                } else if (Objects.equals(cVar2.a, cVar3)) {
                    Log.w("PlaybackViewModel", "onMediaBrowsingStateChanged noop ");
                } else {
                    StringBuilder sbK5 = c.k("onMediaBrowsingStateChanged connectionStatus:");
                    sbK5.append(cVar3.c);
                    Logs.w("PlaybackViewModel", sbK5.toString());
                    if (cVar2.b != null) {
                        int i7 = PlaybackViewModel.a.a[cVar3.c.ordinal()];
                        if (i7 == 1 || i7 == 2 || i7 == 3 || i7 == 4) {
                            cVar2.b.unregisterCallback(cVar2);
                            cVar2.a(null);
                        } else if (i7 == 5) {
                            cVar2.a(null);
                        }
                    }
                    cVar2.a = cVar3;
                    if (cVar3.c == a.e.CONNECTED) {
                        PlaybackViewModel.b bVar = PlaybackViewModel.this.m;
                        MediaBrowserCompat mediaBrowserCompat = cVar3.b;
                        Application application = (Application) ((q6) bVar).a;
                        PlaybackViewModel[] playbackViewModelArr = PlaybackViewModel.n;
                        cVar2.a(new MediaControllerCompat(application, mediaBrowserCompat.getSessionToken()));
                    }
                }
                break;
            default:
                MediaTrampolineHelper mediaTrampolineHelper = (MediaTrampolineHelper) this.b;
                b7 b7Var = (b7) obj;
                if (mediaTrampolineHelper.d) {
                    Log.i("MediaTrampolineHlp", "New media source while resumed: " + b7Var);
                    mediaTrampolineHelper.a(b7Var);
                }
                break;
        }
    }
}
