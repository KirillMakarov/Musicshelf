package com.kamakarov.musicshelf.gui.adapters;

import android.content.Context;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.core.MainApplication;
import com.kamakarov.musicshelf.gui.IIntentManager;
import com.kamakarov.musicshelf.gui.activities.MainActivity;
import com.kamakarov.musicshelf.gui.listeners.OnItemClickListenerInList;
import com.kamakarov.musicshelf.model.Cover;
import com.kamakarov.musicshelf.model.Singer;
import com.kamakarov.musicshelf.utils.StringUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;

public final class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.UnitViewHolder> implements OnItemClickListenerInList {

    @Inject
    IIntentManager intentManager;

    Context context;
    List<Singer> singerList;

    public SingerAdapter(Context context, List<Singer> singerList) {
        MainApplication.getComponent().inject(this);
        this.context = context;
        this.singerList = singerList;
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singer_item, parent, false);
        return new UnitViewHolder(v, this);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder holder, int position) {
        Singer singer = singerList.get(position);

        holder.singerName.setText(singer.getName());

        Cover cover = singer.getCover();
        String path = null;
        if (cover != null) {
            path = cover.getSmall();
        }

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(path)
                .build();
        holder.singerImage.setController(controller);

        String genreString = StringUtil.concatenateWithComma(singer.getGenres());
        holder.singerGenres.setText(genreString);

        int albums = singer.getAlbums();
        int songs = singer.getTracks();
        String albumText = context.getResources().getQuantityString(R.plurals.albums_plural, albums, albums);
        String songText = context.getResources().getQuantityString(R.plurals.songs_plural, songs, songs);
        String albumsAndSongs = albumText + ", " + songText;
        holder.singerAlbumsAndSongs.setText(albumsAndSongs);
    }

    @Override
    public int getItemCount() {
        return singerList.size();
    }


    @Override
    public void onClickPositionWithAnimation(int position, ActivityOptionsCompat activityOptionsCompat) {
        if (position >= 0 && position < singerList.size()) {
            Singer singer = singerList.get(position);
            intentManager.openDetailedInfoWithAnimation(context, singer, activityOptionsCompat);
        }
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.singer_name)
        TextView singerName;

        @Bind(R.id.singer_image)
        DraweeView singerImage;

        @Bind(R.id.singer_genres)
        TextView singerGenres;

        @Bind(R.id.singer_albums_songs)
        TextView singerAlbumsAndSongs;

        @BindString(R.string.transition_name_cover_view)
        String coverTransitionName;

        @BindString(R.string.transition_name_albums_view)
        String albumsTransitionName;

        @BindString(R.string.transition_name_genres_view)
        String genresTransitionName;

        public UnitViewHolder(View itemView, OnItemClickListenerInList listenerInList) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                ActivityOptionsCompat transitionActivityOptions = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    Pair<View, String> coverPair = Pair.create(singerImage, coverTransitionName);
//                    Pair<View, String> genrePair = Pair.create(singerGenres, genresTransitionName);
//                    Pair<View, String> albumsPair = Pair.create(singerAlbumsAndSongs, albumsTransitionName);
                    //just image transition is looked better

                    transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation((MainActivity) context, coverPair);
                }
                listenerInList.onClickPositionWithAnimation(getAdapterPosition(), transitionActivityOptions);
            });

        }
    }
}
