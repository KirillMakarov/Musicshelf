package com.kamakarov.musicshelf.gui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.kamakarov.musicshelf.R;
import com.kamakarov.musicshelf.model.Singer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public final class SingerAdapter extends RecyclerView.Adapter<SingerAdapter.UnitViewHolder> {

    Context context;
    List<Singer> singerList;

    public SingerAdapter(Context context, List<Singer> singerList) {
        this.context = context;
        this.singerList = singerList;
    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.singer_item, parent, false);
        return new UnitViewHolder(v);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder holder, int position) {
        Singer singer = singerList.get(position);

        holder.singerName.setText(singer.getName());


        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(singer.getCover().getSmall())
                .build();
        holder.singerImage.setController(controller);

        // TODO: 27.03.16 Extract to utils:
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < singer.getGenres().size(); i++) {
            sb.append(singer.getGenres().get(i));
            if (i != singer.getGenres().size() - 1) {
                //if not last
                sb.append(", ");
            }
        }
        holder.singerGenres.setText(sb.toString());

        // TODO: 27.03.16 extract, support plural
        int albums = singer.getAlbums();
        int songs = singer.getTracks();
        String albumsAndSongs = albums + " альбом, " + songs + " песни";
        holder.singerAlbumsAndSongs.setText(albumsAndSongs);

    }

    @Override
    public int getItemCount() {
        return singerList.size();
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

        public UnitViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
