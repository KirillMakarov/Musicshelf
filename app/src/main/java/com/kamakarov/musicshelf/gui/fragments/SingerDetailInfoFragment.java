package com.kamakarov.musicshelf.gui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.model.Singer;
import com.kamakarov.musicshelf.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class SingerDetailInfoFragment extends FragmentBase {
    private static final String SINGER_KEY = "singer_key";

    public static SingerDetailInfoFragment newInstance(Singer singer) {
        Bundle args = new Bundle();
        args.putParcelable(SINGER_KEY, singer);

        SingerDetailInfoFragment fragment = new SingerDetailInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Bind(R.id.singer_genres_singer_info)
    TextView genresTextView;

    @Bind(R.id.singer_description_singer_info)
    TextView descriptionTextView;

    @Bind(R.id.big_cover_singer_info)
    DraweeView singerBigImage;

    @Bind(R.id.singer_albums_songs_singer_info)
    TextView albumsAndSongTextVuew;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_singer_info, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = getArguments();
        Singer singer = bundle.getParcelable(SINGER_KEY);
        if (singer != null) {
            getActivity().setTitle(singer.getName());

            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setTapToRetryEnabled(true)
                    .setLowResImageRequest(ImageRequest.fromUri(singer.getCover().getSmall()))
                    .setImageRequest(ImageRequest.fromUri(singer.getCover().getBig()))
                    .build();

            singerBigImage.setController(controller);

            descriptionTextView.setText(singer.getDescription());

            String albums = getContext().getResources().getQuantityString(R.plurals.albums_plural, singer.getAlbums(), singer.getAlbums());
            String songs = getContext().getResources().getQuantityString(R.plurals.songs_plural, singer.getTracks(), singer.getTracks());
            String marker = getContext().getString(R.string.dot_marker);
            String albumsAndSongs = albums + "\t\t" + marker + "\t\t" + songs;
            albumsAndSongTextVuew.setText(albumsAndSongs);

            String genreString = StringUtil.concatenateWithComma(singer.getGenres());
            genresTextView.setText(genreString);

        }
    }
}
