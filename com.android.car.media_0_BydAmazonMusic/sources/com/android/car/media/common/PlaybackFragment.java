package com.android.car.media.common;

import android.app.Application;
import android.car.XCar;
import android.car.content.pm.XCarPackageManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.car.apps.common.CrossfadeImageView;
import com.android.car.media.common.MediaItemMetadata;
import com.android.car.media.common.PlaybackFragment;
import com.android.car.media.common.browse.MediaItemsRepository;
import com.android.car.media.common.playback.PlaybackViewModel;
import com.android.car.media.common.source.MediaSourceViewModel;
import com.android.car.ui.R$id;
import java.util.Objects;
import spi.b0;
import spi.b7;
import spi.c3;
import spi.e6;
import spi.i7;
import spi.i8;
import spi.j8;
import spi.k7;
import spi.r8;
import spi.t8;
import spi.v4;

/* JADX INFO: loaded from: classes.dex */
public class PlaybackFragment extends Fragment {
    public static final /* synthetic */ int j = 0;
    public XCar a;
    public XCarPackageManager b;
    public MediaSourceViewModel c;
    public PlaybackViewModel d;
    public v4<MediaItemMetadata.b> e;
    public ViewModel f;
    public i8 g;
    public a h;
    public boolean i;

    public static class ViewModel extends AndroidViewModel {
        public LiveData<b7> a;
        public LiveData<CharSequence> b;
        public LiveData<Bitmap> c;
        public LiveData<CharSequence> d;
        public LiveData<CharSequence> e;
        public MutableLiveData<Boolean> f;
        public MediaItemsRepository g;
        public PlaybackViewModel h;
        public MediaSourceViewModel i;

        public ViewModel(Application application) {
            super(application);
            this.f = new MutableLiveData<>();
        }
    }

    public class a extends j8 {
        public a(Context context) {
            super(context);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0033  */
        /* JADX WARN: Removed duplicated region for block: B:25:0x0069  */
        @Override // spi.j8
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void b(java.lang.String r6, android.app.PendingIntent r7, java.lang.String r8, int r9) {
            /*
                r5 = this;
                com.android.car.media.common.PlaybackFragment r9 = com.android.car.media.common.PlaybackFragment.this
                r0 = 0
                r9.i = r0
                boolean r9 = android.text.TextUtils.isEmpty(r6)
                r1 = 0
                if (r9 != 0) goto L74
                com.android.car.media.common.PlaybackFragment r9 = com.android.car.media.common.PlaybackFragment.this
                com.android.car.media.common.PlaybackFragment$ViewModel r9 = r9.f
                androidx.lifecycle.MutableLiveData<java.lang.Boolean> r9 = r9.f
                java.lang.Object r9 = r9.getValue()
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                if (r9 == 0) goto L74
                boolean r9 = r9.booleanValue()
                if (r9 != 0) goto L74
                r9 = 1
                if (r7 == 0) goto L33
                com.android.car.media.common.PlaybackFragment r2 = com.android.car.media.common.PlaybackFragment.this
                android.car.content.pm.XCarPackageManager r2 = r2.b
                if (r2 == 0) goto L2e
                boolean r2 = r2.isPendingIntentDistractionOptimized(r7)
                goto L2f
            L2e:
                r2 = r0
            L2f:
                if (r2 == 0) goto L33
                r2 = r9
                goto L34
            L33:
                r2 = r0
            L34:
                com.android.car.media.common.PlaybackFragment r3 = com.android.car.media.common.PlaybackFragment.this
                spi.i8 r3 = r3.g
                com.android.car.apps.common.UxrTextView r4 = r3.a
                r4.setText(r6)
                com.android.car.apps.common.UxrTextView r6 = r3.a
                int r4 = r3.c
                spi.uc.g(r6, r4, r1)
                if (r8 == 0) goto L69
                if (r7 == 0) goto L69
                com.android.car.apps.common.UxrButton r6 = r3.b
                r6.setText(r8)
                com.android.car.apps.common.UxrButton r6 = r3.b
                if (r2 == 0) goto L52
                goto L54
            L52:
                r0 = 64
            L54:
                r6.setUxRestrictions(r0)
                com.android.car.apps.common.UxrButton r6 = r3.b
                spi.u0 r8 = new spi.u0
                r8.<init>(r7, r9)
                r6.setOnClickListener(r8)
                com.android.car.apps.common.UxrButton r6 = r3.b
                int r7 = r3.c
                spi.uc.g(r6, r7, r1)
                goto L70
            L69:
                com.android.car.apps.common.UxrButton r6 = r3.b
                int r7 = r3.c
                spi.uc.b(r6, r7, r1)
            L70:
                com.android.car.media.common.PlaybackFragment r6 = com.android.car.media.common.PlaybackFragment.this
                r6.i = r9
            L74:
                com.android.car.media.common.PlaybackFragment r5 = com.android.car.media.common.PlaybackFragment.this
                boolean r6 = r5.i
                if (r6 != 0) goto L8a
                spi.i8 r5 = r5.g
                com.android.car.apps.common.UxrTextView r6 = r5.a
                int r7 = r5.c
                spi.uc.b(r6, r7, r1)
                com.android.car.apps.common.UxrButton r6 = r5.b
                int r5 = r5.c
                spi.uc.b(r6, r5, r1)
            L8a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.car.media.common.PlaybackFragment.a.b(java.lang.String, android.app.PendingIntent, java.lang.String, int):void");
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public final View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, Bundle bundle) {
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        XCar xCarCreateCar = XCar.createCar(fragmentActivityRequireActivity);
        this.a = xCarCreateCar;
        this.b = (XCarPackageManager) xCarCreateCar.getCarManager(XCar.PACKAGE_SERVICE);
        Application application = fragmentActivityRequireActivity.getApplication();
        final int i = 0;
        this.d = PlaybackViewModel.a(application, 0);
        this.c = MediaSourceViewModel.a(application, 0);
        getContext();
        ViewModel viewModel = (ViewModel) ViewModelProviders.of(fragmentActivityRequireActivity).get(ViewModel.class);
        this.f = viewModel;
        MediaSourceViewModel mediaSourceViewModel = this.c;
        PlaybackViewModel playbackViewModel = this.d;
        MediaItemsRepository mediaItemsRepositoryA = MediaItemsRepository.a(application, 0);
        if (viewModel.i != mediaSourceViewModel || viewModel.h != playbackViewModel || viewModel.g != mediaItemsRepositoryA) {
            viewModel.h = playbackViewModel;
            viewModel.i = mediaSourceViewModel;
            viewModel.g = mediaItemsRepositoryA;
            MutableLiveData<b7> mutableLiveData = mediaSourceViewModel.c;
            viewModel.a = mutableLiveData;
            viewModel.b = e6.b(mutableLiveData, t8.b);
            viewModel.c = e6.b(viewModel.a, t8.c);
            viewModel.d = e6.b(playbackViewModel.e, t8.d);
            viewModel.e = e6.b(playbackViewModel.e, t8.e);
            viewModel.g.d.observe(fragmentActivityRequireActivity, new b0(viewModel, 3));
        }
        View viewInflate = layoutInflater.inflate(com.android.car.ui.R$layout.playback_fragment, viewGroup, false);
        this.g = new i8(viewInflate);
        PlaybackControlsActionBar playbackControlsActionBar = (PlaybackControlsActionBar) viewInflate.findViewById(com.android.car.ui.R$id.playback_controls);
        PlaybackViewModel playbackViewModel2 = this.d;
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        playbackControlsActionBar.t.d(playbackViewModel2, viewLifecycleOwner);
        playbackControlsActionBar.getContext();
        c3.a(viewLifecycleOwner, playbackViewModel2, playbackControlsActionBar.s, playbackControlsActionBar.u);
        final int i2 = 1;
        this.d.j.observe(getViewLifecycleOwner(), new k7(this, playbackControlsActionBar, 1));
        TextView textView = (TextView) viewInflate.findViewById(com.android.car.ui.R$id.app_name);
        LiveData<CharSequence> liveData = this.f.b;
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Objects.requireNonNull(textView);
        final int i3 = 2;
        liveData.observe(viewLifecycleOwner2, new i7(textView, 2));
        TextView textView2 = (TextView) viewInflate.findViewById(com.android.car.ui.R$id.title);
        LiveData<CharSequence> liveData2 = this.f.d;
        LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
        Objects.requireNonNull(textView2);
        liveData2.observe(viewLifecycleOwner3, new i7(textView2, 3));
        TextView textView3 = (TextView) viewInflate.findViewById(com.android.car.ui.R$id.subtitle);
        LiveData<CharSequence> liveData3 = this.f.e;
        LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
        Objects.requireNonNull(textView3);
        liveData3.observe(viewLifecycleOwner4, new i7(textView3, 4));
        final boolean z = getResources().getBoolean(com.android.car.ui.R$bool.use_media_source_logo_for_app_selector);
        final ImageView imageView = (ImageView) viewInflate.findViewById(com.android.car.ui.R$id.app_icon);
        final View viewFindViewById = viewInflate.findViewById(com.android.car.ui.R$id.app_selector_container);
        this.f.c.observe(getViewLifecycleOwner(), new Observer() { // from class: spi.q8
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                boolean z2 = z;
                View view = viewFindViewById;
                ImageView imageView2 = imageView;
                Bitmap bitmap = (Bitmap) obj;
                int i4 = PlaybackFragment.j;
                if (!z2) {
                    imageView2.setImageBitmap(bitmap);
                    return;
                }
                ImageView imageView3 = (ImageView) view.findViewById(R$id.app_selector);
                imageView3.setImageBitmap(bitmap);
                imageView3.setImageTintList(null);
                imageView2.setVisibility(8);
            }
        });
        this.f.f.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.p8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        Boolean bool = (Boolean) obj;
                        int i4 = PlaybackFragment.j;
                        Objects.requireNonNull(playbackFragment);
                        if (bool != null && playbackFragment.h != null) {
                            playbackFragment.h.c("PlaybackFragmentWidget", playbackFragment.d.j.getValue(), false);
                            break;
                        }
                        break;
                    case 1:
                        i8 i8Var = this.b.g;
                        uc.b(i8Var.a, 0, null);
                        uc.b(i8Var.b, 0, null);
                        break;
                    default:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.e.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                }
            }
        });
        this.c.c.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.p8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i2) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        Boolean bool = (Boolean) obj;
                        int i4 = PlaybackFragment.j;
                        Objects.requireNonNull(playbackFragment);
                        if (bool != null && playbackFragment.h != null) {
                            playbackFragment.h.c("PlaybackFragmentWidget", playbackFragment.d.j.getValue(), false);
                            break;
                        }
                        break;
                    case 1:
                        i8 i8Var = this.b.g;
                        uc.b(i8Var.a, 0, null);
                        uc.b(i8Var.b, 0, null);
                        break;
                    default:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.e.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                }
            }
        });
        viewInflate.findViewById(com.android.car.ui.R$id.playback_scrim).setOnClickListener(new View.OnClickListener(this) { // from class: spi.o8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i2) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        int i4 = PlaybackFragment.j;
                        playbackFragment.requireContext().startActivity(null);
                        break;
                    default:
                        PlaybackFragment playbackFragment2 = this.b;
                        if (!playbackFragment2.i) {
                            playbackFragment2.startActivity(new Intent("android.car.intent.action.MEDIA_TEMPLATE"));
                        }
                        break;
                }
            }
        });
        CrossfadeImageView crossfadeImageView = (CrossfadeImageView) viewInflate.findViewById(com.android.car.ui.R$id.album_background);
        int integer = fragmentActivityRequireActivity.getResources().getInteger(com.android.car.ui.R$integer.media_items_bitmap_max_size_px);
        Size size = new Size(integer, integer);
        this.e = new v4<>(v4.b.FOREGROUND, size, new r8(size, crossfadeImageView, 0));
        this.d.e.observe(getViewLifecycleOwner(), new Observer(this) { // from class: spi.p8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                switch (i3) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        Boolean bool = (Boolean) obj;
                        int i4 = PlaybackFragment.j;
                        Objects.requireNonNull(playbackFragment);
                        if (bool != null && playbackFragment.h != null) {
                            playbackFragment.h.c("PlaybackFragmentWidget", playbackFragment.d.j.getValue(), false);
                            break;
                        }
                        break;
                    case 1:
                        i8 i8Var = this.b.g;
                        uc.b(i8Var.a, 0, null);
                        uc.b(i8Var.b, 0, null);
                        break;
                    default:
                        PlaybackFragment playbackFragment2 = this.b;
                        MediaItemMetadata mediaItemMetadata = (MediaItemMetadata) obj;
                        playbackFragment2.e.d(playbackFragment2.getContext(), mediaItemMetadata != null ? mediaItemMetadata.g : null);
                        break;
                }
            }
        });
        viewFindViewById.setVisibility(8);
        viewFindViewById.setOnClickListener(new View.OnClickListener(this) { // from class: spi.o8
            public final /* synthetic */ PlaybackFragment b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        PlaybackFragment playbackFragment = this.b;
                        int i4 = PlaybackFragment.j;
                        playbackFragment.requireContext().startActivity(null);
                        break;
                    default:
                        PlaybackFragment playbackFragment2 = this.b;
                        if (!playbackFragment2.i) {
                            playbackFragment2.startActivity(new Intent("android.car.intent.action.MEDIA_TEMPLATE"));
                        }
                        break;
                }
            }
        });
        this.h = new a(fragmentActivityRequireActivity);
        return viewInflate;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        super.onDestroyView();
        this.a.disconnect();
        this.h = null;
    }
}
